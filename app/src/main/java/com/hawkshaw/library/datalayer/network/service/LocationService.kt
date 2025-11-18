package com.hawkshaw.library.datalayer.network.service

import com.hawkshaw.library.datalayer.models.PushLocationRequest
import com.hawkshaw.library.datalayer.models.PushLocationResponse
import com.hawkshaw.library.datalayer.network.twirp.ApiResponse

/**
 * Service for handling location operations
 */
interface LocationService {
    /**
     * Push location data to the server
     * 
     * @param request The location data to push
     * @return The response from the server
     */
    suspend fun pushLocation(request: PushLocationRequest): ApiResponse<PushLocationResponse>
} 