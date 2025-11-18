package com.hawkshaw.library.datalayer.network.grpc;

import W2.y;
import Y1.K;
import a3.e;
import b3.C0298a;
import i3.C0542a;
import i3.l;
import i3.p;
import j3.f;

public interface GrpcResponse<T> {

    public static final class Default implements GrpcResponse {
        public static final Default INSTANCE = new Default();

        private Default() {
        }

        public String getState() {
            return DefaultImpls.getState(this);
        }

        public Object handle(C0542a aVar, p pVar, l lVar, e eVar) {
            return DefaultImpls.handle(this, aVar, pVar, lVar, eVar);
        }

        public boolean isError() {
            return DefaultImpls.isError(this);
        }

        public boolean isInProgress() {
            return DefaultImpls.isInProgress(this);
        }

        public boolean isSuccess() {
            return DefaultImpls.isSuccess(this);
        }

        public Object onError(p pVar, e eVar) {
            return DefaultImpls.onError(this, pVar, eVar);
        }

        public GrpcResponse onProgress(C0542a aVar) {
            return DefaultImpls.onProgress(this, aVar);
        }

        public Object onSuccess(p pVar, e eVar) {
            return DefaultImpls.onSuccess(this, pVar, eVar);
        }
    }

    public static final class DefaultImpls {
        public static <T> String getState(GrpcResponse<? extends T> grpcResponse) {
            return grpcResponse instanceof Default ? "Default" : grpcResponse instanceof Error ? "Error" : grpcResponse instanceof InProgress ? "InProgress" : grpcResponse instanceof Success ? "Success" : "Unknown";
        }

        public static <T> Object handle(GrpcResponse<? extends T> grpcResponse, C0542a aVar, p pVar, l lVar, e eVar) {
            boolean z4 = grpcResponse instanceof Default;
            y yVar = y.f2418a;
            if (!z4) {
                if (grpcResponse instanceof InProgress) {
                    aVar.invoke();
                } else if (grpcResponse instanceof Success) {
                    Object invoke = pVar.invoke(((Success) grpcResponse).getResult(), eVar);
                    return invoke == C0298a.f4140D ? invoke : yVar;
                } else if (grpcResponse instanceof Error) {
                    lVar.invoke(((Error) grpcResponse).getError());
                }
            }
            return yVar;
        }

        public static <T> boolean isError(GrpcResponse<? extends T> grpcResponse) {
            return grpcResponse instanceof Error;
        }

        public static <T> boolean isInProgress(GrpcResponse<? extends T> grpcResponse) {
            return grpcResponse instanceof InProgress;
        }

        public static <T> boolean isSuccess(GrpcResponse<? extends T> grpcResponse) {
            return grpcResponse instanceof Success;
        }

