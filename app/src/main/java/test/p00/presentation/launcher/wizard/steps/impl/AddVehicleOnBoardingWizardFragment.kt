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

class AddVehicleOnBoardingWizardFragment : OnBoardingWizardStepFragment() {

    companion object {

        const val FRAGMENT_TAG = "tag_AddVehicleStepFragment"

        fun newInstance(): Fragment = AddVehicleOnBoardingWizardFragment().apply {
            arguments = Bundle.EMPTY
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vBanner.apply {
            GlideApp.with(context)
                    .load("file:///android_asset/onboarding/wizard/auto_num.png")
                    .centerInside()
                    .into(this) }

        vTitle.setText(R.string.input_valid_vehicle_number)

        vNumber.apply {
            setHint(R.string.input_valid_vehicle_number_hint)

            filters = listOf(InputFilter.AllCaps(),
                             InputFilter.LengthFilter(9)).toTypedArray() }

        vForward.setOnClickListener {
            presenter.addVehicle(getString(R.string.my_car),
                vNumber.text.toString()) }

        presenter.attachView(this)
    }

    override fun validateInput(input: String) {
        presenter.validateVehicle(input)
    }

    override fun showConformationDialog() {
        AlertDialog.Builder(activity)
            .setMessage(getString(R.string.skip_vehicle_message))
            .setPositiveButton(getString(R.string.skip), { _, _ -> presenter.skipAddVehicle() })
            .setNegativeButton(getString(R.string.input_number), { dialog, _ -> dialog.dismiss() })
            .create()
            .show()
    }

    override fun showError() {
        Toast.makeText(context, getString(R.string.input_valid_vehicle_number), Toast.LENGTH_LONG).show()
    }

}