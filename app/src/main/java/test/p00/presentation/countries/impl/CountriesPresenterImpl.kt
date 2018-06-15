package test.p00.presentation.countries.impl

import io.reactivex.disposables.CompositeDisposable
import test.p00.auxiliary.reactivex.schedulers.Schedulers
import test.p00.auxiliary.reactivex.transformers.ObservableTransformers
import test.p00.data.repository.countries.CountriesRepository
import test.p00.presentation.Router
import test.p00.presentation.countries.CountriesPresenter
import test.p00.presentation.countries.impl.events.CountryPickedEvent
import test.p00.presentation.countries.CountriesView
import test.p00.presentation.model.countries.CountryModel
import test.p00.presentation.model.countries.CountryModel.Mapper
import test.p00.auxiliary.bus.Bus
import test.p00.presentation.countries.CountriesView.*

/**
 * Created by Peter Bukhal on 5/14/18.
 */
class CountriesPresenterImpl(
        private val scheduler: Schedulers,
        private val countriesRepository: CountriesRepository,
        private val router: Router,
        private val bus: Bus):
            CountriesPresenter {

    override var attachedView: CountriesView? = null
    override var disposables = CompositeDisposable()

    override fun displayCountries(name: String, sortBy: SortBy) {
        disposables.add(
            countriesRepository
                .fetchByName(name)
                .map { countries ->
                    when (sortBy) {
                        SortBy.NAME -> countries.sortedBy { it.name }
                        SortBy.PHONE -> countries.sortedBy { it.callingCode }
                    }
                }
                .map { countries -> countries.map(Mapper::map) }
                .compose(ObservableTransformers.schedulers(scheduler))
                .subscribe(
                        { countries -> attachedView?.showCountries(countries, sortings) },
                        { /*  */ }))
    }

    private val sortings =
            listOf(SortByModel("Название", SortBy.NAME, true),
                   SortByModel("Код", SortBy.PHONE))

    override fun pickCountry(country: CountryModel) {
        bus.sendEvent(CountryPickedEvent(country))
        router.back()
    }

}