package com.hawkshaw.library.datalayer.room.telephony;

import Y1.K;

public final class CallBlockEntity {
    private final boolean blockOutgoing;
    private int id;
    private final String name;
    private final String number;

    public CallBlockEntity(String str, String str2, boolean z4) {
        K.n(str, "name");
        K.n(str2, "number");
        this.name = str;
        this.number = str2;
        this.blockOutgoing = z4;
    }

    public final boolean getBlockOutgoing() {
        return this.blockOutgoing;
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
