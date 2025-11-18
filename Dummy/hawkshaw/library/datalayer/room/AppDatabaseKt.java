package com.hawkshaw.library.datalayer.room;

import B1.a;
import W2.e;
import W2.l;

public final class AppDatabaseKt {
    private static final e db$delegate = new l(a.f174D);

    public static final AppDatabase getDb() {
        return (AppDatabase) db$delegate.getValue();
    }
}
