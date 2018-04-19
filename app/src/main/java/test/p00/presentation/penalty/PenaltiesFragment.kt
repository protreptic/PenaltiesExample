package test.p00.presentation.penalty

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
import test.p00.activity.base.AbsFragment
import test.p00.presentation.penalty.adapter.DriverAdapter
import test.p00.presentation.penalty.adapter.VehicleAdapter
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vWipe.setOnClickListener {
            Completable
                .complete()
                .compose(CompletableTransformers.schedulers())
                .doOnSubscribe {
                    Realm.deleteRealm(
                        RealmConfiguration.Builder()
                            .deleteRealmIfMigrationNeeded()
                            .build()) }
                .subscribe { presenter.displayUser() }
        }

        vDrivers.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = DriverAdapter()
        }

        vVehicles.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = VehicleAdapter()
        }

        presenter.attachView(this)
    }

    override fun showUser(vehicles: List<VehicleModel>, drivers: List<DriverModel>) {
        (vVehicles.adapter as VehicleAdapter).setData(vehicles)
        (vDrivers.adapter as DriverAdapter).setData(drivers)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        presenter.detachView()
    }

}