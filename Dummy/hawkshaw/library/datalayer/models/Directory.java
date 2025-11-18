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
public final class Directory {
    /* access modifiers changed from: private */
    public static final KSerializer[] $childSerializers = {null, null, null, null, null, new C0020d(AppFile$$serializer.INSTANCE, 0), null};
    public static final Companion Companion = new Companion((j3.f) null);
    private final boolean canRead;
    private final boolean canWrite;
    private final List<Directory> directories;
    private final List<AppFile> files;
    private final long lastModified;
    private final String name;
    private final String path;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(j3.f fVar) {
            this();
        }

        public final KSerializer serializer() {
            return Directory$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ Directory(int i5, String str, String str2, boolean z4, boolean z5, long j5, List list, List list2, q0 q0Var) {
        if (127 == (i5 & 127)) {
            this.name = str;
            this.path = str2;
            this.canRead = z4;
            this.canWrite = z5;
            this.lastModified = j5;
            this.files = list;
            this.directories = list2;
            return;
        }
        w.x(i5, 127, Directory$$serializer.INSTANCE.getDescriptor());
        throw null;
    }

    public static /* synthetic */ void getCanRead$annotations() {
    }

    public static /* synthetic */ void getCanWrite$annotations() {
    }

    public static /* synthetic */ void getDirectories$annotations() {
    }

    public static /* synthetic */ void getFiles$annotations() {
    }

    public static /* synthetic */ void getLastModified$annotations() {
    }

    public static /* synthetic */ void getName$annotations() {
    }

    public static /* synthetic */ void getPath$annotations() {
    }

    public static final /* synthetic */ void write$Self$library_release(Directory directory, b bVar, SerialDescriptor serialDescriptor) {
        KSerializer[] kSerializerArr = $childSerializers;
        q qVar = (q) bVar;
        qVar.f0(serialDescriptor, 0, directory.name);
        qVar.f0(serialDescriptor, 1, directory.path);
        qVar.X(serialDescriptor, 2, directory.canRead);
        qVar.X(serialDescriptor, 3, directory.canWrite);
        qVar.d0(serialDescriptor, 4, directory.lastModified);
        qVar.s(serialDescriptor, 5, kSerializerArr[5], directory.files);
        qVar.s(serialDescriptor, 6, new C0020d(Directory$$serializer.INSTANCE, 0), directory.directories);
    }

    public final boolean getCanRead() {
        return this.canRead;
    }

    public final boolean getCanWrite() {
        return this.canWrite;
    }

    public final List<Directory> getDirectories() {
        return this.directories;
    }

    public final List<AppFile> getFiles() {
        return this.files;
    }

    public final long getLastModified() {
        return this.lastModified;
    }

    public final String getName() {
        return this.name;
    }

    public final String getPath() {
        return this.path;
    }

    public Directory(String str, String str2, boolean z4, boolean z5, long j5, List<AppFile> list, List<Directory> list2) {
        K.n(str, "name");
        K.n(str2, "path");
        this.name = str;
        this.path = str2;
        this.canRead = z4;
        this.canWrite = z5;
        this.lastModified = j5;
        this.files = list;
        this.directories = list2;
    }
}
