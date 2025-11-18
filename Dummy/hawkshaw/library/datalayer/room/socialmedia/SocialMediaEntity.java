package com.hawkshaw.library.datalayer.room.socialmedia;

import Y1.K;
import com.hawkshaw.library.datalayer.models.PackageName;
import d3.C0393a;
import j3.f;

public final class SocialMediaEntity {
    private final PackageName application;
    private final String ccn;
    private final String ccs;
    private final String message;
    private final String sender;
    private final String time;
    private final Long timestamp;
    private final Type type;

    public enum Type {
        Unknown,
        Sent,
        Received;

        static {
            Type[] $values;
            $ENTRIES = K.J($values);
        }

        public static C0393a getEntries() {
            return $ENTRIES;
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ SocialMediaEntity(String str, String str2, String str3, String str4, Type type2, Long l5, PackageName packageName, String str5, int i5, f fVar) {
        this(str, str2, str3, str4, type2, l5, packageName, (i5 & 128) != 0 ? "" : str5);
    }

    public final PackageName getApplication() {
        return this.application;
    }

    public final String getCcn() {
        return this.ccn;
    }

    public final String getCcs() {
        return this.ccs;
    }

    public final String getMessage() {
        return this.message;
    }

    public final String getSender() {
        return this.sender;
    }

    public final String getTime() {
        return this.time;
    }

    public final Long getTimestamp() {
        return this.timestamp;
    }

    public final Type getType() {
        return this.type;
    }

    public SocialMediaEntity(String str, String str2, String str3, String str4, Type type2, Long l5, PackageName packageName, String str5) {
        K.n(str, "ccn");
        K.n(str4, "message");
        K.n(type2, "type");
        K.n(packageName, "application");
        K.n(str5, "time");
        this.ccn = str;
        this.ccs = str2;
        this.sender = str3;
        this.message = str4;
        this.type = type2;
        this.timestamp = l5;
        this.application = packageName;
        this.time = str5;
    }
}
