package test.p00.data.repository.countries.datasource

import io.reactivex.Observable
import test.p00.data.model.countries.Country

/**
 * Created by Peter Bukhal on 5/20/18.
 */
interface CountriesDataSource {

    /**
     *
     */
    fun fetchEverything(): Observable<List<Country>>

    /**
     *
     */
    fun fetchDefault(): Observable<Country>

    /**
     *
     */
    fun fetchByName(name: String): Observable<List<Country>>

}