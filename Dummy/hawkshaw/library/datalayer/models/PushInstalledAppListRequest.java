package com.hawkshaw.library.datalayer.models;

import B3.f;
import E3.C0020d;
import E3.q0;
import Y1.K;
import java.util.List;
import kotlinx.serialization.KSerializer;
import w3.w;

@f
public final class PushInstalledAppListRequest {
    /* access modifiers changed from: private */
    public static final KSerializer[] $childSerializers = {new C0020d(InstalledApp$$serializer.INSTANCE, 0)};
    public static final Companion Companion = new Companion((j3.f) null);
    private final List<InstalledApp> apps;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(j3.f fVar) {
            this();
        }

        public final KSerializer serializer() {
            return PushInstalledAppListRequest$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ PushInstalledAppListRequest(int i5, List list, q0 q0Var) {
        if (1 == (i5 & 1)) {
            this.apps = list;
        } else {
            w.x(i5, 1, PushInstalledAppListRequest$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
    }

    public static /* synthetic */ void getApps$annotations() {
    }

    public final List<InstalledApp> getApps() {
        return this.apps;
    }

    public PushInstalledAppListRequest(List<InstalledApp> list) {
        K.n(list, "apps");
        this.apps = list;
    }
}
