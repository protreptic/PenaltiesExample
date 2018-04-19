package test.p00.presentation.onboarding.wizard

import test.p00.presentation.abs.Presenter

interface OnBoardingWizardPresenter : Presenter<OnBoardingWizardView> {

    fun displayIntroductoryStep()

}