package com.hawkshaw.library.datalayer.network.grpc.interceptors;

import Y1.C0111h0;
import Y1.C0112i;
import Y1.C0114j;
import Y1.C0115j0;
import Y1.F;
import Y1.m0;
import com.hawkshaw.library.preferences.Prefs;
import d1.e;
import java.util.BitSet;
import me.pushy.sdk.lib.jackson.core.util.MinimalPrettyPrinter;

public final class AuthInterceptor$interceptCall$1 extends F {
    final /* synthetic */ AuthInterceptor this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AuthInterceptor$interceptCall$1(AuthInterceptor authInterceptor, C0114j jVar) {
        super(jVar);
        this.this$0 = authInterceptor;
    }

    public void start(C0112i iVar, m0 m0Var) {
        if (m0Var != null) {
            m0Var.d(this.this$0.baseHeaders);
        }
        if (m0Var != null) {
            e eVar = m0.f2789d;
            BitSet bitSet = C0115j0.f2781d;
            C0111h0 h0Var = new C0111h0("Authorization", eVar);
            String access$getTokenScheme$p = this.this$0.tokenScheme;
            String token = Prefs.INSTANCE.getToken();
            m0Var.e(h0Var, access$getTokenScheme$p + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + token);
        }
        delegate().start(iVar, m0Var);
    }
}
