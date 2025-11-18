package com.hawkshaw.library.datalayer.network.socket

import com.hawkshaw.library.logger.Logger
import kotlinx.coroutines.delay
import okhttp3.WebSocket
import java.net.ConnectException
import javax.net.ssl.SSLPeerUnverifiedException
import okio.IOException
import android.util.Log // Added for logging

/**
 * Helper class for connecting to WebSocket
 */
class WebSocketConnector {
    companion object {
        private const val TAG = "WebSocketConnector" // Existing TAG
        
        /**
         * Connect to a WebSocket with retries
         * 
         * @param webSocket The WebSocket to connect
         * @param socketService The service managing the socket
         * @param delayMillis The delay before connecting
         */
        suspend fun connect(webSocket: WebSocket, socketService: SocketService, delayMillis: Long) {
            Log.d(TAG, "[DEBUG] connect called. webSocket: $webSocket, socketService: $socketService, delayMillis: $delayMillis")
            try {
                // Wait for the specified delay
                Log.d(TAG, "[DEBUG] connect: Delaying for $delayMillis ms.")
                delay(delayMillis)
                Log.d(TAG, "[DEBUG] connect: Delay finished.")
                
                // Configure the socket with listener
                Log.d(TAG, "[DEBUG] connect: Creating SocketListener.")
                val listener = SocketListener(socketService)
                Log.d(TAG, "[DEBUG] connect: SocketListener created: $listener")
                
                // Create new socket connection using the service
                Log.d(TAG, "[DEBUG] connect: Calling socketService.connect with webSocket: $webSocket and listener: $listener, delayMillis: $delayMillis (Note: listener not directly passed to socketService.connect as per its signature, but it is used internally by the service or its components)")
                // The original code passed `webSocket` and `delayMillis` to `socketService.connect`.
                // The `listener` is used internally by `SocketService` when it creates a new WebSocket.
                socketService.connect(webSocket, delayMillis)
                Log.d(TAG, "[DEBUG] connect: socketService.connect call completed.")
                
            } catch (e: IOException) {
                Logger.e(
                    TAG,
                    "Connect: WebSocket protocol violation during handshake",
                    e,
                    false,
                    12,
                    null
                ) // Existing Logger
                Log.e(TAG, "[DEBUG] Connect: WebSocket protocol violation during handshake. Exception: ${e.message}", e)
            } catch (e: SSLPeerUnverifiedException) {
                Logger.e(
                    TAG,
                    "Connect: SSL certificate validation failed - hostname mismatch",
                    e,
                    false,
                    12,
                    null
                ) // Existing Logger
                Log.e(TAG, "[DEBUG] Connect: SSL certificate validation failed - hostname mismatch. Exception: ${e.message}", e)
            } catch (e: ConnectException) {
                Logger.e(TAG, "Connect: Failed to establish connection", e, false, 12, null) // Existing Logger
                Log.e(TAG, "[DEBUG] Connect: Failed to establish connection. Exception: ${e.message}", e)
            } catch (e: Exception) {
                Logger.e(TAG, "Connect: ${e.message}", e, false, 12, null) // Existing Logger
                Log.e(TAG, "[DEBUG] Connect: Generic exception. Exception: ${e.message}", e)
            }
        }
    }
}
