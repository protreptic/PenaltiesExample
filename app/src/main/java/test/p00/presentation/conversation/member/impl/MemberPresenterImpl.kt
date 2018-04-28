package test.p00.presentation.conversation.member.impl

import io.reactivex.disposables.CompositeDisposable
import test.p00.domain.conversation.ConversationInteractor
import test.p00.domain.conversation.ConversationInteractorFactory
import test.p00.presentation.conversation.ConversationRouter
import test.p00.presentation.conversation.member.MemberPresenter
import test.p00.presentation.conversation.member.MemberView
import test.p00.presentation.model.conversation.ConversationModel
import test.p00.util.reactivex.ObservableTransformers
import test.p00.util.reactivex.Schedulers

/**
 * Created by Peter Bukhal on 4/28/18.
 */
class MemberPresenterImpl(
        private val scheduler: Schedulers = Schedulers.create(),
        private val router: ConversationRouter,
        private val conversationInteractor: ConversationInteractor = ConversationInteractorFactory.create()) : MemberPresenter {

    override var attachedView: MemberView? = null
    override var disposables = CompositeDisposable()

    override fun attachView(view: MemberView) {
        super.attachView(view)

        displayMember("1", "1")
    }

    override fun displayMember(conversationId: String, memberId: String) {
        disposables.add(
            conversationInteractor
                .fetchConversation(conversationId)
                .map(ConversationModel.Mapper::map)
                .map { conversation -> conversation.members.find { member -> member.id == memberId }}
                .compose(ObservableTransformers.schedulers(scheduler))
                .subscribe({ member -> attachedView?.showMember(member!!) }, { }))
    }

}