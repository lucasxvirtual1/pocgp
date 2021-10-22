package com.lucasxvirtual.googlepaypoc.models.api.request

import com.google.gson.annotations.SerializedName
import com.lucasxvirtual.googlepaypoc.models.api.response.RequestType

internal data class TransactionRequest(
        val requestId: String,
        val threeDResponse: String? = null,
        val pares: String? = null,
        val cacheToken: String? = null,
        @SerializedName("requesttypedescriptions") val requestTypes: Array<RequestType>? = null,

        @SerializedName("pan") val cardNumber: String? = null,
        @SerializedName("securitycode") val cardSecurityCode: String? = null,
        @SerializedName("expirydate") val cardExpiryDate: String? = null
)