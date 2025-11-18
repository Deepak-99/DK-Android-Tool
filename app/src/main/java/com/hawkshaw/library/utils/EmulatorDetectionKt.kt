package com.hawkshaw.library.utils

import android.os.Build
import android.os.Environment
import android.opengl.GLES20
import java.io.File

/**
 * Utility functions for emulator detection
 */
object EmulatorDetectionKt {
    
    /**
     * Modern approach to check if running on an emulator
     */
    fun isEmulator(): Boolean {
        var detectionScore = 0
        
        // Check PRODUCT field
        val product = Build.PRODUCT
        if (product.contains("sdk") || 
            product.contains("Andy") ||
            product.contains("ttVM_Hdragon") ||
            product.contains("google_sdk") ||
            product.contains("Droid4X") ||
            product.contains("nox") ||
            product.contains("sdk_x86") ||
            product.contains("sdk_google") ||
            product.contains("vbox86p")) {
            detectionScore++
        }
        
        // Check MANUFACTURER field
        val manufacturer = Build.MANUFACTURER
        if (manufacturer == "unknown" ||
            manufacturer == "Genymotion" ||
            manufacturer.contains("Andy") ||
            manufacturer.contains("MIT") ||
            manufacturer.contains("nox") ||
            manufacturer.contains("TiantianVM")) {
            detectionScore++
        }
        
        // Check BRAND field
        val brand = Build.BRAND
        if (brand == "generic" ||
            brand == "generic_x86" ||
            brand == "TTVM" ||
            brand.contains("Andy")) {
            detectionScore++
        }
        
        // Check DEVICE field
        val device = Build.DEVICE
        if (device.contains("generic") ||
            device.contains("generic_x86") ||
            device.contains("Andy") ||
            device.contains("ttVM_Hdragon") ||
            device.contains("Droid4X") ||
            device.contains("nox") ||
            device.contains("generic_x86_64") ||
            device.contains("vbox86p")) {
            detectionScore++
        }
        
        // Check MODEL field
        val model = Build.MODEL
        if (model == "sdk" ||
            model == "google_sdk" ||
            model.contains("Droid4X") ||
            model.contains("TiantianVM") ||
            model.contains("Andy") ||
            model == "Android SDK built for x86_64" ||
            model == "Android SDK built for x86") {
            detectionScore++
        }
        
        // Check HARDWARE field
        val hardware = Build.HARDWARE
        if (hardware == "goldfish" ||
            hardware == "vbox86" ||
            hardware.contains("nox") ||
            hardware.contains("ttVM_x86")) {
            detectionScore++
        }
        
        // Check FINGERPRINT field
        val fingerprint = Build.FINGERPRINT
        if (fingerprint.contains("generic/sdk/generic") ||
            fingerprint.contains("generic_x86/sdk_x86/generic_x86") ||
            fingerprint.contains("Andy") ||
            fingerprint.contains("ttVM_Hdragon") ||
            fingerprint.contains("generic_x86_64") ||
            fingerprint.contains("generic/google_sdk/generic") ||
            fingerprint.contains("vbox86p") ||
            fingerprint.contains("generic/vbox86p/vbox86p")) {
            detectionScore++
        }
        
        // Check OpenGL renderer
        try {
            val renderer = GLES20.glGetString(GLES20.GL_RENDERER)
            if (renderer != null && (renderer.contains("Bluestacks") || renderer.contains("Translator"))) {
                detectionScore += 10
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        
        // Check for Bluestacks file
        try {
            val bstSharedFolder = File(
                "${Environment.getExternalStorageDirectory()}${File.separatorChar}windows${File.separatorChar}BstSharedFolder"
            )
            if (bstSharedFolder.exists()) {
                detectionScore += 10
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        
        // If detection score is 3 or higher, likely an emulator
        return detectionScore > 3
    }
    
    /**
     * Legacy/simplified approach to check if running on an emulator (deprecated)
     */
    @Deprecated("Use isEmulator() instead for more accurate detection")
    fun isEmulatorDeprecated(): Boolean {
        return (Build.FINGERPRINT.startsWith("generic") ||
                Build.FINGERPRINT.startsWith("unknown") ||
                Build.MODEL.contains("google_sdk") ||
                Build.MODEL.contains("Emulator") ||
                Build.MODEL.contains("Android SDK built for x86") ||
                Build.MANUFACTURER.contains("Genymotion") ||
                Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic") ||
                "google_sdk" == Build.PRODUCT)
    }
} 