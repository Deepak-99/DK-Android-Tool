package com.hawkshaw.library.datalayer.room.files;

import D3.b;
import E3.C0029h0;
import E3.H;
import E3.O;
import E3.V;
import E3.u0;
import Y1.K;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;

public final class PushFileTaskEntity$$serializer implements H {
    public static final PushFileTaskEntity$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        PushFileTaskEntity$$serializer pushFileTaskEntity$$serializer = new PushFileTaskEntity$$serializer();
        INSTANCE = pushFileTaskEntity$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.hawkshaw.library.datalayer.room.files.PushFileTaskEntity", pushFileTaskEntity$$serializer, 9);
        pluginGeneratedSerialDescriptor.l("path", false);
        pluginGeneratedSerialDescriptor.l("source", false);
        pluginGeneratedSerialDescriptor.l("medium", false);
        pluginGeneratedSerialDescriptor.l("buffer", true);
        pluginGeneratedSerialDescriptor.l("length", true);
        pluginGeneratedSerialDescriptor.l("timestamp", true);
        pluginGeneratedSerialDescriptor.l("lastPushTimestamp", true);
        pluginGeneratedSerialDescriptor.l("priority", true);
        pluginGeneratedSerialDescriptor.l("id", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private PushFileTaskEntity$$serializer() {
    }

    public KSerializer[] childSerializers() {
        KSerializer[] access$get$childSerializers$cp = PushFileTaskEntity.$childSerializers;
        KSerializer kSerializer = access$get$childSerializers$cp[1];
        KSerializer kSerializer2 = access$get$childSerializers$cp[2];
        O o4 = O.f456a;
        V v4 = V.f466a;
        return new KSerializer[]{u0.f536a, kSerializer, kSerializer2, o4, v4, v4, v4, o4, o4};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v3, resolved type: com.hawkshaw.library.datalayer.models.Command$FileRequest$Source} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v3, resolved type: com.hawkshaw.library.datalayer.models.Command$FileRequest$Medium} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.hawkshaw.library.datalayer.room.files.PushFileTaskEntity deserialize(kotlinx.serialization.encoding.Decoder r24) {
        /*
            r23 = this;
            r0 = r24
            java.lang.String r1 = "decoder"
            Y1.K.n(r0, r1)
            kotlinx.serialization.descriptors.SerialDescriptor r1 = r23.getDescriptor()
            D3.a r0 = r0.a(r1)
            kotlinx.serialization.KSerializer[] r2 = com.hawkshaw.library.datalayer.room.files.PushFileTaskEntity.$childSerializers
            r3 = 1
            r4 = 0
            r5 = 0
            r6 = 0
            r9 = r4
            r13 = r9
            r20 = r13
            r21 = r20
            r10 = r5
            r11 = r10
            r12 = r11
            r14 = r6
            r16 = r14
            r18 = r16
            r5 = r3
        L_0x0027:
            if (r5 == 0) goto L_0x0089
            int r6 = r0.y(r1)
            switch(r6) {
                case -1: goto L_0x0087;
                case 0: goto L_0x0080;
                case 1: goto L_0x0074;
                case 2: goto L_0x0067;
                case 3: goto L_0x005f;
                case 4: goto L_0x0057;
                case 5: goto L_0x004f;
                case 6: goto L_0x0047;
                case 7: goto L_0x003f;
                case 8: goto L_0x0036;
                default: goto L_0x0030;
            }
        L_0x0030:
            B3.m r0 = new B3.m
            r0.<init>(r6)
            throw r0
        L_0x0036:
            r6 = 8
            int r21 = r0.s(r1, r6)
            r9 = r9 | 256(0x100, float:3.59E-43)
            goto L_0x0027
        L_0x003f:
            r6 = 7
            int r20 = r0.s(r1, r6)
            r9 = r9 | 128(0x80, float:1.8E-43)
            goto L_0x0027
        L_0x0047:
            r6 = 6
            long r18 = r0.D(r1, r6)
            r9 = r9 | 64
            goto L_0x0027
        L_0x004f:
            r6 = 5
            long r16 = r0.D(r1, r6)
            r9 = r9 | 32
            goto L_0x0027
        L_0x0057:
            r6 = 4
            long r14 = r0.D(r1, r6)
            r9 = r9 | 16
            goto L_0x0027
        L_0x005f:
            r6 = 3
            int r13 = r0.s(r1, r6)
            r9 = r9 | 8
            goto L_0x0027
        L_0x0067:
            r6 = 2
            r7 = r2[r6]
            java.lang.Object r6 = r0.l(r1, r6, r7, r12)
            r12 = r6
            com.hawkshaw.library.datalayer.models.Command$FileRequest$Medium r12 = (com.hawkshaw.library.datalayer.models.Command.FileRequest.Medium) r12
            r9 = r9 | 4
            goto L_0x0027
        L_0x0074:
            r6 = r2[r3]
            java.lang.Object r6 = r0.l(r1, r3, r6, r11)
            r11 = r6
            com.hawkshaw.library.datalayer.models.Command$FileRequest$Source r11 = (com.hawkshaw.library.datalayer.models.Command.FileRequest.Source) r11
            r9 = r9 | 2
            goto L_0x0027
        L_0x0080:
            java.lang.String r10 = r0.q(r1, r4)
            r9 = r9 | 1
            goto L_0x0027
        L_0x0087:
            r5 = r4
            goto L_0x0027
        L_0x0089:
            r0.c(r1)
            com.hawkshaw.library.datalayer.room.files.PushFileTaskEntity r0 = new com.hawkshaw.library.datalayer.room.files.PushFileTaskEntity
            r22 = 0
            r8 = r0
            r8.<init>(r9, r10, r11, r12, r13, r14, r16, r18, r20, r21, r22)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.room.files.PushFileTaskEntity$$serializer.deserialize(kotlinx.serialization.encoding.Decoder):com.hawkshaw.library.datalayer.room.files.PushFileTaskEntity");
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, PushFileTaskEntity pushFileTaskEntity) {
        K.n(encoder, "encoder");
        K.n(pushFileTaskEntity, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        b a5 = encoder.a(descriptor2);
        PushFileTaskEntity.write$Self$library_release(pushFileTaskEntity, a5, descriptor2);
        a5.c(descriptor2);
    }

    public KSerializer[] typeParametersSerializers() {
        return C0029h0.f501b;
    }
}
