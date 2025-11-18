package com.hawkshaw.library.datalayer.network.twirp

import com.hawkshaw.library.datalayer.models.Command
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import android.util.Log // Added for Logcat

/**
 * Serializer for Command.CommandType enum values
 */
object CommandTypeSerializer : KSerializer<Command.CommandType> {
    private const val TAG = "CommandTypeSerializer" // Added for logging
    private val className = Command.CommandType::class.qualifiedName ?: "com.hawkshaw.library.datalayer.models.Command.CommandType"
    
    // Maps for fast lookup in both directions
    private val lookup = Command.CommandType.entries.associateWith { it.name }
    private val revLookup = Command.CommandType.entries.associateBy { it.name }

    init {
        Log.d(TAG, "[DEBUG] Initializing CommandTypeSerializer. ClassName for descriptor: $className") // Added Logcat
        Log.d(TAG, "[DEBUG] Forward lookup map: $lookup") // Added Logcat
        Log.d(TAG, "[DEBUG] Reverse lookup map: $revLookup") // Added Logcat
    }

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(className, PrimitiveKind.STRING)
    
    override fun serialize(encoder: Encoder, value: Command.CommandType) {
        Log.d(TAG, "[DEBUG] serialize called. Value to serialize: $value") // Added Logcat
        val stringValue = lookup[value] ?: value.name
        Log.d(TAG, "[DEBUG] serialize: Serializing as string: \"$stringValue\"") // Added Logcat
        encoder.encodeString(stringValue)
    }
    
    override fun deserialize(decoder: Decoder): Command.CommandType {
        val decodedString = decoder.decodeString()
        Log.d(TAG, "[DEBUG] deserialize called. String value from decoder: \"$decodedString\"") // Added Logcat
        val commandType = revLookup[decodedString]
        return if (commandType != null) {
            Log.d(TAG, "[DEBUG] deserialize: Found in revLookup. Deserialized to: $commandType") // Added Logcat
            commandType
        } else {
            Log.w(TAG, "[DEBUG] deserialize: \"$decodedString\" not found in revLookup. Defaulting to Command.CommandType.Unknown.") // Added Logcat
            Command.CommandType.Unknown
        }
    }
}
