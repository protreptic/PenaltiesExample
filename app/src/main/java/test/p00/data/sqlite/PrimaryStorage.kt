package test.p00.data.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import test.p00.util.ContextProvider.Companion.context
import java.io.Closeable

/**
 * Created by Peter Bukhal on 4/20/18.
 */
class PrimaryStorage(context: Context = context()) : SQLiteOpenHelper(context, NAME, null, VERSION), Closeable {

    companion object {

        const val NAME = "db_primary"
        const val VERSION = 1

        private val storage by lazy { PrimaryStorage() }

        fun createReadable() = storage.readableDatabase
        fun createWriteable() = storage.writableDatabase

    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE SETTINGS (id INT, name VARCHAR(255), value VARCHAR(255))")

        db.execSQL("INSERT INTO SETTINGS (id, name, value) VALUES (1, 'wasOnBoardingWizardShown', 'false')")
        db.execSQL("INSERT INTO SETTINGS (id, name, value) VALUES (2, 'wasOnBoardingShown', 'false')")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}

}