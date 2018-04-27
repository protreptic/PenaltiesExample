package test.p00.presentation.launcher.onboarding.impl

import io.reactivex.disposables.CompositeDisposable
import test.p00.data.repository.onboarding.OnBoardingRepository
import test.p00.data.repository.onboarding.OnBoardingRepositoryFactory
import test.p00.presentation.launcher.onboarding.OnBoardingPresenter
import test.p00.presentation.launcher.onboarding.OnBoardingRouter
import test.p00.presentation.launcher.onboarding.OnBoardingView
import test.p00.presentation.launcher.onboarding.model.OnBoardingModel
import test.p00.util.reactivex.ObservableTransformers
import test.p00.util.reactivex.Schedulers

class OnBoardingPresenterImpl(
        private val scheduler: Schedulers = Schedulers.create(),
        private val router: OnBoardingRouter,
        private val repository: OnBoardingRepository = OnBoardingRepositoryFactory.create()) : OnBoardingPresenter {

    private lateinit var attachedView: OnBoardingView
    private val disposables = CompositeDisposable()

    override fun attachView(view: OnBoardingView) {
        attachedView = view

        displayOnBoarding()
    }

    override fun detachView() {
        disposables.dispose()
    }

    override fun displayOnBoarding() {
        repository
                .fetch()
                .compose(ObservableTransformers.schedulers(scheduler))
                .subscribe({
                    onBoarding -> attachedView.displayOnBoarding(
                        OnBoardingModel.Mapper.map(onBoarding)) }, { }) }

    override fun closeOnBoarding() {
        router.toHome()
    }

}