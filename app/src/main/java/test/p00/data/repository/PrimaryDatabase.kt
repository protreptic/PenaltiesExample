package test.p00.data.repository

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.util.Log
import test.p00.data.model.countries.Country

/**
 * Created by Peter Bukhal on 6/15/18.
 */
@Database(version = 1,
            exportSchema = false,
                entities = [
                    Country::class ])
abstract class PrimaryDatabase : RoomDatabase() {

    companion object {

        private const val SCRIPTS_DIRECTORY = "storage"
        private const val SCRIPTS_MIGRATIONS_DIRECTORY = "migrations"

        private var instance: PrimaryDatabase? = null

        @Synchronized
        fun instance(context: Context): PrimaryDatabase {
            if (instance == null) {
                instance =
                        Room.databaseBuilder(context, PrimaryDatabase::class.java, "countries")
                            .fallbackToDestructiveMigration()
                            .addCallback(object : RoomDatabase.Callback() {
                                override fun onCreate(db: SupportSQLiteDatabase) {
                                    applyDataScript(context, "countries", 1, db)
                                }
                            })
                            .build()
            }

            return instance!!
        }

        private fun applyDataScript(context: Context, name: String, version: Int, db: SupportSQLiteDatabase) {
            db.beginTransaction()

            try {
                val dataScript = readDataScript(context, name, version)

                db.execSQL(dataScript)
                db.setTransactionSuccessful()
            } catch (e: Exception) {
                Log.e("SQL", "MESSAGE: ${e.message}")
            } finally {
                db.endTransaction()
            }
        }

        private fun readDataScript(context: Context, name: String, version: Int): String =
                context.assets
                        .open(String.format("%s/%s_%d_data.sql",
                                "$SCRIPTS_DIRECTORY/$name",
                                        name, version))
                        .reader()
                        .readText()

    }

    abstract fun countries(): CountryDao

}