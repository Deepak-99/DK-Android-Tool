package com.hawkshaw.library.datalayer.models;

import B3.f;
import E3.q0;
import Y1.K;
import kotlinx.serialization.KSerializer;
import w3.w;

@f
public final class PushLocationRequest {
    public static final Companion Companion = new Companion((j3.f) null);
    private final Location location;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(j3.f fVar) {
            this();
        }

        public final KSerializer serializer() {
            return PushLocationRequest$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ PushLocationRequest(int i5, Location location2, q0 q0Var) {
        if (1 == (i5 & 1)) {
            this.location = location2;
        } else {
            w.x(i5, 1, PushLocationRequest$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
    }

    public static /* synthetic */ void getLocation$annotations() {
    }

    public final Location getLocation() {
        return this.location;
    }

    public PushLocationRequest(Location location2) {
        K.n(location2, "location");
        this.location = location2;
    }
}
