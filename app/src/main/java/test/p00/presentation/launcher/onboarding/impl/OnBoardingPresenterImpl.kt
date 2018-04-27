package test.p00.presentation.launcher.onboarding.impl

import io.reactivex.disposables.CompositeDisposable
import test.p00.domain.launcher.onboarding.OnBoardingInteractor
import test.p00.presentation.launcher.onboarding.OnBoardingPresenter
import test.p00.presentation.launcher.onboarding.OnBoardingRouter
import test.p00.presentation.launcher.onboarding.OnBoardingView
import test.p00.presentation.launcher.onboarding.model.OnBoardingModel
import test.p00.util.reactivex.ObservableTransformers
import test.p00.util.reactivex.Schedulers

class OnBoardingPresenterImpl(
        private val scheduler: Schedulers = Schedulers.create(),
        private val router: OnBoardingRouter,
        private val interactor: OnBoardingInteractor = OnBoardingInteractor()) : OnBoardingPresenter {

    private lateinit var attachedView: OnBoardingView
    private val disposables = CompositeDisposable()

    override fun attachView(view: OnBoardingView) {
        attachedView = view

        displayOnBoarding()
    }

    override fun displayOnBoarding() {
        disposables.add(
            interactor
                .displayOnBoarding()
                .map(OnBoardingModel.Mapper::map)
                .compose(ObservableTransformers.schedulers(scheduler))
                .subscribe({ attachedView.displayOnBoarding(it) }, { })) }

    override fun closeOnBoarding() {
        router.toHome()
    }

    override fun detachView() {
        disposables.dispose()
    }

}