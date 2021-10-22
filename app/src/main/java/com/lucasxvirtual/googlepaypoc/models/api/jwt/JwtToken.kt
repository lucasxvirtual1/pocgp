package com.lucasxvirtual.googlepaypoc.models.api.jwt

import android.util.Base64
import kotlin.Exception

data class JwtToken(
    val token: String
) {
    val urlDecodedToken: String
        get() = token
            .replace("-", "+")
            .replace("_", "/")

    val isValid: Boolean
        get() {
            val tokenParts = urlDecodedToken.split(".")

            if(tokenParts.size != 3 || tokenParts.any { it.isEmpty() } || tokenParts.anyThrows { Base64.decode(it, 0) }) {
                return false
            }

            return true
        }

    val encodedParts: List<String>
        get() {
            if(!isValid) throw InvalidJwtException()

            return urlDecodedToken.split(".")
        }

    val encodedHeader: String
        get() = encodedParts[0]

    val encodedBody: String
        get() = encodedParts[1]

    val encodedSignature: String
        get() = encodedParts[2]

    val header: String
        get() = String(Base64.decode(encodedParts[0], 0))

    val body: String
        get() {
            val t = String(Base64.decode(encodedParts[1], 0))
            return t
        }

    val signature: String
        get() = String(Base64.decode(encodedParts[2], 0))

}

class InvalidJwtException: RuntimeException()

private fun <T> List<T>.anyThrows(block: (item: T) -> Unit): Boolean {
    this.forEach {
        try {
            block(it)
        } catch (ex: Exception) {
            return true
        }
    }

    return false
}