package com.lucasxvirtual.googlepaypoc

import kotlin.random.Random

class PaymentSession internal constructor(
    jwtProvider: () -> String,
    var cardPan: String? = null,
    var cardExpiryDate: String? = null,
    var cardSecurityCode: String? = null,
    var pares: String? = null,
    var threeDResponse: String? = null
) {
    val sessionId = generateSessionId()

    internal var jwtProvider: () -> String = jwtProvider
        @JvmSynthetic get
        @JvmSynthetic set

    internal lateinit var intermediateJwt: String
        @JvmSynthetic get
        @JvmSynthetic set

    internal var cacheJwt: String? = null
        @JvmSynthetic get
        @JvmSynthetic set

    private fun generateSessionId(): String {
        val availableCharacters : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

        val randomString = (1..8)
            .map { Random.nextInt(0, availableCharacters.size) }
            .map { availableCharacters[it] }
            .joinToString("")

        return "J-$randomString"
    }
}