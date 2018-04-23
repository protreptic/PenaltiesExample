package test.p00.presentation.launcher.wizard.introductory.impl

import test.p00.presentation.launcher.wizard.OnBoardingWizardRouter
import test.p00.presentation.launcher.wizard.introductory.OnBoardingWizardIntroductoryPresenter
import test.p00.presentation.launcher.wizard.introductory.OnBoardingWizardIntroductoryView

/**
 * Created by Peter Bukhal on 4/23/18.
 */
class OnBoardingWizardIntroductoryPresenterImpl(
        private val router: OnBoardingWizardRouter) : OnBoardingWizardIntroductoryPresenter {

    private lateinit var attachedView: OnBoardingWizardIntroductoryView

    override fun attachView(view: OnBoardingWizardIntroductoryView) {
        attachedView = view
    }

    override fun displayBeginning() {
        router.toBeginning()
    }

    override fun detachView() {

    }

}