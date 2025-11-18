package com.hawkshaw.library.datalayer.room;

import E3.o0;
import E3.u0;
import F3.b;
import com.hawkshaw.library.datalayer.network.twirp.interceptors.ContentNegotiationInterceptorKt;
import j3.t;
import r3.j;
import w3.w;

public final class MyTypeConverters {
    public static final MyTypeConverters INSTANCE = new MyTypeConverters();

    private MyTypeConverters() {
    }

    public final String fromArray(String[] strArr) {
        b json = ContentNegotiationInterceptorKt.getJson();
        json.getClass();
        return json.b(w.n(new o0(t.a(String.class), u0.f536a)), strArr);
    }

    public final String[] fromString(String str) {
        if (str == null || j.U(str)) {
            return new String[0];
        }
        try {
            b json = ContentNegotiationInterceptorKt.getJson();
            json.getClass();
            return (String[]) json.a(new o0(t.a(String.class), u0.f536a), str);
        } catch (Exception unused) {
            return new String[0];
        }
    }
}
