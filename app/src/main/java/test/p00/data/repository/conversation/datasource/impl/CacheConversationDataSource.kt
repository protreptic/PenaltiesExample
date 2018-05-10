package test.p00.data.repository.conversation.datasource.impl

import io.reactivex.Observable
import io.reactivex.Observable.never
import test.p00.data.model.conversation.Conversation
import test.p00.data.repository.conversation.datasource.ConversationDataSource

/**
 * Created by Peter Bukhal on 4/27/18.
 */
class CacheConversationDataSource : ConversationDataSource {

    override fun fetchById(conversationId: String): Observable<Conversation> = never()

}