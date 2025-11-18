package com.hawkshaw.library.datalayer.models;

import B3.f;
import E3.q0;
import Y1.K;
import kotlinx.serialization.KSerializer;
import w3.w;

@f
public final class PushFCMTokenRequest {
    public static final Companion Companion = new Companion((j3.f) null);
    private final String token;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(j3.f fVar) {
            this();
        }

        public final KSerializer serializer() {
            return PushFCMTokenRequest$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ PushFCMTokenRequest(int i5, String str, q0 q0Var) {
        if (1 == (i5 & 1)) {
            this.token = str;
        } else {
            w.x(i5, 1, PushFCMTokenRequest$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
    }

    public static /* synthetic */ void getToken$annotations() {
    }

    public final String getToken() {
        return this.token;
    }

    public PushFCMTokenRequest(String str) {
        K.n(str, "token");
        this.token = str;
    }
}
