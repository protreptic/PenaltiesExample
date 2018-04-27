package test.p00.presentation.wizard.impl

import io.reactivex.disposables.CompositeDisposable
import test.p00.presentation.wizard.OnBoardingWizardPresenter
import test.p00.presentation.wizard.OnBoardingWizardRouter
import test.p00.presentation.wizard.OnBoardingWizardView

class OnBoardingWizardPresenterImpl(
        private val router: OnBoardingWizardRouter) : OnBoardingWizardPresenter {

    override lateinit var attachedView: OnBoardingWizardView
    override val disposables = CompositeDisposable()

    override fun attachView(view: OnBoardingWizardView) {
        super.attachView(view)

        displayIntroductory()
    }

    override fun displayIntroductory() {
        router.toIntroductory()
    }

}