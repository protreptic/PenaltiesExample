package test.p00.presentation.conversation

import test.p00.presentation.abs.Presenter
import test.p00.presentation.model.conversation.MemberModel
import test.p00.presentation.model.conversation.MessageModel

/**
 * Created by Peter Bukhal on 4/27/18.
 */
interface ConversationPresenter : Presenter<ConversationView> {

    fun displayConversation(conversationId: String)
    fun quitConversation()

    fun displayMember(member: MemberModel)
    fun displayMembers()

    fun sendMessage(messageText: String)
    fun readMessage(message: MessageModel)

}