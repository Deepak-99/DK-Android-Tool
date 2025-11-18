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
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class BatchThumbUploadRequest extends I implements BatchThumbUploadRequestOrBuilder {
    /* access modifiers changed from: private */
    public static final BatchThumbUploadRequest DEFAULT_INSTANCE;
    private static volatile C0382u0 PARSER = null;
    public static final int THUMB_FIELD_NUMBER = 1;
    private Thumbnail thumb_;

    public static final class Builder extends D implements BatchThumbUploadRequestOrBuilder {
        public /* synthetic */ Builder(int i5) {
            this();
        }

        public Builder clearThumb() {
            copyOnWrite();
            ((BatchThumbUploadRequest) this.instance).clearThumb();
            return this;
        }

        public Thumbnail getThumb() {
            return ((BatchThumbUploadRequest) this.instance).getThumb();
        }

        public boolean hasThumb() {
            return ((BatchThumbUploadRequest) this.instance).hasThumb();
        }

        public Builder mergeThumb(Thumbnail thumbnail) {
            copyOnWrite();
            ((BatchThumbUploadRequest) this.instance).mergeThumb(thumbnail);
            return this;
        }

        public Builder setThumb(Thumbnail thumbnail) {
            copyOnWrite();
            ((BatchThumbUploadRequest) this.instance).setThumb(thumbnail);
            return this;
        }

        private Builder() {
            super(BatchThumbUploadRequest.DEFAULT_INSTANCE);
        }

        public Builder setThumb(Thumbnail.Builder builder) {
            copyOnWrite();
            ((BatchThumbUploadRequest) this.instance).setThumb((Thumbnail) builder.build());
            return this;
        }
    }

    public static final class Thumbnail extends I implements ThumbnailOrBuilder {
        public static final int CONTENT_FIELD_NUMBER = 3;
        /* access modifiers changed from: private */
        public static final Thumbnail DEFAULT_INSTANCE;
        public static final int NAME_FIELD_NUMBER = 1;
        private static volatile C0382u0 PARSER = null;
        public static final int PATH_FIELD_NUMBER = 2;
        private C0366m content_ = C0366m.f4803E;
        private String name_ = "";
        private String path_ = "";

        public static final class Builder extends D implements ThumbnailOrBuilder {
            public /* synthetic */ Builder(int i5) {
                this();
            }

            public Builder clearContent() {
                copyOnWrite();
                ((Thumbnail) this.instance).clearContent();
                return this;
            }

            public Builder clearName() {
                copyOnWrite();
                ((Thumbnail) this.instance).clearName();
                return this;
            }

            public Builder clearPath() {
                copyOnWrite();
                ((Thumbnail) this.instance).clearPath();
                return this;
            }

            public C0366m getContent() {
                return ((Thumbnail) this.instance).getContent();
            }

            public String getName() {
                return ((Thumbnail) this.instance).getName();
            }

            public C0366m getNameBytes() {
                return ((Thumbnail) this.instance).getNameBytes();
            }

            public String getPath() {
                return ((Thumbnail) this.instance).getPath();
            }

            public C0366m getPathBytes() {
                return ((Thumbnail) this.instance).getPathBytes();
            }

            public Builder setContent(C0366m mVar) {
                copyOnWrite();
                ((Thumbnail) this.instance).setContent(mVar);
                return this;
            }

            public Builder setName(String str) {
                copyOnWrite();
                ((Thumbnail) this.instance).setName(str);
                return this;
            }

            public Builder setNameBytes(C0366m mVar) {
                copyOnWrite();
                ((Thumbnail) this.instance).setNameBytes(mVar);
                return this;
            }

            public Builder setPath(String str) {
                copyOnWrite();
                ((Thumbnail) this.instance).setPath(str);
                return this;
            }

            public Builder setPathBytes(C0366m mVar) {
                copyOnWrite();
                ((Thumbnail) this.instance).setPathBytes(mVar);
                return this;
            }

            private Builder() {
                super(Thumbnail.DEFAULT_INSTANCE);
            }
        }

        static {
            Thumbnail thumbnail = new Thumbnail();
            DEFAULT_INSTANCE = thumbnail;
            I.registerDefaultInstance(Thumbnail.class, thumbnail);
        }

        private Thumbnail() {
        }

        /* access modifiers changed from: private */
        public void clearContent() {
            this.content_ = getDefaultInstance().getContent();
        }

        /* access modifiers changed from: private */
        public void clearName() {
            this.name_ = getDefaultInstance().getName();
        }

        /* access modifiers changed from: private */
        public void clearPath() {
            this.path_ = getDefaultInstance().getPath();
        }

        public static Thumbnail getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Thumbnail parseDelimitedFrom(InputStream inputStream) {
            return (Thumbnail) I.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Thumbnail parseFrom(ByteBuffer byteBuffer) {
            return (Thumbnail) I.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static C0382u0 parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* access modifiers changed from: private */
        public void setContent(C0366m mVar) {
            mVar.getClass();
            this.content_ = mVar;
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

        public final Object dynamicMethod(H h5, Object obj, Object obj2) {
            switch (h5.ordinal()) {
                case 0:
                    return (byte) 1;
                case 1:
                    return null;
                case 2:
                    return I.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003\n", new Object[]{"name_", "path_", "content_"});
                case 3:
                    return new Thumbnail();
                case 4:
                    return new Builder(0);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    C0382u0 u0Var = PARSER;
                    if (u0Var == null) {
                        synchronized (Thumbnail.class) {
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

        public static Builder newBuilder(Thumbnail thumbnail) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(thumbnail);
        }

        public static Thumbnail parseDelimitedFrom(InputStream inputStream, C0385x xVar) {
            return (Thumbnail) I.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, xVar);
        }

        public static Thumbnail parseFrom(ByteBuffer byteBuffer, C0385x xVar) {
            return (Thumbnail) I.parseFrom(DEFAULT_INSTANCE, byteBuffer, xVar);
        }

        public static Thumbnail parseFrom(C0366m mVar) {
            return (Thumbnail) I.parseFrom(DEFAULT_INSTANCE, mVar);
        }

        public static Thumbnail parseFrom(C0366m mVar, C0385x xVar) {
            return (Thumbnail) I.parseFrom(DEFAULT_INSTANCE, mVar, xVar);
        }

        public static Thumbnail parseFrom(byte[] bArr) {
            return (Thumbnail) I.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Thumbnail parseFrom(byte[] bArr, C0385x xVar) {
            return (Thumbnail) I.parseFrom(DEFAULT_INSTANCE, bArr, xVar);
        }

        public static Thumbnail parseFrom(InputStream inputStream) {
            return (Thumbnail) I.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Thumbnail parseFrom(InputStream inputStream, C0385x xVar) {
            return (Thumbnail) I.parseFrom(DEFAULT_INSTANCE, inputStream, xVar);
        }

        public static Thumbnail parseFrom(C0374q qVar) {
            return (Thumbnail) I.parseFrom(DEFAULT_INSTANCE, qVar);
        }

        public static Thumbnail parseFrom(C0374q qVar, C0385x xVar) {
            return (Thumbnail) I.parseFrom(DEFAULT_INSTANCE, qVar, xVar);
        }
    }

    public interface ThumbnailOrBuilder extends C0371o0 {
        C0366m getContent();

        /* synthetic */ C0369n0 getDefaultInstanceForType();

        String getName();

        C0366m getNameBytes();

        String getPath();

        C0366m getPathBytes();

        /* synthetic */ boolean isInitialized();
    }

    static {
        BatchThumbUploadRequest batchThumbUploadRequest = new BatchThumbUploadRequest();
        DEFAULT_INSTANCE = batchThumbUploadRequest;
        I.registerDefaultInstance(BatchThumbUploadRequest.class, batchThumbUploadRequest);
    }

    private BatchThumbUploadRequest() {
    }

    /* access modifiers changed from: private */
    public void clearThumb() {
        this.thumb_ = null;
    }

    public static BatchThumbUploadRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /* access modifiers changed from: private */
    public void mergeThumb(Thumbnail thumbnail) {
        thumbnail.getClass();
        Thumbnail thumbnail2 = this.thumb_;
        if (thumbnail2 == null || thumbnail2 == Thumbnail.getDefaultInstance()) {
            this.thumb_ = thumbnail;
        } else {
            this.thumb_ = (Thumbnail) ((Thumbnail.Builder) Thumbnail.newBuilder(this.thumb_).mergeFrom(thumbnail)).buildPartial();
        }
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static BatchThumbUploadRequest parseDelimitedFrom(InputStream inputStream) {
        return (BatchThumbUploadRequest) I.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static BatchThumbUploadRequest parseFrom(ByteBuffer byteBuffer) {
        return (BatchThumbUploadRequest) I.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static C0382u0 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* access modifiers changed from: private */
    public void setThumb(Thumbnail thumbnail) {
        thumbnail.getClass();
        this.thumb_ = thumbnail;
    }

    public final Object dynamicMethod(H h5, Object obj, Object obj2) {
        switch (h5.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return I.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\t", new Object[]{"thumb_"});
            case 3:
                return new BatchThumbUploadRequest();
            case 4:
                return new Builder(0);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                C0382u0 u0Var = PARSER;
                if (u0Var == null) {
                    synchronized (BatchThumbUploadRequest.class) {
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

    public Thumbnail getThumb() {
        Thumbnail thumbnail = this.thumb_;
        return thumbnail == null ? Thumbnail.getDefaultInstance() : thumbnail;
    }

    public boolean hasThumb() {
        return this.thumb_ != null;
    }

    public static Builder newBuilder(BatchThumbUploadRequest batchThumbUploadRequest) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(batchThumbUploadRequest);
    }

    public static BatchThumbUploadRequest parseDelimitedFrom(InputStream inputStream, C0385x xVar) {
        return (BatchThumbUploadRequest) I.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, xVar);
    }

    public static BatchThumbUploadRequest parseFrom(ByteBuffer byteBuffer, C0385x xVar) {
        return (BatchThumbUploadRequest) I.parseFrom(DEFAULT_INSTANCE, byteBuffer, xVar);
    }

    public static BatchThumbUploadRequest parseFrom(C0366m mVar) {
        return (BatchThumbUploadRequest) I.parseFrom(DEFAULT_INSTANCE, mVar);
    }

    public static BatchThumbUploadRequest parseFrom(C0366m mVar, C0385x xVar) {
        return (BatchThumbUploadRequest) I.parseFrom(DEFAULT_INSTANCE, mVar, xVar);
    }

    public static BatchThumbUploadRequest parseFrom(byte[] bArr) {
        return (BatchThumbUploadRequest) I.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static BatchThumbUploadRequest parseFrom(byte[] bArr, C0385x xVar) {
        return (BatchThumbUploadRequest) I.parseFrom(DEFAULT_INSTANCE, bArr, xVar);
    }

    public static BatchThumbUploadRequest parseFrom(InputStream inputStream) {
        return (BatchThumbUploadRequest) I.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static BatchThumbUploadRequest parseFrom(InputStream inputStream, C0385x xVar) {
        return (BatchThumbUploadRequest) I.parseFrom(DEFAULT_INSTANCE, inputStream, xVar);
    }

    public static BatchThumbUploadRequest parseFrom(C0374q qVar) {
        return (BatchThumbUploadRequest) I.parseFrom(DEFAULT_INSTANCE, qVar);
    }

    public static BatchThumbUploadRequest parseFrom(C0374q qVar, C0385x xVar) {
        return (BatchThumbUploadRequest) I.parseFrom(DEFAULT_INSTANCE, qVar, xVar);
    }
}
