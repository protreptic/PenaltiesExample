package test.p00.data.model.conversation

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import test.p00.data.model.conversation.message.Message

/**
 * Created by Peter Bukhal on 4/27/18.
 */
open class Conversation : RealmObject() {

    companion object {

        const val FIELD_ID = "id"
        const val FIELD_MEMBERS = "members"
        const val FIELD_MESSAGES = "messages"

    }

    @PrimaryKey
    var id: String = ""
    var members: RealmList<Member> = RealmList()
    var messages: RealmList<Message> = RealmList()

}