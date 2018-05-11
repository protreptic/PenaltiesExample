package test.p00.presentation.conversation.impl.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater.from
import android.view.ViewGroup
import io.reactivex.Completable
import io.reactivex.Observable
import test.p00.R
import test.p00.presentation.conversation.impl.adapter.holder.*
import test.p00.presentation.model.conversation.ConversationModel
import test.p00.presentation.model.conversation.MessageModel
import java.util.*

/**
 * Created by Peter Bukhal on 4/28/18.
 */
class ConversationAdapter(
        private var delegate: Delegate? = null):
        RecyclerView.Adapter<ConversationMessageViewHolder>() {

    interface Delegate {

        /**
         * Событие наступает когда
         * пользователь прочитал сообщение
         * (отобразилось на экране).
         */
        fun onMessageRead(message: MessageModel)

    }

    private lateinit var conversation: ConversationModel
    private var data: LinkedList<MessageModel> = LinkedList()

    fun changeData(conversation: ConversationModel): Completable =
            Observable.just(conversation)
                      .doOnNext {
                          this.conversation = conversation
                          this.data = LinkedList(conversation.messages) }
                      .ignoreElements()

    fun addMessage(message: MessageModel): Completable =
            Observable.just(message)
                      .doOnNext {
                          when (data.contains(message)) {
                              true -> {
                                  val index = data.indexOf(message)

                                  data[index] = message

                                  notifyItemChanged(index)
                              }
                              else -> {
                                  data.addFirst(message)

                                  notifyItemInserted(0)
                              }}}
                      .ignoreElements()

    override fun getItemCount() = data.size

    override fun getItemViewType(position: Int) = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            when (viewType) {
                1 -> {
                    ConversationMessageTextViewHolder(from(parent.context)
                            .inflate(R.layout.view_conversation_message_text, parent, false))
                }
                2 -> {
                    ConversationMessageDocumentViewHolder(from(parent.context)
                            .inflate(R.layout.view_conversation_message_document, parent, false))
                }
                3 -> {
                    ConversationMessageImageViewHolder(from(parent.context)
                            .inflate(R.layout.view_conversation_message_image, parent, false))
                }
                4 -> {
                    ConversationMessageAudioViewHolder(from(parent.context)
                            .inflate(R.layout.view_conversation_message_audio, parent, false))
                }
                5 -> {
                    ConversationMessageVideoViewHolder(from(parent.context)
                            .inflate(R.layout.view_conversation_message_video, parent, false))
                }
                6 -> {
                    ConversationMessageVoiceViewHolder(from(parent.context)
                            .inflate(R.layout.view_conversation_message_voice, parent, false))
                }
                7 -> {
                    ConversationMessageLocationViewHolder(from(parent.context)
                            .inflate(R.layout.view_conversation_message_location, parent, false))
                }
                8 -> {
                    ConversationMessageWebViewHolder(from(parent.context)
                            .inflate(R.layout.view_conversation_message_web, parent, false))
                }
                else -> {
                    ConversationMessageViewHolder(from(parent.context)
                            .inflate(R.layout.view_conversation_message_default, parent, false))
                }
            }

    override fun onBindViewHolder(holder: ConversationMessageViewHolder, position: Int) {
        val message = data[position]

        holder.bindMessage(message).also {
            if (!message.isRead) {
                delegate?.onMessageRead(message)
            }
        }
    }

}