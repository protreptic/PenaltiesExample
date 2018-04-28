package test.p00.presentation.onboarding.wizard.steps

import test.p00.presentation.abs.Presenter

interface OnBoardingWizardStepPresenter : Presenter<OnBoardingWizardStepView> {

    fun addVehicle(name: String, number: String)
    fun validateVehicle(number: String)
    fun skipAddVehicle()

    fun addVehicleLicense(number: String)
    fun validateVehicleLicense(number: String)
    fun skipAddVehicleLicense()

    fun addDriver(name: String, number: String)
    fun validateDriver(number: String)
    fun skipAddDriver()

}