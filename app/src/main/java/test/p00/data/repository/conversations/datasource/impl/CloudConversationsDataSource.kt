package test.p00.data.repository.conversations.datasource.impl

import io.reactivex.Observable
import test.p00.data.model.conversation.Conversation
import test.p00.data.model.conversation.Member
import test.p00.data.repository.conversations.datasource.ConversationsDataSource

/**
 * Created by Peter Bukhal on 5/10/18.
 */
class CloudConversationsDataSource : ConversationsDataSource {

    override fun fetchEverything(): Observable<List<Conversation>> = Observable.never()
    override fun fetchById(conversationId: String): Observable<Conversation> = Observable.never()

}