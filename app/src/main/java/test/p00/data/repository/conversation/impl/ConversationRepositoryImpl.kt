package test.p00.data.repository.conversation.impl

import io.reactivex.Observable
import test.p00.data.model.conversation.Conversation
import test.p00.data.model.conversation.message.Message
import test.p00.data.repository.conversation.ConversationRepository
import test.p00.data.repository.conversation.datasource.ConversationDataSource

/**
 * Created by Peter Bukhal on 4/27/18.
 */
class ConversationRepositoryImpl(
        private val cache: ConversationDataSource,
        private val cloud: ConversationDataSource) : ConversationRepository {

    override fun fetchEverything(): Observable<List<Conversation>> = Observable.never()
    override fun fetchById(conversationId: Int): Observable<Conversation> = Observable.never()

    override fun addMessage(message: Message): Observable<Message> = Observable.never()

}