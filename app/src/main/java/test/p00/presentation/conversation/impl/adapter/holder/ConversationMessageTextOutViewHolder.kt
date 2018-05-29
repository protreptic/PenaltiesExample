package test.p00.presentation.conversation.impl.adapter.holder

import android.view.View
import android.widget.TextView
import kotterknife.bindView
import test.p00.R
import test.p00.presentation.model.conversation.message.MessageModel

class ConversationMessageTextOutViewHolder(itemView: View): ConversationMessageViewHolder(itemView) {

    private val vText: TextView by bindView(R.id.vText)
    private val vDate: TextView by bindView(R.id.vDate)

    override fun bind(model: MessageModel) {
        vText.text = model.text
        vDate.text = "22:13"
    }

}