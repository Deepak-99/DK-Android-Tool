package com.hawkshaw.library.datalayer;

import W2.e;
import W2.l;
import com.hawkshaw.library.datalayer.network.service.AccessibilityService;
import com.hawkshaw.library.datalayer.network.service.AppConfigService;
import com.hawkshaw.library.datalayer.network.service.AppService;
import com.hawkshaw.library.datalayer.network.service.AuthService;
import com.hawkshaw.library.datalayer.network.service.FileService;
import com.hawkshaw.library.datalayer.network.service.LocationService;
import com.hawkshaw.library.datalayer.network.service.LogsService;
import com.hawkshaw.library.datalayer.network.service.MediaService;
import com.hawkshaw.library.datalayer.network.service.MiscService;
import com.hawkshaw.library.datalayer.network.service.TelephonyService;
import j3.f;
import y1.C1094a;

public final class Injector {
    public static final Companion Companion = new Companion((f) null);
    /* access modifiers changed from: private */
    public static final Injector instance = new Injector();
    private final e accessibilityService$delegate = new l(C1094a.f9912E);
    private final e appConfig$delegate = new l(C1094a.f9913F);
    private final e appService$delegate = new l(C1094a.f9914G);
    private final e authService$delegate = new l(C1094a.f9915H);
    private final e fileService$delegate = new l(C1094a.f9916I);
    private final e locationService$delegate = new l(C1094a.f9917J);
    private final e logsService$delegate = new l(C1094a.f9918K);
    private final e mediaService$delegate = new l(C1094a.f9919L);
    private final e miscService$delegate = new l(C1094a.f9920M);
    private final e telephonyService$delegate = new l(C1094a.f9921N);

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(f fVar) {
            this();
        }

        public final Injector getInstance() {
            return Injector.instance;
        }
    }

    public final AccessibilityService getAccessibilityService() {
        return (AccessibilityService) this.accessibilityService$delegate.getValue();
    }

    public final AppConfigService getAppConfig() {
        return (AppConfigService) this.appConfig$delegate.getValue();
    }

    public final AppService getAppService() {
        return (AppService) this.appService$delegate.getValue();
    }

    public final AuthService getAuthService() {
        return (AuthService) this.authService$delegate.getValue();
    }

    public final FileService getFileService() {
        return (FileService) this.fileService$delegate.getValue();
    }

    public final LocationService getLocationService() {
        return (LocationService) this.locationService$delegate.getValue();
    }

    public final LogsService getLogsService() {
        return (LogsService) this.logsService$delegate.getValue();
    }

    public final MediaService getMediaService() {
        return (MediaService) this.mediaService$delegate.getValue();
    }

    public final MiscService getMiscService() {
        return (MiscService) this.miscService$delegate.getValue();
    }

    public final TelephonyService getTelephonyService() {
        return (TelephonyService) this.telephonyService$delegate.getValue();
    }
}
