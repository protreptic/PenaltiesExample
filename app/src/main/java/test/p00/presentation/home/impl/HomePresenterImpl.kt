package test.p00.presentation.home.impl

import io.reactivex.disposables.CompositeDisposable
import test.p00.domain.home.HomeInteractor
import test.p00.presentation.home.HomePresenter
import test.p00.presentation.home.HomeView
import test.p00.presentation.model.user.UserModel.Mapper
import test.p00.util.reactivex.ObservableTransformers

class HomePresenterImpl(
        private val homeInteractor: HomeInteractor = HomeInteractor()) : HomePresenter {

    override lateinit var attachedView: HomeView
    override val disposables = CompositeDisposable()

    override fun attachView(view: HomeView) {
        super.attachView(view)

        displayUser()
    }

    override fun displayUser() {
        disposables.add(
            homeInteractor
                .displayUser()
                .map { user -> Mapper.map(user) }
                .compose(ObservableTransformers.schedulers())
                .subscribe({ user -> attachedView.showUser(user) }, { })) }

}