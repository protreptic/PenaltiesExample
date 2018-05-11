package test.p00.data.model.conversation.message

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by Peter Bukhal on 4/27/18.
 */
open class Author : RealmObject() {

    companion object {

        const val FIELD_ID = "id"
        const val FIELD_NAME = "name"

        val DEFAULT = Author()

    }

    @PrimaryKey
    var id: String = ""
    var name: String = ""

}