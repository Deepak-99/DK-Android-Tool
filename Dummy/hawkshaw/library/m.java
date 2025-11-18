package com.hawkshaw.library;

import W2.y;
import Y1.K;
import a3.e;
import android.content.DialogInterface;
import android.content.Intent;
import b.C0279b;
import com.hawkshaw.library.ktextensions.CoroutineKt;
import i3.l;
import j3.j;
import o.C0769d;

public final class m extends j implements l {

    /* renamed from: D  reason: collision with root package name */
    public final /* synthetic */ int f5049D;

    /* renamed from: E  reason: collision with root package name */
    public final /* synthetic */ HawkshawInitializer f5050E;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public /* synthetic */ m(HawkshawInitializer hawkshawInitializer, int i5) {
        super(1);
        this.f5049D = i5;
        this.f5050E = hawkshawInitializer;
    }

    public final void a(DialogInterface dialogInterface) {
        int i5 = this.f5049D;
        HawkshawInitializer hawkshawInitializer = this.f5050E;
        switch (i5) {
            case 0:
                K.n(dialogInterface, "it");
                CoroutineKt.safeLaunch$default(C0769d.R(hawkshawInitializer.activity), (a3.j) null, new l(dialogInterface, (e) null), 1, (Object) null);
                hawkshawInitializer.onAccessibilityResult((C0279b) null);
                return;
            default:
                K.n(dialogInterface, "it");
                Intent intent = new Intent("android.settings.ACCESSIBILITY_SETTINGS");
                intent.putExtra("android.provider.extra.APP_PACKAGE", hawkshawInitializer.activity.getPackageName());
                hawkshawInitializer.accessibilityResultLauncher.a(intent);
                return;
        }
    }

    public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
        y yVar = y.f2418a;
        switch (this.f5049D) {
            case 0:
                a((DialogInterface) obj);
                return yVar;
            default:
                a((DialogInterface) obj);
                return yVar;
        }
    }
}
