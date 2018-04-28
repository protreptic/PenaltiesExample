package test.p00.presentation.conversation.impl

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.VERTICAL
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotterknife.bindView
import test.p00.R
import test.p00.data.websocket.Connection
import test.p00.presentation.activity.abs.AbsFragment
import test.p00.presentation.conversation.ConversationPresenter
import test.p00.presentation.conversation.ConversationView
import test.p00.presentation.conversation.impl.adapter.ConversationAdapter
import test.p00.presentation.model.conversation.ConversationModel
import test.p00.presentation.model.conversation.MessageModel
import test.p00.util.reactivex.CompletableTransformers

/**
 * Created by Peter Bukhal on 4/27/18.
 */
class ConversationFragment : AbsFragment(), ConversationView, ConversationAdapter.Delegate {

    companion object {

        const val FRAGMENT_TAG = "tag_ConversationFragment"
        const val FRAGMENT_ARG_CONVERSATION = "arg_conversation"

        fun newInstance(conversationId: String): Fragment = ConversationFragment().apply {
            arguments = Bundle().apply {
                putString(FRAGMENT_ARG_CONVERSATION, conversationId)
            }
        }

    }

    private val presenter: ConversationPresenter by lazy {
        ConversationPresenterImpl(router = ConversationRouterImpl(fragmentManager, this))
    }

    override val targetLayout: Int = R.layout.view_conversation

    private val vConversationMessages: RecyclerView by bindView(R.id.vConversationMessages)
    private val vConversationMessage: EditText by bindView(R.id.vConversationMessage)
    private val vConversationMessageSend: View by bindView(R.id.vConversationMessageSend)
    private val vConversationMembers: View by bindView(R.id.vConversationMembers)

    private val messageAdapter by lazy { ConversationAdapter(delegate = this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vConversationMessages.apply {
            layoutManager = LinearLayoutManager(context, VERTICAL, true)
            adapter = messageAdapter
        }

        vConversationMembers.setOnClickListener {
            presenter.displayMembers()
        }

        vConversationMessageSend.setOnClickListener {
            if (vConversationMessage.text.isNotEmpty()) {
                presenter.sendMessage(vConversationMessage.text.toString())
            }

            vConversationMessage.setText("")
        }

        presenter.attachView(this)
    }

    override fun showConversation(conversation: ConversationModel) {
        disposables.add(
            messageAdapter
                .changeData(conversation.messages)
                .compose(CompletableTransformers.schedulers())
                .subscribe { messageAdapter.notifyDataSetChanged() })
    }

    override fun onMessageRead(message: MessageModel) {
        presenter.readMessage(message)
    }

    override fun showMessage(message: MessageModel) {
        disposables.add(
            messageAdapter
                .addMessage(message)
                .subscribe { vConversationMessages.scrollToPosition(0) })
    }

    override fun showConnectionStatus(status: Connection.Status) {
        Toast.makeText(context, "Статус соединения: ${status.name}", Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        presenter.detachView()
    }

}