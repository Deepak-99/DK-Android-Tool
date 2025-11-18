package com.hawkshaw.library.features.telephony;

import W2.e;
import W2.l;
import Y1.J;
import a3.j;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.telephony.TelephonyManager;
import com.hawkshaw.library.ktextensions.CoroutineKt;
import com.hawkshaw.library.ktextensions.ManifestPermissionsKt;
import com.hawkshaw.library.logger.Logger;
import j3.f;
import t3.N;

public final class TelephonyCallbackService extends Service {
    public static final Companion Companion = new Companion((f) null);
    private static final String TAG = "TelephonyCallbackService";
    private final e callStateListener$delegate = new l(new h(this, 0));
    /* access modifiers changed from: private */
    public String phoneNumber;
    private final e phoneStateListener$delegate = new l(new h(this, 1));
    private final e tm$delegate = new l(new h(this, 2));

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(f fVar) {
            this();
        }
    }

    private final TelephonyCallbackService$callStateListener$2$1 getCallStateListener() {
        return (TelephonyCallbackService$callStateListener$2$1) this.callStateListener$delegate.getValue();
    }

    private final TelephonyCallbackService$phoneStateListener$2$1 getPhoneStateListener() {
        return (TelephonyCallbackService$phoneStateListener$2$1) this.phoneStateListener$delegate.getValue();
    }

    private final TelephonyManager getTm() {
        return (TelephonyManager) this.tm$delegate.getValue();
    }

    /* access modifiers changed from: private */
    public final void onCallStateChanged(int i5) {
        CoroutineKt.safeLaunch$default(J.a(N.f9290a), (j) null, new i(this, i5, (a3.e) null), 1, (Object) null);
    }

    private final void registerTelephonyCallback() {
        if (Build.VERSION.SDK_INT >= 31) {
            getTm().registerTelephonyCallback(getApplicationContext().getMainExecutor(), getCallStateListener());
        } else {
            getTm().listen(getPhoneStateListener(), 32);
        }
    }

    /* access modifiers changed from: private */
    public final String toStateString(int i5) {
        return i5 != 0 ? i5 != 1 ? i5 != 2 ? "UNKNOWN" : "OFFHOOK" : "RINGING" : "IDLE";
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        if (ManifestPermissionsKt.hasPermission((Context) this, "android.permission.READ_PHONE_STATE")) {
            registerTelephonyCallback();
            return;
        }
        Logger.e$default(Logger.INSTANCE, TAG, "onCreate: You don't have read_phone_state permission", (Exception) null, false, 12, (Object) null);
        stopSelf();
    }

    public void onDestroy() {
        super.onDestroy();
        if (Build.VERSION.SDK_INT >= 31) {
            getTm().unregisterTelephonyCallback(getCallStateListener());
        } else {
            getTm().listen(getPhoneStateListener(), 0);
        }
    }

    public int onStartCommand(Intent intent, int i5, int i6) {
        String stringExtra = intent != null ? intent.getStringExtra("incoming_number") : null;
        if (stringExtra != null && (!r3.j.U(stringExtra))) {
            this.phoneNumber = stringExtra;
        }
        return super.onStartCommand(intent, i5, i6);
    }
}
