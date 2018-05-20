package test.p00.data.repository.countries.datasource

import test.p00.data.repository.countries.datasource.impl.CountriesDataSourceImpl

/**
 * Created by Peter Bukhal on 5/20/18.
 */
object CountriesDataSourceFactory {

    private val instance: CountriesDataSourceImpl by lazy { CountriesDataSourceImpl() }

    fun create() = instance

}