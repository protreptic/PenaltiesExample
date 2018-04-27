package test.p00.presentation.launcher.onboarding.impl

import io.reactivex.disposables.CompositeDisposable
import test.p00.domain.launcher.onboarding.OnBoardingInteractor
import test.p00.presentation.launcher.onboarding.OnBoardingPresenter
import test.p00.presentation.launcher.onboarding.OnBoardingRouter
import test.p00.presentation.launcher.onboarding.OnBoardingView
import test.p00.presentation.model.onboarding.OnBoardingModel
import test.p00.util.reactivex.ObservableTransformers
import test.p00.util.reactivex.Schedulers

class OnBoardingPresenterImpl(
        private val scheduler: Schedulers = Schedulers.create(),
        private val router: OnBoardingRouter,
        private val interactor: OnBoardingInteractor = OnBoardingInteractor()) : OnBoardingPresenter {

    override lateinit var attachedView: OnBoardingView
    override val disposables = CompositeDisposable()

    override fun attachView(view: OnBoardingView) {
        super.attachView(view)

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

}