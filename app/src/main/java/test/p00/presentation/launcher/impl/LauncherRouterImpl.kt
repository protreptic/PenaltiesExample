package test.p00.presentation.launcher.impl

import android.support.v4.app.FragmentManager
import test.p00.presentation.abs.Router
import test.p00.presentation.launcher.LauncherRouter
import test.p00.presentation.launcher.onboarding.impl.OnBoardingFragment
import test.p00.presentation.launcher.wizard.impl.OnBoardingWizardFragment
import test.p00.presentation.home.impl.HomeFragment

/**
 * Created by Peter Bukhal on 4/23/18.
 */
class LauncherRouterImpl(
        private val routerDelegate: Router.Delegate,
        private val fragmentManager: FragmentManager?) : LauncherRouter {

    override fun toHome() {
        if (routerDelegate.checkIfRoutingAvailable()) {
            fragmentManager
                    ?.beginTransaction()
                    ?.replace(android.R.id.content,
                            HomeFragment.newInstance(),
                            HomeFragment.FRAGMENT_TAG)
                    ?.commit()
        }
    }

    override fun toOnBoardingWizard() {
        if (routerDelegate.checkIfRoutingAvailable()) {
            fragmentManager
                    ?.beginTransaction()
                    ?.replace(android.R.id.content,
                            OnBoardingWizardFragment.newInstance(),
                            OnBoardingWizardFragment.FRAGMENT_TAG)
                    ?.addToBackStack(OnBoardingWizardFragment.FRAGMENT_TAG)
                    ?.commit()
        }
    }

    override fun toOnBoarding() {
        if (routerDelegate.checkIfRoutingAvailable()) {
            fragmentManager
                    ?.beginTransaction()
                    ?.replace(android.R.id.content,
                            OnBoardingFragment.newInstance(),
                            OnBoardingFragment.FRAGMENT_TAG)
                    ?.commit()
        }
    }

}