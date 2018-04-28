package test.p00.data.model.conversation.message.content

import io.realm.RealmObject

/**
 * Created by Peter Bukhal on 4/28/18.
 */
open class ContentAudio : RealmObject() {

    companion object {

        const val FIELD_MIME_TYPE = "mimeType"
        const val FIELD_SIZE = "size"
        const val FIELD_DURATION = "duration"
        const val FIELD_URI = "uri"

        val DEFAULT = ContentAudio()

    }

    var mimeType: String = "*/*"
    var size: Long = 0
    var duration: Long = 0
    var uri: String = ""

}