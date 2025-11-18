package com.hawkshaw.library.datalayer.models;

import D3.b;
import E3.C0029h0;
import E3.H;
import Y1.K;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import p3.q;

public final class PushFileExplorerWalkRequest$$serializer implements H {
    public static final PushFileExplorerWalkRequest$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        PushFileExplorerWalkRequest$$serializer pushFileExplorerWalkRequest$$serializer = new PushFileExplorerWalkRequest$$serializer();
        INSTANCE = pushFileExplorerWalkRequest$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.hawkshaw.library.datalayer.models.PushFileExplorerWalkRequest", pushFileExplorerWalkRequest$$serializer, 1);
        pluginGeneratedSerialDescriptor.l("directory", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private PushFileExplorerWalkRequest$$serializer() {
    }

    public KSerializer[] childSerializers() {
        return new KSerializer[]{Directory$$serializer.INSTANCE};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v3, resolved type: com.hawkshaw.library.datalayer.models.Directory} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.hawkshaw.library.datalayer.models.PushFileExplorerWalkRequest deserialize(kotlinx.serialization.encoding.Decoder r10) {
        /*
            r9 = this;
            java.lang.String r0 = "decoder"
            Y1.K.n(r10, r0)
            kotlinx.serialization.descriptors.SerialDescriptor r0 = r9.getDescriptor()
            D3.a r10 = r10.a(r0)
            r1 = 1
            r2 = 0
            r3 = 0
            r4 = r1
            r5 = r2
            r6 = r3
        L_0x0013:
            if (r4 == 0) goto L_0x0031
            int r7 = r10.y(r0)
            r8 = -1
            if (r7 == r8) goto L_0x002f
            if (r7 != 0) goto L_0x0029
            com.hawkshaw.library.datalayer.models.Directory$$serializer r5 = com.hawkshaw.library.datalayer.models.Directory$$serializer.INSTANCE
            java.lang.Object r5 = r10.l(r0, r2, r5, r6)
            r6 = r5
            com.hawkshaw.library.datalayer.models.Directory r6 = (com.hawkshaw.library.datalayer.models.Directory) r6
            r5 = r1
            goto L_0x0013
        L_0x0029:
            B3.m r10 = new B3.m
            r10.<init>(r7)
            throw r10
        L_0x002f:
            r4 = r2
            goto L_0x0013
        L_0x0031:
            r10.c(r0)
            com.hawkshaw.library.datalayer.models.PushFileExplorerWalkRequest r10 = new com.hawkshaw.library.datalayer.models.PushFileExplorerWalkRequest
            r10.<init>(r5, r6, r3)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.models.PushFileExplorerWalkRequest$$serializer.deserialize(kotlinx.serialization.encoding.Decoder):com.hawkshaw.library.datalayer.models.PushFileExplorerWalkRequest");
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, PushFileExplorerWalkRequest pushFileExplorerWalkRequest) {
        K.n(encoder, "encoder");
        K.n(pushFileExplorerWalkRequest, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        b a5 = encoder.a(descriptor2);
        ((q) a5).e0(descriptor2, 0, Directory$$serializer.INSTANCE, pushFileExplorerWalkRequest.directory);
        a5.c(descriptor2);
    }

    public KSerializer[] typeParametersSerializers() {
        return C0029h0.f501b;
    }
}
