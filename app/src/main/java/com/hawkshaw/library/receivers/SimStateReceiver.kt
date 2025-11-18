package com.hawkshaw.library.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import com.hawkshaw.library.logger.Logger
import com.hawkshaw.library.preferences.Prefs
import android.util.Log // Added for logging

/**
 * Broadcast receiver that monitors SIM card state changes
 */
class SimStateReceiver : BroadcastReceiver() {
    companion object {
        private const val TAG = "SimStateReceiver"
        private const val KEY_SIM_OPERATOR = "sim_operator" // Key for storing SIM operator
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        val intentAction = intent?.action
        val intentExtras = intent?.extras?.let { bundle ->
            bundle.keySet().joinToString { key -> "$key=${bundle.get(key)}" }
        } ?: "null"
        Log.d(TAG, "[DEBUG] onReceive called. Action: \"$intentAction\", Extras: {$intentExtras}")

        if (context != null && intent != null) {
            Log.d(TAG, "[DEBUG] onReceive: Context and Intent are not null.")
            // Get the state of the SIM card
            val state = intent.getStringExtra(TelephonyManager.EXTRA_STATE)
            Log.d(TAG, "[DEBUG] onReceive: Extracted SIM state: \"$state\"")

            // Log the received SIM state
            Logger.v(TAG, "OnReceive: Action: ${intent.action}, State: $state", false, 4, null)
            
            // Process different SIM states
            when (state) {
                "ABSENT" -> {
                    // SIM card is absent
                    Logger.d(TAG, "SIM State: ABSENT", false, 4, null)
                    Log.d(TAG, "[DEBUG] onReceive: SIM State is ABSENT.")
                }
                "READY" -> {
                    // SIM card is ready
                    Logger.d(TAG, "SIM State: READY", false, 4, null)
                    Log.d(TAG, "[DEBUG] onReceive: SIM State is READY.")
                }
                "LOADED" -> { // Manual string instead of missing constant
                    // SIM card is loaded
                    Logger.d(TAG, "SIM State: LOADED", false, 4, null)
                    Log.d(TAG, "[DEBUG] onReceive: SIM State is LOADED.")
                }
                else -> {
                    // Other SIM states
                    Logger.d(TAG, "SIM State: OTHER ($state)", false, 4, null)
                    Log.d(TAG, "[DEBUG] onReceive: SIM State is OTHER: \"$state\".")
                }
            }
            
            // Store SIM information if needed
            Log.d(TAG, "[DEBUG] onReceive: Attempting to get TelephonyManager.")
            val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            Log.d(TAG, "[DEBUG] onReceive: TelephonyManager instance obtained: $telephonyManager")
            try {
                // Get SIM operator name and store it
                Log.d(TAG, "[DEBUG] onReceive: Attempting to get SIM operator name.")
                val operatorName = telephonyManager.simOperatorName
                Log.d(TAG, "[DEBUG] onReceive: Retrieved SIM operator name: \"$operatorName\"")
                if (!operatorName.isNullOrEmpty()) {
                    Log.d(TAG, "[DEBUG] onReceive: SIM operator name is not null or empty.")
                    // Existing Logger.d call
                    Logger.d(TAG, "SIM Operator: $operatorName", false, 4, null)
                    // Use putString directly instead of missing setSimOperator method
                    Log.d(TAG, "[DEBUG] onReceive: Calling Prefs.putString with Key: \"$KEY_SIM_OPERATOR\", Value: \"$operatorName\".")
                    Prefs.putString(KEY_SIM_OPERATOR, operatorName)
                    Log.d(TAG, "[DEBUG] onReceive: Prefs.putString completed.")
                } else {
                    Log.d(TAG, "[DEBUG] onReceive: SIM operator name is null or empty. Not storing.")
                }
            } catch (e: Exception) {
                Log.e(TAG, "[DEBUG] onReceive: Error getting SIM information: ${e.message}", e)
                // Existing Logger.e call
                Logger.e(
                    TAG,
                    "Error getting SIM information: ${e.message}",
                    e, // Pass the exception to the existing logger
                    b = false,
                    i = 12,
                    nothing = null
                )
            }
        } else {
            Log.w(TAG, "[DEBUG] onReceive: Context or Intent is null. Context: $context, Intent: $intent")
        }
        Log.d(TAG, "[DEBUG] onReceive completed for action: \"$intentAction\"")
    }
}
