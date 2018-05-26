package test.p00.presentation.conversation

import test.p00.presentation.Presenter
import test.p00.presentation.model.conversation.MemberModel
import test.p00.presentation.model.conversation.message.MessageModel

/**
 * Created by Peter Bukhal on 4/27/18.
 */
interface ConversationPresenter : Presenter<ConversationView> {

    fun displayConversation()
    fun quitConversation()

    fun displayMember(member: MemberModel)
    fun displayMembers()

    fun sendMessageText(messageText: String)
    fun sendMessageLocation(latitude: Float, longitude: Float)

    fun readMessage(message: MessageModel)

}