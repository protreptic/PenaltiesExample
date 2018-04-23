package test.p00.presentation.launcher.wizard.introductory

import test.p00.presentation.abs.Presenter

interface IntroductoryStepPresenter : Presenter<IntroductoryStepVew> {

    fun displayFirstStep()

}