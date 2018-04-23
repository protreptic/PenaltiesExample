package test.p00.presentation.launcher.wizard.steps.impl

import android.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.InputFilter
import android.view.View
import android.widget.Toast
import test.p00.R
import test.p00.presentation.launcher.wizard.steps.abs.OnBoardingWizardStepFragment
import test.p00.util.glide.GlideApp

class AddVehicleLicenseOnBoardingWizardFragment : OnBoardingWizardStepFragment() {

    companion object {

        const val FRAGMENT_TAG = "tag_AddVehicleLicenseStepFragment"

        fun newInstance(): Fragment = AddVehicleLicenseOnBoardingWizardFragment().apply {
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

    override fun showError() {
        Toast.makeText(context, getString(R.string.input_valid_vehicle_license), Toast.LENGTH_LONG).show()
    }

}