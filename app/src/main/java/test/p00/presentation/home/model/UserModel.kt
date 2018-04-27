package test.p00.presentation.home.model

import test.p00.data.model.user.User

/**
 * Created by Peter Bukhal on 4/27/18.
 */
data class UserModel(val id: Int, val vehicles: List<VehicleModel>, val drivers: List<DriverModel>) {

    object Mapper {

        fun map(user: User) =
                UserModel(user.id,
                          user.vehicles.map { VehicleModel.Mapper.map(it) },
                          user.drivers.map { DriverModel.Mapper.map(it) })

    }

}