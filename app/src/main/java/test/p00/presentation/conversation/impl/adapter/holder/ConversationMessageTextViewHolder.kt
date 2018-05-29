package test.p00.presentation.conversation.impl.adapter.holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.*
import kotterknife.bindView
import test.p00.R
import test.p00.presentation.model.conversation.message.MessageModel

class ConversationMessageTextViewHolder(itemView: View): ConversationMessageViewHolder(itemView) {

    private val vPhoto: ImageView by bindView(R.id.vPhoto)
    private val vAuthor: TextView by bindView(R.id.vAuthor)
    private val vText: TextView by bindView(R.id.vText)
    private val vDate: TextView by bindView(R.id.vDate)

    override fun bind(model: MessageModel) {
        Glide.with(itemView.context)
                .load("http://via.placeholder.com/36x36")
                .apply(circleCropTransform())
                .into(vPhoto)

        vAuthor.text = model.author.name
        vText.text = model.text
        vDate.text = "12:56"
    }

}