package com.hawkshaw.library;

import Y1.A0;
import Y1.C0106f;
import Y1.C0108g;
import Y1.F0;
import Y1.G0;
import Y1.K;
import Y1.o0;
import Y1.p0;
import Y1.y0;
import Y1.z0;
import a3.e;
import a3.j;
import a3.k;
import b2.C0293a;
import b2.C0294b;
import i.s0;
import j3.f;
import w3.C1066b;

public final class FileServiceGrpcKt {
    public static final FileServiceGrpcKt INSTANCE = new FileServiceGrpcKt();
    public static final String SERVICE_NAME = "filecrud.FileService";

    public static abstract class FileServiceCoroutineImplBase extends C0293a {
        public FileServiceCoroutineImplBase() {
            this((j) null, 1, (f) null);
        }

        public static Object batchThumbUpload$suspendImpl(FileServiceCoroutineImplBase fileServiceCoroutineImplBase, C1066b bVar, e eVar) {
            throw new G0(F0.f2690l.h("Method filecrud.FileService.BatchThumbUpload is unimplemented"));
        }

        public static Object uploadFile$suspendImpl(FileServiceCoroutineImplBase fileServiceCoroutineImplBase, C1066b bVar, e eVar) {
            throw new G0(F0.f2690l.h("Method filecrud.FileService.UploadFile is unimplemented"));
        }

        public Object batchThumbUpload(C1066b bVar, e eVar) {
            return batchThumbUpload$suspendImpl(this, bVar, eVar);
        }

        public final z0 bindService() {
            s0 s0Var = new s0(FileServiceGrpc.getServiceDescriptor(), 0);
            j context = getContext();
            p0 uploadFileMethod = FileServiceGrpc.getUploadFileMethod();
            K.m(uploadFileMethod, "getUploadFileMethod(...)");
            K.n(context, "context");
            o0 o0Var = o0.f2794E;
            if (uploadFileMethod.f2798a == o0Var) {
                s0Var.b(new y0(uploadFileMethod));
                j context2 = getContext();
                p0 batchThumbUploadMethod = FileServiceGrpc.getBatchThumbUploadMethod();
                K.m(batchThumbUploadMethod, "getBatchThumbUploadMethod(...)");
                K.n(context2, "context");
                if (batchThumbUploadMethod.f2798a == o0Var) {
                    s0Var.b(new y0(batchThumbUploadMethod));
                    return s0Var.c();
                }
                throw new IllegalArgumentException(("Expected a client streaming method descriptor but got " + batchThumbUploadMethod).toString());
            }
            throw new IllegalArgumentException(("Expected a client streaming method descriptor but got " + uploadFileMethod).toString());
        }

