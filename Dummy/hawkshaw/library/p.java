package com.hawkshaw.library;

import W2.y;
import a3.e;
import c3.C0331i;
import t3.E;

public final class p extends C0331i implements i3.p {

    /* renamed from: D  reason: collision with root package name */
    public int f5054D;

    /* renamed from: E  reason: collision with root package name */
    public /* synthetic */ Object f5055E;

    /* renamed from: F  reason: collision with root package name */
    public final /* synthetic */ HawkshawInitializer f5056F;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public p(HawkshawInitializer hawkshawInitializer, e eVar) {
        super(2, eVar);
        this.f5056F = hawkshawInitializer;
    }

    public final e create(Object obj, e eVar) {
        p pVar = new p(this.f5056F, eVar);
        pVar.f5055E = obj;
        return pVar;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((p) create((E) obj, (e) obj2)).invokeSuspend(y.f2418a);
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x008c  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00aa  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r15) {
        /*
            r14 = this;
            b3.a r0 = b3.C0298a.f4140D
            int r1 = r14.f5054D
            W2.y r2 = W2.y.f2418a
            r3 = 0
            r4 = 2
            r5 = 1
            com.hawkshaw.library.HawkshawInitializer r6 = r14.f5056F
            if (r1 == 0) goto L_0x002a
            if (r1 == r5) goto L_0x0021
            if (r1 != r4) goto L_0x0019
            java.lang.Object r0 = r14.f5055E
            t3.E r0 = (t3.E) r0
            Y1.C0110h.P(r15)
            goto L_0x0082
        L_0x0019:
            java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r15.<init>(r0)
            throw r15
        L_0x0021:
            java.lang.Object r1 = r14.f5055E
            t3.E r1 = (t3.E) r1
            Y1.C0110h.P(r15)
            r15 = r1
            goto L_0x0070
        L_0x002a:
            Y1.C0110h.P(r15)
            java.lang.Object r15 = r14.f5055E
            t3.E r15 = (t3.E) r15
            boolean r1 = com.hawkshaw.library.utils.EmulatorDetectionKt.isEmulator()
            if (r1 == 0) goto L_0x0052
            com.hawkshaw.library.logger.Logger r7 = com.hawkshaw.library.logger.Logger.INSTANCE
            r10 = 0
            r11 = 0
            java.lang.String r8 = "HawkshawInitializer"
            java.lang.String r9 = "Emulator detected, restricted"
            r12 = 12
            r13 = 0
            com.hawkshaw.library.logger.Logger.e$default(r7, r8, r9, r10, r11, r12, r13)
            androidx.activity.o r15 = r6.activity
            java.lang.String r0 = "Emulator detected, restricted"
            com.hawkshaw.library.ktextensions.ContextKt.toast(r15, r0)
            r6.close(r5)
            return r2
        L_0x0052:
            boolean r1 = com.hawkshaw.library.utils.MiscKt.isUserLoggedIn()
            if (r1 != 0) goto L_0x0086
            androidx.activity.o r1 = r6.activity
            com.hawkshaw.library.datalayer.models.Config r1 = com.hawkshaw.library.config.ConfigKt.getConfig(r1)
            java.lang.String r1 = r1.getEmail()
            r14.f5055E = r15
            r14.f5054D = r5
            r7 = 0
            java.lang.Object r1 = com.hawkshaw.library.features.AuthKt.login$default(r1, r7, r14, r4, r3)
            if (r1 != r0) goto L_0x0070
            return r0
        L_0x0070:
            com.hawkshaw.library.config.RemoteConfig r1 = com.hawkshaw.library.config.RemoteConfig.INSTANCE
            long r7 = r1.getDelayAfterLoginMs()
            r14.f5055E = r15
            r14.f5054D = r4
            java.lang.Object r1 = Y1.K.C(r7, r14)
            if (r1 != r0) goto L_0x0081
            return r0
        L_0x0081:
            r0 = r15
        L_0x0082:
            r6.pushData = r5
            r15 = r0
        L_0x0086:
            boolean r0 = com.hawkshaw.library.utils.MiscKt.isUserLoggedIn()
            if (r0 == 0) goto L_0x00aa
            z3.c r0 = t3.N.f9292c
            com.hawkshaw.library.o r1 = new com.hawkshaw.library.o
            r1.<init>(r6, r3)
            com.hawkshaw.library.ktextensions.CoroutineKt.safeLaunch(r15, r0, r1)
            r6.enableAccessibility()
            androidx.activity.o r15 = r6.activity
            androidx.lifecycle.o r15 = r15.getLifecycle()
            com.hawkshaw.library.HawkshawInitializer$init$1$2 r0 = new com.hawkshaw.library.HawkshawInitializer$init$1$2
            r0.<init>(r6)
            r15.a(r0)
            return r2
        L_0x00aa:
            java.lang.Exception r15 = new java.lang.Exception
            java.lang.String r0 = "User not logged in"
            r15.<init>(r0)
            throw r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.p.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
