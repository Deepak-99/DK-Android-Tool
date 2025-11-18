package com.hawkshaw.library.datalayer.models;

import D3.b;
import E3.C0029h0;
import E3.H;
import E3.O;
import Y1.K;
import com.hawkshaw.library.datalayer.models.Command;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;

public final class Command$DeviceAudioRequest$$serializer implements H {
    public static final Command$DeviceAudioRequest$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        Command$DeviceAudioRequest$$serializer command$DeviceAudioRequest$$serializer = new Command$DeviceAudioRequest$$serializer();
        INSTANCE = command$DeviceAudioRequest$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.hawkshaw.library.datalayer.models.Command.DeviceAudioRequest", command$DeviceAudioRequest$$serializer, 5);
        pluginGeneratedSerialDescriptor.l("ringer_mode", true);
        pluginGeneratedSerialDescriptor.l("music_volume", true);
        pluginGeneratedSerialDescriptor.l("ring_volume", true);
        pluginGeneratedSerialDescriptor.l("call_volume", true);
        pluginGeneratedSerialDescriptor.l("alarm_volume", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private Command$DeviceAudioRequest$$serializer() {
    }

    public KSerializer[] childSerializers() {
        O o4 = O.f456a;
        return new KSerializer[]{Command.DeviceAudioRequest.$childSerializers[0], o4, o4, o4, o4};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v3, resolved type: com.hawkshaw.library.datalayer.models.Command$DeviceAudioRequest$RingerMode} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.hawkshaw.library.datalayer.models.Command.DeviceAudioRequest deserialize(kotlinx.serialization.encoding.Decoder r14) {
        /*
            r13 = this;
            java.lang.String r0 = "decoder"
            Y1.K.n(r14, r0)
            kotlinx.serialization.descriptors.SerialDescriptor r0 = r13.getDescriptor()
            D3.a r14 = r14.a(r0)
            kotlinx.serialization.KSerializer[] r1 = com.hawkshaw.library.datalayer.models.Command.DeviceAudioRequest.$childSerializers
            r2 = 1
            r3 = 0
            r4 = 0
            r6 = r3
            r8 = r6
            r9 = r8
            r10 = r9
            r11 = r10
            r7 = r4
            r4 = r2
        L_0x001b:
            if (r4 == 0) goto L_0x0061
            int r5 = r14.y(r0)
            r12 = -1
            if (r5 == r12) goto L_0x005f
            if (r5 == 0) goto L_0x0053
            if (r5 == r2) goto L_0x004c
            r12 = 2
            if (r5 == r12) goto L_0x0045
            r12 = 3
            if (r5 == r12) goto L_0x003e
            r11 = 4
            if (r5 != r11) goto L_0x0038
            int r11 = r14.s(r0, r11)
            r6 = r6 | 16
            goto L_0x001b
        L_0x0038:
            B3.m r14 = new B3.m
            r14.<init>(r5)
            throw r14
        L_0x003e:
            int r10 = r14.s(r0, r12)
            r6 = r6 | 8
            goto L_0x001b
        L_0x0045:
            int r9 = r14.s(r0, r12)
            r6 = r6 | 4
            goto L_0x001b
        L_0x004c:
            int r8 = r14.s(r0, r2)
            r6 = r6 | 2
            goto L_0x001b
        L_0x0053:
            r5 = r1[r3]
            java.lang.Object r5 = r14.l(r0, r3, r5, r7)
            r7 = r5
            com.hawkshaw.library.datalayer.models.Command$DeviceAudioRequest$RingerMode r7 = (com.hawkshaw.library.datalayer.models.Command.DeviceAudioRequest.RingerMode) r7
            r6 = r6 | 1
            goto L_0x001b
        L_0x005f:
            r4 = r3
            goto L_0x001b
        L_0x0061:
            r14.c(r0)
            com.hawkshaw.library.datalayer.models.Command$DeviceAudioRequest r14 = new com.hawkshaw.library.datalayer.models.Command$DeviceAudioRequest
            r12 = 0
            r5 = r14
            r5.<init>((int) r6, (com.hawkshaw.library.datalayer.models.Command.DeviceAudioRequest.RingerMode) r7, (int) r8, (int) r9, (int) r10, (int) r11, (E3.q0) r12)
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.models.Command$DeviceAudioRequest$$serializer.deserialize(kotlinx.serialization.encoding.Decoder):com.hawkshaw.library.datalayer.models.Command$DeviceAudioRequest");
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, Command.DeviceAudioRequest deviceAudioRequest) {
        K.n(encoder, "encoder");
        K.n(deviceAudioRequest, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        b a5 = encoder.a(descriptor2);
        Command.DeviceAudioRequest.write$Self$library_release(deviceAudioRequest, a5, descriptor2);
        a5.c(descriptor2);
    }

    public KSerializer[] typeParametersSerializers() {
        return C0029h0.f501b;
    }
}
