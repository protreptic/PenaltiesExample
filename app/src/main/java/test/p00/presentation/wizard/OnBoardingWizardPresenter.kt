package test.p00.presentation.wizard

import test.p00.presentation.abs.Presenter

interface OnBoardingWizardPresenter : Presenter<OnBoardingWizardView> {

    fun displayIntroductory()

}