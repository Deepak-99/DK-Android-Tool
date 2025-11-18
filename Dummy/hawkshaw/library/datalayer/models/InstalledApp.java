package com.hawkshaw.library.datalayer.models;

import B3.f;
import D3.b;
import E3.q0;
import E3.u0;
import W2.e;
import Y1.C0110h;
import Y1.K;
import androidx.annotation.Keep;
import d3.C0393a;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import p3.q;
import w3.w;

@f
public final class InstalledApp {
    /* access modifiers changed from: private */
    public static final KSerializer[] $childSerializers = {null, null, null, null, null, null, Category.Companion.serializer(), null};
    public static final Companion Companion = new Companion((j3.f) null);
    private final Category category;
    private final boolean enabled;
    private final long firstInstallTime;
    private final boolean isSystemApp;
    private final String name;
    private final String packageName;
    private final long versionCode;
    private final String versionName;

    @Keep
    @f
    public enum Category {
        UNDEFINED,
        GAME,
        AUDIO,
        VIDEO,
        IMAGE,
        SOCIAL,
        NEWS,
        MAPS,
        PRODUCTIVITY,
        ACCESSIBILITY;
        
        /* access modifiers changed from: private */
        public static final e $cachedSerializer$delegate = null;
        public static final Companion Companion = null;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(j3.f fVar) {
                this();
            }

            private final /* synthetic */ KSerializer get$cachedSerializer() {
                return (KSerializer) Category.$cachedSerializer$delegate.getValue();
            }

            public final KSerializer serializer() {
                return get$cachedSerializer();
            }
        }

        static {
            Category[] $values;
            $ENTRIES = K.J($values);
            Companion = new Companion((j3.f) null);
            $cachedSerializer$delegate = C0110h.y(W2.f.f2390D, t.f4904D);
        }

        public static C0393a getEntries() {
            return $ENTRIES;
        }
    }

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(j3.f fVar) {
            this();
        }

        public final KSerializer serializer() {
            return InstalledApp$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ InstalledApp(int i5, String str, String str2, String str3, long j5, long j6, boolean z4, Category category2, boolean z5, q0 q0Var) {
        if (255 == (i5 & 255)) {
            this.name = str;
            this.packageName = str2;
            this.versionName = str3;
            this.versionCode = j5;
            this.firstInstallTime = j6;
            this.isSystemApp = z4;
            this.category = category2;
            this.enabled = z5;
            return;
        }
        w.x(i5, 255, InstalledApp$$serializer.INSTANCE.getDescriptor());
        throw null;
    }

    public static /* synthetic */ void getCategory$annotations() {
    }

    public static /* synthetic */ void getEnabled$annotations() {
    }

    public static /* synthetic */ void getFirstInstallTime$annotations() {
    }

    public static /* synthetic */ void getName$annotations() {
    }

    public static /* synthetic */ void getPackageName$annotations() {
    }

    public static /* synthetic */ void getVersionCode$annotations() {
    }

    public static /* synthetic */ void getVersionName$annotations() {
    }

    public static /* synthetic */ void isSystemApp$annotations() {
    }

    public static final /* synthetic */ void write$Self$library_release(InstalledApp installedApp, b bVar, SerialDescriptor serialDescriptor) {
        KSerializer[] kSerializerArr = $childSerializers;
        q qVar = (q) bVar;
        qVar.f0(serialDescriptor, 0, installedApp.name);
        qVar.f0(serialDescriptor, 1, installedApp.packageName);
        qVar.s(serialDescriptor, 2, u0.f536a, installedApp.versionName);
        qVar.d0(serialDescriptor, 3, installedApp.versionCode);
        qVar.d0(serialDescriptor, 4, installedApp.firstInstallTime);
        qVar.X(serialDescriptor, 5, installedApp.isSystemApp);
        qVar.e0(serialDescriptor, 6, kSerializerArr[6], installedApp.category);
        qVar.X(serialDescriptor, 7, installedApp.enabled);
    }

    public final Category getCategory() {
        return this.category;
    }

    public final boolean getEnabled() {
        return this.enabled;
    }

    public final long getFirstInstallTime() {
        return this.firstInstallTime;
    }

    public final String getName() {
        return this.name;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final long getVersionCode() {
        return this.versionCode;
    }

    public final String getVersionName() {
        return this.versionName;
    }

    public final boolean isSystemApp() {
        return this.isSystemApp;
    }

    public InstalledApp(String str, String str2, String str3, long j5, long j6, boolean z4, Category category2, boolean z5) {
        K.n(str, "name");
        K.n(str2, "packageName");
        K.n(category2, "category");
        this.name = str;
        this.packageName = str2;
        this.versionName = str3;
        this.versionCode = j5;
        this.firstInstallTime = j6;
        this.isSystemApp = z4;
        this.category = category2;
        this.enabled = z5;
    }
}
