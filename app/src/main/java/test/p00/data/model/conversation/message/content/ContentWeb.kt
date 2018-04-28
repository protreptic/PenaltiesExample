package test.p00.data.model.conversation.message.content

import io.realm.RealmObject

/**
 * Created by Peter Bukhal on 4/28/18.
 */
open class ContentWeb : RealmObject() {

    companion object {

        const val FIELD_URI = "uri"

        val DEFAULT = ContentWeb()

    }

    var uri: String = ""

}