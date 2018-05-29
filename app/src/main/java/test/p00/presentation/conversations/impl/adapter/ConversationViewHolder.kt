package test.p00.presentation.conversations.impl.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotterknife.bindView
import test.p00.R
import test.p00.presentation.conversations.impl.adapter.ConversationsAdapter.Delegate
import test.p00.presentation.model.conversation.ConversationModel

class ConversationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val vPhoto: ImageView by bindView(R.id.vPhoto)
    private val vName: TextView by bindView(R.id.vName)

    fun bind(conversation: ConversationModel, delegate: Delegate?) {
        Glide.with(itemView.context)
                .load("http://via.placeholder.com/56x56")
                .apply(RequestOptions.circleCropTransform())
                .into(vPhoto)

        vName.text = conversation.name

        itemView.setOnClickListener {
            delegate?.onConversationPicked(conversation)
        }
    }

}