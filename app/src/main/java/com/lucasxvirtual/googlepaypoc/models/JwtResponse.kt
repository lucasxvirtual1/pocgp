package com.lucasxvirtual.googlepaypoc.models

import com.lucasxvirtual.googlepaypoc.models.api.response.TransactionResponse

data class JwtResponse(
        val customerOutput: TransactionResponse?,
        val responses: List<TransactionResponse>
)