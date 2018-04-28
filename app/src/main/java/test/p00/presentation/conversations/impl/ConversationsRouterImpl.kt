package test.p00.presentation.conversations.impl

import android.support.v4.app.FragmentManager
import test.p00.R
import test.p00.presentation.abs.Router.Delegate
import test.p00.presentation.conversation.impl.ConversationFragment
import test.p00.presentation.conversations.ConversationsRouter
import test.p00.presentation.model.conversation.ConversationModel

/**
 * Created by Peter Bukhal on 4/28/18.
 */
class ConversationsRouterImpl(
        override val fragmentManager: FragmentManager?,
        override val delegate: Delegate) : ConversationsRouter {

    override fun toConversation(conversation: ConversationModel) {
        if (delegate.checkIfRoutingAvailable()) {
            fragmentManager
                    ?.beginTransaction()
                    ?.setCustomAnimations(
                            R.anim.slide_in_right, R.anim.slide_out_right,
                            R.anim.slide_in_right, R.anim.slide_out_right)
                    ?.add(android.R.id.content,
                            ConversationFragment.newInstance(conversation.id),
                            ConversationFragment.FRAGMENT_TAG)
                    ?.addToBackStack(ConversationFragment.FRAGMENT_TAG)
                    ?.commit()
        }
    }

}