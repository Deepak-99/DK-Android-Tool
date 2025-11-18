package com.hawkshaw.library.datalayer.room.converters

import androidx.room.TypeConverter
import com.hawkshaw.library.datalayer.models.Command
import android.util.Log // Added for Logcat

/**
 * Room type converter for FileRequest.Source enum
 */
class FileRequestSourceConverter {
    private val TAG = "FileRequestSourceConv" // Added for logging

    init {
        Log.d(TAG, "[DEBUG] FileRequestSourceConverter initialized.") // Added Logcat
    }

    @TypeConverter
    fun fromSource(source: Command.FileRequest.Source?): String? {
        Log.d(TAG, "[DEBUG] fromSource called. Input source: $source") // Added Logcat
        val result = source?.name
        Log.d(TAG, "[DEBUG] fromSource: Output string: $result") // Added Logcat
        return result
    }

    @TypeConverter
    fun toSource(value: String?): Command.FileRequest.Source {
        Log.d(TAG, "[DEBUG] toSource called. Input string value: \"$value\"") // Added Logcat
        val result = value?.let {
            try {
                val enumVal = enumValueOf<Command.FileRequest.Source>(it)
                Log.d(TAG, "[DEBUG] toSource: Successfully converted \"$it\" to enum: $enumVal") // Added Logcat
                enumVal
            } catch (e: IllegalArgumentException) {
                Log.w(TAG, "[DEBUG] toSource: Failed to convert string \"$it\" to enum. Defaulting to Unknown.", e) // Added Logcat
                Command.FileRequest.Source.Unknown
            }
        } ?: Command.FileRequest.Source.Unknown
        if (value == null) {
            Log.d(TAG, "[DEBUG] toSource: Input string was null, defaulted to $result") // Added Logcat
        }
        return result
    }
}

/**
 * Room type converter for FileRequest.Medium enum
 */
class FileRequestMediumConverter {
    // No changes to this class as per request
    @TypeConverter
    fun fromMedium(medium: Command.FileRequest.Medium?): String? {
        return medium?.name
    }

    @TypeConverter
    fun toMedium(value: String?): Command.FileRequest.Medium {
        return value?.let { enumValueOf<Command.FileRequest.Medium>(it) } ?: Command.FileRequest.Medium.Grpc
    }
}
