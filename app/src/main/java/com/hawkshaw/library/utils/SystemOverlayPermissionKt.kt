package com.hawkshaw.library.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.result.ActivityResultLauncher
import androidx.core.net.toUri

/**
 * Request system overlay permission
 */
fun Context.requestSystemOverlay(launcher: ActivityResultLauncher<Intent>) {
    // Create the appropriate intent based on device type
    val intent = if (!Settings.canDrawOverlays(this)) {
        // Special handling for MIUI devices
        if (System.getProperty("ro.miui.ui.version.name") != null) {
            Intent("miui.intent.action.APP_PERM_EDITOR").apply {
                setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity")
                putExtra("extra_pkgname", packageName)
            }
        } else {
            // Standard Android overlay permission
            Intent(
                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                "package:$packageName".toUri()
            )
        }
    } else {
        // If already has permission, create a dummy intent that will just return immediately
        Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
    }
    
    launcher.launch(intent)
}

/**
 * Check if the app has system overlay permission
 */
fun Context.hasSystemOverlayPermission(): Boolean {
    return Settings.canDrawOverlays(this)
} 