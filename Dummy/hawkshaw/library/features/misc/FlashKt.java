package com.hawkshaw.library.features.misc;

public final class FlashKt {
    private static final String TAG = "Flash";

    /* JADX WARNING: type inference failed for: r1v3, types: [c3.c] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00bf  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object flash(android.content.Context r24, com.hawkshaw.library.datalayer.models.Command.FlashRequest r25, a3.e r26) {
        /*
            r0 = r26
            boolean r1 = r0 instanceof Q1.a
            if (r1 == 0) goto L_0x0015
            r1 = r0
            Q1.a r1 = (Q1.a) r1
            int r2 = r1.f1785K
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L_0x0015
            int r2 = r2 - r3
            r1.f1785K = r2
            goto L_0x001a
        L_0x0015:
            Q1.a r1 = new Q1.a
            r1.<init>(r0)
        L_0x001a:
            java.lang.Object r0 = r1.f1784J
            b3.a r2 = b3.C0298a.f4140D
            int r3 = r1.f1785K
            W2.y r4 = W2.y.f2418a
            r5 = 1
            r6 = 0
            if (r3 == 0) goto L_0x0042
            if (r3 != r5) goto L_0x003a
            int r3 = r1.f1783I
            int r7 = r1.f1782H
            int r8 = r1.f1781G
            long[] r9 = r1.f1780F
            java.lang.String r10 = r1.f1779E
            android.hardware.camera2.CameraManager r11 = r1.f1778D
            Y1.C0110h.P(r0)
        L_0x0037:
            r0 = r8
            goto L_0x00e2
        L_0x003a:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0042:
            Y1.C0110h.P(r0)
            com.hawkshaw.library.datalayer.models.Command$FlashRequest$Facing r0 = r25.getFacing()
            com.hawkshaw.library.datalayer.models.Command$FlashRequest$Facing r3 = com.hawkshaw.library.datalayer.models.Command.FlashRequest.Facing.Front
            if (r0 != r3) goto L_0x004f
            r0 = r6
            goto L_0x0050
        L_0x004f:
            r0 = r5
        L_0x0050:
            java.lang.Class<android.hardware.camera2.CameraManager> r3 = android.hardware.camera2.CameraManager.class
            r7 = r24
            java.lang.Object r3 = r7.getSystemService(r3)
            android.hardware.camera2.CameraManager r3 = (android.hardware.camera2.CameraManager) r3
            java.lang.String[] r7 = r3.getCameraIdList()
            java.lang.String r8 = "getCameraIdList(...)"
            Y1.K.m(r7, r8)
            int r8 = r7.length
            r9 = r6
        L_0x0065:
            if (r9 >= r8) goto L_0x00fa
            r10 = r7[r9]
            android.hardware.camera2.CameraCharacteristics r11 = r3.getCameraCharacteristics(r10)
            java.lang.String r12 = "getCameraCharacteristics(...)"
            Y1.K.m(r11, r12)
            android.hardware.camera2.CameraCharacteristics$Key r12 = android.hardware.camera2.CameraCharacteristics.LENS_FACING
            java.lang.Object r12 = r11.get(r12)
            java.lang.Integer r12 = (java.lang.Integer) r12
            if (r12 != 0) goto L_0x007e
            goto L_0x00f6
        L_0x007e:
            int r12 = r12.intValue()
            if (r12 != r0) goto L_0x00f6
            android.hardware.camera2.CameraCharacteristics$Key r0 = android.hardware.camera2.CameraCharacteristics.FLASH_INFO_AVAILABLE
            java.lang.Object r0 = r11.get(r0)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            java.lang.Boolean r7 = java.lang.Boolean.TRUE
            boolean r0 = Y1.K.f(r0, r7)
            if (r0 != 0) goto L_0x00a4
            com.hawkshaw.library.logger.Logger r11 = com.hawkshaw.library.logger.Logger.INSTANCE
            r14 = 0
            r15 = 0
            java.lang.String r12 = "Flash"
            java.lang.String r13 = "Flash: Selected camera does not have any flash!"
            r16 = 12
            r17 = 0
            com.hawkshaw.library.logger.Logger.e$default(r11, r12, r13, r14, r15, r16, r17)
            return r4
        L_0x00a4:
            com.hawkshaw.library.logger.Logger r18 = com.hawkshaw.library.logger.Logger.INSTANCE
            java.lang.String r20 = "Flash: Staring flash"
            r21 = 0
            java.lang.String r19 = "Flash"
            r22 = 4
            r23 = 0
            com.hawkshaw.library.logger.Logger.v$default(r18, r19, r20, r21, r22, r23)
            long[] r0 = r25.getTimings()
            int r7 = r0.length
            r9 = r0
            r11 = r3
            r0 = r6
            r3 = r7
            r7 = r0
        L_0x00bd:
            if (r7 >= r3) goto L_0x00e4
            r12 = r9[r7]
            int r8 = r0 + 1
            int r0 = r0 % 2
            if (r0 == 0) goto L_0x00c9
            r0 = r5
            goto L_0x00ca
        L_0x00c9:
            r0 = r6
        L_0x00ca:
            r11.setTorchMode(r10, r0)
            r1.f1778D = r11
            r1.f1779E = r10
            r1.f1780F = r9
            r1.f1781G = r8
            r1.f1782H = r7
            r1.f1783I = r3
            r1.f1785K = r5
            java.lang.Object r0 = Y1.K.C(r12, r1)
            if (r0 != r2) goto L_0x0037
            return r2
        L_0x00e2:
            int r7 = r7 + r5
            goto L_0x00bd
        L_0x00e4:
            r11.setTorchMode(r10, r6)
            com.hawkshaw.library.logger.Logger r12 = com.hawkshaw.library.logger.Logger.INSTANCE
            java.lang.String r14 = "Flash: Turning of flash"
            r15 = 0
            java.lang.String r13 = "Flash"
            r16 = 4
            r17 = 0
            com.hawkshaw.library.logger.Logger.v$default(r12, r13, r14, r15, r16, r17)
            goto L_0x00fa
        L_0x00f6:
            int r9 = r9 + 1
            goto L_0x0065
        L_0x00fa:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.features.misc.FlashKt.flash(android.content.Context, com.hawkshaw.library.datalayer.models.Command$FlashRequest, a3.e):java.lang.Object");
    }
}
