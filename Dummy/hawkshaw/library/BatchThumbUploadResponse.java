package com.hawkshaw.library;

import com.google.protobuf.C0346c;
import com.google.protobuf.C0348d;
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
import com.google.protobuf.T;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

public final class BatchThumbUploadResponse extends I implements BatchThumbUploadResponseOrBuilder {
    /* access modifiers changed from: private */
    public static final BatchThumbUploadResponse DEFAULT_INSTANCE;
    private static volatile C0382u0 PARSER = null;
    public static final int SUCCESS_FIELD_NUMBER = 1;
    private T success_ = I.emptyProtobufList();

    public static final class Builder extends D implements BatchThumbUploadResponseOrBuilder {
        public /* synthetic */ Builder(int i5) {
            this();
        }

        public Builder addAllSuccess(Iterable<? extends Thumb> iterable) {
            copyOnWrite();
            ((BatchThumbUploadResponse) this.instance).addAllSuccess(iterable);
            return this;
        }

        public Builder addSuccess(Thumb thumb) {
            copyOnWrite();
            ((BatchThumbUploadResponse) this.instance).addSuccess(thumb);
            return this;
        }

        public Builder clearSuccess() {
            copyOnWrite();
            ((BatchThumbUploadResponse) this.instance).clearSuccess();
            return this;
        }

        public Thumb getSuccess(int i5) {
            return ((BatchThumbUploadResponse) this.instance).getSuccess(i5);
        }

        public int getSuccessCount() {
            return ((BatchThumbUploadResponse) this.instance).getSuccessCount();
        }

        public List<Thumb> getSuccessList() {
            return Collections.unmodifiableList(((BatchThumbUploadResponse) this.instance).getSuccessList());
        }

        public Builder removeSuccess(int i5) {
            copyOnWrite();
            ((BatchThumbUploadResponse) this.instance).removeSuccess(i5);
            return this;
        }

        public Builder setSuccess(int i5, Thumb thumb) {
            copyOnWrite();
            ((BatchThumbUploadResponse) this.instance).setSuccess(i5, thumb);
            return this;
        }

        private Builder() {
            super(BatchThumbUploadResponse.DEFAULT_INSTANCE);
        }

        public Builder addSuccess(int i5, Thumb thumb) {
            copyOnWrite();
            ((BatchThumbUploadResponse) this.instance).addSuccess(i5, thumb);
            return this;
        }

        public Builder setSuccess(int i5, Thumb.Builder builder) {
            copyOnWrite();
            ((BatchThumbUploadResponse) this.instance).setSuccess(i5, (Thumb) builder.build());
            return this;
        }

        public Builder addSuccess(Thumb.Builder builder) {
            copyOnWrite();
            ((BatchThumbUploadResponse) this.instance).addSuccess((Thumb) builder.build());
            return this;
        }

        public Builder addSuccess(int i5, Thumb.Builder builder) {
            copyOnWrite();
            ((BatchThumbUploadResponse) this.instance).addSuccess(i5, (Thumb) builder.build());
            return this;
        }
    }

    public static final class Thumb extends I implements ThumbOrBuilder {
        /* access modifiers changed from: private */
        public static final Thumb DEFAULT_INSTANCE;
        private static volatile C0382u0 PARSER = null;
        public static final int PATH_FIELD_NUMBER = 1;
        private String path_ = "";

        public static final class Builder extends D implements ThumbOrBuilder {
            public /* synthetic */ Builder(int i5) {
                this();
            }

            public Builder clearPath() {
                copyOnWrite();
                ((Thumb) this.instance).clearPath();
                return this;
            }

            public String getPath() {
                return ((Thumb) this.instance).getPath();
            }

            public C0366m getPathBytes() {
                return ((Thumb) this.instance).getPathBytes();
            }

            public Builder setPath(String str) {
                copyOnWrite();
                ((Thumb) this.instance).setPath(str);
                return this;
            }

            public Builder setPathBytes(C0366m mVar) {
                copyOnWrite();
                ((Thumb) this.instance).setPathBytes(mVar);
                return this;
            }

