package test.p00.presentation.onboarding.wizard.introductory

import test.p00.presentation.base.Presenter

interface IntroductoryStepPresenter : Presenter<IntroductoryStepVew> {

    fun displayFirstStep()

}