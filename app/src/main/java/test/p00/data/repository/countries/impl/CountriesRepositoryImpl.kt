package test.p00.data.repository.countries.impl

import test.p00.data.repository.countries.CountriesRepository
import test.p00.data.repository.countries.datasource.CountriesDataSource
import test.p00.data.repository.countries.datasource.CountriesDataSourceFactory

/**
 * Created by Peter Bukhal on 5/14/18.
 */
class CountriesRepositoryImpl(
        private val storage: CountriesDataSource = CountriesDataSourceFactory.create()):
        CountriesRepository {

    override fun fetchEverything() =
            storage.fetchEverything()

    override fun fetchDefault() =
            storage.fetchDefault()

    override fun fetchByName(name: String) =
            storage.fetchByName(name)

}