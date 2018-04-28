package test.p00.presentation.home.impl

import android.support.v4.app.FragmentManager
import test.p00.R
import test.p00.presentation.abs.Router.Delegate
import test.p00.presentation.conversations.impl.ConversationsFragment
import test.p00.presentation.home.HomeRouter

/**
 * Created by Peter Bukhal on 4/29/18.
 */
class HomeRouterImpl(
        override val fragmentManager: FragmentManager?,
        override val delegate: Delegate) : HomeRouter {

    override fun toHome() { /* игнорируем */ }

    override fun toConversations() {
        if (delegate.checkIfRoutingAvailable()) {
            fragmentManager
                ?.beginTransaction()
                ?.setCustomAnimations(
                        R.anim.slide_in_right, R.anim.slide_out_right,
                        R.anim.slide_in_right, R.anim.slide_out_right)
                ?.add(android.R.id.content,
                        ConversationsFragment.newInstance(),
                        ConversationsFragment.FRAGMENT_TAG)
                ?.addToBackStack(ConversationsFragment.FRAGMENT_TAG)
                ?.commit()
        }
    }

}