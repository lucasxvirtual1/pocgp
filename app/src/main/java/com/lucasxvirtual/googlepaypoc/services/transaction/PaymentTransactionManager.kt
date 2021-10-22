package com.lucasxvirtual.googlepaypoc.services.transaction

import android.app.Activity
import android.content.Context
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.annotation.WorkerThread
import com.google.gson.Gson
import com.lucasxvirtual.googlepaypoc.PaymentSession
import com.lucasxvirtual.googlepaypoc.models.DeviceSafetyWarning
import com.lucasxvirtual.googlepaypoc.models.api.jwt.JwtToken
import com.lucasxvirtual.googlepaypoc.models.api.response.CustomerOutput
import com.lucasxvirtual.googlepaypoc.models.api.response.RequestType
import com.lucasxvirtual.googlepaypoc.services.api.TrustPaymentsApiService
import com.lucasxvirtual.googlepaypoc.services.api.TrustPaymentsGatewayType
import com.lucasxvirtual.googlepaypoc.ui.CardinalStyleManager
import com.lucasxvirtual.googlepaypoc.models.api.response.ResponseErrorCode
import com.trustpayments.mobile.core.services.transaction.ThreeDSecureManager
import kotlinx.coroutines.runBlocking


class PaymentTransactionManager(
        context: Context,
        gatewayType: TrustPaymentsGatewayType,
        isCardinalLive: Boolean,
        private val merchantUsername: String,
        cardinalStyleManager: CardinalStyleManager? = null,
        cardinalDarkThemeStyleManager: CardinalStyleManager? = null
) {
    internal var isCardinalLive: Boolean = isCardinalLive
        @JvmSynthetic get
        @JvmSynthetic set

    /**
     * Parameter indicating whenever a merchant gives an option inside his app to override a default
     * system theme (e.g. form the settings inside an app). In that case, to have a possibility of
     * applying correct theme to the Cardinal challenge pop-up, this param has to be set to:
     *
     * * _true_ - when the dark theme is set form the local app settings,
     * * _false_ - when the light theme is set,
     * * _null_  - in case of controlling the theme by the system.
     *
     * It's a merchant responsibility to handle correct value assignment based on the changes inside
     * his app settings.
     */
    var isDarkThemeForced: Boolean? = null
        set(value) {
            field = value
            threeDSecureManager.isDarkThemeForced = field
        }

    private val apiService: TrustPaymentsApiService
        = TrustPaymentsApiService.getInstance(context, gatewayType)
    private val threeDSecureManager: ThreeDSecureManager
        = ThreeDSecureManager.getInstance(context, isCardinalLive, cardinalStyleManager, cardinalDarkThemeStyleManager)

    private val gson by lazy { Gson() }

    //TODO can multiple transactions be done or do we need a 'completed' flag on session after performing single transaction?
    @JvmSynthetic
    fun createSession(
        jwtProvider: () -> String,
        cardPan: String? = null,
        cardExpiryDate: String? = null,
        cardSecurityCode: String? = null,
        threeDSecureV1_pares: String? = null,
        threeDSecureV2_threeDResponse: String? = null
    ) = PaymentSession(
        jwtProvider,
        cardPan,
        cardExpiryDate,
        cardSecurityCode,
        threeDSecureV1_pares,
        threeDSecureV2_threeDResponse
    )

    fun createSession(
        jwt: String,
        cardPan: String?,
        cardExpiryDate: String?,
        cardSecurityCode: String?,
        threeDSecureV1_pares: String?,
        threeDSecureV2_threeDResponse: String?
    ) = PaymentSession(
        { jwt },
        cardPan,
        cardExpiryDate,
        cardSecurityCode,
        threeDSecureV1_pares,
        threeDSecureV2_threeDResponse
    )

    @WorkerThread
    fun executeSession(@NonNull session: PaymentSession,
                       @Nullable activity: Activity?,
                       @Nullable activityResultProvider: ActivityResultProvider?): Response {
        return runBlocking { executeSession(session, activity?.run {{ this }} as (() -> Activity)?, activityResultProvider) }
    }

    @JvmSynthetic
    suspend fun executeSession(
            session: PaymentSession,
            activityProvider: (() -> Activity)? = null,
            activityResultProvider: ActivityResultProvider? = null): Response {

        session.intermediateJwt = session.jwtProvider()

        var requests = try {
            parsePayload(jwt = session.intermediateJwt).requestTypes
        } catch (ex: Exception) {
            return finishExecution(
                session,
                Response(error = Error.ParsingError("Failed to extract request types from given JWT")))
        }

        if (requests.isEmpty()) {
            return finishExecution(
                session,
                Response(error = Error.ParsingError("List of request types in given JWT is empty")))
        }

        if(requests.any { it == RequestType.ThreeDQuery }) {
            if(activityProvider == null) {
                return finishExecution(
                    session,
                    Response(error = Error.DeveloperError.ActivityProviderRequired))
            }
            if(activityResultProvider == null) {
                return finishExecution(
                    session,
                    Response(error = Error.DeveloperError.ActivityResultProviderRequired))
            }
        }

        //Create Cardinal session to be able to check safety warnings
        threeDSecureManager.createSession()
        checkForSafetyWarnings()?.run {
            return finishExecution(
                session,
                Response(error = this))
        }

        //Initialize session only if there's a 3DQuery request on the list
        if(requests.any { it == RequestType.ThreeDQuery }) {
            //If initialization returned any errors, return them here
            val result = initializeSession(session)
            if(result != null) {
                return finishExecution(
                    session,
                    Response(error = result))
            }

            //Deserialize request types again using JWT returned for JSINIT
            requests = try {
                parsePayload(jwt = session.intermediateJwt).requestTypes
            } catch (ex: Exception) {
                return finishExecution(
                    session,
                    Response(error = Error.ParsingError("Failed to extract request types from returned JWT")))
            }

            if (requests.isEmpty()) {
                return finishExecution(
                    session,
                    Response(error = Error.ParsingError("List of request types in returned JWT is empty")))
            }
        }

        val firstResponse = executeSessionPart(session)
        if (firstResponse !is TrustPaymentsApiService.Result.Success) {
            return finishExecution(
                session,
                Response(error = Error.HttpFailure))
        }

        //Find response with customerOutput set
        val customerOutputResponse = firstResponse.transactionResponses.find { it.customerOutput != null }
        val threeDQueryResponse = when (customerOutputResponse?.customerOutput) {
            CustomerOutput.ThreeDRedirect -> customerOutputResponse
            else -> return finishExecution(
                session,
                Response(listOf(firstResponse.responseJwt.token)))
        }

        //Parse payload of first response to later check if second request will be needed
        //or return error here if parsing fails
        val firstResponsePayload = try {
            val payload = parsePayload(jwt = firstResponse.newJwt.token)
            payload.validate()
            payload
        } catch (ex: Exception) {
            return finishExecution(
                session,
                Response(listOf(firstResponse.responseJwt.token), error = Error.ParsingError("Failed to parse server response")))
        }

        //Challenge requested, execute 3DS session
        val cardinalSessionResponse = threeDSecureManager.executeSession(
            threeDQueryResponse.threeDVersion!!,
            threeDQueryResponse.acquirerTransactionReference!!,
            threeDQueryResponse.threeDPayload!!,
            threeDQueryResponse.acsUrl!!,
            firstResponsePayload.termurl,
            activityProvider!!.invoke(),
            activityResultProvider!!)

        val threeDResponse = when (cardinalSessionResponse) {
            is ThreeDSecureManager.ExecutionResult.SuccessV1 -> ThreeDResponse(pares = cardinalSessionResponse.result)
            is ThreeDSecureManager.ExecutionResult.SuccessV2 -> ThreeDResponse(threeDResponse = cardinalSessionResponse.result)
            is ThreeDSecureManager.ExecutionResult.Failure -> {
                return finishExecution(
                    session,
                    Response(listOf(firstResponse.responseJwt.token), error = Error.ThreeDSFailure(cardinalSessionResponse.error)))
                }
        }

        //If 3DS challenge completed successfully and there are no more requests to process, finish here
        if(firstResponsePayload.requestTypes.isEmpty()) {
            //No more requests to perform
            return finishExecution(
                session,
                Response(listOf(firstResponse.responseJwt.token), threeDResponse))
        }

        //Execute remaining requests

        //Update JWT
        session.intermediateJwt = firstResponse.newJwt.token

        val secondResponse = when (cardinalSessionResponse) {
            is ThreeDSecureManager.ExecutionResult.SuccessV1 -> {
                executeSessionPart(session, pares = cardinalSessionResponse.result)
            }
            is ThreeDSecureManager.ExecutionResult.SuccessV2 -> {
                executeSessionPart(session, threeDSecureJwt = cardinalSessionResponse.result)
            }
            else -> {
                executeSessionPart(session)
            }
        }

        if (secondResponse !is TrustPaymentsApiService.Result.Success) {
            return finishExecution(
                session,
                Response(listOf(firstResponse.responseJwt.token), threeDResponse, Error.HttpFailure))
        }

        return finishExecution(
            session,
            Response(listOf(firstResponse.responseJwt.token, secondResponse.responseJwt.token), threeDResponse))
    }

    private fun finishExecution(session: PaymentSession, response: Response): Response {
        session.intermediateJwt = ""
        return response
    }

    private suspend fun executeSessionPart(
            session: PaymentSession,
            threeDSecureJwt: String? = null, pares: String? = null): TrustPaymentsApiService.Result {

        return apiService.performTransaction(
            session.sessionId, merchantUsername, session.intermediateJwt, session.cacheJwt,
            session.cardPan, session.cardExpiryDate, session.cardSecurityCode,
            threeDSecureJwt, pares)
    }

    private suspend fun initializeSession(session: PaymentSession): Error? {
        val result = apiService.initializeSession(
            session.sessionId, merchantUsername, session.intermediateJwt)

        if(result is TrustPaymentsApiService.Result.Success && result.transactionResponses.size == 1) {
            //if result seems correct

            val jsInitResponse = result.transactionResponses.first()

            if(jsInitResponse.errorCode == ResponseErrorCode.Ok && jsInitResponse.threeDInit != null) {
                //always replace JWT with new JWT after JSINIT request
                session.intermediateJwt = result.newJwt.token
                session.cacheJwt = jsInitResponse.cacheToken

                //TODO what to do if user does not execute the session?
                val sessionResult = threeDSecureManager.initSession(jsInitResponse.threeDInit)
                if(sessionResult is ThreeDSecureManager.InitializationResult.Failure) {
                    return Error.InitializationFailure(result.responseJwt.token, sessionResult.error)
                } else {
                    return null
                }
            } else {
                return Error.InitializationFailure(result.responseJwt.token)
            }
        } else {
            return Error.InitializationError
        }
    }

    private fun checkForSafetyWarnings(): Error.SafetyError? {
        val localWarnings = threeDSecureManager.deviceSafetyWarnings

        if(isCardinalLive) {
            //for live environment fail if any of these critical warnings found
            val criticalWarnings = localWarnings.filter {
                it is DeviceSafetyWarning.DeviceRooted ||
                it is DeviceSafetyWarning.EmulatorIsUsed ||
                it is DeviceSafetyWarning.DebuggerIsAttached ||
                it is DeviceSafetyWarning.AppInstalledFromUntrustedSource
            }

            return if(criticalWarnings.isNotEmpty()) {
                return Error.SafetyError(criticalWarnings)
            } else {
                return null
            }
        } else if(localWarnings.firstOrNull { it is DeviceSafetyWarning.DeviceRooted } != null &&
                  localWarnings.firstOrNull { it is DeviceSafetyWarning.EmulatorIsUsed} == null) {
            //for non-live environment fail only if device is rooted and device is not an emulator
            //this is because some emulators are rooted by default

            return Error.SafetyError(listOf(DeviceSafetyWarning.DeviceRooted))
        } else {
            return null
        }
    }

    private fun parsePayload(jwt: String): JwtPayload {
        val token = JwtToken(jwt)
        val body = gson.fromJson(token.body, JwtBody::class.java)

        return body.payload
    }

    data class Response(val responseJwtList: List<String> = emptyList(), val threeDResponse: ThreeDResponse? = null, val error: Error? = null)

    private class JwtBody(payload: JwtPayload) {
        internal val payload = payload
            @JvmSynthetic get
    }

    private class JwtPayload private constructor(
        private val requesttypedescriptions: List<String>,
        termurl: String
    ) {
        internal val termurl = termurl
            @JvmSynthetic get

        @JvmSynthetic
        internal fun validate() {
            //Accessing requestTypes causes conversion of raw request types to PaymentRequestType,
            //if any value fails to be assigned to the type, we consider request types to contain at least one illegal value
            val validation = requestTypes
        }

        internal val requestTypes: List<RequestType>
            @JvmSynthetic get() = requesttypedescriptions.map {
                when (it) {
                    RequestType.JsInit.serializedName -> RequestType.JsInit
                    RequestType.Error.serializedName -> RequestType.Error
                    RequestType.AccountCheck.serializedName -> RequestType.AccountCheck
                    RequestType.Auth.serializedName -> RequestType.Auth
                    RequestType.Subscription.serializedName -> RequestType.Subscription
                    RequestType.ThreeDQuery.serializedName -> RequestType.ThreeDQuery
                    RequestType.RiskDec.serializedName -> RequestType.RiskDec
                    RequestType.CacheTokenise.serializedName -> RequestType.CacheTokenise
                    else -> throw IllegalArgumentException("Unknown request type: $it")
                }
            }
    }
}

