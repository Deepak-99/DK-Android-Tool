package com.hawkshaw.library.datalayer.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model representing a login response from the server
 * Contains user details, authentication tokens, and timestamps
 */
@Serializable
data class LoginResponse(
    @SerialName("uid")
    val uid: String,
    
    @SerialName("name")
    val name: String,
    
    @SerialName("email")
    val email: String,
    
    @SerialName("token")
    val token: String,
    
    @SerialName("refresh_token")
    val refreshToken: String,
    
    @SerialName("created_at")
    val createdAt: Long,
    
    @SerialName("updated_at")
    val updatedAt: Long
) 