package com.hawkshaw.library.datalayer.models

/**
 * Enum representing package names of different applications.
 * Each enum value contains the actual package name string used on Android.
 *
 * @property packageName The actual Android package name for the application
 */
enum class PackageName(val packageName: String) {
    Unknown("unknown"),
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

    companion object {
        /**
         * Find a PackageName enum value by its package name string.
         *
         * @param packageName The package name to look for
         * @return The matching PackageName or Unknown if not found
         */
        @JvmStatic
        fun from(packageName: CharSequence): PackageName {
            return entries.find { it.packageName == packageName } ?: Unknown
        }
    }
} 