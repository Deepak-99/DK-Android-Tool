package com.hawkshaw.library.datalayer.models;

import B3.m;
import D3.a;
import D3.b;
import E3.C0020d;
import E3.C0026g;
import E3.C0029h0;
import E3.H;
import E3.V;
import E3.q0;
import E3.u0;
import Y1.K;
import java.util.List;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import w3.w;

public final class Directory$$serializer implements H {
    public static final Directory$$serializer INSTANCE;
    private static final /* synthetic */ PluginGeneratedSerialDescriptor descriptor;

    static {
        Directory$$serializer directory$$serializer = new Directory$$serializer();
        INSTANCE = directory$$serializer;
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.hawkshaw.library.datalayer.models.Directory", directory$$serializer, 7);
        pluginGeneratedSerialDescriptor.l("name", false);
        pluginGeneratedSerialDescriptor.l("path", false);
        pluginGeneratedSerialDescriptor.l("can_read", false);
        pluginGeneratedSerialDescriptor.l("can_write", false);
        pluginGeneratedSerialDescriptor.l("last_modified", false);
        pluginGeneratedSerialDescriptor.l("files", false);
        pluginGeneratedSerialDescriptor.l("directories", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    private Directory$$serializer() {
    }

    public KSerializer[] childSerializers() {
        KSerializer n4 = w.n(Directory.$childSerializers[5]);
        KSerializer n5 = w.n(new C0020d(INSTANCE, 0));
        u0 u0Var = u0.f536a;
        C0026g gVar = C0026g.f495a;
        return new KSerializer[]{u0Var, u0Var, gVar, gVar, V.f466a, n4, n5};
    }

    public Directory deserialize(Decoder decoder) {
        Decoder decoder2 = decoder;
        K.n(decoder2, "decoder");
        SerialDescriptor descriptor2 = getDescriptor();
        a a5 = decoder2.a(descriptor2);
        KSerializer[] access$get$childSerializers$cp = Directory.$childSerializers;
        List list = null;
        int i5 = 0;
        boolean z4 = false;
        boolean z5 = false;
        String str = null;
        String str2 = null;
        long j5 = 0;
        boolean z6 = true;
        List list2 = null;
        while (z6) {
            int y4 = a5.y(descriptor2);
            switch (y4) {
                case -1:
                    z6 = false;
                    break;
                case 0:
                    str = a5.q(descriptor2, 0);
                    i5 |= 1;
                    break;
                case 1:
                    str2 = a5.q(descriptor2, 1);
                    i5 |= 2;
                    break;
                case 2:
                    z4 = a5.i(descriptor2, 2);
                    i5 |= 4;
                    break;
                case 3:
                    z5 = a5.i(descriptor2, 3);
                    i5 |= 8;
                    break;
                case 4:
                    j5 = a5.D(descriptor2, 4);
                    i5 |= 16;
                    break;
                case 5:
                    list = (List) a5.f(descriptor2, 5, access$get$childSerializers$cp[5], list);
                    i5 |= 32;
                    break;
                case 6:
                    list2 = (List) a5.f(descriptor2, 6, new C0020d(INSTANCE, 0), list2);
                    i5 |= 64;
                    break;
                default:
                    throw new m(y4);
            }
        }
        a5.c(descriptor2);
        return new Directory(i5, str, str2, z4, z5, j5, list, list2, (q0) null);
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(Encoder encoder, Directory directory) {
        K.n(encoder, "encoder");
        K.n(directory, "value");
        SerialDescriptor descriptor2 = getDescriptor();
        b a5 = encoder.a(descriptor2);
        Directory.write$Self$library_release(directory, a5, descriptor2);
        a5.c(descriptor2);
    }

    public KSerializer[] typeParametersSerializers() {
        return C0029h0.f501b;
    }
}
