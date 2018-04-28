package test.p00.presentation.onboarding.wizard.introductory.impl

import io.reactivex.disposables.CompositeDisposable
import test.p00.presentation.onboarding.wizard.OnBoardingWizardRouter
import test.p00.presentation.onboarding.wizard.introductory.OnBoardingWizardIntroductoryPresenter
import test.p00.presentation.onboarding.wizard.introductory.OnBoardingWizardIntroductoryView

/**
 * Created by Peter Bukhal on 4/23/18.
 */
class OnBoardingWizardIntroductoryPresenterImpl(
        private val router: OnBoardingWizardRouter) : OnBoardingWizardIntroductoryPresenter {

    override var attachedView: OnBoardingWizardIntroductoryView? = null
    override var disposables = CompositeDisposable()

    override fun displayBeginning() {
        router.toBeginning()
    }

}