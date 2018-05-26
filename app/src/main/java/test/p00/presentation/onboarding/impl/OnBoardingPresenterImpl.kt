package test.p00.presentation.onboarding.impl

import io.reactivex.disposables.CompositeDisposable
import test.p00.domain.onboarding.OnBoardingInteractor
import test.p00.presentation.model.onboarding.OnBoardingModel
import test.p00.presentation.onboarding.OnBoardingPresenter
import test.p00.presentation.onboarding.OnBoardingRouter
import test.p00.presentation.onboarding.OnBoardingView
import test.p00.auxiliary.reactivex.schedulers.Schedulers
import test.p00.auxiliary.reactivex.transformers.ObservableTransformers
import javax.inject.Inject

class OnBoardingPresenterImpl
    @Inject constructor(
        private val scheduler: Schedulers,
        private val router: OnBoardingRouter,
        private val interactor: OnBoardingInteractor):
            OnBoardingPresenter {

    override var attachedView: OnBoardingView? = null
    override var disposables = CompositeDisposable()

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
                .subscribe(
                        { attachedView?.displayOnBoarding(it) },
                        { /* игнорируем */ }))
    }

    override fun closeOnBoarding() {
        router.toLauncher()
    }

}