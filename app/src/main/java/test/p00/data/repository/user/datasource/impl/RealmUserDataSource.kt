package test.p00.data.repository.user.datasource.impl

import io.reactivex.Observable
import io.reactivex.Observable.*
import io.realm.Realm
import test.p00.data.model.user.User
import test.p00.data.repository.user.datasource.UserDataSource

class RealmUserDataSource : UserDataSource {

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    override fun fetch(): Observable<User> {
        Realm.getDefaultInstance().use { storage ->
            val user =
                    storage.where(User::class.java)
                            .findFirst()

            return when (user == null) {
                true -> retain(User())
                else -> just(storage.copyFromRealm(user))
            }
        }
    }

    override fun retain(user: User): Observable<User> {
        Realm.getDefaultInstance().use { storage ->
            storage.beginTransaction()

            val retainedUser =
                    storage.copyFromRealm(
                    storage.copyToRealmOrUpdate(user))

            storage.commitTransaction()

            return just(retainedUser)
        }
    }

}