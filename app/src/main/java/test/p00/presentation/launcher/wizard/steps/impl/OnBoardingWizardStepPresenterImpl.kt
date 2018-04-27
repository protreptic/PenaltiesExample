package test.p00.presentation.launcher.wizard.steps.impl

import io.reactivex.disposables.CompositeDisposable
import test.p00.domain.launcher.wizard.OnBoardingWizardInteractor
import test.p00.presentation.launcher.wizard.OnBoardingWizardRouter
import test.p00.presentation.launcher.wizard.steps.OnBoardingWizardStepPresenter
import test.p00.presentation.launcher.wizard.steps.OnBoardingWizardStepView
import test.p00.util.reactivex.CompletableTransformers
import test.p00.util.reactivex.ObservableTransformers
import test.p00.util.reactivex.Schedulers

class OnBoardingWizardStepPresenterImpl(
        private val scheduler: Schedulers = Schedulers.create(),
        private val router: OnBoardingWizardRouter,
        private val interactor: OnBoardingWizardInteractor = OnBoardingWizardInteractor()) : OnBoardingWizardStepPresenter {

    override lateinit var attachedView: OnBoardingWizardStepView
    override val disposables = CompositeDisposable()

    override fun addVehicle(name: String, number: String) {
        disposables.add(
            interactor
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
            interactor
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
            interactor
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
            interactor
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
            interactor
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
            interactor
                .validateDriver(number)
                .compose(ObservableTransformers.schedulers(scheduler))
                .subscribe({ attachedView.showValidationResult(it) },
                           { attachedView.showError() }))
    }

    override fun skipAddDriver() {
        disposables.add(
            interactor
                .markOnBoardingWizardAsShown()
                .compose(CompletableTransformers.schedulers(scheduler))
                .subscribe({ router.toHome() }, { }))
    }

}