package com.hawkshaw.library.datalayer.models;

import D3.b;
import E3.C0029h0;
import E3.H;
import E3.O;
import E3.u0;
import Y1.K;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import w3.w;

public final class Contact$$serializer implements H {
    public static final Contact$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        Contact$$serializer contact$$serializer = new Contact$$serializer();
        INSTANCE = contact$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.hawkshaw.library.datalayer.models.Contact", contact$$serializer, 9);
        pluginGeneratedSerialDescriptor.l("id", false);
        pluginGeneratedSerialDescriptor.l("name", false);
        pluginGeneratedSerialDescriptor.l("person", false);
        pluginGeneratedSerialDescriptor.l("starred", false);
        pluginGeneratedSerialDescriptor.l("pinned", false);
        pluginGeneratedSerialDescriptor.l("photo_uri", false);
        pluginGeneratedSerialDescriptor.l("last_updated_timestamp", false);
        pluginGeneratedSerialDescriptor.l("mobile_numbers", false);
        pluginGeneratedSerialDescriptor.l("email_list", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private Contact$$serializer() {
    }

    public KSerializer[] childSerializers() {
        KSerializer[] access$get$childSerializers$cp = Contact.$childSerializers;
        u0 u0Var = u0.f536a;
        KSerializer n4 = w.n(u0Var);
        KSerializer n5 = w.n(u0Var);
        KSerializer n6 = w.n(u0Var);
        KSerializer n7 = w.n(u0Var);
        KSerializer n8 = w.n(u0Var);
        KSerializer kSerializer = access$get$childSerializers$cp[7];
        KSerializer kSerializer2 = access$get$childSerializers$cp[8];
        O o4 = O.f456a;
        return new KSerializer[]{n4, n5, n6, o4, o4, n7, n8, kSerializer, kSerializer2};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v11, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v13, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v3, resolved type: java.util.List} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.hawkshaw.library.datalayer.models.Contact deserialize(kotlinx.serialization.encoding.Decoder r19) {
        /*
            r18 = this;
            r0 = r19
            java.lang.String r1 = "decoder"
            Y1.K.n(r0, r1)
            kotlinx.serialization.descriptors.SerialDescriptor r1 = r18.getDescriptor()
            D3.a r0 = r0.a(r1)
            kotlinx.serialization.KSerializer[] r2 = com.hawkshaw.library.datalayer.models.Contact.$childSerializers
            r5 = 0
            r8 = r5
            r9 = r8
            r10 = r9
            r13 = r10
            r14 = r13
            r15 = r14
            r6 = 1
            r7 = 0
            r11 = 0
            r12 = 0
        L_0x001e:
            if (r6 == 0) goto L_0x009d
            int r4 = r0.y(r1)
            switch(r4) {
                case -1: goto L_0x009a;
                case 0: goto L_0x008c;
                case 1: goto L_0x007f;
                case 2: goto L_0x0072;
                case 3: goto L_0x006a;
                case 4: goto L_0x0062;
                case 5: goto L_0x0055;
                case 6: goto L_0x0048;
                case 7: goto L_0x003b;
                case 8: goto L_0x002d;
                default: goto L_0x0027;
            }
        L_0x0027:
            B3.m r0 = new B3.m
            r0.<init>(r4)
            throw r0
        L_0x002d:
            r4 = 8
            r3 = r2[r4]
            java.lang.Object r3 = r0.l(r1, r4, r3, r5)
            r5 = r3
            java.util.List r5 = (java.util.List) r5
            r7 = r7 | 256(0x100, float:3.59E-43)
            goto L_0x001e
        L_0x003b:
            r3 = 7
            r4 = r2[r3]
            java.lang.Object r3 = r0.l(r1, r3, r4, r15)
            r15 = r3
            java.util.List r15 = (java.util.List) r15
            r7 = r7 | 128(0x80, float:1.8E-43)
            goto L_0x001e
        L_0x0048:
            E3.u0 r3 = E3.u0.f536a
            r4 = 6
            java.lang.Object r3 = r0.f(r1, r4, r3, r14)
            r14 = r3
            java.lang.String r14 = (java.lang.String) r14
            r7 = r7 | 64
            goto L_0x001e
        L_0x0055:
            E3.u0 r3 = E3.u0.f536a
            r4 = 5
            java.lang.Object r3 = r0.f(r1, r4, r3, r13)
            r13 = r3
            java.lang.String r13 = (java.lang.String) r13
            r7 = r7 | 32
            goto L_0x001e
        L_0x0062:
            r3 = 4
            int r12 = r0.s(r1, r3)
            r7 = r7 | 16
            goto L_0x001e
        L_0x006a:
            r3 = 3
            int r11 = r0.s(r1, r3)
            r7 = r7 | 8
            goto L_0x001e
        L_0x0072:
            E3.u0 r3 = E3.u0.f536a
            r4 = 2
            java.lang.Object r3 = r0.f(r1, r4, r3, r10)
            r10 = r3
            java.lang.String r10 = (java.lang.String) r10
            r7 = r7 | 4
            goto L_0x001e
        L_0x007f:
            E3.u0 r3 = E3.u0.f536a
            r4 = 1
            java.lang.Object r3 = r0.f(r1, r4, r3, r9)
            r9 = r3
            java.lang.String r9 = (java.lang.String) r9
            r7 = r7 | 2
            goto L_0x001e
        L_0x008c:
            r4 = 1
            E3.u0 r3 = E3.u0.f536a
            r4 = 0
            java.lang.Object r3 = r0.f(r1, r4, r3, r8)
            r8 = r3
            java.lang.String r8 = (java.lang.String) r8
            r7 = r7 | 1
            goto L_0x001e
        L_0x009a:
            r4 = 0
            r6 = r4
            goto L_0x001e
        L_0x009d:
            r0.c(r1)
            com.hawkshaw.library.datalayer.models.Contact r0 = new com.hawkshaw.library.datalayer.models.Contact
            r17 = 0
            r6 = r0
            r16 = r5
            r6.<init>(r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.models.Contact$$serializer.deserialize(kotlinx.serialization.encoding.Decoder):com.hawkshaw.library.datalayer.models.Contact");
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, Contact contact) {
        K.n(encoder, "encoder");
        K.n(contact, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        b a5 = encoder.a(descriptor2);
        Contact.write$Self$library_release(contact, a5, descriptor2);
        a5.c(descriptor2);
    }

    public KSerializer[] typeParametersSerializers() {
        return C0029h0.f501b;
    }
}
