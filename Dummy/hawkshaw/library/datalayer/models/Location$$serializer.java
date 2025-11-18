package com.hawkshaw.library.datalayer.models;

import B3.m;
import D3.a;
import D3.b;
import E3.C0026g;
import E3.C0029h0;
import E3.C0048y;
import E3.G;
import E3.H;
import E3.V;
import E3.q0;
import E3.u0;
import Y1.K;
import androidx.core.app.NotificationCompat;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import w3.w;

public final class Location$$serializer implements H {
    public static final Location$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        Location$$serializer location$$serializer = new Location$$serializer();
        INSTANCE = location$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.hawkshaw.library.datalayer.models.Location", location$$serializer, 10);
        pluginGeneratedSerialDescriptor.l("altitude", false);
        pluginGeneratedSerialDescriptor.l("latitude", false);
        pluginGeneratedSerialDescriptor.l("longitude", false);
        pluginGeneratedSerialDescriptor.l("accuracy", false);
        pluginGeneratedSerialDescriptor.l("vertical_accuracy_meters", false);
        pluginGeneratedSerialDescriptor.l("speed", false);
        pluginGeneratedSerialDescriptor.l("bearing", false);
        pluginGeneratedSerialDescriptor.l("provider", false);
        pluginGeneratedSerialDescriptor.l("time", false);
        pluginGeneratedSerialDescriptor.l("is_mock", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private Location$$serializer() {
    }

    public KSerializer[] childSerializers() {
        KSerializer n4 = w.n(u0.f536a);
        C0048y yVar = C0048y.f550a;
        G g5 = G.f438a;
        return new KSerializer[]{yVar, yVar, yVar, g5, g5, g5, g5, n4, V.f466a, C0026g.f495a};
    }

    public Location deserialize(Decoder decoder) {
        Decoder decoder2 = decoder;
        K.n(decoder2, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        a a5 = decoder2.a(descriptor2);
        String str = null;
        int i5 = 0;
        boolean z4 = false;
        double d5 = 0.0d;
        double d6 = 0.0d;
        double d7 = 0.0d;
        float f5 = 0.0f;
        float f6 = 0.0f;
        float f7 = 0.0f;
        float f8 = 0.0f;
        long j5 = 0;
        boolean z5 = true;
        while (z5) {
            int y4 = a5.y(descriptor2);
            switch (y4) {
                case -1:
                    z5 = false;
                    break;
                case 0:
                    d5 = a5.j(descriptor2, 0);
                    i5 |= 1;
                    break;
                case 1:
                    d6 = a5.j(descriptor2, 1);
                    i5 |= 2;
                    break;
                case 2:
                    d7 = a5.j(descriptor2, 2);
                    i5 |= 4;
                    break;
                case 3:
                    f5 = a5.G(descriptor2, 3);
                    i5 |= 8;
                    break;
                case 4:
                    f6 = a5.G(descriptor2, 4);
                    i5 |= 16;
                    break;
                case 5:
                    f7 = a5.G(descriptor2, 5);
                    i5 |= 32;
                    break;
                case 6:
                    f8 = a5.G(descriptor2, 6);
                    i5 |= 64;
                    break;
                case 7:
                    str = (String) a5.f(descriptor2, 7, u0.f536a, str);
                    i5 |= 128;
                    break;
                case 8:
                    j5 = a5.D(descriptor2, 8);
                    i5 |= 256;
                    break;
                case 9:
                    z4 = a5.i(descriptor2, 9);
                    i5 |= NotificationCompat.FLAG_GROUP_SUMMARY;
                    break;
                default:
                    throw new m(y4);
            }
        }
        a5.c(descriptor2);
        return new Location(i5, d5, d6, d7, f5, f6, f7, f8, str, j5, z4, (q0) null);
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, Location location) {
        K.n(encoder, "encoder");
        K.n(location, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        b a5 = encoder.a(descriptor2);
        Location.write$Self$library_release(location, a5, descriptor2);
        a5.c(descriptor2);
    }

    public KSerializer[] typeParametersSerializers() {
        return C0029h0.f501b;
    }
}
