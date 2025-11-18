package com.hawkshaw.library;

import Y1.K;
import b.C0279b;
import b.C0280c;
import j3.g;
import j3.i;
import java.util.Map;

public final /* synthetic */ class k implements C0280c, g {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f5046a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ HawkshawInitializer f5047b;

    public /* synthetic */ k(HawkshawInitializer hawkshawInitializer, int i5) {
        this.f5046a = i5;
        this.f5047b = hawkshawInitializer;
    }

    public final i a() {
        switch (this.f5046a) {
            case 0:
                return new i(1, (Object) this.f5047b, HawkshawInitializer.class, "onAccessibilityResult", "onAccessibilityResult(Landroidx/activity/result/ActivityResult;)V");
            case 1:
                return new i(1, (Object) this.f5047b, HawkshawInitializer.class, "onRequestPermissionResult", "onRequestPermissionResult(Ljava/util/Map;)V");
            default:
                return new i(1, (Object) this.f5047b, HawkshawInitializer.class, "onSystemOverlayResult", "onSystemOverlayResult(Landroidx/activity/result/ActivityResult;)V");
        }
    }

    public final void b(Object obj) {
        HawkshawInitializer hawkshawInitializer = this.f5047b;
        int i5 = this.f5046a;
        switch (i5) {
            case 0:
                C0279b bVar = (C0279b) obj;
                switch (i5) {
                    case 0:
                        hawkshawInitializer.onAccessibilityResult(bVar);
                        return;
                    default:
                        hawkshawInitializer.onSystemOverlayResult(bVar);
                        return;
                }
            case 1:
                Map map = (Map) obj;
                K.n(map, "p0");
                hawkshawInitializer.onRequestPermissionResult(map);
                return;
            default:
                C0279b bVar2 = (C0279b) obj;
                switch (i5) {
                    case 0:
                        hawkshawInitializer.onAccessibilityResult(bVar2);
                        return;
                    default:
                        hawkshawInitializer.onSystemOverlayResult(bVar2);
                        return;
                }
        }
    }

    public final boolean equals(Object obj) {
        switch (this.f5046a) {
            case 0:
                if (!(obj instanceof C0280c) || !(obj instanceof g)) {
                    return false;
                }
                return K.f(a(), ((k) ((g) obj)).a());
            case 1:
                if (!(obj instanceof C0280c) || !(obj instanceof g)) {
                    return false;
                }
                return K.f(a(), ((k) ((g) obj)).a());
            default:
                if (!(obj instanceof C0280c) || !(obj instanceof g)) {
                    return false;
                }
                return K.f(a(), ((k) ((g) obj)).a());
        }
    }

    public final int hashCode() {
        switch (this.f5046a) {
            case 0:
                return a().hashCode();
            case 1:
                return a().hashCode();
            default:
                return a().hashCode();
        }
    }
}
