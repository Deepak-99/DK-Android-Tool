package com.hawkshaw.library.datalayer.network.service

import com.hawkshaw.library.datalayer.models.LoginRequest
import com.hawkshaw.library.datalayer.models.LoginResponse
import com.hawkshaw.library.datalayer.network.twirp.ApiResponse
import com.hawkshaw.library.datalayer.network.twirp.apiCall
import android.util.Log // Added for logging

/**
 * Implementation of the AuthService interface
 */
class AuthServiceImpl : AuthService {
    private companion object { // Companion object for TAG
        private const val TAG = "AuthServiceImplLogs"
    }

    init {
        Log.d(TAG, "[DEBUG] AuthServiceImpl instance created.")
    }

    /**
     * Deauthenticate the current user
     */
    override suspend fun deAuth(): ApiResponse<Unit> {
        Log.d(TAG, "[DEBUG] deAuth called.")
        val endpoint = "auth.Auth/DeAuth"
        // For deAuth, LoginRequest is created with empty email and deviceInfo
        val deAuthRequest = LoginRequest(
            email = "",
            deviceInfo = ""
        )
        Log.d(TAG, "[DEBUG] deAuth: Calling apiCall with endpoint: \"$endpoint\", request: $deAuthRequest")
        val response = apiCall<LoginRequest, Unit>(
            endpoint = endpoint,
            req = deAuthRequest
        )
        Log.d(TAG, "[DEBUG] deAuth: apiCall returned. Response: $response")
        return response
    }

    /**
     * Login with the given credentials
     * @param request The login credentials
     */
    override suspend fun login(request: LoginRequest): ApiResponse<LoginResponse> {
        Log.d(TAG, "[DEBUG] login called. Request: $request") // Request contains email, log carefully in production.
        val endpoint = "auth.Auth/AppLogin"
        Log.d(TAG, "[DEBUG] login: Calling apiCall with endpoint: \"$endpoint\", request: $request")
        val response = apiCall<LoginRequest, LoginResponse>(
            endpoint = endpoint,
            req = request
        )
        Log.d(TAG, "[DEBUG] login: apiCall returned. Response: $response")
        return response
    }
}
