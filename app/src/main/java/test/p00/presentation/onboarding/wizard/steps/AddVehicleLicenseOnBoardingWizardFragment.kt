package test.p00.presentation.onboarding.wizard.steps

import android.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat.getColor
import android.text.InputFilter
import android.view.View
import android.widget.Toast
import com.jakewharton.rxbinding2.widget.RxTextView
import test.p00.R
import test.p00.presentation.onboarding.wizard.steps.base.OnBoardingWizardStepFragment
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
                    .into(this)
        }

        vTitle.setText(R.string.input_valid_vehicle_license)

        vNumber.apply {
            setHint(R.string.input_valid_vehicle_license_hint)

            filters = listOf(InputFilter.AllCaps(),
                             InputFilter.LengthFilter(10)).toTypedArray()
        }

        vForward.setOnClickListener {
            presenter.addVehicleLicense(vNumber.text.toString()) }

        presenter.attachView(this)
    }

    override fun validateInput(input: String) {
        presenter.validateVehicleLicense(input)
    }

    override fun forward() {
        fragmentManager!!
                .beginTransaction()
                .setCustomAnimations(
                        R.anim.slide_in_right, R.anim.slide_out_left,
                        R.anim.slide_in_right, R.anim.slide_out_left)
                .add(R.id.wizard_content,
                    AddDriverOnBoardingWizardStepFragment.newInstance(),
                    AddDriverOnBoardingWizardStepFragment.FRAGMENT_TAG)
                .addToBackStack(null)
                .commit()
    }

    override fun skip() {
        AlertDialog.Builder(activity)
                .setMessage(getString(R.string.skip_vehicle_license_message))
                .setPositiveButton(getString(R.string.skip), { _, _ -> skipInternal() })
                .setNegativeButton(getString(R.string.input_number), { dialog, _ -> dialog.dismiss() })
                .create()
                .show()
    }

    private fun skipInternal() {
        fragmentManager!!
                .beginTransaction()
                .setCustomAnimations(
                        R.anim.slide_in_right, R.anim.slide_out_left,
                        R.anim.slide_in_right, R.anim.slide_out_left)
                .add(R.id.wizard_content,
                    AddDriverOnBoardingWizardStepFragment.newInstance(),
                    AddDriverOnBoardingWizardStepFragment.FRAGMENT_TAG)
                .addToBackStack(null)
                .commit()
    }

    override fun showError() {
        Toast.makeText(context, getString(R.string.input_valid_vehicle_license), Toast.LENGTH_LONG).show()
    }

}