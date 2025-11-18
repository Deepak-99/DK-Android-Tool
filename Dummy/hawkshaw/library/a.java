package com.hawkshaw.library;

import W2.y;
import Y1.C0110h;
import a3.e;
import android.content.Context;
import b3.C0298a;
import c3.C0331i;
import com.hawkshaw.library.App;
import com.hawkshaw.library.config.RemoteConfig;
import com.hawkshaw.library.fcm.Firebase;
import com.hawkshaw.library.fcm.Pushy;
import com.hawkshaw.library.preferences.Prefs;
import i3.p;
import t3.E;

public final class a extends C0331i implements p {

    /* renamed from: D  reason: collision with root package name */
    public int f4883D;

    /* renamed from: E  reason: collision with root package name */
    public final /* synthetic */ App f4884E;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public a(App app, e eVar) {
        super(2, eVar);
        this.f4884E = app;
    }

    public final e create(Object obj, e eVar) {
        return new a(this.f4884E, eVar);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((a) create((E) obj, (e) obj2)).invokeSuspend(y.f2418a);
    }

    public final Object invokeSuspend(Object obj) {
        C0298a aVar = C0298a.f4140D;
        int i5 = this.f4883D;
        if (i5 == 0) {
            C0110h.P(obj);
            Prefs prefs = Prefs.INSTANCE;
            App.Companion companion = App.Companion;
            prefs.init(companion.getContext());
            Firebase.INSTANCE.initFCM(companion.getContext());
            RemoteConfig.INSTANCE.init();
            Pushy pushy = Pushy.INSTANCE;
            Context context = companion.getContext();
            this.f4883D = 1;
            if (pushy.initFromApp(context, this) == aVar) {
                return aVar;
            }
        } else if (i5 == 1) {
            C0110h.P(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        this.f4884E.enableReceivers();
        return y.f2418a;
    }
}
