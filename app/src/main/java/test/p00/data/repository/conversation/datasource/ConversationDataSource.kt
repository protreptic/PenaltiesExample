package test.p00.data.repository.conversation.datasource

import io.reactivex.Observable
import test.p00.data.model.conversation.Conversation

/**
 * Created by Peter Bukhal on 4/27/18.
 */
interface ConversationDataSource {

    /**
     * Возвращает беседу по ее идентификатору.
     *
     * @param conversationId идентификатор беседы
     * @return беседа
     */
    fun fetchById(conversationId: String): Observable<Conversation>

}