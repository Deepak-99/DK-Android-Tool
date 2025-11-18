package com.hawkshaw.library.features.push

import android.content.Context
import android.util.Log
import com.hawkshaw.library.config.RemoteConfig
import com.hawkshaw.library.preferences.Prefs

import com.hawkshaw.library.services.PrivatePushService

object PrivatePushInitializer {
    
    private const val TAG = "PrivatePushInitializer"
    
    /**
     * Initialize private push system (replaces Firebase FCM)
     */
    fun initialize(context: Context) {
        try {
            Log.i(TAG, "Initializing private push system")
            
            if (isConfigured(context)) {
                // Start the private push service
                PrivatePushService.startService(context)
                Log.i(TAG, "Private push system initialized successfully")
            } else {
                Log.w(TAG, "Private push system not configured, skipping initialization")
            }
            
        } catch (e: Exception) {
            Log.e(TAG, "Failed to initialize private push system", e)
        }
    }
    
    /**
     * Stop private push system
     */
    fun shutdown(context: Context) {
        try {
            Log.i(TAG, "Shutting down private push system")
            PrivatePushService.stopService(context)
        } catch (e: Exception) {
            Log.e(TAG, "Error shutting down private push system", e)
        }
    }
    
    /**
     * Restart private push system (useful after configuration changes)
     */
    fun restart(context: Context) {
        try {
            Log.i(TAG, "Restarting private push system")
            shutdown(context)
            
            // Small delay to ensure service is stopped
            Thread.sleep(1000)
            
            initialize(context)
        } catch (e: Exception) {
            Log.e(TAG, "Error restarting private push system", e)
        }
    }
    
    /**
     * Check if private push system is properly configured
     */
    fun isConfigured(context: Context): Boolean {
        // Initialize Prefs
        Prefs.init(context)
        
        val serverUrl = RemoteConfig.getInstance(context).getWebsocketUri()
        val deviceId = Prefs.getString("device_id")
        
        return serverUrl.isNotEmpty() && !deviceId.isNullOrEmpty()
    }
}
