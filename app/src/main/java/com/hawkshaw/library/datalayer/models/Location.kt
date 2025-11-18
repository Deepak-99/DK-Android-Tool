package com.hawkshaw.library.datalayer.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model representing a geographical location
 * Includes coordinates, accuracy, speed and other metadata
 */
@Serializable
data class Location(
    @SerialName("altitude")
    val altitude: Double,
    
    @SerialName("latitude")
    val latitude: Double,
    
    @SerialName("longitude")
    val longitude: Double,
    
    @SerialName("accuracy")
    val accuracy: Float,
    
    @SerialName("vertical_accuracy_meters")
    val verticalAccuracyMeters: Float,
    
    @SerialName("speed")
    val speed: Float,
    
    @SerialName("bearing")
    val bearing: Float,
    
    @SerialName("provider")
    val provider: String,
    
    @SerialName("time")
    val time: Long,
    
    @SerialName("is_mock")
    val isMock: Boolean
) 