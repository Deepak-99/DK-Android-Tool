package com.hawkshaw.library;

import com.google.protobuf.C0346c;
import com.google.protobuf.C0366m;
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

public final class FileUploadResponse extends I implements FileUploadResponseOrBuilder {
    /* access modifiers changed from: private */
    public static final FileUploadResponse DEFAULT_INSTANCE;
    public static final int NAME_FIELD_NUMBER = 1;
    private static volatile C0382u0 PARSER = null;
    public static final int PUBLIC_URL_FIELD_NUMBER = 2;
    public static final int STATUS_FIELD_NUMBER = 3;
    private String name_ = "";
    private String publicUrl_ = "";
    private int status_;

    public static final class Builder extends D implements FileUploadResponseOrBuilder {
        public /* synthetic */ Builder(int i5) {
            this();
        }

        public Builder clearName() {
            copyOnWrite();
            ((FileUploadResponse) this.instance).clearName();
            return this;
        }

        public Builder clearPublicUrl() {
            copyOnWrite();
            ((FileUploadResponse) this.instance).clearPublicUrl();
            return this;
        }

        public Builder clearStatus() {
            copyOnWrite();
            ((FileUploadResponse) this.instance).clearStatus();
            return this;
        }

        public String getName() {
            return ((FileUploadResponse) this.instance).getName();
        }

        public C0366m getNameBytes() {
            return ((FileUploadResponse) this.instance).getNameBytes();
        }

        public String getPublicUrl() {
            return ((FileUploadResponse) this.instance).getPublicUrl();
        }

        public C0366m getPublicUrlBytes() {
            return ((FileUploadResponse) this.instance).getPublicUrlBytes();
        }

        public Status getStatus() {
            return ((FileUploadResponse) this.instance).getStatus();
        }

        public int getStatusValue() {
            return ((FileUploadResponse) this.instance).getStatusValue();
        }

        public Builder setName(String str) {
            copyOnWrite();
            ((FileUploadResponse) this.instance).setName(str);
            return this;
        }

        public Builder setNameBytes(C0366m mVar) {
            copyOnWrite();
            ((FileUploadResponse) this.instance).setNameBytes(mVar);
            return this;
        }

        public Builder setPublicUrl(String str) {
            copyOnWrite();
            ((FileUploadResponse) this.instance).setPublicUrl(str);
            return this;
        }

        public Builder setPublicUrlBytes(C0366m mVar) {
            copyOnWrite();
            ((FileUploadResponse) this.instance).setPublicUrlBytes(mVar);
            return this;
        }

        public Builder setStatus(Status status) {
            copyOnWrite();
            ((FileUploadResponse) this.instance).setStatus(status);
            return this;
        }

        public Builder setStatusValue(int i5) {
            copyOnWrite();
            ((FileUploadResponse) this.instance).setStatusValue(i5);
            return this;
        }

        private Builder() {
            super(FileUploadResponse.DEFAULT_INSTANCE);
        }
    }

    public enum Status implements M {
        PENDING(0),
        IN_PROGRESS(1),
        SUCCESS(2),
        FAILED(3),
        UNRECOGNIZED(-1);
        
        public static final int FAILED_VALUE = 3;
        public static final int IN_PROGRESS_VALUE = 1;
        public static final int PENDING_VALUE = 0;
        public static final int SUCCESS_VALUE = 2;
        private static final N internalValueMap = null;
        private final int value;

        /* JADX WARNING: type inference failed for: r0v6, types: [com.google.protobuf.N, java.lang.Object] */
        static {
            internalValueMap = new Object();
        }

        private Status(int i5) {
            this.value = i5;
        }

        public static Status forNumber(int i5) {
            if (i5 == 0) {
                return PENDING;
            }
            if (i5 == 1) {
                return IN_PROGRESS;
            }
            if (i5 == 2) {
                return SUCCESS;
            }
            if (i5 != 3) {
                return null;
            }
            return FAILED;
        }

        public static N internalGetValueMap() {
            return internalValueMap;
        }

        public static O internalGetVerifier() {
            return j.f5045a;
        }

        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }

