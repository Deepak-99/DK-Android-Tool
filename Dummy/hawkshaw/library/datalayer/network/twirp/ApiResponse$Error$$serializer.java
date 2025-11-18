package com.hawkshaw.library.datalayer.network.twirp;

import D3.b;
import E3.H;
import E3.u0;
import Y1.K;
import androidx.core.app.NotificationCompat;
import com.hawkshaw.library.datalayer.network.twirp.ApiResponse;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import w3.w;

public final class ApiResponse$Error$$serializer<T> implements H {
    private final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;
    private final /* synthetic */ KSerializer typeSerial0;

    private ApiResponse$Error$$serializer() {
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.hawkshaw.library.datalayer.network.twirp.ApiResponse.Error", this, 3);
        pluginGeneratedSerialDescriptor.l(NotificationCompat.CATEGORY_MESSAGE, false);
        pluginGeneratedSerialDescriptor.l("code", false);
        pluginGeneratedSerialDescriptor.l("meta", false);
        this.descriptor = pluginGeneratedSerialDescriptor;
    }

    private final KSerializer getTypeSerial0() {
        return this.typeSerial0;
    }

    public KSerializer[] childSerializers() {
        KSerializer n4 = w.n(ApiResponse.Error.$childSerializers[2]);
        u0 u0Var = u0.f536a;
        return new KSerializer[]{u0Var, u0Var, n4};
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v3, resolved type: java.util.Map} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.hawkshaw.library.datalayer.network.twirp.ApiResponse.Error<T> deserialize(kotlinx.serialization.encoding.Decoder r12) {
        /*
            r11 = this;
            java.lang.String r0 = "decoder"
            Y1.K.n(r12, r0)
            kotlinx.serialization.descriptors.SerialDescriptor r0 = r11.getDescriptor()
            D3.a r12 = r12.a(r0)
            kotlinx.serialization.KSerializer[] r1 = com.hawkshaw.library.datalayer.network.twirp.ApiResponse.Error.$childSerializers
            r2 = 1
            r3 = 0
            r4 = 0
            r6 = r3
            r7 = r4
            r8 = r7
            r9 = r8
            r4 = r2
        L_0x0019:
            if (r4 == 0) goto L_0x004b
            int r5 = r12.y(r0)
            r10 = -1
            if (r5 == r10) goto L_0x0049
            if (r5 == 0) goto L_0x0042
            if (r5 == r2) goto L_0x003b
            r10 = 2
            if (r5 != r10) goto L_0x0035
            r5 = r1[r10]
            java.lang.Object r5 = r12.f(r0, r10, r5, r9)
            r9 = r5
            java.util.Map r9 = (java.util.Map) r9
            r6 = r6 | 4
            goto L_0x0019
        L_0x0035:
            B3.m r12 = new B3.m
            r12.<init>(r5)
            throw r12
        L_0x003b:
            java.lang.String r8 = r12.q(r0, r2)
            r6 = r6 | 2
            goto L_0x0019
        L_0x0042:
            java.lang.String r7 = r12.q(r0, r3)
            r6 = r6 | 1
            goto L_0x0019
        L_0x0049:
            r4 = r3
            goto L_0x0019
        L_0x004b:
            r12.c(r0)
            com.hawkshaw.library.datalayer.network.twirp.ApiResponse$Error r12 = new com.hawkshaw.library.datalayer.network.twirp.ApiResponse$Error
            r10 = 0
            r5 = r12
            r5.<init>(r6, r7, r8, r9, r10)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.network.twirp.ApiResponse$Error$$serializer.deserialize(kotlinx.serialization.encoding.Decoder):com.hawkshaw.library.datalayer.network.twirp.ApiResponse$Error");
    }

    public SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    public void serialize(Encoder encoder, ApiResponse.Error<T> error) {
        K.n(encoder, "encoder");
        K.n(error, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        b a5 = encoder.a(descriptor2);
        ApiResponse.Error.write$Self$library_release(error, a5, descriptor2, this.typeSerial0);
        a5.c(descriptor2);
    }

    public KSerializer[] typeParametersSerializers() {
        return new KSerializer[]{this.typeSerial0};
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ApiResponse$Error$$serializer(KSerializer kSerializer) {
        this();
        K.n(kSerializer, "typeSerial0");
        this.typeSerial0 = kSerializer;
    }
}