        public Object uploadFile(C1066b bVar, e eVar) {
            return uploadFile$suspendImpl(this, bVar, eVar);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public FileServiceCoroutineImplBase(j jVar) {
            super(jVar);
            K.n(jVar, "coroutineContext");
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ FileServiceCoroutineImplBase(j jVar, int i5, f fVar) {
            this((i5 & 1) != 0 ? k.f3728D : jVar);
        }
    }

    public static final class FileServiceCoroutineStub extends C0294b {
        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public FileServiceCoroutineStub(C0108g gVar) {
            this(gVar, (C0106f) null, 2, (f) null);
            K.n(gVar, "channel");
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: Y1.m0} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: Y1.m0} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: Y1.m0} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static java.lang.Object batchThumbUpload$default(com.hawkshaw.library.FileServiceGrpcKt.FileServiceCoroutineStub r0, w3.C1066b r1, Y1.m0 r2, a3.e r3, int r4, java.lang.Object r5) {
            /*
                r4 = r4 & 2
                if (r4 == 0) goto L_0x0009
                Y1.m0 r2 = new Y1.m0
                r2.<init>()
            L_0x0009:
                java.lang.Object r0 = r0.batchThumbUpload(r1, r2, r3)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.FileServiceGrpcKt.FileServiceCoroutineStub.batchThumbUpload$default(com.hawkshaw.library.FileServiceGrpcKt$FileServiceCoroutineStub, w3.b, Y1.m0, a3.e, int, java.lang.Object):java.lang.Object");
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: Y1.m0} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: Y1.m0} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: Y1.m0} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static java.lang.Object uploadFile$default(com.hawkshaw.library.FileServiceGrpcKt.FileServiceCoroutineStub r0, w3.C1066b r1, Y1.m0 r2, a3.e r3, int r4, java.lang.Object r5) {
            /*
                r4 = r4 & 2
                if (r4 == 0) goto L_0x0009
                Y1.m0 r2 = new Y1.m0
                r2.<init>()
            L_0x0009:
                java.lang.Object r0 = r0.uploadFile(r1, r2, r3)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.FileServiceGrpcKt.FileServiceCoroutineStub.uploadFile$default(com.hawkshaw.library.FileServiceGrpcKt$FileServiceCoroutineStub, w3.b, Y1.m0, a3.e, int, java.lang.Object):java.lang.Object");
        }

        /* JADX WARNING: Removed duplicated region for block: B:12:0x002f  */
        /* JADX WARNING: Removed duplicated region for block: B:8:0x0021  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object batchThumbUpload(w3.C1066b r11, Y1.m0 r12, a3.e r13) {
            /*
                r10 = this;
                boolean r0 = r13 instanceof com.hawkshaw.library.e
                if (r0 == 0) goto L_0x0013
                r0 = r13
                com.hawkshaw.library.e r0 = (com.hawkshaw.library.e) r0
                int r1 = r0.f4961F
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L_0x0013
                int r1 = r1 - r2
                r0.f4961F = r1
                goto L_0x0018
            L_0x0013:
                com.hawkshaw.library.e r0 = new com.hawkshaw.library.e
                r0.<init>(r10, r13)
            L_0x0018:
                java.lang.Object r13 = r0.f4959D
                b3.a r1 = b3.C0298a.f4140D
                int r2 = r0.f4961F
                r3 = 1
                if (r2 == 0) goto L_0x002f
                if (r2 != r3) goto L_0x0027
                Y1.C0110h.P(r13)
                goto L_0x007d
            L_0x0027:
                java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
                java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
                r11.<init>(r12)
                throw r11
            L_0x002f:
                Y1.C0110h.P(r13)
                Y1.g r13 = r10.getChannel()
                java.lang.String r2 = "getChannel(...)"
                Y1.K.m(r13, r2)
                Y1.p0 r9 = com.hawkshaw.library.FileServiceGrpc.getBatchThumbUploadMethod()
                java.lang.String r2 = "getBatchThumbUploadMethod(...)"
                Y1.K.m(r9, r2)
                Y1.f r5 = r10.getCallOptions()
                java.lang.String r2 = "getCallOptions(...)"
                Y1.K.m(r5, r2)
                r0.f4961F = r3
                Y1.o0 r2 = Y1.o0.f2794E
                Y1.o0 r3 = r9.f2798a
                if (r3 != r2) goto L_0x0083
                b2.e r7 = new b2.e
                r7.<init>(r11)
                b2.j r11 = new b2.j
                r8 = 0
                r2 = r11
                r3 = r13
                r4 = r9
                r6 = r12
                r2.<init>(r3, r4, r5, r6, r7, r8)
                w3.e r12 = new w3.e
                r12.<init>((i3.p) r11)
                b2.k r11 = new b2.k
                r13 = 0
                java.lang.String r2 = "response"
                r11.<init>(r12, r2, r9, r13)
                w3.e r12 = new w3.e
                r12.<init>((i3.p) r11)
                java.lang.Object r13 = v3.j.o(r12, r0)
                if (r13 != r1) goto L_0x007d
                return r1
            L_0x007d:
                java.lang.String r11 = "clientStreamingRpc(...)"
                Y1.K.m(r13, r11)
                return r13
            L_0x0083:
                java.lang.StringBuilder r11 = new java.lang.StringBuilder
                java.lang.String r12 = "Expected a server streaming RPC method, but got "
                r11.<init>(r12)
                r11.append(r9)
                java.lang.String r11 = r11.toString()
                java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
                java.lang.String r11 = r11.toString()
                r12.<init>(r11)
                throw r12
            */
            throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.FileServiceGrpcKt.FileServiceCoroutineStub.batchThumbUpload(w3.b, Y1.m0, a3.e):java.lang.Object");
        }

        public FileServiceCoroutineStub build(C0108g gVar, C0106f fVar) {
            K.n(gVar, "channel");
            K.n(fVar, "callOptions");
            return new FileServiceCoroutineStub(gVar, fVar);
        }

