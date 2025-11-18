package com.hawkshaw.library.datalayer.models;

import D3.b;
import E3.C0026g;
import E3.C0029h0;
import E3.H;
import E3.O;
import E3.V;
import Y1.K;
import com.hawkshaw.library.datalayer.models.Command;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;

public final class Command$RecordAudioRequest$$serializer implements H {
    public static final Command$RecordAudioRequest$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        Command$RecordAudioRequest$$serializer command$RecordAudioRequest$$serializer = new Command$RecordAudioRequest$$serializer();
        INSTANCE = command$RecordAudioRequest$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest", command$RecordAudioRequest$$serializer, 8);
        pluginGeneratedSerialDescriptor.l("duration", true);
        pluginGeneratedSerialDescriptor.l("audio_channel_count", true);
        pluginGeneratedSerialDescriptor.l("audio_encoder", true);
        pluginGeneratedSerialDescriptor.l("audio_source", true);
        pluginGeneratedSerialDescriptor.l("output_format", true);
        pluginGeneratedSerialDescriptor.l("audio_bit_rate", true);
        pluginGeneratedSerialDescriptor.l("audio_sample_rate", true);
        pluginGeneratedSerialDescriptor.l("save_to_private", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private Command$RecordAudioRequest$$serializer() {
    }

    public KSerializer[] childSerializers() {
        KSerializer[] access$get$childSerializers$cp = Command.RecordAudioRequest.$childSerializers;
        KSerializer kSerializer = access$get$childSerializers$cp[2];
        KSerializer kSerializer2 = access$get$childSerializers$cp[3];
        KSerializer kSerializer3 = access$get$childSerializers$cp[4];
        O o4 = O.f456a;
        return new KSerializer[]{V.f466a, o4, kSerializer, kSerializer2, kSerializer3, o4, o4, C0026g.f495a};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v3, resolved type: com.hawkshaw.library.datalayer.models.Command$RecordAudioRequest$AudioEncoderEnum} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v3, resolved type: com.hawkshaw.library.datalayer.models.Command$RecordAudioRequest$AudioSourceEnum} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v3, resolved type: com.hawkshaw.library.datalayer.models.Command$RecordAudioRequest$OutputFormatEnum} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest deserialize(kotlinx.serialization.encoding.Decoder r21) {
        /*
            r20 = this;
            r0 = r21
            java.lang.String r1 = "decoder"
            Y1.K.n(r0, r1)
            kotlinx.serialization.descriptors.SerialDescriptor r1 = r20.getDescriptor()
            D3.a r0 = r0.a(r1)
            kotlinx.serialization.KSerializer[] r2 = com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest.$childSerializers
            r3 = 1
            r4 = 0
            r5 = 0
            r6 = 0
            r9 = r4
            r12 = r9
            r16 = r12
            r17 = r16
            r18 = r17
            r13 = r5
            r14 = r13
            r15 = r14
            r10 = r6
            r5 = r3
        L_0x0025:
            if (r5 == 0) goto L_0x0083
            int r6 = r0.y(r1)
            switch(r6) {
                case -1: goto L_0x0081;
                case 0: goto L_0x007a;
                case 1: goto L_0x0073;
                case 2: goto L_0x0066;
                case 3: goto L_0x0059;
                case 4: goto L_0x004c;
                case 5: goto L_0x0044;
                case 6: goto L_0x003c;
                case 7: goto L_0x0034;
                default: goto L_0x002e;
            }
        L_0x002e:
            B3.m r0 = new B3.m
            r0.<init>(r6)
            throw r0
        L_0x0034:
            r6 = 7
            boolean r18 = r0.i(r1, r6)
            r9 = r9 | 128(0x80, float:1.8E-43)
            goto L_0x0025
        L_0x003c:
            r6 = 6
            int r17 = r0.s(r1, r6)
            r9 = r9 | 64
            goto L_0x0025
        L_0x0044:
            r6 = 5
            int r16 = r0.s(r1, r6)
            r9 = r9 | 32
            goto L_0x0025
        L_0x004c:
            r6 = 4
            r7 = r2[r6]
            java.lang.Object r6 = r0.l(r1, r6, r7, r15)
            r15 = r6
            com.hawkshaw.library.datalayer.models.Command$RecordAudioRequest$OutputFormatEnum r15 = (com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest.OutputFormatEnum) r15
            r9 = r9 | 16
            goto L_0x0025
        L_0x0059:
            r6 = 3
            r7 = r2[r6]
            java.lang.Object r6 = r0.l(r1, r6, r7, r14)
            r14 = r6
            com.hawkshaw.library.datalayer.models.Command$RecordAudioRequest$AudioSourceEnum r14 = (com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest.AudioSourceEnum) r14
            r9 = r9 | 8
            goto L_0x0025
        L_0x0066:
            r6 = 2
            r7 = r2[r6]
            java.lang.Object r6 = r0.l(r1, r6, r7, r13)
            r13 = r6
            com.hawkshaw.library.datalayer.models.Command$RecordAudioRequest$AudioEncoderEnum r13 = (com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest.AudioEncoderEnum) r13
            r9 = r9 | 4
            goto L_0x0025
        L_0x0073:
            int r12 = r0.s(r1, r3)
            r9 = r9 | 2
            goto L_0x0025
        L_0x007a:
            long r10 = r0.D(r1, r4)
            r9 = r9 | 1
            goto L_0x0025
        L_0x0081:
            r5 = r4
            goto L_0x0025
        L_0x0083:
            r0.c(r1)
            com.hawkshaw.library.datalayer.models.Command$RecordAudioRequest r0 = new com.hawkshaw.library.datalayer.models.Command$RecordAudioRequest
            r19 = 0
            r8 = r0
            r8.<init>((int) r9, (long) r10, (int) r12, (com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest.AudioEncoderEnum) r13, (com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest.AudioSourceEnum) r14, (com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest.OutputFormatEnum) r15, (int) r16, (int) r17, (boolean) r18, (E3.q0) r19)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.models.Command$RecordAudioRequest$$serializer.deserialize(kotlinx.serialization.encoding.Decoder):com.hawkshaw.library.datalayer.models.Command$RecordAudioRequest");
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, Command.RecordAudioRequest recordAudioRequest) {
        K.n(encoder, "encoder");
        K.n(recordAudioRequest, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        b a5 = encoder.a(descriptor2);
        Command.RecordAudioRequest.write$Self$library_release(recordAudioRequest, a5, descriptor2);
        a5.c(descriptor2);
    }

    public KSerializer[] typeParametersSerializers() {
        return C0029h0.f501b;
    }
}
