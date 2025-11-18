package com.hawkshaw.library.datalayer.models;

import Y1.K;

public final class BatchThumbUploadRequest {
    private final Thumbnail thumb;

    public static final class Thumbnail {
        private final byte[] content;
        private final String name;
        private final String path;

        public Thumbnail(String str, String str2, byte[] bArr) {
            K.n(str, "name");
            K.n(str2, "path");
            K.n(bArr, "content");
            this.name = str;
            this.path = str2;
            this.content = bArr;
        }

        public final byte[] getContent() {
            return this.content;
        }

        public final String getName() {
            return this.name;
        }

        public final String getPath() {
            return this.path;
        }
    }

    public BatchThumbUploadRequest(Thumbnail thumbnail) {
        K.n(thumbnail, "thumb");
        this.thumb = thumbnail;
    }

    public final Thumbnail getThumb() {
        return this.thumb;
    }
}
