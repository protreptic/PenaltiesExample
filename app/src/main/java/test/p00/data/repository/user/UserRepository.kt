package test.p00.data.repository.user

import io.reactivex.Observable
import test.p00.data.model.user.User

interface UserRepository {

    /**
     * Возвращает пользователя приложения.
     */
    fun fetch(): Observable<User>

    /**
     * Сохраняет пользователя приложения.
     */
    fun retain(user: User): Observable<User>

}