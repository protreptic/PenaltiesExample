package test.p00.presentation.countries.impl

import io.reactivex.Observable
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.Mockito.*
import test.p00.auxiliary.bus.Bus
import test.p00.auxiliary.reactivex.schedulers.TestSchedulers
import test.p00.data.model.countries.Country
import test.p00.data.repository.countries.CountriesRepository
import test.p00.presentation.Router
import test.p00.presentation.countries.CountriesPresenter.CountryPickedEvent
import test.p00.presentation.countries.CountriesView
import test.p00.presentation.model.countries.CountryModel

/**
 * Created by Peter Bukhal on 5/27/18.
 */
class CountriesPresenterImplTest {

    private val schedulers = TestSchedulers()
    private val countriesRepository = mock(CountriesRepository::class.java)
    private val router = mock(Router::class.java)
    private val bus = mock(Bus::class.java)
    private val view = mock(CountriesView::class.java)

    private val countriesPresenter = CountriesPresenterImpl(schedulers, countriesRepository, router, bus)

    @Test
    fun displayCountries() {
        Mockito.`when`(countriesRepository.fetchEverything())
                .thenReturn(Observable.just(listOf(Country(), Country(), Country())))

        countriesPresenter.attachView(view)

        inOrder(countriesRepository, view).apply {
            this.verify(countriesRepository, times(1)).fetchEverything()
            this.verify(view, times(1)).showCountries(ArgumentMatchers.anyList())
            this.verifyNoMoreInteractions()
        }
    }

    @Test
    fun pickCountry() {
        val testCountry = CountryModel(1, "A", "A", "A", "A", "A")
        val testCountryPickedEvent = CountryPickedEvent(testCountry)

        countriesPresenter.pickCountry(testCountry)

        inOrder(bus, router).apply {
            this.verify(bus, times(1)).sendEvent(testCountryPickedEvent)
            this.verify(router, times(1)).back()
            this.verifyNoMoreInteractions()
        }
    }

}