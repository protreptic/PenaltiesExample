package test.p00.presentation.conversations

import test.p00.presentation.Presenter
import test.p00.presentation.model.conversation.ConversationModel

/**
 * Created by Peter Bukhal on 4/28/18.
 */
interface ConversationsPresenter : Presenter<ConversationsView> {

    /**
     * Указывает view показать список
     * бесед пользователя.
     */
    fun displayConversations()

    /**
     * Указывает view показать
     * беседу выбранную пользователем.
     */
    fun displayConversation(conversation: ConversationModel)

}