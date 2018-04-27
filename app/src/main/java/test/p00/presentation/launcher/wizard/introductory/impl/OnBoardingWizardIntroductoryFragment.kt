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
import test.p00.presentation.launcher.wizard.impl.OnBoardingWizardRouterImpl
import test.p00.presentation.launcher.wizard.introductory.OnBoardingWizardIntroductoryPresenter
import test.p00.presentation.launcher.wizard.introductory.OnBoardingWizardIntroductoryView

class OnBoardingWizardIntroductoryFragment : AbsFragment(), OnBoardingWizardIntroductoryView {

    companion object {

        const val FRAGMENT_TAG = "tag_OnBoardingWizardIntroductoryFragment"

        fun newInstance(): Fragment = OnBoardingWizardIntroductoryFragment().apply {
            arguments = Bundle.EMPTY
        }

    }

    private val presenter: OnBoardingWizardIntroductoryPresenter by lazy {
        OnBoardingWizardIntroductoryPresenterImpl(router = OnBoardingWizardRouterImpl(this, fragmentManager))
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
        vGo.setOnClickListener { presenter.displayBeginning() }

        presenter.attachView(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        presenter.detachView()
    }

}