package test.p00.presentation.launcher.impl

import io.reactivex.Observable.*
import io.reactivex.disposables.CompositeDisposable
import test.p00.auxiliary.reactivex.schedulers.Schedulers
import test.p00.domain.launcher.LauncherInteractor
import test.p00.presentation.launcher.LauncherPresenter
import test.p00.presentation.launcher.LauncherRouter
import test.p00.presentation.launcher.LauncherView
import java.util.concurrent.TimeUnit.MILLISECONDS

class LauncherPresenterImpl(
        private val scheduler: Schedulers,
        private val router: LauncherRouter,
        private val launcher: LauncherInteractor):
            LauncherPresenter {

    companion object {

        const val APP_LAUNCH_DELAY = 500L
    }

    override var attachedView: LauncherView? = null
    override var disposables = CompositeDisposable()

    override fun attachView(view: LauncherView) {
        super.attachView(view)

        launchApplication()
    }

    override fun launchApplication() {
        disposables.add(
            timer(APP_LAUNCH_DELAY, MILLISECONDS, scheduler.ui())
                .flatMap { launcher.shouldShowOnBoardingWizard() }
                .flatMap { shouldShow ->
                    when (shouldShow) {
                        true -> {
                            displayOnBoardingWizard()
                            never()
                        }
                        else -> launcher.shouldShowOnBoarding()
                    }
                }
                .flatMap { shouldShow ->
                    when (shouldShow) {
                        true -> {
                            displayOnBoarding()
                            never()
                        }
                        else -> just(true)
                    }
                }
                .subscribe(
                        { router.toHome() },
                        { /*  */ }))
    }

    override fun displayOnBoardingWizard() {
        router.toOnBoardingWizard()
    }

    override fun displayOnBoarding() {
        router.toOnBoarding()
    }

}