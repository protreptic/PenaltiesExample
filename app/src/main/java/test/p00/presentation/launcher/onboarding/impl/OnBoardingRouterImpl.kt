package test.p00.presentation.launcher.onboarding.impl

import android.support.v4.app.FragmentManager
import test.p00.presentation.launcher.impl.LauncherFragment
import test.p00.presentation.launcher.onboarding.OnBoardingRouter

/**
 * Created by Peter Bukhal on 4/23/18.
 */
class OnBoardingRouterImpl(
        private val fragmentManager: FragmentManager?) : OnBoardingRouter {

    override fun toHome() {
        fragmentManager
                ?.beginTransaction()
                ?.replace(android.R.id.content,
                        LauncherFragment.newInstance(),
                        LauncherFragment.FRAGMENT_TAG)
                ?.commit() }

}