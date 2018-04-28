package test.p00.data.model.conversation.message.content

import io.realm.RealmObject

/**
 * Created by Peter Bukhal on 4/28/18.
 */
open class ContentVideo : RealmObject() {

    companion object {

        const val FIELD_MIME_TYPE = "mimeType"
        const val FIELD_DURATION = "duration"
        const val FIELD_THUMB = "thumb"
        const val FIELD_SIZE = "size"
        const val FIELD_URI = "uri"

        val DEFAULT = ContentVideo()

    }

    var mimeType: String = "*/*"
    var duration: Long = 0
    var thumb: String = ""
    var size: Long = 0
    var uri: String = ""

}