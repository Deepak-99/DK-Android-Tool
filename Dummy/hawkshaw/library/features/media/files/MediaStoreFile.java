package com.hawkshaw.library.features.media.files;

import B3.f;
import D3.b;
import E3.O;
import E3.V;
import E3.q0;
import E3.u0;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import p3.q;
import w3.w;

@f
public final class MediaStoreFile {
    public static final Companion Companion = new Companion((j3.f) null);
    private final String bucketId;
    private final String bucketName;
    private final String contentUri;
    private final String data;
    private final Long dateAdded;
    private final Long dateModified;
    private final String documentId;
    private final long id;
    private final String mediaType;
    private final String mime;
    private final String name;
    private final String ownerPackageName;
    private final String parent;
    private final String relativePath;
    private final Integer size;
    private final String title;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(j3.f fVar) {
            this();
        }

        public final KSerializer serializer() {
            return MediaStoreFile$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ MediaStoreFile(int i5, long j5, String str, String str2, String str3, String str4, Long l5, Long l6, String str5, String str6, String str7, String str8, String str9, Integer num, String str10, String str11, String str12, q0 q0Var) {
        if (65535 == (i5 & 65535)) {
            this.id = j5;
            this.bucketName = str;
            this.bucketId = str2;
            this.contentUri = str3;
            this.data = str4;
            this.dateAdded = l5;
            this.dateModified = l6;
            this.documentId = str5;
            this.mime = str6;
            this.name = str7;
            this.ownerPackageName = str8;
            this.relativePath = str9;
            this.size = num;
            this.title = str10;
            this.mediaType = str11;
            this.parent = str12;
            return;
        }
        w.x(i5, 65535, MediaStoreFile$$serializer.INSTANCE.getDescriptor());
        throw null;
    }

    public static /* synthetic */ void getBucketId$annotations() {
    }

    public static /* synthetic */ void getBucketName$annotations() {
    }

    public static /* synthetic */ void getContentUri$annotations() {
    }

    public static /* synthetic */ void getDateAdded$annotations() {
    }

    public static /* synthetic */ void getDateModified$annotations() {
    }

    public static /* synthetic */ void getDocumentId$annotations() {
    }

    public static /* synthetic */ void getMediaType$annotations() {
    }

    public static /* synthetic */ void getOwnerPackageName$annotations() {
    }

    public static /* synthetic */ void getRelativePath$annotations() {
    }

    public static final /* synthetic */ void write$Self$library_release(MediaStoreFile mediaStoreFile, b bVar, SerialDescriptor serialDescriptor) {
        q qVar = (q) bVar;
        qVar.d0(serialDescriptor, 0, mediaStoreFile.id);
        u0 u0Var = u0.f536a;
        qVar.s(serialDescriptor, 1, u0Var, mediaStoreFile.bucketName);
        qVar.s(serialDescriptor, 2, u0Var, mediaStoreFile.bucketId);
        qVar.s(serialDescriptor, 3, u0Var, mediaStoreFile.contentUri);
        qVar.s(serialDescriptor, 4, u0Var, mediaStoreFile.data);
        V v4 = V.f466a;
        qVar.s(serialDescriptor, 5, v4, mediaStoreFile.dateAdded);
        qVar.s(serialDescriptor, 6, v4, mediaStoreFile.dateModified);
        qVar.s(serialDescriptor, 7, u0Var, mediaStoreFile.documentId);
        qVar.s(serialDescriptor, 8, u0Var, mediaStoreFile.mime);
        qVar.s(serialDescriptor, 9, u0Var, mediaStoreFile.name);
        qVar.s(serialDescriptor, 10, u0Var, mediaStoreFile.ownerPackageName);
        qVar.s(serialDescriptor, 11, u0Var, mediaStoreFile.relativePath);
        qVar.s(serialDescriptor, 12, O.f456a, mediaStoreFile.size);
        qVar.s(serialDescriptor, 13, u0Var, mediaStoreFile.title);
        qVar.s(serialDescriptor, 14, u0Var, mediaStoreFile.mediaType);
        qVar.s(serialDescriptor, 15, u0Var, mediaStoreFile.parent);
    }

    public final String getBucketId() {
        return this.bucketId;
    }

    public final String getBucketName() {
        return this.bucketName;
    }

    public final String getContentUri() {
        return this.contentUri;
    }

    public final String getData() {
        return this.data;
    }

    public final Long getDateAdded() {
        return this.dateAdded;
    }

    public final Long getDateModified() {
        return this.dateModified;
    }

    public final String getDocumentId() {
        return this.documentId;
    }

    public final long getId() {
        return this.id;
    }

    public final String getMediaType() {
        return this.mediaType;
    }

    public final String getMime() {
        return this.mime;
    }

    public final String getName() {
        return this.name;
    }

    public final String getOwnerPackageName() {
        return this.ownerPackageName;
    }

    public final String getParent() {
        return this.parent;
    }

    public final String getRelativePath() {
        return this.relativePath;
    }

    public final Integer getSize() {
        return this.size;
    }

    public final String getTitle() {
        return this.title;
    }

    public MediaStoreFile(long j5, String str, String str2, String str3, String str4, Long l5, Long l6, String str5, String str6, String str7, String str8, String str9, Integer num, String str10, String str11, String str12) {
        this.id = j5;
        this.bucketName = str;
        this.bucketId = str2;
        this.contentUri = str3;
        this.data = str4;
        this.dateAdded = l5;
        this.dateModified = l6;
        this.documentId = str5;
        this.mime = str6;
        this.name = str7;
        this.ownerPackageName = str8;
        this.relativePath = str9;
        this.size = num;
        this.title = str10;
        this.mediaType = str11;
        this.parent = str12;
    }
}
