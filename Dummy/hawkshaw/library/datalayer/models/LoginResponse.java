package com.hawkshaw.library.datalayer.models;

import B3.f;
import D3.b;
import E3.q0;
import Y1.K;
import androidx.core.app.NotificationCompat;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import p3.q;
import w3.w;

@f
public final class LoginResponse {
    public static final Companion Companion = new Companion((j3.f) null);
    private final long createdAt;
    private final String email;
    private final String name;
    private final String refreshToken;
    private final String token;
    private final String uid;
    private final long updatedAt;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(j3.f fVar) {
            this();
        }

        public final KSerializer serializer() {
            return LoginResponse$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ LoginResponse(int i5, String str, String str2, String str3, String str4, String str5, long j5, long j6, q0 q0Var) {
        if (127 == (i5 & 127)) {
            this.uid = str;
            this.name = str2;
            this.email = str3;
            this.token = str4;
            this.refreshToken = str5;
            this.createdAt = j5;
            this.updatedAt = j6;
            return;
        }
        w.x(i5, 127, LoginResponse$$serializer.INSTANCE.getDescriptor());
        throw null;
    }

    public static /* synthetic */ void getCreatedAt$annotations() {
    }

    public static /* synthetic */ void getEmail$annotations() {
    }

    public static /* synthetic */ void getName$annotations() {
    }

    public static /* synthetic */ void getRefreshToken$annotations() {
    }

    public static /* synthetic */ void getToken$annotations() {
    }

    public static /* synthetic */ void getUid$annotations() {
    }

    public static /* synthetic */ void getUpdatedAt$annotations() {
    }

    public static final /* synthetic */ void write$Self$library_release(LoginResponse loginResponse, b bVar, SerialDescriptor serialDescriptor) {
        q qVar = (q) bVar;
        qVar.f0(serialDescriptor, 0, loginResponse.uid);
        qVar.f0(serialDescriptor, 1, loginResponse.name);
        qVar.f0(serialDescriptor, 2, loginResponse.email);
        qVar.f0(serialDescriptor, 3, loginResponse.token);
        qVar.f0(serialDescriptor, 4, loginResponse.refreshToken);
        qVar.d0(serialDescriptor, 5, loginResponse.createdAt);
        qVar.d0(serialDescriptor, 6, loginResponse.updatedAt);
    }

    public final long getCreatedAt() {
        return this.createdAt;
    }

    public final String getEmail() {
        return this.email;
    }

    public final String getName() {
        return this.name;
    }

    public final String getRefreshToken() {
        return this.refreshToken;
    }

    public final String getToken() {
        return this.token;
    }

    public final String getUid() {
        return this.uid;
    }

    public final long getUpdatedAt() {
        return this.updatedAt;
    }

    public LoginResponse(String str, String str2, String str3, String str4, String str5, long j5, long j6) {
        K.n(str, "uid");
        K.n(str2, "name");
        K.n(str3, NotificationCompat.CATEGORY_EMAIL);
        K.n(str4, "token");
        K.n(str5, "refreshToken");
        this.uid = str;
        this.name = str2;
        this.email = str3;
        this.token = str4;
        this.refreshToken = str5;
        this.createdAt = j5;
        this.updatedAt = j6;
    }
}