        @Deprecated
        public static Status valueOf(int i5) {
            return forNumber(i5);
        }
    }

    static {
        FileUploadResponse fileUploadResponse = new FileUploadResponse();
        DEFAULT_INSTANCE = fileUploadResponse;
        I.registerDefaultInstance(FileUploadResponse.class, fileUploadResponse);
    }

    private FileUploadResponse() {
    }

    /* access modifiers changed from: private */
    public void clearName() {
        this.name_ = getDefaultInstance().getName();
    }

    /* access modifiers changed from: private */
    public void clearPublicUrl() {
        this.publicUrl_ = getDefaultInstance().getPublicUrl();
    }

    /* access modifiers changed from: private */
    public void clearStatus() {
        this.status_ = 0;
    }

    public static FileUploadResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static FileUploadResponse parseDelimitedFrom(InputStream inputStream) {
        return (FileUploadResponse) I.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static FileUploadResponse parseFrom(ByteBuffer byteBuffer) {
        return (FileUploadResponse) I.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static C0382u0 parser() {
        return DEFAULT_INSTANCE.getParserForType();
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
    public void setPublicUrl(String str) {
        str.getClass();
        this.publicUrl_ = str;
    }

    /* access modifiers changed from: private */
    public void setPublicUrlBytes(C0366m mVar) {
        C0346c.checkByteStringIsUtf8(mVar);
        this.publicUrl_ = mVar.r();
    }

    /* access modifiers changed from: private */
    public void setStatus(Status status) {
        this.status_ = status.getNumber();
    }

    /* access modifiers changed from: private */
    public void setStatusValue(int i5) {
        this.status_ = i5;
    }

    public final Object dynamicMethod(H h5, Object obj, Object obj2) {
        switch (h5.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return I.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003\f", new Object[]{"name_", "publicUrl_", "status_"});
            case 3:
                return new FileUploadResponse();
            case 4:
                return new Builder(0);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                C0382u0 u0Var = PARSER;
                if (u0Var == null) {
                    synchronized (FileUploadResponse.class) {
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

    public String getName() {
        return this.name_;
    }

    public C0366m getNameBytes() {
        return C0366m.k(this.name_);
    }

    public String getPublicUrl() {
        return this.publicUrl_;
    }

    public C0366m getPublicUrlBytes() {
        return C0366m.k(this.publicUrl_);
    }

    public Status getStatus() {
        Status forNumber = Status.forNumber(this.status_);
        return forNumber == null ? Status.UNRECOGNIZED : forNumber;
    }

    public int getStatusValue() {
        return this.status_;
    }

    public static Builder newBuilder(FileUploadResponse fileUploadResponse) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(fileUploadResponse);
    }

    public static FileUploadResponse parseDelimitedFrom(InputStream inputStream, C0385x xVar) {
        return (FileUploadResponse) I.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, xVar);
    }

    public static FileUploadResponse parseFrom(ByteBuffer byteBuffer, C0385x xVar) {
        return (FileUploadResponse) I.parseFrom(DEFAULT_INSTANCE, byteBuffer, xVar);
    }

    public static FileUploadResponse parseFrom(C0366m mVar) {
        return (FileUploadResponse) I.parseFrom(DEFAULT_INSTANCE, mVar);
    }

    public static FileUploadResponse parseFrom(C0366m mVar, C0385x xVar) {
        return (FileUploadResponse) I.parseFrom(DEFAULT_INSTANCE, mVar, xVar);
    }

    public static FileUploadResponse parseFrom(byte[] bArr) {
        return (FileUploadResponse) I.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static FileUploadResponse parseFrom(byte[] bArr, C0385x xVar) {
        return (FileUploadResponse) I.parseFrom(DEFAULT_INSTANCE, bArr, xVar);
    }

    public static FileUploadResponse parseFrom(InputStream inputStream) {
        return (FileUploadResponse) I.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static FileUploadResponse parseFrom(InputStream inputStream, C0385x xVar) {
        return (FileUploadResponse) I.parseFrom(DEFAULT_INSTANCE, inputStream, xVar);
    }

    public static FileUploadResponse parseFrom(C0374q qVar) {
        return (FileUploadResponse) I.parseFrom(DEFAULT_INSTANCE, qVar);
    }

    public static FileUploadResponse parseFrom(C0374q qVar, C0385x xVar) {
        return (FileUploadResponse) I.parseFrom(DEFAULT_INSTANCE, qVar, xVar);
    }
}
