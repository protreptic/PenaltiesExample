package test.p00.presentation.launcher

import test.p00.presentation.abs.Presenter

interface LauncherPresenter : Presenter<LauncherView> {

    fun launchApp()
    fun runOnBoardingWizard()
    fun runOnBoarding()

}