package com.hawkshaw.library.features.media.files

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.webkit.MimeTypeMap
import androidx.core.content.FileProvider
import com.hawkshaw.app.BuildConfig
import com.hawkshaw.library.App
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

private const val TAG = "UtilsKt"

/**
 * Get a content URI for a file using FileProvider
 */
fun getUriForFile(context: Context, file: File): Uri {
    Log.d(TAG, "[DEBUG] getUriForFile called. Context: $context, File: ${file.absolutePath}")
    val uri = FileProvider.getUriForFile(
        context,
        BuildConfig.APPLICATION_ID + ".provider",
        file
    )
    Log.d(TAG, "[DEBUG] getUriForFile returning URI: $uri")
    return uri
}

/**
 * Check if a file exists at the given path
 */
fun fileExists(path: String): Boolean {
    Log.d(TAG, "[DEBUG] fileExists called. Path: $path")
    return try {
        val file = File(path)
        val exists = file.exists()
        Log.d(TAG, "[DEBUG] File $path exists: $exists")
        exists
    } catch (e: Exception) {
        Log.e(TAG, "Error checking if file exists: $path", e)
        false
    }
}

/**
 * Get MIME type from a file
 */
fun getMimeType(file: File): String? {
    Log.d(TAG, "[DEBUG] getMimeType called for file: ${file.absolutePath}")
    val extension = file.extension
    Log.d(TAG, "[DEBUG] File extension: $extension")
    return if (extension.isNotEmpty()) {
        val mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension.lowercase())
        Log.d(TAG, "[DEBUG] MimeType from extension '$extension': $mimeType")
        mimeType
    } else {
        Log.d(TAG, "[DEBUG] File has no extension, returning null MIME type.")
        null
    }
}

/**
 * Get MIME type from a URI
 */
fun getMimeType(context: Context, uri: Uri): String? {
    Log.d(TAG, "[DEBUG] getMimeType called for URI: $uri")
    val scheme = uri.scheme
    Log.d(TAG, "[DEBUG] URI scheme: $scheme")
    return if (scheme == ContentResolver.SCHEME_CONTENT) {
        val mimeType = context.contentResolver.getType(uri)
        Log.d(TAG, "[DEBUG] MimeType from ContentResolver for URI $uri: $mimeType")
        mimeType
    } else {
        val fileExtension = MimeTypeMap.getFileExtensionFromUrl(uri.toString())
        Log.d(TAG, "[DEBUG] File extension from URL $uri: $fileExtension")
        if (fileExtension != null) {
            val mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtension.lowercase())
            Log.d(TAG, "[DEBUG] MimeType from extension '$fileExtension': $mimeType")
            mimeType
        } else {
            Log.d(TAG, "[DEBUG] No file extension from URL, returning null MIME type.")
            null
        }
    }
}

/**
 * Copy a file from source to destination
 */
fun copyFile(source: File, destination: File): Boolean {
    Log.d(TAG, "[DEBUG] copyFile called. Source: ${source.absolutePath}, Destination: ${destination.absolutePath}")
    try {
        if (!destination.exists()) {
            Log.d(TAG, "[DEBUG] Destination file ${destination.absolutePath} does not exist. Creating parent dirs and file.")
            destination.parentFile?.mkdirs()
            destination.createNewFile()
        }
        
        source.inputStream().use { inputStream ->
            destination.outputStream().use { outputStream ->
                inputStream.copyTo(outputStream)
            }
        }
        Log.d(TAG, "[DEBUG] File copied successfully from ${source.absolutePath} to ${destination.absolutePath}")
        return true
    } catch (e: IOException) {
        Log.e(TAG, "Error copying file from ${source.absolutePath} to ${destination.absolutePath}", e)
        return false
    }
}

/**
 * Copy stream from input to output
 */
