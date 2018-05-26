package test.p00.domain.conversations

import io.reactivex.Observable
import test.p00.data.model.conversation.Conversation
import test.p00.data.repository.conversations.ConversationsRepository
import test.p00.domain.abs.Interactor
import javax.inject.Inject

/**
 * Created by Peter Bukhal on 4/27/18.
 */
class ConversationsInteractor
    @Inject constructor(
        private val repository: ConversationsRepository):
            Interactor {

    fun fetchConversations() =
            repository.fetchEverything()

    fun fetchConversation(conversationId: String): Observable<Conversation> =
            repository.fetchById(conversationId)

}