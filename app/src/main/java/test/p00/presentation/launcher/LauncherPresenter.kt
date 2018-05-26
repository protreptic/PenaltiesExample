package test.p00.presentation.launcher

import test.p00.presentation.Presenter

interface LauncherPresenter : Presenter<LauncherView> {

    fun launchApplication()

    fun displayOnBoardingWizard()
    fun displayOnBoarding()

}