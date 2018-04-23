package test.p00.presentation.launcher.wizard.introductory.impl

import android.graphics.drawable.Drawable.createFromStream
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotterknife.bindView
import test.p00.R
import test.p00.presentation.activity.abs.AbsFragment
import test.p00.presentation.launcher.wizard.introductory.IntroductoryStepPresenter
import test.p00.presentation.launcher.wizard.introductory.IntroductoryStepVew
import test.p00.presentation.launcher.wizard.steps.impl.AddVehicleOnBoardingWizardFragment

class IntroductoryStepFragment : AbsFragment(), IntroductoryStepVew {

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

    private val vContentBackground: View by bindView(R.id.vContentBackground)
    private val vContentText: TextView by bindView(R.id.vContentText)
    private val vContentPicture: View by bindView(R.id.vContentPicture)
    private val vGo: View by bindView(R.id.vGo)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vContentBackground.background =
                createFromStream(context!!.assets.open("onboarding/wt_background.png"), null)
        vContentText.text = getString(R.string.onboarding_wizard_introductory_message)
        vContentPicture.background =
                createFromStream(context!!.assets.open("onboarding/wizard/wt_introductory.png"), null)

        vGo.setOnClickListener { displayFirstStep() }

        presenter.attachView(this)
    }

    override fun displayFirstStep() {
        fragmentManager!!
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                .replace(R.id.wizard_content,
                        AddVehicleOnBoardingWizardFragment.newInstance(),
                        AddVehicleOnBoardingWizardFragment.FRAGMENT_TAG)
                .commit() }

    override fun onDestroyView() {
        super.onDestroyView()

        presenter.detachView()
    }

}