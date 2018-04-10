package test.p00.presentation.onboarding.wizard.introductory

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotterknife.bindView
import test.p00.R
import test.p00.presentation.onboarding.wizard.steps.AddVehicleOnBoardingWizardFragment

class IntroductoryStepFragment : Fragment(), IntroductoryStepVew {

    companion object {

        const val FRAGMENT_TAG = "tag_IntroductoryStepFragment"

        fun newInstance(): Fragment = IntroductoryStepFragment().apply {
            arguments = Bundle.EMPTY
        }

    }

    private val presenter: IntroductoryStepPresenter by lazy {
        IntroductoryStepPresenterImpl()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
            = inflater.inflate(R.layout.view_onboarding_wizard_introductory, container, false)

    private val vGo: View by bindView(R.id.vGo)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vGo.setOnClickListener {
            displayFirstStep()
        }

        presenter.attachView(this)
    }

    override fun displayFirstStep() {
        fragmentManager!!
                .beginTransaction()
                .replace(R.id.wizard_content,
                        AddVehicleOnBoardingWizardFragment.newInstance(),
                        AddVehicleOnBoardingWizardFragment.FRAGMENT_TAG)
                .commit() }

    override fun onDestroyView() {
        super.onDestroyView()

        presenter.detachView()
    }

}