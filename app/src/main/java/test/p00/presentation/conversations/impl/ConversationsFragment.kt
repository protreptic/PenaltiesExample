package test.p00.presentation.conversations.impl

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import kotterknife.bindView
import test.p00.R
import test.p00.domain.conversations.ConversationsInteractor
import test.p00.presentation.activity.abs.AbsFragment
import test.p00.presentation.conversations.ConversationsPresenter
import test.p00.presentation.conversations.ConversationsView
import test.p00.presentation.conversations.impl.adapter.ConversationsAdapter
import test.p00.presentation.model.conversation.ConversationModel
import javax.inject.Inject

/**
 * Created by Peter Bukhal on 4/28/18.
 */
class ConversationsFragment : AbsFragment(), ConversationsView, ConversationsAdapter.Delegate {

    companion object {

        const val FRAGMENT_TAG = "tag_ConversationsFragment"

        fun newInstance(): Fragment = ConversationsFragment().apply {
            arguments = Bundle.EMPTY
        }

    }

    @Inject lateinit var conversationsInteractor: ConversationsInteractor

    private val presenter: ConversationsPresenter by lazy {
        ConversationsPresenterImpl(
                schedulers,
                ConversationsRouterImpl(fragmentManager, this),
                conversationsInteractor)
    }

    override val targetLayout: Int = R.layout.view_conversations

    private val conversations: RecyclerView by bindView(R.id.vConversations)
    private val conversationsAdapter by lazy { ConversationsAdapter(delegate = this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        conversations.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = conversationsAdapter
        }

        presenter.attachView(this)
    }

    override fun showConversations(conversations: List<ConversationModel>) {
        disposables.add(
            conversationsAdapter
                .changeData(conversations)
                .subscribeOn(schedulers.background())
                .subscribe { conversationsAdapter.notifyDataSetChanged() })
    }

    override fun onConversationPicked(conversation: ConversationModel) {
        presenter.displayConversation(conversation)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        presenter.detachView()
    }

}