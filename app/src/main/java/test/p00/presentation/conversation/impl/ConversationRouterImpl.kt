package test.p00.presentation.conversation.impl

import android.support.v4.app.FragmentManager
import test.p00.presentation.Router.Delegate
import test.p00.presentation.conversation.ConversationRouter
import test.p00.presentation.conversation.member.impl.MemberFragment
import test.p00.presentation.conversation.members.impl.MembersFragment
import test.p00.presentation.model.conversation.MemberModel

/**
 * Created by Peter Bukhal on 4/27/18.
 */
class ConversationRouterImpl(
        private val conversationId: String,
        override val fragmentManager: FragmentManager?,
        override val delegate: Delegate) : ConversationRouter {

    override fun toMember(member: MemberModel) {
        push(MemberFragment.newInstance(conversationId, member.id))
    }

    override fun toMembers() {
        push(MembersFragment.newInstance(conversationId))
    }

}