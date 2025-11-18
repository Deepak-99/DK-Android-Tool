package com.hawkshaw.library.features.media.files;

import D3.b;
import E3.C0029h0;
import E3.H;
import E3.O;
import E3.V;
import E3.u0;
import Y1.K;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import w3.w;

public final class MediaStoreFile$$serializer implements H {
    public static final MediaStoreFile$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        MediaStoreFile$$serializer mediaStoreFile$$serializer = new MediaStoreFile$$serializer();
        INSTANCE = mediaStoreFile$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.hawkshaw.library.features.media.files.MediaStoreFile", mediaStoreFile$$serializer, 16);
        pluginGeneratedSerialDescriptor.l("id", false);
        pluginGeneratedSerialDescriptor.l("bucket_name", false);
        pluginGeneratedSerialDescriptor.l("bucket_id", false);
        pluginGeneratedSerialDescriptor.l("content_uri", false);
        pluginGeneratedSerialDescriptor.l("data", false);
        pluginGeneratedSerialDescriptor.l("date_added", false);
        pluginGeneratedSerialDescriptor.l("date_modified", false);
        pluginGeneratedSerialDescriptor.l("document_id", false);
        pluginGeneratedSerialDescriptor.l("mime", false);
        pluginGeneratedSerialDescriptor.l("name", false);
        pluginGeneratedSerialDescriptor.l("owner_package_name", false);
        pluginGeneratedSerialDescriptor.l("relative_path", false);
        pluginGeneratedSerialDescriptor.l("size", false);
        pluginGeneratedSerialDescriptor.l("title", false);
        pluginGeneratedSerialDescriptor.l("media_type", false);
        pluginGeneratedSerialDescriptor.l("parent", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private MediaStoreFile$$serializer() {
    }

    public KSerializer[] childSerializers() {
        V v4 = V.f466a;
        u0 u0Var = u0.f536a;
        return new KSerializer[]{v4, w.n(u0Var), w.n(u0Var), w.n(u0Var), w.n(u0Var), w.n(v4), w.n(v4), w.n(u0Var), w.n(u0Var), w.n(u0Var), w.n(u0Var), w.n(u0Var), w.n(O.f456a), w.n(u0Var), w.n(u0Var), w.n(u0Var)};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v5, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v5, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v12, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v14, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v13, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v16, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v20, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v3, resolved type: java.lang.Long} */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x00f8, code lost:
        r2 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0110, code lost:
        r3 = r23;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x012d, code lost:
        r12 = r22;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x014c, code lost:
        r14 = r2;
        r13 = r3;
        r11 = r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0049, code lost:
        r13 = r22;
        r14 = r23;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.hawkshaw.library.features.media.files.MediaStoreFile deserialize(kotlinx.serialization.encoding.Decoder r31) {
        /*
            r30 = this;
            r0 = r31
            java.lang.String r1 = "decoder"
            Y1.K.n(r0, r1)
            kotlinx.serialization.descriptors.SerialDescriptor r1 = r30.getDescriptor()
            D3.a r0 = r0.a(r1)
            r4 = 0
            r5 = 0
            r2 = r4
            r3 = r2
            r7 = r3
            r9 = r7
            r10 = r9
            r11 = r10
            r12 = r11
            r13 = r12
            r14 = r13
            r15 = r14
            r19 = r15
            r20 = r19
            r17 = r5
            r8 = 0
            r21 = 1
            r5 = r20
            r6 = r5
        L_0x0028:
            if (r21 == 0) goto L_0x0188
            r22 = r13
            int r13 = r0.y(r1)
            switch(r13) {
                case -1: goto L_0x0169;
                case 0: goto L_0x0151;
                case 1: goto L_0x0130;
                case 2: goto L_0x0113;
                case 3: goto L_0x00fc;
                case 4: goto L_0x00e4;
                case 5: goto L_0x00d4;
                case 6: goto L_0x00c5;
                case 7: goto L_0x00b7;
                case 8: goto L_0x00a8;
                case 9: goto L_0x0099;
                case 10: goto L_0x008a;
                case 11: goto L_0x007b;
                case 12: goto L_0x006c;
                case 13: goto L_0x005d;
                case 14: goto L_0x004e;
                case 15: goto L_0x0039;
                default: goto L_0x0033;
            }
        L_0x0033:
            B3.m r0 = new B3.m
            r0.<init>(r13)
            throw r0
        L_0x0039:
            E3.u0 r13 = E3.u0.f536a
            r23 = r14
            r14 = 15
            java.lang.Object r12 = r0.f(r1, r14, r13, r12)
            java.lang.String r12 = (java.lang.String) r12
            r13 = 32768(0x8000, float:4.5918E-41)
            r8 = r8 | r13
        L_0x0049:
            r13 = r22
            r14 = r23
            goto L_0x0028
        L_0x004e:
            r23 = r14
            E3.u0 r13 = E3.u0.f536a
            r14 = 14
            java.lang.Object r11 = r0.f(r1, r14, r13, r11)
            java.lang.String r11 = (java.lang.String) r11
            r8 = r8 | 16384(0x4000, float:2.2959E-41)
            goto L_0x0049
        L_0x005d:
            r23 = r14
            E3.u0 r13 = E3.u0.f536a
            r14 = 13
            java.lang.Object r10 = r0.f(r1, r14, r13, r10)
            java.lang.String r10 = (java.lang.String) r10
            r8 = r8 | 8192(0x2000, float:1.148E-41)
            goto L_0x0049
        L_0x006c:
            r23 = r14
            E3.O r13 = E3.O.f456a
            r14 = 12
            java.lang.Object r9 = r0.f(r1, r14, r13, r9)
            java.lang.Integer r9 = (java.lang.Integer) r9
            r8 = r8 | 4096(0x1000, float:5.74E-42)
            goto L_0x0049
        L_0x007b:
            r23 = r14
            E3.u0 r13 = E3.u0.f536a
            r14 = 11
            java.lang.Object r2 = r0.f(r1, r14, r13, r2)
            java.lang.String r2 = (java.lang.String) r2
            r8 = r8 | 2048(0x800, float:2.87E-42)
            goto L_0x0049
        L_0x008a:
            r23 = r14
            E3.u0 r13 = E3.u0.f536a
            r14 = 10
            java.lang.Object r3 = r0.f(r1, r14, r13, r3)
            java.lang.String r3 = (java.lang.String) r3
            r8 = r8 | 1024(0x400, float:1.435E-42)
            goto L_0x0049
        L_0x0099:
            r23 = r14
            E3.u0 r13 = E3.u0.f536a
            r14 = 9
            java.lang.Object r7 = r0.f(r1, r14, r13, r7)
            java.lang.String r7 = (java.lang.String) r7
            r8 = r8 | 512(0x200, float:7.17E-43)
            goto L_0x0049
        L_0x00a8:
            r23 = r14
            E3.u0 r13 = E3.u0.f536a
            r14 = 8
            java.lang.Object r6 = r0.f(r1, r14, r13, r6)
            java.lang.String r6 = (java.lang.String) r6
            r8 = r8 | 256(0x100, float:3.59E-43)
            goto L_0x0049
        L_0x00b7:
            r23 = r14
            E3.u0 r13 = E3.u0.f536a
            r14 = 7
            java.lang.Object r5 = r0.f(r1, r14, r13, r5)
            java.lang.String r5 = (java.lang.String) r5
            r8 = r8 | 128(0x80, float:1.8E-43)
            goto L_0x0049
        L_0x00c5:
            r23 = r14
            E3.V r13 = E3.V.f466a
            r14 = 6
            java.lang.Object r4 = r0.f(r1, r14, r13, r4)
            java.lang.Long r4 = (java.lang.Long) r4
            r8 = r8 | 64
            goto L_0x0049
        L_0x00d4:
            r23 = r14
            E3.V r13 = E3.V.f466a
            r14 = 5
            java.lang.Object r13 = r0.f(r1, r14, r13, r15)
            r15 = r13
            java.lang.Long r15 = (java.lang.Long) r15
            r8 = r8 | 32
            goto L_0x0049
        L_0x00e4:
            r23 = r14
            E3.u0 r13 = E3.u0.f536a
            r14 = 4
            r24 = r2
            r2 = r23
            java.lang.Object r2 = r0.f(r1, r14, r13, r2)
            r14 = r2
            java.lang.String r14 = (java.lang.String) r14
            r8 = r8 | 16
            r13 = r22
        L_0x00f8:
            r2 = r24
            goto L_0x0028
        L_0x00fc:
            r24 = r2
            r2 = r14
            E3.u0 r13 = E3.u0.f536a
            r14 = 3
            r23 = r3
            r3 = r22
            java.lang.Object r3 = r0.f(r1, r14, r13, r3)
            r13 = r3
            java.lang.String r13 = (java.lang.String) r13
            r8 = r8 | 8
            r14 = r2
        L_0x0110:
            r3 = r23
            goto L_0x00f8
        L_0x0113:
            r24 = r2
            r23 = r3
            r2 = r14
            r3 = r22
            E3.u0 r13 = E3.u0.f536a
            r14 = 2
            r22 = r12
            r12 = r20
            java.lang.Object r12 = r0.f(r1, r14, r13, r12)
            r20 = r12
            java.lang.String r20 = (java.lang.String) r20
            r8 = r8 | 4
            r14 = r2
            r13 = r3
        L_0x012d:
            r12 = r22
            goto L_0x0110
        L_0x0130:
            r24 = r2
            r23 = r3
            r2 = r14
            r3 = r22
            r22 = r12
            r12 = r20
            E3.u0 r13 = E3.u0.f536a
            r16 = r11
            r11 = r19
            r14 = 1
            java.lang.Object r11 = r0.f(r1, r14, r13, r11)
            r19 = r11
            java.lang.String r19 = (java.lang.String) r19
            r8 = r8 | 2
        L_0x014c:
            r14 = r2
            r13 = r3
            r11 = r16
            goto L_0x012d
        L_0x0151:
            r24 = r2
            r23 = r3
            r16 = r11
            r2 = r14
            r11 = r19
            r3 = r22
            r13 = 0
            r14 = 1
            r22 = r12
            r12 = r20
            long r17 = r0.D(r1, r13)
            r8 = r8 | 1
            goto L_0x014c
        L_0x0169:
            r24 = r2
            r23 = r3
            r16 = r11
            r2 = r14
            r11 = r19
            r3 = r22
            r13 = 0
            r14 = 1
            r22 = r12
            r12 = r20
            r14 = r2
            r21 = r13
            r11 = r16
            r12 = r22
            r2 = r24
            r13 = r3
            r3 = r23
            goto L_0x0028
        L_0x0188:
            r24 = r2
            r23 = r3
            r16 = r11
            r22 = r12
            r3 = r13
            r2 = r14
            r11 = r19
            r12 = r20
            r0.c(r1)
            com.hawkshaw.library.features.media.files.MediaStoreFile r0 = new com.hawkshaw.library.features.media.files.MediaStoreFile
            r1 = r7
            r7 = r0
            r26 = 0
            r25 = r9
            r27 = r10
            r9 = r17
            r28 = r16
            r29 = r22
            r16 = r4
            r17 = r5
            r18 = r6
            r19 = r1
            r20 = r23
            r21 = r24
            r22 = r25
            r23 = r27
            r24 = r28
            r25 = r29
            r7.<init>(r8, r9, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.features.media.files.MediaStoreFile$$serializer.deserialize(kotlinx.serialization.encoding.Decoder):com.hawkshaw.library.features.media.files.MediaStoreFile");
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, MediaStoreFile mediaStoreFile) {
        K.n(encoder, "encoder");
        K.n(mediaStoreFile, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        b a5 = encoder.a(descriptor2);
        MediaStoreFile.write$Self$library_release(mediaStoreFile, a5, descriptor2);
        a5.c(descriptor2);
    }

    public KSerializer[] typeParametersSerializers() {
        return C0029h0.f501b;
    }
}
