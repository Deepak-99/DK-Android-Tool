package com.hawkshaw.library.features.accessibility.socialmedia;

import Y1.K;
import android.os.Build;
import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;
import me.pushy.sdk.lib.jackson.databind.a;
import r3.j;

public final class Researcher {
    public static final Researcher INSTANCE = new Researcher();
    private static final String TAG = "Researcher";

    private Researcher() {
    }

    private final String dash(int i5) {
        return j.c0("-", i5 * 2);
    }

    private final String getEmpty(String str) {
        return (str == null || j.U(str)) ? "" : str;
    }

    public static /* synthetic */ void printTree$default(Researcher researcher, AccessibilityNodeInfo accessibilityNodeInfo, int i5, int i6, Object obj) {
        if ((i6 & 2) != 0) {
            i5 = 0;
        }
        researcher.printTree(accessibilityNodeInfo, i5);
    }

    public final void print(String... strArr) {
        K.n(strArr, "s");
        for (String v4 : strArr) {
            Log.v(TAG, v4);
        }
    }

    public final void printTree(AccessibilityNodeInfo accessibilityNodeInfo, int i5) {
        if (accessibilityNodeInfo == null) {
            print("printTree-null source");
            return;
        }
        String viewIdResourceName = accessibilityNodeInfo.getViewIdResourceName();
        CharSequence text = accessibilityNodeInfo.getText();
        String empty = getEmpty(accessibilityNodeInfo.getClassName());
        String empty2 = getEmpty(accessibilityNodeInfo.getContentDescription());
        String empty3 = getEmpty(accessibilityNodeInfo.getHintText());
        int i6 = Build.VERSION.SDK_INT;
        String str = "";
        String empty4 = i6 >= 28 ? getEmpty(accessibilityNodeInfo.getPaneTitle()) : str;
        if (i6 >= 28) {
            str = getEmpty(accessibilityNodeInfo.getTooltipText());
        }
        String dash = dash(i5);
        StringBuilder sb = new StringBuilder();
        sb.append(dash);
        sb.append("-> ");
        sb.append(viewIdResourceName);
        sb.append(", ");
        sb.append(text);
        sb.append(", ");
        sb.append(empty);
        sb.append(", ");
        sb.append(empty2);
        sb.append(", ");
        sb.append(empty3);
        sb.append(", ");
        sb.append(empty4);
        print(a.i(sb, ", ", str));
        int childCount = accessibilityNodeInfo.getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            printTree(accessibilityNodeInfo.getChild(i7), i5 + 1);
        }
    }

    private final String getEmpty(CharSequence charSequence) {
        return (charSequence == null || j.U(charSequence)) ? "" : charSequence.toString();
    }

    public final void print(CharSequence... charSequenceArr) {
        K.n(charSequenceArr, "s");
        for (CharSequence empty : charSequenceArr) {
            Log.v(TAG, INSTANCE.getEmpty(empty));
        }
    }
}
