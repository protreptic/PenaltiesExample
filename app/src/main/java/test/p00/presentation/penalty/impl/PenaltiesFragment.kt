package test.p00.presentation.penalty.impl

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.Completable
import io.realm.Realm
import io.realm.RealmConfiguration
import kotterknife.bindView
import test.p00.R
import test.p00.presentation.activity.MainActivity
import test.p00.presentation.activity.abs.AbsFragment
import test.p00.data.repository.settings.datasource.impl.SharedPreferencesSettingsDataSource
import test.p00.presentation.penalty.PenaltiesPresenter
import test.p00.presentation.penalty.PenaltiesView
import test.p00.presentation.penalty.impl.adapter.DriverAdapter
import test.p00.presentation.penalty.impl.adapter.VehicleAdapter
import test.p00.presentation.penalty.model.DriverModel
import test.p00.presentation.penalty.model.VehicleModel
import test.p00.util.reactivex.CompletableTransformers

class PenaltiesFragment : AbsFragment(), PenaltiesView {

    companion object {

        const val FRAGMENT_TAG = "tag_PenaltiesFragment"

        fun newInstance(): Fragment = PenaltiesFragment().apply {
            arguments = Bundle.EMPTY
        }

    }

    private val presenter: PenaltiesPresenter by lazy {
        PenaltiesPresenterImpl()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
            = inflater.inflate(R.layout.view_penalties, container, false)

    private val vWipe: View by bindView(R.id.wipe)
    private val vDrivers: RecyclerView by bindView(R.id.vDrivers)
    private val vVehicles: RecyclerView by bindView(R.id.vVehicles)

    private val driverAdapter = DriverAdapter()
    private val vehicleAdapter = VehicleAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vWipe.setOnClickListener {
            Completable
                .complete()
                .compose(CompletableTransformers.schedulers())
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
                    SharedPreferencesSettingsDataSource().wipe()

                    /*
                     * Повторно запускаем приложение
                     */
                    startActivity(Intent(activity, MainActivity::class.java).apply {
                        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    })}
                .subscribe { presenter.displayUser() }
        }

        vDrivers.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = driverAdapter
        }

        vVehicles.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = vehicleAdapter
        }

        presenter.attachView(this)
    }

    override fun showUser(vehicles: List<VehicleModel>, drivers: List<DriverModel>) {
        vehicleAdapter.setData(vehicles)
        driverAdapter.setData(drivers)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        presenter.detachView()
    }

}