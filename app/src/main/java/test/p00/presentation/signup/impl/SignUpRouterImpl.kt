package test.p00.presentation.signup.impl

import android.support.v4.app.FragmentManager
import test.p00.presentation.abs.Router
import test.p00.presentation.countries.impl.CountriesFragment
import test.p00.presentation.launcher.impl.LauncherFragment
import test.p00.presentation.signup.SignUpRouter
import test.p00.presentation.signup.verification.impl.SignUpVerificationFragment
import test.p00.extensions.push
import test.p00.extensions.pop
import test.p00.extensions.pushRoot

/**
 * Created by Peter Bukhal on 5/12/18.
 */
class SignUpRouterImpl(
        override val fragmentManager: FragmentManager?,
        override val delegate: Router.Delegate): SignUpRouter {

    override fun toPhone() {
        if (delegate.checkIfRoutingAvailable()) {
            fragmentManager?.pop(SignUpVerificationFragment.FRAGMENT_TAG)
        }
    }

    override fun toVerification() {
        if (delegate.checkIfRoutingAvailable()) {
            fragmentManager?.push(SignUpVerificationFragment.newInstance())
        }
    }

    override fun toLauncher() {
        purifyRoute()

        if (delegate.checkIfRoutingAvailable()) {
            fragmentManager?.pushRoot(LauncherFragment.newInstance())
        }
    }

    override fun toCountries() {
        if (delegate.checkIfRoutingAvailable()) {
            fragmentManager?.push(CountriesFragment.newInstance())
        }
    }

}