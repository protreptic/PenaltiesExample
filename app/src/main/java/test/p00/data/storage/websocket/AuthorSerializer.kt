package test.p00.data.storage.websocket

import com.google.gson.JsonObject
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import test.p00.data.model.conversation.message.Author
import java.lang.reflect.Type

/**
 * Created by Peter Bukhal on 5/10/18.
 */
class AuthorSerializer : JsonSerializer<Author> {

    override fun serialize(src: Author?, typeOfSrc: Type?, context: JsonSerializationContext?) = JsonObject()

}