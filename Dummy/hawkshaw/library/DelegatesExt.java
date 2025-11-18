package com.hawkshaw.library;

public final class DelegatesExt {
    public static final DelegatesExt INSTANCE = new DelegatesExt();

    private DelegatesExt() {
    }

    public final <T> NotNullSingleValueVar<T> notNullSingleValue() {
        return new NotNullSingleValueVar<>();
    }
}
