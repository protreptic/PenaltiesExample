package test.p00.data.repository.settings.datasource.impl

import io.reactivex.Observable
import test.p00.data.model.settings.Settings
import test.p00.data.repository.settings.datasource.SettingsDataSource
import test.p00.data.storage.sqlite.PrimaryStorage

/**
 * Created by Peter Bukhal on 4/20/18.
 */
class SqliteSettingsDataStore : SettingsDataSource {

    override fun fetch(): Observable<Settings> =
            Observable.create { source ->
                PrimaryStorage().use { storage ->
                    storage.readableDatabase
                            .rawQuery("SELECT id, name, value FROM SETTINGS", null)
                            .apply { moveToFirst() }.use { cursor ->
                                val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                                val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
                                val value = cursor.getString(cursor.getColumnIndexOrThrow("value"))

                                source.onNext(Settings())
                                source.onComplete()
                            }
                }
            }

    override fun retain(settings: Settings): Observable<Settings> = Observable.just(Settings())

}