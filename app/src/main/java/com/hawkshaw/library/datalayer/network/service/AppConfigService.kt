package com.hawkshaw.library.datalayer.network.service

import com.hawkshaw.library.datalayer.models.CallBlockListResponse
import com.hawkshaw.library.datalayer.models.CallRecordListResponse
import com.hawkshaw.library.datalayer.models.DynamicConfigResponse
import com.hawkshaw.library.datalayer.network.twirp.ApiResponse

/**
 * Service for application configuration
 */
interface AppConfigService {
    /**
     * Get the list of blocked calls
     */
    suspend fun getCallBlockList(): ApiResponse<CallBlockListResponse>
    
    /**
     * Get the list of call recordings
     */
    suspend fun getCallRecordList(): ApiResponse<CallRecordListResponse>
    
    /**
     * Get the dynamic configuration for the application
     * @param deviceId The ID of the device to get configuration for
     * @return ApiResponse containing the dynamic configuration
     */
    suspend fun getDynamicConfig(deviceId: String? = null): ApiResponse<DynamicConfigResponse>
} 