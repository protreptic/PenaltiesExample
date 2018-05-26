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
import test.p00.auxiliary.reactivex.transformers.CompletableTransformers
import test.p00.data.storage.websocket.WebSocketConnection
import test.p00.domain.conversation.ConversationInteractor
import test.p00.domain.conversations.ConversationsInteractor
import test.p00.presentation.impl.abs.AbsView
import test.p00.presentation.auxiliary.dismissKeyboard
import test.p00.presentation.conversation.ConversationPresenter
import test.p00.presentation.conversation.ConversationView
import test.p00.presentation.conversation.impl.adapter.MessagesAdapter
import test.p00.presentation.model.conversation.ConversationModel
import test.p00.presentation.model.conversation.message.MessageModel
import javax.inject.Inject

/**
 * Created by Peter Bukhal on 4/27/18.
 */
class ConversationFragment : AbsView(), ConversationView, MessagesAdapter.Delegate {

    companion object {

        const val FRAGMENT_TAG = "tag_ConversationFragment"
        const val FRAGMENT_ARG_CONVERSATION = "arg_conversation"

        fun newInstance(conversationId: String): Fragment = ConversationFragment().apply {
            arguments = Bundle().apply {
                putString(FRAGMENT_ARG_CONVERSATION, conversationId)
            }
        }

    }

    private val conversationId by lazy {
        arguments!!.getString(FRAGMENT_ARG_CONVERSATION)
    }

    @Inject lateinit var conversationInteractor: ConversationInteractor
    @Inject lateinit var conversationsInteractor: ConversationsInteractor

    private val presenter: ConversationPresenter by lazy {
        ConversationPresenterImpl(
                conversationId,
                schedulers,
                ConversationRouterImpl(conversationId, fragmentManager, this),
                conversationsInteractor,
                conversationInteractor)
    }

    override val targetLayout: Int = R.layout.view_conversation

    private val vConversationMessage: EditText by bindView(R.id.vConversationMessage)
    private val vConversationMessageSend: View by bindView(R.id.vConversationMessageSend)
    private val vConversationMembers: View by bindView(R.id.vConversationMembers)

    private val messages: RecyclerView by bindView(R.id.vConversationMessages)
    private val messagesAdapter by lazy { MessagesAdapter(delegate = this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        messages.apply {
            layoutManager = LinearLayoutManager(context, VERTICAL, true)
            adapter = messagesAdapter
        }

        vConversationMembers.setOnClickListener {
            presenter.displayMembers()

            dismissKeyboard(activity)
        }

        vConversationMessage.apply {
            requestFocus()
        }

        vConversationMessageSend.setOnClickListener {
            if (vConversationMessage.text.isNotEmpty()) {
                presenter.sendMessageText(vConversationMessage.text.toString())
            }

            vConversationMessage.setText("")
        }

        presenter.attachView(this)
    }

    override fun showConversation(conversation: ConversationModel) {
        disposables.add(
            messagesAdapter
                .changeData(conversation)
                .compose(CompletableTransformers.schedulers())
                .subscribe { messagesAdapter.notifyDataSetChanged() })
    }

    override fun onMessageRead(message: MessageModel) {
        //presenter.readMessage(message)
    }

    override fun showMessage(message: MessageModel) {
        disposables.add(
            messagesAdapter
                .addMessage(message)
                .subscribe { messages.scrollToPosition(0) })
    }

    override fun showConnectionStatus(status: WebSocketConnection.Status) {
        Toast.makeText(context, "Статус соединения: ${status.name}", Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        presenter.detachView()
    }

}