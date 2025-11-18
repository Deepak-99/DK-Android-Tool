package com.hawkshaw.library.utils;

import W2.y;
import Y1.C0110h;
import Y1.K;
import a3.e;
import b3.C0298a;
import c3.C0331i;
import com.hawkshaw.library.config.RemoteConfig;
import i3.p;
import t3.E;

public final class d extends C0331i implements p {

    /* renamed from: D  reason: collision with root package name */
    public int f5074D;

    /* renamed from: E  reason: collision with root package name */
    public final /* synthetic */ UncaughtExceptionHandler$handleException$1 f5075E;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public d(UncaughtExceptionHandler$handleException$1 uncaughtExceptionHandler$handleException$1, e eVar) {
        super(2, eVar);
        this.f5075E = uncaughtExceptionHandler$handleException$1;
    }

    public final e create(Object obj, e eVar) {
        return new d(this.f5075E, eVar);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((d) create((E) obj, (e) obj2)).invokeSuspend(y.f2418a);
    }

    public final Object invokeSuspend(Object obj) {
        C0298a aVar = C0298a.f4140D;
        int i5 = this.f5074D;
        if (i5 == 0) {
            C0110h.P(obj);
            long exceptionCounterResetDelayInMillis = RemoteConfig.INSTANCE.getExceptionCounterResetDelayInMillis();
            this.f5074D = 1;
            if (K.C(exceptionCounterResetDelayInMillis, this) == aVar) {
                return aVar;
            }
        } else if (i5 == 1) {
            C0110h.P(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        this.f5075E.exceptionCounter.set(0);
        return y.f2418a;
    }
}
