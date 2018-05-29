package test.p00.presentation.onboarding.impl

import android.support.v4.app.FragmentManager
import test.p00.presentation.Router.Delegate
import test.p00.presentation.launcher.impl.LauncherFragment
import test.p00.presentation.onboarding.OnBoardingRouter

/**
 * Created by Peter Bukhal on 4/23/18.
 */
class OnBoardingRouterImpl(
        override val fragmentManager: FragmentManager?,
        override val delegate: Delegate) : OnBoardingRouter {

    override fun toLauncher() {
        push(LauncherFragment.newInstance(), root = true)
    }

}