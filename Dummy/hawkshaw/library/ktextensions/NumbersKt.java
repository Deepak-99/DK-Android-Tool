package com.hawkshaw.library.ktextensions;

import Y1.J;
import android.content.res.Resources;

public final class NumbersKt {
    public static final int getDp(int i5) {
        return (int) (((float) i5) / Resources.getSystem().getDisplayMetrics().density);
    }

    public static final int getDpi(int i5) {
        return i5 / Resources.getSystem().getDisplayMetrics().densityDpi;
    }

    public static final int getPx(int i5) {
        return (int) (((float) i5) * Resources.getSystem().getDisplayMetrics().density);
    }

    public static final double roundTo(double d5, int i5) {
        double pow = Math.pow(10.0d, (double) i5);
        return ((double) J.y(d5 * pow)) / pow;
    }

    public static final double toGB(long j5) {
        return roundTo(((double) j5) / Math.pow(1000.0d, (double) 3), 2);
    }

    public static final double roundTo(float f5, int i5) {
        double pow = Math.pow(10.0d, (double) i5);
        return ((double) J.y(((double) f5) * pow)) / pow;
    }
}
