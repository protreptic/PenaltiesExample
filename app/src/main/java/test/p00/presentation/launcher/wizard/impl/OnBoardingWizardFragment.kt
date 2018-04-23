package test.p00.presentation.launcher.wizard.impl

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import test.p00.R
import test.p00.presentation.activity.abs.AbsFragment
import test.p00.presentation.launcher.wizard.OnBoardingWizardPresenter
import test.p00.presentation.launcher.wizard.OnBoardingWizardView

class OnBoardingWizardFragment : AbsFragment(), OnBoardingWizardView {

    companion object {

        const val FRAGMENT_TAG = "tag_OnBoardingWizardFragment"

        fun newInstance(): Fragment = OnBoardingWizardFragment().apply {
            arguments = Bundle.EMPTY
        }

    }

    private val presenter: OnBoardingWizardPresenter by lazy {
        OnBoardingWizardPresenterImpl(router = OnBoardingWizardRouterImpl(fragmentManager))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
            = inflater.inflate(R.layout.view_onboarding_wizard, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.attachView(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        presenter.detachView()
    }

}