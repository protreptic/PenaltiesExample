package test.p00.presentation.onboarding.wizard

import test.p00.presentation.base.Presenter

interface OnBoardingWizardPresenter : Presenter<OnBoardingWizardView> {

    fun displayIntroductoryStep()

}