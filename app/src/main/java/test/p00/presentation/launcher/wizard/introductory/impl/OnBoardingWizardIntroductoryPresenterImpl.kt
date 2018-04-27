package test.p00.presentation.launcher.wizard.introductory.impl

import io.reactivex.disposables.CompositeDisposable
import test.p00.presentation.launcher.wizard.OnBoardingWizardRouter
import test.p00.presentation.launcher.wizard.introductory.OnBoardingWizardIntroductoryPresenter
import test.p00.presentation.launcher.wizard.introductory.OnBoardingWizardIntroductoryView

/**
 * Created by Peter Bukhal on 4/23/18.
 */
class OnBoardingWizardIntroductoryPresenterImpl(
        private val router: OnBoardingWizardRouter) : OnBoardingWizardIntroductoryPresenter {

    override lateinit var attachedView: OnBoardingWizardIntroductoryView
    override val disposables = CompositeDisposable()

    override fun displayBeginning() {
        router.toBeginning()
    }

}