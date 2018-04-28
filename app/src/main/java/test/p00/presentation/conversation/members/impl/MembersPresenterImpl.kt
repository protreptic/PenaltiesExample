package test.p00.presentation.conversation.members.impl

import io.reactivex.disposables.CompositeDisposable
import test.p00.domain.conversation.ConversationInteractor
import test.p00.domain.conversation.ConversationInteractorFactory
import test.p00.presentation.conversation.ConversationRouter
import test.p00.presentation.conversation.members.MembersPresenter
import test.p00.presentation.conversation.members.MembersView
import test.p00.presentation.model.conversation.ConversationModel
import test.p00.presentation.model.conversation.MemberModel
import test.p00.util.reactivex.ObservableTransformers
import test.p00.util.reactivex.Schedulers

/**
 * Created by Peter Bukhal on 4/28/18.
 */
class MembersPresenterImpl(
        private val scheduler: Schedulers = Schedulers.create(),
        private val router: ConversationRouter,
        private val conversationInteractor: ConversationInteractor = ConversationInteractorFactory.create()) : MembersPresenter {

    override var attachedView: MembersView? = null
    override var disposables = CompositeDisposable()

    override fun attachView(view: MembersView) {
        super.attachView(view)

        displayMembers("")
    }

    override fun displayMembers(conversationId: String) {
        disposables.add(
            conversationInteractor
                .fetchConversation(conversationId)
                .map(ConversationModel.Mapper::map)
                .map { conversation -> conversation.members }
                .compose(ObservableTransformers.schedulers(scheduler))
                .subscribe({ members -> attachedView?.showMembers(members) }, { }))
    }

    override fun displayMember(member: MemberModel) {
        router.toMember(member)
    }

}