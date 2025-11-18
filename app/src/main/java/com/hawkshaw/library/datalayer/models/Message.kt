package com.hawkshaw.library.datalayer.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model representing a message (SMS, MMS, or RCS)
 * Contains message details, threading information, and timestamps
 */
@Serializable
data class Message(
    @SerialName("id")
    val id: String,
    
    @SerialName("thread_id")
    val threadId: String? = null,
    
    @SerialName("address")
    val address: String,
    
    @SerialName("contact_name")
    val contactName: String,
    
    @SerialName("body")
    val body: String,
    
    @SerialName("subject")
    val subject: String? = null,
    
    @SerialName("type")
    val type: Type,
    
    @SerialName("date")
    val date: Long,
    
    @SerialName("date_sent")
    val dateSent: Long? = null,
    
    @SerialName("creator")
    val creator: String? = null,
    
    @SerialName("read")
    val read: Boolean = false,
    
    @SerialName("attachments")
    val attachments: List<Attachment>? = null,
    
    @SerialName("rcs_features")
    val rcsFeatures: RcsFeatures? = null
) {
    /**
     * Message type enumeration
     */
    enum class Type {
        SMS,
        MMS,
        RCS
    }
    
    /**
     * Data class representing a message attachment
     */
    @Serializable
    data class Attachment(
        @SerialName("uri")
        val uri: String,
        
        @SerialName("mime_type")
        val mimeType: String,
        
        @SerialName("size")
        val size: Long
    )
    
    /**
     * Data class representing RCS-specific features
     */
    @Serializable
    data class RcsFeatures(
        @SerialName("is_composing")
        val isComposing: Boolean = false,
        
        @SerialName("delivery_status")
        val deliveryStatus: DeliveryStatus = DeliveryStatus.UNKNOWN,
        
        @SerialName("read_status")
        val readStatus: ReadStatus = ReadStatus.UNKNOWN,
        
        @SerialName("conversation_id")
        val conversationId: String? = null,
        
        @SerialName("contribution_id")
        val contributionId: String? = null
    ) {
        /**
         * RCS message delivery status
         */
        enum class DeliveryStatus {
            UNKNOWN,
            SENT,
            DELIVERED,
            DISPLAYED,
            FAILED
        }
        
        /**
         * RCS message read status
         */
        enum class ReadStatus {
            UNKNOWN,
            UNREAD,
            READ
        }
    }
} 