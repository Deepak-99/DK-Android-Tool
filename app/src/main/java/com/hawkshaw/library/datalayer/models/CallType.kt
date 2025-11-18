package com.hawkshaw.library.datalayer.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Enumeration of call types/states
 */
@Serializable
enum class CallType {
    @SerialName("incoming")
    INCOMING,
    
    @SerialName("outgoing")
    OUTGOING,
    
    @SerialName("missed")
    MISSED,
    
    @SerialName("rejected")
    REJECTED,
    
    @SerialName("blocked")
    BLOCKED,
    
    @SerialName("voicemail")
    VOICEMAIL,
    
    @SerialName("unknown")
    UNKNOWN
} 