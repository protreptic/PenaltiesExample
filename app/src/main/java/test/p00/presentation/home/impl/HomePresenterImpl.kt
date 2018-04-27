package test.p00.presentation.home.impl

import io.reactivex.disposables.CompositeDisposable
import test.p00.data.repository.user.UserRepository
import test.p00.data.repository.user.UserRepositoryFactory
import test.p00.presentation.home.HomePresenter
import test.p00.presentation.home.HomeView
import test.p00.presentation.home.model.UserModel.Mapper
import test.p00.util.reactivex.ObservableTransformers

class HomePresenterImpl(
        private val userRepository: UserRepository =
                                    UserRepositoryFactory.create()) : HomePresenter {

    override lateinit var attachedView: HomeView
    override val disposables = CompositeDisposable()

    override fun attachView(view: HomeView) {
        super.attachView(view)

        displayUser()
    }

    override fun displayUser() {
        userRepository
            .fetch()
            .map { user -> Mapper.map(user) }
            .compose(ObservableTransformers.schedulers())
            .subscribe({ user -> attachedView.showUser(user) }, { })
    }

}