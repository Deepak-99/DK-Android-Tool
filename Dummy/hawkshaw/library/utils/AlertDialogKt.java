package com.hawkshaw.library.utils;

import Y1.K;
import a3.e;
import a3.j;
import android.app.AlertDialog;
import android.content.DialogInterface;
import androidx.activity.o;
import com.hawkshaw.library.ktextensions.CoroutineKt;
import i3.l;
import o.C0769d;

public final class AlertDialogKt {
    public static final void showAlertDialog(o oVar, String str, String str2, l lVar, l lVar2) {
        K.n(oVar, "activity");
        K.n(str, "title");
        K.n(str2, "message");
        K.n(lVar, "onNegativeButtonClick");
        K.n(lVar2, "onPositiveButtonClick");
        AlertDialog.Builder builder = new AlertDialog.Builder(oVar);
        builder.setCancelable(false);
        builder.setTitle(str);
        builder.setMessage(str2);
        builder.setPositiveButton("OK", new a(0, lVar2));
        builder.setNegativeButton("Cancel", new a(1, lVar));
        CoroutineKt.safeLaunch$default(C0769d.R(oVar), (j) null, new c(oVar, builder, (e) null), 1, (Object) null);
    }

    public static /* synthetic */ void showAlertDialog$default(o oVar, String str, String str2, l lVar, l lVar2, int i5, Object obj) {
        if ((i5 & 8) != 0) {
            lVar = b.f5070D;
        }
        showAlertDialog(oVar, str, str2, lVar, lVar2);
    }

    /* access modifiers changed from: private */
    public static final void showAlertDialog$lambda$2$lambda$0(l lVar, DialogInterface dialogInterface, int i5) {
        K.n(lVar, "$onPositiveButtonClick");
        K.k(dialogInterface);
        lVar.invoke(dialogInterface);
    }

    /* access modifiers changed from: private */
    public static final void showAlertDialog$lambda$2$lambda$1(l lVar, DialogInterface dialogInterface, int i5) {
        K.n(lVar, "$onNegativeButtonClick");
        K.k(dialogInterface);
        lVar.invoke(dialogInterface);
    }
}
