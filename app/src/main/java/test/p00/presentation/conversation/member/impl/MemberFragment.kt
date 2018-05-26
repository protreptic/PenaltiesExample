package test.p00.presentation.conversation.member.impl

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.widget.TextView
import kotterknife.bindView
import test.p00.R
import test.p00.domain.conversations.ConversationsInteractor
import test.p00.presentation.impl.abs.AbsView
import test.p00.presentation.conversation.impl.ConversationRouterImpl
import test.p00.presentation.conversation.member.MemberPresenter
import test.p00.presentation.conversation.member.MemberView
import test.p00.presentation.model.conversation.MemberModel
import javax.inject.Inject

/**
 * Created by Peter Bukhal on 4/28/18.
 */
class MemberFragment : AbsView(), MemberView {

    companion object {

        const val FRAGMENT_TAG = "tag_MemberFragment"
        const val FRAGMENT_ARG_CONVERSATION = "arg_conversation"
        const val FRAGMENT_ARG_MEMBER = "arg_member"

        fun newInstance(conversationId: String, memberId: String): Fragment = MemberFragment().apply {
            arguments = Bundle().apply {
                putString(FRAGMENT_ARG_CONVERSATION, conversationId)
                putString(FRAGMENT_ARG_MEMBER, memberId)
            }
        }

    }

    private val conversationId by lazy {
        arguments!!.getString(FRAGMENT_ARG_CONVERSATION)
    }

    private val memberId by lazy {
        arguments!!.getString(FRAGMENT_ARG_MEMBER)
    }

    @Inject lateinit var conversationsInteractor: ConversationsInteractor

    private val presenter: MemberPresenter by lazy {
        MemberPresenterImpl(
                conversationId,
                memberId,
                schedulers,
                ConversationRouterImpl(memberId, fragmentManager, this),
                conversationsInteractor)
    }

    override val targetLayout: Int = R.layout.view_conversation_member

    private val vText: TextView by bindView(R.id.text)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.attachView(this)
    }

    override fun showMember(member: MemberModel) {
        vText.text = member.name
    }

    override fun onDestroyView() {
        super.onDestroyView()

        presenter.detachView()
    }

}