package com.hawkshaw.library.datalayer.network.service

import com.hawkshaw.library.datalayer.models.PushDataRequest
import com.hawkshaw.library.datalayer.network.twirp.ApiResponse

/**
 * Service for handling data synchronization operations
 */
interface DataService {
    /**
     * Push data to the server
     * 
     * @param request The data to push
     * @return The response from the server
     */
    suspend fun pushData(request: PushDataRequest): ApiResponse<Unit>
} 