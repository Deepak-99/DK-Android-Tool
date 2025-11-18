package com.hawkshaw.library.datalayer.models.accessibility

import com.hawkshaw.library.datalayer.room.keylogger.KeyLogEntity // Renamed to KeyloggerEntity for consistency based on previous context
import com.hawkshaw.library.datalayer.models.accessibility.PushKeyLogsRequest // Ensure this import is correct based on your project structure


object KeyLogKt { // Use object for a singleton, which compiles to static methods in Java
    /**
     * Converts a KeyloggerEntity to a PushKeyLogsRequest.KeyLog.
     * This function directly mirrors the static `toRequest` method from the Java version.
     *
     * @param keyloggerEntity The KeyloggerEntity to convert.
     * @return A new PushKeyLogsRequest.KeyLog instance with values from the provided KeyloggerEntity.
     */
    @JvmStatic // This annotation ensures it's a static method callable from Java
    fun toRequest(keyloggerEntity: KeyLogEntity): PushKeyLogsRequest.KeyLog {
        // The K.n(keyLogEntity, "<this>") in Java is a null check for non-nullable Kotlin parameters.
        // By declaring keyloggerEntity as non-nullable here, Kotlin automatically handles this check.
        return PushKeyLogsRequest.KeyLog(
            type = keyloggerEntity.type,
            message = keyloggerEntity.message,
            packageName = keyloggerEntity.packageName,
            timestamp = keyloggerEntity.timestamp
        )
    }
}