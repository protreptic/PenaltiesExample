package test.p00.data.repository.countries.impl

import test.p00.data.repository.countries.CountriesRepository
import test.p00.data.storage.sqlite.CountriesStorage

/**
 * Created by Peter Bukhal on 5/14/18.
 */
class CountriesRepositoryImpl(val storage: CountriesStorage = CountriesStorage()) : CountriesRepository {

    override fun fetchEverything() =
            storage.fetchEverything()

    override fun fetchDefault() =
            storage.fetchDefault()

}