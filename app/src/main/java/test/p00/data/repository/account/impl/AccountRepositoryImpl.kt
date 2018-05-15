package test.p00.data.repository.account.impl

import io.reactivex.Observable
import test.p00.data.model.account.Account
import test.p00.data.repository.account.AccountRepository

/**
 * Created by Peter Bukhal on 5/15/18.
 */
class AccountRepositoryImpl : AccountRepository {

    override fun fetchEverything(): Observable<List<Account>> = Observable.never()

}