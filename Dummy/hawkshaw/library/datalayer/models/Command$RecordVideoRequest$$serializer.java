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
import w3.w;

public final class Command$RecordVideoRequest$$serializer implements H {
    public static final Command$RecordVideoRequest$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        Command$RecordVideoRequest$$serializer command$RecordVideoRequest$$serializer = new Command$RecordVideoRequest$$serializer();
        INSTANCE = command$RecordVideoRequest$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.hawkshaw.library.datalayer.models.Command.RecordVideoRequest", command$RecordVideoRequest$$serializer, 18);
        pluginGeneratedSerialDescriptor.l("duration", true);
        pluginGeneratedSerialDescriptor.l("video_frame_rate", true);
        pluginGeneratedSerialDescriptor.l("bit_rate", true);
        pluginGeneratedSerialDescriptor.l("i_frame_interval", true);
        pluginGeneratedSerialDescriptor.l("audio_bit_rate", true);
        pluginGeneratedSerialDescriptor.l("audio_sample_rate", true);
        pluginGeneratedSerialDescriptor.l("audio_channel_count", true);
        pluginGeneratedSerialDescriptor.l("audio_min_buffer_size", true);
        pluginGeneratedSerialDescriptor.l("max_resolution", true);
        pluginGeneratedSerialDescriptor.l("flash_mode", true);
        pluginGeneratedSerialDescriptor.l("aspect_ratio", true);
        pluginGeneratedSerialDescriptor.l("save_to_private", true);
        pluginGeneratedSerialDescriptor.l("lens_facing", true);
        pluginGeneratedSerialDescriptor.l("rotation", true);
        pluginGeneratedSerialDescriptor.l("mLensFacing", true);
        pluginGeneratedSerialDescriptor.l("mRotation", true);
        pluginGeneratedSerialDescriptor.l("mFlashMode", true);
        pluginGeneratedSerialDescriptor.l("mAspectRatio", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private Command$RecordVideoRequest$$serializer() {
    }

    public KSerializer[] childSerializers() {
        KSerializer[] access$get$childSerializers$cp = Command.RecordVideoRequest.$childSerializers;
        O o4 = O.f456a;
        return new KSerializer[]{V.f466a, o4, o4, o4, o4, o4, o4, o4, Command$TakePictureRequest$Size$$serializer.INSTANCE, access$get$childSerializers$cp[9], access$get$childSerializers$cp[10], C0026g.f495a, access$get$childSerializers$cp[12], access$get$childSerializers$cp[13], o4, w.n(o4), o4, o4};
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0067, code lost:
        r15 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0069, code lost:
        r14 = r23;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x004a, code lost:
        r9 = r9 | r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x004b, code lost:
        r15 = r21;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.hawkshaw.library.datalayer.models.Command.RecordVideoRequest deserialize(kotlinx.serialization.encoding.Decoder r31) {
        /*
            r30 = this;
            r0 = r31
            java.lang.String r1 = "decoder"
            Y1.K.n(r0, r1)
            kotlinx.serialization.descriptors.SerialDescriptor r1 = r30.getDescriptor()
            D3.a r0 = r0.a(r1)
            kotlinx.serialization.KSerializer[] r2 = com.hawkshaw.library.datalayer.models.Command.RecordVideoRequest.$childSerializers
            r5 = 0
            r6 = 0
            r3 = r5
            r4 = r3
            r8 = r4
            r10 = r6
            r9 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r20 = 1
            r22 = 0
            r25 = 0
            r27 = 0
            r28 = 0
            r6 = r8
            r7 = r6
        L_0x0031:
            if (r20 == 0) goto L_0x0129
            r21 = r15
            int r15 = r0.y(r1)
            switch(r15) {
                case -1: goto L_0x0122;
                case 0: goto L_0x0116;
                case 1: goto L_0x010b;
                case 2: goto L_0x0100;
                case 3: goto L_0x00f7;
                case 4: goto L_0x00ec;
                case 5: goto L_0x00e1;
                case 6: goto L_0x00d7;
                case 7: goto L_0x00cd;
                case 8: goto L_0x00be;
                case 9: goto L_0x00af;
                case 10: goto L_0x00a0;
                case 11: goto L_0x0095;
                case 12: goto L_0x0086;
                case 13: goto L_0x0077;
                case 14: goto L_0x006c;
                case 15: goto L_0x0057;
                case 16: goto L_0x004e;
                case 17: goto L_0x0042;
                default: goto L_0x003c;
            }
        L_0x003c:
            B3.m r0 = new B3.m
            r0.<init>(r15)
            throw r0
        L_0x0042:
            r15 = 17
            int r28 = r0.s(r1, r15)
            r15 = 131072(0x20000, float:1.83671E-40)
        L_0x004a:
            r9 = r9 | r15
        L_0x004b:
            r15 = r21
            goto L_0x0031
        L_0x004e:
            r15 = 16
            int r27 = r0.s(r1, r15)
            r15 = 65536(0x10000, float:9.1835E-41)
            goto L_0x004a
        L_0x0057:
            E3.O r15 = E3.O.f456a
            r23 = r14
            r14 = 15
            java.lang.Object r3 = r0.f(r1, r14, r15, r3)
            java.lang.Integer r3 = (java.lang.Integer) r3
            r14 = 32768(0x8000, float:4.5918E-41)
            r9 = r9 | r14
        L_0x0067:
            r15 = r21
        L_0x0069:
            r14 = r23
            goto L_0x0031
        L_0x006c:
            r23 = r14
            r14 = 14
            int r25 = r0.s(r1, r14)
            r9 = r9 | 16384(0x4000, float:2.2959E-41)
            goto L_0x0067
        L_0x0077:
            r23 = r14
            r14 = 13
            r15 = r2[r14]
            java.lang.Object r4 = r0.l(r1, r14, r15, r4)
            com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Rotation r4 = (com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.Rotation) r4
            r9 = r9 | 8192(0x2000, float:1.148E-41)
            goto L_0x0067
        L_0x0086:
            r23 = r14
            r14 = 12
            r15 = r2[r14]
            java.lang.Object r8 = r0.l(r1, r14, r15, r8)
            com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$LensFacing r8 = (com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.LensFacing) r8
            r9 = r9 | 4096(0x1000, float:5.74E-42)
            goto L_0x0067
        L_0x0095:
            r23 = r14
            r14 = 11
            boolean r22 = r0.i(r1, r14)
            r9 = r9 | 2048(0x800, float:2.87E-42)
            goto L_0x0067
        L_0x00a0:
            r23 = r14
            r14 = 10
            r15 = r2[r14]
            java.lang.Object r7 = r0.l(r1, r14, r15, r7)
            com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$AspectRatio r7 = (com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.AspectRatio) r7
            r9 = r9 | 1024(0x400, float:1.435E-42)
            goto L_0x0067
        L_0x00af:
            r23 = r14
            r14 = 9
            r15 = r2[r14]
            java.lang.Object r6 = r0.l(r1, r14, r15, r6)
            com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$FlashMode r6 = (com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.FlashMode) r6
            r9 = r9 | 512(0x200, float:7.17E-43)
            goto L_0x0067
        L_0x00be:
            r23 = r14
            com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Size$$serializer r14 = com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Size$$serializer.INSTANCE
            r15 = 8
            java.lang.Object r5 = r0.l(r1, r15, r14, r5)
            com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Size r5 = (com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.Size) r5
            r9 = r9 | 256(0x100, float:3.59E-43)
            goto L_0x0067
        L_0x00cd:
            r23 = r14
            r14 = 7
            int r18 = r0.s(r1, r14)
            r9 = r9 | 128(0x80, float:1.8E-43)
            goto L_0x0067
        L_0x00d7:
            r23 = r14
            r14 = 6
            int r17 = r0.s(r1, r14)
            r9 = r9 | 64
            goto L_0x0067
        L_0x00e1:
            r23 = r14
            r14 = 5
            int r16 = r0.s(r1, r14)
            r9 = r9 | 32
            goto L_0x0067
        L_0x00ec:
            r23 = r14
            r14 = 4
            int r15 = r0.s(r1, r14)
            r9 = r9 | 16
            goto L_0x0069
        L_0x00f7:
            r14 = 3
            int r14 = r0.s(r1, r14)
            r9 = r9 | 8
            goto L_0x004b
        L_0x0100:
            r23 = r14
            r13 = 2
            int r13 = r0.s(r1, r13)
            r9 = r9 | 4
            goto L_0x004b
        L_0x010b:
            r23 = r14
            r14 = 1
            int r12 = r0.s(r1, r14)
            r9 = r9 | 2
            goto L_0x0067
        L_0x0116:
            r23 = r14
            r14 = 1
            r15 = 0
            long r10 = r0.D(r1, r15)
            r9 = r9 | 1
            goto L_0x0067
        L_0x0122:
            r23 = r14
            r15 = 0
            r20 = r15
            goto L_0x004b
        L_0x0129:
            r23 = r14
            r21 = r15
            r0.c(r1)
            com.hawkshaw.library.datalayer.models.Command$RecordVideoRequest r0 = new com.hawkshaw.library.datalayer.models.Command$RecordVideoRequest
            r1 = r8
            r8 = r0
            r29 = 0
            r19 = r5
            r20 = r6
            r21 = r7
            r23 = r1
            r24 = r4
            r26 = r3
            r8.<init>(r9, r10, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.models.Command$RecordVideoRequest$$serializer.deserialize(kotlinx.serialization.encoding.Decoder):com.hawkshaw.library.datalayer.models.Command$RecordVideoRequest");
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, Command.RecordVideoRequest recordVideoRequest) {
        K.n(encoder, "encoder");
        K.n(recordVideoRequest, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        b a5 = encoder.a(descriptor2);
        Command.RecordVideoRequest.write$Self$library_release(recordVideoRequest, a5, descriptor2);
        a5.c(descriptor2);
    }

    public KSerializer[] typeParametersSerializers() {
        return C0029h0.f501b;
    }
}
