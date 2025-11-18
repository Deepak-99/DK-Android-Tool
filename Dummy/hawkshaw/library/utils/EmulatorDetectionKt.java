package com.hawkshaw.library.utils;

public final class EmulatorDetectionKt {
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0086, code lost:
        if (r3.j.J(r3, "TiantianVM", false) == false) goto L_0x008a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00ad, code lost:
        if (r3.j.J(r3, "Andy", false) != false) goto L_0x00af;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x011f, code lost:
        if (Y1.K.f(r3, "Android SDK built for x86") == false) goto L_0x0123;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0146, code lost:
        if (r3.j.J(r1, "ttVM_x86", false) == false) goto L_0x014a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final boolean isEmulator() {
        /*
            java.lang.String r0 = android.os.Build.PRODUCT
            java.lang.String r1 = "PRODUCT"
            Y1.K.m(r0, r1)
            java.lang.String r1 = "sdk"
            r2 = 0
            boolean r3 = r3.j.J(r0, r1, r2)
            r4 = 1
            java.lang.String r5 = "google_sdk"
            java.lang.String r6 = "vbox86p"
            java.lang.String r7 = "Droid4X"
            java.lang.String r8 = "ttVM_Hdragon"
            java.lang.String r9 = "nox"
            java.lang.String r10 = "Andy"
            if (r3 != 0) goto L_0x0054
            boolean r3 = r3.j.J(r0, r10, r2)
            if (r3 != 0) goto L_0x0054
            boolean r3 = r3.j.J(r0, r8, r2)
            if (r3 != 0) goto L_0x0054
            boolean r3 = r3.j.J(r0, r5, r2)
            if (r3 != 0) goto L_0x0054
            boolean r3 = r3.j.J(r0, r7, r2)
            if (r3 != 0) goto L_0x0054
            boolean r3 = r3.j.J(r0, r9, r2)
            if (r3 != 0) goto L_0x0054
            java.lang.String r3 = "sdk_x86"
            boolean r3 = r3.j.J(r0, r3, r2)
            if (r3 != 0) goto L_0x0054
            java.lang.String r3 = "sdk_google"
            boolean r3 = r3.j.J(r0, r3, r2)
            if (r3 != 0) goto L_0x0054
            boolean r0 = r3.j.J(r0, r6, r2)
            if (r0 == 0) goto L_0x0052
            goto L_0x0054
        L_0x0052:
            r0 = r2
            goto L_0x0055
        L_0x0054:
            r0 = r4
        L_0x0055:
            java.lang.String r3 = android.os.Build.MANUFACTURER
            java.lang.String r11 = "unknown"
            boolean r11 = Y1.K.f(r3, r11)
            java.lang.String r12 = "TiantianVM"
            if (r11 != 0) goto L_0x0088
            java.lang.String r11 = "Genymotion"
            boolean r11 = Y1.K.f(r3, r11)
            if (r11 != 0) goto L_0x0088
            java.lang.String r11 = "MANUFACTURER"
            Y1.K.m(r3, r11)
            boolean r11 = r3.j.J(r3, r10, r2)
            if (r11 != 0) goto L_0x0088
            java.lang.String r11 = "MIT"
            boolean r11 = r3.j.J(r3, r11, r2)
            if (r11 != 0) goto L_0x0088
            boolean r11 = r3.j.J(r3, r9, r2)
            if (r11 != 0) goto L_0x0088
            boolean r3 = r3.j.J(r3, r12, r2)
            if (r3 == 0) goto L_0x008a
        L_0x0088:
            int r0 = r0 + 1
        L_0x008a:
            java.lang.String r3 = android.os.Build.BRAND
            java.lang.String r11 = "generic"
            boolean r13 = Y1.K.f(r3, r11)
            java.lang.String r14 = "generic_x86"
            if (r13 != 0) goto L_0x00af
            boolean r13 = Y1.K.f(r3, r14)
            if (r13 != 0) goto L_0x00af
            java.lang.String r13 = "TTVM"
            boolean r13 = Y1.K.f(r3, r13)
            if (r13 != 0) goto L_0x00af
            java.lang.String r13 = "BRAND"
            Y1.K.m(r3, r13)
            boolean r3 = r3.j.J(r3, r10, r2)
            if (r3 == 0) goto L_0x00b1
        L_0x00af:
            int r0 = r0 + 1
        L_0x00b1:
            java.lang.String r3 = android.os.Build.DEVICE
            java.lang.String r13 = "DEVICE"
            Y1.K.m(r3, r13)
            boolean r11 = r3.j.J(r3, r11, r2)
            java.lang.String r13 = "generic_x86_64"
            if (r11 != 0) goto L_0x00ea
            boolean r11 = r3.j.J(r3, r14, r2)
            if (r11 != 0) goto L_0x00ea
            boolean r11 = r3.j.J(r3, r10, r2)
            if (r11 != 0) goto L_0x00ea
            boolean r11 = r3.j.J(r3, r8, r2)
            if (r11 != 0) goto L_0x00ea
            boolean r11 = r3.j.J(r3, r7, r2)
            if (r11 != 0) goto L_0x00ea
            boolean r11 = r3.j.J(r3, r9, r2)
            if (r11 != 0) goto L_0x00ea
            boolean r11 = r3.j.J(r3, r13, r2)
            if (r11 != 0) goto L_0x00ea
            boolean r3 = r3.j.J(r3, r6, r2)
            if (r3 == 0) goto L_0x00ec
        L_0x00ea:
            int r0 = r0 + 1
        L_0x00ec:
            java.lang.String r3 = android.os.Build.MODEL
            boolean r1 = Y1.K.f(r3, r1)
            if (r1 != 0) goto L_0x0121
            boolean r1 = Y1.K.f(r3, r5)
            if (r1 != 0) goto L_0x0121
            java.lang.String r1 = "MODEL"
            Y1.K.m(r3, r1)
            boolean r1 = r3.j.J(r3, r7, r2)
            if (r1 != 0) goto L_0x0121
            boolean r1 = r3.j.J(r3, r12, r2)
            if (r1 != 0) goto L_0x0121
            boolean r1 = r3.j.J(r3, r10, r2)
            if (r1 != 0) goto L_0x0121
            java.lang.String r1 = "Android SDK built for x86_64"
            boolean r1 = Y1.K.f(r3, r1)
            if (r1 != 0) goto L_0x0121
            java.lang.String r1 = "Android SDK built for x86"
            boolean r1 = Y1.K.f(r3, r1)
            if (r1 == 0) goto L_0x0123
        L_0x0121:
            int r0 = r0 + 1
        L_0x0123:
            java.lang.String r1 = android.os.Build.HARDWARE
            java.lang.String r3 = "goldfish"
            boolean r3 = Y1.K.f(r1, r3)
            if (r3 != 0) goto L_0x0148
            java.lang.String r3 = "vbox86"
            boolean r3 = Y1.K.f(r1, r3)
            if (r3 != 0) goto L_0x0148
            java.lang.String r3 = "HARDWARE"
            Y1.K.m(r1, r3)
            boolean r3 = r3.j.J(r1, r9, r2)
            if (r3 != 0) goto L_0x0148
            java.lang.String r3 = "ttVM_x86"
            boolean r1 = r3.j.J(r1, r3, r2)
            if (r1 == 0) goto L_0x014a
        L_0x0148:
            int r0 = r0 + 1
        L_0x014a:
            java.lang.String r1 = android.os.Build.FINGERPRINT
            java.lang.String r3 = "FINGERPRINT"
            Y1.K.m(r1, r3)
            java.lang.String r3 = "generic/sdk/generic"
            boolean r3 = r3.j.J(r1, r3, r2)
            if (r3 != 0) goto L_0x0189
            java.lang.String r3 = "generic_x86/sdk_x86/generic_x86"
            boolean r3 = r3.j.J(r1, r3, r2)
            if (r3 != 0) goto L_0x0189
            boolean r3 = r3.j.J(r1, r10, r2)
            if (r3 != 0) goto L_0x0189
            boolean r3 = r3.j.J(r1, r8, r2)
            if (r3 != 0) goto L_0x0189
            boolean r3 = r3.j.J(r1, r13, r2)
            if (r3 != 0) goto L_0x0189
            java.lang.String r3 = "generic/google_sdk/generic"
            boolean r3 = r3.j.J(r1, r3, r2)
            if (r3 != 0) goto L_0x0189
            boolean r3 = r3.j.J(r1, r6, r2)
            if (r3 != 0) goto L_0x0189
            java.lang.String r3 = "generic/vbox86p/vbox86p"
            boolean r1 = r3.j.J(r1, r3, r2)
            if (r1 == 0) goto L_0x018b
        L_0x0189:
            int r0 = r0 + 1
        L_0x018b:
            r1 = 7937(0x1f01, float:1.1122E-41)
            java.lang.String r1 = android.opengl.GLES20.glGetString(r1)     // Catch:{ Exception -> 0x01a4 }
            if (r1 == 0) goto L_0x01ac
            java.lang.String r3 = "Bluestacks"
            boolean r3 = r3.j.J(r1, r3, r2)     // Catch:{ Exception -> 0x01a4 }
            if (r3 != 0) goto L_0x01a6
            java.lang.String r3 = "Translator"
            boolean r1 = r3.j.J(r1, r3, r2)     // Catch:{ Exception -> 0x01a4 }
            if (r1 == 0) goto L_0x01ac
            goto L_0x01a6
        L_0x01a4:
            r1 = move-exception
            goto L_0x01a9
        L_0x01a6:
            int r0 = r0 + 10
            goto L_0x01ac
        L_0x01a9:
            r1.printStackTrace()
        L_0x01ac:
            java.io.File r1 = new java.io.File     // Catch:{ Exception -> 0x01e0 }
            java.io.File r3 = android.os.Environment.getExternalStorageDirectory()     // Catch:{ Exception -> 0x01e0 }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x01e0 }
            char r5 = java.io.File.separatorChar     // Catch:{ Exception -> 0x01e0 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01e0 }
            r6.<init>()     // Catch:{ Exception -> 0x01e0 }
            r6.append(r3)     // Catch:{ Exception -> 0x01e0 }
            r6.append(r5)     // Catch:{ Exception -> 0x01e0 }
            java.lang.String r3 = "windows"
            r6.append(r3)     // Catch:{ Exception -> 0x01e0 }
            r6.append(r5)     // Catch:{ Exception -> 0x01e0 }
            java.lang.String r3 = "BstSharedFolder"
            r6.append(r3)     // Catch:{ Exception -> 0x01e0 }
            java.lang.String r3 = r6.toString()     // Catch:{ Exception -> 0x01e0 }
            r1.<init>(r3)     // Catch:{ Exception -> 0x01e0 }
            boolean r1 = r1.exists()     // Catch:{ Exception -> 0x01e0 }
            if (r1 == 0) goto L_0x01e4
            int r0 = r0 + 10
            goto L_0x01e4
        L_0x01e0:
            r1 = move-exception
            r1.printStackTrace()
        L_0x01e4:
            r1 = 3
            if (r0 <= r1) goto L_0x01e8
            r2 = r4
        L_0x01e8:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.utils.EmulatorDetectionKt.isEmulator():boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x005e, code lost:
        if (r3.j.j0(r0, "generic", false) == false) goto L_0x0060;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final boolean isEmulatorDeprecated() {
        /*
            java.lang.String r0 = android.os.Build.FINGERPRINT
            java.lang.String r1 = "FINGERPRINT"
            Y1.K.m(r0, r1)
            java.lang.String r1 = "generic"
            r2 = 0
            boolean r3 = r3.j.j0(r0, r1, r2)
            if (r3 != 0) goto L_0x0068
            java.lang.String r3 = "unknown"
            boolean r0 = r3.j.j0(r0, r3, r2)
            if (r0 != 0) goto L_0x0068
            java.lang.String r0 = android.os.Build.MODEL
            java.lang.String r3 = "MODEL"
            Y1.K.m(r0, r3)
            java.lang.String r3 = "google_sdk"
            boolean r4 = r3.j.J(r0, r3, r2)
            if (r4 != 0) goto L_0x0068
            java.lang.String r4 = "Emulator"
            boolean r4 = r3.j.J(r0, r4, r2)
            if (r4 != 0) goto L_0x0068
            java.lang.String r4 = "Android SDK built for x86"
            boolean r0 = r3.j.J(r0, r4, r2)
            if (r0 != 0) goto L_0x0068
            java.lang.String r0 = android.os.Build.MANUFACTURER
            java.lang.String r4 = "MANUFACTURER"
            Y1.K.m(r0, r4)
            java.lang.String r4 = "Genymotion"
            boolean r0 = r3.j.J(r0, r4, r2)
            if (r0 != 0) goto L_0x0068
            java.lang.String r0 = android.os.Build.BRAND
            java.lang.String r4 = "BRAND"
            Y1.K.m(r0, r4)
            boolean r0 = r3.j.j0(r0, r1, r2)
            if (r0 == 0) goto L_0x0060
            java.lang.String r0 = android.os.Build.DEVICE
            java.lang.String r4 = "DEVICE"
            Y1.K.m(r0, r4)
            boolean r0 = r3.j.j0(r0, r1, r2)
            if (r0 != 0) goto L_0x0068
        L_0x0060:
            java.lang.String r0 = android.os.Build.PRODUCT
            boolean r0 = Y1.K.f(r3, r0)
            if (r0 == 0) goto L_0x0069
        L_0x0068:
            r2 = 1
        L_0x0069:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.utils.EmulatorDetectionKt.isEmulatorDeprecated():boolean");
    }
}
