package test.p00.data.repository.countries.datasource.impl

import io.reactivex.Observable
import test.p00.data.model.countries.Country
import test.p00.data.repository.PrimaryDatabase
import test.p00.data.repository.countries.datasource.CountriesDataSource
import java.util.*
import javax.inject.Inject

/**
 * Created by Peter Bukhal on 6/15/18.
 */
class RoomCountryDataSource
    @Inject constructor(
            private val database: PrimaryDatabase):
                CountriesDataSource {

    override fun fetchEverything(): Observable<List<Country>> =
            database.countries()
                    .fetchEverything()
                    .doOnNext(this::addFlag)
                    .toObservable()

    override fun fetchDefault(): Observable<Country> =
            database.countries()
                    .fetchEverything()
                    .doOnNext(this::addFlag)
                    .map { countries ->
                        val defaultCountry = Locale.getDefault().isO3Country

                        countries.first { country ->
                            country.iso3.equals(defaultCountry, true)
                        }
                    }
                    .toObservable()

    override fun fetchByName(name: String): Observable<List<Country>> =
            database.countries()
                    .fetchByName("%$name%")
                    .doOnNext(this::addFlag)
                    .toObservable()

    private fun addFlag(countries: List<Country>) {
        countries.forEach { country ->
            country.flag = "file:///android_asset/storage/countries/flags/${country.iso2.toLowerCase()}.png"
        }
    }

}