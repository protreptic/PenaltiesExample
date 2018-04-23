package test.p00.presentation.onboarding.wizard.steps.impl

import io.reactivex.disposables.CompositeDisposable
import test.p00.domain.OnBoardingWizardInteractor
import test.p00.presentation.onboarding.wizard.steps.OnBoardingWizardStepPresenter
import test.p00.presentation.onboarding.wizard.steps.OnBoardingWizardStepView
import test.p00.util.reactivex.ObservableTransformers
import test.p00.util.reactivex.Schedulers

class OnBoardingWizardStepPresenterImpl(
        private val scheduler: Schedulers = Schedulers.create(),
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
                        true -> attachedView.forward()
                        else -> attachedView.showError()
                    } }, { attachedView.showError() }))
    }

    override fun validateVehicle(number: String) {
        disposables.add(
            wizardInteractor
                .validateVehicle(number)
                .compose(ObservableTransformers.schedulers(scheduler))
                .subscribe({ attachedView.showValidationError(it) },
                           { attachedView.showError() }))
    }

    override fun addVehicleLicense(number: String) {
        disposables.add(
            wizardInteractor
                .tryAddVehicleLicense(number)
                .compose(ObservableTransformers.schedulers(scheduler))
                .subscribe({
                    when (it) {
                        true -> attachedView.forward()
                        else -> attachedView.showError()
                    } }, { attachedView.showError() }))
    }

    override fun validateVehicleLicense(number: String) {
        disposables.add(
            wizardInteractor
                .validateVehicleLicense(number)
                .compose(ObservableTransformers.schedulers(scheduler))
                .subscribe({ attachedView.showValidationError(it) },
                           { attachedView.showError() }))
    }

    override fun addDriver(name: String, number: String) {
        disposables.add(
            wizardInteractor
                .tryAddDriver(name, number)
                .compose(ObservableTransformers.schedulers(scheduler))
                .subscribe({
                    when (it) {
                        true -> attachedView.forward()
                        else -> attachedView.showError()
                    } }, { attachedView.showError() }))
    }

    override fun validateDriver(number: String) {
        disposables.add(
            wizardInteractor
                .validateDriver(number)
                .compose(ObservableTransformers.schedulers(scheduler))
                .subscribe({ attachedView.showValidationError(it) },
                           { attachedView.showError() }))
    }

    override fun detachView() {
        disposables.dispose()
    }

}