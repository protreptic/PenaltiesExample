package test.p00.presentation.launcher.wizard.impl

import test.p00.presentation.launcher.wizard.OnBoardingWizardPresenter
import test.p00.presentation.launcher.wizard.OnBoardingWizardRouter
import test.p00.presentation.launcher.wizard.OnBoardingWizardView

class OnBoardingWizardPresenterImpl(
        private val router: OnBoardingWizardRouter) : OnBoardingWizardPresenter {

    private lateinit var attachedView: OnBoardingWizardView

    override fun attachView(view: OnBoardingWizardView) {
        attachedView = view

        displayIntroductory()
    }

    override fun displayIntroductory() {
        router.toIntroductory()
    }

    override fun detachView() {}

}