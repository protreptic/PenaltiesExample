package test.p00.data.repository

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import test.p00.data.model.countries.Country

/**
 * Created by Peter Bukhal on 6/15/18.
 */
@Dao
interface CountryDao {

    @Query("select * from countries order by name")
    fun fetchEverything(): Flowable<List<Country>>

    @Query("select * from countries where name like :namePattern")
    fun fetchByName(namePattern: String): Flowable<List<Country>>

}