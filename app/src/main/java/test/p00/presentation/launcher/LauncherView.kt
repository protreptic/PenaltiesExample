package test.p00.presentation.launcher

import test.p00.presentation.abs.View

interface LauncherView : View {

    fun runMain()
    fun runOnBoardingWizard()
    fun runOnBoarding()

}