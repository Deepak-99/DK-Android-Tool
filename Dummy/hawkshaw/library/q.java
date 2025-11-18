package com.hawkshaw.library;

import W2.y;
import Y1.C0110h;
import a3.e;
import b3.C0298a;
import c3.C0331i;
import com.hawkshaw.library.handler.PushDataKt;
import i3.p;
import t3.E;

public final class q extends C0331i implements p {

    /* renamed from: D  reason: collision with root package name */
    public int f5067D;

    public final e create(Object obj, e eVar) {
        return new C0331i(2, eVar);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((q) create((E) obj, (e) obj2)).invokeSuspend(y.f2418a);
    }

    public final Object invokeSuspend(Object obj) {
        C0298a aVar = C0298a.f4140D;
        int i5 = this.f5067D;
        if (i5 == 0) {
            C0110h.P(obj);
            this.f5067D = 1;
            if (PushDataKt.pushData(this) == aVar) {
                return aVar;
            }
        } else if (i5 == 1) {
            C0110h.P(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return y.f2418a;
    }
}
