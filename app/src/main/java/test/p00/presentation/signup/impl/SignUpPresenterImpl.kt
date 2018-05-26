package test.p00.presentation.signup.impl

import io.reactivex.disposables.CompositeDisposable
import test.p00.domain.signup.SignUpInteractor
import test.p00.presentation.countries.CountriesPresenter
import test.p00.presentation.model.ErrorModel.Mapper
import test.p00.presentation.model.countries.CountryModel
import test.p00.presentation.signup.SignUpPresenter
import test.p00.presentation.signup.SignUpRouter
import test.p00.presentation.signup.SignUpView
import test.p00.extensions.get
import test.p00.auxiliary.reactivex.bus.RxBus
import test.p00.auxiliary.reactivex.schedulers.Schedulers
import test.p00.auxiliary.reactivex.transformers.CompletableTransformers
import test.p00.auxiliary.reactivex.transformers.ObservableTransformers
import javax.inject.Inject

/**
 * Created by Peter Bukhal on 5/12/18.
 */
class SignUpPresenterImpl
    @Inject constructor(
        private val scheduler: Schedulers,
        private val interactor: SignUpInteractor,
        private val router: SignUpRouter,
        private val bus: RxBus):
            SignUpPresenter {

    override var attachedView: SignUpView? = null
    override var disposables = CompositeDisposable()

    override fun attachView(view: SignUpView) {
        super.attachView(view)

        disposables.add(
            interactor
                .fetchDefaultCountry()
                .map(CountryModel.Mapper::map)
                .compose(ObservableTransformers.schedulers(scheduler))
                .subscribe(
                    { country -> attachedView?.showSignUpForm(country) },
                    { error -> attachedView?.showError(Mapper.map(error.get())) }))

        disposables.add(
            bus.subscribe({ event ->
                if (event is CountriesPresenter.CountryPickedEvent) {
                    attachedView?.showSignUpForm(event.pickedCountry)
                }
            }))
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