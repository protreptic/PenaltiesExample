package test.p00.data.storage.sqlite

import android.content.Context
import test.p00.data.storage.sqlite.abs.Storage
import test.p00.util.ContextProvider.Companion.provide

/**
 * Created by Peter Bukhal on 4/20/18.
 */
class ConversationsStorage(context: Context = provide()) : Storage(context, NAME, VERSION) {

    companion object {

        const val NAME = "conversations"
        const val VERSION = 1

    }

}