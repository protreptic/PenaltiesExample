package test.p00.data.storage.websocket

import com.google.gson.JsonObject
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import test.p00.data.model.conversation.Member
import java.lang.reflect.Type

/**
 * Created by Peter Bukhal on 5/11/18.
 */
class MemberSerializer : JsonSerializer<Member> {

    override fun serialize(src: Member?, typeOfSrc: Type?, context: JsonSerializationContext?) = JsonObject()

}