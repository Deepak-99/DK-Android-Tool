package com.hawkshaw.library.datalayer.models;

import D3.b;
import E3.C0029h0;
import E3.H;
import E3.V;
import E3.u0;
import Y1.K;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import w3.w;

public final class AppFileV2$$serializer implements H {
    public static final AppFileV2$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        AppFileV2$$serializer appFileV2$$serializer = new AppFileV2$$serializer();
        INSTANCE = appFileV2$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.hawkshaw.library.datalayer.models.AppFileV2", appFileV2$$serializer, 5);
        pluginGeneratedSerialDescriptor.l("name", false);
        pluginGeneratedSerialDescriptor.l("path", false);
        pluginGeneratedSerialDescriptor.l("mime", false);
        pluginGeneratedSerialDescriptor.l("last_modified", false);
        pluginGeneratedSerialDescriptor.l("file_size", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private AppFileV2$$serializer() {
    }

    public KSerializer[] childSerializers() {
        u0 u0Var = u0.f536a;
        return new KSerializer[]{u0Var, u0Var, w.n(u0Var), TimestampSerializer.INSTANCE, V.f466a};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v3, resolved type: com.hawkshaw.library.datalayer.models.Timestamp} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.hawkshaw.library.datalayer.models.AppFileV2 deserialize(kotlinx.serialization.encoding.Decoder r17) {
        /*
            r16 = this;
            r0 = r17
            java.lang.String r1 = "decoder"
            Y1.K.n(r0, r1)
            kotlinx.serialization.descriptors.SerialDescriptor r1 = r16.getDescriptor()
            D3.a r0 = r0.a(r1)
            r2 = 1
            r3 = 0
            r4 = 0
            r5 = 0
            r8 = r3
            r9 = r4
            r10 = r9
            r11 = r10
            r12 = r11
            r13 = r5
            r4 = r2
        L_0x001b:
            if (r4 == 0) goto L_0x0066
            int r5 = r0.y(r1)
            r6 = -1
            if (r5 == r6) goto L_0x0064
            if (r5 == 0) goto L_0x005d
            if (r5 == r2) goto L_0x0056
            r6 = 2
            if (r5 == r6) goto L_0x004a
            r6 = 3
            if (r5 == r6) goto L_0x003e
            r6 = 4
            if (r5 != r6) goto L_0x0038
            long r13 = r0.D(r1, r6)
            r8 = r8 | 16
            goto L_0x001b
        L_0x0038:
            B3.m r0 = new B3.m
            r0.<init>(r5)
            throw r0
        L_0x003e:
            com.hawkshaw.library.datalayer.models.TimestampSerializer r5 = com.hawkshaw.library.datalayer.models.TimestampSerializer.INSTANCE
            java.lang.Object r5 = r0.l(r1, r6, r5, r12)
            r12 = r5
            com.hawkshaw.library.datalayer.models.Timestamp r12 = (com.hawkshaw.library.datalayer.models.Timestamp) r12
            r8 = r8 | 8
            goto L_0x001b
        L_0x004a:
            E3.u0 r5 = E3.u0.f536a
            java.lang.Object r5 = r0.f(r1, r6, r5, r11)
            r11 = r5
            java.lang.String r11 = (java.lang.String) r11
            r8 = r8 | 4
            goto L_0x001b
        L_0x0056:
            java.lang.String r10 = r0.q(r1, r2)
            r8 = r8 | 2
            goto L_0x001b
        L_0x005d:
            java.lang.String r9 = r0.q(r1, r3)
            r8 = r8 | 1
            goto L_0x001b
        L_0x0064:
            r4 = r3
            goto L_0x001b
        L_0x0066:
            r0.c(r1)
            com.hawkshaw.library.datalayer.models.AppFileV2 r0 = new com.hawkshaw.library.datalayer.models.AppFileV2
            r15 = 0
            r7 = r0
            r7.<init>(r8, r9, r10, r11, r12, r13, r15)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.models.AppFileV2$$serializer.deserialize(kotlinx.serialization.encoding.Decoder):com.hawkshaw.library.datalayer.models.AppFileV2");
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, AppFileV2 appFileV2) {
        K.n(encoder, "encoder");
        K.n(appFileV2, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        b a5 = encoder.a(descriptor2);
        AppFileV2.write$Self$library_release(appFileV2, a5, descriptor2);
        a5.c(descriptor2);
    }

    public KSerializer[] typeParametersSerializers() {
        return C0029h0.f501b;
    }
}
