package com.hawkshaw.library.features.media.files;

import X2.q;
import Y1.K;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public final class FileExplorerMediaStoreKt {
    private static final String TAG = "FileExplorer";

    public static final List<MediaStoreFile> getMediaStoreFiles(ContentResolver contentResolver) {
        int i5;
        String str;
        ArrayList arrayList;
        K.n(contentResolver, "contentResolver");
        int i6 = Build.VERSION.SDK_INT;
        q qVar = q.f2602D;
        if (i6 >= 29) {
            Uri contentUri = MediaStore.Files.getContentUri("external");
            ArrayList arrayList2 = new ArrayList();
            Cursor query = contentResolver.query(contentUri, (String[]) null, "media_type=3", (String[]) null, "date_modified ASC");
            if (query == null || !query.moveToNext()) {
                Log.d(TAG, "Cursor null");
                return qVar;
            }
            int columnIndexOrThrow = query.getColumnIndexOrThrow("_id");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("bucket_display_name");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("bucket_id");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("_data");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("date_added");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("date_modified");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("document_id");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("mime_type");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("_display_name");
            int columnIndexOrThrow10 = query.getColumnIndexOrThrow("owner_package_name");
            int columnIndexOrThrow11 = query.getColumnIndexOrThrow("relative_path");
            int columnIndexOrThrow12 = query.getColumnIndexOrThrow("_size");
            int columnIndexOrThrow13 = query.getColumnIndexOrThrow("relative_path");
            int columnIndexOrThrow14 = query.getColumnIndexOrThrow("media_type");
            ArrayList arrayList3 = arrayList2;
            int columnIndexOrThrow15 = query.getColumnIndexOrThrow("parent");
            int i7 = 50;
            int i8 = columnIndexOrThrow12;
            int i9 = columnIndexOrThrow14;
            while (true) {
                long j5 = query.getLong(columnIndexOrThrow);
                String string = query.isNull(columnIndexOrThrow2) ? null : query.getString(columnIndexOrThrow2);
                String string2 = query.isNull(columnIndexOrThrow3) ? null : query.getString(columnIndexOrThrow3);
                String string3 = query.isNull(columnIndexOrThrow4) ? null : query.getString(columnIndexOrThrow4);
                Long valueOf = query.isNull(columnIndexOrThrow5) ? null : Long.valueOf(query.getLong(columnIndexOrThrow5));
                Long valueOf2 = query.isNull(columnIndexOrThrow6) ? null : Long.valueOf(query.getLong(columnIndexOrThrow6));
                String string4 = query.isNull(columnIndexOrThrow7) ? null : query.getString(columnIndexOrThrow7);
                String string5 = query.isNull(columnIndexOrThrow8) ? null : query.getString(columnIndexOrThrow8);
                String string6 = query.isNull(columnIndexOrThrow9) ? null : query.getString(columnIndexOrThrow9);
                String string7 = query.isNull(columnIndexOrThrow10) ? null : query.getString(columnIndexOrThrow10);
                String string8 = query.isNull(columnIndexOrThrow11) ? null : query.getString(columnIndexOrThrow11);
                int i10 = columnIndexOrThrow;
                int i11 = i8;
                int i12 = i10;
                Integer valueOf3 = query.isNull(i11) ? null : Integer.valueOf(query.getInt(i11));
                String string9 = query.isNull(columnIndexOrThrow13) ? null : query.getString(columnIndexOrThrow13);
                int i13 = i9;
                int i14 = i11;
                int i15 = i13;
                String string10 = query.isNull(i15) ? null : query.getString(i15);
                if (query.isNull(columnIndexOrThrow15)) {
                    i5 = columnIndexOrThrow15;
                    str = null;
                } else {
                    i5 = columnIndexOrThrow15;
                    str = query.getString(columnIndexOrThrow15);
                }
                Uri withAppendedId = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, j5);
                int i16 = i15;
                K.m(withAppendedId, "withAppendedId(...)");
                arrayList = arrayList3;
                arrayList.add(new MediaStoreFile(j5, string, string2, withAppendedId.getPath(), string3, valueOf, valueOf2, string4, string5, string6, string7, string8, valueOf3, string9, string10, str));
                if (!query.moveToNext()) {
                    break;
                }
                int i17 = i7 - 1;
                if (i7 <= 0) {
                    break;
                }
                arrayList3 = arrayList;
                i7 = i17;
                columnIndexOrThrow15 = i5;
                columnIndexOrThrow = i12;
                i8 = i14;
                i9 = i16;
            }
            query.close();
            return arrayList;
        }
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        return qVar;
    }
}
