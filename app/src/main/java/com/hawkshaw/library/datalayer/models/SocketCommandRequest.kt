package com.hawkshaw.library.datalayer.models

import com.hawkshaw.library.datalayer.network.twirp.SocketCommandTypeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Request object for socket-based commands
 */
@Serializable
data class SocketCommandRequest(
    @Serializable(with = SocketCommandTypeSerializer::class)
    @SerialName("type")
    val type: Type,
    
    @SerialName("sent_time")
    val sentTime: Long = System.currentTimeMillis(),
    
    @SerialName("root_command_request")
    val rootCommandRequest: RootCommandRequest? = null,
    
    @SerialName("shell_request")
    val shellRequest: ShellRequest? = null
) {
    /**
     * Types of socket commands
     */
    @Serializable
    enum class Type {
        @SerialName("Unknown")
        Unknown,
        
        @SerialName("Message")
        Message,
        
        @SerialName("RootCommand")
        RootCommand,
        
        @SerialName("Shell")
        Shell
    }
    
    /**
     * Request containing a command to execute
     */
    @Serializable
    data class RootCommandRequest(
        @SerialName("command")
        val command: Command
    )
    
    /**
     * Request containing a shell command to execute
     */
    @Serializable
    data class ShellRequest(
        @SerialName("command")
        val command: String
    )
} 