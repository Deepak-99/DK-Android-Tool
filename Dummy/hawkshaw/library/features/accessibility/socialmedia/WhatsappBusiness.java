package com.hawkshaw.library.features.accessibility.socialmedia;

import Y1.K;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.concurrent.ExecutorService;

public final class WhatsappBusiness {
    public static final WhatsappBusiness INSTANCE = new WhatsappBusiness();

    private WhatsappBusiness() {
    }

    public final void extractMessages(AccessibilityNodeInfo accessibilityNodeInfo, ExecutorService executorService) {
        K.n(accessibilityNodeInfo, "source");
        K.n(executorService, "executor");
        Whatsapp.INSTANCE.extractMessages(accessibilityNodeInfo, executorService);
    }
}
