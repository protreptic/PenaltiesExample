package test.p00.data.model.conversation.message.content

import io.realm.RealmObject

/**
 * Created by Peter Bukhal on 4/28/18.
 */
open class ContentText : RealmObject() {

    companion object {

        const val FIELD_TEXT = "contentText"

        val DEFAULT = ContentText()

    }

    var text: String = ""

}