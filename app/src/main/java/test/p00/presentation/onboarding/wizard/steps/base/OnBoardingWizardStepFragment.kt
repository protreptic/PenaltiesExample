package test.p00.presentation.onboarding.wizard.steps.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.disposables.CompositeDisposable
import kotterknife.bindView
import test.p00.R
import test.p00.presentation.onboarding.wizard.steps.OnBoardingWizardStepPresenter
import test.p00.presentation.onboarding.wizard.steps.OnBoardingWizardStepPresenterImpl
import test.p00.presentation.onboarding.wizard.steps.OnBoardingWizardStepView
import test.p00.presentation.util.dismissKeyboard

abstract class OnBoardingWizardStepFragment : Fragment(), OnBoardingWizardStepView {

    protected val presenter: OnBoardingWizardStepPresenter by lazy {
        OnBoardingWizardStepPresenterImpl()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
            = inflater.inflate(R.layout.view_onboarding_wizard_step, container, false)

    protected val vBanner: ImageView by bindView(R.id.imageView)
    protected val vTitle: TextView by bindView(R.id.vTitle)
    protected val vNumber: EditText by bindView(R.id.editText2)
    protected val vForward: View by bindView(R.id.vNext)
    private val vSkip: View by bindView(R.id.vSkip)

    protected val disposables = CompositeDisposable()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
                        else -> validateInput(number)
                    }
                }, { }))

        vSkip.setOnClickListener { skip() }
    }

    protected abstract fun validateInput(input: String)

    override fun showValidationError(isValid: Boolean) {
        when (isValid) {
            true -> {
                vForward.isEnabled = true
                vNumber.setTextColor(ContextCompat.getColor(context!!, android.R.color.holo_green_dark))
            }
            else -> {
                vForward.isEnabled = false
                vNumber.setTextColor(ContextCompat.getColor(context!!, android.R.color.tertiary_text_dark))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        dismissKeyboard(activity)

        disposables.dispose()
        presenter.detachView()
    }

}