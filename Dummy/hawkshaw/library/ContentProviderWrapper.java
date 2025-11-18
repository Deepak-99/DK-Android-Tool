package com.hawkshaw.library;

import Y1.K;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

public abstract class ContentProviderWrapper extends ContentProvider {
    public int delete(Uri uri, String str, String[] strArr) {
        K.n(uri, "uri");
        return -1;
    }

    public String getType(Uri uri) {
        K.n(uri, "uri");
        return null;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        K.n(uri, "uri");
        return null;
    }

    public abstract boolean onCreate();

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        K.n(uri, "uri");
        return null;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        K.n(uri, "uri");
        return -1;
    }
}
