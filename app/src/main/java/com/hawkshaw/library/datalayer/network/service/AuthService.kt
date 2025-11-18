package com.hawkshaw.library.datalayer.network.service

import com.hawkshaw.library.datalayer.models.LoginRequest
import com.hawkshaw.library.datalayer.models.LoginResponse
import com.hawkshaw.library.datalayer.network.twirp.ApiResponse

/**
 * Service for authentication operations
 */
interface AuthService {
    /**
     * Deauthenticate the current user
     */
    suspend fun deAuth(): ApiResponse<Unit>
    
    /**
     * Login with the given credentials
     * @param request The login credentials
     */
    suspend fun login(request: LoginRequest): ApiResponse<LoginResponse>
} 