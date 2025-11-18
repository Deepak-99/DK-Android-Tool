package com.hawkshaw.library.datalayer.room.socialmedia;

import V.h;
import androidx.room.A;
import androidx.room.i;
import com.hawkshaw.library.datalayer.room.socialmedia.SocialMediaEntity;

public final class d extends i {

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ SocialMediaDao_Impl f4950d;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public d(SocialMediaDao_Impl socialMediaDao_Impl, A a5) {
        super(a5);
        this.f4950d = socialMediaDao_Impl;
    }

    public final String b() {
        return "UPDATE OR IGNORE `SocialMediaEntity` SET `ccn` = ?,`ccs` = ?,`sender` = ?,`message` = ?,`type` = ?,`timestamp` = ?,`application` = ?,`time` = ? WHERE `message` = ? AND `application` = ? AND `time` = ?";
    }

    public final void d(h hVar, Object obj) {
        SocialMediaEntity socialMediaEntity = (SocialMediaEntity) obj;
        hVar.I(1, socialMediaEntity.getCcn());
        if (socialMediaEntity.getCcs() == null) {
            hVar.a0(2);
        } else {
            hVar.I(2, socialMediaEntity.getCcs());
        }
        if (socialMediaEntity.getSender() == null) {
            hVar.a0(3);
        } else {
            hVar.I(3, socialMediaEntity.getSender());
        }
        hVar.I(4, socialMediaEntity.getMessage());
        SocialMediaEntity.Type type = socialMediaEntity.getType();
        SocialMediaDao_Impl socialMediaDao_Impl = this.f4950d;
        hVar.I(5, socialMediaDao_Impl.__Type_enumToString(type));
        if (socialMediaEntity.getTimestamp() == null) {
            hVar.a0(6);
        } else {
            hVar.L(6, socialMediaEntity.getTimestamp().longValue());
        }
        hVar.I(7, socialMediaDao_Impl.__PackageName_enumToString(socialMediaEntity.getApplication()));
        hVar.I(8, socialMediaEntity.getTime());
        hVar.I(9, socialMediaEntity.getMessage());
        hVar.I(10, socialMediaDao_Impl.__PackageName_enumToString(socialMediaEntity.getApplication()));
        hVar.I(11, socialMediaEntity.getTime());
    }
}
