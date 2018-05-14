package test.p00.presentation.countries.impl

import io.reactivex.disposables.CompositeDisposable
import test.p00.data.repository.countries.CountriesRepository
import test.p00.data.repository.countries.CountriesRepositoryFactory
import test.p00.presentation.countries.CountriesPresenter
import test.p00.presentation.countries.CountriesView
import test.p00.presentation.model.countries.CountryModel.Mapper
import test.p00.util.reactivex.ObservableTransformers
import test.p00.util.reactivex.Schedulers

/**
 * Created by Peter Bukhal on 5/14/18.
 */
class CountriesPresenterImpl(
        private val scheduler: Schedulers = Schedulers.create(),
        private val countriesRepository: CountriesRepository = CountriesRepositoryFactory.create()):
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
                        {  }))
    }

    override fun displayCountries(name: String) {
        disposables.add(
            countriesRepository
                .fetchByName(name)
                .map { countries -> countries.map(Mapper::map) }
                .compose(ObservableTransformers.schedulers(scheduler))
                .subscribe(
                        { countries -> attachedView?.showCountries(countries) },
                        {  }))
    }

}