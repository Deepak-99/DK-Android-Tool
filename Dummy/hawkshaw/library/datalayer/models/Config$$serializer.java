package com.hawkshaw.library.datalayer.models;

import D3.b;
import E3.C0026g;
import E3.C0029h0;
import E3.H;
import E3.u0;
import Y1.K;
import androidx.core.app.NotificationCompat;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;

public final class Config$$serializer implements H {
    public static final Config$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        Config$$serializer config$$serializer = new Config$$serializer();
        INSTANCE = config$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.hawkshaw.library.datalayer.models.Config", config$$serializer, 4);
        pluginGeneratedSerialDescriptor.l(NotificationCompat.CATEGORY_EMAIL, false);
        pluginGeneratedSerialDescriptor.l("hide_app_icon", false);
        pluginGeneratedSerialDescriptor.l("firebase", false);
        pluginGeneratedSerialDescriptor.l("default_remote_config", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private Config$$serializer() {
    }

    public KSerializer[] childSerializers() {
        return new KSerializer[]{u0.f536a, C0026g.f495a, Config$Firebase$$serializer.INSTANCE, Config.$childSerializers[3]};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v3, resolved type: com.hawkshaw.library.datalayer.models.Config$Firebase} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v3, resolved type: java.util.Map} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.hawkshaw.library.datalayer.models.Config deserialize(kotlinx.serialization.encoding.Decoder r13) {
        /*
            r12 = this;
            java.lang.String r0 = "decoder"
            Y1.K.n(r13, r0)
            kotlinx.serialization.descriptors.SerialDescriptor r0 = r12.getDescriptor()
            D3.a r13 = r13.a(r0)
            kotlinx.serialization.KSerializer[] r1 = com.hawkshaw.library.datalayer.models.Config.$childSerializers
            r2 = 1
            r3 = 0
            r4 = 0
            r6 = r3
            r8 = r6
            r7 = r4
            r9 = r7
            r10 = r9
            r4 = r2
        L_0x001a:
            if (r4 == 0) goto L_0x005b
            int r5 = r13.y(r0)
            r11 = -1
            if (r5 == r11) goto L_0x0059
            if (r5 == 0) goto L_0x0052
            if (r5 == r2) goto L_0x004b
            r11 = 2
            if (r5 == r11) goto L_0x003f
            r11 = 3
            if (r5 != r11) goto L_0x0039
            r5 = r1[r11]
            java.lang.Object r5 = r13.l(r0, r11, r5, r10)
            r10 = r5
            java.util.Map r10 = (java.util.Map) r10
            r6 = r6 | 8
            goto L_0x001a
        L_0x0039:
            B3.m r13 = new B3.m
            r13.<init>(r5)
            throw r13
        L_0x003f:
            com.hawkshaw.library.datalayer.models.Config$Firebase$$serializer r5 = com.hawkshaw.library.datalayer.models.Config$Firebase$$serializer.INSTANCE
            java.lang.Object r5 = r13.l(r0, r11, r5, r9)
            r9 = r5
            com.hawkshaw.library.datalayer.models.Config$Firebase r9 = (com.hawkshaw.library.datalayer.models.Config.Firebase) r9
            r6 = r6 | 4
            goto L_0x001a
        L_0x004b:
            boolean r8 = r13.i(r0, r2)
            r6 = r6 | 2
            goto L_0x001a
        L_0x0052:
            java.lang.String r7 = r13.q(r0, r3)
            r6 = r6 | 1
            goto L_0x001a
        L_0x0059:
            r4 = r3
            goto L_0x001a
        L_0x005b:
            r13.c(r0)
            com.hawkshaw.library.datalayer.models.Config r13 = new com.hawkshaw.library.datalayer.models.Config
            r11 = 0
            r5 = r13
            r5.<init>(r6, r7, r8, r9, r10, r11)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.models.Config$$serializer.deserialize(kotlinx.serialization.encoding.Decoder):com.hawkshaw.library.datalayer.models.Config");
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, Config config) {
        K.n(encoder, "encoder");
        K.n(config, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        b a5 = encoder.a(descriptor2);
        Config.write$Self$library_release(config, a5, descriptor2);
        a5.c(descriptor2);
    }

    public KSerializer[] typeParametersSerializers() {
        return C0029h0.f501b;
    }
}
