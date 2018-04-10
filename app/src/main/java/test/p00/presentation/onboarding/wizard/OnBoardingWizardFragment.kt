package test.p00.presentation.onboarding.wizard

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import test.p00.R
import test.p00.presentation.onboarding.wizard.introductory.IntroductoryStepFragment

class OnBoardingWizardFragment : Fragment(), OnBoardingWizardView {

    companion object {

        const val FRAGMENT_TAG = "tag_OnBoardingWizardFragment"

        fun newInstance(): Fragment = OnBoardingWizardFragment().apply {
            arguments = Bundle.EMPTY
        }

    }

    private val presenter: OnBoardingWizardPresenter by lazy {
        OnBoardingWizardPresenterImpl()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
            = inflater.inflate(R.layout.view_onboarding_wizard, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.attachView(this)
    }

    override fun displayIntroductoryStep() {
        fragmentManager!!
                .beginTransaction()
                .replace(R.id.wizard_content,
                        IntroductoryStepFragment.newInstance(),
                        IntroductoryStepFragment.FRAGMENT_TAG)
                .commit() }

    override fun onDestroyView() {
        super.onDestroyView()

        presenter.detachView()
    }

}