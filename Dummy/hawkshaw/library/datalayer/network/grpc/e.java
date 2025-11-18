package com.hawkshaw.library.datalayer.network.grpc;

import c3.C0325c;
import com.hawkshaw.library.datalayer.network.grpc.GrpcResponse;
import i3.p;

public final class e extends C0325c {

    /* renamed from: D  reason: collision with root package name */
    public GrpcResponse f4917D;

    /* renamed from: E  reason: collision with root package name */
    public /* synthetic */ Object f4918E;

    /* renamed from: F  reason: collision with root package name */
    public int f4919F;

    public final Object invokeSuspend(Object obj) {
        this.f4918E = obj;
        this.f4919F |= Integer.MIN_VALUE;
        return GrpcResponse.DefaultImpls.onError((GrpcResponse) null, (p) null, this);
    }
}
