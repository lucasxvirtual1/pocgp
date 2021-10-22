package com.lucasxvirtual.googlepaypoc.models.api.request

import com.google.gson.annotations.SerializedName
import com.lucasxvirtual.googlepaypoc.models.api.request.TransactionRequest

internal data class WebserviceRequest(
    val alias: String,
    val jwt: String,
    val version: String,
    val versionInfo: String,
    val acceptCustomerOutput: String,
    @SerializedName("request") val requests: Array<TransactionRequest>
)