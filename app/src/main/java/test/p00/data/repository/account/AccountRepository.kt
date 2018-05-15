package test.p00.data.repository.account

import io.reactivex.Observable
import test.p00.data.model.account.Account

/**
 * Created by Peter Bukhal on 5/15/18.
 */
interface AccountRepository {

    /**
     *
     */
    fun fetchEverything(): Observable<List<Account>>

}