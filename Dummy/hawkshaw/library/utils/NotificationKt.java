package com.hawkshaw.library.utils;

import Y1.K;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import com.hawkshaw.library.App;
import com.hawkshaw.library.R;

public final class NotificationKt {
    private static final String CHANNEL_ID = "NOTIFICATION_CHANNEL_ID";

    private static final void createNotificationChannel(Context context) {
        NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "Settings", 4);
        notificationChannel.setDescription("Settings is core part of android");
        ((NotificationManager) context.getSystemService(NotificationManager.class)).createNotificationChannel(notificationChannel);
    }

    public static final NotificationCompat.Builder getNotification(String str, String str2, Intent intent, Context context) {
        K.n(str, "title");
        K.n(str2, "contentText");
        K.n(intent, "intent");
        K.n(context, "context");
        createNotificationChannel(context);
        NotificationCompat.Builder contentIntent = new NotificationCompat.Builder(context, CHANNEL_ID).setContentTitle(str).setContentText(str2).setSmallIcon(R.drawable.ic_media_record).setWhen(System.currentTimeMillis()).setTicker("...").setPriority(1).setContentIntent(PendingIntent.getActivity(context, 75, intent, 134217728));
        K.m(contentIntent, "setContentIntent(...)");
        return contentIntent;
    }

    public static /* synthetic */ NotificationCompat.Builder getNotification$default(String str, String str2, Intent intent, Context context, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            str = "Settings";
        }
        if ((i5 & 2) != 0) {
            str2 = "Checking system files ...";
        }
        if ((i5 & 4) != 0) {
            intent = new Intent("android.settings.SETTINGS");
        }
        if ((i5 & 8) != 0) {
            context = App.Companion.getContext();
        }
        return getNotification(str, str2, intent, context);
    }
}
