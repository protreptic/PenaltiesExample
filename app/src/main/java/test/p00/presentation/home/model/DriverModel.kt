package test.p00.presentation.home.model

import test.p00.data.model.user.DriverLicense

data class DriverModel(val id: Int = 0, val name: String = "", val number: String = "") {

    object Mapper {

        fun map(driver: DriverLicense) =
                DriverModel(driver.id,
                            driver.name,
                            driver.registrationNumber)
    }

}