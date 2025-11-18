package com.hawkshaw.library.datalayer.models.accessibility;

import Y1.K;
import com.hawkshaw.library.datalayer.models.accessibility.PushNotificationRequest;
import com.hawkshaw.library.datalayer.room.notification.NotificationEntity;

public final class NotificationKt {
    public static final PushNotificationRequest.Notification toRequest(NotificationEntity notificationEntity) {
        K.n(notificationEntity, "<this>");
        return new PushNotificationRequest.Notification(notificationEntity.getPackageName(), notificationEntity.getExtraText(), notificationEntity.getExtraTitle(), notificationEntity.getExtraMessage(), notificationEntity.getExtraBigText(), notificationEntity.getExtraTextLines(), notificationEntity.getTickerText(), notificationEntity.getTimestamp());
    }
}
