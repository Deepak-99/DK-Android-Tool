package com.hawkshaw.library.utils;

import android.content.DialogInterface;
import i3.l;

public final /* synthetic */ class a implements DialogInterface.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f5068a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ l f5069b;

    public /* synthetic */ a(int i5, l lVar) {
        this.f5068a = i5;
        this.f5069b = lVar;
    }

    public final void onClick(DialogInterface dialogInterface, int i5) {
        int i6 = this.f5068a;
        l lVar = this.f5069b;
        switch (i6) {
            case 0:
                AlertDialogKt.showAlertDialog$lambda$2$lambda$0(lVar, dialogInterface, i5);
                return;
            default:
                AlertDialogKt.showAlertDialog$lambda$2$lambda$1(lVar, dialogInterface, i5);
                return;
        }
    }
}
