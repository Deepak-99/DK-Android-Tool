package com.hawkshaw.library.logger

import com.hawkshaw.library.App
import com.hawkshaw.library.datalayer.Injector
import com.hawkshaw.library.datalayer.models.AppLogsRequest
import com.hawkshaw.library.datalayer.network.twirp.ApiResponse
import com.hawkshaw.library.datalayer.room.AppDatabase
import com.hawkshaw.library.datalayer.room.logger.LogEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

/**
 * Utility functions for pushing logs to the server
 */
object PushLogsKt {
    private val db: AppDatabase by lazy { AppDatabase.getInstance(App.getContext()) }
    
    /**
     * Push application logs to the server
     * 
     * @return Unit when complete
     */
    suspend fun pushAppLogs() = withContext(Dispatchers.IO) {
        try {
            // Get the logger DAO
            val loggerDao = db.logDao()
            
            // Get unpushed logs and convert Flow to List
            val unpushedLogs = loggerDao.getUnpushedLogs().first()
            
            // Process logs in chunks to avoid large payloads
            val chunks = unpushedLogs.toList().chunked(100)
            
            for (chunk in chunks) {
                // Convert logs to the request format
                val logs = chunk.map { logEntity: LogEntity -> 
                    toLog(logEntity)
                }
                
                // Create the request object
                val request = AppLogsRequest(logs)
                
                // Get the logs service and push the logs
                val logsService = Injector.INSTANCE.logsService
                val response = logsService.pushAppLogs(request)
                
                when (response) {
                    is ApiResponse.Success -> {
                        // Mark logs as pushed in a coroutine scope
                        chunk.forEach { logEntity: LogEntity ->
                            loggerDao.markAsPushed(logEntity.id)
                        }
                        Logger.d(
                            "PushLogsKt",
                            "Successfully pushed ${chunk.size} logs",
                            false,
                            4,
                            null
                        )
                    }
                    is ApiResponse.Error -> {
                        Logger.e(
                            "PushLogsKt",
                            "Failed to push logs: ${response.message}",
                            b = false,
                            i = 12,
                            nothing = null
                        )
                    }
                }
            }
        } catch (e: Exception) {
            Logger.e("PushLogsKt", "Error pushing logs", e, false, 12, null)
        }
    }

    /**
     * Convert a LogEntity to an AppLogsRequest.Log
     */
    private fun toLog(logEntity: LogEntity): AppLogsRequest.Log {
        return AppLogsRequest.Log(
            logLevel = Logger.LogLevel.valueOf(logEntity.level),
            tag = logEntity.tag,
            message = logEntity.message,
            timestamp = logEntity.timestamp,
            id = logEntity.id.toInt()
        )
    }
} 