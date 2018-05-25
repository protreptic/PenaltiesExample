package test.p00

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.realm.Realm
import io.realm.RealmConfiguration
import test.p00.dependecies.DaggerApplicationComponent

@Suppress("unused")
class PenaltiesApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<PenaltiesApplication> =
            applicationComponent

    private val applicationComponent by lazy {
        DaggerApplicationComponent.builder()
                .withContext(applicationContext)
                .build()
    }

    override fun onCreate() {
        super.onCreate()

        initRealm()
    }

    private fun initRealm() {
        Realm.init(this)

        val configuration =
                RealmConfiguration.Builder()
                        .deleteRealmIfMigrationNeeded()
                        .build()

        try {
            Realm.setDefaultConfiguration(configuration)
            Realm.getDefaultInstance().close()
        } catch (e: Exception) {
            Realm.deleteRealm(configuration)
        }
    }

}