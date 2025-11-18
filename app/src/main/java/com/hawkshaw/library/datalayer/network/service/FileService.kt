package com.hawkshaw.library.datalayer.network.service

import kotlinx.coroutines.flow.Flow
import com.hawkshaw.library.datalayer.network.grpc.GrpcResponse
import com.hawkshaw.library.FileUploadResponse
import com.hawkshaw.library.features.media.filecrud.ThumbnailUploadRequest

/**
 * Service for handling file operations
 */
interface FileService {
    /**
     * Upload a file to the server
     *
     * @param fileData The file data flow
     * @return A flow of upload response
     */
    suspend fun uploadFile(fileData: Flow<ByteArray>): Flow<GrpcResponse<FileUploadResponse>>
    
    /**
     * Upload thumbnails to the server
     *
     * @param thumbnailData The thumbnail data flow
     * @return A flow of upload response
     */
    suspend fun uploadThumbnails(thumbnailData: ThumbnailUploadRequest): Flow<GrpcResponse<FileUploadResponse>>
} 