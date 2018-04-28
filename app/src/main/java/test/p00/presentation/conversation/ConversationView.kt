package test.p00.presentation.conversation

import test.p00.data.websocket.Connection
import test.p00.presentation.abs.View
import test.p00.presentation.model.conversation.ConversationModel
import test.p00.presentation.model.conversation.MessageModel

/**
 * Created by Peter Bukhal on 4/27/18.
 */
interface ConversationView : View {

    fun showConversation(conversation: ConversationModel)
    fun showMessage(message: MessageModel)

    fun showConnectionStatus(status: Connection.Status)

}