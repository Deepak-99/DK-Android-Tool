package com.hawkshaw.library.logger

import android.util.Log
import com.hawkshaw.library.datalayer.room.logger.LogEntity
import io.sentry.Sentry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

/**
 * Application logger with different log levels and optional Sentry integration
 */
object Logger {
    private const val TAG = "Hawkshaw"
    private var isDebugEnabled = false
    private val logsFlow = MutableSharedFlow<LogEntity>(
        replay = 10,
        extraBufferCapacity = 1024
    )

    /**
     * Define LogLevel enum here, to be used consistently
     */
    enum class LogLevel {
        VERBOSE,
        DEBUG,
        INFO, // Added INFO level
        WARN,
        ERROR
    }

    fun setDebugEnabled(enabled: Boolean) {
        isDebugEnabled = enabled
    }

    // Now logToDatabase expects LogLevel enum
    private fun logToDatabase(level: LogLevel, tag: String, message: String) {
        val truncatedMessage = if (message.length > 1500) message.substring(0, 1500) else message
        val logEntity = LogEntity(
            // Store the enum's name (String) in the database
            level = level.name,
            tag = tag,
            message = truncatedMessage
        )
        CoroutineScope(Dispatchers.IO).launch {
            logsFlow.emit(logEntity)
        }
    }

    fun v(tag: String, message: String, b: Boolean, i: Int, nothing: Nothing?) {
        if (isDebugEnabled) {
            Log.v("$TAG:$tag", message)
        }
        logToDatabase(LogLevel.VERBOSE, tag, message)
    }

    fun d(tag: String, message: String, nothing: Boolean, i: Int, nothing1: Nothing?) {
        if (isDebugEnabled) {
            Log.d("$TAG:$tag", message)
        }
        logToDatabase(LogLevel.DEBUG, tag, message)
    }

    // Added the 'i' (info) logging function
    fun i(tag: String, message: String) {
        if (isDebugEnabled) {
            Log.i("$TAG:$tag", message)
        }
        logToDatabase(LogLevel.INFO, tag, message)
    }

    fun w(tag: String, message: String) {
        Log.w("$TAG:$tag", message)
        logToDatabase(LogLevel.WARN, tag, message)
    }

    fun e(
        tag: String,
        message: String,
        throwable: Throwable? = null,
        b: Boolean,
        i: Int,
        nothing: Nothing?
    ) {
        Log.e("$TAG:$tag", message, throwable)
        logToDatabase(LogLevel.ERROR, tag, message)
        throwable?.let { Sentry.captureException(it) }
    }
}