package test.p00.presentation.onboarding

import io.reactivex.disposables.CompositeDisposable
import test.p00.data.repository.onboarding.OnBoardingRepository
import test.p00.data.repository.onboarding.OnBoardingRepositoryFactory
import test.p00.presentation.onboarding.model.OnBoardingPageModel
import test.p00.presentation.onboarding.model.OnBoardingModel
import test.p00.util.reactivex.Schedulers
import test.p00.util.reactivex.ObservableTransformers

class OnBoardingPresenterImpl(
        private val scheduler: Schedulers = Schedulers.create(),
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
                        OnBoardingModel(onBoarding.backgroundUri, onBoarding.pages.map {
                            page -> OnBoardingPageModel(page.message, page.contentUri) })) }, { }) }

}