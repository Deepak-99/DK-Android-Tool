package com.hawkshaw.library.config

import android.util.Log
import com.hawkshaw.library.datalayer.models.DynamicConfig
import com.hawkshaw.library.datalayer.network.twirp.interceptors.ContentNegotiationInterceptorKt
import com.hawkshaw.library.preferences.Prefs
import kotlinx.serialization.SerializationException

// Added a TAG for logging
private const val TAG = "DynamicConfigKt_DEBUG"

/**
 * Helper functions for working with dynamic configuration
 */
object DynamicConfigKt {
    /**
     * Get the current dynamic configuration
     * @return The current dynamic configuration or default values if not set
     */
    fun getDynamicConfig(): DynamicConfig {
        Log.d(TAG, "getDynamicConfig() called.")
        val jsonSerializer = ContentNegotiationInterceptorKt.json
        val dynamicConfigJson = Prefs.getDynamicConfig()
        Log.d(TAG, "Raw JSON from Prefs.getDynamicConfig(): $dynamicConfigJson")

        val jsonToParse = dynamicConfigJson ?: "{}"
        if (dynamicConfigJson == null) {
            Log.i(TAG, "Prefs.getDynamicConfig() returned null, using default empty JSON '{}'.")
        }

        return try {
            Log.d(TAG, "Attempting to decode JSON: $jsonToParse")
            val decodedConfig = jsonSerializer.decodeFromString(DynamicConfig.serializer(), jsonToParse)
            // Be cautious about logging the entire decodedConfig if it might contain many or sensitive fields.
            // For now, logging a specific, known safe field or just success.
            Log.i(TAG, "Successfully decoded dynamic config. WebViewUrl from decoded: ${decodedConfig.webViewUrl}")
            decodedConfig
        } catch (e: SerializationException) {
            Log.e(TAG, "Error decoding dynamic config JSON: '$jsonToParse'. Exception: ${e.message}", e)
            Log.w(TAG, "Returning default DynamicConfig due to deserialization error.")
            DynamicConfig(key = "", value = "") // Return a default/empty DynamicConfig if parsing fails
        } catch (e: Exception) {
            Log.e(TAG, "An unexpected error occurred during dynamic config decoding: ${e.message}", e)
            Log.w(TAG, "Returning default DynamicConfig due to unexpected error.")
            DynamicConfig(key = "", value = "") // Return a default/empty DynamicConfig for other errors
        }
    }

    /**
     * Get the web view URL from dynamic configuration
     * @return The web view URL or null if not set
     */
    fun getWebViewUrl(): String? {
        Log.d(TAG, "getWebViewUrl() called.")
        val webViewUrl = getDynamicConfig().webViewUrl
        Log.d(TAG, "Returning webViewUrl: $webViewUrl")
        return webViewUrl
    }
}
