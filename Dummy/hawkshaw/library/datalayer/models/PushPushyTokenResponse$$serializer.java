package com.hawkshaw.library.datalayer.models;

import B3.m;
import D3.a;
import D3.b;
import E3.C0029h0;
import E3.H;
import E3.q0;
import Y1.K;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;

public final class PushPushyTokenResponse$$serializer implements H {
    public static final PushPushyTokenResponse$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        PushPushyTokenResponse$$serializer pushPushyTokenResponse$$serializer = new PushPushyTokenResponse$$serializer();
        INSTANCE = pushPushyTokenResponse$$serializer;
        descriptor = new PluginGeneratedSerialDescriptor("com.hawkshaw.library.datalayer.models.PushPushyTokenResponse", pushPushyTokenResponse$$serializer, 0);
    }

    private PushPushyTokenResponse$$serializer() {
    }

    public KSerializer[] childSerializers() {
        return new KSerializer[0];
    }

    public PushPushyTokenResponse deserialize(Decoder decoder) {
        K.n(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        a a5 = decoder.a(descriptor2);
        int y4 = a5.y(descriptor2);
        if (y4 == -1) {
            a5.c(descriptor2);
            return new PushPushyTokenResponse(0, (q0) null);
        }
        throw new m(y4);
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, PushPushyTokenResponse pushPushyTokenResponse) {
        K.n(encoder, "encoder");
        K.n(pushPushyTokenResponse, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        b a5 = encoder.a(descriptor2);
        PushPushyTokenResponse.write$Self$library_release(pushPushyTokenResponse, a5, descriptor2);
        a5.c(descriptor2);
    }

    public KSerializer[] typeParametersSerializers() {
        return C0029h0.f501b;
    }
}
