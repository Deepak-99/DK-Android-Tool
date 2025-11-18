package com.hawkshaw.library.datalayer.models;

import D3.b;
import E3.C0026g;
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

public final class AppFile$$serializer implements H {
    public static final AppFile$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        AppFile$$serializer appFile$$serializer = new AppFile$$serializer();
        INSTANCE = appFile$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.hawkshaw.library.datalayer.models.AppFile", appFile$$serializer, 7);
        pluginGeneratedSerialDescriptor.l("name", false);
        pluginGeneratedSerialDescriptor.l("path", false);
        pluginGeneratedSerialDescriptor.l("mime", false);
        pluginGeneratedSerialDescriptor.l("can_read", false);
        pluginGeneratedSerialDescriptor.l("can_write", false);
        pluginGeneratedSerialDescriptor.l("last_modified", false);
        pluginGeneratedSerialDescriptor.l("length", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private AppFile$$serializer() {
    }

    public KSerializer[] childSerializers() {
        u0 u0Var = u0.f536a;
        KSerializer n4 = w.n(u0Var);
        C0026g gVar = C0026g.f495a;
        V v4 = V.f466a;
        return new KSerializer[]{u0Var, u0Var, n4, gVar, gVar, v4, v4};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v3, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.hawkshaw.library.datalayer.models.AppFile deserialize(kotlinx.serialization.encoding.Decoder r20) {
        /*
            r19 = this;
            r0 = r20
            java.lang.String r1 = "decoder"
            Y1.K.n(r0, r1)
            kotlinx.serialization.descriptors.SerialDescriptor r1 = r19.getDescriptor()
            D3.a r0 = r0.a(r1)
            r2 = 1
            r3 = 0
            r4 = 0
            r5 = 0
            r8 = r3
            r12 = r8
            r13 = r12
            r9 = r4
            r10 = r9
            r11 = r10
            r14 = r5
            r16 = r14
            r4 = r2
        L_0x001e:
            if (r4 == 0) goto L_0x006a
            int r5 = r0.y(r1)
            switch(r5) {
                case -1: goto L_0x0068;
                case 0: goto L_0x0061;
                case 1: goto L_0x005a;
                case 2: goto L_0x004d;
                case 3: goto L_0x0045;
                case 4: goto L_0x003d;
                case 5: goto L_0x0035;
                case 6: goto L_0x002d;
                default: goto L_0x0027;
            }
        L_0x0027:
            B3.m r0 = new B3.m
            r0.<init>(r5)
            throw r0
        L_0x002d:
            r5 = 6
            long r16 = r0.D(r1, r5)
            r8 = r8 | 64
            goto L_0x001e
        L_0x0035:
            r5 = 5
            long r14 = r0.D(r1, r5)
            r8 = r8 | 32
            goto L_0x001e
        L_0x003d:
            r5 = 4
            boolean r13 = r0.i(r1, r5)
            r8 = r8 | 16
            goto L_0x001e
        L_0x0045:
            r5 = 3
            boolean r12 = r0.i(r1, r5)
            r8 = r8 | 8
            goto L_0x001e
        L_0x004d:
            E3.u0 r5 = E3.u0.f536a
            r6 = 2
            java.lang.Object r5 = r0.f(r1, r6, r5, r11)
            r11 = r5
            java.lang.String r11 = (java.lang.String) r11
            r8 = r8 | 4
            goto L_0x001e
        L_0x005a:
            java.lang.String r10 = r0.q(r1, r2)
            r8 = r8 | 2
            goto L_0x001e
        L_0x0061:
            java.lang.String r9 = r0.q(r1, r3)
            r8 = r8 | 1
            goto L_0x001e
        L_0x0068:
            r4 = r3
            goto L_0x001e
        L_0x006a:
            r0.c(r1)
            com.hawkshaw.library.datalayer.models.AppFile r0 = new com.hawkshaw.library.datalayer.models.AppFile
            r18 = 0
            r7 = r0
            r7.<init>(r8, r9, r10, r11, r12, r13, r14, r16, r18)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.models.AppFile$$serializer.deserialize(kotlinx.serialization.encoding.Decoder):com.hawkshaw.library.datalayer.models.AppFile");
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, AppFile appFile) {
        K.n(encoder, "encoder");
        K.n(appFile, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        b a5 = encoder.a(descriptor2);
        AppFile.write$Self$library_release(appFile, a5, descriptor2);
        a5.c(descriptor2);
    }

    public KSerializer[] typeParametersSerializers() {
        return C0029h0.f501b;
    }
}
