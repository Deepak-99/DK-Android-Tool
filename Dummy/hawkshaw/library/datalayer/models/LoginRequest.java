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
public final class LoginRequest {
    public static final Companion Companion = new Companion((j3.f) null);
    private final String deviceInfo;
    private final String email;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(j3.f fVar) {
            this();
        }

        public final KSerializer serializer() {
            return LoginRequest$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ LoginRequest(int i5, String str, String str2, q0 q0Var) {
        if (3 == (i5 & 3)) {
            this.email = str;
            this.deviceInfo = str2;
            return;
        }
        w.x(i5, 3, LoginRequest$$serializer.INSTANCE.getDescriptor());
        throw null;
    }

    public static /* synthetic */ void getDeviceInfo$annotations() {
    }

    public static /* synthetic */ void getEmail$annotations() {
    }

    public static final /* synthetic */ void write$Self$library_release(LoginRequest loginRequest, b bVar, SerialDescriptor serialDescriptor) {
        q qVar = (q) bVar;
        qVar.f0(serialDescriptor, 0, loginRequest.email);
        qVar.f0(serialDescriptor, 1, loginRequest.deviceInfo);
    }

    public final String getDeviceInfo() {
        return this.deviceInfo;
    }

    public final String getEmail() {
        return this.email;
    }

    public LoginRequest(String str, String str2) {
        K.n(str, NotificationCompat.CATEGORY_EMAIL);
        K.n(str2, "deviceInfo");
        this.email = str;
        this.deviceInfo = str2;
    }
}
