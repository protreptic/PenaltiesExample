package test.p00.presentation.onboarding.wizard.steps.impl

import android.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.InputFilter
import android.view.View
import test.p00.R
import test.p00.auxiliary.glide.GlideApp
import test.p00.presentation.onboarding.wizard.steps.abs.OnBoardingWizardStepFragment

class OnBoardingWizardAddDriverStepFragment : OnBoardingWizardStepFragment() {

    companion object {

        const val FRAGMENT_TAG = "tag_AddDriverStepFragment"

        fun newInstance(): Fragment = OnBoardingWizardAddDriverStepFragment().apply {
            arguments = Bundle.EMPTY
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vBanner.apply {
            GlideApp.with(context)
                    .load("file:///android_asset/onboarding/wizard/driver_lic.png")
                    .centerInside()
                    .into(this) }

        vTitle.setText(R.string.input_valid_driver_license)

        vNumber.apply {
            setHint(R.string.input_valid_driver_license_hint)

            filters = listOf(InputFilter.AllCaps(),
                             InputFilter.LengthFilter(10)).toTypedArray() }

        vForward.setOnClickListener {
            presenter.addDriver(getString(R.string.new_driver_license),
                vNumber.text.toString()) }

        presenter.attachView(this)
    }

    override fun validateInput(input: String) {
        presenter.validateDriver(input)
    }

    override fun showConformationDialog() {
        AlertDialog.Builder(activity)
                .setMessage(getString(R.string.skip_driver_message))
                .setPositiveButton(getString(R.string.skip), { _, _ -> presenter.skipAddDriver() })
                .setNegativeButton(getString(R.string.input_number), { dialog, _ -> dialog.dismiss() })
                .create()
                .show()
    }

}