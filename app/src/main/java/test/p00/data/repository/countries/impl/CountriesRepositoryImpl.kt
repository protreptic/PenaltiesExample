package test.p00.data.repository.countries.impl

import test.p00.data.repository.countries.CountriesRepository
import test.p00.data.repository.countries.datasource.CountriesDataSource
import javax.inject.Inject

/**
 * Created by Peter Bukhal on 5/14/18.
 */
class CountriesRepositoryImpl
    @Inject constructor(
        private val storage: CountriesDataSource):
            CountriesRepository {

    override fun fetchEverything() =
            storage.fetchEverything()

    override fun fetchDefault() =
            storage.fetchDefault()

    override fun fetchByName(name: String) =
            storage.fetchByName(name)

}