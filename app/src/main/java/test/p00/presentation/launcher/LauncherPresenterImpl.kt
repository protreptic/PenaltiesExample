package test.p00.presentation.launcher

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import test.p00.domain.LauncherInteractor
import test.p00.util.reactivex.CompletableTransformers
import test.p00.util.reactivex.Schedulers
import java.util.concurrent.TimeUnit.MILLISECONDS

class LauncherPresenterImpl(
        private val scheduler: Schedulers = Schedulers.create(),
        private val launcher: LauncherInteractor = LauncherInteractor()) : LauncherPresenter {

    companion object {

        const val APP_LAUNCH_DELAY = 1000L
    }

    private lateinit var attachedView: LauncherView
    private val disposables = CompositeDisposable()

    override fun attachView(view: LauncherView) {
        attachedView = view

        launchApp()
    }

    override fun launchApp() {
        disposables.add(
            Observable
                .timer(APP_LAUNCH_DELAY, MILLISECONDS, scheduler.ui())
                .flatMap { launcher.shouldShowOnBoardingWizard() }
                .flatMap { shouldShow ->
                    if (shouldShow) {
                        runOnBoardingWizard()

                        Observable.never()
                    } else {
                        launcher.shouldShowOnBoarding()
                    }
                }
                .flatMap { shouldShow ->
                    if (shouldShow) {
                        runOnBoarding()

                        Observable.never()
                    } else {
                        Observable.just(true)
                    }
                }
                .subscribe({ attachedView.runMain() }, { }))
    }

    override fun runOnBoardingWizard() {
        disposables.add(
            launcher.markOnBoardingWizardAsShown()
                    .compose(CompletableTransformers.schedulers(scheduler))
                    .subscribe({ attachedView.runOnBoardingWizard() }, { }))
    }

    override fun runOnBoarding() {
        disposables.add(
            launcher.markOnBoardingAsShown()
                    .compose(CompletableTransformers.schedulers(scheduler))
                    .subscribe({ attachedView.runOnBoarding() }, { }))
    }

    override fun detachView() {
        disposables.dispose()
    }

}