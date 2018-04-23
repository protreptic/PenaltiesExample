package test.p00.presentation.onboarding.wizard.introductory.impl

import test.p00.presentation.onboarding.wizard.introductory.IntroductoryStepPresenter
import test.p00.presentation.onboarding.wizard.introductory.IntroductoryStepVew

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