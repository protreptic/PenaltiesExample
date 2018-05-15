package test.p00.presentation.signup.impl

import android.support.v4.app.FragmentManager
import test.p00.R
import test.p00.presentation.abs.Router
import test.p00.presentation.countries.impl.CountriesFragment
import test.p00.presentation.signup.SignUpRouter
import test.p00.presentation.signup.verification.impl.SignUpVerificationFragment

/**
 * Created by Peter Bukhal on 5/12/18.
 */
class SignUpRouterImpl(
        override val fragmentManager: FragmentManager?,
        override val delegate: Router.Delegate): SignUpRouter {

    override fun toCountries() {
        if (delegate.checkIfRoutingAvailable()) {
            fragmentManager
                    ?.beginTransaction()
                    ?.setCustomAnimations(
                            R.anim.slide_in_right, R.anim.slide_out_right,
                            R.anim.slide_in_right, R.anim.slide_out_right)
                    ?.add(android.R.id.content,
                            CountriesFragment.newInstance(),
                            CountriesFragment.FRAGMENT_TAG)
                    ?.addToBackStack(CountriesFragment.FRAGMENT_TAG)
                    ?.commit()
        }
    }

    override fun toVerification() {
        if (delegate.checkIfRoutingAvailable()) {
            fragmentManager
                    ?.beginTransaction()
                    ?.setCustomAnimations(
                            R.anim.slide_in_right, R.anim.slide_out_right,
                            R.anim.slide_in_right, R.anim.slide_out_right)
                    ?.add(android.R.id.content,
                            SignUpVerificationFragment.newInstance(),
                            SignUpVerificationFragment.FRAGMENT_TAG)
                    ?.addToBackStack(SignUpVerificationFragment.FRAGMENT_TAG)
                    ?.commit()
        }
    }

}