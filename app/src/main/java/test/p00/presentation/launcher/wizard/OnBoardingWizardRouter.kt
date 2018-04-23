package test.p00.presentation.launcher.wizard

/**
 * Created by Peter Bukhal on 4/23/18.
 */
interface OnBoardingWizardRouter {

    fun toHome()
    fun toIntroductory()
    fun toBeginning()

    fun toAddVehicleStep()
    fun toAddVehicleLicenseStep()
    fun toAddDriverStep()

}