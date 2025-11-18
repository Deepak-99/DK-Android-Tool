package com.hawkshaw.library.datalayer.models;

import D3.b;
import E3.C0026g;
import E3.C0029h0;
import E3.H;
import E3.O;
import E3.u0;
import Y1.K;
import com.hawkshaw.library.datalayer.models.Command;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;

public final class Command$FileRequest$$serializer implements H {
    public static final Command$FileRequest$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        Command$FileRequest$$serializer command$FileRequest$$serializer = new Command$FileRequest$$serializer();
        INSTANCE = command$FileRequest$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.hawkshaw.library.datalayer.models.Command.FileRequest", command$FileRequest$$serializer, 5);
        pluginGeneratedSerialDescriptor.l("path", false);
        pluginGeneratedSerialDescriptor.l("source", false);
        pluginGeneratedSerialDescriptor.l("medium", true);
        pluginGeneratedSerialDescriptor.l("buffer", true);
        pluginGeneratedSerialDescriptor.l("zip", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private Command$FileRequest$$serializer() {
    }

    public KSerializer[] childSerializers() {
        KSerializer[] access$get$childSerializers$cp = Command.FileRequest.$childSerializers;
        return new KSerializer[]{u0.f536a, access$get$childSerializers$cp[1], access$get$childSerializers$cp[2], O.f456a, C0026g.f495a};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v3, resolved type: com.hawkshaw.library.datalayer.models.Command$FileRequest$Source} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v3, resolved type: com.hawkshaw.library.datalayer.models.Command$FileRequest$Medium} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.hawkshaw.library.datalayer.models.Command.FileRequest deserialize(kotlinx.serialization.encoding.Decoder r14) {
        /*
            r13 = this;
            java.lang.String r0 = "decoder"
            Y1.K.n(r14, r0)
            kotlinx.serialization.descriptors.SerialDescriptor r0 = r13.getDescriptor()
            D3.a r14 = r14.a(r0)
            kotlinx.serialization.KSerializer[] r1 = com.hawkshaw.library.datalayer.models.Command.FileRequest.$childSerializers
            r2 = 1
            r3 = 0
            r4 = 0
            r6 = r3
            r10 = r6
            r11 = r10
            r7 = r4
            r8 = r7
            r9 = r8
            r4 = r2
        L_0x001b:
            if (r4 == 0) goto L_0x0066
            int r5 = r14.y(r0)
            r12 = -1
            if (r5 == r12) goto L_0x0064
            if (r5 == 0) goto L_0x005d
            if (r5 == r2) goto L_0x0051
            r12 = 2
            if (r5 == r12) goto L_0x0045
            r12 = 3
            if (r5 == r12) goto L_0x003e
            r11 = 4
            if (r5 != r11) goto L_0x0038
            boolean r11 = r14.i(r0, r11)
            r6 = r6 | 16
            goto L_0x001b
        L_0x0038:
            B3.m r14 = new B3.m
            r14.<init>(r5)
            throw r14
        L_0x003e:
            int r10 = r14.s(r0, r12)
            r6 = r6 | 8
            goto L_0x001b
        L_0x0045:
            r5 = r1[r12]
            java.lang.Object r5 = r14.l(r0, r12, r5, r9)
            r9 = r5
            com.hawkshaw.library.datalayer.models.Command$FileRequest$Medium r9 = (com.hawkshaw.library.datalayer.models.Command.FileRequest.Medium) r9
            r6 = r6 | 4
            goto L_0x001b
        L_0x0051:
            r5 = r1[r2]
            java.lang.Object r5 = r14.l(r0, r2, r5, r8)
            r8 = r5
            com.hawkshaw.library.datalayer.models.Command$FileRequest$Source r8 = (com.hawkshaw.library.datalayer.models.Command.FileRequest.Source) r8
            r6 = r6 | 2
            goto L_0x001b
        L_0x005d:
            java.lang.String r7 = r14.q(r0, r3)
            r6 = r6 | 1
            goto L_0x001b
        L_0x0064:
            r4 = r3
            goto L_0x001b
        L_0x0066:
            r14.c(r0)
            com.hawkshaw.library.datalayer.models.Command$FileRequest r14 = new com.hawkshaw.library.datalayer.models.Command$FileRequest
            r12 = 0
            r5 = r14
            r5.<init>((int) r6, (java.lang.String) r7, (com.hawkshaw.library.datalayer.models.Command.FileRequest.Source) r8, (com.hawkshaw.library.datalayer.models.Command.FileRequest.Medium) r9, (int) r10, (boolean) r11, (E3.q0) r12)
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.models.Command$FileRequest$$serializer.deserialize(kotlinx.serialization.encoding.Decoder):com.hawkshaw.library.datalayer.models.Command$FileRequest");
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, Command.FileRequest fileRequest) {
        K.n(encoder, "encoder");
        K.n(fileRequest, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        b a5 = encoder.a(descriptor2);
        Command.FileRequest.write$Self$library_release(fileRequest, a5, descriptor2);
        a5.c(descriptor2);
    }

    public KSerializer[] typeParametersSerializers() {
        return C0029h0.f501b;
    }
}
