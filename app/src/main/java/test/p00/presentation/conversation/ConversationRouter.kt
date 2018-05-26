package test.p00.presentation.conversation

import test.p00.presentation.Router
import test.p00.presentation.model.conversation.MemberModel

/**
 * Created by Peter Bukhal on 4/27/18.
 */
interface ConversationRouter : Router {

    /**
     *
     */
    fun toMember(member: MemberModel)

    /**
     *
     */
    fun toMembers()

}