package com.hawkshaw.library.datalayer.models

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

/**
 * Timestamp representation for serialization purposes
 */
@Serializable(with = TimestampSerializer::class)
data class Timestamp(val milliseconds: Long = 0)

/**
 * Serializer for Timestamp class that converts between ISO RFC3339 format strings and Timestamp objects
 */
object TimestampSerializer : KSerializer<Timestamp> {
    private val rfc3339Format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.US).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }
    
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Timestamp", PrimitiveKind.STRING)
    
    override fun deserialize(decoder: Decoder): Timestamp {
        val dateString = decoder.decodeString()
        return Timestamp(rfc3339Format.parse(dateString)?.time ?: 0)
    }
    
    override fun serialize(encoder: Encoder, value: Timestamp) {
        val dateString = rfc3339Format.format(Date(value.milliseconds))
        encoder.encodeString(dateString)
    }
} 