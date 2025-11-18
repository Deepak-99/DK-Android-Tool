package com.hawkshaw.library.datalayer.network.grpc.interceptors;

import Y1.C0112i;
import Y1.F0;
import Y1.H;
import Y1.K;
import Y1.m0;
import Y1.p0;
import androidx.core.app.NotificationCompat;

public final class LoggingInterceptor$interceptCall$1$start$1 extends H {
    final /* synthetic */ p0 $method;
    final /* synthetic */ LoggingInterceptor this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LoggingInterceptor$interceptCall$1$start$1(C0112i iVar, LoggingInterceptor loggingInterceptor, p0 p0Var) {
        super(iVar);
        this.this$0 = loggingInterceptor;
        this.$method = p0Var;
    }

    public void onClose(F0 f02, m0 m0Var) {
        K.n(f02, NotificationCompat.CATEGORY_STATUS);
        this.this$0.logStatus(f02, this.$method);
        delegate().onClose(f02, m0Var);
    }

    public void onHeaders(m0 m0Var) {
        K.n(m0Var, "headers");
        this.this$0.logHeaders("Response", m0Var);
        delegate().onHeaders(m0Var);
    }

    public void onMessage(RespT respt) {
        this.this$0.logMessage("Response", respt);
        delegate().onMessage(respt);
    }
}
