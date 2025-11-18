package com.hawkshaw.library.datalayer.network.service;

import W2.y;
import a3.e;
import c3.C0327e;
import c3.C0331i;
import i3.p;
import t3.E;

@C0327e(c = "com.hawkshaw.library.datalayer.network.twirp.ApiCallKt$apiCall$2", f = "ApiCall.kt", l = {61, 62, 64, 64}, m = "invokeSuspend")
public final class LogsServiceImpl$pushAppLogs$$inlined$apiCall$default$1 extends C0331i implements p {
    final /* synthetic */ String $endpoint;
    final /* synthetic */ Object $req;
    final /* synthetic */ boolean $retry;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LogsServiceImpl$pushAppLogs$$inlined$apiCall$default$1(String str, Object obj, boolean z4, e eVar) {
        super(2, eVar);
        this.$endpoint = str;
        this.$req = obj;
        this.$retry = z4;
    }

    public final e create(Object obj, e eVar) {
        return new LogsServiceImpl$pushAppLogs$$inlined$apiCall$default$1(this.$endpoint, this.$req, this.$retry, eVar);
    }

    /* JADX WARNING: Removed duplicated region for block: B:46:0x0120 A[SYNTHETIC, Splitter:B:46:0x0120] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0146 A[Catch:{ Exception -> 0x0021 }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x014e A[Catch:{ Exception -> 0x0021 }] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0156 A[Catch:{ Exception -> 0x0021 }] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0187 A[Catch:{ Exception -> 0x0021 }] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x018b A[Catch:{ Exception -> 0x0021 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r24) {
        /*
            r23 = this;
            r1 = r23
            java.lang.Class<com.hawkshaw.library.datalayer.network.twirp.ApiResponse$Error> r0 = com.hawkshaw.library.datalayer.network.twirp.ApiResponse.Error.class
            b3.a r2 = b3.C0298a.f4140D
            int r3 = r1.label
            r4 = 0
            r5 = 4
            r6 = 3
            r7 = 2
            r8 = 1
            r9 = 0
            java.lang.String r10 = "ApiCallError: "
            if (r3 == 0) goto L_0x0051
            if (r3 == r8) goto L_0x004a
            if (r3 == r7) goto L_0x0033
            if (r3 == r6) goto L_0x002c
            if (r3 != r5) goto L_0x0024
            Y1.C0110h.P(r24)     // Catch:{ Exception -> 0x0021 }
            r0 = r24
            goto L_0x0185
        L_0x0021:
            r0 = move-exception
            goto L_0x0193
        L_0x0024:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r2)
            throw r0
        L_0x002c:
            Y1.C0110h.P(r24)     // Catch:{ Exception -> 0x0021 }
            r0 = r24
            goto L_0x0144
        L_0x0033:
            java.lang.Object r3 = r1.L$2
            com.hawkshaw.library.logger.Logger r3 = (com.hawkshaw.library.logger.Logger) r3
            java.lang.Object r7 = r1.L$1
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r8 = r1.L$0
            y2.c r8 = (y2.C1097c) r8
            Y1.C0110h.P(r24)     // Catch:{ Exception -> 0x0021 }
            r16 = r3
            r17 = r7
            r7 = r24
            goto L_0x00fd
        L_0x004a:
            Y1.C0110h.P(r24)     // Catch:{ Exception -> 0x0021 }
            r3 = r24
            goto L_0x00d7
        L_0x0051:
            Y1.C0110h.P(r24)
            i2.e r3 = com.hawkshaw.library.datalayer.network.twirp.ClientKt.getClient()     // Catch:{ Exception -> 0x0021 }
            java.lang.String r11 = r1.$endpoint     // Catch:{ Exception -> 0x0021 }
            java.lang.Object r12 = r1.$req     // Catch:{ Exception -> 0x0021 }
            boolean r13 = r1.$retry     // Catch:{ Exception -> 0x0021 }
            x2.d r14 = new x2.d     // Catch:{ Exception -> 0x0021 }
            r14.<init>()     // Catch:{ Exception -> 0x0021 }
            com.hawkshaw.library.config.RemoteConfig r15 = com.hawkshaw.library.config.RemoteConfig.INSTANCE     // Catch:{ Exception -> 0x0021 }
            java.lang.String r15 = r15.getBaseUrl()     // Catch:{ Exception -> 0x0021 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0021 }
            r5.<init>()     // Catch:{ Exception -> 0x0021 }
            r5.append(r15)     // Catch:{ Exception -> 0x0021 }
            java.lang.String r15 = "/twirp/"
            r5.append(r15)     // Catch:{ Exception -> 0x0021 }
            r5.append(r11)     // Catch:{ Exception -> 0x0021 }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x0021 }
            X1.B.A(r14, r5)     // Catch:{ Exception -> 0x0021 }
            java.lang.Class<com.hawkshaw.library.datalayer.models.AppLogsRequest> r5 = com.hawkshaw.library.datalayer.models.AppLogsRequest.class
            if (r12 != 0) goto L_0x009d
            C2.c r11 = C2.c.f266a     // Catch:{ Exception -> 0x0021 }
            r14.f9807d = r11     // Catch:{ Exception -> 0x0021 }
            j3.v r11 = j3.t.b(r5)     // Catch:{ Exception -> 0x0021 }
            java.lang.reflect.Type r12 = p3.q.Q(r11, r4)     // Catch:{ Exception -> 0x0021 }
            j3.d r5 = j3.t.a(r5)     // Catch:{ Exception -> 0x0021 }
            M2.a r15 = new M2.a     // Catch:{ Exception -> 0x0021 }
            r15.<init>(r12, r5, r11)     // Catch:{ Exception -> 0x0021 }
            r14.b(r15)     // Catch:{ Exception -> 0x0021 }
            goto L_0x00bd
        L_0x009d:
            boolean r11 = r12 instanceof C2.g     // Catch:{ Exception -> 0x0021 }
            if (r11 == 0) goto L_0x00a7
            r14.f9807d = r12     // Catch:{ Exception -> 0x0021 }
            r14.b(r9)     // Catch:{ Exception -> 0x0021 }
            goto L_0x00bd
        L_0x00a7:
            r14.f9807d = r12     // Catch:{ Exception -> 0x0021 }
            j3.v r11 = j3.t.b(r5)     // Catch:{ Exception -> 0x0021 }
            java.lang.reflect.Type r12 = p3.q.Q(r11, r4)     // Catch:{ Exception -> 0x0021 }
            j3.d r5 = j3.t.a(r5)     // Catch:{ Exception -> 0x0021 }
            M2.a r15 = new M2.a     // Catch:{ Exception -> 0x0021 }
            r15.<init>(r12, r5, r11)     // Catch:{ Exception -> 0x0021 }
            r14.b(r15)     // Catch:{ Exception -> 0x0021 }
        L_0x00bd:
            if (r13 == 0) goto L_0x00c4
            com.hawkshaw.library.datalayer.network.twirp.ApiCallKt$apiCall$2$1$1 r5 = com.hawkshaw.library.datalayer.network.twirp.ApiCallKt$apiCall$2$1$1.INSTANCE     // Catch:{ Exception -> 0x0021 }
            p2.Y.a(r14, r5)     // Catch:{ Exception -> 0x0021 }
        L_0x00c4:
            A2.v r5 = A2.v.f104c     // Catch:{ Exception -> 0x0021 }
            r14.c(r5)     // Catch:{ Exception -> 0x0021 }
            y2.k r5 = new y2.k     // Catch:{ Exception -> 0x0021 }
            r5.<init>(r14, r3)     // Catch:{ Exception -> 0x0021 }
            r1.label = r8     // Catch:{ Exception -> 0x0021 }
            java.lang.Object r3 = r5.b(r1)     // Catch:{ Exception -> 0x0021 }
            if (r3 != r2) goto L_0x00d7
            return r2
        L_0x00d7:
            r8 = r3
            y2.c r8 = (y2.C1097c) r8     // Catch:{ Exception -> 0x0021 }
            A2.x r3 = r8.g()     // Catch:{ Exception -> 0x0021 }
            boolean r3 = Y1.C0110h.v(r3)     // Catch:{ Exception -> 0x0021 }
            if (r3 != 0) goto L_0x0114
            com.hawkshaw.library.logger.Logger r3 = com.hawkshaw.library.logger.Logger.INSTANCE     // Catch:{ Exception -> 0x0021 }
            java.lang.String r5 = "ApiCall"
            r1.L$0 = r8     // Catch:{ Exception -> 0x0021 }
            r1.L$1 = r5     // Catch:{ Exception -> 0x0021 }
            r1.L$2 = r3     // Catch:{ Exception -> 0x0021 }
            r1.label = r7     // Catch:{ Exception -> 0x0021 }
            java.nio.charset.Charset r7 = r3.C0931a.f8930a     // Catch:{ Exception -> 0x0021 }
            java.lang.Object r7 = Y1.K.h(r8, r7, r1)     // Catch:{ Exception -> 0x0021 }
            if (r7 != r2) goto L_0x00f9
            return r2
        L_0x00f9:
            r16 = r3
            r17 = r5
        L_0x00fd:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0021 }
            r3.<init>(r10)     // Catch:{ Exception -> 0x0021 }
            r3.append(r7)     // Catch:{ Exception -> 0x0021 }
            java.lang.String r18 = r3.toString()     // Catch:{ Exception -> 0x0021 }
            r19 = 0
            r20 = 0
            r21 = 12
            r22 = 0
            com.hawkshaw.library.logger.Logger.e$default(r16, r17, r18, r19, r20, r21, r22)     // Catch:{ Exception -> 0x0021 }
        L_0x0114:
            A2.x r3 = r8.g()     // Catch:{ Exception -> 0x0021 }
            boolean r3 = Y1.C0110h.v(r3)     // Catch:{ Exception -> 0x0021 }
            java.lang.Class<com.hawkshaw.library.datalayer.models.AppLogsResponse> r5 = com.hawkshaw.library.datalayer.models.AppLogsResponse.class
            if (r3 == 0) goto L_0x0156
            j2.c r0 = r8.b()     // Catch:{ Exception -> 0x0021 }
            j3.v r3 = j3.t.b(r5)     // Catch:{ Exception -> 0x0021 }
            java.lang.reflect.Type r4 = p3.q.Q(r3, r4)     // Catch:{ Exception -> 0x0021 }
            j3.d r5 = j3.t.a(r5)     // Catch:{ Exception -> 0x0021 }
            M2.a r7 = new M2.a     // Catch:{ Exception -> 0x0021 }
            r7.<init>(r4, r5, r3)     // Catch:{ Exception -> 0x0021 }
            r1.L$0 = r9     // Catch:{ Exception -> 0x0021 }
            r1.L$1 = r9     // Catch:{ Exception -> 0x0021 }
            r1.L$2 = r9     // Catch:{ Exception -> 0x0021 }
            r1.label = r6     // Catch:{ Exception -> 0x0021 }
            java.lang.Object r0 = r0.a(r7, r1)     // Catch:{ Exception -> 0x0021 }
            if (r0 != r2) goto L_0x0144
            return r2
        L_0x0144:
            if (r0 == 0) goto L_0x014e
            com.hawkshaw.library.datalayer.models.AppLogsResponse r0 = (com.hawkshaw.library.datalayer.models.AppLogsResponse) r0     // Catch:{ Exception -> 0x0021 }
            com.hawkshaw.library.datalayer.network.twirp.ApiResponse$Success r2 = new com.hawkshaw.library.datalayer.network.twirp.ApiResponse$Success     // Catch:{ Exception -> 0x0021 }
            r2.<init>(r0)     // Catch:{ Exception -> 0x0021 }
            goto L_0x01b9
        L_0x014e:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException     // Catch:{ Exception -> 0x0021 }
            java.lang.String r2 = "null cannot be cast to non-null type com.hawkshaw.library.datalayer.models.AppLogsResponse"
            r0.<init>(r2)     // Catch:{ Exception -> 0x0021 }
            throw r0     // Catch:{ Exception -> 0x0021 }
        L_0x0156:
            j2.c r3 = r8.b()     // Catch:{ Exception -> 0x0021 }
            int r6 = p3.C0870k.f8500c     // Catch:{ Exception -> 0x0021 }
            j3.v r5 = j3.t.b(r5)     // Catch:{ Exception -> 0x0021 }
            p3.k r5 = H2.w.m(r5)     // Catch:{ Exception -> 0x0021 }
            j3.v r5 = j3.t.c(r5)     // Catch:{ Exception -> 0x0021 }
            java.lang.reflect.Type r4 = p3.q.Q(r5, r4)     // Catch:{ Exception -> 0x0021 }
            j3.d r0 = j3.t.a(r0)     // Catch:{ Exception -> 0x0021 }
            M2.a r6 = new M2.a     // Catch:{ Exception -> 0x0021 }
            r6.<init>(r4, r0, r5)     // Catch:{ Exception -> 0x0021 }
            r1.L$0 = r9     // Catch:{ Exception -> 0x0021 }
            r1.L$1 = r9     // Catch:{ Exception -> 0x0021 }
            r1.L$2 = r9     // Catch:{ Exception -> 0x0021 }
            r0 = 4
            r1.label = r0     // Catch:{ Exception -> 0x0021 }
            java.lang.Object r0 = r3.a(r6, r1)     // Catch:{ Exception -> 0x0021 }
            if (r0 != r2) goto L_0x0185
            return r2
        L_0x0185:
            if (r0 == 0) goto L_0x018b
            r2 = r0
            com.hawkshaw.library.datalayer.network.twirp.ApiResponse$Error r2 = (com.hawkshaw.library.datalayer.network.twirp.ApiResponse.Error) r2     // Catch:{ Exception -> 0x0021 }
            goto L_0x01b9
        L_0x018b:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException     // Catch:{ Exception -> 0x0021 }
            java.lang.String r2 = "null cannot be cast to non-null type com.hawkshaw.library.datalayer.network.twirp.ApiResponse.Error<T>"
            r0.<init>(r2)     // Catch:{ Exception -> 0x0021 }
            throw r0     // Catch:{ Exception -> 0x0021 }
        L_0x0193:
            com.hawkshaw.library.logger.Logger r2 = com.hawkshaw.library.logger.Logger.INSTANCE
            java.lang.String r3 = r0.getMessage()
            java.lang.String r4 = r1.$endpoint
            java.lang.String r5 = ", Endpoint: "
            java.lang.String r4 = i.C0528x.e(r10, r3, r5, r4)
            java.lang.String r3 = "ApiCall"
            r6 = 0
            r7 = 8
            r8 = 0
            r5 = r0
            com.hawkshaw.library.logger.Logger.e$default(r2, r3, r4, r5, r6, r7, r8)
            com.hawkshaw.library.datalayer.network.twirp.ApiResponse$Error r2 = new com.hawkshaw.library.datalayer.network.twirp.ApiResponse$Error
            java.lang.String r0 = r0.getMessage()
            java.lang.String r3 = ""
            if (r0 != 0) goto L_0x01b6
            r0 = r3
        L_0x01b6:
            r2.<init>(r0, r3, r9)
        L_0x01b9:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.network.service.LogsServiceImpl$pushAppLogs$$inlined$apiCall$default$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    public final Object invoke(E e5, e eVar) {
        return ((LogsServiceImpl$pushAppLogs$$inlined$apiCall$default$1) create(e5, eVar)).invokeSuspend(y.f2418a);
    }
}
