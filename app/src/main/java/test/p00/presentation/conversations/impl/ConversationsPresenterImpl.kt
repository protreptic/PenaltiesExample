package test.p00.presentation.conversations.impl

import io.reactivex.disposables.CompositeDisposable
import test.p00.domain.conversations.ConversationsInteractor
import test.p00.domain.conversations.ConversationsInteractorFactory
import test.p00.presentation.conversations.ConversationsPresenter
import test.p00.presentation.conversations.ConversationsView
import test.p00.presentation.conversations.ConversationsRouter
import test.p00.presentation.model.conversation.ConversationModel
import test.p00.util.reactivex.transformers.ObservableTransformers
import test.p00.util.reactivex.schedulers.Schedulers

/**
 * Created by Peter Bukhal on 4/28/18.
 */
class ConversationsPresenterImpl(
        private val scheduler: Schedulers = Schedulers.create(),
        private val router: ConversationsRouter,
        private val conversationsInteractor: ConversationsInteractor = ConversationsInteractorFactory.create()):
        ConversationsPresenter {

    override var attachedView: ConversationsView? = null
    override var disposables = CompositeDisposable()

    override fun attachView(view: ConversationsView) {
        super.attachView(view)

        displayConversations()
    }

    override fun displayConversations() {
        disposables.add(
            conversationsInteractor
                .fetchConversations()
                .map { conversations -> conversations.map(ConversationModel.Mapper::map) }
                .compose(ObservableTransformers.schedulers(scheduler))
                .subscribe({ conversations -> attachedView?.showConversations(conversations) }, { }))
    }

    override fun displayConversation(conversation: ConversationModel) {
        router.toConversation(conversation)
    }

}