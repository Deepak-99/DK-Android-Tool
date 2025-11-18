package com.hawkshaw.library.datalayer.network.grpc.interceptors;

import Y1.C0106f;
import Y1.C0108g;
import Y1.C0111h0;
import Y1.C0114j;
import Y1.C0115j0;
import Y1.C0116k;
import Y1.m0;
import Y1.p0;
import com.hawkshaw.library.BuildConfig;
import d1.e;
import java.util.BitSet;

public final class AuthInterceptor implements C0116k {
    /* access modifiers changed from: private */
    public final m0 baseHeaders;
    /* access modifiers changed from: private */
    public final String tokenScheme = "Bearer";

    /* JADX WARNING: type inference failed for: r0v1, types: [Y1.m0, java.lang.Object] */
    public AuthInterceptor() {
        ? obj = new Object();
        e eVar = m0.f2789d;
        BitSet bitSet = C0115j0.f2781d;
        obj.e(new C0111h0("x-version-code", eVar), "109");
        obj.e(new C0111h0("x-version-name", eVar), BuildConfig.VERSION_NAME);
        this.baseHeaders = obj;
    }

    public <ReqT, RespT> C0114j interceptCall(p0 p0Var, C0106f fVar, C0108g gVar) {
        return new AuthInterceptor$interceptCall$1(this, gVar != null ? gVar.h(p0Var, fVar) : null);
    }
}
