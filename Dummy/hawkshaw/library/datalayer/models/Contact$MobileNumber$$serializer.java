package com.hawkshaw.library.datalayer.models;

import B3.m;
import D3.a;
import D3.b;
import E3.C0029h0;
import E3.H;
import E3.q0;
import E3.u0;
import Y1.K;
import com.hawkshaw.library.datalayer.models.Contact;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;

public final class Contact$MobileNumber$$serializer implements H {
    public static final Contact$MobileNumber$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        Contact$MobileNumber$$serializer contact$MobileNumber$$serializer = new Contact$MobileNumber$$serializer();
        INSTANCE = contact$MobileNumber$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.hawkshaw.library.datalayer.models.Contact.MobileNumber", contact$MobileNumber$$serializer, 2);
        pluginGeneratedSerialDescriptor.l("number", false);
        pluginGeneratedSerialDescriptor.l("type", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private Contact$MobileNumber$$serializer() {
    }

    public KSerializer[] childSerializers() {
        u0 u0Var = u0.f536a;
        return new KSerializer[]{u0Var, u0Var};
    }

    public Contact.MobileNumber deserialize(Decoder decoder) {
        K.n(decoder, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        a a5 = decoder.a(descriptor2);
        boolean z4 = true;
        int i5 = 0;
        String str = null;
        String str2 = null;
        while (z4) {
            int y4 = a5.y(descriptor2);
            if (y4 == -1) {
                z4 = false;
            } else if (y4 == 0) {
                str = a5.q(descriptor2, 0);
                i5 |= 1;
            } else if (y4 == 1) {
                str2 = a5.q(descriptor2, 1);
                i5 |= 2;
            } else {
                throw new m(y4);
            }
        }
        a5.c(descriptor2);
        return new Contact.MobileNumber(i5, str, str2, (q0) null);
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, Contact.MobileNumber mobileNumber) {
        K.n(encoder, "encoder");
        K.n(mobileNumber, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        b a5 = encoder.a(descriptor2);
        Contact.MobileNumber.write$Self$library_release(mobileNumber, a5, descriptor2);
        a5.c(descriptor2);
    }

    public KSerializer[] typeParametersSerializers() {
        return C0029h0.f501b;
    }
}
