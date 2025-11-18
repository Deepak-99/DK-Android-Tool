package com.hawkshaw.library.datalayer.models;

import D3.b;
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

public final class Command$ScheduleCommandRequest$$serializer implements H {
    public static final Command$ScheduleCommandRequest$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        Command$ScheduleCommandRequest$$serializer command$ScheduleCommandRequest$$serializer = new Command$ScheduleCommandRequest$$serializer();
        INSTANCE = command$ScheduleCommandRequest$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.hawkshaw.library.datalayer.models.Command.ScheduleCommandRequest", command$ScheduleCommandRequest$$serializer, 4);
        pluginGeneratedSerialDescriptor.l("command", false);
        pluginGeneratedSerialDescriptor.l("trigger_at", false);
        pluginGeneratedSerialDescriptor.l("interval", false);
        pluginGeneratedSerialDescriptor.l("request_code", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private Command$ScheduleCommandRequest$$serializer() {
    }

    public KSerializer[] childSerializers() {
        V v4 = V.f466a;
        return new KSerializer[]{Command$$serializer.INSTANCE, v4, v4, O.f456a};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v3, resolved type: com.hawkshaw.library.datalayer.models.Command} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.hawkshaw.library.datalayer.models.Command.ScheduleCommandRequest deserialize(kotlinx.serialization.encoding.Decoder r17) {
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
            r14 = r8
            r9 = r4
            r10 = r5
            r12 = r10
            r4 = r2
        L_0x001a:
            if (r4 == 0) goto L_0x0056
            int r5 = r0.y(r1)
            r6 = -1
            if (r5 == r6) goto L_0x0054
            if (r5 == 0) goto L_0x0048
            if (r5 == r2) goto L_0x0041
            r6 = 2
            if (r5 == r6) goto L_0x003a
            r6 = 3
            if (r5 != r6) goto L_0x0034
            int r14 = r0.s(r1, r6)
            r8 = r8 | 8
            goto L_0x001a
        L_0x0034:
            B3.m r0 = new B3.m
            r0.<init>(r5)
            throw r0
        L_0x003a:
            long r12 = r0.D(r1, r6)
            r8 = r8 | 4
            goto L_0x001a
        L_0x0041:
            long r10 = r0.D(r1, r2)
            r8 = r8 | 2
            goto L_0x001a
        L_0x0048:
            com.hawkshaw.library.datalayer.models.Command$$serializer r5 = com.hawkshaw.library.datalayer.models.Command$$serializer.INSTANCE
            java.lang.Object r5 = r0.l(r1, r3, r5, r9)
            r9 = r5
            com.hawkshaw.library.datalayer.models.Command r9 = (com.hawkshaw.library.datalayer.models.Command) r9
            r8 = r8 | 1
            goto L_0x001a
        L_0x0054:
            r4 = r3
            goto L_0x001a
        L_0x0056:
            r0.c(r1)
            com.hawkshaw.library.datalayer.models.Command$ScheduleCommandRequest r0 = new com.hawkshaw.library.datalayer.models.Command$ScheduleCommandRequest
            r15 = 0
            r7 = r0
            r7.<init>(r8, r9, r10, r12, r14, r15)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.models.Command$ScheduleCommandRequest$$serializer.deserialize(kotlinx.serialization.encoding.Decoder):com.hawkshaw.library.datalayer.models.Command$ScheduleCommandRequest");
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, Command.ScheduleCommandRequest scheduleCommandRequest) {
        K.n(encoder, "encoder");
        K.n(scheduleCommandRequest, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        b a5 = encoder.a(descriptor2);
        Command.ScheduleCommandRequest.write$Self$library_release(scheduleCommandRequest, a5, descriptor2);
        a5.c(descriptor2);
    }

    public KSerializer[] typeParametersSerializers() {
        return C0029h0.f501b;
    }
}
