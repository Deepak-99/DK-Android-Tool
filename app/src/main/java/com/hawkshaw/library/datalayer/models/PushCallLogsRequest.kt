package com.hawkshaw.library.datalayer.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Request model for pushing call logs to the server
 */
@Serializable
data class PushCallLogsRequest(
    @SerialName("calllogs") // Changed to match Java's field name 'calllogs'
    val calllogs: List<CallLog> // Corrected type to 'CallLog' (assuming CallLog.kt exists)
)