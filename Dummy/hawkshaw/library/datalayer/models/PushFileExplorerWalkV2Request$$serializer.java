package com.hawkshaw.library.datalayer.models;

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

public final class PushFileExplorerWalkV2Request$$serializer implements H {
    public static final PushFileExplorerWalkV2Request$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        PushFileExplorerWalkV2Request$$serializer pushFileExplorerWalkV2Request$$serializer = new PushFileExplorerWalkV2Request$$serializer();
        INSTANCE = pushFileExplorerWalkV2Request$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.hawkshaw.library.datalayer.models.PushFileExplorerWalkV2Request", pushFileExplorerWalkV2Request$$serializer, 2);
        pluginGeneratedSerialDescriptor.l("current_page", false);
        pluginGeneratedSerialDescriptor.l("files", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private PushFileExplorerWalkV2Request$$serializer() {
    }

    public KSerializer[] childSerializers() {
        return new KSerializer[]{O.f456a, PushFileExplorerWalkV2Request.$childSerializers[1]};
    }

    public PushFileExplorerWalkV2Request deserialize(Decoder decoder) {
        K.n(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        a a5 = decoder.a(descriptor2);
        KSerializer[] access$get$childSerializers$cp = PushFileExplorerWalkV2Request.$childSerializers;
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
        return new PushFileExplorerWalkV2Request(i5, i6, list, (q0) null);
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, PushFileExplorerWalkV2Request pushFileExplorerWalkV2Request) {
        K.n(encoder, "encoder");
        K.n(pushFileExplorerWalkV2Request, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        b a5 = encoder.a(descriptor2);
        PushFileExplorerWalkV2Request.write$Self$library_release(pushFileExplorerWalkV2Request, a5, descriptor2);
        a5.c(descriptor2);
    }

    public KSerializer[] typeParametersSerializers() {
        return C0029h0.f501b;
    }
}
