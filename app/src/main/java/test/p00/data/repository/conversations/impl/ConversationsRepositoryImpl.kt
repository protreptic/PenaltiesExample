package test.p00.data.repository.conversations.impl

import test.p00.data.repository.conversations.ConversationsRepository
import test.p00.data.repository.conversations.datasource.ConversationsDataSource

/**
 * Created by Peter Bukhal on 4/27/18.
 */
class ConversationsRepositoryImpl(
        private val cache: ConversationsDataSource,
        private val cloud: ConversationsDataSource):
        ConversationsRepository {

    override fun fetchEverything() =
            cache.fetchEverything()

    override fun fetchById(conversationId: String) =
            cache.fetchById(conversationId)

}