        /* JADX WARNING: type inference failed for: r0v2, types: [c3.c] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* JADX WARNING: Removed duplicated region for block: B:12:0x0031  */
        /* JADX WARNING: Removed duplicated region for block: B:8:0x0021  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static <T> java.lang.Object onError(com.hawkshaw.library.datalayer.network.grpc.GrpcResponse<? extends T> r4, i3.p r5, a3.e r6) {
            /*
                boolean r0 = r6 instanceof com.hawkshaw.library.datalayer.network.grpc.e
                if (r0 == 0) goto L_0x0013
                r0 = r6
                com.hawkshaw.library.datalayer.network.grpc.e r0 = (com.hawkshaw.library.datalayer.network.grpc.e) r0
                int r1 = r0.f4919F
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L_0x0013
                int r1 = r1 - r2
                r0.f4919F = r1
                goto L_0x0018
            L_0x0013:
                com.hawkshaw.library.datalayer.network.grpc.e r0 = new com.hawkshaw.library.datalayer.network.grpc.e
                r0.<init>(r6)
            L_0x0018:
                java.lang.Object r6 = r0.f4918E
                b3.a r1 = b3.C0298a.f4140D
                int r2 = r0.f4919F
                r3 = 1
                if (r2 == 0) goto L_0x0031
                if (r2 != r3) goto L_0x0029
                com.hawkshaw.library.datalayer.network.grpc.GrpcResponse r4 = r0.f4917D
                Y1.C0110h.P(r6)
                goto L_0x004a
            L_0x0029:
                java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                r4.<init>(r5)
                throw r4
            L_0x0031:
                Y1.C0110h.P(r6)
                boolean r6 = r4 instanceof com.hawkshaw.library.datalayer.network.grpc.GrpcResponse.Error
                if (r6 == 0) goto L_0x004a
                r6 = r4
                com.hawkshaw.library.datalayer.network.grpc.GrpcResponse$Error r6 = (com.hawkshaw.library.datalayer.network.grpc.GrpcResponse.Error) r6
                com.hawkshaw.library.datalayer.network.grpc.GrpcError r6 = r6.getError()
                r0.f4917D = r4
                r0.f4919F = r3
                java.lang.Object r5 = r5.invoke(r6, r0)
                if (r5 != r1) goto L_0x004a
                return r1
            L_0x004a:
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.network.grpc.GrpcResponse.DefaultImpls.onError(com.hawkshaw.library.datalayer.network.grpc.GrpcResponse, i3.p, a3.e):java.lang.Object");
        }

        public static <T> GrpcResponse<T> onProgress(GrpcResponse<? extends T> grpcResponse, C0542a aVar) {
            K.n(aVar, "cb");
            if (grpcResponse instanceof InProgress) {
                aVar.invoke();
            }
            return grpcResponse;
        }

        /* JADX WARNING: type inference failed for: r0v2, types: [c3.c] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* JADX WARNING: Removed duplicated region for block: B:12:0x0031  */
        /* JADX WARNING: Removed duplicated region for block: B:8:0x0021  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static <T> java.lang.Object onSuccess(com.hawkshaw.library.datalayer.network.grpc.GrpcResponse<? extends T> r4, i3.p r5, a3.e r6) {
            /*
                boolean r0 = r6 instanceof com.hawkshaw.library.datalayer.network.grpc.f
                if (r0 == 0) goto L_0x0013
                r0 = r6
                com.hawkshaw.library.datalayer.network.grpc.f r0 = (com.hawkshaw.library.datalayer.network.grpc.f) r0
                int r1 = r0.f4922F
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L_0x0013
                int r1 = r1 - r2
                r0.f4922F = r1
                goto L_0x0018
            L_0x0013:
                com.hawkshaw.library.datalayer.network.grpc.f r0 = new com.hawkshaw.library.datalayer.network.grpc.f
                r0.<init>(r6)
            L_0x0018:
                java.lang.Object r6 = r0.f4921E
                b3.a r1 = b3.C0298a.f4140D
                int r2 = r0.f4922F
                r3 = 1
                if (r2 == 0) goto L_0x0031
                if (r2 != r3) goto L_0x0029
                com.hawkshaw.library.datalayer.network.grpc.GrpcResponse r4 = r0.f4920D
                Y1.C0110h.P(r6)
                goto L_0x004a
            L_0x0029:
                java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                r4.<init>(r5)
                throw r4
            L_0x0031:
                Y1.C0110h.P(r6)
                boolean r6 = r4 instanceof com.hawkshaw.library.datalayer.network.grpc.GrpcResponse.Success
                if (r6 == 0) goto L_0x004a
                r6 = r4
                com.hawkshaw.library.datalayer.network.grpc.GrpcResponse$Success r6 = (com.hawkshaw.library.datalayer.network.grpc.GrpcResponse.Success) r6
                java.lang.Object r6 = r6.getResult()
                r0.f4920D = r4
                r0.f4922F = r3
                java.lang.Object r5 = r5.invoke(r6, r0)
                if (r5 != r1) goto L_0x004a
                return r1
            L_0x004a:
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.network.grpc.GrpcResponse.DefaultImpls.onSuccess(com.hawkshaw.library.datalayer.network.grpc.GrpcResponse, i3.p, a3.e):java.lang.Object");
        }
    }

    public static final class InProgress implements GrpcResponse {
        public static final InProgress INSTANCE = new InProgress();

        private InProgress() {
        }

        public String getState() {
            return DefaultImpls.getState(this);
        }

        public Object handle(C0542a aVar, p pVar, l lVar, e eVar) {
            return DefaultImpls.handle(this, aVar, pVar, lVar, eVar);
        }

        public boolean isError() {
            return DefaultImpls.isError(this);
        }

        public boolean isInProgress() {
            return DefaultImpls.isInProgress(this);
        }

        public boolean isSuccess() {
            return DefaultImpls.isSuccess(this);
        }

        public Object onError(p pVar, e eVar) {
            return DefaultImpls.onError(this, pVar, eVar);
        }

        public GrpcResponse onProgress(C0542a aVar) {
            return DefaultImpls.onProgress(this, aVar);
        }

        public Object onSuccess(p pVar, e eVar) {
            return DefaultImpls.onSuccess(this, pVar, eVar);
        }
    }

    public static final class Success<T> implements GrpcResponse<T> {
        private final T result;

        public Success(T t4) {
            this.result = t4;
        }

        public final T getResult() {
            return this.result;
        }

        public String getState() {
            return DefaultImpls.getState(this);
        }

        public Object handle(C0542a aVar, p pVar, l lVar, e eVar) {
            return DefaultImpls.handle(this, aVar, pVar, lVar, eVar);
        }

        public boolean isError() {
            return DefaultImpls.isError(this);
        }

        public boolean isInProgress() {
            return DefaultImpls.isInProgress(this);
        }

        public boolean isSuccess() {
            return DefaultImpls.isSuccess(this);
        }

        public Object onError(p pVar, e eVar) {
            return DefaultImpls.onError(this, pVar, eVar);
        }

        public GrpcResponse<T> onProgress(C0542a aVar) {
            return DefaultImpls.onProgress(this, aVar);
        }

        public Object onSuccess(p pVar, e eVar) {
            return DefaultImpls.onSuccess(this, pVar, eVar);
        }
    }

    String getState();

    Object handle(C0542a aVar, p pVar, l lVar, e eVar);

    boolean isError();

    boolean isInProgress();

    boolean isSuccess();

    Object onError(p pVar, e eVar);

    GrpcResponse<T> onProgress(C0542a aVar);

    Object onSuccess(p pVar, e eVar);

    public static final class Error<T> implements GrpcResponse<T> {
        private final GrpcError error;
        private final boolean fromRoot;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Error(GrpcError grpcError, boolean z4, int i5, f fVar) {
            this(grpcError, (i5 & 2) != 0 ? true : z4);
        }

        public final GrpcError getError() {
            return this.error;
        }

        public final boolean getFromRoot() {
            return this.fromRoot;
        }

        public String getState() {
            return DefaultImpls.getState(this);
        }

        public Object handle(C0542a aVar, p pVar, l lVar, e eVar) {
            return DefaultImpls.handle(this, aVar, pVar, lVar, eVar);
        }

        public boolean isError() {
            return DefaultImpls.isError(this);
        }

        public boolean isInProgress() {
            return DefaultImpls.isInProgress(this);
        }

        public boolean isSuccess() {
            return DefaultImpls.isSuccess(this);
        }

        public Object onError(p pVar, e eVar) {
            return DefaultImpls.onError(this, pVar, eVar);
        }

        public GrpcResponse<T> onProgress(C0542a aVar) {
            return DefaultImpls.onProgress(this, aVar);
        }

        public Object onSuccess(p pVar, e eVar) {
            return DefaultImpls.onSuccess(this, pVar, eVar);
        }

        public Error(GrpcError grpcError, boolean z4) {
            K.n(grpcError, "error");
            this.error = grpcError;
            this.fromRoot = z4;
        }
    }
}
