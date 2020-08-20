package common

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

/**
 * Converts to a Json string from the receiver object.
 * If conversion fails, return null.
 */
fun Any.toJsonString(): String? {
    val objectMapper = ObjectMapper().registerKotlinModule()

    return try {
        objectMapper.writeValueAsString(this)!!
    } catch (e: JsonProcessingException) {
        null
    }
}

/**
 * Converts to an object (type [T]) from a Json string.
 * If conversion fails or input [json] is null, return null.
 */
inline fun <reified T> convertToObjectFromJsonString(json: String?): T? {
    if(json == null)
        return null

    val objectMapper = ObjectMapper().registerKotlinModule()

    return try {
        objectMapper.readValue(json, T::class.java)
    } catch (e: JsonProcessingException) {
        null
    }
}
