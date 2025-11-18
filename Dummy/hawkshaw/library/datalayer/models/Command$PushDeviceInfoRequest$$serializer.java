package com.hawkshaw.library.datalayer.models;

import D3.b;
import E3.C0029h0;
import E3.H;
import Y1.K;
import com.hawkshaw.library.datalayer.models.Command;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;

public final class Command$PushDeviceInfoRequest$$serializer implements H {
    public static final Command$PushDeviceInfoRequest$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        Command$PushDeviceInfoRequest$$serializer command$PushDeviceInfoRequest$$serializer = new Command$PushDeviceInfoRequest$$serializer();
        INSTANCE = command$PushDeviceInfoRequest$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.hawkshaw.library.datalayer.models.Command.PushDeviceInfoRequest", command$PushDeviceInfoRequest$$serializer, 1);
        pluginGeneratedSerialDescriptor.l("", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private Command$PushDeviceInfoRequest$$serializer() {
    }

    public KSerializer[] childSerializers() {
        return new KSerializer[]{Command.PushDeviceInfoRequest.$childSerializers[0]};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v3, resolved type: com.hawkshaw.library.datalayer.models.Command$PushDeviceInfoRequest$Type} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.hawkshaw.library.datalayer.models.Command.PushDeviceInfoRequest deserialize(kotlinx.serialization.encoding.Decoder r11) {
        /*
            r10 = this;
            java.lang.String r0 = "decoder"
            Y1.K.n(r11, r0)
            kotlinx.serialization.descriptors.SerialDescriptor r0 = r10.getDescriptor()
            D3.a r11 = r11.a(r0)
            kotlinx.serialization.KSerializer[] r1 = com.hawkshaw.library.datalayer.models.Command.PushDeviceInfoRequest.$childSerializers
            r2 = 1
            r3 = 0
            r4 = 0
            r5 = r2
            r6 = r3
            r7 = r4
        L_0x0017:
            if (r5 == 0) goto L_0x0035
            int r8 = r11.y(r0)
            r9 = -1
            if (r8 == r9) goto L_0x0033
            if (r8 != 0) goto L_0x002d
            r6 = r1[r3]
            java.lang.Object r6 = r11.l(r0, r3, r6, r7)
            r7 = r6
            com.hawkshaw.library.datalayer.models.Command$PushDeviceInfoRequest$Type r7 = (com.hawkshaw.library.datalayer.models.Command.PushDeviceInfoRequest.Type) r7
            r6 = r2
            goto L_0x0017
        L_0x002d:
            B3.m r11 = new B3.m
            r11.<init>(r8)
            throw r11
        L_0x0033:
            r5 = r3
            goto L_0x0017
        L_0x0035:
            r11.c(r0)
            com.hawkshaw.library.datalayer.models.Command$PushDeviceInfoRequest r11 = new com.hawkshaw.library.datalayer.models.Command$PushDeviceInfoRequest
            r11.<init>((int) r6, (com.hawkshaw.library.datalayer.models.Command.PushDeviceInfoRequest.Type) r7, (E3.q0) r4)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.models.Command$PushDeviceInfoRequest$$serializer.deserialize(kotlinx.serialization.encoding.Decoder):com.hawkshaw.library.datalayer.models.Command$PushDeviceInfoRequest");
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, Command.PushDeviceInfoRequest pushDeviceInfoRequest) {
        K.n(encoder, "encoder");
        K.n(pushDeviceInfoRequest, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        b a5 = encoder.a(descriptor2);
        Command.PushDeviceInfoRequest.write$Self$library_release(pushDeviceInfoRequest, a5, descriptor2);
        a5.c(descriptor2);
    }

    public KSerializer[] typeParametersSerializers() {
        return C0029h0.f501b;
    }
}
