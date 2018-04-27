package test.p00.presentation.home

import test.p00.presentation.abs.View
import test.p00.presentation.home.model.DriverModel
import test.p00.presentation.home.model.UserModel
import test.p00.presentation.home.model.VehicleModel

interface HomeView : View {

    fun showUser(user: UserModel)

}