package com.hawkshaw.library.ktextensions;

import Y1.K;
import androidx.core.app.NotificationCompat;
import androidx.room.C0271b;
import i3.l;

public final class LargeMessageLogger {
    public static final LargeMessageLogger INSTANCE = new LargeMessageLogger();

    private LargeMessageLogger() {
    }

    private final void log(String str, l lVar) {
        int length = str.length() / 3950;
        if (length >= 0) {
            int i5 = 0;
            while (true) {
                int i6 = i5 * 3950;
                int i7 = i5 + 1;
                int i8 = i7 * 3950;
                if (i8 > str.length()) {
                    i8 = str.length();
                }
                String substring = str.substring(i6, i8);
                K.m(substring, "substring(...)");
                lVar.invoke(substring);
                if (i5 != length) {
                    i5 = i7;
                } else {
                    return;
                }
            }
        }
    }

    public final void d(String str, String str2) {
        K.n(str, "tag");
        K.n(str2, NotificationCompat.CATEGORY_MESSAGE);
        log(str2, new C0271b(str, 1));
    }

    public final void v(String str, String str2) {
        K.n(str, "tag");
        K.n(str2, NotificationCompat.CATEGORY_MESSAGE);
        log(str2, new C0271b(str, 2));
    }
}
