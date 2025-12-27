package com.hawkshaw.library.adapters.system

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import com.hawkshaw.library.data.*

class NotificationSignalAdapter : NotificationListenerService() {

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        EventQueue.enqueue(
            AgentEvent.NotificationSignal(
                packageName = sbn.packageName,
                category = sbn.notification.category ?: "unknown"
            )
        )
    }
}