package com.hawkshaw.library.datalayer.room

import androidx.room.TypeConverter
import kotlinx.serialization.builtins.ArraySerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import com.hawkshaw.library.datalayer.network.twirp.interceptors.ContentNegotiationInterceptorKt // Import the object itself
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import android.util.Log // Added for Logcat

/**
 * Room TypeConverters for custom data types.
 * This object provides methods to convert String arrays to/from a single String
 * using Kotlinx Serialization (JSON).
 */
object MyTypeConverters {
    private const val TAG = "MyTypeConverters" // Added for logging

    // FIX: Change getJson() to json to access the lazy property of the object
    private val json: Json by lazy {
        Log.d(TAG, "[DEBUG] Initializing and getting Json instance from ContentNegotiationInterceptorKt.") // Added Logcat
        ContentNegotiationInterceptorKt.json
    }

    /**
     * Converts a String array to a JSON string.
     * Used by Room to store String[] in a single database column.
     */
    @TypeConverter
    fun fromArray(strArray: Array<String>?): String? {
        Log.d(TAG, "[DEBUG] fromArray called. Input strArray: ${strArray?.contentToString()}") // Added Logcat
        if (strArray == null) {
            Log.d(TAG, "[DEBUG] fromArray: Input array is null, returning null.") // Added Logcat
            return null
        }
        val result = json.encodeToString(ArraySerializer(String.serializer()), strArray)
        Log.d(TAG, "[DEBUG] fromArray: Output JSON string: \"$result\"") // Added Logcat
        return result
    }

    /**
     * Converts a JSON string back to a String array.
     * Used by Room to retrieve String[] from a single database column.
     */
    @TypeConverter
    fun fromString(str: String?): Array<String> {
        Log.d(TAG, "[DEBUG] fromString called. Input string: \"$str\"") // Added Logcat
        if (str.isNullOrBlank()) {
            Log.d(TAG, "[DEBUG] fromString: Input string is null or blank, returning emptyArray.") // Added Logcat
            return emptyArray()
        }
        return try {
            val result = json.decodeFromString(ArraySerializer(String.serializer()), str)
            Log.d(TAG, "[DEBUG] fromString: Successfully decoded to Array<String>: ${result.contentToString()}") // Added Logcat
            result
        } catch (e: Exception) {
            Log.e(TAG, "[DEBUG] fromString: Error decoding String array from JSON: \"$str\". Returning emptyArray.", e) // Added Logcat
            // Logger.e(TAG, "Error decoding String array from JSON: $str", e)
            emptyArray()
        }
    }
}
