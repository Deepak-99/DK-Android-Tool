package com.hawkshaw.library.datalayer.models

import com.hawkshaw.library.logger.Logger
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Request model for sending application logs to the server
 */
@Serializable
data class AppLogsRequest(
    @SerialName("logs")
    val logs: List<Log>
) {
    /**
     * Log entry data structure
     */
    @Serializable
    data class Log(
        @SerialName("log_level")
        val logLevel: Logger.LogLevel,
        
        @SerialName("tag")
        val tag: String,
        
        @SerialName("message")
        val message: String,
        
        @SerialName("timestamp")
        val timestamp: Long,
        
        @SerialName("id")
        val id: Int
    )
} 