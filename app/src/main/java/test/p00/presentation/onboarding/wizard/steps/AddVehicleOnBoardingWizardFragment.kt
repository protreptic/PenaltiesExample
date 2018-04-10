package test.p00.presentation.onboarding.wizard.steps

import android.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat.getColor
import android.view.View
import android.widget.Toast
import com.jakewharton.rxbinding2.widget.RxTextView
import test.p00.R
import test.p00.presentation.onboarding.wizard.steps.base.OnBoardingWizardStepFragment

class AddVehicleOnBoardingWizardFragment : OnBoardingWizardStepFragment() {

    companion object {

        const val FRAGMENT_TAG = "tag_AddVehicleStepFragment"

        fun newInstance(): Fragment = AddVehicleOnBoardingWizardFragment().apply {
            arguments = Bundle.EMPTY
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vTitle.setHint(R.string.input_valid_vehicle_number)

        disposables.add(
            RxTextView
                .afterTextChangeEvents(vNumber)
                .map { raw -> raw.editable().toString() }
                .subscribe({ number ->
                    when (number.isEmpty()) {
                        true -> {
                            vForward.isEnabled = false
                            vNumber.setTextColor(getColor(context!!, android.R.color.darker_gray))
                        }
                        else -> presenter.validateVehicle(number)
                    }
                }, { }))

        vForward.setOnClickListener {
            presenter.addVehicle(getString(R.string.my_car),
                vNumber.text.toString()) }

        presenter.attachView(this)
    }

    override fun forward() {
        fragmentManager!!
            .beginTransaction()
            .replace(R.id.wizard_content,
                AddVehicleLicenseOnBoardingWizardFragment.newInstance(),
                AddVehicleLicenseOnBoardingWizardFragment.FRAGMENT_TAG)
            .commit() }

    override fun skip() {
        AlertDialog.Builder(activity)
            .setMessage(getString(R.string.skip_vehicle_message))
            .setPositiveButton(getString(R.string.skip), { _, _ -> skipInternal() })
            .setNegativeButton(getString(R.string.back), { dialog, _ -> dialog.dismiss() })
            .create()
            .show()
    }

    private fun skipInternal() {
        fragmentManager!!
        .beginTransaction()
        .replace(R.id.wizard_content,
            AddDriverOnBoardingWizardStepFragment.newInstance(),
            AddDriverOnBoardingWizardStepFragment.FRAGMENT_TAG)
        .commit()
    }

    override fun showError() {
        Toast.makeText(context, getString(R.string.input_valid_vehicle_number), Toast.LENGTH_LONG).show()
    }

}