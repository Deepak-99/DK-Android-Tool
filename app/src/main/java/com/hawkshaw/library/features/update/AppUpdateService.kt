package com.hawkshaw.library.features.update

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.provider.Settings
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import com.hawkshaw.app.R
import com.hawkshaw.library.datalayer.network.AppUpdateResponse
import com.hawkshaw.library.datalayer.network.checkAppUpdate
import com.hawkshaw.library.datalayer.network.downloadAppUpdate
import com.hawkshaw.library.datalayer.network.reportAppInstall
import com.hawkshaw.library.network.ApiService
import kotlinx.coroutines.*
import java.io.File
import java.io.FileOutputStream
import java.security.MessageDigest
import kotlin.coroutines.cancellation.CancellationException
import androidx.core.content.edit

class AppUpdateService : Service() {

    companion object {
        private const val TAG = "AppUpdateService"
        private const val NOTIFICATION_ID = 1001
        private const val DOWNLOAD_NOTIFICATION_ID = 1002
        private const val UPDATE_AVAILABLE_NOTIFICATION_ID = 1003
        private const val ERROR_NOTIFICATION_ID = 1004

        private const val CHANNEL_ID = "app_update_channel"
        private const val CHANNEL_NAME = "App Updates"

        const val ACTION_CHECK_UPDATE = "com.hawkshaw.library.action.CHECK_UPDATE"
        const val ACTION_DOWNLOAD_UPDATE = "com.hawkshaw.library.action.DOWNLOAD_UPDATE"

        const val EXTRA_UPDATE_URL = "extra_update_url"
        const val EXTRA_VERSION_NAME = "extra_version_name"
        const val EXTRA_VERSION_CODE = "extra_version_code"
        const val EXTRA_FILE_SIZE = "extra_file_size"
        const val EXTRA_CHECKSUM = "extra_checksum"
        const val EXTRA_IS_MANDATORY = "extra_is_mandatory"

        @RequiresApi(Build.VERSION_CODES.O)
        fun startUpdateCheck(context: Context) {
            val intent = Intent(context, AppUpdateService::class.java).apply {
                action = ACTION_CHECK_UPDATE
            }
            context.startForegroundService(intent)
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun startUpdateDownload(
            context: Context,
            updateUrl: String,
            versionName: String,
            versionCode: Int,
            fileSize: Long,
            checksum: String,
            isMandatory: Boolean
        ) {
            val intent = Intent(context, AppUpdateService::class.java).apply {
                action = ACTION_DOWNLOAD_UPDATE
                putExtra(EXTRA_UPDATE_URL, updateUrl)
                putExtra(EXTRA_VERSION_NAME, versionName)
                putExtra(EXTRA_VERSION_CODE, versionCode)
                putExtra(EXTRA_FILE_SIZE, fileSize)
                putExtra(EXTRA_CHECKSUM, checksum)
                putExtra(EXTRA_IS_MANDATORY, isMandatory)
            }
            context.startForegroundService(intent)
        }
    }

    private val serviceJob = SupervisorJob()
    private val serviceScope = CoroutineScope(Dispatchers.IO + serviceJob)

    private lateinit var notificationManager: NotificationManager
    private var currentDownloadJob: Job? = null

    private val apiServiceInstance: ApiService by lazy {
        ApiService(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel()
        Log.d(TAG, "AppUpdateService onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand received action: ${intent?.action}")
        when (intent?.action) {
            ACTION_CHECK_UPDATE -> {
                Log.d(TAG, "Starting update check...")
                startForeground(NOTIFICATION_ID, createProgressNotification("Checking for updates...", -1))
                checkForUpdates()
            }
            ACTION_DOWNLOAD_UPDATE -> {
                val updateUrl = intent.getStringExtra(EXTRA_UPDATE_URL)
                val versionName = intent.getStringExtra(EXTRA_VERSION_NAME)
                val versionCode = intent.getIntExtra(EXTRA_VERSION_CODE, 0)
                val fileSize = intent.getLongExtra(EXTRA_FILE_SIZE, 0)
                val checksum = intent.getStringExtra(EXTRA_CHECKSUM)
                val isMandatory = intent.getBooleanExtra(EXTRA_IS_MANDATORY, false)

                if (updateUrl == null || versionName == null || checksum == null || versionCode == 0) {
                    Log.e(TAG, "Missing data for download update. Stopping service.")
                    showErrorNotification("Download failed: Missing update information.")
                    stopForeground(true)
                    stopSelf(startId)
                    return START_NOT_STICKY
                }
                Log.d(TAG, "Starting update download for version $versionName from $updateUrl")
                startForeground(DOWNLOAD_NOTIFICATION_ID, createProgressNotification("Downloading $versionName...", 0))
                downloadUpdate(updateUrl, versionName, versionCode, fileSize, checksum, isMandatory)
            }
            else -> {
                Log.w(TAG, "Unknown or missing action: ${intent?.action}. Stopping service.")
                stopForeground(true)
                stopSelf(startId)
            }
        }
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        super.onDestroy()
        currentDownloadJob?.cancel()
        serviceJob.cancel()
        Log.d(TAG, "AppUpdateService onDestroy")
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "Notifications for app updates"
                setShowBadge(false)
            }
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun createProgressNotification(title: String, progress: Int): Notification {
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(title)
            .setSmallIcon(R.drawable.ic_download)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setOngoing(true)

        if (progress >= 0) {
            builder.setProgress(100, progress, false)
            builder.setContentText("$progress%")
        } else {
            builder.setProgress(0, 0, true)
        }
        return builder.build()
    }

    private fun createActionableNotification(title: String, message: String, pendingIntent: PendingIntent?): Notification {
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.drawable.ic_download)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .apply {
                if (pendingIntent != null) {
                    setContentIntent(pendingIntent)
                }
            }
            .build()
    }


    private fun checkForUpdates() {
        serviceScope.launch {
            try {
                val currentVersionCode = getCurrentVersionCode()
                val deviceId = getAppSpecificDeviceId()
                Log.d(TAG, "Checking for updates. Current version: $currentVersionCode, Device ID: $deviceId")
                val response: AppUpdateResponse = apiServiceInstance.checkAppUpdate(deviceId, currentVersionCode)

                if (response.updateAvailable) {
                    Log.i(TAG, "Update available: ${response.versionName}. Mandatory: ${response.isMandatory}")
                    val downloadIntent = Intent(this@AppUpdateService, AppUpdateService::class.java).apply {
                        action = ACTION_DOWNLOAD_UPDATE
                        putExtra(EXTRA_UPDATE_URL, response.downloadUrl)
                        putExtra(EXTRA_VERSION_NAME, response.versionName)
                        putExtra(EXTRA_VERSION_CODE, response.versionCode)
                        putExtra(EXTRA_FILE_SIZE, response.fileSize)
                        putExtra(EXTRA_CHECKSUM, response.checksum)
                        putExtra(EXTRA_IS_MANDATORY, response.isMandatory)
                    }
                    val pendingIntentFlags =
                        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                    val pendingIntent = PendingIntent.getService(this@AppUpdateService, 0, downloadIntent, pendingIntentFlags)
                    val notification = createActionableNotification(
                        "Update Available",
                        "Version ${response.versionName} is ready to download.",
                        pendingIntent
                    )
                    notificationManager.notify(UPDATE_AVAILABLE_NOTIFICATION_ID, notification)

                    if (response.isMandatory) {
                        Log.i(TAG, "Mandatory update. Starting download automatically.")
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            startUpdateDownload(
                                this@AppUpdateService,
                                response.downloadUrl,
                                response.versionName,
                                response.versionCode,
                                response.fileSize,
                                response.checksum,
                                true
                            )
                        } else {
                            startService(downloadIntent)
                        }
                    } else {
                        // If not mandatory, and check-update was started as foreground, stop foreground
                        // The "Update Available" notification is sufficient.
                        stopForeground(true) // Remove "Checking for updates" notification
                        // Don't stopSelf() here if you want the "Update Available" notification to persist
                        // and its action to start a new download command.
                        // The service will stop if the user dismisses the notification or if the system kills it.
                        // If the user taps the notification, onStartCommand will be called again.
                    }
                } else {
                    Log.d(TAG, "No update available. Message: ${response.message}")
                    stopForeground(true)
                    stopSelf()
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error checking for updates", e)
                showErrorNotification("Failed to check for updates: ${e.localizedMessage}")
                stopForeground(true)
                stopSelf()
            }
        }
    }

    // Corrected downloadUpdate function
    private fun downloadUpdate(
        updateUrl: String,
        versionName: String,
        versionCode: Int,
        fileSize: Long,
        expectedChecksum: String,
        isMandatory: Boolean
    ) {
        currentDownloadJob?.cancel() // Cancel any previous download job
        currentDownloadJob = serviceScope.launch {
            var apkFile: File? = null
            try {
                Log.i(TAG, "Starting download of version $versionName from $updateUrl")

                val downloadDir = File(getExternalFilesDir(null), "updates")
                if (!downloadDir.exists()) {
                    downloadDir.mkdirs()
                }
                apkFile = File(downloadDir, "hawkshaw-$versionName.apk")

                val responseBody = apiServiceInstance.downloadAppUpdate(updateUrl)

                responseBody.use { body ->
                    body.byteStream().use { inputStream ->
                        FileOutputStream(apkFile).use { outputStream ->
                            val buffer = ByteArray(8192)
                            var totalBytesRead = 0L
                            var bytesRead: Int // No need to initialize if read before loop condition

                            bytesRead = inputStream.read(buffer) // Read first chunk
                            while (isActive && bytesRead != -1) { // Check value from first read
                                outputStream.write(buffer, 0, bytesRead)
                                totalBytesRead += bytesRead

                                if (fileSize > 0) {
                                    val progress = ((totalBytesRead * 100) / fileSize).toInt()
                                    val notification = createProgressNotification("Downloading $versionName...", progress)
                                    notificationManager.notify(DOWNLOAD_NOTIFICATION_ID, notification)
                                } else {
                                    val notification = createProgressNotification("Downloading $versionName...", -1)
                                    notificationManager.notify(DOWNLOAD_NOTIFICATION_ID, notification)
                                }
                                bytesRead = inputStream.read(buffer) // Read next chunk for next iteration
                            }
                        }
                    }
                }

                if (!isActive) { // Check if coroutine was cancelled (e.g., service destroyed)
                    Log.w(TAG, "Download cancelled.")
                    apkFile.delete()
                    return@launch
                }

                Log.i(TAG, "Download completed. Verifying checksum...")
                val actualChecksum = calculateChecksum(apkFile)
                if (actualChecksum.equals(expectedChecksum, ignoreCase = true)) { // Make checksum case-insensitive if needed
                    Log.i(TAG, "Checksum verified. Proceeding to install update...")
                    installApk(apkFile, versionName, versionCode, isMandatory)
                } else {
                    Log.e(TAG, "Checksum verification failed. Expected: $expectedChecksum, Actual: $actualChecksum")
                    apkFile.delete()
                    showErrorNotification("Update download failed: File corrupted.")
                    reportInstallAttempt(versionCode, false, "Checksum mismatch")
                    // stopForeground and stopSelf handled in finally
                }

            } catch (e: CancellationException) {
                Log.w(TAG, "Download job cancelled.", e)
                apkFile?.delete()
            } catch (e: Exception) {
                Log.e(TAG, "Error downloading update", e)
                apkFile?.delete()
                showErrorNotification("Update download failed: ${e.localizedMessage}")
                reportInstallAttempt(versionCode, false, e.localizedMessage ?: "Download exception")
            } finally {
                stopForeground(true)
                stopSelf()
            }
        }
    }


    private fun installApk(apkFile: File, versionName: String, versionCode: Int, isMandatory: Boolean) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (!packageManager.canRequestPackageInstalls()) {
                    Log.w(TAG, "Permission to install unknown apps not granted.")
                    val intent = Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES).apply {
                        data = "package:$packageName".toUri()
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_NO_HISTORY
                    }
                    val permPendingIntentFlags =
                        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                    val permPendingIntent = PendingIntent.getActivity(this, 0, intent, permPendingIntentFlags)

                    val notification = createActionableNotification(
                        "Installation Permission Needed",
                        "Please allow installation from unknown sources to update.",
                        permPendingIntent
                    )
                    notificationManager.notify(ERROR_NOTIFICATION_ID, notification)
                    reportInstallAttempt(versionCode, false, "Install permission not granted by user yet")
                    return
                }
            }

            val apkUri = FileProvider.getUriForFile(
                this,
                "$packageName.fileprovider",
                apkFile
            )

            val installIntent = Intent(Intent.ACTION_VIEW).apply {
                setDataAndType(apkUri, "application/vnd.android.package-archive")
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_GRANT_READ_URI_PERMISSION
            }
            startActivity(installIntent)

            Log.i(TAG, "Installation intent started for version $versionName")
            reportInstallAttempt(versionCode, true, null)

        } catch (e: Exception) {
            Log.e(TAG, "Error installing APK", e)
            showErrorNotification("Failed to start update installation: ${e.localizedMessage}")
            reportInstallAttempt(versionCode, false, e.localizedMessage ?: "Install intent failed")
        }
    }

    private fun reportInstallAttempt(versionCode: Int, success: Boolean, errorMessage: String?) {
        serviceScope.launch {
            try {
                val deviceId = getAppSpecificDeviceId()
                apiServiceInstance.reportAppInstall(
                    deviceId = deviceId,
                    versionCode = versionCode,
                    success = success,
                    errorMessage = errorMessage
                )
                Log.d(TAG, "Install attempt reported. Success: $success, Version: $versionCode, Error: $errorMessage")
            } catch (e: Exception) {
                Log.e(TAG, "Failed to report install attempt", e)
            }
        }
    }

    private fun showErrorNotification(message: String) {
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Update Error")
            .setContentText(message)
            .setSmallIcon(R.drawable.ic_error)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()
        notificationManager.notify(ERROR_NOTIFICATION_ID, notification)
    }

    private fun getCurrentVersionCode(): Int {
        return try {
            val packageInfo = packageManager.getPackageInfo(packageName, 0)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                packageInfo.longVersionCode.toInt()
            } else {
                @Suppress("DEPRECATION")
                packageInfo.versionCode
            }
        } catch (e: Exception) {
            Log.e(TAG, "Failed to get current version code", e)
            0
        }
    }

    private fun getAppSpecificDeviceId(): String {
        val sharedPrefs = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        var deviceId = sharedPrefs.getString("device_unique_id", null)
        if (deviceId == null) {
            deviceId = java.util.UUID.randomUUID().toString()
            sharedPrefs.edit { putString("device_unique_id", deviceId) }
        }
        return deviceId
    }

    private fun calculateChecksum(file: File): String {
        return try {
            val digest = MessageDigest.getInstance("SHA-256") // Or "MD5" if your server uses that
            file.inputStream().use { fis ->
                val buffer = ByteArray(8192)
                var bytesRead: Int
                while (fis.read(buffer).also { bytesRead = it } != -1) {
                    digest.update(buffer, 0, bytesRead)
                }
            }
            digest.digest().joinToString("") { "%02x".format(it) }
        } catch (e: Exception) {
            Log.e(TAG, "Failed to calculate checksum", e)
            ""
        }
    }
}
