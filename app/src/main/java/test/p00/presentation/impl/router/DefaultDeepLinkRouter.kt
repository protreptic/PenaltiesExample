package test.p00.presentation.impl.router

import android.net.Uri
import android.support.v4.app.FragmentManager
import test.p00.auxiliary.extensions.push
import test.p00.auxiliary.extensions.pushRoot
import test.p00.auxiliary.extensions.uri
import test.p00.presentation.DeepLinkRouter
import test.p00.presentation.Router
import test.p00.presentation.conversation.impl.ConversationFragment
import test.p00.presentation.conversations.impl.ConversationsFragment
import test.p00.presentation.home.impl.HomeFragment

/**
 * Created by Peter Bukhal on 5/24/18.
 */
class DefaultDeepLinkRouter(
        override val fragmentManager: FragmentManager?,
        override val delegate: Router.Delegate):
            DeepLinkRouter {

    companion object {

        private val DEEP_LINK_CONVERSATION_FIRST = "https://fruit-chat.herokuapp.com/conversations?conversationId=1".uri()
        private val DEEP_LINK_CONVERSATION_SECOND = "https://fruit-chat.herokuapp.com/conversations?conversationId=2".uri()
    }

    private val registeredDeepLinks: MutableMap<Uri, (deepLink: Uri) -> Unit> = HashMap()

    init {
        registeredDeepLinks.apply {
            put(DEEP_LINK_CONVERSATION_FIRST) {
                val conversationId = it.getQueryParameter("conversationId")

                fragmentManager?.pushRoot(HomeFragment.newInstance())
                fragmentManager?.push(ConversationsFragment.newInstance())
                fragmentManager?.push(ConversationFragment.newInstance(conversationId))
            }
            put(DEEP_LINK_CONVERSATION_SECOND) {
                val conversationId = it.getQueryParameter("conversationId")

                fragmentManager?.pushRoot(HomeFragment.newInstance())
                fragmentManager?.push(ConversationsFragment.newInstance())
                fragmentManager?.push(ConversationFragment.newInstance(conversationId))
            }
        }
    }

    override fun openDeepLink(deepLink: Uri) {
        if (registeredDeepLinks.containsKey(deepLink)) {
            registeredDeepLinks[deepLink]?.invoke(deepLink)
        } else {
            toHome()
        }
    }

}