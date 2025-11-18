package com.hawkshaw.library.datalayer.room.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hawkshaw.library.datalayer.room.socialmedia.SocialMediaEntity
import com.hawkshaw.library.datalayer.models.Command
import com.hawkshaw.library.datalayer.models.PackageName
import android.util.Log // Added for Logcat

/**
 * Type converters for Room database
 */
object Converters {
    private const val TAG = "RoomConverters" // Added for logging
    private val gson = Gson()

    init {
        Log.d(TAG, "[DEBUG] Converters object initialized. Gson instance created.") // Added Logcat
    }

    @TypeConverter
    fun fromPackageName(value: PackageName): String {
        Log.d(TAG, "[DEBUG] fromPackageName: Input PackageName: $value, Output String: ${value.toString()}") // Added Logcat
        return value.toString()
    }

    @TypeConverter
    fun toPackageName(value: String): PackageName {
        Log.d(TAG, "[DEBUG] toPackageName: Input String: \"$value\"") // Added Logcat
        return try {
            val result = PackageName.valueOf(value)
            Log.d(TAG, "[DEBUG] toPackageName: Successfully converted to PackageName: $result") // Added Logcat
            result
        } catch (e: IllegalArgumentException) {
            Log.w(TAG, "[DEBUG] toPackageName: Failed to convert \"$value\". Defaulting to PackageName.Unknown.", e) // Added Logcat
            PackageName.Unknown
        }
    }

    @TypeConverter
    fun fromSocialMediaType(value: SocialMediaEntity.Type): String {
        Log.d(TAG, "[DEBUG] fromSocialMediaType: Input Type: $value, Output String: ${value.name}") // Added Logcat
        return value.name
    }

    @TypeConverter
    fun toSocialMediaType(value: String): SocialMediaEntity.Type {
        Log.d(TAG, "[DEBUG] toSocialMediaType: Input String: \"$value\"") // Added Logcat
        return try {
            val result = SocialMediaEntity.Type.valueOf(value)
            Log.d(TAG, "[DEBUG] toSocialMediaType: Successfully converted to SocialMediaEntity.Type: $result") // Added Logcat
            result
        } catch (e: IllegalArgumentException) {
            Log.w(TAG, "[DEBUG] toSocialMediaType: Failed to convert \"$value\". Defaulting to SocialMediaEntity.Type.Unknown.", e) // Added Logcat
            SocialMediaEntity.Type.Unknown
        }
    }

    @TypeConverter
    fun fromStringList(value: String?): List<String>? {
        Log.d(TAG, "[DEBUG] fromStringList: Input String: \"$value\"") // Added Logcat
        if (value == null) {
            Log.d(TAG, "[DEBUG] fromStringList: Input is null, returning null.") // Added Logcat
            return null
        }
        val listType = object : TypeToken<List<String>>() {}.type
        val result = gson.fromJson<List<String>>(value, listType)
        Log.d(TAG, "[DEBUG] fromStringList: Successfully converted to List<String>: $result") // Added Logcat
        return result
    }

    @TypeConverter
    fun toStringList(list: List<String>?): String? {
        Log.d(TAG, "[DEBUG] toStringList: Input List<String>: $list") // Added Logcat
        if (list == null) {
            Log.d(TAG, "[DEBUG] toStringList: Input list is null, returning null.") // Added Logcat
            return null
        }
        val result = gson.toJson(list)
        Log.d(TAG, "[DEBUG] toStringList: Successfully converted to String: \"$result\"") // Added Logcat
        return result
    }

    @TypeConverter
    fun fromFileRequestSource(value: Command.FileRequest.Source): String {
        Log.d(TAG, "[DEBUG] fromFileRequestSource: Input Source: $value, Output String: ${value.name}") // Added Logcat
        return value.name
    }

    @TypeConverter
    fun toFileRequestSource(value: String): Command.FileRequest.Source {
        Log.d(TAG, "[DEBUG] toFileRequestSource: Input String: \"$value\"") // Added Logcat
        return try {
            val result = Command.FileRequest.Source.valueOf(value)
            Log.d(TAG, "[DEBUG] toFileRequestSource: Successfully converted to Command.FileRequest.Source: $result") // Added Logcat
            result
        } catch (e: IllegalArgumentException) {
            Log.w(TAG, "[DEBUG] toFileRequestSource: Failed to convert \"$value\". Defaulting to Command.FileRequest.Source.Unknown.", e) // Added Logcat
            Command.FileRequest.Source.Unknown
        }
    }

    @TypeConverter
    fun fromFileRequestMedium(value: Command.FileRequest.Medium): String {
        Log.d(TAG, "[DEBUG] fromFileRequestMedium: Input Medium: $value, Output String: ${value.name}") // Added Logcat
        return value.name
    }

    @TypeConverter
    fun toFileRequestMedium(value: String): Command.FileRequest.Medium {
        Log.d(TAG, "[DEBUG] toFileRequestMedium: Input String: \"$value\"") // Added Logcat
        return try {
            val result = Command.FileRequest.Medium.valueOf(value)
            Log.d(TAG, "[DEBUG] toFileRequestMedium: Successfully converted to Command.FileRequest.Medium: $result") // Added Logcat
            result
        } catch (e: IllegalArgumentException) {
            Log.w(TAG, "[DEBUG] toFileRequestMedium: Failed to convert \"$value\". Defaulting to Command.FileRequest.Medium.Grpc.", e) // Added Logcat
            Command.FileRequest.Medium.Grpc
        }
    }
}
