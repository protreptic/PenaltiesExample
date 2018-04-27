package test.p00.domain.home

import io.reactivex.Observable
import test.p00.data.model.user.User
import test.p00.data.repository.user.UserRepository
import test.p00.data.repository.user.UserRepositoryFactory
import test.p00.domain.abs.Interactor

/**
 * Created by Peter Bukhal on 4/27/18.
 */
class HomeInteractor(
        private val userRepository: UserRepository =
                                    UserRepositoryFactory.create()) : Interactor {

    fun displayUser(): Observable<User> = userRepository.fetch()

}