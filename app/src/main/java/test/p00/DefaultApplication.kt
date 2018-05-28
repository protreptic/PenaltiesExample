package test.p00

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.realm.Realm
import io.realm.RealmConfiguration
import test.p00.auxiliary.bus.Bus
import test.p00.auxiliary.dependecies.DaggerApplicationComponent
import test.p00.auxiliary.reactivex.schedulers.Schedulers

@Suppress("unused")
class DefaultApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<DefaultApplication> =
            applicationComponent

    private val applicationComponent by lazy {
        DaggerApplicationComponent.builder()
                .withContext(applicationContext)
                .withSchedulers(Schedulers.schedulers())
                .withBus(Bus.bus())
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