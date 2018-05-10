package test.p00.presentation.conversation.impl.adapter.holder

import android.view.View
import android.widget.TextView
import kotterknife.bindView
import test.p00.R
import test.p00.presentation.model.conversation.MessageModel

class ConversationMessageTextViewHolder(itemView: View): ConversationMessageViewHolder(itemView) {

    private val vText: TextView by bindView(R.id.text123)

    override fun bindMessage(model: MessageModel) {
        vText.text = model.text
    }

}