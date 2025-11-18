package com.hawkshaw.library.datalayer.models;

import D3.b;
import E3.C0029h0;
import E3.H;
import E3.V;
import Y1.K;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import w3.w;

public final class SocketCommandResponse$$serializer implements H {
    public static final SocketCommandResponse$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        SocketCommandResponse$$serializer socketCommandResponse$$serializer = new SocketCommandResponse$$serializer();
        INSTANCE = socketCommandResponse$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.hawkshaw.library.datalayer.models.SocketCommandResponse", socketCommandResponse$$serializer, 3);
        pluginGeneratedSerialDescriptor.l("type", false);
        pluginGeneratedSerialDescriptor.l("sent_time", true);
        pluginGeneratedSerialDescriptor.l("shell_response", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private SocketCommandResponse$$serializer() {
    }

    public KSerializer[] childSerializers() {
        return new KSerializer[]{SocketCommandResponse.$childSerializers[0], V.f466a, w.n(SocketCommandResponse$ShellResponse$$serializer.INSTANCE)};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v3, resolved type: com.hawkshaw.library.datalayer.models.SocketCommandResponse$Type} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v3, resolved type: com.hawkshaw.library.datalayer.models.SocketCommandResponse$ShellResponse} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.hawkshaw.library.datalayer.models.SocketCommandResponse deserialize(kotlinx.serialization.encoding.Decoder r15) {
        /*
            r14 = this;
            java.lang.String r0 = "decoder"
            Y1.K.n(r15, r0)
            kotlinx.serialization.descriptors.SerialDescriptor r0 = r14.getDescriptor()
            D3.a r15 = r15.a(r0)
            kotlinx.serialization.KSerializer[] r1 = com.hawkshaw.library.datalayer.models.SocketCommandResponse.$childSerializers
            r2 = 1
            r3 = 0
            r4 = 0
            r5 = 0
            r8 = r3
            r9 = r4
            r12 = r9
            r10 = r5
            r4 = r2
        L_0x001b:
            if (r4 == 0) goto L_0x0052
            int r5 = r15.y(r0)
            r6 = -1
            if (r5 == r6) goto L_0x0050
            if (r5 == 0) goto L_0x0044
            if (r5 == r2) goto L_0x003d
            r6 = 2
            if (r5 != r6) goto L_0x0037
            com.hawkshaw.library.datalayer.models.SocketCommandResponse$ShellResponse$$serializer r5 = com.hawkshaw.library.datalayer.models.SocketCommandResponse$ShellResponse$$serializer.INSTANCE
            java.lang.Object r5 = r15.f(r0, r6, r5, r12)
            r12 = r5
            com.hawkshaw.library.datalayer.models.SocketCommandResponse$ShellResponse r12 = (com.hawkshaw.library.datalayer.models.SocketCommandResponse.ShellResponse) r12
            r8 = r8 | 4
            goto L_0x001b
        L_0x0037:
            B3.m r15 = new B3.m
            r15.<init>(r5)
            throw r15
        L_0x003d:
            long r10 = r15.D(r0, r2)
            r8 = r8 | 2
            goto L_0x001b
        L_0x0044:
            r5 = r1[r3]
            java.lang.Object r5 = r15.l(r0, r3, r5, r9)
            r9 = r5
            com.hawkshaw.library.datalayer.models.SocketCommandResponse$Type r9 = (com.hawkshaw.library.datalayer.models.SocketCommandResponse.Type) r9
            r8 = r8 | 1
            goto L_0x001b
        L_0x0050:
            r4 = r3
            goto L_0x001b
        L_0x0052:
            r15.c(r0)
            com.hawkshaw.library.datalayer.models.SocketCommandResponse r15 = new com.hawkshaw.library.datalayer.models.SocketCommandResponse
            r13 = 0
            r7 = r15
            r7.<init>((int) r8, (com.hawkshaw.library.datalayer.models.SocketCommandResponse.Type) r9, (long) r10, (com.hawkshaw.library.datalayer.models.SocketCommandResponse.ShellResponse) r12, (E3.q0) r13)
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.models.SocketCommandResponse$$serializer.deserialize(kotlinx.serialization.encoding.Decoder):com.hawkshaw.library.datalayer.models.SocketCommandResponse");
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, SocketCommandResponse socketCommandResponse) {
        K.n(encoder, "encoder");
        K.n(socketCommandResponse, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        b a5 = encoder.a(descriptor2);
        SocketCommandResponse.write$Self$library_release(socketCommandResponse, a5, descriptor2);
        a5.c(descriptor2);
    }

    public KSerializer[] typeParametersSerializers() {
        return C0029h0.f501b;
    }
}
