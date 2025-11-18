package com.hawkshaw.library.datalayer.room.socialmedia;

import Y1.K;
import com.hawkshaw.library.datalayer.models.PackageName;
import com.hawkshaw.library.datalayer.room.socialmedia.SocialMediaEntity;
import j3.f;

public final class UnprocessedSocialMediaEntity {
    private final PackageName application;
    private final int id;
    private final String message;
    private final String sender;
    private final String status;
    private final String timeString;
    private final String title;
    private final SocialMediaEntity.Type type;
    private final boolean uploaded;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ UnprocessedSocialMediaEntity(int i5, String str, String str2, String str3, String str4, SocialMediaEntity.Type type2, PackageName packageName, String str5, boolean z4, int i6, f fVar) {
        this(i5, str, str2, str3, str4, type2, packageName, str5, (i6 & 256) != 0 ? false : z4);
    }

    public static /* synthetic */ UnprocessedSocialMediaEntity copy$default(UnprocessedSocialMediaEntity unprocessedSocialMediaEntity, int i5, String str, String str2, String str3, String str4, SocialMediaEntity.Type type2, PackageName packageName, String str5, boolean z4, int i6, Object obj) {
        UnprocessedSocialMediaEntity unprocessedSocialMediaEntity2 = unprocessedSocialMediaEntity;
        int i7 = i6;
        return unprocessedSocialMediaEntity.copy((i7 & 1) != 0 ? unprocessedSocialMediaEntity2.id : i5, (i7 & 2) != 0 ? unprocessedSocialMediaEntity2.title : str, (i7 & 4) != 0 ? unprocessedSocialMediaEntity2.status : str2, (i7 & 8) != 0 ? unprocessedSocialMediaEntity2.sender : str3, (i7 & 16) != 0 ? unprocessedSocialMediaEntity2.message : str4, (i7 & 32) != 0 ? unprocessedSocialMediaEntity2.type : type2, (i7 & 64) != 0 ? unprocessedSocialMediaEntity2.application : packageName, (i7 & 128) != 0 ? unprocessedSocialMediaEntity2.timeString : str5, (i7 & 256) != 0 ? unprocessedSocialMediaEntity2.uploaded : z4);
    }

    public final int component1() {
        return this.id;
    }

    public final String component2() {
        return this.title;
    }

    public final String component3() {
        return this.status;
    }

    public final String component4() {
        return this.sender;
    }

    public final String component5() {
        return this.message;
    }

    public final SocialMediaEntity.Type component6() {
        return this.type;
    }

    public final PackageName component7() {
        return this.application;
    }

    public final String component8() {
        return this.timeString;
    }

    public final boolean component9() {
        return this.uploaded;
    }

    public final UnprocessedSocialMediaEntity copy(int i5, String str, String str2, String str3, String str4, SocialMediaEntity.Type type2, PackageName packageName, String str5, boolean z4) {
        K.n(str, "title");
        String str6 = str4;
        K.n(str6, "message");
        SocialMediaEntity.Type type3 = type2;
        K.n(type3, "type");
        PackageName packageName2 = packageName;
        K.n(packageName2, "application");
        String str7 = str5;
        K.n(str7, "timeString");
        return new UnprocessedSocialMediaEntity(i5, str, str2, str3, str6, type3, packageName2, str7, z4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!K.f(UnprocessedSocialMediaEntity.class, obj != null ? obj.getClass() : null)) {
            return false;
        }
        K.l(obj, "null cannot be cast to non-null type com.hawkshaw.library.datalayer.room.socialmedia.UnprocessedSocialMediaEntity");
        UnprocessedSocialMediaEntity unprocessedSocialMediaEntity = (UnprocessedSocialMediaEntity) obj;
        if (this.id == unprocessedSocialMediaEntity.id && K.f(this.title, unprocessedSocialMediaEntity.title) && K.f(this.status, unprocessedSocialMediaEntity.status) && K.f(this.sender, unprocessedSocialMediaEntity.sender) && K.f(this.message, unprocessedSocialMediaEntity.message) && this.type == unprocessedSocialMediaEntity.type && this.application == unprocessedSocialMediaEntity.application) {
            return K.f(this.timeString, unprocessedSocialMediaEntity.timeString);
        }
        return false;
    }

    public final PackageName getApplication() {
        return this.application;
    }

    public final int getId() {
        return this.id;
    }

    public final String getMessage() {
        return this.message;
    }

    public final String getSender() {
        return this.sender;
    }

    public final String getStatus() {
        return this.status;
    }

    public final String getTimeString() {
        return this.timeString;
    }

    public final String getTitle() {
        return this.title;
    }

    public final SocialMediaEntity.Type getType() {
        return this.type;
    }

    public final boolean getUploaded() {
        return this.uploaded;
    }

    public int hashCode() {
        int hashCode = (this.title.hashCode() + (this.id * 31)) * 31;
        String str = this.status;
        int i5 = 0;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.sender;
        if (str2 != null) {
            i5 = str2.hashCode();
        }
        int hashCode3 = this.message.hashCode();
        int hashCode4 = this.type.hashCode();
        return this.timeString.hashCode() + ((this.application.hashCode() + ((hashCode4 + ((hashCode3 + ((hashCode2 + i5) * 31)) * 31)) * 31)) * 31);
    }

    public String toString() {
        int i5 = this.id;
        String str = this.title;
        String str2 = this.status;
        String str3 = this.sender;
        String str4 = this.message;
        SocialMediaEntity.Type type2 = this.type;
        PackageName packageName = this.application;
        String str5 = this.timeString;
        boolean z4 = this.uploaded;
        return "UnprocessedSocialMediaEntity(id=" + i5 + ", title=" + str + ", status=" + str2 + ", sender=" + str3 + ", message=" + str4 + ", type=" + type2 + ", application=" + packageName + ", timeString=" + str5 + ", uploaded=" + z4 + ")";
    }

    public UnprocessedSocialMediaEntity(int i5, String str, String str2, String str3, String str4, SocialMediaEntity.Type type2, PackageName packageName, String str5, boolean z4) {
        K.n(str, "title");
        K.n(str4, "message");
        K.n(type2, "type");
        K.n(packageName, "application");
        K.n(str5, "timeString");
        this.id = i5;
        this.title = str;
        this.status = str2;
        this.sender = str3;
        this.message = str4;
        this.type = type2;
        this.application = packageName;
        this.timeString = str5;
        this.uploaded = z4;
    }
}
