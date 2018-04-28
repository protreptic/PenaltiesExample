package test.p00.data.model.conversation

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by Peter Bukhal on 4/27/18.
 */
open class Member : RealmObject() {

    enum class Status {

        DEFAULT, OFFLINE, ONLINE

    }

    companion object {

        const val FIELD_ID = "id"
        const val FIELD_NAME = "name"
        const val FIELD_STATUS = "status"

    }

    @PrimaryKey
    var id: String = ""
    var name: String = ""
    var status: String = Status.DEFAULT.name

}