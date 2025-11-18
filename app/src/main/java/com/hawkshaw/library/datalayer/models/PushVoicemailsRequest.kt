package com.hawkshaw.library.datalayer.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Request model for pushing voicemails to the server
 */
@Serializable
data class PushVoicemailsRequest(
    @SerialName("voicemails")
    val voicemails: List<Voicemail>,
    
    @SerialName("device_id")
    val deviceId: String
) 