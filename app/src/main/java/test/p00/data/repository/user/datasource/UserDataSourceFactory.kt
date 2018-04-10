package test.p00.data.repository.user.datasource

import test.p00.data.repository.user.datasource.impl.UserRealmDataSource

object UserDataSourceFactory {

    /**
     * Возвращает источник данных о пользователях.
     */
    fun create(): UserDataSource = UserRealmDataSource()

}