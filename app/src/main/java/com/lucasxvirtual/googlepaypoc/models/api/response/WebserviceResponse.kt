package com.lucasxvirtual.googlepaypoc.models.api.response

import com.google.gson.annotations.SerializedName
import com.lucasxvirtual.googlepaypoc.models.api.jwt.JwtToken

internal data class WebserviceResponse(
        val jwt: JwtToken,
        val header: String,
        val body: Body,
        val signature: String
) {
    data class Body(
        val iat: Long,
        val aud: String,
        val payload: Payload
    ) {
        data class Payload(
            val requestReference: String,
            val version: String,
            val jwt: String,
            val secRand: String,
            @SerializedName("response") val transactionResponses: List<TransactionResponse>
        )
    }
}

data class TransactionResponse(
        val errorCode: ResponseErrorCode,
        val errorMessage: String, //always returned, "Ok" if successful
        val errorData: Array<String>? = null, //may be empty if no error

        val cacheToken: String? = null,
        val threeDInit: String? = null,

        val transactionReference: String? = null,

        val requestTypeDescription: RequestType? = null,

        val settleStatus: ResponseSettlementStatus? = null,
        val liveStatus: ResponseLiveStatus? = null,
        val customerOutput: CustomerOutput? = null,

        val baseAmount: Long? = null, //may be empty if ACCOUNTCHECK
        val currencyISO3a: String? = null, //may be empty if ACCOUNTCHECK

        val maskedPan: String? = null,
        val paymentTypeDescription: String? = null,

        val cavv: String? = null,
        val eci: String? = null,
        val enrolled: String? = null,
        val status: String? = null,
        val xid: String? = null,

        val fraudControlShieldStatusCode: String? = null, //RISKDEC
        val fraudControlResponseCode: String? = null, //RISKDEC

        val acquirerTransactionReference: String? = null, //TODO rework into separate response subtypes
        val acsUrl: String? = null, //TODO rework into separate response subtypes
        val threeDPayload: String? = null, //TODO rework into separate response subtypes
        val threeDVersion: String? = null //TODO rework into separate response subtypes
)

enum class RequestType(val serializedName: String) {
    JsInit("JSINIT"),
    Error("ERROR"),
    AccountCheck("ACCOUNTCHECK"),
    Auth("AUTH"),
    Subscription("SUBSCRIPTION"),
    ThreeDQuery("THREEDQUERY"),
    RiskDec("RISKDEC"),
    CacheTokenise("CACHETOKENISE")
}

enum class ResponseSettlementStatus(val code: Int) {
    Pending(0),
    PendingManualOverride(1),
    Settling(10),
    Settled(100),
    Suspended(2),
    Cancelled(3),

    Other(-1)
}

enum class ResponseLiveStatus(val value: Int) {
    Live(1),
    NotLive(0),

    Other(-1)
}

enum class CustomerOutput(val value: String) {
    Result("RESULT"),
    Redirect("REDIRECT"),
    ThreeDRedirect("THREEDREDIRECT"),
    CurrencySelection("CURRENCYSELECTION"),
    TryAgain("TRYAGAIN")
}