package test.p00.data.repository.conversation.datasource.impl

import io.reactivex.Observable
import test.p00.data.model.conversation.message.Author
import test.p00.data.model.conversation.message.Message
import test.p00.data.model.conversation.message.content.ContentText
import test.p00.data.websocket.Connection
import test.p00.data.websocket.Connection.Status

/**
 * Created by Peter Bukhal on 4/27/18.
 */
class CloudConversationDataSource(
        private val connection: Connection = Connection.connection) {

    init {
        connection.open()
    }

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
                    id = receivedMessage.author.hashCode()
                    name = receivedMessage.author
                }

                contentType = Message.Content.TEXT.name
                contentText = ContentText().apply {
                    text = receivedMessage.message
                }
            }}

    fun sendMessage(text: String) {
        connection.sentMessage(Message().apply {
            status = Message.Status.ADDED.name
            author?.name = "0123456789"

            addedAt = System.currentTimeMillis()

            contentType = Message.Content.TEXT.name
            contentText?.text = text
        })
    }

    fun readMessage(message: Message) {
        connection.readMessage(message)
    }

    fun watchOnConnection(): Observable<Status> =
        connection
            .status

    fun quitConversation() {
        connection.close()
    }

}