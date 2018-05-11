package test.p00.data.storage.websocket.model.message

import test.p00.data.storage.websocket.model.RemoteContentLocation

/**
 * Created by Peter Bukhal on 4/29/18.
 */
class RemoteMessage {

    var id: String = ""
    var createdAt: Long = 0
    var status: Int = 0
    var author: String? = ""

    var contentType: String? = ""
    var contentText: String? = ""
    var contentLocation: RemoteContentLocation? = null

    var message: String? = ""

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RemoteMessage

        if (id != other.id) return false
        if (createdAt != other.createdAt) return false
        if (status != other.status) return false
        if (author != other.author) return false
        if (contentType != other.contentType) return false
        if (contentText != other.contentText) return false
        if (message != other.message) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + createdAt.hashCode()
        result = 31 * result + status
        result = 31 * result + (author?.hashCode() ?: 0)
        result = 31 * result + (contentType?.hashCode() ?: 0)
        result = 31 * result + (contentText?.hashCode() ?: 0)
        result = 31 * result + (message?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "RemoteMessage(id='$id', createdAt=$createdAt, status=$status, author=$author, contentType=$contentType, contentText=$contentText, contentLocation=$contentLocation, message=$message)"
    }

}