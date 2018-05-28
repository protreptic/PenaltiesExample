package test.p00.presentation.impl.router

import android.net.Uri
import android.support.v4.app.FragmentManager
import test.p00.auxiliary.extensions.deepLink
import test.p00.auxiliary.extensions.push
import test.p00.auxiliary.extensions.pushRoot
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

        private val DEEP_LINK_CONVERSATION = "https://fruit-chat.herokuapp.com/conversations".deepLink()
    }

    private val registeredDeepLinks: MutableMap<Uri, (deepLink: Uri) -> Unit> = HashMap()

    init {
        registeredDeepLinks.apply {
            put(DEEP_LINK_CONVERSATION) { deepLink ->
                val conversationId = deepLink.getQueryParameter("conversationId") ?: ""

                if (conversationId.isNotEmpty()) {
                    fragmentManager?.pushRoot(HomeFragment.newInstance())
                    fragmentManager?.push(ConversationsFragment.newInstance(), animate = false)
                    fragmentManager?.push(ConversationFragment.newInstance(conversationId), animate = false)
                } else {
                    toHome()
                }
            }
        }
    }

    override fun openDeepLink(target: Uri) {
        val deepLink = target.deepLink()

        if (registeredDeepLinks.containsKey(deepLink)) {
            registeredDeepLinks[deepLink]?.invoke(target)
        } else {
            toHome()
        }
    }

}