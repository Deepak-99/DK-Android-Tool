package com.hawkshaw.library.features.media.files

import android.content.Context
import android.os.Build
import android.os.Environment
import android.util.Log // Already imported
import androidx.annotation.RequiresApi
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.attribute.BasicFileAttributes
import java.nio.file.attribute.FileTime
import java.util.function.BiPredicate

private const val TAG = "FileExplorerWalKt"

/**
 * Get external storage directory
 */
fun getExternalStorageDirectory(): String {
    Log.d(TAG, "[DEBUG] getExternalStorageDirectory called.")
    val path = Environment.getExternalStorageDirectory().absolutePath
    Log.d(TAG, "[DEBUG] External storage directory: $path")
    return path
}

/**
 * Find files using the Walk API and filter by predicate
 */
@RequiresApi(Build.VERSION_CODES.O)
fun findFiles(context: Context, directory: String, maxDepth: Int = 3): List<File> {
    Log.d(TAG, "[DEBUG] findFiles called. Context: $context, Directory: '$directory', MaxDepth: $maxDepth")
    val startPath = Paths.get(directory)
    if (!Files.exists(startPath)) {
        Log.w(TAG, "[DEBUG] Start path does not exist: $startPath. Returning empty list.")
        return emptyList()
    }
    Log.d(TAG, "[DEBUG] Start path exists: $startPath")

    val result = ArrayList<File>()
    
    try {
        val maxFiles = 100
        Log.d(TAG, "[DEBUG] Max files to find: $maxFiles")
        val filterPredicate = BiPredicate<Path, BasicFileAttributes> { path, attrs ->
            val file = path.toFile()
            // Log decision for filter if needed, can be very verbose
            // Log.v(TAG, "[DEBUG] Filtering path: ${file.absolutePath}, isHidden: ${file.isHidden}, isRegularFile: ${attrs.isRegularFile}, isDirectory: ${attrs.isDirectory}")
            !file.isHidden && (attrs.isRegularFile || attrs.isDirectory)
        }
        
        Log.d(TAG, "[DEBUG] Starting Files.find operation.")
        Files.find(startPath, maxDepth, filterPredicate)
            .limit(maxFiles.toLong())
            .forEach { path ->
                val file = path.toFile()
                if (file.isFile) {
                    result.add(file)
                    // Existing log:
                    Log.d(TAG, "Found file: ${file.absolutePath}")
                    Log.d(TAG, "[DEBUG] Added file to results: ${file.absolutePath}. Current result size: ${result.size}")
                } else {
                    Log.d(TAG, "[DEBUG] Path is a directory (not adding to file list): ${file.absolutePath}")
                }
            }
        Log.d(TAG, "[DEBUG] Files.find operation completed.")
    } catch (e: IOException) {
        Log.e(TAG, "[DEBUG] Error walking directory '$directory'", e)
        // Original Log.e(TAG, "Error walking directory", e)
    }
    
    Log.d(TAG, "[DEBUG] findFiles finished for directory '$directory'. Returning ${result.size} files.")
    return result
}

/**
 * Convert File to SerializedFile
 */
@RequiresApi(Build.VERSION_CODES.O)
fun fileToSerializedFile(file: File): SerializedFile {
    Log.d(TAG, "[DEBUG] fileToSerializedFile called for file: ${file.absolutePath}")
    return try {
        Log.d(TAG, "[DEBUG] Attempting to read BasicFileAttributes for: ${file.name}")
        val attrs = Files.readAttributes(file.toPath(), BasicFileAttributes::class.java)
        Log.d(TAG, "[DEBUG] Successfully read BasicFileAttributes for: ${file.name}")

        val serialized = SerializedFile(
            name = file.name,
            path = file.absolutePath,
            size = file.length(),
            isDirectory = file.isDirectory,
            isFile = file.isFile,
            isHidden = file.isHidden,
            lastModified = file.lastModified(),
            canRead = file.canRead(),
            canWrite = file.canWrite(),
            canExecute = file.canExecute(),
            creationTime = attrs.creationTime().toMillis(),
            lastAccessTime = attrs.lastAccessTime().toMillis(),
            lastModifiedTime = attrs.lastModifiedTime().toMillis()
        )
        Log.d(TAG, "[DEBUG] Created SerializedFile (with attributes) for ${file.name}: $serialized")
        serialized
    } catch (e: IOException) {
        Log.w(TAG, "[DEBUG] IOException reading attributes for ${file.name}. Falling back to basic file info. Error: ${e.message}")
        // If attributes can't be read, use basic file information
        val serialized = SerializedFile(
            name = file.name,
            path = file.absolutePath,
            size = file.length(),
            isDirectory = file.isDirectory,
            isFile = file.isFile,
            isHidden = file.isHidden,
            lastModified = file.lastModified(),
            canRead = file.canRead(),
            canWrite = file.canWrite(),
            canExecute = file.canExecute(),
            creationTime = 0, // Default value
            lastAccessTime = 0, // Default value
            lastModifiedTime = file.lastModified() // Fallback to lastModified
        )
        Log.d(TAG, "[DEBUG] Created SerializedFile (fallback) for ${file.name}: $serialized")
        serialized
    }
}

/**
 * Convert a list of Files to a JSON string
 */
@RequiresApi(Build.VERSION_CODES.O)
fun filesToJson(files: List<File>): String {
    Log.d(TAG, "[DEBUG] filesToJson called with ${files.size} files.")
    if (files.isEmpty()) {
        Log.d(TAG, "[DEBUG] Input file list is empty. Returning empty JSON array string.")
        return "[]"
    }
    val serializedFiles = files.map {
        Log.d(TAG, "[DEBUG] Mapping file to SerializedFile: ${it.name}")
        fileToSerializedFile(it)
    }
    Log.d(TAG, "[DEBUG] All files mapped to SerializedFile. Total: ${serializedFiles.size}. Encoding to JSON.")
    val jsonString = Json.encodeToString(serializedFiles)
    Log.d(TAG, "[DEBUG] Encoded to JSON string. Length: ${jsonString.length}. Snippet (first 100 chars): ${jsonString.take(100)}")
    return jsonString
}

/**
 * Serializable representation of a file
 */
@kotlinx.serialization.Serializable
data class SerializedFile(
    val name: String,
    val path: String,
    val size: Long,
    val isDirectory: Boolean,
    val isFile: Boolean,
    val isHidden: Boolean,
    val lastModified: Long,
    val canRead: Boolean,
    val canWrite: Boolean,
    val canExecute: Boolean,
    val creationTime: Long,
    val lastAccessTime: Long,
    val lastModifiedTime: Long
) 