package test.p00.presentation.conversation.impl

import android.support.v4.app.FragmentManager
import test.p00.R
import test.p00.presentation.abs.Router.Delegate
import test.p00.presentation.conversation.ConversationRouter
import test.p00.presentation.conversation.member.impl.MemberFragment
import test.p00.presentation.conversation.members.impl.MembersFragment
import test.p00.presentation.model.conversation.MemberModel

/**
 * Created by Peter Bukhal on 4/27/18.
 */
class ConversationRouterImpl(
        override val fragmentManager: FragmentManager?,
        override val delegate: Delegate) : ConversationRouter {

    override fun toMember(member: MemberModel) {
        if (delegate.checkIfRoutingAvailable()) {
            fragmentManager
                ?.beginTransaction()
                ?.setCustomAnimations(
                        R.anim.slide_in_right, R.anim.slide_out_right,
                        R.anim.slide_in_right, R.anim.slide_out_right)
                ?.add(android.R.id.content,
                        MemberFragment.newInstance("", member.id),
                        MemberFragment.FRAGMENT_TAG)
                ?.addToBackStack(MemberFragment.FRAGMENT_TAG)
                ?.commit()
        }
    }

    override fun toMembers() {
        if (delegate.checkIfRoutingAvailable()) {
            fragmentManager
                ?.beginTransaction()
                ?.setCustomAnimations(
                        R.anim.slide_in_right, R.anim.slide_out_right,
                        R.anim.slide_in_right, R.anim.slide_out_right)
                ?.add(android.R.id.content,
                        MembersFragment.newInstance(""),
                        MembersFragment.FRAGMENT_TAG)
                ?.addToBackStack(MembersFragment.FRAGMENT_TAG)
                ?.commit()
        }
    }

}