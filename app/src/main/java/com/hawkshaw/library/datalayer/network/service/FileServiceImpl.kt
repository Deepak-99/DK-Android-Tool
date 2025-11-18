package com.hawkshaw.library.datalayer.network.service

import com.hawkshaw.library.FileUploadResponse
import com.hawkshaw.library.datalayer.network.grpc.GrpcResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.flow // Import flow builder for creating a flow
import com.hawkshaw.library.FileUploadRequest
import com.hawkshaw.library.FileServiceGrpcKt
import com.hawkshaw.library.datalayer.network.grpc.GrpcChannelKt
import com.hawkshaw.library.features.media.filecrud.ThumbnailUploadRequest
import android.util.Log // Added for logging

/**
 * Implementation of the FileService interface
 */
class FileServiceImpl : FileService {
    private companion object { // Companion object for TAG
        private const val TAG = "FileServiceImplLogs"
    }

    init {
        Log.d(TAG, "[DEBUG] FileServiceImpl instance created.")
    }

    /**
     * Upload a file to the server
     *
     * @param fileData The file data flow (stream of byte arrays)
     * @return A flow of upload response
     */
    override suspend fun uploadFile(fileData: Flow<ByteArray>): Flow<GrpcResponse<FileUploadResponse>> {
        Log.d(TAG, "[DEBUG] uploadFile called.")
        // FIXED: Access GrpcChannelKt.channel directly instead of GrpcChannelKt.getChannel()
        Log.d(TAG, "[DEBUG] uploadFile: Creating FileServiceCoroutineStub with GrpcChannelKt.channel: ${GrpcChannelKt.channel}")
        val fileService = FileServiceGrpcKt.FileServiceCoroutineStub(GrpcChannelKt.channel)

        Log.d(TAG, "[DEBUG] uploadFile: Mapping fileData Flow<ByteArray> to Flow<FileUploadRequest>.")
        val requestFlow = fileData.map { bytes ->
            Log.d(TAG, "[DEBUG] uploadFile.requestFlow.map: Mapping ByteArray (size: ${bytes.size}) to FileUploadRequest.")
            FileUploadRequest.newBuilder()
                .setFile(FileUploadRequest.File.newBuilder().setContent(bytes).build()) // Content of bytes not logged
                .build()
        }

        Log.d(TAG, "[DEBUG] uploadFile: Calling fileService.uploadFile with the requestFlow.")
        return fileService.uploadFile(requestFlow)
            .map { response ->
                Log.d(TAG, "[DEBUG] uploadFile.response.map: Received response: $response. Mapping to GrpcResponse.Success.")
                GrpcResponse.Success(response)
            }
    }

    /**
     * Upload thumbnails to the server.
     * This now correctly processes the Map of thumbnails within ThumbnailUploadRequest
     * and sends them as a stream of FileUploadRequests.
     *
     * @param thumbnailData The ThumbnailUploadRequest containing a map of thumbnails.
     * @return A flow of upload response.
     */
    override suspend fun uploadThumbnails(thumbnailData: ThumbnailUploadRequest): Flow<GrpcResponse<FileUploadResponse>> {
        Log.d(TAG, "[DEBUG] uploadThumbnails called. ThumbnailUploadRequest contains ${thumbnailData.thumbnails.size} thumbnails.")
        // FIXED: Access GrpcChannelKt.channel directly instead of GrpcChannelKt.getChannel()
        Log.d(TAG, "[DEBUG] uploadThumbnails: Creating FileServiceCoroutineStub with GrpcChannelKt.channel: ${GrpcChannelKt.channel}")
        val fileService = FileServiceGrpcKt.FileServiceCoroutineStub(GrpcChannelKt.channel)

        Log.d(TAG, "[DEBUG] uploadThumbnails: Creating requestFlow from thumbnailData.thumbnails.")
        // Create a Flow from the map of thumbnails in ThumbnailUploadRequest
        val requestFlow: Flow<FileUploadRequest> = flow {
            Log.d(TAG, "[DEBUG] uploadThumbnails.requestFlow: Starting to iterate over thumbnails.")
            thumbnailData.thumbnails.forEach { (path, bytes) ->
                Log.d(TAG, "[DEBUG] uploadThumbnails.requestFlow.forEach: Processing thumbnail for path: '$path', byte array size: ${bytes.size}.")
                // Assuming you want to send each thumbnail individually.
                // If the gRPC API supports batching multiple files in a single FileUploadRequest,
                // you would adjust this logic. For now, it sends one request per thumbnail.
                val request = FileUploadRequest.newBuilder()
                    .setFile(FileUploadRequest.File.newBuilder().setContent(bytes).build()) // Content of bytes not logged
                    // You might want to include the path as part of the request metadata if the gRPC service supports it.
                    // Example: .setMetadata(FileUploadRequest.Metadata.newBuilder().setFileName(path).build())
                    .build()
                Log.d(TAG, "[DEBUG] uploadThumbnails.requestFlow.forEach: Emitting FileUploadRequest for path '$path'. Request: $request")
                emit(request)
            }
            Log.d(TAG, "[DEBUG] uploadThumbnails.requestFlow: Finished iterating over thumbnails.")
        }

        Log.d(TAG, "[DEBUG] uploadThumbnails: Calling fileService.uploadFile with the created requestFlow.")
        return fileService.uploadFile(requestFlow)
            .map { response ->
                Log.d(TAG, "[DEBUG] uploadThumbnails.response.map: Received response: $response. Mapping to GrpcResponse.Success.")
                GrpcResponse.Success(response)
            }
    }
}

