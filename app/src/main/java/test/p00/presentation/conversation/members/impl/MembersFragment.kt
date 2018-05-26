package test.p00.presentation.conversation.members.impl

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import kotterknife.bindView
import test.p00.R
import test.p00.domain.conversations.ConversationsInteractor
import test.p00.presentation.activity.abs.AbsFragment
import test.p00.presentation.conversation.impl.ConversationRouterImpl
import test.p00.presentation.conversation.members.MembersPresenter
import test.p00.presentation.conversation.members.MembersView
import test.p00.presentation.conversation.members.impl.adapter.MembersAdapter
import test.p00.presentation.model.conversation.MemberModel
import javax.inject.Inject

/**
 * Created by Peter Bukhal on 4/28/18.
 */
class MembersFragment : AbsFragment(), MembersView, MembersAdapter.Delegate {

    companion object {

        const val FRAGMENT_TAG = "tag_MembersFragment"
        const val FRAGMENT_ARG_CONVERSATION = "arg_conversation"

        fun newInstance(conversationId: String): Fragment = MembersFragment().apply {
            arguments = Bundle().apply {
                putString(FRAGMENT_ARG_CONVERSATION, conversationId)
            }
        }

    }

    private val conversationId by lazy {
        arguments!!.getString(FRAGMENT_ARG_CONVERSATION)
    }

    @Inject lateinit var conversationsInteractor: ConversationsInteractor

    private val presenter: MembersPresenter by lazy {
        MembersPresenterImpl(
                conversationId,
                schedulers,
                ConversationRouterImpl(conversationId, fragmentManager, this),
                conversationsInteractor)
    }

    override val targetLayout: Int = R.layout.view_conversation_members

    private val members: RecyclerView by bindView(R.id.vMembers)
    private val membersAdapter by lazy { MembersAdapter(delegate = this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        members.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = membersAdapter
        }

        presenter.attachView(this)
    }

    override fun showMembers(members: List<MemberModel>) {
        disposables.add(
            membersAdapter
                .changeData(members)
                .subscribeOn(schedulers.background())
                .subscribe())
    }

    override fun onMemberPicked(member: MemberModel) {
        presenter.displayMember(member)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        presenter.detachView()
    }

}