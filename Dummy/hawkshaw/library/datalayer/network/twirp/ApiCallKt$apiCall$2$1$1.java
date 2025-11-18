package com.hawkshaw.library.datalayer.network.twirp;

import W2.y;
import Y1.K;
import com.hawkshaw.library.datalayer.network.twirp.interceptors.RetryInterceptorKt;
import i3.l;
import j3.j;
import p2.Q;

public final class ApiCallKt$apiCall$2$1$1 extends j implements l {
    public static final ApiCallKt$apiCall$2$1$1 INSTANCE = new ApiCallKt$apiCall$2$1$1();

    public ApiCallKt$apiCall$2$1$1() {
        super(1);
    }

    public final void invoke(Q q4) {
        K.n(q4, "$this$retry");
        RetryInterceptorKt.retryConfig(q4);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Q) obj);
        return y.f2418a;
    }
}
