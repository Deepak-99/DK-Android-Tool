package com.hawkshaw.library.datalayer.network.grpc;

import W2.y;
import Y1.C0110h;
import a3.e;
import android.util.Log;
import b3.C0298a;
import c3.C0331i;
import com.hawkshaw.library.datalayer.network.grpc.GrpcResponse;
import i3.q;
import j3.f;
import w3.c;

public final class b extends C0331i implements q {

    /* renamed from: D  reason: collision with root package name */
    public int f4909D;

    /* renamed from: E  reason: collision with root package name */
    public /* synthetic */ Throwable f4910E;

    /* renamed from: F  reason: collision with root package name */
    public final /* synthetic */ c f4911F;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public b(c cVar, e eVar) {
        super(3, eVar);
        this.f4911F = cVar;
    }

    public final Object d(Object obj, Object obj2, Object obj3) {
        c cVar = (c) obj;
        b bVar = new b(this.f4911F, (e) obj3);
        bVar.f4910E = (Throwable) obj2;
        return bVar.invokeSuspend(y.f2418a);
    }

    public final Object invokeSuspend(Object obj) {
        C0298a aVar = C0298a.f4140D;
        int i5 = this.f4909D;
        if (i5 == 0) {
            C0110h.P(obj);
            Throwable th = this.f4910E;
            String message = th.getMessage();
            Log.e("grpcCall", "grpcCall: " + message, th);
            GrpcResponse.Error error = new GrpcResponse.Error(new GrpcError(th, (String) null, 2, (f) null), false);
            this.f4909D = 1;
            if (this.f4911F.emit(error, this) == aVar) {
                return aVar;
            }
        } else if (i5 == 1) {
            C0110h.P(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return y.f2418a;
    }
}
