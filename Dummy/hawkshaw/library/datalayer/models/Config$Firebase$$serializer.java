package com.hawkshaw.library.datalayer.models;

import B3.m;
import D3.a;
import D3.b;
import E3.C0029h0;
import E3.H;
import E3.q0;
import E3.u0;
import Y1.K;
import com.hawkshaw.library.datalayer.models.Config;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;

public final class Config$Firebase$$serializer implements H {
    public static final Config$Firebase$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        Config$Firebase$$serializer config$Firebase$$serializer = new Config$Firebase$$serializer();
        INSTANCE = config$Firebase$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.hawkshaw.library.datalayer.models.Config.Firebase", config$Firebase$$serializer, 5);
        pluginGeneratedSerialDescriptor.l("web_client_id", false);
        pluginGeneratedSerialDescriptor.l("gcm_sender_id", false);
        pluginGeneratedSerialDescriptor.l("project_id", false);
        pluginGeneratedSerialDescriptor.l("api_key", false);
        pluginGeneratedSerialDescriptor.l("app_id", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private Config$Firebase$$serializer() {
    }

    public KSerializer[] childSerializers() {
        u0 u0Var = u0.f536a;
        return new KSerializer[]{u0Var, u0Var, u0Var, u0Var, u0Var};
    }

    public Config.Firebase deserialize(Decoder decoder) {
        K.n(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        a a5 = decoder.a(descriptor2);
        int i5 = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        boolean z4 = true;
        while (z4) {
            int y4 = a5.y(descriptor2);
            if (y4 == -1) {
                z4 = false;
            } else if (y4 == 0) {
                str = a5.q(descriptor2, 0);
                i5 |= 1;
            } else if (y4 == 1) {
                str2 = a5.q(descriptor2, 1);
                i5 |= 2;
            } else if (y4 == 2) {
                str3 = a5.q(descriptor2, 2);
                i5 |= 4;
            } else if (y4 == 3) {
                str4 = a5.q(descriptor2, 3);
                i5 |= 8;
            } else if (y4 == 4) {
                str5 = a5.q(descriptor2, 4);
                i5 |= 16;
            } else {
                throw new m(y4);
            }
        }
        a5.c(descriptor2);
        return new Config.Firebase(i5, str, str2, str3, str4, str5, (q0) null);
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, Config.Firebase firebase) {
        K.n(encoder, "encoder");
        K.n(firebase, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        b a5 = encoder.a(descriptor2);
        Config.Firebase.write$Self$library_release(firebase, a5, descriptor2);
        a5.c(descriptor2);
    }

    public KSerializer[] typeParametersSerializers() {
        return C0029h0.f501b;
    }
}
