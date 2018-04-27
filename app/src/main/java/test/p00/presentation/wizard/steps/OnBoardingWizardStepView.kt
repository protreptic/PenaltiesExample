package test.p00.presentation.wizard.steps

import test.p00.presentation.abs.View

interface OnBoardingWizardStepView : View {

    fun showConformationDialog()
    fun showValidationResult(isValid: Boolean)
    fun showError()

}