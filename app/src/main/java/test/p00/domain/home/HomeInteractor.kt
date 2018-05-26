package test.p00.domain.home

import io.reactivex.Observable
import test.p00.data.model.user.User
import test.p00.data.repository.user.UserRepository
import test.p00.domain.abs.Interactor
import javax.inject.Inject

/**
 * Created by Peter Bukhal on 4/27/18.
 */
class HomeInteractor
    @Inject constructor(
        private val userRepository: UserRepository):
            Interactor {

    fun fetchUser(): Observable<User> = userRepository.fetch()

}