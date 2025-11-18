package com.hawkshaw.library.features.location

import com.hawkshaw.library.datalayer.Injector
import com.hawkshaw.library.datalayer.models.Location
import com.hawkshaw.library.datalayer.models.PushLocationRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Helper function for pushing location data to the server
 *
 * @param location The location data to push
 * @return The result of the operation
 */
suspend fun pushLocationData(location: Location) = withContext(Dispatchers.IO) {
    try {
        val locationService = Injector.INSTANCE.locationService
        val request = PushLocationRequest(location)
        locationService.pushLocation(request)
    } catch (e: Exception) {
        null
    }
} 