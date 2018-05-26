package test.p00.presentation.home.impl

import android.support.v4.app.FragmentManager
import test.p00.presentation.abs.Router.Delegate
import test.p00.presentation.conversations.impl.ConversationsFragment
import test.p00.presentation.home.HomeRouter
import test.p00.extensions.push

/**
 * Created by Peter Bukhal on 4/29/18.
 */
class HomeRouterImpl(
        override val fragmentManager: FragmentManager?,
        override val delegate: Delegate) : HomeRouter {

    override fun toConversations() {
        if (delegate.checkIfRoutingAvailable()) {
            fragmentManager?.push(ConversationsFragment.newInstance())
        }
    }

}