package test.p00.presentation.launcher

import test.p00.presentation.abs.Router

/**
 * Created by Peter Bukhal on 4/23/18.
 */
interface LauncherRouter : Router {

    fun toOnBoardingWizard()
    fun toOnBoarding()

}