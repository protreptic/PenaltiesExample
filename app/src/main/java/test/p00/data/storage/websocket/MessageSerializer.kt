package test.p00.data.storage.websocket

import com.google.gson.JsonObject
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import test.p00.data.model.conversation.message.Message
import java.lang.reflect.Type

/**
 * Created by Peter Bukhal on 5/10/18.
 */
class MessageSerializer : JsonSerializer<Message> {

    override fun serialize(src: Message?, typeOfSrc: Type?, context: JsonSerializationContext?) = JsonObject()

}