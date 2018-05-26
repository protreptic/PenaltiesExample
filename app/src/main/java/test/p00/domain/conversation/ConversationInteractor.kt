package test.p00.domain.conversation

import android.net.Uri
import io.reactivex.Observable
import test.p00.data.model.conversation.message.Message
import test.p00.data.repository.conversation.datasource.ConversationDataSource
import test.p00.data.storage.websocket.WebSocketConnection
import test.p00.data.storage.websocket.WebSocketConnection.Status
import test.p00.domain.abs.Interactor
import javax.inject.Inject

/**
 * Created by Peter Bukhal on 4/27/18.
 */
class ConversationInteractor
    @Inject constructor(
        private val cache: ConversationDataSource,
        private val cloud: ConversationDataSource):
            Interactor {

    fun joinConversation(conversationId: String) =
            cloud.joinConversation(conversationId)

    fun watchOnConversation(): Observable<Message> =
            cloud.watchOnConversation()

    fun watchOnConnection(conversationId: String): Observable<Status> =
            cloud.watchOnConnection()
                 .doOnNext { status ->
                     if (status == WebSocketConnection.Status.FAILURE) {
                         cloud.joinConversation(conversationId)
                     }
                 }

    fun quitConversation() =
            cloud.quitConversation()

    fun readMessage(message: Message) =
            cloud.readMessage(message)

    fun sendText(text: String) =
            cloud.sendText(text)

    fun sendDocument(document: Uri) =
            cloud.sendDocument(document)

    fun sendImage(image: Uri) =
            cloud.sendImage(image)

    fun sendAudio(audio: Uri) =
            cloud.sendAudio(audio)

    fun sendVideo(video: Uri) =
            cloud.sendVideo(video)

    fun sendVoice(voice: Uri) =
            cloud.sendVoice(voice)

    fun sendLocation(location: Uri) =
            cloud.sendLocation(location)

    fun sendWeb(web: Uri) =
            cloud.sendWeb(web)

}