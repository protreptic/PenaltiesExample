package test.p00.presentation.conversations.impl.adapter

import android.support.v7.util.DiffUtil
import android.support.v7.util.DiffUtil.calculateDiff
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotterknife.bindView
import test.p00.R
import test.p00.presentation.conversations.impl.adapter.ConversationsAdapter.ConversationViewHolder
import test.p00.presentation.model.conversation.ConversationModel

/**
 * Created by Peter Bukhal on 4/28/18.
 */
class ConversationsAdapter(
        private var data: List<ConversationModel> = listOf(),
        private var delegate: Delegate? = null) : RecyclerView.Adapter<ConversationViewHolder>() {

    class Diff(private val old: List<ConversationModel>,
               private val new: List<ConversationModel>) : DiffUtil.Callback() {

        override fun getOldListSize() = old.size
        override fun getNewListSize() = new.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val old = old[oldItemPosition]
            val new = new[newItemPosition]

            return old.id == new.id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val old = old[oldItemPosition]
            val new = new[newItemPosition]

            return old == new
        }

    }

    interface Delegate {

        /**
         * Событие наступает когда пользователь
         * выбирает беседу из списка бесед.
         *
         * @param conversation выбранная беседа
         */
        fun onConversationPicked(conversation: ConversationModel)

    }

    fun changeData(newData: List<ConversationModel>): Completable =
            Observable.just(newData)
                      .map { calculateDiff(Diff(data, newData), false) }
                      .observeOn(AndroidSchedulers.mainThread())
                      .doOnNext { data = newData }
                      .doOnNext { result -> result.dispatchUpdatesTo(this) }
                      .ignoreElements()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ConversationViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_conversations_conversation, parent, false))

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ConversationViewHolder, position: Int) {
        val conversation = data[position]

        holder.bindConversation(conversation)
        holder.itemView.setOnClickListener {
            delegate?.onConversationPicked(conversation)
        }
    }

    class ConversationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val vText: TextView by bindView(R.id.text)

        fun bindConversation(conversation: ConversationModel) {
            vText.text = conversation.id
        }

    }

}