package com.hawkshaw.library.datalayer.network.grpc;

import Y1.K;
import androidx.core.app.NotificationCompat;
import com.hawkshaw.library.datalayer.network.grpc.GrpcResponse;
import i3.l;
import i3.p;
import w3.C1066b;
import w3.e;
import w3.s;
import w3.w;

public final class GrpcCallKt {
    public static final <T> C1066b grpcCallFlow(l lVar) {
        K.n(lVar, NotificationCompat.CATEGORY_CALL);
        return new e((p) new a(lVar, (a3.e) null));
    }

    public static final <T> C1066b grpcCallNestedFlow(boolean z4, l lVar) {
        K.n(lVar, NotificationCompat.CATEGORY_CALL);
        return new e((p) new d(z4, lVar, (a3.e) null));
    }

    public static /* synthetic */ C1066b grpcCallNestedFlow$default(boolean z4, l lVar, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            z4 = true;
        }
        return grpcCallNestedFlow(z4, lVar);
    }

    public static final <T> s grpcStateFlowDefault() {
        return w.a(GrpcResponse.Default.INSTANCE);
    }

    public static final <T> s grpcStateFlowInProgress() {
        return w.a(GrpcResponse.InProgress.INSTANCE);
    }
}
