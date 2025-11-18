package com.hawkshaw.library.datalayer.models;

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
import w3.w;

public final class Message$$serializer implements H {
    public static final Message$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        Message$$serializer message$$serializer = new Message$$serializer();
        INSTANCE = message$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.hawkshaw.library.datalayer.models.Message", message$$serializer, 10);
        pluginGeneratedSerialDescriptor.l("id", false);
        pluginGeneratedSerialDescriptor.l("thread_id", false);
        pluginGeneratedSerialDescriptor.l("address", false);
        pluginGeneratedSerialDescriptor.l("contact_name", false);
        pluginGeneratedSerialDescriptor.l("body", false);
        pluginGeneratedSerialDescriptor.l("subject", false);
        pluginGeneratedSerialDescriptor.l("type", false);
        pluginGeneratedSerialDescriptor.l("date", false);
        pluginGeneratedSerialDescriptor.l("date_sent", false);
        pluginGeneratedSerialDescriptor.l("creator", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private Message$$serializer() {
    }

    public KSerializer[] childSerializers() {
        V v4 = V.f466a;
        KSerializer n4 = w.n(v4);
        O o4 = O.f456a;
        KSerializer n5 = w.n(o4);
        u0 u0Var = u0.f536a;
        return new KSerializer[]{n4, n5, w.n(u0Var), u0Var, w.n(u0Var), w.n(u0Var), w.n(o4), w.n(v4), w.n(v4), w.n(u0Var)};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v3, resolved type: java.lang.Long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v3, resolved type: java.lang.Integer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v12, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v3, resolved type: java.lang.Integer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v14, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v3, resolved type: java.lang.Long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v16, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v3, resolved type: java.lang.Long} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.hawkshaw.library.datalayer.models.Message deserialize(kotlinx.serialization.encoding.Decoder r19) {
        /*
            r18 = this;
            r0 = r19
            java.lang.String r1 = "decoder"
            Y1.K.n(r0, r1)
            kotlinx.serialization.descriptors.SerialDescriptor r1 = r18.getDescriptor()
            D3.a r0 = r0.a(r1)
            r4 = 0
            r7 = r4
            r8 = r7
            r9 = r8
            r10 = r9
            r11 = r10
            r12 = r11
            r13 = r12
            r14 = r13
            r15 = r14
            r5 = 1
            r6 = 0
        L_0x001b:
            if (r5 == 0) goto L_0x00b0
            int r3 = r0.y(r1)
            switch(r3) {
                case -1: goto L_0x00ac;
                case 0: goto L_0x009d;
                case 1: goto L_0x008f;
                case 2: goto L_0x0082;
                case 3: goto L_0x007a;
                case 4: goto L_0x006d;
                case 5: goto L_0x0060;
                case 6: goto L_0x0053;
                case 7: goto L_0x0046;
                case 8: goto L_0x0038;
                case 9: goto L_0x002a;
                default: goto L_0x0024;
            }
        L_0x0024:
            B3.m r0 = new B3.m
            r0.<init>(r3)
            throw r0
        L_0x002a:
            E3.u0 r3 = E3.u0.f536a
            r2 = 9
            java.lang.Object r2 = r0.f(r1, r2, r3, r4)
            r4 = r2
            java.lang.String r4 = (java.lang.String) r4
            r6 = r6 | 512(0x200, float:7.17E-43)
            goto L_0x001b
        L_0x0038:
            E3.V r2 = E3.V.f466a
            r3 = 8
            java.lang.Object r2 = r0.f(r1, r3, r2, r15)
            r15 = r2
            java.lang.Long r15 = (java.lang.Long) r15
            r6 = r6 | 256(0x100, float:3.59E-43)
            goto L_0x001b
        L_0x0046:
            E3.V r2 = E3.V.f466a
            r3 = 7
            java.lang.Object r2 = r0.f(r1, r3, r2, r14)
            r14 = r2
            java.lang.Long r14 = (java.lang.Long) r14
            r6 = r6 | 128(0x80, float:1.8E-43)
            goto L_0x001b
        L_0x0053:
            E3.O r2 = E3.O.f456a
            r3 = 6
            java.lang.Object r2 = r0.f(r1, r3, r2, r13)
            r13 = r2
            java.lang.Integer r13 = (java.lang.Integer) r13
            r6 = r6 | 64
            goto L_0x001b
        L_0x0060:
            E3.u0 r2 = E3.u0.f536a
            r3 = 5
            java.lang.Object r2 = r0.f(r1, r3, r2, r12)
            r12 = r2
            java.lang.String r12 = (java.lang.String) r12
            r6 = r6 | 32
            goto L_0x001b
        L_0x006d:
            E3.u0 r2 = E3.u0.f536a
            r3 = 4
            java.lang.Object r2 = r0.f(r1, r3, r2, r11)
            r11 = r2
            java.lang.String r11 = (java.lang.String) r11
            r6 = r6 | 16
            goto L_0x001b
        L_0x007a:
            r2 = 3
            java.lang.String r10 = r0.q(r1, r2)
            r6 = r6 | 8
            goto L_0x001b
        L_0x0082:
            E3.u0 r2 = E3.u0.f536a
            r3 = 2
            java.lang.Object r2 = r0.f(r1, r3, r2, r9)
            r9 = r2
            java.lang.String r9 = (java.lang.String) r9
            r6 = r6 | 4
            goto L_0x001b
        L_0x008f:
            E3.O r2 = E3.O.f456a
            r3 = 1
            java.lang.Object r2 = r0.f(r1, r3, r2, r8)
            r8 = r2
            java.lang.Integer r8 = (java.lang.Integer) r8
            r6 = r6 | 2
            goto L_0x001b
        L_0x009d:
            r3 = 1
            E3.V r2 = E3.V.f466a
            r3 = 0
            java.lang.Object r2 = r0.f(r1, r3, r2, r7)
            r7 = r2
            java.lang.Long r7 = (java.lang.Long) r7
            r6 = r6 | 1
            goto L_0x001b
        L_0x00ac:
            r3 = 0
            r5 = r3
            goto L_0x001b
        L_0x00b0:
            r0.c(r1)
            com.hawkshaw.library.datalayer.models.Message r0 = new com.hawkshaw.library.datalayer.models.Message
            r17 = 0
            r5 = r0
            r16 = r4
            r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.models.Message$$serializer.deserialize(kotlinx.serialization.encoding.Decoder):com.hawkshaw.library.datalayer.models.Message");
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, Message message) {
        K.n(encoder, "encoder");
        K.n(message, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        b a5 = encoder.a(descriptor2);
        Message.write$Self$library_release(message, a5, descriptor2);
        a5.c(descriptor2);
    }

    public KSerializer[] typeParametersSerializers() {
        return C0029h0.f501b;
    }
}
