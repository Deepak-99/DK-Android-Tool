package com.hawkshaw.library.datalayer.room.telephony;

import Y1.K;

public final class CallRecordEntity {
    private int id;
    private final String name;
    private final String number;

    public CallRecordEntity(String str, String str2) {
        K.n(str, "name");
        K.n(str2, "number");
        this.name = str;
        this.number = str2;
    }

    public final int getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final String getNumber() {
        return this.number;
    }

    public final void setId(int i5) {
        this.id = i5;
    }
}
