package com.hawkshaw.library.datalayer.network.grpc;

import W2.y;
import a3.e;
import c3.C0331i;
import i3.l;
import i3.p;
import w3.c;

public final class a extends C0331i implements p {

    /* renamed from: D  reason: collision with root package name */
    public int f4906D;

    /* renamed from: E  reason: collision with root package name */
    public /* synthetic */ Object f4907E;

    /* renamed from: F  reason: collision with root package name */
    public final /* synthetic */ l f4908F;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public a(l lVar, e eVar) {
        super(2, eVar);
        this.f4908F = lVar;
    }

    public final e create(Object obj, e eVar) {
        a aVar = new a(this.f4908F, eVar);
        aVar.f4907E = obj;
        return aVar;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((a) create((c) obj, (e) obj2)).invokeSuspend(y.f2418a);
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x006a A[Catch:{ all -> 0x002d }] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0086 A[Catch:{ all -> 0x002d }] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:11:0x0028=Splitter:B:11:0x0028, B:23:0x0059=Splitter:B:23:0x0059} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            r10 = this;
            b3.a r0 = b3.C0298a.f4140D
            int r1 = r10.f4906D
            r2 = 0
            r3 = 5
            r4 = 4
            r5 = 3
            r6 = 1
            r7 = 2
            r8 = 0
            if (r1 == 0) goto L_0x0044
            if (r1 == r6) goto L_0x003c
            if (r1 == r7) goto L_0x0034
            if (r1 == r5) goto L_0x002f
            if (r1 == r4) goto L_0x0024
            if (r1 != r3) goto L_0x001c
            Y1.C0110h.P(r11)
            goto L_0x00ab
        L_0x001c:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L_0x0024:
            java.lang.Object r1 = r10.f4907E
            w3.c r1 = (w3.c) r1
        L_0x0028:
            Y1.C0110h.P(r11)     // Catch:{ all -> 0x002d }
            goto L_0x00ab
        L_0x002d:
            r11 = move-exception
            goto L_0x0096
        L_0x002f:
            java.lang.Object r1 = r10.f4907E
            w3.c r1 = (w3.c) r1
            goto L_0x0028
        L_0x0034:
            java.lang.Object r1 = r10.f4907E
            w3.c r1 = (w3.c) r1
            Y1.C0110h.P(r11)     // Catch:{ all -> 0x002d }
            goto L_0x0066
        L_0x003c:
            java.lang.Object r1 = r10.f4907E
            w3.c r1 = (w3.c) r1
            Y1.C0110h.P(r11)
            goto L_0x0059
        L_0x0044:
            Y1.C0110h.P(r11)
            java.lang.Object r11 = r10.f4907E
            w3.c r11 = (w3.c) r11
            com.hawkshaw.library.datalayer.network.grpc.GrpcResponse$InProgress r1 = com.hawkshaw.library.datalayer.network.grpc.GrpcResponse.InProgress.INSTANCE
            r10.f4907E = r11
            r10.f4906D = r6
            java.lang.Object r1 = r11.emit(r1, r10)
            if (r1 != r0) goto L_0x0058
            return r0
        L_0x0058:
            r1 = r11
        L_0x0059:
            i3.l r11 = r10.f4908F     // Catch:{ all -> 0x002d }
            r10.f4907E = r1     // Catch:{ all -> 0x002d }
            r10.f4906D = r7     // Catch:{ all -> 0x002d }
            java.lang.Object r11 = r11.invoke(r10)     // Catch:{ all -> 0x002d }
            if (r11 != r0) goto L_0x0066
            return r0
        L_0x0066:
            boolean r6 = r11 instanceof w3.C1066b     // Catch:{ all -> 0x002d }
            if (r6 == 0) goto L_0x0086
            com.hawkshaw.library.datalayer.network.grpc.GrpcResponse$Error r11 = new com.hawkshaw.library.datalayer.network.grpc.GrpcResponse$Error     // Catch:{ all -> 0x002d }
            com.hawkshaw.library.datalayer.network.grpc.GrpcError r4 = new com.hawkshaw.library.datalayer.network.grpc.GrpcError     // Catch:{ all -> 0x002d }
            java.lang.Throwable r6 = new java.lang.Throwable     // Catch:{ all -> 0x002d }
            java.lang.String r9 = "You should use specialized grpcCallNestedFlow instead of grpcCallFlow"
            r6.<init>(r9)     // Catch:{ all -> 0x002d }
            r4.<init>(r6, r8, r7, r8)     // Catch:{ all -> 0x002d }
            r11.<init>(r4, r2, r7, r8)     // Catch:{ all -> 0x002d }
            r10.f4907E = r1     // Catch:{ all -> 0x002d }
            r10.f4906D = r5     // Catch:{ all -> 0x002d }
            java.lang.Object r11 = r1.emit(r11, r10)     // Catch:{ all -> 0x002d }
            if (r11 != r0) goto L_0x00ab
            return r0
        L_0x0086:
            com.hawkshaw.library.datalayer.network.grpc.GrpcResponse$Success r5 = new com.hawkshaw.library.datalayer.network.grpc.GrpcResponse$Success     // Catch:{ all -> 0x002d }
            r5.<init>(r11)     // Catch:{ all -> 0x002d }
            r10.f4907E = r1     // Catch:{ all -> 0x002d }
            r10.f4906D = r4     // Catch:{ all -> 0x002d }
            java.lang.Object r11 = r1.emit(r5, r10)     // Catch:{ all -> 0x002d }
            if (r11 != r0) goto L_0x00ab
            return r0
        L_0x0096:
            com.hawkshaw.library.datalayer.network.grpc.GrpcResponse$Error r4 = new com.hawkshaw.library.datalayer.network.grpc.GrpcResponse$Error
            com.hawkshaw.library.datalayer.network.grpc.GrpcError r5 = new com.hawkshaw.library.datalayer.network.grpc.GrpcError
            r5.<init>(r11, r8, r7, r8)
            r4.<init>(r5, r2, r7, r8)
            r10.f4907E = r8
            r10.f4906D = r3
            java.lang.Object r11 = r1.emit(r4, r10)
            if (r11 != r0) goto L_0x00ab
            return r0
        L_0x00ab:
            W2.y r11 = W2.y.f2418a
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.network.grpc.a.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
