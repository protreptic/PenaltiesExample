package test.p00.presentation.onboarding.wizard.steps

import android.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.Toast
import com.jakewharton.rxbinding2.widget.RxTextView
import test.p00.R
import test.p00.presentation.launcher.LauncherFragment
import test.p00.presentation.onboarding.wizard.steps.base.OnBoardingWizardStepFragment

class AddDriverOnBoardingWizardStepFragment : OnBoardingWizardStepFragment() {

    companion object {

        const val FRAGMENT_TAG = "tag_AddDriverStepFragment"

        fun newInstance(): Fragment = AddDriverOnBoardingWizardStepFragment().apply {
            arguments = Bundle.EMPTY
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vTitle.setHint(R.string.input_valid_driver_license)

        disposables.add(
            RxTextView
                .afterTextChangeEvents(vNumber)
                .map { raw -> raw.editable().toString() }
                .subscribe({ number ->
                    when (number.isEmpty()) {
                        true -> {
                            vForward.isEnabled = false
                            vNumber.setTextColor(ContextCompat.getColor(context!!, android.R.color.darker_gray))
                        }
                        else -> presenter.validateDriver(number)
                    }
                }, { }))

        vForward.setOnClickListener {
            presenter.addDriver(getString(R.string.new_driver_license),
                vNumber.text.toString()) }

        presenter.attachView(this)
    }

    override fun forward() {
        skipInternal()
    }

    override fun skip() {
        AlertDialog.Builder(activity)
                .setMessage(getString(R.string.skip_driver_message))
                .setPositiveButton(getString(R.string.skip), { _, _ -> skipInternal() })
                .setNegativeButton(getString(R.string.back), { dialog, _ -> dialog.dismiss() })
                .create()
                .show()
    }

    private fun skipInternal() {
        fragmentManager!!
            .beginTransaction()
            .replace(R.id.wizard_content,
                LauncherFragment.newInstance(),
                LauncherFragment.FRAGMENT_TAG)
            .commit()
    }

    override fun showError() {
        Toast.makeText(context, getString(R.string.input_valid_driver_license), Toast.LENGTH_LONG).show()
    }

}