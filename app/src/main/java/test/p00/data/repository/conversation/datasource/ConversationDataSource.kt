package test.p00.data.repository.conversation.datasource

import android.net.Uri
import io.reactivex.Observable
import test.p00.data.model.conversation.Conversation
import test.p00.data.model.conversation.message.Message
import test.p00.data.storage.websocket.WebSocketConnection

/**
 * Created by Peter Bukhal on 4/27/18.
 */
interface ConversationDataSource {

    /**
     * Возвращает беседу по ее идентификатору.
     *
     * @param conversationId идентификатор беседы
     * @return беседа
     */
    fun fetchById(conversationId: String): Observable<Conversation>

    fun joinConversation()
    fun watchOnConversation(): Observable<Message>
    fun watchOnConnection(): Observable<WebSocketConnection.Status>
    fun quitConversation()

    fun sendText(text: String)
    fun sendDocument(document: Uri)
    fun sendImage(image: Uri)
    fun sendAudio(audio: Uri)
    fun sendVideo(video: Uri)
    fun sendVoice(voice: Uri)
    fun sendLocation(location: Uri)
    fun sendWeb(web: Uri)
    fun readMessage(message: Message)

}