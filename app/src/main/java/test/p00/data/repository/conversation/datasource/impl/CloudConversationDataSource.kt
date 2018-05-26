package test.p00.data.repository.conversation.datasource.impl

import android.net.Uri
import io.reactivex.Observable
import io.reactivex.Observable.never
import test.p00.data.model.conversation.Conversation
import test.p00.data.model.conversation.message.Author
import test.p00.data.model.conversation.message.Message
import test.p00.data.model.conversation.message.content.*
import test.p00.data.repository.conversation.datasource.ConversationDataSource
import test.p00.data.storage.websocket.WebSocketConnection
import test.p00.data.storage.websocket.WebSocketConnection.Status
import javax.inject.Inject

/**
 * Created by Peter Bukhal on 4/27/18.
 */
class CloudConversationDataSource
    @Inject constructor(
        private val conversationId: String,
        private val connection: WebSocketConnection):
            ConversationDataSource {

    override fun joinConversation() =
            connection.joinConversation(conversationId)

    override fun watchOnConversation(): Observable<Message> =
            connection
                .messages
                .map { receivedMessage -> Message().apply {
                    id = receivedMessage.id
                    status = when (receivedMessage.status) {
                        1 -> Message.Status.SENT.name
                        2 -> Message.Status.READ.name
                        else -> Message.Status.DEFAULT.name
                    }

                    sentAt = receivedMessage.createdAt

                    author = Author().apply {
                        id = receivedMessage.author ?: ""
                        name = receivedMessage.author ?: ""
                    }

                    contentType = Message.Content.TEXT.name
                    contentText = ContentText().apply {
                        text = receivedMessage.message ?: ""
                    }
                }}

    override fun watchOnConnection(): Observable<Status> =
            connection.status

    override fun quitConversation() =
            connection.quitConversation()

    private fun createNewMessage() =
            Message().apply {
                status = Message.Status.ADDED.name

                author?.id = "1"
                author?.name = "Peter Bukhal"

                addedAt = System.currentTimeMillis()
            }

    override fun sendText(text: String) =
            connection.sendMessage(createNewMessage().apply {
                contentType = Message.Content.TEXT.name
                contentText = ContentText().apply {
                    this.text = text
                }
            })

    override fun sendDocument(document: Uri) =
            connection.sendMessage(createNewMessage().apply {
                contentType = Message.Content.DOCUMENT.name
                contentDocument = ContentDocument().apply {

                }
            })

    override fun sendImage(image: Uri) =
            connection.sendMessage(createNewMessage().apply {
                contentType = Message.Content.IMAGE.name
                contentImage = ContentImage().apply {

                }
            })

    override fun sendAudio(audio: Uri) =
            connection.sendMessage(createNewMessage().apply {
                contentType = Message.Content.AUDIO.name
                contentAudio = ContentAudio().apply {

                }
            })

    override fun sendVideo(video: Uri) =
            connection.sendMessage(createNewMessage().apply {
                contentType = Message.Content.VIDEO.name
                contentVideo = ContentVideo().apply {

                }
            })

    override fun sendVoice(voice: Uri) =
            connection.sendMessage(createNewMessage().apply {
                contentType = Message.Content.VOICE.name
                contentVoice = ContentVoice().apply {

                }
            })

    override fun sendLocation(location: Uri) =
            connection.sendMessage(createNewMessage().apply {
                contentType = Message.Content.LOCATION.name
                contentLocation = ContentLocation().apply {
                    this.latitude = 0F
                    this.longitude = 0F
                }
            })

    override fun sendWeb(web: Uri) =
            connection.sendMessage(createNewMessage().apply {
                contentType = Message.Content.WEB.name
                contentWeb = ContentWeb().apply {

                }
            })

    override fun readMessage(message: Message) =
            connection.sendMessage(message.apply {
                status = Message.Status.READ.name
            })

    override fun fetchById(conversationId: String): Observable<Conversation> = never()

}