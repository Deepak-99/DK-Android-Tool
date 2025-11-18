package com.hawkshaw.library.datalayer.network.twirp.interceptors;

import A1.c;
import A1.f;
import Y1.K;
import i2.g;
import p2.Q;
import p2.X;

public final class RetryInterceptorKt {
    public static final void installRetryInterceptor(g gVar) {
        K.n(gVar, "<this>");
        gVar.a(X.f8385g, c.f22K);
    }

    public static final void retryConfig(Q q4) {
        K.n(q4, "<this>");
        q4.f8367f = 3;
        Q.a(q4);
        q4.f8362a = A1.g.f32E;
        q4.f8363b = A1.g.f33F;
        q4.f8365d = f.f30F;
    }
}
