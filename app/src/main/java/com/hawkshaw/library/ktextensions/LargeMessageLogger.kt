package com.hawkshaw.library.ktextensions

import com.hawkshaw.library.logger.Logger

/**
 * Utility for logging large messages by splitting them into smaller chunks
 */
object LargeMessageLogger {
    
    /**
     * Maximum message chunk size
     */
    private const val CHUNK_SIZE = 3950
    
    /**
     * Log a message to debug level, splitting it into chunks if necessary
     */
    fun d(tag: String, message: String) {
        log(message) { chunk ->
            Logger.d(tag, chunk, false, 4, null)
        }
    }
    
    /**
     * Log a message to verbose level, splitting it into chunks if necessary
     */
    fun v(tag: String, message: String) {
        log(message) { chunk ->
            Logger.v(tag, chunk, false, 4, null)
        }
    }
    
    /**
     * Split a message into chunks and log each chunk using the provided logger function
     */
    private fun log(message: String, logger: (String) -> Unit) {
        val chunks = message.length / CHUNK_SIZE
        
        if (chunks >= 0) {
            for (i in 0..chunks) {
                val start = i * CHUNK_SIZE
                val end = minOf((i + 1) * CHUNK_SIZE, message.length)
                
                val substring = message.substring(start, end)
                logger(substring)
            }
        }
    }
} 