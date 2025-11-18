package com.hawkshaw.library.ktextensions

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import com.hawkshaw.library.App
import android.os.PowerManager // Added for Battery Optimization check

object ManifestPermissionsKt {
    private const val TAG = "ManifestPermissionsKt"

    // Permissions that will be requested through the standard runtime permission flow
    private val generalRuntimePermissions: Array<String> = run {
        Log.d(TAG, "[DEBUG] Initializing generalRuntimePermissions...") // Entry log for the run block
        // Existing Log.d: Log.d(TAG, "Initializing generalRuntimePermissions...") // This is fine, keeping the [DEBUG] version for consistency
        val permissions = mutableListOf(
            "android.permission.ACCESS_COARSE_LOCATION",
            "android.permission.ACCESS_FINE_LOCATION",
            "android.permission.READ_CONTACTS",
            "android.permission.READ_CALL_LOG",
            "android.permission.WRITE_CALL_LOG",
            "android.permission.WRITE_CONTACTS",
            "android.permission.READ_SMS",
            "android.permission.SEND_SMS",
            "android.permission.CALL_PHONE",
            "android.permission.READ_PHONE_STATE",
            // "android.permission.PROCESS_OUTGOING_CALLS", // Protection level 'signature' or deprecated for third-party apps.
            "android.permission.CAMERA",
            "android.permission.RECORD_AUDIO"
            // INTERNET, ACCESS_NETWORK_STATE, ACCESS_WIFI_STATE, RECEIVE_BOOT_COMPLETED, WAKE_LOCK, VIBRATE are normal permissions.
            // CHANGE_WIFI_STATE, CHANGE_NETWORK_STATE might require consent or specific conditions on newer APIs but are often handled as normal or within app ops.
            // REQUEST_DELETE_PACKAGES is handled by Intent by system, not direct runtime request.
        )
        Log.d(TAG, "[DEBUG] Initial base permissions list size: ${permissions.size}")

        // Add ANSWER_PHONE_CALLS for API 26+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            permissions.add("android.permission.ANSWER_PHONE_CALLS")
            Log.d(TAG, "[DEBUG] Added ANSWER_PHONE_CALLS for API >= ${Build.VERSION_CODES.O} (Current: ${Build.VERSION.SDK_INT}).")
            // Existing Log.d: Log.d(TAG, "Added ANSWER_PHONE_CALLS for API >= 26.")
        }

