package com.hawkshaw.library.datalayer.network.grpc;

import W2.y;
import a3.e;
import b3.C0298a;
import com.hawkshaw.library.datalayer.network.grpc.GrpcResponse;

public final class c implements w3.c {

    /* renamed from: D  reason: collision with root package name */
    public final /* synthetic */ w3.c f4912D;

    public c(w3.c cVar) {
        this.f4912D = cVar;
    }

    public final Object emit(Object obj, e eVar) {
        Object emit = this.f4912D.emit(new GrpcResponse.Success(obj), eVar);
        return emit == C0298a.f4140D ? emit : y.f2418a;
    }
}
