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

public final class Command$TakePictureRequest$$serializer implements H {
    public static final Command$TakePictureRequest$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        Command$TakePictureRequest$$serializer command$TakePictureRequest$$serializer = new Command$TakePictureRequest$$serializer();
        INSTANCE = command$TakePictureRequest$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.hawkshaw.library.datalayer.models.Command.TakePictureRequest", command$TakePictureRequest$$serializer, 16);
        pluginGeneratedSerialDescriptor.l("jpeg_quality", true);
        pluginGeneratedSerialDescriptor.l("size", true);
        pluginGeneratedSerialDescriptor.l("take_picture_delay", true);
        pluginGeneratedSerialDescriptor.l("save_to_private", true);
        pluginGeneratedSerialDescriptor.l("capture_mode", true);
        pluginGeneratedSerialDescriptor.l("flash_mode", true);
        pluginGeneratedSerialDescriptor.l("flash_type", true);
        pluginGeneratedSerialDescriptor.l("lens_facing", true);
        pluginGeneratedSerialDescriptor.l("aspect_ratio", true);
        pluginGeneratedSerialDescriptor.l("rotation", true);
        pluginGeneratedSerialDescriptor.l("mCaptureMode", true);
        pluginGeneratedSerialDescriptor.l("mFlashMode", true);
        pluginGeneratedSerialDescriptor.l("mFlashType", true);
        pluginGeneratedSerialDescriptor.l("mLensFacing", true);
        pluginGeneratedSerialDescriptor.l("mAspectRatio", true);
        pluginGeneratedSerialDescriptor.l("mRotation", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private Command$TakePictureRequest$$serializer() {
    }

    public KSerializer[] childSerializers() {
        KSerializer[] access$get$childSerializers$cp = Command.TakePictureRequest.$childSerializers;
        O o4 = O.f456a;
        return new KSerializer[]{o4, Command$TakePictureRequest$Size$$serializer.INSTANCE, V.f466a, C0026g.f495a, access$get$childSerializers$cp[4], access$get$childSerializers$cp[5], access$get$childSerializers$cp[6], access$get$childSerializers$cp[7], access$get$childSerializers$cp[8], access$get$childSerializers$cp[9], o4, o4, o4, o4, o4, w.n(o4)};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v3, resolved type: com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$CaptureMode} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.hawkshaw.library.datalayer.models.Command.TakePictureRequest deserialize(kotlinx.serialization.encoding.Decoder r29) {
        /*
            r28 = this;
            r0 = r29
            java.lang.String r1 = "decoder"
            Y1.K.n(r0, r1)
            kotlinx.serialization.descriptors.SerialDescriptor r1 = r28.getDescriptor()
            D3.a r0 = r0.a(r1)
            kotlinx.serialization.KSerializer[] r2 = com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.$childSerializers
            r5 = 0
            r6 = 0
            r3 = r5
            r4 = r3
            r8 = r4
            r11 = r8
            r15 = r11
            r12 = r6
            r9 = 0
            r10 = 0
            r14 = 0
            r17 = 1
            r21 = 0
            r22 = 0
            r23 = 0
            r24 = 0
            r25 = 0
            r6 = r15
            r7 = r6
        L_0x002d:
            if (r17 == 0) goto L_0x011b
            r18 = r14
            int r14 = r0.y(r1)
            switch(r14) {
                case -1: goto L_0x0114;
                case 0: goto L_0x0108;
                case 1: goto L_0x00f9;
                case 2: goto L_0x00ee;
                case 3: goto L_0x00e3;
                case 4: goto L_0x00d3;
                case 5: goto L_0x00c4;
                case 6: goto L_0x00b6;
                case 7: goto L_0x00a8;
                case 8: goto L_0x0099;
                case 9: goto L_0x008a;
                case 10: goto L_0x007f;
                case 11: goto L_0x0074;
                case 12: goto L_0x0069;
                case 13: goto L_0x005e;
                case 14: goto L_0x0053;
                case 15: goto L_0x003e;
                default: goto L_0x0038;
            }
        L_0x0038:
            B3.m r0 = new B3.m
            r0.<init>(r14)
            throw r0
        L_0x003e:
            E3.O r14 = E3.O.f456a
            r19 = r12
            r12 = 15
            java.lang.Object r3 = r0.f(r1, r12, r14, r3)
            java.lang.Integer r3 = (java.lang.Integer) r3
            r12 = 32768(0x8000, float:4.5918E-41)
            r9 = r9 | r12
        L_0x004e:
            r14 = r18
        L_0x0050:
            r12 = r19
            goto L_0x002d
        L_0x0053:
            r19 = r12
            r12 = 14
            int r25 = r0.s(r1, r12)
            r9 = r9 | 16384(0x4000, float:2.2959E-41)
            goto L_0x004e
        L_0x005e:
            r19 = r12
            r12 = 13
            int r24 = r0.s(r1, r12)
            r9 = r9 | 8192(0x2000, float:1.148E-41)
            goto L_0x004e
        L_0x0069:
            r19 = r12
            r12 = 12
            int r23 = r0.s(r1, r12)
            r9 = r9 | 4096(0x1000, float:5.74E-42)
            goto L_0x004e
        L_0x0074:
            r19 = r12
            r12 = 11
            int r22 = r0.s(r1, r12)
            r9 = r9 | 2048(0x800, float:2.87E-42)
            goto L_0x004e
        L_0x007f:
            r19 = r12
            r12 = 10
            int r21 = r0.s(r1, r12)
            r9 = r9 | 1024(0x400, float:1.435E-42)
            goto L_0x004e
        L_0x008a:
            r19 = r12
            r12 = 9
            r13 = r2[r12]
            java.lang.Object r4 = r0.l(r1, r12, r13, r4)
            com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Rotation r4 = (com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.Rotation) r4
            r9 = r9 | 512(0x200, float:7.17E-43)
            goto L_0x004e
        L_0x0099:
            r19 = r12
            r12 = 8
            r13 = r2[r12]
            java.lang.Object r8 = r0.l(r1, r12, r13, r8)
            com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$AspectRatio r8 = (com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.AspectRatio) r8
            r9 = r9 | 256(0x100, float:3.59E-43)
            goto L_0x004e
        L_0x00a8:
            r19 = r12
            r12 = 7
            r13 = r2[r12]
            java.lang.Object r7 = r0.l(r1, r12, r13, r7)
            com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$LensFacing r7 = (com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.LensFacing) r7
            r9 = r9 | 128(0x80, float:1.8E-43)
            goto L_0x004e
        L_0x00b6:
            r19 = r12
            r12 = 6
            r13 = r2[r12]
            java.lang.Object r6 = r0.l(r1, r12, r13, r6)
            com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$FlashType r6 = (com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.FlashType) r6
            r9 = r9 | 64
            goto L_0x004e
        L_0x00c4:
            r19 = r12
            r12 = 5
            r13 = r2[r12]
            java.lang.Object r5 = r0.l(r1, r12, r13, r5)
            com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$FlashMode r5 = (com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.FlashMode) r5
            r9 = r9 | 32
            goto L_0x004e
        L_0x00d3:
            r19 = r12
            r12 = 4
            r13 = r2[r12]
            java.lang.Object r12 = r0.l(r1, r12, r13, r15)
            r15 = r12
            com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$CaptureMode r15 = (com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.CaptureMode) r15
            r9 = r9 | 16
            goto L_0x004e
        L_0x00e3:
            r19 = r12
            r12 = 3
            boolean r14 = r0.i(r1, r12)
            r9 = r9 | 8
            goto L_0x0050
        L_0x00ee:
            r12 = 2
            long r12 = r0.D(r1, r12)
            r9 = r9 | 4
            r14 = r18
            goto L_0x002d
        L_0x00f9:
            r19 = r12
            com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Size$$serializer r12 = com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Size$$serializer.INSTANCE
            r13 = 1
            java.lang.Object r11 = r0.l(r1, r13, r12, r11)
            com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Size r11 = (com.hawkshaw.library.datalayer.models.Command.TakePictureRequest.Size) r11
            r9 = r9 | 2
            goto L_0x004e
        L_0x0108:
            r19 = r12
            r12 = 0
            r13 = 1
            int r10 = r0.s(r1, r12)
            r9 = r9 | 1
            goto L_0x004e
        L_0x0114:
            r19 = r12
            r12 = 0
            r17 = r12
            goto L_0x004e
        L_0x011b:
            r19 = r12
            r18 = r14
            r0.c(r1)
            com.hawkshaw.library.datalayer.models.Command$TakePictureRequest r0 = new com.hawkshaw.library.datalayer.models.Command$TakePictureRequest
            r1 = r8
            r8 = r0
            r27 = 0
            r16 = r5
            r17 = r6
            r18 = r7
            r19 = r1
            r20 = r4
            r26 = r3
            r8.<init>(r9, r10, r11, r12, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$$serializer.deserialize(kotlinx.serialization.encoding.Decoder):com.hawkshaw.library.datalayer.models.Command$TakePictureRequest");
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, Command.TakePictureRequest takePictureRequest) {
        K.n(encoder, "encoder");
        K.n(takePictureRequest, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        b a5 = encoder.a(descriptor2);
        Command.TakePictureRequest.write$Self$library_release(takePictureRequest, a5, descriptor2);
        a5.c(descriptor2);
    }

    public KSerializer[] typeParametersSerializers() {
        return C0029h0.f501b;
    }
}
