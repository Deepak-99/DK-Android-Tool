package com.hawkshaw.library.datalayer.models.accessibility;

import D3.b;
import E3.C0029h0;
import E3.H;
import E3.V;
import E3.u0;
import Y1.K;
import com.hawkshaw.library.datalayer.models.accessibility.PushSocialMediaRequest;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import w3.w;

public final class PushSocialMediaRequest$SocialMediaMessage$$serializer implements H {
    public static final PushSocialMediaRequest$SocialMediaMessage$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        PushSocialMediaRequest$SocialMediaMessage$$serializer pushSocialMediaRequest$SocialMediaMessage$$serializer = new PushSocialMediaRequest$SocialMediaMessage$$serializer();
        INSTANCE = pushSocialMediaRequest$SocialMediaMessage$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.hawkshaw.library.datalayer.models.accessibility.PushSocialMediaRequest.SocialMediaMessage", pushSocialMediaRequest$SocialMediaMessage$$serializer, 8);
        pluginGeneratedSerialDescriptor.l("ccn", false);
        pluginGeneratedSerialDescriptor.l("ccs", false);
        pluginGeneratedSerialDescriptor.l("sender", false);
        pluginGeneratedSerialDescriptor.l("message", false);
        pluginGeneratedSerialDescriptor.l("type", false);
        pluginGeneratedSerialDescriptor.l("timestamp", false);
        pluginGeneratedSerialDescriptor.l("application", false);
        pluginGeneratedSerialDescriptor.l("time", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private PushSocialMediaRequest$SocialMediaMessage$$serializer() {
    }

    public KSerializer[] childSerializers() {
        KSerializer[] access$get$childSerializers$cp = PushSocialMediaRequest.SocialMediaMessage.$childSerializers;
        u0 u0Var = u0.f536a;
        return new KSerializer[]{u0Var, w.n(u0Var), w.n(u0Var), u0Var, access$get$childSerializers$cp[4], w.n(V.f466a), access$get$childSerializers$cp[6], u0Var};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v3, resolved type: com.hawkshaw.library.datalayer.room.socialmedia.SocialMediaEntity$Type} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v3, resolved type: java.lang.Long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v12, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v3, resolved type: com.hawkshaw.library.datalayer.models.PackageName} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.hawkshaw.library.datalayer.models.accessibility.PushSocialMediaRequest.SocialMediaMessage deserialize(kotlinx.serialization.encoding.Decoder r18) {
        /*
            r17 = this;
            r0 = r18
            java.lang.String r1 = "decoder"
            Y1.K.n(r0, r1)
            kotlinx.serialization.descriptors.SerialDescriptor r1 = r17.getDescriptor()
            D3.a r0 = r0.a(r1)
            kotlinx.serialization.KSerializer[] r2 = com.hawkshaw.library.datalayer.models.accessibility.PushSocialMediaRequest.SocialMediaMessage.$childSerializers
            r3 = 1
            r5 = 0
            r8 = r5
            r9 = r8
            r10 = r9
            r11 = r10
            r12 = r11
            r13 = r12
            r14 = r13
            r15 = r14
            r7 = 0
            r5 = r3
        L_0x001f:
            if (r5 == 0) goto L_0x0089
            int r6 = r0.y(r1)
            switch(r6) {
                case -1: goto L_0x0086;
                case 0: goto L_0x007e;
                case 1: goto L_0x0072;
                case 2: goto L_0x0065;
                case 3: goto L_0x005d;
                case 4: goto L_0x0050;
                case 5: goto L_0x0043;
                case 6: goto L_0x0036;
                case 7: goto L_0x002e;
                default: goto L_0x0028;
            }
        L_0x0028:
            B3.m r0 = new B3.m
            r0.<init>(r6)
            throw r0
        L_0x002e:
            r6 = 7
            java.lang.String r15 = r0.q(r1, r6)
            r7 = r7 | 128(0x80, float:1.8E-43)
            goto L_0x001f
        L_0x0036:
            r6 = 6
            r4 = r2[r6]
            java.lang.Object r4 = r0.l(r1, r6, r4, r14)
            r14 = r4
            com.hawkshaw.library.datalayer.models.PackageName r14 = (com.hawkshaw.library.datalayer.models.PackageName) r14
            r7 = r7 | 64
            goto L_0x001f
        L_0x0043:
            E3.V r4 = E3.V.f466a
            r6 = 5
            java.lang.Object r4 = r0.f(r1, r6, r4, r13)
            r13 = r4
            java.lang.Long r13 = (java.lang.Long) r13
            r7 = r7 | 32
            goto L_0x001f
        L_0x0050:
            r4 = 4
            r6 = r2[r4]
            java.lang.Object r4 = r0.l(r1, r4, r6, r12)
            r12 = r4
            com.hawkshaw.library.datalayer.room.socialmedia.SocialMediaEntity$Type r12 = (com.hawkshaw.library.datalayer.room.socialmedia.SocialMediaEntity.Type) r12
            r7 = r7 | 16
            goto L_0x001f
        L_0x005d:
            r4 = 3
            java.lang.String r11 = r0.q(r1, r4)
            r7 = r7 | 8
            goto L_0x001f
        L_0x0065:
            E3.u0 r4 = E3.u0.f536a
            r6 = 2
            java.lang.Object r4 = r0.f(r1, r6, r4, r10)
            r10 = r4
            java.lang.String r10 = (java.lang.String) r10
            r7 = r7 | 4
            goto L_0x001f
        L_0x0072:
            E3.u0 r4 = E3.u0.f536a
            java.lang.Object r4 = r0.f(r1, r3, r4, r9)
            r9 = r4
            java.lang.String r9 = (java.lang.String) r9
            r7 = r7 | 2
            goto L_0x001f
        L_0x007e:
            r4 = 0
            java.lang.String r8 = r0.q(r1, r4)
            r7 = r7 | 1
            goto L_0x001f
        L_0x0086:
            r4 = 0
            r5 = r4
            goto L_0x001f
        L_0x0089:
            r0.c(r1)
            com.hawkshaw.library.datalayer.models.accessibility.PushSocialMediaRequest$SocialMediaMessage r0 = new com.hawkshaw.library.datalayer.models.accessibility.PushSocialMediaRequest$SocialMediaMessage
            r16 = 0
            r6 = r0
            r6.<init>((int) r7, (java.lang.String) r8, (java.lang.String) r9, (java.lang.String) r10, (java.lang.String) r11, (com.hawkshaw.library.datalayer.room.socialmedia.SocialMediaEntity.Type) r12, (java.lang.Long) r13, (com.hawkshaw.library.datalayer.models.PackageName) r14, (java.lang.String) r15, (E3.q0) r16)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.models.accessibility.PushSocialMediaRequest$SocialMediaMessage$$serializer.deserialize(kotlinx.serialization.encoding.Decoder):com.hawkshaw.library.datalayer.models.accessibility.PushSocialMediaRequest$SocialMediaMessage");
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, PushSocialMediaRequest.SocialMediaMessage socialMediaMessage) {
        K.n(encoder, "encoder");
        K.n(socialMediaMessage, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        b a5 = encoder.a(descriptor2);
        PushSocialMediaRequest.SocialMediaMessage.write$Self$library_release(socialMediaMessage, a5, descriptor2);
        a5.c(descriptor2);
    }

    public KSerializer[] typeParametersSerializers() {
        return C0029h0.f501b;
    }
}
