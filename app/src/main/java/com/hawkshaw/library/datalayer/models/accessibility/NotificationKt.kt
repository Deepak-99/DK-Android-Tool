package com.hawkshaw.library.datalayer.models.accessibility

import com.hawkshaw.library.datalayer.room.notification.NotificationEntity

/**
 * Extension function to convert a NotificationEntity to a PushNotificationRequest.Notification
 *
 * @return A new PushNotificationRequest.Notification instance with values from this NotificationEntity
 */
fun NotificationEntity.toRequest(): PushNotificationRequest.Notification {
    return PushNotificationRequest.Notification(
        id = this.id,
        packageName = this.packageName,
        title = this.title,
        text = this.text,
        timestamp = this.timestamp,
        pushed = this.pushed
    )
} 