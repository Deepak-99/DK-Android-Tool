package com.hawkshaw.library.features.screen

import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.PixelFormat
import android.hardware.display.DisplayManager
import android.hardware.display.VirtualDisplay
import android.media.Image
import android.media.ImageReader
import android.media.projection.MediaProjection
import android.media.projection.MediaProjectionManager
import android.os.IBinder
import android.util.Base64
import android.util.DisplayMetrics
import android.util.Log
import android.view.WindowManager
import com.hawkshaw.library.App
import com.hawkshaw.library.network.ApiService
import com.hawkshaw.library.datalayer.network.sendScreenFrame
import com.hawkshaw.library.utils.NotificationKt
import kotlinx.coroutines.*
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer
import androidx.core.graphics.createBitmap
import androidx.core.graphics.scale

/**
 * Service for handling screen projection/streaming functionality
 */
class ScreenProjectionService : Service() {
    
    companion object {
        private const val TAG = "ScreenProjectionService"
        private const val NOTIFICATION_ID = 1002
        
        const val ACTION_START_PROJECTION = "com.hawkshaw.START_SCREEN_PROJECTION"
        const val ACTION_STOP_PROJECTION = "com.hawkshaw.STOP_SCREEN_PROJECTION"
        
        const val EXTRA_RESULT_CODE = "result_code"
        const val EXTRA_RESULT_DATA = "result_data"
        const val EXTRA_SESSION_ID = "session_id"
        const val EXTRA_QUALITY = "quality"
        const val EXTRA_FRAME_RATE = "frame_rate"
        const val EXTRA_RESOLUTION = "resolution"
        const val EXTRA_SERVER_ENDPOINT = "server_endpoint"
    }
    
    private var mediaProjection: MediaProjection? = null
    private var virtualDisplay: VirtualDisplay? = null
    private var imageReader: ImageReader? = null
    private var isProjecting = false
    private var sessionId: String? = null
    private var serverEndpoint: String? = null
    private var frameRate: Int = 15
    private var quality: String = "medium"
    private var frameNumber = 0
    
    private val serviceScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private var captureJob: Job? = null
    
