package com.hawkshaw.library.features.telephony;

import W2.y;
import a3.e;
import c3.C0331i;
import i3.p;
import t3.E;

public final class i extends C0331i implements p {

    /* renamed from: D  reason: collision with root package name */
    public int f5039D;

    /* renamed from: E  reason: collision with root package name */
    public final /* synthetic */ TelephonyCallbackService f5040E;

    /* renamed from: F  reason: collision with root package name */
    public final /* synthetic */ int f5041F;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public i(TelephonyCallbackService telephonyCallbackService, int i5, e eVar) {
        super(2, eVar);
        this.f5040E = telephonyCallbackService;
        this.f5041F = i5;
    }

    public final e create(Object obj, e eVar) {
        return new i(this.f5040E, this.f5041F, eVar);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((i) create((E) obj, (e) obj2)).invokeSuspend(y.f2418a);
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0063  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r13) {
        
            r12 = this;
            b3.a r0 = b3.C0298a.f4140D
            int r1 = r12.f5039D
            r2 = 2
            r3 = 1
            int r4 = r12.f5041F
            com.hawkshaw.library.features.telephony.TelephonyCallbackService r5 = r12.f5040E
            if (r1 == 0) goto L_0x0020
            if (r1 == r3) goto L_0x001c
            if (r1 != r2) goto L_0x0014
            Y1.C0110h.P(r13)
            goto L_0x0061
        L_0x0014:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r0)
            throw r13
        L_0x001c:
            Y1.C0110h.P(r13)
            goto L_0x004c
        L_0x0020:
            Y1.C0110h.P(r13)
            com.hawkshaw.library.logger.Logger r6 = com.hawkshaw.library.logger.Logger.INSTANCE
            java.lang.String r13 = r5.phoneNumber
            java.lang.String r1 = r5.toStateString(r4)
            java.lang.String r7 = "CallStateChanged: Number: "
            java.lang.String r8 = ", state: "
            java.lang.String r8 = i.C0528x.e(r7, r13, r8, r1)
            r10 = 4
            r11 = 0
            java.lang.String r7 = "TelephonyCallbackService"
            r9 = 0
            com.hawkshaw.library.logger.Logger.d$default(r6, r7, r8, r9, r10, r11)
            com.hawkshaw.library.features.telephony.CallBlocker r13 = com.hawkshaw.library.features.telephony.CallBlocker.INSTANCE
            java.lang.String r1 = r5.phoneNumber
            r12.f5039D = r3
            java.lang.Object r13 = r13.onCallStateChanged(r4, r1, r12)
            if (r13 != r0) goto L_0x004c
            return r0
        L_0x004c:
            java.lang.Boolean r13 = (java.lang.Boolean) r13
            boolean r13 = r13.booleanValue()
            com.hawkshaw.library.features.telephony.CallRecorder$Companion r1 = com.hawkshaw.library.features.telephony.CallRecorder.Companion
            java.lang.String r3 = r5.phoneNumber
            r12.f5039D = r2
            java.lang.Object r13 = r1.onCallStateChanged$library_release(r4, r3, r13, r12)
            if (r13 != r0) goto L_0x0061
            return r0
        L_0x0061:
            if (r4 != 0) goto L_0x0066
            r5.stopSelf()
        L_0x0066:
            W2.y r13 = W2.y.f2418a
            return r13
        
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.features.telephony.i.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
