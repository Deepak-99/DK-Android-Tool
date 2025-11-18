package com.hawkshaw.library.utils;

import Y1.K;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import b.C0281d;

public final class SystemOverlayPermissionKt {
    public static final void requestSystemOverlay(Activity activity, C0281d dVar) {
        Intent intent;
        K.n(activity, "activity");
        K.n(dVar, "launcher");
        if (!Settings.canDrawOverlays(activity)) {
            if (System.getProperty("ro.miui.ui.version.name") != null) {
                intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
                intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity");
                intent.putExtra("extra_pkgname", activity.getPackageName());
            } else {
                String packageName = activity.getPackageName();
                intent = new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + packageName));
            }
            dVar.a(intent);
        }
    }
}
