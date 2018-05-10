package test.p00.data.repository.conversation

import test.p00.data.repository.conversation.datasource.impl.MockConversationDataSource
import test.p00.data.repository.conversation.datasource.impl.CloudConversationDataSource
import test.p00.data.repository.conversation.impl.ConversationRepositoryImpl

/**
 * Created by Peter Bukhal on 4/27/18.
 */
object ConversationRepositoryFactory {

    fun create(conversationId: String): ConversationRepository =
            ConversationRepositoryImpl(
                    MockConversationDataSource(),
                    CloudConversationDataSource(conversationId))

}