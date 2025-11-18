package com.hawkshaw.library.features.misc;

public final class VibratorKt {
    private static final String TAG = "Vibrator";

    /* JADX WARNING: type inference failed for: r1v3, types: [c3.c] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0033  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object vibrate(com.hawkshaw.library.datalayer.models.Command.VibrateRequest r17, a3.e r18) {
        
            r0 = r18
            boolean r1 = r0 instanceof Q1.e
            if (r1 == 0) goto L_0x0015
            r1 = r0
            Q1.e r1 = (Q1.e) r1
            int r2 = r1.f1792E
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L_0x0015
            int r2 = r2 - r3
            r1.f1792E = r2
            goto L_0x001a
        L_0x0015:
            Q1.e r1 = new Q1.e
            r1.<init>(r0)
        L_0x001a:
            java.lang.Object r0 = r1.f1791D
            b3.a r2 = b3.C0298a.f4140D
            int r3 = r1.f1792E
            W2.y r4 = W2.y.f2418a
            r5 = 1
            if (r3 == 0) goto L_0x0033
            if (r3 != r5) goto L_0x002b
            Y1.C0110h.P(r0)
            goto L_0x009c
        L_0x002b:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0033:
            Y1.C0110h.P(r0)
            if (r17 != 0) goto L_0x0039
            return r4
        L_0x0039:
            com.hawkshaw.library.App$Companion r0 = com.hawkshaw.library.App.Companion
            android.content.Context r3 = r0.getContext()
            java.lang.Class<android.os.Vibrator> r6 = android.os.Vibrator.class
            java.lang.Object r3 = E.k.getSystemService(r3, r6)
            android.os.Vibrator r3 = (android.os.Vibrator) r3
            if (r3 == 0) goto L_0x00a9
            boolean r6 = r3.hasVibrator()
            if (r6 != r5) goto L_0x00a9
            long[] r6 = r17.getTimings()
            int[] r7 = r17.getAmplitudes()
            int r8 = r17.getRepeat()
            android.os.VibrationEffect r6 = android.os.VibrationEffect.createWaveform(r6, r7, r8)
            int r7 = android.os.Build.VERSION.SDK_INT
            r8 = 33
            if (r7 < r8) goto L_0x006d
            android.os.VibrationAttributes r7 = android.os.VibrationAttributes.createForUsage(17)
            r3.vibrate(r6, r7)
            goto L_0x007e
        L_0x006d:
            android.media.AudioAttributes$Builder r7 = new android.media.AudioAttributes$Builder
            r7.<init>()
            r8 = 4
            android.media.AudioAttributes$Builder r7 = r7.setUsage(r8)
            android.media.AudioAttributes r7 = r7.build()
            r3.vibrate(r6, r7)
        L_0x007e:
            boolean r3 = r17.getFlash()
            if (r3 == 0) goto L_0x009c
            android.content.Context r0 = r0.getContext()
            com.hawkshaw.library.datalayer.models.Command$FlashRequest r3 = new com.hawkshaw.library.datalayer.models.Command$FlashRequest
            long[] r6 = r17.getTimings()
            r7 = 2
            r8 = 0
            r3.<init>((long[]) r6, (com.hawkshaw.library.datalayer.models.Command.FlashRequest.Facing) r8, (int) r7, (j3.f) r8)
            r1.f1792E = r5
            java.lang.Object r0 = com.hawkshaw.library.features.misc.FlashKt.flash(r0, r3, r1)
            if (r0 != r2) goto L_0x009c
            return r2
        L_0x009c:
            com.hawkshaw.library.logger.Logger r5 = com.hawkshaw.library.logger.Logger.INSTANCE
            java.lang.String r7 = "Successfully vibrated"
            r8 = 0
            java.lang.String r6 = "Vibrator"
            r9 = 4
            r10 = 0
            com.hawkshaw.library.logger.Logger.d$default(r5, r6, r7, r8, r9, r10)
            goto L_0x00b6
        L_0x00a9:
            com.hawkshaw.library.logger.Logger r11 = com.hawkshaw.library.logger.Logger.INSTANCE
            java.lang.String r13 = "This device does not have vibrator"
            r14 = 0
            java.lang.String r12 = "Vibrator"
            r15 = 4
            r16 = 0
            com.hawkshaw.library.logger.Logger.d$default(r11, r12, r13, r14, r15, r16)
        L_0x00b6:
            return r4
        
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.features.misc.VibratorKt.vibrate(com.hawkshaw.library.datalayer.models.Command$VibrateRequest, a3.e):java.lang.Object");
    }
}
