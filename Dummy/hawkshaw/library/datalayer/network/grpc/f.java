package com.hawkshaw.library.datalayer.network.grpc;

import c3.C0325c;
import com.hawkshaw.library.datalayer.network.grpc.GrpcResponse;
import i3.p;

public final class f extends C0325c {

    /* renamed from: D  reason: collision with root package name */
    public GrpcResponse f4920D;

    /* renamed from: E  reason: collision with root package name */
    public /* synthetic */ Object f4921E;

    /* renamed from: F  reason: collision with root package name */
    public int f4922F;

    public final Object invokeSuspend(Object obj) {
        this.f4921E = obj;
        this.f4922F |= Integer.MIN_VALUE;
        return GrpcResponse.DefaultImpls.onSuccess((GrpcResponse) null, (p) null, this);
    }
}