    override fun onBind(intent: Intent?): IBinder? = null
    
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "[DEBUG] onStartCommand called with action: ${intent?.action}")
        
        when (intent?.action) {
            ACTION_START_PROJECTION -> {
                val resultCode = intent.getIntExtra(EXTRA_RESULT_CODE, -1)
                val resultData = intent.getParcelableExtra<Intent>(EXTRA_RESULT_DATA)
                val sessionId = intent.getStringExtra(EXTRA_SESSION_ID)
                val quality = intent.getStringExtra(EXTRA_QUALITY) ?: "medium"
                val frameRate = intent.getIntExtra(EXTRA_FRAME_RATE, 15)
                val resolution = intent.getStringExtra(EXTRA_RESOLUTION)
                val serverEndpoint = intent.getStringExtra(EXTRA_SERVER_ENDPOINT)
                
                if (resultCode != -1 && resultData != null && sessionId != null && serverEndpoint != null) {
                    startScreenProjection(resultCode, resultData, sessionId, quality, frameRate, resolution, serverEndpoint)
                } else {
                    Log.e(TAG, "[ERROR] Invalid parameters for screen projection")
                    stopSelf()
                }
            }
            ACTION_STOP_PROJECTION -> {
                stopScreenProjection()
            }
        }
        
        return START_STICKY
    }
    
    private fun startScreenProjection(
        resultCode: Int,
        resultData: Intent,
        sessionId: String,
        quality: String,
        frameRate: Int,
        resolution: String?,
        serverEndpoint: String
    ) {
        Log.d(TAG, "[DEBUG] startScreenProjection called with sessionId: $sessionId, quality: $quality, frameRate: $frameRate")
        
        if (isProjecting) {
            Log.w(TAG, "[WARNING] Screen projection already in progress")
            return
        }
        
        try {
            this.sessionId = sessionId
            this.quality = quality
            this.frameRate = frameRate
            this.serverEndpoint = serverEndpoint
            
            // Get screen dimensions
            val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(displayMetrics)
            
            val (width, height) = parseResolution(resolution, displayMetrics)
            
            // Setup ImageReader for capturing frames
            imageReader = ImageReader.newInstance(width, height, PixelFormat.RGBA_8888, 2)
            imageReader?.setOnImageAvailableListener({ reader ->
                captureFrame(reader)
            }, null)
            
            // Get MediaProjection
            val mediaProjectionManager = getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager
            mediaProjection = mediaProjectionManager.getMediaProjection(resultCode, resultData)
            
            // Create VirtualDisplay
            virtualDisplay = mediaProjection?.createVirtualDisplay(
                "ScreenProjection",
                width, height, displayMetrics.densityDpi,
                DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR,
                imageReader!!.surface,
                null, null
            )
            
            isProjecting = true
            frameNumber = 0
            
            // Show notification
            startForeground(NOTIFICATION_ID, createProjectionNotification())
            
            Log.i(TAG, "[INFO] Screen projection started: $sessionId")
            
            // Start frame capture loop
            startFrameCapture()
            
        } catch (e: Exception) {
            Log.e(TAG, "[ERROR] Failed to start screen projection", e)
            cleanup()
            stopSelf()
        }
    }
    
    private fun startFrameCapture() {
        captureJob = serviceScope.launch {
            val frameInterval = 1000L / frameRate
            
            while (isProjecting) {
                try {
                    delay(frameInterval)
                } catch (e: Exception) {
                    Log.e(TAG, "[ERROR] Frame capture interrupted", e)
                    break
                }
            }
        }
    }
    
    private fun captureFrame(reader: ImageReader) {
        try {
            val image = reader.acquireLatestImage()
            if (image != null) {
                processFrame(image)
                image.close()
            }
        } catch (e: Exception) {
            Log.e(TAG, "[ERROR] Failed to capture frame", e)
        }
    }
    
    private fun processFrame(image: Image) {
        serviceScope.launch(Dispatchers.IO) {
            try {
                val bitmap = imageToBitmap(image)
                if (bitmap != null) {
                    val compressedBitmap = compressBitmap(bitmap)
                    val base64Data = bitmapToBase64(compressedBitmap)
                    
                    // Send frame to server
                    sendFrameToServer(base64Data)
                    
                    frameNumber++
                    bitmap.recycle()
                    compressedBitmap.recycle()
                }
            } catch (e: Exception) {
                Log.e(TAG, "[ERROR] Failed to process frame", e)
            }
        }
    }
    
    private fun imageToBitmap(image: Image): Bitmap? {
        return try {
            val planes = image.planes
            val buffer = planes[0].buffer
            val pixelStride = planes[0].pixelStride
            val rowStride = planes[0].rowStride
            val rowPadding = rowStride - pixelStride * image.width
            
            val bitmap = createBitmap(image.width + rowPadding / pixelStride, image.height)
            bitmap.copyPixelsFromBuffer(buffer)
            
            // Crop to actual image size if there's padding
            if (rowPadding == 0) {
                bitmap
            } else {
                val croppedBitmap = Bitmap.createBitmap(bitmap, 0, 0, image.width, image.height)
                bitmap.recycle()
                croppedBitmap
            }
        } catch (e: Exception) {
            Log.e(TAG, "[ERROR] Failed to convert image to bitmap", e)
            null
        }
    }
    
    private fun compressBitmap(bitmap: Bitmap): Bitmap {
        val compressionRatio = when (quality) {
            "low" -> 0.3f
            "medium" -> 0.6f
            "high" -> 1.0f
            else -> 0.6f
        }
        
        return if (compressionRatio < 1.0f) {
            val newWidth = (bitmap.width * compressionRatio).toInt()
            val newHeight = (bitmap.height * compressionRatio).toInt()
            bitmap.scale(newWidth, newHeight)
        } else {
            bitmap
        }
    }
    
    private fun bitmapToBase64(bitmap: Bitmap): String {
        val outputStream = ByteArrayOutputStream()
        val compressionQuality = when (quality) {
            "low" -> 50
            "medium" -> 75
            "high" -> 90
            else -> 75
        }
        
        bitmap.compress(Bitmap.CompressFormat.JPEG, compressionQuality, outputStream)
        val byteArray = outputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.NO_WRAP)
    }
    
    private suspend fun sendFrameToServer(frameData: String) {
        try {
            if (serverEndpoint != null && sessionId != null) {
                // Initialize ApiService with application context
                val apiService = ApiService(applicationContext)
                
                val result = apiService.sendScreenFrame(
                    sessionId = sessionId!!,
                    frameData = frameData,
                    frameNumber = frameNumber,
                    timestamp = System.currentTimeMillis()
                )
                
                if (!result.isSuccess) {
                    Log.w(TAG, "[WARNING] Failed to send frame to server: ${result.exceptionOrNull()?.message}")
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "[ERROR] Error sending frame to server", e)
        }
    }
    
    private fun stopScreenProjection() {
        Log.d(TAG, "[DEBUG] stopScreenProjection called")
        
        if (!isProjecting) {
            Log.w(TAG, "[WARNING] No active screen projection to stop")
            return
        }
        
        try {
            isProjecting = false
            captureJob?.cancel()
            
            Log.i(TAG, "[INFO] Screen projection stopped: $sessionId")
            
        } catch (e: Exception) {
            Log.e(TAG, "[ERROR] Failed to stop screen projection", e)
        } finally {
            cleanup()
            stopSelf()
        }
    }
    
    private fun cleanup() {
        try {
            virtualDisplay?.release()
            mediaProjection?.stop()
            imageReader?.close()
        } catch (e: Exception) {
            Log.e(TAG, "[ERROR] Cleanup error", e)
        }
        
        virtualDisplay = null
        mediaProjection = null
        imageReader = null
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
    
    private fun createProjectionNotification() = NotificationKt.getNotification(
        title = "Screen Projection",
        contentText = "Streaming screen...",
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
