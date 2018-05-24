package test.p00.presentation.conversations.impl

import android.support.v4.app.FragmentManager
import test.p00.presentation.abs.Router.Delegate
import test.p00.presentation.conversation.impl.ConversationFragment
import test.p00.presentation.conversations.ConversationsRouter
import test.p00.presentation.model.conversation.ConversationModel
import test.p00.util.extension.push

/**
 * Created by Peter Bukhal on 4/28/18.
 */
class ConversationsRouterImpl(
        override val fragmentManager: FragmentManager?,
        override val delegate: Delegate) : ConversationsRouter {

    override fun toConversation(conversation: ConversationModel) {
        if (delegate.checkIfRoutingAvailable()) {
            fragmentManager?.push(ConversationFragment.newInstance(conversation.id))
        }
    }

}