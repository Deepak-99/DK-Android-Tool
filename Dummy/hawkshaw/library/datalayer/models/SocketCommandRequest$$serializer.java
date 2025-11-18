package com.hawkshaw.library.datalayer.models;

import D3.b;
import E3.C0029h0;
import E3.H;
import E3.V;
import Y1.K;
import com.hawkshaw.library.datalayer.network.twirp.SocketCommandTypeSerializer;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import w3.w;

public final class SocketCommandRequest$$serializer implements H {
    public static final SocketCommandRequest$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        SocketCommandRequest$$serializer socketCommandRequest$$serializer = new SocketCommandRequest$$serializer();
        INSTANCE = socketCommandRequest$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.hawkshaw.library.datalayer.models.SocketCommandRequest", socketCommandRequest$$serializer, 4);
        pluginGeneratedSerialDescriptor.l("type", false);
        pluginGeneratedSerialDescriptor.l("sent_time", true);
        pluginGeneratedSerialDescriptor.l("root_command_request", true);
        pluginGeneratedSerialDescriptor.l("shell_request", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private SocketCommandRequest$$serializer() {
    }

    public KSerializer[] childSerializers() {
        return new KSerializer[]{SocketCommandTypeSerializer.INSTANCE, V.f466a, w.n(SocketCommandRequest$RootCommandRequest$$serializer.INSTANCE), w.n(SocketCommandRequest$ShellRequest$$serializer.INSTANCE)};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v3, resolved type: com.hawkshaw.library.datalayer.models.SocketCommandRequest$Type} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v3, resolved type: com.hawkshaw.library.datalayer.models.SocketCommandRequest$RootCommandRequest} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v3, resolved type: com.hawkshaw.library.datalayer.models.SocketCommandRequest$ShellRequest} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.hawkshaw.library.datalayer.models.SocketCommandRequest deserialize(kotlinx.serialization.encoding.Decoder r15) {
        /*
            r14 = this;
            java.lang.String r0 = "decoder"
            Y1.K.n(r15, r0)
            kotlinx.serialization.descriptors.SerialDescriptor r0 = r14.getDescriptor()
            D3.a r15 = r15.a(r0)
            r1 = 1
            r2 = 0
            r3 = 0
            r4 = 0
            r7 = r2
            r8 = r3
            r11 = r8
            r12 = r11
            r9 = r4
            r3 = r1
        L_0x0018:
            if (r3 == 0) goto L_0x005e
            int r4 = r15.y(r0)
            r5 = -1
            if (r4 == r5) goto L_0x005c
            if (r4 == 0) goto L_0x0050
            if (r4 == r1) goto L_0x0049
            r5 = 2
            if (r4 == r5) goto L_0x003d
            r5 = 3
            if (r4 != r5) goto L_0x0037
            com.hawkshaw.library.datalayer.models.SocketCommandRequest$ShellRequest$$serializer r4 = com.hawkshaw.library.datalayer.models.SocketCommandRequest$ShellRequest$$serializer.INSTANCE
            java.lang.Object r4 = r15.f(r0, r5, r4, r12)
            r12 = r4
            com.hawkshaw.library.datalayer.models.SocketCommandRequest$ShellRequest r12 = (com.hawkshaw.library.datalayer.models.SocketCommandRequest.ShellRequest) r12
            r7 = r7 | 8
            goto L_0x0018
        L_0x0037:
            B3.m r15 = new B3.m
            r15.<init>(r4)
            throw r15
        L_0x003d:
            com.hawkshaw.library.datalayer.models.SocketCommandRequest$RootCommandRequest$$serializer r4 = com.hawkshaw.library.datalayer.models.SocketCommandRequest$RootCommandRequest$$serializer.INSTANCE
            java.lang.Object r4 = r15.f(r0, r5, r4, r11)
            r11 = r4
            com.hawkshaw.library.datalayer.models.SocketCommandRequest$RootCommandRequest r11 = (com.hawkshaw.library.datalayer.models.SocketCommandRequest.RootCommandRequest) r11
            r7 = r7 | 4
            goto L_0x0018
        L_0x0049:
            long r9 = r15.D(r0, r1)
            r7 = r7 | 2
            goto L_0x0018
        L_0x0050:
            com.hawkshaw.library.datalayer.network.twirp.SocketCommandTypeSerializer r4 = com.hawkshaw.library.datalayer.network.twirp.SocketCommandTypeSerializer.INSTANCE
            java.lang.Object r4 = r15.l(r0, r2, r4, r8)
            r8 = r4
            com.hawkshaw.library.datalayer.models.SocketCommandRequest$Type r8 = (com.hawkshaw.library.datalayer.models.SocketCommandRequest.Type) r8
            r7 = r7 | 1
            goto L_0x0018
        L_0x005c:
            r3 = r2
            goto L_0x0018
        L_0x005e:
            r15.c(r0)
            com.hawkshaw.library.datalayer.models.SocketCommandRequest r15 = new com.hawkshaw.library.datalayer.models.SocketCommandRequest
            r13 = 0
            r6 = r15
            r6.<init>((int) r7, (com.hawkshaw.library.datalayer.models.SocketCommandRequest.Type) r8, (long) r9, (com.hawkshaw.library.datalayer.models.SocketCommandRequest.RootCommandRequest) r11, (com.hawkshaw.library.datalayer.models.SocketCommandRequest.ShellRequest) r12, (E3.q0) r13)
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.models.SocketCommandRequest$$serializer.deserialize(kotlinx.serialization.encoding.Decoder):com.hawkshaw.library.datalayer.models.SocketCommandRequest");
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, SocketCommandRequest socketCommandRequest) {
        K.n(encoder, "encoder");
        K.n(socketCommandRequest, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        b a5 = encoder.a(descriptor2);
        SocketCommandRequest.write$Self$library_release(socketCommandRequest, a5, descriptor2);
        a5.c(descriptor2);
    }

    public KSerializer[] typeParametersSerializers() {
        return C0029h0.f501b;
    }
}
