package com.hawkshaw.library.datalayer.models.accessibility;

import B3.m;
import D3.a;
import D3.b;
import E3.C0029h0;
import E3.H;
import E3.O;
import E3.q0;
import Y1.K;
import java.util.List;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;

public final class PushSocialMediaRequest$$serializer implements H {
    public static final PushSocialMediaRequest$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        PushSocialMediaRequest$$serializer pushSocialMediaRequest$$serializer = new PushSocialMediaRequest$$serializer();
        INSTANCE = pushSocialMediaRequest$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.hawkshaw.library.datalayer.models.accessibility.PushSocialMediaRequest", pushSocialMediaRequest$$serializer, 2);
        pluginGeneratedSerialDescriptor.l("page", false);
        pluginGeneratedSerialDescriptor.l("messages", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private PushSocialMediaRequest$$serializer() {
    }

    public KSerializer[] childSerializers() {
        return new KSerializer[]{O.f456a, PushSocialMediaRequest.$childSerializers[1]};
    }

    public PushSocialMediaRequest deserialize(Decoder decoder) {
        K.n(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        a a5 = decoder.a(descriptor2);
        KSerializer[] access$get$childSerializers$cp = PushSocialMediaRequest.$childSerializers;
        boolean z4 = true;
        int i5 = 0;
        int i6 = 0;
        List list = null;
        while (z4) {
            int y4 = a5.y(descriptor2);
            if (y4 == -1) {
                z4 = false;
            } else if (y4 == 0) {
                i6 = a5.s(descriptor2, 0);
                i5 |= 1;
            } else if (y4 == 1) {
                list = (List) a5.l(descriptor2, 1, access$get$childSerializers$cp[1], list);
                i5 |= 2;
            } else {
                throw new m(y4);
            }
        }
        a5.c(descriptor2);
        return new PushSocialMediaRequest(i5, i6, list, (q0) null);
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, PushSocialMediaRequest pushSocialMediaRequest) {
        K.n(encoder, "encoder");
        K.n(pushSocialMediaRequest, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        b a5 = encoder.a(descriptor2);
        PushSocialMediaRequest.write$Self$library_release(pushSocialMediaRequest, a5, descriptor2);
        a5.c(descriptor2);
    }

    public KSerializer[] typeParametersSerializers() {
        return C0029h0.f501b;
    }
}
