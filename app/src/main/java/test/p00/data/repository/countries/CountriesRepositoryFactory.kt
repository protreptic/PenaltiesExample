package test.p00.data.repository.countries

import test.p00.data.repository.countries.impl.CountriesRepositoryImpl

/**
 * Created by Peter Bukhal on 5/14/18.
 */
object CountriesRepositoryFactory {

    fun create() = CountriesRepositoryImpl()

}