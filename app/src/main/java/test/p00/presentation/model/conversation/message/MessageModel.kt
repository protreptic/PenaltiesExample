package test.p00.presentation.model.conversation.message

import test.p00.data.model.conversation.message.Message

/**
 * Created by Peter Bukhal on 4/28/18.
 */
data class MessageModel(
        val id: String,
        val text: String?,
        val isRead: Boolean = false,
        val author: AuthorModel,
        val addedAt: Long = 0) {

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    object Mapper {

        fun map(message: Message) =
                MessageModel(
                        message.id,
                        message.contentText?.text,
                        (message.status == Message.Status.READ.name),
                        AuthorModel.Mapper.map(message.author!!),
                        message.addedAt)

    }

}