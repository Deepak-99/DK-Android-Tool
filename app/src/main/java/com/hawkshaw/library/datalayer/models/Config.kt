package com.hawkshaw.library.datalayer.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

/**
 * Application configuration model
 */
@Serializable
data class Config(
    @SerialName("email")
    val email: String,
    
    @SerialName("hide_app_icon")
    val hideAppIcon: Boolean,
    
    @SerialName("firebase")
    val firebase: Firebase,
    
    @SerialName("default_remote_config")
    val defaultRemoteConfig: Map<String, JsonElement>
) {
    /**
     * Firebase configuration information
     */
    @Serializable
    data class Firebase(
        @SerialName("web_client_id")
        val webClientId: String,
        
        @SerialName("gcm_sender_id")
        val gcmSenderId: String,
        
        @SerialName("project_id")
        val projectId: String,
        
        @SerialName("api_key")
        val apiKey: String,
        
        @SerialName("app_id")
        val appId: String
    )
} 