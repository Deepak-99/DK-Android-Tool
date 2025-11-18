package com.hawkshaw.library.datalayer.models.accessibility;

import B3.f;
import E3.q0;
import Y1.K;
import kotlinx.serialization.KSerializer;
import w3.w;

@f
public final class PushNotificationResponse {
    public static final Companion Companion = new Companion((j3.f) null);
    private final String message;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(j3.f fVar) {
            this();
        }

        public final KSerializer serializer() {
            return PushNotificationResponse$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ PushNotificationResponse(int i5, String str, q0 q0Var) {
        if (1 == (i5 & 1)) {
            this.message = str;
        } else {
            w.x(i5, 1, PushNotificationResponse$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
    }

    public static /* synthetic */ void getMessage$annotations() {
    }

    public final String getMessage() {
        return this.message;
    }

    public PushNotificationResponse(String str) {
        K.n(str, "message");
        this.message = str;
    }
}
