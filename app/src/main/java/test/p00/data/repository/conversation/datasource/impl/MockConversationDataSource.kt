package test.p00.data.repository.conversation.datasource.impl

import io.reactivex.Observable
import io.reactivex.Observable.just
import test.p00.data.model.conversation.Conversation
import test.p00.data.model.conversation.Member
import test.p00.data.repository.conversation.datasource.ConversationDataSource

/**
 * Created by Peter Bukhal on 4/27/18.
 */
class MockConversationDataSource : ConversationDataSource {

    private val mockMember1 = Member().apply {
        id = "1"
        name = "A"
    }

    private val mockMember2 = Member().apply {
        id = "2"
        name = "B"
    }

    private val mockMember3 = Member().apply {
        id = "3"
        name = "C"
    }

    override fun fetchById(conversationId: String): Observable<Conversation> =
            just(Conversation().apply {
                id = conversationId
                members.apply {
                    add(mockMember1)
                    add(mockMember2)
                    add(mockMember3)
                }})

}