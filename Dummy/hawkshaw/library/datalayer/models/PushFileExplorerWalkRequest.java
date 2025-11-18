package com.hawkshaw.library.datalayer.models;

import B3.f;
import E3.q0;
import Y1.K;
import kotlinx.serialization.KSerializer;
import w3.w;

@f
public final class PushFileExplorerWalkRequest {
    public static final Companion Companion = new Companion((j3.f) null);
    private final Directory directory;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(j3.f fVar) {
            this();
        }

        public final KSerializer serializer() {
            return PushFileExplorerWalkRequest$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ PushFileExplorerWalkRequest(int i5, Directory directory2, q0 q0Var) {
        if (1 == (i5 & 1)) {
            this.directory = directory2;
        } else {
            w.x(i5, 1, PushFileExplorerWalkRequest$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
    }

    public static /* synthetic */ void getDirectory$annotations() {
    }

    public final Directory getDirectory() {
        return this.directory;
    }

    public PushFileExplorerWalkRequest(Directory directory2) {
        K.n(directory2, "directory");
        this.directory = directory2;
    }
}
