package com.hawkshaw.library.datalayer.network.twirp.interceptors;

import A1.c;
import Y1.K;
import com.hawkshaw.library.preferences.Prefs;
import i2.g;
import io.ktor.client.plugins.auth.h;
import r2.l;
import r3.j;

public final class AuthInterceptorKt {
    public static final void installAuthInterceptor(g gVar) {
        K.n(gVar, "<this>");
        gVar.a(h.f6153b, c.f17F);
    }

    /* access modifiers changed from: private */
    public static final l installAuthInterceptor$getTokens() {
        Prefs prefs = Prefs.INSTANCE;
        String token = prefs.getToken();
        if (token == null || j.U(token)) {
            return null;
        }
        String refreshToken = prefs.getRefreshToken();
        if (refreshToken == null) {
            refreshToken = token;
        }
        return new l(token, refreshToken);
    }
}
