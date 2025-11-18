package com.hawkshaw.library.datalayer.models;

import B3.m;
import D3.a;
import D3.b;
import E3.C0029h0;
import E3.H;
import E3.O;
import E3.q0;
import Y1.K;
import com.hawkshaw.library.datalayer.models.Command;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;

public final class Command$TakePictureRequest$Size$$serializer implements H {
    public static final Command$TakePictureRequest$Size$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        Command$TakePictureRequest$Size$$serializer command$TakePictureRequest$Size$$serializer = new Command$TakePictureRequest$Size$$serializer();
        INSTANCE = command$TakePictureRequest$Size$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.Size", command$TakePictureRequest$Size$$serializer, 2);
        pluginGeneratedSerialDescriptor.l("width", false);
        pluginGeneratedSerialDescriptor.l("height", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private Command$TakePictureRequest$Size$$serializer() {
    }

    public KSerializer[] childSerializers() {
        O o4 = O.f456a;
        return new KSerializer[]{o4, o4};
    }

    public Command.TakePictureRequest.Size deserialize(Decoder decoder) {
        K.n(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        a a5 = decoder.a(descriptor2);
        boolean z4 = true;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (z4) {
            int y4 = a5.y(descriptor2);
            if (y4 == -1) {
                z4 = false;
            } else if (y4 == 0) {
                i6 = a5.s(descriptor2, 0);
                i5 |= 1;
            } else if (y4 == 1) {
                i7 = a5.s(descriptor2, 1);
                i5 |= 2;
            } else {
                throw new m(y4);
            }
        }
        a5.c(descriptor2);
        return new Command.TakePictureRequest.Size(i5, i6, i7, (q0) null);
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, Command.TakePictureRequest.Size size) {
        K.n(encoder, "encoder");
        K.n(size, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        b a5 = encoder.a(descriptor2);
        Command.TakePictureRequest.Size.write$Self$library_release(size, a5, descriptor2);
        a5.c(descriptor2);
    }

    public KSerializer[] typeParametersSerializers() {
        return C0029h0.f501b;
    }
}
