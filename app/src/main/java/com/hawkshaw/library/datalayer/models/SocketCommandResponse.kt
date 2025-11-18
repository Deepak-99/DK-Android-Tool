package com.hawkshaw.library.datalayer.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response object for socket-based commands
 */
@Serializable
data class SocketCommandResponse(
    @SerialName("type")
    val type: Type,
    
    @SerialName("sent_time")
    val sentTime: Long = System.currentTimeMillis(),
    
    @SerialName("shell_response")
    val shellResponse: ShellResponse? = null
) {
    /**
     * Types of socket command responses
     */
    @Serializable
    enum class Type {
        @SerialName("Unknown")
        Unknown,
        
        @SerialName("Shell")
        Shell
    }
    
    /**
     * Response from a shell command execution
     */
    @Serializable
    data class ShellResponse(
        @SerialName("response")
        val response: String
    )
} 