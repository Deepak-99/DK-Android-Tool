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

public final class CallLog$$serializer implements H {
    public static final CallLog$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        CallLog$$serializer callLog$$serializer = new CallLog$$serializer();
        INSTANCE = callLog$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.hawkshaw.library.datalayer.models.CallLog", callLog$$serializer, 14);
        pluginGeneratedSerialDescriptor.l("id", false);
        pluginGeneratedSerialDescriptor.l("name", false);
        pluginGeneratedSerialDescriptor.l("number", false);
        pluginGeneratedSerialDescriptor.l("date", false);
        pluginGeneratedSerialDescriptor.l("duration", false);
        pluginGeneratedSerialDescriptor.l("call_type", false);
        pluginGeneratedSerialDescriptor.l("call_new", false);
        pluginGeneratedSerialDescriptor.l("sim_slot", false);
        pluginGeneratedSerialDescriptor.l("features", false);
        pluginGeneratedSerialDescriptor.l("voice_mail_uri", false);
        pluginGeneratedSerialDescriptor.l("photo_account_id", false);
        pluginGeneratedSerialDescriptor.l("last_modified", false);
        pluginGeneratedSerialDescriptor.l("call_screen_app_name", false);
        pluginGeneratedSerialDescriptor.l("block_reason", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private CallLog$$serializer() {
    }

    public KSerializer[] childSerializers() {
        u0 u0Var = u0.f536a;
        KSerializer n4 = w.n(u0Var);
        KSerializer n5 = w.n(u0Var);
        KSerializer n6 = w.n(u0Var);
        V v4 = V.f466a;
        KSerializer n7 = w.n(v4);
        KSerializer n8 = w.n(v4);
        KSerializer n9 = w.n(u0Var);
        KSerializer n10 = w.n(u0Var);
        KSerializer n11 = w.n(u0Var);
        O o4 = O.f456a;
        return new KSerializer[]{n4, n5, n6, n7, n8, n9, n10, n11, w.n(o4), w.n(u0Var), w.n(u0Var), w.n(v4), w.n(u0Var), w.n(o4)};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v9, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v13, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v15, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v3, resolved type: java.lang.Long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v17, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v3, resolved type: java.lang.Long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v19, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v21, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v23, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v25, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v3, resolved type: java.lang.Integer} */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x00f2, code lost:
        r2 = r22;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x012e, code lost:
        r2 = r22;
        r23 = r17;
        r17 = r7;
        r7 = r23;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0040, code lost:
        r8 = r19;
        r9 = r20;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.hawkshaw.library.datalayer.models.CallLog deserialize(kotlinx.serialization.encoding.Decoder r26) {
        /*
            r25 = this;
            r0 = r26
            java.lang.String r1 = "decoder"
            Y1.K.n(r0, r1)
            kotlinx.serialization.descriptors.SerialDescriptor r1 = r25.getDescriptor()
            D3.a r0 = r0.a(r1)
            r4 = 0
            r2 = r4
            r3 = r2
            r5 = r3
            r7 = r5
            r8 = r7
            r9 = r8
            r10 = r9
            r11 = r10
            r12 = r11
            r13 = r12
            r14 = r13
            r15 = r14
            r17 = r15
            r6 = 0
            r18 = 1
        L_0x0021:
            if (r18 == 0) goto L_0x014f
            r19 = r8
            int r8 = r0.y(r1)
            switch(r8) {
                case -1: goto L_0x0138;
                case 0: goto L_0x010f;
                case 1: goto L_0x00f6;
                case 2: goto L_0x00de;
                case 3: goto L_0x00ce;
                case 4: goto L_0x00be;
                case 5: goto L_0x00af;
                case 6: goto L_0x00a0;
                case 7: goto L_0x0091;
                case 8: goto L_0x0081;
                case 9: goto L_0x0072;
                case 10: goto L_0x0063;
                case 11: goto L_0x0054;
                case 12: goto L_0x0045;
                case 13: goto L_0x0032;
                default: goto L_0x002c;
            }
        L_0x002c:
            B3.m r0 = new B3.m
            r0.<init>(r8)
            throw r0
        L_0x0032:
            E3.O r8 = E3.O.f456a
            r20 = r9
            r9 = 13
            java.lang.Object r7 = r0.f(r1, r9, r8, r7)
            java.lang.Integer r7 = (java.lang.Integer) r7
            r6 = r6 | 8192(0x2000, float:1.148E-41)
        L_0x0040:
            r8 = r19
            r9 = r20
            goto L_0x0021
        L_0x0045:
            r20 = r9
            E3.u0 r8 = E3.u0.f536a
            r9 = 12
            java.lang.Object r2 = r0.f(r1, r9, r8, r2)
            java.lang.String r2 = (java.lang.String) r2
            r6 = r6 | 4096(0x1000, float:5.74E-42)
            goto L_0x0040
        L_0x0054:
            r20 = r9
            E3.V r8 = E3.V.f466a
            r9 = 11
            java.lang.Object r3 = r0.f(r1, r9, r8, r3)
            java.lang.Long r3 = (java.lang.Long) r3
            r6 = r6 | 2048(0x800, float:2.87E-42)
            goto L_0x0040
        L_0x0063:
            r20 = r9
            E3.u0 r8 = E3.u0.f536a
            r9 = 10
            java.lang.Object r5 = r0.f(r1, r9, r8, r5)
            java.lang.String r5 = (java.lang.String) r5
            r6 = r6 | 1024(0x400, float:1.435E-42)
            goto L_0x0040
        L_0x0072:
            r20 = r9
            E3.u0 r8 = E3.u0.f536a
            r9 = 9
            java.lang.Object r4 = r0.f(r1, r9, r8, r4)
            java.lang.String r4 = (java.lang.String) r4
            r6 = r6 | 512(0x200, float:7.17E-43)
            goto L_0x0040
        L_0x0081:
            r20 = r9
            E3.O r8 = E3.O.f456a
            r9 = 8
            java.lang.Object r8 = r0.f(r1, r9, r8, r15)
            r15 = r8
            java.lang.Integer r15 = (java.lang.Integer) r15
            r6 = r6 | 256(0x100, float:3.59E-43)
            goto L_0x0040
        L_0x0091:
            r20 = r9
            E3.u0 r8 = E3.u0.f536a
            r9 = 7
            java.lang.Object r8 = r0.f(r1, r9, r8, r14)
            r14 = r8
            java.lang.String r14 = (java.lang.String) r14
            r6 = r6 | 128(0x80, float:1.8E-43)
            goto L_0x0040
        L_0x00a0:
            r20 = r9
            E3.u0 r8 = E3.u0.f536a
            r9 = 6
            java.lang.Object r8 = r0.f(r1, r9, r8, r13)
            r13 = r8
            java.lang.String r13 = (java.lang.String) r13
            r6 = r6 | 64
            goto L_0x0040
        L_0x00af:
            r20 = r9
            E3.u0 r8 = E3.u0.f536a
            r9 = 5
            java.lang.Object r8 = r0.f(r1, r9, r8, r12)
            r12 = r8
            java.lang.String r12 = (java.lang.String) r12
            r6 = r6 | 32
            goto L_0x0040
        L_0x00be:
            r20 = r9
            E3.V r8 = E3.V.f466a
            r9 = 4
            java.lang.Object r8 = r0.f(r1, r9, r8, r11)
            r11 = r8
            java.lang.Long r11 = (java.lang.Long) r11
            r6 = r6 | 16
            goto L_0x0040
        L_0x00ce:
            r20 = r9
            E3.V r8 = E3.V.f466a
            r9 = 3
            java.lang.Object r8 = r0.f(r1, r9, r8, r10)
            r10 = r8
            java.lang.Long r10 = (java.lang.Long) r10
            r6 = r6 | 8
            goto L_0x0040
        L_0x00de:
            r20 = r9
            E3.u0 r8 = E3.u0.f536a
            r9 = 2
            r22 = r2
            r2 = r20
            java.lang.Object r2 = r0.f(r1, r9, r8, r2)
            r9 = r2
            java.lang.String r9 = (java.lang.String) r9
            r6 = r6 | 4
            r8 = r19
        L_0x00f2:
            r2 = r22
            goto L_0x0021
        L_0x00f6:
            r22 = r2
            r2 = r9
            E3.u0 r8 = E3.u0.f536a
            r9 = 1
            r23 = r19
            r19 = r3
            r3 = r23
            java.lang.Object r3 = r0.f(r1, r9, r8, r3)
            r8 = r3
            java.lang.String r8 = (java.lang.String) r8
            r6 = r6 | 2
            r9 = r2
            r3 = r19
            goto L_0x00f2
        L_0x010f:
            r22 = r2
            r2 = r9
            r9 = 1
            r23 = r19
            r19 = r3
            r3 = r23
            E3.u0 r8 = E3.u0.f536a
            r9 = 0
            r23 = r17
            r17 = r7
            r7 = r23
            java.lang.Object r7 = r0.f(r1, r9, r8, r7)
            java.lang.String r7 = (java.lang.String) r7
            r6 = r6 | 1
            r9 = r2
            r8 = r3
            r3 = r19
        L_0x012e:
            r2 = r22
            r23 = r17
            r17 = r7
            r7 = r23
            goto L_0x0021
        L_0x0138:
            r22 = r2
            r2 = r9
            r9 = 0
            r23 = r19
            r19 = r3
            r3 = r23
            r24 = r17
            r17 = r7
            r7 = r24
            r8 = r3
            r18 = r9
            r3 = r19
            r9 = r2
            goto L_0x012e
        L_0x014f:
            r22 = r2
            r19 = r3
            r3 = r8
            r2 = r9
            r23 = r17
            r17 = r7
            r7 = r23
            r0.c(r1)
            com.hawkshaw.library.datalayer.models.CallLog r0 = new com.hawkshaw.library.datalayer.models.CallLog
            r1 = r5
            r5 = r0
            r21 = 0
            r20 = r17
            r16 = r4
            r17 = r1
            r18 = r19
            r19 = r22
            r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.models.CallLog$$serializer.deserialize(kotlinx.serialization.encoding.Decoder):com.hawkshaw.library.datalayer.models.CallLog");
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, CallLog callLog) {
        K.n(encoder, "encoder");
        K.n(callLog, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        b a5 = encoder.a(descriptor2);
        CallLog.write$Self$library_release(callLog, a5, descriptor2);
        a5.c(descriptor2);
    }

    public KSerializer[] typeParametersSerializers() {
        return C0029h0.f501b;
    }
}
