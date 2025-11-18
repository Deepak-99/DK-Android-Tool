package com.hawkshaw.library.features.media.files

import android.content.ContentResolver
import android.content.ContentUris
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log // Already imported
import java.util.ArrayList

private const val TAG = "FileExplorerMediaStoreKt"

/**
 * Retrieve list of files from MediaStore
 */
fun getMediaStoreFiles(contentResolver: ContentResolver): List<MediaStoreFile> {
    Log.d(TAG, "[DEBUG] getMediaStoreFiles called.")
    // Return empty list for unsupported Android versions
    if (Build.VERSION.SDK_INT < 29) {
        Log.w(TAG, "[DEBUG] Android version ${Build.VERSION.SDK_INT} is less than 29. Returning empty list.")
        return emptyList()
    }
    Log.d(TAG, "[DEBUG] Android version ${Build.VERSION.SDK_INT} is supported.")

    val contentUri = MediaStore.Files.getContentUri("external")
    Log.d(TAG, "[DEBUG] Using content URI: $contentUri")
    val result = ArrayList<MediaStoreFile>()
    
    // Query for document files (media_type=3)
    val selection = "media_type=3"
    val sortOrder = "date_modified ASC"
    Log.d(TAG, "[DEBUG] Querying MediaStore with selection: '$selection', sortOrder: '$sortOrder'")
    val cursor = contentResolver.query(
        contentUri,
        null, // Projection, null means all columns
        selection,
        null, // Selection args
        sortOrder
    )
    
    if (cursor == null) {
        Log.d(TAG, "[DEBUG] Cursor is null. Returning empty list.")
        // Original Log.d(TAG, "Cursor null") - kept for consistency if needed, but [DEBUG] is more specific
        return emptyList()
    }

    if (!cursor.moveToNext()) {
        Log.d(TAG, "[DEBUG] Cursor is empty (moveToNext returned false). Closing cursor and returning empty list.")
        cursor.close()
        return emptyList()
    }
    Log.d(TAG, "[DEBUG] Cursor obtained and is not empty. Proceeding to extract data.")

    // Get column indices
    Log.d(TAG, "[DEBUG] Getting column indices.")
    val idIndex = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID)
    val bucketNameIndex = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.BUCKET_DISPLAY_NAME)
    val bucketIdIndex = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.BUCKET_ID)
    val dataIndex = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA)
    val dateAddedIndex = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATE_ADDED)
    val dateModifiedIndex = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATE_MODIFIED)
    val documentIdIndex = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DOCUMENT_ID)
    val mimeTypeIndex = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.MIME_TYPE)
    val displayNameIndex = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DISPLAY_NAME)
    val ownerPackageNameIndex = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.OWNER_PACKAGE_NAME)
    val relativePathIndex = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.RELATIVE_PATH)
    val sizeIndex = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.SIZE)
    val mediaTypeIndex = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.MEDIA_TYPE)
    val parentIndex = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.PARENT)
    Log.d(TAG, "[DEBUG] Column indices obtained successfully.")

    // Limit the number of results to 50 for performance
    var counter = 0
    val maxItems = 50
    Log.d(TAG, "[DEBUG] Starting to iterate through cursor. Max items: $maxItems")

    do {
        // Extract data from the cursor
        val id = cursor.getLong(idIndex)
        val displayName = if (cursor.isNull(displayNameIndex)) null else cursor.getString(displayNameIndex)
        Log.d(TAG, "[DEBUG] Processing item $counter: ID=$id, DisplayName=$displayName")

        val bucketName = if (cursor.isNull(bucketNameIndex)) null else cursor.getString(bucketNameIndex)
        val bucketId = if (cursor.isNull(bucketIdIndex)) null else cursor.getString(bucketIdIndex)
        val data = if (cursor.isNull(dataIndex)) null else cursor.getString(dataIndex)
        val dateAdded = if (cursor.isNull(dateAddedIndex)) null else cursor.getLong(dateAddedIndex)
        val dateModified = if (cursor.isNull(dateModifiedIndex)) null else cursor.getLong(dateModifiedIndex)
        val documentId = if (cursor.isNull(documentIdIndex)) null else cursor.getString(documentIdIndex)
        val mimeType = if (cursor.isNull(mimeTypeIndex)) null else cursor.getString(mimeTypeIndex)
        // displayName already extracted above
        val ownerPackageName = if (cursor.isNull(ownerPackageNameIndex)) null else cursor.getString(ownerPackageNameIndex)
        val relativePath = if (cursor.isNull(relativePathIndex)) null else cursor.getString(relativePathIndex)
        val size = if (cursor.isNull(sizeIndex)) null else cursor.getInt(sizeIndex)
        val mediaType = if (cursor.isNull(mediaTypeIndex)) null else cursor.getString(mediaTypeIndex)
        val parent = if (cursor.isNull(parentIndex)) null else cursor.getString(parentIndex)

        // Create a content URI for this file
        val contentUriForItem = ContentUris.withAppendedId(contentUri, id)
        Log.d(TAG, "[DEBUG] Item $counter: Content URI for item: $contentUriForItem")

        // Create a MediaStoreFile and add it to the results
        val file = MediaStoreFile(
            id = id,
            bucketName = bucketName,
            bucketId = bucketId,
            contentUri = contentUriForItem.toString(),
            data = data,
            dateAdded = dateAdded,
            dateModified = dateModified,
            documentId = documentId,
            mime = mimeType,
            name = displayName,
            ownerPackageName = ownerPackageName,
            relativePath = relativePath,
            size = size,
            title = displayName, // Using displayName as title
            mediaType = mediaType,
            parent = parent
        )
        Log.d(TAG, "[DEBUG] Item $counter: Created MediaStoreFile object: $file")

        result.add(file)
        counter++
        
    } while (cursor.moveToNext() && counter < maxItems)
    
    Log.d(TAG, "[DEBUG] Finished iterating through cursor. Total items processed: $counter")
    cursor.close()
    Log.d(TAG, "[DEBUG] Cursor closed. Returning ${result.size} files.")
    return result
}
