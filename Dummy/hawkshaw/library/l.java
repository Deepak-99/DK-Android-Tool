package com.hawkshaw.library;

import W2.y;
import Y1.C0110h;
import a3.e;
import android.content.DialogInterface;
import b3.C0298a;
import c3.C0331i;
import i3.p;
import t3.E;

public final class l extends C0331i implements p {

    /* renamed from: D  reason: collision with root package name */
    public final /* synthetic */ DialogInterface f5048D;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public l(DialogInterface dialogInterface, e eVar) {
        super(2, eVar);
        this.f5048D = dialogInterface;
    }

    public final e create(Object obj, e eVar) {
        return new l(this.f5048D, eVar);
    }

    public final Object invoke(Object obj, Object obj2) {
        y yVar = y.f2418a;
        ((l) create((E) obj, (e) obj2)).invokeSuspend(yVar);
        return yVar;
    }

    public final Object invokeSuspend(Object obj) {
        C0298a aVar = C0298a.f4140D;
        C0110h.P(obj);
        this.f5048D.cancel();
        return y.f2418a;
    }
}
