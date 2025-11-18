package com.hawkshaw.library.features.telephony.contacts

import android.content.ContentResolver
import android.net.Uri
import android.provider.ContactsContract
import com.hawkshaw.library.datalayer.models.Contact
import com.hawkshaw.library.ktextensions.ManifestPermissionsKt.hasPermission
import android.util.Log // Added for logging

private const val TAG = "GetContactsKt" // Defined a TAG for this file

/**
 * Gets the contact name for a given phone number
 */
fun getContactName(contentResolver: ContentResolver, phoneNumber: String): String {
    Log.d(TAG, "[DEBUG] getContactName called. phoneNumber: \"$phoneNumber\"")
    if (!hasPermission("android.permission.READ_CONTACTS")) {
        Log.w(TAG, "[DEBUG] getContactName: READ_CONTACTS permission denied.")
        return "<unknown>"
    }
    Log.d(TAG, "[DEBUG] getContactName: READ_CONTACTS permission granted.")

    val uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNumber))
    Log.d(TAG, "[DEBUG] getContactName: Query URI: $uri")
    var result = phoneNumber // Default to phoneNumber
    
    contentResolver.query(uri, arrayOf("display_name"), null, null, null)?.use { cursor ->
        Log.d(TAG, "[DEBUG] getContactName: Cursor obtained. Count: ${cursor.count}")
        if (cursor.moveToFirst()) {
            Log.d(TAG, "[DEBUG] getContactName: Cursor moved to first.")
            val nameIndex = cursor.getColumnIndex("display_name")
            Log.d(TAG, "[DEBUG] getContactName: 'display_name' column index: $nameIndex")
            if (nameIndex != -1) {
                cursor.getString(nameIndex)?.let { name ->
                    Log.d(TAG, "[DEBUG] getContactName: Found name: \"$name\"")
                    result = name
                } ?: Log.d(TAG, "[DEBUG] getContactName: Name from cursor is null.")
            } else {
                Log.w(TAG, "[DEBUG] getContactName: 'display_name' column not found in cursor.")
            }
        } else {
            Log.d(TAG, "[DEBUG] getContactName: Cursor is empty or could not move to first.")
        }
    } ?: Log.d(TAG, "[DEBUG] getContactName: Query returned null cursor.")
    
    Log.d(TAG, "[DEBUG] getContactName returning: \"$result\"")
    return result // Return original phoneNumber if name not found or an error occurred.
}

/**
 * Gets all contacts with phone numbers
 */
fun getContacts(contentResolver: ContentResolver, typeMapper: (Int) -> String, continuation: (Int) -> String): List<Contact> {
    Log.d(TAG, "[DEBUG] getContacts called.")
    val contacts = mutableListOf<Contact>()
    
    val projection = null // Query all columns
    val selection = "has_phone_number>0"
    val sortOrder = "display_name ASC"
    Log.d(TAG, "[DEBUG] getContacts: Querying ContactsContract.Contacts.CONTENT_URI. Selection: \"$selection\", SortOrder: \"$sortOrder\"")

    val cursor = contentResolver.query(
        ContactsContract.Contacts.CONTENT_URI,
        projection,
        selection,
        null,
        sortOrder
    ) ?: run {
        Log.w(TAG, "[DEBUG] getContacts: Query returned null cursor. Returning empty list.")
        return emptyList()
    }
    
    Log.d(TAG, "[DEBUG] getContacts: Cursor obtained. Count: ${cursor.count}")
    if (cursor.count <= 0) {
        Log.d(TAG, "[DEBUG] getContacts: Cursor count is ${cursor.count}. Closing cursor and returning empty list.")
        cursor.close()
        return emptyList()
    }
    
    cursor.use {
        Log.d(TAG, "[DEBUG] getContacts: Inside cursor.use block.")
        val idColumn = it.getColumnIndex("_id")
        val rawContactIdColumn = it.getColumnIndex("name_raw_contact_id")
        val displayNameColumn = it.getColumnIndex("display_name")
        val starredColumn = it.getColumnIndex("starred")
        val pinnedColumn = it.getColumnIndex("pinned")
        val photoUriColumn = it.getColumnIndex("photo_uri")
        val updatedTimestampColumn = it.getColumnIndex("contact_last_updated_timestamp")
        
        Log.d(TAG, "[DEBUG] getContacts: Column Indices: _id=$idColumn, name_raw_contact_id=$rawContactIdColumn, display_name=$displayNameColumn, starred=$starredColumn, pinned=$pinnedColumn, photo_uri=$photoUriColumn, contact_last_updated_timestamp=$updatedTimestampColumn")

        var rowNum = 0
        while (it.moveToNext()) {
            rowNum++
            Log.d(TAG, "[DEBUG] getContacts: Processing row $rowNum.")
            val id = it.getString(idColumn)
            val rawContactId = it.getString(rawContactIdColumn) // Not used but kept for reference and logging
            val displayName = it.getString(displayNameColumn)
            val starred = it.getInt(starredColumn)
            val pinned = it.getInt(pinnedColumn)
            val photoUri = it.getString(photoUriColumn)
            val lastUpdated = it.getString(updatedTimestampColumn)

            // Get phone numbers
            Log.d(TAG, "[DEBUG] getContacts: Row $rowNum - Calling getContactPhoneNumbers for contactId: \"$id\"")
            val phoneNumbers = getContactPhoneNumbers(contentResolver, id, typeMapper)
            
            // Get email addresses
            Log.d(TAG, "[DEBUG] getContacts: Row $rowNum - Calling getContactEmails for contactId: \"$id\"")
            val emailAddresses = getContactEmails(contentResolver, id)
            
            val contact = Contact(
                id = id,
                name = displayName,
                starred = starred,
                pinned = pinned,
                photoUri = photoUri,
                lastUpdatedTimestamp = lastUpdated,
                mobileNumbers = phoneNumbers,
                emailList = emailAddresses
            )
            contacts.add(contact)
            Log.d(TAG, "[DEBUG] getContacts: Row $rowNum - Added Contact object: $contact")
        }
    }
    Log.d(TAG, "[DEBUG] getContacts: Finished processing cursor. Total contacts retrieved: ${contacts.size}. Returning list.")
    return contacts
}

