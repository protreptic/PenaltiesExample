package test.p00.data.repository.countries.datasource.impl

import android.content.Context
import android.database.Cursor
import io.reactivex.Observable
import test.p00.data.model.countries.Country
import test.p00.data.repository.countries.datasource.CountriesDataSource
import test.p00.data.storage.sqlite.abs.Storage
import java.util.*
import javax.inject.Inject

/**
 * Created by Peter Bukhal on 5/14/18.
 */
class CountriesDataSourceImpl
    @Inject constructor(context: Context):
        Storage(context, NAME, VERSION), CountriesDataSource {

    companion object {

        const val NAME = "countries"
        const val VERSION = 1

    }

    private fun country(cursor: Cursor): Country {
        val code = cursor.getInt(cursor.getColumnIndexOrThrow("CODE"))
        val iso2 = cursor.getString(cursor.getColumnIndexOrThrow("ISO2")).toLowerCase()
        val iso3 = cursor.getString(cursor.getColumnIndexOrThrow("ISO3")).toLowerCase()
        val name = cursor.getString(cursor.getColumnIndexOrThrow("NAME"))
        val callingCode = cursor.getInt(cursor.getColumnIndexOrThrow("CALLING_CODE"))

        return Country().apply {
            this.code = code
            this.iso2 = iso2
            this.iso3 = iso3
            this.name = name
            this.flag = "file:///android_asset/storage/countries/flags/$iso2.png"
            this.callingCode = callingCode
        }
    }

    private fun countries(cursor: Cursor) =
            mutableListOf<Country>().apply {
                while (cursor.moveToNext()) {
                    add(country(cursor))
                }
            }.toList()

    override fun fetchDefault(): Observable<Country> =
            Observable.create { source ->
                val arguments = arrayOf(Locale.getDefault().isO3Country)

                fetch("SELECT * FROM COUNTRIES WHERE ISO3 = ? LIMIT 1", arguments) { cursor ->
                    while (cursor.moveToNext()) {
                        source.onNext(country(cursor))
                        source.onComplete()
                    }
                }
            }

    override fun fetchEverything(): Observable<List<Country>> =
            Observable.create { source ->
                fetch("SELECT * FROM COUNTRIES ORDER BY NAME ASC") { cursor ->
                    source.onNext(countries(cursor))
                    source.onComplete()
                }
            }

    override fun fetchByName(name: String): Observable<List<Country>> =
            Observable.create { source ->
                val arguments = arrayOf("%$name%")

                fetch("SELECT * FROM COUNTRIES WHERE NAME LIKE ? ORDER BY NAME ASC", arguments) { cursor ->
                    source.onNext(countries(cursor))
                    source.onComplete()
                }
            }

}