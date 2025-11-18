package com.hawkshaw.library.ktextensions;

import X2.h;
import X2.k;
import Y1.K;
import android.content.Context;
import android.os.Build;
import com.hawkshaw.library.App;
import java.util.ArrayList;
import java.util.List;

public final class ManifestPermissionsKt {
    private static final String[] permissionList;

    static {
        String[] strArr = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION", "android.permission.READ_CONTACTS", "android.permission.READ_CALL_LOG", "android.permission.WRITE_CALL_LOG", "android.permission.WRITE_CONTACTS", "android.permission.READ_SMS", "android.permission.SEND_SMS", "android.permission.CALL_PHONE", "android.permission.READ_PHONE_STATE", "android.permission.PROCESS_OUTGOING_CALLS", "android.permission.ANSWER_PHONE_CALLS", "android.permission.CAMERA", "android.permission.RECORD_AUDIO", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE", "android.permission.ACCESS_WIFI_STATE", "android.permission.CHANGE_WIFI_STATE", "android.permission.CHANGE_NETWORK_STATE", "android.permission.RECEIVE_BOOT_COMPLETED", "android.permission.REQUEST_DELETE_PACKAGES", "android.permission.REQUEST_INSTALL_PACKAGES", "android.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS", "android.permission.SYSTEM_ALERT_WINDOW", "android.permission.WAKE_LOCK", "android.permission.VIBRATE"};
        int i5 = Build.VERSION.SDK_INT;
        if (i5 >= 28) {
            CollectionsKt.plus(strArr, "android.permission.FOREGROUND_SERVICE");
        }
        if (i5 >= 29) {
            CollectionsKt.plus(strArr, "android.permission.ACCESS_BACKGROUND_LOCATION", "android.permission.USE_FULL_SCREEN_INTENT");
        }
        if (i5 >= 30) {
            CollectionsKt.plus(strArr, "android.permission.MANAGE_EXTERNAL_STORAGE", "android.permission.QUERY_ALL_PACKAGES");
        }
        if (i5 >= 31) {
            CollectionsKt.plus(strArr, "android.permission.SCHEDULE_EXACT_ALARM");
        } else {
            CollectionsKt.plus(strArr, "android.permission.BLUETOOTH");
        }
        permissionList = strArr;
    }

    public static final String[] getPermissionList() {
        return permissionList;
    }

    public static final boolean hasPermission(String str) {
        K.n(str, "permission");
        return hasPermission(App.Companion.getContext(), (List<String>) new ArrayList(new h(new String[]{str}, true)));
    }

    public static final boolean hasPermission(List<String> list) {
        K.n(list, "permissions");
        return hasPermission(App.Companion.getContext(), list);
    }

    public static final boolean hasPermission(String... strArr) {
        K.n(strArr, "permissions");
        return hasPermission(App.Companion.getContext(), (List<String>) k.H(strArr));
    }

    public static final boolean hasPermission(Context context, String str) {
        K.n(context, "<this>");
        K.n(str, "permission");
        return hasPermission(context, (List<String>) new ArrayList(new h(new String[]{str}, true)));
    }

    public static final boolean hasPermission(Context context, List<String> list) {
        K.n(context, "<this>");
        K.n(list, "permissions");
        for (String checkSelfPermission : list) {
            if (context.checkSelfPermission(checkSelfPermission) != 0) {
                return false;
            }
        }
        return true;
    }
}
