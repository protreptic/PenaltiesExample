package test.p00.presentation.conversation.member

import test.p00.presentation.Presenter

/**
 * Created by Peter Bukhal on 4/28/18.
 */
interface MemberPresenter : Presenter<MemberView> {

    fun displayMember()

}