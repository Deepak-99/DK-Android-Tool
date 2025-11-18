package com.hawkshaw.library.ktextensions;

import E.k;
import Y1.K;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AppOpsManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.os.Process;
import android.widget.Toast;
import com.hawkshaw.library.logger.Logger;
import i.C0528x;
import java.util.Iterator;
import java.util.List;

public final class ContextKt {
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0023, code lost:*/
        r2 = r2.getNotificationChannel(r3);
     
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final boolean areNotificationsEnabled(android.content.Context r2, java.lang.String r3) {
        
            java.lang.String r0 = "<this>"
            Y1.K.n(r2, r0)
            r0 = 0
            if (r3 == 0) goto L_0x0030
            boolean r1 = r3.j.U(r3)
            if (r1 == 0) goto L_0x000f
            goto L_0x0030
        L_0x000f:
            java.lang.String r1 = "notification"
            java.lang.Object r2 = r2.getSystemService(r1)
            java.lang.String r1 = "null cannot be cast to non-null type android.app.NotificationManager"
            Y1.K.l(r2, r1)
            android.app.NotificationManager r2 = (android.app.NotificationManager) r2
            boolean r1 = r2.areNotificationsEnabled()
            if (r1 != 0) goto L_0x0023
            return r0
        L_0x0023:
            android.app.NotificationChannel r2 = r2.getNotificationChannel(r3)
            if (r2 == 0) goto L_0x0030
            int r2 = r2.getImportance()
            if (r2 == 0) goto L_0x0030
            r0 = 1
        L_0x0030:
            return r0
        
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.ktextensions.ContextKt.areNotificationsEnabled(android.content.Context, java.lang.String):boolean");
    }

    public static final boolean canLaunchFromBg(Context context) {
        K.n(context, "<this>");
        return Build.VERSION.SDK_INT < 29 || isAppInForeground(context);
    }

    public static final void copyToClipboard(Context context, CharSequence charSequence, String str) {
        K.n(context, "<this>");
        K.n(charSequence, "text");
        K.n(str, "label");
        ClipboardManager clipboardManager = (ClipboardManager) k.getSystemService(context, ClipboardManager.class);
        if (clipboardManager != null) {
            clipboardManager.setPrimaryClip(ClipData.newPlainText(str, charSequence));
        }
    }

    public static /* synthetic */ void copyToClipboard$default(Context context, CharSequence charSequence, String str, int i5, Object obj) {
        if ((i5 & 2) != 0) {
            str = "label";
        }
        copyToClipboard(context, charSequence, str);
    }

    public static final Activity getActivity(Context context) {
        K.n(context, "<this>");
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            Context baseContext = ((ContextWrapper) context).getBaseContext();
            K.m(baseContext, "getBaseContext(...)");
            return getActivity(baseContext);
        }
        throw new Exception("Activity not found");
    }

    public static final boolean isAppInForeground(Context context) {
        Integer num;
        Object obj;
        K.n(context, "<this>");
        Context applicationContext = context.getApplicationContext();
        Object systemService = context.getSystemService("activity");
        K.l(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) systemService).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return false;
        }
        Iterator it = runningAppProcesses.iterator();
        while (true) {
            num = null;
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (K.f(((ActivityManager.RunningAppProcessInfo) obj).processName, applicationContext.getPackageName())) {
                break;
            }
        }
        ActivityManager.RunningAppProcessInfo runningAppProcessInfo = (ActivityManager.RunningAppProcessInfo) obj;
        ActivityManager.getMyMemoryState(runningAppProcessInfo);
        Logger logger = Logger.INSTANCE;
        if (runningAppProcessInfo != null) {
            num = Integer.valueOf(runningAppProcessInfo.importance);
        }
        Logger.v$default(logger, "IsAppInForeground", "Importance: " + num, false, 4, (Object) null);
        return runningAppProcessInfo != null && runningAppProcessInfo.importance == 100;
    }

    public static final boolean isAudioRecordAllowed(Context context) {
        K.n(context, "<this>");
        if (Build.VERSION.SDK_INT < 29) {
            return true;
        }
        Object systemService = context.getSystemService("appops");
        K.l(systemService, "null cannot be cast to non-null type android.app.AppOpsManager");
        int a5 = ((AppOpsManager) systemService).unsafeCheckOpNoThrow("android:record_audio", Process.myUid(), context.getPackageName());
        Logger.v$default(Logger.INSTANCE, "AppOpsManager", C0528x.b("isAudioRecordAllowed: ", a5), false, 4, (Object) null);
        return a5 == 0;
    }

    public static final boolean isCameraAllowed(Context context) {
        K.n(context, "<this>");
        if (Build.VERSION.SDK_INT < 29) {
            return true;
        }
        Object systemService = context.getSystemService("appops");
        K.l(systemService, "null cannot be cast to non-null type android.app.AppOpsManager");
        int o4 = ((AppOpsManager) systemService).unsafeCheckOpNoThrow("android:camera", Process.myUid(), context.getPackageName());
        Logger.v$default(Logger.INSTANCE, "AppOpsManager", C0528x.b("isCameraAllowed: ", o4), false, 4, (Object) null);
        return o4 == 0;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: X2.q} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: X2.q} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: X2.q} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.util.List<java.lang.String> readFromClipboard(android.content.Context r5) {
        
            java.lang.String r0 = "<this>"
            Y1.K.n(r5, r0)
            java.lang.Class<android.content.ClipboardManager> r0 = android.content.ClipboardManager.class
            java.lang.Object r0 = E.k.getSystemService(r5, r0)
            android.content.ClipboardManager r0 = (android.content.ClipboardManager) r0
            X2.q r1 = X2.q.f2602D
            if (r0 != 0) goto L_0x0012
            return r1
        L_0x0012:
            android.content.ClipData r0 = r0.getPrimaryClip()
            if (r0 == 0) goto L_0x0032
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            int r2 = r0.getItemCount()
            r3 = 0
        L_0x0022:
            if (r3 >= r2) goto L_0x0032
            android.content.ClipData$Item r4 = r0.getItemAt(r3)
            java.lang.String r4 = r4.coerceToHtmlText(r5)
            r1.add(r4)
            int r3 = r3 + 1
            goto L_0x0022
        L_0x0032:
            return r1
        
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.ktextensions.ContextKt.readFromClipboard(android.content.Context):java.util.List");
    }

    public static final void toast(Context context, CharSequence charSequence) {
        K.n(context, "<this>");
        K.n(charSequence, "text");
        Toast.makeText(context, charSequence, 0).show();
    }

    public static final void toastLong(Context context, CharSequence charSequence) {
        K.n(context, "<this>");
        K.n(charSequence, "text");
        Toast.makeText(context, charSequence, 1).show();
    }
}
