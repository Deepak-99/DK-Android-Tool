package com.hawkshaw.library.datalayer.models;

import B3.f;
import E3.C0020d;
import E3.q0;
import java.util.List;
import kotlinx.serialization.KSerializer;
import w3.w;

@f
public final class PushMessagesRequest {
    /* access modifiers changed from: private */
    public static final KSerializer[] $childSerializers = {new C0020d(Message$$serializer.INSTANCE, 0)};
    public static final Companion Companion = new Companion((j3.f) null);
    private final List<Message> messages;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(j3.f fVar) {
            this();
        }

        public final KSerializer serializer() {
            return PushMessagesRequest$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ PushMessagesRequest(int i5, List list, q0 q0Var) {
        if (1 == (i5 & 1)) {
            this.messages = list;
        } else {
            w.x(i5, 1, PushMessagesRequest$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
    }

    public static /* synthetic */ void getMessages$annotations() {
    }

    public final List<Message> getMessages() {
        return this.messages;
    }

    public PushMessagesRequest(List<Message> list) {
        this.messages = list;
    }
}
