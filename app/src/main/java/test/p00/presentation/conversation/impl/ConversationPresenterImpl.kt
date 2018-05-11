package test.p00.presentation.conversation.impl

import android.net.Uri
import io.reactivex.disposables.CompositeDisposable
import test.p00.data.model.conversation.message.Message
import test.p00.domain.conversation.ConversationInteractor
import test.p00.domain.conversation.ConversationInteractorFactory
import test.p00.domain.conversations.ConversationsInteractor
import test.p00.domain.conversations.ConversationsInteractorFactory
import test.p00.presentation.conversation.ConversationPresenter
import test.p00.presentation.conversation.ConversationRouter
import test.p00.presentation.conversation.ConversationView
import test.p00.presentation.model.conversation.ConversationModel
import test.p00.presentation.model.conversation.MemberModel
import test.p00.presentation.model.conversation.MessageModel
import test.p00.util.reactivex.ObservableTransformers
import test.p00.util.reactivex.Schedulers

/**
 * Created by Peter Bukhal on 4/27/18.
 */
class ConversationPresenterImpl(
        private val conversationId: String,
        private val scheduler: Schedulers = Schedulers.create(),
        private val router: ConversationRouter,
        private val conversationsInteractor: ConversationsInteractor = ConversationsInteractorFactory.create(),
        private val conversationInteractor: ConversationInteractor = ConversationInteractorFactory.create(conversationId)) :
        ConversationPresenter {

    override var attachedView: ConversationView? = null
    override var disposables = CompositeDisposable()

    override fun attachView(view: ConversationView) {
        super.attachView(view)

        displayConversation()
    }

    override fun displayConversation() {
        conversationInteractor.joinConversation()

        disposables.add(
            conversationsInteractor
                .fetchConversation(conversationId)
                .map(ConversationModel.Mapper::map)
                .compose(ObservableTransformers.schedulers(scheduler))
                .subscribe({ conversation -> attachedView?.showConversation(conversation) }, { }))

        disposables.add(
            conversationInteractor
                .watchOnConversation()
                .map(MessageModel.Mapper::map)
                .compose(ObservableTransformers.schedulers(scheduler))
                .subscribe({ message -> attachedView?.showMessage(message) }, { }))

        disposables.add(
            conversationInteractor
                .watchOnConnection()
                .compose(ObservableTransformers.schedulers(scheduler))
                .subscribe({ status -> attachedView?.showConnectionStatus(status) }, { }))
    }

    override fun quitConversation() {
        conversationInteractor.quitConversation()
    }

    override fun displayMember(member: MemberModel) {
        router.toMember(member)
    }

    override fun displayMembers() {
        router.toMembers()
    }

    override fun sendMessageText(messageText: String) {
        conversationInteractor.sendText(messageText)
    }

    override fun sendMessageLocation(latitude: Float, longitude: Float) {
        conversationInteractor.sendLocation(Uri.parse(String.format("geo:%f,%f", latitude, longitude)))
    }

    override fun readMessage(message: MessageModel) {
        conversationInteractor.readMessage(Message())
    }

    override fun detachView() {
        super.detachView()

        quitConversation()
    }

}