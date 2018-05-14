package test.p00.presentation.signup.impl

import io.reactivex.disposables.CompositeDisposable
import test.p00.data.repository.countries.CountriesRepository
import test.p00.data.repository.countries.CountriesRepositoryFactory
import test.p00.presentation.model.countries.CountryModel
import test.p00.presentation.signup.SignUpPresenter
import test.p00.presentation.signup.SignUpRouter
import test.p00.presentation.signup.SignUpView
import test.p00.util.reactivex.ObservableTransformers
import test.p00.util.reactivex.Schedulers

/**
 * Created by Peter Bukhal on 5/12/18.
 */
class SignUpPresenterImpl(
        private val scheduler: Schedulers = Schedulers.create(),
        private val countriesRepository: CountriesRepository = CountriesRepositoryFactory.create(),
        private val router: SignUpRouter):
        SignUpPresenter {

    override var attachedView: SignUpView? = null
    override var disposables = CompositeDisposable()

    override fun attachView(view: SignUpView) {
        super.attachView(view)

        displaySignUpForm()
    }

    override fun displaySignUpForm() {
        disposables.add(
            countriesRepository
                .fetchDefault()
                .map(CountryModel.Mapper::map)
                .compose(ObservableTransformers.schedulers(scheduler))
                .subscribe(
                    { country -> attachedView?.showSignUpForm(country) },
                    { /*  */ }))
    }

    override fun displayCountries() {
        router.toCountries()
    }

}