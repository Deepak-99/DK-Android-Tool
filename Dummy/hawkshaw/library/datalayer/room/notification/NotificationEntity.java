package com.hawkshaw.library.datalayer.room.notification;

import Y1.K;

public final class NotificationEntity {
    private final String extraBigText;
    private final String extraMessage;
    private final String extraText;
    private final String[] extraTextLines;
    private final String extraTitle;
    private int id;
    private final String packageName;
    private final String tickerText;
    private final long timestamp;

    public NotificationEntity(String str, String str2, String str3, String str4, String str5, String[] strArr, String str6, long j5) {
        K.n(str, "packageName");
        this.packageName = str;
        this.extraText = str2;
        this.extraTitle = str3;
        this.extraMessage = str4;
        this.extraBigText = str5;
        this.extraTextLines = strArr;
        this.tickerText = str6;
        this.timestamp = j5;
    }

    public final String getExtraBigText() {
        return this.extraBigText;
    }

    public final String getExtraMessage() {
        return this.extraMessage;
    }

    public final String getExtraText() {
        return this.extraText;
    }

    public final String[] getExtraTextLines() {
        return this.extraTextLines;
    }

    public final String getExtraTitle() {
        return this.extraTitle;
    }

    public final int getId() {
        return this.id;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final String getTickerText() {
        return this.tickerText;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public final void setId(int i5) {
        this.id = i5;
    }
}