data class ThreeDResponse(
    val pares: String? = null,
    val threeDResponse: String? = null
)

sealed class Error {
    /**
     * Errors representing illegal states on payment session initialization and execution. They
     * indicate merchant's development errors, which should never occur on production environment.
     */
    sealed class DeveloperError(val message: String): Error() {
        object ActivityProviderRequired: DeveloperError("Providing activityProvider parameter is mandatory if request list contains THREEDQUERY")
        object ActivityResultProviderRequired: DeveloperError("Providing activityResultProvider parameter is mandatory if request list contains THREEDQUERY")
    }

    /**
     * Generic error was returned for initialization or Cardinal initialization.
     */
    object InitializationError: Error()

    /**
     * Error or ParsingError was returned as TransactionResponse for initialization.
     */
    data class InitializationFailure(val jwt: String, val initializationError: ThreeDSecureError? = null): Error()

    /**
     * Error returned when any safety warnings have been detected by Cardinal SDK
     */
    data class SafetyError(val errors: List<DeviceSafetyWarning>): Error()

    /**
     * Error returned when parsing JWT or customerOutput fails
     */
    data class ParsingError(val errorMessage: String = ""): Error()

    /**
     * Error returned when HTTP communication fails
     */
    object HttpFailure: Error()

    /**
     * Error returned when 3DS challenge fails
     */
    data class ThreeDSFailure(val threeDSError: ThreeDSecureError): Error()
}