package test.p00.presentation.onboarding.impl

import android.support.v4.app.FragmentManager
import test.p00.presentation.abs.Router.Delegate
import test.p00.presentation.launcher.impl.LauncherFragment
import test.p00.presentation.onboarding.OnBoardingRouter
import test.p00.util.extension.pushRoot

/**
 * Created by Peter Bukhal on 4/23/18.
 */
class OnBoardingRouterImpl(
        override val fragmentManager: FragmentManager?,
        override val delegate: Delegate) : OnBoardingRouter {

    override fun toLauncher() {
        if (delegate.checkIfRoutingAvailable()) {
            fragmentManager?.pushRoot(LauncherFragment.newInstance())
        }
    }

}