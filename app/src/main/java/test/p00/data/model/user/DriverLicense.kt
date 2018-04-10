package test.p00.data.model.user

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class DriverLicense : RealmObject() {

    companion object {

        const val FIELD_ID = "id"
        const val FIELD_NAME = "name"
        const val FIELD_REGISTRATION_NUMBER = "registrationNumber"

    }

    @PrimaryKey
    var id: Int = 0
    var name: String = ""
    var registrationNumber: String = ""

}