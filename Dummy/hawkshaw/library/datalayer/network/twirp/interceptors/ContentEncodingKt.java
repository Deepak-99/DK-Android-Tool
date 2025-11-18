package com.hawkshaw.library.datalayer.network.twirp.interceptors;

import Y1.K;
import i2.b;
import i2.g;
import j4.a;
import q2.C0916b;
import q2.f;
import s2.C0947c;
import s2.d;
import s2.e;

public final class ContentEncodingKt {
    private static final C0916b CustomContentEncoding = new f();

    public static final void installCustomContentEncoding(g gVar) {
        K.n(gVar, "<this>");
        a aVar = e.f9093a;
        gVar.a(C0947c.f9087d, d.f9092D);
        gVar.a(CustomContentEncoding, b.f6075G);
    }
}
