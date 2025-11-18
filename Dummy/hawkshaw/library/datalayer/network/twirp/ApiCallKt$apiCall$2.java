package com.hawkshaw.library.datalayer.network.twirp;

import W2.y;
import Y1.K;
import a3.e;
import c3.C0327e;
import c3.C0331i;
import i3.p;
import t3.E;

@C0327e(c = "com.hawkshaw.library.datalayer.network.twirp.ApiCallKt$apiCall$2", f = "ApiCall.kt", l = {61, 62, 64, 64}, m = "invokeSuspend")
public final class ApiCallKt$apiCall$2 extends C0331i implements p {
    final /* synthetic */ String $endpoint;
    final /* synthetic */ S $req;
    final /* synthetic */ boolean $retry;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ApiCallKt$apiCall$2(String str, S s4, boolean z4, e eVar) {
        super(2, eVar);
        this.$endpoint = str;
        this.$req = s4;
        this.$retry = z4;
    }

    public final e create(Object obj, e eVar) {
        K.A0();
        throw null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:46:0x00e8 A[Catch:{ Exception -> 0x001e }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00ec A[Catch:{ Exception -> 0x001e }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r15) {
        /*
            r14 = this;
            b3.a r0 = b3.C0298a.f4140D
            int r1 = r14.label
            r2 = 0
            r3 = 2
            r4 = 1
            java.lang.String r5 = "ApiCallError: "
            if (r1 == 0) goto L_0x004f
            if (r1 == r4) goto L_0x004b
            if (r1 == r3) goto L_0x0038
            r0 = 3
            if (r1 == r0) goto L_0x0031
            r0 = 4
            if (r1 != r0) goto L_0x0029
            Y1.C0110h.P(r15)     // Catch:{ Exception -> 0x001e }
            if (r15 == 0) goto L_0x0021
            com.hawkshaw.library.datalayer.network.twirp.ApiResponse$Error r15 = (com.hawkshaw.library.datalayer.network.twirp.ApiResponse.Error) r15     // Catch:{ Exception -> 0x001e }
            goto L_0x0127
        L_0x001e:
            r15 = move-exception
            goto L_0x0100
        L_0x0021:
            java.lang.NullPointerException r15 = new java.lang.NullPointerException     // Catch:{ Exception -> 0x001e }
            java.lang.String r0 = "null cannot be cast to non-null type com.hawkshaw.library.datalayer.network.twirp.ApiResponse.Error<T>"
            r15.<init>(r0)     // Catch:{ Exception -> 0x001e }
            throw r15     // Catch:{ Exception -> 0x001e }
        L_0x0029:
            java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r15.<init>(r0)
            throw r15
        L_0x0031:
            Y1.C0110h.P(r15)     // Catch:{ Exception -> 0x001e }
            Y1.K.A0()     // Catch:{ Exception -> 0x001e }
            throw r2     // Catch:{ Exception -> 0x001e }
        L_0x0038:
            java.lang.Object r0 = r14.L$2
            com.hawkshaw.library.logger.Logger r0 = (com.hawkshaw.library.logger.Logger) r0
            java.lang.Object r1 = r14.L$1
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r3 = r14.L$0
            y2.c r3 = (y2.C1097c) r3
            Y1.C0110h.P(r15)     // Catch:{ Exception -> 0x001e }
            r6 = r0
            r7 = r1
            goto L_0x00c9
        L_0x004b:
            Y1.C0110h.P(r15)     // Catch:{ Exception -> 0x001e }
            goto L_0x00a3
        L_0x004f:
            Y1.C0110h.P(r15)
            i2.e r15 = com.hawkshaw.library.datalayer.network.twirp.ClientKt.getClient()     // Catch:{ Exception -> 0x001e }
            java.lang.String r1 = r14.$endpoint     // Catch:{ Exception -> 0x001e }
            S r6 = r14.$req     // Catch:{ Exception -> 0x001e }
            boolean r7 = r14.$retry     // Catch:{ Exception -> 0x001e }
            x2.d r8 = new x2.d     // Catch:{ Exception -> 0x001e }
            r8.<init>()     // Catch:{ Exception -> 0x001e }
            com.hawkshaw.library.config.RemoteConfig r9 = com.hawkshaw.library.config.RemoteConfig.INSTANCE     // Catch:{ Exception -> 0x001e }
            java.lang.String r9 = r9.getBaseUrl()     // Catch:{ Exception -> 0x001e }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x001e }
            r10.<init>()     // Catch:{ Exception -> 0x001e }
            r10.append(r9)     // Catch:{ Exception -> 0x001e }
            java.lang.String r9 = "/twirp/"
            r10.append(r9)     // Catch:{ Exception -> 0x001e }
            r10.append(r1)     // Catch:{ Exception -> 0x001e }
            java.lang.String r1 = r10.toString()     // Catch:{ Exception -> 0x001e }
            X1.B.A(r8, r1)     // Catch:{ Exception -> 0x001e }
            if (r6 == 0) goto L_0x00f8
            boolean r1 = r6 instanceof C2.g     // Catch:{ Exception -> 0x001e }
            if (r1 == 0) goto L_0x00f2
            r8.f9807d = r6     // Catch:{ Exception -> 0x001e }
            r8.b(r2)     // Catch:{ Exception -> 0x001e }
            if (r7 == 0) goto L_0x0090
            com.hawkshaw.library.datalayer.network.twirp.ApiCallKt$apiCall$2$1$1 r1 = com.hawkshaw.library.datalayer.network.twirp.ApiCallKt$apiCall$2$1$1.INSTANCE     // Catch:{ Exception -> 0x001e }
            p2.Y.a(r8, r1)     // Catch:{ Exception -> 0x001e }
        L_0x0090:
            A2.v r1 = A2.v.f104c     // Catch:{ Exception -> 0x001e }
            r8.c(r1)     // Catch:{ Exception -> 0x001e }
            y2.k r1 = new y2.k     // Catch:{ Exception -> 0x001e }
            r1.<init>(r8, r15)     // Catch:{ Exception -> 0x001e }
            r14.label = r4     // Catch:{ Exception -> 0x001e }
            java.lang.Object r15 = r1.b(r14)     // Catch:{ Exception -> 0x001e }
            if (r15 != r0) goto L_0x00a3
            return r0
        L_0x00a3:
            y2.c r15 = (y2.C1097c) r15     // Catch:{ Exception -> 0x001e }
            A2.x r1 = r15.g()     // Catch:{ Exception -> 0x001e }
            boolean r1 = Y1.C0110h.v(r1)     // Catch:{ Exception -> 0x001e }
            if (r1 != 0) goto L_0x00de
            com.hawkshaw.library.logger.Logger r1 = com.hawkshaw.library.logger.Logger.INSTANCE     // Catch:{ Exception -> 0x001e }
            java.lang.String r4 = "ApiCall"
            r14.L$0 = r15     // Catch:{ Exception -> 0x001e }
            r14.L$1 = r4     // Catch:{ Exception -> 0x001e }
            r14.L$2 = r1     // Catch:{ Exception -> 0x001e }
            r14.label = r3     // Catch:{ Exception -> 0x001e }
            java.nio.charset.Charset r3 = r3.C0931a.f8930a     // Catch:{ Exception -> 0x001e }
            java.lang.Object r3 = Y1.K.h(r15, r3, r14)     // Catch:{ Exception -> 0x001e }
            if (r3 != r0) goto L_0x00c4
            return r0
        L_0x00c4:
            r6 = r1
            r7 = r4
            r13 = r3
            r3 = r15
            r15 = r13
        L_0x00c9:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x001e }
            r0.<init>(r5)     // Catch:{ Exception -> 0x001e }
            r0.append(r15)     // Catch:{ Exception -> 0x001e }
            java.lang.String r8 = r0.toString()     // Catch:{ Exception -> 0x001e }
            r9 = 0
            r10 = 0
            r11 = 12
            r12 = 0
            com.hawkshaw.library.logger.Logger.e$default(r6, r7, r8, r9, r10, r11, r12)     // Catch:{ Exception -> 0x001e }
            r15 = r3
        L_0x00de:
            A2.x r15 = r15.g()     // Catch:{ Exception -> 0x001e }
            boolean r15 = Y1.C0110h.v(r15)     // Catch:{ Exception -> 0x001e }
            if (r15 == 0) goto L_0x00ec
            Y1.K.A0()     // Catch:{ Exception -> 0x001e }
            throw r2     // Catch:{ Exception -> 0x001e }
        L_0x00ec:
            int r15 = p3.C0870k.f8500c     // Catch:{ Exception -> 0x001e }
            Y1.K.A0()     // Catch:{ Exception -> 0x001e }
            throw r2     // Catch:{ Exception -> 0x001e }
        L_0x00f2:
            r8.f9807d = r6     // Catch:{ Exception -> 0x001e }
            Y1.K.A0()     // Catch:{ Exception -> 0x001e }
            throw r2     // Catch:{ Exception -> 0x001e }
        L_0x00f8:
            C2.c r15 = C2.c.f266a     // Catch:{ Exception -> 0x001e }
            r8.f9807d = r15     // Catch:{ Exception -> 0x001e }
            Y1.K.A0()     // Catch:{ Exception -> 0x001e }
            throw r2     // Catch:{ Exception -> 0x001e }
        L_0x0100:
            com.hawkshaw.library.logger.Logger r6 = com.hawkshaw.library.logger.Logger.INSTANCE
            java.lang.String r0 = r15.getMessage()
            java.lang.String r1 = r14.$endpoint
            java.lang.String r3 = ", Endpoint: "
            java.lang.String r8 = i.C0528x.e(r5, r0, r3, r1)
            java.lang.String r7 = "ApiCall"
            r10 = 0
            r11 = 8
            r12 = 0
            r9 = r15
            com.hawkshaw.library.logger.Logger.e$default(r6, r7, r8, r9, r10, r11, r12)
            com.hawkshaw.library.datalayer.network.twirp.ApiResponse$Error r0 = new com.hawkshaw.library.datalayer.network.twirp.ApiResponse$Error
            java.lang.String r15 = r15.getMessage()
            java.lang.String r1 = ""
            if (r15 != 0) goto L_0x0123
            r15 = r1
        L_0x0123:
            r0.<init>(r15, r1, r2)
            r15 = r0
        L_0x0127:
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.network.twirp.ApiCallKt$apiCall$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    public final Object invoke(E e5, e eVar) {
        return ((ApiCallKt$apiCall$2) create(e5, eVar)).invokeSuspend(y.f2418a);
    }
}
