package test.p00.presentation.conversation.impl.adapter.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import test.p00.presentation.model.conversation.message.MessageModel

open class ConversationMessageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    open fun bindMessage(model: MessageModel) {}

}