package test.p00.presentation.onboarding.wizard.steps.impl

import android.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.InputFilter
import android.view.View
import test.p00.R
import test.p00.presentation.auxiliary.glide.GlideApp
import test.p00.presentation.onboarding.wizard.steps.abs.OnBoardingWizardStepFragment

class OnBoardingWizardAddVehicleLicenseStepFragment : OnBoardingWizardStepFragment() {

    companion object {

        const val FRAGMENT_TAG = "tag_AddVehicleLicenseStepFragment"

        fun newInstance(): Fragment = OnBoardingWizardAddVehicleLicenseStepFragment().apply {
            arguments = Bundle.EMPTY
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vBanner.apply {
            GlideApp.with(context)
                    .load("file:///android_asset/onboarding/wizard/auto_pass.png")
                    .centerInside()
                    .into(this) }

        vTitle.setText(R.string.input_valid_vehicle_license)

        vNumber.apply {
            setHint(R.string.input_valid_vehicle_license_hint)

            filters = listOf(InputFilter.AllCaps(),
                             InputFilter.LengthFilter(10)).toTypedArray() }

        vForward.setOnClickListener {
            presenter.addVehicleLicense(vNumber.text.toString()) }

        presenter.attachView(this)
    }

    override fun validateInput(input: String) {
        presenter.validateVehicleLicense(input)
    }

    override fun showConformationDialog() {
        AlertDialog.Builder(activity)
                .setMessage(getString(R.string.skip_vehicle_license_message))
                .setPositiveButton(getString(R.string.skip), { _, _ -> presenter.skipAddVehicleLicense() })
                .setNegativeButton(getString(R.string.input_number), { dialog, _ -> dialog.dismiss() })
                .create()
                .show()
    }

}