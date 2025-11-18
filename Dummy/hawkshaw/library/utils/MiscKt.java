package com.hawkshaw.library.utils;

import E.k;
import Y1.K;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.view.accessibility.AccessibilityManager;
import androidx.core.app.NotificationCompat;
import com.hawkshaw.library.App;
import com.hawkshaw.library.R;
import com.hawkshaw.library.activities.TransparentActivity;
import com.hawkshaw.library.features.accessibility.MainAccessibilityService;
import com.hawkshaw.library.ktextensions.ContextKt;
import com.hawkshaw.library.ktextensions.ExceptionsKt;
import com.hawkshaw.library.preferences.Prefs;
import me.pushy.sdk.lib.paho.MqttTopic;
import me.pushy.sdk.lib.paho.internal.ClientDefaults;
import r3.j;

public final class MiscKt {
    public static final boolean isAccessibilityEnabled(Context context) {
        K.n(context, "context");
        String str = context.getPackageName() + MqttTopic.TOPIC_LEVEL_SEPARATOR + MainAccessibilityService.class.getCanonicalName();
        Object systemService = context.getSystemService("accessibility");
        K.l(systemService, "null cannot be cast to non-null type android.view.accessibility.AccessibilityManager");
        for (AccessibilityServiceInfo id : ((AccessibilityManager) systemService).getEnabledAccessibilityServiceList(-1)) {
            if (K.f(str, id.getId())) {
                return true;
            }
        }
        return false;
    }

    public static /* synthetic */ boolean isAccessibilityEnabled$default(Context context, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            context = App.Companion.getContext();
        }
        return isAccessibilityEnabled(context);
    }

    public static final boolean isUserLoggedIn() {
        String token = Prefs.INSTANCE.getToken();
        return !(token == null || j.U(token));
    }

    public static final void openBgActivity(Context context, boolean z4) {
        K.n(context, "context");
        PowerManager powerManager = (PowerManager) k.getSystemService(context, PowerManager.class);
        if (z4 || (powerManager != null && !powerManager.isInteractive())) {
            Intent intent = new Intent(context, TransparentActivity.class);
            intent.putExtra("OPEN_ACTIVITY_IN_FOREGROUND", true);
            intent.setFlags(ClientDefaults.MAX_MSG_SIZE);
            context.startActivity(intent);
        }
    }

    public static /* synthetic */ void openBgActivity$default(Context context, boolean z4, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            context = App.Companion.getContext();
        }
        if ((i5 & 2) != 0) {
            z4 = false;
        }
        openBgActivity(context, z4);
    }

    public static final void startActivity(Intent intent, String str, String str2, Context context) {
        K.n(intent, "intent");
        K.n(str, "title");
        K.n(str2, "subtitle");
        K.n(context, "context");
        try {
            if (ContextKt.canLaunchFromBg(context)) {
                context.startActivity(intent);
            } else {
                startActivity$notify(context, intent, str, str2);
            }
        } catch (Exception e5) {
            ExceptionsKt.logNonFatal(e5);
            startActivity$notify(context, intent, str, str2);
        }
    }

    public static /* synthetic */ void startActivity$default(Intent intent, String str, String str2, Context context, int i5, Object obj) {
        if ((i5 & 2) != 0) {
            str = "Settings";
        }
        if ((i5 & 4) != 0) {
            str2 = "Click to view";
        }
        if ((i5 & 8) != 0) {
            context = App.Companion.getContext();
        }
        startActivity(intent, str, str2, context);
    }

    private static final void startActivity$notify(Context context, Intent intent, String str, String str2) {
        PendingIntent activity = PendingIntent.getActivity(context, 76, intent, 134217728);
        NotificationCompat.Builder autoCancel = NotificationKt.getNotification(str, str2, intent, context).setPriority(0).setFullScreenIntent(activity, true).setContentIntent(activity).setAutoCancel(true);
        K.m(autoCancel, "setAutoCancel(...)");
        ((NotificationManager) context.getSystemService(NotificationManager.class)).notify(76, autoCancel.build());
    }

    public static final void startService(Intent intent, String str, String str2, Context context) {
        K.n(intent, "intent");
        K.n(str, "title");
        K.n(str2, "subtitle");
        K.n(context, "context");
        try {
            context.startService(intent);
        } catch (Exception e5) {
            ExceptionsKt.logNonFatal(e5);
            startService$alarm(context, intent);
        }
    }

    private static final void startService$alarm(Context context, Intent intent) {
        ((AlarmManager) context.getSystemService(AlarmManager.class)).set(0, System.currentTimeMillis() + ((long) 1000), PendingIntent.getService(context, 76, intent, 134217728));
    }

    public static /* synthetic */ void startService$default(Intent intent, String str, String str2, Context context, int i5, Object obj) {
        if ((i5 & 2) != 0) {
            str = "Settings";
        }
        if ((i5 & 4) != 0) {
            str2 = "Click to view";
        }
        if ((i5 & 8) != 0) {
            context = App.Companion.getContext();
        }
        startService(intent, str, str2, context);
    }

    private static final void startService$notify$1(Context context, Intent intent, String str, String str2) {
        NotificationCompat.Builder addAction = NotificationKt.getNotification(str, str2, intent, context).setPriority(0).setAutoCancel(true).addAction(R.drawable.ic_media_record, "Open", PendingIntent.getService(context, 76, intent, 134217728));
        K.m(addAction, "addAction(...)");
        ((NotificationManager) context.getSystemService(NotificationManager.class)).notify(76, addAction.build());
    }
}
