package com.hawkshaw.library.datalayer.network.twirp;

import O.c;
import Y1.K;
import a3.h;
import i2.e;
import i2.g;
import i2.i;
import l2.C0701d;
import l2.C0706i;
import t3.C0984e0;
import t3.f0;

public final class ClientKt {
    private static final e client;

    static {
        a aVar = a.f4938F;
        C0706i iVar = i.f6105a;
        K.n(iVar, "engineFactory");
        g gVar = new g();
        aVar.invoke(gVar);
        C0701d a5 = iVar.a(gVar.f6100d);
        e eVar = new e(a5, gVar);
        h hVar = eVar.f6088G.get(C0984e0.f9322D);
        K.k(hVar);
        ((f0) hVar).Z(new c(a5, 6));
        client = eVar;
    }

    public static final e getClient() {
        return client;
    }
}
