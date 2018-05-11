package test.p00.data.repository.conversations.datasource.impl

import io.reactivex.Observable
import test.p00.data.model.conversation.Conversation
import test.p00.data.model.conversation.Member
import test.p00.data.repository.conversations.datasource.ConversationsDataSource

/**
 * Created by Peter Bukhal on 5/10/18.
 */
class MockConversationsDataSource : ConversationsDataSource {

    private val mockMember1 = Member().apply {
        id = "1"
        name = "Иванов Петр"
    }

    private val mockMember2 = Member().apply {
        id = "2"
        name = "Петров Иван"
    }

    private val mockMember3 = Member().apply {
        id = "3"
        name = "Rjr+"
    }

    private val mockMember4 = Member().apply {
        id = "4"
        name = "D"
    }

    private val mockMember5 = Member().apply {
        id = "5"
        name = "E"
    }

    private val mockMember6 = Member().apply {
        id = "6"
        name = "F"
    }

    private val mockConversation1 = Conversation().apply {
        id = "1"
        name = "Group conversation1"
        members.apply {
            add(mockMember1)
            add(mockMember2)
            add(mockMember3)
        }}

    private val mockConversation2 = Conversation().apply {
        id = "2"
        name = "Group conversation2"
        members.apply {
            add(mockMember4)
            add(mockMember5)
            add(mockMember6)
        }}

    override fun fetchEverything(): Observable<List<Conversation>> =
            Observable.just(listOf(mockConversation1, mockConversation2))

    override fun fetchById(conversationId: String): Observable<Conversation> =
            Observable.just(Conversation().apply {
                id = conversationId
                members.apply {
                    add(mockMember1)
                    add(mockMember2)
                    add(mockMember3)
                }
            })

}