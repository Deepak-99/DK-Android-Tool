package com.hawkshaw.library.datalayer.models;

import D3.b;
import E3.C0026g;
import E3.C0029h0;
import E3.H;
import E3.N;
import E3.O;
import E3.U;
import Y1.K;
import com.hawkshaw.library.datalayer.models.Command;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;

public final class Command$VibrateRequest$$serializer implements H {
    public static final Command$VibrateRequest$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        Command$VibrateRequest$$serializer command$VibrateRequest$$serializer = new Command$VibrateRequest$$serializer();
        INSTANCE = command$VibrateRequest$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.hawkshaw.library.datalayer.models.Command.VibrateRequest", command$VibrateRequest$$serializer, 4);
        pluginGeneratedSerialDescriptor.l("timings", false);
        pluginGeneratedSerialDescriptor.l("amplitudes", true);
        pluginGeneratedSerialDescriptor.l("repeat", true);
        pluginGeneratedSerialDescriptor.l("flash", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private Command$VibrateRequest$$serializer() {
    }

    public KSerializer[] childSerializers() {
        return new KSerializer[]{U.f465c, N.f455c, O.f456a, C0026g.f495a};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v3, resolved type: long[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v3, resolved type: int[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.hawkshaw.library.datalayer.models.Command.VibrateRequest deserialize(kotlinx.serialization.encoding.Decoder r12) {
        /*
            r11 = this;
            java.lang.String r0 = "decoder"
            Y1.K.n(r12, r0)
            kotlinx.serialization.descriptors.SerialDescriptor r0 = r11.getDescriptor()
            D3.a r12 = r12.a(r0)
            r1 = 1
            r2 = 0
            r3 = 0
            r5 = r2
            r8 = r5
            r9 = r8
            r6 = r3
            r7 = r6
            r3 = r1
        L_0x0016:
            if (r3 == 0) goto L_0x0057
            int r4 = r12.y(r0)
            r10 = -1
            if (r4 == r10) goto L_0x0055
            if (r4 == 0) goto L_0x0049
            if (r4 == r1) goto L_0x003d
            r10 = 2
            if (r4 == r10) goto L_0x0036
            r9 = 3
            if (r4 != r9) goto L_0x0030
            boolean r9 = r12.i(r0, r9)
            r5 = r5 | 8
            goto L_0x0016
        L_0x0030:
            B3.m r12 = new B3.m
            r12.<init>(r4)
            throw r12
        L_0x0036:
            int r8 = r12.s(r0, r10)
            r5 = r5 | 4
            goto L_0x0016
        L_0x003d:
            E3.N r4 = E3.N.f455c
            java.lang.Object r4 = r12.l(r0, r1, r4, r7)
            r7 = r4
            int[] r7 = (int[]) r7
            r5 = r5 | 2
            goto L_0x0016
        L_0x0049:
            E3.U r4 = E3.U.f465c
            java.lang.Object r4 = r12.l(r0, r2, r4, r6)
            r6 = r4
            long[] r6 = (long[]) r6
            r5 = r5 | 1
            goto L_0x0016
        L_0x0055:
            r3 = r2
            goto L_0x0016
        L_0x0057:
            r12.c(r0)
            com.hawkshaw.library.datalayer.models.Command$VibrateRequest r12 = new com.hawkshaw.library.datalayer.models.Command$VibrateRequest
            r10 = 0
            r4 = r12
            r4.<init>((int) r5, (long[]) r6, (int[]) r7, (int) r8, (boolean) r9, (E3.q0) r10)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.models.Command$VibrateRequest$$serializer.deserialize(kotlinx.serialization.encoding.Decoder):com.hawkshaw.library.datalayer.models.Command$VibrateRequest");
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, Command.VibrateRequest vibrateRequest) {
        K.n(encoder, "encoder");
        K.n(vibrateRequest, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        b a5 = encoder.a(descriptor2);
        Command.VibrateRequest.write$Self$library_release(vibrateRequest, a5, descriptor2);
        a5.c(descriptor2);
    }

    public KSerializer[] typeParametersSerializers() {
        return C0029h0.f501b;
    }
}
