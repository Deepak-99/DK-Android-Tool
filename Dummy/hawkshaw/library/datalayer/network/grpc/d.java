package com.hawkshaw.library.datalayer.network.grpc;

import W2.y;
import a3.e;
import c3.C0331i;
import i3.l;
import i3.p;
import w3.c;

public final class d extends C0331i implements p {

    /* renamed from: D  reason: collision with root package name */
    public int f4913D;

    /* renamed from: E  reason: collision with root package name */
    public /* synthetic */ Object f4914E;

    /* renamed from: F  reason: collision with root package name */
    public final /* synthetic */ boolean f4915F;

    /* renamed from: G  reason: collision with root package name */
    public final /* synthetic */ l f4916G;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public d(boolean z4, l lVar, e eVar) {
        super(2, eVar);
        this.f4915F = z4;
        this.f4916G = lVar;
    }

    public final e create(Object obj, e eVar) {
        d dVar = new d(this.f4915F, this.f4916G, eVar);
        dVar.f4914E = obj;
        return dVar;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((d) create((c) obj, (e) obj2)).invokeSuspend(y.f2418a);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: w3.c} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x007b A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            b3.a r0 = b3.C0298a.f4140D
            int r1 = r8.f4913D
            r2 = 4
            r3 = 3
            r4 = 1
            r5 = 2
            r6 = 0
            if (r1 == 0) goto L_0x003a
            if (r1 == r4) goto L_0x0032
            if (r1 == r5) goto L_0x002a
            if (r1 == r3) goto L_0x0020
            if (r1 != r2) goto L_0x0018
            Y1.C0110h.P(r9)
            goto L_0x0092
        L_0x0018:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L_0x0020:
            java.lang.Object r1 = r8.f4914E
            w3.c r1 = (w3.c) r1
            Y1.C0110h.P(r9)     // Catch:{ all -> 0x0028 }
            goto L_0x0092
        L_0x0028:
            r9 = move-exception
            goto L_0x007c
        L_0x002a:
            java.lang.Object r1 = r8.f4914E
            w3.c r1 = (w3.c) r1
            Y1.C0110h.P(r9)     // Catch:{ all -> 0x0028 }
            goto L_0x0060
        L_0x0032:
            java.lang.Object r1 = r8.f4914E
            w3.c r1 = (w3.c) r1
            Y1.C0110h.P(r9)
            goto L_0x0053
        L_0x003a:
            Y1.C0110h.P(r9)
            java.lang.Object r9 = r8.f4914E
            r1 = r9
            w3.c r1 = (w3.c) r1
            boolean r9 = r8.f4915F
            if (r9 == 0) goto L_0x0053
            com.hawkshaw.library.datalayer.network.grpc.GrpcResponse$InProgress r9 = com.hawkshaw.library.datalayer.network.grpc.GrpcResponse.InProgress.INSTANCE
            r8.f4914E = r1
            r8.f4913D = r4
            java.lang.Object r9 = r1.emit(r9, r8)
            if (r9 != r0) goto L_0x0053
            return r0
        L_0x0053:
            i3.l r9 = r8.f4916G     // Catch:{ all -> 0x0028 }
            r8.f4914E = r1     // Catch:{ all -> 0x0028 }
            r8.f4913D = r5     // Catch:{ all -> 0x0028 }
            java.lang.Object r9 = r9.invoke(r8)     // Catch:{ all -> 0x0028 }
            if (r9 != r0) goto L_0x0060
            return r0
        L_0x0060:
            w3.b r9 = (w3.C1066b) r9     // Catch:{ all -> 0x0028 }
            com.hawkshaw.library.datalayer.network.grpc.b r4 = new com.hawkshaw.library.datalayer.network.grpc.b     // Catch:{ all -> 0x0028 }
            r4.<init>(r1, r6)     // Catch:{ all -> 0x0028 }
            w3.h r7 = new w3.h     // Catch:{ all -> 0x0028 }
            r7.<init>(r9, r4)     // Catch:{ all -> 0x0028 }
            com.hawkshaw.library.datalayer.network.grpc.c r9 = new com.hawkshaw.library.datalayer.network.grpc.c     // Catch:{ all -> 0x0028 }
            r9.<init>(r1)     // Catch:{ all -> 0x0028 }
            r8.f4914E = r1     // Catch:{ all -> 0x0028 }
            r8.f4913D = r3     // Catch:{ all -> 0x0028 }
            java.lang.Object r9 = r7.collect(r9, r8)     // Catch:{ all -> 0x0028 }
            if (r9 != r0) goto L_0x0092
            return r0
        L_0x007c:
            com.hawkshaw.library.datalayer.network.grpc.GrpcResponse$Error r3 = new com.hawkshaw.library.datalayer.network.grpc.GrpcResponse$Error
            com.hawkshaw.library.datalayer.network.grpc.GrpcError r4 = new com.hawkshaw.library.datalayer.network.grpc.GrpcError
            r4.<init>(r9, r6, r5, r6)
            r9 = 0
            r3.<init>(r4, r9, r5, r6)
            r8.f4914E = r6
            r8.f4913D = r2
            java.lang.Object r9 = r1.emit(r3, r8)
            if (r9 != r0) goto L_0x0092
            return r0
        L_0x0092:
            W2.y r9 = W2.y.f2418a
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.network.grpc.d.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
