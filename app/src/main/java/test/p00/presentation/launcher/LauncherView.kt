package test.p00.presentation.launcher

import test.p00.presentation.base.View

interface LauncherView : View {

    fun runMain()
    fun runOnBoardingWizard()
    fun runOnBoarding()

}