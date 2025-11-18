package com.hawkshaw.library.features.accessibility;

import X2.o;
import Y1.K;
import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import i3.C0542a;
import i3.l;
import java.util.ArrayList;
import java.util.List;

public final class AccessibilityUtilsKt {
    public static final List<AccessibilityNodeInfo> find(AccessibilityNodeInfo accessibilityNodeInfo, String str) {
        K.n(accessibilityNodeInfo, "<this>");
        K.n(str, "id");
        return accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(str);
    }

    public static final boolean findAndClick(AccessibilityNodeInfo accessibilityNodeInfo, String str) {
        K.n(accessibilityNodeInfo, "<this>");
        K.n(str, "id");
        Thread.sleep(200);
        AccessibilityNodeInfo findFirst = findFirst(accessibilityNodeInfo, str);
        if (findFirst != null) {
            return findFirst.performAction(16);
        }
        return false;
    }

    public static final boolean findAndTypeText(AccessibilityNodeInfo accessibilityNodeInfo, String str, String str2) {
        K.n(accessibilityNodeInfo, "<this>");
        K.n(str, "id");
        K.n(str2, "text");
        Thread.sleep(200);
        AccessibilityNodeInfo findFirst = findFirst(accessibilityNodeInfo, str);
        if (findFirst == null) {
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putCharSequence("ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE", str2);
        Thread.sleep(200);
        return findFirst.performAction(2097152, bundle);
    }

    public static final AccessibilityNodeInfo findBy(AccessibilityNodeInfo accessibilityNodeInfo, l lVar) {
        K.n(accessibilityNodeInfo, "<this>");
        K.n(lVar, "predicate");
        if (((Boolean) lVar.invoke(accessibilityNodeInfo)).booleanValue()) {
            return accessibilityNodeInfo;
        }
        int childCount = accessibilityNodeInfo.getChildCount();
        int i5 = 0;
        while (true) {
            AccessibilityNodeInfo accessibilityNodeInfo2 = null;
            if (i5 >= childCount) {
                return null;
            }
            AccessibilityNodeInfo childSafe = getChildSafe(accessibilityNodeInfo, i5);
            if (childSafe != null) {
                accessibilityNodeInfo2 = findBy(childSafe, lVar);
            }
            if (accessibilityNodeInfo2 != null) {
                return accessibilityNodeInfo2;
            }
            i5++;
        }
    }

    public static final List<AccessibilityNodeInfo> findByClass(AccessibilityNodeInfo accessibilityNodeInfo, String str, int i5) {
        K.n(accessibilityNodeInfo, "<this>");
        K.n(str, "classname");
        ArrayList arrayList = new ArrayList();
        findByClass$travel(str, arrayList, i5, accessibilityNodeInfo);
        return arrayList;
    }

    public static /* synthetic */ List findByClass$default(AccessibilityNodeInfo accessibilityNodeInfo, String str, int i5, int i6, Object obj) {
        if ((i6 & 2) != 0) {
            i5 = 1;
        }
        return findByClass(accessibilityNodeInfo, str, i5);
    }

    private static final void findByClass$travel(String str, ArrayList<AccessibilityNodeInfo> arrayList, int i5, AccessibilityNodeInfo accessibilityNodeInfo) {
        if (accessibilityNodeInfo != null) {
            if (K.f(accessibilityNodeInfo.getClassName(), str)) {
                arrayList.add(accessibilityNodeInfo);
                if (i5 > 0 && arrayList.size() >= i5) {
                    return;
                }
            }
            int childCount = accessibilityNodeInfo.getChildCount();
            int i6 = 0;
            while (i6 < childCount) {
                if (i5 <= 0 || arrayList.size() < i5) {
                    findByClass$travel(str, arrayList, i5, accessibilityNodeInfo.getChild(i6));
                    i6++;
                } else {
                    return;
                }
            }
        }
    }

    public static final AccessibilityNodeInfo findFirst(AccessibilityNodeInfo accessibilityNodeInfo, String str) {
        K.n(accessibilityNodeInfo, "<this>");
        K.n(str, "id");
        List<AccessibilityNodeInfo> find = find(accessibilityNodeInfo, str);
        if (find != null) {
            return (AccessibilityNodeInfo) o.T0(find);
        }
        return null;
    }

    public static final AccessibilityNodeInfo getChildSafe(AccessibilityNodeInfo accessibilityNodeInfo, int i5) {
        K.n(accessibilityNodeInfo, "<this>");
        try {
            return accessibilityNodeInfo.getChild(i5);
        } catch (Exception unused) {
            return null;
        }
    }

    public static final boolean ifFalse(boolean z4, C0542a aVar) {
        K.n(aVar, "predicate");
        if (z4) {
            return true;
        }
        return ((Boolean) aVar.invoke()).booleanValue();
    }
}
