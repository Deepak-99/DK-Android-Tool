package com.hawkshaw.library.datalayer.models;

import B3.f;
import D3.b;
import E3.q0;
import E3.u0;
import Y1.K;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import p3.q;
import w3.w;

@f
public final class AppFileV2 {
    public static final Companion Companion = new Companion((j3.f) null);
    private final Timestamp lastModified;
    private final long length;
    private final String mime;
    private final String name;
    private final String path;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(j3.f fVar) {
            this();
        }

        public final KSerializer serializer() {
            return AppFileV2$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ AppFileV2(int i5, String str, String str2, String str3, Timestamp timestamp, long j5, q0 q0Var) {
        if (31 == (i5 & 31)) {
            this.name = str;
            this.path = str2;
            this.mime = str3;
            this.lastModified = timestamp;
            this.length = j5;
            return;
        }
        w.x(i5, 31, AppFileV2$$serializer.INSTANCE.getDescriptor());
        throw null;
    }

    public static /* synthetic */ void getLastModified$annotations() {
    }

    public static /* synthetic */ void getLength$annotations() {
    }

    public static /* synthetic */ void getMime$annotations() {
    }

    public static /* synthetic */ void getName$annotations() {
    }

    public static /* synthetic */ void getPath$annotations() {
    }

    public static final /* synthetic */ void write$Self$library_release(AppFileV2 appFileV2, b bVar, SerialDescriptor serialDescriptor) {
        q qVar = (q) bVar;
        qVar.f0(serialDescriptor, 0, appFileV2.name);
        qVar.f0(serialDescriptor, 1, appFileV2.path);
        qVar.s(serialDescriptor, 2, u0.f536a, appFileV2.mime);
        qVar.e0(serialDescriptor, 3, TimestampSerializer.INSTANCE, appFileV2.lastModified);
        qVar.d0(serialDescriptor, 4, appFileV2.length);
    }

    public final Timestamp getLastModified() {
        return this.lastModified;
    }

    public final long getLength() {
        return this.length;
    }

    public final String getMime() {
        return this.mime;
    }

    public final String getName() {
        return this.name;
    }

    public final String getPath() {
        return this.path;
    }

    public AppFileV2(String str, String str2, String str3, Timestamp timestamp, long j5) {
        K.n(str, "name");
        K.n(str2, "path");
        K.n(timestamp, "lastModified");
        this.name = str;
        this.path = str2;
        this.mime = str3;
        this.lastModified = timestamp;
        this.length = j5;
    }
}
