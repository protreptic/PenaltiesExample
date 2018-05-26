package test.p00.presentation.conversation.members

import test.p00.presentation.Presenter
import test.p00.presentation.model.conversation.MemberModel

/**
 * Created by Peter Bukhal on 4/28/18.
 */
interface MembersPresenter : Presenter<MembersView> {

    fun displayMembers()

    /**
     *
     * @param member
     */
    fun displayMember(member: MemberModel)

}