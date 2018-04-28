package test.p00.data.repository.conversation.datasource.impl

import io.reactivex.Observable
import test.p00.data.model.conversation.Conversation
import test.p00.data.model.conversation.Member
import test.p00.data.repository.conversation.datasource.ConversationDataSource

/**
 * Created by Peter Bukhal on 4/27/18.
 */
class CacheConversationDataSource : ConversationDataSource {

    /**
     * Возвращает все доступные беседы
     * пользователя.
     *
     * @return беседы
     */
    fun fetchConversations(): Observable<List<Conversation>> =
            Observable.just(listOf(
                    Conversation().apply {
                        id = "1"
                        members.apply {
                            add(Member().apply {
                                id = "1"
                                name = "A"
                            })
                            add(Member().apply {
                                id = "2"
                                name = "B"
                            })
                            add(Member().apply {
                                id = "3"
                                name = "C"
                            })
                        }},
                    Conversation().apply {
                        id = "2"
                        members.apply {
                            add(Member().apply {
                                id = "4"
                                name = "D"
                            })
                            add(Member().apply {
                                id = "5"
                                name = "E"
                            })
                            add(Member().apply {
                                id = "6"
                                name = "F"
                            })
                    }}))

    /**
     * Возвращает беседу по ее идентификатору.
     *
     * @param conversationId идентификатор беседы
     * @return беседа
     */
    fun fetchConversation(conversationId: String): Observable<Conversation> =
            Observable.just(
                    Conversation().apply {
                        id = conversationId
                        members.apply {
                            add(Member().apply {
                                id = "1"
                                name = "A"
                            })
                            add(Member().apply {
                                id = "2"
                                name = "B"
                            })
                            add(Member().apply {
                                id = "3"
                                name = "C"
                            })
                        }})

}