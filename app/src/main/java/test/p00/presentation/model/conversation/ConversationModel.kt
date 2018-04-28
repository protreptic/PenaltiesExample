package test.p00.presentation.model.conversation

import test.p00.data.model.conversation.Conversation

/**
 * Created by Peter Bukhal on 4/28/18.
 */
data class ConversationModel(val id: String, val members: List<MemberModel>) {

    var messages: MutableList<MessageModel> = mutableListOf()

    object Mapper {

        fun map(conversation: Conversation) =
                ConversationModel(
                        conversation.id,
                        conversation.members.map(MemberModel.Mapper::map)).apply {
                        conversation.messages.map(MessageModel.Mapper::map) }

    }

}