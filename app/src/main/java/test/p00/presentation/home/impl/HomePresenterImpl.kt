package test.p00.presentation.home.impl

import io.reactivex.disposables.CompositeDisposable
import test.p00.data.repository.user.UserRepository
import test.p00.data.repository.user.UserRepositoryFactory
import test.p00.presentation.home.HomePresenter
import test.p00.presentation.home.HomeView
import test.p00.presentation.home.model.DriverModel
import test.p00.presentation.home.model.VehicleModel
import test.p00.util.reactivex.ObservableTransformers

class HomePresenterImpl(
        private val userRepository: UserRepository =
                                    UserRepositoryFactory.create()) : HomePresenter {

    private lateinit var attachedView: HomeView
    private val disposables = CompositeDisposable()

    override fun attachView(view: HomeView) {
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