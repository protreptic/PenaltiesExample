package test.p00.presentation.conversations

import test.p00.presentation.Router
import test.p00.presentation.model.conversation.ConversationModel

/**
 * Created by Peter Bukhal on 4/28/18.
 */
interface ConversationsRouter : Router {

    /**
     * Переходит на экран с беседой.
     */
    fun toConversation(conversation: ConversationModel)

}