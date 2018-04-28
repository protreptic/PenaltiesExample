package test.p00.presentation.onboarding.wizard.impl

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import test.p00.R
import test.p00.presentation.abs.Router.Delegate
import test.p00.presentation.launcher.impl.LauncherFragment
import test.p00.presentation.onboarding.wizard.OnBoardingWizardRouter
import test.p00.presentation.onboarding.wizard.introductory.impl.OnBoardingWizardIntroductoryFragment
import test.p00.presentation.onboarding.wizard.steps.impl.OnBoardingWizardAddDriverStepFragment
import test.p00.presentation.onboarding.wizard.steps.impl.OnBoardingWizardAddVehicleLicenseStepFragment
import test.p00.presentation.onboarding.wizard.steps.impl.OnBoardingWizardAddVehicleStepFragment

/**
 * Created by Peter Bukhal on 4/23/18.
 */
class OnBoardingWizardRouterImpl(
        override val fragmentManager: FragmentManager?,
        override val delegate: Delegate) : OnBoardingWizardRouter {

    override fun toLauncher() {
        if (delegate.checkIfRoutingAvailable()) {
            fragmentManager
                    ?.beginTransaction()
                    ?.replace(android.R.id.content,
                            LauncherFragment.newInstance(),
                            LauncherFragment.FRAGMENT_TAG)
                    ?.commit()
        }
    }

    override fun toIntroductory() {
        if (delegate.checkIfRoutingAvailable()) {
            fragmentManager
                    ?.beginTransaction()
                    ?.replace(R.id.wizard_content,
                            OnBoardingWizardIntroductoryFragment.newInstance(),
                            OnBoardingWizardIntroductoryFragment.FRAGMENT_TAG)
                    ?.commit()
        }
    }

    override fun toBeginning() { toAddVehicleStep() }

    override fun toAddVehicleStep() {
        if (delegate.checkIfRoutingAvailable()) {
            fragmentManager
                    ?.beginTransaction()
                    ?.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                    ?.replace(R.id.wizard_content,
                            OnBoardingWizardAddVehicleStepFragment.newInstance(),
                            OnBoardingWizardAddVehicleStepFragment.FRAGMENT_TAG)
                    ?.commit()
        }
    }

    override fun toAddVehicleLicenseStep() {
        if (delegate.checkIfRoutingAvailable()) {
            fragmentManager
                    ?.beginTransaction()
                    ?.setCustomAnimations(
                            R.anim.slide_in_right, R.anim.slide_out_left,
                            R.anim.slide_in_right, R.anim.slide_out_left)
                    ?.add(R.id.wizard_content,
                            OnBoardingWizardAddVehicleLicenseStepFragment.newInstance(),
                            OnBoardingWizardAddVehicleLicenseStepFragment.FRAGMENT_TAG)
                    ?.addToBackStack(null)
                    ?.commit()
        }
    }

    override fun toAddDriverStep() {
        if (delegate.checkIfRoutingAvailable()) {
            fragmentManager
                    ?.beginTransaction()
                    ?.setCustomAnimations(
                            R.anim.slide_in_right, R.anim.slide_out_left,
                            R.anim.slide_in_right, R.anim.slide_out_left)
                    ?.add(R.id.wizard_content,
                            OnBoardingWizardAddDriverStepFragment.newInstance(),
                            OnBoardingWizardAddDriverStepFragment.FRAGMENT_TAG)
                    ?.addToBackStack(null)
                    ?.commit()
        }
    }

}