package com.hawkshaw.library.datalayer.models;

import B3.m;
import D3.a;
import D3.b;
import E3.C0029h0;
import E3.H;
import E3.q0;
import E3.u0;
import Y1.K;
import com.hawkshaw.library.datalayer.models.DynamicConfig;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;

public final class DynamicConfig$$serializer implements H {
    public static final DynamicConfig$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        DynamicConfig$$serializer dynamicConfig$$serializer = new DynamicConfig$$serializer();
        INSTANCE = dynamicConfig$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.hawkshaw.library.datalayer.models.DynamicConfig", dynamicConfig$$serializer, 2);
        pluginGeneratedSerialDescriptor.l("call_recorder", true);
        pluginGeneratedSerialDescriptor.l("web_view_url", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private DynamicConfig$$serializer() {
    }

    public KSerializer[] childSerializers() {
        return new KSerializer[]{DynamicConfig$CallRecorder$$serializer.INSTANCE, u0.f536a};
    }

    public DynamicConfig deserialize(Decoder decoder) {
        K.n(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        a a5 = decoder.a(descriptor2);
        boolean z4 = true;
        int i5 = 0;
        DynamicConfig.CallRecorder callRecorder = null;
        String str = null;
        while (z4) {
            int y4 = a5.y(descriptor2);
            if (y4 == -1) {
                z4 = false;
            } else if (y4 == 0) {
                callRecorder = (DynamicConfig.CallRecorder) a5.l(descriptor2, 0, DynamicConfig$CallRecorder$$serializer.INSTANCE, callRecorder);
                i5 |= 1;
            } else if (y4 == 1) {
                str = a5.q(descriptor2, 1);
                i5 |= 2;
            } else {
                throw new m(y4);
            }
        }
        a5.c(descriptor2);
        return new DynamicConfig(i5, callRecorder, str, (q0) null);
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, DynamicConfig dynamicConfig) {
        K.n(encoder, "encoder");
        K.n(dynamicConfig, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        b a5 = encoder.a(descriptor2);
        DynamicConfig.write$Self$library_release(dynamicConfig, a5, descriptor2);
        a5.c(descriptor2);
    }

    public KSerializer[] typeParametersSerializers() {
        return C0029h0.f501b;
    }
}