            private Builder() {
                super(Thumb.DEFAULT_INSTANCE);
            }
        }

        static {
            Thumb thumb = new Thumb();
            DEFAULT_INSTANCE = thumb;
            I.registerDefaultInstance(Thumb.class, thumb);
        }

        private Thumb() {
        }

        /* access modifiers changed from: private */
        public void clearPath() {
            this.path_ = getDefaultInstance().getPath();
        }

        public static Thumb getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Thumb parseDelimitedFrom(InputStream inputStream) {
            return (Thumb) I.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Thumb parseFrom(ByteBuffer byteBuffer) {
            return (Thumb) I.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static C0382u0 parser() {
            return DEFAULT_INSTANCE.getParserForType();
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
                    return I.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001Ȉ", new Object[]{"path_"});
                case 3:
                    return new Thumb();
                case 4:
                    return new Builder(0);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    C0382u0 u0Var = PARSER;
                    if (u0Var == null) {
                        synchronized (Thumb.class) {
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

        public String getPath() {
            return this.path_;
        }

        public C0366m getPathBytes() {
            return C0366m.k(this.path_);
        }

        public static Builder newBuilder(Thumb thumb) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(thumb);
        }

        public static Thumb parseDelimitedFrom(InputStream inputStream, C0385x xVar) {
            return (Thumb) I.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, xVar);
        }

        public static Thumb parseFrom(ByteBuffer byteBuffer, C0385x xVar) {
            return (Thumb) I.parseFrom(DEFAULT_INSTANCE, byteBuffer, xVar);
        }

        public static Thumb parseFrom(C0366m mVar) {
            return (Thumb) I.parseFrom(DEFAULT_INSTANCE, mVar);
        }

        public static Thumb parseFrom(C0366m mVar, C0385x xVar) {
            return (Thumb) I.parseFrom(DEFAULT_INSTANCE, mVar, xVar);
        }

        public static Thumb parseFrom(byte[] bArr) {
            return (Thumb) I.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Thumb parseFrom(byte[] bArr, C0385x xVar) {
            return (Thumb) I.parseFrom(DEFAULT_INSTANCE, bArr, xVar);
        }

        public static Thumb parseFrom(InputStream inputStream) {
            return (Thumb) I.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Thumb parseFrom(InputStream inputStream, C0385x xVar) {
            return (Thumb) I.parseFrom(DEFAULT_INSTANCE, inputStream, xVar);
        }

        public static Thumb parseFrom(C0374q qVar) {
            return (Thumb) I.parseFrom(DEFAULT_INSTANCE, qVar);
        }

        public static Thumb parseFrom(C0374q qVar, C0385x xVar) {
            return (Thumb) I.parseFrom(DEFAULT_INSTANCE, qVar, xVar);
        }
    }

    public interface ThumbOrBuilder extends C0371o0 {
        /* synthetic */ C0369n0 getDefaultInstanceForType();

        String getPath();

        C0366m getPathBytes();

        /* synthetic */ boolean isInitialized();
    }

    static {
        BatchThumbUploadResponse batchThumbUploadResponse = new BatchThumbUploadResponse();
        DEFAULT_INSTANCE = batchThumbUploadResponse;
        I.registerDefaultInstance(BatchThumbUploadResponse.class, batchThumbUploadResponse);
    }

    private BatchThumbUploadResponse() {
    }

    /* access modifiers changed from: private */
    public void addAllSuccess(Iterable<? extends Thumb> iterable) {
        ensureSuccessIsMutable();
        C0346c.addAll(iterable, this.success_);
    }

    /* access modifiers changed from: private */
    public void addSuccess(Thumb thumb) {
        thumb.getClass();
        ensureSuccessIsMutable();
        this.success_.add(thumb);
    }

    /* access modifiers changed from: private */
    public void clearSuccess() {
        this.success_ = I.emptyProtobufList();
    }

    private void ensureSuccessIsMutable() {
        T t4 = this.success_;
        if (!((C0348d) t4).f4778D) {
            this.success_ = I.mutableCopy(t4);
        }
    }

    public static BatchThumbUploadResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static BatchThumbUploadResponse parseDelimitedFrom(InputStream inputStream) {
        return (BatchThumbUploadResponse) I.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static BatchThumbUploadResponse parseFrom(ByteBuffer byteBuffer) {
        return (BatchThumbUploadResponse) I.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static C0382u0 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* access modifiers changed from: private */
    public void removeSuccess(int i5) {
        ensureSuccessIsMutable();
        this.success_.remove(i5);
    }

    /* access modifiers changed from: private */
    public void setSuccess(int i5, Thumb thumb) {
        thumb.getClass();
        ensureSuccessIsMutable();
        this.success_.set(i5, thumb);
    }

    public final Object dynamicMethod(H h5, Object obj, Object obj2) {
        switch (h5.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return I.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"success_", Thumb.class});
            case 3:
                return new BatchThumbUploadResponse();
            case 4:
                return new Builder(0);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                C0382u0 u0Var = PARSER;
                if (u0Var == null) {
                    synchronized (BatchThumbUploadResponse.class) {
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

    public Thumb getSuccess(int i5) {
        return (Thumb) this.success_.get(i5);
    }

    public int getSuccessCount() {
        return this.success_.size();
    }

    public List<Thumb> getSuccessList() {
        return this.success_;
    }

    public ThumbOrBuilder getSuccessOrBuilder(int i5) {
        return (ThumbOrBuilder) this.success_.get(i5);
    }

    public List<? extends ThumbOrBuilder> getSuccessOrBuilderList() {
        return this.success_;
    }

    public static Builder newBuilder(BatchThumbUploadResponse batchThumbUploadResponse) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(batchThumbUploadResponse);
    }

    public static BatchThumbUploadResponse parseDelimitedFrom(InputStream inputStream, C0385x xVar) {
        return (BatchThumbUploadResponse) I.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, xVar);
    }

    public static BatchThumbUploadResponse parseFrom(ByteBuffer byteBuffer, C0385x xVar) {
        return (BatchThumbUploadResponse) I.parseFrom(DEFAULT_INSTANCE, byteBuffer, xVar);
    }

    public static BatchThumbUploadResponse parseFrom(C0366m mVar) {
        return (BatchThumbUploadResponse) I.parseFrom(DEFAULT_INSTANCE, mVar);
    }

    /* access modifiers changed from: private */
    public void addSuccess(int i5, Thumb thumb) {
        thumb.getClass();
        ensureSuccessIsMutable();
        this.success_.add(i5, thumb);
    }

    public static BatchThumbUploadResponse parseFrom(C0366m mVar, C0385x xVar) {
        return (BatchThumbUploadResponse) I.parseFrom(DEFAULT_INSTANCE, mVar, xVar);
    }

    public static BatchThumbUploadResponse parseFrom(byte[] bArr) {
        return (BatchThumbUploadResponse) I.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static BatchThumbUploadResponse parseFrom(byte[] bArr, C0385x xVar) {
        return (BatchThumbUploadResponse) I.parseFrom(DEFAULT_INSTANCE, bArr, xVar);
    }

    public static BatchThumbUploadResponse parseFrom(InputStream inputStream) {
        return (BatchThumbUploadResponse) I.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static BatchThumbUploadResponse parseFrom(InputStream inputStream, C0385x xVar) {
        return (BatchThumbUploadResponse) I.parseFrom(DEFAULT_INSTANCE, inputStream, xVar);
    }

    public static BatchThumbUploadResponse parseFrom(C0374q qVar) {
        return (BatchThumbUploadResponse) I.parseFrom(DEFAULT_INSTANCE, qVar);
    }

    public static BatchThumbUploadResponse parseFrom(C0374q qVar, C0385x xVar) {
        return (BatchThumbUploadResponse) I.parseFrom(DEFAULT_INSTANCE, qVar, xVar);
    }
}
