package com.hawkshaw.library.datalayer.network.service

import com.hawkshaw.library.datalayer.models.PushLocationRequest
import com.hawkshaw.library.datalayer.models.PushLocationResponse
import com.hawkshaw.library.datalayer.network.twirp.ApiResponse
import com.hawkshaw.library.datalayer.network.twirp.apiCall
import android.util.Log // Added for logging

/**
 * Implementation of LocationService
 */
class LocationServiceImpl : LocationService {
    private companion object { // Companion object for TAG
        private const val TAG = "LocationServiceImplLogs"
    }

    init {
        Log.d(TAG, "[DEBUG] LocationServiceImpl instance created.")
    }
    
    /**
     * Push location data to the server
     * 
     * @param request The location data to push
     * @return The response from the server
     */
    override suspend fun pushLocation(request: PushLocationRequest): ApiResponse<PushLocationResponse> {
        Log.d(TAG, "[DEBUG] pushLocation called. Request: $request")
        val endpoint = "location.Location/PushLocation"
        // Corrected line 30: Ensure the entire message is a single string template
        Log.d(TAG, "[DEBUG] pushLocation: Calling apiCall with endpoint: \"$endpoint\", req: $request")
        // Explicit type arguments are already correctly used in the original snippet
        val response = apiCall<PushLocationRequest, PushLocationResponse>(
            endpoint = endpoint,
            req = request
        )
        Log.d(TAG, "[DEBUG] pushLocation: apiCall returned. Response: $response")
        return response
    }
} 

