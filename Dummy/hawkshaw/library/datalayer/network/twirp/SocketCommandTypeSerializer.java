package com.hawkshaw.library.datalayer.network.twirp;

import X2.l;
import X2.w;
import Y1.C0110h;
import Y1.K;
import com.hawkshaw.library.datalayer.models.SocketCommandRequest;
import d3.C0393a;
import j3.t;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import t3.F;

public final class SocketCommandTypeSerializer implements KSerializer {
    public static final SocketCommandTypeSerializer INSTANCE = new SocketCommandTypeSerializer();
    private static final String className;
    private static final SerialDescriptor descriptor = F.g(className);
    private static final Map<SocketCommandRequest.Type, String> lookup;
    private static final Map<String, SocketCommandRequest.Type> revLookup;

    static {
        String b5 = t.a(SocketCommandTypeSerializer.class).b();
        K.k(b5);
        className = b5;
        C0393a<SocketCommandRequest.Type> entries = SocketCommandRequest.Type.getEntries();
        int B4 = C0110h.B(l.L0(entries, 10));
        int i5 = 16;
        if (B4 < 16) {
            B4 = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(B4);
        for (SocketCommandRequest.Type type : entries) {
            linkedHashMap.put(type, type.name());
        }
        lookup = linkedHashMap;
        C0393a entries2 = SocketCommandRequest.Type.getEntries();
        int B5 = C0110h.B(l.L0(entries2, 10));
        if (B5 >= 16) {
            i5 = B5;
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(i5);
        for (Object next : entries2) {
            linkedHashMap2.put(((SocketCommandRequest.Type) next).name(), next);
        }
        revLookup = linkedHashMap2;
    }

    private SocketCommandTypeSerializer() {
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public SocketCommandRequest.Type deserialize(Decoder decoder) {
        K.n(decoder, "decoder");
        return revLookup.getOrDefault(decoder.A(), SocketCommandRequest.Type.Unknown);
    }

    public void serialize(Encoder encoder, SocketCommandRequest.Type type) {
        K.n(encoder, "encoder");
        K.n(type, "value");
        encoder.r((String) w.W(lookup, type));
    }
}
