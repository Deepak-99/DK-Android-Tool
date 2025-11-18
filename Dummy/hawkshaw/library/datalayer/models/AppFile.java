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
public final class AppFile {
    public static final Companion Companion = new Companion((j3.f) null);
    private final boolean canRead;
    private final boolean canWrite;
    private final long lastModified;
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
            return AppFile$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ AppFile(int i5, String str, String str2, String str3, boolean z4, boolean z5, long j5, long j6, q0 q0Var) {
        if (127 == (i5 & 127)) {
            this.name = str;
            this.path = str2;
            this.mime = str3;
            this.canRead = z4;
            this.canWrite = z5;
            this.lastModified = j5;
            this.length = j6;
            return;
        }
        w.x(i5, 127, AppFile$$serializer.INSTANCE.getDescriptor());
        throw null;
    }

    public static /* synthetic */ void getCanRead$annotations() {
    }

    public static /* synthetic */ void getCanWrite$annotations() {
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

    public static final /* synthetic */ void write$Self$library_release(AppFile appFile, b bVar, SerialDescriptor serialDescriptor) {
        q qVar = (q) bVar;
        qVar.f0(serialDescriptor, 0, appFile.name);
        qVar.f0(serialDescriptor, 1, appFile.path);
        qVar.s(serialDescriptor, 2, u0.f536a, appFile.mime);
        qVar.X(serialDescriptor, 3, appFile.canRead);
        qVar.X(serialDescriptor, 4, appFile.canWrite);
        qVar.d0(serialDescriptor, 5, appFile.lastModified);
        qVar.d0(serialDescriptor, 6, appFile.length);
    }

    public final boolean getCanRead() {
        return this.canRead;
    }

    public final boolean getCanWrite() {
        return this.canWrite;
    }

    public final long getLastModified() {
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

    public AppFile(String str, String str2, String str3, boolean z4, boolean z5, long j5, long j6) {
        K.n(str, "name");
        K.n(str2, "path");
        this.name = str;
        this.path = str2;
        this.mime = str3;
        this.canRead = z4;
        this.canWrite = z5;
        this.lastModified = j5;
        this.length = j6;
    }
}
