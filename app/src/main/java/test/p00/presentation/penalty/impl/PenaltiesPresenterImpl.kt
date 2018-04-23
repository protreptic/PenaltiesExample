package test.p00.presentation.penalty.impl

import io.reactivex.disposables.CompositeDisposable
import test.p00.data.repository.user.UserRepository
import test.p00.data.repository.user.UserRepositoryFactory
import test.p00.presentation.penalty.PenaltiesPresenter
import test.p00.presentation.penalty.PenaltiesView
import test.p00.presentation.penalty.model.DriverModel
import test.p00.presentation.penalty.model.VehicleModel
import test.p00.util.reactivex.ObservableTransformers

class PenaltiesPresenterImpl(
        private val userRepository: UserRepository =
                                    UserRepositoryFactory.create()) : PenaltiesPresenter {

    private lateinit var attachedView: PenaltiesView
    private val disposables = CompositeDisposable()

    override fun attachView(view: PenaltiesView) {
        attachedView = view

        displayUser()
    }

    override fun displayUser() {
        userRepository
                .fetch()
                .compose(ObservableTransformers.schedulers())
                .subscribe({ user ->
                    attachedView.showUser(
                            user.vehicles.map { vehicle ->
                                VehicleModel(vehicle.id, vehicle.name,
                                             vehicle.number, vehicle.registrationNumber) },
                            user.drivers.map { driver ->
                                DriverModel(driver.id, driver.name,
                                            driver.registrationNumber) }) }, {})
    }

    override fun detachView() {
        disposables.dispose()
    }

}