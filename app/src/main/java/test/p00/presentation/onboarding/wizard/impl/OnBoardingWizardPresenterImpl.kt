package test.p00.presentation.onboarding.wizard.impl

import io.reactivex.disposables.CompositeDisposable
import test.p00.presentation.onboarding.wizard.OnBoardingWizardPresenter
import test.p00.presentation.onboarding.wizard.OnBoardingWizardRouter
import test.p00.presentation.onboarding.wizard.OnBoardingWizardView

class OnBoardingWizardPresenterImpl(
        private val router: OnBoardingWizardRouter) : OnBoardingWizardPresenter {

    override var attachedView: OnBoardingWizardView? = null
    override var disposables = CompositeDisposable()

    override fun attachView(view: OnBoardingWizardView) {
        super.attachView(view)

        displayIntroductory()
    }

    override fun displayIntroductory() {
        router.toIntroductory()
    }

}