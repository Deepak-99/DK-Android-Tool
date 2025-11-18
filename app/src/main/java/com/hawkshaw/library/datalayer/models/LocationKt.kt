package com.hawkshaw.library.datalayer.models

import android.location.Location
import android.os.Build
import androidx.annotation.RequiresApi

/**
 * Extension function to convert an Android Location to our custom Location model
 * 
 * @return A new Location instance with values from the Android Location
 */
@RequiresApi(Build.VERSION_CODES.O)
fun android.location.Location.toLocation(): com.hawkshaw.library.datalayer.models.Location {
    return com.hawkshaw.library.datalayer.models.Location(
        altitude = this.altitude,
        latitude = this.latitude,
        longitude = this.longitude,
        accuracy = this.accuracy,
        verticalAccuracyMeters = this.verticalAccuracyMeters,
        speed = this.speed,
        bearing = this.bearing,
        provider = this.provider ?: "unknown",
        time = this.time,
        isMock = if (Build.VERSION.SDK_INT >= 31) this.isMock() else this.isFromMockProvider()
    )
} 