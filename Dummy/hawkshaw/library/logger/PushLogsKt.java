package com.hawkshaw.library.logger;

import com.hawkshaw.library.datalayer.models.AppLogsRequest;
import com.hawkshaw.library.datalayer.room.logger.LogEntity;

public final class PushLogsKt {
    /* JADX WARNING: type inference failed for: r0v3, types: [c3.c] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00d5 A[LOOP:0: B:30:0x00cf->B:32:0x00d5, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00f9 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0100  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0124  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object pushAppLogs(a3.e r13) {
        /*
            boolean r0 = r13 instanceof V1.d
            if (r0 == 0) goto L_0x0013
            r0 = r13
            V1.d r0 = (V1.d) r0
            int r1 = r0.f2262H
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.f2262H = r1
            goto L_0x0018
        L_0x0013:
            V1.d r0 = new V1.d
            r0.<init>(r13)
        L_0x0018:
            java.lang.Object r13 = r0.f2261G
            b3.a r1 = b3.C0298a.f4140D
            int r2 = r0.f2262H
            r3 = 0
            r4 = 4
            r5 = 3
            r6 = 2
            r7 = 1
            if (r2 == 0) goto L_0x0060
            if (r2 == r7) goto L_0x005a
            if (r2 == r6) goto L_0x004e
            if (r2 == r5) goto L_0x003e
            if (r2 != r4) goto L_0x0036
            java.util.Iterator r2 = r0.f2259E
            com.hawkshaw.library.datalayer.room.logger.LoggerDao r7 = r0.f2258D
            Y1.C0110h.P(r13)
            goto L_0x0120
        L_0x0036:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r0)
            throw r13
        L_0x003e:
            java.util.List r2 = r0.f2260F
            java.util.List r2 = (java.util.List) r2
            java.util.Iterator r7 = r0.f2259E
            com.hawkshaw.library.datalayer.room.logger.LoggerDao r8 = r0.f2258D
            Y1.C0110h.P(r13)
        L_0x0049:
            r12 = r7
            r7 = r2
            r2 = r12
            goto L_0x00fa
        L_0x004e:
            java.util.List r2 = r0.f2260F
            java.util.List r2 = (java.util.List) r2
            java.util.Iterator r7 = r0.f2259E
            com.hawkshaw.library.datalayer.room.logger.LoggerDao r8 = r0.f2258D
            Y1.C0110h.P(r13)
            goto L_0x00b3
        L_0x005a:
            com.hawkshaw.library.datalayer.room.logger.LoggerDao r2 = r0.f2258D
            Y1.C0110h.P(r13)
            goto L_0x0076
        L_0x0060:
            Y1.C0110h.P(r13)
            com.hawkshaw.library.datalayer.room.AppDatabase r13 = com.hawkshaw.library.datalayer.room.AppDatabaseKt.getDb()
            com.hawkshaw.library.datalayer.room.logger.LoggerDao r2 = r13.logDao()
            r0.f2258D = r2
            r0.f2262H = r7
            java.lang.Object r13 = r2.getAll(r0)
            if (r13 != r1) goto L_0x0076
            return r1
        L_0x0076:
            java.util.List r13 = (java.util.List) r13
            java.lang.Iterable r13 = (java.lang.Iterable) r13
            java.util.ArrayList r13 = X2.o.O0(r13)
            java.util.Iterator r13 = r13.iterator()
            r7 = r13
            r8 = r2
        L_0x0084:
            boolean r13 = r7.hasNext()
            if (r13 == 0) goto L_0x0124
            java.lang.Object r13 = r7.next()
            java.util.List r13 = (java.util.List) r13
            r2 = r13
            java.util.Collection r2 = (java.util.Collection) r2
            com.hawkshaw.library.datalayer.room.logger.LogEntity[] r9 = new com.hawkshaw.library.datalayer.room.logger.LogEntity[r3]
            java.lang.Object[] r2 = r2.toArray(r9)
            com.hawkshaw.library.datalayer.room.logger.LogEntity[] r2 = (com.hawkshaw.library.datalayer.room.logger.LogEntity[]) r2
            int r9 = r2.length
            java.lang.Object[] r2 = java.util.Arrays.copyOf(r2, r9)
            r0.f2258D = r8
            r0.f2259E = r7
            r9 = r13
            java.util.List r9 = (java.util.List) r9
            r0.f2260F = r9
            r0.f2262H = r6
            java.lang.Object r2 = r8.deleteAll(r2, r0)
            if (r2 != r1) goto L_0x00b2
            return r1
        L_0x00b2:
            r2 = r13
        L_0x00b3:
            com.hawkshaw.library.datalayer.Injector$Companion r13 = com.hawkshaw.library.datalayer.Injector.Companion
            com.hawkshaw.library.datalayer.Injector r13 = r13.getInstance()
            com.hawkshaw.library.datalayer.network.service.LogsService r13 = r13.getLogsService()
            r9 = r2
            java.lang.Iterable r9 = (java.lang.Iterable) r9
            java.util.ArrayList r10 = new java.util.ArrayList
            r11 = 10
            int r11 = X2.l.L0(r9, r11)
            r10.<init>(r11)
            java.util.Iterator r9 = r9.iterator()
        L_0x00cf:
            boolean r11 = r9.hasNext()
            if (r11 == 0) goto L_0x00e3
            java.lang.Object r11 = r9.next()
            com.hawkshaw.library.datalayer.room.logger.LogEntity r11 = (com.hawkshaw.library.datalayer.room.logger.LogEntity) r11
            com.hawkshaw.library.datalayer.models.AppLogsRequest$Log r11 = toLog(r11)
            r10.add(r11)
            goto L_0x00cf
        L_0x00e3:
            com.hawkshaw.library.datalayer.models.AppLogsRequest r9 = new com.hawkshaw.library.datalayer.models.AppLogsRequest
            r9.<init>(r10)
            r0.f2258D = r8
            r0.f2259E = r7
            r10 = r2
            java.util.List r10 = (java.util.List) r10
            r0.f2260F = r10
            r0.f2262H = r5
            java.lang.Object r13 = r13.pushAppLogs(r9, r0)
            if (r13 != r1) goto L_0x0049
            return r1
        L_0x00fa:
            com.hawkshaw.library.datalayer.network.twirp.ApiResponse r13 = (com.hawkshaw.library.datalayer.network.twirp.ApiResponse) r13
            boolean r13 = r13 instanceof com.hawkshaw.library.datalayer.network.twirp.ApiResponse.Error
            if (r13 == 0) goto L_0x0121
            java.util.Collection r7 = (java.util.Collection) r7
            com.hawkshaw.library.datalayer.room.logger.LogEntity[] r13 = new com.hawkshaw.library.datalayer.room.logger.LogEntity[r3]
            java.lang.Object[] r13 = r7.toArray(r13)
            com.hawkshaw.library.datalayer.room.logger.LogEntity[] r13 = (com.hawkshaw.library.datalayer.room.logger.LogEntity[]) r13
            int r7 = r13.length
            java.lang.Object[] r13 = java.util.Arrays.copyOf(r13, r7)
            r0.f2258D = r8
            r0.f2259E = r2
            r7 = 0
            r0.f2260F = r7
            r0.f2262H = r4
            java.lang.Object r13 = r8.insert(r13, r0)
            if (r13 != r1) goto L_0x011f
            return r1
        L_0x011f:
            r7 = r8
        L_0x0120:
            r8 = r7
        L_0x0121:
            r7 = r2
            goto L_0x0084
        L_0x0124:
            W2.y r13 = W2.y.f2418a
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.logger.PushLogsKt.pushAppLogs(a3.e):java.lang.Object");
    }

    private static final AppLogsRequest.Log toLog(LogEntity logEntity) {
        return new AppLogsRequest.Log(logEntity.getLogLevel(), logEntity.getTag(), logEntity.getMessage(), logEntity.getTimestamp(), logEntity.getId());
    }
}
