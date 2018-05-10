package test.p00.data.repository.conversations.datasource

import io.reactivex.Observable
import test.p00.data.model.conversation.Conversation

/**
 * Created by Peter Bukhal on 5/10/18.
 */
interface ConversationsDataSource {

    /**
     * Возвращает все доступные беседы
     * пользователя.
     *
     * @return беседы
     */
    fun fetchEverything(): Observable<List<Conversation>>

    /**
     * Возвращает беседу по ее идентификатору.
     *
     * @param conversationId идентификатор беседы
     * @return беседа
     */
    fun fetchById(conversationId: String): Observable<Conversation>

}