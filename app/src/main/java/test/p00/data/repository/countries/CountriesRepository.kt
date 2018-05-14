package test.p00.data.repository.countries

import io.reactivex.Observable
import test.p00.data.model.Country

/**
 * Created by Peter Bukhal on 5/14/18.
 */
interface CountriesRepository {

    /**
     *
     */
    fun fetchEverything(): Observable<List<Country>>

    /**
     *
     */
    fun fetchDefault(): Observable<Country>

}