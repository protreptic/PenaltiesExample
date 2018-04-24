package test.p00.presentation.launcher.wizard.impl

import android.support.v4.app.FragmentManager
import test.p00.R
import test.p00.presentation.launcher.impl.LauncherFragment
import test.p00.presentation.launcher.wizard.OnBoardingWizardRouter
import test.p00.presentation.launcher.wizard.introductory.impl.OnBoardingWizardIntroductoryFragment
import test.p00.presentation.launcher.wizard.steps.impl.OnBoardingWizardAddDriverStepFragment
import test.p00.presentation.launcher.wizard.steps.impl.OnBoardingWizardAddVehicleLicenseStepFragment
import test.p00.presentation.launcher.wizard.steps.impl.OnBoardingWizardAddVehicleStepFragment

/**
 * Created by Peter Bukhal on 4/23/18.
 */
class OnBoardingWizardRouterImpl(
        private val fragmentManager: FragmentManager?) : OnBoardingWizardRouter {

    override fun toHome() {
        fragmentManager
                ?.beginTransaction()
                ?.replace(android.R.id.content,
                        LauncherFragment.newInstance(),
                        LauncherFragment.FRAGMENT_TAG)
                ?.commit() }

    override fun toIntroductory() {
        fragmentManager
                ?.beginTransaction()
                ?.replace(R.id.wizard_content,
                        OnBoardingWizardIntroductoryFragment.newInstance(),
                        OnBoardingWizardIntroductoryFragment.FRAGMENT_TAG)
                ?.commit() }

    override fun toBeginning() { toAddVehicleStep() }

    override fun toAddVehicleStep() {
        fragmentManager
                ?.beginTransaction()
                ?.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                ?.replace(R.id.wizard_content,
                        OnBoardingWizardAddVehicleStepFragment.newInstance(),
                        OnBoardingWizardAddVehicleStepFragment.FRAGMENT_TAG)
                ?.commit() }

    override fun toAddVehicleLicenseStep() {
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

    override fun toAddDriverStep() {
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