        /* JADX WARNING: Removed duplicated region for block: B:12:0x002f  */
        /* JADX WARNING: Removed duplicated region for block: B:8:0x0021  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object uploadFile(w3.C1066b r11, Y1.m0 r12, a3.e r13) {
            /*
                r10 = this;
                boolean r0 = r13 instanceof com.hawkshaw.library.f
                if (r0 == 0) goto L_0x0013
                r0 = r13
                com.hawkshaw.library.f r0 = (com.hawkshaw.library.f) r0
                int r1 = r0.f4964F
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L_0x0013
                int r1 = r1 - r2
                r0.f4964F = r1
                goto L_0x0018
            L_0x0013:
                com.hawkshaw.library.f r0 = new com.hawkshaw.library.f
                r0.<init>(r10, r13)
            L_0x0018:
                java.lang.Object r13 = r0.f4962D
                b3.a r1 = b3.C0298a.f4140D
                int r2 = r0.f4964F
                r3 = 1
                if (r2 == 0) goto L_0x002f
                if (r2 != r3) goto L_0x0027
                Y1.C0110h.P(r13)
                goto L_0x007d
            L_0x0027:
                java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
                java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
                r11.<init>(r12)
                throw r11
            L_0x002f:
                Y1.C0110h.P(r13)
                Y1.g r13 = r10.getChannel()
                java.lang.String r2 = "getChannel(...)"
                Y1.K.m(r13, r2)
                Y1.p0 r9 = com.hawkshaw.library.FileServiceGrpc.getUploadFileMethod()
                java.lang.String r2 = "getUploadFileMethod(...)"
                Y1.K.m(r9, r2)
                Y1.f r5 = r10.getCallOptions()
                java.lang.String r2 = "getCallOptions(...)"
                Y1.K.m(r5, r2)
                r0.f4964F = r3
                Y1.o0 r2 = Y1.o0.f2794E
                Y1.o0 r3 = r9.f2798a
                if (r3 != r2) goto L_0x0083
                b2.e r7 = new b2.e
                r7.<init>(r11)
                b2.j r11 = new b2.j
                r8 = 0
                r2 = r11
                r3 = r13
                r4 = r9
                r6 = r12
                r2.<init>(r3, r4, r5, r6, r7, r8)
                w3.e r12 = new w3.e
                r12.<init>((i3.p) r11)
                b2.k r11 = new b2.k
                r13 = 0
                java.lang.String r2 = "response"
                r11.<init>(r12, r2, r9, r13)
                w3.e r12 = new w3.e
                r12.<init>((i3.p) r11)
                java.lang.Object r13 = v3.j.o(r12, r0)
                if (r13 != r1) goto L_0x007d
                return r1
            L_0x007d:
                java.lang.String r11 = "clientStreamingRpc(...)"
                Y1.K.m(r13, r11)
                return r13
            L_0x0083:
                java.lang.StringBuilder r11 = new java.lang.StringBuilder
                java.lang.String r12 = "Expected a server streaming RPC method, but got "
                r11.<init>(r12)
                r11.append(r9)
                java.lang.String r11 = r11.toString()
                java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
                java.lang.String r11 = r11.toString()
                r12.<init>(r11)
                throw r12
            */
            throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.FileServiceGrpcKt.FileServiceCoroutineStub.uploadFile(w3.b, Y1.m0, a3.e):java.lang.Object");
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public FileServiceCoroutineStub(C0108g gVar, C0106f fVar) {
            super(gVar, fVar);
            K.n(gVar, "channel");
            K.n(fVar, "callOptions");
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public /* synthetic */ FileServiceCoroutineStub(Y1.C0108g r1, Y1.C0106f r2, int r3, j3.f r4) {
            /*
                r0 = this;
                r3 = r3 & 2
                if (r3 == 0) goto L_0x000b
                Y1.f r2 = Y1.C0106f.f2766k
                java.lang.String r3 = "DEFAULT"
                Y1.K.m(r2, r3)
            L_0x000b:
                r0.<init>(r1, r2)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.FileServiceGrpcKt.FileServiceCoroutineStub.<init>(Y1.g, Y1.f, int, j3.f):void");
        }
    }

    private FileServiceGrpcKt() {
    }

    public static final p0 getBatchThumbUploadMethod() {
        p0 batchThumbUploadMethod = FileServiceGrpc.getBatchThumbUploadMethod();
        K.m(batchThumbUploadMethod, "getBatchThumbUploadMethod(...)");
        return batchThumbUploadMethod;
    }

    public static final A0 getServiceDescriptor() {
        A0 serviceDescriptor = FileServiceGrpc.getServiceDescriptor();
        K.m(serviceDescriptor, "getServiceDescriptor(...)");
        return serviceDescriptor;
    }

    public static /* synthetic */ void getServiceDescriptor$annotations() {
    }

    public static final p0 getUploadFileMethod() {
        p0 uploadFileMethod = FileServiceGrpc.getUploadFileMethod();
        K.m(uploadFileMethod, "getUploadFileMethod(...)");
        return uploadFileMethod;
    }
}
