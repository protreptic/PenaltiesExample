package test.p00.domain.conversation

import io.reactivex.Observable
import test.p00.data.model.conversation.Conversation
import test.p00.data.model.conversation.message.Message
import test.p00.data.repository.conversation.datasource.impl.CacheConversationDataSource
import test.p00.data.repository.conversation.datasource.impl.CloudConversationDataSource
import test.p00.data.websocket.Connection.Status
import test.p00.domain.abs.Interactor

/**
 * Created by Peter Bukhal on 4/27/18.
 */
class ConversationInteractor(
        private val cache: CacheConversationDataSource = CacheConversationDataSource(),
        private val cloud: CloudConversationDataSource = CloudConversationDataSource()) : Interactor {

    fun fetchConversations() =
            cache.fetchConversations()

    fun fetchConversation(conversationId: String): Observable<Conversation> =
            cache.fetchConversation(conversationId)

    fun watchOnConversation(conversationId: String): Observable<Message> = cloud.watchOnConversation()
    fun watchOnConnection(): Observable<Status> = cloud.watchOnConnection()

    fun sendMessage(text: String) = cloud.sendMessage(text)

    fun sendMessageText(text: String) {
        sendMessage(text)
    }

    fun sendMessageImage() {}
    fun sendMessageAudio() {}
    fun sendMessageVideo() {}
    fun sendMessageLocation() {}
    fun sendMessageWeb() {}

    fun readMessage() {}

    fun quitConversation() {
        cloud.quitConversation()
    }

}