package test.p00.data.websocket

import android.util.Log
import com.google.gson.GsonBuilder
import io.reactivex.subjects.PublishSubject
import okhttp3.*
import test.p00.data.model.conversation.message.Message
import test.p00.data.websocket.Connection.Status.*

/**
 * Created by Peter Bukhal on 4/28/18.
 */
class Connection {

    enum class Status {

        OPEN, FAILURE, CLOSING, CLOSED

    }

    companion object {

        val connection by lazy { Connection() }

    }

    val status = PublishSubject.create<Status>()
    val messages = PublishSubject.create<RawMessage>()

    private val gson = GsonBuilder().create()

    private lateinit var webSocket: WebSocket
    private val webSocketListener = object : WebSocketListener() {

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            status.onNext(FAILURE)

            Log.d("WEB_SOCKET", "STATUS: FAILURE")
        }

        override fun onOpen(webSocket: WebSocket, response: Response) {
            status.onNext(OPEN)

            Log.d("WEB_SOCKET", "STATUS: OPEN")
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            val message = gson.fromJson<RawMessage>(text, RawMessage::class.java)

            Log.d("WEB_SOCKET", "RAW MESSAGE: $message")

            messages.onNext(message)
        }

        override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
            status.onNext(CLOSING)

            Log.d("WEB_SOCKET", "STATUS: CLOSING")
        }

        override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
            status.onNext(CLOSED)

            Log.d("WEB_SOCKET", "STATUS: CLOSED")
        }

    }

    fun open() {
        webSocket = OkHttpClient()
                        .newWebSocket(Request.Builder()
                        .url("ws://fruit-chat.herokuapp.com/")
                        .build(), webSocketListener)
    }

    fun sentMessage(message: Message) {
        webSocket.send(
            gson.toJson(
                    RawMessage(
                            message.author?.name,
                            message.contentText?.text)))
    }

    fun readMessage(message: Message) {
        webSocket.send(
            gson.toJson(
                RawMessage(
                        message.author?.name,
                        message.contentText?.text)
                        .apply { status = 2 }))
    }

    fun close() {
        webSocket.close(1000, "")
    }

}