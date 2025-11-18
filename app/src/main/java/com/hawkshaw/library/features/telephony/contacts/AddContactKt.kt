package com.hawkshaw.library.features.telephony.contacts

import android.content.ContentProviderOperation
import android.content.ContentProviderResult
import android.content.Context
import android.provider.ContactsContract
import com.hawkshaw.library.datalayer.models.Contact
import com.hawkshaw.library.logger.Logger
import java.security.InvalidParameterException
import android.util.Log // Added for logging

private const val TAG = "AddContact"

/**
 * Adds a new contact to the device's contacts
 * 
 * @param context Application context
 * @param contact Contact to add
 * @return true if contact was added successfully
 * @throws SecurityException if missing required permissions
 * @throws InvalidParameterException if required fields are missing
 */
@Throws(SecurityException::class, InvalidParameterException::class)
fun addContact(context: Context, contact: Contact): Boolean {
    Log.d(TAG, "[DEBUG] addContact called. Context: $context, Contact: $contact")
    // Validate contact data
    if (contact.mobileNumbers.isEmpty()) {
        Log.w(TAG, "[DEBUG] addContact: Validation failed - mobileNumbers is empty.")
        throw InvalidParameterException("At least one phone number is required")
    }
    Log.d(TAG, "[DEBUG] addContact: mobileNumbers list is not empty. Size: ${contact.mobileNumbers.size}")

    contact.mobileNumbers.forEachIndexed { index, number ->
        Log.d(TAG, "[DEBUG] addContact: Validating mobile number at index $index: Number='${number.number}', Type='${number.type}'")
        if (number.number.isBlank()) {
            Log.w(TAG, "[DEBUG] addContact: Validation failed - mobileNumber at index $index has a blank number.")
            throw InvalidParameterException("Phone number cannot be empty")
        }
        if (number.type.isBlank()) {
            Log.w(TAG, "[DEBUG] addContact: Validation failed - mobileNumber at index $index has a blank type.")
            throw InvalidParameterException("Phone number type cannot be empty")
        }
    }
    Log.d(TAG, "[DEBUG] addContact: All mobile numbers validated.")

    return try {
        Log.d(TAG, "[DEBUG] addContact: Starting try block for ContentProviderOperations.")
        val operations = ArrayList<ContentProviderOperation>()
        
        // Create raw contact
        Log.d(TAG, "[DEBUG] addContact: Adding operation: new raw contact.")
        operations.add(ContentProviderOperation.newInsert(
            ContactsContract.RawContacts.CONTENT_URI)
            .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
            .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
            .build())
        
        // Add name
        if (!contact.name.isNullOrBlank()) {
            Log.d(TAG, "[DEBUG] addContact: Contact name '${contact.name}' is not null or blank. Adding operation: name.")
            operations.add(ContentProviderOperation.newInsert(
                ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE,
                    ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,
                    contact.name)
                .build())
        } else {
            Log.d(TAG, "[DEBUG] addContact: Contact name is null or blank. Skipping name operation.")
        }
        
        // Add phone numbers
        Log.d(TAG, "[DEBUG] addContact: Processing ${contact.mobileNumbers.size} mobile numbers.")
        contact.mobileNumbers.forEachIndexed { index, number ->
            Log.d(TAG, "[DEBUG] addContact: Adding operation for mobile number at index $index: Number='${number.number}', Type='${getPhoneType(number.type)}', IsPrimary=${index == 0}")
            operations.add(ContentProviderOperation.newInsert(
                ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE,
                    ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, number.number)
                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE,
                    getPhoneType(number.type))
                .withValue(ContactsContract.CommonDataKinds.Phone.IS_PRIMARY,
                    if (contact.mobileNumbers.indexOf(number) == 0) 1 else 0)
                .build())
        }
        
        // Add email addresses
        Log.d(TAG, "[DEBUG] addContact: Processing ${contact.emailList.size} email addresses.")
        contact.emailList.forEachIndexed { index, email ->
            if (email.isNotBlank()) {
                Log.d(TAG, "[DEBUG] addContact: Adding operation for email at index $index: '$email'")
                operations.add(ContentProviderOperation.newInsert(
                    ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                    .withValue(ContactsContract.Data.MIMETYPE,
                        ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.Email.ADDRESS, email)
                    .withValue(ContactsContract.CommonDataKinds.Email.TYPE,
                        ContactsContract.CommonDataKinds.Email.TYPE_HOME)
                    .build())
            } else {
                Log.d(TAG, "[DEBUG] addContact: Email at index $index is blank. Skipping.")
            }
        }
        Log.d(TAG, "[DEBUG] addContact: Total operations prepared: ${operations.size}. Attempting applyBatch.")

        // Execute all operations
        val results: Array<ContentProviderResult> = context.contentResolver
            .applyBatch(ContactsContract.AUTHORITY, operations)
        Log.d(TAG, "[DEBUG] addContact: applyBatch executed. Results count: ${results.size}")
        if (results.isNotEmpty()) {
            Log.d(TAG, "[DEBUG] addContact: First result URI: ${results[0].uri}")
        }

        val success = results.isNotEmpty() && results[0].uri != null
        Log.d(TAG, "[DEBUG] addContact: Success status: $success")
        if (success) {
            Logger.d(TAG, "Successfully added contact: ${contact.name}", false, 4, null) // Existing log
        } else {
            Logger.e( // Existing log
                TAG,
                "Failed to add contact: ${contact.name}",
                b = false,
                i = 12,
                nothing = null
            )
        }
        
        success
    } catch (e: SecurityException) {
        Log.e(TAG, "[DEBUG] addContact: SecurityException caught: ${e.message}", e)
        Logger.e(TAG, "Missing required permissions", e, false, 12, null) // Existing log
        throw e
    } catch (e: Exception) {
        Log.e(TAG, "[DEBUG] addContact: Generic Exception caught: ${e.message}", e)
        Logger.e(TAG, "Error adding contact", e, false, 12, null) // Existing log
        false
    }
}

/**
 * Converts phone type string to ContactsContract phone type
 * 
 * @param type Phone type string
 * @return ContactsContract phone type constant
 * @throws InvalidParameterException if type is invalid
 */
@Throws(InvalidParameterException::class)
private fun getPhoneType(type: String): Int {
    Log.d(TAG, "[DEBUG] getPhoneType called with type: \"$type\"")
    val result = when (type.lowercase()) {
        "home" -> ContactsContract.CommonDataKinds.Phone.TYPE_HOME
        "mobile" -> ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE
        "work" -> ContactsContract.CommonDataKinds.Phone.TYPE_WORK
        "work_mobile" -> ContactsContract.CommonDataKinds.Phone.TYPE_WORK_MOBILE
        "other" -> ContactsContract.CommonDataKinds.Phone.TYPE_OTHER
        else -> {
            Log.w(TAG, "[DEBUG] getPhoneType: Invalid phone type received: \"$type\". Throwing InvalidParameterException.")
            throw InvalidParameterException("Invalid phone type: $type")
        }
    }
    Log.d(TAG, "[DEBUG] getPhoneType: Mapped \"$type\" to $result")
    return result
}
