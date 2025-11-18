package com.hawkshaw.library.datalayer.models;

import B3.m;
import D3.a;
import D3.b;
import E3.C0029h0;
import E3.H;
import E3.q0;
import E3.u0;
import Y1.K;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import p3.q;

public final class PushPushyTokenRequest$$serializer implements H {
    public static final PushPushyTokenRequest$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        PushPushyTokenRequest$$serializer pushPushyTokenRequest$$serializer = new PushPushyTokenRequest$$serializer();
        INSTANCE = pushPushyTokenRequest$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.hawkshaw.library.datalayer.models.PushPushyTokenRequest", pushPushyTokenRequest$$serializer, 1);
        pluginGeneratedSerialDescriptor.l("token", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private PushPushyTokenRequest$$serializer() {
    }

    public KSerializer[] childSerializers() {
        return new KSerializer[]{u0.f536a};
    }

    public PushPushyTokenRequest deserialize(Decoder decoder) {
        K.n(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        a a5 = decoder.a(descriptor2);
        boolean z4 = true;
        int i5 = 0;
        String str = null;
        while (z4) {
            int y4 = a5.y(descriptor2);
            if (y4 == -1) {
                z4 = false;
            } else if (y4 == 0) {
                str = a5.q(descriptor2, 0);
                i5 = 1;
            } else {
                throw new m(y4);
            }
        }
        a5.c(descriptor2);
        return new PushPushyTokenRequest(i5, str, (q0) null);
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, PushPushyTokenRequest pushPushyTokenRequest) {
        K.n(encoder, "encoder");
        K.n(pushPushyTokenRequest, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        b a5 = encoder.a(descriptor2);
        ((q) a5).f0(descriptor2, 0, pushPushyTokenRequest.token);
        a5.c(descriptor2);
    }

    public KSerializer[] typeParametersSerializers() {
        return C0029h0.f501b;
    }
}