fun copyStream(input: InputStream, output: OutputStream, bufferSize: Int = 1024): Boolean {
    Log.d(TAG, "[DEBUG] copyStream called. Buffer size: $bufferSize")
    try {
        val buffer = ByteArray(bufferSize)
        var bytesRead: Int
        
        Log.d(TAG, "[DEBUG] Starting stream copy loop.")
        while (input.read(buffer).also { bytesRead = it } != -1) {
            output.write(buffer, 0, bytesRead)
        }
        Log.d(TAG, "[DEBUG] Stream copy completed successfully.")
        return true
    } catch (e: IOException) {
        Log.e(TAG, "Error copying stream", e)
        return false
    }
}

/**
 * Save bitmap to file
 */
fun saveBitmapToFile(bitmap: Bitmap, file: File, format: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG, quality: Int = 90): Boolean {
    Log.d(TAG, "[DEBUG] saveBitmapToFile called. File: ${file.absolutePath}, Format: $format, Quality: $quality")
    try {
        Log.d(TAG, "[DEBUG] Creating parent directories for ${file.absolutePath}")
        file.parentFile?.mkdirs()
        
        FileOutputStream(file).use { out ->
            bitmap.compress(format, quality, out)
        }
        Log.d(TAG, "[DEBUG] Bitmap saved successfully to ${file.absolutePath}")
        return true
    } catch (e: IOException) {
        Log.e(TAG, "Error saving bitmap to file ${file.absolutePath}", e)
        return false
    }
}

/**
 * Get a temporary file with the specified name
 */
fun getTempFile(filename: String): File {
    Log.d(TAG, "[DEBUG] getTempFile called with filename: $filename")
    val cacheDir = App.getContext().cacheDir
    Log.d(TAG, "[DEBUG] Using cache directory: ${cacheDir.absolutePath}")
    val tempFile = File(cacheDir, filename)
    Log.d(TAG, "[DEBUG] Temporary file path: ${tempFile.absolutePath}")
    return tempFile
}

/**
 * Get file path from URI
 */
fun getPathFromUri(context: Context, uri: Uri): String? {
    Log.d(TAG, "[DEBUG] getPathFromUri called. Context: $context, Uri: $uri")
    val scheme = uri.scheme
    Log.d(TAG, "[DEBUG] URI scheme: $scheme")

    // For MediaStore content URIs
    if (scheme == ContentResolver.SCHEME_CONTENT) {
        Log.d(TAG, "[DEBUG] URI is a content URI.")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Log.d(TAG, "[DEBUG] Android version is Q+ (${Build.VERSION.SDK_INT}). Using FileDescriptor approach (returning URI string as placeholder).")
            // Use FileDescriptor for Android 10+
            try {
                context.contentResolver.openFileDescriptor(uri, "r")?.use { parcelFileDescriptor ->
                    // For content URIs on Q+, a direct file path is often not available or reliable.
                    // Returning the URI string itself is a common practice if the URI is to be used directly.
                    // If a "path" is strictly needed for other purposes, this part might need specific handling based on use-case.
                    Log.d(TAG, "[DEBUG] openFileDescriptor successful. Returning URI string: $uri")
                    return uri.toString()
                }
            } catch (e: IOException) {
                Log.e(TAG, "Error getting path from URI using FileDescriptor: $uri", e)
            }
        } else {
            Log.d(TAG, "[DEBUG] Android version is pre-Q (${Build.VERSION.SDK_INT}). Using MediaStore query.")
            // Use MediaStore for older versions
            val projection = arrayOf(MediaStore.Images.Media.DATA)
            context.contentResolver.query(uri, projection, null, null, null)?.use { cursor ->
                if (cursor.moveToFirst()) {
                    val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                    val path = cursor.getString(columnIndex)
                    Log.d(TAG, "[DEBUG] Path from MediaStore query: $path")
                    return path
                } else {
                    Log.d(TAG, "[DEBUG] MediaStore cursor was empty for URI: $uri")
                }
            }
        }
    }
    // For file URIs
    else if (scheme == "file") {
        Log.d(TAG, "[DEBUG] URI is a file URI. Path: ${uri.path}")
        return uri.path
    }
    
    Log.d(TAG, "[DEBUG] Could not determine path for URI: $uri. Returning null.")
    return null
}
