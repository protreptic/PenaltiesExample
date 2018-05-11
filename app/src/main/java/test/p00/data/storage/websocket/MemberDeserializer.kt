package test.p00.data.storage.websocket

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import test.p00.data.model.conversation.Member
import java.lang.reflect.Type

/**
 * Created by Peter Bukhal on 5/11/18.
 */
class MemberDeserializer : JsonDeserializer<Member> {

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?) = Member()

}