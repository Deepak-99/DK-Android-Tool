package com.hawkshaw.library.ktextensions;

import X2.k;
import X2.o;
import Y1.K;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public final class CollectionsKt {
    public static final <T> List<T> nullIfEmpty(List<? extends T> list) {
        Collection collection = list;
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        return list;
    }

    public static final <T> T[] plus(T[] tArr, T... tArr2) {
        K.n(tArr, "<this>");
        K.n(tArr2, "elements");
        int length = tArr.length;
        int length2 = tArr2.length;
        T[] copyOf = Arrays.copyOf(tArr, length + length2);
        System.arraycopy(tArr2, 0, copyOf, length, length2);
        K.k(copyOf);
        return copyOf;
    }

    public static final <T> Collection<T> plusSafe(Collection<? extends T> collection, T... tArr) {
        K.n(collection, "<this>");
        K.n(tArr, "elements");
        return o.a1(k.L(tArr), collection);
    }
}
