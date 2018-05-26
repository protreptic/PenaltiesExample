package test.p00.presentation.conversations

import test.p00.presentation.View
import test.p00.presentation.model.conversation.ConversationModel

/**
 * Created by Peter Bukhal on 4/28/18.
 */
interface ConversationsView : View {

    /**
     * Отображает список бесед пользователя.
     *
     * @param conversations беседы пользователя
     */
    fun showConversations(conversations: List<ConversationModel>)

}