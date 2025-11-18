package com.hawkshaw.library.datalayer.models;

import B3.f;
import E3.C0020d;
import E3.q0;
import java.util.List;
import kotlinx.serialization.KSerializer;
import w3.w;

@f
public final class PushCallLogsRequest {
    /* access modifiers changed from: private */
    public static final KSerializer[] $childSerializers = {new C0020d(CallLog$$serializer.INSTANCE, 0)};
    public static final Companion Companion = new Companion((j3.f) null);
    private final List<CallLog> calllogs;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(j3.f fVar) {
            this();
        }

        public final KSerializer serializer() {
            return PushCallLogsRequest$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ PushCallLogsRequest(int i5, List list, q0 q0Var) {
        if (1 == (i5 & 1)) {
            this.calllogs = list;
        } else {
            w.x(i5, 1, PushCallLogsRequest$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
    }

    public static /* synthetic */ void getCalllogs$annotations() {
    }

    public final List<CallLog> getCalllogs() {
        return this.calllogs;
    }

    public PushCallLogsRequest(List<CallLog> list) {
        this.calllogs = list;
    }
}
