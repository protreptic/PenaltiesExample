package test.p00.presentation.onboarding.wizard.introductory

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