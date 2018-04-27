package test.p00.presentation.home.model

import test.p00.data.model.user.Vehicle

data class VehicleModel(val id: Int = 0, val name: String, val number: String = "", val registrationNumber: String = "") {

    object Mapper {

        fun map(vehicle: Vehicle) =
                VehicleModel(vehicle.id,
                             vehicle.name,
                             vehicle.number,
                             vehicle.registrationNumber)

    }

}