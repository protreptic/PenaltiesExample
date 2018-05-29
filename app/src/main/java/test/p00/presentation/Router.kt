package test.p00.presentation

import android.support.v4.app.FragmentManager
import test.p00.presentation.home.impl.HomeFragment
import test.p00.auxiliary.extensions.pop
import test.p00.auxiliary.extensions.pushRoot

/**
 * Created by Peter Bukhal on 4/24/18.
 */
interface Router {

    interface Delegate {

        fun isFragmentTransactionAllowed(): Boolean

    }

    val fragmentManager: FragmentManager?
    val delegate: Delegate

    fun checkIfBackStackNotEmpty() =
            fragmentManager?.backStackEntryCount != 0

    fun purifyRoute() {
        if (delegate.isFragmentTransactionAllowed()) {
            while (checkIfBackStackNotEmpty()) {
                fragmentManager?.pop(immediate = true)
            }
        }
    }

    fun toHome() {
        purifyRoute()

        if (delegate.isFragmentTransactionAllowed()) {
            fragmentManager?.pushRoot(HomeFragment.newInstance())
        }
    }

    fun back() {
        if (delegate.isFragmentTransactionAllowed() && checkIfBackStackNotEmpty()) {
            fragmentManager?.pop()
        }
    }

}