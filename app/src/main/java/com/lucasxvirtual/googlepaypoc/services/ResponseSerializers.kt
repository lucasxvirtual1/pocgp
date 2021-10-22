package com.lucasxvirtual.googlepaypoc.services

import com.google.gson.*
import com.lucasxvirtual.googlepaypoc.models.api.response.*
import com.lucasxvirtual.googlepaypoc.models.api.response.WebserviceResponse
import com.lucasxvirtual.googlepaypoc.models.api.jwt.JwtToken
import java.lang.reflect.Type
import java.util.*


internal class RequestTypeSerializer: JsonSerializer<RequestType>, JsonDeserializer<RequestType> {
    override fun serialize(src: RequestType?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        val nullString: String? = null
        src ?: return JsonPrimitive(nullString)

        return JsonPrimitive(src.serializedName)
    }

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): RequestType {
        val name = json?.asString ?: throw JsonParseException("")

        val enumVal = RequestType.values().firstOrNull { it.serializedName == name }
        if(enumVal != null) {
            return enumVal
        }

        throw JsonParseException("")
    }
}

internal class CustomerOutputTypeSerializer: JsonDeserializer<CustomerOutput> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): CustomerOutput {
        val code = json?.asString ?: throw JsonParseException("")

        val customerOutputVal = CustomerOutput.values().firstOrNull { it.value == code }
        if (customerOutputVal != null) {
            return customerOutputVal
        }

        throw JsonParseException("")
    }
}

internal class SettlementStatusTypeSerializer: JsonDeserializer<ResponseSettlementStatus> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): ResponseSettlementStatus {
        val code = json?.asInt ?: throw JsonParseException("")

        val settlementStatusEnumVal = ResponseSettlementStatus.values().firstOrNull { it.code == code }
        if(settlementStatusEnumVal != null) {
            return settlementStatusEnumVal
        }

        throw JsonParseException("")
    }
}

internal class LiveStatusTypeSerializer: JsonDeserializer<ResponseLiveStatus> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): ResponseLiveStatus {
        val value = json?.asInt ?: throw JsonParseException("")

        val liveStatusEnumVal = ResponseLiveStatus.values().firstOrNull { it.value == value }
        if(liveStatusEnumVal != null) {
            return liveStatusEnumVal
        }

        throw JsonParseException("")
    }
}

internal class ErrorCodeSerializer: JsonDeserializer<ResponseErrorCode> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): ResponseErrorCode {
        val code = json?.asInt ?: throw JsonParseException("")

        val errorCodeEnumVal = ResponseErrorCode.values().firstOrNull { it.code == code }
        if(errorCodeEnumVal != null) {
            return errorCodeEnumVal
        }

        throw JsonParseException("")
    }
}

internal class WebserviceResponseSerializer: JsonDeserializer<WebserviceResponse> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext): WebserviceResponse {
        val jwt = json?.asJsonObject?.get("jwt")?.asString ?: throw JsonParseException("")
        val token = JwtToken(jwt)

        if(!token.isValid) {
            throw JsonParseException("")
        }

        return WebserviceResponse(
            token,
            token.header,
            context.deserialize(JsonParser().parse(token.body).asJsonObject, WebserviceResponse.Body::class.java),
            token.signature
        )
    }
}

@JvmSynthetic internal fun getGson() = GsonBuilder()
    .setFieldNamingStrategy { f -> f?.name?.toLowerCase(Locale.ROOT) }
    .registerTypeAdapter(RequestType::class.java, RequestTypeSerializer())
    .registerTypeAdapter(ResponseSettlementStatus::class.java, SettlementStatusTypeSerializer())
    .registerTypeAdapter(CustomerOutput::class.java, CustomerOutputTypeSerializer())
    .registerTypeAdapter(ResponseLiveStatus::class.java, LiveStatusTypeSerializer())
    .registerTypeAdapter(ResponseErrorCode::class.java, ErrorCodeSerializer())
    .registerTypeAdapter(WebserviceResponse::class.java, WebserviceResponseSerializer())
    .create()