package test.p00.presentation.conversation.impl.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.reactivex.Completable
import io.reactivex.Observable
import kotterknife.bindView
import test.p00.R
import test.p00.presentation.conversation.impl.adapter.ConversationAdapter.ConversationViewHolder
import test.p00.presentation.model.conversation.MessageModel
import java.util.*

/**
 * Created by Peter Bukhal on 4/28/18.
 */
class ConversationAdapter(
        private var data: LinkedList<MessageModel> = LinkedList(),
        private var delegate: Delegate? = null) : RecyclerView.Adapter<ConversationViewHolder>() {

    interface Delegate {

        /**
         * Событие наступает когда
         * пользователь прочитал сообщение.
         */
        fun onMessageRead(message: MessageModel)

    }

    fun changeData(newData: List<MessageModel>): Completable =
            Observable.just(newData)
                      .doOnNext { data = LinkedList(newData) }
                      .ignoreElements()

    fun addMessage(message: MessageModel): Completable =
            Observable.just(message)
                      .map {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ConversationViewHolder(LayoutInflater.from(parent.context)
                      .inflate(R.layout.view_conversation_message_text, parent, false))

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ConversationViewHolder, position: Int) {
        val message = data[position]

        holder.bindMessage(message).also {
            if (!message.isRead) {
                delegate?.onMessageRead(message)
            }
        }
    }

    class ConversationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val vText: TextView by bindView(R.id.text123)

        fun bindMessage(model: MessageModel) {
            vText.text = model.text
        }

    }

}