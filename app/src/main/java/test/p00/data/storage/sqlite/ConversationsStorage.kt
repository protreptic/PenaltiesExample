package test.p00.data.storage.sqlite

import android.content.Context
import test.p00.data.storage.sqlite.abs.Storage
import javax.inject.Inject

/**
 * Created by Peter Bukhal on 4/20/18.
 */
class ConversationsStorage
    @Inject constructor(context: Context):
        Storage(context, NAME, VERSION) {

    companion object {

        const val NAME = "conversations"
        const val VERSION = 1

    }

}