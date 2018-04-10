package test.p00.data.repository.user

import test.p00.data.repository.user.datasource.UserDataSourceFactory
import test.p00.data.repository.user.impl.UserRepositoryImpl

object UserRepositoryFactory {

    /**
     * Возвращает хранилище пользователей.
     */
    fun create(): UserRepository =
                  UserRepositoryImpl(UserDataSourceFactory.create())

}