package test.p00.presentation.conversation.member.impl

import io.reactivex.disposables.CompositeDisposable
import test.p00.auxiliary.reactivex.schedulers.Schedulers
import test.p00.auxiliary.reactivex.transformers.ObservableTransformers
import test.p00.domain.conversations.ConversationsInteractor
import test.p00.presentation.conversation.ConversationRouter
import test.p00.presentation.conversation.member.MemberPresenter
import test.p00.presentation.conversation.member.MemberView
import test.p00.presentation.model.conversation.ConversationModel

/**
 * Created by Peter Bukhal on 4/28/18.
 */
class MemberPresenterImpl(
        private val conversationId: String,
        private val memberId: String,
        private val scheduler: Schedulers,
        private val router: ConversationRouter,
        private val conversationsInteractor: ConversationsInteractor):
            MemberPresenter {

    override var attachedView: MemberView? = null
    override var disposables = CompositeDisposable()

    override fun attachView(view: MemberView) {
        super.attachView(view)

        displayMember()
    }

    override fun displayMember() {
        disposables.add(
            conversationsInteractor
                .fetchConversation(conversationId)
                .map(ConversationModel.Mapper::map)
                .map { conversation -> conversation.members.find { member -> member.id == memberId }}
                .compose(ObservableTransformers.schedulers(scheduler))
                .subscribe({ member -> attachedView?.showMember(member!!) }, { }))
    }

}