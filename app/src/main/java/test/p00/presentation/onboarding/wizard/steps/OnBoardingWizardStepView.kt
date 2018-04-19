package test.p00.presentation.onboarding.wizard.steps

import test.p00.presentation.abs.View

interface OnBoardingWizardStepView : View {

    fun forward()
    fun skip()
    fun showValidationError(isValid: Boolean)
    fun showError()

}