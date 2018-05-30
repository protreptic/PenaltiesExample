package test.p00.presentation

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import test.p00.presentation.home.impl.HomeFragment
import test.p00.auxiliary.extensions.pop
import test.p00.auxiliary.extensions.push
import test.p00.auxiliary.extensions.pushRoot

/**
 * Created by Peter Bukhal on 4/24/18.
 */
interface Router {

    interface Delegate {

        fun transactionAllowed(): Boolean

    }

    val fragmentManager: FragmentManager?
    val delegate: Delegate

    fun backStackNotEmpty() =
            fragmentManager?.backStackEntryCount != 0

    fun purifyRoute() {
        while (backStackNotEmpty()) {
            transaction {
                fragmentManager?.pop(immediate = true)
            }
        }
    }

    fun toHome() {
        purifyRoute()
        push(HomeFragment.newInstance(), root = true)
    }

    fun push(fragment: Fragment, root: Boolean = false) {
        transaction {
            if (root) {
                fragmentManager?.pushRoot(fragment)
            } else {
                fragmentManager?.push(fragment)
            }
        }
    }

    fun back() {
        transaction {
            if (backStackNotEmpty()) {
                fragmentManager?.pop()
            }
        }
    }

    fun transaction(transaction: () -> Unit) {
        if (delegate.transactionAllowed()) {
            transaction()
        }
    }

}