/**
 * Gets all phone numbers for a contact
 */
private fun getContactPhoneNumbers(contentResolver: ContentResolver, contactId: String, typeMapper: (Int) -> String): List<Contact.MobileNumber> {
    Log.d(TAG, "[DEBUG] getContactPhoneNumbers called for contactId: \"$contactId\"")
    val phoneNumbers = mutableListOf<Contact.MobileNumber>()
    
    val projection = null // Query all columns
    val selection = "contact_id = ?"
    val selectionArgs = arrayOf(contactId)
    Log.d(TAG, "[DEBUG] getContactPhoneNumbers: Querying ContactsContract.CommonDataKinds.Phone.CONTENT_URI. Selection: \"$selection\", Args: [$contactId]")

    contentResolver.query(
        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
        projection,
        selection,
        selectionArgs,
        null
    )?.use { cursor ->
        Log.d(TAG, "[DEBUG] getContactPhoneNumbers: Cursor obtained. Count: ${cursor.count}")
        val numberColumnIndex = cursor.getColumnIndex("data1")
        val typeColumnIndex = cursor.getColumnIndex("data2")
        Log.d(TAG, "[DEBUG] getContactPhoneNumbers: Column Indices - data1 (number): $numberColumnIndex, data2 (type): $typeColumnIndex")

        var rowNum = 0
        while (cursor.moveToNext()) {
            rowNum++
            Log.d(TAG, "[DEBUG] getContactPhoneNumbers: Processing row $rowNum for contactId: \"$contactId\"")
            val phoneNumber = cursor.getString(numberColumnIndex)
            val phoneType = cursor.getInt(typeColumnIndex)
            val typeResource = ContactsContract.CommonDataKinds.Phone.getTypeLabelResource(phoneType)
            
            Log.d(TAG, "[DEBUG] getContactPhoneNumbers: Row $rowNum Data - phoneNumber: \"$phoneNumber\", phoneTypeInt: $phoneType, typeResource: $typeResource")

            if (phoneNumber != null) {
                val mappedType = typeMapper.invoke(typeResource)
                Log.d(TAG, "[DEBUG] getContactPhoneNumbers: Row $rowNum - Mapped typeResource $typeResource to \"$mappedType\"")
                val mobileNumber = Contact.MobileNumber(phoneNumber, mappedType)
                phoneNumbers.add(mobileNumber)
                Log.d(TAG, "[DEBUG] getContactPhoneNumbers: Row $rowNum - Added MobileNumber: $mobileNumber")
            } else {
                Log.d(TAG, "[DEBUG] getContactPhoneNumbers: Row $rowNum - phoneNumber is null. Skipping.")
            }
        }
    } ?: Log.d(TAG, "[DEBUG] getContactPhoneNumbers: Query returned null cursor for contactId: \"$contactId\"")
    
    Log.d(TAG, "[DEBUG] getContactPhoneNumbers: Returning ${phoneNumbers.size} phone numbers for contactId: \"$contactId\"")
    return phoneNumbers
}

/**
 * Gets all email addresses for a contact
 */
private fun getContactEmails(contentResolver: ContentResolver, contactId: String): List<String> {
    Log.d(TAG, "[DEBUG] getContactEmails called for contactId: \"$contactId\"")
    val emails = mutableListOf<String>()
    
    val projection = arrayOf("data1") // Only need the email address
    val selection = "contact_id = ?"
    val selectionArgs = arrayOf(contactId)
    Log.d(TAG, "[DEBUG] getContactEmails: Querying ContactsContract.CommonDataKinds.Email.CONTENT_URI. Projection: [\"data1\"], Selection: \"$selection\", Args: [$contactId]")

    contentResolver.query(
        ContactsContract.CommonDataKinds.Email.CONTENT_URI,
        projection,
        selection,
        selectionArgs,
        null
    )?.use { cursor ->
        Log.d(TAG, "[DEBUG] getContactEmails: Cursor obtained. Count: ${cursor.count}")
        val emailColumnIndex = cursor.getColumnIndex("data1")
        Log.d(TAG, "[DEBUG] getContactEmails: Column Index - data1 (email): $emailColumnIndex")

        var rowNum = 0
        while (cursor.moveToNext()) {
            rowNum++
            Log.d(TAG, "[DEBUG] getContactEmails: Processing row $rowNum for contactId: \"$contactId\"")
            cursor.getString(emailColumnIndex)?.let { email ->
                Log.d(TAG, "[DEBUG] getContactEmails: Row $rowNum - Found email: \"$email\"")
                emails.add(email)
            } ?: Log.d(TAG, "[DEBUG] getContactEmails: Row $rowNum - Email from cursor is null.")
        }
    } ?: Log.d(TAG, "[DEBUG] getContactEmails: Query returned null cursor for contactId: \"$contactId\"")
    
    Log.d(TAG, "[DEBUG] getContactEmails: Returning ${emails.size} email addresses for contactId: \"$contactId\"")
    return emails
}
