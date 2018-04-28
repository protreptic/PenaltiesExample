package test.p00.presentation.conversation.member

import test.p00.presentation.abs.View
import test.p00.presentation.model.conversation.MemberModel

/**
 * Created by Peter Bukhal on 4/28/18.
 */
interface MemberView : View {

    /**
     * @param member
     */
    fun showMember(member: MemberModel)

}