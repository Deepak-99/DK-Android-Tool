package com.hawkshaw.library.datalayer.models;

import B3.m;
import D3.a;
import D3.b;
import E3.C0029h0;
import E3.H;
import E3.V;
import E3.q0;
import E3.u0;
import Y1.K;
import androidx.core.app.NotificationCompat;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;

public final class LoginResponse$$serializer implements H {
    public static final LoginResponse$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        LoginResponse$$serializer loginResponse$$serializer = new LoginResponse$$serializer();
        INSTANCE = loginResponse$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.hawkshaw.library.datalayer.models.LoginResponse", loginResponse$$serializer, 7);
        pluginGeneratedSerialDescriptor.l("uid", false);
        pluginGeneratedSerialDescriptor.l("name", false);
        pluginGeneratedSerialDescriptor.l(NotificationCompat.CATEGORY_EMAIL, false);
        pluginGeneratedSerialDescriptor.l("token", false);
        pluginGeneratedSerialDescriptor.l("refresh_token", false);
        pluginGeneratedSerialDescriptor.l("created_at", false);
        pluginGeneratedSerialDescriptor.l("updated_at", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private LoginResponse$$serializer() {
    }

    public KSerializer[] childSerializers() {
        u0 u0Var = u0.f536a;
        V v4 = V.f466a;
        return new KSerializer[]{u0Var, u0Var, u0Var, u0Var, u0Var, v4, v4};
    }

    public LoginResponse deserialize(Decoder decoder) {
        Decoder decoder2 = decoder;
        K.n(decoder2, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        a a5 = decoder2.a(descriptor2);
        int i5 = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        long j5 = 0;
        long j6 = 0;
        boolean z4 = true;
        while (z4) {
            int y4 = a5.y(descriptor2);
            switch (y4) {
                case -1:
                    z4 = false;
                    break;
                case 0:
                    str = a5.q(descriptor2, 0);
                    i5 |= 1;
                    break;
                case 1:
                    str2 = a5.q(descriptor2, 1);
                    i5 |= 2;
                    break;
                case 2:
                    str3 = a5.q(descriptor2, 2);
                    i5 |= 4;
                    break;
                case 3:
                    str4 = a5.q(descriptor2, 3);
                    i5 |= 8;
                    break;
                case 4:
                    str5 = a5.q(descriptor2, 4);
                    i5 |= 16;
                    break;
                case 5:
                    j5 = a5.D(descriptor2, 5);
                    i5 |= 32;
                    break;
                case 6:
                    j6 = a5.D(descriptor2, 6);
                    i5 |= 64;
                    break;
                default:
                    throw new m(y4);
            }
        }
        a5.c(descriptor2);
        return new LoginResponse(i5, str, str2, str3, str4, str5, j5, j6, (q0) null);
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, LoginResponse loginResponse) {
        K.n(encoder, "encoder");
        K.n(loginResponse, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        b a5 = encoder.a(descriptor2);
        LoginResponse.write$Self$library_release(loginResponse, a5, descriptor2);
        a5.c(descriptor2);
    }

    public KSerializer[] typeParametersSerializers() {
        return C0029h0.f501b;
    }
}
