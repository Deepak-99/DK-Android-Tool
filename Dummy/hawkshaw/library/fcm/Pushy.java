package com.hawkshaw.library.fcm;

import J1.c;
import J1.d;
import K0.j;
import K0.k;
import K0.q;
import W2.y;
import X1.B;
import Y1.J;
import Y1.K;
import a3.e;
import android.content.Context;
import b3.C0298a;
import c3.C0331i;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.H;
import com.hawkshaw.library.datalayer.Injector;
import com.hawkshaw.library.datalayer.models.PushPushyTokenRequest;
import com.hawkshaw.library.ktextensions.CoroutineKt;
import com.hawkshaw.library.ktextensions.ExceptionsKt;
import i3.l;
import t3.N;
import z.f;

public final class Pushy {
    public static final Pushy INSTANCE = new Pushy();
    private static final String TAG = "Pushy";

    private Pushy() {
    }

    /* access modifiers changed from: private */
    public final Object pushFCMTokenToServer(e eVar) {
        try {
            FirebaseMessaging c5 = FirebaseMessaging.c();
            c5.getClass();
            j jVar = new j();
            c5.f4592f.execute(new f(15, c5, jVar));
            q qVar = jVar.f1075a;
            H h5 = new H(J1.f.f1039D, 2);
            qVar.getClass();
            qVar.d(k.f1076a, h5);
        } catch (Exception e5) {
            ExceptionsKt.logNonFatal(e5);
        }
        return y.f2418a;
    }

    /* access modifiers changed from: private */
    public static final void pushFCMTokenToServer$lambda$0(l lVar, Object obj) {
        K.n(lVar, "$tmp0");
        lVar.invoke(obj);
    }

    /* access modifiers changed from: private */
    public final Object pushPushyTokenToServer(String str, e eVar) {
        Object pushPushyToken = Injector.Companion.getInstance().getAppService().pushPushyToken(new PushPushyTokenRequest(str), eVar);
        return pushPushyToken == C0298a.f4140D ? pushPushyToken : y.f2418a;
    }

    public final Object init(Context context, e eVar) {
        Object B4 = B.B(N.f9292c, new c(context, (e) null), eVar);
        return B4 == C0298a.f4140D ? B4 : y.f2418a;
    }

    public final Object initFromApp(Context context, e eVar) {
        Object B4 = B.B(N.f9292c, new d(context, (e) null), eVar);
        return B4 == C0298a.f4140D ? B4 : y.f2418a;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [c3.i, i3.p] */
    public final Object pushTokensToServer(e eVar) {
        CoroutineKt.safeLaunch$default(J.a(N.f9292c), (a3.j) null, new C0331i(2, (e) null), 1, (Object) null);
        return y.f2418a;
    }
}
