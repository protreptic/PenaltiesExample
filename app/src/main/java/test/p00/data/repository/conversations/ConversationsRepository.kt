package test.p00.data.repository.conversations

import io.reactivex.Observable
import test.p00.data.model.conversation.Conversation

/**
 * Created by Peter Bukhal on 4/27/18.
 */
interface ConversationsRepository {

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