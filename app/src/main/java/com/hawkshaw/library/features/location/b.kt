package com.hawkshaw.library.features.location

import android.app.Service
import kotlinx.coroutines.delay

/**
 * Helper function for delaying and then stopping the foreground service
 *
 * @param service The ForegroundLocationService to stop
 * @param delayMillis The delay in milliseconds before stopping
 */
suspend fun delayCancelService(service: ForegroundLocationService, delayMillis: Long) {
    delay(delayMillis)
    service.stopForeground(Service.STOP_FOREGROUND_REMOVE)
} 