package test.p00.presentation.signup.impl

import io.reactivex.disposables.CompositeDisposable
import test.p00.auxiliary.extensions.get
import test.p00.auxiliary.reactivex.schedulers.Schedulers
import test.p00.auxiliary.reactivex.transformers.CompletableTransformers
import test.p00.auxiliary.reactivex.transformers.ObservableTransformers
import test.p00.domain.signup.SignUpInteractor
import test.p00.presentation.model.ErrorModel.Mapper
import test.p00.presentation.model.countries.CountryModel
import test.p00.presentation.signup.SignUpPresenter
import test.p00.presentation.signup.SignUpRouter
import test.p00.presentation.signup.SignUpView
import test.p00.auxiliary.bus.Bus
import test.p00.presentation.countries.impl.events.CountryPickedEvent

/**
 * Created by Peter Bukhal on 5/12/18.
 */
class SignUpPresenterImpl(
        private val scheduler: Schedulers,
        private val interactor: SignUpInteractor,
        private val router: SignUpRouter,
        private val bus: Bus):
            SignUpPresenter {

    override var attachedView: SignUpView? = null
    override var disposables = CompositeDisposable()

    fun attachView(view: SignUpView, restored: Boolean = false) {
        super.attachView(view)

        disposables.add(
            bus.subscribe({ event ->
                if (event is CountryPickedEvent) {
                    attachedView?.showSignUpForm(event.pickedCountry)
                }
            }))

        if (!restored) {
            disposables.add(
                interactor
                    .fetchDefaultCountry()
                    .map(CountryModel.Mapper::map)
                    .compose(ObservableTransformers.schedulers(scheduler))
                    .subscribe(
                        { country -> attachedView?.showSignUpForm(country) },
                        { error -> attachedView?.showError(Mapper.map(error.get())) }))
        }

    }

    override fun confirmPhoneNumber(countryCode: String, phoneNumber: String) {
        disposables.add(
            interactor
                .confirmPhoneNumber(countryCode, phoneNumber)
                .compose(CompletableTransformers.schedulers(scheduler))
                .subscribe(
                    { router.toVerification() },
                    { error -> attachedView?.showError(Mapper.map(error.get())) }))
    }

    override fun changeCountry() {
        router.toCountries()
    }

}