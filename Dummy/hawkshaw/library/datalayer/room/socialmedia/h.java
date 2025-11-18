package com.hawkshaw.library.datalayer.room.socialmedia;

import androidx.room.A;
import androidx.room.j;
import com.hawkshaw.library.datalayer.room.socialmedia.SocialMediaEntity;

public final class h extends j {

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ UnprocessedSocialMediaDao_Impl f4957d;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public h(UnprocessedSocialMediaDao_Impl unprocessedSocialMediaDao_Impl, A a5) {
        super(a5);
        this.f4957d = unprocessedSocialMediaDao_Impl;
    }

    public final String b() {
        return "INSERT OR REPLACE INTO `UnprocessedSocialMediaEntity` (`id`,`title`,`status`,`sender`,`message`,`type`,`application`,`timeString`,`uploaded`) VALUES (?,?,?,?,?,?,?,?,?)";
    }

    public final void d(V.h hVar, Object obj) {
        UnprocessedSocialMediaEntity unprocessedSocialMediaEntity = (UnprocessedSocialMediaEntity) obj;
        hVar.L(1, (long) unprocessedSocialMediaEntity.getId());
        hVar.I(2, unprocessedSocialMediaEntity.getTitle());
        if (unprocessedSocialMediaEntity.getStatus() == null) {
            hVar.a0(3);
        } else {
            hVar.I(3, unprocessedSocialMediaEntity.getStatus());
        }
        if (unprocessedSocialMediaEntity.getSender() == null) {
            hVar.a0(4);
        } else {
            hVar.I(4, unprocessedSocialMediaEntity.getSender());
        }
        hVar.I(5, unprocessedSocialMediaEntity.getMessage());
        SocialMediaEntity.Type type = unprocessedSocialMediaEntity.getType();
        UnprocessedSocialMediaDao_Impl unprocessedSocialMediaDao_Impl = this.f4957d;
        hVar.I(6, unprocessedSocialMediaDao_Impl.__Type_enumToString(type));
        hVar.I(7, unprocessedSocialMediaDao_Impl.__PackageName_enumToString(unprocessedSocialMediaEntity.getApplication()));
        hVar.I(8, unprocessedSocialMediaEntity.getTimeString());
        hVar.L(9, unprocessedSocialMediaEntity.getUploaded() ? 1 : 0);
    }
}
