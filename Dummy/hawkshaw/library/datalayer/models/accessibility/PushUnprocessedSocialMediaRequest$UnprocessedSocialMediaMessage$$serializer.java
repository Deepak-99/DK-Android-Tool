package com.hawkshaw.library.datalayer.models.accessibility;

import D3.b;
import E3.C0029h0;
import E3.H;
import E3.O;
import E3.u0;
import Y1.K;
import androidx.core.app.NotificationCompat;
import com.hawkshaw.library.datalayer.models.accessibility.PushUnprocessedSocialMediaRequest;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import w3.w;

public final class PushUnprocessedSocialMediaRequest$UnprocessedSocialMediaMessage$$serializer implements H {
    public static final PushUnprocessedSocialMediaRequest$UnprocessedSocialMediaMessage$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        PushUnprocessedSocialMediaRequest$UnprocessedSocialMediaMessage$$serializer pushUnprocessedSocialMediaRequest$UnprocessedSocialMediaMessage$$serializer = new PushUnprocessedSocialMediaRequest$UnprocessedSocialMediaMessage$$serializer();
        INSTANCE = pushUnprocessedSocialMediaRequest$UnprocessedSocialMediaMessage$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.hawkshaw.library.datalayer.models.accessibility.PushUnprocessedSocialMediaRequest.UnprocessedSocialMediaMessage", pushUnprocessedSocialMediaRequest$UnprocessedSocialMediaMessage$$serializer, 8);
        pluginGeneratedSerialDescriptor.l("id", false);
        pluginGeneratedSerialDescriptor.l("title", false);
        pluginGeneratedSerialDescriptor.l(NotificationCompat.CATEGORY_STATUS, false);
        pluginGeneratedSerialDescriptor.l("sender", false);
        pluginGeneratedSerialDescriptor.l("message", false);
        pluginGeneratedSerialDescriptor.l("type", false);
        pluginGeneratedSerialDescriptor.l("application", false);
        pluginGeneratedSerialDescriptor.l("timeString", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private PushUnprocessedSocialMediaRequest$UnprocessedSocialMediaMessage$$serializer() {
    }

    public KSerializer[] childSerializers() {
        KSerializer[] access$get$childSerializers$cp = PushUnprocessedSocialMediaRequest.UnprocessedSocialMediaMessage.$childSerializers;
        u0 u0Var = u0.f536a;
        return new KSerializer[]{O.f456a, u0Var, w.n(u0Var), w.n(u0Var), u0Var, access$get$childSerializers$cp[5], access$get$childSerializers$cp[6], u0Var};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v3, resolved type: com.hawkshaw.library.datalayer.room.socialmedia.SocialMediaEntity$Type} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v3, resolved type: com.hawkshaw.library.datalayer.models.PackageName} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.hawkshaw.library.datalayer.models.accessibility.PushUnprocessedSocialMediaRequest.UnprocessedSocialMediaMessage deserialize(kotlinx.serialization.encoding.Decoder r18) {
        /*
            r17 = this;
            r0 = r18
            java.lang.String r1 = "decoder"
            Y1.K.n(r0, r1)
            kotlinx.serialization.descriptors.SerialDescriptor r1 = r17.getDescriptor()
            D3.a r0 = r0.a(r1)
            kotlinx.serialization.KSerializer[] r2 = com.hawkshaw.library.datalayer.models.accessibility.PushUnprocessedSocialMediaRequest.UnprocessedSocialMediaMessage.$childSerializers
            r3 = 1
            r5 = 0
            r9 = r5
            r10 = r9
            r11 = r10
            r12 = r11
            r13 = r12
            r14 = r13
            r15 = r14
            r7 = 0
            r8 = 0
            r5 = r3
        L_0x001f:
            if (r5 == 0) goto L_0x0084
            int r6 = r0.y(r1)
            switch(r6) {
                case -1: goto L_0x0081;
                case 0: goto L_0x0079;
                case 1: goto L_0x0072;
                case 2: goto L_0x0065;
                case 3: goto L_0x0058;
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
            r4 = 5
            r6 = r2[r4]
            java.lang.Object r4 = r0.l(r1, r4, r6, r13)
            r13 = r4
            com.hawkshaw.library.datalayer.room.socialmedia.SocialMediaEntity$Type r13 = (com.hawkshaw.library.datalayer.room.socialmedia.SocialMediaEntity.Type) r13
            r7 = r7 | 32
            goto L_0x001f
        L_0x0050:
            r4 = 4
            java.lang.String r12 = r0.q(r1, r4)
            r7 = r7 | 16
            goto L_0x001f
        L_0x0058:
            E3.u0 r4 = E3.u0.f536a
            r6 = 3
            java.lang.Object r4 = r0.f(r1, r6, r4, r11)
            r11 = r4
            java.lang.String r11 = (java.lang.String) r11
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
            java.lang.String r9 = r0.q(r1, r3)
            r7 = r7 | 2
            goto L_0x001f
        L_0x0079:
            r4 = 0
            int r8 = r0.s(r1, r4)
            r7 = r7 | 1
            goto L_0x001f
        L_0x0081:
            r4 = 0
            r5 = r4
            goto L_0x001f
        L_0x0084:
            r0.c(r1)
            com.hawkshaw.library.datalayer.models.accessibility.PushUnprocessedSocialMediaRequest$UnprocessedSocialMediaMessage r0 = new com.hawkshaw.library.datalayer.models.accessibility.PushUnprocessedSocialMediaRequest$UnprocessedSocialMediaMessage
            r16 = 0
            r6 = r0
            r6.<init>(r7, r8, r9, r10, r11, r12, r13, r14, r15, r16)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.models.accessibility.PushUnprocessedSocialMediaRequest$UnprocessedSocialMediaMessage$$serializer.deserialize(kotlinx.serialization.encoding.Decoder):com.hawkshaw.library.datalayer.models.accessibility.PushUnprocessedSocialMediaRequest$UnprocessedSocialMediaMessage");
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, PushUnprocessedSocialMediaRequest.UnprocessedSocialMediaMessage unprocessedSocialMediaMessage) {
        K.n(encoder, "encoder");
        K.n(unprocessedSocialMediaMessage, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        b a5 = encoder.a(descriptor2);
        PushUnprocessedSocialMediaRequest.UnprocessedSocialMediaMessage.write$Self$library_release(unprocessedSocialMediaMessage, a5, descriptor2);
        a5.c(descriptor2);
    }

    public KSerializer[] typeParametersSerializers() {
        return C0029h0.f501b;
    }
}
