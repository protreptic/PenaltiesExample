package test.p00.presentation.signup.impl

import android.support.v4.app.FragmentManager
import test.p00.presentation.Router
import test.p00.presentation.countries.impl.CountriesFragment
import test.p00.presentation.launcher.impl.LauncherFragment
import test.p00.presentation.signup.SignUpRouter
import test.p00.presentation.signup.verification.impl.SignUpVerificationFragment
import test.p00.auxiliary.extensions.push
import test.p00.auxiliary.extensions.pop
import test.p00.auxiliary.extensions.pushRoot

/**
 * Created by Peter Bukhal on 5/12/18.
 */
class SignUpRouterImpl(
        override val fragmentManager: FragmentManager?,
        override val delegate: Router.Delegate): SignUpRouter {

    override fun toPhone() {
        if (delegate.isFragmentTransactionAllowed()) {
            fragmentManager?.pop(SignUpVerificationFragment.FRAGMENT_TAG)
        }
    }

    override fun toVerification() {
        if (delegate.isFragmentTransactionAllowed()) {
            fragmentManager?.push(SignUpVerificationFragment.newInstance())
        }
    }

    override fun toLauncher() {
        purifyRoute()

        if (delegate.isFragmentTransactionAllowed()) {
            fragmentManager?.pushRoot(LauncherFragment.newInstance())
        }
    }

    override fun toCountries() {
        if (delegate.isFragmentTransactionAllowed()) {
            fragmentManager?.push(CountriesFragment.newInstance())
        }
    }

}