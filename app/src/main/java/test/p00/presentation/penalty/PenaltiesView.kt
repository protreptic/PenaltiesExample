package test.p00.presentation.penalty

import test.p00.presentation.abs.View
import test.p00.presentation.penalty.model.DriverModel
import test.p00.presentation.penalty.model.VehicleModel

interface PenaltiesView : View {

    fun showUser(vehicles: List<VehicleModel>, drivers: List<DriverModel>)

}