package com.hawkshaw.library.features.telephony;

import E.k;
import Y1.K;
import android.content.Context;
import android.telephony.TelephonyManager;
import i3.C0542a;
import j3.j;

public final class h extends j implements C0542a {

    /* renamed from: D  reason: collision with root package name */
    public final /* synthetic */ int f5037D;

    /* renamed from: E  reason: collision with root package name */
    public final /* synthetic */ TelephonyCallbackService f5038E;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public /* synthetic */ h(TelephonyCallbackService telephonyCallbackService, int i5) {
        super(0);
        this.f5037D = i5;
        this.f5038E = telephonyCallbackService;
    }

    public final Object invoke() {
        int i5 = this.f5037D;
        TelephonyCallbackService telephonyCallbackService = this.f5038E;
        switch (i5) {
            case 0:
                return new TelephonyCallbackService$callStateListener$2$1(telephonyCallbackService);
            case 1:
                return new TelephonyCallbackService$phoneStateListener$2$1(telephonyCallbackService);
            default:
                Context applicationContext = telephonyCallbackService.getApplicationContext();
                K.m(applicationContext, "getApplicationContext(...)");
                Object systemService = k.getSystemService(applicationContext, TelephonyManager.class);
                K.l(systemService, "null cannot be cast to non-null type android.telephony.TelephonyManager");
                return (TelephonyManager) systemService;
        }
    }
}
