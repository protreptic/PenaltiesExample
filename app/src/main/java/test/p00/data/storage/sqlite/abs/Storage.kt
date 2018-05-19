package test.p00.data.storage.sqlite.abs

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.io.Closeable

/**
 * Created by Peter Bukhal on 5/12/18.
 */
abstract class Storage(
        protected val context: Context,
        protected val name: String,
        protected val version: Int):
        SQLiteOpenHelper(context, name, null, version), Closeable {

    companion object {

        private const val SCRIPTS_DIRECTORY = "storage"
        private const val SCRIPTS_MIGRATIONS_DIRECTORY = "migrations"

    }

    fun fetch(query: String, arguments: Array<String>? = null, block: (Cursor) -> Unit) =
        read { storage ->
            storage.rawQuery(query, arguments).use { cursor ->
                block(cursor)
            }
        }

    fun read(writable: Boolean = false, block: (SQLiteDatabase) -> Unit) {
        var exception: Throwable? = null

        try {
            return block(if (writable) writableDatabase else readableDatabase)
        } catch (e: Throwable) {
            exception = e

            throw e
        } finally {
            when(exception) {
                null -> close()
                else -> {
                    try {
                        close()
                    } catch (closeException: Throwable) {
                        // игнорируем
                    }
                }
            }
        }
    }

    private fun creationScript(): String =
            context.assets
                    .open(String.format("%s/%s_%d.sql",
                            "$SCRIPTS_DIRECTORY/$name",
                                    name, version))
                    .reader()
                    .readText()

    private fun creationDataScript(): String =
            context.assets
                    .open(String.format("%s/%s_%d_data.sql",
                            "$SCRIPTS_DIRECTORY/$name",
                                    name, version))
                    .reader()
                    .readText()

    private fun migrationScript(oldVersion: Int, newVersion: Int): String =
            context.assets
                    .open(String.format("%s/%s_%d_to_%d.sql",
                            "$SCRIPTS_DIRECTORY/$name/$SCRIPTS_MIGRATIONS_DIRECTORY",
                                    name, oldVersion, newVersion))
                    .reader()
                    .readText()

    override fun onCreate(storage: SQLiteDatabase) {
        storage.beginTransaction()

        try {
            storage.execSQL(creationScript())
            storage.execSQL(creationDataScript())
            storage.setTransactionSuccessful()
        } catch (e: Exception) {
            Log.e("SQL", "MESSAGE: ${e.message}")
        } finally {
            storage.endTransaction()
        }
    }

    override fun onUpgrade(storage: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        while (oldVersion < newVersion) {
            /* Миграция БД */
            storage.beginTransaction()

            try {
                storage.execSQL(migrationScript(oldVersion, newVersion))
                storage.setTransactionSuccessful()
            } catch (e: Exception) {
                Log.e("SQL", "MESSAGE: ${e.message}")
            } finally {
                storage.endTransaction()
            }
        }
    }

}