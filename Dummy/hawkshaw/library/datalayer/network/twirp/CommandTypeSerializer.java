package com.hawkshaw.library.datalayer.network.twirp;

import X2.l;
import X2.w;
import Y1.C0110h;
import Y1.K;
import com.hawkshaw.library.datalayer.models.Command;
import d3.C0393a;
import j3.t;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import t3.F;

public final class CommandTypeSerializer implements KSerializer {
    public static final CommandTypeSerializer INSTANCE = new CommandTypeSerializer();
    private static final String className;
    private static final SerialDescriptor descriptor = F.g(className);
    private static final Map<Command.CommandType, String> lookup;
    private static final Map<String, Command.CommandType> revLookup;

    static {
        String b5 = t.a(CommandTypeSerializer.class).b();
        K.k(b5);
        className = b5;
        C0393a<Command.CommandType> entries = Command.CommandType.getEntries();
        int B4 = C0110h.B(l.L0(entries, 10));
        int i5 = 16;
        if (B4 < 16) {
            B4 = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(B4);
        for (Command.CommandType commandType : entries) {
            linkedHashMap.put(commandType, commandType.name());
        }
        lookup = linkedHashMap;
        C0393a entries2 = Command.CommandType.getEntries();
        int B5 = C0110h.B(l.L0(entries2, 10));
        if (B5 >= 16) {
            i5 = B5;
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(i5);
        for (Object next : entries2) {
            linkedHashMap2.put(((Command.CommandType) next).name(), next);
        }
        revLookup = linkedHashMap2;
    }

    private CommandTypeSerializer() {
    }

    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public Command.CommandType deserialize(Decoder decoder) {
        K.n(decoder, "decoder");
        return revLookup.getOrDefault(decoder.A(), Command.CommandType.Unknown);
    }

    public void serialize(Encoder encoder, Command.CommandType commandType) {
        K.n(encoder, "encoder");
        K.n(commandType, "value");
        encoder.r((String) w.W(lookup, commandType));
    }
}
