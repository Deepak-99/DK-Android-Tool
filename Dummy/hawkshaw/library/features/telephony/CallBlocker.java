package com.hawkshaw.library.features.telephony;

import Y1.K;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.os.IInterface;
import android.telecom.TelecomManager;
import com.hawkshaw.library.App;
import com.hawkshaw.library.ktextensions.ManifestPermissionsKt;
import com.hawkshaw.library.logger.Logger;
import i.C0528x;
import java.lang.reflect.Method;

public final class CallBlocker {
    public static final CallBlocker INSTANCE = new CallBlocker();
    private static final String TAG = "CallBlocker";

    private CallBlocker() {
    }

    private final boolean endCall(String str, boolean z4) {
        String d5;
        boolean endCall = endCall();
        if (endCall) {
            d5 = (z4 ? "Outgoing" : "Incoming") + " call blocked from " + str;
        } else {
            d5 = C0528x.d("Call blocking for ", str, " failed!");
        }
        Logger.d$default(Logger.INSTANCE, TAG, d5, false, 4, (Object) null);
        return endCall;
    }

    public static /* synthetic */ boolean endCall$default(CallBlocker callBlocker, String str, boolean z4, int i5, Object obj) {
        if ((i5 & 2) != 0) {
            z4 = false;
        }
        return callBlocker.endCall(str, z4);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0050 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0021  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object getMatch(java.lang.String r8, a3.e r9) {
        
            r7 = this;
            boolean r0 = r9 instanceof com.hawkshaw.library.features.telephony.a
            if (r0 == 0) goto L_0x0013
            r0 = r9
            com.hawkshaw.library.features.telephony.a r0 = (com.hawkshaw.library.features.telephony.a) r0
            int r1 = r0.f5016G
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.f5016G = r1
            goto L_0x0018
        L_0x0013:
            com.hawkshaw.library.features.telephony.a r0 = new com.hawkshaw.library.features.telephony.a
            r0.<init>(r7, r9)
        L_0x0018:
            java.lang.Object r9 = r0.f5014E
            b3.a r1 = b3.C0298a.f4140D
            int r2 = r0.f5016G
            r3 = 1
            if (r2 == 0) goto L_0x0031
            if (r2 != r3) goto L_0x0029
            java.lang.String r8 = r0.f5013D
            Y1.C0110h.P(r9)
            goto L_0x0047
        L_0x0029:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0031:
            Y1.C0110h.P(r9)
            com.hawkshaw.library.datalayer.room.AppDatabase r9 = com.hawkshaw.library.datalayer.room.AppDatabaseKt.getDb()
            com.hawkshaw.library.datalayer.room.telephony.TelephonyDao r9 = r9.telephonyDao()
            r0.f5013D = r8
            r0.f5016G = r3
            java.lang.Object r9 = r9.getCallBlockNumbers(r0)
            if (r9 != r1) goto L_0x0047
            return r1
        L_0x0047:
            java.util.List r9 = (java.util.List) r9
            boolean r0 = r9.isEmpty()
            r1 = 0
            if (r0 == 0) goto L_0x0051
            return r1
        L_0x0051:
            s1.c r0 = s1.c.d()
            java.lang.Iterable r9 = (java.lang.Iterable) r9
            java.util.Iterator r9 = r9.iterator()
        L_0x005b:
            boolean r2 = r9.hasNext()
            if (r2 == 0) goto L_0x0083
            java.lang.Object r2 = r9.next()
            r4 = r2
            com.hawkshaw.library.datalayer.room.telephony.CallBlockEntity r4 = (com.hawkshaw.library.datalayer.room.telephony.CallBlockEntity) r4
            java.lang.String r5 = r4.getName()
            java.lang.String r6 = "HAWKSHAW_ALL"
            boolean r5 = Y1.K.f(r5, r6)
            if (r5 == 0) goto L_0x0075
            goto L_0x0082
        L_0x0075:
            java.lang.String r4 = r4.getNumber()
            int r4 = r0.g(r4, r8)
            r5 = 2
            if (r4 == r5) goto L_0x005b
            if (r4 == r3) goto L_0x005b
        L_0x0082:
            r1 = r2
        L_0x0083:
            return r1
        
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.features.telephony.CallBlocker.getMatch(java.lang.String, a3.e):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0035  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0056  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0021  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object onCallStateChanged(int r5, java.lang.String r6, a3.e r7) {
        
            r4 = this;
            boolean r0 = r7 instanceof com.hawkshaw.library.features.telephony.b
            if (r0 == 0) goto L_0x0013
            r0 = r7
            com.hawkshaw.library.features.telephony.b r0 = (com.hawkshaw.library.features.telephony.b) r0
            int r1 = r0.f5022I
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.f5022I = r1
            goto L_0x0018
        L_0x0013:
            com.hawkshaw.library.features.telephony.b r0 = new com.hawkshaw.library.features.telephony.b
            r0.<init>(r4, r7)
        L_0x0018:
            java.lang.Object r7 = r0.f5020G
            b3.a r1 = b3.C0298a.f4140D
            int r2 = r0.f5022I
            r3 = 1
            if (r2 == 0) goto L_0x0035
            if (r2 != r3) goto L_0x002d
            int r5 = r0.f5019F
            java.lang.String r6 = r0.f5018E
            com.hawkshaw.library.features.telephony.CallBlocker r0 = r0.f5017D
            Y1.C0110h.P(r7)
            goto L_0x0052
        L_0x002d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0035:
            Y1.C0110h.P(r7)
            if (r6 != 0) goto L_0x003d
            java.lang.Boolean r5 = java.lang.Boolean.FALSE
            return r5
        L_0x003d:
            if (r5 != 0) goto L_0x0042
            java.lang.Boolean r5 = java.lang.Boolean.FALSE
            return r5
        L_0x0042:
            r0.f5017D = r4
            r0.f5018E = r6
            r0.f5019F = r5
            r0.f5022I = r3
            java.lang.Object r7 = r4.getMatch(r6, r0)
            if (r7 != r1) goto L_0x0051
            return r1
        L_0x0051:
            r0 = r4
        L_0x0052:
            com.hawkshaw.library.datalayer.room.telephony.CallBlockEntity r7 = (com.hawkshaw.library.datalayer.room.telephony.CallBlockEntity) r7
            if (r7 != 0) goto L_0x0059
            java.lang.Boolean r5 = java.lang.Boolean.FALSE
            return r5
        L_0x0059:
            r1 = 2
            if (r5 == r3) goto L_0x0071
            if (r5 == r1) goto L_0x005f
            goto L_0x006e
        L_0x005f:
            boolean r5 = r7.getBlockOutgoing()
            if (r5 == 0) goto L_0x006e
            boolean r5 = r0.endCall(r6, r3)
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
            return r5
        L_0x006e:
            java.lang.Boolean r5 = java.lang.Boolean.FALSE
            return r5
        L_0x0071:
            r5 = 0
            r7 = 0
            boolean r5 = endCall$default(r0, r6, r5, r1, r7)
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
            return r5
        
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.features.telephony.CallBlocker.onCallStateChanged(int, java.lang.String, a3.e):java.lang.Object");
    }

    private final boolean endCall() {
        Class<IBinder> cls = IBinder.class;
        if (Build.VERSION.SDK_INT >= 28) {
            Object systemService = App.Companion.getContext().getSystemService("telecom");
            K.l(systemService, "null cannot be cast to non-null type android.telecom.TelecomManager");
            TelecomManager telecomManager = (TelecomManager) systemService;
            if (!ManifestPermissionsKt.hasPermission("android.permission.ANSWER_PHONE_CALLS")) {
                return false;
            }
            telecomManager.endCall();
            return true;
        }
        try {
            Class<?> cls2 = Class.forName("com.android.internal.telephony.ITelephony");
            Class cls3 = cls2.getClasses()[0];
            Class<?> cls4 = Class.forName("android.os.ServiceManager");
            Class<?> cls5 = Class.forName("android.os.ServiceManagerNative");
            Method method = cls4.getMethod("getService", new Class[]{String.class});
            Method method2 = cls5.getMethod("asInterface", new Class[]{cls});
            Binder binder = new Binder();
            binder.attachInterface((IInterface) null, "fake");
            Object invoke = method.invoke(method2.invoke((Object) null, new Object[]{binder}), new Object[]{"phone"});
            K.l(invoke, "null cannot be cast to non-null type android.os.IBinder");
            cls2.getMethod("endCall", new Class[0]).invoke(cls3.getMethod("asInterface", new Class[]{cls}).invoke((Object) null, new Object[]{(IBinder) invoke}), new Object[0]);
            return true;
        } catch (Exception e5) {
            e5.printStackTrace();
            return false;
        }
    }
}
