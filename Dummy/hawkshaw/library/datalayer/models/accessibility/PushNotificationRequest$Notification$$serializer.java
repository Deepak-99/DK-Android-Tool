package com.hawkshaw.library.datalayer.models.accessibility;

import D3.b;
import E3.C0029h0;
import E3.H;
import E3.V;
import E3.u0;
import Y1.K;
import com.hawkshaw.library.datalayer.models.accessibility.PushNotificationRequest;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import w3.w;

public final class PushNotificationRequest$Notification$$serializer implements H {
    public static final PushNotificationRequest$Notification$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        PushNotificationRequest$Notification$$serializer pushNotificationRequest$Notification$$serializer = new PushNotificationRequest$Notification$$serializer();
        INSTANCE = pushNotificationRequest$Notification$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.hawkshaw.library.datalayer.models.accessibility.PushNotificationRequest.Notification", pushNotificationRequest$Notification$$serializer, 8);
        pluginGeneratedSerialDescriptor.l("package_name", false);
        pluginGeneratedSerialDescriptor.l("extra_text", false);
        pluginGeneratedSerialDescriptor.l("extra_title", false);
        pluginGeneratedSerialDescriptor.l("extra_message", false);
        pluginGeneratedSerialDescriptor.l("extra_big_text", false);
        pluginGeneratedSerialDescriptor.l("extra_text_lines", false);
        pluginGeneratedSerialDescriptor.l("ticker_text", false);
        pluginGeneratedSerialDescriptor.l("timestamp", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private PushNotificationRequest$Notification$$serializer() {
    }

    public KSerializer[] childSerializers() {
        KSerializer[] access$get$childSerializers$cp = PushNotificationRequest.Notification.$childSerializers;
        u0 u0Var = u0.f536a;
        return new KSerializer[]{u0Var, w.n(u0Var), w.n(u0Var), w.n(u0Var), w.n(u0Var), w.n(access$get$childSerializers$cp[5]), w.n(u0Var), V.f466a};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v3, resolved type: java.lang.String[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.hawkshaw.library.datalayer.models.accessibility.PushNotificationRequest.Notification deserialize(kotlinx.serialization.encoding.Decoder r21) {
        /*
            r20 = this;
            r0 = r21
            java.lang.String r1 = "decoder"
            Y1.K.n(r0, r1)
            kotlinx.serialization.descriptors.SerialDescriptor r1 = r20.getDescriptor()
            D3.a r0 = r0.a(r1)
            kotlinx.serialization.KSerializer[] r2 = com.hawkshaw.library.datalayer.models.accessibility.PushNotificationRequest.Notification.$childSerializers
            r3 = 1
            r4 = 0
            r5 = 0
            r6 = 0
            r9 = r4
            r10 = r5
            r11 = r10
            r12 = r11
            r13 = r12
            r14 = r13
            r15 = r14
            r17 = r6
            r6 = r3
        L_0x0022:
            if (r6 == 0) goto L_0x008e
            int r7 = r0.y(r1)
            switch(r7) {
                case -1: goto L_0x008c;
                case 0: goto L_0x0085;
                case 1: goto L_0x0079;
                case 2: goto L_0x006c;
                case 3: goto L_0x005f;
                case 4: goto L_0x0052;
                case 5: goto L_0x0045;
                case 6: goto L_0x0039;
                case 7: goto L_0x0031;
                default: goto L_0x002b;
            }
        L_0x002b:
            B3.m r0 = new B3.m
            r0.<init>(r7)
            throw r0
        L_0x0031:
            r7 = 7
            long r17 = r0.D(r1, r7)
            r9 = r9 | 128(0x80, float:1.8E-43)
            goto L_0x0022
        L_0x0039:
            E3.u0 r7 = E3.u0.f536a
            r8 = 6
            java.lang.Object r5 = r0.f(r1, r8, r7, r5)
            java.lang.String r5 = (java.lang.String) r5
            r9 = r9 | 64
            goto L_0x0022
        L_0x0045:
            r7 = 5
            r8 = r2[r7]
            java.lang.Object r7 = r0.f(r1, r7, r8, r15)
            r15 = r7
            java.lang.String[] r15 = (java.lang.String[]) r15
            r9 = r9 | 32
            goto L_0x0022
        L_0x0052:
            E3.u0 r7 = E3.u0.f536a
            r8 = 4
            java.lang.Object r7 = r0.f(r1, r8, r7, r14)
            r14 = r7
            java.lang.String r14 = (java.lang.String) r14
            r9 = r9 | 16
            goto L_0x0022
        L_0x005f:
            E3.u0 r7 = E3.u0.f536a
            r8 = 3
            java.lang.Object r7 = r0.f(r1, r8, r7, r13)
            r13 = r7
            java.lang.String r13 = (java.lang.String) r13
            r9 = r9 | 8
            goto L_0x0022
        L_0x006c:
            E3.u0 r7 = E3.u0.f536a
            r8 = 2
            java.lang.Object r7 = r0.f(r1, r8, r7, r12)
            r12 = r7
            java.lang.String r12 = (java.lang.String) r12
            r9 = r9 | 4
            goto L_0x0022
        L_0x0079:
            E3.u0 r7 = E3.u0.f536a
            java.lang.Object r7 = r0.f(r1, r3, r7, r11)
            r11 = r7
            java.lang.String r11 = (java.lang.String) r11
            r9 = r9 | 2
            goto L_0x0022
        L_0x0085:
            java.lang.String r10 = r0.q(r1, r4)
            r9 = r9 | 1
            goto L_0x0022
        L_0x008c:
            r6 = r4
            goto L_0x0022
        L_0x008e:
            r0.c(r1)
            com.hawkshaw.library.datalayer.models.accessibility.PushNotificationRequest$Notification r0 = new com.hawkshaw.library.datalayer.models.accessibility.PushNotificationRequest$Notification
            r19 = 0
            r8 = r0
            r16 = r5
            r8.<init>(r9, r10, r11, r12, r13, r14, r15, r16, r17, r19)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.models.accessibility.PushNotificationRequest$Notification$$serializer.deserialize(kotlinx.serialization.encoding.Decoder):com.hawkshaw.library.datalayer.models.accessibility.PushNotificationRequest$Notification");
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, PushNotificationRequest.Notification notification) {
        K.n(encoder, "encoder");
        K.n(notification, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        b a5 = encoder.a(descriptor2);
        PushNotificationRequest.Notification.write$Self$library_release(notification, a5, descriptor2);
        a5.c(descriptor2);
    }

    public KSerializer[] typeParametersSerializers() {
        return C0029h0.f501b;
    }
}
