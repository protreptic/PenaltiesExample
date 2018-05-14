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
        val id = cursor.getInt(cursor.getColumnIndexOrThrow("ID"))
        val iso = cursor.getString(cursor.getColumnIndexOrThrow("ISO")).toLowerCase()
        val name = cursor.getString(cursor.getColumnIndexOrThrow("NAME"))
        val name2 = cursor.getString(cursor.getColumnIndexOrThrow("NAME2"))
        val phoneCode = cursor.getInt(cursor.getColumnIndexOrThrow("PHONECODE"))

        return Country().apply {
            this.id = id
            this.iso = iso
            this.name = name
            this.name2 = name2
            this.phoneCode = phoneCode
            this.flag = "file:///android_asset/storage/countries/flags/$iso.png"
        }
    }

    fun fetchEverything(): Observable<List<Country>> =
            Observable.create { source ->
                CountriesStorage().use { storage ->
                    storage.readableDatabase.use { database ->
                        database.rawQuery("SELECT * FROM COUNTRIES ORDER BY NAME2 ASC", null).use { cursor ->
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
                        database.rawQuery("SELECT * FROM COUNTRIES WHERE NAME2 LIKE ? ORDER BY NAME2 ASC", arrayOf("%$name%")).use { cursor ->
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