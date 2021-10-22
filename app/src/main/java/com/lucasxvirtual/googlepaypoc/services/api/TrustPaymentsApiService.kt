package com.lucasxvirtual.googlepaypoc.services.api

import android.content.Context
import android.os.Build
import com.lucasxvirtual.googlepaypoc.models.api.jwt.JwtToken
import com.lucasxvirtual.googlepaypoc.models.api.request.TransactionRequest
import com.lucasxvirtual.googlepaypoc.models.api.request.WebserviceRequest
import com.lucasxvirtual.googlepaypoc.models.api.response.RequestType
import com.lucasxvirtual.googlepaypoc.models.api.response.TransactionResponse
import com.lucasxvirtual.googlepaypoc.models.api.response.WebserviceResponse
import com.lucasxvirtual.googlepaypoc.services.getGson
import okhttp3.CertificatePinner
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit

internal class TrustPaymentsApiService private constructor(
    context: Context,
    gatewayType: TrustPaymentsGatewayType
) {
    companion object {
        @JvmSynthetic
        internal fun getInstance(context: Context, gatewayType: TrustPaymentsGatewayType)
                = TrustPaymentsApiService(context, gatewayType)
    }

    private val retrofitApi: TrustPaymentsApi

    private val MAX_RETRY_NUMBER = 20
    private val MAX_RETRY_TIMEOUT_SEC = 40
    private val CONNECT_TIMEOUT_SEC = 5L
    private val READ_WRITE_TIMEOUT_SEC = 60L

    private val contentFormatVersion = "1.00"
    private val acceptFormatVersion = "2.00"
    private val sdkName = "MSDK"
    private val versionInfo
            = "$sdkName::${KotlinVersion.CURRENT}::${context.packageManager.getPackageInfo(context.packageName, 0).versionName}::${Build.VERSION.RELEASE}"

    private val gson = getGson()

    init {
        val headerInterceptor = object: Interceptor {
            override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                val newRequest = chain.request().newBuilder().addHeader("Accept", "application/json").build()
                return chain.proceed(newRequest)
            }
        }

        val okHttpClient = OkHttpClient.Builder()
            .applyPinning(CertificatePinner.Builder()
                        .add("webservices.securetrading.net", "sha256/kCv4KV+TUcfQ7XFk1Hk4oF2JlFRk9fObpVuZCKCZ/mk=")
                        .add("webservices.securetrading.net", "sha256/yMZhDu5hIsQaSd5wdC0kIxImZ2BpJPz5YGXasZe0IGQ=")
                        .build())
            .apply {
                addInterceptor(HttpLoggingInterceptor(object: HttpLoggingInterceptor.Logger {
                    override fun log(message: String) {
                    }
                }).apply { level = HttpLoggingInterceptor.Level.BODY })
            }
            .readTimeout(READ_WRITE_TIMEOUT_SEC, TimeUnit.SECONDS)
            .writeTimeout(READ_WRITE_TIMEOUT_SEC, TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIMEOUT_SEC, TimeUnit.SECONDS)
            .addInterceptor(headerInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(gatewayType.url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        retrofitApi = retrofit.create(TrustPaymentsApi::class.java)
    }

    @JvmSynthetic
    internal suspend fun initializeSession(
        sessionId: String, username: String, jwt: String): Result {
        val paymentRequest =
            TransactionRequest(
                sessionId,
                requestTypes = arrayOf(
                    RequestType.JsInit
                )
            )
        val webRequest =
            WebserviceRequest(
                username,
                jwt,
                contentFormatVersion,
                versionInfo,
                acceptFormatVersion,
                arrayOf(paymentRequest)
            )

        return performRequest(webRequest)
    }

    @JvmSynthetic
    internal suspend fun performTransaction(
        sessionId: String, username: String, jwt: String, cacheToken: String?,
        cardNumber: String?, cardExpiryDate: String?, cardSecurityCode: String?,
        threeDResponse: String? = null, pares: String? = null
    ): Result {
        val paymentRequest =
            TransactionRequest(
                sessionId,
                threeDResponse = threeDResponse,
                pares = pares,
                cacheToken = cacheToken,
                cardNumber = cardNumber,
                cardExpiryDate = cardExpiryDate,
                cardSecurityCode = cardSecurityCode
            )
        val webRequest =
            WebserviceRequest(
                username,
                jwt,
                contentFormatVersion,
                versionInfo,
                acceptFormatVersion,
                arrayOf(paymentRequest)
            )

        return performRequest(webRequest)
    }

    private suspend fun performRequest(request: WebserviceRequest): Result {
        val startTimeoutMillis = System.currentTimeMillis()
        var numberOfRetries = 0

        while(numberOfRetries < MAX_RETRY_NUMBER && (System.currentTimeMillis() + CONNECT_TIMEOUT_SEC * 1000 < startTimeoutMillis + MAX_RETRY_TIMEOUT_SEC * 1000)) {
            val response = try {
                retrofitApi.sendJwtRequest(request)
            } catch (ex: Exception) {
                return when(ex) {
                    is SocketTimeoutException,
                    is ConnectException -> {
                        //this can happen if there is networking error (connect)
                        numberOfRetries++
                        Result.Failure
                    }
                    //this can happen if there is networking error (not connect) or parsing error (no jwt found, or response structure is incorrect)
                    else -> Result.Failure
                }
            }

            //at this point either there is a HTTP status code error (in which case response was not yet deserialized)
            //or HTTP status code is ok, and response was successfully deserialized
            //we are treating single transaction deserialization errors as 'correct' responses, returning TransactionResponse.ParsingError
            return validateResponse(response)
        }

        //maximum number of retries has been reached
        return Result.Failure
    }

    private fun validateResponse(response: retrofit2.Response<WebserviceResponse>): Result {
        if(!response.isSuccessful) {
            return Result.Failure
        }

        val body = response.body()

        body ?: return Result.Failure

        val newJwt = JwtToken(body.body.payload.jwt)
        if(!newJwt.isValid) {
            return Result.Failure
        }

        return Result.Success(body.jwt, newJwt, body.body.payload.transactionResponses.toList())
    }

    internal sealed class Result {
        data class Success(
                val responseJwt: JwtToken,
                val newJwt: JwtToken,
                val transactionResponses: List<TransactionResponse>): Result()
        object Failure: Result()
    }
}

private interface TrustPaymentsApi {
    @POST("jwt/")
    suspend fun sendJwtRequest(@Body request: WebserviceRequest): Response<WebserviceResponse>
}

enum class TrustPaymentsGatewayType(val url: String) {
    EU("https://webservices.securetrading.net/"),
    EU_BACKUP("https://webservices2.securetrading.net/"),
    US("https://webservices.securetrading.us/")
}

fun OkHttpClient.Builder.applyPinning(pinner: CertificatePinner): OkHttpClient.Builder {
    return this
}