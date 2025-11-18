package com.hawkshaw.library.datalayer.models;

import B3.m;
import D3.a;
import D3.b;
import E3.C0029h0;
import E3.H;
import E3.U;
import E3.q0;
import Y1.K;
import com.hawkshaw.library.datalayer.models.Command;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;

public final class Command$FlashRequest$$serializer implements H {
    public static final Command$FlashRequest$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        Command$FlashRequest$$serializer command$FlashRequest$$serializer = new Command$FlashRequest$$serializer();
        INSTANCE = command$FlashRequest$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.hawkshaw.library.datalayer.models.Command.FlashRequest", command$FlashRequest$$serializer, 2);
        pluginGeneratedSerialDescriptor.l("timings", false);
        pluginGeneratedSerialDescriptor.l("facing", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private Command$FlashRequest$$serializer() {
    }

    public KSerializer[] childSerializers() {
        return new KSerializer[]{U.f465c, Command.FlashRequest.$childSerializers[1]};
    }

    public Command.FlashRequest deserialize(Decoder decoder) {
        K.n(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        a a5 = decoder.a(descriptor2);
        KSerializer[] access$get$childSerializers$cp = Command.FlashRequest.$childSerializers;
        boolean z4 = true;
        int i5 = 0;
        long[] jArr = null;
        Command.FlashRequest.Facing facing = null;
        while (z4) {
            int y4 = a5.y(descriptor2);
            if (y4 == -1) {
                z4 = false;
            } else if (y4 == 0) {
                jArr = (long[]) a5.l(descriptor2, 0, U.f465c, jArr);
                i5 |= 1;
            } else if (y4 == 1) {
                facing = (Command.FlashRequest.Facing) a5.l(descriptor2, 1, access$get$childSerializers$cp[1], facing);
                i5 |= 2;
            } else {
                throw new m(y4);
            }
        }
        a5.c(descriptor2);
        return new Command.FlashRequest(i5, jArr, facing, (q0) null);
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, Command.FlashRequest flashRequest) {
        K.n(encoder, "encoder");
        K.n(flashRequest, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        b a5 = encoder.a(descriptor2);
        Command.FlashRequest.write$Self$library_release(flashRequest, a5, descriptor2);
        a5.c(descriptor2);
    }

    public KSerializer[] typeParametersSerializers() {
        return C0029h0.f501b;
    }
}
