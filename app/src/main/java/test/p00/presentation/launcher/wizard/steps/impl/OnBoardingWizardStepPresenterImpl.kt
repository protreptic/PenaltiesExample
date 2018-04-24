package test.p00.presentation.launcher.wizard.steps.impl

import io.reactivex.disposables.CompositeDisposable
import test.p00.domain.onboarding.wizard.OnBoardingWizardInteractor
import test.p00.presentation.launcher.wizard.OnBoardingWizardRouter
import test.p00.presentation.launcher.wizard.steps.OnBoardingWizardStepPresenter
import test.p00.presentation.launcher.wizard.steps.OnBoardingWizardStepView
import test.p00.util.reactivex.CompletableTransformers
import test.p00.util.reactivex.ObservableTransformers
import test.p00.util.reactivex.Schedulers

class OnBoardingWizardStepPresenterImpl(
        private val scheduler: Schedulers = Schedulers.create(),
        private val router: OnBoardingWizardRouter,
        private val wizardInteractor: OnBoardingWizardInteractor = OnBoardingWizardInteractor()) : OnBoardingWizardStepPresenter {

    private lateinit var attachedView: OnBoardingWizardStepView
    private val disposables = CompositeDisposable()

    override fun attachView(view: OnBoardingWizardStepView) {
        attachedView = view
    }

    override fun addVehicle(name: String, number: String) {
        disposables.add(
            wizardInteractor
                .tryAddVehicle(name, number)
                .compose(ObservableTransformers.schedulers(scheduler))
                .subscribe({
                    when (it) {
                        true -> router.toAddVehicleLicenseStep()
                        else -> attachedView.showError()
                    } }, { attachedView.showError() }))
    }

    override fun validateVehicle(number: String) {
        disposables.add(
            wizardInteractor
                .validateVehicle(number)
                .compose(ObservableTransformers.schedulers(scheduler))
                .subscribe({ attachedView.showValidationResult(it) },
                           { attachedView.showError() }))
    }

    override fun skipAddVehicle() {
        router.toAddDriverStep()
    }

    override fun addVehicleLicense(number: String) {
        disposables.add(
            wizardInteractor
                .tryAddVehicleLicense(number)
                .compose(ObservableTransformers.schedulers(scheduler))
                .subscribe({
                    when (it) {
                        true -> router.toAddDriverStep()
                        else -> attachedView.showError()
                    } }, { attachedView.showError() }))
    }

    override fun validateVehicleLicense(number: String) {
        disposables.add(
            wizardInteractor
                .validateVehicleLicense(number)
                .compose(ObservableTransformers.schedulers(scheduler))
                .subscribe({ attachedView.showValidationResult(it) },
                           { attachedView.showError() }))
    }

    override fun skipAddVehicleLicense() {
        router.toAddDriverStep()
    }

    override fun addDriver(name: String, number: String) {
        disposables.add(
            wizardInteractor
                .tryAddDriver(name, number)
                .compose(ObservableTransformers.schedulers(scheduler))
                .subscribe({
                    when (it) {
                        true -> skipAddDriver()
                        else -> attachedView.showError()
                    } }, { attachedView.showError() }))
    }

    override fun validateDriver(number: String) {
        disposables.add(
            wizardInteractor
                .validateDriver(number)
                .compose(ObservableTransformers.schedulers(scheduler))
                .subscribe({ attachedView.showValidationResult(it) },
                           { attachedView.showError() }))
    }

    override fun skipAddDriver() {
        disposables.add(
            wizardInteractor
                .markOnBoardingWizardAsShown()
                .compose(CompletableTransformers.schedulers(scheduler))
                .subscribe({ router.toHome() }, { }))
    }

    override fun detachView() {
        disposables.dispose()
    }

}