package test.p00.presentation.onboarding.wizard.steps.impl

import io.reactivex.disposables.CompositeDisposable
import test.p00.auxiliary.reactivex.schedulers.Schedulers
import test.p00.auxiliary.reactivex.transformers.ObservableTransformers
import test.p00.domain.onboarding.OnBoardingWizardInteractor
import test.p00.presentation.onboarding.wizard.OnBoardingWizardRouter
import test.p00.presentation.onboarding.wizard.steps.OnBoardingWizardStepPresenter
import test.p00.presentation.onboarding.wizard.steps.OnBoardingWizardStepView

class OnBoardingWizardStepPresenterImpl(
        private val scheduler: Schedulers,
        private val router: OnBoardingWizardRouter,
        private val interactor: OnBoardingWizardInteractor):
            OnBoardingWizardStepPresenter {

    override var attachedView: OnBoardingWizardStepView? = null
    override var disposables = CompositeDisposable()

    override fun addVehicle(name: String, number: String) {
        disposables.add(
            interactor
                .addVehicle(name, number)
                .compose(ObservableTransformers.schedulers(scheduler))
                .subscribe({
                    when (it) {
                        true -> router.toAddVehicleLicenseStep()
                        else -> attachedView?.showError()
                    } }, { attachedView?.showError() }))
    }

    override fun validateVehicle(number: String) {
        disposables.add(
            interactor
                .validateVehicle(number)
                .compose(ObservableTransformers.schedulers(scheduler))
                .subscribe({ attachedView?.showValidationResult(it) },
                           { attachedView?.showError() }))
    }

    override fun skipAddVehicle() {
        router.toAddDriverStep()
    }

    override fun addVehicleLicense(number: String) {
        disposables.add(
            interactor
                .addVehicleLicense(number)
                .compose(ObservableTransformers.schedulers(scheduler))
                .subscribe({
                    when (it) {
                        true -> router.toAddDriverStep()
                        else -> attachedView?.showError()
                    } }, { attachedView?.showError() }))
    }

    override fun validateVehicleLicense(number: String) {
        disposables.add(
            interactor
                .validateVehicleLicense(number)
                .compose(ObservableTransformers.schedulers(scheduler))
                .subscribe({ attachedView?.showValidationResult(it) },
                           { attachedView?.showError() }))
    }

    override fun skipAddVehicleLicense() {
        router.toAddDriverStep()
    }

    override fun addDriver(name: String, number: String) {
        disposables.add(
            interactor
                .addDriver(name, number)
                .compose(ObservableTransformers.schedulers(scheduler))
                .subscribe({
                    when (it) {
                        true -> skipAddDriver()
                        else -> attachedView?.showError()
                    } }, { attachedView?.showError() }))
    }

    override fun validateDriver(number: String) {
        disposables.add(
            interactor
                .validateDriver(number)
                .compose(ObservableTransformers.schedulers(scheduler))
                .subscribe({ attachedView?.showValidationResult(it) },
                           { attachedView?.showError() }))
    }

    override fun skipAddDriver() {
        disposables.add(
            interactor
                .markOnBoardingWizardAsShown()
                .compose(ObservableTransformers.schedulers(scheduler))
                .subscribe({ router.toLauncher() }, { }))
    }

}