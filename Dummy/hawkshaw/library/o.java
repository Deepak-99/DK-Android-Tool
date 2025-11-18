package com.hawkshaw.library;

import W2.y;
import Y1.C0110h;
import Y1.K;
import a3.e;
import android.app.Application;
import b3.C0298a;
import c3.C0331i;
import com.hawkshaw.library.fcm.Pushy;
import i3.p;
import t3.E;

public final class o extends C0331i implements p {

    /* renamed from: D  reason: collision with root package name */
    public int f5052D;

    /* renamed from: E  reason: collision with root package name */
    public final /* synthetic */ HawkshawInitializer f5053E;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public o(HawkshawInitializer hawkshawInitializer, e eVar) {
        super(2, eVar);
        this.f5053E = hawkshawInitializer;
    }

    public final e create(Object obj, e eVar) {
        return new o(this.f5053E, eVar);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((o) create((E) obj, (e) obj2)).invokeSuspend(y.f2418a);
    }

    public final Object invokeSuspend(Object obj) {
        C0298a aVar = C0298a.f4140D;
        int i5 = this.f5052D;
        if (i5 == 0) {
            C0110h.P(obj);
            Pushy pushy = Pushy.INSTANCE;
            Application application = this.f5053E.activity.getApplication();
            K.m(application, "getApplication(...)");
            this.f5052D = 1;
            if (pushy.init(application, this) == aVar) {
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
