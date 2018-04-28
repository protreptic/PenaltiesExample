package test.p00.presentation.model.conversation

import test.p00.data.model.conversation.Member

/**
 * Created by Peter Bukhal on 4/28/18.
 */
data class MemberModel(val id: String, val name: String) {

    object Mapper {

        fun map(member: Member) =
                MemberModel(member.id, member.name)

    }

}