package com.hawkshaw.library.features.accessibility.socialmedia;

import Y1.K;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.concurrent.ExecutorService;

public final class Gmail {
    public static final Gmail INSTANCE = new Gmail();

    /* renamed from: p  reason: collision with root package name */
    private static final String f4965p = "com.google.android.gm:id";

    private Gmail() {
    }

    public final void extractMessages(AccessibilityNodeInfo accessibilityNodeInfo, ExecutorService executorService) {
        K.n(accessibilityNodeInfo, "node");
        K.n(executorService, "executor");
    }
}
