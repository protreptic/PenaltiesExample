package test.p00.presentation.onboarding.wizard.impl

import android.support.v4.app.FragmentManager
import test.p00.R
import test.p00.presentation.abs.Router.Delegate
import test.p00.presentation.launcher.impl.LauncherFragment
import test.p00.presentation.onboarding.wizard.OnBoardingWizardRouter
import test.p00.presentation.onboarding.wizard.introductory.impl.OnBoardingWizardIntroductoryFragment
import test.p00.presentation.onboarding.wizard.steps.impl.OnBoardingWizardAddDriverStepFragment
import test.p00.presentation.onboarding.wizard.steps.impl.OnBoardingWizardAddVehicleLicenseStepFragment
import test.p00.presentation.onboarding.wizard.steps.impl.OnBoardingWizardAddVehicleStepFragment
import test.p00.extensions.push
import test.p00.extensions.pushRoot

/**
 * Created by Peter Bukhal on 4/23/18.
 */
class OnBoardingWizardRouterImpl(
        override val fragmentManager: FragmentManager?,
        override val delegate: Delegate) : OnBoardingWizardRouter {

    override fun toLauncher() {
        if (delegate.checkIfRoutingAvailable()) {
            fragmentManager?.pushRoot(LauncherFragment.newInstance())
        }
    }

    override fun toIntroductory() {
        if (delegate.checkIfRoutingAvailable()) {
            fragmentManager?.pushRoot(
                    OnBoardingWizardIntroductoryFragment.newInstance(),
                    containerId = R.id.wizard_content)
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
            fragmentManager?.push(
                    OnBoardingWizardAddVehicleLicenseStepFragment.newInstance(),
                    containerId = R.id.wizard_content)
        }
    }

    override fun toAddDriverStep() {
        if (delegate.checkIfRoutingAvailable()) {
            fragmentManager?.push(
                    OnBoardingWizardAddDriverStepFragment.newInstance(),
                    containerId = R.id.wizard_content)
        }
    }

}