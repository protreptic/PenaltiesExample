package test.p00.presentation.home.impl

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import io.reactivex.Completable
import io.realm.Realm
import io.realm.RealmConfiguration
import kotterknife.bindView
import test.p00.R
import test.p00.auxiliary.reactivex.transformers.CompletableTransformers
import test.p00.data.repository.settings.datasource.impl.SharedPreferencesSettingsDataSource
import test.p00.domain.home.HomeInteractor
import test.p00.presentation.impl.DefaultContainer
import test.p00.presentation.impl.abs.AbsView
import test.p00.presentation.home.HomePresenter
import test.p00.presentation.home.HomeView
import test.p00.presentation.home.impl.adapter.DriversAdapter
import test.p00.presentation.home.impl.adapter.VehiclesAdapter
import test.p00.presentation.model.user.UserModel
import javax.inject.Inject

class HomeFragment : AbsView(), HomeView {

    companion object {

        const val FRAGMENT_TAG = "FRAGMENT_TAG_HOME"

        fun newInstance(): Fragment = HomeFragment().apply {
            arguments = Bundle.EMPTY
        }

    }

    @Inject lateinit var homeInteractor: HomeInteractor

    private val presenter: HomePresenter by lazy {
        HomePresenterImpl(
                schedulers,
                HomeRouterImpl(fragmentManager, this),
                homeInteractor)
    }

    override val targetLayout: Int = R.layout.view_home

    private val vWipe: View by bindView(R.id.wipe)
    private val vChat: View by bindView(R.id.chat)
    private val vDrivers: RecyclerView by bindView(R.id.vDrivers)
    private val vVehicles: RecyclerView by bindView(R.id.vVehicles)

    private val driversAdapter = DriversAdapter()
    private val vehiclesAdapter = VehiclesAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vWipe.setOnClickListener {
            Completable
                .complete()
                .compose(CompletableTransformers.schedulers(schedulers))
                .doOnSubscribe {
                    /*
                     * Очищаем Realm хранилище
                     */
                    Realm.deleteRealm(
                        RealmConfiguration.Builder()
                            .deleteRealmIfMigrationNeeded()
                            .build())

                    /*
                     * Очищаем SharedPreferences хранилище
                     */
                    SharedPreferencesSettingsDataSource(context!!).wipe()

                    /*
                     * Повторно запускаем приложение
                     */
                    startActivity(Intent(activity, DefaultContainer::class.java).apply {
                        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    })}
                .subscribe { presenter.displayUser() }
        }

        vDrivers.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = driversAdapter
        }

        vVehicles.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = vehiclesAdapter
        }

        vChat.setOnClickListener {
            presenter.displayConversations()
        }

        presenter.attachView(this)
    }

    override fun showUser(user: UserModel) {
        disposables.addAll(
            vehiclesAdapter
                .changeData(user.vehicles)
                .subscribeOn(schedulers.background())
                .subscribe(),
            driversAdapter
                .changeData(user.drivers)
                .subscribeOn(schedulers.background())
                .subscribe())
    }

    override fun onDestroyView() {
        super.onDestroyView()

        presenter.detachView()
    }

}