package test.p00.data.storage.sqlite

import android.content.Context
import android.database.Cursor
import io.reactivex.Observable
import test.p00.data.model.countries.Country
import test.p00.data.storage.sqlite.abs.Storage
import test.p00.util.ContextProvider
import java.util.*

/**
 * Created by Peter Bukhal on 5/14/18.
 */
class CountriesStorage(context: Context = ContextProvider.provide()) : Storage(context, NAME, VERSION) {

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

    fun fetchDefault(): Observable<Country> =
            Observable.create { source ->
                val arguments = arrayOf(Locale.getDefault().isO3Country)

                CountriesStorage().fetch("SELECT * FROM COUNTRIES WHERE ISO3 = ? LIMIT 1", arguments) { cursor ->
                    while (cursor.moveToNext()) {
                        source.onNext(country(cursor))
                        source.onComplete()
                    }
                }
            }

    fun fetchEverything(): Observable<List<Country>> =
            Observable.create { source ->
                CountriesStorage().fetch("SELECT * FROM COUNTRIES ORDER BY NAME ASC") { cursor ->
                    source.onNext(countries(cursor))
                    source.onComplete()
                }
            }

    fun fetchByName(name: String): Observable<List<Country>> =
            Observable.create { source ->
                val arguments = arrayOf("%$name%")

                CountriesStorage().fetch("SELECT * FROM COUNTRIES WHERE NAME LIKE ? ORDER BY NAME ASC", arguments) { cursor ->
                    source.onNext(countries(cursor))
                    source.onComplete()
                }
            }

}