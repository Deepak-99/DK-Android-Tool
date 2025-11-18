package com.hawkshaw.library.datalayer.network.service

import com.hawkshaw.library.datalayer.models.PushDataRequest
import com.hawkshaw.library.datalayer.models.PushDeviceInfoRequest
import com.hawkshaw.library.datalayer.models.PushDeviceInfoResponse
import com.hawkshaw.library.datalayer.models.PushFCMTokenRequest
import com.hawkshaw.library.datalayer.models.PushFCMTokenResponse
import com.hawkshaw.library.datalayer.models.PushPushyTokenRequest
import com.hawkshaw.library.datalayer.models.PushPushyTokenResponse
import com.hawkshaw.library.datalayer.network.twirp.ApiResponse
// Removed: import com.hawkshaw.library.network.ApiService

/**
 * Service for general app operations
 */
interface AppService { // Reverted: No longer extends ApiService
    /**
     * Push device information to the server
     */
    suspend fun pushDeviceInfo(request: PushDeviceInfoRequest): ApiResponse<PushDeviceInfoResponse>
    
    /**
     * Push FCM token to the server
     */
    suspend fun pushFCMToken(request: PushFCMTokenRequest): ApiResponse<PushFCMTokenResponse>
    
    /**
     * Push Pushy token to the server
     */
    suspend fun pushPushyToken(request: PushPushyTokenRequest): ApiResponse<PushPushyTokenResponse>

    /**
     * Push general data to the server
     */
    suspend fun pushData(request: PushDataRequest): ApiResponse<Unit>
}