        // Add storage permissions based on SDK version
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) { // API 33+
            permissions.add("android.permission.READ_MEDIA_IMAGES")
            permissions.add("android.permission.READ_MEDIA_VIDEO")
            permissions.add("android.permission.READ_MEDIA_AUDIO")
            // POST_NOTIFICATIONS is a runtime permission for API 33+
            permissions.add("android.permission.POST_NOTIFICATIONS")
            Log.d(TAG, "[DEBUG] Added READ_MEDIA_IMAGES, READ_MEDIA_VIDEO, READ_MEDIA_AUDIO, POST_NOTIFICATIONS for API >= ${Build.VERSION_CODES.TIRAMISU} (Current: ${Build.VERSION.SDK_INT}).")
            // Existing Log.d: Log.d(TAG, "Added READ_MEDIA_IMAGES, READ_MEDIA_VIDEO, READ_MEDIA_AUDIO, POST_NOTIFICATIONS for API >= 33.")
        } else { // Below API 33 (covers API < 29, and 29-32)
            permissions.add("android.permission.READ_EXTERNAL_STORAGE")
            Log.d(TAG, "[DEBUG] Added READ_EXTERNAL_STORAGE for API < ${Build.VERSION_CODES.TIRAMISU} (Current: ${Build.VERSION.SDK_INT}).")
            // WRITE_EXTERNAL_STORAGE: Be mindful of Scoped Storage on API 29+.
            // If targeting API 30+, this permission has limited effect for writes outside app-specific directories.
            // For API < 29, it provides broader write access.
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) { // Only add legacy write for pre-Q
                 permissions.add("android.permission.WRITE_EXTERNAL_STORAGE")
                 Log.d(TAG, "[DEBUG] Added WRITE_EXTERNAL_STORAGE for API < ${Build.VERSION_CODES.Q} (Current: ${Build.VERSION.SDK_INT}).")
                 // Existing Log.d: Log.d(TAG, "Added READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE for API < 29.")
            } else {
                 Log.d(TAG, "[DEBUG] For API ${Build.VERSION.SDK_INT} (>= ${Build.VERSION_CODES.Q} and < ${Build.VERSION_CODES.TIRAMISU}), only READ_EXTERNAL_STORAGE added. WRITE_EXTERNAL_STORAGE behavior is limited by Scoped Storage.")
                 // Existing Log.d: Log.d(TAG, "Added READ_EXTERNAL_STORAGE for API 29-32. WRITE_EXTERNAL_STORAGE behavior is limited by Scoped Storage.")
            }
        }

        // Add FOREGROUND_SERVICE for API 28 (P) and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            permissions.add("android.permission.FOREGROUND_SERVICE")
            Log.d(TAG, "[DEBUG] Added FOREGROUND_SERVICE for API >= ${Build.VERSION_CODES.P} (Current: ${Build.VERSION.SDK_INT}).")
            // More specific foreground service types for API 34+ if needed
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) { // API 34+
                 Log.d(TAG, "[DEBUG] Current API is ${Build.VERSION.SDK_INT} (>= UPSIDE_DOWN_CAKE). Consider specific foreground service types if needed.")
                 // Example: permissions.add("android.permission.FOREGROUND_SERVICE_DATA_SYNC")
            }
            Log.d(TAG, "Added FOREGROUND_SERVICE for API >= P.")
        }
        
        val finalPermissions = permissions.toTypedArray()
        Log.d(TAG, "[DEBUG] Final generalRuntimePermissions initialized with ${finalPermissions.size} permissions: ${finalPermissions.joinToString()}.")
        // Existing Log.d: Log.d(TAG, "Final generalRuntimePermissions initialized with ${finalPermissions.size} permissions: ${finalPermissions.joinToString()}")
        finalPermissions
    }

    // Constants for special permissions (handled via Intent)
    const val SYSTEM_ALERT_WINDOW = "android.permission.SYSTEM_ALERT_WINDOW"
    const val REQUEST_INSTALL_PACKAGES = "android.permission.REQUEST_INSTALL_PACKAGES"
    const val REQUEST_IGNORE_BATTERY_OPTIMIZATIONS = "android.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS"
    // const val MANAGE_EXTERNAL_STORAGE = "android.permission.MANAGE_EXTERNAL_STORAGE" // If ever needed

    @JvmStatic
    fun getGeneralPermissionList(): Array<String> {
        Log.d(TAG, "[DEBUG] getGeneralPermissionList() called.")
        // Existing Log.d: Log.d(TAG, "getGeneralPermissionList() called. Returning array of ${generalRuntimePermissions.size} permissions.")
        Log.d(TAG, "[DEBUG] getGeneralPermissionList() returning array of ${generalRuntimePermissions.size} permissions.")
        return generalRuntimePermissions
    }

    @JvmStatic
    fun hasPermission(permission: String): Boolean {
        Log.d(TAG, "[DEBUG] hasPermission(permission: \"$permission\") called. Using App.getContext().")
        val result = hasPermission(App.getContext(), listOf(permission))
        Log.d(TAG, "[DEBUG] hasPermission(permission: \"$permission\") result: $result.")
        return result
    }

    @JvmStatic
    fun hasPermission(context: Context, permission: String): Boolean {
        Log.d(TAG, "[DEBUG] hasPermission(context: $context, permission: \"$permission\") called.")
        val result = hasPermission(context, listOf(permission))
        Log.d(TAG, "[DEBUG] hasPermission(context: $context, permission: \"$permission\") result: $result.")
        return result
    }

    @JvmStatic
    fun hasPermission(permissions: List<String>): Boolean {
        Log.d(TAG, "[DEBUG] hasPermission(permissions: [${permissions.joinToString()}]) called. Using App.getContext().")
        val result = hasPermission(App.getContext(), permissions)
        Log.d(TAG, "[DEBUG] hasPermission(permissions: [${permissions.joinToString()}]) result: $result.")
        return result
    }

    @JvmStatic
    fun hasPermission(vararg permissions: String): Boolean {
        Log.d(TAG, "[DEBUG] hasPermission(vararg permissions: [${permissions.joinToString()}]) called. Using App.getContext().")
        val result = hasPermission(App.getContext(), permissions.toList())
        Log.d(TAG, "[DEBUG] hasPermission(vararg permissions: [${permissions.joinToString()}]) result: $result.")
        return result
    }

    @JvmStatic
    fun hasPermission(context: Context, permissions: List<String>): Boolean {
        Log.d(TAG, "[DEBUG] hasPermission(context: $context, permissions: [${permissions.joinToString()}]) entry.")
        for (permission in permissions) {
            Log.d(TAG, "[DEBUG] Checking permission: \"$permission\"")
            val isGranted = when (permission) {
                SYSTEM_ALERT_WINDOW -> {
                    Log.d(TAG, "[DEBUG] Special check for SYSTEM_ALERT_WINDOW. SDK: ${Build.VERSION.SDK_INT}")
                    val canDraw = android.provider.Settings.canDrawOverlays(context)
                    Log.d(TAG, "[DEBUG] SYSTEM_ALERT_WINDOW canDrawOverlays: $canDraw")
                    canDraw
                }
                REQUEST_INSTALL_PACKAGES -> {
                    Log.d(TAG, "[DEBUG] Special check for REQUEST_INSTALL_PACKAGES. SDK: ${Build.VERSION.SDK_INT}")
                    val canInstall = Build.VERSION.SDK_INT < Build.VERSION_CODES.O || context.packageManager.canRequestPackageInstalls()
                    Log.d(TAG, "[DEBUG] REQUEST_INSTALL_PACKAGES canRequestPackageInstalls: $canInstall")
                    canInstall
                }
                REQUEST_IGNORE_BATTERY_OPTIMIZATIONS -> {
                    Log.d(TAG, "[DEBUG] Special check for REQUEST_IGNORE_BATTERY_OPTIMIZATIONS. SDK: ${Build.VERSION.SDK_INT}")
                    val powerManager = context.getSystemService(Context.POWER_SERVICE) as? PowerManager
                    val isIgnoring = powerManager?.isIgnoringBatteryOptimizations(context.packageName) ?: false
                    Log.d(TAG, "[DEBUG] REQUEST_IGNORE_BATTERY_OPTIMIZATIONS (API >= M): PowerManager: $powerManager, isIgnoring: $isIgnoring for ${context.packageName}")
                    isIgnoring
                }
                else -> {
                    Log.d(TAG, "[DEBUG] Standard checkSelfPermission for \"$permission\".")
                    val selfPerm = context.checkSelfPermission(permission)
                    val granted = selfPerm == PackageManager.PERMISSION_GRANTED
                    Log.d(TAG, "[DEBUG] checkSelfPermission for \"$permission\" result: $selfPerm (GRANTED is ${PackageManager.PERMISSION_GRANTED}). Granted: $granted")
                    granted
                }
            }

            // Existing Log.v: Log.v(TAG, "Checking permission: $permission -> Granted: $isGranted")
            Log.d(TAG, "[DEBUG] Permission \"$permission\" check complete. Granted: $isGranted.")
            if (!isGranted) {
                Log.d(TAG, "[DEBUG] Permission \"$permission\" is NOT granted. Returning false from hasPermission overall.")
                // Existing Log.d: Log.d(TAG, "Permission '$permission' is NOT granted.")
                return false
            }
        }
        Log.d(TAG, "[DEBUG] All ${permissions.size} permissions checked were granted. Returning true from hasPermission overall.")
        // Existing Log.d: Log.d(TAG, "All ${permissions.size} permissions checked were granted.")
        return true
    }
}

