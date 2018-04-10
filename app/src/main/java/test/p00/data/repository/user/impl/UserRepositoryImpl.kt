package test.p00.data.repository.user.impl

import test.p00.data.model.user.User
import test.p00.data.repository.user.UserRepository
import test.p00.data.repository.user.datasource.UserDataSource

class UserRepositoryImpl(private val source: UserDataSource) : UserRepository {

    override fun fetch() = source.fetch()
    override fun retain(user: User) = source.retain(user)

}