package com.hawkshaw.library;

import com.google.protobuf.C0346c;
import com.google.protobuf.C0366m;
import com.google.protobuf.C0369n0;
import com.google.protobuf.C0371o0;
import com.google.protobuf.C0374q;
import com.google.protobuf.C0382u0;
import com.google.protobuf.C0385x;
import com.google.protobuf.D;
import com.google.protobuf.E;
import com.google.protobuf.H;
import com.google.protobuf.I;
import com.google.protobuf.M;
import com.google.protobuf.N;
import com.google.protobuf.O;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class FileUploadRequest extends I implements FileUploadRequestOrBuilder {
    /* access modifiers changed from: private */
    public static final FileUploadRequest DEFAULT_INSTANCE;
    public static final int FILE_FIELD_NUMBER = 2;
    public static final int METADATA_FIELD_NUMBER = 1;
    private static volatile C0382u0 PARSER;
    private int requestCase_ = 0;
    private Object request_;

    public static final class Builder extends D implements FileUploadRequestOrBuilder {
        public /* synthetic */ Builder(int i5) {
            this();
        }

        public Builder clearFile() {
            copyOnWrite();
            ((FileUploadRequest) this.instance).clearFile();
            return this;
        }

        public Builder clearMetadata() {
            copyOnWrite();
            ((FileUploadRequest) this.instance).clearMetadata();
            return this;
        }

        public Builder clearRequest() {
            copyOnWrite();
            ((FileUploadRequest) this.instance).clearRequest();
            return this;
        }

        public File getFile() {
            return ((FileUploadRequest) this.instance).getFile();
        }

        public MetaData getMetadata() {
            return ((FileUploadRequest) this.instance).getMetadata();
        }

        public RequestCase getRequestCase() {
            return ((FileUploadRequest) this.instance).getRequestCase();
        }

        public boolean hasFile() {
            return ((FileUploadRequest) this.instance).hasFile();
        }

        public boolean hasMetadata() {
            return ((FileUploadRequest) this.instance).hasMetadata();
        }

        public Builder mergeFile(File file) {
            copyOnWrite();
            ((FileUploadRequest) this.instance).mergeFile(file);
            return this;
        }

        public Builder mergeMetadata(MetaData metaData) {
            copyOnWrite();
            ((FileUploadRequest) this.instance).mergeMetadata(metaData);
            return this;
        }

        public Builder setFile(File file) {
            copyOnWrite();
            ((FileUploadRequest) this.instance).setFile(file);
            return this;
        }

        public Builder setMetadata(MetaData metaData) {
            copyOnWrite();
            ((FileUploadRequest) this.instance).setMetadata(metaData);
            return this;
        }

        private Builder() {
            super(FileUploadRequest.DEFAULT_INSTANCE);
        }

        public Builder setFile(File.Builder builder) {
            copyOnWrite();
            ((FileUploadRequest) this.instance).setFile((File) builder.build());
            return this;
        }

        public Builder setMetadata(MetaData.Builder builder) {
            copyOnWrite();
            ((FileUploadRequest) this.instance).setMetadata((MetaData) builder.build());
            return this;
        }
    }

    public static final class File extends I implements FileOrBuilder {
        public static final int CONTENT_FIELD_NUMBER = 1;
        /* access modifiers changed from: private */
        public static final File DEFAULT_INSTANCE;
        private static volatile C0382u0 PARSER;
        private C0366m content_ = C0366m.f4803E;

        public static final class Builder extends D implements FileOrBuilder {
            public /* synthetic */ Builder(int i5) {
                this();
            }

            public Builder clearContent() {
                copyOnWrite();
                ((File) this.instance).clearContent();
                return this;
            }

            public C0366m getContent() {
                return ((File) this.instance).getContent();
            }

            public Builder setContent(C0366m mVar) {
                copyOnWrite();
                ((File) this.instance).setContent(mVar);
                return this;
            }

            private Builder() {
                super(File.DEFAULT_INSTANCE);
            }
        }

        static {
            File file = new File();
            DEFAULT_INSTANCE = file;
            I.registerDefaultInstance(File.class, file);
        }

        private File() {
        }

        /* access modifiers changed from: private */
        public void clearContent() {
            this.content_ = getDefaultInstance().getContent();
        }

        public static File getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static File parseDelimitedFrom(InputStream inputStream) {
            return (File) I.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static File parseFrom(ByteBuffer byteBuffer) {
            return (File) I.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static C0382u0 parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* access modifiers changed from: private */
        public void setContent(C0366m mVar) {
            mVar.getClass();
            this.content_ = mVar;
        }

        public final Object dynamicMethod(H h5, Object obj, Object obj2) {
            switch (h5.ordinal()) {
                case 0:
                    return (byte) 1;
                case 1:
                    return null;
                case 2:
                    return I.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\n", new Object[]{"content_"});
                case 3:
                    return new File();
                case 4:
                    return new Builder(0);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    C0382u0 u0Var = PARSER;
                    if (u0Var == null) {
                        synchronized (File.class) {
                            try {
                                u0Var = PARSER;
                                if (u0Var == null) {
                                    u0Var = new E(DEFAULT_INSTANCE);
                                    PARSER = u0Var;
                                }
                            } catch (Throwable th) {
                                throw th;
                            }
                        }
                    }
                    return u0Var;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public C0366m getContent() {
            return this.content_;
        }

        public static Builder newBuilder(File file) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(file);
        }

        public static File parseDelimitedFrom(InputStream inputStream, C0385x xVar) {
            return (File) I.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, xVar);
        }

        public static File parseFrom(ByteBuffer byteBuffer, C0385x xVar) {
            return (File) I.parseFrom(DEFAULT_INSTANCE, byteBuffer, xVar);
        }

        public static File parseFrom(C0366m mVar) {
            return (File) I.parseFrom(DEFAULT_INSTANCE, mVar);
        }

        public static File parseFrom(C0366m mVar, C0385x xVar) {
            return (File) I.parseFrom(DEFAULT_INSTANCE, mVar, xVar);
        }

        public static File parseFrom(byte[] bArr) {
            return (File) I.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static File parseFrom(byte[] bArr, C0385x xVar) {
            return (File) I.parseFrom(DEFAULT_INSTANCE, bArr, xVar);
        }

        public static File parseFrom(InputStream inputStream) {
            return (File) I.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static File parseFrom(InputStream inputStream, C0385x xVar) {
            return (File) I.parseFrom(DEFAULT_INSTANCE, inputStream, xVar);
        }

        public static File parseFrom(C0374q qVar) {
            return (File) I.parseFrom(DEFAULT_INSTANCE, qVar);
        }

        public static File parseFrom(C0374q qVar, C0385x xVar) {
            return (File) I.parseFrom(DEFAULT_INSTANCE, qVar, xVar);
        }
    }

    public interface FileOrBuilder extends C0371o0 {
        C0366m getContent();

        /* synthetic */ C0369n0 getDefaultInstanceForType();

        /* synthetic */ boolean isInitialized();
    }

    public static final class MetaData extends I implements MetaDataOrBuilder {
        /* access modifiers changed from: private */
        public static final MetaData DEFAULT_INSTANCE;
        public static final int MIME_FIELD_NUMBER = 3;
        public static final int NAME_FIELD_NUMBER = 1;
        private static volatile C0382u0 PARSER = null;
        public static final int PATH_FIELD_NUMBER = 2;
        public static final int SOURCE_FIELD_NUMBER = 4;
        private String mime_ = "";
        private String name_ = "";
        private String path_ = "";
        private int source_;

        public static final class Builder extends D implements MetaDataOrBuilder {
            public /* synthetic */ Builder(int i5) {
                this();
            }

            public Builder clearMime() {
                copyOnWrite();
                ((MetaData) this.instance).clearMime();
                return this;
            }

            public Builder clearName() {
                copyOnWrite();
                ((MetaData) this.instance).clearName();
                return this;
            }

            public Builder clearPath() {
                copyOnWrite();
                ((MetaData) this.instance).clearPath();
                return this;
            }

            public Builder clearSource() {
                copyOnWrite();
                ((MetaData) this.instance).clearSource();
                return this;
            }

            public String getMime() {
                return ((MetaData) this.instance).getMime();
            }

            public C0366m getMimeBytes() {
                return ((MetaData) this.instance).getMimeBytes();
            }

            public String getName() {
                return ((MetaData) this.instance).getName();
            }

            public C0366m getNameBytes() {
                return ((MetaData) this.instance).getNameBytes();
            }

            public String getPath() {
                return ((MetaData) this.instance).getPath();
            }

            public C0366m getPathBytes() {
                return ((MetaData) this.instance).getPathBytes();
            }

            public Source getSource() {
                return ((MetaData) this.instance).getSource();
            }

            public int getSourceValue() {
                return ((MetaData) this.instance).getSourceValue();
            }

            public Builder setMime(String str) {
                copyOnWrite();
                ((MetaData) this.instance).setMime(str);
                return this;
            }

            public Builder setMimeBytes(C0366m mVar) {
                copyOnWrite();
                ((MetaData) this.instance).setMimeBytes(mVar);
                return this;
            }

            public Builder setName(String str) {
                copyOnWrite();
                ((MetaData) this.instance).setName(str);
                return this;
            }

            public Builder setNameBytes(C0366m mVar) {
                copyOnWrite();
                ((MetaData) this.instance).setNameBytes(mVar);
                return this;
            }

            public Builder setPath(String str) {
                copyOnWrite();
                ((MetaData) this.instance).setPath(str);
                return this;
            }

            public Builder setPathBytes(C0366m mVar) {
                copyOnWrite();
                ((MetaData) this.instance).setPathBytes(mVar);
                return this;
            }

            public Builder setSource(Source source) {
                copyOnWrite();
                ((MetaData) this.instance).setSource(source);
                return this;
            }

            public Builder setSourceValue(int i5) {
                copyOnWrite();
                ((MetaData) this.instance).setSourceValue(i5);
                return this;
            }

            private Builder() {
                super(MetaData.DEFAULT_INSTANCE);
            }
        }

        public enum Source implements M {
            Unknown(0),
            CameraImage(1),
            VideoRecording(2),
            AudioRecording(3),
            Screenshot(4),
            ScreenRecording(5),
            FileExplorer(6),
            CallRecording(7),
            UNRECOGNIZED(-1);
            
            public static final int AudioRecording_VALUE = 3;
            public static final int CallRecording_VALUE = 7;
            public static final int CameraImage_VALUE = 1;
            public static final int FileExplorer_VALUE = 6;
            public static final int ScreenRecording_VALUE = 5;
            public static final int Screenshot_VALUE = 4;
            public static final int Unknown_VALUE = 0;
            public static final int VideoRecording_VALUE = 2;
            private static final N internalValueMap = null;
            private final int value;

            /* JADX WARNING: type inference failed for: r0v10, types: [com.google.protobuf.N, java.lang.Object] */
            static {
                internalValueMap = new Object();
            }

            private Source(int i5) {
                this.value = i5;
            }

            public static Source forNumber(int i5) {
                switch (i5) {
                    case 0:
                        return Unknown;
                    case 1:
                        return CameraImage;
                    case 2:
                        return VideoRecording;
                    case 3:
                        return AudioRecording;
                    case 4:
                        return Screenshot;
                    case 5:
                        return ScreenRecording;
                    case 6:
                        return FileExplorer;
                    case 7:
                        return CallRecording;
                    default:
                        return null;
                }
            }

            public static N internalGetValueMap() {
                return internalValueMap;
            }

            public static O internalGetVerifier() {
                return h.f5044a;
            }

            public final int getNumber() {
                if (this != UNRECOGNIZED) {
                    return this.value;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }

            @Deprecated
            public static Source valueOf(int i5) {
                return forNumber(i5);
            }
        }

        static {
            MetaData metaData = new MetaData();
            DEFAULT_INSTANCE = metaData;
            I.registerDefaultInstance(MetaData.class, metaData);
        }

        private MetaData() {
        }

        /* access modifiers changed from: private */
        public void clearMime() {
            this.mime_ = getDefaultInstance().getMime();
        }

        /* access modifiers changed from: private */
        public void clearName() {
            this.name_ = getDefaultInstance().getName();
        }

        /* access modifiers changed from: private */
        public void clearPath() {
            this.path_ = getDefaultInstance().getPath();
        }

        /* access modifiers changed from: private */
        public void clearSource() {
            this.source_ = 0;
        }

        public static MetaData getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static MetaData parseDelimitedFrom(InputStream inputStream) {
            return (MetaData) I.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static MetaData parseFrom(ByteBuffer byteBuffer) {
            return (MetaData) I.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static C0382u0 parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* access modifiers changed from: private */
        public void setMime(String str) {
            str.getClass();
            this.mime_ = str;
        }

        /* access modifiers changed from: private */
        public void setMimeBytes(C0366m mVar) {
            C0346c.checkByteStringIsUtf8(mVar);
            this.mime_ = mVar.r();
        }

        /* access modifiers changed from: private */
        public void setName(String str) {
            str.getClass();
            this.name_ = str;
        }

        /* access modifiers changed from: private */
        public void setNameBytes(C0366m mVar) {
            C0346c.checkByteStringIsUtf8(mVar);
            this.name_ = mVar.r();
        }

        /* access modifiers changed from: private */
        public void setPath(String str) {
            str.getClass();
            this.path_ = str;
        }

        /* access modifiers changed from: private */
        public void setPathBytes(C0366m mVar) {
            C0346c.checkByteStringIsUtf8(mVar);
            this.path_ = mVar.r();
        }

        /* access modifiers changed from: private */
        public void setSource(Source source) {
            this.source_ = source.getNumber();
        }

        /* access modifiers changed from: private */
        public void setSourceValue(int i5) {
            this.source_ = i5;
        }

        public final Object dynamicMethod(H h5, Object obj, Object obj2) {
            switch (h5.ordinal()) {
                case 0:
                    return (byte) 1;
                case 1:
                    return null;
                case 2:
                    return I.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003Ȉ\u0004\f", new Object[]{"name_", "path_", "mime_", "source_"});
                case 3:
                    return new MetaData();
                case 4:
                    return new Builder(0);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    C0382u0 u0Var = PARSER;
                    if (u0Var == null) {
                        synchronized (MetaData.class) {
                            try {
                                u0Var = PARSER;
                                if (u0Var == null) {
                                    u0Var = new E(DEFAULT_INSTANCE);
                                    PARSER = u0Var;
                                }
                            } catch (Throwable th) {
                                throw th;
                            }
                        }
                    }
                    return u0Var;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public String getMime() {
            return this.mime_;
        }

        public C0366m getMimeBytes() {
            return C0366m.k(this.mime_);
        }

        public String getName() {
            return this.name_;
        }

        public C0366m getNameBytes() {
            return C0366m.k(this.name_);
        }

        public String getPath() {
            return this.path_;
        }

        public C0366m getPathBytes() {
            return C0366m.k(this.path_);
        }

        public Source getSource() {
            Source forNumber = Source.forNumber(this.source_);
            return forNumber == null ? Source.UNRECOGNIZED : forNumber;
        }

        public int getSourceValue() {
            return this.source_;
        }

        public static Builder newBuilder(MetaData metaData) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(metaData);
        }

        public static MetaData parseDelimitedFrom(InputStream inputStream, C0385x xVar) {
            return (MetaData) I.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, xVar);
        }

        public static MetaData parseFrom(ByteBuffer byteBuffer, C0385x xVar) {
            return (MetaData) I.parseFrom(DEFAULT_INSTANCE, byteBuffer, xVar);
        }

        public static MetaData parseFrom(C0366m mVar) {
            return (MetaData) I.parseFrom(DEFAULT_INSTANCE, mVar);
        }

        public static MetaData parseFrom(C0366m mVar, C0385x xVar) {
            return (MetaData) I.parseFrom(DEFAULT_INSTANCE, mVar, xVar);
        }

        public static MetaData parseFrom(byte[] bArr) {
            return (MetaData) I.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static MetaData parseFrom(byte[] bArr, C0385x xVar) {
            return (MetaData) I.parseFrom(DEFAULT_INSTANCE, bArr, xVar);
        }

        public static MetaData parseFrom(InputStream inputStream) {
            return (MetaData) I.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static MetaData parseFrom(InputStream inputStream, C0385x xVar) {
            return (MetaData) I.parseFrom(DEFAULT_INSTANCE, inputStream, xVar);
        }

        public static MetaData parseFrom(C0374q qVar) {
            return (MetaData) I.parseFrom(DEFAULT_INSTANCE, qVar);
        }

        public static MetaData parseFrom(C0374q qVar, C0385x xVar) {
            return (MetaData) I.parseFrom(DEFAULT_INSTANCE, qVar, xVar);
        }
    }

    public interface MetaDataOrBuilder extends C0371o0 {
        /* synthetic */ C0369n0 getDefaultInstanceForType();

        String getMime();

        C0366m getMimeBytes();

        String getName();

        C0366m getNameBytes();

        String getPath();

        C0366m getPathBytes();

        MetaData.Source getSource();

        int getSourceValue();

        /* synthetic */ boolean isInitialized();
    }

    public enum RequestCase {
        METADATA(1),
        FILE(2),
        REQUEST_NOT_SET(0);
        
        private final int value;

        private RequestCase(int i5) {
            this.value = i5;
        }

        public static RequestCase forNumber(int i5) {
            if (i5 == 0) {
                return REQUEST_NOT_SET;
            }
            if (i5 == 1) {
                return METADATA;
            }
            if (i5 != 2) {
                return null;
            }
            return FILE;
        }

        public int getNumber() {
            return this.value;
        }

        @Deprecated
        public static RequestCase valueOf(int i5) {
            return forNumber(i5);
        }
    }

    static {
        FileUploadRequest fileUploadRequest = new FileUploadRequest();
        DEFAULT_INSTANCE = fileUploadRequest;
        I.registerDefaultInstance(FileUploadRequest.class, fileUploadRequest);
    }

    private FileUploadRequest() {
    }

    /* access modifiers changed from: private */
    public void clearFile() {
        if (this.requestCase_ == 2) {
            this.requestCase_ = 0;
            this.request_ = null;
        }
    }

    /* access modifiers changed from: private */
    public void clearMetadata() {
        if (this.requestCase_ == 1) {
            this.requestCase_ = 0;
            this.request_ = null;
        }
    }

    /* access modifiers changed from: private */
    public void clearRequest() {
        this.requestCase_ = 0;
        this.request_ = null;
    }

    public static FileUploadRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /* access modifiers changed from: private */
    public void mergeFile(File file) {
        file.getClass();
        if (this.requestCase_ != 2 || this.request_ == File.getDefaultInstance()) {
            this.request_ = file;
        } else {
            this.request_ = ((File.Builder) File.newBuilder((File) this.request_).mergeFrom(file)).buildPartial();
        }
        this.requestCase_ = 2;
    }

    /* access modifiers changed from: private */
    public void mergeMetadata(MetaData metaData) {
        metaData.getClass();
        if (this.requestCase_ != 1 || this.request_ == MetaData.getDefaultInstance()) {
            this.request_ = metaData;
        } else {
            this.request_ = ((MetaData.Builder) MetaData.newBuilder((MetaData) this.request_).mergeFrom(metaData)).buildPartial();
        }
        this.requestCase_ = 1;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static FileUploadRequest parseDelimitedFrom(InputStream inputStream) {
        return (FileUploadRequest) I.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static FileUploadRequest parseFrom(ByteBuffer byteBuffer) {
        return (FileUploadRequest) I.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static C0382u0 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* access modifiers changed from: private */
    public void setFile(File file) {
        file.getClass();
        this.request_ = file;
        this.requestCase_ = 2;
    }

    /* access modifiers changed from: private */
    public void setMetadata(MetaData metaData) {
        metaData.getClass();
        this.request_ = metaData;
        this.requestCase_ = 1;
    }

    public final Object dynamicMethod(H h5, Object obj, Object obj2) {
        switch (h5.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return I.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0001\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001<\u0000\u0002<\u0000", new Object[]{"request_", "requestCase_", MetaData.class, File.class});
            case 3:
                return new FileUploadRequest();
            case 4:
                return new Builder(0);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                C0382u0 u0Var = PARSER;
                if (u0Var == null) {
                    synchronized (FileUploadRequest.class) {
                        try {
                            u0Var = PARSER;
                            if (u0Var == null) {
                                u0Var = new E(DEFAULT_INSTANCE);
                                PARSER = u0Var;
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                }
                return u0Var;
            default:
                throw new UnsupportedOperationException();
        }
    }

    public File getFile() {
        return this.requestCase_ == 2 ? (File) this.request_ : File.getDefaultInstance();
    }

    public MetaData getMetadata() {
        return this.requestCase_ == 1 ? (MetaData) this.request_ : MetaData.getDefaultInstance();
    }

    public RequestCase getRequestCase() {
        return RequestCase.forNumber(this.requestCase_);
    }

    public boolean hasFile() {
        return this.requestCase_ == 2;
    }

    public boolean hasMetadata() {
        return this.requestCase_ == 1;
    }

    public static Builder newBuilder(FileUploadRequest fileUploadRequest) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(fileUploadRequest);
    }

    public static FileUploadRequest parseDelimitedFrom(InputStream inputStream, C0385x xVar) {
        return (FileUploadRequest) I.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, xVar);
    }

    public static FileUploadRequest parseFrom(ByteBuffer byteBuffer, C0385x xVar) {
        return (FileUploadRequest) I.parseFrom(DEFAULT_INSTANCE, byteBuffer, xVar);
    }

    public static FileUploadRequest parseFrom(C0366m mVar) {
        return (FileUploadRequest) I.parseFrom(DEFAULT_INSTANCE, mVar);
    }

    public static FileUploadRequest parseFrom(C0366m mVar, C0385x xVar) {
        return (FileUploadRequest) I.parseFrom(DEFAULT_INSTANCE, mVar, xVar);
    }

    public static FileUploadRequest parseFrom(byte[] bArr) {
        return (FileUploadRequest) I.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static FileUploadRequest parseFrom(byte[] bArr, C0385x xVar) {
        return (FileUploadRequest) I.parseFrom(DEFAULT_INSTANCE, bArr, xVar);
    }

    public static FileUploadRequest parseFrom(InputStream inputStream) {
        return (FileUploadRequest) I.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static FileUploadRequest parseFrom(InputStream inputStream, C0385x xVar) {
        return (FileUploadRequest) I.parseFrom(DEFAULT_INSTANCE, inputStream, xVar);
    }

    public static FileUploadRequest parseFrom(C0374q qVar) {
        return (FileUploadRequest) I.parseFrom(DEFAULT_INSTANCE, qVar);
    }

    public static FileUploadRequest parseFrom(C0374q qVar, C0385x xVar) {
        return (FileUploadRequest) I.parseFrom(DEFAULT_INSTANCE, qVar, xVar);
    }
}
