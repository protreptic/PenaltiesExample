package test.p00.presentation.onboarding.wizard

import test.p00.presentation.abs.Router

/**
 * Created by Peter Bukhal on 4/23/18.
 */
interface OnBoardingWizardRouter : Router {

    fun toIntroductory()
    fun toBeginning()

    fun toAddVehicleStep()
    fun toAddVehicleLicenseStep()
    fun toAddDriverStep()

    fun toLauncher()

}