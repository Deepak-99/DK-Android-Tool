package com.hawkshaw.library.datalayer.network.service

import com.hawkshaw.library.datalayer.models.PushDataRequest
import com.hawkshaw.library.datalayer.network.twirp.ApiResponse
import com.hawkshaw.library.datalayer.network.twirp.apiCall
import android.util.Log // Added for logging

/**
 * Implementation of DataService
 */
class DataServiceImpl : DataService {
    private companion object { // Companion object for TAG
        private const val TAG = "DataServiceImplLogs"
    }

    init {
        Log.d(TAG, "[DEBUG] DataServiceImpl instance created.")
    }

    /**
     * Push data to the server
     * 
     * @param request The data to push
     * @return The response from the server
     */
    override suspend fun pushData(request: PushDataRequest): ApiResponse<Unit> {
        Log.d(TAG, "[DEBUG] pushData called. Request: $request")
        val endpoint = "data.Data/PushData"
        Log.d(TAG, "[DEBUG] pushData: Calling apiCall with endpoint: \"$endpoint\", request: $request")
        // Explicit type arguments are already correctly used in the original snippet for this file
        val response = apiCall<PushDataRequest, Unit>(
            endpoint = endpoint,
            req = request
        )
        Log.d(TAG, "[DEBUG] pushData: apiCall returned. Response: $response")
        return response
    }
}
