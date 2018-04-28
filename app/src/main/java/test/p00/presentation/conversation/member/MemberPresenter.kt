package test.p00.presentation.conversation.member

import test.p00.presentation.abs.Presenter
import test.p00.presentation.conversation.members.MembersView
import test.p00.presentation.model.conversation.ConversationModel
import test.p00.presentation.model.conversation.MemberModel

/**
 * Created by Peter Bukhal on 4/28/18.
 */
interface MemberPresenter : Presenter<MemberView> {

    /**
     * @param conversationId
     * @param member
     */
    fun displayMember(conversationId: String, memberId: String)

}