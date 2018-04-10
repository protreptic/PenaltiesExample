package test.p00.data.repository.user.datasource

import io.reactivex.Observable
import test.p00.data.model.user.User

interface UserDataSource {

    /**
     * Извлекает из истоника пользователя.
     */
    fun fetch(): Observable<User>

    /**
     * Сохраняет в источнике данных пользователя.
     *
     * @param user пользователь
     */
    fun retain(user: User): Observable<User>

}