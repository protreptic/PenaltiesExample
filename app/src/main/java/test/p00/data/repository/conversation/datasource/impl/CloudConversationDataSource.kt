package test.p00.data.repository.conversation.datasource.impl

import android.net.Uri
import io.reactivex.Observable
import io.reactivex.Observable.*
import test.p00.data.model.conversation.Conversation
import test.p00.data.model.conversation.message.Author
import test.p00.data.model.conversation.message.Message
import test.p00.data.model.conversation.message.content.*
import test.p00.data.repository.conversation.datasource.ConversationDataSource
import test.p00.data.storage.websocket.WebSocketConnection
import test.p00.data.storage.websocket.WebSocketConnection.Status

/**
 * Created by Peter Bukhal on 4/27/18.
 */
class CloudConversationDataSource(
        private val conversationId: String,
        private val connection: WebSocketConnection = WebSocketConnection.connection):
        ConversationDataSource {

    fun joinConversation() =
            connection.joinConversation(conversationId)

    fun watchOnConversation(): Observable<Message> =
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

    fun watchOnConnection(): Observable<Status> =
            connection.status

    fun quitConversation() =
            connection.quitConversation()

    private fun createNewMessage() =
            Message().apply {
                status = Message.Status.ADDED.name
                author?.name = "Peter Bukhal"

                addedAt = System.currentTimeMillis()
            }

    fun sendText(text: String) =
            connection.sendMessage(createNewMessage().apply {
                contentType = Message.Content.TEXT.name
                contentText = ContentText().apply {
                    this.text = text
                }
            })

    fun sendDocument(document: Uri) =
            connection.sendMessage(createNewMessage().apply {
                contentType = Message.Content.DOCUMENT.name
                contentDocument = ContentDocument().apply {

                }
            })

    fun sendImage(image: Uri) =
            connection.sendMessage(createNewMessage().apply {
                contentType = Message.Content.IMAGE.name
                contentImage = ContentImage().apply {

                }
            })

    fun sendAudio(audio: Uri) =
            connection.sendMessage(createNewMessage().apply {
                contentType = Message.Content.AUDIO.name
                contentAudio = ContentAudio().apply {

                }
            })

    fun sendVideo(video: Uri) =
            connection.sendMessage(createNewMessage().apply {
                contentType = Message.Content.VIDEO.name
                contentVideo = ContentVideo().apply {

                }
            })

    fun sendVoice(voice: Uri) =
            connection.sendMessage(createNewMessage().apply {
                contentType = Message.Content.VOICE.name
                contentVoice = ContentVoice().apply {

                }
            })

    fun sendLocation(location: Uri) =
            connection.sendMessage(createNewMessage().apply {
                contentType = Message.Content.LOCATION.name
                contentLocation = ContentLocation().apply {
                    this.latitude = 0F
                    this.longitude = 0F
                }
            })

    fun sendWeb(web: Uri) =
            connection.sendMessage(createNewMessage().apply {
                contentType = Message.Content.WEB.name
                contentWeb = ContentWeb().apply {

                }
            })

    fun readMessage(message: Message) =
            connection.sendMessage(message.apply {
                status = Message.Status.READ.name
            })

    override fun fetchById(conversationId: String): Observable<Conversation> = never()

}