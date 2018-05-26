package test.p00.data.repository.conversation.datasource.impl

import android.net.Uri
import io.reactivex.Observable
import io.reactivex.Observable.just
import io.reactivex.Observable.never
import test.p00.data.model.conversation.Conversation
import test.p00.data.model.conversation.Member
import test.p00.data.model.conversation.message.Message
import test.p00.data.repository.conversation.datasource.ConversationDataSource
import test.p00.data.storage.websocket.WebSocketConnection

/**
 * Created by Peter Bukhal on 4/27/18.
 */
class MockConversationDataSource : ConversationDataSource {

    override fun joinConversation() {}

    override fun watchOnConversation(): Observable<Message> = never()
    override fun watchOnConnection(): Observable<WebSocketConnection.Status> = never()

    override fun quitConversation() {}

    override fun sendText(text: String) {}
    override fun sendDocument(document: Uri) {}
    override fun sendImage(image: Uri) {}
    override fun sendAudio(audio: Uri) {}
    override fun sendVideo(video: Uri) {}
    override fun sendVoice(voice: Uri) {}
    override fun sendLocation(location: Uri) {}
    override fun sendWeb(web: Uri) {}

    override fun readMessage(message: Message) {}

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