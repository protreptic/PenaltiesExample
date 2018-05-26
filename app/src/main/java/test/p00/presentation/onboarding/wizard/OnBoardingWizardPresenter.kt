package test.p00.presentation.onboarding.wizard

import test.p00.presentation.Presenter

interface OnBoardingWizardPresenter : Presenter<OnBoardingWizardView> {

    fun displayIntroductory()

}