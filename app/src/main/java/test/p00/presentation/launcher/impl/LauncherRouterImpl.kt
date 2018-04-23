package test.p00.presentation.launcher.impl

import android.support.v4.app.FragmentManager
import test.p00.presentation.launcher.LauncherRouter
import test.p00.presentation.onboarding.impl.OnBoardingFragment
import test.p00.presentation.onboarding.wizard.OnBoardingWizardFragment
import test.p00.presentation.penalty.PenaltiesFragment

/**
 * Created by Peter Bukhal on 4/23/18.
 */
class LauncherRouterImpl(private val fragmentManager: FragmentManager?) : LauncherRouter {

    override fun runMain() {
        fragmentManager?.beginTransaction()?.replace(android.R.id.content,
                PenaltiesFragment.newInstance(),
                PenaltiesFragment.FRAGMENT_TAG)?.commit()
    }

    override fun runOnBoardingWizard() {
        fragmentManager?.beginTransaction()?.replace(android.R.id.content,
                OnBoardingWizardFragment.newInstance(),
                OnBoardingWizardFragment.FRAGMENT_TAG)?.commit()
    }

    override fun runOnBoarding() {
        fragmentManager?.beginTransaction()?.replace(android.R.id.content,
                OnBoardingFragment.newInstance(),
                OnBoardingFragment.FRAGMENT_TAG)?.commit()
    }

}