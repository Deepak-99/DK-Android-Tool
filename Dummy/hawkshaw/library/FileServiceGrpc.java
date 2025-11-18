package com.hawkshaw.library;

import X1.B;
import Y1.A0;
import Y1.C0104e;
import Y1.C0106f;
import Y1.C0108g;
import Y1.C0114j;
import Y1.o0;
import Y1.p0;
import Y1.y0;
import Y1.z0;
import com.google.protobuf.C0385x;
import g2.C0452b;
import g2.C0453c;
import i.s0;
import io.grpc.stub.a;
import io.grpc.stub.b;
import io.grpc.stub.c;
import io.grpc.stub.f;
import io.grpc.stub.g;
import io.grpc.stub.i;
import io.grpc.stub.j;
import java.util.List;
import p3.q;

public final class FileServiceGrpc {
    private static final int METHODID_BATCH_THUMB_UPLOAD = 1;
    private static final int METHODID_UPLOAD_FILE = 0;
    public static final String SERVICE_NAME = "filecrud.FileService";
    private static volatile p0 getBatchThumbUploadMethod;
    private static volatile p0 getUploadFileMethod;
    private static volatile A0 serviceDescriptor;

    public static final class FileServiceBlockingStub extends b {
        public /* synthetic */ FileServiceBlockingStub(C0108g gVar, C0106f fVar, int i5) {
            this(gVar, fVar);
        }

        private FileServiceBlockingStub(C0108g gVar, C0106f fVar) {
            super(gVar, fVar);
        }

        public FileServiceBlockingStub build(C0108g gVar, C0106f fVar) {
            return new FileServiceBlockingStub(gVar, fVar);
        }
    }

    public static final class FileServiceFutureStub extends c {
        public /* synthetic */ FileServiceFutureStub(C0108g gVar, C0106f fVar, int i5) {
            this(gVar, fVar);
        }

        private FileServiceFutureStub(C0108g gVar, C0106f fVar) {
            super(gVar, fVar);
        }

        public FileServiceFutureStub build(C0108g gVar, C0106f fVar) {
            return new FileServiceFutureStub(gVar, fVar);
        }
    }

    public static abstract class FileServiceImplBase {
        public j batchThumbUpload(j jVar) {
            return B.f(FileServiceGrpc.getBatchThumbUploadMethod(), jVar);
        }

        public final z0 bindService() {
            s0 s0Var = new s0(FileServiceGrpc.getServiceDescriptor(), 0);
            p0 uploadFileMethod = FileServiceGrpc.getUploadFileMethod();
            q.J(uploadFileMethod, "method must not be null");
            s0Var.b(new y0(uploadFileMethod));
            p0 batchThumbUploadMethod = FileServiceGrpc.getBatchThumbUploadMethod();
            q.J(batchThumbUploadMethod, "method must not be null");
            s0Var.b(new y0(batchThumbUploadMethod));
            return s0Var.c();
        }

        public j uploadFile(j jVar) {
            return B.f(FileServiceGrpc.getUploadFileMethod(), jVar);
        }
    }

    public static final class FileServiceStub extends a {
        public /* synthetic */ FileServiceStub(C0108g gVar, C0106f fVar, int i5) {
            this(gVar, fVar);
        }

        /* JADX WARNING: type inference failed for: r4v1, types: [Y1.m0, java.lang.Object] */
        public j batchThumbUpload(j jVar) {
            C0114j h5 = getChannel().h(FileServiceGrpc.getBatchThumbUploadMethod(), getCallOptions());
            C0104e eVar = i.f6119a;
            f fVar = new f(h5);
            h5.start(new g(jVar, fVar), new Object());
            h5.request(2);
            return fVar;
        }

        /* JADX WARNING: type inference failed for: r4v1, types: [Y1.m0, java.lang.Object] */
        public j uploadFile(j jVar) {
            C0114j h5 = getChannel().h(FileServiceGrpc.getUploadFileMethod(), getCallOptions());
            C0104e eVar = i.f6119a;
            f fVar = new f(h5);
            h5.start(new g(jVar, fVar), new Object());
            h5.request(2);
            return fVar;
        }

        private FileServiceStub(C0108g gVar, C0106f fVar) {
            super(gVar, fVar);
        }

        public FileServiceStub build(C0108g gVar, C0106f fVar) {
            return new FileServiceStub(gVar, fVar);
        }
    }

    private FileServiceGrpc() {
    }

    public static p0 getBatchThumbUploadMethod() {
        p0 p0Var = getBatchThumbUploadMethod;
        if (p0Var == null) {
            synchronized (FileServiceGrpc.class) {
                try {
                    p0Var = getBatchThumbUploadMethod;
                    if (p0Var == null) {
                        o0 o0Var = o0.f2794E;
                        String a5 = p0.a("filecrud.FileService", "BatchThumbUpload");
                        BatchThumbUploadRequest defaultInstance = BatchThumbUploadRequest.getDefaultInstance();
                        C0385x xVar = C0453c.f5487a;
                        p0Var = new p0(o0Var, a5, new C0452b(defaultInstance), new C0452b(BatchThumbUploadResponse.getDefaultInstance()), true);
                        getBatchThumbUploadMethod = p0Var;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return p0Var;
    }

    public static A0 getServiceDescriptor() {
        A0 a02 = serviceDescriptor;
        if (a02 == null) {
            synchronized (FileServiceGrpc.class) {
                try {
                    a02 = serviceDescriptor;
                    if (a02 == null) {
                        s0 s0Var = new s0("filecrud.FileService", 0);
                        p0 uploadFileMethod = getUploadFileMethod();
                        q.J(uploadFileMethod, "method");
                        ((List) s0Var.f5964F).add(uploadFileMethod);
                        p0 batchThumbUploadMethod = getBatchThumbUploadMethod();
                        q.J(batchThumbUploadMethod, "method");
                        ((List) s0Var.f5964F).add(batchThumbUploadMethod);
                        a02 = new A0(s0Var);
                        serviceDescriptor = a02;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return a02;
    }

    public static p0 getUploadFileMethod() {
        p0 p0Var = getUploadFileMethod;
        if (p0Var == null) {
            synchronized (FileServiceGrpc.class) {
                try {
                    p0Var = getUploadFileMethod;
                    if (p0Var == null) {
                        o0 o0Var = o0.f2794E;
                        String a5 = p0.a("filecrud.FileService", "UploadFile");
                        FileUploadRequest defaultInstance = FileUploadRequest.getDefaultInstance();
                        C0385x xVar = C0453c.f5487a;
                        p0Var = new p0(o0Var, a5, new C0452b(defaultInstance), new C0452b(FileUploadResponse.getDefaultInstance()), true);
                        getUploadFileMethod = p0Var;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return p0Var;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [io.grpc.stub.d, java.lang.Object] */
    public static FileServiceBlockingStub newBlockingStub(C0108g gVar) {
        return (FileServiceBlockingStub) b.newStub(new Object(), gVar);
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [io.grpc.stub.d, java.lang.Object] */
    public static FileServiceFutureStub newFutureStub(C0108g gVar) {
        return (FileServiceFutureStub) c.newStub(new Object(), gVar);
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [io.grpc.stub.d, java.lang.Object] */
    public static FileServiceStub newStub(C0108g gVar) {
        return (FileServiceStub) a.newStub(new Object(), gVar);
    }
}
