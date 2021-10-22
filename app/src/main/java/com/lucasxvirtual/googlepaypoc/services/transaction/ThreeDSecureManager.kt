package com.trustpayments.mobile.core.services.transaction

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.cardinalcommerce.cardinalmobilesdk.Cardinal
import com.cardinalcommerce.cardinalmobilesdk.enums.CardinalEnvironment
import com.cardinalcommerce.cardinalmobilesdk.enums.CardinalRenderType
import com.cardinalcommerce.cardinalmobilesdk.enums.CardinalUiType
import com.cardinalcommerce.cardinalmobilesdk.models.CardinalActionCode
import com.cardinalcommerce.cardinalmobilesdk.models.CardinalConfigurationParameters
import com.cardinalcommerce.cardinalmobilesdk.models.ValidateResponse
import com.cardinalcommerce.cardinalmobilesdk.services.CardinalInitService
import com.cardinalcommerce.cardinalmobilesdk.services.CardinalValidateReceiver
import com.lucasxvirtual.googlepaypoc.models.DeviceSafetyWarning
import com.lucasxvirtual.googlepaypoc.services.transaction.ActivityResultProvider
import com.lucasxvirtual.googlepaypoc.services.transaction.ThreeDSecureError
import com.lucasxvirtual.googlepaypoc.ui.*
import com.lucasxvirtual.googlepaypoc.ui.ThreeDSecureRequest
import com.lucasxvirtual.googlepaypoc.ui.ThreeDSecureResponse
import com.lucasxvirtual.googlepaypoc.ui.ThreeDSecureWebActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

