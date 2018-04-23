package test.p00.data.repository.settings.datasource.impl

import io.reactivex.Observable
import io.realm.Realm
import test.p00.data.model.settings.Settings
import test.p00.data.repository.settings.datasource.SettingsDataSource

class RealmSettingsDataSource : SettingsDataSource {

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    override fun fetch(): Observable<Settings> {
        Realm.getDefaultInstance().use { storage ->
            val settings =
                    storage.where(Settings::class.java)
                           .findFirst()

            return when (settings == null) {
                true -> retain(Settings())
                else -> Observable.just(storage.copyFromRealm(settings))
            }
        }
    }

    override fun retain(settings: Settings): Observable<Settings> {
        Realm.getDefaultInstance().use { storage ->
            storage.beginTransaction()

            val retainedSettings =
                    storage.copyFromRealm(
                    storage.copyToRealmOrUpdate(settings))

            storage.commitTransaction()

            return Observable.just(retainedSettings)
        }
    }

}