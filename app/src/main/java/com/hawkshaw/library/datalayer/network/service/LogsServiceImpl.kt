package com.hawkshaw.library.datalayer.network.service

import com.hawkshaw.library.datalayer.models.AppLogsRequest
import com.hawkshaw.library.datalayer.models.AppLogsResponse
import com.hawkshaw.library.datalayer.network.twirp.ApiResponse
import com.hawkshaw.library.datalayer.network.twirp.apiCall
import android.util.Log // Added for logging

/**
 * Implementation of LogsService
 */
class LogsServiceImpl : LogsService {
    private companion object { // Companion object for TAG
        private const val TAG = "LogsServiceImplLogs"
    }

    init {
        Log.d(TAG, "[DEBUG] LogsServiceImpl instance created.")
    }

    /**
     * Push application logs to the server
     * 
     * @param request The logs data to push
     * @return The response from the server
     */
    override suspend fun pushAppLogs(request: AppLogsRequest): ApiResponse<AppLogsResponse> {
        Log.d(TAG, "[DEBUG] pushAppLogs called. Request: $request")
        val endpoint = "logs.Logs/PushAppLogs"
        Log.d(TAG, "[DEBUG] pushAppLogs: Calling apiCall with endpoint: \"$endpoint\", req: $request")
        // Explicit type arguments are already correctly used
        val response = apiCall<AppLogsRequest, AppLogsResponse>(
            endpoint = endpoint,
            req = request
        )
        Log.d(TAG, "[DEBUG] pushAppLogs: apiCall returned. Response: $response")
        return response
    }
}