internal class ThreeDSecureManager private constructor(
        private val applicationContext: Context,
        private val isLive: Boolean,
        private val cardinalStyleManager: CardinalStyleManager?,
        private val cardinalDarkThemeStyleManager: CardinalStyleManager?
) {
    internal var cardinal: Cardinal? = null
        @JvmSynthetic get
        @JvmSynthetic set

    internal var isDarkThemeForced: Boolean? = null
        @JvmSynthetic get
        @JvmSynthetic set

    companion object {
        @JvmSynthetic
        internal const val WEBVIEWACTIVITY_REQUEST_CODE = 12345

        @JvmSynthetic
        internal fun getInstance(
                context: Context,
                isLive: Boolean,
                cardinalStyleManager: CardinalStyleManager?,
                cardinalDarkModeStyleManager: CardinalStyleManager?
        ) = ThreeDSecureManager(context, isLive, cardinalStyleManager, cardinalDarkModeStyleManager)
    }

    internal val deviceSafetyWarnings: List<DeviceSafetyWarning>
        @JvmSynthetic
        get() = cardinal?.warnings?.mapNotNull {
            when (it.id) {
                "SW01" -> DeviceSafetyWarning.DeviceRooted
                "SW02" -> DeviceSafetyWarning.SdkIntegrityTampered
                "SW03" -> DeviceSafetyWarning.EmulatorIsUsed
                "SW04" -> DeviceSafetyWarning.DebuggerIsAttached
                "SW06" -> DeviceSafetyWarning.AppInstalledFromUntrustedSource
                else -> null
            }
        } ?: emptyList()

    @JvmSynthetic
    internal fun createSession() {

        val newCardinal = Cardinal.getInstance().apply {
            val cardinalConfigurationParameters = CardinalConfigurationParameters()
            cardinalConfigurationParameters.environment = if(isLive) CardinalEnvironment.PRODUCTION else CardinalEnvironment.STAGING
            cardinalConfigurationParameters.requestTimeout = 8000
            cardinalConfigurationParameters.challengeTimeout = 8
            cardinalConfigurationParameters.isEnableLogging = false

            val renderType = JSONArray()
            renderType.put(CardinalRenderType.OTP)
            renderType.put(CardinalRenderType.SINGLE_SELECT)
            renderType.put(CardinalRenderType.MULTI_SELECT)
            renderType.put(CardinalRenderType.OOB)
            renderType.put(CardinalRenderType.HTML)
            cardinalConfigurationParameters.renderType = renderType

            cardinalConfigurationParameters.uiType = CardinalUiType.BOTH

            isDarkThemeForced?.let {
                if (it) {
                    CardinalStyleManager.applyDarkTheme(
                        applicationContext, cardinalDarkThemeStyleManager, cardinalConfigurationParameters
                    )
                } else {
                    CardinalStyleManager.applyLightTheme(applicationContext, cardinalStyleManager, cardinalConfigurationParameters)
                }
            } ?: run {
                if (CardinalStyleManager.isDarkThemeSetOnDevice(applicationContext)) {
                    CardinalStyleManager.applyDarkTheme(
                        applicationContext, cardinalDarkThemeStyleManager, cardinalConfigurationParameters
                    )
                } else {
                    CardinalStyleManager.applyLightTheme(applicationContext, cardinalStyleManager, cardinalConfigurationParameters)
                }
            }

            configure(applicationContext, cardinalConfigurationParameters)
        }

        cardinal = newCardinal
    }

    @JvmSynthetic
    internal suspend fun initSession(jwtToken: String) = suspendCoroutine<InitializationResult> { continuation ->
        val localCardinal = this.cardinal
        if(localCardinal == null) {
            continuation.resume(InitializationResult.Failure(ThreeDSecureError.Unknown))
            return@suspendCoroutine
        }

        localCardinal.init(jwtToken, object: CardinalInitService {
            override fun onSetupCompleted(consumerSessionId: String?) {
                //this method will be called when init succeeds
                if(consumerSessionId != null) {
                    continuation.resume(InitializationResult.Success(consumerSessionId))
                } else {
                    continuation.resume(InitializationResult.Failure(ThreeDSecureError.Unknown))
                }
            }

            override fun onValidated(validateResponse: ValidateResponse?, serverJwt: String?) {
                //this method will be called when init fails
                if(validateResponse != null) {
                    continuation.resume(InitializationResult.Failure(
                        ThreeDSecureError.GeneralError(validateResponse.errorNumber)))
                } else {
                    continuation.resume(InitializationResult.Failure(ThreeDSecureError.Unknown))
                }
            }
        })
    }

    @JvmSynthetic
    internal suspend fun executeSession(
        version: String, transactionId: String, payload: String, acsUrl: String, termUrl: String,
        activity: Activity, activityResultProvider: ActivityResultProvider
    ): ExecutionResult {

        val versionParts = version.split(".")

        val versionInt = try {
            versionParts[0].toInt()
        } catch (ex: Exception) {
            return ExecutionResult.Failure(ThreeDSecureError.IncorrectVersion)
        }

        if(versionInt == 1) {
            return executeV1Session(transactionId, payload, acsUrl, termUrl, activity, activityResultProvider)
        } else if(versionInt == 2) {
            return executeV2Session(transactionId, payload, activity)
        } else {
            return ExecutionResult.Failure(ThreeDSecureError.IncorrectVersion)
        }
    }

    private suspend fun executeV1Session(
        transactionId: String, payload: String, acsUrl: String, termUrl: String,
        activity: Activity, activityResultProvider: ActivityResultProvider
    ): ExecutionResult = withContext(Dispatchers.Default) {

        val uri = Uri.parse(acsUrl).buildUpon()
            .appendQueryParameter("PaReq", payload)
            .appendQueryParameter("TermUrl", termUrl)
            .appendQueryParameter("MD", transactionId)
            .build()

        val headers = hashMapOf("Content-Type" to "x-www-form-urlencoded")

        val intent = Intent(activity, ThreeDSecureWebActivity::class.java)
        intent.putExtra(ThreeDSecureWebActivity.EXTRA_REQUEST, ThreeDSecureRequest(
            "POST", uri.toString(), headers, termUrl
        ))

        activity.startActivityForResult(intent, WEBVIEWACTIVITY_REQUEST_CODE)

        var activityResult: ThreeDSecureWebActivityResult? = null
        while (activityResult?.requestCode != WEBVIEWACTIVITY_REQUEST_CODE) {
            activityResult = activityResultProvider.result
        }

        if(activityResult.resultCode == ThreeDSecureWebActivity.RESULT_SUCCESSFUL) {
            return@withContext activityResult.bundle?.let {
                val url = (it.getSerializable(ThreeDSecureWebActivity.EXTRA_RESPONSE) as ThreeDSecureResponse).url

                val responseUri = Uri.parse(url)
                val md = responseUri.getQueryParameter("MD")
                val pares = responseUri.getQueryParameter("PaRes")

                if(md != null && pares != null) {
                    if(md == transactionId) {
                        return@withContext ExecutionResult.SuccessV1(pares)
                    } else {
                        return@withContext ExecutionResult.Failure(ThreeDSecureError.IncorrectTransactionId)
                    }
                } else {
                    return@withContext ExecutionResult.Failure(ThreeDSecureError.EmptyChallengeResult)
                }
            } ?: ExecutionResult.Failure(ThreeDSecureError.EmptyChallengeResult)
        } else {
            return@withContext ExecutionResult.Failure(ThreeDSecureError.ChallengeCanceled)
        }
    }

    private suspend fun executeV2Session(transactionId: String, payload: String, activity: Activity) = suspendCoroutine<ExecutionResult> { continuation ->
        val localCcardinal = this.cardinal
        if(localCcardinal == null) {
            continuation.resume(ExecutionResult.Failure(ThreeDSecureError.Unknown))
            return@suspendCoroutine
        }

        localCcardinal.cca_continue(transactionId, payload, activity, object: CardinalValidateReceiver {
            override fun onValidated(context: Context?, validationResponse: ValidateResponse?, responseJwt: String?) {
                when(validationResponse?.actionCode) {
                    CardinalActionCode.SUCCESS -> {
                        releaseCardinal()
                        continuation.resume(ExecutionResult.SuccessV2(responseJwt!!))
                    }
                    CardinalActionCode.NOACTION -> {
                        releaseCardinal()
                        continuation.resume(ExecutionResult.SuccessV2(responseJwt!!))
                    }
                    null -> continuation.resume(ExecutionResult.Failure(ThreeDSecureError.Unknown))
                    else -> continuation.resume(ExecutionResult.Failure(
                        ThreeDSecureError.GeneralError(validationResponse.errorNumber)))
                }
            }
        })
    }

    private fun releaseCardinal() {
        cardinal?.cleanup()
        cardinal = null
    }

    internal sealed class InitializationResult {
        data class Success(val result: String): InitializationResult()
        data class Failure(val error: ThreeDSecureError): InitializationResult()
    }

    internal sealed class ExecutionResult {
        data class SuccessV1(val result: String): ExecutionResult()
        data class SuccessV2(val result: String): ExecutionResult()
        data class Failure(val error: ThreeDSecureError): ExecutionResult()
    }
}