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

    fun fetchEverything(): Observable<List<Country>> =
            Observable.create { source ->
                CountriesStorage().use { storage ->
                    storage.readableDatabase.use { database ->
                        database.rawQuery("SELECT * FROM COUNTRIES ORDER BY NAME ASC", null).use { cursor ->
                            val countries = mutableListOf<Country>()

                            while (cursor.moveToNext()) {
                                countries.add(country(cursor))
                            }

                            source.onNext(countries.toList())
                            source.onComplete()
                        }
                    }
                }
            }

    fun fetchDefault(): Observable<Country> =
            Observable.create { source ->
                CountriesStorage().use { storage ->
                    storage.readableDatabase.use { database ->
                        val defaultCountry = Locale.getDefault().isO3Country

                        database.rawQuery("SELECT * FROM COUNTRIES WHERE ISO3 = ? LIMIT 1",
                                arrayOf(defaultCountry)).use { cursor ->
                            while (cursor.moveToNext()) {
                                source.onNext(country(cursor))
                                source.onComplete()

                                return@create
                            }
                        }
                    }
                }
            }

    fun fetchByName(name: String): Observable<List<Country>> =
            Observable.create { source ->
                CountriesStorage().use { storage ->
                    storage.readableDatabase.use { database ->
                        database.rawQuery("SELECT * FROM COUNTRIES WHERE NAME LIKE ? ORDER BY NAME ASC", arrayOf("%$name%")).use { cursor ->
                            val countries = mutableListOf<Country>()

                            while (cursor.moveToNext()) {
                                countries.add(country(cursor))
                            }

                            source.onNext(countries.toList())
                            source.onComplete()
                        }
                    }
                }
            }
}