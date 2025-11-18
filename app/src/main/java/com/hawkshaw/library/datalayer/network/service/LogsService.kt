package com.hawkshaw.library.datalayer.network.service

import com.hawkshaw.library.datalayer.models.AppLogsRequest
import com.hawkshaw.library.datalayer.models.AppLogsResponse
import com.hawkshaw.library.datalayer.network.twirp.ApiResponse

/**
 * Service for handling application logs operations
 */
interface LogsService {
    /**
     * Push application logs to the server
     * 
     * @param request The logs data to push
     * @return The response from the server
     */
    suspend fun pushAppLogs(request: AppLogsRequest): ApiResponse<AppLogsResponse>
} 