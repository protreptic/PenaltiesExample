package test.p00.presentation.onboarding.wizard.steps

import test.p00.presentation.View

interface OnBoardingWizardStepView : View {

    fun showConformationDialog()
    fun showValidationResult(isValid: Boolean)
    fun showError()

}