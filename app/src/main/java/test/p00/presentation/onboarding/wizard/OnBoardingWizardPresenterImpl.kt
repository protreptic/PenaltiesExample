package test.p00.presentation.onboarding.wizard

class OnBoardingWizardPresenterImpl : OnBoardingWizardPresenter {

    private lateinit var attachedView: OnBoardingWizardView

    override fun attachView(view: OnBoardingWizardView) {
        attachedView = view

        displayIntroductoryStep()
    }

    override fun displayIntroductoryStep() {
        attachedView.displayIntroductoryStep()
    }

    override fun detachView() {}

}