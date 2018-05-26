package test.p00.presentation.home.impl

import io.reactivex.disposables.CompositeDisposable
import test.p00.domain.home.HomeInteractor
import test.p00.presentation.home.HomePresenter
import test.p00.presentation.home.HomeRouter
import test.p00.presentation.home.HomeView
import test.p00.presentation.model.user.UserModel.Mapper
import test.p00.auxiliary.reactivex.schedulers.Schedulers
import test.p00.auxiliary.reactivex.transformers.ObservableTransformers
import javax.inject.Inject

class HomePresenterImpl
    @Inject constructor(
        private val schedulers: Schedulers,
        private val route: HomeRouter,
        private val homeInteractor: HomeInteractor):
            HomePresenter {

    override var attachedView: HomeView? = null
    override var disposables = CompositeDisposable()

    override fun attachView(view: HomeView) {
        super.attachView(view)

        displayUser()
    }

    override fun displayUser() {
        disposables.add(
            homeInteractor
                .fetchUser()
                .map { user -> Mapper.map(user) }
                .compose(ObservableTransformers.schedulers(schedulers))
                .subscribe({ user -> attachedView?.showUser(user) }, { }))
    }

    override fun displayConversations() {
        route.toConversations()
    }

}