package test.p00.data.model.user

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class User : RealmObject() {

    companion object {

        const val FIELD_ID = "id"
        const val FIELD_VEHICLES = "vehicles"
        const val FIELD_DRIVER_LICENSES = "drivers"

    }

    @PrimaryKey
    var id: Int = 0
    var vehicles: RealmList<Vehicle> = RealmList()
    var drivers: RealmList<DriverLicense> = RealmList()

}