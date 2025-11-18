package com.hawkshaw.library.utils;

public final class UncaughtExceptionHandler {
    public static final UncaughtExceptionHandler INSTANCE = new UncaughtExceptionHandler();
    private static final String TAG = "UncaughtExceptionHandler";

    private UncaughtExceptionHandler() {
    }

    public final void handleException() {
        Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler$handleException$1());
    }
}
