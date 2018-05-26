package test.p00.data.storage.sqlite

import android.content.Context
import android.database.Cursor
import io.reactivex.Observable
import test.p00.data.model.user.UserNew
import test.p00.data.model.user.profile.UserProfile
import test.p00.data.repository.countries.datasource.CountriesDataSource
import test.p00.data.repository.countries.datasource.impl.CountriesDataSourceImpl
import test.p00.data.storage.sqlite.abs.Storage
import javax.inject.Inject

class UsersStorage
    @Inject constructor(
            private val context1: Context):
                Storage(context1, NAME, VERSION) {

    companion object {

        const val NAME = "users"
        const val VERSION = 1

    }

    private fun user(cursor: Cursor): UserNew {
        val id = cursor.getInt(cursor.getColumnIndexOrThrow("ID"))
        val accessToken = cursor.getString(cursor.getColumnIndexOrThrow("ACCESS_TOKEN"))
        val pushToken = cursor.getString(cursor.getColumnIndexOrThrow("PUSH_TOKEN"))
        val isDefault = (cursor.getInt(cursor.getColumnIndexOrThrow("IS_DEFAULT")) == 1)

        val phoneNumber = cursor.getString(cursor.getColumnIndexOrThrow("PHONE_NUMBER"))

        return UserNew(id, accessToken, pushToken, isDefault,
                UserProfile(userId = id, phoneNumber = phoneNumber))
    }

    private fun users(cursor: Cursor) =
            mutableListOf<UserNew>().apply {
                while (cursor.moveToNext()) {
                    add(user(cursor))
                }
            }.toList()

    fun fetchEverything(): Observable<List<UserNew>> =
            Observable.create { source ->
                CountriesDataSourceImpl(context1).fetch("SELECT USERS.*, USER_PROFILES.PHONE_NUMBER FROM USERS, USER_PROFILES") { cursor ->
                    source.onNext(users(cursor))
                    source.onComplete()
                }
            }

    fun fetchDefault(): Observable<UserNew> =
            Observable.create { source ->
                CountriesDataSourceImpl(context1).fetch("SELECT * FROM USERS WHERE IS_DEFAULT = 1 LIMIT 1") { cursor ->
                    while (cursor.moveToNext()) {
                        source.onNext(user(cursor))
                        source.onComplete()
                    }
                }
            }

    fun fetchById(userId: Int): Observable<UserNew> =
            Observable.create { source ->
                val arguments = arrayOf(userId.toString())

                CountriesDataSourceImpl(context1).fetch("SELECT * FROM USERS WHERE ID = ? LIMIT 1", arguments) { cursor ->
                    while (cursor.moveToNext()) {
                        source.onNext(user(cursor))
                        source.onComplete()
                    }
                }
            }

}