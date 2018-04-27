package test.p00.data.repository.user.datasource

import test.p00.data.repository.user.datasource.impl.RealmUserDataSource

object UserDataSourceFactory {

    /**
     * Возвращает источник данных о пользователях.
     */
    fun create(): UserDataSource = RealmUserDataSource()

}