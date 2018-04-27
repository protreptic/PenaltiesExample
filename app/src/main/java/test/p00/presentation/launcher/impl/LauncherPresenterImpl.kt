package test.p00.presentation.launcher.impl

import io.reactivex.Observable.*
import io.reactivex.disposables.CompositeDisposable
import test.p00.domain.launcher.LauncherInteractor
import test.p00.presentation.launcher.LauncherPresenter
import test.p00.presentation.launcher.LauncherRouter
import test.p00.presentation.launcher.LauncherView
import test.p00.util.reactivex.Schedulers
import java.util.concurrent.TimeUnit.MILLISECONDS

class LauncherPresenterImpl(
        private val scheduler: Schedulers = Schedulers.create(),
        private val router: LauncherRouter,
        private val launcher: LauncherInteractor = LauncherInteractor()) : LauncherPresenter {

    companion object {

        const val APP_LAUNCH_DELAY = 500L
    }

    override lateinit var attachedView: LauncherView
    override val disposables = CompositeDisposable()

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
                            never() }
                        else -> launcher.shouldShowOnBoarding() } }
                .flatMap { shouldShow ->
                    when (shouldShow) {
                        true -> {
                            displayOnBoarding()
                            never() }
                        else -> just(true) } }
                .subscribe({ router.toHome() }, { }))
    }

    override fun displayOnBoardingWizard() {
        router.toOnBoardingWizard()
    }

    override fun displayOnBoarding() {
        router.toOnBoarding()
    }

}