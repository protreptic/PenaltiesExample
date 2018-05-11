package test.p00.presentation.model.conversation

import test.p00.data.model.conversation.message.Author

/**
 * Created by Peter Bukhal on 5/10/18.
 */
class AuthorModel(val id: String, val name: String) {

    object Mapper {

        fun map(author: Author) =
                AuthorModel(author.id, author.name)

    }

}