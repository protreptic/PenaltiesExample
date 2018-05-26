package test.p00.presentation.countries.impl

import io.reactivex.disposables.CompositeDisposable
import test.p00.data.repository.countries.CountriesRepository
import test.p00.presentation.Router
import test.p00.presentation.countries.CountriesPresenter
import test.p00.presentation.countries.CountriesPresenter.*
import test.p00.presentation.countries.CountriesView
import test.p00.presentation.model.countries.CountryModel
import test.p00.presentation.model.countries.CountryModel.Mapper
import test.p00.auxiliary.reactivex.transformers.ObservableTransformers
import test.p00.auxiliary.reactivex.schedulers.Schedulers
import test.p00.auxiliary.reactivex.bus.RxBus
import javax.inject.Inject

/**
 * Created by Peter Bukhal on 5/14/18.
 */
class CountriesPresenterImpl
    @Inject constructor(
            private val scheduler: Schedulers,
            private val countriesRepository: CountriesRepository,
            private val router: Router,
            private val bus: RxBus):
            CountriesPresenter {

    override var attachedView: CountriesView? = null
    override var disposables = CompositeDisposable()

    override fun attachView(view: CountriesView) {
        super.attachView(view)

        displayCountries()
    }

    override fun displayCountries() {
        disposables.add(
            countriesRepository
                .fetchEverything()
                .map { countries -> countries.map(Mapper::map) }
                .compose(ObservableTransformers.schedulers(scheduler))
                .subscribe(
                        { countries -> attachedView?.showCountries(countries) },
                        { /*  */ }))
    }

    override fun displayCountries(name: String) {
        disposables.add(
            countriesRepository
                .fetchByName(name)
                .map { countries -> countries.map(Mapper::map) }
                .compose(ObservableTransformers.schedulers(scheduler))
                .subscribe(
                        { countries -> attachedView?.showCountries(countries) },
                        { /*  */ }))
    }

    override fun pickCountry(country: CountryModel) {
        bus.sendEvent(CountryPickedEvent(country))
        router.back()
    }

}