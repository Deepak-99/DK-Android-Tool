package com.hawkshaw.library.datalayer.models;

import D3.b;
import E3.C0029h0;
import E3.G;
import E3.H;
import E3.O;
import E3.V;
import Y1.K;
import com.hawkshaw.library.datalayer.models.Command;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;

public final class Command$GetLocationRequest$$serializer implements H {
    public static final Command$GetLocationRequest$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        Command$GetLocationRequest$$serializer command$GetLocationRequest$$serializer = new Command$GetLocationRequest$$serializer();
        INSTANCE = command$GetLocationRequest$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.hawkshaw.library.datalayer.models.Command.GetLocationRequest", command$GetLocationRequest$$serializer, 6);
        pluginGeneratedSerialDescriptor.l("priority", true);
        pluginGeneratedSerialDescriptor.l("interval", true);
        pluginGeneratedSerialDescriptor.l("fastest_interval", true);
        pluginGeneratedSerialDescriptor.l("expiration_duration", true);
        pluginGeneratedSerialDescriptor.l("smallest_displacement", true);
        pluginGeneratedSerialDescriptor.l("mPriority", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private Command$GetLocationRequest$$serializer() {
    }

    public KSerializer[] childSerializers() {
        V v4 = V.f466a;
        return new KSerializer[]{Command.GetLocationRequest.$childSerializers[0], v4, v4, v4, G.f438a, O.f456a};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v3, resolved type: com.hawkshaw.library.datalayer.models.Command$GetLocationRequest$Priority} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.hawkshaw.library.datalayer.models.Command.GetLocationRequest deserialize(kotlinx.serialization.encoding.Decoder r22) {
        /*
            r21 = this;
            r0 = r22
            java.lang.String r1 = "decoder"
            Y1.K.n(r0, r1)
            kotlinx.serialization.descriptors.SerialDescriptor r1 = r21.getDescriptor()
            D3.a r0 = r0.a(r1)
            kotlinx.serialization.KSerializer[] r2 = com.hawkshaw.library.datalayer.models.Command.GetLocationRequest.$childSerializers
            r3 = 1
            r4 = 0
            r5 = 0
            r6 = 0
            r8 = 0
            r10 = r4
            r19 = r10
            r11 = r5
            r12 = r6
            r14 = r12
            r16 = r14
            r18 = r8
            r5 = r3
        L_0x0024:
            if (r5 == 0) goto L_0x0068
            int r6 = r0.y(r1)
            switch(r6) {
                case -1: goto L_0x0066;
                case 0: goto L_0x005a;
                case 1: goto L_0x0053;
                case 2: goto L_0x004b;
                case 3: goto L_0x0043;
                case 4: goto L_0x003b;
                case 5: goto L_0x0033;
                default: goto L_0x002d;
            }
        L_0x002d:
            B3.m r0 = new B3.m
            r0.<init>(r6)
            throw r0
        L_0x0033:
            r6 = 5
            int r19 = r0.s(r1, r6)
            r10 = r10 | 32
            goto L_0x0024
        L_0x003b:
            r6 = 4
            float r18 = r0.G(r1, r6)
            r10 = r10 | 16
            goto L_0x0024
        L_0x0043:
            r6 = 3
            long r16 = r0.D(r1, r6)
            r10 = r10 | 8
            goto L_0x0024
        L_0x004b:
            r6 = 2
            long r14 = r0.D(r1, r6)
            r10 = r10 | 4
            goto L_0x0024
        L_0x0053:
            long r12 = r0.D(r1, r3)
            r10 = r10 | 2
            goto L_0x0024
        L_0x005a:
            r6 = r2[r4]
            java.lang.Object r6 = r0.l(r1, r4, r6, r11)
            r11 = r6
            com.hawkshaw.library.datalayer.models.Command$GetLocationRequest$Priority r11 = (com.hawkshaw.library.datalayer.models.Command.GetLocationRequest.Priority) r11
            r10 = r10 | 1
            goto L_0x0024
        L_0x0066:
            r5 = r4
            goto L_0x0024
        L_0x0068:
            r0.c(r1)
            com.hawkshaw.library.datalayer.models.Command$GetLocationRequest r0 = new com.hawkshaw.library.datalayer.models.Command$GetLocationRequest
            r20 = 0
            r9 = r0
            r9.<init>(r10, r11, r12, r14, r16, r18, r19, r20)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.models.Command$GetLocationRequest$$serializer.deserialize(kotlinx.serialization.encoding.Decoder):com.hawkshaw.library.datalayer.models.Command$GetLocationRequest");
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, Command.GetLocationRequest getLocationRequest) {
        K.n(encoder, "encoder");
        K.n(getLocationRequest, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        b a5 = encoder.a(descriptor2);
        Command.GetLocationRequest.write$Self$library_release(getLocationRequest, a5, descriptor2);
        a5.c(descriptor2);
    }

    public KSerializer[] typeParametersSerializers() {
        return C0029h0.f501b;
    }
}
