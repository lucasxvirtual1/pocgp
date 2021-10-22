package com.lucasxvirtual.googlepaypoc.util

import com.lucasxvirtual.googlepaypoc.models.JwtResponse
import com.lucasxvirtual.googlepaypoc.models.api.jwt.JwtToken
import com.lucasxvirtual.googlepaypoc.models.api.response.WebserviceResponse
import com.lucasxvirtual.googlepaypoc.services.getGson

class ResponseParser {
    companion object {
        private val tag = "ResponseParser"

        private val gson by lazy { getGson() }

        fun parse(jwt: String): JwtResponse? {
            val jwtToken = JwtToken(jwt)
            if (!jwtToken.isValid) {
                return null
            }

            val responses = gson.fromJson(jwtToken.body, WebserviceResponse.Body::class.java).payload.transactionResponses
            return JwtResponse(responses.find { it.customerOutput != null }, responses)
        }

        fun parse(jwtList: List<String>): List<JwtResponse>? = jwtList.mapNotNull { parse(it) }.takeIf { it.size == jwtList.size }
    }
}