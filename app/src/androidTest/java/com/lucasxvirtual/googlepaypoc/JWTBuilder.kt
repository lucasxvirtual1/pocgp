package com.lucasxvirtual.googlepaypoc

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lucasxvirtual.googlepaypoc.models.api.response.RequestType
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import java.util.*
import javax.crypto.SecretKey

class JWTBuilder(
    private val merchantUsername: String,
    private val siteReference: String,
    private val jwtKey: String
) {

    fun getForGooglePay(walletToken : String,
                        requestTypes: List<RequestType>,
                        baseAmount: Int = 1050,
                        billingContactDetailsOverride : Int = 0,
                        customerContactDetailsOverride : Int = 0,
                        billingEmail: String? = null,
                        billingFirstName: String? = null,
                        billingPostCode: String? = null,
                        customerEmail: String? = null,
                        customerFirstName: String? = null,
                        customerPostCode: String? = null
    ) =
            buildJWT(
                    merchantUsername,
                    GooglePayTPPayload(
                            siteReference,
                            "GBP",
                            baseAmount,
                            requestTypes.map { it.serializedName },
                            walletToken,
                            billingContactDetailsOverride = billingContactDetailsOverride,
                            customerContactDetailsOverride = customerContactDetailsOverride,
                            billingEmail = billingEmail,
                            customerEmail = customerEmail,
                            billingFirstName = billingFirstName,
                            billingPostCode = billingPostCode,
                            customerFirstName = customerFirstName,
                            customerPostCode = customerPostCode
                    )
            )

    private fun buildJWT(
        merchantUsername: String,
        payload: TPPayload
    ): String {
        val key: SecretKey = Keys.hmacShaKeyFor(jwtKey.toByteArray())
        val header = Jwts.header().setType("JWT")

        return Jwts.builder()
            .setHeader(header)
            .setIssuer(merchantUsername)
            .setIssuedAt(Date())
            .claim(
                "payload",
                payload.serializeObjectToMap(Gson().newBuilder().setFieldNamingStrategy {
                    it.name.toLowerCase(Locale.ROOT)
                }.create())
            )
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

    fun buildResponseJWT(payload: BasePayload): String {
        val key: SecretKey = Keys.hmacShaKeyFor(jwtKey.toByteArray())
        val header = Jwts.header().setType("JWT")

        return Jwts.builder()
            .setHeader(header)
            .setIssuer(merchantUsername)
            .setIssuedAt(Date())
            .claim(
                "payload",
                payload.serializeObjectToMap(Gson().newBuilder().setFieldNamingStrategy {
                    it.name.toLowerCase(Locale.ROOT)
                }.create())
            )
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

    fun <InputType> InputType.serializeObjectToMap(gson: Gson): Map<String, Any> {
        return convert(gson)
    }

    inline fun <InputType, reified OutputType> InputType.convert(gson: Gson): OutputType {
        val json = gson.toJson(this)

        return gson.fromJson(json, object : TypeToken<OutputType>() {}.type)
    }
}