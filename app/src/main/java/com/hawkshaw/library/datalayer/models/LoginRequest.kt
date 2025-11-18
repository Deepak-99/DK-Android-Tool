package com.hawkshaw.library.datalayer.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model representing a login request
 * Contains user email and device information
 */
@Serializable
data class LoginRequest(
    @SerialName("email")
    val email: String,
    
    @SerialName("device_info")
    val deviceInfo: String
) 