package test.p00.domain.conversation

import android.net.Uri
import io.reactivex.Observable
import test.p00.data.model.conversation.message.Message
import test.p00.data.repository.conversation.datasource.impl.MockConversationDataSource
import test.p00.data.repository.conversation.datasource.impl.CloudConversationDataSource
import test.p00.data.storage.websocket.WebSocketConnection
import test.p00.data.storage.websocket.WebSocketConnection.Status
import test.p00.domain.abs.Interactor

/**
 * Created by Peter Bukhal on 4/27/18.
 */
class ConversationInteractor(
        private val conversationId: String,
        private val cache: MockConversationDataSource = MockConversationDataSource(),
        private val cloud: CloudConversationDataSource = CloudConversationDataSource(conversationId)):
        Interactor {

    fun joinConversation() =
            cloud.joinConversation()

    fun watchOnConversation(): Observable<Message> =
            cloud.watchOnConversation()

    fun watchOnConnection(): Observable<Status> =
            cloud.watchOnConnection()
                 .doOnNext { status ->
                     if (status == WebSocketConnection.Status.FAILURE) {
                         cloud.joinConversation() }}

    fun quitConversation() =
            cloud.quitConversation()

    fun readMessage(message: Message) =
            cloud.readMessage(message)

    fun sendMessageText(text: String) =
            cloud.sendText(text)

    fun sendMessageDocument(document: Uri) =
            cloud.sendDocument(document)

    fun sendMessageImage(image: Uri) =
            cloud.sendImage(image)

    fun sendMessageAudio(audio: Uri) =
            cloud.sendAudio(audio)

    fun sendMessageVideo(video: Uri) =
            cloud.sendVideo(video)

    fun sendMessageVoice(voice: Uri) =
            cloud.sendVoice(voice)

    fun sendMessageLocation(latitude: Float, longitude: Float) =
            cloud.sendLocation(latitude, longitude)

    fun sendMessageWeb(web: Uri) =
            cloud.sendWeb(web)

}