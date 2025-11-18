package com.hawkshaw.library.datalayer.network.grpc.interceptors;

import Y1.C0112i;
import Y1.C0114j;
import Y1.F;
import Y1.K;
import Y1.m0;
import Y1.p0;

public final class LoggingInterceptor$interceptCall$1 extends F {
    final /* synthetic */ p0 $method;
    final /* synthetic */ LoggingInterceptor this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LoggingInterceptor$interceptCall$1(LoggingInterceptor loggingInterceptor, p0 p0Var, C0114j jVar) {
        super(jVar);
        this.this$0 = loggingInterceptor;
        this.$method = p0Var;
    }

    public void sendMessage(ReqT reqt) {
        this.this$0.logMessage("Request", reqt);
        super.sendMessage(reqt);
    }

    public void start(C0112i iVar, m0 m0Var) {
        K.n(m0Var, "headers");
        this.this$0.logMethod(this.$method);
        this.this$0.logHeaders("Request", m0Var);
        delegate().start(new LoggingInterceptor$interceptCall$1$start$1(iVar, this.this$0, this.$method), m0Var);
    }
}
