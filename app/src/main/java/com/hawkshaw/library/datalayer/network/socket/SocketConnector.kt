package com.hawkshaw.library.datalayer.network.socket

import android.content.Intent
import com.hawkshaw.library.App
import com.hawkshaw.library.logger.Logger
import kotlinx.coroutines.delay
import android.util.Log // Added for logging

/**
 * Helper class for connecting WebSocket service
 */
class SocketConnector {
    companion object {
        private const val TAG = "SocketConnector" // Existing TAG
        
        /**
         * Connects to the socket service with proper delay and service lifecycle management
         */
        suspend fun connect() {
            Log.d(TAG, "[DEBUG] connect method called.")
            try {
                val context = App.getContext()
                val intent = Intent(context, SocketService::class.java)
                
                // First stop the service if it's running
                Log.d(TAG, "[DEBUG] Attempting to stop SocketService.")
                try {
                    context.stopService(intent)
                    Log.d(TAG, "[DEBUG] SocketService stopService called.")
                } catch (e: Exception) {
                    Logger.e(TAG, "Error stopping socket service: ${e.message}", e, false, 12, null) // Existing Logger
                    Log.e(TAG, "[DEBUG] Exception stopping SocketService: ${e.message}", e) // Added for Logcat
                }
                
                // Wait a short delay to ensure clean shutdown
                Log.d(TAG, "[DEBUG] Starting 1000ms delay for clean shutdown.")
                delay(1000)
                Log.d(TAG, "[DEBUG] Finished 1000ms delay.")
                
                // Start the service again
                Log.d(TAG, "[DEBUG] Attempting to start SocketService.")
                try {
                    context.startService(intent)
                    Log.d(TAG, "[DEBUG] SocketService startService called.")
                } catch (e: Exception) {
                    Logger.e(TAG, "Error starting socket service: ${e.message}", e, false, 12, null) // Existing Logger
                    Log.e(TAG, "[DEBUG] Exception starting SocketService: ${e.message}", e) // Added for Logcat
                }
            } catch (e: Exception) {
                Logger.e(TAG, "Error in socket connection: ${e.message}", e, false, 12, null) // Existing Logger
                Log.e(TAG, "[DEBUG] Generic exception in connect method: ${e.message}", e) // Added for Logcat
            }
        }
    }
} 
