package test.p00.data.repository.settings.datasource.impl

import android.content.ContentValues
import android.content.Context
import io.reactivex.Observable
import test.p00.data.model.settings.Settings
import test.p00.data.repository.settings.datasource.SettingsDataSource
import test.p00.data.storage.sqlite.SettingsStorage
import javax.inject.Inject

/**
 * Created by Peter Bukhal on 4/20/18.
 */
class SqliteSettingsDataStore
    @Inject constructor(
        private val context: Context):
            SettingsDataSource {

    override fun fetch(): Observable<Settings> =
            Observable.create { source ->
                SettingsStorage(context).use { storage ->
                    storage.readableDatabase.use { database ->
                        database.rawQuery("SELECT * FROM SETTINGS", null).use { cursor ->
                                val settings = Settings()

                                while (cursor.moveToNext()) {
                                    val name = cursor.getString(cursor.getColumnIndexOrThrow("NAME"))
                                    val value = cursor.getString(cursor.getColumnIndexOrThrow("VALUE"))

                                    when (name) {
                                        "wasOnBoardingShown" -> {
                                            settings.wasOnBoardingShown = (value == "1")
                                        }
                                        "wasOnBoardingWizardShown" -> {
                                            settings.wasOnBoardingWizardShown = (value == "1")
                                        }
                                    }
                                }

                                source.onNext(settings)
                                source.onComplete()
                            }
                    }
                }
            }

    override fun retain(settings: Settings): Observable<Settings> {
        SettingsStorage(context).use { storage ->
            storage.writableDatabase.use {
                it.update("SETTINGS", ContentValues().apply {
                    put("VALUE", if (settings.wasOnBoardingShown) "1" else "0") },
                        "NAME = ?", arrayOf("wasOnBoardingShown"))
                it.update("SETTINGS", ContentValues().apply {
                    put("VALUE", if (settings.wasOnBoardingWizardShown) "1" else "0") },
                        "NAME = ?", arrayOf("wasOnBoardingWizardShown"))
            }
        }

        return Observable.just(settings)
    }

}