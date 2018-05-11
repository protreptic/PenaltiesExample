package test.p00.data.storage.websocket

import android.util.Log
import com.google.gson.GsonBuilder
import io.reactivex.subjects.PublishSubject
import okhttp3.*
import test.p00.data.model.conversation.Member
import test.p00.data.model.conversation.message.Author
import test.p00.data.model.conversation.message.Message
import test.p00.data.storage.websocket.WebSocketConnection.Status.*
import test.p00.data.storage.websocket.model.RemoteContentLocation
import test.p00.data.storage.websocket.model.message.RemoteMessage

/**
 * Created by Peter Bukhal on 4/28/18.
 */
class WebSocketConnection {

    enum class Status {

        OPEN,
        FAILURE,
        CLOSING,
        CLOSED

    }

    companion object {

        val connection by lazy { WebSocketConnection() }

    }

    val status: PublishSubject<Status> = PublishSubject.create<Status>()
    val messages: PublishSubject<RemoteMessage> = PublishSubject.create<RemoteMessage>()

    private val gson =
            GsonBuilder()
                    .registerTypeAdapter(Author::class.java, AuthorSerializer())
                    .registerTypeAdapter(Author::class.java, AuthorDeserializer())
                    .registerTypeAdapter(Member::class.java, MemberSerializer())
                    .registerTypeAdapter(Member::class.java, MemberDeserializer())
                    .registerTypeAdapter(Message::class.java, MessageSerializer())
                    .registerTypeAdapter(Message::class.java, MessageDeserializer())
                    .create()

    private var webSocket: WebSocket? = null
    private val webSocketListener = object : WebSocketListener() {

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            Log.d("WEB_SOCKET", "STATUS: FAILURE")

            status.onNext(FAILURE)
        }

        override fun onOpen(webSocket: WebSocket, response: Response) {
            Log.d("WEB_SOCKET", "STATUS: OPEN")

            status.onNext(OPEN)
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            try {
                onMessage(gson.fromJson(text, RemoteMessage::class.java))
            } catch (e: Exception) {
                //
            }
        }

        fun onMessage(message: RemoteMessage) {
            Log.d("WEB_SOCKET", "RAW MESSAGE: $message")

            messages.onNext(message)
        }

        override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
            Log.d("WEB_SOCKET", "STATUS: CLOSING")

            status.onNext(CLOSING)
        }

        override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
            Log.d("WEB_SOCKET", "STATUS: CLOSED")

            status.onNext(CLOSED)
        }

    }

    fun joinConversation(conversationId: String) {
        synchronized(connection) {
            try {
                webSocket?.close(1000, "")
                webSocket =
                    OkHttpClient().newWebSocket(
                        Request.Builder()
                            .url("ws://fruit-chat.herokuapp.com/")
                            .addHeader("X-Conversation-Key", "~")
                            .addHeader("X-Conversation-Id", conversationId)
                            .build(), webSocketListener)
            } catch (e: Exception) {
                //
            }
        }
    }

    fun sendMessage(newMessage: Message) {
        synchronized(connection) {
            try {
                webSocket?.send(gson.toJson(RemoteMessage().apply {
                    status = 0
                    author = newMessage.author?.name
                    message = newMessage.contentText?.text

                    contentType = newMessage.contentType

                    if (newMessage.contentText != null) {
                        contentText = newMessage.contentText?.text
                    }

                    if (newMessage.contentLocation != null) {
                        contentLocation = RemoteContentLocation(
                                newMessage.contentLocation?.latitude ?: 0F,
                                newMessage.contentLocation?.longitude ?: 0F)
                    }
                }))
            } catch (e: Exception) {
                //
            }
        }
    }

    fun quitConversation() {
        synchronized(connection) {
            try {
                webSocket?.close(1000, "")
                webSocket = null
            } catch (e: Exception) {
                //
            }
        }
    }

}