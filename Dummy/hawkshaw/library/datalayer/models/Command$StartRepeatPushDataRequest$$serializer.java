package com.hawkshaw.library.datalayer.models;

import B3.m;
import D3.a;
import D3.b;
import E3.C0029h0;
import E3.H;
import E3.V;
import E3.q0;
import Y1.K;
import com.hawkshaw.library.datalayer.models.Command;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import p3.q;

public final class Command$StartRepeatPushDataRequest$$serializer implements H {
    public static final Command$StartRepeatPushDataRequest$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        Command$StartRepeatPushDataRequest$$serializer command$StartRepeatPushDataRequest$$serializer = new Command$StartRepeatPushDataRequest$$serializer();
        INSTANCE = command$StartRepeatPushDataRequest$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.hawkshaw.library.datalayer.models.Command.StartRepeatPushDataRequest", command$StartRepeatPushDataRequest$$serializer, 1);
        pluginGeneratedSerialDescriptor.l("interval", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private Command$StartRepeatPushDataRequest$$serializer() {
    }

    public KSerializer[] childSerializers() {
        return new KSerializer[]{V.f466a};
    }

    public Command.StartRepeatPushDataRequest deserialize(Decoder decoder) {
        K.n(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        a a5 = decoder.a(descriptor2);
        long j5 = 0;
        boolean z4 = true;
        int i5 = 0;
        while (z4) {
            int y4 = a5.y(descriptor2);
            if (y4 == -1) {
                z4 = false;
            } else if (y4 == 0) {
                j5 = a5.D(descriptor2, 0);
                i5 = 1;
            } else {
                throw new m(y4);
            }
        }
        a5.c(descriptor2);
        return new Command.StartRepeatPushDataRequest(i5, j5, (q0) null);
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, Command.StartRepeatPushDataRequest startRepeatPushDataRequest) {
        K.n(encoder, "encoder");
        K.n(startRepeatPushDataRequest, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        b a5 = encoder.a(descriptor2);
        ((q) a5).d0(descriptor2, 0, startRepeatPushDataRequest.intervalMillis);
        a5.c(descriptor2);
    }

    public KSerializer[] typeParametersSerializers() {
        return C0029h0.f501b;
    }
}
