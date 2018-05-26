package test.p00.presentation.conversation.members.impl

import io.reactivex.disposables.CompositeDisposable
import test.p00.domain.conversations.ConversationsInteractor
import test.p00.presentation.conversation.ConversationRouter
import test.p00.presentation.conversation.members.MembersPresenter
import test.p00.presentation.conversation.members.MembersView
import test.p00.presentation.model.conversation.ConversationModel
import test.p00.presentation.model.conversation.MemberModel
import test.p00.auxiliary.reactivex.schedulers.Schedulers
import test.p00.auxiliary.reactivex.transformers.ObservableTransformers
import javax.inject.Inject

/**
 * Created by Peter Bukhal on 4/28/18.
 */
class MembersPresenterImpl
    @Inject constructor(
        private val conversationId: String,
        private val scheduler: Schedulers,
        private val router: ConversationRouter,
        private val conversationsInteractor: ConversationsInteractor):
            MembersPresenter {

    override var attachedView: MembersView? = null
    override var disposables = CompositeDisposable()

    override fun attachView(view: MembersView) {
        super.attachView(view)

        displayMembers()
    }

    override fun displayMembers() {
        disposables.add(
            conversationsInteractor
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