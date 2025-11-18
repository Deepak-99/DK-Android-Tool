package com.hawkshaw.library.datalayer.models;

import B3.f;
import E3.C0020d;
import E3.q0;
import E3.u0;
import java.util.List;
import kotlinx.serialization.KSerializer;
import w3.w;

@f
public final class GetPendingThumbnailsResponse {
    /* access modifiers changed from: private */
    public static final KSerializer[] $childSerializers = {new C0020d(u0.f536a, 0)};
    public static final Companion Companion = new Companion((j3.f) null);
    private final List<String> paths;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(j3.f fVar) {
            this();
        }

        public final KSerializer serializer() {
            return GetPendingThumbnailsResponse$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ GetPendingThumbnailsResponse(int i5, List list, q0 q0Var) {
        if (1 == (i5 & 1)) {
            this.paths = list;
        } else {
            w.x(i5, 1, GetPendingThumbnailsResponse$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
    }

    public static /* synthetic */ void getPaths$annotations() {
    }

    public final List<String> getPaths() {
        return this.paths;
    }

    public GetPendingThumbnailsResponse(List<String> list) {
        this.paths = list;
    }
}
