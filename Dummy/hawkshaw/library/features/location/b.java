package com.hawkshaw.library.features.location;

import W2.y;
import Y1.C0110h;
import Y1.K;
import a3.e;
import b3.C0298a;
import c3.C0331i;
import i3.p;
import t3.E;

public final class b extends C0331i implements p {

    /* renamed from: D  reason: collision with root package name */
    public int f4972D;

    /* renamed from: E  reason: collision with root package name */
    public final /* synthetic */ ForegroundLocationService f4973E;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public b(ForegroundLocationService foregroundLocationService, e eVar) {
        super(2, eVar);
        this.f4973E = foregroundLocationService;
    }

    public final e create(Object obj, e eVar) {
        return new b(this.f4973E, eVar);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((b) create((E) obj, (e) obj2)).invokeSuspend(y.f2418a);
    }

    public final Object invokeSuspend(Object obj) {
        C0298a aVar = C0298a.f4140D;
        int i5 = this.f4972D;
        ForegroundLocationService foregroundLocationService = this.f4973E;
        if (i5 == 0) {
            C0110h.P(obj);
            long access$getExpirationDuration$p = foregroundLocationService.expirationDuration;
            this.f4972D = 1;
            if (K.C(access$getExpirationDuration$p, this) == aVar) {
                return aVar;
            }
        } else if (i5 == 1) {
            C0110h.P(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        foregroundLocationService.stopForeground(1);
        return y.f2418a;
    }
}
