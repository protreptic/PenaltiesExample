package test.p00.presentation.launcher.wizard.introductory.impl

import test.p00.presentation.launcher.wizard.introductory.IntroductoryStepPresenter
import test.p00.presentation.launcher.wizard.introductory.IntroductoryStepVew

class IntroductoryStepPresenterImpl : IntroductoryStepPresenter {

    private lateinit var attachedView: IntroductoryStepVew

    override fun attachView(view: IntroductoryStepVew) {
        attachedView = view
    }

    override fun displayFirstStep() {
        attachedView.displayFirstStep()
    }

    override fun detachView() {}

}