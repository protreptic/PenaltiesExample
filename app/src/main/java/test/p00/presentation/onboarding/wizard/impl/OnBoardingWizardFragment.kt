package test.p00.presentation.onboarding.wizard.impl

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import test.p00.R
import test.p00.presentation.impl.abs.AbsView
import test.p00.presentation.onboarding.wizard.OnBoardingWizardPresenter
import test.p00.presentation.onboarding.wizard.OnBoardingWizardView

class OnBoardingWizardFragment : AbsView(), OnBoardingWizardView {

    companion object {

        const val FRAGMENT_TAG = "tag_OnBoardingWizardFragment"

        fun newInstance(): Fragment = OnBoardingWizardFragment().apply {
            arguments = Bundle.EMPTY
        }

    }

    private val presenter: OnBoardingWizardPresenter by lazy {
        OnBoardingWizardPresenterImpl(
                OnBoardingWizardRouterImpl(fragmentManager, this))
    }

    override val targetLayout: Int = R.layout.view_onboarding_wizard

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.attachView(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        presenter.detachView()
    }

}