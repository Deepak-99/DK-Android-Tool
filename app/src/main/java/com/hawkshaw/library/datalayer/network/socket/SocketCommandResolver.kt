package com.hawkshaw.library.datalayer.network.socket

import android.os.Build
import com.hawkshaw.library.datalayer.models.SocketCommandRequest
import com.hawkshaw.library.datalayer.network.twirp.interceptors.ContentNegotiationInterceptorKt
import com.hawkshaw.library.logger.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import android.util.Log // Added for Android Logcat
import androidx.annotation.RequiresApi

/**
 * Helper class to parse and resolve socket commands
 */
class SocketCommandResolver {
    companion object {
        private const val TAG = "SocketCommandResolver" // Existing TAG
        
        /**
         * Parses a command string and calls the resolver to handle it
         * 
         * @param commandStr The JSON string containing the command
         */
        @RequiresApi(Build.VERSION_CODES.P)
        suspend fun parseAndResolveCommand(commandStr: String) = withContext(Dispatchers.IO) {
            Log.d(TAG, "parseAndResolveCommand called. commandStr: $commandStr")
            try {
                // Existing custom logger call
                Logger.d(TAG, "Socket Command Received (custom logger): $commandStr", false, 4, null)
                
                Log.d(TAG, "Attempting to parse commandStr into SocketCommandRequest...")
                // Parse the command from JSON
                val json = ContentNegotiationInterceptorKt.json
                val commandRequest = json.decodeFromString(
                    SocketCommandRequest.serializer(),
                    commandStr
                )
                Log.d(TAG, "Successfully parsed command. Request: $commandRequest")
                
                // Handle the command
                Log.d(TAG, "Calling SocketCommandResolverKt.handleSocketCommand with original commandStr.")
                SocketCommandResolverKt.handleSocketCommand(commandStr)
            } catch (e: Exception) {
                // Existing custom logger call
                Logger.e(TAG, "HandleSocketCommand (custom logger): ${e.message}", e, false, 12, null)
                // Added for standard Android Logcat
                Log.e(TAG, "Error in parseAndResolveCommand: ${e.message}", e)
            }
        }
    }
}
