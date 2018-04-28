package test.p00.presentation.abs

import android.support.v4.app.FragmentManager
import test.p00.presentation.home.impl.HomeFragment

/**
 * Created by Peter Bukhal on 4/24/18.
 */
interface Router {

    interface Delegate {

        fun checkIfRoutingAvailable(): Boolean

    }

    val fragmentManager: FragmentManager?
    val delegate: Delegate

    fun purifyRoute() {
        if (delegate.checkIfRoutingAvailable()) {
            while (fragmentManager?.backStackEntryCount != 0) {
                   fragmentManager?.popBackStackImmediate()
            }
        }
    }

    fun toHome() {
        purifyRoute()

        if (delegate.checkIfRoutingAvailable()) {
            fragmentManager
                ?.beginTransaction()
                ?.replace(android.R.id.content,
                        HomeFragment.newInstance(),
                        HomeFragment.FRAGMENT_TAG)
                ?.commit()
        }
    }

}