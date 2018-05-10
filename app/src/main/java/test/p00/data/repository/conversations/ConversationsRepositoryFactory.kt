package test.p00.data.repository.conversations

import test.p00.data.repository.conversations.datasource.impl.MockConversationsDataSource
import test.p00.data.repository.conversations.impl.ConversationsRepositoryImpl

/**
 * Created by Peter Bukhal on 4/27/18.
 */
object ConversationsRepositoryFactory {

    fun create(): ConversationsRepository =
            ConversationsRepositoryImpl(
                    MockConversationsDataSource(),
                    MockConversationsDataSource())

}