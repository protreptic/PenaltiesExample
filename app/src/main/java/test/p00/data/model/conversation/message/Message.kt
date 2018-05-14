package test.p00.data.model.conversation.message

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import test.p00.data.model.conversation.message.content.*

/**
 * Created by Peter Bukhal on 4/27/18.
 */
open class Message : RealmObject() {

    enum class Status {

        DEFAULT, ADDED, SENT, DELIVERED, READ

    }

    enum class Content {

        DEFAULT, SYSTEM, TEXT, IMAGE, DOCUMENT, AUDIO, VIDEO, WEB, LOCATION, VOICE

    }

    companion object {

        const val FIELD_ID = "id"
        const val FIELD_CONVERSATION_ID = "conversationId"
        const val FIELD_AUTHOR = "author"
        const val FIELD_STATUS = "status"

        const val FILED_ADDED_AT = "addedAt"
        const val FIELD_SENT_AT = "sentAt"
        const val FIELD_DELIVERED_AT = "deliveredAt"
        const val FIELD_READ_AT = "readAt"

        const val FIELD_CONTENT_TYPE = "contentType"
        const val FIELD_CONTENT_TEXT = "contentText"
        const val FIELD_CONTENT_IMAGE = "contentImage"
        const val FIELD_CONTENT_FILE = "contentDocument"
        const val FIELD_CONTENT_AUDIO = "contentAudio"
        const val FIELD_CONTENT_VIDEO = "contentVideo"
        const val FIELD_CONTENT_WEB = "contentWeb"
        const val FIELD_CONTENT_LOCATION = "contentLocation"
        const val FIELD_CONTENT_VOICE = "contentVoice"

        val DEFAULT = Message()

    }

    @PrimaryKey
    var id: String = ""
    var conversationId: String = ""

    var author: Author? = Author.DEFAULT
    var status: String = Status.DEFAULT.name

    var addedAt: Long = 0
    var sentAt: Long = 0
    var deliveredAt: Long = 0
    var readAt: Long = 0

    var contentType: String = Content.DEFAULT.name
    var contentSystem: ContentSystem? = ContentSystem.DEFAULT
    var contentText: ContentText? = ContentText.DEFAULT
    var contentImage: ContentImage? = ContentImage.DEFAULT
    var contentDocument: ContentDocument? = ContentDocument.DEFAULT
    var contentAudio: ContentAudio? = ContentAudio.DEFAULT
    var contentVideo: ContentVideo? = ContentVideo.DEFAULT
    var contentWeb: ContentWeb? = ContentWeb.DEFAULT
    var contentLocation: ContentLocation? = ContentLocation.DEFAULT
    var contentVoice: ContentVoice? = ContentVoice.DEFAULT

}