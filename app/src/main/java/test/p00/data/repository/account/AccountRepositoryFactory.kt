package test.p00.data.repository.account

import test.p00.data.repository.account.impl.AccountRepositoryImpl

/**
 * Created by Peter Bukhal on 5/15/18.
 */
object AccountRepositoryFactory {

    fun create(): AccountRepository = AccountRepositoryImpl()

}