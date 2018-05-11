package test.p00.data.storage.websocket

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import test.p00.data.model.conversation.message.Author
import java.lang.reflect.Type

/**
 * Created by Peter Bukhal on 5/10/18.
 */
class AuthorDeserializer : JsonDeserializer<Author> {

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?) = Author()

}