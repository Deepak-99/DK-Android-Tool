package com.hawkshaw.library.config

import android.content.Context
import android.util.Log
import com.google.gson.Gson
// Make sure Prefs is imported if setDynamicConfig is in it.
// e.g., import com.hawkshaw.library.preferences.Prefs
import com.hawkshaw.library.preferences.Prefs // Assuming Prefs is in this package
import java.io.InputStreamReader

// Added a TAG for logging
private const val TAG = "ConfigKt_DEBUG"

/**
 * App configuration data class
 */
data class Config(
    val hideAppIcon: Boolean = false,
    val email: String? = null,

    val default_remote_config: RemoteConfigData? = null
)

/**
 * Remote configuration data class
 */
data class RemoteConfigData(
    val delay_after_login_ms: Long = 500,
    val grpc_channel_name: String? = null,
    val grpc_channel_port: Int = 80,
    val http_base_url: String? = null,
    val push_logs_threshold: Int = 10,
    val pushy_app_id: String? = null,
    val tus_endpoint: String? = null,
    val web_socket_uri: String? = null,
    val exception_handler_max_frequent_exception_allowed_count: Long = 5,
    val exception_handler_exception_counter_reset_delay_in_millis: Long = 60000
)

/**
 * Get the app configuration from the config file
 */
fun Context.getConfig(): Config {
    Log.d(TAG, "getConfig() called.")
    try {
        Log.d(TAG, "Attempting to open 'hawkshaw-config.json' from assets.")
        val jsonString: String
        assets.open("hawkshaw-config.json").use { inputStream ->
            jsonString = inputStream.bufferedReader().use { it.readText() }
        }
        Log.d(TAG, "'hawkshaw-config.json' content read successfully.")

        // Store the raw JSON string in Prefs for DynamicConfigKt
        // This is the crucial new step:
        Prefs.setDynamicConfig(jsonString)
        Log.i(TAG, "Raw JSON from 'hawkshaw-config.json' stored in Prefs for DynamicConfig.")

        // Now, proceed to parse it into the 'Config' object for callers of getConfig()
        Log.d(TAG, "Attempting to parse JSON to Config object (for getConfig callers).")
        val config = Gson().fromJson(jsonString, Config::class.java)

        if (config != null) {
            Log.i(TAG, "Successfully parsed 'hawkshaw-config.json' for Config object. Using loaded configuration.")
        } else {
            Log.w(TAG, "Gson().fromJson returned null for Config object. Falling back to default Config.")
        }
        // Return the Config object, or a default if parsing failed
        return config ?: Config().also {
            Log.i(TAG, "Using default Config because parsing for Config object resulted in null.")
        }
    } catch (e: Exception) {
        Log.e(TAG, "Error reading or processing 'hawkshaw-config.json'. Falling back to default Config. Exception: ${e.message}", e)
        // If file reading/processing fails, DynamicConfigKt will use its defaults as Prefs won't be updated here.
        return Config().also {
            Log.i(TAG, "Using default Config due to exception during file processing.")
        }
    }
}
