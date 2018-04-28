package test.p00.data.repository.conversation

import io.reactivex.Observable
import test.p00.data.model.conversation.Conversation
import test.p00.data.model.conversation.message.Message

/**
 * Created by Peter Bukhal on 4/27/18.
 */
interface ConversationRepository {

    /**
     *
     */
    fun fetchEverything(): Observable<List<Conversation>>

    /**
     *
     */
    fun fetchById(conversationId: Int): Observable<Conversation>

    /**
     *
     */
    fun addMessage(message: Message): Observable<Message>

}