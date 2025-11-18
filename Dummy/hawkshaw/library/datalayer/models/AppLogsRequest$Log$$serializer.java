package com.hawkshaw.library.datalayer.models;

import D3.b;
import E3.C0029h0;
import E3.H;
import E3.O;
import E3.V;
import E3.u0;
import Y1.K;
import com.hawkshaw.library.datalayer.models.AppLogsRequest;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;

public final class AppLogsRequest$Log$$serializer implements H {
    public static final AppLogsRequest$Log$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        AppLogsRequest$Log$$serializer appLogsRequest$Log$$serializer = new AppLogsRequest$Log$$serializer();
        INSTANCE = appLogsRequest$Log$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.hawkshaw.library.datalayer.models.AppLogsRequest.Log", appLogsRequest$Log$$serializer, 5);
        pluginGeneratedSerialDescriptor.l("log_level", false);
        pluginGeneratedSerialDescriptor.l("tag", false);
        pluginGeneratedSerialDescriptor.l("message", false);
        pluginGeneratedSerialDescriptor.l("timestamp", false);
        pluginGeneratedSerialDescriptor.l("id", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private AppLogsRequest$Log$$serializer() {
    }

    public KSerializer[] childSerializers() {
        u0 u0Var = u0.f536a;
        return new KSerializer[]{AppLogsRequest.Log.$childSerializers[0], u0Var, u0Var, V.f466a, O.f456a};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v3, resolved type: com.hawkshaw.library.logger.Logger$LogLevel} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.hawkshaw.library.datalayer.models.AppLogsRequest.Log deserialize(kotlinx.serialization.encoding.Decoder r18) {
        /*
            r17 = this;
            r0 = r18
            java.lang.String r1 = "decoder"
            Y1.K.n(r0, r1)
            kotlinx.serialization.descriptors.SerialDescriptor r1 = r17.getDescriptor()
            D3.a r0 = r0.a(r1)
            kotlinx.serialization.KSerializer[] r2 = com.hawkshaw.library.datalayer.models.AppLogsRequest.Log.$childSerializers
            r3 = 1
            r4 = 0
            r5 = 0
            r6 = 0
            r9 = r4
            r15 = r9
            r10 = r5
            r11 = r10
            r12 = r11
            r13 = r6
            r5 = r3
        L_0x001f:
            if (r5 == 0) goto L_0x0065
            int r6 = r0.y(r1)
            r7 = -1
            if (r6 == r7) goto L_0x0063
            if (r6 == 0) goto L_0x0057
            if (r6 == r3) goto L_0x0050
            r7 = 2
            if (r6 == r7) goto L_0x0049
            r7 = 3
            if (r6 == r7) goto L_0x0042
            r7 = 4
            if (r6 != r7) goto L_0x003c
            int r15 = r0.s(r1, r7)
            r9 = r9 | 16
            goto L_0x001f
        L_0x003c:
            B3.m r0 = new B3.m
            r0.<init>(r6)
            throw r0
        L_0x0042:
            long r13 = r0.D(r1, r7)
            r9 = r9 | 8
            goto L_0x001f
        L_0x0049:
            java.lang.String r12 = r0.q(r1, r7)
            r9 = r9 | 4
            goto L_0x001f
        L_0x0050:
            java.lang.String r11 = r0.q(r1, r3)
            r9 = r9 | 2
            goto L_0x001f
        L_0x0057:
            r6 = r2[r4]
            java.lang.Object r6 = r0.l(r1, r4, r6, r10)
            r10 = r6
            com.hawkshaw.library.logger.Logger$LogLevel r10 = (com.hawkshaw.library.logger.Logger.LogLevel) r10
            r9 = r9 | 1
            goto L_0x001f
        L_0x0063:
            r5 = r4
            goto L_0x001f
        L_0x0065:
            r0.c(r1)
            com.hawkshaw.library.datalayer.models.AppLogsRequest$Log r0 = new com.hawkshaw.library.datalayer.models.AppLogsRequest$Log
            r16 = 0
            r8 = r0
            r8.<init>(r9, r10, r11, r12, r13, r15, r16)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.models.AppLogsRequest$Log$$serializer.deserialize(kotlinx.serialization.encoding.Decoder):com.hawkshaw.library.datalayer.models.AppLogsRequest$Log");
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, AppLogsRequest.Log log) {
        K.n(encoder, "encoder");
        K.n(log, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        b a5 = encoder.a(descriptor2);
        AppLogsRequest.Log.write$Self$library_release(log, a5, descriptor2);
        a5.c(descriptor2);
    }

    public KSerializer[] typeParametersSerializers() {
        return C0029h0.f501b;
    }
}
