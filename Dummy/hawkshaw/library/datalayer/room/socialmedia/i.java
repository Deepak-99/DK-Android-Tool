package com.hawkshaw.library.datalayer.room.socialmedia;

import V.h;
import androidx.room.A;
import com.hawkshaw.library.datalayer.room.socialmedia.SocialMediaEntity;

public final class i extends androidx.room.i {

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ UnprocessedSocialMediaDao_Impl f4958d;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public i(UnprocessedSocialMediaDao_Impl unprocessedSocialMediaDao_Impl, A a5) {
        super(a5);
        this.f4958d = unprocessedSocialMediaDao_Impl;
    }

    public final String b() {
        return "UPDATE OR IGNORE `UnprocessedSocialMediaEntity` SET `id` = ?,`title` = ?,`status` = ?,`sender` = ?,`message` = ?,`type` = ?,`application` = ?,`timeString` = ?,`uploaded` = ? WHERE `id` = ?";
    }

    public final void d(h hVar, Object obj) {
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
        UnprocessedSocialMediaDao_Impl unprocessedSocialMediaDao_Impl = this.f4958d;
        hVar.I(6, unprocessedSocialMediaDao_Impl.__Type_enumToString(type));
        hVar.I(7, unprocessedSocialMediaDao_Impl.__PackageName_enumToString(unprocessedSocialMediaEntity.getApplication()));
        hVar.I(8, unprocessedSocialMediaEntity.getTimeString());
        hVar.L(9, unprocessedSocialMediaEntity.getUploaded() ? 1 : 0);
        hVar.L(10, (long) unprocessedSocialMediaEntity.getId());
    }
}
