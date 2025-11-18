package com.hawkshaw.library.datalayer.network

import com.hawkshaw.library.datalayer.network.service.*
import android.util.Log // Added for Logcat

/**
 * Factory for creating service instances
 */
object ServiceFactory {
    private const val TAG = "ServiceFactory" // Added for logging

    /**
     * Create an instance of AccessibilityService
     */
    fun createAccessibilityService(): AccessibilityService {
        Log.d(TAG, "[DEBUG] createAccessibilityService called. Creating AccessibilityServiceImpl.") // Added Logcat
        return AccessibilityServiceImpl()
    }

    /**
     * Create an instance of AppConfigService
     */
    fun createAppConfigService(): AppConfigService {
        Log.d(TAG, "[DEBUG] createAppConfigService called. Creating AppConfigServiceImpl.") // Added Logcat
        return AppConfigServiceImpl()
    }

    /**
     * Create an instance of AppService
     */
    fun createAppService(): AppService {
        Log.d(TAG, "[DEBUG] createAppService called. Creating AppServiceImpl.") // Added Logcat
        return AppServiceImpl()
    }

    /**
     * Create an instance of AuthService
     */
    fun createAuthService(): AuthService {
        Log.d(TAG, "[DEBUG] createAuthService called. Creating AuthServiceImpl.") // Added Logcat
        return AuthServiceImpl()
    }

    /**
     * Create an instance of DataService
     */
    fun createDataService(): DataService {
        Log.d(TAG, "[DEBUG] createDataService called. Creating DataServiceImpl.") // Added Logcat
        return DataServiceImpl()
    }

    /**
     * Create an instance of FileService
     */
    fun createFileService(): FileService {
        Log.d(TAG, "[DEBUG] createFileService called. Creating FileServiceImpl.") // Added Logcat
        return FileServiceImpl()
    }

    /**
     * Create an instance of LocationService
     */
    fun createLocationService(): LocationService {
        Log.d(TAG, "[DEBUG] createLocationService called. Creating LocationServiceImpl.") // Added Logcat
        return LocationServiceImpl()
    }

    /**
     * Create an instance of LogsService
     */
    fun createLogsService(): LogsService {
        Log.d(TAG, "[DEBUG] createLogsService called. Creating LogsServiceImpl.") // Added Logcat
        return LogsServiceImpl()
    }

    /**
     * Create an instance of MediaService
     */
    fun createMediaService(): MediaService {
        Log.d(TAG, "[DEBUG] createMediaService called. Creating MediaServiceImpl.") // Added Logcat
        return MediaServiceImpl()
    }

    /**
     * Create an instance of MiscService
     */
    fun createMiscService(): MiscService {
        Log.d(TAG, "[DEBUG] createMiscService called. Creating MiscServiceImpl.") // Added Logcat
        return MiscServiceImpl()
    }

    /**
     * Create an instance of TelephonyService
     */
    fun createTelephonyService(): TelephonyService {
        Log.d(TAG, "[DEBUG] createTelephonyService called. Creating TelephonyServiceImpl.") // Added Logcat
        return TelephonyServiceImpl()
    }
}
