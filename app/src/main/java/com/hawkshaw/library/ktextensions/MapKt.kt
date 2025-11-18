package com.hawkshaw.library.ktextensions

import kotlinx.serialization.json.*

/**
 * Utility functions for Maps and JSON conversions
 */

/**
 * Encode a map with the given key-value pairs to a JSON string
 */
fun Json.encodeToString(vararg pairs: Pair<String, Any?>): String {
    val map = if (pairs.isEmpty()) {
        emptyMap()
    } else if (pairs.size == 1) {
        mapOf(pairs[0])
    } else {
        pairs.toMap()
    }
    
    return encodeToString(JsonElement.serializer(), map.toJsonElement())
}

/**
 * Convert a JsonElement to its corresponding Kotlin object
 */
fun JsonElement.parseValue(): Any? {
    return when (this) {
        is JsonPrimitive -> parseValue(this)
        is JsonArray -> this.map { it.parseValue() }
        is JsonObject -> throw Error("File: Map.kt - JsonElement.parseValue JsonObject NotImplemented")
        JsonNull -> null
        else -> throw RuntimeException("Unknown JsonElement type")
    }
}

/**
 * Convert a JsonPrimitive to its corresponding Kotlin object
 */
fun parseValue(primitive: JsonPrimitive): Any {
    return when {
        primitive.isString -> {
            val content = primitive.content
            when {
                content.toBooleanStrictOrNull() != null -> content.toBoolean()
                content.toIntOrNull() != null -> content.toInt()
                content.toLongOrNull() != null -> content.toLong()
                content.toFloatOrNull() != null -> content.toFloat()
                content.toDoubleOrNull() != null -> content.toDouble()
                else -> content
            }
        }
        primitive.booleanOrNull != null -> primitive.boolean
        else -> {
            // This is likely a number since it's not a string or boolean
            val content = primitive.content
            when {
                content.contains(".") -> content.toDouble()
                else -> content.toLong()
            }
        }
    }
}

/**
 * Convert an array to a JsonArray
 */
fun Array<*>.toJsonArray(): JsonArray {
    return JsonArray(this.map { it.toJsonElement() })
}

/**
 * Convert any object to its JsonElement representation
 */
fun Any?.toJsonElement(): JsonElement {
    return when (this) {
        null -> JsonNull
        is Number -> JsonPrimitive(this)
        is Boolean -> JsonPrimitive(this)
        is String -> JsonPrimitive(this)
        is Array<*> -> this.toJsonArray()
        is Iterable<*> -> this.toJsonArray()
        is Map<*, *> -> this.toJsonObject()
        is JsonElement -> this
        else -> JsonNull
    }
}

/**
 * Convert a Map to a JsonObject
 */
fun Map<*, *>.toJsonObject(): JsonObject {
    val stringMap = this.entries.associate { 
        it.key.toString() to it.value 
    }
    
    return JsonObject(stringMap.mapValues { it.value.toJsonElement() })
}

/**
 * Convert an Iterable to a JsonArray
 */
fun Iterable<*>.toJsonArray(): JsonArray {
    return JsonArray(this.map { it.toJsonElement() })
} 