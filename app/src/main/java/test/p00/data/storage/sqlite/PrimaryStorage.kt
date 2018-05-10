package test.p00.data.storage.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import test.p00.util.ContextProvider.Companion.provide
import java.io.Closeable

/**
 * Created by Peter Bukhal on 4/20/18.
 */
class PrimaryStorage(context: Context = provide()) : SQLiteOpenHelper(context, NAME, null, VERSION), Closeable {

    companion object {

        const val NAME = "primary"
        const val VERSION = 1

    }

    override fun onCreate(storage: SQLiteDatabase) {
        storage.execSQL("CREATE TABLE SETTINGS (id INT, name VARCHAR(255), value VARCHAR(255))")

        storage.execSQL("INSERT INTO SETTINGS (id, name, value) VALUES (1, 'wasOnBoardingWizardShown', 'false')")
        storage.execSQL("INSERT INTO SETTINGS (id, name, value) VALUES (2, 'wasOnBoardingShown', 'false')")
    }

    override fun onUpgrade(storage: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}

}