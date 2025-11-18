package com.hawkshaw.library.datalayer.models;

import B3.m;
import D3.a;
import D3.b;
import E3.C0026g;
import E3.C0029h0;
import E3.H;
import E3.q0;
import E3.u0;
import Y1.K;
import com.hawkshaw.library.datalayer.models.GetCallBlockListResponse;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;

public final class GetCallBlockListResponse$CallBlock$$serializer implements H {
    public static final GetCallBlockListResponse$CallBlock$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        GetCallBlockListResponse$CallBlock$$serializer getCallBlockListResponse$CallBlock$$serializer = new GetCallBlockListResponse$CallBlock$$serializer();
        INSTANCE = getCallBlockListResponse$CallBlock$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.hawkshaw.library.datalayer.models.GetCallBlockListResponse.CallBlock", getCallBlockListResponse$CallBlock$$serializer, 3);
        pluginGeneratedSerialDescriptor.l("name", false);
        pluginGeneratedSerialDescriptor.l("number", false);
        pluginGeneratedSerialDescriptor.l("block_outgoing_call", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private GetCallBlockListResponse$CallBlock$$serializer() {
    }

    public KSerializer[] childSerializers() {
        u0 u0Var = u0.f536a;
        return new KSerializer[]{u0Var, u0Var, C0026g.f495a};
    }

    public GetCallBlockListResponse.CallBlock deserialize(Decoder decoder) {
        K.n(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        a a5 = decoder.a(descriptor2);
        int i5 = 0;
        boolean z4 = false;
        String str = null;
        String str2 = null;
        boolean z5 = true;
        while (z5) {
            int y4 = a5.y(descriptor2);
            if (y4 == -1) {
                z5 = false;
            } else if (y4 == 0) {
                str = a5.q(descriptor2, 0);
                i5 |= 1;
            } else if (y4 == 1) {
                str2 = a5.q(descriptor2, 1);
                i5 |= 2;
            } else if (y4 == 2) {
                z4 = a5.i(descriptor2, 2);
                i5 |= 4;
            } else {
                throw new m(y4);
            }
        }
        a5.c(descriptor2);
        return new GetCallBlockListResponse.CallBlock(i5, str, str2, z4, (q0) null);
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, GetCallBlockListResponse.CallBlock callBlock) {
        K.n(encoder, "encoder");
        K.n(callBlock, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        b a5 = encoder.a(descriptor2);
        GetCallBlockListResponse.CallBlock.write$Self$library_release(callBlock, a5, descriptor2);
        a5.c(descriptor2);
    }

    public KSerializer[] typeParametersSerializers() {
        return C0029h0.f501b;
    }
}
