package com.hawkshaw.library.features.location;

import W2.y;
import Y1.C0110h;
import a3.e;
import b3.C0298a;
import c3.C0331i;
import com.hawkshaw.library.datalayer.Injector;
import com.hawkshaw.library.datalayer.models.Location;
import com.hawkshaw.library.datalayer.models.PushLocationRequest;
import com.hawkshaw.library.datalayer.network.service.LocationService;
import i3.p;
import t3.E;

public final class a extends C0331i implements p {

    /* renamed from: D  reason: collision with root package name */
    public int f4970D;

    /* renamed from: E  reason: collision with root package name */
    public final /* synthetic */ Location f4971E;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public a(Location location, e eVar) {
        super(2, eVar);
        this.f4971E = location;
    }

    public final e create(Object obj, e eVar) {
        return new a(this.f4971E, eVar);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((a) create((E) obj, (e) obj2)).invokeSuspend(y.f2418a);
    }

    public final Object invokeSuspend(Object obj) {
        C0298a aVar = C0298a.f4140D;
        int i5 = this.f4970D;
        if (i5 == 0) {
            C0110h.P(obj);
            LocationService locationService = Injector.Companion.getInstance().getLocationService();
            PushLocationRequest pushLocationRequest = new PushLocationRequest(this.f4971E);
            this.f4970D = 1;
            if (locationService.pushLocation(pushLocationRequest, this) == aVar) {
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
