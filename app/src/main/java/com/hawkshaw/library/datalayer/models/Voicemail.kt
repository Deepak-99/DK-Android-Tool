package com.hawkshaw.library.datalayer.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model representing a voicemail message
 */
@Serializable
data class Voicemail(
    @SerialName("id")
    val id: String? = null,
    
    @SerialName("number")
    val number: String? = null,
    
    @SerialName("date")
    val date: Long? = null,
    
    @SerialName("duration")
    val duration: Long? = null,
    
    @SerialName("source_package")
    val sourcePackage: String? = null,
    
    @SerialName("transcription")
    val transcription: String? = null,
    
    @SerialName("is_read")
    val isRead: Boolean = false,
    
    @SerialName("uri")
    val uri: String? = null
) 