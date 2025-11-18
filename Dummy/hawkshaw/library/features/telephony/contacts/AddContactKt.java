package com.hawkshaw.library.features.telephony.contacts;

import Y1.K;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.Context;
import android.provider.ContactsContract;
import com.hawkshaw.library.datalayer.models.Contact;
import java.util.ArrayList;

public final class AddContactKt {
    public static final boolean addContact(Context context, Contact contact) {
        K.n(context, "context");
        K.n(contact, "c");
        ArrayList arrayList = new ArrayList();
        arrayList.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI).withValue("account_type", (Object) null).withValue("account_name", (Object) null).build());
        boolean z4 = false;
        arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/name").withValue("data2", contact.getName()).build());
        for (Contact.MobileNumber mobileNumber : contact.getMobileNumbers()) {
            arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/phone_v2").withValue("data1", mobileNumber.getNumber()).withValue("data2", mobileNumber.getType()).build());
        }
        for (String withValue : contact.getEmailList()) {
            arrayList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/email_v2").withValue("data1", withValue).withValue("data2", 2).build());
        }
        try {
            ContentProviderResult[] applyBatch = context.getContentResolver().applyBatch("com.android.contacts", arrayList);
            K.m(applyBatch, "applyBatch(...)");
            int length = applyBatch.length;
            int i5 = 0;
            while (true) {
                if (i5 < length) {
                    if (applyBatch[i5].uri != null) {
                        break;
                    }
                    i5++;
                } else {
                    z4 = true;
                    break;
                }
            }
            return !z4;
        } catch (Exception e5) {
            e5.printStackTrace();
            return false;
        }
    }
}
