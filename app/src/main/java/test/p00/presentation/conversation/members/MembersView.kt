package test.p00.presentation.conversation.members

import test.p00.presentation.View
import test.p00.presentation.model.conversation.MemberModel

/**
 * Created by Peter Bukhal on 4/28/18.
 */
interface MembersView : View {

    /**
     * @param members
     */
    fun showMembers(members: List<MemberModel>)

}